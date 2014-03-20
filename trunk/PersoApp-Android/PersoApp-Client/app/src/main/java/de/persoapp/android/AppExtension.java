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
        // ICardHandler cardHandler = CardHandler.getInstance(mainView);

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
