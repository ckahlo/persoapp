
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for CertificatePathValidityVerificationDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificatePathValidityVerificationDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CertificateValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}CertificateValidityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TSLValidity" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *         &lt;element name="TrustAnchor" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificatePathValidityVerificationDetailType", propOrder = {
    "certificateValidity",
    "tslValidity",
    "trustAnchor"
})
public class CertificatePathValidityVerificationDetailType {

    @XmlElement(name = "CertificateValidity")
    protected List<CertificateValidityType> certificateValidity;
    @XmlElement(name = "TSLValidity")
    protected AnyType tslValidity;
    @XmlElement(name = "TrustAnchor", required = true)
    protected VerificationResultType trustAnchor;

    /**
     * Gets the value of the certificateValidity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificateValidity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificateValidity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CertificateValidityType }
     * 
     * 
     */
    public List<CertificateValidityType> getCertificateValidity() {
        if (certificateValidity == null) {
            certificateValidity = new ArrayList<CertificateValidityType>();
        }
        return this.certificateValidity;
    }

    /**
     * Gets the value of the tslValidity property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getTSLValidity() {
        return tslValidity;
    }

    /**
     * Sets the value of the tslValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setTSLValidity(AnyType value) {
        this.tslValidity = value;
    }

    /**
     * Gets the value of the trustAnchor property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getTrustAnchor() {
        return trustAnchor;
    }

    /**
     * Sets the value of the trustAnchor property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setTrustAnchor(VerificationResultType value) {
        this.trustAnchor = value;
    }

}
