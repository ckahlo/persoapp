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
