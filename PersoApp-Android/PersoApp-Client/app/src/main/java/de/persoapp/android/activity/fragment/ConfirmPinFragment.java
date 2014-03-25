package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import javax.inject.Inject;

import de.persoapp.android.R;
import de.persoapp.android.activity.AuthenticateActivity;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class ConfirmPinFragment extends PinFragment {

    @Inject
    InputMethodManager mInputMethodManager;

    private PinRow mPinRow;

    private AuthenticateActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AuthenticateActivity) activity;
        mActivity.inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_pin, container, false);

        mPinRow = (PinRow) view.findViewById(R.id.pinRow_current_pin);

        return view;
    }

    @Override
    public boolean isInputComplete() {
        return mPinRow != null && mPinRow.isComplete();
    }

    @Override
    public byte[] getApprovedPin() {
        if (isInputComplete()) {
            return mPinRow.getPin();
        } else {
            return null;
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(PinRow.InputEvent event) {
        switch (event) {
            case FINISHED:
                View focusedView = mActivity.getCurrentFocus();
                if (focusedView != null) {
                    mInputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
                }
                break;
            case NEW_INPUT:
                if (getParentFragment() instanceof AuthenticateFragment) {
                    boolean isInputComplete = isInputComplete();
                    if (isInputComplete) {
                        mActivity.setPin(mPinRow.getPin());
                    } else {
                        mActivity.setPin(null);
                    }

                    ((AuthenticateFragment) getParentFragment()).setConfirmVisible(isInputComplete);
                }
                break;
        }
    }
}
