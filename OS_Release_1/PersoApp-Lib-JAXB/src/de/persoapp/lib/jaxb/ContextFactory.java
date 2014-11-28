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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRegistry;

public class ContextFactory {
	public static JAXBContext createContext(String contextPath, ClassLoader classLoader, Map<String, ?> properties)
	throws JAXBException {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		StringTokenizer tokens = new StringTokenizer(contextPath, ":");
		
		boolean foundObjectFactory;
		while (tokens.hasMoreTokens()) {
			foundObjectFactory = false;
			String pkg = tokens.nextToken();

			final Class<?> o;
			try {
				o = classLoader.loadClass(pkg + ".ObjectFactory");
				if(o.getAnnotation(XmlRegistry.class) != null) {
					classes.add(o);
					foundObjectFactory = true;					
				}
			} catch (ClassNotFoundException e) {}

			if (!foundObjectFactory) {
				throw new JAXBException("No ObjectFactory in context-path: " + pkg);
			}
		}

		return createContext(classes.toArray(new Class[classes.size()]), properties);
	}

	public static JAXBContext createContext(Class<?>[] classes, Map<String,?> properties)
	throws JAXBException {
        if(properties == null)
            properties = Collections.emptyMap();
        else
            properties = new HashMap<String,Object>(properties);

		return ContextImpl.createContext(classes, properties);
	}
}
