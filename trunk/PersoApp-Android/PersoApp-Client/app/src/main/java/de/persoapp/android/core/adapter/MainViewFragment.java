package de.persoapp.android.core.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Browser;

import net.vrallev.android.base.BaseActivity;
import net.vrallev.android.base.LooperMain;
import net.vrallev.android.base.util.Cat;
import net.vrallev.android.lib.crouton.extension.CroutonBuilder;

import java.net.URL;

import javax.inject.Inject;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Style;
import de.persoapp.android.activity.dialog.QuestionDialog;
import de.persoapp.core.ECardWorker;
import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.SecureHolder;
import hugo.weaving.DebugLog;

/**
 * @author Ralf Wondratschek
 *
 * TODO: add support for intent library
 *
 */
@SuppressWarnings("ConstantConditions")
public class MainViewFragment extends Fragment implements IMainView {

    public static final String TAG = "MainViewFragmentTag";

    public static MainViewFragment findOrCreateFragment(BaseActivity activity) {
        Fragment fragment = activity.getFragmentManager().findFragmentByTag(TAG);

        if (!(fragment instanceof MainViewFragment)) {
            MainViewFragment mainViewFragment = new MainViewFragment();
            activity.inject(mainViewFragment);
            activity.getFragmentManager().beginTransaction().add(mainViewFragment, TAG).commit();

            return mainViewFragment;
        }

        return (MainViewFragment) fragment;
    }

    @Inject
    NfcTransportProvider mNfcTransportProvider;

    @Inject
    @LooperMain
    Looper mMainLooper;

    private Handler mMainHandler;

    private String mTcTokenUrl;
    private String mRefreshAddress;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setRetainInstance(true);

        mMainHandler = new MyHandler(mMainLooper);
    }

    public void setTcTokenUrl(String tcTokenUrl) {
        mTcTokenUrl = tcTokenUrl;

        // TODO: cache thread
        new Thread() {
            @Override
            public void run() {
                try {
                    mRefreshAddress = ECardWorker.start(new URL(mTcTokenUrl));
//                    mRefreshAddress = new AndroidECApiHttpHandler().startAuthentication(new URL(mTcTokenUrl));
                } catch (Exception e) {
                    Cat.e(e);
                }
            }
        }.start();
    }

    @Override
    @DebugLog
    public void setEventLister(EventListener listener) {

    }

    @Override
    @DebugLog
    public Object triggerEvent(int event, Object... eventData) {
        return null;
    }

    @Override
    @DebugLog
    public void showMainDialog(IEAC_Info eacInfo, int MODE) {

    }

    @Override
    @DebugLog
    public MainDialogResult getMainDialogResult() {
        char[] pin = new char[]{'1', '2', '3', '4', '5', '6'};
        byte[] pinByte = new String(pin).getBytes();

        return new MainDialogResult(16816132, pinByte, true);
    }

    @Override
    @DebugLog
    public SecureHolder showCANDialog(String msg) {
        return null;
    }

    @Override
    @DebugLog
    public void showChangePinDialog() {

    }

    @Override
    @DebugLog
    public void showProgress(String message, int amount, boolean enabled) {

    }

    @Override
    @DebugLog
    public boolean showQuestion(String title, String message) {
        return new QuestionDialog().askForResult((BaseActivity) getActivity(), title, message);
    }

    @Override
    @DebugLog
    public void showError(final String title, final String message) {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                CroutonBuilder.showError(getActivity(), title, message);
            }
        });
    }

    @Override
    @DebugLog
    public void showMessage(final String msg, int type) {
        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                new CroutonBuilder(getActivity())
                        .setColor(Style.holoBlueLight)
                        .setDuration(Configuration.DURATION_LONG)
                        .setHideOnClick(true)
                        .setMessage(msg)
                        .show();
            }
        });
    }

    @Override
    public void closeDialogs() {
        // TODO: handle activity null
//        Activity activity = getActivity();
//
//        activity.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                    Log.d(PreferencesMgr.LOG_TAG, "Closing dialogs, refreshBrowser == " + refreshBrowser
//                            + ", refreshURL == " + mRefreshURL);

//                    if (mMainDialogResult == null || mMainDialogResult.getCHAT() == -1L) {
//                        showMessage("Authentisierung nicht erfolgreich.", IMainView.ERROR);
//                        if (!refreshBrowser) {
//                            final Intent i = new Intent().putExtra(EXTRA_REFRESH_ADDRESS, mRefreshURL);
//                            getActivity().setResult(Activity.RESULT_CANCELED, i);
//                        }
//                    } else {
//                        if (refreshBrowser) {
        if (mRefreshAddress != null) {
            final Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(mRefreshAddress));

            // use this to reuse the last tab in the browser
            i.putExtra(Browser.EXTRA_APPLICATION_ID, "com.android.browser");
            try {
                Cat.d("RefreshAddress == %b, %s", mRefreshAddress.endsWith("Major=ok"), mRefreshAddress);

                startActivity(i);
                if (getActivity() != null) {
                    getActivity().finish();
                }
//                        getActivity().finish();
            } catch (final Exception e) {
                // XXX
            }
//                    } else {
            // if refreshURL could not be validated display error
        }
//            }

//                else
//
//                {
//                    final Intent data = new Intent();
//                    data.putExtra(EXTRA_REFRESH_ADDRESS, mRefreshURL);
//                    if (getActivity() == null) {
//                        Log.d(PreferencesMgr.LOG_TAG, "XXXXXXXX");
//                    } else {
//                        getActivity().setResult(Activity.RESULT_OK, data);
//                    }
//        });
    }

    @Override
    @DebugLog
    public void shutdown() {

    }

    private class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
