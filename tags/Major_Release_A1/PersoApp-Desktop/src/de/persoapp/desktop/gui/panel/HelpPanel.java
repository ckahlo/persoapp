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
package de.persoapp.desktop.gui.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.gui.MyTitledBorder;

/**
 * @author ckahlo
 * 
 */
public class HelpPanel extends JPanel {

	private static final long				serialVersionUID	= 4377094333650746341L;

	public static final String				BOLD				= "Bold";
	public static final String				NORMAL				= "Normal";

	private JTextPane						textPane;
	private JScrollPane						scrollPane;
	private JLabel							pinStatus, inputRequired;

	private final PropertyResolver.Bundle	textBundle;

	public HelpPanel() {
		super();

		textBundle = PropertyResolver.getBundle("text");
		this.setDoubleBuffered(true);
		this.setLayout(new GridBagLayout());
		this.setBorder(new MyTitledBorder(textBundle.get("help")));

		initComponents();
		drawComponents();
	}

	private void initComponents() {
		textPane = new JTextPane();
		textPane.setEditable(false);
		final DefaultCaret caret = (DefaultCaret) textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

		scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		Style style = textPane.addStyle(BOLD, null);
		StyleConstants.setBold(style, true);
		style = textPane.addStyle(NORMAL, null);

		pinStatus = new JLabel("");
		pinStatus.setFont(new Font(Configuration.FONT, Font.BOLD, 12));

		inputRequired = new JLabel("");
		inputRequired.setFont(new Font(Configuration.FONT, Font.PLAIN, 12));
		inputRequired.setVisible(false);
	}

	private void drawComponents() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 1;
		cons.weighty = 1;

		this.add(scrollPane, cons);

		cons.gridy = 1;
		cons.weighty = 0;
		this.add(pinStatus, cons);

		cons.gridy = 2;
		this.add(inputRequired, cons);
	}

	private void setDescription(final String text, final String style) {
		final Document doc = textPane.getDocument();
		try {
			doc.insertString(doc.getLength(), text, textPane.getStyle(style));
		} catch (final BadLocationException e) {
			e.printStackTrace();
		}
	}

	public void clear() {
		final Document doc = textPane.getDocument();
		try {
			doc.remove(0, doc.getLength());
		} catch (final BadLocationException e) {
			e.printStackTrace();
		}
		((MyTitledBorder) this.getBorder()).setTitle(textBundle.get("help"));
		this.repaint();
	}

	public void setText(final String header, final String description) {
		clear();
		((MyTitledBorder) this.getBorder()).setTitle(header);
		setDescription(description, NORMAL);
		this.repaint();
	}

	public void setText(final String header, final String description, final boolean saveScrollState) {
		if (saveScrollState) {
			final Point position = scrollPane.getViewport().getViewPosition();
			setText(header, description);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					scrollPane.getViewport().setViewPosition(position);
				}
			});
		} else {
			setText(header, description);
		}
	}

	public void setPINState(final int attempts) {
		inputRequired.setVisible(false);

		switch (attempts) {
			case 3:
			case 2:
				pinStatus.setText(textBundle.get("HelpPanel_PIN_attempts") + " " + attempts);
				break;
			case 1:
				pinStatus.setText(textBundle.get("HelpPanel_PIN_attempts") + " 1");
				inputRequired.setText(textBundle.get("HelpPanel_requires_CAN"));
				inputRequired.setVisible(true);
				break;
			case 0:
				pinStatus.setText(textBundle.get("HelpPanel_PIN_blocked"));
				inputRequired.setText(textBundle.get("HelpPanel_requires_PUK"));
				inputRequired.setVisible(true);
				break;
			case 255:
				pinStatus.setText(textBundle.get("HelpPanel_PIN_deactivated"));
				break;
			case -1:
				pinStatus.setText(textBundle.get("HelpPanel_PIN_no_card"));
				break;
			default:
				pinStatus.setText("");
				break;
		}
	}

	@Override
	public void addMouseListener(final MouseListener l) {
		super.addMouseListener(l);
		textPane.addMouseListener(l);
		scrollPane.addMouseListener(l);
		pinStatus.addMouseListener(l);
		inputRequired.addMouseListener(l);
	}

	@Override
	public void removeMouseListener(final MouseListener l) {
		super.removeMouseListener(l);
		textPane.removeMouseListener(l);
		scrollPane.removeMouseListener(l);
		pinStatus.removeMouseListener(l);
		inputRequired.removeMouseListener(l);
	}

	@Override
	public Dimension getPreferredSize() {
		/*
		 * This fix is needed as the textPane's preferred size differs with
		 * changing text.
		 */
		final Dimension d = super.getPreferredSize();
		d.width = 178;
		return d;
	}

	@Override
	public Dimension getMinimumSize() {
		/*
		 * If a comfort reading device is plugged, the pin panel displays text.
		 * This text requests too much space. For this case this fix is needed.
		 */
		final Dimension d = super.getMinimumSize();
		d.width = 178;
		return d;
	}
}
