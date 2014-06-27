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
 * The <tt>PAOSInitiatorFactory</tt> creates instances from the
 * {@link PAOSInitiator} object, to allow the use of the <tt>PAOS</tt>
 * -webservice.
 * <p>
 * <code>public class PAOSInitiatorFactory</code>
 * </p>
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class PAOSInitiatorFactory {

	/**
	 * Creates and returns a new {@link PAOSInitiator} instance.
	 * 
	 * @param wsCtx
	 *            - The webservice context to access the different methods and
	 *            security informations.
	 * @param endpoint
	 *            - The webservice endpoint which contains the different
	 *            methods.
	 * @param sessionID
	 *            - The currently session id.
	 * @param pskKey
	 *            - The currently used pre shared key.
	 * 
	 * @return Returns the created instance of {@link PAOSInitiator}.
	 * 
	 * @throws IOException
	 *             - Throws <tt>IOException</tt> if a error occurs during the
	 *             creation process of the {@link PAOSInitiator}-object.
	 */
    public PAOSInitiator createPAOSInitiator(WSContainer wsCtx, URI endpoint, String sessionID, byte[] pskKey) throws IOException {
        try {
            return new PAOSInitiator(wsCtx, endpoint, sessionID, pskKey);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

	/**
	 * Creates and returns a empty instance of the {@link PAOSInitiator}-class.
	 * 
	 * @return Creates and returns a empty instance of the {@link PAOSInitiator}
	 *         -class.
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
