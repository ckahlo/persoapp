
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.etsi.uri._01903.v1_3.CompleteCertificateRefsType;
import org.etsi.uri._01903.v1_3.CompleteRevocationRefsType;


/**
 * <p>Java class for UnsignedSignaturePropertiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnsignedSignaturePropertiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="CounterSignature" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignatureValidityType"/>
 *         &lt;element name="SignatureTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/>
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}CompleteCertificateRefs"/>
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}CompleteRevocationRefs"/>
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}AttributeCertificateRefs"/>
 *         &lt;element ref="{http://uri.etsi.org/01903/v1.3.2#}AttributeRevocationRefs"/>
 *         &lt;element name="SigAndRefsTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/>
 *         &lt;element name="RefsOnlyTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/>
 *         &lt;element name="CertificateValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateValuesType"/>
 *         &lt;element name="RevocationValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}RevocationValuesType"/>
 *         &lt;element name="AttrAuthoritiesCertValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateValuesType"/>
 *         &lt;element name="AttributeRevocationValues" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}RevocationValuesType"/>
 *         &lt;element name="ArchiveTimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/>
 *       &lt;/choice>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnsignedSignaturePropertiesType", propOrder = {
    "counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs"
})
public class UnsignedSignaturePropertiesType {

    @XmlElementRefs({
        @XmlElementRef(name = "SignatureTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "AttributeRevocationRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class),
        @XmlElementRef(name = "AttrAuthoritiesCertValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "CounterSignature", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "AttributeRevocationValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "AttributeCertificateRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class),
        @XmlElementRef(name = "ArchiveTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "CertificateValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "RevocationValues", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "SigAndRefsTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "CompleteCertificateRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class),
        @XmlElementRef(name = "RefsOnlyTimeStamp", namespace = "urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#", type = JAXBElement.class),
        @XmlElementRef(name = "CompleteRevocationRefs", namespace = "http://uri.etsi.org/01903/v1.3.2#", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCounterSignatureOrSignatureTimeStampOrCompleteCertificateRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteRevocationRefsType }{@code >}
     * {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link SignatureValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteCertificateRefsType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link CertificateValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link RevocationValuesType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteCertificateRefsType }{@code >}
     * {@link JAXBElement }{@code <}{@link TimeStampValidityType }{@code >}
     * {@link JAXBElement }{@code <}{@link CompleteRevocationRefsType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getCounterSignatureOrSignatureTimeStampOrCompleteCertificateRefs() {
        if (counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs == null) {
            counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs = new ArrayList<JAXBElement<?>>();
        }
        return this.counterSignatureOrSignatureTimeStampOrCompleteCertificateRefs;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
