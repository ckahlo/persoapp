package de.persoapp.android.activity;

import android.os.Bundle;

import de.persoapp.android.activity.fragment.CurrentPinFragment;

/**
 * @author Ralf Wondratschek
 */
public class ChangePinActivity extends AbstractNfcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_activate_pin);
        if (savedInstanceState == null) {
            replaceFragment(android.R.id.content, new CurrentPinFragment());
        }
    }
}
