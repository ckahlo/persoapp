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
package de.persoapp.desktop.gui;

import java.awt.Component;
import java.util.logging.Level;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.LayoutStyle;
import javax.swing.LookAndFeel;
import javax.swing.UIDefaults;

import de.persoapp.desktop.Logging;

/**
 * @author ckahlo
 * 
 */
public class LAFFacade extends LookAndFeel {

	private LookAndFeel	target	= null;

	public LAFFacade() {
		super();
	}

	public LAFFacade(final LookAndFeel laf) {
		super();
		setTarget(laf);
	}

	public void setTarget(final LookAndFeel target) {
		this.target = target;
		Logging.getLogger().log(Level.INFO, "LAF: " + target.getClass() + " = " + target.toString());
	}

	private LookAndFeel target() {
		return this.target;
	}

	@Override
	public String getName() {
		return target().getName();
	}

	@Override
	public String getID() {
		return target().getID();
	}

	@Override
	public String getDescription() {
		return target().getDescription();
	}

	@Override
	public boolean isNativeLookAndFeel() {
		return target().isNativeLookAndFeel();
	}

	@Override
	public boolean isSupportedLookAndFeel() {
		return target().isSupportedLookAndFeel();
	}

	@Override
	public LayoutStyle getLayoutStyle() {
		return target().getLayoutStyle();
	}

	@Override
	public void provideErrorFeedback(final Component component) {
		target().provideErrorFeedback(component);
	}

	@Override
	public Icon getDisabledIcon(final JComponent component, final Icon icon) {
		return target().getDisabledIcon(component, icon);
	}

	@Override
	public Icon getDisabledSelectedIcon(final JComponent component, final Icon icon) {
		return target().getDisabledSelectedIcon(component, icon);
	}

	@Override
	public boolean getSupportsWindowDecorations() {
		return target().getSupportsWindowDecorations();
	}

	@Override
	public void initialize() {
		target().initialize();
	}

	@Override
	public void uninitialize() {
		target().uninitialize();
	}

	@Override
	public UIDefaults getDefaults() {
		return target().getDefaults();
	}

	@Override
	public String toString() {
		return target().toString();
	}

}
