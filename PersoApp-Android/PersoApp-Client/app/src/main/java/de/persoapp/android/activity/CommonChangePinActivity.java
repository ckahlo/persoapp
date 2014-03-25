package de.persoapp.android.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import net.vrallev.android.base.util.Cat;

import de.persoapp.android.R;
import de.persoapp.android.activity.fragment.CommonChangePinFragment;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.core.client.IMainView;
import hugo.weaving.DebugLog;

/**
 * @author Ralf Wondratschek
 * TODO: move buttons to the top
 */
public class CommonChangePinActivity extends AbstractNfcActivity {

    public static final String MODE_KEY = "mode";

    public static Intent createIntent(Context context, Mode mode) {
        if (context == null || mode == null) {
            throw new IllegalArgumentException();
        }

        Intent intent = new Intent(context, CommonChangePinActivity.class);
        intent.putExtra(MODE_KEY, mode.ordinal());
        return intent;
    }

    private Mode mMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        if (savedInstanceState == null) {
            replaceFragment(R.id.frameLayout, new CommonChangePinFragment(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
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

    public void onConfirmPressed() {
        // TODO
        new Thread() {
            @Override
            public void run() {

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

                Object result = mMainViewFacade.triggerEvent(getMode().getCoreEventId(), pinDialogResult);

                Cat.d("");
            }
        }.start();
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

    private class MyMainViewCallback extends MainViewFragment.MainViewCallback {
        @Override
        @DebugLog
        public void showProgress(String message, int amount, boolean enabled) {
        }
    }
}
