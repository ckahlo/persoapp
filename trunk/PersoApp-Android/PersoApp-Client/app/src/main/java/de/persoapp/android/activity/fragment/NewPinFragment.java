package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import net.vrallev.android.base.util.Cat;

import javax.inject.Inject;

import de.persoapp.android.R;
import de.persoapp.android.activity.CommonChangePinActivity;
import de.persoapp.android.view.EditTextFalse;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class NewPinFragment extends PinFragment {

    private CommonChangePinActivity mActivity;

    private PinRow mPinRow;
    private PinRow mPinRowConfirm;

    @Inject
    InputMethodManager mInputMethodManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (CommonChangePinActivity) activity;
        mActivity.inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_pin, container, false);

        mPinRow = (PinRow) view.findViewById(R.id.pinRow_new_pin);
        mPinRowConfirm = (PinRow) view.findViewById(R.id.pinRow_new_pin_confirm);

        mPinRowConfirm.increaseIds();

        mPinRow.setLastImeOption(EditorInfo.IME_ACTION_NEXT);

        TextView textView = (TextView) view.findViewById(R.id.textView_new_pin_description);

        switch (mActivity.getMode()) {
            case ACTIVATE:
                textView.setText(R.string.new_pin_activate_description);
                break;

            case CHANGE:
            case UNLOCK:
                textView.setText(R.string.new_pin_pin_change_description);
                break;
        }

        return view;
    }

    @Override
    public boolean isInputComplete() {
        return mPinRow != null && mPinRowConfirm != null && !hasFalseField() && mPinRow.isComplete() && mPinRowConfirm.isComplete();
    }

    @Override
    public byte[] getApprovedPin() {
        if (!isInputComplete()) {
            return null;
        } else {
            return mPinRow.getPin();
        }
    }

    @SuppressWarnings({"ConstantConditions", "UnusedDeclaration"})
    public void onEvent(PinRow.InputEvent event) {
        switch (event) {
            case FINISHED:
                onInputFinishedEvent();
                break;
        }
    }

    private void onInputFinishedEvent() {
        if (mActivity == null) {
            Cat.w("Activity is null.");
            return;
        }

        View focusedView = mActivity.getCurrentFocus();
        if (focusedView == null) {
            Cat.w("Focused view is null.");
            return;
        }

        if (mPinRow.contains(focusedView)) {
            mPinRowConfirm.requestFocus();
        } else if (mPinRowConfirm.contains(focusedView)) {
            mInputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        } else {
            mPinRow.requestFocus();
        }
    }

    private boolean hasFalseField() {
        boolean result = false;

        for (int i = mPinRow.getFieldCount() - 1; i >= 0; i--) {
            EditTextFalse editText = mPinRow.getEditText(i);
            EditTextFalse editTextConfirm = mPinRowConfirm.getEditText(i);

            if (!TextUtils.isEmpty(editText.getText()) && !TextUtils.isEmpty(editTextConfirm.getText())) {
                boolean isFalse = !TextUtils.equals(editText.getText(), editTextConfirm.getText());
                editTextConfirm.setFalse(isFalse);
                result = result || isFalse;
            } else {
                editTextConfirm.setFalse(false);
            }
        }

        return result;
    }
}
