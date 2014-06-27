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

// TODO: Auto-generated Javadoc
/**
 * <p>
 * A implementation of the interface <tt>IEAC_Info</tt> provides all
 * informations for the user about the <em>eID-process</em>.
 * <p>
 * <code>public interface IEAC_Info</code>
 * <p>
 * 
 * @author Christian Kahlo, Ralf Wondratschek.
 * @author Rico Klimsa - added javadoc comments.
 */
public interface IEAC_Info {
	
	/** The Constant OID_BSI_DE. */
	public static final String	OID_BSI_DE						= "0.4.0.127.0.7";
	
	/** The Constant OID_BSI_DE_APPS. */
	public static final String	OID_BSI_DE_APPS					= "0.4.0.127.0.7.3";
	
	/** The Constant OID_BSI_DE_MRTD. */
	public static final String	OID_BSI_DE_MRTD					= "0.4.0.127.0.7.3.1";

	/** The Constant OID_BSI_DE_MRTD_ROLES. */
	public static final String	OID_BSI_DE_MRTD_ROLES			= "0.4.0.127.0.7.3.1.2";
	
	/** The Constant OID_BSI_DE_MRTD_ROLES_IS. */
	public static final String	OID_BSI_DE_MRTD_ROLES_IS		= "0.4.0.127.0.7.3.1.2.1";
	
	/** The Constant OID_BSI_DE_MRTD_ROLES_AT. */
	public static final String	OID_BSI_DE_MRTD_ROLES_AT		= "0.4.0.127.0.7.3.1.2.2";
	
	/** The Constant OID_BSI_DE_MRTD_ROLES_ST. */
	public static final String	OID_BSI_DE_MRTD_ROLES_ST		= "0.4.0.127.0.7.3.1.2.3";

	/** The Constant OID_BSI_DE_MRTD_EXT. */
	public static final String	OID_BSI_DE_MRTD_EXT				= "0.4.0.127.0.7.3.1.3";
	
	/** The Constant OID_BSI_DE_MRTD_EXT_DESC. */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC		= "0.4.0.127.0.7.3.1.3.1";
	
	/** The Constant OID_BSI_DE_MRTD_EXT_DESC_TEXT. */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_TEXT	= "0.4.0.127.0.7.3.1.3.1.1";
	
	/** The Constant OID_BSI_DE_MRTD_EXT_DESC_HTML. */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_HTML	= "0.4.0.127.0.7.3.1.3.1.2";
	
	/** The Constant OID_BSI_DE_MRTD_EXT_DESC_PDF. */
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_PDF	= "0.4.0.127.0.7.3.1.3.1.3";
	
	/** The Constant OID_BSI_DE_MRTD_EXT_SECT. */
	public static final String	OID_BSI_DE_MRTD_EXT_SECT		= "0.4.0.127.0.7.3.1.3.2";

	/**
	 * Returns the EffectiveDate.
	 * 
	 * @return Returns the EffectiveDate.
	 */
	public Date getEffectiveDate();

	/**
	 * Returns the ExpirationDate.
	 * 
	 * @return Returns the ExpirationDate.
	 */
	public Date getExpirationDate();

	/**
	 * Returns the TransactionInfo.
	 * 
	 * @return Returns the TransactionInfo.
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
