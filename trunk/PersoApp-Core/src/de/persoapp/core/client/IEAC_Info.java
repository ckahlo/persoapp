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

import java.util.Date;

public interface IEAC_Info {
	public static final String	OID_BSI_DE						= "0.4.0.127.0.7";
	public static final String	OID_BSI_DE_APPS					= "0.4.0.127.0.7.3";
	public static final String	OID_BSI_DE_MRTD					= "0.4.0.127.0.7.3.1";

	public static final String	OID_BSI_DE_MRTD_ROLES			= "0.4.0.127.0.7.3.1.2";
	public static final String	OID_BSI_DE_MRTD_ROLES_IS		= "0.4.0.127.0.7.3.1.2.1";
	public static final String	OID_BSI_DE_MRTD_ROLES_AT		= "0.4.0.127.0.7.3.1.2.2";
	public static final String	OID_BSI_DE_MRTD_ROLES_ST		= "0.4.0.127.0.7.3.1.2.3";

	public static final String	OID_BSI_DE_MRTD_EXT				= "0.4.0.127.0.7.3.1.3";
	public static final String	OID_BSI_DE_MRTD_EXT_DESC		= "0.4.0.127.0.7.3.1.3.1";
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_TEXT	= "0.4.0.127.0.7.3.1.3.1.1";
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_HTML	= "0.4.0.127.0.7.3.1.3.1.2";
	public static final String	OID_BSI_DE_MRTD_EXT_DESC_PDF	= "0.4.0.127.0.7.3.1.3.1.3";
	public static final String	OID_BSI_DE_MRTD_EXT_SECT		= "0.4.0.127.0.7.3.1.3.2";

	public Date getEffectiveDate();

	public Date getExpirationDate();

	public String getTransactionInfo();

	public String getDescriptionType();

	public String getIssuerName();

	public String getIssuerURL();

	public String getSubjectName();

	public String getSubjectURL();

	public String getTermsOfUsage();

	public String getRedirectURL();

	public long getRequiredChat();

	public long getOptionalChat();

	public byte[] getAuxiliaryData();

	String getVerifyCommunityID();

	long getVerifyAge();
}
