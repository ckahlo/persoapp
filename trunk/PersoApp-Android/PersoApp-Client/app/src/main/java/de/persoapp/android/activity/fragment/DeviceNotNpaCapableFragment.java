package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.lib.crouton.extension.CroutonBuilder;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import de.persoapp.android.R;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import de.persoapp.android.nfc.NpaTester;

/**
 * @author Ralf Wondratschek
 */
public class DeviceNotNpaCapableFragment extends Fragment {

    @Inject
    EventBus mEventBus;

    @Inject
    NpaTester mNpaTester;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivitySupport) activity).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device_not_npa_capable, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mEventBus.register(this);
    }

    @Override
    public void onStop() {
        mEventBus.unregister(this);
        super.onStop();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEvent(NfcTransportProvider.NfcConnectedEvent event) {
        boolean result = mNpaTester.testIsoDep(event.getIsoDep());

        if (result) {
            mEventBus.post(new InitializeAppFragment.OnAppInitialized(true));
        } else {
            CroutonBuilder.showError(getActivity(), getString(R.string.test_failed_again));
        }
    }
}
