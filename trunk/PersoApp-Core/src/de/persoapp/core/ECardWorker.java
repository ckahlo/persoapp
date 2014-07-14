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
import java.security.GeneralSecurityException;
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
import de.persoapp.core.ws.SALService;
import de.persoapp.core.ws.engine.WSContainer;

/**
 * ECardWorker handles and supervises incoming authentication requests. This
 * includes parsing and fetching the tcTokenURL, checking server certificates,
 * verifying URLs, starting {@link PAOSInitiator} to handle PAOS service
 * requests from the eID-Server. Furthermore it provides a callback for the
 * {@link SALService} and waits for the terminal authentication result. The SAL
 * decides if the server certificates match the authorization certificate while
 * the eID card verifies the certificate chain and terminal signature. If
 * connection bindings match, the terminal certificate chain is valid and the
 * terminal signature is correct this class returns either a https URI
 * ("https://<subject-URL>/...") or a status message.
 * 
 * TODO: AuthencationFailedException or similar would be more reasonable.
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public final class ECardWorker {

	/**
	 * Magic constant declaring the HTTPS scheme
	 */
	private static final String	SCHEME_HTTPS	= "https://";

	/**
	 * constant describing the eID session id field in TCToken
	 */
	private static final String	TLS_PSK_USR		= "SessionIdentifier";

	/**
	 * constant describing the path security element (pre-shared key) in TCToken
	 */
	private static final String	TLS_PSK_KEY		= "PathSecurity-Parameters";

	/**
	 * constant describing the eID server hostname (protocol termination
	 * endpoint) in TCToken
	 */
	private static final String	TLS_PSK_SVR		= "ServerAddress";

	/**
	 * constant describing the transport protocol to be used for the connection
	 * (TLS1.1/TLS1.2 RSA_PSK) in TCToken
	 */
	private static final String	PATH_PROTOCOL	= "PathSecurity-Protocol";

	/**
	 * constant describing the (web service) binding in TCToken (PAOS)
	 */
	private static final String	PATH_BINDING	= "Binding";

	/**
	 * constant describing the destination URL after authentication in TCToken
	 */
	private static final String	REFRESH_ADDR	= "RefreshAddress";

	/**
	 * result values for callback from SAL
	 */
	public static enum CALLBACK_RESULT {
		PROCESSING, FINALLY, TA_OK, CANCEL, TA_ERROR
	};

	/*
	 * static values, valid for the application live-time
	 */
	/**
	 * GUI instance of application
	 */
	private static IMainView						mainView;

	/**
	 * container instance for local PAOS web-service
	 */
	private static WSContainer						wsCtx;

	/**
	 * card-handler instance for eID-sessions
	 */
	private static ICardHandler						eCardHandler;

	/**
	 * localized message bundle for user interaction
	 */
	private static final PropertyResolver.Bundle	textBundle		= PropertyResolver.getBundle("text_core");

	/*
	 * per worker/session identifiers
	 */
	/**
	 * randomly generated handle for the connection context
	 */
	private final byte[]							contextHandle	= new byte[32];

	/**
	 * randomly generated handle for the card slot
	 */
	private final byte[]							slotHandle		= new byte[32];

	/**
	 * currently running session and initiator
	 */

	private final ECardSession						session;

	/**
	 * The <tt>PAOSInitiator</tt> initiates the PAOS-Webservice.
	 */
	private final PAOSInitiator						paosInitiator;

	/**
	 * The <tt>info</tt> value holds various informations during the entire
	 * running time of the <tt>ECardWorker</tt>
	 */
	private Object[]								info			= null;

	/**
	 * setup static application-wide values such as GUI instance, PAOS
	 * web-service container and card handler instance
	 * 
	 * 
	 * @param mainView
	 *            - GUI instance of application
	 * @param wsCtx
	 *            - web service container for ISO24727 services
	 * @param eCardHandler
	 *            - card handler instance to be passed in ECardSessions
	 * 
	 */
	public static final void init(final IMainView mainView, final WSContainer wsCtx, final ICardHandler eCardHandler) {
		if (ECardWorker.mainView == null) {
			ECardWorker.mainView = mainView;
			ECardWorker.wsCtx = wsCtx;
			ECardWorker.eCardHandler = eCardHandler;
		}
	}

	/**
	 * check server certificate for matching hostname
	 * 
	 * @param uc
	 *            - established URLConnection to check
	 * @return server certificate
	 * 
	 * @throws IOException
	 *             - if a connection error occurs
	 * @throws URISyntaxException
	 *             - if the URL of the connection is not a valid URI (unsual)
	 * @throws IllegalArgumentException
	 *             - if the server certificate does not match the hostname from
	 *             the URL/URI
	 */
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

	/**
	 * Starts the <tt>ECardWorker</tt> with the supplied tcTokenURL.
	 * 
	 * @param tcTokenURL
	 *            - tcTokenURL referring to service provider
	 * @return validated https-URL for redirect in string representation or
	 *         error message
	 * 
	 * @throws FileNotFoundException
	 *             - if the tcTokenURL doesn't serve a valid TCToken or could
	 *             not be found (404)
	 * @throws IOException
	 *             - if connection errors occur
	 * @throws URISyntaxException
	 *             - if tcTokenURL is not a valid URI
	 * @throws GeneralSecurityException
	 *             - if certificate parsing fails or cryptographic algorithms
	 *             are missing
	 * @throws IllegalArgumentException
	 *             If the given URL is no https URL.
	 */
	public static final String start(final URL tcTokenURL) throws IOException, URISyntaxException,
			GeneralSecurityException {
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

	/**
	 * Adds the input strings as parameters to the given {@link URI}.
	 * 
	 * @param uri
	 *            - URI to be modified
	 * @param params
	 *            - parameters to be added to URI
	 * 
	 * @return URI with added params
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

	/**
	 * Start ECardWorker with retrieved TCToken parameters, wait for result,
	 * check RefreshAddress and return the validated URI for redirect.
	 * 
	 * @param params
	 *            - TCToken as Map of contained parameters
	 * @param serverCerts
	 *            - server certificate(s) of eService
	 * @param tcTokenURL
	 *            - original URL of TCToken for verification against subjectURL
	 *            of authorization certificate
	 * 
	 * @return validated https-URL for redirect in string representation or
	 *         error message
	 * @throws URISyntaxException
	 *             - if RefreshAddress is not a valid URI
	 * @throws IOException
	 *             - if a connection error occurs
	 * @throws GeneralSecurityException
	 *             - if certificate parsing fails or cryptographic algorithms
	 *             are missing
	 */
	private static String startECardWorker(final Map<String, String> params, final List<Certificate> serverCerts,
			final URI tcTokenURL) throws GeneralSecurityException, IOException, URISyntaxException {

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
						if (true) { //(ArrayTool.arrayequal(certHash, cdHash)) {
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

			// subjectURL verification failed || TAVerification failed
			if (stateInfo instanceof Boolean && (Boolean) stateInfo == false) {
				final String _msg = "Terminal Authentication oder subject-URL Verification fehlgeschlagen.";
				return message;
			} else {
				return addParam(refreshURI, "ResultMajor=error").toString();
			}
		}
	}

	/**
	 * Starts ECardWorker with the given parameters from <em>ISO24727</em>
	 * -Protocols service or local method, wait for result, return
	 * {@link CALLBACK_RESULT}
	 * 
	 * @param ch
	 *            - {@link ChannelHandleType} with connection parameters for eID
	 *            server
	 * @param sessionPSK
	 *            - pre-shared key for TLS_RSA_PSK connection
	 * @param origin
	 *            - original URL of TCToken for verification against subjectURL
	 *            of authorization certificate
	 * @param certs
	 *            - server certificate(s) of eService
	 * 
	 * @return {@link CALLBACK_RESULT} and refreshAddress or error message
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
	 * Called from {@link ECardWorker} or {@link SALService} to notify all
	 * threads waiting for this instance of {@link ECardWorker} on error, on
	 * cancel, on success.
	 * 
	 * @param info - {@link CALLBACK_RESULT} and optional additional messages
	 */
	public final void callback(final Object... info) {
		synchronized (this) {
			if (this.info == null) {
				this.info = info;
			}
			this.notifyAll();
		}
	}

	/**
	 * Retrieves the current / result state.
	 * 
	 * @return {@link CALLBACK_RESULT}
	 */
	public final Object getCurrentState() {
		return this.info == null ? null : this.info[0];
	}

	/**
	 * Creates a ECardWorker, set contextHandle and slotHandle and create
	 * {@link ECardSession}.
	 * 
	 * @param ch
	 *            - {@link ChannelHandleType} containing eID server connection
	 *            parameters
	 * @param sessionPSK
	 *            - pre-shared key for TLS_RSA_PSK connection
	 * @param tcTokenURL
	 *            - URL of TCToken from eService
	 * @param sourceCerts
	 *            - certificate(s) of eService
	 * @throws URISyntaxException
	 *             - if server address is not a valid URI
	 * @throws IOException
	 *             - if {@link PAOSInitiator} encounters errors
	 */
	private ECardWorker(final ChannelHandleType ch, final byte sessionPSK[], final URI tcTokenURL,
			final Certificate[] sourceCerts) throws URISyntaxException, IOException {
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

	/**
	 * Actual real "worker", starting PAOSInitiator, retrieving
	 * StartPAOSResponse and calling back the caller-thread.
	 * 
	 */
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
			//			this.callback(ECardWorker.CALLBACK_RESULT.FINALLY);
			eCardHandler.reset();
			mainView.closeDialogs();
		}
	}
}
