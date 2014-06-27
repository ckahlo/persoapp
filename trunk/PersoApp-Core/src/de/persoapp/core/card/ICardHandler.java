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

/**
 * The <tt>ICardHandler</tt> interface declares methods to enable the different
 * card- and card communication-functions and behaves as a abstract interface to
 * the functions of a smart card.
 * <p>
 * <code>public interface ICardHandler</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public interface ICardHandler {
	
	/**
	 * The application identifier for the main application of the <tt>NPA</tt>.
	 */
	public static final String	AID_NPA		= "E80704007F00070302";
	
	/**
	 * The application identifier for the main <tt>ICAO</tt>-application.
	 */
	public static final String	AID_ICAO	= "A0000002471001";
	
	/**
	 * The application identifier for the main <tt>VX4</tt>-application.
	 */
	public static final String	AID_VX4		= "D2760000930101";
	
	/**
	 * The application identifier for the electronic signing application.
	 */
	public static final String	AID_eSign	= "A000000167455349474E";

	/**
	 * Returns the {@link TransportProvider} of the current inserted
	 * <tt>ECard</tt>. Additionally informations of the card terminal
	 * are displayed in the console during the function.
	 * 
	 * @return Returns the {@link TransportProvider} of the current inserted
	 *         <tt>ECard</tt>.
	 */
	TransportProvider getECard();

	/**
	 * Returns <em>zero</em> if the connected terminal doesn't support
	 * <tt>PACE</tt>.
	 * 
	 * @param transport
	 *            - The object to transport according to the <tt>PACE</tt>
	 *            -Protocol.
	 * @return Returns <em>zero</em> if the connected terminal doesn't support
	 *         <tt>PACE</tt>. 
	 */
	int hasPACE(Object transport);

	/**
	 * Unblocks the blocked active pin through the insertion of the <em>PUK</em>
	 * 
	 * @param tp
	 *            - The {@link TransportProvider}, which is used to transmit
	 *            data.
	 * @param verifySecret
	 *            - The verify secret.
	 * @param verifySecretInput
	 *            - The inserted currently active <em>PIN</em>.
	 * @param unblockSecret
	 *            - The unblock secret.
	 * @return Returns the status code of the <em>APDU</em>-Response.
	 */
	int doPINUnblock(TransportProvider tp, byte verifySecret, SecureHolder verifySecretInput, byte unblockSecret);

	/**
	 * Changes the currently active pin, through the insertion of the new pin.
	 * 
	 * @param tp
	 *            - The used transport provider.
	 * @param verifySecret
	 *            - The verify secret.
	 * @param verifySecretInput
	 *            - The inserted active <em>PIN</em>.
	 * @param modifySecret
	 *            - The modify secret.
	 * @param modifySecretInput
	 *            - The inserted new <em>PIN</em>.
	 * @return Returns the status code of the <em>APDU</em>-Response.
	 */
	int doPINChange(TransportProvider tp, byte verifySecret, SecureHolder verifySecretInput, byte modifySecret,
			SecureHolder modifySecretInput);

	/**
	 * Initializes the electronic signing process.
	 * 
	 * @param tp0
	 *            - The used transport provider.
	 * @return Returns the status code of the <em>APDU</em>-Response.
	 */
	public int doESignInit(TransportProvider tp0);

	/**
	 * Changes the actual electronic signature pin.
	 * 
	 * @param tp0
	 *            - The used transport provider.
	 * @return Returns the status code of the <em>APDU</em>-Response.
	 */
	public int doESignChange(TransportProvider tp0);

	/**
	 * Unblocks the actual electronic signature pin.
	 * 
	 * @param tp0
	 *            - The used transport provider.
	 * @return Returns the status code of the <em>APDU</em>-Response.
	 */
	public int doESignUnblock(TransportProvider tp0);

	/**
	 * Terminates the electronic signing process.
	 * 
	 * @param tp0
	 *            - The used transport provider.
	 * @return Returns the status code of the <em>APDU</em>-Response.
	 */
	public int doESignTerminate(TransportProvider tp0);

	/**
	 * Starts the authentication of the user against the eID-Server.
	 * 
	 * @param CHAT
	 *            - The user data to send.
	 * @param secret
	 *            - The inserted pin.
	 * @param termDesc
	 *            - The description of the used terminal.
	 * 
	 * @return Returns <strong>true</strong>, if the authentication was a
	 *         success, otherwise <strong>false</strong>.
	 */
	boolean startAuthentication(byte[] CHAT, SecureHolder secret, byte[] termDesc);

	/**
	 * Resets the <tt>CardHandler</tt>. Sets the <tt>initialized</tt> state, of
	 * the <tt>CardHandler</tt> to <strong>false</strong>, executes the
	 * <tt>close</tt> function of the {@link TransportProvider} and deletes the
	 * reference to the used {@link TransportProvider}.
	 */
	void reset();

	/**
	 * Returns the <tt>IDPICC</tt>, the currently used
	 * <tt>Internetwork Datagram Protocol</tt>.
	 * 
	 * @return Returns the <tt>IDPICC</tt>.
	 */
	byte[] getIDPICC();

	/**
	 * Returns the <tt>CAReference</tt>.
	 * 
	 * @return Returns <tt>CAReference</tt>.
	 */
	List<byte[]> getCAReferences();

	/**
	 * Returns the content of the file <tt>EF.CardAccess</tt>. The file contains
	 * the following security informations according to [TR-03110].
	 * <ul>
	 * <li>PACEInfo</li>
	 * <li>ChipAuthenticationInfo</li>
	 * <li>ChipAuthenticationDomainParameterInfo</li>
	 * <li>PrivilegedTerminalInfo</li>
	 * <li>TerminalAuthenticationInfo</li>
	 * <li>CardInfoLocator</li>
	 * </ul>
	 * 
	 * @return Returns the <tt>EF.CardAccess</tt>-file.
	 * 
	 */
	byte[] getEFCardAccess();

	/**
	 * The command <tt>PSO:Verify Certificate</tt> is used to verify and import
	 * certificates for Terminal Authentication.
	 * 
	 * @param cvc
	 *            - Certificate to valid.
	 * 
	 * @return Returns <strong>true</strong> if the given certificate is valid.
	 *         Otherwise <strong>false</strong>.
	 */
	boolean verifyCertificate(byte[] cvc);

	/**
	 * Initializes the terminal authentication. The terminal authentication
	 * protocol is a two move challenge-response protocol that provides explicit
	 * unilateral authentication of the terminal.
	 * <p>
	 * The ephemeral key is used to create the ECDH.
	 * </p>
	 * 
	 * @param ephemeralKey - The ephemeral key.
	 * @param auxData - The auxiliary data.
	 */
	void initTA(byte[] ephemeralKey, byte[] auxData);

	/**
	 * The command Get Challenge is used to perform Terminal Authentication.
	 * 
	 * @return Returns the <em>APDU</em>-Response.
	 */
	byte[] getTAChallenge();

	/**
	 * Verifies the terminal signature during the Terminal
	 * Authentication-Process.
	 * 
	 * @param taSignature
	 *            - The given signature.
	 * @return Returns <strong>true</strong>, if the signature is valid.
	 *         Otherwise <strong>false</strong>.
	 */
	boolean verifyTASignature(byte[] taSignature);

	/**
	 * <p>
	 * Returns the content of the file <tt>EF.CardAccess</tt>. The file contains
	 * the following security informations according to [TR-03110].
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
	 * @return Returns the content of the file <tt>EF.CardSecurity</tt>.
	 */
	byte[] getEFCardSecurity();

	/**
	 * Executes the card access to access the inserted card.
	 * 
	 * @return Returns the response of the general authentication.
	 */
	byte[] execCA();

	/**
	 * Transmits the provided <em>cmd</em> in order to fulfill the <tt>PACE</tt>
	 * -protocol.
	 * 
	 * @param cmd
	 *            - The provided <em>command</em>.
	 * 
	 * @return Returns the <em>response</em> or <strong>null</strong>, if no
	 *         response was sent.
	 */
	byte[] transmit(byte[] cmd);
}
