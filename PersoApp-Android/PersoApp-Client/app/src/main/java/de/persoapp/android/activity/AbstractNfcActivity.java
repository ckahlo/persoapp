package de.persoapp.android.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.vrallev.android.base.BaseActivitySupport;

import java.util.List;

import javax.inject.Inject;

import de.persoapp.android.ActivityModule;
import de.persoapp.android.core.adapter.MainViewFacade;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;
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

    protected MainViewFragment mMainViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewFragment = MainViewFragment.findOrCreateFragment(this);
        mMainViewFacade.setMainView(mMainViewFragment);
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
    protected void addModules(List<Object> modules) {
        super.addModules(modules);
        modules.add(new ActivityModule());
    }

    // TODO: check
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//
//        mNfcTransportProvider.handleIntent(intent);
//
//        if (mUri != null) {
//
//            final String tcURL = mUri.getQueryParameter("tcTokenURL");
//            if (tcURL != null) {
//                mMainViewFragment.startAuthentication(tcURL);
//            }
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenuHelper.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mMenuHelper.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
