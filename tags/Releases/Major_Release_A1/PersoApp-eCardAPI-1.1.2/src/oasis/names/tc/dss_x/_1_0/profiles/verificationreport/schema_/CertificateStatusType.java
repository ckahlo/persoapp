
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import org.etsi.uri._01903.v1_3.CRLIdentifierType;
import org.etsi.uri._01903.v1_3.OCSPIdentifierType;


/**
 * <p>Java class for CertificateStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CertStatusOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="RevocationInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="RevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="RevocationReason" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="RevocationEvidence" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="CRLValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CRLValidityType"/>
 *                   &lt;element name="CRLReference" type="{http://uri.etsi.org/01903/v1.3.2#}CRLIdentifierType"/>
 *                   &lt;element name="OCSPValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}OCSPValidityType"/>
 *                   &lt;element name="OCSPReference" type="{http://uri.etsi.org/01903/v1.3.2#}OCSPIdentifierType"/>
 *                   &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateStatusType", propOrder = {
    "certStatusOK",
    "revocationInfo",
    "revocationEvidence"
})
public class CertificateStatusType {

    @XmlElement(name = "CertStatusOK", required = true)
    protected VerificationResultType certStatusOK;
    @XmlElement(name = "RevocationInfo")
    protected CertificateStatusType.RevocationInfo revocationInfo;
    @XmlElement(name = "RevocationEvidence")
    protected CertificateStatusType.RevocationEvidence revocationEvidence;

    /**
     * Gets the value of the certStatusOK property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getCertStatusOK() {
        return certStatusOK;
    }

    /**
     * Sets the value of the certStatusOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setCertStatusOK(VerificationResultType value) {
        this.certStatusOK = value;
    }

    /**
     * Gets the value of the revocationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateStatusType.RevocationInfo }
     *     
     */
    public CertificateStatusType.RevocationInfo getRevocationInfo() {
        return revocationInfo;
    }

    /**
     * Sets the value of the revocationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateStatusType.RevocationInfo }
     *     
     */
    public void setRevocationInfo(CertificateStatusType.RevocationInfo value) {
        this.revocationInfo = value;
    }

    /**
     * Gets the value of the revocationEvidence property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateStatusType.RevocationEvidence }
     *     
     */
    public CertificateStatusType.RevocationEvidence getRevocationEvidence() {
        return revocationEvidence;
    }

    /**
     * Sets the value of the revocationEvidence property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateStatusType.RevocationEvidence }
     *     
     */
    public void setRevocationEvidence(CertificateStatusType.RevocationEvidence value) {
        this.revocationEvidence = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice>
     *         &lt;element name="CRLValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CRLValidityType"/>
     *         &lt;element name="CRLReference" type="{http://uri.etsi.org/01903/v1.3.2#}CRLIdentifierType"/>
     *         &lt;element name="OCSPValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}OCSPValidityType"/>
     *         &lt;element name="OCSPReference" type="{http://uri.etsi.org/01903/v1.3.2#}OCSPIdentifierType"/>
     *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "crlValidity",
        "crlReference",
        "ocspValidity",
        "ocspReference",
        "other"
    })
    public static class RevocationEvidence {

        @XmlElement(name = "CRLValidity")
        protected CRLValidityType crlValidity;
        @XmlElement(name = "CRLReference")
        protected CRLIdentifierType crlReference;
        @XmlElement(name = "OCSPValidity")
        protected OCSPValidityType ocspValidity;
        @XmlElement(name = "OCSPReference")
        protected OCSPIdentifierType ocspReference;
        @XmlElement(name = "Other")
        protected AnyType other;

        /**
         * Gets the value of the crlValidity property.
         * 
         * @return
         *     possible object is
         *     {@link CRLValidityType }
         *     
         */
        public CRLValidityType getCRLValidity() {
            return crlValidity;
        }

        /**
         * Sets the value of the crlValidity property.
         * 
         * @param value
         *     allowed object is
         *     {@link CRLValidityType }
         *     
         */
        public void setCRLValidity(CRLValidityType value) {
            this.crlValidity = value;
        }

        /**
         * Gets the value of the crlReference property.
         * 
         * @return
         *     possible object is
         *     {@link CRLIdentifierType }
         *     
         */
        public CRLIdentifierType getCRLReference() {
            return crlReference;
        }

        /**
         * Sets the value of the crlReference property.
         * 
         * @param value
         *     allowed object is
         *     {@link CRLIdentifierType }
         *     
         */
        public void setCRLReference(CRLIdentifierType value) {
            this.crlReference = value;
        }

        /**
         * Gets the value of the ocspValidity property.
         * 
         * @return
         *     possible object is
         *     {@link OCSPValidityType }
         *     
         */
        public OCSPValidityType getOCSPValidity() {
            return ocspValidity;
        }

        /**
         * Sets the value of the ocspValidity property.
         * 
         * @param value
         *     allowed object is
         *     {@link OCSPValidityType }
         *     
         */
        public void setOCSPValidity(OCSPValidityType value) {
            this.ocspValidity = value;
        }

        /**
         * Gets the value of the ocspReference property.
         * 
         * @return
         *     possible object is
         *     {@link OCSPIdentifierType }
         *     
         */
        public OCSPIdentifierType getOCSPReference() {
            return ocspReference;
        }

        /**
         * Sets the value of the ocspReference property.
         * 
         * @param value
         *     allowed object is
         *     {@link OCSPIdentifierType }
         *     
         */
        public void setOCSPReference(OCSPIdentifierType value) {
            this.ocspReference = value;
        }

        /**
         * Gets the value of the other property.
         * 
         * @return
         *     possible object is
         *     {@link AnyType }
         *     
         */
        public AnyType getOther() {
            return other;
        }

        /**
         * Sets the value of the other property.
         * 
         * @param value
         *     allowed object is
         *     {@link AnyType }
         *     
         */
        public void setOther(AnyType value) {
            this.other = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="RevocationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="RevocationReason" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "revocationDate",
        "revocationReason"
    })
    public static class RevocationInfo {

        @XmlElement(name = "RevocationDate", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar revocationDate;
        @XmlElement(name = "RevocationReason", required = true)
        protected VerificationResultType revocationReason;

        /**
         * Gets the value of the revocationDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getRevocationDate() {
            return revocationDate;
        }

        /**
         * Sets the value of the revocationDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setRevocationDate(XMLGregorianCalendar value) {
            this.revocationDate = value;
        }

        /**
         * Gets the value of the revocationReason property.
         * 
         * @return
         *     possible object is
         *     {@link VerificationResultType }
         *     
         */
        public VerificationResultType getRevocationReason() {
            return revocationReason;
        }

        /**
         * Sets the value of the revocationReason property.
         * 
         * @param value
         *     allowed object is
         *     {@link VerificationResultType }
         *     
         */
        public void setRevocationReason(VerificationResultType value) {
            this.revocationReason = value;
        }

    }

}
