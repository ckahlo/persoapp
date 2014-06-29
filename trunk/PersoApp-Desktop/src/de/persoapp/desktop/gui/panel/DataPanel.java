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
 * The <tt>DataPanel</tt> provides the platform to mark user data from the
 * electronic identity card for sending.
 * <p>
 * <code>public class DataPanel extends JPanel</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class DataPanel extends JPanel {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long				serialVersionUID	= -950928041357540864L;

	/**
	 * The position integer array of the <tt>JCheckboxes</tt>.
	 */
	private static final int[]				pos					= new int[] { 0, 1, 2, 8, 9, 10, 11, 12, 13, 14, 15,
			16, 17, 20, 24, 26									};

	/**
	 * The <tt>JCheckBoxes</tt> provides the possibilities to mark data for sending.
	 */
	@SuppressWarnings("unused")
	private JCheckBox						vorname, name, doktorgrad, anschrift, geburtsdatum, geburtsort,
			kuenstlername, ausweistyp, land, ablaufdatum, wohnortbestaetigung, altersverifikation, pseudonym,
			geburtsname, nebenbestimmungen, nationalitaet;
	
	/**
	 * The <tt>JCheckBox-Array</tt> for easy access and iteration over the defined <tt>JCheckboxes</tt>.
	 */
	private JCheckBox[]						components;

	/**
	 * The necessary <tt>JPanels</tt> which holds the defined
	 * <tt>JCheckBoxes</tt> for user input.
	 */
	private JPanel							mRequiredPanel, mOptionalPanel;

	/**
	 * The <tt>bundle</tt> which resolves the necessary properties.
	 */
	private final PropertyResolver.Bundle	textBundle;

	/**
	 * Constructs a new instance of the {@link DataPanel}. The constructed Panel
	 * is double-buffered for advanced displaying.
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
	 * Initializes the member-attributes of the {@link DataPanel}. This
	 * includes the instantiation of the member-attributes, defined
	 * <tt>JCheckBoxes</tt> and the setup of the layout of the JPanel.
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
	 * Set up the layout of the required panel and the optional panel and thus
	 * of the {@link DataPanel}.
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
	 * Set all <tt>JCheckBoxes</tt> into a "not selected" state. Disables and
	 * removes the boxes from the required panel and from the optional panel. The
	 * required panel and the optional panel are member attributes of the
	 * {@link DataPanel}.
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
	 * fills in specific certificate informations from the given {@link IEAC_Info}.
	 * <ul>
	 * <li>The result of the required chat.</li>
	 * <li>The result of the optional chat.</li>
	 * <li>Additionally selects all defined JCheckBoxes
	 * in the {@link DataPanel}.</li>
	 * </ul>
	 * Inserts the 
	 * @param eacInfo
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
	 * Returns a specific value which is calculated from all selected
	 * {@link JCheckBox} objects in the given dialog.
	 * 
	 * @return Returns the calculated result from all selected {@link JCheckBox}
	 *         objects.
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
	 * Fills a panel with the given List of {@link JCheckBox} objects and 
	 * adds additionally <tt>JPanel</tt> objects to the given panel.
	 * <p>
	 * Just one panel is added, if the number of JCheckbox objects is even.
	 * Two panels are added, if the number of JCheckbox objects is odd.
	 * </p>
	 * 
	 * @param panel - The given panel.
	 * @param boxes - The list of {@link JCheckBox} objects to add.
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
	 * Collects all <tt>JCheckBoxes which are selected and not enabled.
	 * Adds the collected <tt>JCheckBoxes</tt> to the required panel and
	 * does the set up for the panel layout.
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
	 * Collects all <tt>JCheckBoxes</tt> which arn't selected and enabled.
	 * Adds the collected <tt>JCheckBoxes</tt> to the optional panel and 
	 * does the set up the panel layout.
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
