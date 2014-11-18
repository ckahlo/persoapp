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
package de.persoapp.android.core.adapter;

import net.vrallev.android.base.util.Cat;

import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.SecureHolder;
import hugo.weaving.DebugLog;

/**
 * This class is the adapter between core library and client. One instance of this class is created and registered in the {@link de.persoapp.android.AppExtension}
 * class. Instead of registering other {@link de.persoapp.core.client.IMainView} instances in the core library,
 * you should set other {@link de.persoapp.core.client.IMainView} instances by calling {@link de.persoapp.android.core.adapter.MainViewFacade#setMainView(de.persoapp.core.client.IMainView)}
 * in order to receive callbacks, e.g. the {@link de.persoapp.android.core.adapter.MainViewFragment} class does this and eases the process even more.
 *
 * @see de.persoapp.android.core.adapter.MainViewFragment
 *
 * @author Ralf Wondratschek
 */
public class MainViewFacade implements IMainView {

    private EventListener mEventListener;
    private IMainView mMainView;

    public void setMainView(IMainView mainView) {
        mMainView = mainView;
    }

    @Override
    @DebugLog
    public void setEventLister(EventListener listener) {
        mEventListener = listener;
    }

    @Override
    @DebugLog
    public Object triggerEvent(int event, Object... eventData) {
        return mEventListener.handleEvent(event, eventData);
    }

    @Override
    @DebugLog
    public void showMainDialog(IEAC_Info eacInfo, int MODE) {
        if (mMainView != null) {
            mMainView.showMainDialog(eacInfo, MODE);
        } else {
            Cat.w("MainView must not be null!");
        }
    }

    @Override
    @DebugLog
    public MainDialogResult getMainDialogResult() {
        if (mMainView != null) {
            return mMainView.getMainDialogResult();
        } else {
            Cat.w("MainView must not be null!");
            return null;
        }
    }

    @Override
    @DebugLog
    public SecureHolder showCANDialog(String msg) {
        if (mMainView != null) {
            return mMainView.showCANDialog(msg);
        } else {
            Cat.w("MainView must not be null!");
            return null;
        }
    }

    @Override
    @DebugLog
    public void showChangePinDialog() {
        if (mMainView != null) {
            mMainView.showChangePinDialog();
        } else {
            Cat.w("MainView must not be null!");
        }
    }

    @Override
    @DebugLog
    public void showProgress(String message, int amount, boolean enabled) {
        if (mMainView != null) {
            mMainView.showProgress(message, amount, enabled);
        } else {
            Cat.w("MainView must not be null!");
        }
    }

    @Override
    @DebugLog
    public boolean showQuestion(String title, String message) {
        if (mMainView != null) {
            return mMainView.showQuestion(title, message);
        } else {
            Cat.w("MainView must not be null!");
            return false;
        }
    }

    @Override
    @DebugLog
    public void showError(String title, String message) {
        if (mMainView != null) {
            mMainView.showError(title, message);
        } else {
            Cat.w("MainView must not be null!");
        }
    }

    @Override
    @DebugLog
    public void showMessage(String msg, int type) {
        if (mMainView != null) {
            mMainView.showMessage(msg, type);
        } else {
            Cat.w("MainView must not be null!");
        }
    }

    @Override
    @DebugLog
    public void closeDialogs() {
        if (mMainView != null) {
            mMainView.closeDialogs();
        } else {
            Cat.w("MainView must not be null!");
        }
    }

    @Override
    @DebugLog
    public void shutdown() {
        if (mMainView != null) {
            mMainView.shutdown();
        } else {
            Cat.w("MainView must not be null!");
        }
    }
}
