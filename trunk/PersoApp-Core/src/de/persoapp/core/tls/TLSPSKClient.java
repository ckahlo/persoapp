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

import org.bouncycastle.crypto.tls.ExtensionType;
import org.bouncycastle.crypto.tls.PSKTlsClient;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsPSKIdentity;

/**
 * 
 * Support class for BouncyCastle TLS stack to enable TLS_RSA_PSK support.
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class TLSPSKClient extends PSKTlsClient {

	/**
	 * Authentication instance to retrieve server certificate.
	 * 
	 */
	private final TlsAuthentication	authentication	= new BCTlsAuthentication();

	/**
	 * Hostname to be used in server name indication extension.
	 */
	private final String			hostname;

	/**
	 * Enabled cipher suites.
	 */
	private static final int[]		defaultCS		= new int[] {
													//
													// 0x008C, 0x008D, 0x00AE, 0x00AF, // TLS_PSK_*
			0x0094, 0x0095, 0x00B6, 0x00B7			// TLS_RSA_PSK
													};

	/*
	 * @Override(non-Javadoc)
	 * 
	 * @see org.bouncycastle.crypto.tls.PSKTlsClient#getCipherSuites()
	 */
	@Override
	public int[] getCipherSuites() {
		return defaultCS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bouncycastle.crypto.tls.AbstractTlsPeer#notifyAlertRaised(short,
	 * short, java.lang.String, java.lang.Exception)
	 */
	@Override
	public void notifyAlertRaised(final short alertLevel, final short alertDescription, final String message,
			final Exception cause) {
		System.out.println("### ALERT RAISED: " + alertLevel + "/" + alertDescription + " = " + message + ", " + cause);
	}

	@Override
	public void notifySecureRenegotiation(final boolean secureRenegotiation) throws IOException {
		//		System.out.println("### RENEGO: secure? " + secureRenegotiation);
		//		//		throw new TlsFatalAlert((short) 100);
	}

	/**
	 * Create an instance of TLSPSKClient
	 * 
	 * @param hostname
	 *            - optional server name for server name indication, SNI will be
	 *            omitted if <em>null</em>
	 * @param pskId
	 *            - pre-shared key username / eID session id
	 * @param pskSecret
	 *            - corresponding secret for eID session
	 */
	public TLSPSKClient(final String hostname, final byte[] pskId, final byte[] pskSecret) {
		super(new TlsPSKIdentity() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.bouncycastle.crypto.tls.TlsPSKIdentity#skipIdentityHint()
			 */
			@Override
			public void skipIdentityHint() {
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * org.bouncycastle.crypto.tls.TlsPSKIdentity#notifyIdentityHint
			 * (byte[])
			 */
			@Override
			public void notifyIdentityHint(final byte[] psk_identity_hint) {
				System.out.println("PSK id hint: " + new String(psk_identity_hint));
			}

			/*
			 * @Override(non-Javadoc)
			 * 
			 * @see org.bouncycastle.crypto.tls.TlsPSKIdentity#getPSKIdentity()
			 */
			@Override
			public byte[] getPSKIdentity() {
				return pskId;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.bouncycastle.crypto.tls.TlsPSKIdentity#getPSK()
			 */
			@Override
			public byte[] getPSK() {
				return pskSecret;
			}
		});
		this.hostname = hostname;
	}

	/**
	 * Create an instance of TLSPSKClient with no server name indication.
	 * 
	 * @param pskId
	 *            - pre-shared key username / eID session id
	 * @param pskSecret
	 *            - corresponding secret for eID session
	 */
	public TLSPSKClient(final byte[] pskId, final byte[] pskSecret) {
		this(null, pskId, pskSecret);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bouncycastle.crypto.tls.TlsClient#getAuthentication()
	 */
	@Override
	public TlsAuthentication getAuthentication() throws IOException {
		return this.authentication;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bouncycastle.crypto.tls.AbstractTlsClient#getMinimumVersion()
	 */
	@Override
	public ProtocolVersion getMinimumVersion() {
		return ProtocolVersion.TLSv10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bouncycastle.crypto.tls.AbstractTlsClient#getClientExtensions()
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
