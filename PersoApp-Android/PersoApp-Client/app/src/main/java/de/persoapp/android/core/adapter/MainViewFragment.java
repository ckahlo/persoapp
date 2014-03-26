package de.persoapp.android.core.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Browser;
import android.support.v4.app.Fragment;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.LooperMain;
import net.vrallev.android.base.util.Cat;
import net.vrallev.android.lib.crouton.extension.CroutonBuilder;

import java.net.URL;
import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Style;
import de.persoapp.android.R;
import de.persoapp.android.activity.dialog.CanDialog;
import de.persoapp.android.activity.dialog.QuestionDialog;
import de.persoapp.core.ECardWorker;
import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.SecureHolder;
import hugo.weaving.DebugLog;

/**
 * @author Ralf Wondratschek
 *         TODO: update Crouton library
 */
@SuppressWarnings("ConstantConditions")
public class MainViewFragment extends Fragment implements IMainView {

    public static final String TAG = "MainViewFragmentTag";

    private static final String EXTRA_DO_REDIRECT = "net.ageto.eid.intent.extra.do_redirect";
    private static final String EXTRA_REFRESH_ADDRESS = "net.ageto.eid.intent.extra.refresh_address";

    private static final int MSG_FINISH = 1;

    public static MainViewFragment findOrCreateFragment(BaseActivitySupport activity) {
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(TAG);

        if (!(fragment instanceof MainViewFragment)) {
            return forceCreateFragment(activity);
        }

        return (MainViewFragment) fragment;
    }

    public static MainViewFragment forceCreateFragment(BaseActivitySupport activity) {
        Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(TAG);
        if (fragment != null) {
            activity.getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }

        fragment = new MainViewFragment();
        activity.inject(fragment);
        activity.getSupportFragmentManager().beginTransaction().add(fragment, TAG).commit();

        return (MainViewFragment) fragment;
    }

    @Inject
    @LooperMain
    Looper mMainLooper;

    private Handler mMainHandler;

    private String mTcTokenUrl;
    private String mRefreshAddress;

    private boolean mRefreshBrowser;

    private EventListener mEventListener;
    private final Object mEventMonitor = new Object();

    private MainViewCallback mMainViewCallback;

    private MainDialogResult mMainDialogResult;
    private CountDownLatch mCountDownLatchResult;

    private long mCroutonDismissedTime;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setRetainInstance(true);

        mMainHandler = new MyHandler(mMainLooper);
        mCountDownLatchResult = new CountDownLatch(1);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void startAuthentication(String tcTokenUrl) {
        startAuthentication(tcTokenUrl, null);
    }

    public void startAuthentication(String tcTokenUrl, Intent extra) {
        if (mTcTokenUrl != null) {
            throw new IllegalStateException("You can only start one authentication per session.");
        }

        mTcTokenUrl = tcTokenUrl;
        mRefreshBrowser = extra == null || extra.getBooleanExtra(EXTRA_DO_REDIRECT, true);

        new Thread() {
            @Override
            public void run() {
                try {
                    mRefreshAddress = ECardWorker.start(new URL(mTcTokenUrl));

                    if (getActivity() != null) {
                        getActivity().setResult(Activity.RESULT_CANCELED, new Intent().putExtra(EXTRA_REFRESH_ADDRESS, mRefreshAddress));
                    }

                } catch (Exception e) {
                    Cat.w(e, "Bad request");
                    closeDialogs();
                }
            }
        }.start();
    }

    public void setMainViewCallback(MainViewCallback mainViewCallback) {
        mMainViewCallback = mainViewCallback;
    }

    public void setMainDialogResult(MainDialogResult mainDialogResult) {
        mMainDialogResult = mainDialogResult;
        mCountDownLatchResult.countDown();
    }

    @Override
    public void setEventLister(EventListener listener) {
        synchronized (mEventMonitor) {
            mEventListener = listener;
        }
    }

    @Override
    public Object triggerEvent(int event, Object... eventData) {
        if (mEventListener != null) {
            synchronized (mEventMonitor) {
                return mEventListener.handleEvent(event, eventData);
            }
        } else {
            Cat.w("EventListener is null.");
            return null;
        }
    }

    @Override
    public void showMainDialog(IEAC_Info eacInfo, int mode) {
        if (mMainViewCallback != null) {
            mMainViewCallback.showMainDialog(eacInfo, mode);
        }
    }

    @Override
    public MainDialogResult getMainDialogResult() {
        try {
            mCountDownLatchResult.await();
        } catch (InterruptedException e) {
            Cat.e(e);
        }

        return mMainDialogResult;
    }

    @Override
    public SecureHolder showCANDialog(String msg) {
        if (getActivity() != null) {
            return new CanDialog().askForResult((BaseActivitySupport) getActivity(), getString(R.string.can_dialog_title), msg);
        } else {
            return null;
        }
    }

    @Override
    @DebugLog
    public void showChangePinDialog() {

    }

    @Override
    @DebugLog
    public void showProgress(String message, int amount, boolean enabled) {
        if (mMainViewCallback != null) {
            mMainViewCallback.showProgress(message, amount, enabled);
        }
    }

    @Override
    public boolean showQuestion(String title, String message) {
        return getActivity() != null && new QuestionDialog().askForResult((BaseActivitySupport) getActivity(), title, message);
    }

    @Override
    public void showError(final String title, final String message) {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    CroutonBuilder.showError(getActivity(), title, message);
                }
            }
        });
    }

    @Override
    public void showMessage(final String msg, final int type) {
        long time = System.currentTimeMillis();
        if (mCroutonDismissedTime < time) {
            mCroutonDismissedTime = time;
        }

        final int duration = Configuration.DURATION_SHORT;

        mCroutonDismissedTime += duration;

        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (getActivity() == null) {
                    return;
                }

                CroutonBuilder builder = new CroutonBuilder(getActivity())
                        .setDuration(duration)
                        .setHideOnClick(true)
                        .setMessage(msg);

                switch (type) {
                    case IMainView.ERROR:
                        builder.setColor(Style.holoRedLight);
                        break;
                    case IMainView.INFO:
                    case IMainView.RELOAD:
                    case IMainView.QUESTION:
                        builder.setColor(Style.holoBlueLight);
                        break;
                    case IMainView.SUCCESS:
                        builder.setColor(Style.holoGreenLight);
                        break;
                    case IMainView.WARNING:
                        builder.setColor(Color.parseColor("#ffffbb33"));
                        break;
                }

                builder.show();
            }
        });
    }

    @Override
    @DebugLog
    public void closeDialogs() {
        boolean success = isAuthenticationSuccess();
        if (!success && getActivity() != null) {
            showMessage(getActivity().getString(R.string.authentication_failed), IMainView.ERROR);
        }

        long time = System.currentTimeMillis();
        if (mCroutonDismissedTime <= time) {
            mMainHandler.sendEmptyMessage(MSG_FINISH);
        } else {
            mMainHandler.sendEmptyMessageDelayed(MSG_FINISH, Math.min(mCroutonDismissedTime - time, 2000L));
        }

        if (mMainViewCallback != null) {
            mMainViewCallback.closeDialogs(success);
        }
    }

    @Override
    @DebugLog
    public void shutdown() {
        closeDialogs();
    }

    private class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FINISH:
                    finish();
                    break;
            }
        }
    }

    protected void finish() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }

        if (isAuthenticationSuccess()) {
            if (mRefreshBrowser) {
                sendIntentToBrowser();
            } else {
                activity.setResult(Activity.RESULT_OK, new Intent().putExtra(EXTRA_REFRESH_ADDRESS, mRefreshAddress));
            }
        }

        activity.finish();
    }

    protected boolean isAuthenticationSuccess() {
        return mMainDialogResult != null && mMainDialogResult.getCHAT() != -1;
    }

    protected void sendIntentToBrowser() {
        if (mRefreshAddress != null) {
            final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(mRefreshAddress));

            // use this to reuse the last tab in the browser
            intent.putExtra(Browser.EXTRA_APPLICATION_ID, "com.android.browser");
            try {
                Cat.d("RefreshAddress == %b, %s", mRefreshAddress.endsWith("Major=ok"), mRefreshAddress);

                startActivity(intent);
            } catch (final Exception e) {
                Cat.w(e, "Unexpected exception");
            }
        }
    }


    public static abstract class MainViewCallback {

        public void showMainDialog(IEAC_Info eacInfo, int mode) {
            // override me
        }

        public void showProgress(String message, int amount, boolean enabled) {
            // override me
        }

        public void closeDialogs(boolean success) {
            // override me
        }
    }
}
