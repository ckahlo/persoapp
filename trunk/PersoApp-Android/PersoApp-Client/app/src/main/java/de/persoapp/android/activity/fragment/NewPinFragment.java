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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import net.vrallev.android.base.util.Cat;

import javax.inject.Inject;

import de.persoapp.android.R;
import de.persoapp.android.activity.CommonChangePinActivity;
import de.persoapp.android.view.EditTextFalse;
import de.persoapp.android.view.PinRow;

/**
 * This class provides the logic and the view for the user to
 * insert a new pin.
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class NewPinFragment extends PinFragment {

    private CommonChangePinActivity mActivity;

    private PinRow mPinRow;
    private PinRow mPinRowConfirm;

    @Inject
    InputMethodManager mInputMethodManager;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (CommonChangePinActivity) activity;
        mActivity.inject(this);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_pin, container, false);

        mPinRow = (PinRow) view.findViewById(R.id.pinRow_new_pin);
        mPinRowConfirm = (PinRow) view.findViewById(R.id.pinRow_new_pin_confirm);

        mPinRowConfirm.increaseIds();

        mPinRow.setLastImeOption(EditorInfo.IME_ACTION_NEXT);

        TextView textView = (TextView) view.findViewById(R.id.textView_new_pin_description);

        switch (mActivity.getMode()) {
            case ACTIVATE:
                textView.setText(R.string.new_pin_activate_description);
                break;

            case CHANGE:
            case UNLOCK:
                textView.setText(R.string.new_pin_pin_change_description);
                break;
        }

        return view;
    }

    @Override
    public boolean isInputComplete() {
        return mPinRow != null && mPinRowConfirm != null && !hasFalseField() && mPinRow.isComplete() && mPinRowConfirm.isComplete();
    }

    @Override
    public byte[] getApprovedPin() {
        if (!isInputComplete()) {
            return null;
        } else {
            return mPinRow.getPin();
        }
    }

    @SuppressWarnings({"ConstantConditions", "UnusedDeclaration"})
    public void onEvent(PinRow.InputEvent event) {
        switch (event) {
            case FINISHED:
                onInputFinishedEvent();
                break;
        }
    }

    private void onInputFinishedEvent() {
        if (mActivity == null) {
            Cat.w("Activity is null.");
            return;
        }

        View focusedView = mActivity.getCurrentFocus();
        if (focusedView == null) {
            Cat.w("Focused view is null.");
            return;
        }

        if (mPinRow.contains(focusedView)) {
            mPinRowConfirm.requestFocus();
        } else if (mPinRowConfirm.contains(focusedView)) {
            mInputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        } else {
            mPinRow.requestFocus();
        }
    }

    private boolean hasFalseField() {
        boolean result = false;

        for (int i = mPinRow.getFieldCount() - 1; i >= 0; i--) {
            EditTextFalse editText = mPinRow.getEditText(i);
            EditTextFalse editTextConfirm = mPinRowConfirm.getEditText(i);

            if (!TextUtils.isEmpty(editText.getText()) && !TextUtils.isEmpty(editTextConfirm.getText())) {
                boolean isFalse = !TextUtils.equals(editText.getText(), editTextConfirm.getText());
                editTextConfirm.setFalse(isFalse);
                result = result || isFalse;
            } else {
                editTextConfirm.setFalse(false);
            }
        }

        return result;
    }
}
