package de.persoapp.lib.paos;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import de.persoapp.core.paos.PAOSInitiator;
import de.persoapp.core.paos.PAOSInitiatorFactory;
import de.persoapp.core.ws.engine.WSContainer;

/**
 * @author Ralf Wondratschek
 */
public class PAOSInitiatorExtension extends PAOSInitiator {

    protected Map<String, String> mNsMap;

    public static void setup() {
        PAOSInitiator.setPaosInitiatorFactory(new PAOSInitiatorFactory() {
            @Override
            public PAOSInitiator createPAOSInitiator(WSContainer wsCtx, URI endpoint, String sessionID, byte[] pskKey) throws IOException {
                try {
                    return new PAOSInitiatorExtension(wsCtx, endpoint, sessionID, pskKey);
                } catch (JAXBException e) {
                    throw new IOException(e);
                }
            }

            @Override
            public PAOSInitiator createPreStartPAOSInitiator() {
                try {
                    return new PAOSInitiatorExtension();
                } catch (JAXBException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

    protected PAOSInitiatorExtension() throws JAXBException {

        marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper",
                new de.persoapp.lib.jaxb.NamespacePrefixMapper() {
                    //new com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper() {
                    @Override
                    public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
                        String result = mNsMap.get(namespaceUri);
                        if (result != null) {
                            return result;
                        }
                        return suggestion;
                    }
                });
    }

    public PAOSInitiatorExtension(WSContainer wsCtx, URI endpoint, String sessionID, byte[] pskKey) throws IOException, JAXBException {
        super(wsCtx, endpoint, sessionID, pskKey);
    }

    @Override
    protected JAXBContext createJAXBContext() {
        mNsMap = new HashMap<String, String>();

        mNsMap.put("urn:iso:std:iso-iec:24727:tech:schema", "iso");
        mNsMap.put("urn:oasis:names:tc:dss:1.0:core:schema", "dss");
        mNsMap.put("urn:liberty:paos:2006-08", "paos"); // "P"); // "paos");
        mNsMap.put("http://schemas.xmlsoap.org/soap/envelope/", "soap"); // "S"); // "soap");
        mNsMap.put("http://www.w3.org/2005/03/addressing", "wsa"); // "A"); // "wsa");
        mNsMap.put("http://www.w3.org/2000/09/xmldsig#", "dsig");
        mNsMap.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        mNsMap.put("http://www.bsi.bund.de/ecard/api/1.1", "ecapi");

//        Class<?>[] objectFactories = new Class<?>[]{
//                org.xmlsoap.schemas.soap.envelope.ObjectFactory.class,    // SOAP Envelope
//                liberty.paos._2006_08.ObjectFactory.class,                // PAOS Header
//                org.w3._2005._03.addressing.ObjectFactory.class,        // SOAP Addressing Header
//                iso.std.iso_iec._24727.tech.schema.ObjectFactory.class    // ISO-24727 messages
//        };

        try {
            // return net.ageto.lib.eid_jxb.ContextImpl.createContext(objectFactories, null);

            // use lazy binding
            return de.persoapp.lib.jaxb.ContextImpl.createContext(null, null);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
