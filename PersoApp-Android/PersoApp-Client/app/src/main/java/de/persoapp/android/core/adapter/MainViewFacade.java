package de.persoapp.android.core.adapter;

import net.vrallev.android.base.util.Cat;

import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.SecureHolder;
import hugo.weaving.DebugLog;

/**
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
