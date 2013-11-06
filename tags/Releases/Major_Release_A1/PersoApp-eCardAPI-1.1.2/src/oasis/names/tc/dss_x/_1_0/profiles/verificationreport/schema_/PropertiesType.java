
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
 * <p>Java class for PropertiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PropertiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SignedProperties" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}SignedPropertiesType" minOccurs="0"/>
 *         &lt;element name="UnsignedProperties" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}UnsignedPropertiesType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Target" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropertiesType", propOrder = {
    "signedProperties",
    "unsignedProperties"
})
public class PropertiesType {

    @XmlElement(name = "SignedProperties")
    protected SignedPropertiesType signedProperties;
    @XmlElement(name = "UnsignedProperties")
    protected UnsignedPropertiesType unsignedProperties;
    @XmlAttribute(name = "Target")
    @XmlSchemaType(name = "anyURI")
    protected String target;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the signedProperties property.
     * 
     * @return
     *     possible object is
     *     {@link SignedPropertiesType }
     *     
     */
    public SignedPropertiesType getSignedProperties() {
        return signedProperties;
    }

    /**
     * Sets the value of the signedProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedPropertiesType }
     *     
     */
    public void setSignedProperties(SignedPropertiesType value) {
        this.signedProperties = value;
    }

    /**
     * Gets the value of the unsignedProperties property.
     * 
     * @return
     *     possible object is
     *     {@link UnsignedPropertiesType }
     *     
     */
    public UnsignedPropertiesType getUnsignedProperties() {
        return unsignedProperties;
    }

    /**
     * Sets the value of the unsignedProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnsignedPropertiesType }
     *     
     */
    public void setUnsignedProperties(UnsignedPropertiesType value) {
        this.unsignedProperties = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
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
