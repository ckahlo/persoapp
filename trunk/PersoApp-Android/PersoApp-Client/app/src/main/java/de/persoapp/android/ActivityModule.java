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
package de.persoapp.android;

import android.app.Activity;

import net.vrallev.android.base.BaseActivityModule;
import net.vrallev.android.base.BaseActivitySupport;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.persoapp.android.activity.CommonChangePinActivity;
import de.persoapp.android.activity.AuthenticateActivity;
import de.persoapp.android.activity.MainActivity;
import de.persoapp.android.activity.PinOptionsActivity;
import de.persoapp.android.activity.dialog.CanDialog;
import de.persoapp.android.activity.dialog.NfcDeactivatedDialog;
import de.persoapp.android.activity.dialog.NoInternetConnectionDialog;
import de.persoapp.android.activity.dialog.QuestionDialog;
import de.persoapp.android.activity.fragment.CommonChangePinFragment;
import de.persoapp.android.activity.fragment.AuthenticateFragment;
import de.persoapp.android.activity.fragment.ConfirmPinFragment;
import de.persoapp.android.activity.fragment.DeviceNotNpaCapableFragment;
import de.persoapp.android.activity.fragment.InitializeAppFragment;
import de.persoapp.android.activity.fragment.NewPinFragment;
import de.persoapp.android.nfc.DeviceStateTester;
import de.persoapp.android.view.MenuHelper;
import de.persoapp.android.view.PinRow;

/**
 * Provides dependencies with {@link android.app.Activity} scope.
 *
 * @author Ralf Wondratschek
 */
@SuppressWarnings("UnusedDeclaration")
@Module(
        addsTo = AppModule.class,
        includes = {
                BaseActivityModule.class
        },
        injects = {
                MainActivity.class,
                AuthenticateActivity.class,
                PinOptionsActivity.class,
                CommonChangePinActivity.class,

                NewPinFragment.class,
                ConfirmPinFragment.class,
                QuestionDialog.class,
                CanDialog.class,
                AuthenticateFragment.class,
                InitializeAppFragment.class,
                CommonChangePinFragment.class,
                DeviceNotNpaCapableFragment.class,
                NfcDeactivatedDialog.class,
                NoInternetConnectionDialog.class,

                PinRow.class,
                DeviceStateTester.class
        }
)
public class ActivityModule {

    @Provides
    @Singleton
    MenuHelper provideMenuHelper(Activity activity) {
        return new MenuHelper((BaseActivitySupport) activity);
    }

    @Provides
    @Singleton
    DeviceStateTester provideDeviceStateTester(Activity activity) {
        return new DeviceStateTester((BaseActivitySupport) activity);
    }
}
