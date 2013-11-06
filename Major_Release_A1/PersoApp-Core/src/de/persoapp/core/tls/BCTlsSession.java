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

import java.io.IOException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.List;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.bouncycastle.crypto.tls.AbstractTlsClient;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsClient;
import org.bouncycastle.crypto.tls.TlsPeer;

/**
 * @author ckahlo
 * 
 */
public class BCTlsSession implements SSLSession {

	final TlsPeer	tlsPeer;

	public BCTlsSession(final BCTlsSocketImpl bcTlsSocketImpl) {
		this.tlsPeer = bcTlsSocketImpl.getPeerHandler();
	}

	@Override
	public int getApplicationBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCipherSuite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCreationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLastAccessedTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Certificate[] getLocalCertificates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Principal getLocalPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPacketBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
		final Certificate[] peerCerts = getPeerCertificates();
		if (peerCerts != null) {
			final X509Certificate[] result = new X509Certificate[peerCerts.length];
			for (int i = 0; i < peerCerts.length; i++) {
				try {
					result[i] = X509Certificate.getInstance(peerCerts[i].getEncoded());
				} catch (final CertificateException e) {
					e.printStackTrace();
				} catch (final CertificateEncodingException e) {
					e.printStackTrace();
				}
			}
			return result;
		}

		throw new SSLPeerUnverifiedException(null);
	}

	@Override
	public Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
		if (this.tlsPeer instanceof TlsClient) {
			try {
				final TlsAuthentication tlsAuth = ((TlsClient) tlsPeer).getAuthentication();
				if (tlsAuth != null && tlsAuth instanceof BCTlsAuthentication) {
					final List<Certificate> peerChain = ((BCTlsAuthentication) tlsAuth).getServerCertList();
					if (peerChain == null || peerChain.size() == 0) {
						throw new SSLPeerUnverifiedException("peerChain empty");
					} else {
						return peerChain.toArray(new Certificate[0]);
					}
				}
			} catch (final IOException e) {
				throw new SSLPeerUnverifiedException(e.toString());
			}
		}

		return null;
	}

	@Override
	public String getPeerHost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPeerPort() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
		final X509Certificate[] peerCerts = getPeerCertificateChain();
		if (peerCerts != null && peerCerts.length > 0) {
			return peerCerts[0].getSubjectDN();
		}

		return null;
	}

	@Override
	// TODO: implement another custom BC TlsClient to reflect
	// really negotiated version
	public String getProtocol() {
		// TODO Auto-generated method stub
		return ((AbstractTlsClient) this.tlsPeer).getClientVersion().toString();
	}

	@Override
	public SSLSessionContext getSessionContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(final String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getValueNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalidate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void putValue(final String arg0, final Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeValue(final String arg0) {
		// TODO Auto-generated method stub

	}

}
