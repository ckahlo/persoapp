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
package de.persoapp.android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import de.persoapp.android.R;

/**
 * This class manages the possible pin operations related to the npa.
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class PinOptionsActivity extends AbstractNfcActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_options);

        AbsListView adapterView = (AbsListView) findViewById(R.id.adapterView);
        adapterView.setAdapter(mAdapter);
        adapterView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommonChangePinActivity.Mode mode;
                switch (position) {
                    case 0:
                        mode = CommonChangePinActivity.Mode.ACTIVATE;
                        break;
                    case 1:
                        mode = CommonChangePinActivity.Mode.CHANGE;
                        break;
                    case 2:
                        mode = CommonChangePinActivity.Mode.UNLOCK;
                        break;
                    default:
                        throw new IllegalStateException();
                }

                startActivityForResult(CommonChangePinActivity.createIntent(PinOptionsActivity.this, mode), CommonChangePinActivity.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CommonChangePinActivity.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private BaseAdapter mAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public String getItem(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.activate_npa);
                case 1:
                    return getString(R.string.change_pin);
                case 2:
                    return getString(R.string.unlock_pin);
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.pin_options_list_item, parent, false);
            }

            TextView label = (TextView) convertView.findViewById(R.id.textView);
            label.setText(getItem(position));

            return convertView;
        }
    };
}
