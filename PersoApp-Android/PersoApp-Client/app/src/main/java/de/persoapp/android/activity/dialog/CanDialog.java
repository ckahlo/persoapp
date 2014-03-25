package de.persoapp.android.activity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import javax.inject.Inject;

import de.persoapp.android.R;
import de.persoapp.android.view.PinRow;
import de.persoapp.core.client.SecureHolder;

/**
 * @author Ralf Wondratschek
 */
public class CanDialog extends AbstractGetResultDialog<SecureHolder> {

    @Inject
    InputMethodManager mInputMethodManager;

    @SuppressWarnings("ConstantConditions")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        setCancelable(false);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_can, null);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(getArguments().getString(MESSAGE));

        final PinRow pinRow = (PinRow) view.findViewById(R.id.pinRow);
        pinRow.getEditText(0).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setOnFocusChangeListener(null);
                    getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        postResult(new SecureHolder(pinRow.getPin()));
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        postResult(new SecureHolder(null));
                        break;
                }
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher)
                .setTitle(getArguments().getString(TITLE))
                .setPositiveButton(android.R.string.yes, onClickListener)
                .setNegativeButton(android.R.string.no, onClickListener)
                .setView(view)
                .create();
    }

    @Override
    public void onResume() {
        super.onResume();
        mEventBus.register(this);
    }

    @Override
    public void onPause() {
        mEventBus.unregister(this);
        super.onPause();
    }

    public void onEvent(PinRow.InputEvent event) {
        if (PinRow.InputEvent.FINISHED.equals(event)) {
            View focusedView = getDialog().getCurrentFocus();
            if (focusedView != null) {
                mInputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
            }
        }
    }
}
