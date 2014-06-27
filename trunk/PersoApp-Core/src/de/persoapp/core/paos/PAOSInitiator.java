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
package de.persoapp.core.paos;

import org.w3._2005._03.addressing.AttributedURIType;
import org.w3._2005._03.addressing.RelatesToType;
import org.w3c.dom.Element;
import org.xmlsoap.schemas.soap.envelope.Body;
import org.xmlsoap.schemas.soap.envelope.Envelope;
import org.xmlsoap.schemas.soap.envelope.Header;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.Random;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

import de.persoapp.core.tls.BCTlsSocketFactoryImpl;
import de.persoapp.core.util.Hex;
import de.persoapp.core.ws.engine.WSContainer;
import iso.std.iso_iec._24727.tech.schema.ConnectionHandleType;
import iso.std.iso_iec._24727.tech.schema.RequestType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;
import iso.std.iso_iec._24727.tech.schema.StartPAOS;
import iso.std.iso_iec._24727.tech.schema.StartPAOS.SupportedAPIVersions;
import liberty.paos._2006_08.EndpointReferenceType;
import liberty.paos._2006_08.MetaDataType;
import liberty.paos._2006_08.OptionsType;
import liberty.paos._2006_08.PaosType;

//@TODO: check for server hello version and don't allow server version
//lower than a specified minimum level ... either here or in app-code

/**
 * This class initiates the <tt>PAOS</tt>-protocol which is used to communicate
 * with <tt>PAOS</tt>-webservices. PAOS is another name for the implementation
 * of the Liberty Reverse HTTP Binding for SOAP Specification. The use of
 * <tt>PAOS</tt> makes possible the exchange of information between user agent
 * hosted services and remote servers.
 * <p>
 * In a typical forward SOAP binding, an HTTP client exposes a service via a
 * client request and a server response. For example, a cell phone user (the
 * client) may contact his phone service provider (the service) in order to
 * retrieve stocks quotes and weather information. The service verifies the
 * user’s identity, and responds with the requested information.
 * </p>
 * <p>
 * In a reverse HTTP SOAP binding, the Service Provider server plays the client
 * role, and the client plays the server role. Technically, the initial SOAP
 * request from the server is bound to a server HTTP response. Then the
 * subsequent response is bound to a client request. This is why Reversed HTTP
 * Binding for SOAP is also known as <tt>PAOS</tt> (or “SOAP” spelled
 * backwards).
 * </p>
 * <p>
 * <code>public class PAOSInitiator</code>
 * </p>
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class PAOSInitiator {
	
	/**
	 * The <tt>1.1</tt> version of the liberty scheme.
	 */
    public static final String VERSION1_1  = "urn:liberty:2003-08";
  
    /**
     * The <tt>2.0</tt> version of the liberty scheme.
     */
    public static final String VERSION2_0  = "urn:liberty:2006-08";
   
    /**
     * The first role of the <tt>liberty scheme</tt>.
     */
    public static final String ENDPOINT1   = "http://www.projectliberty.org/2006/01/role/paos";
   
    /**
     * The second role of the <tt>liberty scheme</tt>.
     */
    public static final String ENDPOINT2   = "http://www.projectliberty.org/2006/02/role/paos";
    
    /**
     * The servicetype of the <tt>PAOSInitiator</tt>.
     */
    public static final String SERVICETYPE = "http://www.bsi.bund.de/ecard/api/1.0/PAOS/GetNextCommand";
    
    /**
     * The actor of the <tt>PAOSInitiator</tt>.
     */
    public static final String ACTOR 	   = "http://schemas.xmlsoap.org/soap/actor/next";

    /**
     * The used {@link liberty.paos._2006_08.ObjectFactory} for <tt>PAOS</tt> of the {@link PAOSInitiator}.
     */
    private static final liberty.paos._2006_08.ObjectFactory paosOF = new liberty.paos._2006_08.ObjectFactory();
    
    /**
     * The used {@link org.xmlsoap.schemas.soap.envelope.ObjectFactory} for <tt>SOAP</tt> of the {@link PAOSInitiator}.
     */
    private static final org.xmlsoap.schemas.soap.envelope.ObjectFactory soapOF = new org.xmlsoap.schemas.soap.envelope.ObjectFactory();
  
    /**
     * The used {@link org.w3._2005._03.addressing.ObjectFactory} to address webservices correctly. 
     */
    private static final org.w3._2005._03.addressing.ObjectFactory wsaOF = new org.w3._2005._03.addressing.ObjectFactory();

    /**
     * The used message id as a {@link QName}.
     */
    private static final QName wsa_messageID = new QName("http://www.w3.org/2005/03/addressing", "MessageID");

    /**
     * The used {@link PAOSInitiatorFactory}.
     */
    private static PAOSInitiatorFactory paosInitiatorFactory;

    /**
     * Sets a new {@link PAOSInitiatorFactory}.
     * 
     * @param paosInitiatorFactory - The {@link PAOSInitiatorFactory} to set.
     */
    public static void setPaosInitiatorFactory(PAOSInitiatorFactory paosInitiatorFactory) {
        PAOSInitiator.paosInitiatorFactory = paosInitiatorFactory;
    }
    
    /**
     * The {@link JAXBContext}, which is used to convert Strings into JAXB-format.
     */
    protected static JAXBContext jaxbCtx;

    /**
     * The current used {@link WSContainer}.
     */
    protected WSContainer wsCtx;
    
	/**
	 * The current used MiniHttpClient for handling the connection and
	 * dispatching data.
	 */
    protected MiniHttpClient mhc;
    
    /**
     * The service URL of the {@link MiniHttpClient}.
     */
    protected URL serviceURL;
    
    /**
     * The current <tt>session id</tt>.
     */
    protected String sessionID;

    /**
     * The created pre-shared key.
     */
    protected byte[] pskKey;

    /**
     * The id of the last message.
     */
    protected String lastMessageID;
    
    /**
     * Used to provide access to JAXB xml binding data for a JAXB object.
     */
    protected final JAXBIntrospector jaxbIS;

    /**
     * The used {@link Marshaller} to create xml-documents from objects.
     */
    protected final Marshaller marshaller;
    
    /**
     * The used {@link Unmarshaller} to create objects from xml-documents.
     */
    protected final Unmarshaller unmarshaller;

    /**
     * Constructs a new instance of the {@link PAOSInitiator}.
     * 
     * @throws JAXBException Throws a <tt>JAXBException</tt>
     */
    protected PAOSInitiator() throws JAXBException {
        if (jaxbCtx == null) {
            jaxbCtx = createJAXBContext();
        }

        jaxbIS = jaxbCtx.createJAXBIntrospector();
        marshaller = jaxbCtx.createMarshaller();
        unmarshaller = jaxbCtx.createUnmarshaller();

        // omit xml-header
        marshaller.setProperty("com.sun.xml.internal.bind.xmlDeclaration", false);
        // print it user-friendly
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    }

	/**
	 * Constructs a new instance of the {@link PAOSInitiator}.
	 * 
	 * @param wsCtx
	 *            - The webservice context to access the different methods and
	 *            security informations.
	 * @param endpoint
	 *            - The webservice endpoint which contains the different
	 *            methods.
	 * @param sessionID
	 *            - The currently session id.
	 * @param pskKey
	 *            - The currently used pre shared key.
	 * 
	 * @throws IOException
	 *             If a error occurs during the creation process of the
	 *             {@link PAOSInitiator}-object.
	 * @throws JAXBException
	 *             If a error occurs during the <tt>JAXB-format</tt> process.
	 */
    public PAOSInitiator(WSContainer wsCtx, URI endpoint, String sessionID, byte[] pskKey) throws IOException, JAXBException {
        this();

        if(wsCtx == null || endpoint == null) {
            throw new IllegalArgumentException();
        }

        this.wsCtx = wsCtx;
        this.sessionID = sessionID;
        this.pskKey = pskKey;

        if(endpoint.getPath().isEmpty()) {
            endpoint = endpoint.resolve("/").normalize();
        }

        if(sessionID != null) {
            endpoint = endpoint.resolve("?sessionid=" + sessionID);
        }

        this.serviceURL = endpoint.toURL();

        this.mhc = createClient();
        SSLSession s = this.mhc.getSSLSession();
        String p = s == null ? null : s.getProtocol();
        if(p != null && p.startsWith("TLSv") && Float.parseFloat(p.substring(4)) > 1.1d) {
            System.out.println("protocol ok: " + p);
        } else {
            System.out.println("protocol warning: " + p);
        }
    }

    /**
     * Retrieves a so called "pre-start instance" of a {@link PAOSInitiator}.
     * 
     * @return Retrieves the "pre-start instance" of a {@link PAOSInitiator}.
     */
    public static PAOSInitiator getPreStartInstance() {
        if (paosInitiatorFactory == null) {
            paosInitiatorFactory = new PAOSInitiatorFactory();
        }

        return paosInitiatorFactory.createPreStartPAOSInitiator();
    }

    /**
     * This function creates and returns a new {@link JAXBContext}.
     * 
     * @return Returns the newly created {@link JAXBContext}.
     */
    protected JAXBContext createJAXBContext() {
        Class<?>[] objectFactories = new Class<?>[] {
                org.xmlsoap.schemas.soap.envelope.ObjectFactory.class,	// SOAP Envelope
                liberty.paos._2006_08.ObjectFactory.class,				// PAOS Header
                org.w3._2005._03.addressing.ObjectFactory.class,		// SOAP Addressing Header
                iso.std.iso_iec._24727.tech.schema.ObjectFactory.class	// ISO-24727 messages
        };

        try {
            return JAXBContext.newInstance(objectFactories);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves a new instance of the {@link PAOSInitiator}.
     *  
     * @param wsCtx - The used {@link WSContainer}.
     * @param endpoint - The used endpoint.
     * @param sessionID - The used sessionID.
     * @param pskKey - The used pre-shared key.
     * 
     * @return Returns a new instance of the {@link PAOSInitiator}.
     * 
     * @throws IOException Throws {@link IOException} if a error occurs during the creation process of a new instance.
     */
    public static PAOSInitiator getInstance(WSContainer wsCtx, URI endpoint, String sessionID, byte[] pskKey) throws IOException {
        if (paosInitiatorFactory == null) {
            paosInitiatorFactory = new PAOSInitiatorFactory();
        }

        return paosInitiatorFactory.createPAOSInitiator(wsCtx, endpoint, sessionID, pskKey);
    }

	/**
	 * Creates a new {@link MiniHttpClient}.
	 * 
	 * @return Returns the created {@link MiniHttpClient}.
	 * 
	 * @throws IOException
	 *             If a error occurs during the creation process.
	 */
    private MiniHttpClient createClient() throws IOException {
        final MiniHttpClient mhcInstance = new MiniHttpClient(serviceURL);
        mhcInstance.setSocketFactory(new BCTlsSocketFactoryImpl(sessionID.getBytes(), pskKey));

        mhcInstance.setRequestHeader("Accept", "application/vnd.paos+xml");

		/*
		 * Content-Type has to be "application/vnd.paos+xml" for PAOS messages
		 */
        mhcInstance.setRequestHeader("Content-Type", "application/vnd.paos+xml; charset=UTF-8");

        mhcInstance.addRequestHeader("PAOS", "ver=\"" + VERSION1_1 + "\",\"" + VERSION2_0 + "\"");
        mhcInstance.addRequestHeader("PAOS", SERVICETYPE);

        return mhcInstance;
    }

    /**
     * Retrieves the peer {@link Certificate}.
     * 
     * @return The peer {@link Certificate}.
     */
    public final Certificate getPeerCertificate() {
        try {
            return this.mhc.getSSLSession().getPeerCertificates()[0];
            // return ((HttpsURLConnection) this.uc).getServerCertificates()[0];
        } catch (final SSLPeerUnverifiedException e) {
            return null;
        }
    }

    /**
     * Retrieves the current running {@link SSLSession}.
     * 
     * @return The current running {@link SSLSession}.
     */
    public SSLSession getSSLSession() {
        return this.mhc.getSSLSession();
    }
	
	/*
	 * create SOAP-Envelope
	 */
	/**
	 * Creates the <tt>SOAP</tt>-Envelope with the given parameters.
	 * 
	 * @param messageHeader
	 *            - The given message header.
	 * @param messageID
	 *            - The given message id.
	 * @param relatesTo
	 *            - The given relates to.
	 * @param action
	 *            - The given action for setting into the header.
	 * @param message
	 *            - The message to send.
	 * 
	 * @return The created envelope in <tt>JAXB-Format</tt>.
	 */
    public final JAXBElement<Envelope> createEnvelope(final JAXBElement<PaosType> messageHeader,
                                                      final String messageID, final String relatesTo, final String action, final Object message) {
        final Header header = new Header();
        header.getAny().add(messageHeader);

        final AttributedURIType mid = new AttributedURIType();
        mid.setValue(messageID);
        header.getAny().add(wsaOF.createMessageID(mid));

        final org.w3._2005._03.addressing.EndpointReferenceType replyTo = new org.w3._2005._03.addressing.EndpointReferenceType();
        final AttributedURIType replyToAddress = new AttributedURIType();
        replyToAddress.setValue(ENDPOINT2);
        replyTo.setAddress(replyToAddress);
        header.getAny().add(wsaOF.createReplyTo(replyTo));

        if (relatesTo != null) {
            final RelatesToType relation = new RelatesToType();
            relation.setValue(relatesTo);
            header.getAny().add(wsaOF.createRelatesTo(relation));
        }

        if (action != null) {
            final AttributedURIType actionURI = new AttributedURIType();
            actionURI.setValue(action);
            header.getAny().add(wsaOF.createAction(actionURI));
        }

        final Body body = new Body();
        body.getAny().add(message);

        final Envelope envelope = new Envelope();
        envelope.setHeader(header);
        envelope.setBody(body);

        return soapOF.createEnvelope(envelope);
    }

    /*
     * create PAOS-Header for SOAP-Envelope
     */
    /**
     * Create the PAOS-Header for SOAP-Envelope.
     */
    final Random															random			= new Random();

	/**
	 * Creates a PAOSHeader.
	 * 
	 * @return The PAOSHeader in <tt>JAXB</tt>-Format.
	 */
    public final JAXBElement<PaosType> createPAOSHeader() {
        final PaosType pt = new PaosType();
        pt.setMustUnderstand(true);
        pt.setActor(ACTOR);

        pt.getVersion().add(VERSION2_0);
        pt.getVersion().add(VERSION1_1);

        final EndpointReferenceType endpointRef = new EndpointReferenceType();
        endpointRef.setAddress(ENDPOINT1);

        final MetaDataType metaData = new MetaDataType();
        metaData.setServiceType(SERVICETYPE);
        metaData.setOptions(new OptionsType());
        endpointRef.setMetaData(metaData);

        pt.getEndpointReference().add(endpointRef);

        return paosOF.createPAOS(pt);
    }

    /**
     * Creates a new messageId.
     * 
     * @return The created message id.
     */
    private String createMessageID() {
        final byte[] randomID = new byte[16];
        random.nextBytes(randomID);
        return "urn:uuid" + Hex.toString(randomID);
    }

    /**
	 * Dispatches the given message as <tt>JAXBElement</tt>.
	 * 
	 * @param msg
	 *            - The given message to send.
	 * 
	 * @return The response in JAXB-Format. If no response is received, the
	 *         function returns <strong>null</strong>.
	 * 
	 * @throws IOException
	 *             - If some error occurs during the use of the established
	 *             connection.
	 */
    private JAXBElement<Envelope> jaxbDispatch(final JAXBElement<Envelope> msg) throws IOException {
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.marshal(msg, baos);
            byte[] buf = baos.toByteArray();
            System.out.println("<< " + (buf != null ? buf.length : "-") + ": "
                    + (buf != null ? new String(buf) : "null"));

            buf = mhc.transmit(baos.toByteArray());

            System.out.println(">> " + (buf != null ? buf.length : "-") + ": "
                    + (buf != null ? new String(buf) : "null"));
            if (buf != null) {
                final ByteArrayInputStream bais = new ByteArrayInputStream(buf);
                return unmarshaller.unmarshal(new StreamSource(bais), Envelope.class);
            } else {
                return null;
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
            throw new IOException("Transmit error");
        }
    }

	/**
	 * Dispatches the given object and returns the message name and the message
	 * itself, which was received from the response.
	 * 
	 * @param in
	 *            - The given object.
	 * @return Returns the message name and the message id in a 2 elements sized
	 *         object array.
	 * 
	 * @throws IOException
	 *             If a error occurs during the sending process.
	 */
    public final Object[] dispatch(final Object in) throws IOException {
        final JAXBElement<Envelope> env = jaxbDispatch(createEnvelope(createPAOSHeader(), createMessageID(),
                lastMessageID, null, in));

        if (env == null) {
            return null;
        }

        final Envelope e = env.getValue();
        final Header soapHeader = e.getHeader();
        if (soapHeader != null) {
            for (final Object soapHE : soapHeader.getAny()) {
                final JAXBElement<?> je = (JAXBElement<?>) soapHE;

                if (wsa_messageID.equals(je.getName())) {
                    final AttributedURIType value = (AttributedURIType) je.getValue();
                    lastMessageID = value.getValue();
                }
            }
        }

        QName msgName = null;
        Object message = e.getBody().getAny().size() > 0 ? e.getBody().getAny().get(0) : null;
        if (message != null) {
            if (message instanceof JAXBElement) {
                msgName = ((JAXBElement<?>) message).getName();
                message = ((JAXBElement<?>) message).getValue();
            } else if (message instanceof Element) {
                final Element elem = (Element) message;
                System.out.println("#-> " + elem.getNamespaceURI() + ":" + elem.getNodeName() + " = "
                        + elem.getNodeValue());
                try {
                    message = unmarshaller.unmarshal(elem);
                } catch (final UnmarshalException ue) {
                    try {
                        message = unmarshaller.unmarshal(elem, RequestType.class);
                    } catch (final JAXBException je) {
                        je.printStackTrace();
                    }
                } catch (final JAXBException e1) {
                    e1.printStackTrace();
                }
                System.out.println(message.getClass() + " = " + message);
                @SuppressWarnings("ConstantConditions")
                final JAXBElement<?> je = (JAXBElement<?>) message;
                System.out.println("*-> " + je.getName().getNamespaceURI() + ":" + je.getName().getLocalPart());
                msgName = je.getName();
                message = je.getValue();
            } else {
                msgName = jaxbIS.getElementName(message);
            }
        }

        return new Object[] { msgName, message };
    }

	/**
	 * Starts the {@link PAOSInitiator}.
	 * 
	 * @param contextHandle
	 *            - The used contextHandle.
	 * @param slotHandle
	 *            - The used slotHandle.
	 * 
	 * @return Returns the {@link ResponseType} of the paosMsg or
	 *         <strong>null</strong> if no response is received.
	 * 
	 * @throws IOException
	 *             If something goes wrong with the dispatching process of the
	 *             {@link StartPAOS} object.
	 */
    public final ResponseType start(final byte[] contextHandle, final byte[] slotHandle) throws IOException {
        final StartPAOS sp = new StartPAOS();
        sp.setSessionIdentifier(sessionID);

        final ConnectionHandleType ch = new ConnectionHandleType();
        ch.setContextHandle(contextHandle);
        ch.setSlotHandle(slotHandle);
        sp.getConnectionHandle().add(ch);

        final StartPAOS.UserAgent spua = new StartPAOS.UserAgent();
        spua.setName("PersoApp");
        spua.setVersionMajor(BigInteger.ZERO);
        spua.setVersionMinor(BigInteger.ONE);
        spua.setVersionSubminor(null);
        sp.setUserAgent(spua);

        final StartPAOS.SupportedAPIVersions api113 = new SupportedAPIVersions();
        api113.setMajor(BigInteger.ONE);
        api113.setMinor(BigInteger.ONE);
        api113.setSubminor(BigInteger.valueOf(3));

        sp.getSupportedAPIVersions().add(api113);

        sp.getSupportedDIDProtocols().add(null);

        // ISO24727Protocols.startPAOS(sp);
        Object[] paosMsg = dispatch(sp);
        while (paosMsg != null && paosMsg[1] != null && paosMsg[1] instanceof RequestType) {
            final Object wsres = wsCtx.processRequest((QName) paosMsg[0], paosMsg[1]);
            System.out.println(paosMsg[0] + " = " + paosMsg[1]);

            if (wsres != null) {
                paosMsg = dispatch(wsres);
            } else {
                return null;
            }
        }

        if (paosMsg != null && paosMsg[1] != null) {
            if (paosMsg[1] instanceof ResponseType) {
                return (ResponseType) paosMsg[1];
            } else {
                System.out.println("Unknown object: " + paosMsg[1].getClass() + " = " + paosMsg[1]);
                return null;
            }
        } else {
            return null;
        }
    }
}
