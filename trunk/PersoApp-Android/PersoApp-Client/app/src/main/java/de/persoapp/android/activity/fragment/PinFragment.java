package de.persoapp.android.activity.fragment;

import android.support.v4.app.Fragment;

/**
 * @author Ralf Wondratschek
 */
public abstract class PinFragment extends Fragment {

    public abstract boolean isInputComplete();

    public abstract byte[] getApprovedPin();
}
