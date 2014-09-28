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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;

/**
 * <p>
 * The PersoSimTransport-class simulates the transport and the exchange
 * of data from a personal identity card with a remotehost.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class PersoSimTransport implements TransportProvider {

	/**
	 * The last received status word.
	 */
	private int				lastSW;
	
	/**
	 * The name or ip address of the remotehost.
	 */
	private final String	host;
	
	/**
	 * The port of the remotehost.
	 */
	private final int		port;
	
	/**
	 * The underlying {@link Socket} for transmitting operations.
	 */
	private Socket			socket;

	/**
	 * Creates and initializes a new {@link PersoSimTransport}.
	 * 
	 * @param host
	 *            - The used host.
	 * @param port
	 *            - The used port.
	 */
	private PersoSimTransport(final String host, final int port) {
		this.host = host;
		this.port = port;
	}
	
	/**
	 * Returns a new instance of {@link PersoSimTransport}.
	 * 
	 * @param host
	 *            - The name of the remotehost.
	 * @param port
	 *            - The port of the remotehost.
	 * 
	 * @return Returns a new instance of {@link PersoSimTransport}.
	 */
	public static PersoSimTransport getInstance(final String host, final int port) {
		/*
		 * The actual PersoSimTransport instance.
		 */
		final PersoSimTransport instance = new PersoSimTransport(host, port);

		final String atr = instance.exchangeApdu("FFFF0000");

		if (atr == null) {
			return null;
		} else {
			System.out.println("ATR: " + atr);
			return instance;
		}
	}

	@Override
	public Object getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * The empty response. 
	 */
	private static final byte[]	EMPTY_RESPONSE	= new byte[0];

	@Override
	public byte[] transmit(final byte[] apdu) {
		if (apdu != null && apdu.length >= 5 && apdu[0] == (byte) 0xFF && apdu[1] == (byte) 0x9A) {
			System.out.println("reader simulation for " + Hex.toString(apdu));
			lastSW = 0x6A88;
			byte[] response = EMPTY_RESPONSE;
			if (apdu[2] == 0x01) {
				switch (apdu[3]) {
					case 0x01:
						response = "PersoSim".getBytes();
						lastSW = 0x9000;
						break;
					case 0x03:
						response = "Transport".getBytes();
						lastSW = 0x9000;
						break;
					case 0x06:
						response = "V0.2".getBytes();
						lastSW = 0x9000;
						break;
					case 0x07:
						response = "TCP/IP".getBytes();
						lastSW = 0x9000;
						break;
				}
			}
			return response;
		} else {
			final byte[] rpdu = Hex.fromString(exchangeApdu(Hex.toString(apdu)));
			lastSW = ((rpdu[rpdu.length - 2] & 0xFF) << 8) + (rpdu[rpdu.length - 1] & 0xFF);

			//					System.out.println("PS: " + Hex.toString(ArrayTool.subArray(rpdu, 0, rpdu.length - 2)) + " / "
			//							+ Hex.shortToString(lastSW));
			return ArrayTool.subArray(rpdu, 0, rpdu.length - 2);
		}
	}

	@Override
	public int lastSW() {
		// TODO Auto-generated method stub
		return lastSW;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	/**
	 * Converts the provided argument into a {@link String} and exchanges it
	 * with a remotehost.
	 * 
	 * @param cmdApdu
	 *            - The provided data.
	 * @return Returns the response.
	 */
	private String exchangeApdu(String cmdApdu) {
		//		if (socket == null || socket.isClosed() || socket.isInputShutdown() || socket.isOutputShutdown()) {
		try {
			socket = new Socket(host, port);
		} catch (final IOException e) {
			socket = null;
			return null;
		}
		//		}

		PrintStream out = null;
		BufferedReader in = null;
		try {
			out = new PrintStream(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (final IOException e) {
			e.printStackTrace();
		}

		cmdApdu = cmdApdu.replaceAll("\\s", "");
		out.println(cmdApdu);
		out.flush();

		String respApdu = null;
		try {
			respApdu = in.readLine();
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return respApdu;
	}
}
