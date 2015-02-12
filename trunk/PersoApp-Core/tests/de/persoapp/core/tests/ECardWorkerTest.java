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
 * @version 1.0, 20.01.2015 09:58:17
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

package de.persoapp.core.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import iso.std.iso_iec._24727.tech.schema.ChannelHandleType;
import iso.std.iso_iec._24727.tech.schema.Transmit;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import mockit.Mock;
import mockit.MockUp;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.CardHandler;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.MainViewEventListener;
import de.persoapp.core.tests.util.TcTokenParam;
import de.persoapp.core.tests.util.TestMainView;
import de.persoapp.core.util.EcApi_TCTokenHandler;
import de.persoapp.core.util.Util;
import de.persoapp.core.ws.IFDService;
import de.persoapp.core.ws.ManagementService;
import de.persoapp.core.ws.SALService;
import de.persoapp.core.ws.engine.WSContainer;
import de.persoapp.core.ws.engine.WSEndpoint;

/**
 * Test cases related to the eCardWorker. An Mock object is not really capable
 * of retrieving internal information because most used objects are final and
 * the remaining objects are not an important part of the system.
 * 
 * @author Rico Klimsa
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ECardWorkerTest {
	
	/*
	 * magic constant for default test service
	 */
	private static String serviceURL = null;
	private static String DEFAULT_PIN = null;
	private static String tcToken = "";
	private static Map<String,String> ECApiParams = null;
	
	private static final String resourcePath = "/tests/resources/test_config.properties";
	
	private static Properties properties = null;
	
	
//	@BeforeClass
//	public void eCardWorkerTest_init() throws NoSuchMethodException,
//			SecurityException, IllegalAccessException,
//			IllegalArgumentException, InvocationTargetException,
//			CertificateEncodingException, IOException {
//			
//		if(sourceCerts==null) {
//			sourceCerts = new ArrayList<Certificate>();
//		}		
//		String location = serviceURL;
//
//		while (true) {
//			URL tc = new URL(location);
//			Assert.assertTrue("tcTokenURL has no https protocol", tc.getProtocol().equalsIgnoreCase("https"));
//			con = (HttpURLConnection) Util.openURL(tc);
//			con.connect();
//			Method method = ECardWorker.class.getDeclaredMethod(
//					"checkCertificate", URLConnection.class);
//			method.setAccessible(true);
//			Certificate cert = (Certificate) method.invoke(null, con);
//			method.setAccessible(false);
//
//			Assert.assertNotNull("Cert is null", cert);
//			sourceCerts.add(cert);
//			Assert.assertNotEquals("400 Http Error", con.getResponseCode(), 400);
//
//			int responseCode = con.getResponseCode();
//			if (responseCode == 302 || responseCode == 303
//					|| responseCode == 307) {
//				location = con.getHeaderField("location");
//				con.disconnect();
//			} else {
//				break;
//			}
//		}
//
//	}
	
	/**
	 * Load the resource file for default pin and
	 * service url.
	 * If the resource file does not exist, it
	 * must be created by the developer per hand.
	 */
	@BeforeClass
	public static void setUp() throws FileNotFoundException, IOException {
		final File res = new File(new File("").getAbsolutePath()+resourcePath);

		if(res.exists()) {
			properties = new Properties();
			properties.load(new FileInputStream(res));
		}
		else {
			fail("Missing file: "+res.getPath());
		}
	}
	
	
	/**
	 * 
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IMainView} is created</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The mainView was successfully created.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link CardHandler} is created.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The {@link CardHandler} is successfully created.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link MainViewEventListener} is created and set.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link WSContainer} is created.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The {@link WSContainer} is successfully created.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link ManagementService} is created and added as a service to
	 * the {@link WSContainer}. The specific {@link WSEndpoint} is added to the
	 * {@link WSContainer}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService} is created and added as a service to the
	 * {@link WSContainer}. The specific {@link WSEndpoint} is added to the
	 * {@link WSContainer}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService} is created and added as a service to the
	 * {@link WSContainer}. The specific {@link WSEndpoint} is added to the
	 * {@link WSContainer}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>.
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Initializes the {@link WSContainer} and injects all created
	 * endpoints.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>.
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Initializes the {@link ECardWorker}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>.
	 * </ul>
	 */	
	@Before
	public void init() {
		
		DEFAULT_PIN = (String) properties.get("Default_PIN");
		
		serviceURL = (String) properties.get("eID_service_URL");
		
		final IMainView mainView = TestMainView.getInstance(DEFAULT_PIN);
		assertNotNull("no main view", mainView);

		final CardHandler eCardHandler = new CardHandler(mainView);
		assertNotNull("no card handler", eCardHandler);
		assertNotNull("No eID card inserted", eCardHandler.getECard());
		mainView.setEventLister(new MainViewEventListener(eCardHandler, mainView));
		
		final WSContainer wsCtx = new WSContainer();
		assertNotNull("no web service container", wsCtx);

		wsCtx.addService(new ManagementService());
		wsCtx.addService(new SALService());
		wsCtx.addService(new IFDService());
		wsCtx.init(null);

		ECardWorker.init(mainView, wsCtx, eCardHandler);	
	}
	
	@Test
	public void eCardWorkerTest_1() throws IOException {
		
		tcToken = initTcToken();
		ECApiParams = Util.getEcApiParams(tcToken);
		
		Assert.assertNotNull("No TC Token Map", ECApiParams);
		
		for(Entry<String,String> e : ECApiParams.entrySet()) {
			Logger.getLogger(getClass().getName()).log(Level.INFO,String.format(" Key: %s  Value: %s", e.getKey(),e.getValue()));
		}
	}
	
	@Test
	public void eCardWorkerTest_2() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, MalformedURLException, URISyntaxException {
		String[] args = new String[]{"ResultMajor=ok"};
		Method method = ECardWorker.class.getDeclaredMethod("addParam", URI.class,args.getClass());
		method.setAccessible(true);
		URI uri = (URI) method.invoke(null, new URL(
				"http://localhost").toURI(), args);
		Assert.assertNotNull("URI is null", uri);
		Assert.assertTrue("Param is not added", uri.toString().contains("ResultMajor=ok"));
		method.setAccessible(false);
	}
	
	/**
	 * Calling the PersoApp Client without an tc token.<br/>
	 * <br/>
	 * <b>References: </b>TR-03124-1, Section 2.3 <em>TCToken</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The service provider of the eService calls the PersoApp and provides no tc token.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link FileNotFoundException} is thrown.</li>.
	 * </ul>
	 */
	@Test
	public void eCardWorkerTest_3(){
		
		new MockUp<Util>() {
			@Mock
			private void parseObject(final String httpObject, final ContentHandler ch) {
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
					((EcApi_TCTokenHandler)ch).getProperties().clear();
				} catch (final IOException ex) {
					System.err.println(ex.toString());
				} catch (final SAXException ex) {
					System.err.println(ex.toString());
				} catch (final ParserConfigurationException e) {
					e.printStackTrace();
				}
			}			
		};
		
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			final String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			assertFalse("process failed", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
			final URL refresh = new URL(refreshURL);
			final HttpURLConnection uc = (HttpsURLConnection) Util.openURL(refresh);
			uc.setInstanceFollowRedirects(true);
			final Object content = uc.getContent();
			System.out.println("HTTP Response " + uc.getResponseCode() + " " + uc.getResponseMessage());
			if (content instanceof InputStream) {
				final Scanner scanner = new Scanner((InputStream) content, "UTF-8").useDelimiter("\\A");
				System.out.println(scanner.next());
				scanner.close();
			} else {
				System.out.println(content);
			}
		} catch (final FileNotFoundException e) {
			//test case succeed
			return;
		} catch (final IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final URISyntaxException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}	
	
	
	/**
	 * Calling the PersoApp Client without an server address in the tc token.<br/>
	 * <br/>
	 * <b>References: </b>TR-03124-1, Section 2.3 <em>TCToken</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The service provider of the eService calls the PersoApp and provides no server address.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The application get stuck.</li>.
	 * </ul>
	 */
//	@Test
	public void eCardWorkerTest_4() {
		
		new MockUp<Util>() {
			@Mock
			private void parseObject(final String httpObject, final ContentHandler ch) {
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
					
					assertTrue("No server address in tc token",
							((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.SERVER_ADDRESS.getValue()));
					((EcApi_TCTokenHandler)ch).getProperties().remove(TcTokenParam.SERVER_ADDRESS.getValue());

					assertTrue(
							"server address is still present in the tcToken",
							!((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.SERVER_ADDRESS.getValue()));
				} catch (final IOException ex) {
					System.err.println(ex.toString());
				} catch (final SAXException ex) {
					System.err.println(ex.toString());
				} catch (final ParserConfigurationException e) {
					e.printStackTrace();
				}
			}			
		};
		
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			final String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			assertFalse("process failed", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
			final URL refresh = new URL(refreshURL);
			final HttpURLConnection uc = (HttpsURLConnection) Util.openURL(refresh);
			uc.setInstanceFollowRedirects(true);
			final Object content = uc.getContent();
			System.out.println("HTTP Response " + uc.getResponseCode() + " " + uc.getResponseMessage());
			if (content instanceof InputStream) {
				final Scanner scanner = new Scanner((InputStream) content, "UTF-8").useDelimiter("\\A");
				System.out.println(scanner.next());
				scanner.close();
			} else {
				System.out.println(content);
			}
		} catch (final IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final URISyntaxException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	/**
	 * Calling the PersoApp Client without binding in the tc token.<br/>
	 * <br/>
	 * <b>References: </b>TR-03124-1, Section 2.3 <em>TCToken</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The service provider of the eService calls the PersoApp and provides no binding.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication completes normal.</li>.
	 * </ul>
	 */
	@Test
	public void eCardWorkerTest_5() {

		new MockUp<Util>() {
			@Mock
			private void parseObject(final String httpObject, final ContentHandler ch) {
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
					
					assertTrue("No binding in tc token",
							((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.BINDING.getValue()));
					((EcApi_TCTokenHandler)ch).getProperties().remove(TcTokenParam.BINDING.getValue());

					assertTrue(
							"Binding is still present in the tcToken",
							!((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.BINDING.getValue()));
				} catch (final IOException ex) {
					System.err.println(ex.toString());
				} catch (final SAXException ex) {
					System.err.println(ex.toString());
				} catch (final ParserConfigurationException e) {
					e.printStackTrace();
				}
			}			
		};
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			final String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			assertFalse("process failed", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
			final URL refresh = new URL(refreshURL);
			final HttpURLConnection uc = (HttpsURLConnection) Util.openURL(refresh);
			uc.setInstanceFollowRedirects(true);
			final Object content = uc.getContent();
			System.out.println("HTTP Response " + uc.getResponseCode() + " " + uc.getResponseMessage());
			if (content instanceof InputStream) {
				final Scanner scanner = new Scanner((InputStream) content, "UTF-8").useDelimiter("\\A");
				System.out.println(scanner.next());
				scanner.close();
			} else {
				System.out.println(content);
			}
		} catch (final IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final URISyntaxException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	/**
	 * Calling the PersoApp Client without path security parameters in the tc token.<br/>
	 * <br/>
	 * <b>References: </b>TR-03124-1, Section 2.3 <em>TCToken</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The service provider of the eService calls the PersoApp without path security parameters.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The online authentication fails.</li>.
	 * </ul>
	 */
	@Test
	public void eCardWorkerTest_6(){

		new MockUp<Util>() {
			@Mock
			private void parseObject(final String httpObject, final ContentHandler ch) {
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
					
					assertTrue("No path security parameter in tc token",
							((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.PATH_SEC_PARAM.getValue()));
					((EcApi_TCTokenHandler)ch).getProperties().remove(TcTokenParam.PATH_SEC_PARAM.getValue());

					assertTrue(
							"Path security parameter are still present in the tcToken",
							!((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.PATH_SEC_PARAM.getValue()));
				} catch (final IOException ex) {
					System.err.println(ex.toString());
				} catch (final SAXException ex) {
					System.err.println(ex.toString());
				} catch (final ParserConfigurationException e) {
					e.printStackTrace();
				}
			}			
		};
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			assertTrue("process succeed", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
		} catch (final IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final GeneralSecurityException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Calling the PersoApp Client without path security protocol in the tc token.<br/>
	 * <br/>
	 * <b>References: </b>TR-03124-1, Section 2.3 <em>TCToken</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The service provider of the eService calls the PersoApp without an path security protocol.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The online authentication fails.</li>.
	 * </ul>
	 */
	@Test
	public void eCardWorkerTest_7() {
		new MockUp<Util>() {
			@Mock
			private void parseObject(final String httpObject, final ContentHandler ch) {
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
					
					assertTrue("No path security protocol in tc token",
							((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.PATH_SEC_PROT.getValue()));
					((EcApi_TCTokenHandler)ch).getProperties().remove(TcTokenParam.PATH_SEC_PROT.getValue());

					assertTrue(
							"Path security protocol is still present in the tcToken",
							!((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.PATH_SEC_PROT.getValue()));
				} catch (final IOException ex) {
					System.err.println(ex.toString());
				} catch (final SAXException ex) {
					System.err.println(ex.toString());
				} catch (final ParserConfigurationException e) {
					e.printStackTrace();
				}
			}			
		};
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			assertTrue("process succeed", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
		} catch (final IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final GeneralSecurityException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Calling the PersoApp Client without refresh address in the tc token.<br/>
	 * <br/>
	 * <b>References: </b>TR-03124-1, Section 2.3 <em>TCToken</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The service provider of the eService calls the PersoApp without an refresh address.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The online authentication fails with an {@link NullPointerException}.</li>.
	 * </ul>
	 */
	@Test
	public void eCardWorkerTest_8() {
		new MockUp<Util>() {
			@Mock
			private void parseObject(final String httpObject, final ContentHandler ch) {
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
					
					assertTrue("No refresh address in tc token",
							((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.REFRESH_ADDRESS.getValue()));
					((EcApi_TCTokenHandler)ch).getProperties().remove(TcTokenParam.REFRESH_ADDRESS.getValue());

					assertTrue(
							"Refresh address is still present in the tcToken",
							!((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.REFRESH_ADDRESS.getValue()));
				} catch (final IOException ex) {
					System.err.println(ex.toString());
				} catch (final SAXException ex) {
					System.err.println(ex.toString());
				} catch (final ParserConfigurationException e) {
					e.printStackTrace();
				}
			}			
		};
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			assertTrue("process succeeded", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
		} catch (final NullPointerException e) {
			// test succeeded
			return;
		} catch (final IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final GeneralSecurityException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Calling the PersoApp Client without an session identifier in the tc token.<br/>
	 * <br/>
	 * <b>References: </b>TR-03124-1, Section 2.3 <em>TCToken</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The service provider of the eService calls the PersoApp without an session identifier.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The online authentication fails throwing an {@link NullPointerException}.</li>.
	 * </ul>
	 */
	@Test
	public void eCardWorkerTest_9() {
		new MockUp<Util>() {
			@Mock
			private void parseObject(final String httpObject, final ContentHandler ch) {
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
					
					assertTrue("No session identifier in tc token",
							((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.SESSION_IDENTIFIER.getValue()));
					((EcApi_TCTokenHandler)ch).getProperties().remove(TcTokenParam.SESSION_IDENTIFIER.getValue());

					assertTrue(
							"Session identifer is still present in the tcToken",
							!((EcApi_TCTokenHandler) ch).getProperties()
									.containsKey(TcTokenParam.SESSION_IDENTIFIER.getValue()));
				} catch (final IOException ex) {
					System.err.println(ex.toString());
				} catch (final SAXException ex) {
					System.err.println(ex.toString());
				} catch (final ParserConfigurationException e) {
					e.printStackTrace();
				}
			}			
		};
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			final String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			assertTrue("process succeeded", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
		} catch (final NullPointerException e) {
			// test succeeded
			return;
		}
		catch (final IOException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final URISyntaxException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (final GeneralSecurityException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	

	private static String initTcToken() throws IOException {
		final StringBuffer sb = new StringBuffer();
		
		sb.append("<TCTokenType>");
		sb.append("<ServerAddress>https://eid.vx4.net:443/eid/ws/paos/</ServerAddress>");
		sb.append("<SessionIdentifier>53F95A1AC9D3FB0986D09582A31A3E79</SessionIdentifier>");
		sb.append("<RefreshAddress>");
		sb.append("https://eid.services.ageto.net/gw/_auth/exec?id=692E7402A58D4A02D5A726FB4EE78F4B811A9305EF2B353677765C750C8D1788");
		sb.append("</RefreshAddress>");
		sb.append("<Binding>urn:liberty:paos:2006-08</Binding>");
		sb.append("<PathSecurity-Protocol>urn:ietf:rfc:4279</PathSecurity-Protocol>");
		sb.append("<PathSecurity-Parameters>");
		sb.append("<PSK>");
		sb.append("B9B012BDD01EA9AE8B0D178EDD2C322A8DEAC3586FB235B278E18DE399CAA08E");
		sb.append("</PSK>");
		sb.append("</PathSecurity-Parameters>");
		sb.append("</TCTokenType>");

		return Util.readStream(new ByteArrayInputStream(sb.toString().getBytes()));
	}

}
