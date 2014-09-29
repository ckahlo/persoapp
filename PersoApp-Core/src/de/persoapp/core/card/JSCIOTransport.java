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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;

/**
 * <p>
 * The JSCIOTransport provider implements the use of the hardware
 * interfaces to generical find and communicate with installed CCID and inserted
 * ICCs. The implementation relies on the javax.smartcardio library and
 * is in general an abstraction of the <em>javax.smartcardio</em> interface.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class JSCIOTransport implements TransportProvider, CCID {

	/**
	 * The command code for exchanging the smart card interface device vendors.
	 */
	private static final int	IOCTL_SMARTCARD_VENDOR_IFD_EXCHANGE	= SCARD_CTL_CODE(2048);
	
	/**
	 * The command code for the <em>get feature request</em>.
	 */
	private static final int	CM_IOCTL_GET_FEATURE_REQUEST		= SCARD_CTL_CODE(3400);

	/**
	 * The escape command code for an <em>CCID</em>.
	 */
	private static final int	IOCTL_CCID_ESCAPE					= SCARD_CTL_CODE(3500);

	/**
	 * The service type.
	 */
	private static final String	PcscServiceType						= "PC/SC";
	
	/**
	 * The default type of the terminal factory.
	 */
	private static final String	NoneType							= "None";
	
	/**
	 * The <em>Personal Computer</em> / <em>Smart card</em> default provider.
	 */
	private static final String	PcscDefaultProvider					= "SunPCSC";


	/**
	 * Computes the smart card control code, according to the underlying
	 * operating system, from the provided code.
	 * 
	 * @param code
	 *            - The command code, to convert.
	 * @return Returns the computed control code.
	 */
	private static final int SCARD_CTL_CODE(final int code) {
		final String os_name = System.getProperty("os.name").toLowerCase();
		if (os_name.indexOf("windows") > -1) {
			return 0x31 << 16 | code << 2;
		} else {
			return 0x42000000 + code;
		}
	}

	/**
	 * The handle of the used card channel.
	 */
	private final CardChannel			cc;
	
	/**
	 * The handle of the currently in the terminal inserted "integrated circuit card".
	 */
	private final Card					icc;
	
	/**
	 * The feature map of the <em>CCID</em>.
	 */
	private final Map<Byte, Integer>	features;

	/**
	 * The last received status word.
	 */
	private int							lastSW;

	/**
	 * <p>
	 * Creates and initializes a {@link JSCIOTransport}-provider. The provided
	 * handle of the {@link CardChannel} is used to retrieve the handle of the
	 * card and the underlying features of the <em>CCID</em>.
	 * </p>
	 * 
	 * @param cc
	 *            - The underlying {@link CardChannel}.
	 */
	private JSCIOTransport(final CardChannel cc) {
		this.cc = cc;
		this.icc = cc.getCard();
		this.features = queryFeatures(this.icc);
	}

	/**
	 * Returns all connected and installed <em>CardTerminals</em>.
	 * 
	 * @return Returns all connected and installed IFDs.
	 */
	private static final List<CardTerminal> getList() {
		try {
			TerminalFactory tf = null;

			if (NoneType.equals(TerminalFactory.getDefaultType())) {
				try {
					tf = TerminalFactory.getInstance(PcscServiceType, null, PcscDefaultProvider);
				} catch (final GeneralSecurityException gse) {
					Logger.getLogger(JSCIOTransport.class.getName()).log(Level.SEVERE, null, gse);
				}
			} else {
				tf = TerminalFactory.getDefault();
			}

			Logger.getLogger(JSCIOTransport.class.getName()).log(Level.INFO,
					"TerminalFactory: " + tf.getType() + ": " + tf.getProvider());

			return tf.terminals().list(CardTerminals.State.ALL);
		} catch (final CardException ex) {
			Logger.getLogger(JSCIOTransport.class.getName()).log(Level.SEVERE, null, ex);
		}

		return null;
	}

	/**
	 * <p>
	 * This function initializes according to the given application identifier
	 * the smart card for further commands and examines the response. A new
	 * handle of the {@link CardChannel} to the inserted <em>ECard</em> is
	 * created and returned in an Java Smart Card Input Output
	 * Transport-Provider.
	 * </p>
	 * <p>
	 * Additionally, it installs for every found <em>card terminal</em>, which
	 * has currently a card inserted, an {@link FutureTask} to call the inserted
	 * card. If the connection protocol of an inserted card did not match T=1,
	 * the specific card executes disconnect and the card terminal
	 * re-establishes the connection according to the protocol T=1.
	 * </p>
	 * 
	 * @param AID
	 *            - The application identifier, for opening.
	 * @return Returns a {@link JSCIOTransport}.
	 * 
	 * @throws IllegalArgumentException
	 *             If no terminals are installed.
	 */
	public static final JSCIOTransport open(final byte[] AID) {
		final List<CardTerminal> terminalList = getList();
		if (terminalList == null || terminalList.size() == 0) {
			throw new IllegalStateException("No Terminals installed.");
		}

		for (final CardTerminal terminal : terminalList) {
			try {
				final FutureTask<Card> task = new FutureTask<Card>(new Callable<Card>() {
					@Override
					public final Card call() throws Exception {
						try {
							Card currentCard = terminal.connect("*");
							if (!"T=1".equals(currentCard.getProtocol())) {
								currentCard.disconnect(false);
								currentCard = null;
								currentCard = terminal.connect("T=1");
							}
							return currentCard;
							// } catch(CardNotPresentException cnpex) {
							// no card in reader
						} catch (final CardException cex) {
							// most probably could not connect with T=1, because it's a T=0 card
							System.out.println(terminal.getName() + ": " + cex.getClass().getName() + ", "
									+ cex.getMessage());
						}
						return null;
					}
				});

				Executors.newFixedThreadPool(1).submit(task);

				Card currentCard = null;
				try {
					currentCard = task.get(2, TimeUnit.SECONDS);
				} catch (final TimeoutException toe) {
					Logger.getLogger(JSCIOTransport.class.getName()).log(Level.SEVERE,
							"!Terminal timed out: " + terminal.getName());
				}

				if (currentCard == null) {
					continue;
				}

				final byte[] atr = currentCard.getATR().getBytes();
				System.out.println("Trying terminal " + terminal.getName() + " with card " + Hex.toString(atr) + " /"
						+ currentCard.getProtocol());

				final CardChannel cc = currentCard.getBasicChannel();
				final byte[] selectAID = { (byte) 0x00, (byte) 0xA4, (byte) 0x04, (byte) 0x0C, (byte) AID.length };
				final ResponseAPDU rapdu = cc.transmit(new CommandAPDU(ArrayTool.arrayconcat(selectAID, AID)));
				if (rapdu.getSW() == 0x9000 || rapdu.getSW() == 0x6982) {
					return new JSCIOTransport(cc);
				}
			} catch (final CardException ex) {
				Logger.getLogger(JSCIOTransport.class.getName()).log(Level.SEVERE, terminal.getName(), ex);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Logs the given message to the console.
	 * 
	 * @param msg
	 *            - The message, which is logged to the console.
	 */
	private static void log(final String msg) {
		System.out.println(msg);
	}

	@Override
	public Object getParent() {
		return this.cc;
	}

	@Override
	public int lastSW() {
		return lastSW;
	}

	@Override
	public byte[] transmit(byte[] apdu) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			log("<P " + Hex.toString(apdu));
			ResponseAPDU res = cc.transmit(new CommandAPDU(apdu));
			baos.write(res.getData());
			lastSW = res.getSW();
			log(">P " + Hex.shortToString(lastSW) + ": " + Hex.toString(res.getData()));
			while (res.getSW1() == 0x61 || res.getSW1() == 0x9F) {
				if (res.getSW1() == 0x9F) {
					apdu = new byte[] { (byte) 0xA0, (byte) 0xC0, 0, 0, (byte) res.getSW2() };
				} else {
					apdu = new byte[] { 0, (byte) 0xC0, 0, 0, (byte) res.getSW2() };
				}
				res = cc.transmit(new CommandAPDU(apdu));
				baos.write(res.getData());
				lastSW = res.getSW();
			}
		} catch (final CardException ce) {
			throw new IllegalStateException(ce);
		} catch (final IOException ioe) {
			throw new IllegalStateException(ioe);
		}
		apdu = baos.toByteArray();
		return apdu;
	}

	@Override
	public void close() {
		if (cc != null) {
			try {
				// cc.getCard().disconnect(true);
				// false seems to reset card succesfully, while true doesn't?!
				cc.getCard().disconnect(false);
			} catch (final CardException e) {
				e.printStackTrace();
			}
		}
		// can't because it's final
		// cc = null;
	}

	/*
	 * CCID stuff
	 */
	
	/**
	 * Query all supported features of the terminal device, in which the icc is
	 * inserted.
	 * 
	 * @param icc
	 *            - The handle to the inserted card.
	 * @return Returns a map of all supported features.
	 */
	private static final Map<Byte, Integer> queryFeatures(final Card icc) {
		Map<Byte, Integer> features = null;

		try {
			final byte[] resp = icc.transmitControlCommand(CM_IOCTL_GET_FEATURE_REQUEST, new byte[0]);
			if (resp == null || resp.length == 0) {
				return null;
			}

			features = new HashMap<Byte, Integer>();
			for (int i = 0; i < resp.length; i += 6) {
				final int ioctlBigEndian = (0xff & resp[i + 2]) << 24 | (0xff & resp[i + 3]) << 16
						| (0xff & resp[i + 4]) << 8 | 0xff & resp[i + 5];
				features.put(new Byte(resp[i]), ioctlBigEndian);
			}
		} catch (final CardException ce) {
			// IOCTL not supported
		}

		if (features != null) {
			if (!features.containsKey(CCID.FEATURE_EXECUTE_PACE) && features.containsKey(FEATURE_VERIFY_PIN_DIRECT)) {
				// dirty work-around for Reiner-SCT Linux driver
				try {
					// Reiner SCT executePACE: 32 => 0x00313730 = OS-Base + (3500 + feature) * 4
					// feature = 3532 = 0x0DCC
					final byte[] paceRes = icc.transmitControlCommand(SCARD_CTL_CODE(3532), new byte[] { 0x01, 0x00,
							0x000 });
					System.out.println("######## " + Hex.toString(paceRes));
					if (paceRes != null && paceRes.length > 0) {
						final ByteBuffer bb = ByteBuffer.wrap(paceRes);
						bb.order(ByteOrder.LITTLE_ENDIAN);
						if (bb.getInt() == 0) {
							if (bb.getShort() == 1) {
								if ((bb.get() & 0x40) != 0) {
									features.put(CCID.FEATURE_EXECUTE_PACE, SCARD_CTL_CODE(3532));
								}
							}
						}
					}
				} catch (final Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("SCARD: No features.");
		}

		return features;
	}

	@Override
	public boolean hasFeature(final byte feature) {
		if (features != null) {
			return features.containsKey(feature);
		}
		return false;
	}

	@Override
	public byte[] transmitControlCommand(final byte feature, final byte[] ctrlCommand) {
		try {
			if (features == null || !features.containsKey(feature)) {
				throw new CardException(CCID.FEATURES[feature & 0xFF] + " not supported");
			}
			return icc.transmitControlCommand(features.get(feature), ctrlCommand);
		} catch (final CardException ex) {
			throw new RuntimeException("Failed to transmit CtrlCommand for " + CCID.FEATURES[feature & 0xFF]);
		}
	}

	@Override
	public byte[] verifyPinDirect(final byte[] PIN_VERIFY) throws CardException {
		if (!features.containsKey(CCID.FEATURE_VERIFY_PIN_DIRECT)) {
			throw new CardException("FEATURE_VERIFY_PIN_DIRECT not supported");
		}
		return icc.transmitControlCommand(features.get(CCID.FEATURE_VERIFY_PIN_DIRECT), PIN_VERIFY);
	}

	@Override
	public byte[] modifyPinDirect(final byte[] PIN_MODIFY) throws CardException {
		if (!features.containsKey(CCID.FEATURE_MODIFY_PIN_DIRECT)) {
			throw new CardException("FEATURE_MODIFY_PIN_DIRECT not supported");
		}

		return icc.transmitControlCommand(features.get(CCID.FEATURE_MODIFY_PIN_DIRECT), PIN_MODIFY);
	}

	@Override
	public String getName() {
		return icc.toString();
	}
}
