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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.persoapp.core.client.EAC_Info;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;
import de.persoapp.core.util.Util;

/**
 * @author ckahlo
 * 
 */
public final class ECApiHttpHandler implements HttpHandler {

	private static final String	HTTP_USER_AGENT	= "eID-Client";
	private static final String	HTTP_CHARSET	= "ISO-8859-1";

	private static final String	HTTP_METH_HEAD	= "HEAD";
	private static final String	HTTP_METH_GET	= "GET";
	private static final String	HTTP_METH_POST	= "POST";

	private static final String	TC_TOKEN_REQ	= "/eID-Client";
	private static final String	TC_TOKEN_URL	= "tcTokenURL=";

	private static final String	TLS_PSK_USR		= "SessionIdentifier";
	private static final String	TLS_PSK_KEY		= "PathSecurity-Parameters";
	private static final String	TLS_PSK_SVR		= "ServerAddress";
	private static final String	PATH_PROTOCOL	= "PathSecurity-Protocol";
	private static final String	PATH_BINDING	= "Binding";
	private static final String	REFRESH_ADDR	= "RefreshAddress";

	private final X509Certificate checkCertificate(final URLConnection uc) throws IOException, URISyntaxException {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange
	 * )
	 */
	@Override
	public final void handle(final HttpExchange he) throws IOException {
		Throwable exception = null;
		String tcData = null;

		final String requestMethod = he.getRequestMethod();
		String qs = he.getRequestURI().getQuery();

		if (qs != null && qs.startsWith("activationObject=")) {
			final String START_MARKER = "document.location.href = \"http://127.0.0.1:24727/eID-Client?";
			final String END_MARKER = "\";\n";
			final int n = qs.indexOf(START_MARKER);
			if (n > 0) {
				final int m = qs.indexOf(END_MARKER);
				if (m > n + START_MARKER.length()) {
					qs = qs.substring(n + START_MARKER.length(), m);
				}
			}
		}

		System.out.println("qs: " + qs);

		final URL tcTokenURL = qs != null && qs.contains(TC_TOKEN_URL) ? new URL(URLDecoder.decode(qs.substring(qs
				.indexOf(TC_TOKEN_URL) + TC_TOKEN_URL.length()))) : null;

		final String referer = he.getRequestHeaders().getFirst("Referer");

		System.out.println("EAHH: tcTokenURL = " + tcTokenURL);
		System.out.println("EAHH: referer = " + referer);

		if (HTTP_METH_HEAD.equals(requestMethod)) {
			he.getResponseHeaders().add("Server", HTTP_USER_AGENT);
			he.sendResponseHeaders(200, -1);
			he.close();
			return;
		} else if (HTTP_METH_GET.equals(requestMethod)) {
			try {
				if (tcTokenURL != null) {
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
						tcData = Util.readStream(uc.getInputStream());
						// disconnect for possible re-use of TLS-channel
						uc.disconnect();

						params = Util.getEcApiParams(tcData);
						if (params != null && params.size() > 0) {
							startECardWorker(params, sourceCerts, tcTokenURL.toURI(), he);
							he.close();
							return;
						} else {
							exception = new FileNotFoundException("No parameters");
						}
					} else {
						exception = new FileNotFoundException(uc.getResponseCode() + " " + uc.getResponseMessage());
					}
				}
			} catch (final Exception e) {
				e.printStackTrace();
				exception = e;
			}
		}

		he.getResponseHeaders().add("Content-Type", "text/html");
		if (exception != null) {
			if (exception instanceof IllegalArgumentException || exception instanceof IllegalStateException) {
				he.sendResponseHeaders(400, 0);
			} else {
				he.sendResponseHeaders(404, 0);
			}
		} else {
			he.sendResponseHeaders(200, 0);
		}

		InputStream resource = null;
		String message = null;

		if (exception != null) {
			message = "Error:<br/>";
			if (exception instanceof FileNotFoundException) {
				message += "TCToken konnte nicht gefunden werden: " + exception.getMessage();
			} else {
				while (exception.getCause() != null) {
					exception = exception.getCause();
				}

				message += exception.getMessage();
			}
		} else if (tcData != null) {
			message = "<br/>" + tcData;
		} else if (tcTokenURL == null) {
			final URI requestURI = he.getRequestURI();
			if ("/favicon.ico".equalsIgnoreCase(requestURI.getPath())) {
				resource = this.getClass().getResourceAsStream("/resources/ic_tray.png");
			}

		} else {
			// No error, no data, to tcTokenURL
			message = "No TCToken found.";
		}

		final String propertyFile = "version";
		final String buildVersion = PropertyResolver.getProperty(propertyFile, "buildVersion");
		final String buildRevision = PropertyResolver.getProperty(propertyFile, "buildRevision");
		final String buildNo = PropertyResolver.getProperty(propertyFile, "buildNo");
		final String buildDate = PropertyResolver.getProperty(propertyFile, "buildDate");

		final OutputStream os = he.getResponseBody();

		if (resource == null) {
			os.write(("<html><head><title>PersoApp eID-Client</title></head><body style=\"font-family:arial;font-size:10pt\">"
					+ "<a href=\"https://www.persoapp.de/\">PersoApp eID-Client</a>&nbsp;"
					+ buildVersion
					+ "b"
					+ buildRevision
					+ "-"
					+ buildNo
					+ " @ "
					+ buildDate
					+ "<br/><br/>"
					+ (message != null ? message : "")
					+ "<br/><br/>"
					+ "eCard-API Receiver on "
					+ System.getProperty("os.name")
					+ " V"
					+ System.getProperty("os.version")
					+ " with "
					+ System.getProperty("java.vendor")
					+ " V"
					+ System.getProperty("java.version")
					+ "<br/><br/>"
					+ "tcTokenURL: "
					+ tcTokenURL
					+ "<br/>"
					+ "Referer: "
					+ referer
					+ "<br/>"
					+ "<br/>"
					+ "<form method=\"GET\" action=\"/eID-Client\">Connect to&nbsp;"
					+ "<input type=\"text\" name=\"tcTokenURL\" size=\"40\"/>&nbsp;<input type=\"submit\"value=\"OK\"/></form><br/>" + "</body></html>")
					.getBytes(HTTP_CHARSET));
		} else {
			final byte[] resourceBuf = new byte[resource.available()];

			for (int xfer = 0; xfer < resourceBuf.length; xfer += resource.read(resourceBuf, xfer, resourceBuf.length
					- xfer)) {
				;
			}

			os.write(resourceBuf);
		}

		os.flush();
		he.close();
	}

	/*
	 * start eCardWorker with retrieved connection handle parameters
	 */

	private void startECardWorker(final Map<String, String> params, final List<Certificate> serverCerts,
			final URI tcTokenURL, final HttpExchange he) throws Exception {

		System.out.println("Params: " + params);

		he.getResponseHeaders().add("Server", HTTP_USER_AGENT);
		he.getResponseHeaders().set("Connection", "close");
		// he.getResponseHeaders().set("Content-Length", String.valueOf(0));
		// he.sendResponseHeaders(102, -1);

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
							sendResponse(he, 303, addParam(refreshURI, "ResultMajor=ok"), null);
							return;
						}
					}
				} catch (final Exception e) {
					e.printStackTrace();
				}

				System.out.println("certificate not matched");
			}

			final String message = "Fehler bei Weiterleitung.";
			sendResponse(he, 400, null, message);
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
				sendResponse(he, 400, null, _msg);
			} else {
				sendResponse(he, 303, addParam(refreshURI, "ResultMajor=error"), null);
			}
		}
	}

	/*
	 * write response
	 */
	private void sendResponse(final HttpExchange he, final int code, final URI refreshURI, final String message)
			throws IOException {
		final byte[] _msg = message == null ? null : message.getBytes(HTTP_CHARSET);

		final Headers respHeaders = he.getResponseHeaders();
		// respHeaders.set("Connection", "close");
		// respHeaders.set("Content-Length", String.valueOf(_msg == null ? 0 : _msg.length));
		respHeaders.set("Content-Type", "text/plain");

		if (refreshURI != null) {
			he.getResponseHeaders().set("Location", refreshURI.toASCIIString());
		}
		he.sendResponseHeaders(code, _msg == null ? 0 : _msg.length);
		/*
		 * os.write(( "HTTP/1.1 " + code + "\r\n" + "Connection: close\r\n" +
		 * "Content-Length: " + (_msg == null ? 0 : _msg.length) + "\r\n" +
		 * "Content-Type: text/plain\r\n" + (refreshURI != null ? ("Location: "
		 * + refreshURI.toASCIIString() + "\r\n") : "") + "\r\n"
		 * ).getBytes(HTTP_CHARSET));
		 */
		if (_msg != null) {
			final OutputStream os = he.getResponseBody();
			os.write(_msg);
			os.flush();
		}
	}

	/*
	 * helper to add params to URI
	 */
	private URI addParam(final URI uri, final String... params) {
		final StringBuffer uriBuffer = new StringBuffer(uri.toString());
		boolean first = !uri.toString().contains("?");

		for (final String param : params) {
			uriBuffer.append((first ? '?' : '&') + param);
			first = false;
		}

		return URI.create(uriBuffer.toString());
	}
}
