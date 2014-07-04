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
 * <p>
 * The <tt>PropertyResolver</tt> offers static methods to access property files
 * and properties.
 * </p>
 * <p>
 * <code>public class PropertyResolver</code>
 * </p>
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class PropertyResolver {

	/**
	 * The stored properties.
	 */
    private final Map<String, Properties> mPropertiesMap;
   
    /**
     * The stored property-bundles.
     */
    private final Map<String, Bundle> mBundleMap;

    /**
     * Creates and initializes a new empty {@link PropertyResolver}.
     */
    public PropertyResolver() {
        mPropertiesMap = new HashMap<String, Properties>();
        mBundleMap = new HashMap<String, Bundle>();
    }

	/**
	 * Stores the given <em>properties</em> mapped to the provided <em>key</em> in
	 * the {@link PropertieResolver}.
	 * 
	 * @param key
	 *            - The provided key, for storing the <em>property</em>. Can't
	 *            be <strong>null</strong>.
	 * @param properties
	 *            - The stored properties.
	 * 
	 * @return Returns the value, which is associated with the <em>key</em>.
	 * 
	 * @throws IllegalArgumentException
	 *             Throws a <code>IllegalArgumentException</code>, if the
	 *             <em>key</em> is <strong>null</strong>, because the underlying
	 *             implementation doesn't support a <strong>null</strong> key.
	 */
    public Properties putProperties(String key, Properties properties) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mPropertiesMap.put(key, properties);
    }

	/**
	 * Stores the file, which is associated to the given <em>filename</em>, in the properties.
	 * 
	 * @param fileName
	 *            - The name of the file which is stored.
	 * 
	 * @return Returns the stored <em>file</em> as a property.
	 * 
	 * @throws IllegalArgumentException
	 *             Throws a IllegalArgumentException if the <em>filename</em> is
	 *             <strong>null</strong>, because a <strong>null</strong>
	 *             filename would lead to a {@link NullPointerException}.
	 */
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

	/**
	 * Returns the property which is associated with the given <em>key</em>.
	 * 
	 * @param key
	 *            - The provided key. Can't be <strong>null</strong>.
	 * 
	 * @return - The property which is associated to the provided key, or
	 *         <strong>null</strong>, if the key doesn't exist.
	 * 
	 * @throws IllegalArgumentException
	 *             Throws a <code>IllegalArgumentException</code>, if the
	 *             <em>key</em> is <strong>null</strong>, because the underlying
	 *             implementation doesn't support a <strong>null</strong> key.
	 */
    public Properties getPropertiesAll(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mPropertiesMap.get(key);
    }

	/**
	 * Returns a specific property associated with the given key.
	 * 
	 * @param key
	 *            - The used <em>key</em>.
	 * @param concreteProperty
	 *            - The specific <em>property</em>.
	 * 
	 * @return Returns a specific property <tt>string</tt>, or
	 *         <strong>null</strong>, if the property doesn't exist.
	 * 
	 * @throws IllegalArgumentException
	 *             Throws a <tt>IllegalArgumentException</tt> if the
	 *             <em>concreteProperty</em> is <strong>null</strong>.
	 */
    public String getPropertySingle(String key, String concreteProperty) {
        if (concreteProperty == null) {
            throw new IllegalArgumentException("The concreteProperty must not be null!");
        }
        return getPropertiesAll(key).getProperty(concreteProperty);
    }

	/**
	 * Returns the bundle, which is stored into the properties.
	 * 
	 * @param key
	 *            - The <em>key</em>, which is used for storing.
	 * @param bundle
	 *            - The stored <em>bundle</em>.
	 * 
	 * @return Returns the bundle, which is now stored into the properties and
	 *         associated with the given key.
	 * 
	 * @throws IllegalArgumentException
	 *             Throws a <code>IllegalArgumentException</code>, if the
	 *             <em>key</em> is <strong>null</strong>, because the underlying
	 *             implementation doesn't support a <strong>null</strong> key.
	 */
    public Bundle putBundle(String key, Bundle bundle) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mBundleMap.put(key, bundle);
    }

	/**
	 * Returns all bundles, which are associated with the given key.
	 * 
	 * @param key
	 *            - The <em>key</em> which is used to retrieve the bundles.
	 *            Can't be <strong>null</strong>.
	 * 
	 * @return Returns all bundles, which are associated with the given key.

	 * @throws IllegalArgumentException
	 *             Throws a <code>IllegalArgumentException</code>, if the
	 *             <em>key</em> is <strong>null</strong>, because the underlying
	 *             implementation doesn't support a <strong>null</strong> key.
	 */
    public Bundle getBundleAll(String key) {
        if (key == null) {
            throw new IllegalArgumentException("The key must not be null!");
        }
        return mBundleMap.get(key);
    }

	/**
	 * Returns the concrete {@link String} representation of the searched
	 * bundle, or <strong>null</strong> if the searched bundle doesn't exist.
	 * 
	 * @param key
	 *            - The <em>key</em>, which is associated with the searched
	 *            bundle.
	 * @param concreteString
	 *            - The name of the searched bundle.
	 * 
	 * @return Returns the concrete {@link String} representation of the
	 *         searched bundle, or <strong>null</strong> if the searched bundle
	 *         doesn't exist.
	 * 
	 * @throws IllegalArgumentException
	 *             If <em>concreteString</em> is <strong>null</strong>.
	 */
    public String getBundleString(String key, String concreteString) {
        if (concreteString == null) {
            throw new IllegalArgumentException("The concreteString must not be null!");
        }
        return getBundleAll(key).get(concreteString);
    }

    /*
     * Old support interface
     */

    /**
     * The resolver for the default properties.
     */
    private static final PropertyResolver DEFAULT_PROPERTY_RESOLVER = new PropertyResolver();

	/**
	 * Returns the resolver for the default properties.
	 * 
	 * @return Returns the resolver for the default properties.
	 */
    public static PropertyResolver getDefaultPropertyResolver() {
        return DEFAULT_PROPERTY_RESOLVER;
    }

	/**
	 * Retrieves all properties of a file, which is associated with the given
	 * <em>filename</em>.
	 * 
	 * @param fileName
	 *            - The name of the resource file.
	 *            
	 * @return Retrieves all properties of a file, which is associated with the
	 *         given <em>filename</em>.
	 */
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
	/**
	 * Returns the specified <em>property</em> of a specified <em>file</em> or
	 * <strong>null</strong>, if the resource file or the <em>property</em>
	 * doesn't exist.
	 * 
	 * @param file
	 *            - The desired resource file (.properties).
	 * @param property
	 *            - The searched <em>property</em>
	 * @return Returns the specified <em>property</em> of a specified
	 *         <em>file</em> or <strong>null</strong>, if the resource file or
	 *         the <em>property</em> doesn't exist.
	 */
    public static String getProperty(final String file, final String property) {
        Properties properties = getProperties(file + ".properties");
        return properties == null ? null : properties.getProperty(property);
    }

	/**
	 * Returns the bundle, which is associated with the given <em>name</em> or
	 * returns a new one.
	 * 
	 * @param name
	 *            - The identifier of the searched bundle.
	 * 
	 * @return Returns a {@link ResourceBundleImplementation}, with all
	 *         previously created ressources.
	 */
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

	/**
	 * Returns <strong>null</strong> if the key isn't associated with a
	 * resource. Otherwise the function returns a string representation of the
	 * searched resource.
	 * 
	 * @param resource
	 *            - The identifier of the {@link ResourceBundle}.
	 * @param key
	 *            - The identifier of the searched resource.
	 * 
	 * @return Returns <strong>null</strong> if the key isn't associated with a
	 *         resource. Otherwise the function returns a string representation
	 *         of the searched resource.
	 */
    public static String getString(String resource, String key) {
        Bundle bundle = getBundle(resource);
        return bundle == null ? null : bundle.get(key);
    }

	/**
	 * The defined <tt>Bundle</tt> interface declares a method to gather string
	 * representations from resources of a {@link ResourceBundle}.
	 * 
	 * @author Christian Kahlo, Ralf Wondratschek
	 */
    public static interface Bundle {
    	
		/**
		 * Returns a <tt>String</tt> representation of the object, which is
		 * associated with the given <em>key</em>. If no object can't be found
		 * in the current {@link ResourceBundle} and the parent bundle is
		 * <strong>not null</strong>, the function tries to obtain the desired
		 * object in the parent bundle.
		 * <p>
		 * When the desired object can't be found in the parent bundle, the
		 * function throws a {@link MissingResourceException}.
		 * </p>
		 * 
		 * @param key
		 *            - The key, which is associated with the searched object.
		 * @return Returns the <tt>string representation</tt> of the object,
		 *         which is associated with the key.
		 *         
		 * @throws MissingResourceException
		 *             Throws a <tt>MissingResourceException</tt> if the desired
		 *             object can't be found.
		 * @throws NullPointerException
		 *             If <em>key</em> is <strong>null</strong>.
		 */
        public String get(String key);
    }

	/**
	 * The <tt>ResourceBundleImplementation</tt> provides a <tt>private</tt>
	 * constructor and a wrapper function of the
	 * <p>
	 * <code>public String getString(String key)</code>
	 * </p>
	 * method. The <tt>private</tt> constructor ensures the internal creation of
	 * <tt>ResourceBundle</tt>-objects.
	 * <p>
	 * The <tt>ResourceBundleImplementation</tt> is a wrapper class for common
	 * {@link ResourceBundle}-objects.
	 * </p>
	 * 
	 * @author Christian Kahlo, Ralf Wondratschek
	 */
    public static class ResourceBundleImplementation implements Bundle {

    	/**
    	 * The current instance of the {@link ResourceBundle}.
    	 */
        private final ResourceBundle mResourceBundle;

		/**
		 * Creates and initializes new instance of
		 * {@link ResourceBundleImplementation}.
		 * 
		 * @param resourceBundle
		 *            - The <em>resourceBundle</em>, which is going to be
		 *            wrapped. Can't be <strong>null</strong>.
		 * 
		 * @throws IllegalArgumentException
		 *             If the <em>resourceBundle</em> is <strong>null</strong>.
		 */
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

	/**
	 * The <tt>PropertyBundleImplementation</tt> is a wrapper class for common
	 * {@link Properties}. The wrapper class provides a overridden
	 * <tt>getString()</tt>-Method, to retrieve {@link String} representations
	 * from properties.
	 * <p>
	 * <code> public static class PropertyBundleImplementation implements Bundle </code>
	 * 
	 * @author Christian Kahlo, Ralf Wondratschek
	 */
    public static class PropertyBundleImplementation implements Bundle {

    	/**
    	 * The current set {@link Properties}.
    	 */
        private final Properties mProperties;

        /**
         * Creates and initializes a new object of {@link PropertyBundleImplementation}.
         * 
         * @param properties 
         */
        public PropertyBundleImplementation(Properties properties) {
            mProperties = properties;
        }
        
		/**
		 * Returns the string representation of the searched property or
		 * <strong>null</strong>, if the searched property doesn't exist.
		 * 
		 * @param key
		 *            - The <em>key</em> which is associated to the searched
		 *            <em>property</em>.
		 * @return Returns the string representation of the searched property or
		 *         <strong>null</strong>.
		 */
        @Override
        public String get(String key) {
            return mProperties.getProperty(key);
        }
    }

    /**
     * The <code>ResouceBundle</code> reads <em>property-files</em> as <tt>UTF-8</tt>.
     */
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
