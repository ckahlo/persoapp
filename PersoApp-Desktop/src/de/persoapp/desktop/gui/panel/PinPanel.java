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
 * <p>
 * The PinPanel contains {@link PinRows} to store the numbers which are
 * inserted via the keyboard, the virtual keypad or the keypad of the used card
 * reader.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class PinPanel extends JPanel {


	private static final long				serialVersionUID	= 1L;

	/**
	 * Stores the index of the last focused password field.
	 */
	private int								lastFocusedField;
	
	/**
	 * Stores the flag if the pin panel is activated.
	 */
	private boolean							pinActivated;

	/**
	 * Checks the last two rows for additionally safety, if set to <strong>true</strong>.
	 */
	private final boolean					checkLastTwoRows;

	/**
	 * The {@link ButtonPanel} for user interaction.
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
	 * The used PasswordField, which holds the different passwords.
	 */
	private ArrayList<MyPasswordField>		fields;
	
	/**
	 * The used PinRows, to insert different kinds of pins.
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
	  *Localized message bundle for user interaction.	 
	  */
	private final PropertyResolver.Bundle	textBundle;

	/**
	 * Constructs a new instance of the {@link PinPanel} with just one
	 * {@link PinRow}.
	 * 
	 * @param buttonPanel
	 *            - The {@link ButtonPanel} for user interaction. Can not set to
	 *            <strong>null</strong>.
	 * @param header
	 *            - The <em>header</em>, which is a sub-title for the created
	 *            PinRow to guide the user interaction. Can not set to
	 *            <strong>null</strong>.
	 * @param fieldCount
	 *            - The number of fields of the created PinRow.
	 */
	public PinPanel(final ButtonPanel buttonPanel, final String header, final int fieldCount) {
		this(buttonPanel, new String[] { header }, new int[] { fieldCount });
	}

	/**
	 * Constructs a new instance of the {@link PinPanel} and doesn't check the
	 * last two rows.
	 * 
	 * @param buttonPanel
	 *            - The {@link ButtonPanel} for user interaction. Can not set to
	 *            <strong>null</strong>.
	 * @param header
	 *            - The <em>headers</em>, which are a sub-title for each created
	 *            PinRow to guide the user interaction. Can not set to
	 *            <strong>null</strong> and needs to be at the same length like
	 *            the <em>fieldCounts</em> array.
	 * @param fieldCounts
	 *            - The array for specifying the number of fields for each
	 *            PinRow. The length of this array specifies how many
	 *            PinRows are going to be created. Can not set to
	 *            <strong>null</strong>.
	 */
	public PinPanel(final ButtonPanel buttonPanel, final String[] header, final int[] fieldCounts) {
		this(buttonPanel, header, fieldCounts, false);
	}

	/**
	 * Constructs a new instance of the {@link PinPanel}. The constructed
	 * panel is double-buffered to achieve benefits by the extended use
	 * of memory.
	 * 
	 * 
	 * @param buttonPanel
	 *            - The {@link ButtonPanel} for user interaction. Can not set to
	 *            <strong>null</strong>.
	 * @param header
	 *            - The <em>headers</em>, which are a sub-title for each created
	 *            PinRow to guide the user interaction. Can not set to
	 *            <strong>null</strong> and needs to be at the same length like
	 *            the <em>fieldCounts</em> array.
	 * @param fieldCounts
	 *            - The array for specifying the number of fields for each
	 *            PinRow. The length of this array specifies how many
	 *            PinRows are going to be created. Can not set to
	 *            <strong>null</strong>.
	 * @param checkLastTwoRows
	 *            - Checks if the last two PinRows are equal. Can not
	 *            set to <strong>null</strong>.
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
	 * <p>
	 * Initializes the components of the {@link PinPanel}. Set up every title
	 * through the given header-array and creates the instances of the
	 * PinFields. The fieldCounts-array specifies how many
	 * PinRows are created and how many fields does one
	 * PinRow count.
	 * </p>
	 * 
	 * @param header
	 *            - The given header-array.
	 * @param fieldCounts
	 *            - The array of the numbers of PasswordFields are
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
	 * Draws the components of the PinPanel.
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
	 * <p>
	 * Returns the number PinRows.
	 * </p>
	 * 
	 * @return Returns the number PinRows.
	 */
	public int getRowCount() {
		return pinRows.length;
	}

	/**
	 * <p>
	 * Returns the inserted values as a PIN.
	 * </p>
	 * 
	 * @param row
	 *            - Index of the PinRow.
	 * @return Returns the inserted values as a PIN.
	 */
	public byte[] getPinCode(final int row) {
		return pinRows[row].getPinCode();
	}

	/**
	 * Checks if all PinRows are completely filled up with values and
	 * if the new pin matches with the repetition.
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
		//if not completely filled, it remains as null.
		final byte[] pin = getPinCode(row1);
		final byte[] pinRep = getPinCode(row2);

		if (pin == null || pinRep == null || pin.length != pinRep.length) {
			return false;
		}

		for (int i = 0; i < pin.length; i++) {
			if (pin[i] != pinRep[i]) {
				//He just comes to this place if all fields are filled and if the last to rows 
				//should be checked.
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
	 * This function is going to be called by the virtual KeyPad and removes the
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
	 * Removes the content from all fields of the {@link PinPanel}.
	 * Puts the focus to the first field.
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
	 * <p>
	 * This method enables the possibility to use the {@link PinPanel} and the
	 * virtual keypad or the connected keyboard, if the argument
	 * <em>enabled</em> is set to <strong>true</strong>. Otherwise the
	 * PinRows are removed and the user is forced to use his card
	 * reader for the insertion of the required numbers.
	 * </p>
	 * 
	 * @param enabled
	 *            - Set to <strong>true</strong>, to use the integrated
	 *            PinRows and the virtual keypad or the connected
	 *            keyboard. Set to <strong>false</strong> to use the connected
	 *            card reader.
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
	 * <p>
	 * This method checks first, if a specific {@link MyPasswordField} with the
	 * provided <em>index</em> exists in a {@link PinRow}. If such a
	 * PasswordField can be located and has the specific {@link PinRow}
	 * the same length as the provided <em>value</em>, it is pasted at the
	 * provided <em>index</em> in the {@link PinRow}.
	 * </p>
	 * 
	 * @param value
	 *            - The inserted value as a PIN.
	 * 
	 * @param index
	 *            Index of the specific MyPasswordField.
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
	 * The PinRow provides all fields for inserting the numbers of a
	 * pin.
	 * 
	 * @author Christian Kahlo
	 */
	private class PinRow extends JPanel {

		private static final long		serialVersionUID	= 1L;
		
		/**
		 * The fields for inserting the numbers of the pins.
		 */
		private final MyPasswordField[]	fields;

		/**
		 * Constructs a new instance of the {@link PinRow}. Also draws and
		 * initializes all components. The {@link GridBagLayout} and predefined
		 * {@link GridBagConstraints} are used for drawing.
		 * 
		 * @param fields
		 *            - The given PinFields.
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
		 * Returns the PinCode if anyone is typed in, otherwise
		 * <strong>null</strong>.
		 * 
		 * @return Returns the PinCode if anyone is typed in, otherwise
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
		 * Returns the Array of the used password fields.
		 * 
		 * @return Returns the Array of the used password fields.
		 */
		public MyPasswordField[] getPasswordFields() {
			return fields;
		}

		/**
		 * Checks if a specific {@link MyPasswordField} is located in the array
		 * of used MyPasswordFields.
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
	 * The <ttMyKeyListener listens to events of the keys of the
	 * {@link KeypadPanel} and provides on this way dynamic behavior.
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
		
		/**
		 * Checks, if the typed key is a digit.
		 * 
		 * @param e
		 *            - The triggered {@link KeyEvent}.
		 * @return Returns <strong>true</strong>, if the typed key is a digit
		 *         and otherwise <strong>false</strong>.
		 */
		private boolean isDigit(final KeyEvent e) {
			return e.getKeyChar() >= '0' && e.getKeyChar() <= '9';
		}
	}

	/**
	 * The MyFocusListener listens on focus events, like
	 * focusLost and focusGained. Also he provides a function to
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
	 * The MyDocumentListener listens on updates of documents
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
