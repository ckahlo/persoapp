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
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import net.vrallev.android.base.util.Cat;

import java.lang.reflect.Field;

import de.persoapp.android.BuildConfig;
import de.persoapp.android.R;
import de.persoapp.android.activity.LicenseActivity;

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
                        startActivity(new Intent(getActivity(), LicenseActivity.class));
                        break;
                }
            }
        };

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_about, null);

        TextView versionTextView = (TextView) view.findViewById(R.id.textView_version);
        versionTextView.setText(getString(R.string.version_string, getVersionName()));

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
                .setNegativeButton(R.string.close, null)
                .setPositiveButton(R.string.licenses, onClickListener)
                .create();
    }

    @SuppressWarnings("ConstantConditions")
    private String getVersionName() {
        try {
            Field field = BuildConfig.class.getField("VERSION_NAME");
            return (String) field.get(null);

        } catch (NoSuchFieldException | IllegalAccessException ignore) {
            // eclipse...

            try {
                PackageInfo packageInfo = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
                return packageInfo.versionName;
            } catch (Exception e) {
                Cat.e(e);
                return "";
            }
        }
    }
}
