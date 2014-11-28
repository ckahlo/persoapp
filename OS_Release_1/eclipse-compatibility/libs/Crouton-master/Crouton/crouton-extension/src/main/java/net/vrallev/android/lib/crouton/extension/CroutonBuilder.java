package net.vrallev.android.lib.crouton.extension;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Ralf Wondratschek
 */
@SuppressWarnings("UnusedDeclaration")
public class CroutonBuilder {

    private final Activity mActivity;

    private String mMessage;
    private String mTitle;

    private int mColor;
    private Drawable mDrawable;

    private boolean mHideOnClick;
    private boolean mDisplayConfirmButton;
    private int mDuration;

    private int mContainer;

    public CroutonBuilder(Activity activity) {
        mActivity = activity;

        mColor = Style.holoBlueLight;
        mDuration = Configuration.DURATION_SHORT;
        mContainer = -1;
    }

    public CroutonBuilder setMessage(int messageId) {
        return setMessage(mActivity.getString(messageId));
    }

    public CroutonBuilder setMessage(String message) {
        mMessage = message;
        return this;
    }

    public CroutonBuilder setTitle(int titleId) {
        return setTitle(mActivity.getString(titleId));
    }

    public CroutonBuilder setTitle(String title) {
        mTitle = title;
        return this;
    }

    public CroutonBuilder setColor(int color) {
        mColor = color;
        return this;
    }

    public CroutonBuilder setDrawable(int drawableId) {
        return setDrawable(mActivity.getResources().getDrawable(drawableId));
    }

    public CroutonBuilder setDrawable(Drawable drawable) {
        mDrawable = drawable;
        return this;
    }

    public CroutonBuilder setHideOnClick(boolean hideOnClick) {
        mHideOnClick = hideOnClick;
        return this;
    }

    public CroutonBuilder setDisplayConfirmButton(boolean displayConfirmButton) {
        mDisplayConfirmButton = displayConfirmButton;
        return this;
    }

    public CroutonBuilder setDuration(int duration) {
        mDuration = duration;
        return this;
    }

    public CroutonBuilder setContainer(int containerId) {
        mContainer = containerId;
        return this;
    }

    public Crouton show() {
        View container = mActivity.getLayoutInflater().inflate(R.layout.crouton_custom_view, null);
        if (container == null) {
            return null;
        }
        container.setBackgroundColor(mColor);

        ImageView imageView = (ImageView) container.findViewById(R.id.imageView_crouton);
        if (mDrawable == null) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setImageDrawable(mDrawable);
            imageView.setContentDescription(mTitle == null ? mMessage : mTitle);
        }

        TextView titleView = (TextView) container.findViewById(R.id.textView_crouton_title);
        TextView messageView = (TextView) container.findViewById(R.id.textView_crouton_message);

        if (mTitle == null) {
            titleView.setVisibility(View.GONE);
        } else {
            titleView.setText(mTitle);
        }
        if (mMessage == null) {
            messageView.setVisibility(View.GONE);
        } else {
            messageView.setText(mMessage);
        }


        Configuration config = new Configuration.Builder().setDuration(mDuration).build();
        final Crouton crouton = Crouton.make(mActivity, container, mContainer, config);

        View.OnClickListener hideOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crouton.hide(crouton);
            }
        };

        if (mHideOnClick) {
            container.setOnClickListener(hideOnClickListener);
        }

        ImageView accept = (ImageView) container.findViewById(R.id.imageView_crouton_accept);
        if (mDisplayConfirmButton) {
            accept.setOnClickListener(hideOnClickListener);
        } else {
            accept.setVisibility(View.GONE);
        }

        crouton.show();
        return crouton;
    }

    public static void showError(Activity activity, String title, String message) {
        new CroutonBuilder(activity)
                .setColor(Style.holoRedLight)
                .setDuration(Configuration.DURATION_LONG)
                .setHideOnClick(true)
                .setDrawable(R.drawable.ic_action_warning)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    public static void showError(Activity activity, String title) {
        showError(activity, title, null);
    }

    public static void showError(Activity activity, int title, int message) {
        showError(activity, title >= 0 ? activity.getString(title) : null, message >= 0 ? activity.getString(message) : null);
    }

    public static void showError(Activity activity, int title) {
        showError(activity, title, -1);
    }
}
