package de.persoapp.android.activity.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import net.vrallev.android.base.BaseActivitySupport;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.nfc.DeviceStateTester;

/**
 * @author Ralf Wondratschek
 */
@SuppressWarnings("ConstantConditions")
public class InitializeAppFragment extends Fragment {

    @Inject
    EventBus mEventBus;

    private ImageView mImageView;

    private ColorMatrix mColorMatrix;
    private float mSaturation;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivitySupport) activity).inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_initialize_app, container, false);

        mImageView = (ImageView) view.findViewById(R.id.imageView);

        mSaturation = 0;
        mColorMatrix = new ColorMatrix();
        mColorMatrix.setSaturation(mSaturation);
        mImageView.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        mEventBus.unregister(this);
        super.onStop();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(DeviceStateTester.NpaCapableEvent event) {
        if (event.isNpaSupported()) {
            playSuccessAnimation();
        } else {
            mEventBus.post(new OnAppInitialized(false));
        }
    }

    private void playSuccessAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "saturation", 1F);
        animator.setDuration(1000L);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mImageView.animate().alpha(0).setDuration(300l).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        mEventBus.post(new OnAppInitialized(true));
                    }
                });
            }
        });
        animator.start();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void setSaturation(float saturation) {
        mSaturation = saturation;
        mColorMatrix.setSaturation(saturation);
        mImageView.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
    }

    @SuppressWarnings("UnusedDeclaration")
    public float getSaturation() {
        return mSaturation;
    }

    public static class OnAppInitialized {
        private final boolean mSuccess;

        public OnAppInitialized(boolean success) {
            mSuccess = success;
        }

        public boolean isSuccess() {
            return mSuccess;
        }
    }
}
