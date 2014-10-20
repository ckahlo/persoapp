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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.gui.MyTitledBorder;

/**
 * <p>
 * The DataPanel provides the platform for the user to share his
 * personal data which are previously requested by an entity, like an
 * administrative body or an online-shop that supports eID-Shopping, in a
 * process related to the online identity card function.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class DataPanel extends JPanel {

	private static final long				serialVersionUID	= -950928041357540864L;

	/**
	 * The position integer array of the JCheckboxes.
	 */
	private static final int[]				pos					= new int[] { 0, 1, 2, 8, 9, 10, 11, 12, 13, 14, 15,
			16, 17, 20, 24, 26									};

	/**
	 * The individual data as JCheckBoxes.
	 */
	@SuppressWarnings("unused")
	private JCheckBox						vorname, name, doktorgrad, anschrift, geburtsdatum, geburtsort,
			kuenstlername, ausweistyp, land, ablaufdatum, wohnortbestaetigung, altersverifikation, pseudonym,
			geburtsname, nebenbestimmungen, nationalitaet;
	
	/**
	 * The JCheckBox-Array for easy access and iteration over the defined JCheckboxes.
	 */
	private JCheckBox[]						components;

	/**
	 * The necessary JPanels which shows the user data.
	 * <ul>
	 * <li>mRequiredPanel - Shows an overview of the data, which is requested by an entity in the current eID-Session.</li>
	 * <li>mOptionalPanel - Shows an overview of the data, which is not requested by an entity in the current eID-Session.</li>
	 * </ul>
	 */
	private JPanel							mRequiredPanel, mOptionalPanel;

	/**
	  *Localized message bundle for user interaction.	 
	  */
	private final PropertyResolver.Bundle	textBundle;

	/**
	 * Constructs a new instance of the {@link DataPanel}. The constructed
	 * panel is double-buffered to achieve benefits by extended use of
	 * memory.
	 */
	public DataPanel() {
		super();
		this.textBundle = PropertyResolver.getBundle("text");

		this.setDoubleBuffered(true);
		this.setLayout(new GridBagLayout());

		initComponents();
		drawComponents();
	}

	/**
	 * <p>
	 * Initializes the components. This includes the instantiation of the
	 * member-attributes, defined JCheckBoxes and the setup of the
	 * layout of the JPanel.
	 * </p>
	 * <p>
	 * The {@link GridBagLayout} is used to manage the layout.
	 * </p>
	 */
	private void initComponents() {
		components = new JCheckBox[pos.length];

		int i = 0;

		// initialized again later down below
		components[i++] = altersverifikation = new JCheckBox(textBundle.get("DataPanel_age_verification")); // Bit 0
		components[i++] = wohnortbestaetigung = new JCheckBox(textBundle.get("DataPanel_address_confirmation")); // Bit 1

		components[i++] = pseudonym = new JCheckBox(textBundle.get("DataPanel_rid"));						// Bit 2

		// Privileged Terminal, Bit 3
		// CAN allowed, Bit 4
		// PIN Management, Bit 5
		// Install Certificate, Big 6
		// Install Qualified Certificate, Bit 7

		components[i++] = ausweistyp = new JCheckBox(textBundle.get("DataPanel_card_type"));				// DG1, Bit 8
		components[i++] = land = new JCheckBox(textBundle.get("DataPanel_issuing_country"));				// DG2, Bit 9
		components[i++] = ablaufdatum = new JCheckBox(textBundle.get("DataPanel_date_of_expiry"));			// DG3, Bit 10
		components[i++] = vorname = new JCheckBox(textBundle.get("DataPanel_first_name"));					// DG4, Bit 11
		components[i++] = name = new JCheckBox(textBundle.get("DataPanel_name"));							// DG5, Bit 12
		components[i++] = kuenstlername = new JCheckBox(textBundle.get("DataPanel_pseudonym"));				// DG6, Bit 13
		components[i++] = doktorgrad = new JCheckBox(textBundle.get("DataPanel_doctoral_degree"));			// DG7, Bit 14
		components[i++] = geburtsdatum = new JCheckBox(textBundle.get("DataPanel_birthday"));				// DG8, Bit 15
		components[i++] = geburtsort = new JCheckBox(textBundle.get("DataPanel_birthplace"));				// DG9, Bit 16
		components[i++] = nationalitaet = new JCheckBox(textBundle.get("DataPanel_nationality"));			// DG10, Bit 17
		// Sex, DG10, Bit 18
		// Optional Data, DG11, Bit 19
		components[i++] = geburtsname = new JCheckBox(textBundle.get("DataPanel_birth_name"));				// DG13, Bit 20
		components[i++] = anschrift = new JCheckBox(textBundle.get("DataPanel_address"));					// DG17, Bit 24

		components[i++] = nebenbestimmungen = new JCheckBox(textBundle.get("DataPanel_auxiliary_conditions_1")); // DG19, Bit 26

		mRequiredPanel = new JPanel(true);
		mRequiredPanel.setLayout(new GridBagLayout());
		mRequiredPanel.setBorder(new MyTitledBorder(textBundle.get("DataPanel_header_required")));

		mOptionalPanel = new JPanel(true);
		mOptionalPanel.setLayout(new GridBagLayout());
		mOptionalPanel.setBorder(new MyTitledBorder(textBundle.get("DataPanel_header_optional")));

		clear();
	}

	/**
	 * Draws the components.
	 */
	private void drawComponents() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		cons.weightx = 1;
		cons.weighty = 0;

		this.add(mRequiredPanel, cons);

		cons.gridy = 1;
		cons.weighty = 1;
		this.add(mOptionalPanel, cons);
	}

	/**
	 * Removes the overview of the user data, which are requested at the last
	 * time.
	 */
	public void clear() {
		for (final JCheckBox box : components) {
			box.setSelected(false);
			box.setEnabled(false);
		}

		for (final Component component : mRequiredPanel.getComponents()) {
			mRequiredPanel.remove(component);
		}

		for (final Component component : mOptionalPanel.getComponents()) {
			mOptionalPanel.remove(component);
		}
	}
	
	/**
	 * Removes the currently displayed content of the {@link DataPanel} and
	 * prepares the internal data structures for displaying an overview of the
	 * requested user data and the optional user data.
	 * <ul>
	 * <li>The required chat, which contains an overview of the data, which is
	 * requested by an entity in the current eID-Session.</li>
	 * <li>The result of the optional chat, which contains an overview of the
	 * data, which is not requested by an entity in the current eID-Session.</li>
	 * </ul>
	 * 
	 * @param eacInfo
	 *            - The <em>Extended Access Control</em>-structure, which
	 *            contains the session specific data.
	 */
	public void fillCertificate(final IEAC_Info eacInfo) {
		clear();

		final long req = eacInfo.getRequiredChat();
		final long opt = eacInfo.getOptionalChat();

		for (int i = 0; i < pos.length; i++) {
			if ((req & 1L << pos[i]) != 0) {
				components[i].setSelected(true);
				components[i].setEnabled(false);
			}
		}

		for (int i = 0; i < pos.length; i++) {
			if ((opt & 1L << pos[i]) != 0) {
				components[i].setSelected(false);
				components[i].setEnabled(true);
			}
		}

		if (eacInfo.getVerifyAge() != 0) {
			altersverifikation.setText(textBundle.get("DataPanel_age_verification") + " (>= " + eacInfo.getVerifyAge()
					+ ")");
		} else {
			altersverifikation.setText(textBundle.get("DataPanel_age_verification"));
		}

		//		shall not be included according to BSI Meeting on CeBit 2012
		//		if(eacInfo.getVerifyCommunityID() != null) {
		//			wohnortbestaetigung.setText(
		//					wohnortbestaetigung.getText() + " \n(" +
		//					eacInfo.getVerifyCommunityID() + ")");
		//		}

		fillRequiredPanel();
		fillOptionalPanel();
	}

	/**
	 * Returns a long value, which represents the selected user data of the
	 * <em>CHAT</em>.
	 * 
	 * @return Returns a long value, which represents the selected user data of
	 *         the <em>CHAT</em>.
	 */
	public long getResultChat() {
		long result = 0;

		for (int i = 0; i < components.length; i++) {
			result += components[i].isSelected() ? 1 << pos[i] : 0;
		}

		return result;
	}

	@Override
	public void addMouseListener(final MouseListener l) {
		super.addMouseListener(l);
		for (final JCheckBox box : components) {
			box.addMouseListener(l);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		final Dimension preferredSize = super.getPreferredSize();
		if (preferredSize.height < 200) {
			preferredSize.height = 200;
		}

		return preferredSize;
	}

	/**
	 * Places {@link JCheckBox}-objects on the provided <em>panel</em> according
	 * to the predefined {@link GridBagConstraints}.
	 * 
	 * @param panel
	 *            - The <em>panel</em> to be filled.
	 * @param boxes
	 *            - The list of {@link JCheckBox} objects to add to the
	 *            <em>panel</em>.
	 */
	private void fillPanel(final JPanel panel, final List<JCheckBox> boxes) {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 0.5;
		cons.weighty = 0;

		for (final JCheckBox box : boxes) {
			panel.add(box, cons);

			if (cons.gridx == 0) {
				cons.gridx = 1;
			} else {
				cons.gridx = 0;
				cons.gridy++;
			}
		}

		if (cons.gridx == 1) {
			panel.add(new JPanel(true), cons);
		}

		cons.fill = GridBagConstraints.BOTH;
		cons.gridx = 0;
		cons.gridy++;
		cons.insets = new Insets(0, 0, 0, 0);
		cons.gridwidth = 2;
		cons.weightx = 1;
		cons.weighty = 1;
		panel.add(new JPanel(true), cons);
	}

	/**
	 * Collects all JCheckBoxes which are selected and not enabled.
	 * Adds the collected JCheckBoxes to the panel of the required user
	 * data and does the set up for the panel layout.
	 */
	private void fillRequiredPanel() {
		final List<JCheckBox> requiredBoxes = new ArrayList<JCheckBox>();
		for (final JCheckBox box : components) {
			if (box.isSelected() && !box.isEnabled()) {
				requiredBoxes.add(box);
			}
		}

		if (requiredBoxes.isEmpty()) {
			mRequiredPanel.setBorder(null);
		} else {
			if (mRequiredPanel.getBorder() == null) {
				mRequiredPanel.setBorder(new MyTitledBorder(textBundle.get("DataPanel_header_required")));
			}

			fillPanel(mRequiredPanel, requiredBoxes);
		}
	}

	/**
	 * Collects all JCheckBoxes which arn't selected and enabled. Adds
	 * the collected JCheckBoxes to the panel of the optional user data
	 * and does the set up the panel layout.
	 */
	private void fillOptionalPanel() {
		final List<JCheckBox> optionalBoxes = new ArrayList<JCheckBox>();
		for (final JCheckBox box : components) {
			if (!box.isSelected() && box.isEnabled()) {
				optionalBoxes.add(box);
			}
		}

		if (optionalBoxes.isEmpty()) {
			mOptionalPanel.setBorder(null);
		} else {
			if (mOptionalPanel.getBorder() == null) {
				mOptionalPanel.setBorder(new MyTitledBorder(textBundle.get("DataPanel_header_optional")));
			}

			fillPanel(mOptionalPanel, optionalBoxes);
		}
	}
}
