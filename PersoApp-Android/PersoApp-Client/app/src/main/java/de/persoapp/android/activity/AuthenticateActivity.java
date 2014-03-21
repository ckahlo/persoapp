package de.persoapp.android.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import net.vrallev.android.base.view.ViewHelper;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.activity.fragment.CertificateFragment;
import de.persoapp.android.activity.fragment.ConfirmPinFragment;
import de.persoapp.android.activity.fragment.DataFragment;
import de.persoapp.android.activity.fragment.PinFragment;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class AuthenticateActivity extends AbstractNfcActivity {

    private static final String TC_TOKEN_PARAMETER = "tcTokenUrl";

    @Inject
    EventBus mEventBus;

    private ViewPager mViewPager;

    private MyFragmentPagerAdapter mFragmentPagerAdapter;

    private View mViewConfirm;
    private View mViewCancel;

    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        mUri = getIntent().getData();

        if (mUri == null || mUri.getQueryParameter(TC_TOKEN_PARAMETER) == null) {
            Toast.makeText(this, R.string.invalid_request, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());

        mViewConfirm = findViewById(R.id.textView_confirm);
        mViewConfirm.setVisibility(View.INVISIBLE);

        mViewCancel = findViewById(R.id.textView_cancel);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(mFragmentPagerAdapter);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) findViewById(R.id.pagerSlidingTabStrip);
        tabStrip.setViewPager(mViewPager);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.textView_confirm:
                        // TODO:
                        Toast.makeText(AuthenticateActivity.this, "Confirm", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.textView_cancel:
                        finish();
                        break;
                }
            }
        };

        mViewConfirm.setOnClickListener(onClickListener);
        mViewCancel.setOnClickListener(onClickListener);

        if (mUri != null) {

            final String tcURL = mUri.getQueryParameter("tcTokenURL");
            if (tcURL != null) {
                mMainViewFragment.setTcTokenUrl(tcURL);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mEventBus.register(this);
    }

    @Override
    protected void onPause() {
        mEventBus.unregister(this);
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        mNfcTransportProvider.handleIntent(intent);
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(PinRow.InputEvent event) {
        switch (event) {
            case NEW_INPUT:
                if (isInputComplete() && mViewConfirm.getVisibility() != View.VISIBLE) {
                    ViewHelper.setVisibility(mViewConfirm, View.VISIBLE);
                } else if (!isInputComplete() && mViewConfirm.getVisibility() == View.VISIBLE) {
                    ViewHelper.setVisibility(mViewConfirm, View.INVISIBLE);
                }
                break;
        }
    }

    protected boolean isInputComplete() {
        return ((PinFragment) mFragmentPagerAdapter.findFragment(2)).isInputComplete();
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment findFragment(int index) {
            return getFragmentManager().findFragmentByTag(makeFragmentName(R.id.viewPager, index));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.certificate_information);
                case 1:
                    return getString(R.string.read_data);
                case 2:
                    return getString(R.string.pin_enter);
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CertificateFragment();
                case 1:
                    return new DataFragment();
                case 2:
                    return new ConfirmPinFragment();
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }
}
