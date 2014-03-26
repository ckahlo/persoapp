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

        if (mWaiter != null) {
            // the waiter is null on orientation change, but that's fine, it's already registered on the event bus
            mEventBus.register(mWaiter);
        }
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
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(MESSAGE, message);

        return askForResult(activity, args);
    }

    public T askForResult(final BaseActivitySupport activity, Bundle args) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalThreadStateException("You are on the UI thread!");
        }

        final double id = Math.random();

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
