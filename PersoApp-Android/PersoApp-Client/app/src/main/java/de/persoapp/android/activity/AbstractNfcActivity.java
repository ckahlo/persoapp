package de.persoapp.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.vrallev.android.base.BaseActivitySupport;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.ActivityModule;
import de.persoapp.android.activity.fragment.InitializeAppFragment;
import de.persoapp.android.core.adapter.MainViewFacade;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import de.persoapp.android.nfc.NfcTester;
import de.persoapp.android.view.MenuHelper;

/**
 * @author Ralf Wondratschek
 */
public abstract class AbstractNfcActivity extends BaseActivitySupport {

    @Inject
    protected NfcTransportProvider mNfcTransportProvider;

    @Inject
    protected MainViewFacade mMainViewFacade;

    @Inject
    protected MenuHelper mMenuHelper;

    @Inject
    protected EventBus mEventBus;

    @Inject
    protected NfcTester mNfcTester;

    protected MainViewFragment mMainViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewFragment = MainViewFragment.findOrCreateFragment(this);
        mMainViewFacade.setMainView(mMainViewFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEventBus.register(this, InitializeAppFragment.OnAppInitialized.class, NfcTransportProvider.NfcConnectedEvent.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNfcTransportProvider.enableForegroundDispatch(this);
    }

    @Override
    protected void onPause() {
        mNfcTransportProvider.disableForegroundDispatch(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        mEventBus.unregister(this, InitializeAppFragment.OnAppInitialized.class, NfcTransportProvider.NfcConnectedEvent.class);
        super.onStop();
    }

    @Override
    protected void addModules(List<Object> modules) {
        super.addModules(modules);
        modules.add(new ActivityModule());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenuHelper.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mMenuHelper.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mNfcTransportProvider.handleIntent(intent);
    }

    @SuppressWarnings("UnusedDeclaration")
    public final void onEvent(InitializeAppFragment.OnAppInitialized event) {
        if (event.isSuccess()) {
            onDeviceNfcCapable();
        } else {
            // content will change to DeviceNotCapableFragment
            mNfcTester.needsToShowOtherContent();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public final void onEvent(NfcTransportProvider.NfcConnectedEvent event) {
        mNfcTester.testIsoDep(event.getIsoDep());
    }

    public void onDeviceNfcCapable() {
        // no op
    }
}
