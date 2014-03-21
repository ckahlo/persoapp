package de.persoapp.android;

import android.content.Context;
import android.nfc.NfcManager;

import net.vrallev.android.base.BaseAppModule;
import net.vrallev.android.base.ForApplication;
import net.vrallev.android.base.settings.SettingsMgr;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.greenrobot.event.EventBus;
import de.persoapp.android.core.adapter.MainViewFacade;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;

/**
 * @author Ralf Wondratschek
 */
@SuppressWarnings("UnusedDeclaration")
@Module(
        overrides = true,
        includes = {
                BaseAppModule.class
        },
        injects = {
                AppExtension.class,

                MainViewFragment.class
        }
)
public class AppModule {

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    SettingsMgr provideSettingsMgr(@ForApplication Context context) {
        return new SettingsMgr(context);
    }

    @Provides
    @Singleton
    NfcTransportProvider provideNfcTransportProvider(NfcManager nfcManager) {
        return new NfcTransportProvider(nfcManager);
    }

    @Provides
    @Singleton
    MainViewFacade provideMainViewFacade() {
        return new MainViewFacade();
    }
}
