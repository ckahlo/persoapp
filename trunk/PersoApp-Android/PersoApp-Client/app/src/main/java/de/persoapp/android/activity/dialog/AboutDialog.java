package de.persoapp.android.activity.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import de.persoapp.android.BuildConfig;
import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
public class AboutDialog extends DialogFragment {

    @SuppressWarnings("ConstantConditions")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        setCancelable(true);

        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getActivity(), "Licenses", Toast.LENGTH_SHORT).show();
                        break;

//                    case DialogInterface.BUTTON_NEUTRAL:
////                        startActivity(new Intent(getActivity(), LicenseActivity.class));
//                        // TODO:
//
//                        break;
                }
            }
        };

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_about, null);
        TextView versionTextView = (TextView) view.findViewById(R.id.textView_version);
        versionTextView.setText(getString(R.string.version_string, BuildConfig.VERSION_NAME));

        TextView homepageTextView = (TextView) view.findViewById(R.id.textview_homepage);
        homepageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.persoapp.de")));
            }
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.ic_launcher)
                .setView(view)
                .setNegativeButton(R.string.close, onClickListener)
                .setPositiveButton(R.string.licenses, onClickListener)
                .create();
    }
}
