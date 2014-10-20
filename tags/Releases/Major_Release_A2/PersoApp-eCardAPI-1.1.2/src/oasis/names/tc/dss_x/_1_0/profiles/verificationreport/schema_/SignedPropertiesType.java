
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
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for SignedPropertiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignedPropertiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SignedSignatureProperties" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignedSignaturePropertiesType" minOccurs="0"/>
 *         &lt;element name="SignedDataObjectProperties" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignedDataObjectPropertiesType" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
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
@XmlType(name = "SignedPropertiesType", propOrder = {
    "signedSignatureProperties",
    "signedDataObjectProperties",
    "other"
})
public class SignedPropertiesType {

    @XmlElement(name = "SignedSignatureProperties")
    protected SignedSignaturePropertiesType signedSignatureProperties;
    @XmlElement(name = "SignedDataObjectProperties")
    protected SignedDataObjectPropertiesType signedDataObjectProperties;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the signedSignatureProperties property.
     * 
     * @return
     *     possible object is
     *     {@link SignedSignaturePropertiesType }
     *     
     */
    public SignedSignaturePropertiesType getSignedSignatureProperties() {
        return signedSignatureProperties;
    }

    /**
     * Sets the value of the signedSignatureProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedSignaturePropertiesType }
     *     
     */
    public void setSignedSignatureProperties(SignedSignaturePropertiesType value) {
        this.signedSignatureProperties = value;
    }

    /**
     * Gets the value of the signedDataObjectProperties property.
     * 
     * @return
     *     possible object is
     *     {@link SignedDataObjectPropertiesType }
     *     
     */
    public SignedDataObjectPropertiesType getSignedDataObjectProperties() {
        return signedDataObjectProperties;
    }

    /**
     * Sets the value of the signedDataObjectProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedDataObjectPropertiesType }
     *     
     */
    public void setSignedDataObjectProperties(SignedDataObjectPropertiesType value) {
        this.signedDataObjectProperties = value;
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
