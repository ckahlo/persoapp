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
package de.persoapp.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.vrallev.android.base.BaseActivitySupport;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.ActivityModule;
import de.persoapp.android.activity.fragment.InitializeAppFragment;
import de.persoapp.android.core.adapter.MainViewFacade;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import de.persoapp.android.nfc.DeviceStateTester;
import de.persoapp.android.view.MenuHelper;

/**
 * All activities with NFC capabilietes should extend this class. It setups the foreground dispatch and
 * has callbacks for the {@link de.persoapp.android.nfc.DeviceStateTester}.
 *
 * <br>
 * <br>
 *
 * It's important to note, that you should <b>NOT</b> display your activity's content in the {@link android.app.Activity#onCreate(android.os.Bundle)} method
 * right away. Instead you should call {@link de.persoapp.android.nfc.DeviceStateTester#needsToShowOtherContent()} first. If the {@link de.persoapp.android.nfc.DeviceStateTester}
 * doesn't need to display other content, then you can display your views. Otherwise you receive a callback in {@link AbstractNfcActivity#onDeviceReady()}
 * when the device is ready to display your content.
 *
 * @author Ralf Wondratschek
 */
public abstract class AbstractNfcActivity extends BaseActivitySupport {

    @Inject
    protected NfcTransportProvider mNfcTransportProvider;

    @Inject
    protected MainViewFacade mMainViewFacade;

    @Inject
    protected MenuHelper mMenuHelper;

    @Inject
    protected EventBus mEventBus;

    @Inject
    protected DeviceStateTester mDeviceStateTester;

    protected MainViewFragment mMainViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMainViewFragment = MainViewFragment.findOrCreateFragment(this);
        mMainViewFacade.setMainView(mMainViewFragment);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEventBus.register(this, InitializeAppFragment.OnAppInitialized.class, NfcTransportProvider.NfcConnectedEvent.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNfcTransportProvider.enableForegroundDispatch(this);
    }

    @Override
    protected void onPause() {
        mNfcTransportProvider.disableForegroundDispatch(this);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mNfcTransportProvider.close();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        mEventBus.unregister(this, InitializeAppFragment.OnAppInitialized.class, NfcTransportProvider.NfcConnectedEvent.class);
        super.onStop();
    }

    @Override
    protected void addModules(List<Object> modules) {
        super.addModules(modules);
        modules.add(new ActivityModule());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mMenuHelper.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mMenuHelper.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mNfcTransportProvider.handleIntent(intent);
    }

    @SuppressWarnings("UnusedDeclaration")
    public final void onEvent(InitializeAppFragment.OnAppInitialized event) {
        if (event.isSuccess()) {
            onDeviceReady();
        } else {
            // content will change to DeviceNotCapableFragment
            mDeviceStateTester.needsToShowOtherContent();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public final void onEvent(NfcTransportProvider.NfcConnectedEvent event) {
        mDeviceStateTester.testIsoDep(event.getIsoDep());
    }

    public void onDeviceReady() {
        // no op
    }
}
