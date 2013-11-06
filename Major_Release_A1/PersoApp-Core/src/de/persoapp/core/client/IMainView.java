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

public interface IMainView {

	public static final int	ERROR			= 0;
	public static final int	INFO			= 1;
	public static final int	RELOAD			= 2;
	public static final int	SUCCESS			= 3;
	public static final int	WARNING			= 4;
	public static final int	QUESTION		= 5;

	/* request and submit PIN and CHAT */
	public static final int	MODE_PIN_CHAT	= 0;

	/* request only CHAT, PIN is entered on reader keyboard */
	public static final int	MODE_CHATONLY	= 1;

	/* i.e. for RID-only authentication = login */
	/* request only PIN, CHAT is fixed */
	public static final int	MODE_PINONLY	= 2;

	/* request only "OK" or "CANCEL" */
	public static final int	MODE_NONE		= 3;

	public class MainDialogResult {
		private final long			chat;
		private final SecureHolder	pin;
		private boolean				approved	= false;

		public MainDialogResult(final long chat, final byte[] pin, final boolean approved) {
			this.chat = chat;
			this.pin = new SecureHolder(pin);
			this.approved = approved;
		}

		public long getCHAT() {
			return this.chat;
		}

		public SecureHolder getPIN() {
			return this.pin;
		}

		public boolean isApproved() {
			return this.approved;
		}
	}

	public class ChangePINDialogResult {
		private final SecureHolder	pinOld;
		private final SecureHolder	pinNew;
		private boolean				approved	= false;

		public ChangePINDialogResult(final byte[] pinOld, final byte[] pinNew, final boolean approved) {
			this.pinOld = new SecureHolder(pinOld);
			this.pinNew = new SecureHolder(pinNew);
			this.approved = approved;
		}

		public SecureHolder getPINNew() {
			return this.pinNew;
		}

		public SecureHolder getPINOld() {
			return this.pinOld;
		}

		public boolean isApproved() {
			return this.approved;
		}
	}

	public interface EventListener {
		public static final int	EVENT_TEST_CARD_STATE	= 1001;
		public static final int	EVENT_TEST_PIN_STATE	= 1002;

		public static final int	EVENT_ACTIVATE_PIN_EID	= 1011;
		public static final int	EVENT_CHANGE_PIN_EID	= 1012;
		public static final int	EVENT_UNLOCK_PIN_EID	= 1013;

		public static final int	EVENT_INIT_PIN_ESIGN	= 1021;
		public static final int	EVENT_CHANGE_PIN_ESIGN	= 1022;
		public static final int	EVENT_UNBLOCK_PIN_ESIGN	= 1023;
		public static final int	EVENT_TERMINATE_ESIGN	= 1024;

		public static final int	EVENT_CHANGE_CAN		= 1031;

		public static final int	EVENT_SHOW_ESIGN_CERTS	= 1101;

		Object handleEvent(int event, Object[] optionalEventData);
	}

	/*
	 * set event listener for this GUI instance
	 */
	public void setEventLister(EventListener listener);

	/*
	 * trigger a certain event
	 */
	public Object triggerEvent(int event, Object... eventData);

	/*
	 * display main screen for online authentication
	 */
	public void showMainDialog(IEAC_Info eacInfo, int MODE);

	/*
	 * wait for result and return it
	 */
	public MainDialogResult getMainDialogResult();

	/*
	 * show minimal CAN-Dialog
	 */
	public SecureHolder showCANDialog(String msg);

	/*
	 * show change PIN Dialog
	 */
	public void showChangePinDialog();

	/*
	 * show progress of currenz authentication process
	 */
	public void showProgress(String message, int amount, boolean enabled);

	/*
	 * display a question, ask for OK/CANCEL
	 */
	public boolean showQuestion(String title, String message);

	/*
	 * display an error message in a specific dialog
	 */
	public void showError(String title, String message);

	/*
	 * show message
	 */
	public void showMessage(String msg, int type);

	/*
	 * close / hide currently open dialogs because a processed finished
	 */
	public void closeDialogs();

	/*
	 * close GUI instance and release all resources
	 */
	public void shutdown();
}
