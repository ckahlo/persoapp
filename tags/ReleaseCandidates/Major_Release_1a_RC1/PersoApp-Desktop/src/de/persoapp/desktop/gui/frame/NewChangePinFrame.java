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
package de.persoapp.desktop.gui.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.client.IMainView.ChangePINDialogResult;
import de.persoapp.core.client.IMainView.EventListener;
import de.persoapp.desktop.Configuration;
import de.persoapp.desktop.Logging;
import de.persoapp.desktop.MainView;
import de.persoapp.desktop.gui.ArrowButton;
import de.persoapp.desktop.gui.MyMouseListener;
import de.persoapp.desktop.gui.MyTitledBorder;
import de.persoapp.desktop.gui.panel.ButtonPanel;
import de.persoapp.desktop.gui.panel.HelpPanel;
import de.persoapp.desktop.gui.panel.KeypadPanel;
import de.persoapp.desktop.gui.panel.PinPanel;

/**
 * @author ckahlo
 * 
 */
public class NewChangePinFrame extends JFrame implements HelpPanelProvider, SidebarProvider {

	private static final long				serialVersionUID	= 7054039676016930476L;

	private final static Logger				LOGGER				= Logging.getLogger();

	public final static int					STATE_CHOOSE		= 0;
	public final static int					STATE_ACTIVATE		= 1;
	public final static int					STATE_CHANGE		= 2;
	public final static int					STATE_UNLOCK		= 3;
	public final static int					STATE_QES_CHOOSE	= 4;
	public final static int					STATE_QES_SET		= 5;
	public final static int					STATE_QES_CHANGE	= 6;
	public final static int					STATE_QES_UNLOCK	= 7;
	public final static int					STATE_QES_TERMINATE	= 8;

	private static final Dimension			SIZE				= new Dimension(361, 234);

	private final PropertyResolver.Bundle	textBundle;

	private JPanel							mainPanel, choosePanel, displayedPanel, qesChoosePanel, buttonPanelHolder;
	private ButtonPanel						buttonPanel;
	private PinPanel						changePinPanel, activatePinPanel, pukPanel;
	private HelpPanel						helpPanel;
	private JLabel							pic, claimLabel;
	private JButton							unlockButton, activateButton, changeButton, abortButton, qesChooseButton,
			qesSetButton, qesChangeButton, qesUnlockButton, qesTerminateButton, qesAbortButton;
	private JButton[]						choosePanelButton, qesChoosePanelButton;
	private ArrowButton						arrowButton;
	private KeypadPanel						keypadPanel;

	private int								state;
	private boolean							showSideBar;

	private final Dimension					minSizeNoSidebar, minSizeWithSidebar;
	private static final int				extension			= 175;

	public NewChangePinFrame() {
		super();

		textBundle = PropertyResolver.getBundle("text");
		state = -1;
		showSideBar = false;

		final int width = 750, height = 320;
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screenSize.width - width) / 2, (screenSize.height - height) / 2, width, height);
		this.setMinimumSize(this.getBounds().getSize());

		minSizeNoSidebar = this.getMinimumSize();
		minSizeWithSidebar = new Dimension(minSizeNoSidebar.width + extension, minSizeNoSidebar.height);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(Configuration.RESIZABLE);
		this.setTitle(textBundle.get("window_title"));
		this.setIconImage(Configuration.WINDOW_ICON);

		this.setName("NewChangePinFrame");

		initPanels();
		addListener();
		drawPanels();

		setPanelState(STATE_CHOOSE);

		//NÃ¶tig, da das Feld sonst nach dem erst Ã–ffnen leer ist, weil sich die Maus noch in der Tray-Anzeige befindet. 
		this.setHelpPanelText(textBundle.get("NewChangePinFrame_Choose_header"),
				textBundle.get("NewChangePinFrame_Choose_description"));

		this.setAlwaysOnTop(true);
		this.requestFocus();
	}

	public void initPanels() {
		mainPanel = new JPanel(true);
		mainPanel.setLayout(new GridBagLayout());

		activateButton = new JButton(textBundle.get("NewChangePinFrame_transport_button"));
		changeButton = new JButton(textBundle.get("NewChangePinFrame_change_button"));
		unlockButton = new JButton(textBundle.get("NewChangePinFrame_puk_button"));
		qesChooseButton = new JButton(textBundle.get("NewChangePinFrame_qes_choose_button"));
		abortButton = new JButton(textBundle.get("cancel"));
		choosePanelButton = new JButton[] { activateButton, changeButton, unlockButton, qesChooseButton, abortButton };

		qesSetButton = new JButton(textBundle.get("NewChangePinFrame_qes_set_button"));
		qesChangeButton = new JButton(textBundle.get("NewChangePinFrame_qes_change_button"));
		qesUnlockButton = new JButton(textBundle.get("NewChangePinFrame_qes_unlock_button"));
		qesTerminateButton = new JButton(textBundle.get("NewChangePinFrame_qes_terminate_button"));
		qesAbortButton = new JButton(textBundle.get("return"));
		qesChoosePanelButton = new JButton[] { qesSetButton, qesChangeButton, qesUnlockButton, qesTerminateButton,
				qesAbortButton };

		buttonPanel = new ButtonPanel();
		arrowButton = new ArrowButton();
		buttonPanelHolder = getButtonPanelHolder();

		keypadPanel = new KeypadPanel(null);
		final Dimension d = new Dimension(extension - 10, minSizeNoSidebar.height - 10);
		keypadPanel.setMinimumSize(d);
		keypadPanel.setPreferredSize(d);

		changePinPanel = new PinPanel(buttonPanel, new String[] { textBundle.get("NewChangePinFrame_pin_active"),
				textBundle.get("ChangePinPanel_pin_new"), textBundle.get("ChangePinPanel_pin_new_rep") }, new int[] {
				6, 6, 6 }, true);
		activatePinPanel = new PinPanel(buttonPanel, new String[] { textBundle.get("NewChangePinFrame_pin_transport"),
				textBundle.get("ChangePinPanel_pin_new"), textBundle.get("ChangePinPanel_pin_new_rep") }, new int[] {
				5, 6, 6 }, true);

		pukPanel = new PinPanel(buttonPanel, textBundle.get("NewChangePinFrame_puk"), 10);
		pukPanel.setMinimumSize(SIZE);
		pukPanel.setMaximumSize(SIZE);
		pukPanel.setPreferredSize(SIZE);

		helpPanel = new HelpPanel();

		pic = new JLabel(new ImageIcon(Configuration.LOGO));
		claimLabel = new JLabel(Configuration.CLAIM_TEXT == null ? "" : Configuration.CLAIM_TEXT, JLabel.CENTER);
		claimLabel.setFont(new Font(Configuration.FONT, Font.BOLD, 18));

		choosePanel = getNewChoosePanel(textBundle.get("NewChangePinFrame_choose_pinheadline"), choosePanelButton);
		qesChoosePanel = getNewChoosePanel(textBundle.get("NewChangePinFrame_qes_choose_button"), qesChoosePanelButton);

		displayedPanel = choosePanel;
	}

	public void drawPanels() {
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
		cons.gridheight = 3;
		cons.weighty = 1;
		mainPanel.add(helpPanel, cons);

		cons.gridx = 1;
		cons.gridy = 0;
		cons.gridheight = 2;
		cons.weightx = 0;
		cons.weighty = 1;
		mainPanel.add(displayedPanel, cons);

		cons.gridy = 2;
		cons.gridheight = 1;
		cons.weighty = 0;
		mainPanel.add(new JPanel(true), cons);

		cons.gridheight = 1;
		cons.gridy = 3;
		cons.weighty = 0;
		mainPanel.add(buttonPanelHolder, cons);

		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}

	private void addListener() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				setPanelState(STATE_CHOOSE);
				returnResultAbort();
			}
		});

		buttonPanel.getCancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				switch (state) {
					case STATE_QES_SET:
					case STATE_QES_CHANGE:
					case STATE_QES_UNLOCK:
					case STATE_QES_TERMINATE:
						setPanelState(STATE_QES_CHOOSE);
						break;
					default:
						setPanelState(STATE_CHOOSE);
						break;
				}

				if (showSideBar) {
					removeSidebar();
					arrowButton.setIconState(ArrowButton.STATE_RIGHT);
				}
			}
		});

		buttonPanel.getConfirm().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {

				// disable to prevent double click
				buttonPanel.getConfirm().setEnabled(false);
				returnResultConfirm();
				if (showSideBar) {
					removeSidebar();
					arrowButton.setIconState(ArrowButton.STATE_RIGHT);
				}
			}
		});

		buttonPanel.getConfirm().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(final KeyEvent e) {
				if (e.getKeyChar() == '\n') {

					// produces two events if pressing "Enter"
					//returnResultConfirm();
					if (showSideBar) {
						removeSidebar();
						arrowButton.setIconState(ArrowButton.STATE_RIGHT);
					}
				}
			}
		});

		final ActionListener actionListener1 = new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				final Object source = e.getSource();
				int state = -1;
				final NewChangePinFrame target = NewChangePinFrame.this;

				if (source == target.activateButton) {
					state = STATE_ACTIVATE;
				} else if (source == target.changeButton) {
					state = STATE_CHANGE;
				} else if (source == target.qesChooseButton) {
					setPanelState(STATE_QES_CHOOSE);
				} else if (source == target.unlockButton) {
					state = STATE_UNLOCK;
				} else if (source == target.abortButton) {
					returnResultAbort();
				} else if (source == target.qesSetButton) {
					state = STATE_QES_SET;
				} else if (source == target.qesChangeButton) {
					state = STATE_QES_CHANGE;
				} else if (source == target.qesUnlockButton) {
					state = STATE_QES_UNLOCK;
				} else if (source == target.qesTerminateButton) {
					state = STATE_QES_TERMINATE;
				} else if (source == target.qesAbortButton) {
					setPanelState(STATE_CHOOSE);
				}

				if (state >= 0) {
					executeButtonAction(state);
				}
			}
		};

		activateButton.addActionListener(actionListener1);
		changeButton.addActionListener(actionListener1);
		qesChooseButton.addActionListener(actionListener1);
		unlockButton.addActionListener(actionListener1);

		qesSetButton.addActionListener(actionListener1);
		qesChangeButton.addActionListener(actionListener1);
		qesUnlockButton.addActionListener(actionListener1);
		qesTerminateButton.addActionListener(actionListener1);
		qesAbortButton.addActionListener(actionListener1);

		/*
		 * Reihenfolge der MouseListener-Zuweisungen ist wichtig, da bei einem
		 * Panel rekursiv jede enthaltene Komponente den Listern und den Namen
		 * erhÃ¤lt.
		 */

		MyMouseListener.addListener(this, choosePanel, "Choose");

		MyMouseListener.addListener(this, pukPanel, "Unlock");
		MyMouseListener.addListener(this, activatePinPanel, "Activate");
		MyMouseListener.addListener(this, changePinPanel, "Change");

		MyMouseListener.addListener(this, qesChoosePanel, "choose_QES");

		MyMouseListener.addListener(this, buttonPanel, "buttonPanel");

		MyMouseListener.addListener(this, abortButton, "abortButton");
		abortButton.addActionListener(actionListener1);
	}

	private JPanel getNewChoosePanel(final String borderTitle, final JButton... button) {
		final JPanel result = new JPanel(true);
		result.setLayout(new GridBagLayout());
		result.setBorder(new MyTitledBorder(borderTitle));

		result.setMinimumSize(SIZE);
		result.setMaximumSize(SIZE);
		result.setPreferredSize(SIZE);

		//erst die Seiten-Panel, dann die die Button mit Zwischen-Panels, dann das letzte Panel

		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridheight = 11;
		cons.gridwidth = 1;
		cons.gridx = 0;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 0.25;
		cons.weighty = 1;

		result.add(new JPanel(true), cons);

		cons.gridx = 2;
		result.add(new JPanel(true), cons);

		cons.gridheight = 1;
		cons.gridx = 1;
		cons.weightx = 0.5;

		for (int i = 0; i < button.length; i++) {
			cons.gridy = 2 * i;
			cons.weighty = 1d / button.length;
			result.add(new JPanel(true), cons);
			cons.gridy++;
			cons.weighty = 0;
			result.add(button[i], cons);
		}

		cons.gridy++;
		cons.weighty = 1d / button.length;
		result.add(new JPanel(true), cons);

		return result;
	}

	public JPanel getButtonPanelHolder() {
		final JPanel result = new JPanel(true);
		result.setLayout(new BorderLayout());
		result.add(buttonPanel, BorderLayout.CENTER);
		result.add(arrowButton, BorderLayout.EAST);

		return result;
	}

	@Override
	public void addSidebar() {
		showSideBar = true;

		final GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.BOTH;
		cons.gridwidth = 1;
		cons.gridheight = 4;
		cons.gridx = 2;
		cons.gridy = 0;
		cons.insets = new Insets(5, 5, 5, 5);
		cons.weightx = 0;
		cons.weighty = 1;
		mainPanel.add(keypadPanel, cons);

		this.setSize(this.getWidth() + extension, this.getHeight());
		this.setMinimumSize(minSizeWithSidebar);

		this.validate();
	}

	@Override
	public void removeSidebar() {
		showSideBar = false;

		mainPanel.remove(keypadPanel);

		this.setMinimumSize(minSizeNoSidebar);
		this.setSize(this.getWidth() - extension, this.getHeight());

		this.validate();
	}

	public void setKeypadPanelEnabled(final boolean enabled, final PinPanel panel) {
		keypadPanel.setPinPanel(panel);
		keypadPanel.setEnabled(enabled);
	}

	public void setPanelState(final int state) {
		if (this.state != state) {

			mainPanel.remove(displayedPanel);
			if (displayedPanel instanceof PinPanel) {
				((PinPanel) displayedPanel).removeContent();
			}

			final GridBagConstraints cons = new GridBagConstraints();
			cons.fill = GridBagConstraints.BOTH;
			cons.gridheight = 1;
			cons.gridwidth = 1;
			cons.gridx = 1;
			cons.gridy = 3;
			cons.insets = new Insets(5, 5, 5, 5);
			cons.weightx = 0;
			cons.weighty = 0;

			switch (state) {
				case STATE_CHOOSE:
				case STATE_QES_CHOOSE:
					mainPanel.remove(buttonPanelHolder);
					mainPanel.add(claimLabel, cons);
					break;
				case STATE_ACTIVATE:
				case STATE_CHANGE:
				case STATE_UNLOCK:
				case STATE_QES_SET:
				case STATE_QES_CHANGE:
				case STATE_QES_UNLOCK:
				case STATE_QES_TERMINATE:
					mainPanel.remove(claimLabel);
					mainPanel.add(buttonPanelHolder, cons);
					break;
			}

			displayedPanel = getStatePanel(state);

			cons.gridheight = 2;
			cons.gridwidth = 1;
			cons.gridx = 1;
			cons.gridy = 0;
			cons.weightx = 0;
			cons.weighty = 1;
			mainPanel.add(displayedPanel, cons);

			mainPanel.validate();
			mainPanel.repaint();

			helpPanel.setText(textBundle.get(getStatePanel(state).getName() + "_header"),
					textBundle.get(getStatePanel(state).getName() + "_description"));

			this.state = state;
		}
	}

	/**
	 * Wird nur verwendet, wenn LesegerÃ¤t mit Pinpad genutzt wird.
	 * 
	 * Ã„ndert die MouseListener, de- und aktiviert die Button
	 */
	private void setPinDisabledUI(final boolean disabled) {
		if (disabled) {
			MyMouseListener.setName(this, choosePanel, "pin_disabled");
			MyMouseListener.setName(this, qesChoosePanel, "pin_disabled");
			helpPanel.setText(textBundle.get("attention"), textBundle.get("use_reading_device"));
		} else {
			MyMouseListener.setName(this, choosePanel, "Choose");
			MyMouseListener.setName(this, qesChoosePanel, "choose_QES");
			helpPanel.setText(textBundle.get("NewChangePinFrame_Choose_header"),
					textBundle.get("NewChangePinFrame_Choose_description"));
		}

		for (final JButton button : choosePanelButton) {
			button.setEnabled(!disabled);
		}

		for (final JButton button : qesChoosePanelButton) {
			button.setEnabled(!disabled);
		}
	}

	private JPanel getStatePanel(final int state) {
		switch (state) {
			case STATE_ACTIVATE:
				return activatePinPanel;
			case STATE_CHANGE:
				return changePinPanel;
			case STATE_UNLOCK:
				return pukPanel;
			case STATE_QES_CHOOSE:
			case STATE_QES_SET:
			case STATE_QES_CHANGE:
			case STATE_QES_UNLOCK:
			case STATE_QES_TERMINATE:
				return qesChoosePanel;
			default:
				return choosePanel;
		}
	}

	private void resetFields(final boolean testCard) {
		activatePinPanel.removeContent();
		changePinPanel.removeContent();
		pukPanel.removeContent();
		if (showSideBar) {
			removeSidebar();
			arrowButton.setIconState(ArrowButton.STATE_RIGHT);
		}

		if (testCard) {
			final Object result = MainView.getInstance().triggerEvent(EventListener.EVENT_TEST_CARD_STATE);
			if (result != null) {
				qesChooseButton.setEnabled(result instanceof Integer && ((Integer) result & 0x10) > 0);

				final int attempts = (Integer) MainView.getInstance().triggerEvent(EventListener.EVENT_TEST_PIN_STATE);
				helpPanel.setPINState(attempts);

				switch (attempts) {
					case 3:
					case 2:
					case 1:
						activateButton.setEnabled(true);
						changeButton.setEnabled(true);
						unlockButton.setEnabled(false);
						break;
					case 0: // blocked
						activateButton.setEnabled(false);
						changeButton.setEnabled(false);
						unlockButton.setEnabled(true);
						break;
					case 255: // deactivated
						activateButton.setEnabled(false);
						changeButton.setEnabled(false);
						unlockButton.setEnabled(false);
						break;
					default: // no card
						activateButton.setEnabled(false);
						changeButton.setEnabled(false);
						unlockButton.setEnabled(false);
						helpPanel.setPINState(-1);
						break;
				}
			} else {
				helpPanel.setPINState(-1);
				activateButton.setEnabled(false);
				changeButton.setEnabled(false);
				unlockButton.setEnabled(false);
				qesChooseButton.setEnabled(false);
			}
		}
	}

	/*
	 * ###########################################
	 * ###########################################
	 * 
	 * GUI finished, the logic stuff is coming now
	 * 
	 * ###########################################
	 * ###########################################
	 */

	public void returnResultAbort() {
		LOGGER.log(Level.INFO, "Cancel was pressed");
		this.setVisible(false);
	}

	public void returnResultConfirm() {
		if (displayedPanel instanceof PinPanel) {
			final PinPanel panel = (PinPanel) displayedPanel;
			switch (panel.getRowCount()) {
				case 1:
					//PUK, auch PIN bei QES
					final byte[] puk = panel.getPinCode(0);
					if (puk == null) {
						if (state == STATE_UNLOCK || state == STATE_QES_UNLOCK) {
							MainView.getInstance().showError(
									textBundle.get("NewChangePinFrame_puk_input_error_header"),
									textBundle.get("NewChangePinFrame_puk_input_error"));
						} else if (state == STATE_QES_CHOOSE || state == STATE_QES_TERMINATE) {
							MainView.getInstance().showError(textBundle.get("pin_input_error"),
									textBundle.get("pin_input_error_message"));
						}
						return;
					}

					processStateAction(state, new ChangePINDialogResult(puk, null, true));
					break;
				case 3:
					//Ã¼berprÃ¼ft Gleichheit der neue PIN & Wiederholung (zeigt Fehlerfall selbststÃ¤ndig an)
					panel.checkCompletion();

					if (panel.getPinCode(0) == null) {
						MainView.getInstance().showError(textBundle.get("pin_input_error"),
								textBundle.get("NewChangePinFrame_pin_input_error_old"));
						return;
					}
					processStateAction(state, new ChangePINDialogResult(panel.getPinCode(0), panel.getPinCode(1), true));
					break;
			}
		}
	}

	private void executeButtonAction(final int state) {
		LOGGER.log(Level.INFO, "executeDialogAction: " + state);

		final Object result = MainView.getInstance().triggerEvent(EventListener.EVENT_TEST_CARD_STATE);
		if (result != null) {
			if (result instanceof Integer && ((Integer) result & 0x40) > 0) {
				setPinDisabledUI(true);
				processStateAction(state, new ChangePINDialogResult(null, null, true));
			} else {
				setPanelState(state);
			}
		}
	}

	private void processStateAction(final int state, final Object eventData) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				final IMainView mainView = MainView.getInstance();
				int event = -1;
				if (state == STATE_ACTIVATE) {
					event = EventListener.EVENT_ACTIVATE_PIN_EID;
				} else if (state == STATE_CHANGE) {
					event = EventListener.EVENT_CHANGE_PIN_EID;
				} else if (state == STATE_UNLOCK) {
					event = EventListener.EVENT_UNLOCK_PIN_EID;
				} else if (state == STATE_QES_SET) {
					event = EventListener.EVENT_INIT_PIN_ESIGN;
				} else if (state == STATE_QES_CHANGE) {
					event = EventListener.EVENT_CHANGE_PIN_ESIGN;
				} else if (state == STATE_QES_UNLOCK) {
					event = EventListener.EVENT_UNBLOCK_PIN_ESIGN;
				} else if (state == STATE_QES_TERMINATE) {
					event = EventListener.EVENT_TERMINATE_ESIGN;
				}

				boolean success = false;
				if (event >= 0) {
					final Object result = mainView.triggerEvent(event, eventData);
					if (result != null && result instanceof Boolean) {
						success = ((Boolean) result).booleanValue();
					}
				}

				if (!abortButton.isEnabled()) {
					//tritt nur ein, wenn LesegerÃ¤t mit Pinpad verwendet wird.
					//vorher wurde nichts verÃ¤ndert, daher brauch jetzt auch nichts verÃ¤ndert werden.
					setPinDisabledUI(false);
				}

				if (success) {
					mainView.showMessage(textBundle.get("process_success"), IMainView.SUCCESS);
					NewChangePinFrame.this.setVisible(false);
					NewChangePinFrame.this.setPanelState(STATE_CHOOSE);
				} else {
					mainView.showMessage(textBundle.get("process_fail"), IMainView.ERROR);
					setPanelState(STATE_CHOOSE);
					NewChangePinFrame.this.resetFields(true);
				}
			}
		}).start();
	}

	@Override
	public void setVisible(final boolean visible) {
		super.setVisible(visible);
		resetFields(visible);
	}

	@Override
	public void setHelpPanelText(final String header, final String description) {
		helpPanel.setText(header, description, true);
	}

	@Override
	public void clearHelpPanelText() {
		/*
		 * Er soll nichts tun, denn dann bleibt automatisch der Text vom
		 * aktuellen State stehen, wenn die Maus das Fenster verlÃ¤sst. WÃ¼rde
		 * der Text entfernt werden, wÃ¤re das helpPanel leer, wenn die Maus
		 * auÃŸerhalb vom Fenster ist, was doch etwas komisch aussieht.
		 */
	}
}
