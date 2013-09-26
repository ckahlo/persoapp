/**
 * 
 * COPYRIGHT (C) 2010, 2011, 2012, 2013 AGETO Innovation GmbH
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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebResult;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.namespace.QName;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.w3c.dom.Element;

/**
 * @author ckahlo
 * 
 */
public final class WSContainer implements WebServiceContext {
	private final Map<String, WSEndpoint>	endpointsMap	= new HashMap<String, WSEndpoint>();
	private ThreadLocal<MsgCtx>				messageContexts;
	private WebServiceContext				wsCtx			= null;

	public WSContainer() {
	}

	public final class MsgCtx extends HashMap<String, Object> implements MessageContext {
		private static final long	serialVersionUID	= 43022433778691293L;

		MsgCtx() {
		}

		@Override
		public final void setScope(final String name, final Scope scope) {
		}

		@Override
		public final Scope getScope(final String name) {
			return null;
		}
	}

	/*
	 * 
	 */

	@Override
	public final EndpointReference getEndpointReference(final Element... arg0) {
		return null;
	}

	@Override
	public final <T extends EndpointReference> T getEndpointReference(final Class<T> arg0, final Element... arg1) {
		return null;
	}

	@Override
	public final MessageContext getMessageContext() {
		if (messageContexts == null) {
			messageContexts = new ThreadLocal<MsgCtx>() {
				@Override
				protected final MsgCtx initialValue() {
					return new MsgCtx();
				}
			};
		}

		return messageContexts.get();
	}

	@Override
	public final Principal getUserPrincipal() {
		return null;
	}

	@Override
	public final boolean isUserInRole(final String arg0) {
		return false;
	}

	public final void addEndpoint(final WSEndpoint wse) {
		endpointsMap.put(wse.getPortName(), wse);
	}

	public final void addService(final Object impl) {
		addEndpoint(new WSEndpoint(impl));
	}

	public final Set<Class<? extends Object>> getXmlSeeAlso() {
		final Set<Class<? extends Object>> result = new HashSet<Class<? extends Object>>();

		for (final WSEndpoint wse : endpointsMap.values()) {
			result.addAll(wse.getXmlSeeAlso());
		}

		return result;
	}

	public final void init(final WebServiceContext externalContext) {
		if (this.wsCtx == null) {
			if (externalContext != null) {
				this.wsCtx = externalContext;
			} else {
				this.wsCtx = this;
			}

			for (final WSEndpoint wse : endpointsMap.values()) {
				injectOnInit(wse.getPort());
			}
		}
	}

	private void injectOnInit(final Object target) {
		final Class<?> clazz = target.getClass();

		try {
			for (final Field f : clazz.getDeclaredFields()) {
				final Resource r = f.getAnnotation(Resource.class);
				if (r != null) {
					if (f.getType() == WebServiceContext.class) {
						f.setAccessible(true);
						f.set(target, wsCtx);
					}
				}
			}

			for (final Method m : clazz.getDeclaredMethods()) {
				final Resource r = m.getAnnotation(Resource.class);
				if (r != null) {
					final Class<?>[] params = m.getParameterTypes();
					if (params != null && params.length == 1 && params[0] == WebServiceContext.class) {
						m.setAccessible(true);
						m.invoke(target, new Object[] { wsCtx });
					}
				}
			}

			for (final Method m : clazz.getDeclaredMethods()) {
				if (m.isAnnotationPresent(PostConstruct.class)) {
					final int modifiers = m.getModifiers();
					assert m.getReturnType() == void.class;
					assert !Modifier.isStatic(modifiers);
					assert !Modifier.isFinal(modifiers);
					assert m.getExceptionTypes().length == 0;

					m.setAccessible(true);
					m.invoke(target, (Object[]) null);
					break;
				}
			}
		} catch (final InvocationTargetException ite) {
			System.out.println(ite.toString() + ": " + String.valueOf(ite.getCause()));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	//@TODO: optimize that into a "global" map
	//Note: namespace cannot be used to identify correct service, because
	// namespace is not unique per service
	public final Object processRequest(final QName name, final Object message) {
		Method m = null;
		Object port = null;
		for (final WSEndpoint wse : endpointsMap.values()) {
			m = wse.resolveMethod(name.toString());
			if (m != null) {
				port = wse.getPort();
				break;
			}
		}

		if (m != null) {
			Object result = null;

			try {
				result = m.invoke(port, message);

				// XXX: is the more correct way when interface classes are used, keep it mind 
				//				result = port.getClass().getMethod(m.getName(), m.getParameterTypes()).invoke(port, message);
			} catch (final Exception e) {
				e.printStackTrace();
			}

			if (!m.getReturnType().isAnnotationPresent(XmlRootElement.class)) {
				final WebResult wr = m.getAnnotation(WebResult.class);

				if (wr != null) {
					result = new JAXBElement(new QName(wr.targetNamespace(), wr.name()), m.getReturnType(), result);
				}
			}

			return result;
		} else {
			System.out.println("Method " + name.toString() + " not found.");
		}

		return null;
	}

}
