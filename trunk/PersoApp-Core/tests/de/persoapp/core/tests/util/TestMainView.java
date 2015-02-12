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
 * @version 1.0, 02.09.2014 15:25:29
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

package de.persoapp.core.tests.util;

import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.SecureHolder;

/**
 * @author ckahlo
 * 
 */
public class TestMainView implements IMainView {

	private final String	defaultPIN;
	private EventListener	eventListener;
	private long			resultCHAT;

	/**
	 * @param defaultPIN
	 */
	public TestMainView(final String defaultPIN) {
		this.defaultPIN = defaultPIN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.persoapp.core.client.IMainView#setEventLister(de.persoapp.core.client
	 * .IMainView.EventListener)
	 */
	@Override
	public void setEventLister(final EventListener listener) {
		this.eventListener = listener;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#triggerEvent(int,
	 * java.lang.Object[])
	 */
	@Override
	public Object triggerEvent(final int event, final Object... eventData) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.persoapp.core.client.IMainView#showMainDialog(de.persoapp.core.client
	 * .IEAC_Info, int)
	 */
	@Override
	public void showMainDialog(final IEAC_Info eacInfo, final int MODE) {
		System.out.println("EAC-Info input:");
		this.resultCHAT = eacInfo.getRequiredChat();
		System.out.println(eacInfo.getSubjectName());
		System.out.println(eacInfo.getSubjectURL());
		System.out.println(eacInfo.getEffectiveDate());
		System.out.println(eacInfo.getExpirationDate());
		System.out.println(eacInfo.getIssuerName());
		System.out.println(eacInfo.getIssuerURL());
		System.out.println(eacInfo.getDescriptionType());
		System.out.println(eacInfo.getTermsOfUsage());
		System.out.println(eacInfo.getTransactionInfo());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#getMainDialogResult()
	 */
	@Override
	public MainDialogResult getMainDialogResult() {
		/*
		 * approve by default
		 */
		return new MainDialogResult(resultCHAT, defaultPIN.getBytes(), true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#showCANDialog(java.lang.String)
	 */
	@Override
	public SecureHolder showCANDialog(final String msg) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#showChangePinDialog()
	 */
	@Override
	public void showChangePinDialog() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#showProgress(java.lang.String,
	 * int, boolean)
	 */
	@Override
	public void showProgress(final String message, final int amount, final boolean enabled) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#showQuestion(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean showQuestion(final String title, final String message) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#showError(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void showError(final String title, final String message) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#showMessage(java.lang.String, int)
	 */
	@Override
	public void showMessage(final String msg, final int type) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#closeDialogs()
	 */
	@Override
	public void closeDialogs() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.persoapp.core.client.IMainView#shutdown()
	 */
	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param dEFAULT_PIN
	 * @return
	 */
	public static IMainView getInstance(final String defaultPIN) {
		final IMainView instance = new TestMainView(defaultPIN);
		return instance;
	}
}
