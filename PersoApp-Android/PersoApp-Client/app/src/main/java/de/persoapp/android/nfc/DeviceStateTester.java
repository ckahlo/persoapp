/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013 AGETO Innovation GmbH
 *
 * Authors Christian Kahlo, Ralf Wondratschek
 *
 * All Rights Reserved.
 *
 * Contact: PersoApp, http://www.persoapp.de
 *
 * @version 1.0, 30.07.2013 13:50:47
 *
 *          This file is part of PersoApp.
 *
 *          PersoApp is free software: you can redistribute it and/or modify it
 *          under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation, either version 3 of the
 *          License, or (at your option) any later version.
 *
 *          PersoApp is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details.
 *
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with PersoApp. If not, see
 *          <http://www.gnu.org/licenses/>.
 *
 *          Diese Datei ist Teil von PersoApp.
 *
 *          PersoApp ist Freie Software: Sie können es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          späteren veröffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 *
 *          PersoApp wird in der Hoffnung, dass es nützlich sein wird, aber OHNE
 *          JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License für weitere
 *          Details.
 *
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 *
 */
package de.persoapp.android.nfc;

import android.nfc.NfcManager;
import android.nfc.tech.IsoDep;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.settings.SettingsMgr;
import net.vrallev.android.base.util.NetworkHelper;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.activity.dialog.NfcDeactivatedDialog;
import de.persoapp.android.activity.dialog.NfcNotSupportedDialog;
import de.persoapp.android.activity.dialog.NoInternetConnectionDialog;
import de.persoapp.android.activity.fragment.DeviceNotNpaCapableFragment;
import de.persoapp.android.activity.fragment.InitializeAppFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import hugo.weaving.DebugLog;

/**
 * @author Ralf Wondratschek
 */
public class DeviceStateTester {

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

    @Inject
    NetworkHelper mNetworkHelper;

    private int mLastContentId;

    private Thread mNpaTestThread;

    public DeviceStateTester(BaseActivitySupport activity) {
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

                    synchronized (DeviceStateTester.this) {
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

        } else if (!mNetworkHelper.isNetworkAvailable()) {
            mActivity.showDialog(new NoInternetConnectionDialog(), "dialog");
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
