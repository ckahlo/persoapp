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
 * @version 1.0, 30.09.2014 12:48:32
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import iso.std.iso_iec._24727.tech.schema.ConnectionHandleType;
import iso.std.iso_iec._24727.tech.schema.DIDAuthenticate;
import iso.std.iso_iec._24727.tech.schema.DIDAuthenticateResponse;
import iso.std.iso_iec._24727.tech.schema.DIDAuthenticationDataType;
import iso.std.iso_iec._24727.tech.schema.EAC1InputType;
import iso.std.iso_iec._24727.tech.schema.EAC1OutputType;
import iso.std.iso_iec._24727.tech.schema.EAC2InputType;
import iso.std.iso_iec._24727.tech.schema.EAC2OutputType;
import iso.std.iso_iec._24727.tech.schema.EACAdditionalInputType;
import iso.std.iso_iec._24727.tech.schema.InputAPDUInfoType;
import iso.std.iso_iec._24727.tech.schema.RequestType;
import iso.std.iso_iec._24727.tech.schema.Transmit;
import iso.std.iso_iec._24727.tech.schema.TransmitResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import de.bund.bsi.ecard.api._1.InitializeFrameworkResponse;
import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.CardHandler;
import de.persoapp.core.client.ECardSession;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.MainViewEventListener;
import de.persoapp.core.util.Hex;
import de.persoapp.core.util.Util;
import de.persoapp.core.util.TLV;
import de.persoapp.core.ws.*;
import de.persoapp.core.ws.engine.WSContainer;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Rico Klimsa, 2014
 */
public class WebServiceTest {
	
	private static WSContainer wsCtx = null;
	private final String	DEFAULT_PIN	= "123456";
	private final String	serviceURL	= "https://eid.services.ageto.net/persoapp/eidtest.jsp";
	private final String	certificatePath = "C:/Users/rklimsa/workspaceEclipseBranch/PersoApp-Core/tests/de/persoapp/core/tests/TestCertificate.cer";
	
	private static Logger logger = Logger.getLogger(WebServiceTest.class.getName());
	private static IMainView	mainView = null;

	private static IFDService ifdservice = null;
	
	/**
	 * Test spy for indirect output
	 */
	private static TestSALService salservice = null;
	private static ManagementService managementservice = null;
	
	private static CardHandler eCardHandler = null;
	private static ECardSession session = null;
	
	/**
	 * The constant to identify {@link EAC1InputType} structures.
	 */
	private static final int EAC_1 = 0x01;
	
	/**
	 * The constant to identify {@link EAC2InputType} structures.
	 */
	private static final int EAC_2 = 0x02;
	
	/**
	 * The constant to identify {@link EACAdditionalInputType}.
	 */
	private static final int EAC_A = 0x03;
	
	/**
	 * The constant to identify unknown authentication protocol data.<br/>
	 * @see DIDAuthenticationDataType
	 */
	public static final int UNKNOWN_AUTH_PROT_DATA = 0xA;
	
	/**
	 * The constant to renew the authentication protocol data in the first
	 * phase of the EAC.
	 */
	public static final int RENEW_DATA_FIRST_PHASE_OF_EAC = 0xB;

	/**
	 * The constant to renew the authentication protocol data in the second
	 * phase of the EAC.
	 */
	public static final int RENEW_DATA_SECOND_PHASE_OF_EAC = 0xC;
	
	/**
	 * The constant to renew the authentication protocol data in the additional
	 * phase of the EAC.
	 */
	public static final int RENEW_DATA_ADDITIONAL_PHASE_OF_EAC = 0xD;	
	
	/**
	 * The constant to delete the authentication protocol data.
	 */
	public static final int DELETE_DATA = 0xE;
	
	/**
	 * Initialize important test objects as singletons.
	 */
	@Before
	public void init(){

		if(mainView==null) {
			mainView = TestMainView.getInstance(DEFAULT_PIN);
			assertNotNull("no main view", mainView);		
		}
		
		if( eCardHandler == null) {
			eCardHandler = new CardHandler(mainView);
			assertNotNull("no card handler", eCardHandler);
			mainView.setEventLister(new MainViewEventListener(eCardHandler, mainView));
		}
		
		if(managementservice==null) {
			managementservice = new ManagementService();
		}
		
		if(salservice==null) {
			salservice = new TestSALService();
		}
		
		if(ifdservice==null) {
			ifdservice = new IFDService();
		}
		
		if(wsCtx==null) {
			wsCtx = new WSContainer();
			assertNotNull("no web service container", wsCtx);
			wsCtx.addService(managementservice);
			wsCtx.addService(salservice);
			wsCtx.addService(ifdservice);
			wsCtx.init(null);
		}
		
		if(session == null) {
			session = new ECardSession(mainView, eCardHandler);
			assertNotNull("no session",session);		
		}
		ECardWorker.init(mainView, wsCtx, eCardHandler);
	}
	
	/**
	 * Function invocation with null as inserted value.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-6, Section 3.2.5 <em>Transmit</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService#transmit(Transmit)} is invoked with <b>null</b>.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link NullPointerException} is thrown.</li>.
	 * </ul>
	 * 
	 * @see IFDService#transmit(Transmit)
	 */
	@Test
	public void testIFDServiceNull_1() {
		wsCtx.getMessageContext().clear();		
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		try{
			ifdservice.transmit(null);
		} catch(final NullPointerException e) {
			logger.log(Level.INFO, "NullPointerException is thrown: "+e.getStackTrace()[0]);
			return;
		} catch(final Throwable t) {
			fail("Unexpected exception: "+t.getStackTrace()[0]);
		}
		fail("No NullPointerException is thrown.");
	}
	
	/**
	 * Function invocation without an {@link ECardSession}.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-6, Section 3.2.5 <em>Transmit</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService#transmit(Transmit)} is invoked with empty
	 * {@link Transmit} and without an {@link ECardSession} in the {@link #wsCtx}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link NullPointerException} is thrown.</li>.
	 * </ul>
	 * 
	 * @see IFDService#transmit(Transmit)
	 * @see Transmit
	 */
	@Test
	public void testIFDServiceNull_2() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), null);
		Transmit parameters = new Transmit();
		try{
			ifdservice.transmit(parameters);
		} catch(final NullPointerException e) {
			logger.log(Level.INFO, "NullPointerException is thrown: "+e.getStackTrace()[0]);
			return;
		} catch(final Throwable t) {
			fail("Unexpected exception: "+t.getStackTrace()[0]);
		}
		fail("No NullPointerException is thrown.");
	}
	
	/**
	 * Function invocation without an output APDU in {@link Transmit}. <br/>
	 * <br/>
	 * <b>References: </b>TR-03112-6, Section 3.2.5 <em>Transmit</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService#transmit(Transmit)} is invoked with empty
	 * {@link Transmit}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link TransmitResponse} is returned with the major result
	 * {@link EcAPIProvider#ECARD_API_RESULT_OK}.</li>.
	 * </ul>
	 * 
	 * @see IFDService#transmit(Transmit)
	 * @see Transmit
	 * @see InputAPDUInfoType
	 */
	@Test
	public void testIFDServiceInvalidParameter_1() {
		wsCtx.getMessageContext().clear();		
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		
		Transmit parameters = new Transmit();
		
		TransmitResponse response = null;

		try{
			response = ifdservice.transmit(parameters);			
		} catch (final Throwable t) {
			fail("Unexpected throwable is thrown: "+t.getStackTrace()[0]);
		}

		assertNotNull("transmit result is null",response);
		assertNotNull("result is null",response.getResult());
		assertNotNull("major result is null",response.getResult().getResultMajor());
		assertEquals("wrong major result",EcAPIProvider.ECARD_API_RESULT_OK,response.getResult().getResultMajor());
	}
	
	/**
	 * Function invocation with an empty apdu.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-6, Section 3.2.5 <em>Transmit</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService#transmit(Transmit)} is invoked with
	 * {@link Transmit} which has an empty apdu.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link TransmitResponse} is returned with the major result
	 * {@link EcAPIProvider#ECARD_API_RESULT_OK}.</li>.
	 * </ul>
	 * 
	 * @see IFDService#transmit(Transmit)
	 * @see Transmit
	 * @see InputAPDUInfoType
	 * @see TransmitResponse
	 */
	@Test
	public void testIFDServiceInvalidParameter_2() {
		wsCtx.getMessageContext().clear();		
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		
		byte[] wrongApdu = new byte[0];
		
		InputAPDUInfoType apdu = new InputAPDUInfoType();		
		apdu.setInputAPDU(wrongApdu);

		Transmit parameters = new Transmit();
		parameters.getInputAPDUInfo().add(apdu);

		TransmitResponse response = null;

		try{
			response = ifdservice.transmit(parameters);			
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}

		assertNotNull("transmit result is null",response);
		assertNotNull("result is null",response.getResult());
		assertNotNull("major result is null",response.getResult().getResultMajor());
		assertEquals("wrong major result",EcAPIProvider.ECARD_API_RESULT_OK,response.getResult().getResultMajor());
	}
	
	
	/**
	 * Function invocation with forbidden apdu.<br/>
	 * <b>References: </b>TR-03112-6, Section 3.2.5 <em>Transmit</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService#transmit(Transmit)} is invoked with
	 * {@link Transmit} and an APDU which is not allowed to be send.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link TransmitResponse} is returned with the major result
	 * {@link EcAPIProvider#ECARD_API_RESULT_OK} and an OutputAPDU
	 * <code>0x6D,0x00</code>.</li>.
	 * </ul>
	 * 
	 * @see IFDService#transmit(Transmit)
	 * @see Transmit
	 * @see InputAPDUInfoType
	 * @see TransmitResponse
	 */
	@Test
	public void testIFDServiceInvalidParameter_3() {
		wsCtx.getMessageContext().clear();		
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		
		byte[] wrongApdu = new byte[1];
		wrongApdu[0] = (byte) 0xff;
		
		InputAPDUInfoType apdu = new InputAPDUInfoType();		
		apdu.setInputAPDU(wrongApdu);

		Transmit parameters = new Transmit();
		parameters.getInputAPDUInfo().add(apdu);
		
		
		TransmitResponse response = null;
		try {			
			response = ifdservice.transmit(parameters);			
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}

		assertNotNull("transmit result is null", response);
		assertEquals("apdu with cla = 0xff is not handled by the implementation", 1,response.getOutputAPDU().size());
		assertEquals("apdu with cla = 0xff is not handled by the implementation", "6D00",Hex.toString(response.getOutputAPDU().get(0)));
	}
		
	/**
	 * Function invocation with an malformed APDU.<br/><br/>
	 * <b>References: </b>TR-03112-6, Section 3.2.5 <em>Transmit</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService#transmit(Transmit)} is invoked with
	 * {@link Transmit} and an malformed APDU.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link IllegalArgumentException} is thrown.</li>
	 * </ul>
	 * 
	 * @see IFDService#transmit(Transmit)
	 * @see Transmit
	 * @see InputAPDUInfoType
	 * @see TransmitResponse
	 */
	@Test
	public void testIFDServiceInvalidParameter_4(){
		wsCtx.getMessageContext().clear();		
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		
		byte[] wrongApdu = new byte[1];
		wrongApdu[0] = (byte) 0x12;
		
		InputAPDUInfoType apdu = new InputAPDUInfoType();		
		apdu.setInputAPDU(wrongApdu);
		
		Transmit parameters = new Transmit();
		parameters.getInputAPDUInfo().add(apdu);
		
		assertNotNull("No transport Provider",eCardHandler.getECard());
		
		try {
			ifdservice.transmit(parameters);		
			eCardHandler = null;
		} catch (final IllegalArgumentException e) {
			logger.log(Level.INFO, "IllegalArgumentException is thrown: "+e.getStackTrace()[0]);
			return;
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}
		
		fail("No IllegalArgumentException is thrown.");
	}
	
	/**
	 * Function invocation with correct parameters.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-6, Section 3.2.5 <em>Transmit</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link IFDService#transmit(Transmit)} is invoked with
	 * {@link Transmit} and an valid APDU.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The APDU is correctly send, which is indicated through the response APDU <code>9000</code>.</li>
	 * </ul>
	 * 
	 * @see IFDService#transmit(Transmit)
	 * @see Transmit
	 * @see InputAPDUInfoType
	 * @see TransmitResponse
	 */
	@Test
	public void testIFDServiceValidParameter_1(){
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		
		short FID = 0x011C;
		byte[] correctApdu = new byte[]{0x00, (byte) 0xA4, 0x02, 0x0C, 0x02, (byte) (FID >> 8), (byte) (FID & 0xFF) };
		
		InputAPDUInfoType apdu = new InputAPDUInfoType();		
		apdu.setInputAPDU(correctApdu);
		
		Transmit parameters = new Transmit();
		parameters.getInputAPDUInfo().add(apdu);
		
		TransmitResponse response = null;
		
		assertNotNull("No transport Provider",eCardHandler.getECard());
		
		try {
			response = ifdservice.transmit(parameters);
			eCardHandler = null;
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}
		assertNotNull("response is null",response);
		assertEquals("apdu not correct", "9000",Hex.toString(response.getOutputAPDU().get(0)));
	}
	
	
	/**
	 * Function invocation without an {@link ECardSession}.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked with
	 * {@link DIDAuthenticate} and without an {@link ECardSession}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link NullPointerException} is thrown.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see ConnectionHandleType
	 */
	@Test
	public void testSALServiceNull_1() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), null);
		salservice.response.clear();
		
		Random sr = new Random();
		byte[] slotHandle = new byte[32];
		
		sr.nextBytes(slotHandle);
		
		ConnectionHandleType cht = new ConnectionHandleType();
		cht.setSlotHandle(slotHandle);
		
		DIDAuthenticate parameters = new DIDAuthenticate();
		parameters.setConnectionHandle(cht);
		
		try{
			salservice.didAuthenticate(parameters);
		} catch (final NullPointerException e) {
			logger.log(Level.INFO, "NullpointerException is thrown: "+e.getStackTrace()[0]);
			return;
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}
		fail("No NullpointerException is thrown.");
	}
	
	/**
	 * <p>
	 * <b>Important: </b>Not handled exceptions in
	 * {@link de.persoapp.core.client.EAC_Info} and {@link WSContainer}
	 * preventing this test from completing and forcing the test suite into a
	 * deadlock until the callback in the {@link ECardWorker} is timed out.
	 * </p>
	 * Function invocation without certificates in the first EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked with
	 * {@link DIDAuthenticate} and without certificates in the {@link EAC1InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The whole authentication process fails.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC1InputType
	 */
	@Test
	public void testSALServiceNull_2() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		//Waiting on callback timeout
		nullList.add("certificate");
		makeNull(nullList, EAC_1);
	}	
	
	/**
	 * <p>
	 * <b>Important: </b>Not handled exceptions in {@link SALService} and
	 * {@link WSContainer} preventing this test from completing and forcing the
	 * test suite into a deadlock until the callback in the {@link ECardWorker}
	 * is timed out.
	 * </p>
	 * Function invocation without certificateDescription in the first EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without certificateDescription in the
	 * {@link EAC1InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The whole authentication process fails.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC1InputType
	 */
	@Test
	public void testSALServiceNull_3() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("certificateDescription");
		makeNull(nullList, EAC_1);
	}
	
	/**
	 * Function invocation without providerInfo in the first EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without providerInfo in the
	 * {@link EAC1InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication process executes and completes normally
	 * because this element is deprecated and can be ignored.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC1InputType
	 */
	@Test
	public void testSALServiceNull_4() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("providerInfo");
		makeNull(nullList, EAC_1,false);
	}
	
	/**
	 * <p>
	 * <b>Important: </b>Not handled exceptions in {@link SALService} and
	 * {@link WSContainer} preventing this test from completing and forcing the
	 * test suite into a deadlock until the callback in the {@link ECardWorker} is
	 * timed out.
	 * </p>
	 * Function invocation without requiredCHAT in the first EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without requiredCHAT in the
	 * {@link EAC1InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>Causes the authentication process and therefore the whole
	 * alternative invocation to fail.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC1InputType
	 */
	@Test
	public void testSALServiceNull_5() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("requiredCHAT");
		makeNull(nullList, EAC_1);
	}	
	
	
	/**
	 * Function invocation without optionalCHAT in the first EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without optionalCHAT in the
	 * {@link EAC1InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication process executes and completes normally
	 * because this element is not required and can be null.</li>
	 * </ul>
	 *
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC1InputType
	 */
	@Test
	public void testSALServiceNull_6() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("optionalCHAT");
		makeNull(nullList, EAC_1,false);
	}	
	
	
	/**
	 * <p>
	 * <b>Important: </b>Not handled exceptions in
	 * {@link de.persoapp.core.util.ArrayTool} and {@link WSContainer}
	 * preventing this test from completing and forcing the test suite into a
	 * deadlock until the callback in the {@link ECardWorker} is timed out.
	 * </p>
	 * Function invocation without authenticatedAuxiliaryData in the first EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without authenticatedAuxiliaryData in the
	 * {@link EAC1InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>Causes the authentication process and therefore the whole
	 * alternative invocation to fail.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC1InputType
	 */
	@Test
	public void testSALServiceNull_7() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("authenticatedAuxiliaryData");
		makeNull(nullList, EAC_1);
	}
	
	
	/**
	 * Function invocation without transactionInfo in the first EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without transactionInfo in the
	 * {@link EAC1InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li> The authentication process executes and completes normally
	 * because this element is not required and can be null.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC1InputType
	 */
	@Test
	public void testSALServiceNull_8() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("transactionInfo");
		makeNull(nullList, EAC_1,false);
	}
	
	/**
	 * Function invocation without certificates in the second EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without certificate in the
	 * {@link EAC2InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li> The authentication process fails.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC2InputType
	 */	
	@Test
	public void testSALServiceNull_9() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("certificate");
		makeNull(nullList,EAC_2,false);
	}
	
	/**
	 * <p>
	 * <b>Important: </b>Not handled exceptions in {@link TLV} and
	 * {@link WSContainer} preventing this test from completing and forcing the
	 * test suite into a deadlock until the callback in the {@link ECardWorker}
	 * is timed out.
	 * </p>
	 * Function invocation without an ephemeralPublicKey in the second
	 * EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without ephemeralPublicKey in the
	 * {@link EAC2InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication process fails and can not complete in a normal
	 * way.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC2InputType
	 */	
	@Test
	public void testSALServiceNull_10() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("ephemeralPublicKey");
		makeNull(nullList,EAC_2);
	}	
	
	/**
	 * Function invocation without the signature in the second EAC-phase.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and without the signature in the
	 * {@link EAC2InputType}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication process executes and completes normally
	 * because this element is not required.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 * @see EAC2InputType
	 */		
	@Test
	public void testSALServiceNull_11() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		
		ArrayList<String> nullList = new ArrayList<String>();
		nullList.add("signature");
		makeNull(nullList,EAC_2,false);
	}
	
	/**
	 * Function invocation without authentication protocol data.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The function completes and returns null.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see ConnectionHandleType
	 * @see DIDAuthenticateResponse
	 */
	@Test
	public void testSALServiceInvalidParameter_1() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		salservice.configFlag = DELETE_DATA;
		
		try{
			makeNull(null, EAC_1);
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}
		
		HashMap<DIDAuthenticate,DIDAuthenticateResponse> response = salservice.response;
		
		assertEquals(0, response.size());
	}
	
	/**
	 * <p>
	 * <b>Important: </b>The thread does not return into the test case and thus
	 * the test case remains in a deadlock until the {@link ECardWorker} times
	 * out.
	 * </p>
	 * Function invocation with unknown authentication protocol data.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and {@link DIDAuthenticationDataType} as
	 * unknown authentication protocol data.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The function returns an {@link DIDAuthenticateResponse} with
	 * {@link EcAPIProvider#ECARD_API_RESULT_ERROR} as major result.</li>
	 * </ul>
	 * 
	 * @see TestSALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see ConnectionHandleType
	 * @see DIDAuthenticationDataType
	 * @see DIDAuthenticateResponse
	 */
	@Test
	public void testSALServiceInvalidParameter_2() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		salservice.configFlag = UNKNOWN_AUTH_PROT_DATA;
		
		makeAlternativeInvocationFail();
		
		HashMap<DIDAuthenticate,DIDAuthenticateResponse> response = salservice.response;
		for(Entry<DIDAuthenticate,DIDAuthenticateResponse> entry: response.entrySet())
		{
			assertNotNull("parameter is null", entry.getKey());
			
			assertNotNull("Response is null.",entry.getValue());
			assertNotNull("no result", entry.getValue().getResult());
			assertEquals("wrong result",entry.getValue().getResult().getResultMajor(),EcAPIProvider.ECARD_API_RESULT_ERROR);			
		}
	}	
	
	
	/**
	 * <p>
	 * <b>Important: </b>Not handled exceptions in {@link TLV} and
	 * {@link WSContainer} preventing this test from completing and forcing the
	 * test suite in a deadlock. The PersoApp terminates and the application
	 * pointer does not return to the test case.
	 * </p>
	 * Function invocation with missing authentication data in the first
	 * EAC-Phase. <br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and the {@link EAC1InputType} is missing all
	 * data.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication process does not complete normally.</li>
	 * </ul>
	 * 
	 * @see SALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 */
	@Test
	public void testSALServiceInvalidParameter_3() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		salservice.configFlag = RENEW_DATA_FIRST_PHASE_OF_EAC;
		
		makeAlternativeInvocationFail();
	}	
	
	/**
	 * <p>
	 * <b>Important: </b>Not handled exceptions in {@link TLV} and
	 * {@link WSContainer} preventing this test from completing and forcing the
	 * test suite in a deadlock. The PersoApp terminates and the application
	 * pointer does not return to the test case.
	 * </p>
	 * Function invocation with missing authentication data in the second
	 * EAC-Phase. <br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and the {@link EAC2InputType} is missing all
	 * data.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication process does not complete normally.</li>
	 * </ul>
	 * 
	 * @see SALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 */
	@Test
	public void testSALServiceInvalidParameter_4() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
		salservice.configFlag = RENEW_DATA_SECOND_PHASE_OF_EAC;
		
		makeAlternativeInvocationFail();
	}
	

	
	/**
	 * Function invocation with valid parameters.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-7, Section 3.6.4 <em>Overview of EAC protocol sequence</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link SALService#didAuthenticate(DIDAuthenticate)} is invoked
	 * with {@link DIDAuthenticate} and the correct authentication protocol data
	 * according to the EAC-phases.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The authentication completes normally and the alternative call
	 * succeeds without an error.</li>
	 * </ul>
	 * 
	 * @see SALService#didAuthenticate(DIDAuthenticate)
	 * @see DIDAuthenticate
	 * @see DIDAuthenticateResponse
	 */
	@Test
	public void testSALServiceValidParameter_1() {
		wsCtx.getMessageContext().clear();
		wsCtx.getMessageContext().put(ECardSession.class.getName(), session);
		salservice.response.clear();
			makeAlternativeInvocationSuccess();
			
			HashMap<DIDAuthenticate,DIDAuthenticateResponse> response = salservice.response;
			
			assertFalse("no response from didAuthenticate",response.isEmpty());
			assertTrue("EAC-authentication not correctly processed",response.size()>1);
			for(Entry<DIDAuthenticate,DIDAuthenticateResponse> entry : response.entrySet())
			{
				assertNotNull("no authentication protocol data",entry.getKey().getAuthenticationProtocolData());
				assertNotNull("no authentication protocol return data",entry.getValue().getAuthenticationProtocolData());
				
				if(entry.getKey().getAuthenticationProtocolData() instanceof EAC1InputType){//EAC-phase 1 - Extended PACE - Protocol
				
					assertNotNull("no certificate",((EAC1InputType)entry.getKey().getAuthenticationProtocolData()).getCertificate());
					assertNotNull("no certificate description",((EAC1InputType)entry.getKey().getAuthenticationProtocolData()).getCertificateDescription());
					
					assertTrue("wrong authentication protocol return data", entry.getValue().getAuthenticationProtocolData() instanceof EAC1OutputType);
				
					assertNotNull("no Certificate Holder Authorization Template",((EAC1OutputType)entry.getValue().getAuthenticationProtocolData()).getCertificateHolderAuthorizationTemplate());
					assertNotNull("no Certification Authority Reference",((EAC1OutputType)entry.getValue().getAuthenticationProtocolData()).getCertificationAuthorityReference());
					assertNotNull("no EF.CardAccess",((EAC1OutputType)entry.getValue().getAuthenticationProtocolData()).getEFCardAccess());
					assertNotNull("no IDPICC",((EAC1OutputType)entry.getValue().getAuthenticationProtocolData()).getIDPICC());
					assertNotNull("no Challange",((EAC1OutputType)entry.getValue().getAuthenticationProtocolData()).getChallenge());
					
				}else if(entry.getKey().getAuthenticationProtocolData() instanceof EAC2InputType){//EAC-phase 2 - combination of Terminal and Chip Authentication
					assertNotNull("no ephemeral public key", ((EAC2InputType)entry.getKey().getAuthenticationProtocolData()).getEphemeralPublicKey());
					
					assertTrue("wrong authentication protocol return data", entry.getValue().getAuthenticationProtocolData() instanceof EAC2OutputType);
					
					assertNotNull("no EFCardSecurity",((EAC2OutputType)entry.getValue().getAuthenticationProtocolData()).getEFCardSecurity());
					assertNotNull("no Authentication Token",((EAC2OutputType)entry.getValue().getAuthenticationProtocolData()).getAuthenticationToken());
					assertNotNull("no Nonce",((EAC2OutputType)entry.getValue().getAuthenticationProtocolData()).getNonce());
					
					if(((EAC2InputType)entry.getKey().getAuthenticationProtocolData()).getSignature()==null){//EAC-phase 2b - conditional additional message with signature 
						assertNotNull("No Signature and no Challange from the PICC",((EAC2OutputType)entry.getValue().getAuthenticationProtocolData()).getChallenge());	
					}
					
				}else {
					assertTrue("Unknown parameter",entry.getKey().getAuthenticationProtocolData() instanceof EACAdditionalInputType);
					assertNotNull("no signature",((EACAdditionalInputType)entry.getValue().getAuthenticationProtocolData()).getSignature());
				}
				assertNotNull("no result",entry.getValue().getResult());
				assertNotNull("no major result",entry.getValue().getResult().getResultMajor());
				assertEquals("no correct major result", EcAPIProvider.ECARD_API_RESULT_OK, entry.getValue().getResult().getResultMajor());
			}
	}
	
	/**
	 * Function invocation with null as inserted value.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-3, Section 3.1.1 <em>InitializeFramework</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link ManagementService#initializeFramework(RequestType)} is
	 * invoked with <b>null</b> as inserted value.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>An {@link NullPointerException} is thrown.</li>
	 * </ul>
	 * 
	 * @see ManagementService#initializeFramework(RequestType)
	 */
	@Test
	public void testManagementServiceNull_1() {
		wsCtx.getMessageContext().clear();
		
		try{
			managementservice.initializeFramework(null);
		} catch(final NullPointerException e) {
			logger.log(Level.INFO, "NullPointerException is thrown: "+e.getStackTrace()[0]);
			return;
		} catch(final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}
		fail("No NullPointerException is thrown.");	//fail if no exception is thrown.
	}
	
	/**
	 * Function invocation without an requestID.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-3, Section 3.1.1 <em>InitializeFramework</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link ManagementService#initializeFramework(RequestType)} is
	 * invoked with {@link RequestType} which is missing an requestID.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The function completes normally.</li>
	 * </ul>
	 * 
	 * @see ManagementService#initializeFramework(RequestType)
	 * @see RequestType
	 * @see InitializeFrameworkResponse
	 */	
	@Test
	public void testManagementServiceInvalidParameter_1() {
		wsCtx.getMessageContext().clear();
		
		RequestType parameters = new RequestType();
		InitializeFrameworkResponse ifr = null;
		try{
			ifr = managementservice.initializeFramework(parameters);
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}
		
		assertNotNull("Response is null.",ifr);
	}
	
	/**
	 * Function invocation with correct parameters.<br/>
	 * <br/>
	 * <b>References: </b>TR-03112-3, Section 3.1.1 <em>InitializeFramework</em><br/>
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>The {@link ManagementService#initializeFramework(RequestType)} is
	 * invoked with {@link RequestType} which has valid parameters.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The function completes normally.</li>
	 * </ul>
	 * 
	 * @see ManagementService#initializeFramework(RequestType)
	 * @see RequestType
	 * @see InitializeFrameworkResponse
	 */
	@Test
	public void testManagementServiceValidParameter_1() {
		wsCtx.getMessageContext().clear();
		
		RequestType parameters = new RequestType();
		
		Random sr = new Random();
		byte[] reqID = new byte[32];
		sr.nextBytes(reqID);
		parameters.setRequestID(Hex.toString(reqID));
		
		InitializeFrameworkResponse ifr = null;
		
		try{
			ifr = managementservice.initializeFramework(parameters);
		} catch (final Throwable t) {
			fail("Unexpected Throwable is thrown: "+t.getStackTrace()[0]);
		}
		assertNotNull("Response is null",ifr);
	}
	
	/**
	 * Executes the alternative invocation and sets the given arguments
	 * in the authentication protocol data to null.
	 * 
	 * @param nullList
	 *            - List of arguments.
	 * @param flag
	 *            - Flag to distinguish between {@link EAC1InputType} and
	 *            {@link EAC2InputType}.
	 *            
	 * @see {@link SALService#didAuthenticate(DIDAuthenticate)
	 * @see {@link DIDAuthenticate}
	 * @see {@link EAC1InputType}
	 * @see {@link EAC2InputType}
	 */
	public void makeNull(ArrayList<String> nullList, int flag) {
		makeNull(nullList,flag,true);
	}
	
	/**
	 * Does the regular alternative invocation, in german <em>Alternativer Aufruf</em>,
	 * with the eID-Client.
	 */
	public void makeAlternativeInvocationSuccess() {
		makeNull(null,0,false);
	}
	
	public void makeAlternativeInvocationFail() {
		makeNull(null,0,true);
	}
	
	/**
	 * Executes the alternative invocation and sets the given arguments 
	 * in the authentication protocol data to null.
	 * 
	 * @param nullList
	 *            - List of arguments.
	 * @param flag
	 *            - Flag to distinguish between {@link EAC1InputType} and
	 *            {@link EAC2InputType}.
	 * @param processFailed
	 * 			  - Indicates that the missing attribute causes the alternative 
	 * 				invocation to fail.
	 *            
	 * @see {@link SALService#didAuthenticate(DIDAuthenticate)
	 * @see {@link DIDAuthenticate}
	 * @see {@link EAC1InputType}
	 * @see {@link EAC2InputType}
	 */
	public void makeNull(ArrayList<String> nullList, int flag, boolean processFailed) {
		
		URL tcTokenURL = null;
		
		try {
			tcTokenURL = new URL(serviceURL);
		} catch (final MalformedURLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		try {
			
			switch(flag){
				//Change EAC1InputType
				case EAC_1: {
					salservice.nullListEAC1 = nullList;
					break;
				}
				//Change EAC2InputType
				case EAC_2: {
					salservice.nullListEAC2 = nullList;
					break;
				}
				//Change EACAdditionalInputType
				case EAC_A: {
					salservice.nullListEAC2b = nullList;
					break;
				}
				//Do nothing
				default: break;
			}
			
			String refreshURL = ECardWorker.start(tcTokenURL);
			assertNotNull("no refresh URL", refreshURL);

			System.out.println("refreshURL: " + refreshURL);
			if(processFailed){
				assertTrue("process is succeded", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
			} else {
				assertNotNull("no refresh URL", refreshURL);

				System.out.println("refreshURL: " + refreshURL);
				assertFalse("process failed", refreshURL.toLowerCase().indexOf("resultmajor=ok") < 0);
				final URL refresh = new URL(refreshURL);
				final HttpURLConnection uc = (HttpsURLConnection) Util.openURL(refresh);
				uc.setInstanceFollowRedirects(true);
				final Object content = uc.getContent();
				System.out.println("HTTP Response " + uc.getResponseCode() + " " + uc.getResponseMessage());
				if (content instanceof InputStream) {
					final Scanner scanner = new Scanner((InputStream) content, "UTF-8").useDelimiter("\\A");
					System.out.println(scanner.next());
					scanner.close();
				} else {
					System.out.println(content);
				}
			}

			
		} catch(final NullPointerException e) {
			logger.log(Level.INFO, "NullPointerException occured: "+e.getStackTrace()[0]);
			return;
		}
		catch (final CertificateException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final GeneralSecurityException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
	
	public DIDAuthenticate createDIDAuthenticate() {
		DIDAuthenticate result = new DIDAuthenticate();
		
		byte[] slotHandle = new byte[]{     40,
			      48,
			      1,
			      -62,
			      -70,
			      -59,
			      -66,
			      -63,
			      99,
			      102,
			      -13,
			      121,
			      -70,
			      -5,
			      7,
			      37,
			      105,
			      14,
			      93,
			      -82,
			      12,
			      -72,
			      2,
			      -35,
			      -66,
			      111,
			      -27,
			      -37,
			      44,
			      -12,
			      -61,
			      -100
};
		
		ConnectionHandleType con = new ConnectionHandleType();
		con.setSlotHandle(slotHandle);
		result.setConnectionHandle(con);
		result.setDIDName("PIN");
		
		EAC1InputType eac1 = new EAC1InputType();
		
		byte[] cert1 = new byte[]{
	            127,
	            33,
	            -126,
	            1,
	            74,
	            127,
	            78,
	            -126,
	            1,
	            2,
	            95,
	            41,
	            1,
	            0,
	            66,
	            16,
	            68,
	            69,
	            68,
	            86,
	            84,
	            73,
	            68,
	            65,
	            71,
	            84,
	            79,
	            48,
	            48,
	            48,
	            49,
	            56,
	            127,
	            73,
	            79,
	            6,
	            10,
	            4,
	            0,
	            127,
	            0,
	            7,
	            2,
	            2,
	            2,
	            2,
	            3,
	            -122,
	            65,
	            4,
	            77,
	            118,
	            -73,
	            -10,
	            47,
	            -126,
	            99,
	            46,
	            54,
	            -19,
	            -33,
	            124,
	            -21,
	            -118,
	            110,
	            -99,
	            -50,
	            25,
	            110,
	            103,
	            56,
	            46,
	            -128,
	            -5,
	            -11,
	            74,
	            -66,
	            -91,
	            -70,
	            -4,
	            -118,
	            12,
	            91,
	            112,
	            -109,
	            -33,
	            -122,
	            -97,
	            123,
	            -16,
	            78,
	            -78,
	            108,
	            -21,
	            28,
	            -89,
	            -75,
	            71,
	            -58,
	            70,
	            60,
	            98,
	            -68,
	            95,
	            -51,
	            36,
	            124,
	            -74,
	            92,
	            66,
	            114,
	            -118,
	            -68,
	            -33,
	            95,
	            32,
	            16,
	            68,
	            69,
	            48,
	            48,
	            48,
	            48,
	            48,
	            48,
	            48,
	            48,
	            50,
	            48,
	            48,
	            48,
	            48,
	            49,
	            127,
	            76,
	            18,
	            6,
	            9,
	            4,
	            0,
	            127,
	            0,
	            7,
	            3,
	            1,
	            2,
	            2,
	            83,
	            5,
	            0,
	            5,
	            19,
	            -1,
	            -121,
	            95,
	            37,
	            6,
	            1,
	            4,
	            1,
	            0,
	            0,
	            1,
	            95,
	            36,
	            6,
	            1,
	            4,
	            1,
	            0,
	            2,
	            9,
	            101,
	            94,
	            115,
	            45,
	            6,
	            9,
	            4,
	            0,
	            127,
	            0,
	            7,
	            3,
	            1,
	            3,
	            1,
	            -128,
	            32,
	            103,
	            80,
	            -63,
	            -106,
	            -119,
	            -128,
	            -76,
	            -67,
	            -83,
	            -83,
	            -55,
	            10,
	            31,
	            -118,
	            113,
	            83,
	            113,
	            69,
	            22,
	            19,
	            -68,
	            -81,
	            -128,
	            126,
	            107,
	            26,
	            -61,
	            67,
	            77,
	            122,
	            -109,
	            -39,
	            115,
	            45,
	            6,
	            9,
	            4,
	            0,
	            127,
	            0,
	            7,
	            3,
	            1,
	            3,
	            2,
	            -128,
	            32,
	            87,
	            74,
	            -27,
	            -78,
	            -64,
	            -61,
	            -37,
	            44,
	            92,
	            -120,
	            91,
	            -69,
	            -20,
	            7,
	            -14,
	            1,
	            -56,
	            15,
	            66,
	            46,
	            121,
	            3,
	            -4,
	            124,
	            -57,
	            -119,
	            24,
	            -57,
	            3,
	            127,
	            45,
	            -114,
	            95,
	            55,
	            64,
	            -113,
	            114,
	            -110,
	            -109,
	            93,
	            120,
	            -102,
	            83,
	            98,
	            106,
	            19,
	            2,
	            106,
	            -72,
	            37,
	            44,
	            -90,
	            20,
	            -92,
	            23,
	            -35,
	            105,
	            -40,
	            -110,
	            -44,
	            56,
	            115,
	            32,
	            -47,
	            -44,
	            -70,
	            58,
	            -111,
	            37,
	            120,
	            59,
	            -18,
	            -97,
	            18,
	            96,
	            -9,
	            -56,
	            6,
	            92,
	            -127,
	            -69,
	            20,
	            52,
	            89,
	            97,
	            -7,
	            114,
	            26,
	            -50,
	            7,
	            -74,
	            37,
	            -94,
	            -24,
	            66,
	            75,
	            67,
	            -63,
	            -40
		};
		
		byte[] cert2 = new byte[]{
	            127,
	            33,
	            -127,
	            -25,
	            127,
	            78,
	            -127,
	            -96,
	            95,
	            41,
	            1,
	            0,
	            66,
	            14,
	            68,
	            69,
	            84,
	            69,
	            83,
	            84,
	            101,
	            73,
	            68,
	            48,
	            48,
	            48,
	            48,
	            52,
	            127,
	            73,
	            79,
	            6,
	            10,
	            4,
	            0,
	            127,
	            0,
	            7,
	            2,
	            2,
	            2,
	            2,
	            3,
	            -122,
	            65,
	            4,
	            31,
	            -109,
	            -22,
	            114,
	            5,
	            98,
	            -43,
	            -57,
	            82,
	            -54,
	            78,
	            86,
	            33,
	            -41,
	            0,
	            -121,
	            11,
	            -43,
	            1,
	            25,
	            -67,
	            23,
	            -1,
	            76,
	            -88,
	            -85,
	            85,
	            4,
	            -65,
	            -68,
	            77,
	            -102,
	            124,
	            111,
	            -6,
	            118,
	            95,
	            86,
	            -49,
	            -40,
	            114,
	            -27,
	            -10,
	            -13,
	            79,
	            -38,
	            -100,
	            -83,
	            -22,
	            113,
	            17,
	            -53,
	            16,
	            -95,
	            -48,
	            39,
	            79,
	            119,
	            29,
	            -10,
	            -125,
	            98,
	            -6,
	            115,
	            95,
	            32,
	            16,
	            68,
	            69,
	            68,
	            86,
	            84,
	            73,
	            68,
	            65,
	            71,
	            84,
	            79,
	            48,
	            48,
	            48,
	            49,
	            56,
	            127,
	            76,
	            18,
	            6,
	            9,
	            4,
	            0,
	            127,
	            0,
	            7,
	            3,
	            1,
	            2,
	            2,
	            83,
	            5,
	            64,
	            5,
	            19,
	            -1,
	            -121,
	            95,
	            37,
	            6,
	            1,
	            4,
	            1,
	            0,
	            0,
	            1,
	            95,
	            36,
	            6,
	            1,
	            4,
	            1,
	            2,
	            3,
	            0,
	            95,
	            55,
	            64,
	            -98,
	            -125,
	            8,
	            66,
	            -74,
	            36,
	            55,
	            2,
	            -3,
	            44,
	            72,
	            -5,
	            -34,
	            27,
	            109,
	            92,
	            -23,
	            55,
	            40,
	            84,
	            -58,
	            -45,
	            -119,
	            -90,
	            29,
	            49,
	            -17,
	            26,
	            -47,
	            118,
	            -70,
	            -117,
	            67,
	            101,
	            -34,
	            -5,
	            -95,
	            34,
	            87,
	            -4,
	            13,
	            74,
	            117,
	            -88,
	            -59,
	            112,
	            -85,
	            -100,
	            4,
	            -101,
	            -70,
	            12,
	            120,
	            56,
	            -74,
	            96,
	            -76,
	            102,
	            112,
	            122,
	            -101,
	            -61,
	            9,
	            -66	
				
		};
		
		byte[] cert3 = new byte[]{
	            127,
	            33,
	            -126,
	            1,
	            -74,
	            127,
	            78,
	            -126,
	            1,
	            110,
	            95,
	            41,
	            1,
	            0,
	            66,
	            14,
	            68,
	            69,
	            84,
	            69,
	            83,
	            84,
	            101,
	            73,
	            68,
	            48,
	            48,
	            48,
	            48,
	            49,
	            127,
	            73,
	            -126,
	            1,
	            29,
	            6,
	            10,
	            4,
	            0,
	            127,
	            0,
	            7,
	            2,
	            2,
	            2,
	            2,
	            3,
	            -127,
	            32,
	            -87,
	            -5,
	            87,
	            -37,
	            -95,
	            -18,
	            -87,
	            -68,
	            62,
	            102,
	            10,
	            -112,
	            -99,
	            -125,
	            -115,
	            114,
	            110,
	            59,
	            -10,
	            35,
	            -43,
	            38,
	            32,
	            40,
	            32,
	            19,
	            72,
	            29,
	            31,
	            110,
	            83,
	            119,
	            -126,
	            32,
	            125,
	            90,
	            9,
	            117,
	            -4,
	            44,
	            48,
	            87,
	            -18,
	            -10,
	            117,
	            48,
	            65,
	            122,
	            -1,
	            -25,
	            -5,
	            -128,
	            85,
	            -63,
	            38,
	            -36,
	            92,
	            108,
	            -23,
	            74,
	            75,
	            68,
	            -13,
	            48,
	            -75,
	            -39,
	            -125,
	            32,
	            38,
	            -36,
	            92,
	            108,
	            -23,
	            74,
	            75,
	            68,
	            -13,
	            48,
	            -75,
	            -39,
	            -69,
	            -41,
	            124,
	            -65,
	            -107,
	            -124,
	            22,
	            41,
	            92,
	            -9,
	            -31,
	            -50,
	            107,
	            -52,
	            -36,
	            24,
	            -1,
	            -116,
	            7,
	            -74,
	            -124,
	            65,
	            4,
	            -117,
	            -46,
	            -82,
	            -71,
	            -53,
	            126,
	            87,
	            -53,
	            44,
	            75,
	            72,
	            47,
	            -4,
	            -127,
	            -73,
	            -81,
	            -71,
	            -34,
	            39,
	            -31,
	            -29,
	            -67,
	            35,
	            -62,
	            58,
	            68,
	            83,
	            -67,
	            -102,
	            -50,
	            50,
	            98,
	            84,
	            126,
	            -8,
	            53,
	            -61,
	            -38,
	            -60,
	            -3,
	            -105,
	            -8,
	            70,
	            26,
	            20,
	            97,
	            29,
	            -55,
	            -62,
	            119,
	            69,
	            19,
	            45,
	            -19,
	            -114,
	            84,
	            92,
	            29,
	            84,
	            -57,
	            47,
	            4,
	            105,
	            -105,
	            -123,
	            32,
	            -87,
	            -5,
	            87,
	            -37,
	            -95,
	            -18,
	            -87,
	            -68,
	            62,
	            102,
	            10,
	            -112,
	            -99,
	            -125,
	            -115,
	            113,
	            -116,
	            57,
	            122,
	            -93,
	            -75,
	            97,
	            -90,
	            -9,
	            -112,
	            30,
	            14,
	            -126,
	            -105,
	            72,
	            86,
	            -89,
	            -122,
	            65,
	            4,
	            9,
	            110,
	            -75,
	            -117,
	            -3,
	            -122,
	            37,
	            34,
	            56,
	            -20,
	            38,
	            82,
	            24,
	            92,
	            67,
	            -61,
	            -91,
	            108,
	            50,
	            6,
	            -127,
	            -94,
	            30,
	            55,
	            -88,
	            -26,
	            -99,
	            -36,
	            56,
	            124,
	            12,
	            95,
	            85,
	            19,
	            -123,
	            110,
	            -2,
	            47,
	            -36,
	            101,
	            110,
	            96,
	            72,
	            -109,
	            33,
	            46,
	            41,
	            68,
	            -101,
	            54,
	            94,
	            48,
	            70,
	            5,
	            -84,
	            84,
	            19,
	            -25,
	            91,
	            -29,
	            30,
	            100,
	            31,
	            18,
	            -121,
	            1,
	            1,
	            95,
	            32,
	            14,
	            68,
	            69,
	            84,
	            69,
	            83,
	            84,
	            101,
	            73,
	            68,
	            48,
	            48,
	            48,
	            48,
	            50,
	            127,
	            76,
	            18,
	            6,
	            9,
	            4,
	            0,
	            127,
	            0,
	            7,
	            3,
	            1,
	            2,
	            2,
	            83,
	            5,
	            -2,
	            15,
	            1,
	            -1,
	            -1,
	            95,
	            37,
	            6,
	            1,
	            0,
	            0,
	            9,
	            2,
	            1,
	            95,
	            36,
	            6,
	            1,
	            3,
	            0,
	            9,
	            2,
	            1,
	            95,
	            55,
	            64,
	            20,
	            17,
	            32,
	            -96,
	            -3,
	            -4,
	            1,
	            26,
	            82,
	            -13,
	            -9,
	            43,
	            56,
	            122,
	            61,
	            -57,
	            -84,
	            -88,
	            -117,
	            72,
	            104,
	            -43,
	            -82,
	            -105,
	            65,
	            120,
	            11,
	            111,
	            -8,
	            -96,
	            -76,
	            -98,
	            95,
	            85,
	            22,
	            -102,
	            45,
	            41,
	            -114,
	            -11,
	            -49,
	            -107,
	            -109,
	            93,
	            -54,
	            12,
	            61,
	            -13,
	            -23,
	            -44,
	            45,
	            -60,
	            95,
	            116,
	            -14,
	            6,
	            99,
	            23,
	            21,
	            73,
	            97,
	            -26,
	            -57,
	            70				
		};
		
		byte[] cert4 = new byte[] {
	            127,
	            33,
	            -126,
	            1,
	            -74,
	            127,
	            78,
	            -126,
	            1,
	            110,
	            95,
	            41,
	            1,
	            0,
	            66,
	            14,
	            68,
	            69,
	            84,
	            69,
	            83,
	            84,
	            101,
	            73,
	            68,
	            48,
	            48,
	            48,
	            48,
	            50,
	            127,
	            73,
	            -126,
	            1,
	            29,
	            6,
	            10,
	            4,
	            0,
	            127,
	            0,
	            7,
	            2,
	            2,
	            2,
	            2,
	            3,
	            -127,
	            32,
	            -87,
	            -5,
	            87,
	            -37,
	            -95,
	            -18,
	            -87,
	            -68,
	            62,
	            102,
	            10,
	            -112,
	            -99,
	            -125,
	            -115,
	            114,
	            110,
	            59,
	            -10,
	            35,
	            -43,
	            38,
	            32,
	            40,
	            32,
	            19,
	            72,
	            29,
	            31,
	            110,
	            83,
	            119,
	            -126,
	            32,
	            125,
	            90,
	            9,
	            117,
	            -4,
	            44,
	            48,
	            87,
	            -18,
	            -10,
	            117,
	            48,
	            65,
	            122,
	            -1,
	            -25,
	            -5,
	            -128,
	            85,
	            -63,
	            38,
	            -36,
	            92,
	            108,
	            -23,
	            74,
	            75,
	            68,
	            -13,
	            48,
	            -75,
	            -39,
	            -125,
	            32,
	            38,
	            -36,
	            92,
	            108,
	            -23,
	            74,
	            75,
	            68,
	            -13,
	            48,
	            -75,
	            -39,
	            -69,
	            -41,
	            124,
	            -65,
	            -107,
	            -124,
	            22,
	            41,
	            92,
	            -9,
	            -31,
	            -50,
	            107,
	            -52,
	            -36,
	            24,
	            -1,
	            -116,
	            7,
	            -74,
	            -124,
	            65,
	            4,
	            -117,
	            -46,
	            -82,
	            -71,
	            -53,
	            126,
	            87,
	            -53,
	            44,
	            75,
	            72,
	            47,
	            -4,
	            -127,
	            -73,
	            -81,
	            -71,
	            -34,
	            39,
	            -31,
	            -29,
	            -67,
	            35,
	            -62,
	            58,
	            68,
	            83,
	            -67,
	            -102,
	            -50,
	            50,
	            98,
	            84,
	            126,
	            -8,
	            53,
	            -61,
	            -38,
	            -60,
	            -3,
	            -105,
	            -8,
	            70,
	            26,
	            20,
	            97,
	            29,
	            -55,
	            -62,
	            119,
	            69,
	            19,
	            45,
	            -19,
	            -114,
	            84,
	            92,
	            29,
	            84,
	            -57,
	            47,
	            4,
	            105,
	            -105,
	            -123,
	            32,
	            -87,
	            -5,
	            87,
	            -37,
	            -95,
	            -18,
	            -87,
	            -68,
	            62,
	            102,
	            10,
	            -112,
	            -99,
	            -125,
	            -115,
	            113,
	            -116,
	            57,
	            122,
	            -93,
	            -75,
	            97,
	            -90,
	            -9,
	            -112,
	            30,
	            14,
	            -126,
	            -105,
	            72,
	            86,
	            -89,
	            -122,
	            65,
	            4,
	            116,
	            -1,
	            99,
	            -85,
	            -125,
	            -116,
	            115,
	            -61,
	            3,
	            -84,
	            0,
	            61,
	            -2,
	            -23,
	            92,
	            -8,
	            -65,
	            85,
	            -7,
	            30,
	            -113,
	            -21,
	            -53,
	            115,
	            -107,
	            -39,
	            66,
	            3,
	            110,
	            71,
	            -49,
	            24,
	            69,
	            -20,
	            120,
	            110,
	            -55,
	            91,
	            -76,
	            83,
	            -86,
	            -62,
	            -120,
	            -83,
	            2,
	            59,
	            96,
	            103,
	            -111,
	            60,
	            -7,
	            -74,
	            63,
	            -112,
	            -113,
	            73,
	            48,
	            78,
	            92,
	            -4,
	            -117,
	            48,
	            80,
	            -35,
	            -121,
	            1,
	            1,
	            95,
	            32,
	            14,
	            68,
	            69,
	            84,
	            69,
	            83,
	            84,
	            101,
	            73,
	            68,
	            48,
	            48,
	            48,
	            48,
	            52,
	            127,
	            76,
	            18,
	            6,
	            9,
	            4,
	            0,
	            127,
	            0,
	            7,
	            3,
	            1,
	            2,
	            2,
	            83,
	            5,
	            -4,
	            15,
	            19,
	            -1,
	            -1,
	            95,
	            37,
	            6,
	            1,
	            2,
	            0,
	            5,
	            1,
	            1,
	            95,
	            36,
	            6,
	            1,
	            5,
	            0,
	            5,
	            1,
	            1,
	            95,
	            55,
	            64,
	            92,
	            3,
	            90,
	            6,
	            17,
	            -74,
	            -59,
	            -113,
	            11,
	            82,
	            97,
	            -3,
	            -48,
	            9,
	            -34,
	            -54,
	            -73,
	            -36,
	            122,
	            121,
	            72,
	            45,
	            82,
	            72,
	            -52,
	            -95,
	            25,
	            5,
	            -101,
	            125,
	            -126,
	            -78,
	            21,
	            124,
	            -16,
	            -60,
	            -92,
	            -103,
	            -68,
	            -12,
	            65,
	            -17,
	            -35,
	            53,
	            -30,
	            -108,
	            -91,
	            -116,
	            10,
	            -15,
	            -102,
	            52,
	            -96,
	            118,
	            33,
	            89,
	            83,
	            50,
	            -123,
	            -84,
	            -15,
	            112,
	            -91,
	            5				
		};
		
		eac1.getCertificate().add(cert1);
		eac1.getCertificate().add(cert2);
		eac1.getCertificate().add(cert3);
		eac1.getCertificate().add(cert4);
		
		byte[] certDesc = new byte[]{
	            48,
	            -126,
	            2,
	            -98,
	            6,
	            10,
	            4,
	            0,
	            127,
	            0,
	            7,
	            3,
	            1,
	            3,
	            1,
	            1,
	            -95,
	            20,
	            12,
	            18,
	            65,
	            71,
	            69,
	            84,
	            79,
	            32,
	            83,
	            101,
	            114,
	            118,
	            105,
	            99,
	            101,
	            32,
	            71,
	            109,
	            98,
	            72,
	            -94,
	            22,
	            12,
	            20,
	            104,
	            116,
	            116,
	            112,
	            58,
	            47,
	            47,
	            119,
	            119,
	            119,
	            46,
	            97,
	            103,
	            101,
	            116,
	            111,
	            46,
	            110,
	            101,
	            116,
	            -93,
	            23,
	            12,
	            21,
	            65,
	            71,
	            69,
	            84,
	            79,
	            32,
	            73,
	            110,
	            110,
	            111,
	            118,
	            97,
	            116,
	            105,
	            111,
	            110,
	            32,
	            71,
	            109,
	            98,
	            72,
	            -92,
	            33,
	            12,
	            31,
	            104,
	            116,
	            116,
	            112,
	            115,
	            58,
	            47,
	            47,
	            101,
	            105,
	            100,
	            46,
	            115,
	            101,
	            114,
	            118,
	            105,
	            99,
	            101,
	            115,
	            46,
	            97,
	            103,
	            101,
	            116,
	            111,
	            46,
	            110,
	            101,
	            116,
	            47,
	            -91,
	            -126,
	            1,
	            -36,
	            12,
	            -126,
	            1,
	            -40,
	            78,
	            97,
	            109,
	            101,
	            44,
	            32,
	            65,
	            110,
	            115,
	            99,
	            104,
	            114,
	            105,
	            102,
	            116,
	            32,
	            117,
	            110,
	            100,
	            32,
	            69,
	            45,
	            77,
	            97,
	            105,
	            108,
	            45,
	            65,
	            100,
	            114,
	            101,
	            115,
	            115,
	            101,
	            32,
	            100,
	            101,
	            115,
	            32,
	            68,
	            105,
	            101,
	            110,
	            115,
	            116,
	            97,
	            110,
	            98,
	            105,
	            101,
	            116,
	            101,
	            114,
	            115,
	            58,
	            10,
	            65,
	            71,
	            69,
	            84,
	            79,
	            32,
	            73,
	            110,
	            110,
	            111,
	            118,
	            97,
	            116,
	            105,
	            111,
	            110,
	            32,
	            71,
	            109,
	            98,
	            72,
	            10,
	            87,
	            105,
	            110,
	            122,
	            101,
	            114,
	            108,
	            97,
	            101,
	            114,
	            32,
	            83,
	            116,
	            114,
	            46,
	            32,
	            50,
	            10,
	            48,
	            55,
	            55,
	            52,
	            53,
	            32,
	            74,
	            101,
	            110,
	            97,
	            10,
	            110,
	            112,
	            97,
	            64,
	            97,
	            103,
	            101,
	            116,
	            111,
	            46,
	            110,
	            101,
	            116,
	            10,
	            10,
	            90,
	            119,
	            101,
	            99,
	            107,
	            32,
	            100,
	            101,
	            114,
	            32,
	            68,
	            97,
	            116,
	            101,
	            110,
	            -61,
	            -68,
	            98,
	            101,
	            114,
	            109,
	            105,
	            116,
	            116,
	            108,
	            117,
	            110,
	            103,
	            58,
	            10,
	            73,
	            100,
	            101,
	            110,
	            116,
	            105,
	            102,
	            105,
	            122,
	            105,
	            101,
	            114,
	            117,
	            110,
	            103,
	            32,
	            117,
	            110,
	            100,
	            32,
	            82,
	            101,
	            103,
	            105,
	            115,
	            116,
	            114,
	            105,
	            101,
	            114,
	            117,
	            110,
	            103,
	            32,
	            122,
	            117,
	            109,
	            32,
	            112,
	            101,
	            114,
	            115,
	            -61,
	            -74,
	            110,
	            108,
	            105,
	            99,
	            104,
	            101,
	            110,
	            32,
	            75,
	            117,
	            110,
	            100,
	            101,
	            110,
	            107,
	            111,
	            110,
	            116,
	            111,
	            10,
	            10,
	            90,
	            117,
	            115,
	            116,
	            -61,
	            -92,
	            110,
	            100,
	            105,
	            103,
	            101,
	            32,
	            68,
	            97,
	            116,
	            101,
	            110,
	            115,
	            99,
	            104,
	            117,
	            116,
	            122,
	            98,
	            101,
	            104,
	            -61,
	            -74,
	            114,
	            100,
	            101,
	            58,
	            10,
	            84,
	            104,
	            -61,
	            -68,
	            114,
	            105,
	            110,
	            103,
	            101,
	            114,
	            32,
	            76,
	            97,
	            110,
	            100,
	            101,
	            115,
	            118,
	            101,
	            114,
	            119,
	            97,
	            108,
	            116,
	            117,
	            110,
	            103,
	            115,
	            97,
	            109,
	            116,
	            10,
	            82,
	            101,
	            102,
	            101,
	            114,
	            97,
	            116,
	            32,
	            72,
	            111,
	            104,
	            101,
	            105,
	            116,
	            115,
	            97,
	            110,
	            103,
	            101,
	            108,
	            101,
	            103,
	            101,
	            110,
	            104,
	            101,
	            105,
	            116,
	            101,
	            110,
	            44,
	            32,
	            71,
	            101,
	            102,
	            97,
	            104,
	            114,
	            101,
	            110,
	            97,
	            98,
	            119,
	            101,
	            104,
	            114,
	            10,
	            87,
	            101,
	            105,
	            109,
	            97,
	            114,
	            112,
	            108,
	            97,
	            116,
	            122,
	            32,
	            52,
	            10,
	            57,
	            57,
	            52,
	            50,
	            51,
	            32,
	            87,
	            101,
	            105,
	            109,
	            97,
	            114,
	            10,
	            10,
	            84,
	            101,
	            108,
	            58,
	            32,
	            40,
	            48,
	            51,
	            32,
	            54,
	            49,
	            41,
	            32,
	            51,
	            55,
	            32,
	            55,
	            51,
	            32,
	            55,
	            50,
	            32,
	            53,
	            56,
	            10,
	            70,
	            97,
	            120,
	            58,
	            32,
	            40,
	            48,
	            51,
	            32,
	            54,
	            49,
	            41,
	            32,
	            51,
	            55,
	            32,
	            55,
	            51,
	            32,
	            55,
	            51,
	            32,
	            52,
	            54,
	            10,
	            112,
	            111,
	            115,
	            116,
	            115,
	            116,
	            101,
	            108,
	            108,
	            101,
	            64,
	            116,
	            108,
	            118,
	            119,
	            97,
	            46,
	            116,
	            104,
	            117,
	            101,
	            114,
	            105,
	            110,
	            103,
	            101,
	            110,
	            46,
	            100,
	            101,
	            10,
	            65,
	            110,
	            115,
	            112,
	            114,
	            101,
	            99,
	            104,
	            112,
	            97,
	            114,
	            116,
	            110,
	            101,
	            114,
	            58,
	            32,
	            70,
	            114,
	            97,
	            117,
	            32,
	            65,
	            110,
	            107,
	            101,
	            32,
	            78,
	            101,
	            117,
	            109,
	            97,
	            110,
	            110,
	            -89,
	            70,
	            49,
	            68,
	            4,
	            32,
	            46,
	            120,
	            -21,
	            -6,
	            0,
	            30,
	            -23,
	            -39,
	            -16,
	            44,
	            -50,
	            107,
	            93,
	            -109,
	            83,
	            95,
	            -56,
	            73,
	            47,
	            -90,
	            52,
	            -66,
	            91,
	            -35,
	            103,
	            -98,
	            -12,
	            48,
	            -57,
	            56,
	            100,
	            0,
	            4,
	            32,
	            -39,
	            -42,
	            -126,
	            -10,
	            68,
	            -51,
	            -61,
	            104,
	            87,
	            71,
	            16,
	            74,
	            124,
	            -89,
	            -63,
	            -77,
	            48,
	            45,
	            -127,
	            -46,
	            -95,
	            122,
	            -120,
	            96,
	            113,
	            67,
	            -10,
	            100,
	            -65,
	            35,
	            -1,
	            -112				
		};
		
		eac1.getCertificateDescription().add(certDesc);
		eac1.setRequiredCHAT(new byte[]{      127,
      76,
      18,
      6,
      9,
      4,
      0,
      127,
      0,
      7,
      3,
      1,
      2,
      2,
      83,
      5,
      0,
      1,
      0,
      -104,
      4});
		
		eac1.setOptionalCHAT(new byte[]{      127,
      76,
      18,
      6,
      9,
      4,
      0,
      127,
      0,
      7,
      3,
      1,
      2,
      2,
      83,
      5,
      0,
      0,
      0,
      0,
      0});
		
		eac1.setAuthenticatedAuxiliaryData(new byte[]{      103,
      23,
      115,
      21,
      6,
      9,
      4,
      0,
      127,
      0,
      7,
      3,
      1,
      4,
      2,
      83,
      8,
      50,
      48,
      49,
      52,
      49,
      48,
      49,
      48});
		
		eac1.setProtocol("urn:oid:1.3.162.15480.3.0.14.2");
		result.setAuthenticationProtocolData(eac1);
		return result;
	}
	
//	public PAOSInitiator createPAOSInitiator(){
//		URI endpoint = null;
//		
////		PAOSInitiator paos = PAOSInitiator.getInstance(wsCtx, endpoint, sessionID, pskKey);
//		
//		return paos;
//	}
	
	/**
	 * Reads all characters from a specified reader and returns them concatenated as a single String.
	 * 
	 * @param reader the reader to read from; must <strong>not</strong> be <code>null</code>
	 * @return the read content
	 * @throws IOException if an error occurs while reading from the reader
	 * @throws IllegalArgumentException if the reader is <code>null</code>
	 */
	public static String readerToString(Reader reader) throws IOException {
		if (reader == null) {
			throw new IllegalArgumentException("Reader may not be null.");
		}

		final StringBuilder sb = new StringBuilder();
		char[] cbuf = new char[1024];
		int len = 0;

		while ((len = reader.read(cbuf)) != -1) {
			sb.append(new String(cbuf, 0, len));
		}

		return sb.toString();
	}
	
	//Test function
	public void failTest()
	{
		fail();
	}
}
