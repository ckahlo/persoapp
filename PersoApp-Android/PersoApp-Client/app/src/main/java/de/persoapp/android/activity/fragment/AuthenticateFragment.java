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

import com.astuetz.PagerSlidingTabStrip;

import net.vrallev.android.base.view.ViewHelper;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.activity.AuthenticateActivity;

/**
 * @author Ralf Wondratschek
 */
public class AuthenticateFragment extends Fragment {

    @Inject
    EventBus mEventBus;

    private AuthenticateActivity mActivity;

    private View mViewConfirm;

    private MyFragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AuthenticateActivity) activity;
        mActivity.inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authenticate, container, false);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());

        mViewConfirm = view.findViewById(R.id.textView_confirm);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(mFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pagerSlidingTabStrip);
        tabStrip.setViewPager(viewPager);

        tabStrip.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Fragment fragment = mFragmentPagerAdapter.findFragment(2);
                if (position == 2) {
                    mEventBus.register(fragment);
                } else if (fragment != null) {
                    mEventBus.unregister(fragment);
                }
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.textView_confirm:
                        mActivity.onConfirmPressed();
                        break;
                }
            }
        };

        mViewConfirm.setOnClickListener(onClickListener);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        setConfirmVisible(mActivity.getPin() != null);
    }

    public void setConfirmVisible(boolean visible) {
        if (visible && mViewConfirm.getVisibility() != View.VISIBLE) {
            ViewHelper.setVisibility(mViewConfirm, View.VISIBLE);
        } else if (!visible && mViewConfirm.getVisibility() == View.VISIBLE) {
            ViewHelper.setVisibility(mViewConfirm, View.GONE);
        }
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
