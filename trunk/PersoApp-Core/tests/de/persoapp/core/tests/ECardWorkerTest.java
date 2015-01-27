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
 * @version 1.0, 20.01.2015 09:58:17
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

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.sun.net.ssl.HttpsURLConnection;

import de.persoapp.core.ECardWorker;
import de.persoapp.core.util.Util;

/**
 * @author Rico Klimsa
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ECardWorkerTest {
	
	/*
	 * magic constant for default test service
	 */
	private static final String	serviceURL	= "https://eid.services.ageto.net/persoapp/eidtest.jsp";
	private static HttpURLConnection con = null;
	
	@BeforeClass
	public static void init() {
		if(con==null) {
			try {
				con = (HttpURLConnection) Util.openURL(new URL(serviceURL));
				con.connect();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	@Test
	public void eCardWorkerTest_1() {
		try {
			Method method = ECardWorker.class.getDeclaredMethod("checkCertificate", URLConnection.class);
			method.setAccessible(true);
			X509Certificate cert = (X509Certificate) method.invoke(null, con);
			Assert.assertNotNull("Cert is null",cert);
			method.setAccessible(false);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void eCardWorkerTest_2() {
		try {
			String[] args = new String[]{"ResultMajor=ok"};
			Method method = ECardWorker.class.getDeclaredMethod("addParam", URI.class,args.getClass());
			method.setAccessible(true);
			URI uri = (URI) method.invoke(null, new URL(
					"http://localhost").toURI(), args);
			Assert.assertNotNull("URI is null", uri);
			Assert.assertTrue("Param is not added", uri.toString().contains("ResultMajor=ok"));
			method.setAccessible(false);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
