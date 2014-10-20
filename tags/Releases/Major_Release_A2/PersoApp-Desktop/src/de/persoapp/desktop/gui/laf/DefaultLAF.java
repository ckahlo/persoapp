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
package de.persoapp.desktop.gui.laf;

import java.awt.Color;
import java.awt.Font;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.gui.LAFFacade;

/**
 * <p>
 * The DefaultLAF-class defines the Default Look and Feel,
 * which is a system-predefined version of the <em>Look and Feel</em>.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class DefaultLAF extends LAFFacade {

	/**
	 * <p>
	 * Creates a new instance of the {@link DefaultLAF}. The
	 * Default Look and Feel is created through the system informations
	 * of the system like <em>Look and Feel</em>.
	 * </p>
	 */
	public DefaultLAF() {
		super();

		LookAndFeel laf = null;
		try {
			laf = (LookAndFeel) Class.forName(UIManager.getSystemLookAndFeelClassName()).newInstance();
		} catch (final Exception e) {
			laf = new BasicLAF();
		}

		setTarget(laf);

		final FontUIResource textFont = new FontUIResource(Configuration.FONT, Font.PLAIN, 12);
		final FontUIResource buttonFont = new FontUIResource(Configuration.FONT, Font.BOLD, 12);

		UIManager.put("TextPane.font", textFont);
		UIManager.put("Button.font", buttonFont);

		final Color bgColor1 = new Color(0x00FFFFFF);
		UIManager.put("Panel.background", bgColor1);
		UIManager.put("CheckBox.background", bgColor1);
		UIManager.put("ProgressBar.background", bgColor1);
		UIManager.put("OptionPane.background", bgColor1);

		final Color bgColor2 = new Color(0x00F7F7F7);
		UIManager.put("TextPane.background", bgColor2);
		UIManager.put("PasswordField.background", bgColor2);
	}
}
