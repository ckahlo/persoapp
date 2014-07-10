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

import java.util.List;

import de.persoapp.core.client.SecureHolder;
import de.persoapp.core.ws.IFDService;

/**
 * The <tt>ICardHandler</tt> interface describes basic functionality to handle
 * retrieval of transport providers, do PIN management, process extended access
 * control and digital signature requests as well as tunneling commands from the
 * {@link IFDService} to the ICC.
 * 
 * <p>
 * <code>public interface ICardHandler</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public interface ICardHandler {

	/**
	 * application identifier for BSI TR-03110 eID application, oid =
	 * 0.4.0.127.0.7.3.2
	 */
	public static final String	AID_NPA		= "E80704007F00070302";

	/**
	 * application identifier for ICAO 9303 MRTD application
	 */
	public static final String	AID_ICAO	= "A0000002471001";

	/**
	 * application identifier for CEN 14890 DF.eSign
	 */
	public static final String	AID_eSign	= "A000000167455349474E";

	/**
	 * <p>
	 * Search through all connected terminals and returns a transport provider
	 * if a suitable ICC was found.
	 * </p>
	 * 
	 * @return {@link TransportProvider} if ECard found, <em>null<em> if
	 * no card is available.
	 */
	TransportProvider getECard();

	/**
	 * Checks for terminal support of <em>PACE</em> feature.
	 * 
	 * @param transport
	 *            - {@link TransportProvider} to be used
	 * @return supported PACE features or <em>0</em> if not supported
	 */
	int hasPACE(Object transport);

	/**
	 * Handles PIN unblocking with PACE and reset retry counter.
	 * 
	 * @param tp
	 *            - {@link TransportProvider} to be used
	 * @param verifySecret
	 *            - password reference (i.e. PUK)
	 * @param verifySecretInput
	 *            - unblocking password (PUK) or null if terminal supports PACE
	 *            (pinpad and display available)
	 * @param unblockSecret
	 *            - password reference of blocked password
	 * @return status word of unblock sequence
	 */
	int doPINUnblock(TransportProvider tp, byte verifySecret, SecureHolder verifySecretInput, byte unblockSecret);

	/**
	 * Handles PIN change with PACE and PIN MODIFY.
	 * 
	 * @param tp
	 *            - {@link TransportProvider} to be used
	 * @param verifySecret
	 *            - password reference of secret to verify
	 * @param verifySecretInput
	 *            - secret or null if terminal supports PACE
	 * @param modifySecret
	 *            - password reference of secret to modify
	 * @param modifySecretInput
	 *            - new secret or null if terminal supports PACE
	 * @return last status word of pin modify sequence
	 */
	int doPINChange(TransportProvider tp, byte verifySecret, SecureHolder verifySecretInput, byte modifySecret,
			SecureHolder modifySecretInput);

	/**
	 * Select signing application (only with signature terminal), requires CAN
	 * input on terminal pinpad
	 * 
	 * @param tp0
	 *            - {@link TransportProvider} to be used
	 * @return last status word
	 */
	public int doESignInit(TransportProvider tp0);

	/**
	 * Handle pin change of signature PIN.
	 * 
	 * @param tp0
	 *            - {@link TransportProvider} to be used
	 * @return last status word
	 */
	public int doESignChange(TransportProvider tp0);

	/**
	 * Handle unblock of signature PIN.
	 * 
	 * @param tp0
	 *            - {@link TransportProvider} to be used
	 * @return last status word
	 */
	public int doESignUnblock(TransportProvider tp0);

	/**
	 * Deletes signature key and terminates signature PIN.
	 * 
	 * @param tp0
	 *            - {@link TransportProvider} to be used
	 * @return last status word
	 */
	public int doESignTerminate(TransportProvider tp0);

	/**
	 * Initiate authentication process with eID card.
	 * 
	 * @param CHAT
	 *            - <em>Card Holder Authorization Template</em> describing which
	 *            data groups are to be read
	 * @param secret
	 *            - eID PIN or null if PACE is supported
	 * @param termDesc
	 *            - terminal description of authorization certificate to be
	 *            displayed on terminals (card readers) with display
	 * 
	 * @return <em>true</em> is successful, <em>false</em> otherwise
	 */
	boolean startAuthentication(byte[] CHAT, SecureHolder secret, byte[] termDesc);

	/**
	 * Close transport providers and set card handler initialization state to
	 * false.
	 */
	void reset();

	/**
	 * Retrieve IDPICC-value calculated while processing PACE (compressed base
	 * point of second ECDHE)
	 * 
	 * @return IDPICC
	 */
	byte[] getIDPICC();

	/**
	 * Retrieve Certificate Authority References of the PKIs known by the ECard.
	 * Usually the current root and its predecessor.
	 * 
	 * @return list of byte array containing CA reference of the card
	 */
	List<byte[]> getCAReferences();

	/**
	 * Returns the raw content of <tt>EF.CardAccess</tt> file containing:
	 * <ul>
	 * <li>PACEInfo</li>
	 * <li>ChipAuthenticationInfo</li>
	 * <li>ChipAuthenticationDomainParameterInfo</li>
	 * <li>PrivilegedTerminalInfo</li>
	 * <li>TerminalAuthenticationInfo</li>
	 * <li>CardInfoLocator</li>
	 * </ul>
	 * 
	 * @return raw <tt>EF.CardAccess</tt> content
	 * 
	 */
	byte[] getEFCardAccess();

	/**
	 * Verify a single certificate from terminal certificate chain. This
	 * function is intended to be called multiple times beginning with the root
	 * (link) certificate, then document verifier CA and finally the terminal
	 * certificate itself.
	 * 
	 * @param cvc
	 *            - card verifiable certificate from certificate chain
	 * 
	 * @return <em>true</em> if successfully verified, <em>false</em> otherwise
	 */
	boolean verifyCertificate(byte[] cvc);

	/**
	 * Initializes the terminal authentication for extended access control
	 * protocol.
	 * 
	 * @param ephemeralKey
	 *            - ephemeral terminal key
	 * @param auxData
	 *            - auxiliary data to be verified (document expiration date,
	 *            community id, age)
	 */
	void initTA(byte[] ephemeralKey, byte[] auxData);

	/**
	 * Retrieve card challenge for terminal authentication.
	 * 
	 * @return card challenge
	 */
	byte[] getTAChallenge();

	/**
	 * Verify terminal signature for terminal authentication.
	 * 
	 * @param taSignature
	 *            - signature from terminal (eID server)
	 * @return <em>true</em> if valid, false otherwise
	 */
	boolean verifyTASignature(byte[] taSignature);

	/**
	 * <p>
	 * Returns the raw content of <tt>EF.CardAccess</tt> file containing:
	 * <ul>
	 * <li>PACEInfo</li>
	 * <li>ChipAuthenticationInfo</li>
	 * <li>ChipAuthenticationDomainParameterInfo</li>
	 * <li>ChipAuthenticationPublicKeyInfo</li>
	 * <li>TerminalAuthenticationInfo</li>
	 * <li>CardInfoLocator</li>
	 * <li>RestrictedIdentificationInfo</li>
	 * <li>RestrictedIdentificationDomainParameterInfo</li>
	 * </ul>
	 * </p>
	 * 
	 * @return raw content of <tt>EF.CardSecurity</tt> file
	 */
	byte[] getEFCardSecurity();

	/**
	 * Execute chip authentication.
	 * 
	 * @return result of GENERAL AUTHENTICATE
	 */
	byte[] execCA();

	/**
	 * Transparently transmit <em>APDU</em> from IFDService to card.
	 * 
	 * @param cmd
	 *            - <em>APDU</em>
	 * 
	 * @return response APDU from card
	 */
	byte[] transmit(byte[] cmd);
}
