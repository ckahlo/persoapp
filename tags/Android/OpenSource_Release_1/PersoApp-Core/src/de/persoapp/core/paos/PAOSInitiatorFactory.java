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
package de.persoapp.core.paos;

import java.io.IOException;
import java.net.URI;

import javax.xml.bind.JAXBException;

import de.persoapp.core.ws.engine.WSContainer;

/**
 * The PAOSInitiatorFactory creates instances from the
 * {@link PAOSInitiator} object, to allow the use of the PAOS
 * -protocol.
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class PAOSInitiatorFactory {

	/**
	 * Creates, initializes and returns a new {@link PAOSInitiator}.
	 * 
	 * @param wsCtx
	 *            - The container for local web-services to dispatch PAOS
	 *            requests.
	 * 
	 * @param endpoint
	 *            - The webservice endpoint which contains the callable methods
	 *            of the webservice.
	 * 
	 * @param sessionID
	 *            - The eID session ID (PSK user name).
	 * @param pskKey
	 *            - The pre-shared key for TLS_RSA_PSK connection.
	 * 
	 * @return Returns the created instance of {@link PAOSInitiator}.
	 * 
	 * @throws IOException
	 *             Throws IOException if a error occurs during the
	 *             creation process of the {@link PAOSInitiator}.
	 */
    public PAOSInitiator createPAOSInitiator(WSContainer wsCtx, URI endpoint, String sessionID, byte[] pskKey) throws IOException {
        try {
            return new PAOSInitiator(wsCtx, endpoint, sessionID, pskKey);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

	/**
	 * Creates and returns a new instance {@link PAOSInitiator} with initialized
	 * JAXB components.
	 * 
	 * @return Creates and returns a new instance {@link PAOSInitiator} with
	 *         initialized JAXB components.
	 */
    public PAOSInitiator createPreStartPAOSInitiator() {
        try {
            return new PAOSInitiator();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

}
