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

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Tag-Length-Value encoding utilities
 * 
 * @author ckahlo, based on VX4.NET code
 * 
 */

public class TLV {

	// Tag Classes
	public static final byte	UNIVERSAL		= (byte) 0x00;
	public static final byte	APPLICATION		= (byte) 0x40;
	public static final byte	CONTEXT			= (byte) 0x80;
	public static final byte	PRIVATE			= (byte) 0xA0;

	public static final byte	CONSTRUCTED		= (byte) 0x20;

	// Tag Types
	public static final byte	EOC				= (byte) 0x00;					// End Of Content
	public static final byte	BOOLEAN			= (byte) 0x01;
	public static final byte	INTEGER			= (byte) 0x02;
	public static final byte	BITSTRING		= (byte) 0x03;
	public static final byte	OCTETSTRING		= (byte) 0x04;
	public static final byte	NULL			= (byte) 0x05;
	public static final byte	OID				= (byte) 0x06;
	//	public static final byte UNK1			= (byte)0x07;
	//	public static final byte UNK2			= (byte)0x08;
	public static final byte	REAL			= (byte) 0x09;
	public static final byte	ENUMERATED		= (byte) 0x0A;
	//	public static final byte UNK3			= (byte)0x0B;
	public static final byte	RELATIV_OID		= (byte) 0x0D;

	public static final byte	STRING_UTF8		= (byte) 0x0C;
	public static final byte	STRING_NUM		= (byte) 0x12;
	public static final byte	STRING_PRINT	= (byte) 0x13;
	public static final byte	STRING_T61		= (byte) 0x14;
	public static final byte	STRING_VTXT		= (byte) 0x15;
	public static final byte	STRING_IA5		= (byte) 0x16;
	public static final byte	STRING_GRAPHIC	= (byte) 0x19;
	public static final byte	STRING_ISO646	= (byte) 0x1A;
	public static final byte	STRING_GENERAL	= (byte) 0x1B;
	public static final byte	STRING_UNI		= (byte) 0x1C;
	public static final byte	STRING_BMP		= (byte) 0x1E;

	public static final byte	TIME_UTC		= (byte) 0x17;
	public static final byte	TIME_GENERAL	= (byte) 0x18;

	public static final byte	MORE			= (byte) 0x1F;

	public static final byte	SEQ				= CONSTRUCTED | (byte) 0x10;
	public static final byte	SET				= CONSTRUCTED | (byte) 0x11;

	private static void val2dst(final byte[] dst, final int ofs, final int value, final byte valSize) {
		for (int i = 0; i < valSize; i++) {
			dst[ofs + valSize - 1 - i] = (byte) (value >> 8 * i);
		}
	}

	public static byte[] build(final int tagID, final byte[] data) {
		final byte tagLen = (byte) (tagID < 0x100 ? 1 : tagID < 0x10000 ? 2 : tagID < 0x10000 ? 3 : 4);
		final int dataLen = data != null ? data.length : 0;
		final byte sizeLen = (byte) (dataLen < 0x80 ? 1 : dataLen < 0x0100 ? 2 : dataLen < 0x10000 ? 3
				: dataLen < 0x1000000 ? 4 : 5);
		final byte[] tag = new byte[tagLen + sizeLen + dataLen];

		int x = 0;
		val2dst(tag, x, tagID, tagLen);
		x += tagLen;

		if (sizeLen == 1) {
			tag[x] = (byte) dataLen;
			x += 1;
		} else {
			tag[x] = (byte) (0x80 + sizeLen - 1);
			val2dst(tag, x + 1, dataLen, (byte) (sizeLen - 1));
			x += sizeLen;
		}

		System.arraycopy(data, 0, tag, x, dataLen);
		return tag;
	}

	public static byte[] buildUTF8(final int tagID, final String value) {
		try {
			return build(tagID, build(STRING_UTF8, value.getBytes("UTF-8")));
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] buildOID(final String oid, final byte[] data) {
		final byte[] binOID;

		if (oid.indexOf('.') < 0) {
			binOID = build(OID, Hex.fromString(oid));
		} else {
			final String[] oidSplit = oid.split("\\.");
			if (oidSplit.length > 0) {
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				baos.write(0x40 * Integer.parseInt(oidSplit[0], 10) + Integer.parseInt(oidSplit[1], 10));
				for (int i = 2; i < oidSplit.length; i++) {
					final int oidPart = Integer.parseInt(oidSplit[i], 10);
					if (oidPart > 255) {
						System.out.println("#### " + oid);
					} else {
						baos.write(oidPart);
					}
				}

				binOID = build(OID, baos.toByteArray());
			} else {
				binOID = new byte[0];
			}
			//    		try {
			//				binOID = new DERObjectIdentifier(oid).getEncoded();
			//			} catch (IOException e) {
			//				e.printStackTrace();
			//			}
		}
		return buildOID(binOID, data);
	}

	public static byte[] buildOID(final byte[] oid, final byte[] data) {
		return concat(oid, data);
	}

	public static byte[] get(final byte[] data, final byte[] tag) {
		if (data == null || tag == null || data.length == 0 || tag.length == 0) {
			return null;
		}

		try {
			int i = 0, datalen = 0, sizelen = 0;
			while (i + tag.length + 1 < data.length) {
				int tagLength = 1;
				if ((data[i] & 0x1F) == 0x1F) {
					while ((data[i + tagLength++] & 0x1F) == 0x1F) {
						;
					}
				}
				datalen = data[i + tagLength] & 0xFF;
				sizelen = 1;
				if (datalen > 0x7F) {
					sizelen = datalen - 0x80 + 1;
					datalen = 0;
					for (int j = 0; j < sizelen - 1; j++) {
						datalen = (datalen << 8) + (data[i + tagLength + 1 + j] & 0xFF);
					}
				}

				if (data[i] == tag[0]) {
					if (tag.length == 1 || ArrayTool.arrayequal(ArrayTool.subArray(data, i, tag.length), tag)) {
						break;
					}
				}

				i += tagLength + sizelen + datalen;
			}

			if (i >= data.length) {
				return null;
			}

			final byte[] result = new byte[datalen];
			System.arraycopy(data, i + tag.length + sizelen, result, 0, result.length);
			return result;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] get(final byte[] data, final byte tag) {
		return get(data, new byte[] { tag });
	}

	public static byte[] get(final byte[] data, final short tag) {
		return get(data, new byte[] { (byte) (tag >> 8 & 0xFF), (byte) (tag & 0xFF) });
	}

	/*
	 * case DerParser.UNIVERSAL_STRING: throw new
	 * IOException("Invalid DER: can't handle UCS-4 string"); //$NON-NLS-1$
	 */
	public static String getString(final byte[] data) {
		if (data == null) {
			return null;
		}

		try {
			switch (data[0]) {
				case STRING_UTF8:
					return new String(TLV.get(data, data[0]), "UTF-8");
				case STRING_NUM:
				case STRING_PRINT:
				case STRING_VTXT:
				case STRING_IA5:
				case STRING_GRAPHIC:
				case STRING_ISO646:
				case STRING_GENERAL:
					return new String(TLV.get(data, data[0]), "ASCII");
				case STRING_BMP:
					return new String(TLV.get(data, data[0]), "UTF-16BE");
			}
		} catch (final UnsupportedEncodingException uee) {
		}
		return new String(TLV.get(data, data[0]));
	}

	/*
	 * getC, getConstructed
	 */

	public static List<byte[]> getM(final byte[] data, final byte[] tag) {
		if (data == null || tag == null || data.length == 0 || tag.length == 0) {
			return null;
		}

		int i = 0;
		try {
			int datalen = 0, sizelen = 0;
			while (i + tag.length + 1 < data.length) {
				int tagLength = 1;
				if ((data[i] & 0x1F) == 0x1F) {
					while ((data[i + tagLength++] & 0x1F) == 0x1F) {
						;
					}
				}
				datalen = data[i + tagLength] & 0xFF;
				sizelen = 1;
				if (datalen > 0x7F) {
					sizelen = datalen - 0x80 + 1;
					datalen = 0;
					for (int j = 0; j < sizelen - 1; j++) {
						datalen = (datalen << 8) + (data[i + tagLength + 1 + j] & 0xFF);
					}
				}

				if (data[i] == tag[0]) {
					if (tag.length == 1 || ArrayTool.arrayequal(ArrayTool.subArray(data, i, tag.length), tag)) {
						break;
					}
				}

				i += tagLength + sizelen + datalen;
			}

			if (i >= data.length) {
				return null;
			}

			final List<byte[]> list = new ArrayList<byte[]>();
			list.add(ArrayTool.subArray(data, i + tag.length + sizelen, datalen));

			i += tag.length + sizelen + datalen;
			if (i < data.length) {
				byte[] remainder = ArrayTool.subArray(data, i, data.length - i);

				while (remainder != null) {
					final List<byte[]> list2 = getM(remainder, tag);
					if (list2 != null) {
						list.addAll(list2);
					}
					//...
					remainder = null;
				}

			}

			return list;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<byte[]> getM(final byte[] data, final byte tag) {
		return getM(data, new byte[] { tag });
	}

	public static List<byte[]> getM(final byte[] data, final short tag) {
		return getM(data, new byte[] { (byte) (tag >> 8 & 0xFF), (byte) (tag & 0xFF) });
	}

	public static final byte[] concat(final byte[] b1, final int offset1, final int length1, final byte[] b2,
			final int offset2, final int length2) {
		if (b1 == null || b2 == null || offset1 + length1 > b1.length || offset2 + length2 > b2.length) {
			return null;
		}

		final byte[] result = new byte[length1 + length2];
		System.arraycopy(b1, offset1, result, 0, length1);
		System.arraycopy(b2, offset2, result, length1, length2);
		return result;
	}

	public static final byte[] concat(final byte[] b1, final byte[] b2) {
		return concat(b1, 0, b1.length, b2, 0, b2.length);
	}
}
