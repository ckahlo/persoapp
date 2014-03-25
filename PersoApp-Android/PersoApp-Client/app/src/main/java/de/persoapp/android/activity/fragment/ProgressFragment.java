package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.vrallev.android.base.util.Cat;

import java.util.concurrent.CountDownLatch;

import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
public class ProgressFragment extends Fragment {

    private static final int SET_TEXT = 1;
    private static final String MESSAGE = "message";

    private TextView mTextViewMessage;
    private Handler mHandler;

    public ProgressFragment() {
        setArguments(new Bundle());

        HandlerThread handlerThread = new HandlerThread("progressAnimator");
        handlerThread.start();
        mHandler = new MyHandler(handlerThread.getLooper());
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);

        mTextViewMessage = (TextView) view.findViewById(R.id.textView_message);
        mTextViewMessage.setText(getArguments().getString(MESSAGE, ""));

        return view;
    }

    @SuppressWarnings("ConstantConditions")
    public void setMessage(String message) {
        if (message == null) {
            message = "";
        }

        getArguments().putString(MESSAGE, message);
        if (mTextViewMessage != null) {
            mHandler.sendMessage(mHandler.obtainMessage(SET_TEXT, message));
        }
    }

    @SuppressWarnings("ConstantConditions")
    private class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(final Message msg) {
            Activity activity = getActivity();

            if (msg.what != SET_TEXT || activity == null) {
                return;
            }

            final CountDownLatch countDownLatch = new CountDownLatch(1);

            final Runnable secondEndAction = new Runnable() {
                @Override
                public void run() {
                    countDownLatch.countDown();
                }
            };
            final Runnable firstEndAction = new Runnable() {
                @Override
                public void run() {
                    mTextViewMessage.setText((CharSequence) msg.obj);
                    mTextViewMessage.animate().alpha(1).setDuration(150l).withEndAction(secondEndAction);
                }
            };

            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextViewMessage.animate().alpha(0f).setDuration(150l).withEndAction(firstEndAction);
                }
            });

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                Cat.e(e);
            }
        }
    }
}
