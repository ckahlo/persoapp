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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.client.PropertyResolver.Bundle;
import de.persoapp.desktop.Configuration;

/**
 * The <tt>LicenseAgreementDialog</tt> provides the functionality for confirming
 * or rejecting the license through the user input.
 * <p>
 * <code>public class LicenseAgreementDialog extends JDialog</code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public class LicenseAgreementDialog extends JDialog {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long	serialVersionUID	= 8412805519732754109L;

	/**
	 * The <tt>bundle</tt> which resolves the necessary properties.
	 */
	private final Bundle		textBundle;

	/**
	 * The attribute in which the result of the user input is going to be stored.
	 */
	private boolean				accept;

	/**
	 * The area, in which the text is displayed. 
	 */
	private JTextArea			textArea;
	
	/**
	 * The confirm button.
	 */
	private JButton				confirm;
	
	/**
	 * The abort button.
	 */
	private JButton				abort;
	
	/**
	 * The picture of the {@link LicenseAgreementDialog}.
	 */
	private JLabel				pic;
	
	/**
	 * The main panel of the {@link LicenseAgreementDialog}.
	 */
	private JPanel				mainPanel;
	
	/**
	 * The label, in which important notes are going to be displayed.
	 */
	private JLabel				note;
	
	/**
	 * The panel, which contains the area, to display the text.
	 */
	private JPanel				textAreaPanel;

	/**
	 * Creates a new instance of the {@link LicenseAgreementDialog}.
	 */
	private LicenseAgreementDialog() {
		super();
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.setModal(true);
		this.setAlwaysOnTop(true);
		this.setResizable(Configuration.RESIZABLE);
		this.setIconImage(Configuration.WINDOW_ICON);
		this.setMinimumSize(new Dimension(750, 400));

		textBundle = PropertyResolver.getBundle("text");

		this.setTitle(textBundle.get("LicenseAgreementDialog_title"));

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				accept = false;
				LicenseAgreementDialog.this.dispose();
			}
		});

		initComponents();
		drawComponents();
		addListener();

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Initializes the components of the {@link LicenseAgreementDialog}.
	 */
	private void initComponents() {
		confirm = new JButton(textBundle.get("LicenseAgreementDialog_confirm"));
		abort = new JButton(textBundle.get("LicenseAgreementDialog_abort"));
		pic = new JLabel(new ImageIcon(Configuration.LOGO));
		note = new JLabel(textBundle.get("LicenseAgreementDialog_note"));

		final Bundle licenseBundle = PropertyResolver.getBundle("license_agreement");
		textArea = new JTextArea(licenseBundle.get("text"), 20, 20);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textAreaPanel = new JPanel(new BorderLayout(), true);
		textAreaPanel.add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		textAreaPanel.setBorder(new TitledBorder(licenseBundle.get("header")));

		mainPanel = new JPanel(new GridBagLayout(), true);
	}

	/**
	 * Draws the components of the {@link LicenseAgreementDialog}. The
	 * {@link GridBagLayout} is used for drawing.
	 */
	private void drawComponents() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 3;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.weightx = 1;
		cons.weighty = 0;
		mainPanel.add(pic, cons);

		cons.insets = new Insets(15, 10, 10, 5);
		cons.gridy = 1;
		mainPanel.add(note, cons);

		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridy = 2;
		cons.weighty = 1;
		mainPanel.add(textAreaPanel, cons);

		cons.gridwidth = 1;
		cons.gridy = 3;
		cons.weighty = 0;
		mainPanel.add(new JPanel(true), cons);

		cons.insets = new Insets(5, 10, 5, 5);
		cons.gridx = 1;
		cons.weightx = 0;
		mainPanel.add(confirm, cons);

		cons.insets = new Insets(5, 5, 5, 10);
		cons.gridx = 2;
		mainPanel.add(abort, cons);

		this.setContentPane(mainPanel);
	}

	/**
	 * Adds a action-listener to the <code>confirm</code>- and the
	 * <code>abort</code>-button.
	 */
	private void addListener() {
		final ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (e.getSource() == confirm) {
					accept = true;
				} else if (e.getSource() == abort) {
					accept = false;
				}
				LicenseAgreementDialog.this.dispose();
			}
		};

		confirm.addActionListener(listener);
		abort.addActionListener(listener);
	}

	/**
	 * Returns the stored result of the user input.
	 * 
	 * @return Returns <strong>true</strong> if the license is accepted,
	 *         otherwise <strong>false</strong>.
	 */
	public boolean getResult() {
		return accept;
	}

	/**
	 * Returns the result of the user input.
	 * 
	 * @return Returns <strong>true</strong> if the license is accepted,
	 *         otherwise <strong>false</strong>.
	 */
	public static boolean accept() {
		final LicenseAgreementDialog licenseAgreementDialog = new LicenseAgreementDialog();
		return licenseAgreementDialog.getResult();
	}
}
