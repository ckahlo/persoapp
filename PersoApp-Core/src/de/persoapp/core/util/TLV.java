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
package de.persoapp.core.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tag-Length-Value encoding utilities for encoding/decoding issues.
 * Additionally, functions for building <tt>object identifier-(OID)</tt> are
 * also included.
 * <p>
 * <code>public class TLV</code>
 * </p>
 * 
 * @author Christian Kahlo, based on VX4.NET code
 * @author Rico Klimsa - added javadoc comments.
 */
public class TLV {

	// Tag Classes
	/** The Constant UNIVERSAL. */
	public static final byte	UNIVERSAL		= (byte) 0x00;
	
	/** The Constant APPLICATION. */
	public static final byte	APPLICATION		= (byte) 0x40;
	
	/** The Constant CONTEXT. */
	public static final byte	CONTEXT			= (byte) 0x80;
	
	/** The Constant PRIVATE. */
	public static final byte	PRIVATE			= (byte) 0xA0;

	/** The Constant CONSTRUCTED. */
	public static final byte	CONSTRUCTED		= (byte) 0x20;

	// Tag Types
	/** The Constant EOC. */
	public static final byte	EOC				= (byte) 0x00;					// End Of Content
	
	/** The Constant BOOLEAN. */
	public static final byte	BOOLEAN			= (byte) 0x01;
	
	/** The Constant INTEGER. */
	public static final byte	INTEGER			= (byte) 0x02;
	
	/** The Constant BITSTRING. */
	public static final byte	BITSTRING		= (byte) 0x03;
	
	/** The Constant OCTETSTRING. */
	public static final byte	OCTETSTRING		= (byte) 0x04;
	
	/** The Constant NULL. */
	public static final byte	NULL			= (byte) 0x05;
	
	/** The Constant OID. */
	public static final byte	OID				= (byte) 0x06;
	//	public static final byte UNK1			= (byte)0x07;
	//	public static final byte UNK2			= (byte)0x08;
	/** The Constant REAL. */
	public static final byte	REAL			= (byte) 0x09;
	
	/** The Constant ENUMERATED. */
	public static final byte	ENUMERATED		= (byte) 0x0A;
	//	public static final byte UNK3			= (byte)0x0B;
	/** The Constant RELATIV_OID. */
	public static final byte	RELATIV_OID		= (byte) 0x0D;

	/** The Constant STRING_UTF8. */
	public static final byte	STRING_UTF8		= (byte) 0x0C;
	
	/** The Constant STRING_NUM. */
	public static final byte	STRING_NUM		= (byte) 0x12;
	
	/** The Constant STRING_PRINT. */
	public static final byte	STRING_PRINT	= (byte) 0x13;
	
	/** The Constant STRING_T61. */
	public static final byte	STRING_T61		= (byte) 0x14;
	
	/** The Constant STRING_VTXT. */
	public static final byte	STRING_VTXT		= (byte) 0x15;
	
	/** The Constant STRING_IA5. */
	public static final byte	STRING_IA5		= (byte) 0x16;
	
	/** The Constant STRING_GRAPHIC. */
	public static final byte	STRING_GRAPHIC	= (byte) 0x19;
	
	/** The Constant STRING_ISO646. */
	public static final byte	STRING_ISO646	= (byte) 0x1A;
	
	/** The Constant STRING_GENERAL. */
	public static final byte	STRING_GENERAL	= (byte) 0x1B;
	
	/** The Constant STRING_UNI. */
	public static final byte	STRING_UNI		= (byte) 0x1C;
	
	/** The Constant STRING_BMP. */
	public static final byte	STRING_BMP		= (byte) 0x1E;

	/** The Constant TIME_UTC. */
	public static final byte	TIME_UTC		= (byte) 0x17;
	
	/** The Constant TIME_GENERAL. */
	public static final byte	TIME_GENERAL	= (byte) 0x18;

	/** The Constant MORE. */
	public static final byte	MORE			= (byte) 0x1F;

	/** The Constant SEQ. */
	public static final byte	SEQ				= CONSTRUCTED | (byte) 0x10;
	
	/** The Constant SET. */
	public static final byte	SET				= CONSTRUCTED | (byte) 0x11;

	
	/**
	 * <p>
	 * Converts the value of the parameter <strong>value</strong> into a signed
	 * byte and copies it in the <strong>dest</strong> array.
	 * </p>
	 * <p>
	 * The position of the element in the dest array is determined by the
	 * starting offset and the size of the copied value.
	 * </p>
	 * 
	 * @param dst
	 *            - The destination array.
	 * @param ofs
	 *            - The starting offset.
	 * @param value
	 *            - The value, to copie.
	 * @param valSize
	 *            - The size of the value.
	 */
	private static void val2dst(final byte[] dst, final int ofs, final int value, final byte valSize) {
		for (int i = 0; i < valSize; i++) {
			dst[ofs + valSize - 1 - i] = (byte) (value >> 8 * i);
		}
	}

	/**
	 * Builds the <em>BER-TLV</em>-object according to <em>ISO/IEC8825</em> and
	 * <em>ASN.1</em>.
	 * 
	 * @param tagID
	 *            - The given Tag-ID.
	 * @param data
	 *            - THe given data.
	 * @return Returns the <em>BER-TLV</em>-object.
	 */
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

	/**
	 * Builds the <em>BER-TLV</em> data structure according to
	 * <em>ISO/IEC8825</em> and the <em>ASN.1</em>. Previously converts the
	 * given <em>String</em> in <tt>UTF-8</tt> format.
	 * 
	 * @param tagID
	 *            - The given tag id.
	 * @param value
	 *            - The given String.
	 *            
	 * @return Returns the <em>BER-TLV</em>-object.
	 */
	public static byte[] buildUTF8(final int tagID, final String value) {
		try {
			return build(tagID, build(STRING_UTF8, value.getBytes("UTF-8")));
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * The result of this function is determined by the inserted <em>oid</em>.
	 * <p>
	 * If the <em>oid</em> does not contain a dot, the function encodes the
	 * <em>oid</em> after the <em>BER</em> and returns a <tt>byte array</tt>
	 * that has the following content:
	 * <table border="1">
	 * <tr>
	 * <td>The <em>oid</em> as <em>BER-TLV object</em></td>
	 * <td>The given data</td>
	 * </tr>
	 * </table>
	 * </p>
	 * <p>
	 * If the given <em>oid</em> contains one or more dots, the dots are removed
	 * and the given <em>oid</em> is parsed and encoded in hexadecimal format.
	 * The return value has the same structure as in the case that there arn't
	 * any dots from beginning.
	 * </p>
	 * 
	 * @param oid
	 *            - The oid.
	 * @param data
	 *            - The given data.
	 * @return Returns a <tt>byte-array</tt> with the digits of the created
	 *         <em>BER-TLV object</em> at the beginning and the digits of the
	 *         given <em>data</em> at the end.
	 */
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

	/**
	 * This function builds the object identifier (oid) through the given
	 * byte-array.
	 * 
	 * @param oid
	 *            - The non actual object identifier.
	 * @param data
	 *            - The data which needs to be identified through a oid.
	 * @return A chained byte-array with the digits of the given
	 *         <strong>oid</strong> at the beginning and the digits of the given
	 *         <strong>data</strong> right after them.
	 */
	public static byte[] buildOID(final byte[] oid, final byte[] data) {
		return concat(oid, data);
	}

	/**
	 * This function returns a byte-array, which contains just the digits which
	 * fit to the given tags.
	 * 
	 * @param data
	 *            - The given byte-array.
	 * @param tag
	 *            - The given tags which identify the searched data.
	 * @return The hexadecimal digits in binary form which fits to the given
	 *         tag.
	 * 
	 * @see {@link #get(byte[], byte[])}
	 */
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

	/**
	 * This function returns a byte-array, which contains just the data part
	 * from the <em>BER-TLV objects</em>, which tagId fits to the given tag.
	 * 
	 * @param data
	 *            - The given <em>BER-TLV objects</em>
	 * @param tag
	 *            - The tag which identifies the searched data.
	 * @return The data parts of the given <em>BER-TLV object</em> that tagId
	 *         fits to the given tag.
	 * 
	 * @see {@link #get(byte[], byte[])}
	 */
	public static byte[] get(final byte[] data, final byte tag) {
		return get(data, new byte[] { tag });
	}

	/**
	 * This function returns a byte-array, which contains just the digits which
	 * fit to the tag.
	 * 
	 * @param data
	 *            - The given byte-array.
	 * @param tag
	 *            - The given short-valued-tag which identifies the searched
	 *            data.
	 * @return The hexadecimal digits in binary form which fits to the given
	 *         tag.
	 * 
	 * @see {@link #get(byte[], byte)}
	 */
	public static byte[] get(final byte[] data, final short tag) {
		return get(data, new byte[] { (byte) (tag >> 8 & 0xFF), (byte) (tag & 0xFF) });
	}

	/*
	 * case DerParser.UNIVERSAL_STRING: throw new
	 * IOException("Invalid DER: can't handle UCS-4 string"); //$NON-NLS-1$
	 */
	/**
	 * Returns a string-representation of the given byte-array, according to the
	 * first element of the byte-array. The first element is responsible for
	 * setting the used format.
	 * 
	 * @param data
	 *            - The given byte array.
	 * @return A string-representation of the given byte-array.
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
	/**
	 * This function returns all values of the given byte-array
	 * <strong>data</strong> which fit on the given <strong>tag</strong>.
	 * 
	 * @param data
	 *            - The given array of bytes, which contains the digits.
	 * @param tag
	 *            - The given array of tags, which identifies the searched
	 *            values in the given data-array.
	 * @return A List of byte-arrays. Every byte-array refers to a given tag.
	 * 
	 * @see {@link #getM(byte[], short)}
	 * @see {@link #getM(byte[], byte)}
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
	
	/**
	 * This function returns the data parts of the given <em>BER-TLV</em> values
	 * which are identified through the given tag.
	 * 
	 * @param data
	 *            - The given <em>BER-TLV</em> values.
	 * @param tag
	 *            - The given tag.
	 * @return A List of the identified data parts.
	 */
	public static List<byte[]> getM(final byte[] data, final byte tag) {
		return getM(data, new byte[] { tag });
	}

	/**
	 * This function returns the data parts of the given <em>BER-TLV</em> values
	 * which are identified through the given tag.
	 * 
	 * @param data
	 *            - The given <em>BER-TLV</em> values.
	 * @param tag
	 *            - The given tag.
	 * @return A List of the identified data parts.
	 */
	public static List<byte[]> getM(final byte[] data, final short tag) {
		return getM(data, new byte[] { (byte) (tag >> 8 & 0xFF), (byte) (tag & 0xFF) });
	}

	/**
	 * This function stores the digits of defined range from the byte-arrays b1
	 * and b2 into a new byte-array.
	 * 
	 * @param b1
	 *            - The first byte-array.
	 * @param offset1
	 *            - The starting offset of the first byte-array.
	 * @param length1
	 *            - The length of the data, which is going to be stored.
	 * @param b2
	 *            - The second byte-array.
	 * @param offset2
	 *            - The starting offset of the second byte-array.
	 * @param length2
	 *            - The length of the data, which is going to be stored.
	 * @return This function returns a byte-array, which stores the copied
	 *         values from b1 and b2.
	 */
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

	/**
	 * This function chains the arrays b1 and b2 together.
	 * 
	 * @param b1
	 *            - The first byte array, which values are situated at the
	 *            beginning of the returned array.
	 * @param b2
	 *            - The second byte array, which values are situated after the
	 *            values of b2.
	 * @return The concatenated array which holds now the values of b1 and b2
	 * 
	 * @see #concat(byte[], byte[])
	 */
	public static final byte[] concat(final byte[] b1, final byte[] b2) {
		return concat(b1, 0, b1.length, b2, 0, b2.length);
	}
}
