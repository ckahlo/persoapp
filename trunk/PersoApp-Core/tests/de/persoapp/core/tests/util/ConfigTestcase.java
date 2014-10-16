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
 * @version 1.0, 15.10.2014 11:03:22
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

package de.persoapp.core.tests.util;

import iso.std.iso_iec._24727.tech.schema.DIDAuthenticationDataType;

/**
 * @author Rico Klimsa
 */
public enum ConfigTestcase {
	
	/**
	 * The constant to identify unknown authentication protocol data.<br/>
	 * @see DIDAuthenticationDataType
	 */
	UNKNOWN_AUTH_PROT_DATA,
	
	/**
	 * The constant to renew the authentication protocol data in the first
	 * phase of the EAC.
	 */
	RENEW_DATA_FIRST_PHASE_OF_EAC,
	
	/**
	 * The constant to renew the authentication protocol data in the second
	 * phase of the EAC.
	 */
	RENEW_DATA_SECOND_PHASE_OF_EAC,
	
	/**
	 * The constant to renew the authentication protocol data in the additional
	 * phase of the EAC.
	 */
	RENEW_DATA_ADDITIONAL_PHASE_OF_EAC,
	
	/**
	 * The constant to delete the authentication protocol data.
	 */
	DELETE_DATA;
}
