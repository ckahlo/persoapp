package de.persoapp.android.core.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.provider.Settings;

import net.vrallev.android.base.util.Cat;
import net.vrallev.android.base.util.IoUtils;

import java.io.IOException;

import javax.smartcardio.CardException;

import de.greenrobot.event.EventBus;
import de.persoapp.core.card.CCID;
import de.persoapp.core.card.TransportProvider;
import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;

/**
 * @author Ralf Wondratschek
 *
 * TODO: clean comments
 *
 */
public class NfcTransportProvider implements TransportProvider, CCID {

    private final NfcAdapter mNfcAdapter;
    private final EventBus mEventBus;

    private IsoDep mIsoDep = null;
    private int mLastSW = 0;

    public NfcTransportProvider(NfcManager nfcManager, EventBus eventBus) {
        mNfcAdapter = nfcManager.getDefaultAdapter();
        mEventBus = eventBus;
    }

//    public static NfcTransport getInstance() {
//        return instance;
//    }

    private void ensureSensorIsOn(final Activity activity) {
        if (mNfcAdapter != null && !mNfcAdapter.isEnabled()) {
            new AlertDialog.Builder(activity)
                    .setTitle("NFC abgeschaltet")
                    .setMessage("NFC einschalten")
                    .setPositiveButton("Einstellungen", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            activity.startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
                        }
                    }).setNegativeButton(android.R.string.cancel, null)
                    .show();
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void enableForegroundDispatch(Activity activity) {
        if (mNfcAdapter != null) {
            Cat.d("NFC Found");

            ensureSensorIsOn(activity);

            final Context applicationContext = activity.getApplicationContext();

            final Intent intent = new Intent(applicationContext, activity.getClass());
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            final PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0);

            final IntentFilter[] intentFilters = new IntentFilter[1];
            intentFilters[0] = new IntentFilter();
            intentFilters[0].addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
            intentFilters[0].addCategory(Intent.CATEGORY_DEFAULT);

            mNfcAdapter.enableForegroundDispatch(activity, pendingIntent, intentFilters, new String[][] { new String[] { IsoDep.class.getName() } });
        }
    }

    public void disableForegroundDispatch(final Activity targetActivity) {
        if (mNfcAdapter != null && mNfcAdapter.isEnabled()) {
            mNfcAdapter.disableForegroundDispatch(targetActivity);
        }
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

//            System.out.println("ISO-tag found: " + mIsoDep + " ID: " + Hex.toString(tag.getId()), (hb != null ? Hex.toString(hb) : "null"));

//            System.out.println("extended length capable? " + miso.isExtendedLengthApduSupported());
//            System.out.println("Maximum transceive length: " + iso.getMaxTransceiveLength());

            // 10 seconds time-out for tests
            //	iso.setTimeout(10000);

            try {
                if (!mIsoDep.isConnected()) {
                    mIsoDep.connect();
                }

                mEventBus.post(new NfcConnectedEvent(mIsoDep));

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
//            System.out.println("Not an ISO-Tag");
            Cat.d("Not an ISO-Tag");
        }

        //		final NotificationManager notifMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //
        //		final Notification notif = new Notification();
        //		notif.ledARGB = Color.argb(255, 0, 0, 255);
        //		notif.flags |= Notification.FLAG_SHOW_LIGHTS;
        //		notif.ledOnMS = 200;
        //		notif.ledOffMS = 300;
        //
        //		notifMgr.notify(1234, notif);

//		QuestionDialog.dismissLastInstance();

    }

    private IsoDep getIsoDep() {
        // TODO: add basic check at the start of the app
        boolean extendedLengthSupported = true;
        if (mIsoDep != null) {
            extendedLengthSupported = mIsoDep.isExtendedLengthApduSupported();
        }

        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        // if (iso != null) {
        // extendedLengthSupported = iso.isExtendedLengthApduSupported();
        // }
        // }

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

//	@Override
//	public byte[] transmit(final String apdu) {
//		return transmit(Hex.fromString(apdu));
//	}

    @Override
    public byte[] transmit(final byte[] apdu) {
        try {
            mLastSW = -1;

            final IsoDep iso = getIsoDep();
            if (iso != null) {
                // System.out.println("<NFC: " + Hex.toString(apdu));

                // TODO: catch Exceptions here / Transceive failed or Transceive length exceeds supported maximum
                byte[] rpdu = iso.transceive(apdu);

                // System.out.println(">NFC: " + Hex.toString(rpdu));

                if (rpdu != null && rpdu.length >= 2) {
                    mLastSW = ((rpdu[rpdu.length - 2] & 0xFF) << 8) + (rpdu[rpdu.length - 1] & 0xFF);
                    rpdu = ArrayTool.subArray(rpdu, 0, rpdu.length - 2);
                }
                return rpdu;
            }
        } catch (final IOException e) {
            Cat.e(e);
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

    public static class NfcConnectedEvent {

        private final IsoDep mIsoDep;

        public NfcConnectedEvent(IsoDep isoDep) {
            mIsoDep = isoDep;
        }

        public IsoDep getIsoDep() {
            return mIsoDep;
        }
    }
}
