
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.VerifyManifestResultsType;


/**
 * <p>Java class for DetailedSignatureReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DetailedSignatureReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormatOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="Properties" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}PropertiesType" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}VerifyManifestResults" minOccurs="0"/>
 *         &lt;element name="SignatureHasVisibleContent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
@XmlType(name = "DetailedSignatureReportType", propOrder = {
    "formatOK",
    "properties",
    "verifyManifestResults",
    "signatureHasVisibleContent",
    "signatureOK",
    "certificatePathValidity"
})
public class DetailedSignatureReportType {

    @XmlElement(name = "FormatOK", required = true)
    protected VerificationResultType formatOK;
    @XmlElement(name = "Properties")
    protected PropertiesType properties;
    @XmlElement(name = "VerifyManifestResults", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected VerifyManifestResultsType verifyManifestResults;
    @XmlElement(name = "SignatureHasVisibleContent")
    protected Boolean signatureHasVisibleContent;
    @XmlElement(name = "SignatureOK", required = true)
    protected SignatureValidityType signatureOK;
    @XmlElement(name = "CertificatePathValidity", required = true)
    protected CertificatePathValidityType certificatePathValidity;

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
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link PropertiesType }
     *     
     */
    public PropertiesType getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertiesType }
     *     
     */
    public void setProperties(PropertiesType value) {
        this.properties = value;
    }

    /**
     * Gets the value of the verifyManifestResults property.
     * 
     * @return
     *     possible object is
     *     {@link VerifyManifestResultsType }
     *     
     */
    public VerifyManifestResultsType getVerifyManifestResults() {
        return verifyManifestResults;
    }

    /**
     * Sets the value of the verifyManifestResults property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerifyManifestResultsType }
     *     
     */
    public void setVerifyManifestResults(VerifyManifestResultsType value) {
        this.verifyManifestResults = value;
    }

    /**
     * Gets the value of the signatureHasVisibleContent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSignatureHasVisibleContent() {
        return signatureHasVisibleContent;
    }

    /**
     * Sets the value of the signatureHasVisibleContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSignatureHasVisibleContent(Boolean value) {
        this.signatureHasVisibleContent = value;
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
