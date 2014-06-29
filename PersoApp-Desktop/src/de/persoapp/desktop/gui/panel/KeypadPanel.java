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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.gui.MyTitledBorder;

/**
 * The <tt>KeypadPanel</tt> holds the buttons of the {@link PinPanel} and
 * provides the option to insert the needed <tt>Pins</tt>.
 * <p>
 * <code>public class KeypadPanel extends JPanel</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class KeypadPanel extends JPanel {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long	serialVersionUID	= 2807875955848469717L;

	/**
	 * The <code>ArrayList</code> of the keys.
	 */
	private ArrayList<JButton>	keys;
	
	/**
	 * The <tt>JButtons</tt> for additionally functionality like
	 * <strong>clear</strong> and <strong>delete</strong>.
	 */
	private JButton				clear, delete;
	
	/**
	 * The {@link PinPanel} which is necessary for the input of the pin.
	 */
	private PinPanel			pinPanel;

	/**
	 * Creates a new instance of the {@link KeypadPanel} with the given
	 * {@link PinPanel}. The constructed Panel is double-buffered for advanced
	 * displaying.
	 * 
	 * @param panel
	 *            - The given {@link PinPanel}.
	 */
	public KeypadPanel(final PinPanel panel) {
		super();
		this.setDoubleBuffered(true);
		this.setLayout(new GridBagLayout());
		this.setBorder(new MyTitledBorder(PropertyResolver.getBundle("text").get("KeypadPanel_title")));

		this.pinPanel = panel;

		initComponents();
		drawComponents();

		this.setEnabled(false);
	}

	/**
	 * Initializes the components of the {@link KeypadPanel}.
	 */
	private void initComponents() {
		keys = new ArrayList<JButton>();

		final ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				pinPanel.fillPanel(((JButton) e.getSource()).getText());
			}
		};

		for (int i = 0; i < 10; i++) {
			keys.add(new JButton(String.valueOf(i)));
			keys.get(i).setFocusable(false);
			keys.get(i).addActionListener(listener);
		}
		Collections.shuffle(keys);

		clear = new JButton("C");
		clear.setFocusable(false);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				pinPanel.clear();
			}
		});
		//		delete = new JButton("\u232B");
		delete = new JButton("<");
		delete.setFocusable(false);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				pinPanel.clearField();
			}
		});
	}

	/**
	 * Draws the <tt>components</tt> of the KeypadPanel.
	 */
	private void drawComponents() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.NONE;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 1d / 3d;
		cons.weighty = 0.25;

		int i = 0;
		for (int y = 0; y < 3; y++) {
			cons.gridy = y;
			for (int x = 0; x < 3; x++) {
				cons.gridx = x;
				this.add(keys.get(i++), cons);
			}
		}

		cons.gridx = 0;
		cons.gridy = 3;
		this.add(delete, cons);

		cons.gridx = 1;
		this.add(keys.get(i), cons);

		cons.gridx = 2;
		this.add(clear, cons);
	}

	/**
	 * Shuffles the keys of the {@link KeyPadPanel}.
	 */
	public void shuffle() {
		for (final JButton button : keys) {
			this.remove(button);
		}
		this.remove(clear);
		this.remove(delete);

		Collections.shuffle(keys);

		drawComponents();

		this.repaint();
	}
	
	/**
	 * Sets the {@link PinPanel}.
	 * 
	 * @param panel
	 *            - The given {@link PinPanel}.
	 */
	public void setPinPanel(final PinPanel panel) {
		this.pinPanel = panel;
	}

	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);

		for (final JButton button : keys) {
			button.setEnabled(enabled);
		}

		delete.setEnabled(enabled);
	}

	@Override
	public void addMouseListener(final MouseListener l) {
		super.addMouseListener(l);
		for (final JButton b : keys) {
			b.addMouseListener(l);
		}
		clear.addMouseListener(l);
		delete.addMouseListener(l);
	}
}
