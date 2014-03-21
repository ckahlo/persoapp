package de.persoapp.android.activity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
public class QuestionDialog extends AbstractGetResultDialog<Boolean> {

    @SuppressWarnings("ConstantConditions")
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        this.setCancelable(false);

        final DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                boolean result;

                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        result = true;
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        result = false;
                        break;
                    default:
                        result = false;
                        break;

                }

                postResult(result);
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher)
                .setTitle(getArguments().getString(TITLE))
                .setMessage(getArguments().getString(MESSAGE))
                .setPositiveButton(android.R.string.yes, onClickListener)
                .setNegativeButton(android.R.string.no, onClickListener)
                .create();
    }
}
