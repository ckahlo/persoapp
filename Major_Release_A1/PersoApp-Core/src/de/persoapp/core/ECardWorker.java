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

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.Random;
import java.util.concurrent.Exchanger;

import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.client.ECardSession;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.paos.PAOSInitiator;
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
