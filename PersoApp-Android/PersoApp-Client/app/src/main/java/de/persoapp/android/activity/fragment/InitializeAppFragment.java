package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.persoapp.android.R;
import de.persoapp.android.activity.MainActivity;

/**
 * @author Ralf Wondratschek
 */
public class InitializeAppFragment extends Fragment {

    private MainActivity mMainActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMainActivity = (MainActivity) activity;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_initialize_app, container, false);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_done:
                        mMainActivity.onAppInitialized(true);
                        break;
                }
            }
        };

        view.findViewById(R.id.button_done).setOnClickListener(onClickListener);

        return view;
    }
}
