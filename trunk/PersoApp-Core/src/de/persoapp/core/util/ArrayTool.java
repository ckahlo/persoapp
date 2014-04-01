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

package de.persoapp.core.util;

/**
 * 
 * @author chris
 */
public class ArrayTool {

	/** Creates a new instance of ArrayTool */
	public ArrayTool() {
	}

	public static final byte[] arrayconcat(final byte[] b1, final int offset1, final int length1, final byte[] b2,
			final int offset2, final int length2) {
		if (b1 == null || b2 == null || offset1 + length1 > b1.length || offset2 + length2 > b2.length) {
			return null;
		}

		final byte[] result = new byte[length1 + length2];
		System.arraycopy(b1, offset1, result, 0, length1);
		System.arraycopy(b2, offset2, result, length1, length2);
		return result;
	}

	public static final byte[] arrayconcat(final byte[] b1, final byte[] b2) {
		return arrayconcat(b1, 0, b1.length, b2, 0, b2.length);
	}

	public static final byte[] subArray(final byte[] b1, final int offset, final int length) {
		if (b1 == null || offset + length > b1.length) {
			return null;
		}

		final byte[] result = new byte[length];
		System.arraycopy(b1, offset, result, 0, length);
		return result;
	}

	public static final boolean arrayequal(final byte[] b1, final byte[] b2) {
		if (b1.length != b2.length) {
			return false;
		}

		for (int i = 0; i < b1.length; i++) {
			if (b1[i] != b2[i]) {
				return false;
			}
		}
		return true;
	}

	public static void insertShortInByteArray(final byte[] b, final int ofs, final int s) {
		b[ofs + 0] = (byte) ((s & 0xFF00) >> 8);
		b[ofs + 1] = (byte) (s & 0x00FF);
	}

	public static void insertIntInByteArray(final byte[] b, final int ofs, final int i) {
		b[ofs + 0] = (byte) ((i & 0xFF000000) >> 24);
		b[ofs + 1] = (byte) ((i & 0x00FF0000) >> 16);
		b[ofs + 2] = (byte) ((i & 0x0000FF00) >> 8);
		b[ofs + 3] = (byte) (i & 0x000000FF);
	}

	public static void insertLongInByteArray(final byte[] b, final int ofs, final long l) {
		b[ofs + 0] = (byte) ((l & 0xFF00000000000000L) >> 56);
		b[ofs + 1] = (byte) ((l & 0x00FF000000000000L) >> 48);
		b[ofs + 2] = (byte) ((l & 0x0000FF0000000000L) >> 40);
		b[ofs + 3] = (byte) ((l & 0x000000FF00000000L) >> 32);
		b[ofs + 4] = (byte) ((l & 0x00000000FF000000L) >> 24);
		b[ofs + 5] = (byte) ((l & 0x0000000000FF0000L) >> 16);
		b[ofs + 6] = (byte) ((l & 0x000000000000FF00L) >> 8);
		b[ofs + 7] = (byte) (l & 0x00000000000000FFL);
	}

	public static short createShortfromByteArray(final byte[] b, final int ofs) {
		final short result = (short) (((short) (b[ofs + 0] & 0xFF) << 8) + (short) (b[ofs + 1] & 0xFF));

		return result;
	}

	public static int createIntfromByteArray(final byte[] b, final int ofs) {
		final int result = ((b[ofs + 0] & 0xFF) << 24) + ((b[ofs + 1] & 0xFF) << 16) + ((b[ofs + 2] & 0xFF) << 8)
				+ (b[ofs + 3] & 0xFF);

		return result;
	}

	public static long createLongfromByteArray(final byte[] b, final int ofs) {
		final long result = ((long) (b[ofs + 0] & 0xFF) << 56) + ((long) (b[ofs + 1] & 0xFF) << 48)
				+ ((long) (b[ofs + 2] & 0xFF) << 40) + ((long) (b[ofs + 3] & 0xFF) << 32)
				+ ((long) (b[ofs + 4] & 0xFF) << 24) + ((long) (b[ofs + 5] & 0xFF) << 16)
				+ ((long) (b[ofs + 6] & 0xFF) << 8) + (b[ofs + 7] & 0xFF);

		return result;
	}

	// overwrite byte arrays
	public static final void overwrite(final byte[] b) {
		if (b == null) {
			return;
		}
		for (int i = 0; i < b.length; i++) {
			b[i] = 0;
		}
	}

}
