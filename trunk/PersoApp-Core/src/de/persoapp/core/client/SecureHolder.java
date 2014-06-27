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

import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>
 * The <tt>SecureHolder</tt> encrypts and stores data in a safety way. After a
 * PIN is inserted through a {@link PinPanel}, the inserted PIN is transmitted
 * from the gui to the core through a provided instance of the
 * <em>SecureHolder</em>.
 * </p>
 * <p>
 * <code>public class SecureHolder</code>
 * </p>
 * 
 * @author Christian Kahlo, Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class SecureHolder {
	
	/**
	 * The stored value.
	 */
	private transient byte[]	value	= null;
	
	/**
	 * The current system time for advanced encryption and decryption.
	 */
	private transient long		ts		= System.currentTimeMillis();

	/**
	 * Creates and initializes a new {@link SecureHolder}. The data of the
	 * <em>source</em> argument is encrypted and stored.
	 * 
	 * @param source
	 *            - The data, which is going to be stored.
	 */
	public SecureHolder(final byte[] source) {
		this.value = cipher(true, source);
		for (int i = 0; source != null && i < source.length; source[i++] = 0) {
			;
		}
	}

	/**
	 * Decrypts and returns the current stored value.
	 * 
	 * @return Decrypts and returns the current stored value.
	 */
	public final byte[] getValue() {
		return cipher(false, value);
	}

	/**
	 * Deletes the current stored value.
	 */
	public final void clean() {
		final byte[] source = value;
		value = null;
		for (int i = 0; source != null && i < source.length; source[i++] = 0) {
			;
		}
	}

	/**
	 * Encrypts the <em>input</em>, if <em>encrypt</em> is set to
	 * <strong>true</strong>. Otherwise it decrypts the provided data argument
	 * <em>input</em>.
	 * 
	 * @param encrypt
	 *            - If set to <strong>true</strong>, the function ecrypts the
	 *            data of the argument <em>input</em>. Otherwise it decrypts the
	 *            provided data of the argument <em>input</em>.
	 * @param input
	 *            - The data, which needs to be encrypted or decrypted.
	 * 
	 * @return Returns the encrypted or decrypted data.
	 */
	private byte[] cipher(final boolean encrypt, final byte[] input) {
		if (input == null) {
			return null;
		}

		try {
			final MessageDigest md = MessageDigest.getInstance("SHA-1");
			final byte[] result = new byte[12];
			final int hc = this.hashCode();
			result[0] = (byte) (ts & 0xFF);
			result[1] = (byte) (ts >> 8 & 0xFF);
			result[2] = (byte) (ts >> 16 & 0xFF);
			result[3] = (byte) (ts >> 24 & 0xFF);
			result[4] = (byte) (ts >> 32 & 0xFF);
			result[5] = (byte) (ts >> 40 & 0xFF);
			result[6] = (byte) (ts >> 48 & 0xFF);
			result[7] = (byte) (ts >> 56 & 0xFF);
			result[8] = (byte) (hc & 0xFF);
			result[9] = (byte) (hc >> 8 & 0xFF);
			result[10] = (byte) (hc >> 16 & 0xFF);
			result[11] = (byte) (hc >> 24 & 0xFF);
			md.update(this.getClass().getName().getBytes());

			final Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
			c.init(encrypt ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE, new SecretKeySpec(md.digest(result), 0, 16,
					"AES"), new IvParameterSpec(new byte[16]));

			return c.doFinal(input);
		} catch (final GeneralSecurityException e) {
			throw new IllegalStateException("SecureHolder: required Algorithm not available", e);
		}
	}
}
