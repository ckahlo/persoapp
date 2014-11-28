/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
 *
 * Authors Christian Kahlo, Ralf Wondratschek
 *
 * All Rights Reserved.
 *
 * Contact: PersoApp, http://www.persoapp.de
 *
 * @version 1.0, 30.07.2013 13:50:47
 *
 *          This file is part of PersoApp.
 *
 *          PersoApp is free software: you can redistribute it and/or modify it
 *          under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation, either version 3 of the
 *          License, or (at your option) any later version.
 *
 *          PersoApp is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details.
 *
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with PersoApp. If not, see
 *          <http://www.gnu.org/licenses/>.
 *
 *          Diese Datei ist Teil von PersoApp.
 *
 *          PersoApp ist Freie Software: Sie können es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          späteren veröffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 *
 *          PersoApp wird in der Hoffnung, dass es nützlich sein wird, aber OHNE
 *          JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License für weitere
 *          Details.
 *
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 *
 */
package de.persoapp.lib.jaxb;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.attachment.AttachmentUnmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;


public class UnmarshallerImpl implements Unmarshaller {
	private static final String DEFAULT_VALUE = "##default";
	
	private ContextImpl ctx;
	
	UnmarshallerImpl(ContextImpl ctx) {
		this.ctx = ctx;
	}

	private Object getField(Field f, Object instance) {
		try {
			if(!f.isAccessible()) {
				f.setAccessible(true);
			}
			return f.get(instance);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void setField(Field f, Object instance, Object value) {
		try {
			if(!f.isAccessible()) {
				f.setAccessible(true);
			}
			if(value instanceof String && f.getType() == Boolean.class) {
				value = Boolean.parseBoolean((String)value);
			}
				
			f.set(instance, value);
		} catch (IllegalArgumentException e) {
			System.err.println(f.getClass() + "." + f.getType() + " ~ " + value);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private void injectData(Node node, Object instance) {
		if(instance == null)
			throw new NullPointerException("instance is null");

		Class<?> clz = instance.getClass();
		
		List<Class<?>> classes = new ArrayList<Class<?>>();
		classes.add(clz);
		while((clz = clz.getSuperclass()) != Object.class) {
			classes.add(clz);
		}
		
		for(Class<?> c : classes) {
			ctx.log("INJECT-START: " + c + " < " + instance + " /Input: " + node.getClass() + " = " + node);
			
			for(Annotation a : c.getAnnotations()) {
				ctx.log("C " + a.toString());
			}		
			
			for(Field f : c.getDeclaredFields()) {				
				Class<?> type = f.getType();

				/*
				 * handle simple XML-Attributes, it's always a simple type (assume String in the moment)
				 */
				XmlAttribute xa = f.getAnnotation(XmlAttribute.class);
				if(xa != null) {
					Object value = ((Element)node).getAttributeNS(xa.namespace(), xa.name());

					if(value != null) {
						((Element)node).removeAttributeNS(xa.namespace(), xa.name());
						setField(f, instance, value);
					}
					
					continue;
				}
				
				/*
				 * put any attributes here not known
				 */
				XmlAnyAttribute xaa = f.getAnnotation(XmlAnyAttribute.class);
				if(xaa != null) {
					@SuppressWarnings("unchecked")
					Map<QName, String> anyAttr = (Map<QName, String>) getField(f, instance);
					NamedNodeMap nnm = ((Element)node).getAttributes();
					for(int i = 0; i < nnm.getLength(); i++) {
						Node attr = nnm.item(i);
						anyAttr.put(new QName(attr.getNamespaceURI(), attr.getLocalName()), attr.getTextContent());
					}
					
					continue;
				}

				
				/*
				 * plain XML-values
				 * 
				 */
				XmlValue xv = f.getAnnotation(XmlValue.class);
				XmlSchemaType xst = f.getAnnotation(XmlSchemaType.class);
				// do short-hand for well-known classes, generalize to simpleType, String, BigInteger, BigDecimal, Date, etc.
				if(xv != null) {
					//XmlSchemaType.DEFAULT //?
					ctx.log("XV: " + xv + " / XST: " + xst + " for " + node.getTextContent());
					setField(f, instance, ContextImpl.convertSchemaType(node, xst, type));
					
					continue;
				}
								
				for(Annotation a : f.getAnnotations()) {
					ctx.log("F " + type + " " + f.getName() + ": " + a.toString());
				}
				
				/*
				 * complex values
				 * 
				 */
				
				XmlElement xe = f.getAnnotation(XmlElement.class);				
				if(xe != null) {
					String ns = xe.namespace();
					String name = xe.name();
					if(DEFAULT_VALUE.equals(ns)) {
						ns = node.getNamespaceURI();
						if(type.getPackage() != null) {
							XmlSchema xs = ContextImpl.getAnnotation(XmlSchema.class, type.getPackage());
							if(xs != null)
								ns = xs.namespace();
							
						}							
					}
					
					if(f.getType() == List.class) {
						ctx.log("IS LIST: " + f.getType() + " " + type);
						@SuppressWarnings("unchecked")
						List<Object> list = (List<Object>) getField(f, instance);
						if(list == null) {
							list = new ArrayList<Object>();
							setField(f, instance, list);
						}
												
						ctx.log("GENERIC? " + f.getGenericType());
						
						Object t1 = ((ParameterizedType)f.getGenericType()).getActualTypeArguments()[0];
						if(t1 instanceof GenericArrayType) {
							type = (Class<?>) ((GenericArrayType)t1).getGenericComponentType();
							type = Array.newInstance(type, 0).getClass();
						} else  {
							type = (Class<?>) t1;
						}
					}
					
					ctx.log("IS TYPE: " + type + " / " + ns + ":" + name);
					
					NodeList nodeList = ((Element)node).getElementsByTagNameNS(ns, name);
					Node parent = node.getParentNode();
					if(parent != null) {
						//parent.removeChild(node);
					}

					for(int i = 0; i < nodeList.getLength(); i++) {
						Node n1 = nodeList.item(i);
						//((Element)node).removeChild(n1);
						Object value = null;
						

						// do short-hand for well-known classes, generalize to simpleType, String, BigInteger, BigDecimal, Date, etc.
						if(type == String.class || type == byte[].class) {
							value = ContextImpl.convertSchemaType(n1, null, type);
							
							if(f.getType() == List.class) {
								@SuppressWarnings("unchecked")
								List<Object> list = (List<Object>) getField(f, instance);
								list.add(value);
							} else {
								setField(f, instance, value);
							}

							continue;
						}
						
						// handle simple types within elements
						xst = f.getAnnotation(XmlSchemaType.class);
						if(xst != null ) {
							value = ContextImpl.convertSchemaType(n1, xst, type);
						}
						
						if(value == null) {
							String xsiType = ((Element)n1).getAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type");
							if(xsiType != null && xsiType.length() > 0) {
								int j = xsiType.indexOf(':');
								String prefix = null;
								if(j >= 0) {
									prefix = xsiType.substring(0, j);
									ctx.log("PREFIX: " + prefix);									
								}							
								xsiType = xsiType.substring(j + 1);
								
								value = ctx.createType(new QName(n1.lookupNamespaceURI(prefix), xsiType));
								if(value == null) {
									ctx.log("xsi-type not found");
								}
							} 
						}
						
						if(value == null && type.getAnnotation(XmlType.class) != null) {
							try {
								value = type.newInstance();
								try {
									Field valueField = type.getDeclaredField("value");
									xst = valueField.getAnnotation(XmlSchemaType.class); 
									if(xst != null) {
										setField(valueField, value,
												ContextImpl.convertSchemaType(n1, xst, type));						
									} else {
										ctx.log("##### ERROR: " + f + " " + value + " " + n1.getTextContent());
									}									
								} catch(NoSuchFieldException nsfe){
								}
							} catch (InstantiationException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
						
						if(value == null) {
							value = ctx.createType(new QName(n1.getNamespaceURI(), n1.getLocalName()));
						}
																		
						ctx.log(n1 + " -> " + value);
						if(f.getType() == List.class) {
							@SuppressWarnings("unchecked")
							List<Object> list = (List<Object>) getField(f, instance);
							list.add(value);
						} else {
							setField(f, instance, value);
						}

						injectData(n1, value);
					}
					
					continue;
				}
				
				
				XmlAnyElement xae = f.getAnnotation(XmlAnyElement.class);
				if(xae != null) {
					List<Object> list = new ArrayList<Object>();
					setField(f, instance, list);
					NodeList nl = node.getChildNodes();
					for(int i = 0; i < nl.getLength(); i++) {
						Node n = nl.item(i);
						Object value = null;
						String xsiType = ((Element)n).getAttributeNS("http://www.w3.org/2001/XMLSchema-instance", "type");
						if(xsiType != null && xsiType.length() > 0) {
							ctx.log("XSI-TYPE: " + xsiType);
							int j = xsiType.indexOf(':');
							String prefix = null;
							if(j >= 0) {
								prefix = xsiType.substring(0, j);
								ctx.log("PREFIX: " + prefix);									
							}							
							xsiType = xsiType.substring(j + 1);
							
							value = ctx.createType(new QName(n.lookupNamespaceURI(prefix), xsiType));
							value = new JAXBElement(new QName(n.getNamespaceURI(), n.getLocalName()),
									value.getClass(), JAXBElement.GlobalScope.class, value);
						} else {
							value = ctx.createType(new QName(n.getNamespaceURI(), n.getLocalName()));
						}
						
						ctx.log("XAE: " + n + " -> " + value);
						if(value != null) {
							list.add(value);
							if(value instanceof JAXBElement) {
								value = ((JAXBElement)value).getValue();
							} 
							injectData(n, value);
						} else {
							list.add(n.cloneNode(true));
						}
					}
					
				} else  {
					ctx.log("*********** " + f.getName() + ": " + f.getType() + " / " + getField(f, instance) + " -> " + node.getTextContent());
					if(node.getTextContent() != null && node.getTextContent().length() > 0) {
						setField(f, instance, node.getTextContent());
					}
				}
			}
			
			for(Method m : c.getDeclaredMethods()) {
				for(Annotation a : m.getAnnotations()) {
					ctx.log("M " + m.getName() + ": " + a.toString());
				}			
			}
		}
	}
	
	
	@Override
	public Object unmarshal(File f) throws JAXBException {
		try {
			return unmarshal(this.ctx.getDocumentBuilder().parse(f));
		} catch (SAXException e) {
			throw new JAXBException(e);
		} catch (IOException e) {
			throw new JAXBException(e);
		}
	}

	@Override
	public Object unmarshal(InputStream is) throws JAXBException {
		try {
			return unmarshal(this.ctx.getDocumentBuilder().parse(is));
		} catch (SAXException e) {
			throw new JAXBException(e);
		} catch (IOException e) {
			throw new JAXBException(e);
		}
	}

	@Override
	public Object unmarshal(Reader reader) throws JAXBException {
		return unmarshal(new InputSource(reader));
	}

	@Override
	public Object unmarshal(URL url) throws JAXBException {
		try {
			return unmarshal(url.openStream());
		} catch (IOException e) {
			throw new JAXBException(e);
		}
	}

	@Override
	public Object unmarshal(InputSource source) throws JAXBException {
		try {
			return unmarshal(this.ctx.getDocumentBuilder().parse(source));
		} catch (IOException e) {
			throw new JAXBException(e);
		} catch (SAXException e) {
			throw new JAXBException(e);
		}
	}

	@Override
	public Object unmarshal(Node node) throws JAXBException {
		return unmarshal(node, null);
	}

	@Override
	public <T> JAXBElement<T> unmarshal(Node node, Class<T> declaredType)
			throws JAXBException {

		if(node instanceof Document) {
			node = ((Document)node).getDocumentElement();
		}
		ctx.log("declared type: " + declaredType);
		ctx.log(node.getClass() + " = " + node);
		ctx.log(node.getNamespaceURI() + " : " + node.getLocalName());
		//Element elem = (Element)node;
		
		//JAXBElement<T> je = (JAXBElement<T>)
			//ctx.createElement(node.getNamespaceURI(), node.getLocalName());
		QName name = new QName(node.getNamespaceURI(), node.getLocalName());
		Object value = ctx.createType(name);
		if(value == null) {
			try {
				value = declaredType.newInstance();
			} catch (Exception e) {
				throw new UnmarshalException(e);
			}
		}
		JAXBElement<T> je = new JAXBElement<T>(name, declaredType,
				JAXBElement.GlobalScope.class, (T)value);
		
		ctx.log(je + ", " + je.getName() + " = " + je.getValue());
		injectData(node, je.getValue());
		
		return je;
	}

	@Override
	public Object unmarshal(Source source) throws JAXBException {
		return unmarshal(source, null);
	}

	@Override
	public <T> JAXBElement<T> unmarshal(Source source, Class<T> declaredType)
			throws JAXBException {
		Node node = null;
		try {
			if(source instanceof SAXSource) {
				node = this.ctx.getDocumentBuilder().parse(((SAXSource)source).getInputSource());
			} else if(source instanceof StreamSource) {
				node = this.ctx.getDocumentBuilder().parse(((StreamSource)source).getInputStream());
			} else {
				node = ((DOMSource)source).getNode();
			}
		} catch (SAXException e) {
			throw new JAXBException(e);
		} catch (IOException e) {
			throw new JAXBException(e);
		}
		
		if(node != null) {
			return unmarshal(node, declaredType);
		} else
			return null;
	}

	@Override
	public Object unmarshal(XMLStreamReader reader) throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> JAXBElement<T> unmarshal(XMLStreamReader reader,
			Class<T> declaredType) throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object unmarshal(XMLEventReader reader) throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> JAXBElement<T> unmarshal(XMLEventReader reader,
			Class<T> declaredType) throws JAXBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UnmarshallerHandler getUnmarshallerHandler() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValidating(boolean validating) throws JAXBException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValidating() throws JAXBException {
		// TODO Auto-generated method stub
		return false;
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
	public void setProperty(String name, Object value) throws PropertyException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getProperty(String name) throws PropertyException {
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
	public void setAttachmentUnmarshaller(AttachmentUnmarshaller au) {
		// TODO Auto-generated method stub

	}

	@Override
	public AttachmentUnmarshaller getAttachmentUnmarshaller() {
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
