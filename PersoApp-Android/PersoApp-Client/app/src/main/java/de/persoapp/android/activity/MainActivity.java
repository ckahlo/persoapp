package de.persoapp.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import net.vrallev.android.base.settings.SettingsMgr;

import javax.inject.Inject;

import de.persoapp.android.BuildConfig;
import de.persoapp.android.activity.fragment.InitializeAppFragment;
import de.persoapp.android.activity.fragment.MainFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;

/**
 * @author Ralf Wondratschek
 */
public class MainActivity extends AbstractNfcActivity {

    private static final String APP_NPA_CAPABLE = "firstStart";

    @Inject
    SettingsMgr mSettingsMgr;

    @Inject
    NfcTransportProvider mNfcTransportProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null &&BuildConfig.DEBUG) {
            // TODO: remove
//            mSettingsMgr.putBoolean(APP_NPA_CAPABLE, false);
        }

        updateContentFragment();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mNfcTransportProvider.handleIntent(intent);
    }

    public void onAppInitialized(boolean success) {
        if (success) {
            mSettingsMgr.putBoolean(APP_NPA_CAPABLE, true);
            updateContentFragment();
        } else {
            Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
            // TODO: retry
        }
    }

    private void updateContentFragment() {
        Fragment contentFragment = getSupportFragmentManager().findFragmentById(android.R.id.content);

        if (mSettingsMgr.getBoolean(APP_NPA_CAPABLE, false)) {
            if (!(contentFragment instanceof MainFragment)) {
                replaceFragment(android.R.id.content, new MainFragment(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            }

        } else {
            if (!(contentFragment instanceof InitializeAppFragment)) {
                replaceFragment(android.R.id.content, new InitializeAppFragment());
            }
        }
    }
}
