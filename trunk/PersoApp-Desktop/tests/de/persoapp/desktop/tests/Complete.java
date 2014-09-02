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
import de.persoapp.desktop.MainView;
import de.persoapp.desktop.ProxyAuthenticator;

/**
 * @author ckahlo
 * 
 *         sample test case building up an integration ("complete") test
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Complete {

	/*
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

	@Test
	public void test1_1() {
		mainView = MainView.getInstance();
		assertNotNull("no main view", mainView);
	}

	@Test
	public void test1_2() {
		Authenticator.setDefault(new ProxyAuthenticator(mainView));
	}

	@Test
	public void test2_1() {
		eCardHandler = new CardHandler(mainView);
		assertNotNull("no card handler", eCardHandler);
	}

	@Test
	public void test2_2() {
		mainView.setEventLister(new MainViewEventListener(eCardHandler, mainView));
	}

	@Test
	public void test3_1() {
		wsCtx = new WSContainer();
		assertNotNull("no web service container", wsCtx);
	}

	@Test
	public void test3_2() {
		wsCtx.addService(new ManagementService());
	}

	@Test
	public void test3_3() {
		wsCtx.addService(new SALService());
	}

	@Test
	public void test3_4() {
		wsCtx.addService(new IFDService());
	}

	@Test
	public void test3_5() {
		wsCtx.init(null);
	}

	@Test
	public void test4() {
		ECardWorker.init(mainView, wsCtx, eCardHandler);
	}

	@Test
	public void test5() {
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

	@Test
	public void test6() {
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
