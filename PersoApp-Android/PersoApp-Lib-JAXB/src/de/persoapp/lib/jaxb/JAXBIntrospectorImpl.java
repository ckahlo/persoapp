package de.persoapp.lib.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

public class JAXBIntrospectorImpl extends JAXBIntrospector {
	ContextImpl ctx;
	
	JAXBIntrospectorImpl(ContextImpl ctx) {
		this.ctx = ctx;
	}
	
	@Override
	public boolean isElement(Object object) {
		if(object instanceof JAXBElement)
			return true;
		if(object.getClass().getAnnotation(XmlRootElement.class) != null)
			return true;

		return false;
	}

	@Override
	public QName getElementName(Object jaxbElement) {
		if(jaxbElement == null)
			return null;
		
		if(jaxbElement instanceof JAXBElement) {
			return ((JAXBElement)jaxbElement).getName();
		} else {
			Class<?> clz = jaxbElement.getClass();
			XmlRootElement xre = clz.getAnnotation(XmlRootElement.class);
			XmlType xt = clz.getAnnotation(XmlType.class);
			if(xt != null) {
				String ns = xt.namespace();
				String name = xt.name();
				if("##default".equals(ns)) {
					XmlSchema xs = ContextImpl.getAnnotation(XmlSchema.class, jaxbElement.getClass().getPackage());
					if(xs != null) {
						ns = xs.namespace();
					}
				}
				if(name == null || name.length() == 0 || "##default".equals(ns)) {
					if(xre != null) {
						name = xre.name();
					} else {
						name = clz.getSimpleName();						
					}
				}
				
				return new QName(ns, name);
			}
		}
		return null;
	}

}
