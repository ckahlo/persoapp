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
package de.persoapp.core.client;

import java.util.HashMap;
import java.util.Map;

import de.persoapp.core.card.ICardHandler;

/**
 * The <tt>ECardSession</tt> handles all functions to maintain a session with a
 * inserted <tt>ECard</tt>.
 * <p>
 * <code>public class ECardSession</code>
 * </p>
 * 
 * @author Christian Kahlo, Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class ECardSession {

	/**
	 * The used key states of the {@link ECardSession}.
	 * 
	 * @author Christian Kahlo, Ralf Wondratschek
	 */
	public static enum KEYS {
		SPServerCert, tcTokenURL, ;
	}

	/**
	 * Set to <strong>true</strong>, if the current {@link ECardSession} is
	 * initialized, otherwise set to <strong>false</strong>.
	 */
	private boolean						initialized	= false;
	
	/**
	 * The current instance of the <tt>PersoApp-Application</tt>.
	 */
	private IMainView					mainView;
	
	/**
	 * The current used <tt>CardHandler</tt>.
	 */
	private ICardHandler				cardHandler;
	
	/**
	 * The attributes of the current {@link ECardSession}.
	 */
	private final Map<Object, Object>	attributes	= new HashMap<Object, Object>();

	/**
	 * Creates and initializes a <tt>ECardSession</tt>.
	 * 
	 * @param mainView
	 *            - The used <em>mainView</em>.
	 * @param ch
	 *            - The handler to the currently inserted card.
	 */
	public ECardSession(final IMainView mainView, final ICardHandler ch) {
		if (mainView == null || ch == null) {
			throw new NullPointerException();
		}

		this.attributes.clear();
		this.mainView = mainView;
		this.cardHandler = ch;
		this.initialized = true;
	}

	/**
	 * Terminates the current {@link ECardSession} and deletes all attributes.
	 */
	public void terminate() {
		this.initialized = false;
		this.attributes.clear();
		this.mainView = null;
		this.cardHandler = null;
	}

	/**
	 * Returns <strong>true</strong>, if the session is initialized, otherwise
	 * <strong>false</strong>.
	 * 
	 * @return Returns <strong>true</strong>, if the session is initialized,
	 *         otherwise <strong>false</strong>.
	 */
	public boolean isInitialized() {
		return this.initialized;
	}

	/**
	 * Stores the given <em>value</em> in the current session attributes.
	 * 
	 * @param key
	 *            - The used <em>key</em>, to map with the value.
	 * @param value
	 *            - The used <em>attribute</em>, to store in the session
	 *            attributes.
	 */
	public void setAttribute(final Object key, final Object value) {
		this.attributes.put(key, value);
	}

	/**
	 * Retrieves the session attributes, which are mapped to the given
	 * <em>key</em>.
	 * 
	 * @param key
	 *            - The <em>key</em>, which is mapped to the searched attribute.
	 * @return Returns the attribute to the <em>key</em> or
	 *         <strong>null</strong>, if no attribute is set.
	 */
	public Object getAttribute(final String key) {
		return this.attributes.get(key);
	}

	/**
	 * Returns the current running instance of the <tt>PersoApp-Application</tt>
	 * .
	 * 
	 * @return Returns the current running instance of the
	 *         <tt>PersoApp-Application</tt>.
	 */
	public IMainView getMainView() {
		return this.mainView;
	}

	/**
	 * Returns the <tt>CardHandler</tt>
	 * 
	 * @param slotHandle
	 *            - This parameter isn't used and can be <strong>null</strong>.
	 * @return Returns the <tt>CardHandler</tt>
	 */
	public ICardHandler getCardHandler(final byte[] slotHandle) {
		return this.cardHandler;
	}
}
