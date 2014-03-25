package de.persoapp.android.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

import de.persoapp.android.R;

/**
 * @author Ralf Wondratschek
 */
@SuppressWarnings("UnusedDeclaration")
public class EditTextFalse extends EditText {

    private boolean mFalse;

    private Drawable mCorrectBackground;
    private Drawable mFalseBackground;

    public EditTextFalse(Context context) {
        super(context);
    }

    public EditTextFalse(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextFalse(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean isFalse() {
        return mFalse;
    }

    public void setFalse(boolean value) {
        if (value != mFalse) {
            initBackgroundResources();

            setBackground(value ? mFalseBackground : mCorrectBackground);
            mFalse = value;
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void initBackgroundResources() {
        if (mCorrectBackground == null) {
            mCorrectBackground = getBackground();
            mFalseBackground = getResources().getDrawable(R.drawable.edit_text_background_red);
        }
    }
}
