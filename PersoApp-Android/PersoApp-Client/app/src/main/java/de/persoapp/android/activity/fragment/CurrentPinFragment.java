package de.persoapp.android.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import de.persoapp.android.R;
import de.persoapp.android.activity.ActivatePinActivity;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class CurrentPinFragment extends PinFragment {

    private PinRow mPinRow;

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout;
        if (getActivity() instanceof ActivatePinActivity) {
            layout = R.layout.fragment_transport_pin;
        } else {
            layout = R.layout.fragment_current_pin;
        }

        View view = inflater.inflate(layout, container, false);

        mPinRow = (PinRow) view.findViewById(R.id.pinRow_current_pin);

        // TODO: check while authentication
        mPinRow.setLastImeOption(EditorInfo.IME_ACTION_NEXT, true);

        return view;
    }

    @Override
    public boolean isInputComplete() {
        return mPinRow != null && mPinRow.isComplete();
    }
}
