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

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;

import de.persoapp.core.util.ArrayTool;

/**
 * CMac using standard Java cipher interface as a base. Inspired by
 * BouncyCastles internal engine.
 * 
 * @author ckahlo
 * 
 */
public class CMac {
	private static final byte	CONSTANT_128	= (byte) 0x87;

	private final byte[]		buf;
	private int					bufOff;
	private final Cipher		cipher;

	private final int			macSize;

	private final byte[]		L, Lu, Lu2;

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

		System.arraycopy(in, inOff, buf, bufOff, len);
		bufOff += len;
	}

	private static final byte[]	SM_PAD	= new byte[] { (byte) 0x80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public byte[] doFinal() throws GeneralSecurityException {
		final int blockSize = cipher.getBlockSize();

		byte[] lu;
		if (bufOff == blockSize) {
			lu = Lu;
		} else {
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
	 * Reset the mac generator.
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
