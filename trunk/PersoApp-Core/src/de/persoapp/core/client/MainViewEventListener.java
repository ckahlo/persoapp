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
package de.persoapp.core.client;

import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.card.TransportProvider;
import de.persoapp.core.client.IMainView.ChangePINDialogResult;

public final class MainViewEventListener implements IMainView.EventListener {
	final ICardHandler	eCardHandler;
	final IMainView		mainView;

	public MainViewEventListener(final ICardHandler eCardHandler, final IMainView mainView) {
		this.eCardHandler = eCardHandler;
		this.mainView = mainView;
	}

	private final TransportProvider getCard(final boolean requireCard) {
		TransportProvider tp = null;
		do {
			tp = eCardHandler.getECard();
			if (requireCard && tp == null) {
				if (!mainView.showQuestion(PropertyResolver.getBundle("text").get("error"),
						PropertyResolver.getBundle("text_core").get("eCardService_device_and_card"))) {
					break;
				}
			}
		} while (requireCard && tp == null);
		return tp;
	}

	@Override
	public final Object handleEvent(final int event, final Object[] optionalEventData) {
		TransportProvider tp = getCard(false);
		Object result = null;
		int lastSW = -1;

		switch (event) {
		// events with optional card presence

			case MainViewEventListener.EVENT_TEST_CARD_STATE:
				result = tp == null ? null : eCardHandler.hasPACE(tp);
				break;

			case MainViewEventListener.EVENT_TEST_PIN_STATE:
				if (tp != null) {
					switch (tp.lastSW()) {
						case 0x9000:
							result = 3;
							break;
						case 0x63C2:
							result = 2;
							break;
						case 0x63C1:
							result = 1;
							break;
						case 0x63C0:
							result = 0;
							break;
						case 0x6283:
							result = 255;
							break;
						default:
							result = -1;
							break;
					}
				} else {
					result = -1;
				}

				break;

			default:
				//events with required card presence;

				if (tp == null) {
					tp = getCard(true);
				}

				if (tp != null) {
					ChangePINDialogResult dr;

					switch (event) {
						case MainViewEventListener.EVENT_CHANGE_PIN_EID:
							//int authStatus = tp.lastSW();
							dr = (ChangePINDialogResult) optionalEventData[0];
							lastSW = eCardHandler.doPINChange(tp, (byte) 0x03, dr.getPINOld(), (byte) 0x03,
									dr.getPINNew());
							break;

						case MainViewEventListener.EVENT_ACTIVATE_PIN_EID:
							//int authStatus = tp.lastSW();
							dr = (ChangePINDialogResult) optionalEventData[0];
							lastSW = eCardHandler.doPINChange(tp, (byte) 0x03, dr.getPINOld(), (byte) 0x03,
									dr.getPINNew());
							break;

						case MainViewEventListener.EVENT_UNLOCK_PIN_EID:
							//int authStatus = tp.lastSW();
							dr = (ChangePINDialogResult) optionalEventData[0];
							lastSW = eCardHandler.doPINUnblock(tp, (byte) 0x04, dr.getPINOld(), (byte) 0x03);
							break;

						case MainViewEventListener.EVENT_INIT_PIN_ESIGN:
							lastSW = eCardHandler.doESignInit(tp);
							break;

						case MainViewEventListener.EVENT_CHANGE_PIN_ESIGN:
							lastSW = eCardHandler.doESignChange(tp);
							break;

						case MainViewEventListener.EVENT_UNBLOCK_PIN_ESIGN:
							lastSW = eCardHandler.doESignUnblock(tp);
							break;

						case MainViewEventListener.EVENT_TERMINATE_ESIGN:
							lastSW = eCardHandler.doESignTerminate(tp);
							break;

						default:
							break;
					}

					//				tp.close();				
				}
				break;
		}

		if (result == null && lastSW != -1) {
			result = 0x9000 == lastSW ? true : false;
		}

		if (tp != null) {
			//			tp.close();
		}

		return result;
	}
}
