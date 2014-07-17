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
 * Utility class for en-/decoding TLV data structures
 * 
 * @author Christian Kahlo, based on VX4.NET code
 * @author Rico Klimsa - added javadoc comments.
 */
public class TLV {

	/** universal tag class */
	public static final byte	UNIVERSAL		= (byte) 0x00;

	/** application tag class */
	public static final byte	APPLICATION		= (byte) 0x40;

	/** context specific tag class */
	public static final byte	CONTEXT			= (byte) 0x80;

	/** private tag class */
	public static final byte	PRIVATE			= (byte) 0xA0;

	/** constructed data object */
	public static final byte	CONSTRUCTED		= (byte) 0x20;

	/** universal tag EOC (End-of-Content). */
	public static final byte	EOC				= (byte) 0x00;

	/** universal tag BOOLEAN. */
	public static final byte	BOOLEAN			= (byte) 0x01;

	/** universal tag INTEGER. */
	public static final byte	INTEGER			= (byte) 0x02;

	/** universal tag BITSTRING. */
	public static final byte	BITSTRING		= (byte) 0x03;

	/** universal tag OCTETSTRING. */
	public static final byte	OCTETSTRING		= (byte) 0x04;

	/** universal tag NULL. */
	public static final byte	NULL			= (byte) 0x05;

	/** universal tag OID (object identifier). */
	public static final byte	OID				= (byte) 0x06;

	/** universal tag object descriptor. */
	public static final byte	ODESC			= (byte) 0x07;

	/** universal tag EXTERNAL. */
	public static final byte	EXTERNAL		= (byte) 0x08;

	/** universal tag REAL. */
	public static final byte	REAL			= (byte) 0x09;

	/** universal tag ENUMERATED. */
	public static final byte	ENUMERATED		= (byte) 0x0A;

	/** universal tag EMBEDDED PDV */
	public static final byte	EMB_PDV			= (byte) 0x0B;

	/** universal tag UTF8String */
	public static final byte	STRING_UTF8		= (byte) 0x0C;

	/** universal tag RELATIVE_OID. */
	public static final byte	RELATIVE_OID	= (byte) 0x0D;

	/** universal tag NumericString */
	public static final byte	STRING_NUM		= (byte) 0x12;

	/** universal tag PrintableString */
	public static final byte	STRING_PRINT	= (byte) 0x13;

	/** universal tag T61String */
	public static final byte	STRING_T61		= (byte) 0x14;

	/** universal tag VideotexString */
	public static final byte	STRING_VTXT		= (byte) 0x15;

	/** universal tag IA5String */
	public static final byte	STRING_IA5		= (byte) 0x16;

	/** universal tag UTCTime */
	public static final byte	TIME_UTC		= (byte) 0x17;

	/** universal tag GeneralizedTime */
	public static final byte	TIME_GENERAL	= (byte) 0x18;

	/** universal tag GraphicString */
	public static final byte	STRING_GRAPHIC	= (byte) 0x19;

	/** universal tag VisibleString */
	public static final byte	STRING_ISO646	= (byte) 0x1A;

	/** universal tag GeneralString */
	public static final byte	STRING_GENERAL	= (byte) 0x1B;

	/** universal tag UniversalString */
	public static final byte	STRING_UNI		= (byte) 0x1C;

	/** universal tag CHARACTER STRING */
	public static final byte	STRING_CHAR		= (byte) 0x1D;

	/** universal tag BMPString */
	public static final byte	STRING_BMP		= (byte) 0x1E;

	/** indicator for "use long-form" */
	public static final byte	MORE			= (byte) 0x1F;

	/** universal tag SEQUENCE of. */
	public static final byte	SEQ				= CONSTRUCTED | (byte) 0x10;

	/** universal tag SET of. */
	public static final byte	SET				= CONSTRUCTED | (byte) 0x11;

	/**
	 * convert tagID to byte-array
	 * 
	 * @param dst
	 *            - destination array
	 * @param ofs
	 *            - destination offset
	 * @param value
	 *            - tag value
	 * @param valSize
	 *            - size of tag value (amount of bytes)
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
	 *            - tagID
	 * @param data
	 *            - content
	 * @return <em>BER-TLV</em> object
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
	 * Encode an UTF-8 string as BER-TLV
	 * 
	 * @param tagID
	 *            - enclosing tag ID
	 * @param value
	 *            - content
	 * 
	 * @return input string as <em>BER-TLV</em> object.
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
	 * Encode an object ID as <em>BER-TLV</em> object
	 * 
	 * @param oid
	 *            - object ID
	 * @param data
	 *            - content
	 * @return BER-TLV encoded object ID and content
	 * 
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
	 * Simply concatenate given object identifier and content
	 * 
	 * @param oid
	 *            - object id
	 * @param data
	 *            - content
	 * @return object identifier and content as one byte-array
	 */
	public static byte[] buildOID(final byte[] oid, final byte[] data) {
		return concat(oid, data);
	}

	/**
	 * get first occurrence of given tag
	 * 
	 * @param data
	 *            - <em>BER-TLV</em> encoded input data
	 * @param tag
	 *            - tag to find
	 * @return content of tag or null if no data available or found
	 * 
	 * @see {@link #get(byte[], byte)}
	 * @see {@link #get(byte[], short)}
	 */
	public static byte[] get(final byte[] data, final byte[] tag) {
		if (data == null || tag == null || data.length == 0 || tag.length == 0) {
			return null;
		}

		try {
			int i = 0, datalen = 0, sizelen = 0;
			while (i + tag.length + 1 < data.length) {
				int tagLength = 1;
				if ((data[i] & MORE) == MORE) {
					while ((data[i + tagLength++] & 0x80) != 0) {
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
	 * get first occurrence of given single-byte tag
	 * 
	 * @param data
	 *            - <em>BER-TLV</em> encoded input data
	 * @param tag
	 *            - tag to find
	 * @return - content or null if not found
	 * 
	 * @see {@link #get(byte[], byte[])}
	 * @see {@link #get(byte[], short)}
	 */
	public static byte[] get(final byte[] data, final byte tag) {
		return get(data, new byte[] { tag });
	}

	/**
	 * get first occurrence of given two-byte tag
	 * 
	 * @param data
	 *            - <em>BER-TLV</em> encoded input data
	 * @param tag
	 *            - tag to find
	 * @return - content or null if not found
	 * 
	 * @see {@link #get(byte[], byte)}
	 * @see {@link #get(byte[], byte[])}
	 */
	public static byte[] get(final byte[] data, final short tag) {
		return get(data, new byte[] { (byte) (tag >> 8 & 0xFF), (byte) (tag & 0xFF) });
	}

	/*
	 * case DerParser.UNIVERSAL_STRING: throw new
	 * IOException("Invalid DER: can't handle UCS-4 string"); //$NON-NLS-1$
	 */
	/**
	 * Parse BER-TLV string representations and return string.
	 * 
	 * @param data
	 *            - BER-TLV encoded string
	 * @return {@link String} from input data
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

	/**
	 * return a list of all matching occurrences of given tag
	 * 
	 * @param data
	 *            - <em>BER-TLV</em> encoded input data
	 * @param tag
	 *            - tag to find
	 * 
	 * @return {@link List} of byte-array containing content
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
				if ((data[i] & MORE) == MORE) {
					while ((data[i + tagLength++] & 0x80) != 0) {
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
	 * return a list of all matching occurrences of given single-byte tag
	 * 
	 * @param data
	 *            - <em>BER-TLV</em> encoded input data
	 * @param tag
	 *            - tag to find
	 * 
	 * @return {@link List} of byte-array containing content
	 * 
	 */
	public static List<byte[]> getM(final byte[] data, final byte tag) {
		return getM(data, new byte[] { tag });
	}

	/**
	 * return a list of all matching occurrences of given two-byte tag
	 * 
	 * @param data
	 *            - <em>BER-TLV</em> encoded input data
	 * @param tag
	 *            - tag to find
	 * 
	 * @return {@link List} of byte-array containing content
	 * 
	 */
	public static List<byte[]> getM(final byte[] data, final short tag) {
		return getM(data, new byte[] { (byte) (tag >> 8 & 0xFF), (byte) (tag & 0xFF) });
	}

	/**
	 * concatenate two byte arrays
	 * 
	 * @param b1
	 *            - first source array
	 * @param offset1
	 *            - offset in first source array
	 * @param length1
	 *            - length for first source array
	 * @param b2
	 *            - second source array
	 * @param offset2
	 *            - offset in second source array
	 * @param length2
	 *            - length for second source array
	 * 
	 * @return first and second source array concatenated
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
	 * concatenate two byte arrays
	 * 
	 * @param b1
	 *            - first source array
	 * @param b2
	 *            - second source array
	 * @return first and second source array concatenated
	 * 
	 * @see #concat(byte[], byte[])
	 */
	public static final byte[] concat(final byte[] b1, final byte[] b2) {
		return concat(b1, 0, b1.length, b2, 0, b2.length);
	}
}
