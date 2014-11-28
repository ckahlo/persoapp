/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
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
package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.lib.crouton.extension.CroutonBuilder;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.nfc.DeviceStateTester;

/**
 * This class provides the logic to inform the user, that the used
 * device is not capable to communicate with an nPA. This is the
 * case, if the extended length of the <em>NFC.apk</em> is not 
 * supported.
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class DeviceNotNpaCapableFragment extends Fragment {

    @Inject
    EventBus mEventBus;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivitySupport) activity).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_not_npa_capable, container, false);
        view.setFilterTouchesWhenObscured(true);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        mEventBus.unregister(this);
        super.onStop();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(DeviceStateTester.NpaCapableEvent event) {
        if (event.isNpaSupported()) {
            mEventBus.post(new InitializeAppFragment.OnAppInitialized(true));
        } else {
            CroutonBuilder.showError(getActivity(), getString(R.string.test_failed_again));
        }
    }
}
