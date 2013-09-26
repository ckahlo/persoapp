
package oasis.names.tc.dss_x._1_0.profiles.encryption.schema_;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import oasis.names.tc.dss._1_0.core.schema.RequestBaseType;
import oasis.names.tc.dss._1_0.core.schema.ResponseBaseType;
import org.w3._2001._04.xmlenc_.EncryptionMethodType;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.dss_x._1_0.profiles.encryption.schema_ package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EncryptRequest_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "EncryptRequest");
    private final static QName _EncryptionKey_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "EncryptionKey");
    private final static QName _DecryptRequest_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "DecryptRequest");
    private final static QName _EncryptedDocument_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "EncryptedDocument");
    private final static QName _DecryptResponse_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "DecryptResponse");
    private final static QName _ContentEncryptionMethod_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "ContentEncryptionMethod");
    private final static QName _EncryptResponse_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "EncryptResponse");
    private final static QName _EncryptionContent_QNAME = new QName("urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", "EncryptionContent");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.dss_x._1_0.profiles.encryption.schema_
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EncryptionKeySelectorType }
     * 
     */
    public EncryptionKeySelectorType createEncryptionKeySelectorType() {
        return new EncryptionKeySelectorType();
    }

    /**
     * Create an instance of {@link EncryptionContentType }
     * 
     */
    public EncryptionContentType createEncryptionContentType() {
        return new EncryptionContentType();
    }

    /**
     * Create an instance of {@link InsertEncryptedDataType }
     * 
     */
    public InsertEncryptedDataType createInsertEncryptedDataType() {
        return new InsertEncryptedDataType();
    }

    /**
     * Create an instance of {@link DestinationSelectorType }
     * 
     */
    public DestinationSelectorType createDestinationSelectorType() {
        return new DestinationSelectorType();
    }

    /**
     * Create an instance of {@link SelectorType }
     * 
     */
    public SelectorType createSelectorType() {
        return new SelectorType();
    }

    /**
     * Create an instance of {@link EncryptedDocumentType }
     * 
     */
    public EncryptedDocumentType createEncryptedDocumentType() {
        return new EncryptedDocumentType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "EncryptRequest")
    public JAXBElement<RequestBaseType> createEncryptRequest(RequestBaseType value) {
        return new JAXBElement<RequestBaseType>(_EncryptRequest_QNAME, RequestBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionKeySelectorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "EncryptionKey")
    public JAXBElement<EncryptionKeySelectorType> createEncryptionKey(EncryptionKeySelectorType value) {
        return new JAXBElement<EncryptionKeySelectorType>(_EncryptionKey_QNAME, EncryptionKeySelectorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "DecryptRequest")
    public JAXBElement<RequestBaseType> createDecryptRequest(RequestBaseType value) {
        return new JAXBElement<RequestBaseType>(_DecryptRequest_QNAME, RequestBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptedDocumentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "EncryptedDocument")
    public JAXBElement<EncryptedDocumentType> createEncryptedDocument(EncryptedDocumentType value) {
        return new JAXBElement<EncryptedDocumentType>(_EncryptedDocument_QNAME, EncryptedDocumentType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "DecryptResponse")
    public JAXBElement<ResponseBaseType> createDecryptResponse(ResponseBaseType value) {
        return new JAXBElement<ResponseBaseType>(_DecryptResponse_QNAME, ResponseBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionMethodType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "ContentEncryptionMethod")
    public JAXBElement<EncryptionMethodType> createContentEncryptionMethod(EncryptionMethodType value) {
        return new JAXBElement<EncryptionMethodType>(_ContentEncryptionMethod_QNAME, EncryptionMethodType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseBaseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "EncryptResponse")
    public JAXBElement<ResponseBaseType> createEncryptResponse(ResponseBaseType value) {
        return new JAXBElement<ResponseBaseType>(_EncryptResponse_QNAME, ResponseBaseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EncryptionContentType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#", name = "EncryptionContent")
    public JAXBElement<EncryptionContentType> createEncryptionContent(EncryptionContentType value) {
        return new JAXBElement<EncryptionContentType>(_EncryptionContent_QNAME, EncryptionContentType.class, null, value);
    }

}
