
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CAKeyInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CAKeyInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="PrivateKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/>
 *         &lt;element name="generateFlag" type="{urn:iso:std:iso-iec:24727:tech:schema}NULL"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAKeyInfoType", propOrder = {
    "privateKeyValue",
    "generateFlag"
})
public class CAKeyInfoType {

    @XmlElement(name = "PrivateKeyValue")
    protected KeyValueType privateKeyValue;
    protected NULL generateFlag;

    /**
     * Gets the value of the privateKeyValue property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getPrivateKeyValue() {
        return privateKeyValue;
    }

    /**
     * Sets the value of the privateKeyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setPrivateKeyValue(KeyValueType value) {
        this.privateKeyValue = value;
    }

    /**
     * Gets the value of the generateFlag property.
     * 
     * @return
     *     possible object is
     *     {@link NULL }
     *     
     */
    public NULL getGenerateFlag() {
        return generateFlag;
    }

    /**
     * Sets the value of the generateFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link NULL }
     *     
     */
    public void setGenerateFlag(NULL value) {
        this.generateFlag = value;
    }

}
