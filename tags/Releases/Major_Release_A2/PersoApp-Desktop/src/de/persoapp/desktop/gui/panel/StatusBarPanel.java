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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import de.persoapp.desktop.gui.ArrowButton;

/**
 * <p>
 * The StatusBarPanel contains all informations about the used status
 * bar which shows the progress of operations and to operations related
 * informations.
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class StatusBarPanel extends JPanel {

	private static final long	serialVersionUID	= 7386565036232115981L;

	/**
	 * <p>
	 * The value, which stands for the completely filled progress bar.
	 * </p>
	 */
	private static final int	MAX					= 99;
	
	/**
	 * <p>
	 * The value, which stands for the completely emptied progress bar.
	 * </p>
	 */
	private static final int	MIN					= 0;

	/**
	 * <p>
	 * The {@link JProgressBar} which displays the progress and 
	 * informations, related to the current attempted operation.
	 * </p>
	 */
	private JProgressBar		progressBar;
	
	/**
	 * <p>
	 * The used arrow buttons for adding and removing the sidebar.
	 * </p>
	 * <p>
	 * The sidebar comes only to work, if it is necessary to display an virtual pin panel.
	 * </p>
	 */
	private ArrowButton			arrowButton;

	/**
	 * <p>
	 * Constructs a new instance of the {@link StatusBarPanel}. Also initializes
	 * and draws the components.
	 * </p>
	 * <p>
	 * The constructed Panel is double-buffered to achieve benefits by
	 * extended use of memory.
	 * </p>
	 * <p>
	 * The {@link GridBagLayout} is used for drawing, after the components are initialized.
	 * </p>
	 */
	public StatusBarPanel() {
		super();
		this.setDoubleBuffered(true);
		this.setLayout(new GridBagLayout());

		initComponents();
		drawComponents();
	}

	/**
	 * <p>
	 * Initializes the components of the {@link StatusBarPanel}.
	 * </p>
	 */
	private void initComponents() {
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, MIN, MAX);
		progressBar.setStringPainted(true);
		progressBar.setEnabled(false);

		arrowButton = new ArrowButton();
	}

	/**
	 * <p>
	 * Draws the components of the {@link StatusBarPanel}.
	 * </p>
	 */
	private void drawComponents() {
		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.gridheight = 1;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(0, 0, 0, 0);
		cons.weightx = 1;
		cons.weighty = 1;

		this.add(progressBar, cons);

		cons.gridx = 1;
		cons.weightx = 0;
		cons.insets = new Insets(0, 5, 0, 0);
		this.add(arrowButton, cons);
	}

	/**
	 * <p>
	 * Displays the given text in the progress bar. The given text can
	 * be <strong>null</strong>. In this case, no text is shown. The argument
	 * <code>enabled</code> alters the state and the behavior of the
	 * progress bar. If the progress bar is <em>enabled</em>,
	 * it is able to respond to user input and generate related events. In the
	 * <em>disabled</em> state, the bar can not respond to user input and thus
	 * can not generate new events.
	 * </p>
	 * 
	 * @param message
	 *            - The text, which is shown in the progress bar. 
	 * @param enabled
	 *            - Enables or disables the progress bar. 
	 */
	public void setProgressBarValue(final String message, final boolean enabled) {
		progressBar.setString(message == null ? "" : message);
		progressBar.setStringPainted(true);
		progressBar.setEnabled(enabled);
	}

	/**
	 * <p>
	 * Displays the given text in the progress bar and fills the bar to
	 * the given <strong>amount</strong>.<br/>
	 * The argument <code>enabled</code> alters the state and the behavior of
	 * the progress bar. If the progress bar is
	 * <em>enabled</em>, it is able to respond to user input and generate
	 * related events. In the <em>disabled</em> state, the bar can not respond
	 * to user input and thus can not generate new events.
	 * </p>
	 * 
	 * @param message
	 *            -The text, which is shown in the progress bar.
	 * 
	 * @param amount
	 *            - The <strong>amount</strong>, to which the bar is filled up.
	 *            If the <strong>amount</strong> is bigger than the defined
	 *            maximal value, the bar is filled up completely. The progress
	 *            bar is completely emptied if the given <strong>amount</strong>
	 *            is lower than the defined minimal value.
	 * 
	 * @param enabled
	 *            - Enables or disables the progress bar.
	 */
	public void setProgressBarValue(final String message, int amount, final boolean enabled) {
		if (amount > MAX) {
			amount = MAX;
		} else if (amount < MIN) {
			amount = MIN;
		}

		progressBar.setValue(amount);
		progressBar.setString(message != null ? message + " " : "" + amount + "%");
		progressBar.setStringPainted(true);
		progressBar.setEnabled(enabled);
	}
}
