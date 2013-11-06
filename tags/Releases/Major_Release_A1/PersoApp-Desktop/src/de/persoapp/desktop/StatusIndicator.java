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

import java.awt.AWTException;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.gui.frame.AboutFrame;

/**
 * @author ckahlo
 * 
 */
public class StatusIndicator {

	protected SystemTray		tray;
	protected final TrayIcon	trayIcon;
	private final JDialog		infoFrame;
	protected String			defaultTitle;

	public StatusIndicator() {
		final ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (e.getActionCommand().equals("StatusIndicator_tray_about")) {
					new AboutFrame();
				} else if (e.getActionCommand().equals("changepin")) {
					MainView.getInstance().showChangePinDialog();
				} else if (e.getActionCommand().equals("exit")) {
					System.exit(0);
				} else if (e.getActionCommand().equals("icon")) {
					//Passiert nur bei Doppelklick, das wird aber korrekt mit MouseListener gelÃ¶st
					Logging.getLogger().log(Level.INFO, "Icon clicked");
				} else {
					Logging.getLogger().log(Level.INFO, "Action performed " + e);
				}
			}
		};

		final PropertyResolver.Bundle textBundle = PropertyResolver.getBundle("text");

		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();

			final PopupMenu popup = new PopupMenu();

			MenuItem menuItem = new MenuItem(textBundle.get("StatusIndicator_tray_about"));
			menuItem.setActionCommand("StatusIndicator_tray_about");
			menuItem.addActionListener(actionListener);
			popup.add(menuItem);

			menuItem = new MenuItem(textBundle.get("StatusIndicator_tray_pin"));
			menuItem.setActionCommand("changepin");
			menuItem.addActionListener(actionListener);
			popup.add(menuItem);

			menuItem = new MenuItem(textBundle.get("StatusIndicator_tray_exit"));
			menuItem.setActionCommand("exit");
			menuItem.addActionListener(actionListener);
			popup.add(menuItem);

			/*
			 * popup.addSeparator(); menuItem = new MenuItem("Konfiguration");
			 * menuItem.setActionCommand("config");
			 * menuItem.addActionListener(actionListener); popup.add(menuItem);
			 */

			/*
			 * menuItem = new MenuItem("Status");
			 * menuItem.setActionCommand("status");
			 * menuItem.addActionListener(actionListener); popup.add(menuItem);
			 */

			trayIcon = new TrayIcon(Configuration.TRAY_ICON, null, popup);
			//trayIcon = new TrayIcon(Configuration.loadImage("/resources/icon_32px.png"), null, popup);

			trayIcon.setImageAutoSize(true);
			trayIcon.setActionCommand("icon");
			trayIcon.addActionListener(actionListener);

			trayIcon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(final MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
						new AboutFrame();
					}
				}
			});

			try {
				tray.add(trayIcon);
			} catch (final AWTException e) {
				Logging.getLogger().log(Level.WARNING, "TrayIcon could not be added.");
			}

			infoFrame = null;
		} else {
			//System.setProperty("apple.laf.userScreenMenuBar", "true");

			infoFrame = new JDialog();

			infoFrame.setUndecorated(true);
			infoFrame.setResizable(false);
			infoFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			//infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			//infoFrame.setBackground(Color.GREEN);

			//com.sun.awt.AWTUtilities.setWindowOpacity(infoFrame, 0.66f);

			final JMenu m = new JMenu("");
			JMenuItem menuItem = new JMenuItem(textBundle.get("StatusIndicator_tray_about"));
			menuItem.setActionCommand("StatusIndicator_tray_about");
			menuItem.addActionListener(actionListener);
			m.add(menuItem);

			menuItem = new JMenuItem(textBundle.get("StatusIndicator_tray_pin"));
			menuItem.setActionCommand("changepin");
			menuItem.addActionListener(actionListener);
			m.add(menuItem);

			menuItem = new JMenuItem(textBundle.get("StatusIndicator_tray_exit"));
			menuItem.setActionCommand("exit");
			menuItem.addActionListener(actionListener);
			m.add(menuItem);

			final JMenuBar mb = new JMenuBar();

			//TODO: check
			//			Icon icon = new ImageIcon(Utils.getImage("icon_32px.png"));
			final Icon icon = new ImageIcon(Configuration.TRAY_ICON);

			m.setBackground(Color.WHITE);
			m.setIcon(icon);
			m.setEnabled(true);

			mb.add(m);
			mb.setBackground(Color.WHITE);

			infoFrame.setJMenuBar(mb);

			infoFrame.addWindowListener(new WindowListener() {

				@Override
				public void windowActivated(final WindowEvent arg0) {
				}

				@Override
				public void windowClosed(final WindowEvent arg0) {
				}

				@Override
				public void windowClosing(final WindowEvent arg0) {
					infoFrame.dispose();

					if (infoFrame.isUndecorated()) {
						infoFrame.setUndecorated(false);
						infoFrame.setSize(50, 64);
					} else {
						infoFrame.setUndecorated(true);
						infoFrame.setSize(50, 40);
					}

					infoFrame.setVisible(true);
				}

				@Override
				public void windowDeactivated(final WindowEvent arg0) {
				}

				@Override
				public void windowDeiconified(final WindowEvent arg0) {
				}

				@Override
				public void windowIconified(final WindowEvent arg0) {
				}

				@Override
				public void windowOpened(final WindowEvent arg0) {
				}
			});

			class MyMouseListener implements MouseListener, MouseMotionListener {
				@Override
				public void mouseDragged(final MouseEvent arg0) {
					final Point location = arg0.getLocationOnScreen();
					infoFrame.setLocation(location);
				}

				@Override
				public void mouseMoved(final MouseEvent arg0) {
				}

				@Override
				public void mouseClicked(final MouseEvent arg0) {
				}

				@Override
				public void mouseEntered(final MouseEvent arg0) {
				}

				@Override
				public void mouseExited(final MouseEvent arg0) {
				}

				@Override
				public void mousePressed(final MouseEvent arg0) {
					//System.out.println(arg0);
					//					infoFrame.dispose();
					//					
					//					if(infoFrame.isUndecorated()) {
					//						infoFrame.setUndecorated(false);
					//						infoFrame.setSize(50, 64);
					//					} else {
					//						infoFrame.setUndecorated(true);
					//						infoFrame.setSize(50, 40);
					//					}
					//					
					//					infoFrame.setVisible(true);
				}

				@Override
				public void mouseReleased(final MouseEvent arg0) {
				}
			}

			final MyMouseListener mml = new MyMouseListener();

			infoFrame.addMouseMotionListener(mml);
			infoFrame.addMouseListener(mml);

			//infoFrame.pack();
			infoFrame.setSize(50, 40);
			infoFrame.setAlwaysOnTop(true);

			final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			final GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
			final Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
			final int x = (int) rect.getMaxX() - infoFrame.getWidth();
			final int y = (int) rect.getMaxY() - infoFrame.getHeight();
			infoFrame.setLocation(x, y);

			infoFrame.setVisible(true);

			trayIcon = null;
		}
	}

	public void displayMessage(String title, final String message, final int type) {
		if (title == null) {
			title = this.defaultTitle;
		}

		if (trayIcon != null) {
			MessageType messageType = null;
			switch (type) {
				case IMainView.ERROR:
					messageType = MessageType.ERROR;
					break;
				case IMainView.WARNING:
					messageType = MessageType.WARNING;
					break;
				case IMainView.INFO:
					messageType = MessageType.INFO;
					break;

				case IMainView.SUCCESS:
				case IMainView.RELOAD:
					messageType = MessageType.NONE;
					break;
			}
			trayIcon.displayMessage(title, message, messageType);
		} else {
			if (!infoFrame.isDisplayable()) {
				infoFrame.setVisible(true);
			}
		}
	}

	public void setDefaultTitle(final String defaultTitle) {
		this.defaultTitle = defaultTitle;
		if (trayIcon != null) {
			trayIcon.setToolTip(this.defaultTitle);
		}
	}

	public String getDefaultTitle() {
		return defaultTitle;
	}

	public void close() {
		if (tray != null && trayIcon != null) {
			tray.remove(trayIcon);
		} else if (infoFrame != null) {
			infoFrame.dispose();
		}
	}
}
