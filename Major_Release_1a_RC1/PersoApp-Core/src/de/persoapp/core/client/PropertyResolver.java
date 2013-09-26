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
 *          PersoApp ist Freie Software: Sie k�nnen es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          sp�teren ver�ffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 * 
 *          PersoApp wird in der Hoffnung, dass es n�tzlich sein wird, aber OHNE
 *          JEDE GEW�HRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gew�hrleistung der MARKTF�HIGKEIT oder EIGNUNG F�R EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License f�r weitere
 *          Details.
 * 
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 * 
 */
package de.persoapp.core.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Bietet statische Methoden für den Zugriff auf Property Dateien und Properties
 * 
 * @author sgruen
 * 
 */
public class PropertyResolver {
	private static final Map<String, Properties>		propertiesCache			= new HashMap<String, Properties>();

	private static final Map<String, ResourceBundle>	resourceBundlesCache	= new HashMap<String, ResourceBundle>();

	/**
	 * Gibt den Wert einer Property zurück. Als Eingabe wird der Name des Files
	 * in dem die Property steht und der Name der Property erwartet. Der
	 * Dateiname ist ohne Dateiendung anzugeben.
	 * 
	 * @param file
	 * @param property
	 * @return
	 */
	public static Properties getProperties(final String fileName) {
		Properties props = propertiesCache.get(fileName);

		if (props == null) {
			synchronized (propertiesCache) {
				if (propertiesCache.get(fileName) == null) {
					try {
						props = new Properties();
						final InputStream in = PropertyResolver.class.getResourceAsStream("/resources/" + fileName);
						if (in != null) {
							props.load(new InputStreamReader(in, "UTF-8"));
							in.close();
						}
					} catch (final IOException e) {
						e.printStackTrace();
					}
					propertiesCache.put(fileName, props);
				}
			}
		}

		return props;
	}

	// XXX: TODO: shall return a null-value or a non-null value?
	// There is code that reads the config.properties which is irritated with
	// non-null but invalid values. Implement a second function for this?
	public static String getProperty(final String file, final String property) {
		if (file == null || property == null) {
			return null;
		}

		final String value = getProperties(file + ".properties").getProperty(property);
		//		if(value == null) {
		//			value = "??? " + property + " ???";
		//		}
		return value;
	}

	/*
	 * use localizable resource bundles under /resources/ tree
	 */

	private static ResourceBundle.Control	utf8Control	= new ResourceBundle.Control() {
															@Override
															public ResourceBundle newBundle(final String baseName,
																	final Locale locale, final String format,
																	final ClassLoader loader, final boolean reload)
																	throws IllegalAccessException,
																	InstantiationException, IOException {

																// The below is a copy of the default implementation.
																final String bundleName = toBundleName(baseName, locale);
																final String resourceName = toResourceName(bundleName,
																		"properties");
																ResourceBundle bundle = null;
																InputStream stream = null;
																if (reload) {
																	final URL url = loader.getResource(resourceName);
																	if (url != null) {
																		final URLConnection connection = url
																				.openConnection();
																		if (connection != null) {
																			connection.setUseCaches(false);
																			stream = connection.getInputStream();
																		}
																	}
																} else {
																	stream = loader.getResourceAsStream(resourceName);
																}
																if (stream != null) {
																	try {
																		// Only this line is changed to make it to read properties files as UTF-8.
																		bundle = new PropertyResourceBundle(
																				new InputStreamReader(stream, "UTF-8"));
																	} finally {
																		stream.close();
																	}
																}
																return bundle;
															}
														};

	private static ResourceBundle getResourceBundle(final String name) {
		ResourceBundle resBundle = resourceBundlesCache.get(name);

		if (resBundle == null) {
			synchronized (resourceBundlesCache) {
				if (resourceBundlesCache.get(name) == null) {
					resBundle = ResourceBundle.getBundle("resources." + name, Locale.getDefault(),
							PropertyResolver.class.getClassLoader(), utf8Control);
					resourceBundlesCache.put(name, resBundle);
				}
			}
		}

		return resBundle;
	}

	public static String getString(final String resource, final String key) {
		if (resource == null || key == null) {
			return null;
		}

		if (getResourceBundle(resource).getObject(key) == null) {
			return "??? " + key + " ???";
		}

		return getResourceBundle(resource).getString(key);
	}

	public static Bundle getBundle(final String resource) {
		return new Bundle(getResourceBundle(resource));
	}

	public static class Bundle {
		private final ResourceBundle	resBundle;

		private Bundle(final ResourceBundle resBundle) {
			this.resBundle = resBundle;
		}

		public String get(final String key) {
			return this.resBundle.getString(key);
		}
	}
}
