package de.persoapp.lib.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3._2005._03.addressing.AttributedURIType;
import org.w3._2005._03.addressing.RelatesToType;
import org.xmlsoap.schemas.soap.envelope.Body;
import org.xmlsoap.schemas.soap.envelope.Envelope;
import org.xmlsoap.schemas.soap.envelope.Header;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Random;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import de.persoapp.core.util.Hex;
import iso.std.iso_iec._24727.tech.schema.ConnectionHandleType;
import iso.std.iso_iec._24727.tech.schema.StartPAOS;
import liberty.paos._2006_08.EndpointReferenceType;
import liberty.paos._2006_08.MetaDataType;
import liberty.paos._2006_08.OptionsType;
import liberty.paos._2006_08.PaosType;

public class JXBTest {
	public static final String VERSION1_1  = "urn:liberty:2003-08";
	public static final String VERSION2_0  = "urn:liberty:2006-08";
	public static final String ENDPOINT1   = "http://www.projectliberty.org/2006/01/role/paos";
	public static final String ENDPOINT2   = "http://www.projectliberty.org/2006/02/role/paos";
	public static final String SERVICETYPE = "http://www.bsi.bund.de/ecard/api/1.0/PAOS/GetNextCommand";
	public static final String ACTOR 	   = "http://schemas.xmlsoap.org/soap/actor/next";

	private liberty.paos._2006_08.ObjectFactory paosOF = new liberty.paos._2006_08.ObjectFactory();
	private org.xmlsoap.schemas.soap.envelope.ObjectFactory soapOF = new org.xmlsoap.schemas.soap.envelope.ObjectFactory();
	private org.w3._2005._03.addressing.ObjectFactory orgW3OF = new org.w3._2005._03.addressing.ObjectFactory();

	public JAXBElement<Envelope> createEnvelope(JAXBElement<PaosType> messageHeader, String messageID, String relatesTo, String action, Object message) {
		Header header = new Header();
		header.getAny().add(messageHeader);

		AttributedURIType mid = new AttributedURIType();
		mid.setValue(messageID);
		header.getAny().add(orgW3OF.createMessageID(mid));

		org.w3._2005._03.addressing.EndpointReferenceType replyTo = orgW3OF.createEndpointReferenceType();
		AttributedURIType replyToAddress = new AttributedURIType();
		replyToAddress.setValue("http://www.projectliberty.org/2006/02/role/paos");
		replyTo.setAddress(replyToAddress);
		header.getAny().add(orgW3OF.createReplyTo(replyTo));

		if(relatesTo != null) {
			RelatesToType relation = orgW3OF.createRelatesToType();
			relation.setValue(relatesTo);
			header.getAny().add(orgW3OF.createRelatesTo(relation));
		}

		if(action != null) {
			AttributedURIType actionURI = new AttributedURIType();
			actionURI.setValue(action);
			header.getAny().add(orgW3OF.createAction(actionURI));
		}

		Body body = new Body();
		body.getAny().add(message);

		Envelope envelope = new Envelope();
		envelope.setHeader(header);
		envelope.setBody(body);

		return soapOF.createEnvelope(envelope);
	}

	public JAXBElement<PaosType> createPAOSHeader() {
		PaosType pt = new PaosType();
		pt.setMustUnderstand(true);
		pt.setActor("http://schemas.xmlsoap.org/soap/actor/next");

		pt.getVersion().add(VERSION2_0);
		pt.getVersion().add(VERSION1_1);

		EndpointReferenceType endpointRef = new EndpointReferenceType();
		endpointRef.setAddress("http://www.projectliberty.org/2006/01/role/paos");

		MetaDataType metaData = new MetaDataType();
		metaData.setServiceType("http://www.bsi.bund.de/ecard/api/1.0/PAOS/GetNextCommand");
		metaData.setOptions(new OptionsType());
		endpointRef.setMetaData(metaData);

		pt.getEndpointReference().add(endpointRef);

		return paosOF.createPAOS(pt);
	}

	Random random = new Random();
	private String createMessageID() {
		byte[] randomID = new byte[16];
		random.nextBytes(randomID);
		return new StringBuilder().append("urn:uuid").append(Hex.toString(randomID)).toString();
	}

	private JAXBContext jaxbCtx;
	private String lastMessageID;

	public void run() {
		try {
			Class<?>[] objectFactories = new Class<?>[] {
					liberty.paos._2006_08.ObjectFactory.class,
					org.xmlsoap.schemas.soap.envelope.ObjectFactory.class,
					org.w3._2005._03.addressing.ObjectFactory.class,
					iso.std.iso_iec._24727.tech.schema.ObjectFactory.class,
//					oasis.names.tc.dss._1_0.core.schema.ObjectFactory.class
			};

			//jaxbCtx = ContextImpl.createContext(objectFactories, null);
			jaxbCtx = ContextImpl.createContext(null, null);
/*
			jaxbCtx = JAXBContext.newInstance(
					"org.xmlsoap.schemas.soap.envelope:liberty.paos._2006_08:org.w3._2005._03.addressing" +
					":iso.std.iso_iec._24727.tech.schema:oasis.names.tc.dss._1_0.core.schema"
					);
*/
		} catch (JAXBException e) {
			e.printStackTrace();
		}

        StartPAOS sp = new StartPAOS();
        sp.setSessionIdentifier("$sid$123456");

        ConnectionHandleType ch = new ConnectionHandleType();
        byte[] contextHandle = new byte[32];
        byte[] slotHandle = new byte[32];
        Random r = new Random();
        r.nextBytes(contextHandle);
        r.nextBytes(slotHandle);

        ch.setContextHandle(contextHandle);
        ch.setSlotHandle(slotHandle);

        sp.getConnectionHandle().add(ch);

        String currentMessageID = createMessageID();

        Marshaller marshaller = null;
        Unmarshaller unmarshaller = null;
        JAXBIntrospector jaxbIS = null;
		try {
			marshaller = jaxbCtx.createMarshaller();
			unmarshaller = jaxbCtx.createUnmarshaller();
			jaxbIS = jaxbCtx.createJAXBIntrospector();

			marshaller.setProperty("xml.internal.bind.xmlDeclaration", false);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty("xml.internal.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
		        @Override
		        public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		        	if("urn:iso:std:iso-iec:24727:tech:schema".equals(namespaceUri)) {
		        		return "iso";
		        	} else if("urn:oasis:names:tc:dss:1.0:core:schema".equals(namespaceUri)) {
		        		return "dss";
		        	} else if("urn:liberty:paos:2006-08".equals(namespaceUri)) {
		        		return "P";
		        	} else if("http://schemas.xmlsoap.org/soap/envelope/".equals(namespaceUri)) {
		        		return "S";
		        	} else if("http://www.w3.org/2005/03/addressing".equals(namespaceUri)) {
		        		return "A";
		        	} else if("http://www.w3.org/2000/09/xmldsig#".equals(namespaceUri)) {
		        		return "dsig";
		        	} else if("http://www.w3.org/2001/XMLSchema-instance".equals(namespaceUri)) {
		        		return "xsi";
		        	} return suggestion;
		        }
			});
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
        Object msg = createEnvelope(createPAOSHeader(), currentMessageID, lastMessageID, null, sp);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
			marshaller.marshal(msg, new StreamResult(baos));
			System.out.println("Transform-Result:\n" + new String(baos.toByteArray()));
			//String sampleRes = "<ns1:Envelope xmlns:ns2=\"urn:liberty:paos:2003-08\" xmlns:ns1=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns3=\"urn:liberty:paos:2006-08\" xmlns:ns5=\"http://www.w3.org/2005/03/addressing\"><ns1:Header><ns5:MessageID>urn:uuidda0b6c406dbe2bd898c8612dfc7ee3bac1e45197</ns5:MessageID><ns5:ReplyTo><ns5:Address>https://eid.eid-service.de:443</ns5:Address></ns5:ReplyTo><ns5:Action>http://www.bsi.bund.de/ecard/api/1.0/PAOS/GetNextCommand</ns5:Action></ns1:Header><ns1:Body><InitializeFramework:InitializeFramework xsi:type=\"iso:RequestType\" xmlns=\"http://www.bsi.bund.de/ecard/api/1.1\" xmlns:tsl2=\"http://uri.etsi.org/02231/v2.1.1#\" xmlns:ecdsa=\"http://www.w3.org/2001/04/xmldsig-more#\" xmlns:olsc=\"http://www.openlimit.com/ecard/api/ext/acbc\" xmlns:xenc=\"http://www.w3.org/2001/04/xmlenc#\" xmlns:iso=\"urn:iso:std:iso-iec:24727:tech:schema\" xmlns:saml=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:vr=\"urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#\" xmlns:InitializeFramework=\"http://www.bsi.bund.de/ecard/api/1.1\" xmlns:dss=\"urn:oasis:names:tc:dss:1.0:core:schema\" xmlns:SOAP-ENC=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:dsse=\"urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#\" xmlns:ec=\"http://www.bsi.bund.de/ecard/api/1.1\" xmlns:tsl=\"http://uri.etsi.org/02231/v3.1.2#\" xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:XAdES=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:saml2=\"urn:oasis:names:tc:SAML:2.0:assertion\" xmlns:dssades=\"urn:oasis:names:tc:dss:1.0:profiles:AdES:schema#\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:dssx=\"urn:oasis:names:tc:dss-x:1.0:profiles:SignaturePolicy:schema#\" xmlns:ers=\"http://www.setcce.org/schemas/ers\" xmlns:tslg=\"http://uri.etsi.org/02231/v2.x#\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/></ns1:Body></ns1:Envelope>";
			String sampleRes = "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:wsa=\"http://www.w3.org/2005/03/addressing\" xmlns:paos=\"urn:liberty:paos:2006-08\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><S:Header><paos:PAOS S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\"><paos:Version>urn:liberty:2006-08</paos:Version><paos:EndpointReference><paos:Address>http://www.projectliberty.org/2006/01/role/paos</paos:Address></paos:EndpointReference></paos:PAOS><wsa:MessageID>urn:uuid:b50d8025-1087-425c-9406-16b92bdf3921</wsa:MessageID><wsa:Action>http://www.bsi.bund.de/ecard/api/1.0/PAOS/GetNextCommand</wsa:Action><wsa:RelatesTo S:actor=\"http://schemas.xmlsoap.org/soap/actor/next\" S:mustUnderstand=\"1\">urn:uuidF76E5582E780ED649DDFD546CA7E76FC</wsa:RelatesTo></S:Header><S:Body><ns5:InitializeFramework xmlns:ns14=\"urn:oasis:names:tc:SAML:1.0:protocol\" xmlns:ns13=\"urn:oasis:names:tc:SAML:2.0:assertion\" xmlns:ns12=\"urn:oasis:names:tc:SAML:1.0:assertion\" xmlns:ns11=\"urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#\" xmlns:ns10=\"http://uri.etsi.org/01903/v1.3.2#\" xmlns:ns9=\"http://www.w3.org/2001/04/xmldsig-more#\" xmlns:ns8=\"http://www.w3.org/2001/04/xmlenc#\" xmlns:ns7=\"http://www.setcce.org/schemas/ers\" xmlns:ns6=\"http://uri.etsi.org/02231/v2#\" xmlns:ns5=\"http://www.bsi.bund.de/ecard/api/1.1\" xmlns:ns4=\"urn:iso:std:iso-iec:24727:tech:schema\" xmlns:ns3=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns2=\"urn:oasis:names:tc:dss:1.0:core:schema\" /></S:Body></S:Envelope>";
			System.out.println("IS-Test: " + jaxbIS.getElementName(sp));

			System.out.println("Sample: " + sampleRes);

			JAXBElement<Envelope> env =
				unmarshaller.unmarshal(new StreamSource(new ByteArrayInputStream(sampleRes.getBytes())), Envelope.class);

			System.out.println("Result: " + env);

			Envelope e = env.getValue();

	        Header soapHeader = e.getHeader();
	        if(soapHeader != null) {
	            for(Object soapHE : soapHeader.getAny()) {
	            	JAXBElement<?> je = (JAXBElement<?>)soapHE;
	            	Object value = je.getValue();
	            	//while(value != null && !(value instanceof String)) {
		            	if(value instanceof AttributedURIType) {
		            		value = ((AttributedURIType)value).getValue();
		            	} else if(value instanceof EndpointReferenceType) {
		            		value = ((EndpointReferenceType)value).getAddress();
		            	} else if(value instanceof org.w3._2005._03.addressing.EndpointReferenceType) {
		            		value = ((org.w3._2005._03.addressing.EndpointReferenceType)value).getAddress();
		            	}
	            	//}
	                System.out.println("SOAP-Header: " + je.getName() + " = " + value);

	            	if(new QName("http://www.w3.org/2005/03/addressing", "MessageID").equals(je.getName())) {
	            		AttributedURIType uri = (AttributedURIType) je.getValue();
	            		lastMessageID = uri.getValue();
	            		System.out.println("LAST MESSAGE-ID: " + lastMessageID);
	            	}
	            }
	        }

	        QName msgName = null;
	        Object message = e.getBody().getAny().get(0);

	        if(message instanceof JAXBElement) {
	        	JAXBElement<?> je = (JAXBElement<?>)message;
	        	message = je.getValue();
	        	System.out.println("Transform-Result:\n" + je.getName() + " -> "+ message.getClass() + " = " + message);
	        } else {
	        	System.out.println("Transform-Result:\n" + message.getClass() + " = " + message);
	        }

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private List<byte[]> test;

	public static void main(String[] args) throws Exception {

		Field f = JXBTest.class.getDeclaredField("test");
		Object t1 = ((ParameterizedType)f.getGenericType()).getActualTypeArguments()[0];

		System.out.println(((ParameterizedType)f.getGenericType()));
		System.out.println(((ParameterizedType)f.getGenericType()).getOwnerType());
		System.out.println(((ParameterizedType)f.getGenericType()).getRawType());

		System.out.println(Array.newInstance(byte.class, 0).getClass());
		System.out.println(t1 + " " + t1.getClass());

		if(true)
			return;

		new JXBTest().run();
		//net.ageto.services.eid.Client.main(args);
	}
}
