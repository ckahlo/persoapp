package de.persoapp.android.activity.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.persoapp.android.R;
import de.persoapp.android.activity.PinOptionsActivity;

/**
 * @author Ralf Wondratschek
 */
public class MainFragment extends Fragment {

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.card_use_npa:
                        startActivity(new Intent(getActivity(), PinOptionsActivity.class));
                        break;
                }
            }
        };

        view.findViewById(R.id.card_use_npa).setOnClickListener(onClickListener);

        return view;
    }
}
