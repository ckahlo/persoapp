package de.persoapp.lib.jaxb;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.attachment.AttachmentMarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;

import de.persoapp.core.util.Hex;

public class MarshallerImpl implements Marshaller {
	private static final String DEFAULT_VALUE = "##default";
	
	private ContextImpl ctx;
	private Transformer transformer;

	private NamespacePrefixMapper customNSPrefixMapper = null;
	private NamespacePrefixMapper defaultNSPrefixMapper = new NamespacePrefixMapper() {
		Map<String, String> prefixes = new HashMap<String, String>();
		public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
			int i = 0;
			/*
			 * don't ever return suggestions, return a prefix everytime
			 */
			if(namespaceUri == null)
				return null;
			String prefix = prefixes.get(namespaceUri);
			synchronized(prefixes) {
				if((prefix = prefixes.get(namespaceUri)) == null) {
					prefix = "ns" + (prefixes.size() + 1);
					//prefix = "ns" + (++i);
					prefixes.put(namespaceUri, prefix);
				}
			}
			return prefix;
		}
	};
	
	MarshallerImpl(ContextImpl ctx) throws JAXBException {
		this.ctx = ctx;
		try {
			this.transformer = this.ctx.getTransformerFactory().newTransformer();
		} catch (TransformerConfigurationException e) {
			throw new JAXBException(e);
		}
		
		/*
		 * move this to setProperty and adapt SUN-behaviour as far as possible
		 */
		transformer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION, "yes");
		transformer.setOutputProperty(javax.xml.transform.OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "no");
		//transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
		//transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	}

	private Element createElement(Node parent, String ns, String localPart) {
		Document dom = (parent instanceof Document) ? (Document)parent : parent.getOwnerDocument();

		if(DEFAULT_VALUE.equals(ns)) {
			if(parent.getPrefix() == null) {
				throw new NullPointerException();
			}
			ctx.log("########################" + parent.toString());
			return dom.createElementNS(parent.getNamespaceURI(), parent.getPrefix() + ":" + localPart);
		} else if(dom != parent && dom.lookupPrefix(ns) != null) {
			return dom.createElementNS(ns, dom.lookupPrefix(ns) + ":" + localPart);
		} else {
			String prefix = null;
			
			if(this.customNSPrefixMapper != null) {
				prefix = this.customNSPrefixMapper.getPreferredPrefix(ns, null, false);
			}
			
			if(prefix == null) {
				prefix = this.defaultNSPrefixMapper.getPreferredPrefix(ns, null, true);
			}
			
			if(prefix == null) {
				throw new NullPointerException();
			}
			
			return dom.createElementNS(ns, prefix + ":" + localPart);
		}
	}

	// object2Element
	// field2Element

	private void serializeField(Object input, Field f, Node me, Node parent) {
		Object value;
		try {
			//f = clz.getDeclaredField(property);
			if(!f.isAccessible()) {
				f.setAccessible(true);
			}
			value = f.get(input);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		
		XmlAttribute xa = f.getAnnotation(XmlAttribute.class);
		if(xa != null && value != null) {
			String ns = xa.namespace();
			String name = xa.name();
			if(DEFAULT_VALUE.equals(ns)) {
				ns = null;
			}
			if(DEFAULT_VALUE.equals(name)) {
				name = f.getName();
			}
			ctx.log("| " + ns + " | " + name + " = " + value.toString());
			//@XmlJavaTypeAdapter
			if(value instanceof Boolean) {
				value = ((Boolean)value) == true ? "1" : "0";
			}
			((Element)me).setAttributeNS(ns, name, value.toString());
				
			return;
		}
		
		XmlAnyAttribute xaa = f.getAnnotation(XmlAnyAttribute.class);
		if(xaa != null && value != null) {
			ctx.log("XAA: " + value.getClass() + " = " + value);
			Map<QName, String> anyAttr = (Map<QName, String>)value;
			for(QName attrName : anyAttr.keySet()) {
				((Element)me).setAttributeNS(attrName.getNamespaceURI(),
						attrName.getLocalPart(), anyAttr.get(attrName));
			}
			return;
		}
		
		XmlElement xe = f.getAnnotation(XmlElement.class);
		if(xe != null && (xe.required() || value != null)) {
			String ns = xe.namespace();
			String name = xe.name();
			ctx.log("XE: " + xe.namespace() + ":" + xe.name());
			if(DEFAULT_VALUE.equals(ns)) {
				XmlSchema xs = ContextImpl.getAnnotation(XmlSchema.class, f.getType().getPackage());
				ns = xs != null ? xs.namespace() : ns;
			}
			
			Class<?> valueType = null;
			XmlType xt = null;
			
			if(value != null) {
				valueType = value.getClass(); 
				xt = valueType.getAnnotation(XmlType.class); 
			}
			
			ctx.log("###########: " + xt + " vs. " + ns + " / " + name);
			
			if(value instanceof List) {
				for(Object item : ((List<?>)value)) {
					Element child = (Element)me.appendChild(createElement(me, ns, name));
					if(xt != null && !name.equals(xt.name())) {
						child.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type",
								child.lookupPrefix(ContextImpl.getAnnotation(XmlSchema.class,
										valueType.getPackage()).namespace()) + ":" + xt.name());						
					}
					
					serialize(item, child, me);
				}						
			} else {
				Element child = (Element)me.appendChild(createElement(me, ns, name));
				if(xt != null && !name.equals(xt.name())) {
					child.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "xsi:type",
							child.lookupPrefix(ContextImpl.getAnnotation(XmlSchema.class,
									valueType.getPackage()).namespace()) + ":" + xt.name());						
				}
				
				serialize(value, child, me);						
			}
			return;
		}
		
		XmlAnyElement xae = f.getAnnotation(XmlAnyElement.class);
		// lax!?
		if(xae != null && value != null) {
			if(value instanceof List) {
				Document dom = me.getOwnerDocument();
				for(Object item : (List<?>)value) {
					if(item instanceof Node) {
						me.appendChild(dom.importNode((Node)item, true));
					} else {
						ctx.log("XAE: UNKNOWN any-item: " + item.getClass() + " = " + item);
						serialize(item, null, me);
					}
				}
			} else {
				ctx.log("XAE: " + xae.value() + " / " + value.getClass() + " = " + value);
			}
				
			return;
		}
		
		//me.setTextContent(value.toString());
		
		try {
			ctx.log(f.getType() + "." + f.getName() + " = " + f.get(input));
		} catch (Exception e) { }
		
		serialize(value, (Element)me, parent);
	}
	
	private void serialize(Object input, Element me, Node parent) {
		ctx.log("P=" + parent + ", M=" + me + ", I=" + input);
		if(input == null)
			return;

		if(input instanceof JAXBElement) {
			JAXBElement<?> je = (JAXBElement<?>)input;
			ctx.log("JAXBElement: " + je.getName() + " > " + je.getDeclaredType() + " (" + je.getScope() + ")");
			if(me == null) {
				me = (Element)parent.appendChild(createElement(parent, je.getName().getNamespaceURI(), je.getName().getLocalPart()));
			}
			input = je.getValue();
		}
		Class<?> clz = input.getClass();
		
		ctx.log(">>>>>>>>>>>> " + clz.getName() + " = " + input);
		XmlRootElement xre = clz.getAnnotation(XmlRootElement.class); 
		if(xre != null) {		// a new document ... in theory
			if(me == null) {
				String ns = xre.namespace();
				ns = ns == null ? ns : ContextImpl.getAnnotation(XmlSchema.class, clz.getPackage()).namespace();
				me = (Element)parent.appendChild(createElement(parent, ns, xre.name()));
			}
		}
		
		XmlType xt = clz.getAnnotation(XmlType.class);
		if(xt != null && me == null) {
			me = (Element)parent.appendChild(createElement(parent, xt.namespace(), xt.name()));
		}
		
		ctx.log(clz + " = " + input);
		Package pkg = clz.getPackage();
		
		if(ContextImpl.getAnnotations(pkg) != null) {
			for(Annotation a : ContextImpl.getAnnotations(pkg)) {
				ctx.log("P " + a.toString());
			}			
		}

		for(Annotation a : clz.getAnnotations()) {
			ctx.log("C " + a.toString());
		}

		for(Field f : clz.getDeclaredFields()) {
			for(Annotation a : f.getAnnotations()) {
				ctx.log("F " + f.getName() + ": " + a.toString());
			}
		}

		for(Method m : clz.getDeclaredMethods()) {
			for(Annotation a : m.getAnnotations()) {
				ctx.log("M " + m.getName() + ": " + a.toString());
			}			
		}
		
		if(xt != null) {
			Set<String> doneFields = new HashSet<String>();

			for(String property : xt.propOrder()) {
				if(property.length() <= 0) {
					continue;
				}
				doneFields.add(property);
				try {
					serializeField(input, clz.getDeclaredField(property), me, parent);
				} catch (NoSuchFieldException e) {
					System.err.println(e.toString() + ": " + clz.getName() + "." + property);
				}
			}
			
			for(Field f : clz.getDeclaredFields()) {
				if(!doneFields.contains(f.getName())) {
					serializeField(input, f, me, parent);					
				}
			}
			
			while((clz = clz.getSuperclass()) != Object.class) {
				XmlType xt2 = clz.getAnnotation(XmlType.class);
				if(xt2 != null) {
					doneFields = new HashSet<String>();
					for(String property : xt2.propOrder()) {
						if(property.length() <= 0) {
							continue;
						}
						doneFields.add(property);
						try {
							serializeField(input, clz.getDeclaredField(property), me, parent);
						} catch (NoSuchFieldException e) {
							ctx.log("NSFE: " + property + " in " + clz.getName());
							e.printStackTrace();
						}
					}
					
					for(Field f : clz.getDeclaredFields()) {
						if(!doneFields.contains(f.getName())) {
							serializeField(input, f, me, parent);					
						}
					}
				}				
			}			
		} else if(input instanceof List) {
			for(Object item : ((List<?>)input)) {
				//serialize(item, null, me != null ? me : parent);
				serialize(item, me, parent);
			}
		} else {
			if(clz == byte[].class) {
				input = Hex.toString((byte[])input);
			} else {
				ctx.log("UNKNOWN Type: " + clz + " = " + input);
			}
			
			if(me != null) {
				me.setTextContent(input.toString());
			} else {
				if(input != null && xt == null && xre == null) {
					if(input instanceof String) {
						parent.setTextContent(input.toString());
					} else if(input.getClass().isPrimitive()) {
						parent.setTextContent(String.valueOf(input));
					}
				}
				ctx.log("/////////////// " + input.getClass() + " = " + input.toString());
				//throw new IllegalStateException(input.toString());
			}
		}
	}
	
	@Override
	public void marshal(Object jaxbElement, Result result) throws JAXBException {		
		Document dom = this.ctx.getDocumentBuilder().newDocument();
		this.marshal(jaxbElement, dom);
		dom.normalizeDocument();
		
		try {
			transformer.transform(new DOMSource(dom), result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void marshal(Object jaxbElement, OutputStream os)
			throws JAXBException {
		this.marshal(jaxbElement, new StreamResult(os));
	}

	@Override
	public void marshal(Object jaxbElement, File output) throws JAXBException {
		try {
			this.marshal(jaxbElement, new FileOutputStream(output));
		} catch (FileNotFoundException e) {
			throw new JAXBException(e);
		}
	}

	@Override
	public void marshal(Object jaxbElement, Writer writer) throws JAXBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void marshal(Object jaxbElement, ContentHandler handler)
			throws JAXBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void marshal(Object jaxbElement, Node node) throws JAXBException {
		serialize(jaxbElement, null, node);
	}

	@Override
	public void marshal(Object jaxbElement, XMLStreamWriter writer)
			throws JAXBException {
		// TODO Auto-generated method stub
	}

	public void marshal(Object jaxbElement, XMLEventWriter writer)
			throws JAXBException {
		// TODO Auto-generated method stub
	}

	@Override
	public Node getNode(Object contentTree) throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperty(String name, Object value) throws PropertyException {
		if("xml.internal.bind.namespacePrefixMapper".equals(name)) {
			this.customNSPrefixMapper = (NamespacePrefixMapper)value;
		}
	}

	@Override
	public Object getProperty(String name) throws PropertyException {
		if("xml.internal.bind.namespacePrefixMapper".equals(name)) {
			return this.customNSPrefixMapper;
		}
		return null;
	}

	@Override
	public void setEventHandler(ValidationEventHandler handler)
			throws JAXBException {
		// TODO Auto-generated method stub
	}

	@Override
	public ValidationEventHandler getEventHandler() throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAdapter(XmlAdapter adapter) {
		// TODO Auto-generated method stub
	}

	@Override
	public <A extends XmlAdapter> void setAdapter(Class<A> type, A adapter) {
		// TODO Auto-generated method stub
	}

	@Override
	public <A extends XmlAdapter> A getAdapter(Class<A> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttachmentMarshaller(AttachmentMarshaller am) {
		// TODO Auto-generated method stub
	}

	@Override
	public AttachmentMarshaller getAttachmentMarshaller() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSchema(Schema schema) {
		// TODO Auto-generated method stub
	}

	@Override
	public Schema getSchema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setListener(Listener listener) {
		// TODO Auto-generated method stub
	}

	@Override
	public Listener getListener() {
		// TODO Auto-generated method stub
		return null;
	}
}
