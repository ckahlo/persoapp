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
package de.persoapp.desktop;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.SplashScreen;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.client.SecureHolder;
import de.persoapp.desktop.gui.frame.CANDialog;
import de.persoapp.desktop.gui.frame.MainFrame;
import de.persoapp.desktop.gui.frame.NewChangePinFrame;

/**
 * Starting point.
 * 
 * @author Ralf Wondratschek
 * 
 */

public class MainView implements IMainView {

	private final static Logger	LOGGER	= Logging.getLogger();

	private static IMainView	instance;

	protected StatusIndicator	statusIndicator;
	private EventListener		listener;
	protected volatile JFrame	currentFrame;

	// Main Dialog
	private MainFrame			mainFrame;
	private MainDialogResult	result;

	// PIN Ã„nderung
	private NewChangePinFrame	changePinFrame;

	static {
		// install the configured Look-and-feel in the system
		installLAF();
	}

	private static void installLAF() {
		try {
			UIManager.setLookAndFeel(Configuration.LOOK_AND_FEEL.getName());
		} catch (final Exception e) {
			LOGGER.log(Level.WARNING, "", e);
		}
	}

	protected MainView() {
		init();
	}

	protected void init() {
		final PropertyResolver.Bundle text_mainBundle = PropertyResolver.getBundle("text");
		final PropertyResolver.Bundle text_coreBundle = PropertyResolver.getBundle("text_core");

		statusIndicator = new StatusIndicator();
		statusIndicator.setDefaultTitle(text_mainBundle.get("window_title"));

		final SplashScreen ss = SplashScreen.getSplashScreen();
		if (ss != null && ss.isVisible()) {
			final URL splashURL = Utils.getResourceUrl("splash_white_324x204.png");
			if (splashURL != null) {
				try {
					ss.setImageURL(splashURL);
					final Graphics2D splashGraphics = ss.createGraphics();

					new Thread(new Runnable() {
						@Override
						public void run() {
							splashGraphics.setBackground(Color.WHITE);
							splashGraphics.setColor(Color.BLACK);
							splashGraphics.setFont(new Font("Arial", Font.TRUETYPE_FONT | Font.PLAIN, 14));

							splashGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
									RenderingHints.VALUE_INTERPOLATION_BILINEAR);
							splashGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
									RenderingHints.VALUE_ANTIALIAS_ON);
							splashGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
									RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
							splashGraphics.setRenderingHint(RenderingHints.KEY_RENDERING,
									RenderingHints.VALUE_RENDER_QUALITY);

							final int width = (int) ss.getSize().getWidth(), height = (int) ss.getSize().getHeight();

							final BufferedImage img = Configuration.LOGO;
							if (img != null) {
								splashGraphics.drawImage(img, (width - img.getWidth()) / 2,
										(height - img.getHeight()) / 2, null);
							}

							String msg = text_mainBundle.get("window_title");
							splashGraphics.drawString(msg, 4, 16);

							final String buildVersion = PropertyResolver.getProperty("version", "buildVersion");
							final String buildRevision = PropertyResolver.getProperty("version", "buildRevision");
							final String buildNo = PropertyResolver.getProperty("version", "buildNo");
							final String buildDate = PropertyResolver.getProperty("version", "buildDate");

							msg = buildVersion + "." + buildRevision + "." + buildNo + " " + buildDate;
							splashGraphics.drawString(msg, 4, 32);

							msg = "persoapp@trust.cased.de";
							splashGraphics.drawString(msg, 4, 195);

							final FontMetrics metrics = splashGraphics.getFontMetrics();
							msg = "(c) 2013 www.persoapp.de";
							splashGraphics.drawString(msg, width - metrics.stringWidth(msg) - 4, 195);

							msg = text_coreBundle.get("Main_starting");
							splashGraphics.drawString(msg, (width - metrics.stringWidth(msg)) / 2, 160);

							ss.update();

							try {
								Thread.sleep(3000);
							} catch (final InterruptedException e) {
							}

							try {
								ss.close();
							} catch (final Exception e) {
								// already closed
							}
						}
					}).start();
				} catch (final Exception e) {
					e.printStackTrace();
				}
			} else {
				ss.close();
			}
		}
	}

	public static IMainView getInstance() {
		if (instance == null) {
			try {
				instance = Configuration.MAINVIEW_CLASS.newInstance();
			} catch (final InstantiationException e) {
				e.printStackTrace();
			} catch (final IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public MainFrame getMainFrame() {
		if (this.mainFrame == null) {
			this.mainFrame = new MainFrame();
			this.result = null;
		}

		currentFrame = this.mainFrame;
		return this.mainFrame;
	}

	public NewChangePinFrame getChangePinFrame() {
		if (this.changePinFrame == null) {
			this.changePinFrame = new NewChangePinFrame();
		}

		currentFrame = this.changePinFrame;
		return this.changePinFrame;
	}

	@Override
	public void showMainDialog(final IEAC_Info eacInfo, final int MODE) {
		this.result = null;

		final MainFrame mf = getMainFrame();
		mf.init(eacInfo);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mf.setAlwaysOnTop(true);
				mf.setVisible(true);
				mf.toFront();
				mf.setPinEnabled(!(MODE == IMainView.MODE_NONE || MODE == IMainView.MODE_CHATONLY));
			}
		});
	}

	@Override
	public MainDialogResult getMainDialogResult() {
		while (this.result == null) {
			try {
				synchronized (this) {
					this.wait();
				}
			} catch (final InterruptedException e) {
			}
		}

		return this.result;
	}

	public void setResult(final long chat, final byte[] pin, final boolean approved) {
		this.result = new MainDialogResult(chat, pin, approved);
		synchronized (this) {
			this.notify();
		}
	}

	@Override
	public void showProgress(final String message, final int amount, final boolean enabled) {
		this.getMainFrame().showProgress(message, amount, enabled);
	}

	@Override
	public boolean showQuestion(final String title, final String message) {
		return JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(currentFrame, message, title,
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void showError(final String title, final String message) {
		JOptionPane.showMessageDialog(currentFrame, message, title, JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void closeDialogs() {
		if (mainFrame != null) {
			mainFrame.setVisible(false);
		}

		if (changePinFrame != null) {
			changePinFrame.setVisible(false);
		}
	}

	@Override
	public void shutdown() {
		closeDialogs();

		if (statusIndicator != null) {
			statusIndicator.close();
		}
	}

	@Override
	public void showChangePinDialog() {
		if (mainFrame != null) {
			mainFrame.setVisible(false);
		}

		getChangePinFrame().setPanelState(NewChangePinFrame.STATE_CHOOSE);
		getChangePinFrame().setVisible(true);
	}

	@Override
	public void showMessage(final String info, final int type) {
		statusIndicator.displayMessage(null, info, type);
	}

	@Override
	public Object triggerEvent(final int event, final Object... eventData) {
		synchronized (listener) {
			return listener.handleEvent(event, eventData);
		}
	}

	@Override
	public void setEventLister(final EventListener listener) {
		this.listener = listener;
	}

	@Override
	public SecureHolder showCANDialog(final String msg) {
		return CANDialog.show(currentFrame, PropertyResolver.getBundle("text").get("MainView_can_title"), msg);
	}
}
