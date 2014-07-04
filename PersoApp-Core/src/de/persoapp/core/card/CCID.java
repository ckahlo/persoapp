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
package de.persoapp.core.card;

import javax.smartcardio.CardException;

/**
 * <p>
 * The <tt>CCID</tt> interface specifies methods and functions for use with a
 * Chip Card Interface Devices. This is a card terminal in general.
 * </p>
 * <p>
 * <code>public interface CCID</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public interface CCID {
	
	/**
	 * The feature list.
	 */
	static final String[]		FEATURES					= new String[] { "NO_FEATURE", "FEATURE_VERIFY_PIN_START",
			"FEATURE_VERIFY_PIN_FINISH", "FEATURE_MODIFY_PIN_START", "FEATURE_MODIFY_PIN_FINISH",
			"FEATURE_GET_KEY_PRESSED", "FEATURE_VERIFY_PIN_DIRECT", "FEATURE_MODIFY_PIN_DIRECT",
			"FEATURE_MCT_READER_DIRECT", "FEATURE_MCT_UNIVERSAL", "FEATURE_IFD_PIN_PROPERTIES", "FEATURE_ABORT",
			"FEATURE_SET_SPE_MESSAGE", "FEATURE_VERIFY_PIN_DIRECT_APP_ID", "FEATURE_MODIFY_PIN_DIRECT_APP_ID",
			"FEATURE_WRITE_DISPLAY", "FEATURE_GET_KEY", "FEATURE_IFD_DISPLAY_PROPERTIES" };

	/**
	 * The feature to start the verify pin process.
	 */
	public static final byte	FEATURE_VERIFY_PIN_START	= 0x01;
	
	/**
	 * The feature to finish the verify pin process.
	 */
	public static final byte	FEATURE_VERIFY_PIN_FINISH	= 0x02;
	
	/**
	 * The feature to start the modify pin process.
	 */
	public static final byte	FEATURE_MODIFY_PIN_START	= 0x03;
	
	/**
	 * The feature to finish the modify pin process.
	 */
	public static final byte	FEATURE_MODIFY_PIN_FINISH	= 0x04;
	
	/**
	 * The feature to retrieve the pressed key.
	 */
	public static final byte	FEATURE_GET_KEY_PRESSED		= 0x05;
	
	/**
	 * The feature to verify the pin direct.
	 */
	public static final byte	FEATURE_VERIFY_PIN_DIRECT	= 0x06;
	
	/**
	 * The feature to modify the pin direct.
	 */
	public static final byte	FEATURE_MODIFY_PIN_DIRECT	= 0x07;
	
	/**
	 * The feature to use the reader function of a <em>Multislot Card Terminal</em>.
	 */
	public static final byte	FEATURE_MCT_READER_DIRECT	= 0x08;
	
	/**
	 * The feature to use all functions of a <em>Multislot Card Terminal</em>.
	 */
	public static final byte	FEATURE_MCT_UNIVERSAL		= 0x09;
	
	/**
	 * The feature to handle the pin properties.
	 */
	public static final byte	FEATURE_IFD_PIN_PROPERTIES	= 0x0A;
	
	/**
	 * The feature to execute the <em>PACE</em>-protocol.
	 */
	public static final byte	FEATURE_EXECUTE_PACE		= 0x20;

	/**
	 * Returns the name of the <tt>integrated chip card</tt>.
	 * 
	 * @return Returns the name of the <tt>integrated chip card</tt>.
	 */
	public String getName();

	// Card connect() throws CardException;
	/**
	 * Returns <strong>true</strong>. if the requested feature is available.
	 * Otherwise <strong>false</strong>.
	 * 
	 * @param feature
	 *            - The requested feature.
	 * @return Returns <strong>true</strong>. if the requested feature is
	 *         available. Otherwise <strong>false</strong>.
	 */
	public boolean hasFeature(final byte feature);

	/**
	 * Verify the pin direct at the card terminal.
	 * 
	 * @param PIN_VERIFY
	 *            - The <em>APDU</em>-command.
	 * @return Returns the <em>APDU</em>-Response.
	 * @throws CardException
	 *             If no card is present.
	 */
	public byte[] verifyPinDirect(final byte[] PIN_VERIFY) throws CardException;

	/**
	 * Modifies the pin direct at the card terminal.
	 * 
	 * @param PIN_MODIFY
	 *            - The <em>APDU</em>-command.
	 * @return Returns the <em>APDU</em>-Response.
	 * @throws CardException
	 *             If no card is present.
	 */
	public byte[] modifyPinDirect(final byte[] PIN_MODIFY) throws CardException;

	// executePACE()
	/**
	 * Transmits the <em>control command</em>, according to the given
	 * <tt>feature</tt>, and retrieves the response.
	 * 
	 * @param feature
	 *            - The feature, which uses the control command.
	 * 
	 * @param ctrlCommand
	 *            - The control command to transmit.
	 * @return Returns the received response.
	 * 
	 * @throw CardException Thrown if the requested feature is not provided by
	 *        the card.
	 */
	public byte[] transmitControlCommand(final byte feature, final byte[] ctrlCommand);
}
