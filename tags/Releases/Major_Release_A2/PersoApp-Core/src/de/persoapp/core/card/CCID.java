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
 * The CCID describes basic functionality of an terminal to do feature
 * verifying, do pin management and to transmit control commands to an ICC.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public interface CCID {
	
	/**
	 * The list of relevant features of an CCID.
	 */
	static final String[]		FEATURES					= new String[] { "NO_FEATURE", "FEATURE_VERIFY_PIN_START",
			"FEATURE_VERIFY_PIN_FINISH", "FEATURE_MODIFY_PIN_START", "FEATURE_MODIFY_PIN_FINISH",
			"FEATURE_GET_KEY_PRESSED", "FEATURE_VERIFY_PIN_DIRECT", "FEATURE_MODIFY_PIN_DIRECT",
			"FEATURE_MCT_READER_DIRECT", "FEATURE_MCT_UNIVERSAL", "FEATURE_IFD_PIN_PROPERTIES", "FEATURE_ABORT",
			"FEATURE_SET_SPE_MESSAGE", "FEATURE_VERIFY_PIN_DIRECT_APP_ID", "FEATURE_MODIFY_PIN_DIRECT_APP_ID",
			"FEATURE_WRITE_DISPLAY", "FEATURE_GET_KEY", "FEATURE_IFD_DISPLAY_PROPERTIES" };

	/**
	 * This feature starts an indirect verify PIN procedure in the IFD.
	 */
	public static final byte	FEATURE_VERIFY_PIN_START	= 0x01;
	
	/**
	 * This feature ends an indirect verify PIN procedure in the IFD.
	 */
	public static final byte	FEATURE_VERIFY_PIN_FINISH	= 0x02;
	
	/**
	 * This feature starts an indirect modify PIN procedure in the IFD.
	 */
	public static final byte	FEATURE_MODIFY_PIN_START	= 0x03;
	
	/**
	 * This feature ends an indirect modify PIN procedure in the IFD.
	 */
	public static final byte	FEATURE_MODIFY_PIN_FINISH	= 0x04;
	
	/**
	 * This feature transmits a pressed key during a indirect PIN procedure in
	 * the IFD.
	 */
	public static final byte	FEATURE_GET_KEY_PRESSED		= 0x05;
	
	/**
	 * This feature performs a complete (direct) verify PIN procedure in the
	 * IFD.
	 */
	public static final byte	FEATURE_VERIFY_PIN_DIRECT	= 0x06;
	
	/**
	 * This feature performs a complete (direct) modify PIN procedure in the
	 * IFD.
	 */
	public static final byte	FEATURE_MODIFY_PIN_DIRECT	= 0x07;
	
	/**
	 * This feature can be used to transmit a command to the IFD.
	 */
	public static final byte	FEATURE_MCT_READER_DIRECT	= 0x08;
	
	/**
	 * This feature can be used to transmit a command to the IFD or the ICC.
	 */
	public static final byte	FEATURE_MCT_UNIVERSAL		= 0x09;
	
	/**
	 * This feature can be used to retrieve the properties of the IFD regarding
	 * PIN handling.
	 */
	public static final byte	FEATURE_IFD_PIN_PROPERTIES	= 0x0A;
	
	/**
	 * This feature is used to command the PACE functionality within the reader.
	 */
	public static final byte	FEATURE_EXECUTE_PACE		= 0x20;

	/**
	 * Returns a string representation of the inserted card.
	 * 
	 * @return The <em>name</em> of the card.
	 */
	public String getName();

	/**
	 * Checks if the requested feature of a <em>CCID</em> is available.
	 * 
	 * @param feature
	 *            - The requested feature.
	 * @return <strong>True</strong>, if the requested feature is
	 *         available. Otherwise <strong>false</strong>.
	 */
	public boolean hasFeature(final byte feature);

	/**
	 * Does a complete verify pin procedure at the IFD.
	 * 
	 * @param PIN_VERIFY
	 *            - The corresponding <em>APDU</em>-command.
	 * @return The <em>APDU</em>-Response.
	 * @throws CardException
	 *             If no card is present.
	 */
	public byte[] verifyPinDirect(final byte[] PIN_VERIFY) throws CardException;

	/**
	 * Does a complete modify pin procedure at the IFD.
	 * 
	 * @param PIN_MODIFY
	 *            - The  corresponding<em>APDU</em>-command.
	 * @return The <em>APDU</em>-Response.
	 * @throws CardException
	 *             If no card is present.
	 */
	public byte[] modifyPinDirect(final byte[] PIN_MODIFY) throws CardException;

	/**
	 * Transmits the <em>control command</em> to the inserted card.
	 * 
	 * @param feature
	 *            - The feature, to which the control command is related.
	 * 
	 * @param ctrlCommand
	 *            - The control command, to transmit.
	 * @return The examined response.
	 * 
	 * @throw CardException If the requested feature is not provided by the
	 *        card.
	 */
	public byte[] transmitControlCommand(final byte feature, final byte[] ctrlCommand);
}
