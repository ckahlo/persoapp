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
package de.persoapp.desktop.gui.frame;

import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.client.SecureHolder;
import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.gui.panel.ButtonPanel;
import de.persoapp.desktop.gui.panel.PinPanel;

/**
 * The <tt>CANDialog</tt> provides the option to insert a Pin in the included
 * {@link PinPanel}, in order to activate the suspended pin, after the pin
 * insertion fails the second time.
 * <p>
 * <code>public class CANDialog extends JDialog</code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public class CANDialog extends JDialog {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long	serialVersionUID	= -7427161104872715697L;

	/**
	 * Stores the user input.
	 */
	private SecureHolder		result;
	
	/**
	 * The used buttons for user interaction. 
	 */
	private JButton				confirm, cancel;
	
	/**
	 * The {@link PinPanel} to insert the needed <tt>Pin</tt>.
	 */
	private PinPanel			pinPanel;
	
	/**
	 * The {@link ButtonPanel}, to allow further user interaction.
	 */
	private ButtonPanel			buttonPanel;
	
	/**
	 * The label to display informations.
	 */
	private JLabel				label;
	
	/**
	 * The monitoring object.
	 */
	private Object				monitor;

	/**
	 * Creates a new instance of the {@link CANDialog}.
	 * 
	 * @param parent
	 *            - The parent frame.
	 * @param title
	 *            - The title of the {@link CANDialog}.
	 * @param msg
	 *            - The message of the {@link CANDialog}.
	 */
	private CANDialog(final Frame parent, final String title, final String msg) {
		super(parent, title, true);

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setResizable(Configuration.RESIZABLE);
		this.setIconImage(Configuration.WINDOW_ICON);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent we) {
				setResult();
			}
		});

		initComponents(msg);
		setContentPane();

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Initializes the components of the {@link CANDialog}.
	 * 
	 * @param msg
	 *            - The message, which is going to be displayed in the dialog.
	 */
	private void initComponents(final String msg) {
		monitor = new Object();

		buttonPanel = new ButtonPanel();

		confirm = buttonPanel.getConfirm();
		cancel = buttonPanel.getCancel();
		final ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				setResult();
			}
		};
		confirm.addActionListener(listener);
		cancel.addActionListener(listener);

		label = new JLabel(msg);

		pinPanel = new PinPanel(buttonPanel, PropertyResolver.getBundle("text").get("CANDialog_pin_title"), 6);
	}

	/**
	 * Sets the <tt>content pane</tt> of the {@link CANDialog}, using the
	 * {@link GridBagLayout}.
	 */
	private void setContentPane() {
		final Container contentPane = this.getContentPane();
		contentPane.setLayout(new GridBagLayout());

		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 1;
		cons.weighty = 1d / 3d;

		contentPane.add(label, cons);

		cons.gridy = 1;
		contentPane.add(pinPanel, cons);

		cons.gridy = 2;
		contentPane.add(buttonPanel, cons);
	}

	/**
	 * Stores the result of the user input, notifies the monitoring object and
	 * disposes the {@link CANDialog}.
	 */
	private void setResult() {
		result = new SecureHolder(pinPanel.getPinCode(0));
		synchronized (monitor) {
			monitor.notify();
		}
		this.dispose();
	}

	/**
	 * Retrieves the result of the user input of the {@link CANDialog}. As long
	 * as the result is <strong>null</strong>, the monitoring object is waiting.
	 * 
	 * @return Returns the result of the user input in a {@link SecureHolder}.
	 */
	private SecureHolder getResult() {
		while (result == null) {
			synchronized (monitor) {
				try {
					monitor.wait();
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * Shows the {@link CANDialog} and retrieves the result.
	 * 
	 * @param parent
	 *            - The parent frame of the {@link CANDialog}.
	 * @param title
	 *            - The title of the dialog.
	 * @param msg
	 *            - The message, which is going to be displayed in the dialog.
	 * 
	 * @return Returns the result of the user input in the {@link SecureHolder}.
	 */
	public static SecureHolder show(final Frame parent, final String title, final String msg) {
		return new CANDialog(parent, title, msg).getResult();
	}
}
