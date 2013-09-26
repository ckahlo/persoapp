
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TimeStampValidityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeStampValidityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormatOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="TimeStampContent" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TstContentType" minOccurs="0"/>
 *         &lt;element name="MessageHashAlgorithm" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AlgorithmValidityType" minOccurs="0"/>
 *         &lt;element name="SignatureOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignatureValidityType"/>
 *         &lt;element name="CertificatePathValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificatePathValidityType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeStampValidityType", propOrder = {
    "formatOK",
    "timeStampContent",
    "messageHashAlgorithm",
    "signatureOK",
    "certificatePathValidity"
})
public class TimeStampValidityType {

    @XmlElement(name = "FormatOK", required = true)
    protected VerificationResultType formatOK;
    @XmlElement(name = "TimeStampContent")
    protected TstContentType timeStampContent;
    @XmlElement(name = "MessageHashAlgorithm")
    protected AlgorithmValidityType messageHashAlgorithm;
    @XmlElement(name = "SignatureOK", required = true)
    protected SignatureValidityType signatureOK;
    @XmlElement(name = "CertificatePathValidity", required = true)
    protected CertificatePathValidityType certificatePathValidity;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the formatOK property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getFormatOK() {
        return formatOK;
    }

    /**
     * Sets the value of the formatOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setFormatOK(VerificationResultType value) {
        this.formatOK = value;
    }

    /**
     * Gets the value of the timeStampContent property.
     * 
     * @return
     *     possible object is
     *     {@link TstContentType }
     *     
     */
    public TstContentType getTimeStampContent() {
        return timeStampContent;
    }

    /**
     * Sets the value of the timeStampContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link TstContentType }
     *     
     */
    public void setTimeStampContent(TstContentType value) {
        this.timeStampContent = value;
    }

    /**
     * Gets the value of the messageHashAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmValidityType }
     *     
     */
    public AlgorithmValidityType getMessageHashAlgorithm() {
        return messageHashAlgorithm;
    }

    /**
     * Sets the value of the messageHashAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmValidityType }
     *     
     */
    public void setMessageHashAlgorithm(AlgorithmValidityType value) {
        this.messageHashAlgorithm = value;
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
     * Gets the value of the certificatePathValidity property.
     * 
     * @return
     *     possible object is
     *     {@link CertificatePathValidityType }
     *     
     */
    public CertificatePathValidityType getCertificatePathValidity() {
        return certificatePathValidity;
    }

    /**
     * Sets the value of the certificatePathValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificatePathValidityType }
     *     
     */
    public void setCertificatePathValidity(CertificatePathValidityType value) {
        this.certificatePathValidity = value;
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
