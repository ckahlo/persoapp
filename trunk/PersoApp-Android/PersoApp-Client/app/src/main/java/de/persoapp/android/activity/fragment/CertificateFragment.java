package de.persoapp.android.activity.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.vrallev.android.base.util.Cat;

import java.util.Date;

import de.persoapp.android.R;
import de.persoapp.android.activity.AuthenticateActivity;
import de.persoapp.core.client.IEAC_Info;

/**
 * @author Ralf Wondratschek
 *
 * TODO: finish
 *
 */
public class CertificateFragment extends Fragment {

    private TextView mTextViewSubjectName;
    private TextView mTextViewSubjectUrl;
    private TextView mTextViewTermsOfUsage;
    private TextView mTextViewTransaction;
    private TextView mTextViewDate;
    private TextView mTextViewIssuerName;
    private TextView mTextViewIssuerUrl;

    private AuthenticateActivity mActivity;
    private IEAC_Info mInfo;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AuthenticateActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInfo = mActivity.getIeacInfo();
        if (mInfo == null) {
            Cat.w(new NullPointerException(), "IEAC_Info is null!");
        }
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certificate, container, false);

        mTextViewSubjectName = (TextView) view.findViewById(R.id.textView_service_provider_subject_name);
        mTextViewSubjectUrl = (TextView) view.findViewById(R.id.textView_service_provider_subject_url);
        mTextViewDate = (TextView) view.findViewById(R.id.textView_service_provider_date);
        mTextViewTermsOfUsage = (TextView) view.findViewById(R.id.textView_service_provider_terms_of_usage);
        mTextViewTransaction = (TextView) view.findViewById(R.id.textView_service_provider_transaction);

        mTextViewIssuerName = (TextView) view.findViewById(R.id.textView_certificate_issuer_name);
        mTextViewIssuerUrl = (TextView) view.findViewById(R.id.textView_certificate_issuer_url);

        fillInfo();

        return view;
    }

    protected void fillInfo() {
        mTextViewSubjectName.setText(mInfo.getSubjectName());
        mTextViewSubjectUrl.setText(mInfo.getSubjectURL());
        mTextViewTermsOfUsage.setText(mInfo.getTermsOfUsage());
        mTextViewTransaction.setText(mInfo.getTransactionInfo());

        mTextViewDate.setText(getString(R.string.validity_string, dateToString(mActivity, mInfo.getEffectiveDate()), dateToString(mActivity, mInfo.getExpirationDate())));

        mTextViewIssuerName.setText(mInfo.getIssuerName());
        mTextViewIssuerUrl.setText(mInfo.getIssuerURL());
    }

    public static String dateToString(Context context, Date date) {
        return DateFormat.getDateFormat(context).format(date);
    }
}
