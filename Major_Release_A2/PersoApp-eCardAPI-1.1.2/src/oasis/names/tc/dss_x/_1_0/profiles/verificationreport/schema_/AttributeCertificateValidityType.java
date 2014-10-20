
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AttributeCertificateValidityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AttributeCertificateValidityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AttributeCertificateIdentifier" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttrCertIDType"/>
 *         &lt;element name="AttributeCertificateValue" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="AttributeCertificateContent" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeCertificateContentType" minOccurs="0"/>
 *         &lt;element name="SignatureOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignatureValidityType"/>
 *         &lt;element name="CertificatePathValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificatePathValidityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttributeCertificateValidityType", propOrder = {
    "attributeCertificateIdentifier",
    "attributeCertificateValue",
    "attributeCertificateContent",
    "signatureOK",
    "certificatePathValidity"
})
public class AttributeCertificateValidityType {

    @XmlElement(name = "AttributeCertificateIdentifier", required = true)
    protected AttrCertIDType attributeCertificateIdentifier;
    @XmlElement(name = "AttributeCertificateValue")
    protected byte[] attributeCertificateValue;
    @XmlElement(name = "AttributeCertificateContent")
    protected AttributeCertificateContentType attributeCertificateContent;
    @XmlElement(name = "SignatureOK", required = true)
    protected SignatureValidityType signatureOK;
    @XmlElement(name = "CertificatePathValidity", required = true)
    protected CertificatePathValidityType certificatePathValidity;

    /**
     * Gets the value of the attributeCertificateIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link AttrCertIDType }
     *     
     */
    public AttrCertIDType getAttributeCertificateIdentifier() {
        return attributeCertificateIdentifier;
    }

    /**
     * Sets the value of the attributeCertificateIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttrCertIDType }
     *     
     */
    public void setAttributeCertificateIdentifier(AttrCertIDType value) {
        this.attributeCertificateIdentifier = value;
    }

    /**
     * Gets the value of the attributeCertificateValue property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getAttributeCertificateValue() {
        return attributeCertificateValue;
    }

    /**
     * Sets the value of the attributeCertificateValue property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setAttributeCertificateValue(byte[] value) {
        this.attributeCertificateValue = ((byte[]) value);
    }

    /**
     * Gets the value of the attributeCertificateContent property.
     * 
     * @return
     *     possible object is
     *     {@link AttributeCertificateContentType }
     *     
     */
    public AttributeCertificateContentType getAttributeCertificateContent() {
        return attributeCertificateContent;
    }

    /**
     * Sets the value of the attributeCertificateContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link AttributeCertificateContentType }
     *     
     */
    public void setAttributeCertificateContent(AttributeCertificateContentType value) {
        this.attributeCertificateContent = value;
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

}
