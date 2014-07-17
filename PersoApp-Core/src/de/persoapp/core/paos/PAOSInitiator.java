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

import iso.std.iso_iec._24727.tech.schema.ConnectionHandleType;
import iso.std.iso_iec._24727.tech.schema.RequestType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;
import iso.std.iso_iec._24727.tech.schema.StartPAOS;
import iso.std.iso_iec._24727.tech.schema.StartPAOS.SupportedAPIVersions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.HashMap;
import java.util.Map;
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

import liberty.paos._2006_08.EndpointReferenceType;
import liberty.paos._2006_08.MetaDataType;
import liberty.paos._2006_08.OptionsType;
import liberty.paos._2006_08.PaosType;

import org.w3._2005._03.addressing.AttributedURIType;
import org.w3._2005._03.addressing.RelatesToType;
import org.w3c.dom.Element;
import org.xmlsoap.schemas.soap.envelope.Body;
import org.xmlsoap.schemas.soap.envelope.Envelope;
import org.xmlsoap.schemas.soap.envelope.Header;

import de.persoapp.core.tls.BCTlsSocketFactoryImpl;
import de.persoapp.core.util.Hex;
import de.persoapp.core.ws.engine.WSContainer;

//@TODO: check for server hello version and don't allow server version
//lower than a specified minimum level ... either here or in app-code

/**
 * PAOS Initiator connects to a remote server and dispatches web-service
 * requests to local services. PAOS is reverse SOAP so that network clients
 * expose services to network servers.
 * http://www.projectliberty.org/liberty/content
 * /download/909/6303/file/liberty-paos-v2.0.pdf
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class PAOSInitiator {

	/**
	 * namespace identifier for PAOS version 1.0
	 */
	public static final String												VERSION1_1		= "urn:liberty:2003-08";

	/**
	 * namespace identifier for PAOS version 2.0
	 */
	public static final String												VERSION2_0		= "urn:liberty:2006-08";

	/**
	 * PAOS endpoint reference address header value
	 */
	public static final String												ENDPOINT1		= "http://www.projectliberty.org/2006/01/role/paos";

	/**
	 * PAOS reply-to address header value (current transport channel only)
	 */
	public static final String												ENDPOINT2		= "http://www.projectliberty.org/2006/02/role/paos";

	/**
	 * service type for endpoint reference
	 */
	public static final String												SERVICETYPE		= "http://www.bsi.bund.de/ecard/api/1.0/PAOS/GetNextCommand";

	/**
	 * actor value indicating the next application in the message path must
	 * process the request
	 */
	public static final String												ACTOR			= "http://schemas.xmlsoap.org/soap/actor/next";

	/*
	 * object factory instances
	 */
	private static final liberty.paos._2006_08.ObjectFactory				paosOF			= new liberty.paos._2006_08.ObjectFactory();
	private static final org.xmlsoap.schemas.soap.envelope.ObjectFactory	soapOF			= new org.xmlsoap.schemas.soap.envelope.ObjectFactory();
	private static final org.w3._2005._03.addressing.ObjectFactory			wsaOF			= new org.w3._2005._03.addressing.ObjectFactory();

	/*
	 * qualified name of message ID element for addressing header
	 */
	private static final QName												wsa_messageID	= new QName(
																									"http://www.w3.org/2005/03/addressing",
																									"MessageID");

	/**
	 * the factory for creation of PAOSInitators if different implementations
	 * exist, i.e. Android
	 */
	private static PAOSInitiatorFactory										paosInitiatorFactory;

	/**
	 * setter for {@link PAOSInitiatorFactory} to inject platform specific
	 * implementation
	 */
	public static void setPaosInitiatorFactory(final PAOSInitiatorFactory paosInitiatorFactory) {
		PAOSInitiator.paosInitiatorFactory = paosInitiatorFactory;
	}

	/**
	 * local JAXB content
	 */
	protected static JAXBContext				jaxbCtx;

	/**
	 * container for local web-services to dispatch PAOS requests
	 */
	protected WSContainer						wsCtx;

	/**
	 * http client instance for network connection
	 */
	protected MiniHttpClient					mhc;

	/**
	 * URL of PAOS remote endpoint
	 */
	protected URL								serviceURL;

	/**
	 * eID session ID (PSK user name)
	 */
	protected String							sessionID;

	/**
	 * pre-shared key for TLS_RSA_PSK connection
	 */
	protected byte[]							pskKey;

	/**
	 * The id of the last message.
	 */
	protected String							lastMessageID;

	/**
	 * Used to provide access to JAXB xml binding data for a JAXB object.
	 */
	protected final JAXBIntrospector			jaxbIS;

	/**
	 * local JAXB marshaller instance to convert objects into XML
	 */
	protected final Marshaller					marshaller;

	/**
	 * local JAXB unmarshaller instance to convert XML into objects
	 */
	protected final Unmarshaller				unmarshaller;

	/**
	 * namespace map to convert prefixes to fully qualified namespace and vice
	 * versa
	 */
	private static final Map<String, String>	nsMap	= new HashMap<String, String>();

	/*
	 * initialize namespace map
	 */
	static {
		nsMap.put("urn:iso:std:iso-iec:24727:tech:schema", "iso"); // important for some server implementations
	}

	/**
	 * Creates a new instance and initialize JAXB components
	 * 
	 * @throws JAXBException
	 *             - if JAXB component initialization fails
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

		//		marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper",
		//		// new net.ageto.lib.eid_jxb.NamespacePrefixMapper() {
		//				new com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper() {
		//					@Override
		//					public String getPreferredPrefix(final String namespaceUri, final String suggestion,
		//							final boolean requirePrefix) {
		//						final String result = nsMap.get(namespaceUri);
		//						if (result != null) {
		//							return result;
		//						}
		//						return suggestion;
		//					}
		//				});
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param wsCtx
	 *            - webservice container context
	 * 
	 * @param endpoint
	 *            - PAOS remote endpoint address
	 * 
	 * @param sessionID
	 *            - eID session identifier
	 * 
	 * @param pskKey
	 *            - pre-shared key for TLS_RSA_PSK connection
	 * 
	 * @throws IOException
	 *             - on communication error
	 * @throws JAXBException
	 *             - on error during JAXB context creation
	 */
	public PAOSInitiator(final WSContainer wsCtx, URI endpoint, final String sessionID, final byte[] pskKey)
			throws IOException, JAXBException {
		this();

		if (wsCtx == null || endpoint == null) {
			throw new IllegalArgumentException();
		}

		this.wsCtx = wsCtx;
		this.sessionID = sessionID;
		this.pskKey = pskKey;

		if (endpoint.getPath().isEmpty()) {
			endpoint = endpoint.resolve("/").normalize();
		}

		/*
		 * TODO: remove?
		 */
		if (sessionID != null) {
			endpoint = endpoint.resolve("?sessionid=" + sessionID);
		}

		this.serviceURL = endpoint.toURL();

		this.mhc = createClient();
		final SSLSession s = this.mhc.getSSLSession();
		final String p = s == null ? null : s.getProtocol();
		if (p != null && p.startsWith("TLSv") && Float.parseFloat(p.substring(4)) > 1.1d) {
			System.out.println("protocol ok: " + p);
		} else {
			System.out.println("protocol warning: " + p);
		}
	}

	/**
	 * Used for Android and slow/embedded platforms to load and resolve PAOS and
	 * JAXB stack. Not used in other environments.
	 * 
	 * @return {@link PAOSInitiator}
	 */
	public static PAOSInitiator getPreStartInstance() {
		if (paosInitiatorFactory == null) {
			paosInitiatorFactory = new PAOSInitiatorFactory();
		}

		return paosInitiatorFactory.createPreStartPAOSInitiator();
	}

	/**
	 * create a JAXB context using specific object factories
	 * 
	 * @return {@link JAXBContext}
	 */
	protected JAXBContext createJAXBContext() {
		final Class<?>[] objectFactories = new Class<?>[] {
				//
				org.xmlsoap.schemas.soap.envelope.ObjectFactory.class,	// SOAP Envelope
				liberty.paos._2006_08.ObjectFactory.class,				// PAOS Header
				org.w3._2005._03.addressing.ObjectFactory.class,		// SOAP Addressing Header
				iso.std.iso_iec._24727.tech.schema.ObjectFactory.class,	// ISO-24727 messages
				de.bund.bsi.ecard.api._1.ObjectFactory.class			// eCard API for ManagementService.initializeFramework
		};

		try {
			return JAXBContext.newInstance(objectFactories);
		} catch (final JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Create a new instance using the factory.
	 * 
	 * @param wsCtx
	 *            - webservice container context
	 * @param endpoint
	 *            - PAOS remote endpoint
	 * @param sessionID
	 *            - eID session identifier
	 * @param pskKey
	 *            - pre-shared key for TLS_RSA_PSK
	 * 
	 * @return {@link PAOSInitiator}
	 * 
	 * @throws IOException
	 *             - on communication error
	 */
	public static PAOSInitiator getInstance(final WSContainer wsCtx, final URI endpoint, final String sessionID,
			final byte[] pskKey) throws IOException {
		if (paosInitiatorFactory == null) {
			paosInitiatorFactory = new PAOSInitiatorFactory();
		}

		return paosInitiatorFactory.createPAOSInitiator(wsCtx, endpoint, sessionID, pskKey);
	}

	/**
	 * create http client for network communication
	 * 
	 * @throws IOException
	 *             on connection error
	 * @return {@link MiniHttpClient}
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
	 * retrieve the TLS {@link Certificate} of the remote endpoint
	 * 
	 * @return remote TLS {@link Certificate}.
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
	 * get the TLS session object for the underlying connection
	 * 
	 * @return {@link SSLSession}
	 */
	public SSLSession getSSLSession() {
		return this.mhc.getSSLSession();
	}

	/**
	 * create <tt>SOAP</tt> envelope based on given header values
	 * 
	 * @param messageHeader
	 *            - PAOS message header
	 * @param messageID
	 *            - current message ID
	 * @param relatesTo
	 *            - message ID of corresponding request
	 * @param action
	 *            - SOAP action
	 * @param message
	 *            - PAOS message
	 * 
	 * @return {@JAXBElement} containing SOAP envelope
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

	/**
	 * create PAOS header for SOAP envelope
	 * 
	 * @return {@link JAXBElement} containing {@link PaosType}
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
	 * random generator for message IDs
	 */
	private final Random	random	= new Random();

	/**
	 * create a random session ID to be used for header creation
	 * 
	 * @return {@link String} containing generated message ID as "urn:uuidXXX"
	 */
	private String createMessageID() {
		final byte[] randomID = new byte[16];
		random.nextBytes(randomID);
		return "urn:uuid" + Hex.toString(randomID);
	}

	/**
	 * Dispatch a JAXB message to the remote endpoint
	 * 
	 * @param msg
	 *            - message to be sent
	 * 
	 * @return {@link JAXBException} containing response or <em>null</em> if no
	 *         response was received
	 * 
	 * @throws IOException
	 *             - on communication error
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
	 * Transform and dispatch java object message to remote endpoint
	 * 
	 * @param in
	 *            - message to be sent
	 * @return {@link Object} array containing message name as {@link QName} and
	 *         message itself
	 * 
	 * @throws IOException
	 *             - on communication error
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
	 * start PAOS communication
	 * 
	 * @param contextHandle
	 *            - context handle for this connection
	 * 
	 * @param slotHandle
	 *            - slot handle for this connection
	 * 
	 * @return StartPAOSResponse as {@link ResponseType} or null if no response
	 *         or response of wrong type was received
	 * 
	 * @throws IOException
	 *             - on communication error
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
