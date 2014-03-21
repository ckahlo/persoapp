package de.persoapp.android.view;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.vrallev.android.base.BaseActivitySupport;

import de.persoapp.android.R;
import de.persoapp.android.activity.dialog.AboutDialog;

/**
 * @author Ralf Wondratschek
 */
public class MenuHelper {

    private final BaseActivitySupport mActivity;

    public MenuHelper(BaseActivitySupport activity) {
        mActivity = activity;
    }

    public void onCreateOptionsMenu(Menu menu) {
        mActivity.getMenuInflater().inflate(R.menu.basic, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(mActivity, "TODO: settings", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_help:
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.persoapp.de/")));
                return true;

            case R.id.action_about:
                mActivity.showDialog(new AboutDialog(), AboutDialog.class.getName());
                return true;

            default:
                return false;
        }
    }
}
