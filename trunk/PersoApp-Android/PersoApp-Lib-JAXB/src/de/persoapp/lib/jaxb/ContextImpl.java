package de.persoapp.lib.jaxb;

import org.w3c.dom.Node;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Validator;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactory;

import de.persoapp.core.util.Hex;


public class ContextImpl extends javax.xml.bind.JAXBContext implements Serializable {

	private static final long serialVersionUID = -5879232270421880711L;
	
	private transient DocumentBuilderFactory dbf;
	private transient DocumentBuilder db;
	private transient TransformerFactory tf;
	
	private Map<String, Object> objectFactories;
	private Map<String, Class<?>> types;
	private Map<String, Method> factoryMethods;

	private boolean lazyBinding;

	private ContextImpl() {
		dbf = DocumentBuilderFactory.newInstance();
		try {
			dbf.setNamespaceAware(true);
			this.db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			new JAXBException(e);
		}
		this.tf = TransformerFactory.newInstance();
		
		this.objectFactories = new HashMap<String, Object>();
		this.types = new HashMap<String, Class<?>>();
		this.factoryMethods = new HashMap<String, Method>();
	}
	
	void log(String msg) {
		if(false)
		{
			System.out.println(msg);
		}
	}
	
	@Override
	public Unmarshaller createUnmarshaller() throws JAXBException {
		return new UnmarshallerImpl(this);
	}

	@Override
	public Marshaller createMarshaller() throws JAXBException {
		return new de.persoapp.lib.jaxb.MarshallerImpl(this);
	}

	@Override
	public Validator createValidator() throws JAXBException {
		throw new UnsupportedOperationException("Validator not supported.");
	}

	@Override
	public JAXBIntrospector createJAXBIntrospector() {
		return new de.persoapp.lib.jaxb.JAXBIntrospectorImpl(this);
	}
	
	public static Annotation[] getAnnotations(Package p) {
		if(p == null) {
			return null;
		}

		try {
			Class<?> pi = Class.forName(p.getName() + ".package-info");
			return pi.getAnnotations();
		} catch (ClassNotFoundException e) { }
		return null;
	}
	
	public static <A extends Annotation> A  getAnnotation(Class<A> annotation, Package p) {
		if(p == null) {
			return null;
		}

		try {
			Class<?> pi = Class.forName(p.getName() + ".package-info");
			return pi.getAnnotation(annotation);
		} catch (ClassNotFoundException e) { }
		return null;
	}
	
	public static <A extends Annotation> A  getAnnotation(Class<A> annotation, Class<?> c) {
		return c.getAnnotation(annotation);
	}
	
	public static <A extends Annotation> A  getAnnotation(Class<A> annotation, Method m) {
		return m.getAnnotation(annotation);
	}
	
	public static <A extends Annotation> A  getAnnotation(Class<A> annotation, Field f) {
		return f.getAnnotation(annotation);
	}
	
	public static JAXBContext createContext(Class<?>[] classes, Map<String,?> properties)
	throws JAXBException {
		ContextImpl ctx = new ContextImpl();
		
		if(classes == null) {
			ctx.lazyBinding = true;
		} else {
			for(Class<?> c : classes) {
				ctx.addClass(c);
			}			
		}
		
		System.out.println("OF: " + ctx.objectFactories.size() + ": " + ctx.objectFactories);
		System.out.println("DT: " + ctx.types.size() + ": " + ctx.types);
		System.out.println("FM: " + ctx.factoryMethods.size() + ": " + ctx.factoryMethods);
		System.out.println();
		
		return ctx;
	}

	void addClass(Class<?> clz) {
		XmlRegistry xr = clz.getAnnotation(XmlRegistry.class);
		if(xr != null) {
			XmlSchema xs = ContextImpl.getAnnotation(XmlSchema.class, clz.getPackage());
			if(xs != null) {
				if(this.objectFactories.get(xs.namespace()) == null) {
					try {
						this.objectFactories.put(xs.namespace(), clz.newInstance());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					return;
				}
			}
			
			for(Method m : clz.getDeclaredMethods()) {
				if(Modifier.isPublic(m.getModifiers())) {
					XmlElementDecl xed = m.getAnnotation(XmlElementDecl.class);
					if(xed != null) {
						String ns = xed.namespace();
						String name = xed.name();
						addClass(m.getParameterTypes()[0]);
						this.factoryMethods.put(new QName(ns, name).toString(), m);
					} else if(m.getParameterTypes().length == 0) {
						Class<?> retType = m.getReturnType();
						XmlType xt = retType.getAnnotation(XmlType.class);
						if(xt != null) {
							String ns = xt.namespace();
							String name = xt.name();
							if("##default".equals(ns)) {
								ns = getAnnotation(XmlSchema.class, clz.getPackage()).namespace();
							}
							this.factoryMethods.put(new QName(ns, name).toString(), m);
						}
					}
				}
			}
		}
		
		XmlSeeAlso xsa = clz.getAnnotation(XmlSeeAlso.class);
		if(!lazyBinding && xsa != null) {
			for(Class<?> c : xsa.value()) {
				addClass(c);
			}
		}
		
		XmlType xt = clz.getAnnotation(XmlType.class);
		XmlRootElement xre = clz.getAnnotation(XmlRootElement.class);
		//@XmlAccessorType
		if(xt != null) {
			String ns = xt.namespace();
			String name = xre != null ? xre.name() : xt.name();
			if("##default".equals(ns)) {
				XmlSchema xs = getAnnotation(XmlSchema.class, clz.getPackage());
				if(xs != null) {
					ns = xs.namespace();
				} else {
					log("no namespace: " + clz.getName());
				}
			}
			this.types.put(new QName(ns, name).toString(), clz);
		}
	}
	
	Object createFactoryType(QName name) {
		Method m = this.factoryMethods.get(name.toString());
		log(name + " -> " + this.objectFactories.get(name.getNamespaceURI()) + "." + m);
		if(m == null) {
			log("NO FACTORY METHOD.");
			return null;
		}
		Object instance = null;
		try {
			if(m.getParameterTypes().length > 0 && m.getParameterTypes()[0] == byte[].class) 
				 return null;
			 
			instance = m.invoke(this.objectFactories.get(name.getNamespaceURI()),
					 m.getParameterTypes().length == 1 ? new Object[] { m.getParameterTypes()[0].newInstance() } : null);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return instance;
	}
	
	Object createType(QName name) {
		Class<?> type = this.types.get(name.toString());
		Object instance = null;
		if(type != null) {
			try {
				instance = type.newInstance();
			} catch(Exception e) {
				e.printStackTrace();
			}			
		} else {
			instance = createFactoryType(name);
		}
		
		if(instance == null) {
			if(lazyBinding) {
				log("LAZY-BIND: " + name);
				String ns = name.getNamespaceURI();
				String ln = name.getLocalPart();

				ns = ns.replace('-', '_');
				ln = ln.replace('-', '_').replace('/', '_').replace('.', '_');
				
				ns = convertName(ns);
				
				log("LB: " + ns + "." + ln);
				try {
					type = Class.forName(ns + '.' + ln);
					this.addClass(type);
					instance = type.newInstance();
				} catch (Exception e) {
					try {
						type = Class.forName(ns + ".ObjectFactory");
						this.addClass(type);
						instance = createFactoryType(name);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}
		
		if(instance == null) {
			log("TYPE NOT FOUND FOR: " + name.toString());			
		}
		
		return instance;
	}
	
	private String convertName(String name) {
		URI u = URI.create(name);
		
		if(!"urn".equalsIgnoreCase(u.getScheme())) {
			name = convertUrlPart(u.getHost().replace("www", ""), false) + '.' + convertUrlPart(u.getPath(), true);
		} else {
			name = convertUrlPart(u.getSchemeSpecificPart(), true);
		}
		
		return name;
	}

	private String convertUrlPart(String part, boolean forward) {
		StringTokenizer st = new StringTokenizer(part, "/:.");
		StringBuffer sb = new StringBuffer();
		String last = null;
		while(st.hasMoreElements()) {
			String s = (String) st.nextElement();
			if(s.equals(last)) {
				continue;
			}
			last = s;
			if(forward) {
				if(sb.length() > 0) {
					sb.append('.');
				}
				if(!Character.isJavaIdentifierStart(s.charAt(0))) {
					sb.append('_');
				}
				sb.append(s);
			} else {
				if(sb.length() > 0) {
					sb.insert(0, '.');
				}
				sb.insert(0, s);
				if(!Character.isJavaIdentifierStart(s.charAt(0))) {
					sb.insert(0, '_');
				}
			}
		}
		
		return sb.toString();		
	}
	
	JAXBElement<?> createElement(String namespace, String localname) {
		QName name = new QName(namespace, localname);
		
		Object value = createType(name);
				
		return null;
	}
	
	DocumentBuilder getDocumentBuilder() {
		return this.db;
	}
	
	TransformerFactory getTransformerFactory() {
		return this.tf;
	}

	public static Object convertSchemaType(Node node, XmlSchemaType xst, Class<?> type) {
		if((xst != null && "anyURI".equals(xst.name())) || type == String.class) {
			return node.getTextContent();
		} else if((xst != null && "hexBinary".equals(xst.name())) || type == byte[].class) {
			return Hex.fromString(node.getTextContent());
		} else {
			System.out.println("MISSING XML-SchemaType: " + xst.namespace() + " / " + xst.name() + " for " + node.getTextContent());
		}
		return null;
	}
	
}
