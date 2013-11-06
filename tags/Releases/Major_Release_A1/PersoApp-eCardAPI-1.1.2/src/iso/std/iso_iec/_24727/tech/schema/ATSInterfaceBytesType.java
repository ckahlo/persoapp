
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ATSInterfaceBytesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ATSInterfaceBytesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TA1" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *         &lt;element name="TB1" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *         &lt;element name="TC1" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ATSInterfaceBytesType", propOrder = {
    "ta1",
    "tb1",
    "tc1"
})
public class ATSInterfaceBytesType {

    @XmlElement(name = "TA1")
    protected ByteMaskType ta1;
    @XmlElement(name = "TB1")
    protected ByteMaskType tb1;
    @XmlElement(name = "TC1")
    protected ByteMaskType tc1;

    /**
     * Gets the value of the ta1 property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTA1() {
        return ta1;
    }

    /**
     * Sets the value of the ta1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTA1(ByteMaskType value) {
        this.ta1 = value;
    }

    /**
     * Gets the value of the tb1 property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTB1() {
        return tb1;
    }

    /**
     * Sets the value of the tb1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTB1(ByteMaskType value) {
        this.tb1 = value;
    }

    /**
     * Gets the value of the tc1 property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTC1() {
        return tc1;
    }

    /**
     * Sets the value of the tc1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTC1(ByteMaskType value) {
        this.tc1 = value;
    }

}
