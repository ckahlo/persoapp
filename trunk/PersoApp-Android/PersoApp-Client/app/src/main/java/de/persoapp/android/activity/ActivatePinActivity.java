package de.persoapp.android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import de.persoapp.android.R;
import de.persoapp.android.activity.fragment.ActivatePinFragment;

/**
 * @author Ralf Wondratschek
 *
 * TODO: move buttons to the top
 *
 */
public class ActivatePinActivity extends AbstractNfcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        if (savedInstanceState == null) {
            replaceFragment(R.id.frameLayout, new ActivatePinFragment(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }
    }

    public void onConfirmPressed() {

    }
}
