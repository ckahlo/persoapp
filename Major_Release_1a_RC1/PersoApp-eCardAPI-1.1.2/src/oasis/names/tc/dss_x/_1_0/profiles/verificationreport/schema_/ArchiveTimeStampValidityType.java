
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

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


/**
 * <p>Java class for ArchiveTimeStampValidityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArchiveTimeStampValidityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormatOK" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}VerificationResultType"/>
 *         &lt;element name="DigestAlgorithm" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AlgorithmValidityType" minOccurs="0"/>
 *         &lt;element name="Attributes" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Attribute" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ReducedHashTree" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="PartialHashTree">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence maxOccurs="unbounded">
 *                             &lt;element name="HashValue" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}HashValueType"/>
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
 *         &lt;element name="TimeStamp" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}TimeStampValidityType"/>
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
@XmlType(name = "ArchiveTimeStampValidityType", propOrder = {
    "formatOK",
    "digestAlgorithm",
    "attributes",
    "reducedHashTree",
    "timeStamp"
})
public class ArchiveTimeStampValidityType {

    @XmlElement(name = "FormatOK", required = true)
    protected VerificationResultType formatOK;
    @XmlElement(name = "DigestAlgorithm")
    protected AlgorithmValidityType digestAlgorithm;
    @XmlElement(name = "Attributes")
    protected ArchiveTimeStampValidityType.Attributes attributes;
    @XmlElement(name = "ReducedHashTree")
    protected ArchiveTimeStampValidityType.ReducedHashTree reducedHashTree;
    @XmlElement(name = "TimeStamp", required = true)
    protected TimeStampValidityType timeStamp;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the formatOK property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationResultType }
     *     
     */
    public VerificationResultType getFormatOK() {
        return formatOK;
    }

    /**
     * Sets the value of the formatOK property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationResultType }
     *     
     */
    public void setFormatOK(VerificationResultType value) {
        this.formatOK = value;
    }

    /**
     * Gets the value of the digestAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmValidityType }
     *     
     */
    public AlgorithmValidityType getDigestAlgorithm() {
        return digestAlgorithm;
    }

    /**
     * Sets the value of the digestAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmValidityType }
     *     
     */
    public void setDigestAlgorithm(AlgorithmValidityType value) {
        this.digestAlgorithm = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link ArchiveTimeStampValidityType.Attributes }
     *     
     */
    public ArchiveTimeStampValidityType.Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchiveTimeStampValidityType.Attributes }
     *     
     */
    public void setAttributes(ArchiveTimeStampValidityType.Attributes value) {
        this.attributes = value;
    }

    /**
     * Gets the value of the reducedHashTree property.
     * 
     * @return
     *     possible object is
     *     {@link ArchiveTimeStampValidityType.ReducedHashTree }
     *     
     */
    public ArchiveTimeStampValidityType.ReducedHashTree getReducedHashTree() {
        return reducedHashTree;
    }

    /**
     * Sets the value of the reducedHashTree property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArchiveTimeStampValidityType.ReducedHashTree }
     *     
     */
    public void setReducedHashTree(ArchiveTimeStampValidityType.ReducedHashTree value) {
        this.reducedHashTree = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link TimeStampValidityType }
     *     
     */
    public TimeStampValidityType getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeStampValidityType }
     *     
     */
    public void setTimeStamp(TimeStampValidityType value) {
        this.timeStamp = value;
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
     *         &lt;element name="Attribute" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeType" maxOccurs="unbounded"/>
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
        "attribute"
    })
    public static class Attributes {

        @XmlElement(name = "Attribute", required = true)
        protected List<AttributeType> attribute;

        /**
         * Gets the value of the attribute property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the attribute property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAttribute().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AttributeType }
         * 
         * 
         */
        public List<AttributeType> getAttribute() {
            if (attribute == null) {
                attribute = new ArrayList<AttributeType>();
            }
            return this.attribute;
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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="PartialHashTree">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence maxOccurs="unbounded">
     *                   &lt;element name="HashValue" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}HashValueType"/>
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
        "partialHashTree"
    })
    public static class ReducedHashTree {

        @XmlElement(name = "PartialHashTree", required = true)
        protected List<ArchiveTimeStampValidityType.ReducedHashTree.PartialHashTree> partialHashTree;

        /**
         * Gets the value of the partialHashTree property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the partialHashTree property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPartialHashTree().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArchiveTimeStampValidityType.ReducedHashTree.PartialHashTree }
         * 
         * 
         */
        public List<ArchiveTimeStampValidityType.ReducedHashTree.PartialHashTree> getPartialHashTree() {
            if (partialHashTree == null) {
                partialHashTree = new ArrayList<ArchiveTimeStampValidityType.ReducedHashTree.PartialHashTree>();
            }
            return this.partialHashTree;
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
         *       &lt;sequence maxOccurs="unbounded">
         *         &lt;element name="HashValue" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}HashValueType"/>
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
            "hashValue"
        })
        public static class PartialHashTree {

            @XmlElement(name = "HashValue", required = true)
            protected List<HashValueType> hashValue;

            /**
             * Gets the value of the hashValue property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the hashValue property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getHashValue().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link HashValueType }
             * 
             * 
             */
            public List<HashValueType> getHashValue() {
                if (hashValue == null) {
                    hashValue = new ArrayList<HashValueType>();
                }
                return this.hashValue;
            }

        }

    }

}
