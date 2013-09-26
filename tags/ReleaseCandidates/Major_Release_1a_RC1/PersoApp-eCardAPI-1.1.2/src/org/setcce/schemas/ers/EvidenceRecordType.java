
package org.setcce.schemas.ers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EvidenceRecordType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EvidenceRecordType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncryptionInformation" type="{http://www.setcce.org/schemas/ers}EncryptionInfo" minOccurs="0"/>
 *         &lt;element name="ArchiveTimeStampSequence">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ArchiveTimeStampChain" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ArchiveTimeStamp" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="HashTree" type="{http://www.setcce.org/schemas/ers}HashTreeType" minOccurs="0"/>
 *                                       &lt;element name="TimeStamp">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="CryptographicInformation" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="Attributes" type="{http://www.setcce.org/schemas/ers}Attributes" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="CanonicalizationMethod" type="{http://www.setcce.org/schemas/ers}CanonicalizationMethodType"/>
 *                             &lt;element name="DigestMethod" type="{http://www.setcce.org/schemas/ers}DigestMethodType"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
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
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceRecordType", propOrder = {
    "encryptionInformation",
    "archiveTimeStampSequence"
})
public class EvidenceRecordType {

    @XmlElement(name = "EncryptionInformation")
    protected EncryptionInfo encryptionInformation;
    @XmlElement(name = "ArchiveTimeStampSequence", required = true)
    protected EvidenceRecordType.ArchiveTimeStampSequence archiveTimeStampSequence;
    @XmlAttribute(name = "Version", required = true)
    protected String version;

    /**
     * Gets the value of the encryptionInformation property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionInfo }
     *     
     */
    public EncryptionInfo getEncryptionInformation() {
        return encryptionInformation;
    }

    /**
     * Sets the value of the encryptionInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionInfo }
     *     
     */
    public void setEncryptionInformation(EncryptionInfo value) {
        this.encryptionInformation = value;
    }

    /**
     * Gets the value of the archiveTimeStampSequence property.
     * 
     * @return
     *     possible object is
     *     {@link EvidenceRecordType.ArchiveTimeStampSequence }
     *     
     */
    public EvidenceRecordType.ArchiveTimeStampSequence getArchiveTimeStampSequence() {
        return archiveTimeStampSequence;
    }

    /**
     * Sets the value of the archiveTimeStampSequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link EvidenceRecordType.ArchiveTimeStampSequence }
     *     
     */
    public void setArchiveTimeStampSequence(EvidenceRecordType.ArchiveTimeStampSequence value) {
        this.archiveTimeStampSequence = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     *         &lt;element name="ArchiveTimeStampChain" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ArchiveTimeStamp" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="HashTree" type="{http://www.setcce.org/schemas/ers}HashTreeType" minOccurs="0"/>
     *                             &lt;element name="TimeStamp">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="CryptographicInformation" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                             &lt;element name="Attributes" type="{http://www.setcce.org/schemas/ers}Attributes" minOccurs="0"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="CanonicalizationMethod" type="{http://www.setcce.org/schemas/ers}CanonicalizationMethodType"/>
     *                   &lt;element name="DigestMethod" type="{http://www.setcce.org/schemas/ers}DigestMethodType"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
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
        "archiveTimeStampChain"
    })
    public static class ArchiveTimeStampSequence {

        @XmlElement(name = "ArchiveTimeStampChain")
        protected List<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain> archiveTimeStampChain;

        /**
         * Gets the value of the archiveTimeStampChain property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the archiveTimeStampChain property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getArchiveTimeStampChain().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain }
         * 
         * 
         */
        public List<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain> getArchiveTimeStampChain() {
            if (archiveTimeStampChain == null) {
                archiveTimeStampChain = new ArrayList<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain>();
            }
            return this.archiveTimeStampChain;
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
         *         &lt;element name="ArchiveTimeStamp" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="HashTree" type="{http://www.setcce.org/schemas/ers}HashTreeType" minOccurs="0"/>
         *                   &lt;element name="TimeStamp">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="CryptographicInformation" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                   &lt;element name="Attributes" type="{http://www.setcce.org/schemas/ers}Attributes" minOccurs="0"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="CanonicalizationMethod" type="{http://www.setcce.org/schemas/ers}CanonicalizationMethodType"/>
         *         &lt;element name="DigestMethod" type="{http://www.setcce.org/schemas/ers}DigestMethodType"/>
         *       &lt;/sequence>
         *       &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "archiveTimeStamp",
            "canonicalizationMethod",
            "digestMethod"
        })
        public static class ArchiveTimeStampChain {

            @XmlElement(name = "ArchiveTimeStamp", required = true)
            protected List<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp> archiveTimeStamp;
            @XmlElement(name = "CanonicalizationMethod", required = true)
            protected CanonicalizationMethodType canonicalizationMethod;
            @XmlElement(name = "DigestMethod", required = true)
            protected DigestMethodType digestMethod;
            @XmlAttribute(name = "Order", required = true)
            protected BigInteger order;

            /**
             * Gets the value of the archiveTimeStamp property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the archiveTimeStamp property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getArchiveTimeStamp().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp }
             * 
             * 
             */
            public List<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp> getArchiveTimeStamp() {
                if (archiveTimeStamp == null) {
                    archiveTimeStamp = new ArrayList<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp>();
                }
                return this.archiveTimeStamp;
            }

            /**
             * Gets the value of the canonicalizationMethod property.
             * 
             * @return
             *     possible object is
             *     {@link CanonicalizationMethodType }
             *     
             */
            public CanonicalizationMethodType getCanonicalizationMethod() {
                return canonicalizationMethod;
            }

            /**
             * Sets the value of the canonicalizationMethod property.
             * 
             * @param value
             *     allowed object is
             *     {@link CanonicalizationMethodType }
             *     
             */
            public void setCanonicalizationMethod(CanonicalizationMethodType value) {
                this.canonicalizationMethod = value;
            }

            /**
             * Gets the value of the digestMethod property.
             * 
             * @return
             *     possible object is
             *     {@link DigestMethodType }
             *     
             */
            public DigestMethodType getDigestMethod() {
                return digestMethod;
            }

            /**
             * Sets the value of the digestMethod property.
             * 
             * @param value
             *     allowed object is
             *     {@link DigestMethodType }
             *     
             */
            public void setDigestMethod(DigestMethodType value) {
                this.digestMethod = value;
            }

            /**
             * Gets the value of the order property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getOrder() {
                return order;
            }

            /**
             * Sets the value of the order property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setOrder(BigInteger value) {
                this.order = value;
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
             *         &lt;element name="HashTree" type="{http://www.setcce.org/schemas/ers}HashTreeType" minOccurs="0"/>
             *         &lt;element name="TimeStamp">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="CryptographicInformation" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *         &lt;element name="Attributes" type="{http://www.setcce.org/schemas/ers}Attributes" minOccurs="0"/>
             *       &lt;/sequence>
             *       &lt;attribute name="Order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "hashTree",
                "timeStamp",
                "cryptographicInformation",
                "attributes"
            })
            public static class ArchiveTimeStamp {

                @XmlElement(name = "HashTree")
                protected HashTreeType hashTree;
                @XmlElement(name = "TimeStamp", required = true)
                protected EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp timeStamp;
                @XmlElement(name = "CryptographicInformation")
                protected List<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.CryptographicInformation> cryptographicInformation;
                @XmlElement(name = "Attributes")
                protected Attributes attributes;
                @XmlAttribute(name = "Order", required = true)
                protected BigInteger order;

                /**
                 * Gets the value of the hashTree property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link HashTreeType }
                 *     
                 */
                public HashTreeType getHashTree() {
                    return hashTree;
                }

                /**
                 * Sets the value of the hashTree property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link HashTreeType }
                 *     
                 */
                public void setHashTree(HashTreeType value) {
                    this.hashTree = value;
                }

                /**
                 * Gets the value of the timeStamp property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp }
                 *     
                 */
                public EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp getTimeStamp() {
                    return timeStamp;
                }

                /**
                 * Sets the value of the timeStamp property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp }
                 *     
                 */
                public void setTimeStamp(EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.TimeStamp value) {
                    this.timeStamp = value;
                }

                /**
                 * Gets the value of the cryptographicInformation property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the cryptographicInformation property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCryptographicInformation().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.CryptographicInformation }
                 * 
                 * 
                 */
                public List<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.CryptographicInformation> getCryptographicInformation() {
                    if (cryptographicInformation == null) {
                        cryptographicInformation = new ArrayList<EvidenceRecordType.ArchiveTimeStampSequence.ArchiveTimeStampChain.ArchiveTimeStamp.CryptographicInformation>();
                    }
                    return this.cryptographicInformation;
                }

                /**
                 * Gets the value of the attributes property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Attributes }
                 *     
                 */
                public Attributes getAttributes() {
                    return attributes;
                }

                /**
                 * Sets the value of the attributes property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Attributes }
                 *     
                 */
                public void setAttributes(Attributes value) {
                    this.attributes = value;
                }

                /**
                 * Gets the value of the order property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getOrder() {
                    return order;
                }

                /**
                 * Sets the value of the order property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setOrder(BigInteger value) {
                    this.order = value;
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
                 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
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
                    "content"
                })
                public static class CryptographicInformation {

                    @XmlMixed
                    @XmlAnyElement(lax = true)
                    protected List<Object> content;

                    /**
                     * Gets the value of the content property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the content property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getContent().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link Object }
                     * {@link String }
                     * 
                     * 
                     */
                    public List<Object> getContent() {
                        if (content == null) {
                            content = new ArrayList<Object>();
                        }
                        return this.content;
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
                 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
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
                    "content"
                })
                public static class TimeStamp {

                    @XmlMixed
                    @XmlAnyElement(lax = true)
                    protected List<Object> content;

                    /**
                     * Gets the value of the content property.
                     * 
                     * <p>
                     * This accessor method returns a reference to the live list,
                     * not a snapshot. Therefore any modification you make to the
                     * returned list will be present inside the JAXB object.
                     * This is why there is not a <CODE>set</CODE> method for the content property.
                     * 
                     * <p>
                     * For example, to add a new item, do as follows:
                     * <pre>
                     *    getContent().add(newItem);
                     * </pre>
                     * 
                     * 
                     * <p>
                     * Objects of the following type(s) are allowed in the list
                     * {@link Object }
                     * {@link String }
                     * 
                     * 
                     */
                    public List<Object> getContent() {
                        if (content == null) {
                            content = new ArrayList<Object>();
                        }
                        return this.content;
                    }

                }

            }

        }

    }

}
