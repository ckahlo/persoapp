/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
 *
 * Authors Christian Kahlo, Ralf Wondratschek
 *
 * All Rights Reserved.
 *
 * Contact: PersoApp, http://www.persoapp.de
 *
 * @version 1.0, 30.07.2013 13:50:47
 *
 *          This file is part of PersoApp.
 *
 *          PersoApp is free software: you can redistribute it and/or modify it
 *          under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation, either version 3 of the
 *          License, or (at your option) any later version.
 *
 *          PersoApp is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details.
 *
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with PersoApp. If not, see
 *          <http://www.gnu.org/licenses/>.
 *
 *          Diese Datei ist Teil von PersoApp.
 *
 *          PersoApp ist Freie Software: Sie können es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          späteren veröffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 *
 *          PersoApp wird in der Hoffnung, dass es nützlich sein wird, aber OHNE
 *          JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License für weitere
 *          Details.
 *
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 *
 */
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
 * The <tt>CertificateFragment</tt> displays information about the
 * transmitted certificate of the eID-Server to the user.
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
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
