package de.persoapp.android.core.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.provider.Browser;

import net.vrallev.android.base.BaseActivity;
import net.vrallev.android.base.util.Cat;

import java.net.URL;

import javax.inject.Inject;

import de.persoapp.core.ECardWorker;
import de.persoapp.core.client.IEAC_Info;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.SecureHolder;
import hugo.weaving.DebugLog;

/**
 * @author Ralf Wondratschek
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        setRetainInstance(true);
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
////
////        ((BaseActivity) activity).inject(this);
//    }

    @Inject
    NfcTransportProvider mNfcTransportProvider;

    private String mTcTokenUrl;
    private String mRefreshAddress;

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
        return false;
    }

    @Override
    @DebugLog
    public void showError(String title, String message) {

    }

    @Override
    @DebugLog
    public void showMessage(String msg, int type) {

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
}
