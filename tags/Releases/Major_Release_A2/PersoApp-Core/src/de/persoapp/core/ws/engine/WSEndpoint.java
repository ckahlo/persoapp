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
package de.persoapp.core.ws.engine;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.namespace.QName;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceProvider;

/**
 * <p>
 * The WSEndpoint provides an interface of the functions of an
 * WebService-Endpoint.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public final class WSEndpoint {
	
	/**
	 * The target namespace of the webservice.
	 */
	private final String						targetNamespace;
	
	/**
	 * The name of the webservice.
	 */
	private final String						serviceName;
	
	/**
	 * The port name of the webservice.
	 */
	private final String						portName;
	
	/**
	 * The port of the webservice.
	 */
	private final Object						port;
	
	/**
	 * The class of the port.
	 */
	private final Class<?>						portClass;
	
	/**
	 * The class of the used interface.
	 */
	private Class<?>							interfaceClass;
	
	/**
	 * The store for the methods of the webservice.
	 */
	private final Map<String, Method>			methodMap	= new HashMap<String, Method>();
	
	/**
	 * The classes which are bound by the JAXB implementation.
	 */
	private final Set<Class<? extends Object>>	xmlSeeAlso	= new HashSet<Class<? extends Object>>();

	/**
	 * Creates a new instance of the {@link WSEndpoint}. The <em>impl</em>
	 * parameter refers to the port of the endpoint.
	 * 
	 * @param impl - The implementation of the webservice.
	 */
	public WSEndpoint(final Object impl) {
		port = impl;
		portClass = port.getClass();
		final boolean isProvider = verifyImplementorClass(portClass);

		final WebServiceProvider wsProvider = portClass.getAnnotation(WebServiceProvider.class);
		final WebService ws = portClass.getAnnotation(WebService.class);

		if (isProvider) {
			System.out.println(wsProvider);
			throw new IllegalArgumentException();
		}

		targetNamespace = ws.targetNamespace();
		serviceName = new QName(targetNamespace, ws.serviceName()).toString();
		portName = new QName(targetNamespace, ws.portName()).toString();

		System.out.println("Service: " + serviceName + ", Port: " + portName);
		//System.out.println("Name: " + ws.name()); // only used in interfaces

		if (ws.endpointInterface() != null && ws.endpointInterface().length() > 0) {
			try {
				interfaceClass = Class.forName(ws.endpointInterface());
			} catch (final ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			for (final Class<?> wsif : portClass.getInterfaces()) {
				final WebService ws0 = wsif.getAnnotation(WebService.class);
				if (ws0 != null) {
					interfaceClass = wsif;
					break;
				}
			}
		}

		try {
			processMethods(portClass);
		} catch (final Exception e) {
		}

		if (interfaceClass != null) {
			try {
				processMethods(interfaceClass);
			} catch (final Exception e) {
			}
			final XmlSeeAlso seeAlsoAnnotation = getPrivClassAnnotation(interfaceClass, XmlSeeAlso.class);
			if (seeAlsoAnnotation != null) {
				for (final Class<?> seeAlso : seeAlsoAnnotation.value()) {
					this.xmlSeeAlso.add(seeAlso);
				}
			}
		}
	}

	@Override
	public final String toString() {
		return this.serviceName + ", " + this.portName + " (" + super.toString() + ")";
	}

	/**
	 * Returns the servicename, of the specific {@link WSEndpoint}.
	 * 
	 * @return The service name.
	 */
	public final String getServiceName() {
		return this.serviceName;
	}

	/**
	 * Returns the port name of the {@link WebEndpoint}.
	 * 
	 * @return The port name.
	 */
	public final String getPortName() {
		return this.portName;
	}

	/**
	 * Returns the port of the {@link WebEndpoint}.
	 * 
	 * @return The port.
	 */
	public final Object getPort() {
		return this.port;
	}

	/**
	 * Returns the method, who fits to the given name.
	 * 
	 * @param qname
	 *            - The qualified name, to resolve.
	 * @return The method.
	 */
	public final Method resolveMethod(final String qname) {
		return methodMap.get(qname);
	}

	/**
	 * <p>
	 * Returns classes which are bound by the JAXB implementation.
	 * </p>
	 * 
	 * @return The classes, which have to be known to JAXBContext.
	 */
	public final Set<Class<? extends Object>> getXmlSeeAlso() {
		return this.xmlSeeAlso;
	}

	/**
	 * Stores the web methods of the given class in the buffer.
	 * 
	 * @param clz
	 *            - The class, which web methods are processed.
	 */
	private final void processMethods(final Class<?> clz) {
		if (clz == null) {
			throw new IllegalStateException("'null' class parameter is not allowed");
		}

		for (final Method m : clz.getMethods()) {
			if (m.getDeclaringClass() != Object.class) {
				final int modifiers = m.getModifiers();
				assert Modifier.isPublic(modifiers);
				assert !Modifier.isStatic(modifiers);
				final WebMethod webMethod = getPrivMethodAnnotation(m, WebMethod.class);
				if (webMethod != null && !webMethod.exclude()) {
					final String methodName = new QName(this.targetNamespace, webMethod.operationName()).toString();
					methodMap.put(methodName, m);
				}
			}
		}
	}

	/**
	 * Evaluates the given class about an implementation of an WebService or an
	 * WebServiceProvider. Checks further, that the given class implements an
	 * Provider or AsyncProvider interface.
	 * 
	 * @param clz
	 *            - The class, to verify.
	 * 
	 * @return Returns <strong>true</strong>, if the given class has
	 *         <code>WebServiceProvider</code>- and <code>WebService</code>
	 *         -annotations and also implements an <code>Provider</code> or an
	 *         <code>AsyncProvider</code>-interface.
	 */
	private static boolean verifyImplementorClass(final Class<?> clz) {
		final WebServiceProvider wsProvider = clz.getAnnotation(WebServiceProvider.class);
		final WebService ws = clz.getAnnotation(WebService.class);
		if (wsProvider == null && ws == null) {
			throw new IllegalArgumentException(clz + " has neither @WebSerivce nor @WebServiceProvider annotation");
		}
		if (wsProvider != null && ws != null) {
			throw new IllegalArgumentException(clz + " has both @WebSerivce and @WebServiceProvider annotations");
		}
		if (wsProvider != null) {
			if (Provider.class.isAssignableFrom(clz)) {
				return true;
			}
			throw new IllegalArgumentException(clz + " doesn't implement Provider or AsyncProvider interface");
		}
		return false;
	}

	/**
	 * Returns the privileged class annotations from the given <em>clazz</em>.
	 * 
	 * @param clazz
	 *            - The specific class, to search for annotations.
	 * @param T
	 *            - The annotation class, to return.
	 * 
	 * @return Returns the privileged annotations of the specific <em>clazz</em>.
	 */
	private static <T extends Annotation> T getPrivClassAnnotation(final Class<?> clazz, final Class<T> T) {
		return AccessController.doPrivileged(new PrivilegedAction<T>() {
			@Override
			public T run() {
				return clazz.getAnnotation(T);
			}
		});
	}
	
	/**
	 * Returns the privileged method annotations from the method.
	 * 
	 * @param method
	 *            - The specific method, which annotations are returned.
	 * @param T
	 *            - The annotation class, to return.
	 * @return Returns the privileged annotations of the specific method.
	 */
	private static <T extends Annotation> T getPrivMethodAnnotation(final Method method, final Class<T> T) {
		return AccessController.doPrivileged(new PrivilegedAction<T>() {
			@Override
			public T run() {
				return method.getAnnotation(T);
			}
		});
	}
}
