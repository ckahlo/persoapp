package de.persoapp.android.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
public class DataFragment extends Fragment {

    private ViewGroup mRequiredContainer;
    private View mOptionalContainer;

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        mRequiredContainer = (ViewGroup) view.findViewById(R.id.card_required_fields_container);
        mOptionalContainer = view.findViewById(R.id.card_optional_fields_container);

        for (int i = mRequiredContainer.getChildCount() - 1; i >= 0; i--) {
            CheckBox checkBox = (CheckBox) mRequiredContainer.getChildAt(i);
            checkBox.setEnabled(false);
            checkBox.setChecked(true);
        }

        return view;
    }

    protected CheckBox getCheckBox(boolean required, int id) {
        if (required) {
            return (CheckBox) mRequiredContainer.findViewById(id);
        } else {
            return (CheckBox) mOptionalContainer.findViewById(id);
        }
    }
}
