
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Result;


/**
 * <p>Java class for IndividualReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IndividualReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SignedObjectIdentifier" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignedObjectIdentifierType"/>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/>
 *         &lt;element name="Details" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IndividualReportType", propOrder = {
    "signedObjectIdentifier",
    "result",
    "details"
})
public class IndividualReportType {

    @XmlElement(name = "SignedObjectIdentifier", required = true)
    protected SignedObjectIdentifierType signedObjectIdentifier;
    @XmlElement(name = "Result", namespace = "urn:oasis:names:tc:dss:1.0:core:schema", required = true)
    protected Result result;
    @XmlElement(name = "Details")
    protected AnyType details;

    /**
     * Gets the value of the signedObjectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link SignedObjectIdentifierType }
     *     
     */
    public SignedObjectIdentifierType getSignedObjectIdentifier() {
        return signedObjectIdentifier;
    }

    /**
     * Sets the value of the signedObjectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedObjectIdentifierType }
     *     
     */
    public void setSignedObjectIdentifier(SignedObjectIdentifierType value) {
        this.signedObjectIdentifier = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setDetails(AnyType value) {
        this.details = value;
    }

}
