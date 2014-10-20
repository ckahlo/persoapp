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
 *          PersoApp ist Freie Software: Sie k�nnen es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          sp�teren ver�ffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 * 
 *          PersoApp wird in der Hoffnung, dass es n�tzlich sein wird, aber OHNE
 *          JEDE GEW�HRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gew�hrleistung der MARKTF�HIGKEIT oder EIGNUNG F�R EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License f�r weitere
 *          Details.
 * 
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 * 
 */
package de.persoapp.desktop.gui.frame;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.gui.MyTitledBorder;

/**
 * <p>
 * The AboutFrame displays the links to the homepage and the support
 * and further informations about the PersoApp Application.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class AboutFrame extends JFrame {

	private static final long				serialVersionUID	= 7707227028876424642L;

	/**
	 * The panels, that provides the content and functionality of the
	 * {@link AboutFrame}.
	 */
	private JPanel							mainPanel, contentPanel;
	
	/**
	 * The labels to display necessary informations and the icon of the
	 * PersoApp Application.
	 */
	private JLabel							pic, homepage, support;
	
	/**
	 * The confirm button, to leave the {@link AboutFrame}.
	 */
	private JButton							confirm;
	
	/**
	 * Localized message bundle for user interaction.
	 */
	private final PropertyResolver.Bundle	textBundle;

	/**
	 * Constructs and initializes a new {@link AboutFrame} and shows it in the
	 * center of the screen.
	 */
	public AboutFrame() {
		super();
		textBundle = PropertyResolver.getBundle("text");

		// centered - this.pack() calculates optimized size
		this.setLocationRelativeTo(null);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(Configuration.RESIZABLE);
		this.setTitle(textBundle.get("window_title"));
		this.setIconImage(Configuration.WINDOW_ICON);
		this.setName("AboutFrame");

		initPanels();
		addListener();
		drawPanels();

		this.setAlwaysOnTop(true);
		this.requestFocus();
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Initializes all panels of the {@link AboutFrame}.
	 */
	private void initPanels() {
		mainPanel = new JPanel(true);
		mainPanel.setLayout(new GridBagLayout());

		pic = new JLabel(new ImageIcon(Configuration.LOGO));

		confirm = new JButton(textBundle.get("confirm"));

		homepage = new JLabel(getText("homepage_text"));

		support = new JLabel(getText("support_text"));

		contentPanel = getContentPanel();
	}

	/**
	 * Adds several listeners to the supported buttons and hyperlinks.
	 */
	private void addListener() {
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				AboutFrame.this.dispose();
			}
		});

		final MouseAdapter adapter = new MouseAdapter() {

			@Override
			public void mouseClicked(final MouseEvent e) {
				if (e.getSource() == homepage) {
					try {
						Desktop.getDesktop().browse(new URI(homepage.getText()));
					} catch (final Exception e1) {
						e1.printStackTrace();
					}
				} else if (e.getSource() == support) {
					try {
						Desktop.getDesktop().mail(
								new URI("mailto:" + support.getText()
										+ "?SUBJECT=PersoApp%20-%20eID%20Client%20Support"));
					} catch (final Exception e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void mouseEntered(final MouseEvent e) {
				if (e.getSource() instanceof JLabel) {
					((JLabel) e.getSource()).setFont(new Font(Configuration.FONT, Font.BOLD, 12));
				}
			}

			@Override
			public void mouseExited(final MouseEvent e) {
				if (e.getSource() instanceof JLabel) {
					((JLabel) e.getSource()).setFont(new Font(Configuration.FONT, Font.PLAIN, 12));
				}
			}
		};

		homepage.addMouseListener(adapter);
		support.addMouseListener(adapter);
	}

	/**
	 * Draws the panels of the {@link AboutFrame}. The panels have to be
	 * initialized before.
	 * 
	 * @see #initPanels()
	 */
	private void drawPanels() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(15, 5, 5, 5);
		cons.weightx = 1;
		cons.weighty = 0;
		mainPanel.add(pic, cons);

		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridy = 1;
		cons.weighty = 1;
		mainPanel.add(contentPanel, cons);

		cons.gridy = 2;
		cons.weighty = 0;
		cons.fill = GridBagConstraints.NONE;
		mainPanel.add(confirm, cons);

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	/**
	 * Creates and returns the ContentPanel of the {@link AboutFrame}.
	 * The ContentPanel displays all components of the whole
	 * {@link AboutFrame}.
	 * 
	 * @return Returns the newly created content panel.
	 */
	private JPanel getContentPanel() {
		final Font bold = new Font(Configuration.FONT, Font.BOLD, 12);

		final JPanel result = new JPanel(true);
		result.setBorder(new MyTitledBorder(getText("about")));
		result.setLayout(new GridBagLayout());

		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 0.5;
		cons.weighty = 0.25;
		JLabel label = new JLabel(getText("copyright_header"));
		label.setFont(bold);
		result.add(label, cons);

		cons.gridx = 1;
		result.add(new JLabel(getText("copyright_text")), cons);

		cons.gridx = 0;
		cons.gridy = 1;
		label = new JLabel(getText("homepage_header"));
		label.setFont(bold);
		result.add(label, cons);

		cons.gridx = 1;
		result.add(homepage, cons);

		cons.gridx = 0;
		cons.gridy = 2;
		label = new JLabel(getText("build_header"));
		label.setFont(bold);
		result.add(label, cons);

		cons.gridx = 1;
		result.add(
				new JLabel(PropertyResolver.getProperty("version", "buildDate") + ", "
						+ PropertyResolver.getProperty("version", "buildVersion") + "b"
						+ PropertyResolver.getProperty("version", "buildRevision") + "-"
						+ PropertyResolver.getProperty("version", "buildNo")), cons);

		cons.gridx = 0;
		cons.gridy = 3;
		label = new JLabel(getText("support_header"));
		label.setFont(bold);
		result.add(label, cons);

		cons.gridx = 1;
		result.add(support, cons);

		return result;
	}

	/**
	 * <p>
	 * Returns the text, which is stored in the properties according to the
	 * provided identifier.
	 * </p>
	 * 
	 * @param identifier
	 *            - The used identifier of the text.
	 * 
	 * @return Returns the stored <em>text</em> or <strong>null</strong> if no
	 *         text is stored in the properties by using the provided
	 *         identifier.
	 */
	private String getText(final String identifier) {
		return textBundle.get(this.getName() + "_" + identifier);
	}
}
