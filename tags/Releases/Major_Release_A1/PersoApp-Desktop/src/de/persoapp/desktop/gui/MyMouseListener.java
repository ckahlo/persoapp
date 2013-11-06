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

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;

import de.persoapp.core.client.PropertyResolver;
import de.persoapp.desktop.gui.frame.HelpPanelProvider;

/*
 * TODO: refactor this class Its intent was to reduce the number of MouseAdapter
 * instances, however, the great degree of complexity isn't maintainable
 * anymore.
 */
/**
 * @author ckahlo
 * 
 */
public class MyMouseListener extends MouseAdapter {

	private static MyMouseListener						instance	= new MyMouseListener();

	private final PropertyResolver.Bundle				textBundle;
	private final HashMap<String, HelpPanelProvider>	frameCache;

	private MyMouseListener() {
		textBundle = PropertyResolver.getBundle("text");
		frameCache = new HashMap<String, HelpPanelProvider>();
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		final HelpPanelProvider provider = getHelpPanelProvider(e.getComponent());

		if (provider != null) {
			provider.setHelpPanelText(textBundle.get(e.getComponent().getName() + "_header"),
					textBundle.get(e.getComponent().getName() + "_description"));
		}
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		final HelpPanelProvider helpPanelProvider = getHelpPanelProvider(e.getComponent());
		if (helpPanelProvider != null) {
			helpPanelProvider.clearHelpPanelText();
		}
	}

	private HelpPanelProvider getHelpPanelProvider(final Component c) {
		if (c.getName() == null) {
			return null;
		}

		final String frameName = c.getName().substring(0, c.getName().indexOf("_"));
		HelpPanelProvider result = frameCache.get(frameName);

		if (result == null) {
			final Component frame = SwingUtilities.getRoot(c);
			if (frame instanceof HelpPanelProvider) {
				frameCache.put(frameName, (HelpPanelProvider) frame);
				result = (HelpPanelProvider) frame;
			}
		}

		return result;
	}

	/**
	 * FÃ¼gt den Standard-MouseListener der Komponente hinzu. Weiterhin wird der
	 * Name der Komponente gesetzt.
	 * 
	 * Der Name der Komponente setzt sich aus FrameKlassenName_Parameter-Name
	 * zusammen. Der FrameKlassenName wird dynamisch gefunden. In der
	 * text.properties Datei wÃ¼rde beispielhaft stehen:
	 * 
	 * NameVomFrame_GewÃ¤hlterName_header
	 * NameVomFrame_GewÃ¤hlterName_description
	 */
	public static void addListener(final Component c, final String name) {
		addListener(SwingUtilities.getRoot(c), c, name);
	}

	public static void addListener(final Component frame, final Component c, final String name) {
		if (!(frame instanceof HelpPanelProvider)) {
			throw new IllegalArgumentException("frame must be an instance of HelpPanelProvider");
		}

		c.addMouseListener(instance);
		c.setName(frame.getName() + "_" + name);

		if (c instanceof JPanel || c instanceof JScrollPane || c instanceof JViewport) {
			final JComponent panel = (JComponent) c;
			for (int i = 0; i < panel.getComponentCount(); i++) {
				addListener(frame, panel.getComponent(i), name);
			}
		}
	}

	public static void removeListener(final Component c) {
		c.removeMouseListener(instance);
		c.setName("");

		if (c instanceof JPanel || c instanceof JScrollPane || c instanceof JViewport) {
			final JComponent panel = (JComponent) c;
			for (int i = 0; i < panel.getComponentCount(); i++) {
				removeListener(panel.getComponent(i));
			}
		}
	}

	/**
	 * Setzt rekursiv fÃ¼r alle enthaltenen Komponenten den Namen.
	 */
	public static void setName(final Component frame, final Component c, final String name) {
		if (!(frame instanceof HelpPanelProvider)) {
			throw new IllegalArgumentException("frame must be an instance of HelpPanelProvider");
		}

		c.setName(frame.getName() + "_" + name);

		if (c instanceof JPanel || c instanceof JScrollPane || c instanceof JViewport) {
			final JComponent panel = (JComponent) c;
			for (int i = 0; i < panel.getComponentCount(); i++) {
				setName(frame, panel.getComponent(i), name);
			}
		}
	}

	public static void setName(final Component c, final String name) {
		setName(SwingUtilities.getRoot(c), c, name);
	}
}
