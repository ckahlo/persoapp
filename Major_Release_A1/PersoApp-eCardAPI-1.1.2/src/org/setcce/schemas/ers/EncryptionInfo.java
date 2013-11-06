
package org.setcce.schemas.ers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for EncryptionInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncryptionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncryptionInfoType" type="{http://www.setcce.org/schemas/ers}ObjectIdentifier"/>
 *         &lt;element name="EncryptionInfoValue" type="{http://www.setcce.org/schemas/ers}OpenType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionInfo", propOrder = {
    "encryptionInfoType",
    "encryptionInfoValue"
})
public class EncryptionInfo {

    @XmlElement(name = "EncryptionInfoType", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String encryptionInfoType;
    @XmlElement(name = "EncryptionInfoValue", required = true)
    protected OpenType encryptionInfoValue;

    /**
     * Gets the value of the encryptionInfoType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptionInfoType() {
        return encryptionInfoType;
    }

    /**
     * Sets the value of the encryptionInfoType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptionInfoType(String value) {
        this.encryptionInfoType = value;
    }

    /**
     * Gets the value of the encryptionInfoValue property.
     * 
     * @return
     *     possible object is
     *     {@link OpenType }
     *     
     */
    public OpenType getEncryptionInfoValue() {
        return encryptionInfoValue;
    }

    /**
     * Sets the value of the encryptionInfoValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link OpenType }
     *     
     */
    public void setEncryptionInfoValue(OpenType value) {
        this.encryptionInfoValue = value;
    }

}
