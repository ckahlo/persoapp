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
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.MainView;
import de.persoapp.desktop.gui.MyPasswordField;
import de.persoapp.desktop.gui.MyTitledBorder;
import de.persoapp.desktop.gui.frame.MainFrame;
import de.persoapp.desktop.gui.frame.NewChangePinFrame;

/**
 * The <tt>PinPanel</tt> contains {@link PinRows} to provide the option to
 * insert a <tt>Pin</tt>.
 * <p>
 * <code>public class PinPanel extends JPanel</code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public class PinPanel extends JPanel {

	/**
	 * The <tt>serialVersionUID</tt> which is necessary for serialization. 
	 */
	private static final long				serialVersionUID	= 1L;

	/**
	 * Stores the index of the <tt>last focused password field</tt>.
	 */
	private int								lastFocusedField;
	
	/**
	 * Stores the flag if the pin is activated.
	 */
	private boolean							pinActivated;

	/**
	 * Checks the last two rows for additionally safety, if set to <strong>true</strong>.
	 */
	private final boolean					checkLastTwoRows;

	/**
	 * Stores the used {@link ButtonPanel}.
	 */
	private final ButtonPanel				buttonPanel;
	
	/**
	 * Stores the label of the reading device.
	 */
	private JLabel							useReadingDeviceLabel;
	
	/**
	 * Stores the used {@link Window}.
	 */
	private Window							window;

	/**
	 * The used <tt>PasswordField</tt>, which holds the different passwords.
	 */
	private ArrayList<MyPasswordField>		fields;
	
	/**
	 * The used <tt>PinRows</tt>, which holds the different pins.
	 */
	private PinRow[]						pinRows;

	/*
	 * The cached sizes are needed for the comfort smartcard reader device. The
	 * content of this panel changes completely and returns different sizes.
	 * However, we want always the standard size.
	 */
	/**
	 * The minimum size of the cache.
	 */
	private final Dimension					mCachedMinimumSize;
	
	/**
	 * The preferred size of the cache.
	 */
	private final Dimension					mCachedPreferredSize;

	/**
	 * The <tt>bundle</tt> which resolves the necessary properties.
	 */
	private final PropertyResolver.Bundle	textBundle;

	/**
	 * Constructs a new instance of the {@link PinPanel} with just one {@link PinRow}.
	 * 
	 * @param buttonPanel - The given {@link ButtonPanel}.
	 * @param header - The given header.
	 * @param fieldCount - The given size of the {@link PinRow}.
	 */
	public PinPanel(final ButtonPanel buttonPanel, final String header, final int fieldCount) {
		this(buttonPanel, new String[] { header }, new int[] { fieldCount });
	}

	/**
	 * Constructs a new instance of the {@link PinPanel} and doesn't check the
	 * last two rows.
	 * 
	 * @param buttonPanel
	 *            - The given {@link ButtonPanel}.
	 * @param header
	 *            - The given <tt>header-array</tt>.
	 * @param fieldCounts
	 *            - The array of the numbers of <tt>PasswordFields</tt> are
	 *            going to be created, with their individual size.
	 */
	public PinPanel(final ButtonPanel buttonPanel, final String[] header, final int[] fieldCounts) {
		this(buttonPanel, header, fieldCounts, false);
	}

	/**
	 * Constructs a new instance of the {@link PinPanel}. The constructed Panel
	 * is double-buffered for advanced displaying.
	 * 
	 * @param buttonPanel
	 *            - The given {@link ButtonPanel}.
	 * @param header
	 *            - The given <tt>header-array</tt>.
	 * @param fieldCounts
	 *            - The array of the numbers of the used <tt>PasswordFields</tt>
	 *            . Each <tt>PasswordField</tt> is going to be created with
	 *            their individual size.
	 * @param checkLastTwoRows
	 *            - Checks the last two rows if <strong>true</strong>, otherwise
	 *            not.
	 */
	public PinPanel(final ButtonPanel buttonPanel, final String[] header, final int[] fieldCounts,
			final boolean checkLastTwoRows) {
		super();

		if (header.length != fieldCounts.length) {
			throw new IllegalArgumentException("Different array lengths");
		}

		textBundle = PropertyResolver.getBundle("text");
		this.setDoubleBuffered(true);
		this.setLayout(new GridBagLayout());

		this.buttonPanel = buttonPanel;
		this.lastFocusedField = 0;
		this.pinActivated = true;
		this.checkLastTwoRows = checkLastTwoRows;

		initCompontents(header, fieldCounts);
		drawComponents();

		checkCompletion();

		mCachedMinimumSize = super.getMinimumSize();
		mCachedPreferredSize = super.getPreferredSize();
	}

	/**
	 * Initializes the components of the {@link PinPanel}. Set up every title
	 * through the given <tt>header-array</tt> and creates the instances of the
	 * <tt>PinFields</tt>. The <tt>fieldCounts-array</tt> specifies how many
	 * instances are created.
	 * 
	 * @param header
	 *            - The given <tt>header-array</tt>.
	 * @param fieldCounts
	 *            - The array of the numbers of <tt>PasswordFields</tt> are
	 *            going to be created, with their individual size.
	 */
	private void initCompontents(final String[] header, final int[] fieldCounts) {
		pinRows = new PinRow[fieldCounts.length];
		for (int i = 0; i < fieldCounts.length; i++) {
			pinRows[i] = new PinRow(fieldCounts[i], header[i]);
		}

		fields = new ArrayList<MyPasswordField>();
		for (final PinRow row : pinRows) {
			fields.addAll(Arrays.asList(row.getPasswordFields()));
		}

		final MyKeyListener keyListener = new MyKeyListener();
		final MyFocusListener focusListener = new MyFocusListener();
		final MyDocumentListener documentListener = new MyDocumentListener();

		int i = 0;
		for (final MyPasswordField field : fields) {
			field.setIndex(i++);
			field.addKeyListener(keyListener);
			field.addFocusListener(focusListener);
			field.getDocument().addDocumentListener(documentListener);
		}

		useReadingDeviceLabel = new JLabel("<html><div style=\"text-align: center;\">"
				+ textBundle.get("use_reading_device").replace("\n", "<br>") + "</div></html>");
		useReadingDeviceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		useReadingDeviceLabel.setFont(new Font(Configuration.FONT, Font.BOLD, 12));
	}

	/**
	 * Draws the components of the <tt>PinPanel</tt>.
	 */
	private void drawComponents() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		cons.weightx = 1;

		if (pinActivated) {
			cons.weighty = 1D / pinRows.length;

			for (int i = 0; i < pinRows.length; i++) {
				cons.gridy = i;
				this.add(pinRows[i], cons);
			}
		} else {
			cons.gridy = 0;
			cons.weighty = 1;
			this.add(useReadingDeviceLabel, cons);
		}
	}

	/**
	 * Returns the number of pins.
	 * 
	 * @return Returns the number of pins.
	 */
	public int getRowCount() {
		return pinRows.length;
	}

	/**
	 * Returns the <tt>PinCode</tt> of the row which is identified through the
	 * given argument.
	 * 
	 * @param row
	 *           - Index of the <tt>PinRow</tt>.
	 */
	public byte[] getPinCode(final int row) {
		return pinRows[row].getPinCode();
	}

	/**
	 * Checks the completion of the creation of the {@link PinPanel}.
	 */
	public void checkCompletion() {
		if (pinActivated) {
			boolean enabled = true;

			for (final PinRow row : pinRows) {
				if (row.getPinCode() == null) {
					enabled = false;
					break;
				}
			}

			if (enabled && checkLastTwoRows && !pinMatch(pinRows.length - 2, pinRows.length - 1)) {
				enabled = false;
			}

			buttonPanel.getConfirm().setEnabled(enabled);
		} else {
			buttonPanel.getConfirm().setEnabled(true);
		}
	}

	/**
	 * Checks if the given pins are equal.
	 * 
	 * @param row1 - The first pin.
	 * @param row2 - The second pin.
	 * 
	 * @return Returns <strong>true</false> if the given pins are equal, otherwise <strong>false</strong>. 
	 */
	private boolean pinMatch(final int row1, final int row2) {
		//wenn nicht komplett ausgefÃ¼llt, eh null
		final byte[] pin = getPinCode(row1);
		final byte[] pinRep = getPinCode(row2);

		if (pin == null || pinRep == null || pin.length != pinRep.length) {
			return false;
		}

		for (int i = 0; i < pin.length; i++) {
			if (pin[i] != pinRep[i]) {
				//Hier kommt er nur rein, wenn alle Felder ausgefÃ¼llt sind und die letzten beiden
				//Reihen Ã¼berprÃ¼ft werden sollen, sonst nie!
				MainView.getInstance().showError(textBundle.get("pin_input_error"),
						textBundle.get("NewChangePinFrame_pin_input_error_new"));
				return false;
			}
		}

		return true;
	}

	/**
	 * This function is going to be called by the virtual <code>KeyPad</code>
	 * and fills the given text in the field that last had the focus.
	 */
	public void fillPanel(final String text) {
		fields.get(lastFocusedField).setText(text);
		if (lastFocusedField < fields.size() - 1) {
			fields.get(++lastFocusedField).requestFocus();
		} else {
			buttonPanel.setFocusToComponent(0);
		}
	}

	/**
	 * This function is going to be called by the virtual <tt>KeyPad</tt> and removes the
	 * content of the field that last had the focus.
	 */
	public void clearField() {
		if (fields.get(lastFocusedField).getPassword().length == 0 && lastFocusedField > 0) {
			fields.get(lastFocusedField - 1).setText("");
			fields.get(lastFocusedField - 1).requestFocus();
		} else {
			fields.get(lastFocusedField).setText("");
		}
	}

	/**
	 * Removes the content through the <code>Keypad</code> c-Button.
	 */
	public void clear() {
		removeContent();
		fields.get(0).requestFocus();
	}

	/**
	 * Removes the content of all <code>MyPasswordFields</code>.
	 */
	public void removeContent() {
		for (final MyPasswordField field : fields) {
			field.setText("");
		}
	}

	/**
	 * Enables the {@link PinPanel} if the argument is <strong>true</strong>, otherwise
	 * disables the {@link PinPanel}.
	 * 
	 * @param enabled
	 *            - The given argument.
	 */
	public void setPinEnabled(final boolean enabled) {
		if (enabled != this.pinActivated) {
			this.pinActivated = enabled;

			if (enabled) {
				this.remove(useReadingDeviceLabel);
			} else {
				for (final PinRow row : pinRows) {
					this.remove(row);
				}
			}

			drawComponents();
			this.repaint();
			this.validate();
			buttonPanel.getConfirm().setText(textBundle.get(pinActivated ? "confirm" : "continue"));
		}

		checkCompletion();

		if (enabled) {
			fields.get(0).requestFocus();
		} else {
			buttonPanel.getConfirm().requestFocus();
		}
	}

	/**
	 * This function is going to be called in {@link MyPasswordField}, if a
	 * number bigger than 9 is inserted.
	 * 
	 * @param value
	 *            The value can just be an Integer greater than 9. This is checked
	 *            in <tt>MyPasswordField</tt>.
	 * 
	 * @param index
	 *            Index of the specific <tt>MyPasswordField</tt>.
	 */
	public void paste(final String value, final int index) {
		PinRow row = null;
		for (final PinRow aRow : pinRows) {
			if (aRow.containsField(index)) {
				row = aRow;
				break;
			}
		}
		if (row == null || row.getPasswordFields().length != value.length()) {
			return;
		}

		for (int i = 0; i < value.length(); i++) {
			row.getPasswordFields()[i].setText(String.valueOf(value.charAt(i)));
		}
	}

	@Override
	public Dimension getMinimumSize() {
		return mCachedMinimumSize;
	}

	@Override
	public Dimension getPreferredSize() {
		return mCachedPreferredSize;
	}

	/**
	 * The <tt>PinRow</tt> provides all used <tt>PinFields</tt>.
	 * 
	 * @author Christian Kahlo
	 */
	private class PinRow extends JPanel {

		/**
		 * The <tt>serialVersionUID</tt> which is necessary for serialization.
		 */
		private static final long		serialVersionUID	= 1L;
		
		/**
		 * The used <tt>Password fields</tt>.
		 */
		private final MyPasswordField[]	fields;

		/**
		 * Constructs a new instance of the {@link PinRow}. Also draws and
		 * initializes all components. The {@link GridBagLayout} is used for
		 * drawing.
		 * 
		 * @param fields
		 *            - The given <tt>PinFields</tt>.
		 * 
		 * @param header
		 *            - The header as the title.
		 */
		public PinRow(final int fields, final String header) {
			super(true);
			this.setLayout(new GridBagLayout());
			this.setBorder(new MyTitledBorder(header));

			this.fields = new MyPasswordField[fields];
			for (int i = 0; i < fields; i++) {
				this.fields[i] = new MyPasswordField();
			}

			final GridBagConstraints cons = new GridBagConstraints();
			cons.fill = GridBagConstraints.BOTH;
			cons.gridheight = 1;
			cons.gridwidth = 1;
			cons.gridx = 0;
			cons.gridy = 0;
			cons.insets = new Insets(5, 2, 5, 2);
			cons.weightx = 0.5d;
			cons.weighty = 0;
			this.add(new JPanel(true), cons);

			cons.weightx = 0;
			for (int i = 0; i < fields; i++) {
				cons.gridx = i + 1;
				this.add(this.fields[i], cons);
			}

			cons.gridwidth = 1;
			cons.weightx = 0.5d;
			cons.gridx = fields + 1;
			this.add(new JPanel(true), cons);

			cons.insets = new Insets(0, 0, 0, 0);
			cons.gridx = 0;
			cons.gridy = 1;
			cons.gridwidth = fields + 2;
			cons.weightx = 1;
			cons.weighty = 1;
			this.add(new JPanel(true), cons);
		}

		/**
		 * Returns the <tt>PinCode</tt> if anyone is typed in, otherwise
		 * <strong>null</strong>.
		 * 
		 * @return Returns the <tt>PinCode</tt> if anyone is typed in, otherwise
		 *         <strong>null</strong>.
		 */
		public byte[] getPinCode() {
			final char[] res = new char[fields.length];
			for (int i = 0; i < fields.length; i++) {
				try {
					res[i] = fields[i].getPassword()[0];
				} catch (final Exception e) {
					return null;
				}
			}

			// use system-default encoding as that's the same as inside the fields
			return new String(res).getBytes();
		}
		
		/**
		 * Returns the <tt>Array</tt> of the used password fields.
		 * 
		 * @return Returns the <tt>Array</tt> of the used password fields.
		 */
		public MyPasswordField[] getPasswordFields() {
			return fields;
		}

		/**
		 * Checks if a specific {@link MyPasswordField} is located in the array
		 * of used <tt>MyPasswordFields</tt>.
		 * 
		 * @param index
		 *            - The index of the specific password field.
		 * 
		 * @return Returns <strong>true</strong> if the password field is
		 *         located in the array, otherwise <strong>false</strong>.
		 */
		public boolean containsField(final int index) {
			for (final MyPasswordField field : fields) {
				if (index == field.getIndex()) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * The <ttMyKeyListener</tt> listens to events of the keys of the
	 * {@link PinPanel} and provides on this way dynamic behavior.
	 * 
	 * @author Christian Kahlo
	 */
	private class MyKeyListener extends KeyAdapter {
		@Override
		public void keyTyped(final KeyEvent e) {
			final int index = ((MyPasswordField) e.getSource()).getIndex();

			if (index < fields.size() - 1 && isDigit(e)) {
				fields.get(index + 1).requestFocus();
			}

			if (e.getKeyChar() == '\n') {
				buttonPanel.setFocusToComponent(0);
			}
		}

		@Override
		public void keyPressed(final KeyEvent e) {
			final int index = ((MyPasswordField) e.getSource()).getIndex();

			if (index > 0 && e.getKeyChar() == '\b' && fields.get(index).getCaretPosition() == 0) {
				//Delete Ã¼ber Enter
				fields.get(index - 1).requestFocus();
				fields.get(index - 1).setText("");
			} else if (index < fields.size() - 1 && e.getKeyChar() == KeyEvent.VK_DELETE
					&& fields.get(index).getCaretPosition() == fields.get(index).getPassword().length) {
				//Entf
				fields.get(index + 1).requestFocus();
				fields.get(index + 1).setText("");
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && index < fields.size() - 1) {
				//Right
				fields.get(index + 1).requestFocus();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT && index > 0) {
				//Left
				fields.get(index - 1).requestFocus();
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				//Up
				for (int i = 0; i < pinRows.length; i++) {
					if (pinRows[i].containsField(index)) {
						if (i > 0) {
							int newIndex = index - pinRows[i].getPasswordFields().length;
							if (newIndex < 0) {
								newIndex = 0;
							}
							fields.get(newIndex).requestFocus();
						}
						break;
					}
				}
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				//Down
				for (int i = 0; i < pinRows.length; i++) {
					if (pinRows[i].containsField(index)) {
						if (i < pinRows.length - 1) {
							int newIndex = index + pinRows[i].getPasswordFields().length;
							if (newIndex >= fields.size()) {
								newIndex = fields.size() - 1;
							}
							fields.get(newIndex).requestFocus();
						}
						break;
					}
				}
			}
		}

		private boolean isDigit(final KeyEvent e) {
			return e.getKeyChar() >= '0' && e.getKeyChar() <= '9';
		}
	}

	/**
	 * The <tt>MyFocusListener</tt> listens on <tt>focus events</tt>, like
	 * <tt>focusLost</tt> and <tt>focusGained</tt>. Also he provides a function to
	 * change the enabled state of the keypad.
	 * 
	 * @author Christian Kahlo
	 */
	private class MyFocusListener implements FocusListener {
		@Override
		public void focusLost(final FocusEvent e) {
			lastFocusedField = ((MyPasswordField) e.getComponent()).getIndex();
			setKeypadEnabled(false);
		}

		@Override
		public void focusGained(final FocusEvent e) {
			lastFocusedField = ((MyPasswordField) e.getComponent()).getIndex();
			setKeypadEnabled(true);
		}

		private void setKeypadEnabled(final boolean enabled) {
			if (window == null) {
				window = (Window) SwingUtilities.getRoot(PinPanel.this);
			}
			if (window instanceof MainFrame) {
				((MainFrame) window).setKeypadPanelEnabled(enabled);
			} else if (window instanceof NewChangePinFrame) {
				((NewChangePinFrame) window).setKeypadPanelEnabled(enabled, PinPanel.this);
			}
		}
	}

	/**
	 * The <tt>MyDocumentListener</tt> listens on updates of <tt>documents</tt>
	 * to which the listener is added.
	 * 
	 * @author Christian Kahlo.
	 */
	private class MyDocumentListener implements DocumentListener {
		@Override
		public void insertUpdate(final DocumentEvent e) {
			checkCompletion();
		}

		@Override
		public void removeUpdate(final DocumentEvent e) {
			checkCompletion();
		}

		@Override
		public void changedUpdate(final DocumentEvent e) {
			checkCompletion();
		}
	}
}
