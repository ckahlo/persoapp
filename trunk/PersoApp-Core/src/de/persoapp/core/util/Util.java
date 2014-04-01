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
package de.persoapp.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.persoapp.core.tls.BCTlsSocketFactoryImpl;

/**
 * @author ckahlo
 * 
 */
public class Util {
	private static SSLSocketFactory	sslSF	= null;

	private static SSLSocketFactory getSSLSocketFactory() {
		if (sslSF == null) {
			sslSF = new BCTlsSocketFactoryImpl();
		}
		return sslSF;
	}

	public static String readStream(final InputStream is) throws IOException {
		final StringBuffer sb = new StringBuffer();
		final BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String t = null;
		while ((t = br.readLine()) != null) {
			sb.append(t);
		}
		br.close();
		is.close();
		return sb.toString();
	}

	public static Map<String, String> getEcApiParams(String httpObject) {
		if (httpObject.contains("<TCTokenType>")) {
			final String START = "<TCTokenType>", END = "</TCTokenType>";
			final int n1 = httpObject.indexOf(START);
			final int n2 = n1 >= 0 ? httpObject.indexOf(END, n1) : -1;
			if (n2 > n1) {
				httpObject = httpObject.substring(n1, n2 + END.length());
				final EcApi_TCTokenHandler EA_tth = new EcApi_TCTokenHandler();
				parseObject(httpObject, EA_tth);
				return EA_tth.getProperties();
			}
		}

		return null;
	}

	private static void parseObject(final String httpObject, final ContentHandler ch) {
		try {
			XMLReader reader = null;
			try {
				reader = XMLReaderFactory.createXMLReader();
			} catch (final SAXException ex) {
			}

			if (reader == null) {
				final SAXParserFactory spf = SAXParserFactory.newInstance();
				reader = spf.newSAXParser().getXMLReader();
			}

			reader.setContentHandler(ch);
			reader.parse(new InputSource(new StringReader(httpObject)));
		} catch (final IOException ex) {
			System.err.println(ex.toString());
		} catch (final SAXException ex) {
			System.err.println(ex.toString());
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static URLConnection openURL(final URL url) {
		URLConnection uc = null;
		try {
			for (final Proxy proxy : ProxySelector.getDefault().select(url.toURI())) {
				try {
					System.out.println("proxy: " + proxy);
					uc = url.openConnection(proxy);
					break;
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		} catch (final URISyntaxException e) {
			e.printStackTrace();
		}

		if (uc != null && uc instanceof HttpURLConnection) {
			((HttpURLConnection) uc).setInstanceFollowRedirects(false);

			if (uc instanceof HttpsURLConnection && getSSLSocketFactory() != null) {
				((HttpsURLConnection) uc).setSSLSocketFactory(getSSLSocketFactory());
			}
		}

		return uc;
	}

	public static boolean validateIdentity(final X509Certificate cert, final URI uri) {
		try {
			final Collection<List<?>> altNames = cert.getSubjectAlternativeNames();
			if (altNames != null && !altNames.isEmpty()) {
				for (final List<?> extensionEntry : altNames) {
					final Integer nameType = (Integer) extensionEntry.get(0);
					final Object name = extensionEntry.get(1);
					if (name != null) {
						// 0 = otherName, 1 = rfc822Name (IA5), 2 = dNSName (IA5), 3 = x400Address,
						// 4 = directoryName, 5 = ediPartyName, 6 = URI (IA5), 7 = IPAddress,
						// 8 = registered object identifier
						final String val = String.valueOf(name);
						if (nameType == 6) { // uri
							// check same-origin policy of URIs
							if (new URI(val).resolve("/").equals(uri.resolve("/"))) {
								return true;
							}
						} else if (nameType == 2) { // DNS Name
							if (val.startsWith("*.") && uri.getHost().endsWith(val.substring(1))) {
								return true;
							} else if (val.equalsIgnoreCase(uri.getHost())) {
								return true;
							}
						}
						// unsupported name-type
					}
				}
			}

			String subjectName = cert.getSubjectDN().getName();
			final int i0 = subjectName.indexOf("CN=") + 3;
			if (i0 >= 0) {
				int i1 = subjectName.indexOf(',', i0);
				i1 = i1 < 0 ? subjectName.length() : i1;
				subjectName = subjectName.substring(i0, i1);
			}

			// new X500Name(subjectName).getCommonName()
			if (uri.getHost().equalsIgnoreCase(subjectName)) {
				return true;
			} else if (subjectName.startsWith("*.") && uri.getHost().endsWith(subjectName.substring(1))) {
				return true;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
