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
package de.persoapp.desktop.gui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.gui.panel.ButtonPanel;

/**
 * Informations follow in MR3.
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class ProxyPassFrame extends JFrame {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long				serialVersionUID	= 1L;

	/**
	 * The attributes to store the user credentials.
	 */
	private String							user, pass;
	
	/**
	 * The name of the {@link ProxyPassFrame}.
	 */
	private final String					frameName;

	/**
	 * The <tt>bundle</tt> which resolves the necessary properties.
	 */
	private final PropertyResolver.Bundle	textBundle;
	
	/**
	 * The top text area.
	 */
	private JTextArea						topTextArea;
	
	/**
	 * The <tt>label</tt> for the field of the username and the password.
	 */
	private JLabel							userLabel, passLabel;
	
	/**
	 * The <tt>textfield</tt> for the username. 
	 */
	private JTextField						userField;
	
	/**
	 * The <tt>textfield</tt> for containing the user-password.
	 */
	private JPasswordField					passField;
	
	/**
	 * The {@link ButtonPanel} for providing the option of user interaction.
	 */
	private ButtonPanel						buttonPanel;
	
	/**
	 * The main panel of the {@link ProxyPassFrame}.
	 */
	private JPanel							mainPanel;

	/**
	 * Creates a new instance of the {@link ProxyPassFrame} and initializes it
	 * with the given arguments.
	 * 
	 * @param realm
	 *            - The user account location. The given realm can be either a
	 *            prefix (A domain of an operating system like Windows NT 4.0)
	 *            or a suffix (A DNS domain name or the name of an Active
	 *            Directory domain).
	 * 
	 * @param creds
	 *            - Username and password, can be null or left blank.
	 */
	public ProxyPassFrame(final String realm, final String... creds) {

		if (creds.length >= 2) {
			user = creds[0];
			pass = creds[1];
		}

		textBundle = PropertyResolver.getBundle("text");
		this.setName("ProxyPassFrame");
		this.frameName = this.getName() + "_";
		this.setTitle(textBundle.get(frameName + "window_title"));

		this.setIconImage(Configuration.WINDOW_ICON);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(Configuration.RESIZABLE);
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);

		initPanels(realm);
		addListener();
		drawPanels();

		this.pack();
		this.setLocationRelativeTo(null);
		this.setMinimumSize(this.getBounds().getSize());

		this.setVisible(true);

		if (user != null) {
			userField.setText(user);
			buttonPanel.getConfirm().requestFocus();
		} else {
			userField.requestFocus();
		}

		if (pass != null) {
			passField.setText(pass);
		}
	}

	/**
	 * Initializes the panels of the {@link ProxyPassFrame}. The given realm can
	 * be either a prefix (A domain of an operating system like Windows NT 4.0)
	 * or a suffix (A DNS domain name or the name of an Active Directory domain).
	 * 
	 * @param realm
	 *            - The currently user account location.
	 */
	private void initPanels(final String realm) {
		topTextArea = new JTextArea();
		topTextArea.setFont(UIManager.getFont("Label.font"));
		topTextArea.setText(realm);
		topTextArea.setEditable(false);
		topTextArea.setCursor(null);
		topTextArea.setOpaque(false);
		topTextArea.setAutoscrolls(false);
		topTextArea.setWrapStyleWord(false);
		topTextArea.setLineWrap(true);
		topTextArea.setForeground(Color.WHITE);

		userLabel = new JLabel(textBundle.get(frameName + "user_title"));
		userLabel.setForeground(Color.WHITE);
		passLabel = new JLabel(textBundle.get(frameName + "pass_title"));
		passLabel.setForeground(Color.WHITE);

		userField = new JTextField(user, 20);
		passField = new JPasswordField(pass, 20);
		passField.setActionCommand("confirm");

		final Color background = new Color(51, 51, 150);
		buttonPanel = new ButtonPanel();
		buttonPanel.setBackground(background);
		buttonPanel.getConfirm().setBackground(background);
		buttonPanel.getConfirm().setActionCommand("confirm");
		buttonPanel.getCancel().setBackground(background);
		buttonPanel.getCancel().setActionCommand("cancel");

		mainPanel = new JPanel(true);
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setBackground(background);
	}

	/**
	 * Adds a {@link ActionListener} to the buttons of the {@link ButtonPanel}
	 * and to the field of the <tt>password</tt>.
	 */
	private void addListener() {
		final ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (e.getActionCommand().equals("confirm")) {
					user = userField.getText();
					pass = new String(passField.getPassword());
				} else if (e.getActionCommand().equals("cancel")) {
					user = null;
					pass = null;
				}

				synchronized (ProxyPassFrame.this) {
					ProxyPassFrame.this.notify();
				}

				ProxyPassFrame.this.dispose();
			}
		};

		passField.addActionListener(listener);
		buttonPanel.getConfirm().addActionListener(listener);
		buttonPanel.getCancel().addActionListener(listener);
	}

	/**
	 * Draws the panels of the {@link ProxyPassFrame}. The {@link GridBagLayout}
	 * is used for drawing.
	 */
	private void drawPanels() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 2;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(5, 10, 5, 10);
		cons.weightx = 1;
		cons.weighty = 0.25;
		mainPanel.add(topTextArea, cons);

		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridy = 3;
		mainPanel.add(buttonPanel, cons);

		cons.gridy = 1;
		cons.gridwidth = 1;
		cons.weightx = 0;
		cons.insets = new Insets(5, 10, 5, 10);
		mainPanel.add(userLabel, cons);

		cons.gridy = 2;
		mainPanel.add(passLabel, cons);

		cons.insets = new Insets(5, 20, 5, 10);
		cons.gridx = 1;
		cons.gridy = 1;
		cons.weightx = 1;
		mainPanel.add(userField, cons);

		cons.gridy = 2;
		mainPanel.add(passField, cons);

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Returns the credentials of a user.
	 * 
	 * @return Returns the <strong>username</strong> and
	 *         <strong>password</strong>.
	 */
	public String[] getCredentials() {
		synchronized (this) {
			try {
				this.wait();
			} catch (final InterruptedException e) {
				return null;
			}
		}
		return new String[] { user, pass };
	}
}
