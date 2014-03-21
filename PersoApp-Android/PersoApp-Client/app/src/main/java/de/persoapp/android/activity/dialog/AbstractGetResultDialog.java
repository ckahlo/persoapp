package de.persoapp.android.activity.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.DialogFragment;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.util.Cat;

import java.util.concurrent.CountDownLatch;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * @author Ralf Wondratschek
 */
public abstract class AbstractGetResultDialog<T> extends DialogFragment {

    public static final String	TAG	= "getResultDialog";

    protected static final String TITLE = "title";
    protected static final String MESSAGE = "message";

    private static final String ID = "id";

    @Inject
    protected EventBus mEventBus;

    private Waiter mWaiter;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivitySupport) activity).inject(this);

        mEventBus.register(mWaiter);
    }

    public final class Waiter {

        private final CountDownLatch mCountDownLatch;
        private final double mId;

        private ResultEvent mQuestionClosedEvent;

        private Waiter(double id) {
            mCountDownLatch = new CountDownLatch(1);
            mId = id;
        }

        @SuppressWarnings("UnusedDeclaration")
        public void onEvent(ResultEvent event) {
            if (event.getId() == mId) {
                mEventBus.unregister(this);

                mQuestionClosedEvent = event;
                mCountDownLatch.countDown();
            }
        }

        public T getResult() {
            try {
                mCountDownLatch.await();
            } catch (InterruptedException e) {
                Cat.e(e);
            }

            return mQuestionClosedEvent != null ? mQuestionClosedEvent.getResult() : null;
        }
    }

    @SuppressWarnings("ConstantConditions")
    protected final void postResult(T result) {
        mEventBus.post(new ResultEvent(getArguments().getDouble(ID), result));
    }

    private class ResultEvent {
        private final double mId;
        private final T mResult;

        private ResultEvent(double id, T result) {
            mId = id;
            mResult = result;
        }

        public double getId() {
            return mId;
        }

        public T getResult() {
            return mResult;
        }
    }

    public T askForResult(final BaseActivitySupport activity, String title, String message) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalThreadStateException("You are on the UI thread!");
        }

        final double id = Math.random();

        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, message);
        args.putDouble(ID, id);
        setArguments(args);

        mWaiter = new Waiter(id);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.showDialog(AbstractGetResultDialog.this, TAG);
            }
        });

        return mWaiter.getResult();
    }
}
