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
package de.persoapp.android.core.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;

import net.vrallev.android.base.BaseActivitySupport;
import net.vrallev.android.base.util.Cat;
import net.vrallev.android.base.util.IoUtils;

import java.io.IOException;

import javax.smartcardio.CardException;

import de.greenrobot.event.EventBus;
import de.persoapp.core.card.CCID;
import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.card.TransportProvider;
import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;

/**
 * This transport provider implements nfc specific functions. The
 * implemented functions are necessary to communicate with an nPA through
 * the nfc module.
 * 
 * @author Ralf Wondratschek
 * @author Rico Klimsa - Added javadoc comments.
 */
public class NfcTransportProvider implements TransportProvider, CCID {

    private final NfcAdapter mNfcAdapter;
    private final EventBus mEventBus;

    private IsoDep mIsoDep = null;
    private int mLastSW = 0;

    private TranceiveErrorCallback mCallback;

    public NfcTransportProvider(NfcManager nfcManager, EventBus eventBus) {
        mNfcAdapter = nfcManager.getDefaultAdapter();
        mEventBus = eventBus;
    }

    @SuppressWarnings("ConstantConditions")
    public void enableForegroundDispatch(BaseActivitySupport activity) {
        if (mNfcAdapter != null) {

            final Context applicationContext = activity.getApplicationContext();

            final Intent intent = new Intent(applicationContext, activity.getClass());
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            final PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0);

            final IntentFilter[] intentFilters = new IntentFilter[1];
            intentFilters[0] = new IntentFilter();
            intentFilters[0].addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
            intentFilters[0].addCategory(Intent.CATEGORY_DEFAULT);

            mNfcAdapter.enableForegroundDispatch(activity, pendingIntent, intentFilters, new String[][]{new String[]{IsoDep.class.getName()}});

            Cat.d("Foreground dispatch enabled");
        }
    }

    public void disableForegroundDispatch(final Activity targetActivity) {
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            mNfcAdapter.disableForegroundDispatch(targetActivity);
            Cat.d("Foreground dispatch disabled");
        }
    }

    public void setTranceiveErrorCallback(TranceiveErrorCallback callback) {
        mCallback = callback;
    }

    @SuppressWarnings("ConstantConditions")
    public void handleIntent(final Intent intent) {
        final String action = intent.getAction();
        if (!NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            return;
        }


        final Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tag == null) {
            return;
        }

        mIsoDep = null;

        for (final String tech : tag.getTechList()) {
            if (tech.equals(IsoDep.class.getName())) {
                mIsoDep = IsoDep.get(tag);
                break;
            }
        }

        if (mIsoDep != null) {
            final byte[] hb = mIsoDep.getHistoricalBytes();
            Cat.d("ISO-tag found: %s ID: %s HB: ", mIsoDep, Hex.toString(tag.getId()), hb != null ? Hex.toString(hb) : "null");
            Cat.d("extended length capable? %b", mIsoDep.isExtendedLengthApduSupported());
            Cat.d("Maximum tranceive length: %d", mIsoDep.getMaxTransceiveLength());

            try {
                if (!mIsoDep.isConnected()) {
                    mIsoDep.connect();
                }

            } catch (final IOException e) {
                try {
                    mIsoDep.close();
                } catch (final IOException e1) {
                    Cat.e(e1);
                }

                mIsoDep = null;
                Cat.e(e);
            }

        } else {
            Cat.d("Not an ISO-Tag");
        }

        // mIsoDep may be null, but that's fine
        mEventBus.post(new NfcConnectedEvent(mIsoDep));
    }

    private IsoDep getIsoDep() {
        boolean extendedLengthSupported = true;
        if (mIsoDep != null) {
            extendedLengthSupported = mIsoDep.isExtendedLengthApduSupported();
        }

        if (mIsoDep != null && extendedLengthSupported && !mIsoDep.isConnected()) {
            try {
                mIsoDep.connect();
            } catch (final IOException e) {
                mIsoDep = null;
            }
        }

        return mIsoDep;
    }

    @Override
    public Object getParent() {
        return null;
    }

    public boolean testExtendedLength() {
        boolean capable = false;

        try {
            byte[] transmit = transmit(Hex.fromString("00a4040c00" + Hex.shortToString(ICardHandler.AID_NPA.length() / 2) + ICardHandler.AID_NPA));
            if (transmit != null && (mLastSW == 0x9000 || mLastSW == 0x6982)) {
                byte[] buffer = new byte[400];
                transmit = transmit(Hex.fromString("00a4040c00" + Hex.shortToString(buffer.length) + Hex.toString(buffer)));
                capable = transmit != null && (mLastSW == 0x6a82 || mLastSW == 0x6a87 || mLastSW == 0x6700);
            }
        } catch (Exception e) {
            Cat.e(e);
        }

        return capable;
    }


    @Override
    public byte[] transmit(final byte[] apdu) {
        try {
            mLastSW = -1;

            final IsoDep iso = getIsoDep();
            if (iso != null) {
                byte[] rpdu = iso.transceive(apdu);

                if (rpdu != null && rpdu.length >= 2) {
                    mLastSW = ((rpdu[rpdu.length - 2] & 0xFF) << 8) + (rpdu[rpdu.length - 1] & 0xFF);
                    rpdu = ArrayTool.subArray(rpdu, 0, rpdu.length - 2);
                }
                return rpdu;
            }
        } catch (final IOException e) {
            if (mCallback != null && mCallback.shouldRepeatTranceive(apdu, e)) {
                return transmit(apdu);
            }
        }

        return null;
    }

    @Override
    public int lastSW() {
        return mLastSW;
    }

    @Override
    public void close() {
        IoUtils.closeQuietly(mIsoDep);
        mIsoDep = null;
    }

    @Override
    public String getName() {
        return "AGETO NFC Transport";
    }

    @Override
    public boolean hasFeature(final byte feature) {
        return false;
    }

    @Override
    public byte[] verifyPinDirect(final byte[] PIN_VERIFY) throws CardException {
        return null;
    }

    @Override
    public byte[] modifyPinDirect(final byte[] PIN_MODIFY) throws CardException {
        return null;
    }

    @Override
    public byte[] transmitControlCommand(final byte feature, final byte[] ctrlCommand) {
        return null;
    }

    @SuppressWarnings("UnusedDeclaration")
    public static class NfcConnectedEvent {

        private final IsoDep mIsoDep;

        private NfcConnectedEvent(IsoDep isoDep) {
            mIsoDep = isoDep;
        }

        public IsoDep getIsoDep() {
            return mIsoDep;
        }

        public boolean isConnected() {
            return mIsoDep != null;
        }
    }

    public static interface TranceiveErrorCallback {
        public boolean shouldRepeatTranceive(byte[] apdu, Exception e);
    }
}
