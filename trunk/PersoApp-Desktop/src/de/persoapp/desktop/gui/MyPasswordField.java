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
package de.persoapp.desktop.gui;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import de.persoapp.desktop.gui.panel.PinPanel;

/**
 * <p>
 * The MyPasswordField contains the logic for safely caching the
 * numbers of an inserted <em>PIN</em> in a safe and correct way.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class MyPasswordField extends JPasswordField {
	
	private static final long	serialVersionUID	= 5514819391155385931L;

	/**
	 * The BULLET for displaying in exchange for the LETTER.
	 */
	private static final char	BULLET				= '\u2022';
	
	/**
	 * The plain LETTER.
	 */
	private static final char	LETTER				= '\u0000';

	/**
	 * The index of the field.
	 */
	private int					index;
	
	/**
	 * The parent {@link PinPanel}.
	 */
	private PinPanel			parent;

	/**
	 * Creates and initializes a new {@link MyPasswordField}.
	 */
	public MyPasswordField() {
		super();
		setEchoChar(BULLET);
		this.setHorizontalAlignment(CENTER);

		final Dimension dim = new Dimension(28, 28);
		this.setMinimumSize(dim);
		this.setPreferredSize(dim);
	}

	@Override
	protected Document createDefaultModel() {
		return new IntTextDocument();
	}

	@Override
	public boolean isValid() {
		try {
			Integer.parseInt(new String(getPassword()));
			return true;
		} catch (final Exception e) {
			return false;
		}
	}

	/**
	 * Returns the inserted number of the field.
	 * 
	 * @return Returns the inserted number.
	 */
	public int getValue() {
		try {
			return Integer.parseInt(new String(getPassword()));
		} catch (final NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * <p>
	 * Shows the plain number, if <em>show</em> is set to <b>true</b>, otherwise
	 * the text is hidden.
	 * </p>
	 * 
	 * @param show
	 *            - The inserted numbers are displayed as plain text, if set to
	 *            <b>true</b>. At the other way, the text is hidden.
	 */
	@Deprecated
	public void showPlainText(final boolean show) {
		setEchoChar(show ? LETTER : BULLET);
	}

	/**
	 * Returns the index of the current field.
	 * 
	 * @return Returns the index of the current field.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the provided value as current index.
	 * 
	 * @param index
	 *            - The current index. Should not be negative.
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * <p>
	 * Finds the parent pin panel in a recursive way. Stores the
	 * parent pin panel in case of a success.
	 * </p>
	 * 
	 * @param c
	 *            - The container <em>c</em>, which is going to be searched
	 *            after a {@link PinPanel}.
	 */
	private void findParentPinPanel(final Container c) {
		if (c != null) {
			if (c instanceof PinPanel) {
				parent = (PinPanel) c;
			} else {
				findParentPinPanel(c.getParent());
			}
		}
	}

	/**
	 * <p>
	 * The InTextDocument contains the logic to process inserted
	 * digits.
	 * </p>
	 * 
	 * @author Christian Kahlo
	 */
	private class IntTextDocument extends PlainDocument {

		private static final long	serialVersionUID	= -3244520335727589902L;

		/**
		 * Creates and initializes a new {@link InternalTextDocument}.
		 */
		public IntTextDocument() {
			super();
		}

		@Override
		public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {

			final String old = getText(0, getLength());
			if (str == null) {
				return;
			}
			try {
				final int value = Integer.parseInt(str);
				if (value <= 9 && value >= 0) {
					if (old.length() == 1) {
						super.replace(0, 1, str, a);
					} else {
						super.insertString(0, str, a);
					}
				} else if (value > 9) {
					if (parent == null) {
						findParentPinPanel(MyPasswordField.this);
					}
					parent.paste(str, index);
				}
			} catch (final NumberFormatException e) {
			} catch (final NullPointerException e) {
			}
		}
	}
}
