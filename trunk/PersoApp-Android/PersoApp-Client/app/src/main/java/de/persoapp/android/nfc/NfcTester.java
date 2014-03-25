package de.persoapp.android.nfc;

import android.nfc.NfcManager;
import android.nfc.tech.IsoDep;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.settings.SettingsMgr;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.activity.dialog.NfcDeactivatedDialog;
import de.persoapp.android.activity.dialog.NfcNotSupportedDialog;
import de.persoapp.android.activity.fragment.DeviceNotNpaCapableFragment;
import de.persoapp.android.activity.fragment.InitializeAppFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import hugo.weaving.DebugLog;

/**
 * @author Ralf Wondratschek
 */
public class NfcTester {

    private static final String APP_NPA_CAPABLE = "npaCapable";

    private static final int NOT_TESTED = 0;
    private static final int CAPABLE = 1;
    private static final int NOT_CAPABLE = -1;

    private final BaseActivitySupport mActivity;

    @Inject
    SettingsMgr mSettingsMgr;

    @Inject
    NfcTransportProvider mNfcTransportProvider;

    @Inject
    EventBus mEventBus;

    @Inject
    NfcManager mNfcManager;

    private int mLastContentId;

    private Thread mNpaTestThread;

    public NfcTester(BaseActivitySupport activity) {
        mActivity = activity;
        mActivity.inject(this);

        mLastContentId = -1;
    }

    @DebugLog
    public synchronized void testIsoDep(IsoDep isoDep) {
        if (mNpaTestThread == null && isoDep != null && !isNpaCapable()) {
            mNpaTestThread = new Thread() {
                @Override
                public void run() {
                    boolean capable = mNfcTransportProvider.testExtendedLength();
                    mSettingsMgr.putInt(APP_NPA_CAPABLE, capable ? CAPABLE : NOT_CAPABLE);

                    mEventBus.post(new NpaCapableEvent(capable));

                    synchronized (NfcTester.this) {
                        mNpaTestThread = null;
                    }
                }
            };
            mNpaTestThread.start();
        }
    }

    public boolean isNpaCapable() {
        return mSettingsMgr.getInt(APP_NPA_CAPABLE, NOT_CAPABLE) == CAPABLE;
    }

    public boolean hasTested() {
        return mSettingsMgr.getInt(APP_NPA_CAPABLE, NOT_TESTED) != NOT_TESTED;
    }

    public boolean needsToShowOtherContent() {
        return needsToShowOtherContent(mLastContentId > 0 ? mLastContentId : android.R.id.content);
    }

    public boolean needsToShowOtherContent(int contentId) {
        mLastContentId = contentId;

        Fragment fragment = mActivity.getSupportFragmentManager().findFragmentById(contentId);

        if (mNfcManager.getDefaultAdapter() == null) {
            mActivity.showDialog(new NfcNotSupportedDialog(), "dialog");
            return true;

        } else if (!mNfcManager.getDefaultAdapter().isEnabled()) {
            mActivity.showDialog(new NfcDeactivatedDialog(), "dialog");
            return true;

        } else if (!hasTested()) {
            if (!(fragment instanceof InitializeAppFragment)) {
                mActivity.replaceFragment(contentId, new InitializeAppFragment(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            }
            return true;

        } else if (!isNpaCapable()) {
            if (!(fragment instanceof DeviceNotNpaCapableFragment)) {
                mActivity.replaceFragment(contentId, new DeviceNotNpaCapableFragment(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            }
            return true;
        }

        return false;
    }

    public static class NpaCapableEvent {
        private final boolean mNpaSupported;

        public NpaCapableEvent(boolean npaSupported) {
            mNpaSupported = npaSupported;
        }

        public boolean isNpaSupported() {
            return mNpaSupported;
        }
    }
}
