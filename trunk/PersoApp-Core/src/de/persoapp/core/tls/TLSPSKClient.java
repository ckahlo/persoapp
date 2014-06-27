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
package de.persoapp.core.tls;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.bouncycastle.crypto.tls.ExtensionType;
import org.bouncycastle.crypto.tls.PSKTlsClient;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsPSKIdentity;

/**
 * The <tt>TLSPSKClient</tt> uses a <tt>pre-shared key</tt> to get further
 * security by exchanging keys.
 * <p>
 * <code>public class TLSPSKClient extends PSKTlsClient</code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public class TLSPSKClient extends PSKTlsClient {

	/**
	 * The currently used {@link BCTlsAuthentication} for retrieving the
	 * username and the password of the client.
	 */
	private final TlsAuthentication	authentication	= new BCTlsAuthentication();

	/**
	 * The currently acquired hostname.
	 */
	private final String			hostname;

	/**
	 * The cipher suites for encryption. The defined suites belongs to the
	 * RSA_PSK cipher.
	 */
	private static final int[]		defaultCS		= new int[] {
													//
													// 0x008C, 0x008D, 0x00AE, 0x00AF, // TLS_PSK_*
			0x0094, 0x0095, 0x00B6, 0x00B7, // TLS_RSA_PSK
													};

	/**
	 * Returns the defined cipher suites.
	 * 
	 * @return Returns the defined cipher suites.
	 */
	@Override
	public int[] getCipherSuites() {
		return defaultCS;
	}

	/**
	 * Creates and initializes a instance of the {@link TLSPSKClient}-class.
	 * 
	 * @param hostname
	 *            - The name of the host.
	 * @param pskId
	 *            - The id of the <tt>pre-shared key</tt>.
	 * @param pskSecret
	 *            - The <tt>pre-shared key</tt> itself.
	 */
	public TLSPSKClient(final String hostname, final byte[] pskId, final byte[] pskSecret) {
		super(new TlsPSKIdentity() {
			@Override
			public void skipIdentityHint() {
			}

			@Override
			public void notifyIdentityHint(final byte[] psk_identity_hint) {
				System.out.println("PSK id hint: " + new String(psk_identity_hint));
			}
			
			/**
			 * Returns the psk(Pre-Shared Key) identity.
			 * 
			 * @return Returns the psk-identity.
			 */
			@Override
			public byte[] getPSKIdentity() {
				return pskId;
			}

			/**
			 * Returns the psk(Pre-shared key).
			 * 
			 * @return Returns the psk.
			 */
			@Override
			public byte[] getPSK() {
				return pskSecret;
			}
		});
		this.hostname = hostname;
	}

	/**
	 * Creates and initializes a {@link TLSPSKClient} and setting the name of
	 * the host to <strong>null</strong>.
	 * 
	 * @param pskId
	 *            - The pre-shared key id.
	 * @param pskSecret
	 *            - The pre shared key itself.
	 */
	public TLSPSKClient(final byte[] pskId, final byte[] pskSecret) {
		this(null, pskId, pskSecret);
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
