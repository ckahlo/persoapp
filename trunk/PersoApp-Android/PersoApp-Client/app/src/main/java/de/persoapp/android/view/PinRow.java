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
package de.persoapp.android.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.vrallev.android.base.BaseActivitySupport;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.AppExtension;
import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
@SuppressWarnings({"UnusedParameters", "UnusedDeclaration"})
public class PinRow extends LinearLayout {

    private static final int DEFAULT_FIELD_COUNT = 6;

    @Inject
    protected EventBus mEventBus;

    protected int mFieldCount;
    protected int mFieldMargin;

    protected EditTextFalse[] mEditTexts;

    public PinRow(Context context) {
        super(context);
        constructor(context, null, 0);
    }

    public PinRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructor(context, attrs, 0);
    }

    public PinRow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        constructor(context, attrs, defStyle);
    }

    @SuppressWarnings("ConstantConditions")
    protected void constructor(Context context, AttributeSet attrs, int defStyle) {
        mFieldCount = DEFAULT_FIELD_COUNT;
        mFieldMargin = -1;

        if (attrs != null) {
            TypedArray typedArray = null;
            try {
                typedArray = context.obtainStyledAttributes(attrs, R.styleable.PinRow);
                mFieldCount = typedArray.getInteger(R.styleable.PinRow_fieldCount, mFieldCount);
                mFieldMargin = (int) typedArray.getDimension(R.styleable.PinRow_fieldMargin, mFieldMargin);

            } finally {
                if (typedArray != null) {
                    typedArray.recycle();
                }
            }
        }

        mEditTexts = new EditTextFalse[mFieldCount];

        final LayoutInflater layoutInflater = LayoutInflater.from(context);

        for (int i = 0; i < mEditTexts.length; i++) {
            mEditTexts[i] = (EditTextFalse) layoutInflater.inflate(R.layout.pin_field, this, false);
            addView(mEditTexts[i]);

            if (mFieldMargin >= 0) {
                ((LayoutParams)mEditTexts[i].getLayoutParams()).setMargins(mFieldMargin, mFieldMargin, mFieldMargin, mFieldMargin);
            }

            mEditTexts[i].setId(100 + i); // assign IDs for configuration change
            mEditTexts[i].setImeOptions(i < (mEditTexts.length - 1) ? EditorInfo.IME_ACTION_NEXT : EditorInfo.IME_ACTION_DONE);
            mEditTexts[i].addTextChangedListener(mTextWatcher);
            mEditTexts[i].setRawInputType(Configuration.KEYBOARD_12KEY);
        }

        if (context instanceof BaseActivitySupport) {
            ((BaseActivitySupport) context).inject(this);
        } else if (isInEditMode()) {
            mEventBus = EventBus.getDefault();
        } else {
            ((AppExtension)context.getApplicationContext()).inject(this);
        }
    }

    public int getFieldCount() {
        return mFieldCount;
    }

    public boolean contains(View view) {
        if (view instanceof EditText) {
            for (EditText editText : mEditTexts) {
                if (view.equals(editText)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setLastImeOption(int imeOption) {
        setLastImeOption(imeOption, false);
    }

    public void setLastImeOption(int imeOption, boolean withEvent) {
        mEditTexts[mFieldCount - 1].setImeOptions(imeOption);
        if (withEvent) {
            mEditTexts[mFieldCount - 1].setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        mEventBus.post(InputEvent.FINISHED);
                    }
                    return false;
                }
            });
        }
    }

    public byte[] getPin() {
        StringBuilder builder = new StringBuilder();
        for (EditText editText : mEditTexts) {
            if (!TextUtils.isEmpty(editText.getText())) {
                builder.append(editText.getText());
            }
        }

        return builder.toString().getBytes();
    }

    public boolean isComplete() {
        for (EditText editText : mEditTexts) {
            if (TextUtils.isEmpty(editText.getText())) {
                return false;
            }
        }
        return true;
    }

    public EditTextFalse getEditText(int index) {
        return mEditTexts[index];
    }

    // this is necessary, when we have two PinRows in one layout
    public void increaseIds() {
        for (EditText editText : mEditTexts) {
            editText.setId(editText.getId() + 100);
        }
    }

    private TextWatcher mTextWatcher = new TextWatcher() {

        private boolean mIgnoreNextEvent;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s instanceof SpannableStringBuilder) {
                SpannableStringBuilder builder = (SpannableStringBuilder) s;

                if (builder.length() >= 2) {
                    // we only want a max length of 1, but we set a maxLength of 2 to get a callback in the textWatcher
                    // don't post the next event, because we programmatically changed the text
                    mIgnoreNextEvent = true;
                    char c = builder.charAt(start);
                    builder.replace(0, builder.length(), String.valueOf(c));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mIgnoreNextEvent) {
                mIgnoreNextEvent = false;
                return;
            }

            mEventBus.post(InputEvent.NEW_INPUT);

            if(!TextUtils.isEmpty(s)){
                View view = getRootView();
                if (view == null) {
                    return;
                }
                view = view.findFocus();
                if (view == null) {
                    return;
                }

                view = view.focusSearch(FOCUS_RIGHT);
                if (view != null) {
                    view.requestFocus();
                } else {
                    mEventBus.post(InputEvent.FINISHED);
                }
            }

        }
    };

    public static enum InputEvent {
        NEW_INPUT,
        FINISHED
    }
}
