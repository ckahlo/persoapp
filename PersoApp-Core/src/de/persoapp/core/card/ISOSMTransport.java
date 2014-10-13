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

import java.nio.ByteBuffer;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.TLV;

/**
 * <p>
 * The ISOSMTransport implements the channel, that transmits the data
 * to the inserted card according to <em>ISO 7816-4</em>, after a <em>PACE</em>
 * -tunnel to the smart card is established.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class ISOSMTransport implements TransportProvider {
	
	/**
	 * The algorithm to encrypt the initialization vector.
	 */
	private static final String		IV_ENC_ALG		= "AES/ECB/NoPadding";
	
	/**
	 * The algorithm to encrypt the data, which is send on the established line.
	 */
	private static final String		LINE_ENC_ALG	= "AES/CBC/NoPadding";

	/**
	 * The currently used TransportProvider.
	 */
	private final TransportProvider	parent;

	/**
	 * The used keys.
	 */
	private byte[]					kEnc, kMac;

	/**
	 * The ciphers for encoding, decoding and the initialization vector.
	 */
	private Cipher					ivCipher, encCipher, decCipher;
	
	/**
	 * The used block-cipher based message authentication code.
	 */
	private CMac					cmac;
	
	/**
	 * The buffer of the initialization vector.
	 */
	private ByteBuffer				ivBuf			= null;
	
	/**
	 * Changes the initialization vector with every call on <code>getIV()</code>.
	 */
	private long					ssc				= 0;
	
	/**
	 * The last status word.
	 */
	private int						lastSW			= -1;

	/**
	 * Creates and initializes a new instance of the {@link ISOSMTransport}
	 * -provider. 
	 * 
	 * @param parent
	 *            - The underlying transport provider.
	 */
	public ISOSMTransport(TransportProvider parent) {
		if (parent == null) {
			throw new NullPointerException("parent transport provider required");
		}
		if (parent instanceof ISOSMTransport) {
			parent = (TransportProvider) parent.getParent();
		}
		this.parent = parent;
	}

	@Override
	public Object getParent() {
		return this.parent;
	}

	@Override
	public int lastSW() {
		if (lastSW == -1) {
			return parent.lastSW();
		}
		return lastSW;
	}

	@Override
	public byte[] transmit(byte[] apdu) {
		if (ivCipher != null && encCipher != null && decCipher != null) {
			apdu = encodeSM(apdu);
		}

		//System.out.println("<" + Hex.toString(apdu));

		apdu = parent.transmit(apdu);

		//System.out.println(">" + Hex.toString(apdu));

		if (ivCipher != null && encCipher != null && decCipher != null) {
			apdu = decodeSM(apdu);
		}

		return apdu;
	}

	/**
	 * Initializes the used ciphers. This is the cipher for the initialization
	 * vector, the cipher for encryption and the cipher for decryption. The
	 * cipher of the message authentication code is also initialized by this
	 * function.
	 * 
	 * @param newkEnc
	 *            - The EncKey.
	 * @param newkMac
	 *            - The MacKey.
	 */
	public void setupKeys(final byte[] newkEnc, final byte[] newkMac) {
		kEnc = newkEnc.clone();
		kMac = newkMac.clone();

		try {
			ivCipher = Cipher.getInstance(IV_ENC_ALG);
			encCipher = Cipher.getInstance(LINE_ENC_ALG);
			decCipher = Cipher.getInstance(LINE_ENC_ALG);

			final Cipher macCipher = Cipher.getInstance(LINE_ENC_ALG);
			macCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kMac, "AES"), new IvParameterSpec(new byte[16]));
			cmac = new CMac(macCipher, 8);

			ivBuf = ByteBuffer.allocate(16);
			ssc = 0;

			ivCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kEnc, "AES"));
		} catch (final Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Returns the initialization vector.
	 * 
	 * @return Returns the initialization vector.
	 */
	private byte[] getIV() {
		try {
			ivBuf.rewind();
			ivBuf.putLong(0);
			ivBuf.putLong(++ssc);
			return ivBuf.array();
		} catch (final Exception ex) {
		}
		return null;
	}

	/**
	 * The padding.
	 */
	private static final byte[]	SM_PAD	= new byte[] { (byte) 0x80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	/**
	 * Decodes the given data.
	 * 
	 * @param in
	 *            - The encoded message, as ANS1-structure.
	 * @return Returns the decoded message.
	 * 
	 * @throws IllegalStateException
	 *             If the cmac is corrupted.
	 */
	private byte[] decodeSM(final byte[] in) {
		try {
			byte[] data = TLV.get(in, (byte) 0x87);
			final byte[] mac = TLV.get(in, (byte) 0x8E);
			final byte[] sw = TLV.get(in, (byte) 0x99);

			final byte[] currentSSC = getIV();
			cmac.update(currentSSC, 0, currentSSC.length);
			if (in.length > 0) {
				// -8 -2 is short for "ignore MAC"
				cmac.update(in, 0, in.length - 8 - 2);
				cmac.update(SM_PAD, 0, SM_PAD.length - (in.length - 8 - 2) % SM_PAD.length);
			}

			if (mac != null && !ArrayTool.arrayequal(mac, cmac.doFinal())) {
				throw new IllegalStateException("MAC error.");
			}

			decCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kEnc, "AES"),
					new IvParameterSpec(ivCipher.doFinal(currentSSC)));

			if (data != null) {
				data = decCipher.doFinal(data, 1, data.length - 1);
				for (int i = data.length - 1; i > 0; i--) {
					if (data[i] == 0x00) {
						continue;
					} else if (data[i] == (byte) 0x80) {
						data = ArrayTool.subArray(data, 0, i);
						break;
					} else {
						break;
					}
				}
			} else {
				data = new byte[0];
			}

			if (sw != null) {
				this.lastSW = ((sw[0] & 0xFF) << 8) + (sw[1] & 0xFF);
			} else {
				this.lastSW = parent.lastSW();
			}

			return data;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Encodes the given APDU after <em>ISO 7816-4</em>.
	 * 
	 * @param in
	 *            - The APDU, to encode.
	 * 
	 * @return Returns the encoded APDU.
	 * 
	 * @throws IllegalArgumentException
	 *             If the APDU has an malformed structure.
	 */
	private byte[] encodeSM(final byte[] in) {
		try {
			final byte[] currentSSC = getIV();

			byte[] cmdHead = null;
			byte[] data = null;
			int ne = 0;

			try {
				cmdHead = ArrayTool.subArray(in, 0, 4);
				// case 1 APDU
				if (in.length < 5) {
					data = new byte[0];
				} else {
					final int l1 = in[4] & 0xff;
					// case 2 short
					if (in.length == 5) {
						data = new byte[0];
						ne = l1 == 0 ? 256 : l1;
						// short APDU
					} else if (l1 != 0) {
						// case 3 short, header, length, data
						if (in.length == 4 + 1 + l1) {
							data = ArrayTool.subArray(in, 5, l1);
							// case 4 short, header, length, data, response-length
						} else if (in.length == 4 + 2 + l1) {
							data = ArrayTool.subArray(in, 5, l1);
							final int l2 = in[in.length - 1] & 0xff;
							ne = l2 == 0 ? 256 : l2;
						} else {
							throw new IllegalArgumentException("Invalid APDU#1: length=" + in.length + ", le=" + l1);
						}
						// extended length
					} else if (in.length >= 7) {
						final int l2 = (in[5] & 0xff) << 8 | in[6] & 0xff;
						// case 2 extended
						if (in.length == 7) {
							data = new byte[0];
							ne = l2 == 0 ? 65536 : l2;
						} else if (l2 == 0) {
							throw new IllegalArgumentException("Invalid APDU#3: length=" + in.length + ", le1=" + l1
									+ ", le2=" + l2);
							// case 3 extended
						} else if (in.length == 4 + 3 + l2) {
							data = ArrayTool.subArray(in, 7, l2);
							// case 4 extended
						} else if (in.length == 4 + 5 + l2) {
							data = ArrayTool.subArray(in, 7, l2);
							final int neOfs = in.length - 2;
							final int l3 = (in[neOfs] & 0xff) << 8 | in[neOfs + 1] & 0xff;
							ne = l3 == 0 ? 65536 : l3;
						} else {
							throw new IllegalArgumentException("Invalid APDU#4: length=" + in.length + ", le1=" + l1
									+ ", le2=" + l2);
						}
					} else {
						throw new IllegalArgumentException("Invalid APDU#2: length=" + in.length + ", le=" + l1);
					}
				}
			} catch (final Exception e) {
				//e.printStackTrace();
				cmdHead = ArrayTool.subArray(in, 0, 4);
				data = ArrayTool.subArray(in, 5, in[4]);
				if (in.length > 5 + data.length) {
					ne = in[5 + data.length];
				}
			}

			if (data.length > 0) {
				encCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kEnc, "AES"),
						new IvParameterSpec(ivCipher.doFinal(currentSSC)));

				final int dataLength = data.length;
				data = encCipher.update(data, 0, dataLength);

				if (data != null) {
					data = ArrayTool.arrayconcat(data,
							encCipher.doFinal(SM_PAD, 0, SM_PAD.length - (dataLength - 0) % SM_PAD.length));
				} else {
					data = encCipher.doFinal(SM_PAD, 0, SM_PAD.length - (dataLength - 0) % SM_PAD.length);
				}

				data = TLV.build((byte) 0x87, ArrayTool.arrayconcat(new byte[] { 0x01 }, data));
			}

			if (ne > 0) {
				if (ne < 0x0100) {
					data = ArrayTool.arrayconcat(data, TLV.build((byte) 0x97, new byte[] { (byte) ne }));
				} else {
					data = ArrayTool.arrayconcat(data, TLV.build((byte) 0x97, new byte[] { (byte) 0, (byte) 0 }));
				}
			}

			cmdHead[0] |= 0x0C;

			cmac.update(currentSSC, 0, currentSSC.length);
			cmac.update(cmdHead, 0, cmdHead.length);
			cmac.update(SM_PAD, 0, SM_PAD.length - cmdHead.length % SM_PAD.length);
			if (data.length > 0) {
				cmac.update(data, 0, data.length);
				cmac.update(SM_PAD, 0, SM_PAD.length - data.length % SM_PAD.length);
			}

			data = ArrayTool.arrayconcat(data, TLV.build((byte) 0x8E, cmac.doFinal()));

			if (data.length < 0x0100 && ne < 0x0100) {
				data = ArrayTool.arrayconcat(cmdHead, ArrayTool.arrayconcat(
						ArrayTool.arrayconcat(new byte[] { (byte) data.length }, data), new byte[] { 0 }));
			} else {
				data = ArrayTool.arrayconcat(
						cmdHead,
						ArrayTool.arrayconcat(ArrayTool.arrayconcat(new byte[] { 0, (byte) (data.length >> 8),
								(byte) data.length }, data), new byte[] { 0, 0 }));
			}

			return data;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void close() {
		parent.close();
	}
}
