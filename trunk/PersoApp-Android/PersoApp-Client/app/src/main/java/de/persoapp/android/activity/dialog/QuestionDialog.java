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
