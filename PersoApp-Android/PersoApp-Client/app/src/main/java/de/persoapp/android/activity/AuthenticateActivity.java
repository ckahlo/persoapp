package de.persoapp.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import de.persoapp.android.R;
import de.persoapp.android.activity.fragment.AuthenticateFragment;

/**
 * @author Ralf Wondratschek
 */
public class AuthenticateActivity extends AbstractNfcActivity {

    private static final String TC_TOKEN_PARAMETER = "tcTokenURL";

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUri = getIntent().getData();

        if (mUri == null || mUri.getQueryParameter(TC_TOKEN_PARAMETER) == null) {
            Toast.makeText(this, R.string.invalid_request, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (savedInstanceState == null) {
            replaceFragment(android.R.id.content, new AuthenticateFragment());
        }

        if (mUri != null) {

            final String tcURL = mUri.getQueryParameter("tcTokenURL");
            if (tcURL != null) {
                mMainViewFragment.setTcTokenUrl(tcURL);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mNfcTransportProvider.handleIntent(intent);
    }


}
