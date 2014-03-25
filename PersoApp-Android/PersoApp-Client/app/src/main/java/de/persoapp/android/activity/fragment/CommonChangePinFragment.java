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
import de.persoapp.android.activity.CommonChangePinActivity;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class CommonChangePinFragment extends Fragment {

    @Inject
    EventBus mEventBus;

    private CommonChangePinActivity mActivity;

    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;
    private View mViewConfirm;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (CommonChangePinActivity) activity;
        mActivity.inject(this);

        switch (mActivity.getMode()) {
            case ACTIVATE:
                mActivity.setTitle(R.string.activate_npa);
                break;
            case CHANGE:
                mActivity.setTitle(R.string.change_pin);
                break;
            case UNLOCK:
                mActivity.setTitle(R.string.unlock_pin);
                break;
            default:
                throw new IllegalStateException();
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_change_pin, container, false);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getChildFragmentManager());

        mViewConfirm = view.findViewById(R.id.textView_confirm);

        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mViewPager.setAdapter(mFragmentPagerAdapter);

        PagerSlidingTabStrip tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.pagerSlidingTabStrip);
        tabStrip.setViewPager(mViewPager);

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

        tabStrip.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // this is needed for the focus switch
                if (position == 0) {
                    mEventBus.unregister(mFragmentPagerAdapter.findFragment(1));
                } else {
                    mEventBus.register(mFragmentPagerAdapter.findFragment(1));
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mEventBus.register(this);

        setConfirmVisible(isInputComplete());
    }

    @Override
    public void onPause() {
        mEventBus.unregister(this);
        super.onPause();
    }

    public void setConfirmVisible(boolean visible) {
        if (visible && mViewConfirm.getVisibility() != View.VISIBLE) {
            ViewHelper.setVisibility(mViewConfirm, View.VISIBLE);
        } else if (!visible && mViewConfirm.getVisibility() == View.VISIBLE) {
            ViewHelper.setVisibility(mViewConfirm, View.GONE);
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(PinRow.InputEvent event) {
        switch (event) {
            case FINISHED:
                if (mViewPager.getCurrentItem() == 0) {
                    mViewPager.setCurrentItem(1);
                }
                break;

            case NEW_INPUT:
                setConfirmVisible(isInputComplete());
                break;
        }
    }

    public byte[] getPinOld() {
        if (isInputComplete()) {
            return mFragmentPagerAdapter.findFragment(0).getApprovedPin();
        } else {
            return null;
        }
    }

    public byte[] getPinNew() {
        if (isInputComplete()) {
            return mFragmentPagerAdapter.findFragment(1).getApprovedPin();
        } else {
            return null;
        }
    }

    protected boolean isInputComplete() {
        PinFragment fragment1 = mFragmentPagerAdapter.findFragment(0);
        PinFragment fragment2 = mFragmentPagerAdapter.findFragment(1);
        return fragment1 != null && fragment2 != null && fragment1.isInputComplete() && fragment2.isInputComplete();
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public PinFragment findFragment(int index) {
            return (PinFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(R.id.viewPager, index));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    switch (mActivity.getMode()) {
                        case ACTIVATE:
                            return getString(R.string.transport_pin);
                        case CHANGE:
                            return getString(R.string.current_pin);
                        case UNLOCK:
                            return getString(R.string.puk);
                        default:
                            throw new IllegalStateException();
                    }
                case 1:
                    return getString(R.string.new_pin);
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public PinFragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CurrentPinFragment();
                case 1:
                    return new NewPinFragment();
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

}
