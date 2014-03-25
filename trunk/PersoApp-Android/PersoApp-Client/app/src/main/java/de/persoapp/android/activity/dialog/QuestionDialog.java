package de.persoapp.android.activity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import de.persoapp.android.R;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import de.persoapp.core.client.PropertyResolver;

/**
 * @author Ralf Wondratschek
 */
public class QuestionDialog extends AbstractGetResultDialog<Boolean> {

    // this is a dirty hack, but requested
    private static final String MAGIC_QUESTION = PropertyResolver.getString("text_core", "SALService_insert_card");

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

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher)
                .setTitle(getArguments().getString(TITLE))
                .setMessage(getArguments().getString(MESSAGE))
                .setNegativeButton(android.R.string.no, onClickListener);

        if (!MAGIC_QUESTION.equals(getArguments().getString(MESSAGE))) {
            builder.setPositiveButton(android.R.string.yes, onClickListener);
        }

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (MAGIC_QUESTION.equals(getArguments().getString(MESSAGE))) {
            mEventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        if (MAGIC_QUESTION.equals(getArguments().getString(MESSAGE))) {
            mEventBus.unregister(this);
        }
        super.onStop();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(NfcTransportProvider.NfcConnectedEvent event) {
        postResult(true);
        dismiss();
    }
}
