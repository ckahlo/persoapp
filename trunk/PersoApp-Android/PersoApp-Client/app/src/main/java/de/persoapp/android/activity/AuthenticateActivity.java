package de.persoapp.android.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import net.vrallev.android.base.util.Cat;

import java.util.Arrays;

import de.persoapp.android.BuildConfig;
import de.persoapp.android.R;
import de.persoapp.android.activity.dialog.QuestionDialog;
import de.persoapp.android.activity.fragment.AuthenticateFragment;
import de.persoapp.android.activity.fragment.ProgressFragment;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;
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

    private String mTcUrl;

    private MyMainViewCallback mMainViewCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout); // we need this empty frame, otherwise Crouton may render its croutons wrong

        Uri uri = getIntent().getData();
        mTcUrl = uri != null ? uri.getQueryParameter(TC_TOKEN_PARAMETER) : null;

        if (mTcUrl == null) {
            // skip whole life cycle
            Toast.makeText(this, R.string.invalid_request, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (savedInstanceState == null) {

            if (!mDeviceStateTester.needsToShowOtherContent(R.id.frameLayout)) {
                startAuthentication();
            }

        } else {
            mPin = savedInstanceState.getByteArray(PIN_KEY);
            mResultChat = savedInstanceState.getLong(RESULT_CHAT_KEY);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainViewCallback = new MyMainViewCallback();
        mMainViewFragment.setMainViewCallback(mMainViewCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNfcTransportProvider.setTranceiveErrorCallback(mMainViewCallback);
    }

    @Override
    protected void onPause() {
        mNfcTransportProvider.setTranceiveErrorCallback(null);
        super.onPause();
    }

    @Override
    protected void onStop() {
        mMainViewFragment.setMainViewCallback(null);
        mMainViewCallback = null;
        super.onStop();
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

    @Override
    public void onDeviceReady() {
        startAuthentication();
    }

    private void startAuthentication() {
        ProgressFragment progressFragment = new ProgressFragment();
        progressFragment.setMessage(getString(R.string.please_wait));
        replaceFragment(R.id.frameLayout, progressFragment);

        mMainViewFragment.startAuthentication(mTcUrl);
        mTcUrl = null;
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

    private class MyMainViewCallback extends MainViewFragment.MainViewCallback implements NfcTransportProvider.TranceiveErrorCallback {

        private static final int PIN_SUCCESSFULLY_CHECKED = 25;

        private int mProgress;

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
            mProgress = amount;

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

        @Override
        public boolean shouldRepeatTranceive(byte[] apdu, Exception e) {
            return mProgress < PIN_SUCCESSFULLY_CHECKED && new QuestionDialog().askForResult(AuthenticateActivity.this, R.string.tranceive_failed_title, R.string.tranceive_failed_message, true);
        }

        @Override
        public void closeDialogs() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
                    if (fragment != null) {
                        removeFragment(fragment, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                    }
                }
            });
        }
    }
}
