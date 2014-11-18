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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import net.vrallev.android.base.util.Cat;

import de.persoapp.android.R;
import de.persoapp.android.activity.AuthenticateActivity;
import de.persoapp.core.client.IEAC_Info;

/**
 * This class manages and shows the different data, which is requested by the 
 * eID-Server.
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class DataFragment extends Fragment {

    private static final int[] POS = new int[]{0, 1, 2, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 20, 24, 26};
    private static final int[] TEXT_VIEW_ID = new int[]{
            R.id.data_altersverifikation, R.id.data_wohnortbestaetigung, R.id.data_pseudonym, R.id.data_ausweistyp,
            R.id.data_ausstellendes_land, R.id.data_ablaufdatum, R.id.data_vorname, R.id.data_name,
            R.id.data_kuenstlername, R.id.data_doktorgrad, R.id.data_geburtsdatum, R.id.data_geburtsort,
            R.id.data_nationalitaet, R.id.data_geburtsname, R.id.data_anschrift, R.id.data_nebenbestimmungen_1
    };

    private static final int ALTERSVERIFIKATION = 0;

    private ViewGroup mRequiredContainer;
    private ViewGroup mOptionalContainer;

    private TextView mTextViewRequiredHeader;
    private TextView mTextViewOptionalHeader;

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
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        mRequiredContainer = (ViewGroup) view.findViewById(R.id.card_required_fields_container);
        mOptionalContainer = (ViewGroup) view.findViewById(R.id.card_optional_fields_container);

        mTextViewRequiredHeader = (TextView) view.findViewById(R.id.textView_required_header);
        mTextViewOptionalHeader = (TextView) view.findViewById(R.id.textView_optional_header);

        // we need to change the IDs, otherwise onSavedInstanceState produces unexpected results
        increaseIds(mOptionalContainer);

        fillFields(mInfo.getRequiredChat(), mInfo.getOptionalChat(), mInfo.getVerifyAge());

        for (int i = mOptionalContainer.getChildCount() - 1; i >= 0; i--) {
            CheckBox box = (CheckBox) mOptionalContainer.getChildAt(i);
            box.setOnCheckedChangeListener(mOnCheckedChangeListener);
        }

        if (savedInstanceState == null) {
            mActivity.setResultChat(getResultChat());
        }

        return view;
    }

    @SuppressWarnings("ConstantConditions")
    protected void increaseIds(ViewGroup viewGroup) {
        for (int i = viewGroup.getChildCount() - 1; i >= 0; i--) {
            View view = viewGroup.getChildAt(i);
            view.setId(view.getId() + 100);
        }
    }

    protected CheckBox getCheckBox(boolean required, int id) {
        if (required) {
            return (CheckBox) mRequiredContainer.findViewById(id);
        } else {
            return (CheckBox) mOptionalContainer.findViewById(id + 100);
        }
    }

    private void fillFields(long req, long opt, long verifyAge) {
        boolean hasRequired = false;
        boolean hasOptional = false;

        for (int i = 0; i < POS.length; i++) {
            if ((req & (1L << POS[i])) != 0) {
                CheckBox box = getCheckBox(true, TEXT_VIEW_ID[i]);
                box.setChecked(true);
                box.setEnabled(false);
                box.setVisibility(View.VISIBLE);
                hasRequired = true;

            } else if ((opt & (1L << POS[i])) != 0) {
                CheckBox box = getCheckBox(false, TEXT_VIEW_ID[i]);
                box.setChecked(false);
                box.setEnabled(true);
                box.setVisibility(View.VISIBLE);
                hasOptional = true;
            }
        }

        if (!hasRequired) {
            mRequiredContainer.setVisibility(View.GONE);
            mTextViewRequiredHeader.setVisibility(View.GONE);
        }
        if (!hasOptional) {
            mOptionalContainer.setVisibility(View.GONE);
            mTextViewOptionalHeader.setVisibility(View.GONE);
        }

        if (verifyAge > 0) {
            CheckBox checkBox = getCheckBox(true, TEXT_VIEW_ID[ALTERSVERIFIKATION]);
            if (checkBox.getVisibility() != View.VISIBLE) {
                checkBox = getCheckBox(false, TEXT_VIEW_ID[ALTERSVERIFIKATION]);
            }
            if (checkBox.getVisibility() != View.VISIBLE) {
                Cat.w("Wrong state");
            }
            checkBox.setText(getString(R.string.authenticate_field_altersverifikation_text, verifyAge));
        }
    }

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mActivity.setResultChat(getResultChat());
        }
    };

    public long getResultChat() {
        long result = 0;

        for (int i = 0; i < POS.length; i++) {
            int id = TEXT_VIEW_ID[i];
            result += (getCheckBox(true, id).isChecked() || getCheckBox(false, id).isChecked()) ? (1 << POS[i]) : 0;
        }

        return result;
    }
}
