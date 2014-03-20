package de.persoapp.android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.vrallev.android.base.BaseActivity;

import javax.inject.Inject;

import de.persoapp.android.R;
import de.persoapp.android.core.adapter.MainViewFacade;
import de.persoapp.android.core.adapter.MainViewFragment;
import de.persoapp.android.core.adapter.NfcTransportProvider;

/**
 * @author Ralf Wondratschek
 */
public class MainActivity extends BaseActivity {

//    @Inject
//    SettingsMgr mSettingsMgr;

    @Inject
    NfcTransportProvider mNfcTransportProvider;

    @Inject
    MainViewFacade mMainViewFacade;

    private Uri mUri;

//    private String mRefreshAddress;

    private MainViewFragment mMainViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUri = getIntent().getData();

        mMainViewFragment = MainViewFragment.findOrCreateFragment(this);
        mMainViewFacade.setMainView(mMainViewFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        NfcTransport.getInstance().activateNFC(this);
        mNfcTransportProvider.enableForegroundDispatch(this);
    }

    @Override
    protected void onPause() {
        mNfcTransportProvider.disableForegroundDispatch(this);
//        NfcTransport.getInstance().pause(this);
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

//        NfcTransport.getInstance().handleIntent(intent);
        mNfcTransportProvider.handleIntent(intent);

        if (mUri != null) {

            final String tcURL = mUri.getQueryParameter("tcTokenURL");
            if (tcURL != null) {
                mMainViewFragment.setTcTokenUrl(tcURL);
            }

//            final ClassLoader oldCL = Thread.currentThread().getContextClassLoader();

//            try {
////                Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
////                mRefreshURL = ECardApiHandler.startECardAPI(tcURL, AuthenticateFragment.this);
//
//                new Thread() {
//                    @Override
//                    public void run() {
//                        try {
////                            mRefreshAddress = ECardWorker.start(new URL(tcURL));
//                            mRefreshAddress = new AndroidECApiHttpHandler().startAuthentication(new URL(tcURL));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }.start();
//
//            } catch (final Exception e) {
//                e.printStackTrace();
//            } finally {
////                AuthenticateFragment.this.closeDialogs();
////                Thread.currentThread().setContextClassLoader(oldCL);
//            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:

                Toast.makeText(this, "TODO", Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    public class MyMainView implements IMainView {
//
//        @Override
//        @DebugLog
//        public void setEventLister(EventListener eventListener) {
//
//        }
//
//        @Override
//        @DebugLog
//        public Object triggerEvent(int i, Object... objects) {
//            return null;
//        }
//
//        @Override
//        @DebugLog
//        public void showMainDialog(IEAC_Info ieac_info, int i) {
//        }
//
//        @Override
//        @DebugLog
//        public MainDialogResult getMainDialogResult() {
//        }
//
//        @Override
//        @DebugLog
//        public SecureHolder showCANDialog(String s) {
//            return null;
//        }
//
//        @Override
//        @DebugLog
//        public void showChangePinDialog() {
//
//        }
//
//        @Override
//        @DebugLog
//        public void showProgress(String s, int i, boolean b) {
//
//        }
//
//        @Override
//        @DebugLog
//        public boolean showQuestion(String s, String s2) {
//            return false;
//        }
//
//        @Override
//        @DebugLog
//        public void showError(String s, String s2) {
//
//        }
//
//        @Override
//        @DebugLog
//        public void showMessage(String s, int i) {
//
//        }
//
//        @Override
//        @DebugLog
//        public void closeDialogs() {
//        }
//
//        @Override
//        @DebugLog
//        public void shutdown() {
//
//        }
//    }
}
