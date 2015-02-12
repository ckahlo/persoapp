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
 * @version 1.0, 02.09.2014 11:56:44
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
 *          PersoApp ist Freie Software: Sie k�nnen es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          sp�teren ver�ffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 * 
 *          PersoApp wird in der Hoffnung, dass es n�tzlich sein wird, aber OHNE
 *          JEDE GEW�HRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gew�hrleistung der MARKTF�HIGKEIT oder EIGNUNG F�R EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License f�r weitere
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.CardHandler;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.MainViewEventListener;
import de.persoapp.core.tests.util.TestMainView;
import de.persoapp.core.util.Util;
import de.persoapp.core.ws.IFDService;
import de.persoapp.core.ws.ManagementService;
import de.persoapp.core.ws.SALService;
import de.persoapp.core.ws.engine.WSContainer;
import de.persoapp.core.ws.engine.WSEndpoint;

/**
 * Sample test case building up an integration ("complete") test.
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Complete {

	/*
	 * magic constant for default test service
	 */
	private String	serviceURL	= null;
	private String	DEFAULT_PIN	= null;

	private static final String resourcePath = "/tests/resources/test_config.properties";
	
	private static Properties properties = null;
	
	static {
		System.out.println("Test " + Complete.class.getName() + " loaded.");
	}

	{
		System.out.println("Test " + this.getClass().getName() + " created.");
	}

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
	 * Initialize important test objects as singletons.
	 */
	@Before
	public void init(){

		if(DEFAULT_PIN == null) {
			DEFAULT_PIN = (String) properties.get("Default_PIN");
		}
		
		if(serviceURL == null) {
			serviceURL = (String) properties.get("eID_service_URL");
		}
	}
	
	
	/**
	 * 
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active test eID-Card is connected to the card reader.</li>
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
	@Test
	public void a1_initialization() {
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

	/**
	 * Test case doing the complete alternative call with an supplied
	 * serviceURL.<br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #a1_initialization()} was successful.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Creates the tcTokenURL from the {@link #serviceURL}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No {@link MalformedURLException} occurred, which indicates an
	 * successful result.</li>.
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link ECardWorker} is started with the tcTokenURL.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No {@link MalformedURLException} occurred and an refreshURL is
	 * received by the test, which indicates an successful result.</li>.
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Creates the refresh from the {@link #refreshURL}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No {@link MalformedURLException} occurred, which indicates an
	 * successful result.</li>.
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>An connection to the {@link #refreshURL} is established.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exceptions occurred and and the refreshURL leads to the eService,
	 * which indicates an successful result.</li>.
	 * </ul>
	 */
	@Test
	public void b1_alternativeInvocation() {
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
}
