package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.util.Cat;

import javax.inject.Inject;

import de.persoapp.android.R;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 *
 * TODO: compare characters while typing
 */
public class NewPinFragment extends PinFragment {

    private BaseActivitySupport mActivity;

    private PinRow mPinRow;
    private PinRow mPinRowConfirm;

    @Inject
    InputMethodManager mInputMethodManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivitySupport) activity;
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

        return view;
    }

    @Override
    public boolean isInputComplete() {
        return mPinRow != null && mPinRowConfirm != null && mPinRow.isComplete() && mPinRowConfirm.isComplete();
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

        // TODO: possible null
        View focusedView = mActivity.getCurrentFocus();

        if (mPinRow.contains(focusedView)) {
            mPinRowConfirm.requestFocus();
        } else if (mPinRowConfirm.contains(focusedView)) {
            mInputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        } else {
            mPinRow.requestFocus();
        }
    }
}
