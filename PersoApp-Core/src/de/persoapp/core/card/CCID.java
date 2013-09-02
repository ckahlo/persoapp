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
package de.persoapp.core.card;

/**
 * 
 * @author ckahlo
 */
import javax.smartcardio.CardException;

public interface CCID {
	static final String[]		FEATURES					= new String[] { "NO_FEATURE", "FEATURE_VERIFY_PIN_START",
			"FEATURE_VERIFY_PIN_FINISH", "FEATURE_MODIFY_PIN_START", "FEATURE_MODIFY_PIN_FINISH",
			"FEATURE_GET_KEY_PRESSED", "FEATURE_VERIFY_PIN_DIRECT", "FEATURE_MODIFY_PIN_DIRECT",
			"FEATURE_MCT_READER_DIRECT", "FEATURE_MCT_UNIVERSAL", "FEATURE_IFD_PIN_PROPERTIES", "FEATURE_ABORT",
			"FEATURE_SET_SPE_MESSAGE", "FEATURE_VERIFY_PIN_DIRECT_APP_ID", "FEATURE_MODIFY_PIN_DIRECT_APP_ID",
			"FEATURE_WRITE_DISPLAY", "FEATURE_GET_KEY", "FEATURE_IFD_DISPLAY_PROPERTIES" };

	public static final byte	FEATURE_VERIFY_PIN_START	= 0x01;
	public static final byte	FEATURE_VERIFY_PIN_FINISH	= 0x02;
	public static final byte	FEATURE_MODIFY_PIN_START	= 0x03;
	public static final byte	FEATURE_MODIFY_PIN_FINISH	= 0x04;
	public static final byte	FEATURE_GET_KEY_PRESSED		= 0x05;
	public static final byte	FEATURE_VERIFY_PIN_DIRECT	= 0x06;
	public static final byte	FEATURE_MODIFY_PIN_DIRECT	= 0x07;
	public static final byte	FEATURE_MCT_READER_DIRECT	= 0x08;
	public static final byte	FEATURE_MCT_UNIVERSAL		= 0x09;
	public static final byte	FEATURE_IFD_PIN_PROPERTIES	= 0x0A;
	public static final byte	FEATURE_EXECUTE_PACE		= 0x20;

	public String getName();

	// Card connect() throws CardException;

	public boolean hasFeature(final byte feature);

	public byte[] verifyPinDirect(final byte[] PIN_VERIFY) throws CardException;

	public byte[] modifyPinDirect(final byte[] PIN_MODIFY) throws CardException;

	// executePACE()
	public byte[] transmitControlCommand(final byte feature, final byte[] ctrlCommand);
}
