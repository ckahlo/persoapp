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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.util.TimeZone;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.client.PropertyResolver.Bundle;
import de.persoapp.desktop.gui.MyTitledBorder;

/**
 * The <tt>ServiceProviderPanel</tt> displays all informations about the used
 * <tt>ServiceProvider</tt>.
 * <p>
 * <code>public class ServiceProviderPanel extends JPanel</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class ServiceProviderPanel extends JPanel {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long	serialVersionUID	= 1064043770147009097L;

	/**
	 * The constant for <tt>bold</tt> text style.
	 */
	public static final String	BOLD				= "Bold";
	
	/**
	 * The constant for <tt>normal</tt> text style.
	 */
	public static final String	NORMAL				= "Normal";

	/**
	 * The necessary {@link JTextPane} to display the
	 * <tt>ServiceProvider Informations</tt>.
	 */
	private JTextPane			textPane;

	/**
	 * Constructs a new instance of the {@link ServiceProviderPanel}. The
	 * constructed panel is double-buffered for advanced displaying.
	 */
	public ServiceProviderPanel() {
		super();
		this.setDoubleBuffered(true);
		this.setLayout(new BorderLayout());
		this.setBorder(new MyTitledBorder(PropertyResolver.getBundle("text").get("ServiceProviderPanel_title")));

		drawContent();
	}

	/**
	 * Draws the content of the {@link ServiceProviderPanel}.
	 */
	private void drawContent() {
		textPane = new JTextPane();
		textPane.setEditable(false);

		Style style = textPane.addStyle(BOLD, null);
		StyleConstants.setBold(style, true);
		style = textPane.addStyle(NORMAL, null);

		this.add(new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);
	}

	/**
	 * Adds the given <tt>text</tt> in the given <tt>style</tt> to the
	 * {@link ServiceProviderPanel}.
	 * 
	 * @param text
	 *            - The given text.
	 * @param style
	 *            - The given style.
	 */
	private void addText(final String text, final String style) {
		final Document doc = textPane.getDocument();
		try {
			doc.insertString(doc.getLength(), text, textPane.getStyle(style));
		} catch (final BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears the content of the {@link ServiceProviderPanel}.
	 */
	public void clear() {
		textPane.setText("");
	}

	/**
	 * Fills in the certificate informations from the given {@link IEAC_Info}.
	 * This includes the following informations:
	 * <p>
	 * <ul>
	 * <li>Subject Name</li>
	 * <li>Subject URL</li>
	 * <li>Effective Date - the current date</li>
	 * <li>Expiration Date - the date of expiration</li>
	 * <li>Transaction Info</li>
	 * <li>Terms of Usage</li>
	 * </ul>
	 * </p>
	 * 
	 * @param eacInfo
	 *            - The given {@link IEAC_Info}.
	 */
	public void fillCertificate(final IEAC_Info eacInfo) {
		clear();
		addText(eacInfo.getSubjectName() + "\n", BOLD);
		addText(eacInfo.getSubjectURL() + "\n", NORMAL);

		final Bundle tb = PropertyResolver.getBundle("text");
		final DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		addText(tb.get("ServiceProviderPanel_validity") + ": " + df.format(eacInfo.getEffectiveDate()) + " - "
				+ df.format(eacInfo.getExpirationDate()) + "\n\n", NORMAL);

		if (eacInfo.getTransactionInfo() != null) {
			addText(tb.get("ServiceProviderPanel_transactionInfo") + ":\n" + eacInfo.getTransactionInfo() + "\n\n",
					BOLD);
		}

		addText(eacInfo.getTermsOfUsage().trim(), NORMAL);
	}

	@Override
	public void addMouseListener(final MouseListener l) {
		super.addMouseListener(l);
		textPane.addMouseListener(l);
	}

	@Override
	public Dimension getPreferredSize() {
		/*
		 * This fix is needed as the textPane's preferred size differs with
		 * changing text.
		 */
		final Dimension d = super.getPreferredSize();
		d.width = 420;
		return d;
	}
}
