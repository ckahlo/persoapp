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
package de.persoapp.core.client;

/**
 * <p>
 * The IMainView provides an unified, isolated communication channel to the
 * GUI. Additionally, the IMainView provides a event listener, which is
 * registered to listen and react on events of the user input and forward
 * them to the core.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public interface IMainView {

	/**
	 * Is set, if an error occurs.
	 */
	public static final int	ERROR			= 0;
	
	/**
	 * Is set, if an info is given.
	 */
	public static final int	INFO			= 1;
	
	/**
	 * Is set, if the MainView is reloaded.
	 */
	public static final int	RELOAD			= 2;
	
	/**
	 * Is set, if an operation is completed successful.
	 */
	public static final int	SUCCESS			= 3;
	
	/**
	 * Is set, if an warning occurs.
	 */
	public static final int	WARNING			= 4;
	
	/**
	 * Is set, if an question is handed to the user.
	 */
	public static final int	QUESTION		= 5;

	/**
	 * Magic constant for showing the PIN Panel and the CHAT.
	 */
	public static final int	MODE_PIN_CHAT	= 0;

	/**
	 * Request only CHAT
	 */
	public static final int	MODE_CHATONLY	= 1;

	/* i.e. for RID-only authentication = login */
	/**
	 * Request only PIN. 
	 */
	public static final int	MODE_PINONLY	= 2;

	/**
	 * Request only "OK" or "CANCEL".
	 */
	public static final int	MODE_NONE		= 3;

	/**
	 * The MainDialogResult contains the selected personal data and the actual
	 * inserted pin.
	 * 
	 * @author Christian Kahlo, Ralf Wondratschek
	 */
	public class MainDialogResult {
		
		/**
		 * The template for selecting personal data which is going to be read
		 * from the identity card.
		 */
		private final long			chat;
		
		/**
		 * The inserted actual pin.
		 */
		private final SecureHolder	pin;
		
		/**
		 * Set to <strong>true</strong>, if the user confirms, that he wants to
		 * proceed.
		 */
		private boolean				approved	= false;

		/**
		 * Creates a new instance of the {@link MainDialogResult}. The marked
		 * personal data and the pin are set through the handed parameters.
		 * 
		 * @param chat
		 *            - The personal data, which is read and send.
		 * @param pin
		 *            - The inserted actual pin.
		 * @param approved
		 *            - Set to <strong>true</strong> if the user wants to
		 *            proceed and let the data read from the used eID-Card.
		 */
		public MainDialogResult(final long chat, final byte[] pin, final boolean approved) {
			this.chat = chat;
			this.pin = new SecureHolder(pin);
			this.approved = approved;
		}

		/**
		 * Returns the marked personal data.
		 * 
		 * @return Returns the marked personal data.
		 */
		public long getCHAT() {
			return this.chat;
		}

		/**
		 * Returns the actual pin in a {@link SecureHolder}.
		 * 
		 * @return Returns the actual pin.
		 */
		public SecureHolder getPIN() {
			return this.pin;
		}
		
		/**
		 * Returns <strong>true</strong>, if the data of the
		 * {@link MainDialogResult} is approved. Otherwise
		 * <strong>false</strong>.
		 * 
		 * @return Returns <strong>true</strong>, if the data of the
		 *         {@link MainDialogResult} is approved. Otherwise
		 *         <strong>false</strong>.
		 */
		public boolean isApproved() {
			return this.approved;
		}
	}

	/**
	 * The ChangePinDialogResult contains the old pin and the new pin.
	 * 
	 * @author Christian Kahlo, Ralf Wondratschek
	 */
	public class ChangePINDialogResult {
		
		/**
		 * The old pin.
		 */
		private final SecureHolder	pinOld;
		
		/**
		 * The new pin.
		 */
		private final SecureHolder	pinNew;
		
		/**
		 * Set to <strong>true</strong>, if the user wants to proceed with the
		 * operation by clicking the specific button.
		 */
		private boolean				approved	= false;

		/**
		 * Creates a new instance of the {@link ChangePINDialogResult}. The old
		 * pin and the new pin are set through the handed arguments. The
		 * attribute approved shows the result of the attempt to exchange the
		 * old pin through the new pin.
		 * 
		 * @param pinOld
		 *            - The old pin, which is going to be replaced.
		 * @param pinNew
		 *            - The new pin.
		 * @param approved
		 *            - Set to <strong>true</strong>, if the attempt to change
		 *            the pin was successful, otherwise <strong>false</strong>.
		 */
		public ChangePINDialogResult(final byte[] pinOld, final byte[] pinNew, final boolean approved) {
			this.pinOld = new SecureHolder(pinOld);
			this.pinNew = new SecureHolder(pinNew);
			this.approved = approved;
		}

		/**
		 * Returns the new pin in a {@link SecureHolder}.
		 * 
		 * @return Returns the new pin.
		 */
		public SecureHolder getPINNew() {
			return this.pinNew;
		}

		/**
		 * Returns the old pin in a {@link SecureHolder}.
		 * 
		 * @return Returns the old pin.
		 */
		public SecureHolder getPINOld() {
			return this.pinOld;
		}

		/**
		 * Returns <strong>true</strong>, if the new pin is approved. Returns
		 * <strong>false</strong>, if it isn't.
		 * 
		 * @return Returns the result of the confirming operation.
		 */
		public boolean isApproved() {
			return this.approved;
		}
	}
	
	/**
	 * The EventListener listens on specific events to ensure the
	 * functionalities. The EventListener listens on the following
	 * events.
	 * <p>
	 * <code>
	 * 	public static final int	EVENT_TEST_CARD_STATE	= 1001;<br>
	 *	public static final int	EVENT_TEST_PIN_STATE	= 1002;<br>
	 *
	 *	public static final int	EVENT_ACTIVATE_PIN_EID	= 1011;<br>
	 *	public static final int	EVENT_CHANGE_PIN_EID	= 1012;<br>
	 *	public static final int	EVENT_UNLOCK_PIN_EID	= 1013;<br>
 	 *
	 *	public static final int	EVENT_INIT_PIN_ESIGN	= 1021;<br>
	 *	public static final int	EVENT_CHANGE_PIN_ESIGN	= 1022;<br>
	 *	public static final int	EVENT_UNBLOCK_PIN_ESIGN	= 1023;<br>
	 *	public static final int	EVENT_TERMINATE_ESIGN	= 1024;<br>
	 *
	 *	public static final int	EVENT_CHANGE_CAN		= 1031;<br>
 	 *
	 *	public static final int	EVENT_SHOW_ESIGN_CERTS	= 1101;<br>
	 * </code>
	 * </p>
	 * <p>
	 * <code>public interface EventListener</code>
	 * </p>
	 * @author Christian Kahlo, Ralf Wondratschek
	 */
	public interface EventListener {
		
		/**
		 * The event topic, related to the state of the test card.
		 */
		public static final int	EVENT_TEST_CARD_STATE	= 1001;
		
		/**
		 * The event topic, related to the state of the pin.
		 */
		public static final int	EVENT_TEST_PIN_STATE	= 1002;

		/**
		 * The event topic, related to the pin activation, with a electronic identity card.
		 */
		public static final int	EVENT_ACTIVATE_PIN_EID	= 1011;
		
		/**
		 * The event topic, related to the pin changing, with a electronic identity card.
		 */
		public static final int	EVENT_CHANGE_PIN_EID	= 1012;
		
		/**
		 * The event topic, related to the pin unlocking, with a electronic identity card.
		 */
		public static final int	EVENT_UNLOCK_PIN_EID	= 1013;

		/**
		 * The event topic, related to the function for pin initialization via a electronic signing mechanism.
		 */
		public static final int	EVENT_INIT_PIN_ESIGN	= 1021;
		
		/**
		 * The event topic, related to the change pin function via a electronic signing mechanism. 
		 */
		public static final int	EVENT_CHANGE_PIN_ESIGN	= 1022;
		
		/**
		 * The event topic, related to the unblock pin function via a electronic signing mechanism.
		 */
		public static final int	EVENT_UNBLOCK_PIN_ESIGN	= 1023;
		
		/**
		 * The event topic, related to the terminate function of the electronic signing mechanism.
		 */
		public static final int	EVENT_TERMINATE_ESIGN	= 1024;

		/**
		 * The event topic, related to events of the change mechanism of the CAN-Dialog. 
		 */
		public static final int	EVENT_CHANGE_CAN		= 1031;

		/**
		 * The event topic, related to events of showing the electronic certificates.
		 */
		public static final int	EVENT_SHOW_ESIGN_CERTS	= 1101;

		/**
		 * Handles the event and sets window states according to it.
		 * 
		 * @param event
		 *            - The event, which is handled.
		 * @param optionalEventData
		 *            - The optional event-data, which is handled with the
		 *            event.
		 * @return Returns the new state of the MainView
		 */
		Object handleEvent(int event, Object[] optionalEventData);
	}

	/**
	 * Sets the event listener for this GUI instance.
	 * 
	 * @param listener
	 *            - The listener, to set
	 */
	public void setEventLister(EventListener listener);

	/**
	 * Trigger the event which is handed to the function.
	 * 
	 * @param event
	 *            - The event, which is triggered.
	 * @param eventData
	 *            - The eventData, which is dispatched with the event.
	 * 
	 * @return Returns the new info state of the main view.
	 */
	public Object triggerEvent(int event, Object... eventData);

	/**
	 * Shows the main dialog with informations about the actual process and
	 * creates a asynchronous thread to handle the pin panel during the online
	 * authentication process according to the given <em>MODE</em>.
	 * 
	 * @param eacInfo
	 *            - The informations about the actual process.
	 * @param MODE
	 *            - The MODE of authentication.
	 */
	public void showMainDialog(IEAC_Info eacInfo, int MODE);

	/**
	 * Wait for a result and return it.
	 * 
	 * @return Returns the result of the user interaction in a
	 *         {@link MainDialogResult}.
	 */
	public MainDialogResult getMainDialogResult();

	/**
	 * Show the minimal CAN-Dialog.
	 * 
	 * @param msg
	 *            - The message which is displayed in the dialog.
	 * 
	 * @return Returns the result of the user interaction in a
	 *         {@link SecureHolder}.
	 */
	public SecureHolder showCANDialog(String msg);

	/**
	 * Show the change pin dialog.
	 */
	public void showChangePinDialog();

	/**
	 * Show the progress of the current authentication process.
	 * 
	 * @param message
	 *            - The message, which is shown in the progress bar.
	 * @param amount
	 *            - The amount, to which the bar is filled up.
	 * @param enabled
	 *            - Enables the progress bar, if set to <strong>true</strong>,
	 *            otherwise disables the bar.
	 */
	public void showProgress(String message, int amount, boolean enabled);

	/**
	 * Display a question, ask for OK/CANCEL.
	 * 
	 * @param title
	 *            - The title of the question.
	 * @param message
	 *            - The question, which is displayed.
	 * 
	 * @return Returns <strong>true</strong> if ok is pushed, otherwise
	 *         <strong>false</strong>.
	 */
	public boolean showQuestion(String title, String message);

	/**
	 * Display an error message in a specific dialog.
	 * 
	 * @param title
	 *            - The title of the error message.
	 * @param message
	 *            - The message, which is shown.
	 */
	public void showError(String title, String message);

	/**
	 * Show a message in a specific dialog.
	 * 
	 * @param msg
	 *            - The message, which is shown.
	 * @param type
	 *            - The type of the dialog.
	 */
	public void showMessage(String msg, int type);

	/**
	 * Close / hide currently open dialogs because a processed finished.
	 */
	public void closeDialogs();

	/**
	 * Close GUI instance and release all resources.
	 */
	public void shutdown();
}
