
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ATRInterfaceBytesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ATRInterfaceBytesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TAi" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *         &lt;element name="TBi" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *         &lt;element name="TCi" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *         &lt;element name="TDi" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ATRInterfaceBytesType", propOrder = {
    "tAi",
    "tBi",
    "tCi",
    "tDi"
})
public class ATRInterfaceBytesType {

    @XmlElement(name = "TAi")
    protected ByteMaskType tAi;
    @XmlElement(name = "TBi")
    protected ByteMaskType tBi;
    @XmlElement(name = "TCi")
    protected ByteMaskType tCi;
    @XmlElement(name = "TDi")
    protected ByteMaskType tDi;

    /**
     * Gets the value of the tAi property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTAi() {
        return tAi;
    }

    /**
     * Sets the value of the tAi property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTAi(ByteMaskType value) {
        this.tAi = value;
    }

    /**
     * Gets the value of the tBi property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTBi() {
        return tBi;
    }

    /**
     * Sets the value of the tBi property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTBi(ByteMaskType value) {
        this.tBi = value;
    }

    /**
     * Gets the value of the tCi property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTCi() {
        return tCi;
    }

    /**
     * Sets the value of the tCi property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTCi(ByteMaskType value) {
        this.tCi = value;
    }

    /**
     * Gets the value of the tDi property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTDi() {
        return tDi;
    }

    /**
     * Sets the value of the tDi property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTDi(ByteMaskType value) {
        this.tDi = value;
    }

}
