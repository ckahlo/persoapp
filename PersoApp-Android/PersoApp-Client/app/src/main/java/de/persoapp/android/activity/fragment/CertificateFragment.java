package de.persoapp.android.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
public class CertificateFragment extends Fragment {

    private TextView mTextViewServiceProvider;
    private TextView mTextViewIssuer;

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certificate, container, false);

        mTextViewServiceProvider = (TextView) view.findViewById(R.id.textView_certificate_service_provider);
        mTextViewIssuer = (TextView) view.findViewById(R.id.textView_certificate_issuer);

        return view;
    }
}
