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
 * @version 1.0, 12.10.2014 10:40:45
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
package de.persoapp.desktop.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import javax.swing.JFrame;

import junit.framework.TestCase;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.CardHandler;
import de.persoapp.core.client.ECardSession;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.MainViewEventListener;
import de.persoapp.core.client.IMainView.ChangePINDialogResult;
import de.persoapp.core.client.IMainView.MainDialogResult;
import de.persoapp.core.ws.IFDService;
import de.persoapp.core.ws.ManagementService;
import de.persoapp.core.ws.SALService;
import de.persoapp.core.ws.engine.WSContainer;
import de.persoapp.desktop.MainView;
import de.persoapp.desktop.gui.frame.MainFrame;
import de.persoapp.desktop.gui.frame.NewChangePinFrame;


/**
 * @author Rico Klimsa
 */
public class MainViewTest{
	
	private static WSContainer wsCtx = null;
	private final String	DEFAULT_PIN	= "123456";
	private final String	serviceURL	= "https://eid.services.ageto.net/persoapp/eidtest.jsp";
	
	private static Logger logger = Logger.getLogger(MainViewTest.class.getName());
	private static IMainView	mainView = null;
	private static IFDService ifdservice = null;
	
	/**
	 * Test spy for indirect output
	 */
	private static SALService salservice = null;
	private static ManagementService managementservice = null;
	
	private static CardHandler eCardHandler = null;
	private static ECardSession session = null;
		
	
	@Before
	public void init() {
		if(mainView==null) {
			mainView = MainView.getInstance();
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
			salservice = new SALService();
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
		}
		assertNotNull("no session",session);		
		ECardWorker.init(mainView, wsCtx, eCardHandler);		
	}
	
	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Test the capabilities to of {@link IMainView} to process events.</li>
	 * <li>Test the card state through - {@link IMainView.EventListener#EVENT_TEST_CARD_STATE}</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The state of the card or zero, if pace is not supported by the CCID.</li>
	 * </ul>
	 */
	@Test
	public void EVENT_TEST_CARD_STATE() {
		assertNotNull("no result",mainView.triggerEvent(IMainView.EventListener.EVENT_TEST_CARD_STATE));
	}
	
	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Test the capabilities to of {@link IMainView} to process events.</li>
	 * <li>Test the pin state through - {@link IMainView.EventListener#EVENT_TEST_PIN_STATE}</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The remaining attempts to insert the correct pin.</li>
	 * </ul>
	 */	
	@Test
	public void EVENT_TEST_PIN_STATE() {
		Object result = mainView.triggerEvent(IMainView.EventListener.EVENT_TEST_PIN_STATE);
		assertNotNull("no pin check result",result);
		assertTrue("no Integer result", result instanceof Integer);
	}
	
	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Test the capabilities to of {@link IMainView} to process events.</li>
	 * <li>Test the pin state through - {@link IMainView.EventListener#EVENT_TEST_PIN_STATE}</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The remaining attempts to insert the correct pin.</li>
	 * </ul>
	 */	
	@Test
	public void EVENT_SHOW_ESIGN_CERTS_BASEREADER () {
		Object result = mainView.triggerEvent(IMainView.EventListener.EVENT_SHOW_ESIGN_CERTS);
		assertNull("A base card reader should not support esign",result);
	}
	
	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Create a {@link ChangePINDialogResult} with valid old pin and valid new pin</li>
	 * <li>Test the capabilities to of {@link IMainView} to process events.</li>
	 * <li>Test the card state through - {@link IMainView.EventListener#EVENT_CHANGE_PIN_EID}</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>True, indicating that the change of the pin was successful.</li>
	 * </ul>
	 */		
	@Test
	public void EVENT_TEST_CHANGE_PIN_EID_1() {
		final ChangePINDialogResult dr = new ChangePINDialogResult(DEFAULT_PIN.getBytes(),DEFAULT_PIN.getBytes(), true);
		assertNotNull("no change pin dialog result",dr);
		
		Object result = mainView.triggerEvent(IMainView.EventListener.EVENT_CHANGE_PIN_EID,dr);

		assertNotNull("no pin change result",result);
		assertTrue("pin change not successful",(boolean)result);
	}

	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Create a {@link ChangePINDialogResult} with valid old pin and no new pin</li>
	 * <li>Test the capabilities to of {@link IMainView} to process events.</li>
	 * <li>Test the card state through - {@link IMainView.EventListener#EVENT_CHANGE_PIN_EID}</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>False, indicating that the change of the pin was not successful.</li>
	 * </ul>
	 */			
	@Test
	public void EVENT_TEST_CHANGE_PIN_EID_2() {
		
		ChangePINDialogResult dr = new ChangePINDialogResult(DEFAULT_PIN.getBytes(),null, true);
		assertNotNull("no change pin dialog result",dr);
		
		Object result = mainView.triggerEvent(IMainView.EventListener.EVENT_CHANGE_PIN_EID,dr);
		assertNotNull("no pin change result",result);
		assertFalse("pin change successful",(boolean)result);
	}	
	
	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Create a {@link ChangePINDialogResult} with no old pin and a valid new pin</li>
	 * <li>Test the capabilities to of {@link IMainView} to process events.</li>
	 * <li>Test the card state through - {@link IMainView.EventListener#EVENT_CHANGE_PIN_EID}</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>False, indicating that the change of the pin was not successful.</li>
	 * </ul>
	 */				
	@Test
	public void EVENT_TEST_CHANGE_PIN_EID_3() {
		
		ChangePINDialogResult dr = new ChangePINDialogResult(null,DEFAULT_PIN.getBytes(), true);
		assertNotNull("no change pin dialog result",dr);
		
		Object result = mainView.triggerEvent(IMainView.EventListener.EVENT_CHANGE_PIN_EID,dr);
		assertNull("pin change result not null",result);
	}		
	
	/**
	 * <b>Preconditions:</b>
	 * <ul>
	 * <li>A single basic card reader is connected to the eID-Client system.</li>
	 * <li>A single active eID-Card is connected to the card reader.</li>
	 * </ul>
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Create a {@link ChangePINDialogResult} with no old pin and no new pin</li>
	 * <li>Test the capabilities to of {@link IMainView} to process events.</li>
	 * <li>Test the card state through - {@link IMainView.EventListener#EVENT_CHANGE_PIN_EID}</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>False, indicating that the change of the pin was not successful.</li>
	 * </ul>
	 */
	@Test
	public void EVENT_TEST_CHANGE_PIN_EID_4() {
		
		ChangePINDialogResult dr = new ChangePINDialogResult(null,null, true);
		assertNotNull("no change pin dialog result",dr);
		
		Object result = mainView.triggerEvent(IMainView.EventListener.EVENT_CHANGE_PIN_EID,dr);
		assertNull("pin change result not null",result);
	}		
	
	/**
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Show an error message through {@link MainView#showMessage(String, int)}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred which indicates an successful result.</li>
	 * </ul>
	 */	
	@Test
	public void TEST_INTERFACE_GUI_CORE_01 () {
		mainView.showMessage("Test error field.", IMainView.ERROR);
	}
	
	/**
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Show the can dialog through {@link MainView#showCANDialog(String)}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred which indicates an successful result.</li>
	 * </ul>
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void TEST_INTERFACE_GUI_CORE_02 () throws InterruptedException {
		new Thread(new Runnable(){
			@Override
			public void run() {
				((MainView)mainView).showCANDialog("Test can dialog");
			}}).start();
	}

	/**
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Show the can dialog through {@link MainView#showCANDialog(String)}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>No Exception occurred which indicates an successful result.</li>
	 * </ul>
	 * 
	 * @throws InterruptedException
	 */	
	@Test
	public void TEST_INTERFACE_GUI_01() {
		((MainView)mainView).getChangePinFrame().setVisible(true);
		((MainView)mainView).getMainFrame().setVisible(true);
		((MainView)mainView).closeDialogs();
		assertFalse("change pin frame still visible",((MainView)mainView).getChangePinFrame().isVisible());
		assertFalse("main frame still visible",((MainView)mainView).getMainFrame().isVisible());
	}
	
	/**
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Create the {@link NewChangePinFrame} through {@link MainView#getChangePinFrame()}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The {@link NewChangePinFrame} is returned.</li>
	 * </ul>
	 */	
	@Test
	public void TEST_INTERFACE_GUI_02 () {
		NewChangePinFrame result = ((MainView)mainView).getChangePinFrame();
		assertNotNull("no new chnage pin frame",result);
	}

	/**
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Create the {@link MainFrame} through {@link MainView#getMainFrame()}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The {@link MainFrame} is returned.</li>
	 * </ul>
	 */
	@Test
	public void TEST_INTERFACE_GUI_03 () {
		MainFrame result = ((MainView)mainView).getMainFrame();
		assertNotNull("no main frame",result);
	}	
	
	/**
	 * <b>TestStep: </b>
	 * <ul>
	 * <li>Retrieve an {@link MainDialogResult} through {@link MainView#getMainFrame()}.</li>
	 * </ul>
	 * <b>Expected Result: </b>
	 * <ul>
	 * <li>The {@link MainDialogResult} is returned.</li>
	 * </ul>
	 */	
	@Test
	public void TEST_INTERFACE_GUI_04 () throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		final MainView mainv = ((MainView)mainView);

		new Thread(new Runnable(){
			@Override
			public void run() {
				assertNotNull("main dialog result null",mainv.getMainDialogResult());
			}}).start();
		
		Field field = mainv.getClass().getDeclaredField("result");
		field.setAccessible(true);
		field.set(mainv, new MainDialogResult(0, null, true));
		field.setAccessible(false);
		
		assertNotNull("main dialog result null",mainv.getMainDialogResult());
	}

}
