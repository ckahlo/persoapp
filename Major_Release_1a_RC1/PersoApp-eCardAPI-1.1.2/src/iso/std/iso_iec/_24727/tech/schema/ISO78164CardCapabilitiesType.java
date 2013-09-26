
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ISO7816-4-CardCapabilitiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ISO7816-4-CardCapabilitiesType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}FileRefReqType">
 *       &lt;sequence>
 *         &lt;element name="FirstSoftwareFunctionTable">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DF-selection" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="by-full-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                             &lt;element name="by-partial-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                             &lt;element name="by-path" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                             &lt;element name="by-file-identifier" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                             &lt;element name="implicit" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Short-EF-identifier" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                   &lt;element name="Record-number" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                   &lt;element name="Record-identifier" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SecondSoftwareFunctionTable">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EFs-of-TLV-structure" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                   &lt;element name="Behaviour-of-write-functions" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *                           &lt;sequence>
 *                             &lt;element name="Behaviour" type="{urn:iso:std:iso-iec:24727:tech:schema}WriteBehaviourType"/>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Data-unit-size-in-quartets" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *                           &lt;sequence>
 *                             &lt;element name="Exponent">
 *                               &lt;simpleType>
 *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                   &lt;minInclusive value="1"/>
 *                                   &lt;maxInclusive value="31"/>
 *                                 &lt;/restriction>
 *                               &lt;/simpleType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Value-FF-for-first-byte-of-BER-TLV-valid" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ThirdSoftwareFunctionTable">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Command-chaining" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                   &lt;element name="Extended-Lc-and-Le-fields" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
 *                   &lt;element name="Logical-Channel-support" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="LC-Number-by-Card" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
 *                             &lt;element name="LC-Number-by-IFD" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
 *                             &lt;element name="Number-of-Logical-Channels">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Maxium-Number">
 *                                         &lt;simpleType>
 *                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *                                             &lt;minInclusive value="1"/>
 *                                             &lt;maxInclusive value="8"/>
 *                                           &lt;/restriction>
 *                                         &lt;/simpleType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/extension>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
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
@XmlType(name = "ISO7816-4-CardCapabilitiesType", propOrder = {
    "firstSoftwareFunctionTable",
    "secondSoftwareFunctionTable",
    "thirdSoftwareFunctionTable"
})
public class ISO78164CardCapabilitiesType
    extends FileRefReqType
{

    @XmlElement(name = "FirstSoftwareFunctionTable", required = true)
    protected ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable firstSoftwareFunctionTable;
    @XmlElement(name = "SecondSoftwareFunctionTable", required = true)
    protected ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable secondSoftwareFunctionTable;
    @XmlElement(name = "ThirdSoftwareFunctionTable", required = true)
    protected ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable thirdSoftwareFunctionTable;

    /**
     * Gets the value of the firstSoftwareFunctionTable property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable }
     *     
     */
    public ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable getFirstSoftwareFunctionTable() {
        return firstSoftwareFunctionTable;
    }

    /**
     * Sets the value of the firstSoftwareFunctionTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable }
     *     
     */
    public void setFirstSoftwareFunctionTable(ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable value) {
        this.firstSoftwareFunctionTable = value;
    }

    /**
     * Gets the value of the secondSoftwareFunctionTable property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable }
     *     
     */
    public ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable getSecondSoftwareFunctionTable() {
        return secondSoftwareFunctionTable;
    }

    /**
     * Sets the value of the secondSoftwareFunctionTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable }
     *     
     */
    public void setSecondSoftwareFunctionTable(ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable value) {
        this.secondSoftwareFunctionTable = value;
    }

    /**
     * Gets the value of the thirdSoftwareFunctionTable property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable }
     *     
     */
    public ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable getThirdSoftwareFunctionTable() {
        return thirdSoftwareFunctionTable;
    }

    /**
     * Sets the value of the thirdSoftwareFunctionTable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable }
     *     
     */
    public void setThirdSoftwareFunctionTable(ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable value) {
        this.thirdSoftwareFunctionTable = value;
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
     *         &lt;element name="DF-selection" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="by-full-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *                   &lt;element name="by-partial-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *                   &lt;element name="by-path" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *                   &lt;element name="by-file-identifier" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *                   &lt;element name="implicit" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Short-EF-identifier" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *         &lt;element name="Record-number" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *         &lt;element name="Record-identifier" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
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
        "dfSelection",
        "shortEFIdentifier",
        "recordNumber",
        "recordIdentifier"
    })
    public static class FirstSoftwareFunctionTable {

        @XmlElement(name = "DF-selection")
        protected ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable.DFSelection dfSelection;
        @XmlElement(name = "Short-EF-identifier")
        protected BitReqType shortEFIdentifier;
        @XmlElement(name = "Record-number")
        protected BitReqType recordNumber;
        @XmlElement(name = "Record-identifier")
        protected BitReqType recordIdentifier;

        /**
         * Gets the value of the dfSelection property.
         * 
         * @return
         *     possible object is
         *     {@link ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable.DFSelection }
         *     
         */
        public ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable.DFSelection getDFSelection() {
            return dfSelection;
        }

        /**
         * Sets the value of the dfSelection property.
         * 
         * @param value
         *     allowed object is
         *     {@link ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable.DFSelection }
         *     
         */
        public void setDFSelection(ISO78164CardCapabilitiesType.FirstSoftwareFunctionTable.DFSelection value) {
            this.dfSelection = value;
        }

        /**
         * Gets the value of the shortEFIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getShortEFIdentifier() {
            return shortEFIdentifier;
        }

        /**
         * Sets the value of the shortEFIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setShortEFIdentifier(BitReqType value) {
            this.shortEFIdentifier = value;
        }

        /**
         * Gets the value of the recordNumber property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getRecordNumber() {
            return recordNumber;
        }

        /**
         * Sets the value of the recordNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setRecordNumber(BitReqType value) {
            this.recordNumber = value;
        }

        /**
         * Gets the value of the recordIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getRecordIdentifier() {
            return recordIdentifier;
        }

        /**
         * Sets the value of the recordIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setRecordIdentifier(BitReqType value) {
            this.recordIdentifier = value;
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
         *         &lt;element name="by-full-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
         *         &lt;element name="by-partial-DF-name" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
         *         &lt;element name="by-path" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
         *         &lt;element name="by-file-identifier" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
         *         &lt;element name="implicit" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
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
            "byPartialDFName",
            "byPath",
            "byFileIdentifier",
            "implicit"
        })
        public static class DFSelection {

            @XmlElement(name = "by-full-DF-name")
            protected BitReqType byFullDFName;
            @XmlElement(name = "by-partial-DF-name")
            protected BitReqType byPartialDFName;
            @XmlElement(name = "by-path")
            protected BitReqType byPath;
            @XmlElement(name = "by-file-identifier")
            protected BitReqType byFileIdentifier;
            protected BitReqType implicit;

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

            /**
             * Gets the value of the byPath property.
             * 
             * @return
             *     possible object is
             *     {@link BitReqType }
             *     
             */
            public BitReqType getByPath() {
                return byPath;
            }

            /**
             * Sets the value of the byPath property.
             * 
             * @param value
             *     allowed object is
             *     {@link BitReqType }
             *     
             */
            public void setByPath(BitReqType value) {
                this.byPath = value;
            }

            /**
             * Gets the value of the byFileIdentifier property.
             * 
             * @return
             *     possible object is
             *     {@link BitReqType }
             *     
             */
            public BitReqType getByFileIdentifier() {
                return byFileIdentifier;
            }

            /**
             * Sets the value of the byFileIdentifier property.
             * 
             * @param value
             *     allowed object is
             *     {@link BitReqType }
             *     
             */
            public void setByFileIdentifier(BitReqType value) {
                this.byFileIdentifier = value;
            }

            /**
             * Gets the value of the implicit property.
             * 
             * @return
             *     possible object is
             *     {@link BitReqType }
             *     
             */
            public BitReqType getImplicit() {
                return implicit;
            }

            /**
             * Sets the value of the implicit property.
             * 
             * @param value
             *     allowed object is
             *     {@link BitReqType }
             *     
             */
            public void setImplicit(BitReqType value) {
                this.implicit = value;
            }

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
     *         &lt;element name="EFs-of-TLV-structure" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *         &lt;element name="Behaviour-of-write-functions" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
     *                 &lt;sequence>
     *                   &lt;element name="Behaviour" type="{urn:iso:std:iso-iec:24727:tech:schema}WriteBehaviourType"/>
     *                 &lt;/sequence>
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Data-unit-size-in-quartets" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
     *                 &lt;sequence>
     *                   &lt;element name="Exponent">
     *                     &lt;simpleType>
     *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                         &lt;minInclusive value="1"/>
     *                         &lt;maxInclusive value="31"/>
     *                       &lt;/restriction>
     *                     &lt;/simpleType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Value-FF-for-first-byte-of-BER-TLV-valid" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
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
        "eFsOfTLVStructure",
        "behaviourOfWriteFunctions",
        "dataUnitSizeInQuartets",
        "valueFFForFirstByteOfBERTLVValid"
    })
    public static class SecondSoftwareFunctionTable {

        @XmlElement(name = "EFs-of-TLV-structure")
        protected BitReqType eFsOfTLVStructure;
        @XmlElement(name = "Behaviour-of-write-functions")
        protected ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.BehaviourOfWriteFunctions behaviourOfWriteFunctions;
        @XmlElement(name = "Data-unit-size-in-quartets")
        protected ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.DataUnitSizeInQuartets dataUnitSizeInQuartets;
        @XmlElement(name = "Value-FF-for-first-byte-of-BER-TLV-valid")
        protected BitReqType valueFFForFirstByteOfBERTLVValid;

        /**
         * Gets the value of the eFsOfTLVStructure property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getEFsOfTLVStructure() {
            return eFsOfTLVStructure;
        }

        /**
         * Sets the value of the eFsOfTLVStructure property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setEFsOfTLVStructure(BitReqType value) {
            this.eFsOfTLVStructure = value;
        }

        /**
         * Gets the value of the behaviourOfWriteFunctions property.
         * 
         * @return
         *     possible object is
         *     {@link ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.BehaviourOfWriteFunctions }
         *     
         */
        public ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.BehaviourOfWriteFunctions getBehaviourOfWriteFunctions() {
            return behaviourOfWriteFunctions;
        }

        /**
         * Sets the value of the behaviourOfWriteFunctions property.
         * 
         * @param value
         *     allowed object is
         *     {@link ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.BehaviourOfWriteFunctions }
         *     
         */
        public void setBehaviourOfWriteFunctions(ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.BehaviourOfWriteFunctions value) {
            this.behaviourOfWriteFunctions = value;
        }

        /**
         * Gets the value of the dataUnitSizeInQuartets property.
         * 
         * @return
         *     possible object is
         *     {@link ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.DataUnitSizeInQuartets }
         *     
         */
        public ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.DataUnitSizeInQuartets getDataUnitSizeInQuartets() {
            return dataUnitSizeInQuartets;
        }

        /**
         * Sets the value of the dataUnitSizeInQuartets property.
         * 
         * @param value
         *     allowed object is
         *     {@link ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.DataUnitSizeInQuartets }
         *     
         */
        public void setDataUnitSizeInQuartets(ISO78164CardCapabilitiesType.SecondSoftwareFunctionTable.DataUnitSizeInQuartets value) {
            this.dataUnitSizeInQuartets = value;
        }

        /**
         * Gets the value of the valueFFForFirstByteOfBERTLVValid property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getValueFFForFirstByteOfBERTLVValid() {
            return valueFFForFirstByteOfBERTLVValid;
        }

        /**
         * Sets the value of the valueFFForFirstByteOfBERTLVValid property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setValueFFForFirstByteOfBERTLVValid(BitReqType value) {
            this.valueFFForFirstByteOfBERTLVValid = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
         *       &lt;sequence>
         *         &lt;element name="Behaviour" type="{urn:iso:std:iso-iec:24727:tech:schema}WriteBehaviourType"/>
         *       &lt;/sequence>
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "behaviour"
        })
        public static class BehaviourOfWriteFunctions
            extends RequirementsType
        {

            @XmlElement(name = "Behaviour", required = true)
            protected WriteBehaviourType behaviour;

            /**
             * Gets the value of the behaviour property.
             * 
             * @return
             *     possible object is
             *     {@link WriteBehaviourType }
             *     
             */
            public WriteBehaviourType getBehaviour() {
                return behaviour;
            }

            /**
             * Sets the value of the behaviour property.
             * 
             * @param value
             *     allowed object is
             *     {@link WriteBehaviourType }
             *     
             */
            public void setBehaviour(WriteBehaviourType value) {
                this.behaviour = value;
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
         *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
         *       &lt;sequence>
         *         &lt;element name="Exponent">
         *           &lt;simpleType>
         *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *               &lt;minInclusive value="1"/>
         *               &lt;maxInclusive value="31"/>
         *             &lt;/restriction>
         *           &lt;/simpleType>
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
        @XmlType(name = "", propOrder = {
            "exponent"
        })
        public static class DataUnitSizeInQuartets
            extends RequirementsType
        {

            @XmlElement(name = "Exponent")
            protected int exponent;

            /**
             * Gets the value of the exponent property.
             * 
             */
            public int getExponent() {
                return exponent;
            }

            /**
             * Sets the value of the exponent property.
             * 
             */
            public void setExponent(int value) {
                this.exponent = value;
            }

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
     *         &lt;element name="Command-chaining" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *         &lt;element name="Extended-Lc-and-Le-fields" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType" minOccurs="0"/>
     *         &lt;element name="Logical-Channel-support" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="LC-Number-by-Card" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
     *                   &lt;element name="LC-Number-by-IFD" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
     *                   &lt;element name="Number-of-Logical-Channels">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
     *                           &lt;sequence>
     *                             &lt;element name="Maxium-Number">
     *                               &lt;simpleType>
     *                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
     *                                   &lt;minInclusive value="1"/>
     *                                   &lt;maxInclusive value="8"/>
     *                                 &lt;/restriction>
     *                               &lt;/simpleType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/extension>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "commandChaining",
        "extendedLcAndLeFields",
        "logicalChannelSupport"
    })
    public static class ThirdSoftwareFunctionTable {

        @XmlElement(name = "Command-chaining")
        protected BitReqType commandChaining;
        @XmlElement(name = "Extended-Lc-and-Le-fields")
        protected BitReqType extendedLcAndLeFields;
        @XmlElement(name = "Logical-Channel-support")
        protected ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport logicalChannelSupport;

        /**
         * Gets the value of the commandChaining property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getCommandChaining() {
            return commandChaining;
        }

        /**
         * Sets the value of the commandChaining property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setCommandChaining(BitReqType value) {
            this.commandChaining = value;
        }

        /**
         * Gets the value of the extendedLcAndLeFields property.
         * 
         * @return
         *     possible object is
         *     {@link BitReqType }
         *     
         */
        public BitReqType getExtendedLcAndLeFields() {
            return extendedLcAndLeFields;
        }

        /**
         * Sets the value of the extendedLcAndLeFields property.
         * 
         * @param value
         *     allowed object is
         *     {@link BitReqType }
         *     
         */
        public void setExtendedLcAndLeFields(BitReqType value) {
            this.extendedLcAndLeFields = value;
        }

        /**
         * Gets the value of the logicalChannelSupport property.
         * 
         * @return
         *     possible object is
         *     {@link ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport }
         *     
         */
        public ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport getLogicalChannelSupport() {
            return logicalChannelSupport;
        }

        /**
         * Sets the value of the logicalChannelSupport property.
         * 
         * @param value
         *     allowed object is
         *     {@link ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport }
         *     
         */
        public void setLogicalChannelSupport(ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport value) {
            this.logicalChannelSupport = value;
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
         *         &lt;element name="LC-Number-by-Card" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
         *         &lt;element name="LC-Number-by-IFD" type="{urn:iso:std:iso-iec:24727:tech:schema}BitReqType"/>
         *         &lt;element name="Number-of-Logical-Channels">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
         *                 &lt;sequence>
         *                   &lt;element name="Maxium-Number">
         *                     &lt;simpleType>
         *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
         *                         &lt;minInclusive value="1"/>
         *                         &lt;maxInclusive value="8"/>
         *                       &lt;/restriction>
         *                     &lt;/simpleType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/extension>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "lcNumberByCard",
            "lcNumberByIFD",
            "numberOfLogicalChannels"
        })
        public static class LogicalChannelSupport {

            @XmlElement(name = "LC-Number-by-Card", required = true)
            protected BitReqType lcNumberByCard;
            @XmlElement(name = "LC-Number-by-IFD", required = true)
            protected BitReqType lcNumberByIFD;
            @XmlElement(name = "Number-of-Logical-Channels", required = true)
            protected ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport.NumberOfLogicalChannels numberOfLogicalChannels;

            /**
             * Gets the value of the lcNumberByCard property.
             * 
             * @return
             *     possible object is
             *     {@link BitReqType }
             *     
             */
            public BitReqType getLCNumberByCard() {
                return lcNumberByCard;
            }

            /**
             * Sets the value of the lcNumberByCard property.
             * 
             * @param value
             *     allowed object is
             *     {@link BitReqType }
             *     
             */
            public void setLCNumberByCard(BitReqType value) {
                this.lcNumberByCard = value;
            }

            /**
             * Gets the value of the lcNumberByIFD property.
             * 
             * @return
             *     possible object is
             *     {@link BitReqType }
             *     
             */
            public BitReqType getLCNumberByIFD() {
                return lcNumberByIFD;
            }

            /**
             * Sets the value of the lcNumberByIFD property.
             * 
             * @param value
             *     allowed object is
             *     {@link BitReqType }
             *     
             */
            public void setLCNumberByIFD(BitReqType value) {
                this.lcNumberByIFD = value;
            }

            /**
             * Gets the value of the numberOfLogicalChannels property.
             * 
             * @return
             *     possible object is
             *     {@link ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport.NumberOfLogicalChannels }
             *     
             */
            public ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport.NumberOfLogicalChannels getNumberOfLogicalChannels() {
                return numberOfLogicalChannels;
            }

            /**
             * Sets the value of the numberOfLogicalChannels property.
             * 
             * @param value
             *     allowed object is
             *     {@link ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport.NumberOfLogicalChannels }
             *     
             */
            public void setNumberOfLogicalChannels(ISO78164CardCapabilitiesType.ThirdSoftwareFunctionTable.LogicalChannelSupport.NumberOfLogicalChannels value) {
                this.numberOfLogicalChannels = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
             *       &lt;sequence>
             *         &lt;element name="Maxium-Number">
             *           &lt;simpleType>
             *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
             *               &lt;minInclusive value="1"/>
             *               &lt;maxInclusive value="8"/>
             *             &lt;/restriction>
             *           &lt;/simpleType>
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
            @XmlType(name = "", propOrder = {
                "maxiumNumber"
            })
            public static class NumberOfLogicalChannels
                extends RequirementsType
            {

                @XmlElement(name = "Maxium-Number")
                protected int maxiumNumber;

                /**
                 * Gets the value of the maxiumNumber property.
                 * 
                 */
                public int getMaxiumNumber() {
                    return maxiumNumber;
                }

                /**
                 * Sets the value of the maxiumNumber property.
                 * 
                 */
                public void setMaxiumNumber(int value) {
                    this.maxiumNumber = value;
                }

            }

        }

    }

}
