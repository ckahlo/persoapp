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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import de.persoapp.core.client.PropertyResolver;

/**
 * The ECApiHttpHandler handles requests which are send to the internal server
 * of the eID-Client. First of all, it checks if the received query string has
 * an tcTokenURL attached. If one is attached, the ECApiHttpHandler forwards the
 * tcTokenURL to the ECardWorker for validating and fetches the response.<br/>
 * If the response is an valid https://URL the browser is redirected to the
 * specific URL. <br/>
 * If an error occurs the browser is receiving the specific error message.<br/>
 * See: TR-03124-1 for further information.
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 * 
 * @see {@link ECardWorker}
 */
public final class ECApiHttpHandler implements HttpHandler {

	/**
	 * The user-agent identifier of the PersoApp.
	 */
	private static final String	HTTP_USER_AGENT	= "eID-Client";
	
	/**
	 * The http-charset.
	 */
	private static final String	HTTP_CHARSET	= "ISO-8859-1";

	/**
	 * The http-method HEAD.
	 */
	private static final String	HTTP_METH_HEAD	= "HEAD";
	
	/**
	 * The http-method GET.
	 */
	private static final String	HTTP_METH_GET	= "GET";
	
	/**
	 * The http-method POST.
	 */
	private static final String	HTTP_METH_POST	= "POST";

	/**
	 * The token context.
	 */
	private static final String	TC_TOKEN_REQ	= "/eID-Client";
	
	/**
	 * The url which shows the address of the eService to retrieve the trusted
	 * channel token.
	 */
	private static final String	TC_TOKEN_URL	= "tcTokenURL=";

	/**
	 * This function checks the query string of the received request. If the
	 * query string matches the template <local
	 * address>/eID-Client?tcTokenURL=https://<URI of the service provicer>, the
	 * ECardWorker is started with the supplied tcTokenURL. If the tcToken can't
	 * be retrieved through the ECardWorker due to some error or if the query
	 * string is malformed, an response with an error code and an human readable
	 * error message is transmitted to the browser as result of the received
	 * request.
	 * 
	 * @param he
	 *            - The object, which examines requests and builds responses.
	 * @see
	 * com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange            
	 */
	@Override
	public final void handle(final HttpExchange he) throws IOException {
		Throwable exception = null;
		final String tcData = null;

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
			he.getResponseHeaders().add("Server", HTTP_USER_AGENT);
			he.getResponseHeaders().set("Connection", "close");
			// he.getResponseHeaders().set("Content-Length", String.valueOf(0));
			// he.sendResponseHeaders(102, -1);

			URL refreshURL = null;
			if (tcTokenURL != null) {
				try {
					final String result = ECardWorker.start(tcTokenURL);
					if (result != null) {
						if (result.toLowerCase().startsWith("https://")) {
							refreshURL = new URL(result);
							sendResponse(he, 303, refreshURL.toURI(), null);
						} else {
							sendResponse(he, 400, null, result);
						}
					} else {
						System.out.println("Warning to result!");
						throw new FileNotFoundException("missing result");
					}
				} catch (final Exception e) {
					e.printStackTrace();
					exception = e;
				}
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

	/**
	 * This function sends the response of the request with the forwarded
	 * tcTokenURL.
	 * 
	 * @param he
	 *            - The {@link HttpExchange}, to build the response.
	 * @param code
	 *            - The html status code, to be send.
	 * @param refreshURI
	 *            - The refresh {@link URI}, to redirect the client after the
	 *            conclusion of the online authentication.
	 * @param message
	 *            - The message to send, within the response.
	 * 
	 * @throws IOException
	 *             If the used {@link OutputStream} is not ready.
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

}
