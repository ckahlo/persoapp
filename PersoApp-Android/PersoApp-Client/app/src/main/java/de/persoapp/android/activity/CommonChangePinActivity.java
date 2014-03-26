package de.persoapp.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import net.vrallev.android.base.LooperBackground;
import net.vrallev.android.base.util.Cat;

import javax.inject.Inject;

import de.persoapp.android.R;
import de.persoapp.android.activity.fragment.CommonChangePinFragment;
import de.persoapp.android.activity.fragment.ProgressFragment;
import de.persoapp.core.client.IMainView;

/**
 * @author Ralf Wondratschek
 */
public class CommonChangePinActivity extends AbstractNfcActivity {

    public static final int REQUEST_CODE = 100;

    public static final String MODE_KEY = "mode";

    private static final int MSG_CHANGE_PIN = 1;

    public static Intent createIntent(Context context, Mode mode) {
        if (context == null || mode == null) {
            throw new IllegalArgumentException();
        }

        Intent intent = new Intent(context, CommonChangePinActivity.class);
        intent.putExtra(MODE_KEY, mode.ordinal());
        return intent;
    }

    private Mode mMode;

    @Inject
    @LooperBackground
    Looper mLooper;

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        mHandler = new MyHandler(mLooper);

        if (savedInstanceState == null) {
            setResult(RESULT_CANCELED);

            if (!mDeviceStateTester.needsToShowOtherContent()) {
                updateContentFragment();
            }
        }
    }

    @Override
    public void onDeviceReady() {
        updateContentFragment();
    }

    protected void updateContentFragment() {
        replaceFragment(R.id.frameLayout, new CommonChangePinFragment(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
    }

    public void onConfirmPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if (!(fragment instanceof CommonChangePinFragment)) {
            Cat.w("Wrong state");
            return;
        }

        byte[] pinOld = ((CommonChangePinFragment) fragment).getPinOld();
        byte[] pinNew = ((CommonChangePinFragment) fragment).getPinNew();

        IMainView.ChangePINDialogResult pinDialogResult;

        switch (getMode()) {
            case ACTIVATE:
            case CHANGE:
                pinDialogResult = new IMainView.ChangePINDialogResult(pinOld, pinNew, true);
                break;

            case UNLOCK:
                // pinOld is the PUK
                pinDialogResult = new IMainView.ChangePINDialogResult(pinOld, null, true);
                break;

            default:
                throw new IllegalStateException();
        }

        replaceFragment(R.id.frameLayout, new ProgressFragment(), "Tag", FragmentTransaction.TRANSIT_FRAGMENT_OPEN, true);

        mHandler.sendMessage(mHandler.obtainMessage(MSG_CHANGE_PIN, pinDialogResult));
    }

    public Mode getMode() {
        if (mMode == null) {
            mMode = Mode.values()[getIntent().getIntExtra(MODE_KEY, -1)];
            if (mMode == null) {
                throw new IllegalStateException(new NullPointerException("You must pass a Mode value."));
            }
        }
        return mMode;
    }

    public static enum Mode {
        ACTIVATE(IMainView.EventListener.EVENT_ACTIVATE_PIN_EID),
        CHANGE(IMainView.EventListener.EVENT_CHANGE_PIN_EID),
        UNLOCK(IMainView.EventListener.EVENT_UNLOCK_PIN_EID);

        private final int mCoreEventId;

        Mode(int coreEventId) {
            mCoreEventId = coreEventId;
        }

        public int getCoreEventId() {
            return mCoreEventId;
        }
    }

    private class MyHandler extends Handler {

        private MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MSG_CHANGE_PIN) {
                return;
            }

            Object result = mMainViewFacade.triggerEvent(getMode().getCoreEventId(), msg.obj);

            if (result instanceof Boolean && (Boolean)result) {
                setResult(RESULT_OK);
                mMainViewFragment.showMessage(getString(R.string.action_success), IMainView.SUCCESS);
                mMainViewFragment.closeDialogs();

            } else {
                mMainViewFragment.showMessage(getString(R.string.action_failure), IMainView.ERROR);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        removeFragment(getSupportFragmentManager().findFragmentById(R.id.frameLayout), FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                        getSupportFragmentManager().popBackStack();
                    }
                });
            }
        }
    }
}
