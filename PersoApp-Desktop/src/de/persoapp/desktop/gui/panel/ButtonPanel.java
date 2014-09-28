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
package de.persoapp.desktop.gui.panel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.persoapp.core.client.PropertyResolver;

/**
 * <p>
 * The ButtonPanel contains the <em>confirm</em>- and the
 * <em>cancel</em>-button of the PersoApp Desktop Client.
 * </p>
 * <p>
 * The defined buttons have the following purpose:
 * <ul>
 * <li>confirm - The button to confirm, that the data, which you have
 * chosen, can be send to the service provider or to proceed with the selected
 * operation, like the activation of the electronic identity card.</li>
 * <li>cancel - The button to cancel the currently active choice of
 * personal data and to abort the currently running operation.</li>
 * </ul>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class ButtonPanel extends JPanel {

	private static final long				serialVersionUID	= -3803650768195073587L;

	/**
	 * The declared buttons in the button panel.
	 * <ul>
	 * <li>confirm - The button to confirm, that the data, which you
	 * have chosen, can be send to the service provider or to proceed with the
	 * selected operation, like the activation of the electronic identity card.</li>
	 * <li>cancel - The button to cancel the currently active choice of
	 * personal data and to abort the currently running operation.</li>
	 * </ul>
	 */
	private JButton							confirm, cancel;
	
	/**
	 * Localized message bundle for user interaction.
	 */
	private final PropertyResolver.Bundle	textBundle;

	/**
	 * <p>
	 * Constructs a new instance of the {@link ButtonPanel}. Also draws the
	 * components and initializes the member attributes.
	 * </p>
	 * <p>
	 * The constructed panel is double-buffered to achieve benefits by
	 * the extended use of memory.
	 * </p>
	 * <p>
	 * The {@link GridBagLayout} is used for drawing the components of the
	 * ButtonPanel.
	 * </p>
	 */
	public ButtonPanel() {
		super();
		textBundle = PropertyResolver.getBundle("text");

		this.setDoubleBuffered(true);
		this.setLayout(new GridBagLayout());

		initComponents();
		drawComponents();
	}

	/**
	 * Initializes the components by constructing new instances for every
	 * button of the {@link ButtonPanel}.
	 */
	private void initComponents() {
		confirm = new JButton(textBundle.get("confirm"));
		cancel = new JButton(textBundle.get("cancel"));
	}

	/**
	 * Draws the components of the {@link ButtonPanel}. 
	 */
	private void drawComponents() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(0, 0, 0, 5);
		cons.weightx = 0.5;
		cons.weighty = 1;

		this.add(getConfirm(), cons);

		cons.gridx = 1;
		cons.insets = new Insets(0, 5, 0, 0);
		this.add(getCancel(), cons);
	}
	
	/**
	 * Sets the focus to the component, which is identified through the given
	 * index. This function allows currently to set the focus to the
	 * <em>cancel</em>- or to the <em>confirm</em>-button.
	 * 
	 * @param index
	 *            - The specified index of the component, which requests the
	 *            focus. If index is equal to <b>0</b>, the
	 *            <em>confirm</em>-button gains the focus. The <em>cancel</em>
	 *            -button gains the focus, if index is equal to
	 *            <b>1</b>. <b>Null</b> is not allowed and by other values,
	 *            which are unequal to <b>0</b> and <b>1</b>, nothing is going
	 *            to happen.
	 */
	public void setFocusToComponent(final int index) {
		switch (index) {
			case 0:
				getConfirm().requestFocus();
				break;
			case 1:
				getCancel().requestFocus();
				break;
		}
	}

	/**
	 * Returns the instance of the confirm button.
	 * 
	 * @return Returns the instance of the confirm button.
	 */
	public JButton getConfirm() {
		return confirm;
	}

	/**
	 * Returns the instance of the cancel button.
	 * 
	 * @return Returns the instance of the cancel button.
	 */
	public JButton getCancel() {
		return cancel;
	}

	@Override
	public void addMouseListener(final MouseListener l) {
		super.addMouseListener(l);
		confirm.addMouseListener(l);
		cancel.addMouseListener(l);
	}

	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		confirm.setEnabled(enabled);
		cancel.setEnabled(enabled);
	}
}
