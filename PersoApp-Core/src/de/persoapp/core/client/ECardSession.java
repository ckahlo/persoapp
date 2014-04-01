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
package de.persoapp.core.client;

import java.util.HashMap;
import java.util.Map;

import de.persoapp.core.card.ICardHandler;

public class ECardSession {

	public static enum KEYS {
		SPServerCert, tcTokenURL, ;
	}

	private boolean						initialized	= false;
	private IMainView					mainView;
	private ICardHandler				cardHandler;
	private final Map<Object, Object>	attributes	= new HashMap<Object, Object>();

	public ECardSession(final IMainView mainView, final ICardHandler ch) {
		if (mainView == null || ch == null) {
			throw new NullPointerException();
		}

		this.attributes.clear();
		this.mainView = mainView;
		this.cardHandler = ch;
		this.initialized = true;
	}

	public void terminate() {
		this.initialized = false;
		this.attributes.clear();
		this.mainView = null;
		this.cardHandler = null;
	}

	public boolean isInitialized() {
		return this.initialized;
	}

	public void setAttribute(final Object key, final Object value) {
		this.attributes.put(key, value);
	}

	public Object getAttribute(final String key) {
		return this.attributes.get(key);
	}

	public IMainView getMainView() {
		return this.mainView;
	}

	public ICardHandler getCardHandler(final byte[] slotHandle) {
		return this.cardHandler;
	}
}
