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

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;

import de.persoapp.core.util.ArrayTool;

/**
 * <p>
 * Functions for creating a CMac using standard Java cipher interfaces as a
 * base. Inspired by BouncyCastles internal engine.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class CMac {

	/**
	 * The constant to prevent the first byte from overflowing during doubling the bytes of
	 * an byte array.
	 */
	private static final byte	CONSTANT_128	= (byte) 0x87;

	/**
	 * The temporary buffer.
	 */
	private final byte[]		buf;
	
	/**
	 * The offset of the current processed data during the creation of the cmac.
	 */
	private int					bufOff;
	
	/**
	 * The used cipher.
	 */
	private final Cipher		cipher;

	/**
	 * The size of the mac.
	 */
	private final int			macSize;

	
	/** The values for additionally randomness. */
	private final byte[]		L, Lu, Lu2;

	/**
	 * Create a standard MAC based on a block cipher with the size of the MAC
	 * been given in bits.
	 * 
	 * @param cipher
	 *            - The cipher to be used as the basis of the MAC generation.
	 * @param macSize
	 *            - The size of the MAC in bytes. Must be a multiple of 8 and <=
	 *            128.
	 * @throws GeneralSecurityException
	 *             If a error occurs during the creation process of the cipher.
	 */
	public CMac(final Cipher cipher, final int macSize) throws GeneralSecurityException {
		if (macSize > cipher.getBlockSize()) {
			throw new IllegalArgumentException("macSize must be <= " + cipher.getBlockSize());
		}

		if (cipher.getBlockSize() != 16) {
			throw new IllegalArgumentException("cipher blocksize != 16 bytes");
		}

		this.cipher = cipher;
		this.macSize = macSize;

		buf = new byte[cipher.getBlockSize()];
		bufOff = 0;

		//initializes the L, Lu, Lu2 numbers
		final byte[] ZEROES = new byte[cipher.getBlockSize()];
		L = new byte[ZEROES.length];
		cipher.doFinal(ZEROES, 0, ZEROES.length, L, 0);

		Lu = doubleLu(L);
		Lu2 = doubleLu(Lu);
	}

	/**
	 * Doubles the values of every byte of the given byte array.
	 * 
	 * @param in
	 *            - The byte array, to modify.
	 * @return The byte array, which contains the doubled bytes.
	 */
	private byte[] doubleLu(final byte[] in) {
		final int FirstBit = (in[0] & 0xFF) >> 7;
		final byte[] ret = new byte[in.length];
		for (int i = 0; i < in.length - 1; i++) {
			ret[i] = (byte) ((in[i] << 1) + ((in[i + 1] & 0xFF) >> 7));
		}
		ret[in.length - 1] = (byte) (in[in.length - 1] << 1);
		if (FirstBit == 1) {
			ret[in.length - 1] ^= CONSTANT_128;
		}
		return ret;
	}

	/**
	 * Continues the multiple-part creation of the {@link CMac}, processing
	 * another data part. The first len bytes in the input
	 * buffer, starting at inOff are processed.
	 * 
	 * @param in
	 *            - The input buffer.
	 * @param inOff
	 *            - The offset in the input buffer, where the input starts.
	 * @param len
	 *            - The input length.
	 * 
	 * @throws GeneralSecurityException
	 *             If an error occurs during the encryption operation.
	 */
	public void update(final byte[] in, int inOff, int len) throws GeneralSecurityException {
		final int blockSize = cipher.getBlockSize();
		final int gapLen = blockSize - bufOff;

		if (len > gapLen) {
			System.arraycopy(in, inOff, buf, bufOff, gapLen);
			cipher.update(buf, 0, blockSize);

			bufOff = 0;
			len -= gapLen;
			inOff += gapLen;

			while (len > blockSize) {
				cipher.update(in, inOff, blockSize);

				len -= blockSize;
				inOff += blockSize;
			}
		}
		
		//saves the result of the processed operation through the copie in the buffer.
		System.arraycopy(in, inOff, buf, bufOff, len);
		//updates the buffer length with the length of the copied result
		bufOff += len;
	}

	/**
	 * The padding to fill up the last block of encrypted or decrypted data.
	 */
	private static final byte[]	SM_PAD	= new byte[] { (byte) 0x80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	/**
	 * <p>
	 * Encrypts or decrypts data in a single-part operation, or finishes a
	 * multiple-part operation.
	 * </p>
	 * 
	 * @return Returns the new buffer with the result.
	 * 
	 * @throws GeneralSecurityException
	 *             If a error occurs during the encryption or decryption
	 *             process.
	 */
	public byte[] doFinal() throws GeneralSecurityException {
		final int blockSize = cipher.getBlockSize();

		byte[] lu;
		//Padding doesn't has to be added.
		if (bufOff == blockSize) {
			lu = Lu;
		} else {
			//Padding is added.
			System.arraycopy(SM_PAD, 0, buf, bufOff, buf.length - bufOff);
			lu = Lu2;
		}
		for (int i = 0; i < buf.length; i++) {
			buf[i] ^= lu[i];
		}

		final byte[] mac = cipher.doFinal(buf, 0, buf.length);
		reset();
		return ArrayTool.subArray(mac, 0, macSize);
	}

	/**
	 * Resets the mac generator.
	 * 
	 * @throws GeneralSecurityException
	 *             If an error occurs during the call of the doFinal
	 *             method.
	 */
	public void reset() throws GeneralSecurityException {
		/*
		 * clean the buffer.
		 */
		for (int i = 0; i < buf.length; i++) {
			buf[i] = 0;
		}

		bufOff = 0;

		/*
		 * reset the underlying cipher.
		 */
		cipher.doFinal();
	}
}
