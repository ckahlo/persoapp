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
 * The <tt>MyPasswordField</tt>-class contains the logic for storing and
 * displaying <tt>Pins</tt> in a safe and correct way.
 * <p>
 * <code>public class MyPasswordField extends JPasswordField</code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public class MyPasswordField extends JPasswordField {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization.
	 */
	private static final long	serialVersionUID	= 5514819391155385931L;

	/**
	 * The <tt>BULLET</tt> for displaying in exchange for the <tt>LETTER</tt>.
	 */
	private static final char	BULLET				= '\u2022';
	
	/**
	 * The plain <tt>LETTER</tt>.
	 */
	private static final char	LETTER				= '\u0000';

	/**
	 * The index of the current position of the inserted values.
	 */
	private int					index;
	
	/**
	 * The parent {@link PinPanel}.
	 */
	private PinPanel			parent;

	/**
	 * Creates a new instance of {@link MyPasswordField}.
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
	 * Returns the inserted pin of the field.
	 * 
	 * @return Returns the inserted pin.
	 */
	public int getValue() {
		try {
			return Integer.parseInt(new String(getPassword()));
		} catch (final NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Shows the plain text, if <tt>show</tt> is set to <strong>true</strong>,
	 * otherwise the text is hidden.
	 * 
	 * @param show
	 *            - Can be true or false.
	 */
	@Deprecated
	public void showPlainText(final boolean show) {
		setEchoChar(show ? LETTER : BULLET);
	}

	/**
	 * Returns the current index.
	 * 
	 * @return Returns the current active index.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Sets the given value as current index.
	 * 
	 * @param index
	 *            - The current index. Should not be negative.
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * Finds the <tt>parent pin panel</tt> in a recursive way. Stores the
	 * <tt>parent pin panel</tt> when it is found.
	 * 
	 * @param c
	 *            - The container, which haves a <tt>parent pin panel</tt>.
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
	 * The class for internal text documents, to store text.
	 * <p>
	 * <code>private class IntTextDocument extends PlainDocument</code>
	 * </p>
	 * 
	 * @author Christian Kahlo
	 */
	private class IntTextDocument extends PlainDocument {

		/**
		 * The <tt>serialVersionUID</tt> which is necessary for serialization.
		 */
		private static final long	serialVersionUID	= -3244520335727589902L;

		/**
		 * Creates a new instance of {@link InternalTextDocument}.
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
