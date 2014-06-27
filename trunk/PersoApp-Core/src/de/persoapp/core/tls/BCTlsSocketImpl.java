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
 * The <tt>BCTlsSocketImpl</tt> enables additional functionality for
 * {@link SSLSocket}-object.
 * <p>
 * The additionally functionality allows the <tt>Socket</tt> to work with the
 * implemented <tt>pre-shared keys</tt>.
 * </p>
 * <p>
 * <code>
 * public final class BCTlsSocketImpl extends SSLSocket
 * </code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public final class BCTlsSocketImpl extends SSLSocket {

	/**
	 * The used <tt>TlsProtocol</tt> of the <tt>TlsConnection</tt>.
	 */
	final TlsProtocol	tls;
	
	/**
	 * The current <tt>TlsPeer</tt> of the <tt>TlsConnection</tt>.
	 */
	final TlsPeer		peerHandler;

	/**
	 * Creates a new instance of a <tt>BCTlsSocket</tt>. If a
	 * <tt>pre-shared key</tt> is set, the constructor uses a
	 * {@link TLSPSKClient} to establish the connection. If no
	 * <tt>pre-shared key</tt> is set, a {@link TLSClient} is used to establish
	 * the connection to the endpoint.
	 * 
	 * @param netSocket
	 *            - The underlying <tt>Socket</tt> of the <tt>BCTlsSocket</tt>
	 *            which maintains the connection.
	 * @param factory
	 *            - The factory, which provides the pre-shared key and the
	 *            pre-shared key id, if their are set.
	 * @param serverMode
	 *            - Can't set to <strong>true</strong>.
	 * 
	 * @throws IOException
	 *             If something goes wrong with the set up of the
	 *             <tt>Socket</tt>.
	 * @throws UnsupportedOperationException
	 *             Is thrown, when the <tt>serverMode</tt> is set to
	 *             <strong>true</strong>.
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
	 * Returns the current set <tt>PeerHandler</tt>.
	 * 
	 * @return Returns the current set <tt>PeerHandler</tt>.
	 */
	final TlsPeer getPeerHandler() {
		return this.peerHandler;
	}

	@Override
	public InputStream getInputStream() {
		return tls.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() {
		return tls.getOutputStream();
	}

	@Override
	public void addHandshakeCompletedListener(final HandshakeCompletedListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean getEnableSessionCreation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getEnabledCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getEnabledProtocols() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getNeedClientAuth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SSLSession getSession() {
		return new BCTlsSession(this);
	}

	@Override
	public String[] getSupportedCipherSuites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getSupportedProtocols() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getUseClientMode() {
		return true;
	}

	@Override
	public boolean getWantClientAuth() {
		return false;
	}

	@Override
	public void removeHandshakeCompletedListener(final HandshakeCompletedListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnableSessionCreation(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabledCipherSuites(final String[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnabledProtocols(final String[] arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setNeedClientAuth(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUseClientMode(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWantClientAuth(final boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startHandshake() throws IOException {
	}
}
