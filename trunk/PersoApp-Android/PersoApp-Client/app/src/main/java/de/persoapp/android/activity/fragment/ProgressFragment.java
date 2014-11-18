/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
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
 * The <tt>ProgressFragment</tt> implements an interface to the logic of the
 * animation which is used to animate the progress of the ongoing operation. 
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
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
