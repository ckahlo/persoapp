
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ATRType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ATRType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TS" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
 *         &lt;element name="T0" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType"/>
 *         &lt;element name="InterfaceBytes">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Tx1" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
 *                   &lt;element name="Tx2" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
 *                   &lt;element name="Tx3" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
 *                   &lt;element name="Tx4" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="HistoricalBytes" minOccurs="0">
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
 *         &lt;element name="TCK" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteMaskType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ATRType", propOrder = {
    "ts",
    "t0",
    "interfaceBytes",
    "historicalBytes",
    "tck"
})
public class ATRType {

    @XmlElement(name = "TS", required = true)
    protected ByteMaskType ts;
    @XmlElement(name = "T0", required = true)
    protected ByteMaskType t0;
    @XmlElement(name = "InterfaceBytes", required = true)
    protected ATRType.InterfaceBytes interfaceBytes;
    @XmlElement(name = "HistoricalBytes")
    protected ATRType.HistoricalBytes historicalBytes;
    @XmlElement(name = "TCK")
    protected ByteMaskType tck;

    /**
     * Gets the value of the ts property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTS() {
        return ts;
    }

    /**
     * Sets the value of the ts property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTS(ByteMaskType value) {
        this.ts = value;
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
     *     {@link ATRType.InterfaceBytes }
     *     
     */
    public ATRType.InterfaceBytes getInterfaceBytes() {
        return interfaceBytes;
    }

    /**
     * Sets the value of the interfaceBytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATRType.InterfaceBytes }
     *     
     */
    public void setInterfaceBytes(ATRType.InterfaceBytes value) {
        this.interfaceBytes = value;
    }

    /**
     * Gets the value of the historicalBytes property.
     * 
     * @return
     *     possible object is
     *     {@link ATRType.HistoricalBytes }
     *     
     */
    public ATRType.HistoricalBytes getHistoricalBytes() {
        return historicalBytes;
    }

    /**
     * Sets the value of the historicalBytes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATRType.HistoricalBytes }
     *     
     */
    public void setHistoricalBytes(ATRType.HistoricalBytes value) {
        this.historicalBytes = value;
    }

    /**
     * Gets the value of the tck property.
     * 
     * @return
     *     possible object is
     *     {@link ByteMaskType }
     *     
     */
    public ByteMaskType getTCK() {
        return tck;
    }

    /**
     * Sets the value of the tck property.
     * 
     * @param value
     *     allowed object is
     *     {@link ByteMaskType }
     *     
     */
    public void setTCK(ByteMaskType value) {
        this.tck = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Tx1" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
     *         &lt;element name="Tx2" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
     *         &lt;element name="Tx3" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
     *         &lt;element name="Tx4" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRInterfaceBytesType"/>
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
        "tx1",
        "tx2",
        "tx3",
        "tx4"
    })
    public static class InterfaceBytes {

        @XmlElement(name = "Tx1", required = true)
        protected ATRInterfaceBytesType tx1;
        @XmlElement(name = "Tx2", required = true)
        protected ATRInterfaceBytesType tx2;
        @XmlElement(name = "Tx3", required = true)
        protected ATRInterfaceBytesType tx3;
        @XmlElement(name = "Tx4", required = true)
        protected ATRInterfaceBytesType tx4;

        /**
         * Gets the value of the tx1 property.
         * 
         * @return
         *     possible object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public ATRInterfaceBytesType getTx1() {
            return tx1;
        }

        /**
         * Sets the value of the tx1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public void setTx1(ATRInterfaceBytesType value) {
            this.tx1 = value;
        }

        /**
         * Gets the value of the tx2 property.
         * 
         * @return
         *     possible object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public ATRInterfaceBytesType getTx2() {
            return tx2;
        }

        /**
         * Sets the value of the tx2 property.
         * 
         * @param value
         *     allowed object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public void setTx2(ATRInterfaceBytesType value) {
            this.tx2 = value;
        }

        /**
         * Gets the value of the tx3 property.
         * 
         * @return
         *     possible object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public ATRInterfaceBytesType getTx3() {
            return tx3;
        }

        /**
         * Sets the value of the tx3 property.
         * 
         * @param value
         *     allowed object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public void setTx3(ATRInterfaceBytesType value) {
            this.tx3 = value;
        }

        /**
         * Gets the value of the tx4 property.
         * 
         * @return
         *     possible object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public ATRInterfaceBytesType getTx4() {
            return tx4;
        }

        /**
         * Sets the value of the tx4 property.
         * 
         * @param value
         *     allowed object is
         *     {@link ATRInterfaceBytesType }
         *     
         */
        public void setTx4(ATRInterfaceBytesType value) {
            this.tx4 = value;
        }

    }

}
