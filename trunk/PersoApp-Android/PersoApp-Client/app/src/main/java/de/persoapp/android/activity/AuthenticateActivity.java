package de.persoapp.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import net.vrallev.android.base.util.Cat;

import java.util.Arrays;

import de.persoapp.android.BuildConfig;
import de.persoapp.android.R;
import de.persoapp.android.activity.fragment.AuthenticateFragment;
import de.persoapp.android.activity.fragment.ProgressFragment;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;

/**
 * @author Ralf Wondratschek
 */
public class AuthenticateActivity extends AbstractNfcActivity {

    private static final String TC_TOKEN_PARAMETER = "tcTokenURL";
    private static final String IEAC_INFO = "ieac_info";

    private static final String PIN_KEY = "pin";
    private static final String RESULT_CHAT_KEY = "resultChat";

    private IEAC_Info mIeacInfo;

    private byte[] mPin;
    private long mResultChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate); // we need this empty frame, otherwise Crouton may render its croutons wrong

        Uri uri = getIntent().getData();
        final String tcURL = uri != null ? uri.getQueryParameter(TC_TOKEN_PARAMETER) : null;

        if (tcURL == null) {
            // skip whole life cycle
            Toast.makeText(this, R.string.invalid_request, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (savedInstanceState == null) {
            replaceFragment(R.id.frameLayout, new ProgressFragment());
            mMainViewFragment.startAuthentication(tcURL);

        } else {
            mPin = savedInstanceState.getByteArray(PIN_KEY);
            mResultChat = savedInstanceState.getLong(RESULT_CHAT_KEY);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainViewFragment.setMainViewCallback(new MyMainViewCallback());
    }

    @Override
    protected void onStop() {
        mMainViewFragment.setMainViewCallback(null);
        super.onStop();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mNfcTransportProvider.handleIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPin != null) {
            outState.putByteArray(PIN_KEY, mPin);
        }
        outState.putLong(RESULT_CHAT_KEY, mResultChat);
    }

    @Override
    public void onBackPressed() {
        onCancelPressed();
        super.onBackPressed();
    }

    public IEAC_Info getIeacInfo() {
        if (mIeacInfo == null) {
            mIeacInfo = (IEAC_Info) load(IEAC_INFO);
        }
        return mIeacInfo;
    }

    public void onConfirmPressed() {
        mMainViewFragment.setMainDialogResult(new IMainView.MainDialogResult(getResultChat(), getPin(), true));
    }

    public void onCancelPressed() {
        mMainViewFragment.setMainDialogResult(new IMainView.MainDialogResult(-1L, null, false));
    }

    public void setPin(byte[] pin) {
        if (BuildConfig.DEBUG) {
            if (!Arrays.equals(mPin, pin)) {
                Cat.d("Got new PIN");
            }
        }
        mPin = pin;
    }

    public byte[] getPin() {
        return mPin;
    }

    public void setResultChat(long resultChat) {
        if (mResultChat != resultChat) {
            mResultChat = resultChat;
            Cat.d("Got new result CHAT");
        }
    }

    public long getResultChat() {
        return mResultChat;
    }

    private class MyMainViewCallback extends MainViewFragment.MainViewCallback {
        @Override
        public void showMainDialog(final IEAC_Info eacInfo, final int mode) {
            mIeacInfo = eacInfo;
            put(IEAC_INFO, mIeacInfo);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    replaceFragment(R.id.frameLayout, new AuthenticateFragment());
                }
            });
        }

        @Override
        public void showProgress(final String message, int amount, boolean enabled) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
                    if (!(fragment instanceof ProgressFragment)) {
                        fragment = new ProgressFragment();
                        replaceFragment(R.id.frameLayout, fragment);
                    }

                    ((ProgressFragment) fragment).setMessage(message);
                }
            });
        }
    }
}
