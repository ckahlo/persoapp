
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ISO7816-4-CardServiceDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ISO7816-4-CardServiceDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}FileRefReqType">
 *       &lt;sequence>
 *         &lt;element name="Application-selection" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="by-full-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
 *                   &lt;element name="by-partial-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BER-TLV-data-objects-available" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="in-EF.DIR" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
 *                   &lt;element name="in-EF.ATR" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EF.x-access-services" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="ReadBinary" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
 *                   &lt;element name="ReadRecord" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
 *                   &lt;element name="GetData" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Root" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="Card-with-MF" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
 *                   &lt;element name="Card-without-MF" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ISO7816-4-CardServiceDataType", propOrder = {
    "applicationSelection",
    "bertlvDataObjectsAvailable",
    "efxAccessServices",
    "root"
})
public class ISO78164CardServiceDataType
    extends FileRefReqType
{

    @XmlElement(name = "Application-selection")
    protected ISO78164CardServiceDataType.ApplicationSelection applicationSelection;
    @XmlElement(name = "BER-TLV-data-objects-available")
    protected ISO78164CardServiceDataType.BERTLVDataObjectsAvailable bertlvDataObjectsAvailable;
    @XmlElement(name = "EF.x-access-services")
    protected ISO78164CardServiceDataType.EFXAccessServices efxAccessServices;
    @XmlElement(name = "Root")
    protected ISO78164CardServiceDataType.Root root;

    /**
     * Gets the value of the applicationSelection property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardServiceDataType.ApplicationSelection }
     *     
     */
    public ISO78164CardServiceDataType.ApplicationSelection getApplicationSelection() {
        return applicationSelection;
    }

    /**
     * Sets the value of the applicationSelection property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardServiceDataType.ApplicationSelection }
     *     
     */
    public void setApplicationSelection(ISO78164CardServiceDataType.ApplicationSelection value) {
        this.applicationSelection = value;
    }

    /**
     * Gets the value of the bertlvDataObjectsAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardServiceDataType.BERTLVDataObjectsAvailable }
     *     
     */
    public ISO78164CardServiceDataType.BERTLVDataObjectsAvailable getBERTLVDataObjectsAvailable() {
        return bertlvDataObjectsAvailable;
    }

    /**
     * Sets the value of the bertlvDataObjectsAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardServiceDataType.BERTLVDataObjectsAvailable }
     *     
     */
    public void setBERTLVDataObjectsAvailable(ISO78164CardServiceDataType.BERTLVDataObjectsAvailable value) {
        this.bertlvDataObjectsAvailable = value;
    }

    /**
     * Gets the value of the efxAccessServices property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardServiceDataType.EFXAccessServices }
     *     
     */
    public ISO78164CardServiceDataType.EFXAccessServices getEFXAccessServices() {
        return efxAccessServices;
    }

    /**
     * Sets the value of the efxAccessServices property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardServiceDataType.EFXAccessServices }
     *     
     */
    public void setEFXAccessServices(ISO78164CardServiceDataType.EFXAccessServices value) {
        this.efxAccessServices = value;
    }

    /**
     * Gets the value of the root property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardServiceDataType.Root }
     *     
     */
    public ISO78164CardServiceDataType.Root getRoot() {
        return root;
    }

    /**
     * Sets the value of the root property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardServiceDataType.Root }
     *     
     */
    public void setRoot(ISO78164CardServiceDataType.Root value) {
        this.root = value;
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
     *         &lt;element name="by-full-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
     *         &lt;element name="by-partial-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
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
        "byFullDFName",
        "byPartialDFName"
    })
    public static class ApplicationSelection {

        @XmlElement(name = "by-full-DF-name", required = true)
        protected BitReqType byFullDFName;
        @XmlElement(name = "by-partial-DF-name", required = true)
        protected BitReqType byPartialDFName;

        /**
         * Gets the value of the byFullDFName property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getByFullDFName() {
            return byFullDFName;
        }

        /**
         * Sets the value of the byFullDFName property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setByFullDFName(BitReqType value) {
            this.byFullDFName = value;
        }

        /**
         * Gets the value of the byPartialDFName property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getByPartialDFName() {
            return byPartialDFName;
        }

        /**
         * Sets the value of the byPartialDFName property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setByPartialDFName(BitReqType value) {
            this.byPartialDFName = value;
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
     *         &lt;element name="in-EF.DIR" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
     *         &lt;element name="in-EF.ATR" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
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
        "inEFDIR",
        "inEFATR"
    })
    public static class BERTLVDataObjectsAvailable {

        @XmlElement(name = "in-EF.DIR", required = true)
        protected BitReqType inEFDIR;
        @XmlElement(name = "in-EF.ATR", required = true)
        protected BitReqType inEFATR;

        /**
         * Gets the value of the inEFDIR property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getInEFDIR() {
            return inEFDIR;
        }

        /**
         * Sets the value of the inEFDIR property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setInEFDIR(BitReqType value) {
            this.inEFDIR = value;
        }

        /**
         * Gets the value of the inEFATR property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getInEFATR() {
            return inEFATR;
        }

        /**
         * Sets the value of the inEFATR property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setInEFATR(BitReqType value) {
            this.inEFATR = value;
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
     *       &lt;choice>
     *         &lt;element name="ReadBinary" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
     *         &lt;element name="ReadRecord" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
     *         &lt;element name="GetData" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "readBinary",
        "readRecord",
        "getData"
    })
    public static class EFXAccessServices {

        @XmlElement(name = "ReadBinary")
        protected BasicRequirementsType readBinary;
        @XmlElement(name = "ReadRecord")
        protected BasicRequirementsType readRecord;
        @XmlElement(name = "GetData")
        protected BasicRequirementsType getData;

        /**
         * Gets the value of the readBinary property.
         * 
         * @return
         *     possible object is
         *     {@link BasicRequirementsType }
         *     
         */
        public BasicRequirementsType getReadBinary() {
            return readBinary;
        }

        /**
         * Sets the value of the readBinary property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicRequirementsType }
         *     
         */
        public void setReadBinary(BasicRequirementsType value) {
            this.readBinary = value;
        }

        /**
         * Gets the value of the readRecord property.
         * 
         * @return
         *     possible object is
         *     {@link BasicRequirementsType }
         *     
         */
        public BasicRequirementsType getReadRecord() {
            return readRecord;
        }

        /**
         * Sets the value of the readRecord property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicRequirementsType }
         *     
         */
        public void setReadRecord(BasicRequirementsType value) {
            this.readRecord = value;
        }

        /**
         * Gets the value of the getData property.
         * 
         * @return
         *     possible object is
         *     {@link BasicRequirementsType }
         *     
         */
        public BasicRequirementsType getGetData() {
            return getData;
        }

        /**
         * Sets the value of the getData property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicRequirementsType }
         *     
         */
        public void setGetData(BasicRequirementsType value) {
            this.getData = value;
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
     *       &lt;choice>
     *         &lt;element name="Card-with-MF" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
     *         &lt;element name="Card-without-MF" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "cardWithMF",
        "cardWithoutMF"
    })
    public static class Root {

        @XmlElement(name = "Card-with-MF")
        protected BasicRequirementsType cardWithMF;
        @XmlElement(name = "Card-without-MF")
        protected BasicRequirementsType cardWithoutMF;

        /**
         * Gets the value of the cardWithMF property.
         * 
         * @return
         *     possible object is
         *     {@link BasicRequirementsType }
         *     
         */
        public BasicRequirementsType getCardWithMF() {
            return cardWithMF;
        }

        /**
         * Sets the value of the cardWithMF property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicRequirementsType }
         *     
         */
        public void setCardWithMF(BasicRequirementsType value) {
            this.cardWithMF = value;
        }

        /**
         * Gets the value of the cardWithoutMF property.
         * 
         * @return
         *     possible object is
         *     {@link BasicRequirementsType }
         *     
         */
        public BasicRequirementsType getCardWithoutMF() {
            return cardWithoutMF;
        }

        /**
         * Sets the value of the cardWithoutMF property.
         * 
         * @param value
         *     allowed object is
         *     {@link BasicRequirementsType }
         *     
         */
        public void setCardWithoutMF(BasicRequirementsType value) {
            this.cardWithoutMF = value;
        }

    }

}
