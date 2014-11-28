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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.bouncycastle.crypto.tls.TlsClient;
import org.bouncycastle.crypto.tls.TlsClientProtocol;
import org.bouncycastle.crypto.tls.TlsPeer;
import org.bouncycastle.crypto.tls.TlsProtocol;

/*
 * pre-liminary stub
 */

/**
 * <tt>BCTlsSocketImpl</tt> provides a javax.net.ssl.* compatible interface to
 * BouncyCastle TLS.
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public final class BCTlsSocketImpl extends SSLSocket {

	/**
	 * TLS protocol instance
	 */
	final TlsProtocol	tls;

	/**
	 * Corresponding peer for this socket connection
	 */
	final TlsPeer		peerHandler;

	/**
	 * Create a new instance of <tt>BCTlsSocket</tt>. If a
	 * <tt>pre-shared key</tt> is set {@link TLSPSKClient} is used. If no
	 * <tt>pre-shared key</tt> is set {@link TLSClient} is used to establish the
	 * connection to the endpoint.
	 * 
	 * @param netSocket
	 *            - The underlying network <tt>Socket</tt> provided by
	 *            {@link BCTlsSocketFactoryImpl}
	 * @param factory
	 *            - factory creating this socket
	 * @param serverMode
	 *            - for completeness, server mode isn't supported
	 * 
	 * @throws IOException
	 *             if connection could not be established
	 * @throws UnsupportedOperationException
	 *             if server mode had been selected
	 */
	public BCTlsSocketImpl(final Socket netSocket, final BCTlsSocketFactoryImpl factory, final boolean serverMode)
			throws IOException {
		tls = new TlsClientProtocol(netSocket.getInputStream(), netSocket.getOutputStream());
		final byte[][] pskParams = factory.getPSKParameters();

		final String hostname = netSocket.getInetAddress().getCanonicalHostName();
		if (!serverMode) {
			if (pskParams != null) {
				peerHandler = new TLSPSKClient(hostname, pskParams[0], pskParams[1]);
			} else {
				peerHandler = new TLSClient(hostname);
			}

			((TlsClientProtocol) tls).connect((TlsClient) peerHandler);
		} else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Retrieve the TLS peer handler instance associated with this socket.
	 * 
	 * @return {@link TlsPeer}
	 */
	final TlsPeer getPeerHandler() {
		return this.peerHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.net.Socket#getInputStream()
	 */
	@Override
	public InputStream getInputStream() {
		return tls.getInputStream();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.net.Socket#getOutputStream()
	 */
	@Override
	public OutputStream getOutputStream() {
		return tls.getOutputStream();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#addHandshakeCompletedListener(javax.net.ssl.
	 * HandshakeCompletedListener)
	 */
	@Override
	public void addHandshakeCompletedListener(final HandshakeCompletedListener arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getEnableSessionCreation()
	 */
	@Override
	public boolean getEnableSessionCreation() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getEnabledCipherSuites()
	 */
	@Override
	public String[] getEnabledCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getEnabledProtocols()
	 */
	@Override
	public String[] getEnabledProtocols() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getNeedClientAuth()
	 */
	@Override
	public boolean getNeedClientAuth() {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getSession()
	 */
	@Override
	public SSLSession getSession() {
		return new BCTlsSession(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getSupportedCipherSuites()
	 */
	@Override
	public String[] getSupportedCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getSupportedProtocols()
	 */
	@Override
	public String[] getSupportedProtocols() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getUseClientMode()
	 */
	@Override
	public boolean getUseClientMode() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#getWantClientAuth()
	 */
	@Override
	public boolean getWantClientAuth() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.net.ssl.SSLSocket#removeHandshakeCompletedListener(javax.net.ssl
	 * .HandshakeCompletedListener)
	 */
	@Override
	public void removeHandshakeCompletedListener(final HandshakeCompletedListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnableSessionCreation(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#setEnabledCipherSuites(java.lang.String[])
	 */
	@Override
	public void setEnabledCipherSuites(final String[] arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#setEnabledProtocols(java.lang.String[])
	 */
	@Override
	public void setEnabledProtocols(final String[] arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#setNeedClientAuth(boolean)
	 */
	@Override
	public void setNeedClientAuth(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#setUseClientMode(boolean)
	 */
	@Override
	public void setUseClientMode(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#setWantClientAuth(boolean)
	 */
	@Override
	public void setWantClientAuth(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.net.ssl.SSLSocket#startHandshake()
	 */
	@Override
	public void startHandshake() throws IOException {
	}
}
