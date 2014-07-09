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
package de.persoapp.desktop;

import java.awt.SplashScreen;
import java.io.File;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;

import de.persoapp.core.ECApiHttpHandler;
import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.CardHandler;
import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.MainViewEventListener;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.ws.IFDService;
import de.persoapp.core.ws.ManagementService;
import de.persoapp.core.ws.SALService;
import de.persoapp.core.ws.engine.WSContainer;

/**
 * <p>
 * The <tt>PersoApp</tt> class is the main class and entry point of the whole
 * <em>PersoApp</em> application.
 * </p>
 * <p>
 * <ul>
 * <li>Initialization and start of the local {@link HttpServer} and
 * {@link ECApiHttpHandler} to handle invocations via tcTokenURL eID activation
 * requests.</li>
 * <li>Initialization of the {@link CardHandler}.</li>
 * <li>Initialization of the {@link WSContainer} and PAOS web-services.</li>
 * <li>Creation of the {@link MainView} GUI instance.</li>
 * </ul>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public final class PersoApp implements Runnable {

	// avoid "localhost" name spoofing
	/**
	 * use the IP address of the localhost interface to bind the http server to
	 */
	private static final String	EID_HTTP_HOST_NAME	= "127.0.0.1";

	/**
	 * port (as defined in BSI TR-03112-7 and BSI TR-03124-1)
	 */
	private static final int	EID_HTTP_PORT		= 24727;

	/**
	 * URL path for the {@link ECApiHttpHandler}
	 */
	private static final String	EID_HTTP_CTX_NAME	= "/eID-Client";

	/**
	 * local logger instance
	 */
	private final static Logger	log					= Logger.getLogger(PersoApp.class.getName());

	@Override
	public final void run() {
		try {
			final ClassLoader cl0 = this.getClass().getClassLoader();
			log.info("ClassLoader in use: " + cl0);

			/*
			 * replace splash-screen with logo from inner JAR
			 */
			final SplashScreen ss = SplashScreen.getSplashScreen();
			final URL logoURL = cl0.getResource("resources/logo.png");

			if (ss != null && logoURL != null) {
				ss.setImageURL(logoURL);
			}

			// use system proxies by default if not set already
			if (System.getProperty("java.net.useSystemProxies") == null) {
				System.setProperty("java.net.useSystemProxies", "true");
			}

			// initialize MainView and set an authenticator dialog for proxies
			final IMainView mainView = MainView.getInstance();
			Authenticator.setDefault(new ProxyAuthenticator(mainView));

			// fix PCSC issues on weird platforms
			final String PCSC_PROP = "sun.security.smartcardio.library";
			String pcscDriver = null;

			/*
			 * "Linux" => any linux distribution "os.arch" = x86_64 => 64-Bit
			 * 
			 * if ("64".equals(System.getProperty("sun.arch.data.model")))
			 * 
			 * => check for /usr/lib64/ if not found then /usr/lib/ LIB1 =
			 * "/usr/$LIBISA/libpcsclite.so"; LIB2 =
			 * "/usr/local/$LIBISA/libpcsclite.so";
			 * 
			 * "ubuntu": /lib/x86_64-linux-gnu/libpcsclite.so.1
			 */
			// "Mac OS X" => Apple JDK
			// "Darwin" => Open/SUN JDK

			final String osName = System.getProperty("os.name").toLowerCase();
			if (osName.indexOf("darwin") >= 0) {
				pcscDriver = "/System/Library/Frameworks/PCSC.framework/Versions/Current/PCSC";

			} else if (osName.indexOf("linux") >= 0 && "amd64".equals(System.getProperty("os.arch"))) {
				final String debianX64PCSC = "/usr/lib/x86_64-linux-gnu/libpcsclite.so.1";
				final String defaultX64PCSC = "/usr/lib64/libpcsclite.so.1";

				if (new File(debianX64PCSC).isFile()) {
					pcscDriver = debianX64PCSC;
				} else if (new File(defaultX64PCSC).isFile()) {
					pcscDriver = defaultX64PCSC;
				}
			} else if (osName.indexOf("bsd") >= 0) {
				final String defaultBSDPCSC = "/usr/local/lib/libpcsclite.so";
				if (new File(defaultBSDPCSC).isFile()) {
					pcscDriver = defaultBSDPCSC;
				}
			}

			if (pcscDriver != null && System.getProperty(PCSC_PROP, "").isEmpty()) {
				System.setProperty(PCSC_PROP, pcscDriver);
			}

			final PropertyResolver.Bundle textBundle = PropertyResolver.getBundle("text");

			try {
				Class.forName("javax.smartcardio.TerminalFactory");
			} catch (final Exception e) {
				Logging.getLogger().log(Level.INFO, "No smartcard support available. Stopped.");
				mainView.showError(textBundle.get("error"), textBundle.get("AEC_error"));
				return;
			}

			try {
				final ECApiHttpHandler ecApiHttp = new ECApiHttpHandler();

				final HttpServer server = HttpServer.create(new InetSocketAddress(EID_HTTP_HOST_NAME, EID_HTTP_PORT),
						10);
				server.start();
				server.createContext("/").setHandler(ecApiHttp);
				server.createContext(EID_HTTP_CTX_NAME).setHandler(ecApiHttp);
			} catch (final Exception e) {
				e.printStackTrace();
				mainView.showMessage(textBundle.get("Main_already_started"), IMainView.ERROR);
				Thread.sleep(2000);
				mainView.shutdown();
				return;
			}

			try {
				final ICardHandler eCardHandler = new CardHandler(mainView);

				// view and cardHandler for offline events, pin change, etc.
				mainView.setEventLister(new MainViewEventListener(eCardHandler, mainView));

				final WSContainer wsCtnr = new WSContainer();
				// ManagementService, only for InitializeFramework handling
				wsCtnr.addService(new ManagementService());
				// external SAL
				wsCtnr.addService(new SALService());
				// external IFD
				wsCtnr.addService(new IFDService());
				// set up services
				wsCtnr.init(null);

				// view and cardHandler for eCardSession, i.e. online authentication
				ECardWorker.init(mainView, wsCtnr, eCardHandler);
			} catch (final Exception e) {
				e.printStackTrace();
			}

			mainView.showMessage(textBundle.get("Main_ready"), IMainView.SUCCESS);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            command line arguments, unused for now
	 */
	public static final void main(final String[] args) {
		new PersoApp().run();
	}
}
