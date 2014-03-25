package de.persoapp.android.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.text.Editable;
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
 *
 * TODO: delete button
 * TODO: Sony devices fix
 */
@SuppressWarnings({"UnusedParameters", "UnusedDeclaration"})
public class PinRow extends LinearLayout {

    private static final int DEFAULT_FIELD_COUNT = 6;

    @Inject
    protected EventBus mEventBus;

    protected int mFieldCount;
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

        if (attrs != null) {
            TypedArray typedArray = null;
            try {
                typedArray = context.obtainStyledAttributes(attrs, R.styleable.PinRow);
                mFieldCount = typedArray.getInteger(R.styleable.PinRow_fieldCount, mFieldCount);

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

            mEditTexts[i].setId(100 + i); // assign IDs for configuration change
            mEditTexts[i].setOnKeyListener(mOnKeyListener);
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

    private OnKeyListener mOnKeyListener = new OnKeyListener() {

        private char mChar = 255;

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (v instanceof EditText) {
                        EditText editText = (EditText) v;
                        if (!TextUtils.isEmpty(editText.getText())) {
                            mChar = (char) event.getUnicodeChar();
                        }
                    }
                    break;

                case KeyEvent.ACTION_UP:
                    View view = v.focusSearch(FOCUS_RIGHT);
                    if (view != null) {
                        view.requestFocus();
                    } else {
                        mEventBus.post(InputEvent.FINISHED);
                    }

                    if (mChar != 255) {
                        ((EditText) v).setText(String.valueOf(mChar));
                        mChar = 255;
                    }

                    break;
            }

            return false;
        }
    };

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mEventBus.post(InputEvent.NEW_INPUT);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public static enum InputEvent {
        NEW_INPUT,
        FINISHED
    }
}
