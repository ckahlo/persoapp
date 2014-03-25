package de.persoapp.android.activity.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.activity.MainActivity;
import de.persoapp.android.core.adapter.NfcTransportProvider;

/**
 * @author Ralf Wondratschek
 */
@SuppressWarnings("ConstantConditions")
public class InitializeAppFragment extends Fragment {

    @Inject
    EventBus mEventBus;

    private MainActivity mMainActivity;

    private ImageView mImageView;
    private TextView mTextViewHeader;
    private View mCard;

    private ColorMatrix mColorMatrix;
    private float mSaturation;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivity = (MainActivity) activity;
        mMainActivity.inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_initialize_app, container, false);

        mImageView = (ImageView) view.findViewById(R.id.imageView);
        mTextViewHeader = (TextView) view.findViewById(R.id.textView_header);
        mCard = view.findViewById(R.id.card_init_device);

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
    public void onEvent(NfcTransportProvider.NfcConnectedEvent event) {
        if (isDeviceCapable(event.getIsoDep())) {
//            playSuccessAnimation();
            mMainActivity.onAppInitialized(true);
        } else {
            // TODO
            Toast.makeText(mMainActivity, "TODO: failur", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isDeviceCapable(IsoDep isoDep) {
        return isoDep.getMaxTransceiveLength() >= 65546;
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
                        mMainActivity.onAppInitialized(true);
                    }
                });
            }
        });
        animator.start();

        ObjectAnimator alpha = ObjectAnimator.ofFloat(mCard, "alpha", 0f);
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(mTextViewHeader, "alpha", 0f);
        alpha.setDuration(1000L);
        alpha1.setDuration(1000L);
        alpha.start();
        alpha1.start();
    }

    public void setSaturation(float saturation) {
        mSaturation = saturation;
        mColorMatrix.setSaturation(saturation);
        mImageView.setColorFilter(new ColorMatrixColorFilter(mColorMatrix));
    }

    public float getSaturation() {
        return mSaturation;
    }
}
