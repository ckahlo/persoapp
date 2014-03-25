package de.persoapp.android.activity;

import android.os.Bundle;

import net.vrallev.android.base.BaseActivity;

import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
public class LicenseActivity extends BaseActivity {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licenses);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
