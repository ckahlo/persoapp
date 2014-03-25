package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import de.persoapp.android.R;
import de.persoapp.android.activity.CommonChangePinActivity;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class CurrentPinFragment extends PinFragment {

    private CommonChangePinActivity mActivity;
    private PinRow mPinRow;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (CommonChangePinActivity) activity;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout;
        switch (mActivity.getMode()) {
            case ACTIVATE:
                layout = R.layout.fragment_transport_pin;
                break;
            case CHANGE:
                layout = R.layout.fragment_current_pin;
                break;
            case UNLOCK:
                layout = R.layout.fragment_puk;
                break;
            default:
                throw new IllegalStateException();
        }

        View view = inflater.inflate(layout, container, false);

        mPinRow = (PinRow) view.findViewById(R.id.pinRow_current_pin);

        mPinRow.setLastImeOption(EditorInfo.IME_ACTION_NEXT, true);

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
}
