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
package de.persoapp.core.tls;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.bouncycastle.crypto.tls.DefaultTlsClient;
import org.bouncycastle.crypto.tls.ExtensionType;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsAuthentication;

/**
 * The <tt>TLSClient</tt> is the contains all functions to allow
 * <tt>KeyExchanges</tt> according to the supported TLS version.
 * <p>
 * <code>public class TLSClient extends DefaultTlsClient</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class TLSClient extends DefaultTlsClient {

	/**
	 * The currently used authentication
	 */
	private final TlsAuthentication	authentication	= new BCTlsAuthentication();

	/**
	 * The currently acquired hostname.
	 */
	private final String			hostname;

	/**
	 * The implemented cipher suites for encryption. The implemented array have
	 * a size of 12 elements.
	 * <p>
	 * <table border=1>
	 * <tr>
	 * <th>Place</th>
	 * <th>Cipher</th>
	 * </tr>
	 * <tr>
	 * <td>[0]-[3]</td>
	 * <td>RSA</td>
	 * </tr>
	 * <tr>
	 * <td>[4]-[7]</td>
	 * <td>ECDHE_ECDSA</td>
	 * </tr>
	 * <tr>
	 * <td>[8]-[11]</td>
	 * <td>ECDHE_RSA</td>
	 * </tr>
	 * </table>
	 * </p>
	 */
	private static final int[]		defaultCS		= new int[] {
													//
			0x002F, 0x0035, 0x003C, 0x003D, 		// TLS_RSA_*
			0xC009, 0xC00A, 0xC023, 0xC024, 		// TLS_ECDHE_ECDSA_*
			0xC013, 0xC014, 0xC027, 0xC028			// TLS_ECDHE_RSA_*
													};

	/**
	 * Returns the defined cipher suites for encryption.
	 * 
	 * @return Returns the defined cipher suites for encryption.
	 */
	@Override
	public int[] getCipherSuites() {
		return defaultCS;
	}

	/**
	 * Creates a instance of the {@link TLSClient} with the name of the current
	 * activ host.
	 * 
	 * @param hostname
	 *            - The used hostname.
	 */
	public TLSClient(final String hostname) {
		super();
		this.hostname = hostname;
	}

	/**
	 * Creates a instance of the {@link TLSClient} without a hostname.
	 */
	public TLSClient() {
		this(null);
	}

	/**
	 * Returns the current authenticator.
	 *	
	 * @return Returns the current authenticator.
	 */
	@Override
	public TlsAuthentication getAuthentication() throws IOException {
		return this.authentication;
	}

	/**
	 * Returns the minimal version of the used <tt>TLS</tt>-library.
	 *	
	 * @return Returns the minimal version of the used <tt>TLS</tt>-library.
	 */
	@Override
	public ProtocolVersion getMinimumVersion() {
		return ProtocolVersion.TLSv11;
	}

	/**
	 * Returns the client specific extensions.
	 * 
	 * @return Returns the client specific extensions.
	 */
	@Override
	public Hashtable<Integer, byte[]> getClientExtensions() throws IOException {
		@SuppressWarnings("unchecked")
		Hashtable<Integer, byte[]> clientExtensions = super.getClientExtensions();
		if (clientExtensions == null) {
			clientExtensions = new Hashtable<Integer, byte[]>();
		}

		final ByteArrayOutputStream extBaos = new ByteArrayOutputStream();
		final DataOutputStream extOS = new DataOutputStream(extBaos);

		if (this.hostname != null) {
			final byte[] hostnameBytes = this.hostname.getBytes();
			final int snl = hostnameBytes.length;

			// OpenSSL breaks if an extension with length "0" sent, they expect at least
			// an entry with length "0"
			extOS.writeShort(snl == 0 ? 0 : snl + 3); // entry size
			if (snl > 0) {
				extOS.writeByte(0); // name type = hostname
				extOS.writeShort(snl); // name size
				if (snl > 0) {
					extOS.write(hostnameBytes);
				}
			}

			extOS.close();
			clientExtensions.put(ExtensionType.server_name, extBaos.toByteArray());
		}

		return clientExtensions;
	}
}
