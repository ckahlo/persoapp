package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.view.ViewHelper;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class AuthenticateFragment extends Fragment {

    @Inject
    EventBus mEventBus;

    private ViewPager mViewPager;

    private MyFragmentPagerAdapter mFragmentPagerAdapter;

    private View mViewConfirm;
    private View mViewCancel;

    private BaseActivitySupport mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivitySupport) activity;
        mActivity.inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authenticate, container, false);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());

        mViewConfirm = view.findViewById(R.id.textView_confirm);
        mViewConfirm.setVisibility(View.INVISIBLE);

        mViewCancel = view.findViewById(R.id.textView_cancel);

        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(mFragmentPagerAdapter);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pagerSlidingTabStrip);
        tabStrip.setViewPager(mViewPager);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.textView_confirm:
                        // TODO:
                        Toast.makeText(mActivity, "Confirm", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.textView_cancel:
                        mActivity.finish();
                        break;
                }
            }
        };

        mViewConfirm.setOnClickListener(onClickListener);
        mViewCancel.setOnClickListener(onClickListener);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mEventBus.register(this);
    }

    @Override
    public void onPause() {
        mEventBus.unregister(this);
        super.onPause();
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
            return getChildFragmentManager().findFragmentByTag(makeFragmentName(R.id.viewPager, index));
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
