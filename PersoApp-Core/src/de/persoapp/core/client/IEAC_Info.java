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

import java.util.Date;

/**
 * <p>
 * The <em>IEAC_Info</em> interface provides the magic constants and functions
 * related to the EAC_Protocol. See for further informations TR-03110.
 * </p>
 * 
 * @author Christian Kahlo, Ralf Wondratschek.
 * @author Rico Klimsa - added javadoc comments.
 */
public interface IEAC_Info {
	
	/**
	 * Base OID for BSI, Germany
	 * <p>
	 * <code>id-bsi OBJECT IDENTIFIER ::= { 0.4.0.127.0.7 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE						= "0.4.0.127.0.7";
	
	/**
	 * The base OID, to identify applications.
	 * <p>
	 * <code>id-app OBJECT IDENTIFIER ::= { bsi-de applications(3) }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_APPS					= "0.4.0.127.0.7.3";
	
	/** 
	 * The base OID, which identifies the machine readable travel documents
	 * <p>
	 * <code>id-mrtd OBJECT IDENTIFIER ::= { bsi-de applications(3) 1 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD					= "0.4.0.127.0.7.3.1";

	/**
	 * The base OID, which identifies the roles and authorization levels of
	 * different terminal types.
	 * <p>
	 * <code>id-mrtd OBJECT IDENTIFIER ::= { bsi-de applications(3) mrtd(1) 2 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_ROLES			= "0.4.0.127.0.7.3.1.2";
	
	/**
	 * The OID, which identifies the inspection system.
	 * <p>
	 * <code>id-EAC-ePassport OBJECT IDENTIFIER ::= { bsi-de applications(3) mrtd(1) roles(2) 1 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_ROLES_IS		= "0.4.0.127.0.7.3.1.2.1";
	
	/**
	 * The OID, which identifies the authentication terminal.
	 * <p>
	 * <code>id-EAC-ePassport OBJECT IDENTIFIER ::= { bsi-de applications(3) mrtd(1) roles(2) 2 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_ROLES_AT		= "0.4.0.127.0.7.3.1.2.2";
	
	/**
	 * The OID, which identifies the secure target.
	 * <p>
	 * <code>id-EAC-ePassport OBJECT IDENTIFIER ::= { bsi-de applications(3) mrtd(1) roles(2) 3 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_ROLES_ST		= "0.4.0.127.0.7.3.1.2.3";

	/**
	 * The base OID to identify certificate extensions
	 * <p>
	 * <code>id-extensions OBJECT IDENTIFIER ::= { bsi-de applictions(3) mrtd(1) 3 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_EXT				= "0.4.0.127.0.7.3.1.3";
	
	/**
	 * The OID to identify extensions of the description.
	 * <p>
	 * <code>id-description OBJECT IDENTIFIER ::= { id-extensions 1 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC		= "0.4.0.127.0.7.3.1.3.1";
	
	/**
	 * The OID to identify the terms of usage in plain text format.
	 * <p>
	 * <code>id-plainFormat OBJECT IDENTIFIER ::= { id-description 1 }PlainTermsOfUsage ::= UTF8String</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_TEXT	= "0.4.0.127.0.7.3.1.3.1.1";
	
	/**
	 * The OID to identify the terms of usage in html format.
	 * <p>
	 * <code>id-htmlFormat OBJECT IDENTIFIER ::= { id-description 2 }HtmlTermsOfUsage ::= IA5String</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_HTML	= "0.4.0.127.0.7.3.1.3.1.2";
	
	/**
	 * The OID to identify the terms of usage in pdf format.
	 * <p>
	 * <code>id-pdfFormat OBJECT IDENTIFIER ::= { id-description 3 }PdfTermsOfUsage ::= OCTET STRING</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_PDF	= "0.4.0.127.0.7.3.1.3.1.3";
	
	/**
	 * The OID to identify the terminal sector.
	 * <p>
	 * <code>id-termSect OBJECT IDENTIFIER ::= { bsi-de applications(3) mrtd(1) extension(3) 2 }</code>
	 * </p>
	 */
	public static final String	OID_BSI_DE_MRTD_EXT_SECT		= "0.4.0.127.0.7.3.1.3.2";

	/**
	 * Returns the date on which the certificate was issued.
	 * 
	 * @return Returns the date on which the certificate was issued.
	 */
	public Date getEffectiveDate();

	/**
	 * Returns the date on which the certificate expires.
	 * 
	 * @return Returns the date on which the certificate expires.
	 */
	public Date getExpirationDate();

	/**
	 * Returns the informations about the current transaction.
	 * 
	 * @return Returns the informations about the current transaction.
	 */
	public String getTransactionInfo();

	/**
	 * Returns the DescriptionType.
	 * 
	 * @return Returns the DescriptionType.
	 */
	public String getDescriptionType();

	/**
	 * Returns the IssuerName.
	 * 
	 * @return Returns the IssuerName.
	 */
	public String getIssuerName();

	/**
	 * Returns the IssuerURL.
	 * 
	 * @return Returns the IssuerURL.
	 */
	public String getIssuerURL();

	/**
	 * Returns the SubjectName.
	 * 
	 * @return Returns the SubjectName.
	 */
	public String getSubjectName();

	/**
	 * Returns the SubjectURL.
	 * 
	 * @return Returns the SubjectURL.
	 */
	public String getSubjectURL();

	/**
	 * Returns the TermsOfUsage.
	 * 
	 * @return Returns the TermsOfUsage.
	 */
	public String getTermsOfUsage();

	/**
	 * Returns the RedirectURL.
	 * 
	 * @return Returns the RedirectURL.
	 */
	public String getRedirectURL();

	/**
	 * Returns the RequiredChat.
	 * 
	 * @return Returns the RequiredChat.
	 */
	public long getRequiredChat();

	/**
	 * Returns the OptionalChat.
	 * 
	 * @return Returns the OptionalChat.
	 */
	public long getOptionalChat();

	/**
	 * Returns the AuxiliaryData.
	 *
	 * @return Returns the AuxiliaryData.
	 */
	public byte[] getAuxiliaryData();

	/**
	 * Returns the VerifyCommunityID.
	 * 
	 * @return Returns the VerifyCommunityID.
	 */
	String getVerifyCommunityID();

	/**
	 * Returns the verifyAge.
	 * 
	 * @return Returns the verifyAge.
	 */
	long getVerifyAge();
}
