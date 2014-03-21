package de.persoapp.android.activity;

import android.app.FragmentManager;
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
import de.persoapp.android.activity.fragment.CurrentPinFragment;
import de.persoapp.android.activity.fragment.NewPinFragment;
import de.persoapp.android.activity.fragment.PinFragment;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class ActivatePinActivity extends AbstractNfcActivity {

    @Inject
    EventBus mEventBus;

    private ViewPager mViewPager;

    private MyFragmentPagerAdapter mFragmentPagerAdapter;

    private View mViewConfirm;
    private View mViewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_pin);

        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getFragmentManager());

        mViewConfirm = findViewById(R.id.textView_confirm);
        mViewConfirm.setVisibility(View.INVISIBLE);
//        mViewConfirm.setEnabled(false);

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
                        Toast.makeText(ActivatePinActivity.this, "Confirm", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.textView_cancel:
                        finish();
                        break;
                }
            }
        };

        mViewConfirm.setOnClickListener(onClickListener);
        mViewCancel.setOnClickListener(onClickListener);

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

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(PinRow.InputEvent event) {
        switch (event) {
            case FINISHED:
                if (mViewPager.getCurrentItem() == 0) {
                    mViewPager.setCurrentItem(1);
                }
                break;

            case NEW_INPUT:
//                mViewConfirm.setEnabled(isInputComplete());
                if (isInputComplete() && mViewConfirm.getVisibility() != View.VISIBLE) {
                    ViewHelper.setVisibility(mViewConfirm, View.VISIBLE);
                } else if (!isInputComplete() && mViewConfirm.getVisibility() == View.VISIBLE) {
                    ViewHelper.setVisibility(mViewConfirm, View.INVISIBLE);
                }
                break;
        }
    }

    protected boolean isInputComplete() {
        return mFragmentPagerAdapter.findFragment(0).isInputComplete() && mFragmentPagerAdapter.findFragment(1).isInputComplete();
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }

        public PinFragment findFragment(int index) {
            return (PinFragment) getFragmentManager().findFragmentByTag(makeFragmentName(R.id.viewPager, index));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.transport_pin);
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
}
