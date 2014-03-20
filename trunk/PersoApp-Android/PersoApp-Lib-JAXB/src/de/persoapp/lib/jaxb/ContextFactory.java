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
