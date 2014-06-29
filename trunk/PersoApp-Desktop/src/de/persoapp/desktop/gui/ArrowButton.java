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
package de.persoapp.desktop.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import de.persoapp.desktop.Utils;
import de.persoapp.desktop.gui.frame.SidebarProvider;

/**
 * The <tt>ArrowButton</tt>-class provides the main functionality to work with a
 * <tt>SidebarProvider</tt>. The ArrowButtons can be used to hide and add a
 * sidebar to a <tt>Window</tt>.
 * <p>
 * <code>public class ArrowButton extends JButton implements ActionListener<code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class ArrowButton extends JButton implements ActionListener {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * The constant to set up the left pressed state.
	 */
	public static final int		STATE_LEFT			= 0;
	
	/**
	 * The constant to set up the right pressed state.
	 */
	public static final int		STATE_RIGHT			= 1;

	/**
	 * The <tt>ImageIcons</tt> for displaying the different button states.
	 */
	private static ImageIcon	left				= new ImageIcon(Utils.getImage("arrow_left.png")),
			leftPressed = new ImageIcon(Utils.getImage("arrow_left_dark.png")), leftRollover = new ImageIcon(
					Utils.getImage("arrow_left_light.png")), right = new ImageIcon(Utils.getImage("arrow_right.png")),
			rightPressed = new ImageIcon(Utils.getImage("arrow_right_dark.png")), rightRollover = new ImageIcon(
					Utils.getImage("arrow_right_light.png"));

	/**
	 * The provider for the sidebar.
	 */
	private SidebarProvider		window;

	/**
	 * The state of the {@link ArrowButton}.
	 */
	private int					state;

	/**
	 * Constructs a new instance of the {@link ArrowButton}.
	 */
	public ArrowButton() {
		super();

		this.setBorder(null);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);

		this.setIconState(STATE_RIGHT);

		this.addActionListener(this);
	}

	/**
	 * Sets the current icon state.<br>
	 * </br> The state can be <code>STATE_LEFT</code> for removing the sidebar
	 * and <code>STATE_RIGHT</code> for adding the sidebar.
	 * 
	 * @param state - The state to set.
	 */
	public void setIconState(final int state) {
		switch (state) {
			case STATE_LEFT:
				this.setIcon(left);
				this.setPressedIcon(leftPressed);
				this.setRolloverIcon(leftRollover);
				break;
			case STATE_RIGHT:
				this.setIcon(right);
				this.setPressedIcon(rightPressed);
				this.setRolloverIcon(rightRollover);
				break;
		}

		this.state = state;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		if (this.window == null) {
			final Component window = SwingUtilities.getRoot(this);
			if (window == null || !(window instanceof SidebarProvider)) {
				throw new IllegalArgumentException(
						"The root of an ArrowButton has to be an instance of SidebarProvider!");
			} else {
				this.window = (SidebarProvider) window;
			}
		}

		switch (this.state) {
			case STATE_LEFT:
				window.removeSidebar();
				setIconState(STATE_RIGHT);
				break;
			case STATE_RIGHT:
				window.addSidebar();
				setIconState(STATE_LEFT);
				break;
		}
	}
}
