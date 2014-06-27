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

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import de.persoapp.core.util.Util;

/**
 * The <tt>Utils</tt>-class provides some utility functions, for managing the
 * <em>screen</em> and resources.
 * <p>
 * <code>public class Utils</code>
 * </p>
 * 
 * @author Christian Kahlo
 */
public class Utils {

	/**
	 * The resource folder path.
	 */
	private static final String	RESOURCE_FOLDER	= "/resources/";

	/**
	 * Calculates the real screen size, which can be used to display windows.
	 * 
	 * @return Returns the real screen size.
	 */
	public static Dimension getRealScreenSize() {
		//Don't cache. Size changes.
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

		final Insets screenInsets = new Insets((int) maxBounds.getY(), (int) maxBounds.getX(),
				(int) (screenSize.getHeight() - maxBounds.getY() - maxBounds.getHeight()), (int) (screenSize.getWidth()
						- maxBounds.getWidth() - maxBounds.getX()));

		return new Dimension((int) (screenSize.getWidth() - screenInsets.right - screenInsets.left),
				(int) (screenSize.getHeight() - screenInsets.top - screenInsets.bottom));
	}

	/**
	 * Returns the file of the given filename as a {@link InputStream}.
	 * 
	 * @param fileName
	 *            - The filename from the resource.
	 * 
	 * @return The resource as an {@link InputStream}, which can be read.
	 */
	public static InputStream getResourceStream(final String fileName) {
		return Util.class.getResourceAsStream(RESOURCE_FOLDER + fileName);
	}

	/**
	 * Returns the {@link URL} of the specified file.
	 * 
	 * @param fileName
	 *            - The filename from the resource.
	 * 
	 * @return Returns the {@link URL} from the specified resource.
	 */
	public static URL getResourceUrl(final String fileName) {
		return Util.class.getResource(RESOURCE_FOLDER + fileName);
	}

	/**
	 * Returns the {@link BufferedImage} of the specified file.
	 * 
	 * @param fileName
	 *            - The filename of the specified resource.
	 * @return Returns the {@link BufferedImage} of the specified file.
	 */
	public static BufferedImage getImage(final String fileName) {
		try {
			return ImageIO.read(getResourceUrl(fileName));
		} catch (final IOException e) {
			Logging.getLogger().log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
	}
}
