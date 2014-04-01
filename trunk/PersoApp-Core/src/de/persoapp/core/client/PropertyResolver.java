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
 * Bietet statische Methoden für den Zugriff auf Property Dateien und Properties (
 *
 * @author Ralf Wondratschek
 *
 */
public class PropertyResolver {

    private final Map<String, Properties> mPropertiesMap;
    private final Map<String, Bundle> mBundleMap;

    public PropertyResolver() {
        mPropertiesMap = new HashMap<String, Properties>();
        mBundleMap = new HashMap<String, Bundle>();
    }

    public Properties putProperties(String key, Properties properties) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mPropertiesMap.put(key, properties);
    }

    public Properties putProperties(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("The fileName must not be null!");
        }


        InputStream inputStream = null;
        try {
            Properties properties = new Properties();

            inputStream = PropertyResolver.class.getResourceAsStream(fileName);
            properties.load(new InputStreamReader(inputStream, "UTF-8"));
            putProperties(fileName, properties);

            return properties;

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
        }

        return null;
    }

    public Properties getPropertiesAll(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mPropertiesMap.get(key);
    }

    public String getPropertySingle(String key, String concreteProperty) {
        if (concreteProperty == null) {
            throw new IllegalArgumentException("The concreteProperty must not be null!");
        }
        return getPropertiesAll(key).getProperty(concreteProperty);
    }

    public Bundle putBundle(String key, Bundle bundle) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mBundleMap.put(key, bundle);
    }

    public Bundle getBundleAll(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mBundleMap.get(key);
    }

    public String getBundleString(String key, String concreteString) {
        if (concreteString == null) {
            throw new IllegalArgumentException("The concreteString must not be null!");
        }
        return getBundleAll(key).get(concreteString);
    }

    /*
     * Old support interface
     */

    private static final PropertyResolver DEFAULT_PROPERTY_RESOLVER = new PropertyResolver();

    public static PropertyResolver getDefaultPropertyResolver() {
        return DEFAULT_PROPERTY_RESOLVER;
    }

    public static Properties getProperties(final String fileName) {
        Properties properties = DEFAULT_PROPERTY_RESOLVER.getPropertiesAll(fileName);
        if (properties == null) {
            synchronized (DEFAULT_PROPERTY_RESOLVER) {
                properties = DEFAULT_PROPERTY_RESOLVER.getPropertiesAll(fileName);
                if (properties == null) {
                    final String key = "/resources/" + fileName;
                    DEFAULT_PROPERTY_RESOLVER.putProperties(key);
                    properties = DEFAULT_PROPERTY_RESOLVER.getPropertiesAll(key);
                }
            }
        }

        return properties;
    }

    // XXX: TODO: shall return a null-value or a non-null value?
    // There is code that reads the config.properties which is irritated with
    // non-null but invalid values. Implement a second function for this?
    public static String getProperty(final String file, final String property) {
        Properties properties = getProperties(file + ".properties");
        return properties == null ? null : properties.getProperty(property);
    }

    public static Bundle getBundle(String name) {
        Bundle bundle = DEFAULT_PROPERTY_RESOLVER.getBundleAll(name);
        if (bundle == null) {
            synchronized (DEFAULT_PROPERTY_RESOLVER) {
                bundle = DEFAULT_PROPERTY_RESOLVER.getBundleAll(name);
                if (bundle == null) {
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("resources." + name, Locale.getDefault(), PropertyResolver.class.getClassLoader(), UTF8_CONTROL);
                    bundle = new ResourceBundleImplementation(resourceBundle);
                    DEFAULT_PROPERTY_RESOLVER.putBundle(name, bundle);
                }
            }
        }

        return bundle;
    }

    public static String getString(String resource, String key) {
        Bundle bundle = getBundle(resource);
        return bundle == null ? null : bundle.get(key);
    }

    public static interface Bundle {
        public String get(String key);
    }

    public static class ResourceBundleImplementation implements Bundle {

        private final ResourceBundle mResourceBundle;

        private ResourceBundleImplementation(ResourceBundle resourceBundle) {
            if (resourceBundle == null) {
                throw new IllegalArgumentException("A resource bundle must not be null!");
            }
            mResourceBundle = resourceBundle;
        }

        @Override
        public String get(String key) {
            return mResourceBundle.getString(key);
        }
    }

    public static class PropertyBundleImplementation implements Bundle {

        private final Properties mProperties;

        public PropertyBundleImplementation(Properties properties) {
            mProperties = properties;
        }

        @Override
        public String get(String key) {
            return mProperties.getProperty(key);
        }
    }

    private static final ResourceBundle.Control UTF8_CONTROL = new ResourceBundle.Control() {
        @Override
        public ResourceBundle newBundle(final String baseName, final Locale locale, final String format, final ClassLoader loader, final boolean reload) throws IllegalAccessException, InstantiationException, IOException {

            // The below is a copy of the default implementation.
            final String bundleName = toBundleName(baseName, locale);
            final String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                final URL url = loader.getResource(resourceName);
                if (url != null) {
                    final URLConnection connection = url.openConnection();
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
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }

            return bundle;
        }
    };
}
