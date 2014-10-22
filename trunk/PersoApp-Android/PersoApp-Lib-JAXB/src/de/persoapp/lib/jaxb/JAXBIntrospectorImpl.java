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
