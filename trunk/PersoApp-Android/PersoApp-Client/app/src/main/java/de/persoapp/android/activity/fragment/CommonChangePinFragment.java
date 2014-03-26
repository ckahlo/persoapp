/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013 AGETO Innovation GmbH
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
        mEventBus.unregister(mFragmentPagerAdapter.findFragment(1));
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
        return fragment1 != null && fragment2 != null && fragment2.isInputComplete() && fragment1.isInputComplete();
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
