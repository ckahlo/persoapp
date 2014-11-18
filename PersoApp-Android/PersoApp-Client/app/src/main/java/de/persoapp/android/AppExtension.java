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
package de.persoapp.android;

import android.content.res.AssetManager;

import net.vrallev.android.base.BaseApp;
import net.vrallev.android.base.util.Cat;
import net.vrallev.android.base.util.IoUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.inject.Inject;

import de.persoapp.android.core.adapter.AndroidCardHandler;
import de.persoapp.android.core.adapter.MainViewFacade;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.client.MainViewEventListener;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.ws.IFDService;
import de.persoapp.core.ws.SALService;
import de.persoapp.core.ws.engine.WSContainer;
import de.persoapp.lib.paos.PAOSInitiatorExtension;

/**
 * This class setups the connection to the core library.
 *
 * @author Ralf Wondratschek
 */
public class AppExtension extends BaseApp {

    @Inject
    NfcTransportProvider mNfcTransportProvider;

    @Inject
    MainViewFacade mMainViewFacade;

    @Override
    public void onCreate() {
        super.onCreate();

        inject(this);

        initJaxb();
        initPropertyResolver();
        initWebService();

    }

    @Override
    protected void addModules(List<Object> modules) {
        super.addModules(modules);
        modules.add(new AppModule());
    }

    protected void initJaxb() {
        PAOSInitiatorExtension.setup();
    }

    protected void initPropertyResolver() {
        PropertyResolver defaultPropertyResolver = PropertyResolver.getDefaultPropertyResolver();
        defaultPropertyResolver.putProperties("config.properties", loadProps(getAssets(), "config.properties"));
        defaultPropertyResolver.putBundle("text", new PropertyResolver.PropertyBundleImplementation(loadProps(getAssets(), "text.properties")));
        defaultPropertyResolver.putBundle("text_core", new PropertyResolver.PropertyBundleImplementation(loadProps(getAssets(), "text_core.properties")));
    }

    protected void initWebService() {
        ICardHandler cardHandler = new AndroidCardHandler(mMainViewFacade, mNfcTransportProvider);

        mMainViewFacade.setEventLister(new MainViewEventListener(cardHandler, mMainViewFacade));

        WSContainer wsContainer = new WSContainer();
        wsContainer.addService(new SALService());
        wsContainer.addService(new IFDService());

        wsContainer.init(null);

        ECardWorker.init(mMainViewFacade, wsContainer, cardHandler);
    }

    private static Properties loadProps(AssetManager assetManager, String name) {
        InputStreamReader reader = null;
        try {
            Properties properties = new Properties();
            reader = new InputStreamReader(assetManager.open(name), "UTF-8");
            properties.load(reader);
            return properties;

        } catch (IOException e) {
            Cat.e(e);
            return null;

        } finally {
            IoUtils.closeQuietly(reader);
        }
    }
}
