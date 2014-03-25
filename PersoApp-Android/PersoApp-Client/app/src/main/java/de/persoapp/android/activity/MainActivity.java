package de.persoapp.android.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import de.persoapp.android.activity.fragment.MainFragment;

/**
 * @author Ralf Wondratschek
 */
public class MainActivity extends AbstractNfcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null && !mNfcTester.needsToShowOtherContent()) {
            updateContentFragment();
        }
    }

    @Override
    public void onDeviceNfcCapable() {
        updateContentFragment();
    }

    private void updateContentFragment() {
        Fragment contentFragment = getSupportFragmentManager().findFragmentById(android.R.id.content);
        if (!(contentFragment instanceof MainFragment)) {
            replaceFragment(android.R.id.content, new MainFragment(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
    }
}
