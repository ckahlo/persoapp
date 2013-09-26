
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ATSType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ATSType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TL" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
 *         &lt;element name="T0" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
 *         &lt;element name="InterfaceBytes" type="{urn:iso:std:iso-iec:24727:tech:schema}ATSInterfaceBytesType"/>
 *         &lt;element name="HistoricalBytes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="15" minOccurs="0">
 *                   &lt;element name="Ti" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CRC1" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
 *         &lt;element name="CRC2" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ATSType", propOrder = {
    "tl",
    "t0",
    "interfaceBytes",
    "historicalBytes",
    "crc1",
    "crc2"
})
public class ATSType {

    @XmlElement(name = "TL", required = true)
    protected ByteMaskType tl;
    @XmlElement(name = "T0", required = true)
    protected ByteMaskType t0;
    @XmlElement(name = "InterfaceBytes", required = true)
    protected ATSInterfaceBytesType interfaceBytes;
    @XmlElement(name = "HistoricalBytes", required = true)
    protected ATSType.HistoricalBytes historicalBytes;
    @XmlElement(name = "CRC1", required = true)
    protected ByteMaskType crc1;
    @XmlElement(name = "CRC2", required = true)
    protected ByteMaskType crc2;

    /**
     * Gets the value of the tl property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTL() {
        return tl;
    }

    /**
     * Sets the value of the tl property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTL(ByteMaskType value) {
        this.tl = value;
    }

    /**
     * Gets the value of the t0 property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getT0() {
        return t0;
    }

    /**
     * Sets the value of the t0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setT0(ByteMaskType value) {
        this.t0 = value;
    }

    /**
     * Gets the value of the interfaceBytes property.
     * 
     * @return
     *     possible object is
     *     {@link ATSInterfaceBytesType }
     *     
     */
    public ATSInterfaceBytesType getInterfaceBytes() {
        return interfaceBytes;
    }

    /**
     * Sets the value of the interfaceBytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATSInterfaceBytesType }
     *     
     */
    public void setInterfaceBytes(ATSInterfaceBytesType value) {
        this.interfaceBytes = value;
    }

    /**
     * Gets the value of the historicalBytes property.
     * 
     * @return
     *     possible object is
     *     {@link ATSType.HistoricalBytes }
     *     
     */
    public ATSType.HistoricalBytes getHistoricalBytes() {
        return historicalBytes;
    }

    /**
     * Sets the value of the historicalBytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATSType.HistoricalBytes }
     *     
     */
    public void setHistoricalBytes(ATSType.HistoricalBytes value) {
        this.historicalBytes = value;
    }

    /**
     * Gets the value of the crc1 property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getCRC1() {
        return crc1;
    }

    /**
     * Sets the value of the crc1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setCRC1(ByteMaskType value) {
        this.crc1 = value;
    }

    /**
     * Gets the value of the crc2 property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getCRC2() {
        return crc2;
    }

    /**
     * Sets the value of the crc2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setCRC2(ByteMaskType value) {
        this.crc2 = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="15" minOccurs="0">
     *         &lt;element name="Ti" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ti"
    })
    public static class HistoricalBytes {

        @XmlElement(name = "Ti")
        protected List<ByteMaskType> ti;

        /**
         * Gets the value of the ti property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ti property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTi().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ByteMaskType }
         * 
         * 
         */
        public List<ByteMaskType> getTi() {
            if (ti == null) {
                ti = new ArrayList<ByteMaskType>();
            }
            return this.ti;
        }

    }

}
