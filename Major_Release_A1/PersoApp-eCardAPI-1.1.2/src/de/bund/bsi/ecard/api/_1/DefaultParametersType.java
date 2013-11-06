
package de.bund.bsi.ecard.api._1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import iso.std.iso_iec._24727.tech.schema.ChannelHandleType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for DefaultParametersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DefaultParametersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DefaultFrameworkBehaviour">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="VerbosityLevel" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *                   &lt;element name="VerifyAddedIdentity">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="AddTrustedIdentityCheckAlgorithm" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                             &lt;element name="AddCertificate" type="{http://www.bsi.bund.de/ecard/api/1.1}AddCertificateOptionsType"/>
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
 *         &lt;element name="DefaultSignOptions" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *         &lt;element name="DefaultVerifyOptions" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *         &lt;element name="DefaultEncryptOptions" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *         &lt;element name="DefaultDecryptOptions" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *         &lt;element name="DefaultHashAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="DefaultTSA" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DefaultMessages">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="LocalizedMessages" type="{http://www.bsi.bund.de/ecard/api/1.1}LocalizedMessagesType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="UpdateService">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Address" type="{urn:iso:std:iso-iec:24727:tech:schema}ChannelHandleType"/>
 *                   &lt;element name="UpdateFrequency" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
 *                   &lt;element name="AutomaticInstallation" type="{http://www.bsi.bund.de/ecard/api/1.1}UpdatePriorityType" maxOccurs="3" minOccurs="0"/>
 *                   &lt;element name="Other" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="DefaultCardInfoRepository" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="OtherParameters" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DefaultParametersType", propOrder = {
    "defaultFrameworkBehaviour",
    "defaultSignOptions",
    "defaultVerifyOptions",
    "defaultEncryptOptions",
    "defaultDecryptOptions",
    "defaultHashAlgorithm",
    "defaultTSA",
    "defaultMessages",
    "updateService",
    "defaultCardInfoRepository",
    "otherParameters"
})
public class DefaultParametersType {

    @XmlElement(name = "DefaultFrameworkBehaviour", required = true)
    protected DefaultParametersType.DefaultFrameworkBehaviour defaultFrameworkBehaviour;
    @XmlElement(name = "DefaultSignOptions", required = true)
    protected AnyType defaultSignOptions;
    @XmlElement(name = "DefaultVerifyOptions", required = true)
    protected AnyType defaultVerifyOptions;
    @XmlElement(name = "DefaultEncryptOptions", required = true)
    protected AnyType defaultEncryptOptions;
    @XmlElement(name = "DefaultDecryptOptions", required = true)
    protected AnyType defaultDecryptOptions;
    @XmlElement(name = "DefaultHashAlgorithm", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String defaultHashAlgorithm;
    @XmlElement(name = "DefaultTSA")
    protected List<String> defaultTSA;
    @XmlElement(name = "DefaultMessages", required = true)
    protected DefaultParametersType.DefaultMessages defaultMessages;
    @XmlElement(name = "UpdateService", required = true)
    protected DefaultParametersType.UpdateService updateService;
    @XmlElement(name = "DefaultCardInfoRepository")
    @XmlSchemaType(name = "anyURI")
    protected String defaultCardInfoRepository;
    @XmlElement(name = "OtherParameters")
    protected Object otherParameters;

    /**
     * Gets the value of the defaultFrameworkBehaviour property.
     * 
     * @return
     *     possible object is
     *     {@link DefaultParametersType.DefaultFrameworkBehaviour }
     *     
     */
    public DefaultParametersType.DefaultFrameworkBehaviour getDefaultFrameworkBehaviour() {
        return defaultFrameworkBehaviour;
    }

    /**
     * Sets the value of the defaultFrameworkBehaviour property.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultParametersType.DefaultFrameworkBehaviour }
     *     
     */
    public void setDefaultFrameworkBehaviour(DefaultParametersType.DefaultFrameworkBehaviour value) {
        this.defaultFrameworkBehaviour = value;
    }

    /**
     * Gets the value of the defaultSignOptions property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getDefaultSignOptions() {
        return defaultSignOptions;
    }

    /**
     * Sets the value of the defaultSignOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setDefaultSignOptions(AnyType value) {
        this.defaultSignOptions = value;
    }

    /**
     * Gets the value of the defaultVerifyOptions property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getDefaultVerifyOptions() {
        return defaultVerifyOptions;
    }

    /**
     * Sets the value of the defaultVerifyOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setDefaultVerifyOptions(AnyType value) {
        this.defaultVerifyOptions = value;
    }

    /**
     * Gets the value of the defaultEncryptOptions property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getDefaultEncryptOptions() {
        return defaultEncryptOptions;
    }

    /**
     * Sets the value of the defaultEncryptOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setDefaultEncryptOptions(AnyType value) {
        this.defaultEncryptOptions = value;
    }

    /**
     * Gets the value of the defaultDecryptOptions property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getDefaultDecryptOptions() {
        return defaultDecryptOptions;
    }

    /**
     * Sets the value of the defaultDecryptOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setDefaultDecryptOptions(AnyType value) {
        this.defaultDecryptOptions = value;
    }

    /**
     * Gets the value of the defaultHashAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultHashAlgorithm() {
        return defaultHashAlgorithm;
    }

    /**
     * Sets the value of the defaultHashAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultHashAlgorithm(String value) {
        this.defaultHashAlgorithm = value;
    }

    /**
     * Gets the value of the defaultTSA property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the defaultTSA property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefaultTSA().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDefaultTSA() {
        if (defaultTSA == null) {
            defaultTSA = new ArrayList<String>();
        }
        return this.defaultTSA;
    }

    /**
     * Gets the value of the defaultMessages property.
     * 
     * @return
     *     possible object is
     *     {@link DefaultParametersType.DefaultMessages }
     *     
     */
    public DefaultParametersType.DefaultMessages getDefaultMessages() {
        return defaultMessages;
    }

    /**
     * Sets the value of the defaultMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultParametersType.DefaultMessages }
     *     
     */
    public void setDefaultMessages(DefaultParametersType.DefaultMessages value) {
        this.defaultMessages = value;
    }

    /**
     * Gets the value of the updateService property.
     * 
     * @return
     *     possible object is
     *     {@link DefaultParametersType.UpdateService }
     *     
     */
    public DefaultParametersType.UpdateService getUpdateService() {
        return updateService;
    }

    /**
     * Sets the value of the updateService property.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultParametersType.UpdateService }
     *     
     */
    public void setUpdateService(DefaultParametersType.UpdateService value) {
        this.updateService = value;
    }

    /**
     * Gets the value of the defaultCardInfoRepository property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultCardInfoRepository() {
        return defaultCardInfoRepository;
    }

    /**
     * Sets the value of the defaultCardInfoRepository property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultCardInfoRepository(String value) {
        this.defaultCardInfoRepository = value;
    }

    /**
     * Gets the value of the otherParameters property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOtherParameters() {
        return otherParameters;
    }

    /**
     * Sets the value of the otherParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOtherParameters(Object value) {
        this.otherParameters = value;
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
     *         &lt;element name="VerbosityLevel" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
     *         &lt;element name="VerifyAddedIdentity">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="AddTrustedIdentityCheckAlgorithm" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *                   &lt;element name="AddCertificate" type="{http://www.bsi.bund.de/ecard/api/1.1}AddCertificateOptionsType"/>
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
        "verbosityLevel",
        "verifyAddedIdentity"
    })
    public static class DefaultFrameworkBehaviour {

        @XmlElement(name = "VerbosityLevel", required = true)
        @XmlSchemaType(name = "nonNegativeInteger")
        protected BigInteger verbosityLevel;
        @XmlElement(name = "VerifyAddedIdentity", required = true)
        protected DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity verifyAddedIdentity;

        /**
         * Gets the value of the verbosityLevel property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getVerbosityLevel() {
            return verbosityLevel;
        }

        /**
         * Sets the value of the verbosityLevel property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setVerbosityLevel(BigInteger value) {
            this.verbosityLevel = value;
        }

        /**
         * Gets the value of the verifyAddedIdentity property.
         * 
         * @return
         *     possible object is
         *     {@link DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity }
         *     
         */
        public DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity getVerifyAddedIdentity() {
            return verifyAddedIdentity;
        }

        /**
         * Sets the value of the verifyAddedIdentity property.
         * 
         * @param value
         *     allowed object is
         *     {@link DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity }
         *     
         */
        public void setVerifyAddedIdentity(DefaultParametersType.DefaultFrameworkBehaviour.VerifyAddedIdentity value) {
            this.verifyAddedIdentity = value;
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
         *         &lt;element name="AddTrustedIdentityCheckAlgorithm" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
         *         &lt;element name="AddCertificate" type="{http://www.bsi.bund.de/ecard/api/1.1}AddCertificateOptionsType"/>
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
            "addTrustedIdentityCheckAlgorithm",
            "addCertificate"
        })
        public static class VerifyAddedIdentity {

            @XmlElement(name = "AddTrustedIdentityCheckAlgorithm")
            protected boolean addTrustedIdentityCheckAlgorithm;
            @XmlElement(name = "AddCertificate", required = true)
            protected AddCertificateOptionsType addCertificate;

            /**
             * Gets the value of the addTrustedIdentityCheckAlgorithm property.
             * 
             */
            public boolean isAddTrustedIdentityCheckAlgorithm() {
                return addTrustedIdentityCheckAlgorithm;
            }

            /**
             * Sets the value of the addTrustedIdentityCheckAlgorithm property.
             * 
             */
            public void setAddTrustedIdentityCheckAlgorithm(boolean value) {
                this.addTrustedIdentityCheckAlgorithm = value;
            }

            /**
             * Gets the value of the addCertificate property.
             * 
             * @return
             *     possible object is
             *     {@link AddCertificateOptionsType }
             *     
             */
            public AddCertificateOptionsType getAddCertificate() {
                return addCertificate;
            }

            /**
             * Sets the value of the addCertificate property.
             * 
             * @param value
             *     allowed object is
             *     {@link AddCertificateOptionsType }
             *     
             */
            public void setAddCertificate(AddCertificateOptionsType value) {
                this.addCertificate = value;
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
     *         &lt;element name="LocalizedMessages" type="{http://www.bsi.bund.de/ecard/api/1.1}LocalizedMessagesType" maxOccurs="unbounded"/>
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
        "localizedMessages"
    })
    public static class DefaultMessages {

        @XmlElement(name = "LocalizedMessages", required = true)
        protected List<LocalizedMessagesType> localizedMessages;

        /**
         * Gets the value of the localizedMessages property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the localizedMessages property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLocalizedMessages().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LocalizedMessagesType }
         * 
         * 
         */
        public List<LocalizedMessagesType> getLocalizedMessages() {
            if (localizedMessages == null) {
                localizedMessages = new ArrayList<LocalizedMessagesType>();
            }
            return this.localizedMessages;
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
     *         &lt;element name="Address" type="{urn:iso:std:iso-iec:24727:tech:schema}ChannelHandleType"/>
     *         &lt;element name="UpdateFrequency" type="{http://www.w3.org/2001/XMLSchema}time" minOccurs="0"/>
     *         &lt;element name="AutomaticInstallation" type="{http://www.bsi.bund.de/ecard/api/1.1}UpdatePriorityType" maxOccurs="3" minOccurs="0"/>
     *         &lt;element name="Other" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
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
        "address",
        "updateFrequency",
        "automaticInstallation",
        "other"
    })
    public static class UpdateService {

        @XmlElement(name = "Address", required = true)
        protected ChannelHandleType address;
        @XmlElement(name = "UpdateFrequency")
        @XmlSchemaType(name = "time")
        protected XMLGregorianCalendar updateFrequency;
        @XmlElement(name = "AutomaticInstallation")
        protected List<UpdatePriorityType> automaticInstallation;
        @XmlElement(name = "Other")
        protected Object other;

        /**
         * Gets the value of the address property.
         * 
         * @return
         *     possible object is
         *     {@link ChannelHandleType }
         *     
         */
        public ChannelHandleType getAddress() {
            return address;
        }

        /**
         * Sets the value of the address property.
         * 
         * @param value
         *     allowed object is
         *     {@link ChannelHandleType }
         *     
         */
        public void setAddress(ChannelHandleType value) {
            this.address = value;
        }

        /**
         * Gets the value of the updateFrequency property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getUpdateFrequency() {
            return updateFrequency;
        }

        /**
         * Sets the value of the updateFrequency property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setUpdateFrequency(XMLGregorianCalendar value) {
            this.updateFrequency = value;
        }

        /**
         * Gets the value of the automaticInstallation property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the automaticInstallation property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAutomaticInstallation().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link UpdatePriorityType }
         * 
         * 
         */
        public List<UpdatePriorityType> getAutomaticInstallation() {
            if (automaticInstallation == null) {
                automaticInstallation = new ArrayList<UpdatePriorityType>();
            }
            return this.automaticInstallation;
        }

        /**
         * Gets the value of the other property.
         * 
         * @return
         *     possible object is
         *     {@link Object }
         *     
         */
        public Object getOther() {
            return other;
        }

        /**
         * Sets the value of the other property.
         * 
         * @param value
         *     allowed object is
         *     {@link Object }
         *     
         */
        public void setOther(Object value) {
            this.other = value;
        }

    }

}
