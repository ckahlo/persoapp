package de.persoapp.android.activity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import net.vrallev.android.base.BaseActivitySupport;

import de.persoapp.android.R;
import de.persoapp.android.core.adapter.NfcTransportProvider;
import de.persoapp.core.client.PropertyResolver;

/**
 * @author Ralf Wondratschek
 */
public class QuestionDialog extends AbstractGetResultDialog<Boolean> {

    protected static final String MAGIC_QUESTION_KEY = "magicQuestion";

    // this is a dirty hack, but requested
    private static final String MAGIC_QUESTION = PropertyResolver.getString("text_core", "SALService_insert_card");
    private static final String MAGIC_QUESTION_2 = PropertyResolver.getString("text_core", "eCardService_device_and_card");

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

        if (!isMagicQuestion()) {
            builder.setPositiveButton(android.R.string.yes, onClickListener);
        }

        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isMagicQuestion()) {
            mEventBus.register(this);
        }
    }

    @Override
    public void onStop() {
        if (isMagicQuestion()) {
            mEventBus.unregister(this);
        }
        super.onStop();
    }

    protected boolean isMagicQuestion() {
        return getArguments().getBoolean(MAGIC_QUESTION_KEY, false)
                || MAGIC_QUESTION.equals(getArguments().getString(MESSAGE))
                || MAGIC_QUESTION_2.equals(getArguments().getString(MESSAGE));
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(NfcTransportProvider.NfcConnectedEvent event) {
        postResult(true);
        dismiss();
    }

    public Boolean askForResult(BaseActivitySupport activity, int titleId, int messageId, boolean magicQuestion) {
        Bundle args = new Bundle();
        args.putString(TITLE, activity.getString(titleId));
        args.putString(MESSAGE, activity.getString(messageId));
        args.putBoolean(MAGIC_QUESTION_KEY, magicQuestion);


        return super.askForResult(activity, args);
    }
}
