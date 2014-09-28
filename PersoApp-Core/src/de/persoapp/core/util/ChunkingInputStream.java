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

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * The {@link ChunkingInputStream} provides methods to handle chunks of data and
 * to simply read data as well.
 * </p>
 * 
 * @author Christian Kahlo, Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class ChunkingInputStream extends FilterInputStream {

	/**
	 * The carriage return, which ends the current line.
	 */
	private static final char	CR			= 13;
	
	/**
	 * The line feed, which starts a new line.
	 */
	private static final char	LF			= 10;
	
	/**
	 * An estimate of the number of bytes that can be read (or skipped over)
	 * from this input stream without blocking.
	 */
	int							chunksize	= 0;

	/**
	 * Creates a new instance of {@link ChunkingInputStream}.
	 * 
	 * @param in
	 *            - The underlying {@link InputStream}.
	 */
	public ChunkingInputStream(final InputStream in) {
		super(in);
	}
	
	/**
	 * The buffer which is about to be read.
	 */
	byte[]	readBuf	= new byte[1];

	@Override
	public int read() throws IOException {
		if (read(readBuf) == 1) {
			return readBuf[0] & 0xFF;
		} else {
			return -1;
		}
	}

	@Override
	public int read(final byte[] b) throws IOException {
		return read(b, 0, b.length);    // in.read(b);
	}

	@Override
	public int read(final byte[] b, final int off, final int len) throws IOException {
		return readChunk(b, off, len);    // in.read(b, off, len);
	}

	@Override
	public int available() throws IOException {
		return chunksize;
	}

	@Override
	public boolean markSupported() {
		return false;
	}

	@Override
	public long skip(final long n) throws IOException {
		return 0;    // we don't skip
	}

	/**
	 * Reads a line of data from the given {@link InputStream}. A line of data
	 * ends as soon an line feed is hit.
	 * 
	 * @param input
	 *            - The InputStream, from which is read.
	 * @return The read line of the given InputStream as a String
	 * 
	 * @throws IOException
	 *             Thrown if the {@link InputStream} is blocked or already closed..
	 * @throws EOFException
	 *             Thrown if the EOF is reached during the reading process.
	 */
	private String readLine(final InputStream input) throws IOException, EOFException {
		final StringBuffer line = new StringBuffer();
		int c;

		while ((c = input.read()) != LF) {
			if (c == -1) {
				throw new EOFException();
			}

			if (c != CR) {
				line.append((char) c);
			}
		}

		return line.toString();
	}

	/**
	 * Reads a chunk of data. The remaining space is filled up with the data of
	 * the {@link FilterInputStream}.
	 * 
	 * @param out
	 *            - The buffer into which the data is read.
	 * @param offset
	 *            - The start offset in array b at which the data is written.
	 * @param size
	 *            - The maximum number of bytes read.
	 * @return This function returns <strong>-1</strong> if an error occurs.
	 *         Otherwise it returns the amount of space which is still available
	 *         in the buffer.
	 * 
	 * @throws IOException
	 *             Thrown if the {@link FilterInputStream} is blocked or closed.
	 * @throws IllegalArgumentException
	 *             Thrown if the amount of data, which is going to be read
	 *             exceeds the buffer <em>out</em>.
	 */
	private int readChunk(final byte[] out, int offset, final int size) throws IOException {
		if (out.length < offset + size) {
			throw new IllegalArgumentException();
		}

		if (chunksize == -1) {
			return -1;
		}

		if (chunksize == 0) {
			chunksize = Integer.parseInt(readLine(in), 16);

			if (chunksize == 0) {
				readLine(in);
				chunksize = -1;

				return -1;
			}
		}

		final int amount = Math.min(chunksize, size);
		int remaining = amount;

		while (remaining > 0) {
			final int len = in.read(out, offset, remaining);
			if (len < 0) {
				chunksize = -1;    // EOF
				return amount - remaining;
			}

			offset += len;
			remaining -= len;
		}

		chunksize -= amount;

		/* read ahead algorithm */
		if (chunksize == 0) {
			readLine(in);        // read CRLF at end of last data line
			chunksize = Integer.parseInt(readLine(in), 16);

			if (chunksize == 0) {
				readLine(in);    // read trailing CRLF
				chunksize = -1;
			}
		}

		return amount;
	}
}
