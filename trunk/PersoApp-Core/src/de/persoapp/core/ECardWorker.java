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
package de.persoapp.core;

import iso.std.iso_iec._24727.tech.schema.ChannelHandleType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Exchanger;

import javax.net.ssl.HttpsURLConnection;

import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.client.EAC_Info;
import de.persoapp.core.client.ECardSession;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.paos.PAOSInitiator;
import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;
import de.persoapp.core.util.Util;
import de.persoapp.core.ws.EcAPIProvider;
import de.persoapp.core.ws.engine.WSContainer;

/**
 * @author ckahlo
 * 
 */
public final class ECardWorker {
	/*
	 * constants
	 */
	private static final String	SCHEME_HTTPS	= "https://";

	private static final String	TLS_PSK_USR		= "SessionIdentifier";
	private static final String	TLS_PSK_KEY		= "PathSecurity-Parameters";
	private static final String	TLS_PSK_SVR		= "ServerAddress";
	private static final String	PATH_PROTOCOL	= "PathSecurity-Protocol";
	private static final String	PATH_BINDING	= "Binding";
	private static final String	REFRESH_ADDR	= "RefreshAddress";

	/*
	 * 
	 */

	public static enum CALLBACK_RESULT {
		PROCESSING, FINALLY, TA_OK, CANCEL, TA_ERROR
	};

	/*
	 * static values, valid for the application live-time
	 */
	private static IMainView						mainView;
	private static WSContainer						wsCtx;
	private static ICardHandler						eCardHandler;

	private static final PropertyResolver.Bundle	textBundle		= PropertyResolver.getBundle("text_core");

	/*
	 * per worker/session identifiers
	 */

	private final byte[]							contextHandle	= new byte[32];
	private final byte[]							slotHandle		= new byte[32];

	/*
	 * currently running session and initiator
	 */
	private final ECardSession						session;
	private final PAOSInitiator						paosInitiator;

	/*
	 * result info values (prototype);
	 */
	private Object[]								info			= null;

	/*
	 * setup application-wide values must be static for Android & embedded
	 * clients
	 */
	public static final void init(final IMainView mainView, final WSContainer wsCtx, final ICardHandler eCardHandler) {
		if (ECardWorker.mainView == null) {
			ECardWorker.mainView = mainView;
			ECardWorker.wsCtx = wsCtx;
			ECardWorker.eCardHandler = eCardHandler;
		}
	}

	private static final X509Certificate checkCertificate(final URLConnection uc) throws IOException,
			URISyntaxException {
		X509Certificate cert = null;
		final URI uri = uc.getURL().toURI();
		if (uc == null
				|| !(uc instanceof HttpsURLConnection)
				|| !Util.validateIdentity(
						cert = (X509Certificate) ((HttpsURLConnection) uc).getServerCertificates()[0], uri)) {
			throw new IllegalArgumentException("invalid identity: " + uri + " / " + cert);
		}
		System.out.println("Read from: " + cert.getSubjectDN().getName());

		return cert;
	}

	// TODO: continue re-work, clean-up and simplification

	/*
	 * start an eCardWorker with supplied tcTokenURL
	 */

	public static final String start(final URL tcTokenURL) throws Exception {
		final List<Certificate> sourceCerts = new ArrayList<Certificate>();
		HttpURLConnection uc = null;
		Map<String, String> params = null;

		URL tc = tcTokenURL;

		while (true) {
			if (!"https".equalsIgnoreCase(tc.getProtocol())) {
				throw new IllegalArgumentException("no https-URL");
			}

			uc = (HttpURLConnection) Util.openURL(tc);
			// do connect
			uc.connect();
			// check certificate after opening connection, beware of Android 4.0.x bug
			sourceCerts.add(checkCertificate(uc));
			// read response-code and store it
			final int responseCode = uc.getResponseCode();

			// check response code
			if (responseCode == 302 || responseCode == 303 || responseCode == 307) {
				final String location = uc.getHeaderField("Location");
				tc = new URL(location);
				// disconnect for possible re-use of TLS-channel
				uc.disconnect();
			} else {
				break;
			}
		}

		if (uc.getResponseCode() == 200) {
			final String tcData = Util.readStream(uc.getInputStream());
			// disconnect for possible re-use of TLS-channel
			uc.disconnect();

			params = Util.getEcApiParams(tcData);
			if (params != null && params.size() > 0) {
				return startECardWorker(params, sourceCerts, tcTokenURL.toURI());
			} else {
				throw new FileNotFoundException("No parameters");
			}
		} else {
			throw new FileNotFoundException(uc.getResponseCode() + " " + uc.getResponseMessage());
		}
	}

	/*
	 * helper to add params to URI
	 */
	private static URI addParam(final URI uri, final String... params) {
		final StringBuffer uriBuffer = new StringBuffer(uri.toString());
		boolean first = !uri.toString().contains("?");

		for (final String param : params) {
			uriBuffer.append((first ? '?' : '&') + param);
			first = false;
		}

		return URI.create(uriBuffer.toString());
	}

	/*
	 * start eCardWorker with retrieved connection handle parameters
	 */

	private static String startECardWorker(final Map<String, String> params, final List<Certificate> serverCerts,
			final URI tcTokenURL) throws Exception {

		System.out.println("Params: " + params);

		/*
		 * launch ECardWorker thread and wait for terminal authentication
		 */
		final ChannelHandleType ch = new ChannelHandleType();
		ch.setProtocolTerminationPoint(params.get(TLS_PSK_SVR));
		ch.setSessionIdentifier(params.get(TLS_PSK_USR));

		String pathSecurityParams = params.get(TLS_PSK_KEY);
		// fix for bos Governikus
		if (pathSecurityParams == null) {
			pathSecurityParams = params.get("PathSecurity-Parameter");
		}

		if (pathSecurityParams != null) {
			pathSecurityParams = pathSecurityParams.replace("<PSK>", "").replace("</PSK>", "").trim();
		}

		final Object[] callbackResult = ECardWorker.start(ch,
				pathSecurityParams != null ? Hex.fromString(pathSecurityParams) : null, tcTokenURL,
				serverCerts.toArray(new Certificate[0]));

		final Object stateInfo = callbackResult != null && callbackResult.length > 0 ? callbackResult[0] : null;

		URI refreshURI = new URI(params.get(REFRESH_ADDR));

		if (stateInfo == ECardWorker.CALLBACK_RESULT.TA_OK) {
			final EAC_Info eacInfo = (EAC_Info) callbackResult[1];
			final byte[][] certHashes = eacInfo.getCertificateHashes();
			final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

			final URI origin = tcTokenURL.resolve("/");

			while (refreshURI != null && !refreshURI.resolve("/").equals(origin)) {
				System.out.println("connecting to " + refreshURI);
				if (!"https".equalsIgnoreCase(refreshURI.getScheme())) {
					throw new IllegalArgumentException("no https-URL");
				}

				// open connection to URI
				final HttpURLConnection uc = (HttpURLConnection) Util.openURL(refreshURI.toURL());
				// reset refreshURI
				refreshURI = null;
				// connect to current URI
				uc.connect();
				// check certificate
				final byte[] certHash = sha256.digest(checkCertificate(uc).getEncoded());

				for (final byte[] cdHash : certHashes) {
					if (ArrayTool.arrayequal(certHash, cdHash)) {
						final int responseCode = uc.getResponseCode();
						if (responseCode == 302 || responseCode == 303 || responseCode == 307) {
							// set new value
							refreshURI = new URL(uc.getHeaderField("Location")).toURI();
							// disconnect for possible re-use of TLS-channel
							uc.disconnect();

							continue;
						}

						System.out.println("cert matched, wrong status code: " + responseCode);
					}
					System.out.println("cert didn't match");
				}
			}

			if (refreshURI != null) {
				// open connection to URI
				final HttpURLConnection uc = (HttpURLConnection) Util.openURL(refreshURI.toURL());
				uc.connect();
				// check certificate
				try {
					final X509Certificate cert = checkCertificate(uc);
					// disconnect after certificate check, don't read
					uc.disconnect();

					System.out.println("### valid identity");
					final byte[] certHash = sha256.digest(cert.getEncoded());
					for (final byte[] cdHash : certHashes) {
						if (ArrayTool.arrayequal(certHash, cdHash)) {
							//sendResponse(he, 303, addParam(refreshURI, "ResultMajor=ok"), null);
							return addParam(refreshURI, "ResultMajor=ok").toString();
						}
					}
				} catch (final Exception e) {
					e.printStackTrace();
				}

				System.out.println("certificate not matched");
			}

			final String message = "Fehler bei Weiterleitung.";
			return message;
		} else {
			// XXX: distinguish cancel and subjectURL verification error
			// cancel = redirect to refreshAdress ( o == null )
			// verification error = send 400 ( o == false )
			System.out.println("## cancel / error: stateInfo=" + stateInfo);
			final Exception e = stateInfo instanceof Exception ? (Exception) stateInfo : null;

			final String message = "Error\n" + (e != null ? e.toString() : "Authentisierung abgebrochen");
			System.out.println(message);

			// (strictMode && subjectURL verification failed) || TAVerification failed
			if (stateInfo instanceof Boolean && (Boolean) stateInfo == false) {
				final String _msg = "Terminal Authentication oder subject-URL Verification fehlgeschlagen.";
				return message;
			} else {
				return addParam(refreshURI, "ResultMajor=error").toString();
			}
		}
	}

	/*
	 * start an eCard-Worker with given parameters from ISO24727-Protocols
	 * service or ECApiHttpHandler
	 */
	public static final Object[] start(final ChannelHandleType ch, final byte sessionPSK[], final URI origin,
			final Certificate[] certs) {
		try {
			final Exchanger<ECardWorker> xchg = new Exchanger<ECardWorker>();
			new Thread(new Runnable() {
				@Override
				public void run() {
					ECardWorker ecw = null;
					try {
						ecw = new ECardWorker(ch, sessionPSK, origin, certs);
						xchg.exchange(ecw);
						ecw.run();
					} catch (final Exception e1) {
						e1.printStackTrace();
						if (ecw != null) {
							ecw.callback(ECardWorker.CALLBACK_RESULT.TA_ERROR);
						}
					}
				}
			}).start();

			final ECardWorker ecw = xchg.exchange(null);
			synchronized (ecw) {
				try {
					System.out.println("Waiting for callback ...");
					ecw.wait(300 * 1000);
					System.out.println("Got callback: " + ecw.info);
					if (ecw.info != null && ecw.info.length > 0) {
						return ecw.info;
					}
				} catch (final InterruptedException ie) {
					System.out.println("callback timed out");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * called from ECardWorker on final and from SALService on error, on cancel
	 * and on success
	 */
	public final void callback(final Object... info) {
		synchronized (this) {
			if (this.info == null) {
				this.info = info;
			}
			this.notifyAll();
		}
	}

	/*
	 * retrieve current state of info field
	 */
	public final Object getCurrentState() {
		return this.info == null ? null : this.info[0];
	}

	/*
	 * create an eCard-Worker, setup all components
	 */
	private ECardWorker(final ChannelHandleType ch, final byte sessionPSK[], final URI tcTokenURL,
			final Certificate[] sourceCerts) throws Exception {
		final String serverURL = ch.getProtocolTerminationPoint();
		final URI endpoint = new URL(serverURL.startsWith(SCHEME_HTTPS) ? serverURL : SCHEME_HTTPS + serverURL).toURI();
		paosInitiator = PAOSInitiator.getInstance(wsCtx, endpoint, ch.getSessionIdentifier(), sessionPSK);

		final Random sr = new Random();
		// TR-03112-6 IFD::EstablishContext
		sr.nextBytes(this.contextHandle);
		// TR-03112-6 IFD::Connect
		sr.nextBytes(this.slotHandle);

		mainView.showMessage(
				textBundle.get("ECardWorker_connecting_from") + tcTokenURL.resolve("/")
						+ textBundle.get("ECardWorker_connecting_to") + endpoint, IMainView.RELOAD);

		session = new ECardSession(mainView, eCardHandler);
		session.setAttribute(ECardWorker.class.getName(), this);
		session.setAttribute(ECardSession.KEYS.SPServerCert.name(), sourceCerts);
		session.setAttribute(ECardSession.KEYS.tcTokenURL.name(), tcTokenURL);
		session.setAttribute(PAOSInitiator.class.getName(), paosInitiator);
	}

	private final void run() {
		/*
		 * this is a per-thread operation, so it has to be done inside the
		 * thread the initiator will work in
		 */
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);

		try {
			final ResponseType startPAOSResponse = paosInitiator.start(contextHandle, slotHandle);

			if (startPAOSResponse != null) {
				final String resMajor = startPAOSResponse.getResult() != null ? startPAOSResponse.getResult()
						.getResultMajor() : null;
				System.out.println("SPR: " + resMajor);
				if (resMajor != null && EcAPIProvider.ECARD_API_RESULT_OK.equals(resMajor.toLowerCase())) {
					// OK
				} else if (startPAOSResponse.getResult() != null) {
					System.out.println("Error: " + resMajor + ", " + startPAOSResponse.getResult().getResultMinor());
				} else {
					System.out.println("Error: no result");
				}
			} else {
				// most probably an error
				System.out.println("SPR: 'null' response to StartPAOS request.");
			}

			mainView.showMessage(textBundle.get("ECardWorker_process_finished"), IMainView.INFO);
		} catch (final IOException e) {
			e.printStackTrace();
			this.callback(e);
			mainView.showMessage(textBundle.get("ECardWorker_server_error"), IMainView.ERROR);
		} catch (final Exception e) {
			e.printStackTrace();
			this.callback(e);
		} finally {
			this.callback(ECardWorker.CALLBACK_RESULT.FINALLY);
			eCardHandler.reset();
			mainView.closeDialogs();
		}
	}
}
