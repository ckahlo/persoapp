
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig_.X509IssuerSerialType;


/**
 * <p>Java class for CertificateValidityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateValidityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CertificateIdentifier" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ChainingOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="ValidityPeriodOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="ExtensionsOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="CertificateValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="CertificateContent" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateContentType" minOccurs="0"/>
 *         &lt;element name="SignatureOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignatureValidityType"/>
 *         &lt;element name="CertificateStatus" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateStatusType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateValidityType", propOrder = {
    "certificateIdentifier",
    "subject",
    "chainingOK",
    "validityPeriodOK",
    "extensionsOK",
    "certificateValue",
    "certificateContent",
    "signatureOK",
    "certificateStatus"
})
public class CertificateValidityType {

    @XmlElement(name = "CertificateIdentifier", required = true)
    protected X509IssuerSerialType certificateIdentifier;
    @XmlElement(name = "Subject", required = true)
    protected String subject;
    @XmlElement(name = "ChainingOK", required = true)
    protected VerificationResultType chainingOK;
    @XmlElement(name = "ValidityPeriodOK", required = true)
    protected VerificationResultType validityPeriodOK;
    @XmlElement(name = "ExtensionsOK", required = true)
    protected VerificationResultType extensionsOK;
    @XmlElement(name = "CertificateValue")
    protected byte[] certificateValue;
    @XmlElement(name = "CertificateContent")
    protected CertificateContentType certificateContent;
    @XmlElement(name = "SignatureOK", required = true)
    protected SignatureValidityType signatureOK;
    @XmlElement(name = "CertificateStatus", required = true)
    protected CertificateStatusType certificateStatus;

    /**
     * Gets the value of the certificateIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public X509IssuerSerialType getCertificateIdentifier() {
        return certificateIdentifier;
    }

    /**
     * Sets the value of the certificateIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public void setCertificateIdentifier(X509IssuerSerialType value) {
        this.certificateIdentifier = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

    /**
     * Gets the value of the chainingOK property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getChainingOK() {
        return chainingOK;
    }

    /**
     * Sets the value of the chainingOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setChainingOK(VerificationResultType value) {
        this.chainingOK = value;
    }

    /**
     * Gets the value of the validityPeriodOK property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getValidityPeriodOK() {
        return validityPeriodOK;
    }

    /**
     * Sets the value of the validityPeriodOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setValidityPeriodOK(VerificationResultType value) {
        this.validityPeriodOK = value;
    }

    /**
     * Gets the value of the extensionsOK property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getExtensionsOK() {
        return extensionsOK;
    }

    /**
     * Sets the value of the extensionsOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setExtensionsOK(VerificationResultType value) {
        this.extensionsOK = value;
    }

    /**
     * Gets the value of the certificateValue property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCertificateValue() {
        return certificateValue;
    }

    /**
     * Sets the value of the certificateValue property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCertificateValue(byte[] value) {
        this.certificateValue = ((byte[]) value);
    }

    /**
     * Gets the value of the certificateContent property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateContentType }
     *     
     */
    public CertificateContentType getCertificateContent() {
        return certificateContent;
    }

    /**
     * Sets the value of the certificateContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateContentType }
     *     
     */
    public void setCertificateContent(CertificateContentType value) {
        this.certificateContent = value;
    }

    /**
     * Gets the value of the signatureOK property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureValidityType }
     *     
     */
    public SignatureValidityType getSignatureOK() {
        return signatureOK;
    }

    /**
     * Sets the value of the signatureOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureValidityType }
     *     
     */
    public void setSignatureOK(SignatureValidityType value) {
        this.signatureOK = value;
    }

    /**
     * Gets the value of the certificateStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateStatusType }
     *     
     */
    public CertificateStatusType getCertificateStatus() {
        return certificateStatus;
    }

    /**
     * Sets the value of the certificateStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateStatusType }
     *     
     */
    public void setCertificateStatus(CertificateStatusType value) {
        this.certificateStatus = value;
    }

}
