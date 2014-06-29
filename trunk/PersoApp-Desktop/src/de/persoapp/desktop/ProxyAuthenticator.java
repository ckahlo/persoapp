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
package de.persoapp.desktop;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import de.persoapp.core.client.IMainView;
import de.persoapp.desktop.gui.frame.ProxyPassFrame;

/**
 * The <tt>ProxyAuthenticator</tt>-class provides a function for the
 * <code>Password Authentication</code> and is used to authenticate a user name
 * and a password against a web service.
 * 
 * <p>
 * <code>public final class ProxyAuthenticator extends Authenticator</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public final class ProxyAuthenticator extends Authenticator {
	
	/**
	 * The <tt>mainView</tt> of the <tt>PersoApp-Application</tt>.
	 */
	private final IMainView				mainView;
	
	/**
	 * The <tt>Authentication Cache</tt> of the <tt>PersoApp-Application</tt>.
	 */
	private final Map<String, String[]>	authCache	= new HashMap<String, String[]>();

	/**
	 * Constructs a new instance of the {@link ProxyAuthenticator}.
	 * 
	 * @param mainView - The <tt>mainView</tt> to set.
	 */
	public ProxyAuthenticator(final IMainView mainView) {
		this.mainView = mainView;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		System.out.println("Authenticator: " + this.getRequestorType().toString() + " " + this.getRequestingHost()
				+ ":" + this.getRequestingPort() + " " + this.getRequestingScheme() + " " + this.getRequestingPrompt()
				+ " " + this.getRequestingProtocol() + " " + this.getRequestingURL());

		final String realm = this.getRequestorType().toString() + ": " + this.getRequestingHost() + ":"
				+ this.getRequestingPort() + "\n" + this.getRequestingPrompt();

		String[] creds = authCache.get(realm);
		if (creds == null) {
			creds = new String[] { System.getProperty("user.name"), null };
		}

		String dest = null;
		try {
			dest = this.getRequestingURL().toURI().resolve("/").toASCIIString();
		} catch (final URISyntaxException e) {
			dest = this.getRequestingHost();
		}

		final ProxyPassFrame ppf = new ProxyPassFrame(realm + "\n" + dest, creds);
		ppf.setVisible(true);

		creds = ppf.getCredentials();
		if (creds != null && creds.length == 2 && creds[0] != null && creds[1] != null) {
			authCache.put(realm, creds);
			return new PasswordAuthentication(creds[0], creds[1].toCharArray());
		}

		return null;
	}
}
