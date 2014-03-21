package de.persoapp.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
public class AuthenticateActivity extends AbstractNfcActivity {

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        mUri = getIntent().getData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mNfcTransportProvider.handleIntent(intent);

        if (mUri != null) {

            final String tcURL = mUri.getQueryParameter("tcTokenURL");
            if (tcURL != null) {
                mMainViewFragment.setTcTokenUrl(tcURL);
            }
        }
    }
}
