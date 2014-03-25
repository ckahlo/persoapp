package de.persoapp.android.nfc;

import android.nfc.tech.IsoDep;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.settings.SettingsMgr;

import javax.inject.Inject;

import de.persoapp.android.activity.fragment.DeviceNotNpaCapableFragment;
import de.persoapp.android.activity.fragment.InitializeAppFragment;

/**
 * @author Ralf Wondratschek
 */
public class NpaTester {

    private static final String APP_NPA_CAPABLE = "npaCapable";

    private static final int NOT_TESTED = 0;
    private static final int CAPABLE = 1;
    private static final int NOT_CAPABLE = -1;

    private final BaseActivitySupport mActivity;

    @Inject
    SettingsMgr mSettingsMgr;

    private int mLastContentId;

    public NpaTester(BaseActivitySupport activity) {
        mActivity = activity;
        mActivity.inject(this);

        mLastContentId = -1;
    }

    public boolean testIsoDep(IsoDep isoDep) {
        boolean result = isoDep != null && isoDep.getMaxTransceiveLength() >= 65546;

        mSettingsMgr.putInt(APP_NPA_CAPABLE, result ? CAPABLE : NOT_CAPABLE);

        return result;
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

        if (!hasTested()) {
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
}
