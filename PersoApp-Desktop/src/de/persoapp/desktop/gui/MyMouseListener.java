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
 * The <tt>MyMouseListener</tt> class provides the functionality of regular
 * <tt>MouseListeners</tt>. In case of a <tt>MyMouseListener</tt> is added to a
 * <tt>component</tt>, the name of the <tt>Component</tt> and their
 * subcomponents is set dynamically, during the adding process.
 * <p>
 * <code>public class MyMouseListener extends MouseAdapter</code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public class MyMouseListener extends MouseAdapter {

	/**
	 * The singleton instance of the {@link MyMouseListener}.
	 */
	private static MyMouseListener						instance	= new MyMouseListener();

	/**
	 * The <tt>bundle</tt> which resolves the necessary properties.
	 */
	private final PropertyResolver.Bundle				textBundle;
	
	/**
	 * The cache of all frames.
	 */
	private final HashMap<String, HelpPanelProvider>	frameCache;

	/**
	 * Constructs a new instance of {@link MyMouseListener}.
	 */
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

	/**
	 * Retrieves the {@link HelpPanelProvider}.
	 * 
	 * @param c
	 *            - A underlying <tt>Component</tt> of the
	 *            {@link HelpPanelProvider}.
	 * 
	 * @return Returns the {@link HelpPanelProvider}.
	 */
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
	 * Adds the standard {@link MouseListener} to the given <tt>component</tt>
	 * c. The name of the component is found and set dynamically. The name is
	 * going to be something like
	 * <p>
	 * <ul>
	 * <li>NameOfFrame_ChoosedName_header</li>
	 * <li>NameOfFrame_ChoosedName_description</li>
	 * </ul>
	 * </p>
	 * 
	 * @param frame
	 *            - The <tt>Frame</tt> to set the specified name.
	 * @param c
	 *            - The <tt>Component</tt> to which the listener is going to be
	 *            added.
	 * @param name
	 *            - The name of the component.
	 */
	public static void addListener(final Component c, final String name) {
		addListener(SwingUtilities.getRoot(c), c, name);
	}

	/**
	 * Adds the standard {@link MouseListener} to the given <tt>component</tt>
	 * c. The name of the component is found and set dynamically. The name is
	 * going to be something like
	 * <p>
	 * <ul>
	 * <li>NameOfFrame_ChoosedName_header</li>
	 * <li>NameOfFrame_ChoosedName_description</li>
	 * </ul>
	 * </p>
	 * 
	 * @param frame
	 *            - The <tt>Frame</tt> to set the specified name.
	 * @param c
	 *            - The <tt>Component</tt> to which the listener is going to be
	 *            added.
	 * @param name
	 *            - The name of the component.
	 */
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

	/**
	 * Removes the {@link MyMouseListener} from c and all belonging components.
	 * 
	 * @param c
	 *            - The <tt>Component</tt> which listener is going to be
	 *            removed.
	 */
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
	 * Sets the name for all given components, in a recursive way.
	 * 
	 * @param frame
	 *            - The <tt>Frame</tt>
	 * @param c
	 *            - The <tt>Component</tt> which name is set. The names of the
	 *            <tt>components</tt> of c are set in a recursive way.
	 * @param name
	 *            - The name to be set.
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

	/**
	 * Recursively sets the name of c and all belonging <tt>components</tt>.
	 * 
	 * @param c
	 *            - The <tt>Component</tt> which name is going to be set.
	 * 
	 * @param name
	 *            - The name to be set.
	 */
	public static void setName(final Component c, final String name) {
		setName(SwingUtilities.getRoot(c), c, name);
	}
}
