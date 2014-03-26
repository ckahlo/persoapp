/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013 AGETO Innovation GmbH
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import de.persoapp.android.R;
import de.persoapp.android.activity.CommonChangePinActivity;
import de.persoapp.android.view.PinRow;

/**
 * @author Ralf Wondratschek
 */
public class CurrentPinFragment extends PinFragment {

    private CommonChangePinActivity mActivity;
    private PinRow mPinRow;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (CommonChangePinActivity) activity;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layout;
        switch (mActivity.getMode()) {
            case ACTIVATE:
                layout = R.layout.fragment_transport_pin;
                break;
            case CHANGE:
                layout = R.layout.fragment_current_pin;
                break;
            case UNLOCK:
                layout = R.layout.fragment_puk;
                break;
            default:
                throw new IllegalStateException();
        }

        View view = inflater.inflate(layout, container, false);

        mPinRow = (PinRow) view.findViewById(R.id.pinRow_current_pin);

        mPinRow.setLastImeOption(EditorInfo.IME_ACTION_NEXT, true);

        return view;
    }

    @Override
    public boolean isInputComplete() {
        return mPinRow != null && mPinRow.isComplete();
    }

    @Override
    public byte[] getApprovedPin() {
        if (isInputComplete()) {
            return mPinRow.getPin();
        } else {
            return null;
        }
    }
}
