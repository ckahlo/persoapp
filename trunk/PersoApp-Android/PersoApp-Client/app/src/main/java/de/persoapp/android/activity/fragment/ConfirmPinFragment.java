package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import net.vrallev.android.base.BaseActivity;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class ConfirmPinFragment extends PinFragment {

    @Inject
    EventBus mEventBus;

    @Inject
    InputMethodManager mInputMethodManager;

    private PinRow mPinRow;

    private BaseActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (BaseActivity) activity;
        mActivity.inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_pin, container, false);

        mPinRow = (PinRow) view.findViewById(R.id.pinRow_current_pin);

//        // TODO: check while authentication
//        mPinRow.setLastImeOption(EditorInfo.IME_ACTION_NEXT, true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mEventBus.register(this);
    }

    @Override
    public void onPause() {
        mEventBus.unregister(this);
        super.onPause();
    }

    @Override
    public boolean isInputComplete() {
        return mPinRow != null && mPinRow.isComplete();
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
        }
    }
}
