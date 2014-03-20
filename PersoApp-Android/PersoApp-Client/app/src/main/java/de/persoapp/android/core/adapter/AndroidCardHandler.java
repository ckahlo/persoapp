package de.persoapp.android.core.adapter;

import de.persoapp.core.card.CardHandler;
import de.persoapp.core.card.TransportProvider;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;

public class AndroidCardHandler extends CardHandler {

    private final TransportProvider mTransportProvider;

	public AndroidCardHandler(IMainView mainView, TransportProvider transportProvider) {
        super(mainView);
        mTransportProvider = transportProvider;
	}

    @Override
    protected TransportProvider getHALTransport() {
        return openNFC(Hex.fromString(AID_NPA), mTransportProvider);
    }

    private static TransportProvider openNFC(final byte[] AID, TransportProvider provider) {
//        final TransportProvider provider = NfcTransport.getInstance();

        final byte[] selectAID = { (byte) 0x00, (byte) 0xA4, (byte) 0x04, (byte) 0x0C, (byte) AID.length };
        provider.transmit(ArrayTool.arrayconcat(selectAID, AID));

        if (provider.lastSW() == 0x9000 || provider.lastSW() == 0x6982) {
            return provider;
        } else {
            provider.close();
        }

        return null;
    }
}
