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

import java.util.List;

import de.persoapp.core.client.SecureHolder;

public interface ICardHandler {
	public static final String	AID_NPA		= "E80704007F00070302";
	public static final String	AID_ICAO	= "A0000002471001";
	public static final String	AID_VX4		= "D2760000930101";
	public static final String	AID_eSign	= "A000000167455349474E";

	TransportProvider getECard();

	int hasPACE(Object transport);

	int doPINUnblock(TransportProvider tp, byte verifySecret, SecureHolder verifySecretInput, byte unblockSecret);

	int doPINChange(TransportProvider tp, byte verifySecret, SecureHolder verifySecretInput, byte modifySecret,
			SecureHolder modifySecretInput);

	public int doESignInit(TransportProvider tp0);

	public int doESignChange(TransportProvider tp0);

	public int doESignUnblock(TransportProvider tp0);

	public int doESignTerminate(TransportProvider tp0);

	boolean startAuthentication(byte[] CHAT, SecureHolder secret, byte[] termDesc);

	void reset();

	byte[] getIDPICC();

	List<byte[]> getCAReferences();

	byte[] getEFCardAccess();

	boolean verifyCertificate(byte[] cvc);

	void initTA(byte[] ephemeralKey, byte[] auxData);

	byte[] getTAChallenge();

	boolean verifyTASignature(byte[] taSignature);

	byte[] getEFCardSecurity();

	byte[] execCA();

	byte[] transmit(byte[] cmd);
}
