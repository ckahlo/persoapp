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

package de.persoapp.desktop.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.CardHandler;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.MainViewEventListener;
import de.persoapp.core.util.Util;
import de.persoapp.core.ws.IFDService;
import de.persoapp.core.ws.ManagementService;
import de.persoapp.core.ws.SALService;
import de.persoapp.core.ws.engine.WSContainer;
import de.persoapp.core.ws.engine.WSEndpoint;
import de.persoapp.desktop.MainView;
import de.persoapp.desktop.ProxyAuthenticator;

/**
 * sample test case building up an integration ("complete") test
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Complete {

	/**
	 * magic constant for default test service
	 */
	private final String		serviceURL	= "https://eid.services.ageto.net/persoapp/eidtest.jsp";

	/*
	 * needed between test-cases
	 */
	private static IMainView	mainView;
	private static CardHandler	eCardHandler;
	private static WSContainer	wsCtx;

	private static String		refreshURL;

	static {
		System.out.println("Test " + Complete.class.getName() + " loaded.");
	}

	{
		System.out.println("Test " + this.getClass().getName() + " created.");
	}

	/**
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
	 */		
	@Test
	public void a1_initializeMainView() {
		mainView = MainView.getInstance();
		assertNotNull("no main view", mainView);
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #a1_initializeMainView()} was successful</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link ProxyAuthenticator} is created and set.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>
	 * </ul>
	 */			
	@Test
	public void a2_initializeAuthenticator() {
		try{
		Authenticator.setDefault(new ProxyAuthenticator(mainView));
		} catch (final Exception e) {
			fail("Exception occurred: "+e.getStackTrace()[0]);
		}
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #a2_initializeAuthenticator()} was successful.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link CardHandler} is created.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The {@link CardHandler} is successfully created.</li>
	 * </ul>
	 */			
	@Test
	public void b1_initializeCardHandler() {
		eCardHandler = new CardHandler(mainView);
		assertNotNull("no card handler", eCardHandler);
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #b1_initializeCardHandler()} was successful.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link MainViewEventListener} is created and set.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred, which indicates an successful result.</li>
	 * </ul>
	 */	
	@Test
	public void b2_initializeMainViewEventListener() {
		mainView.setEventLister(new MainViewEventListener(eCardHandler, mainView));
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #b2_initializeMainViewEventListener()} was successful.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link WSContainer} is created.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The {@link WSContainer} is successfully created.</li>
	 * </ul>
	 */	
	@Test
	public void c1_constructWSContainer() {
		wsCtx = new WSContainer();
		assertNotNull("no web service container", wsCtx);
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #c1_constructWSContainer()} was successful.</li>
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
	 */
	@Test
	public void c2_addManagementServiceEndpoint() {
		wsCtx.addService(new ManagementService());
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #c2_addManagementServiceEndpoint()} was successful.</li>
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
	 */	
	@Test
	public void c3_addSALServiceEndpoint() {
		wsCtx.addService(new SALService());
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #c3_addSALServiceEndpoint()} was successful.</li>
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
	 */
	@Test
	public void c4_addIFDServiceEndpoint() {
		wsCtx.addService(new IFDService());
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #c4_addIFDServiceEndpoint()} was successful.</li>
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
	 */
	@Test
	public void c5_initializeWSContainer() {
		wsCtx.init(null);
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #c5_initializeWSContainer()} was successful.</li>
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
	public void d1_initializeECardWorker() {
		ECardWorker.init(mainView, wsCtx, eCardHandler);
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #test_4()} was successful.</li>
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
	 */
	@Test
	public void e1_startECardWorker() {
		URL tcTokenURL = null;

		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		try {
			refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);
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
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * <li>{@link #test_5()} was successful.</li>
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
	 * <li>No Exception occurred and and the refreshURL leads to the eService,
	 * which indicates an successful result.</li>.
	 * </ul>
	 */
	@Test
	public void f1_retrieveResponse() {
		try {
			System.out.println("refreshURL: " + refreshURL);
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
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
