
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.InternationalStringType;


/**
 * <p>Java class for CardTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProfilingInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="BasisSpecification" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                   &lt;element name="ProfilingRelation" type="{urn:iso:std:iso-iec:24727:tech:schema}ProfilingType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ObjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="SpecificationBodyOrIssuer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardTypeName" type="{urn:oasis:names:tc:dss:1.0:core:schema}InternationalStringType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Version" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="SubMinor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="CardInfoRepository" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardTypeType", propOrder = {
    "profilingInfo",
    "objectIdentifier",
    "specificationBodyOrIssuer",
    "cardTypeName",
    "version",
    "status",
    "date",
    "cardInfoRepository",
    "other"
})
public class CardTypeType {

    @XmlElement(name = "ProfilingInfo")
    protected CardTypeType.ProfilingInfo profilingInfo;
    @XmlElement(name = "ObjectIdentifier", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String objectIdentifier;
    @XmlElement(name = "SpecificationBodyOrIssuer")
    protected String specificationBodyOrIssuer;
    @XmlElement(name = "CardTypeName")
    protected List<InternationalStringType> cardTypeName;
    @XmlElement(name = "Version")
    protected CardTypeType.Version version;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "CardInfoRepository")
    @XmlSchemaType(name = "anyURI")
    protected String cardInfoRepository;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the profilingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CardTypeType.ProfilingInfo }
     *     
     */
    public CardTypeType.ProfilingInfo getProfilingInfo() {
        return profilingInfo;
    }

    /**
     * Sets the value of the profilingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardTypeType.ProfilingInfo }
     *     
     */
    public void setProfilingInfo(CardTypeType.ProfilingInfo value) {
        this.profilingInfo = value;
    }

    /**
     * Gets the value of the objectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectIdentifier() {
        return objectIdentifier;
    }

    /**
     * Sets the value of the objectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectIdentifier(String value) {
        this.objectIdentifier = value;
    }

    /**
     * Gets the value of the specificationBodyOrIssuer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecificationBodyOrIssuer() {
        return specificationBodyOrIssuer;
    }

    /**
     * Sets the value of the specificationBodyOrIssuer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecificationBodyOrIssuer(String value) {
        this.specificationBodyOrIssuer = value;
    }

    /**
     * Gets the value of the cardTypeName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardTypeName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardTypeName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InternationalStringType }
     * 
     * 
     */
    public List<InternationalStringType> getCardTypeName() {
        if (cardTypeName == null) {
            cardTypeName = new ArrayList<InternationalStringType>();
        }
        return this.cardTypeName;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link CardTypeType.Version }
     *     
     */
    public CardTypeType.Version getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardTypeType.Version }
     *     
     */
    public void setVersion(CardTypeType.Version value) {
        this.version = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the cardInfoRepository property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardInfoRepository() {
        return cardInfoRepository;
    }

    /**
     * Sets the value of the cardInfoRepository property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardInfoRepository(String value) {
        this.cardInfoRepository = value;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setOther(AnyType value) {
        this.other = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
     *         &lt;element name="BasisSpecification" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
     *         &lt;element name="ProfilingRelation" type="{urn:iso:std:iso-iec:24727:tech:schema}ProfilingType"/>
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
        "basisSpecification",
        "profilingRelation"
    })
    public static class ProfilingInfo {

        @XmlElement(name = "BasisSpecification", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String basisSpecification;
        @XmlElement(name = "ProfilingRelation", required = true)
        protected ProfilingType profilingRelation;

        /**
         * Gets the value of the basisSpecification property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBasisSpecification() {
            return basisSpecification;
        }

        /**
         * Sets the value of the basisSpecification property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBasisSpecification(String value) {
            this.basisSpecification = value;
        }

        /**
         * Gets the value of the profilingRelation property.
         * 
         * @return
         *     possible object is
         *     {@link ProfilingType }
         *     
         */
        public ProfilingType getProfilingRelation() {
            return profilingRelation;
        }

        /**
         * Sets the value of the profilingRelation property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProfilingType }
         *     
         */
        public void setProfilingRelation(ProfilingType value) {
            this.profilingRelation = value;
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
     *         &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="SubMinor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "major",
        "minor",
        "subMinor"
    })
    public static class Version {

        @XmlElement(name = "Major", required = true)
        protected String major;
        @XmlElement(name = "Minor")
        protected String minor;
        @XmlElement(name = "SubMinor")
        protected String subMinor;

        /**
         * Gets the value of the major property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMajor() {
            return major;
        }

        /**
         * Sets the value of the major property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMajor(String value) {
            this.major = value;
        }

        /**
         * Gets the value of the minor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMinor() {
            return minor;
        }

        /**
         * Sets the value of the minor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMinor(String value) {
            this.minor = value;
        }

        /**
         * Gets the value of the subMinor property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubMinor() {
            return subMinor;
        }

        /**
         * Sets the value of the subMinor property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubMinor(String value) {
            this.subMinor = value;
        }

    }

}
