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
 * @version 1.0, 29.01.2015 15:50:34
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

package de.persoapp.core.tests;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.persoapp.core.util.ArrayTool;

/**
 * Some test cases for the array tool utility class.

 * @author Rico Klimsa, 2015
 */
public class ArrayToolTest {

	private static byte[] byteArray1 = null;
	private static byte[] byteArray2 = null;

	@Before
	public void init() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byteArray1 = DatatypeConverter.printHexBinary(md.digest(byteArray1 = Integer.toHexString(
				new Random().nextInt(Integer.MAX_VALUE / 2) + 5000000)
				.getBytes("UTF-8"))).getBytes();

		byteArray2 = DatatypeConverter.printHexBinary(md.digest(byteArray2 = Integer.toHexString(
				new Random().nextInt(Integer.MAX_VALUE / 2) + 5000000)
				.getBytes("UTF-8"))).getBytes();

		Logger.getLogger(getClass().getName()).info(
				"Byte array1: " + new String(byteArray1));
		Logger.getLogger(getClass().getName()).info(
				"Byte array2: " + new String(byteArray2));
	}

	@Test
	public void ArrayTool_1() {
		ArrayTool arrayTool = new ArrayTool();
		Assert.assertNotNull("ArrayTool constructor returns null", arrayTool);
	}

	@Test
	public void ArrayTool_2() {
		byte[] concat = ArrayTool.arrayconcat(byteArray1, byteArray2);
		Assert.assertTrue("Result array has an unexpected length.",
				concat.length == byteArray1.length + byteArray2.length);

		byte[] tmpArray = new byte[byteArray1.length];
		System.arraycopy(byteArray1, 0, tmpArray, 0, byteArray1.length);
		Assert.assertArrayEquals("Array1 is not correctly included",
				byteArray1, tmpArray);
	}

	@Test
	public void ArrayTool_3() {
		Assert.assertTrue("Arrays are not equal.",
				ArrayTool.arrayequal(byteArray1, byteArray1));
	}

	@Test
	public void ArrayTool_4() {
		ArrayTool.overwrite(byteArray1);
		for (byte b : byteArray1) {
			Assert.assertTrue("Array not correctly overwritten.", b == 0);
		}
	}

	@Test
	public void ArrayTool_5() {
		int offset = new Random().nextInt(byteArray2.length - 1);
		byte[] destArray = new byte[byteArray2.length - offset];
		
		System.arraycopy(byteArray2, offset, destArray, 0, byteArray2.length - offset);
		Assert.assertArrayEquals("Arrays are not equal.",
				destArray,
				
				ArrayTool.subArray(byteArray2, offset, byteArray2.length - offset));
	}

	@Test
	public void ArrayTool_6() {
		int offset = new Random().nextInt(byteArray1.length - 1);
		Assert.assertEquals("Short representations are not equal.", ByteBuffer
				.wrap(byteArray1).getShort(offset), ArrayTool
				.createShortfromByteArray(byteArray1, offset));
	}

	@Test
	public void ArrayTool_7() {
		int offset = new Random().nextInt(byteArray1.length - 4);
		Assert.assertEquals("Int representation are not equal.", ByteBuffer
				.wrap(byteArray1).getInt(offset), ArrayTool
				.createIntfromByteArray(byteArray1, offset));
	}

	@Test
	public void ArrayTool_8() {
		int offset = new Random().nextInt(byteArray1.length - 8);

		Assert.assertEquals("Long representation are not equal", ByteBuffer
				.wrap(byteArray1).getLong(offset), ArrayTool
				.createLongfromByteArray(byteArray1, offset));
	}
	
	@Test
	public void ArrayTool_9() {
		int offset = new Random().nextInt(byteArray1.length - 3);
		short value = (short) new Random().nextInt(Short.MAX_VALUE);
		byte[] tmpByteArray1 = byteArray1;
		ByteBuffer.wrap(byteArray1).putShort(offset, value);
		ArrayTool.insertShortInByteArray(tmpByteArray1, offset, value);
		Assert.assertArrayEquals("Arrays are not equal", byteArray1, tmpByteArray1);
	}
}
