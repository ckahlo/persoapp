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
package de.persoapp.desktop;

import java.awt.image.BufferedImage;
import java.util.Properties;

import javax.swing.LookAndFeel;

import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.gui.laf.DefaultLAF;

/**
 * @author ckahlo
 * 
 */
public class Configuration {

	private static final Properties						PROPS		= PropertyResolver
																			.getProperties("config.properties");

	static {
		LOOK_AND_FEEL = DefaultLAF.class;

		final String claim = PROPS.getProperty("claim_text");
		if (claim == null || claim.trim().isEmpty()) {
			CLAIM_TEXT = null;
		} else {
			CLAIM_TEXT = claim;
		}

		MAINVIEW_CLASS = de.persoapp.desktop.MainView.class;
	}

	public static final Class<? extends IMainView>		MAINVIEW_CLASS;

	public static final Class<? extends LookAndFeel>	LOOK_AND_FEEL;

	public static final String							CLAIM_TEXT;

	public static final boolean							LOGGING		= Boolean.parseBoolean(PROPS.getProperty("logging",
																			"true"));

	public static final boolean							RESIZABLE	= Boolean.parseBoolean(PROPS.getProperty(
																			"layout.resizeable", "false"));

	public static final String							FONT		= PROPS.getProperty("layout.font", "Arial");

	public static final BufferedImage					WINDOW_ICON	= Utils.getImage("icon.png");

	public static final BufferedImage					TRAY_ICON	= WINDOW_ICON;

	public static final BufferedImage					LOGO		= WINDOW_ICON;
}
