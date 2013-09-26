
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import org.etsi.uri._01903.v1_3.ObjectIdentifierType;


/**
 * <p>Java class for ExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtensionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExtnId" type="{http://uri.etsi.org/01903/v1.3.2#}ObjectIdentifierType"/>
 *         &lt;element name="Critical" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ExtnValue" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *         &lt;element name="ExtensionOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensionType", propOrder = {
    "extnId",
    "critical",
    "extnValue",
    "extensionOK"
})
public class ExtensionType {

    @XmlElement(name = "ExtnId", required = true)
    protected ObjectIdentifierType extnId;
    @XmlElement(name = "Critical")
    protected boolean critical;
    @XmlElement(name = "ExtnValue")
    protected AnyType extnValue;
    @XmlElement(name = "ExtensionOK", required = true)
    protected VerificationResultType extensionOK;

    /**
     * Gets the value of the extnId property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectIdentifierType }
     *     
     */
    public ObjectIdentifierType getExtnId() {
        return extnId;
    }

    /**
     * Sets the value of the extnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectIdentifierType }
     *     
     */
    public void setExtnId(ObjectIdentifierType value) {
        this.extnId = value;
    }

    /**
     * Gets the value of the critical property.
     * 
     */
    public boolean isCritical() {
        return critical;
    }

    /**
     * Sets the value of the critical property.
     * 
     */
    public void setCritical(boolean value) {
        this.critical = value;
    }

    /**
     * Gets the value of the extnValue property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getExtnValue() {
        return extnValue;
    }

    /**
     * Sets the value of the extnValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setExtnValue(AnyType value) {
        this.extnValue = value;
    }

    /**
     * Gets the value of the extensionOK property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getExtensionOK() {
        return extensionOK;
    }

    /**
     * Sets the value of the extensionOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setExtensionOK(VerificationResultType value) {
        this.extensionOK = value;
    }

}
