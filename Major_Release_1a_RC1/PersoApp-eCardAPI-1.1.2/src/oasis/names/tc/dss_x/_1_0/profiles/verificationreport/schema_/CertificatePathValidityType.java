
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.w3._2000._09.xmldsig_.X509IssuerSerialType;


/**
 * <p>Java class for CertificatePathValidityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificatePathValidityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PathValiditySummary" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="CertificateIdentifier" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType"/>
 *         &lt;element name="PathValidityDetail" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificatePathValidityVerificationDetailType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificatePathValidityType", propOrder = {
    "pathValiditySummary",
    "certificateIdentifier",
    "pathValidityDetail"
})
public class CertificatePathValidityType {

    @XmlElement(name = "PathValiditySummary", required = true)
    protected VerificationResultType pathValiditySummary;
    @XmlElement(name = "CertificateIdentifier", required = true)
    protected X509IssuerSerialType certificateIdentifier;
    @XmlElement(name = "PathValidityDetail")
    protected CertificatePathValidityVerificationDetailType pathValidityDetail;

    /**
     * Gets the value of the pathValiditySummary property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getPathValiditySummary() {
        return pathValiditySummary;
    }

    /**
     * Sets the value of the pathValiditySummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setPathValiditySummary(VerificationResultType value) {
        this.pathValiditySummary = value;
    }

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
     * Gets the value of the pathValidityDetail property.
     * 
     * @return
     *     possible object is
     *     {@link CertificatePathValidityVerificationDetailType }
     *     
     */
    public CertificatePathValidityVerificationDetailType getPathValidityDetail() {
        return pathValidityDetail;
    }

    /**
     * Sets the value of the pathValidityDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificatePathValidityVerificationDetailType }
     *     
     */
    public void setPathValidityDetail(CertificatePathValidityVerificationDetailType value) {
        this.pathValidityDetail = value;
    }

}
