
package oasis.names.tc.dss_x._1_0.profiles.encryption.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import org.w3._2000._09.xmldsig_.KeyInfoType;
import org.w3._2001._04.xmlenc_.EncryptionMethodType;


/**
 * <p>Java class for EncryptionKeySelectorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncryptionKeySelectorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}KeyInfo"/>
 *           &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *         &lt;/choice>
 *         &lt;element name="KeyEncryptionMethod" type="{http://www.w3.org/2001/04/xmlenc#}EncryptionMethodType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="EncryptedKeyId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CertificateValidation" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="urn:TODO:warn" />
 *       &lt;attribute name="IncludeEncryptedKey" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="AddReferenceList" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptionKeySelectorType", propOrder = {
    "keyInfo",
    "other",
    "keyEncryptionMethod"
})
public class EncryptionKeySelectorType {

    @XmlElement(name = "KeyInfo", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected KeyInfoType keyInfo;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlElement(name = "KeyEncryptionMethod")
    protected EncryptionMethodType keyEncryptionMethod;
    @XmlAttribute(name = "EncryptedKeyId")
    protected String encryptedKeyId;
    @XmlAttribute(name = "CertificateValidation")
    @XmlSchemaType(name = "anyURI")
    protected String certificateValidation;
    @XmlAttribute(name = "IncludeEncryptedKey")
    protected Boolean includeEncryptedKey;
    @XmlAttribute(name = "AddReferenceList")
    protected Boolean addReferenceList;

    /**
     * Gets the value of the keyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link KeyInfoType }
     *     
     */
    public KeyInfoType getKeyInfo() {
        return keyInfo;
    }

    /**
     * Sets the value of the keyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyInfoType }
     *     
     */
    public void setKeyInfo(KeyInfoType value) {
        this.keyInfo = value;
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
     * Gets the value of the keyEncryptionMethod property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionMethodType }
     *     
     */
    public EncryptionMethodType getKeyEncryptionMethod() {
        return keyEncryptionMethod;
    }

    /**
     * Sets the value of the keyEncryptionMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionMethodType }
     *     
     */
    public void setKeyEncryptionMethod(EncryptionMethodType value) {
        this.keyEncryptionMethod = value;
    }

    /**
     * Gets the value of the encryptedKeyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptedKeyId() {
        return encryptedKeyId;
    }

    /**
     * Sets the value of the encryptedKeyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptedKeyId(String value) {
        this.encryptedKeyId = value;
    }

    /**
     * Gets the value of the certificateValidation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateValidation() {
        if (certificateValidation == null) {
            return "urn:TODO:warn";
        } else {
            return certificateValidation;
        }
    }

    /**
     * Sets the value of the certificateValidation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateValidation(String value) {
        this.certificateValidation = value;
    }

    /**
     * Gets the value of the includeEncryptedKey property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIncludeEncryptedKey() {
        if (includeEncryptedKey == null) {
            return true;
        } else {
            return includeEncryptedKey;
        }
    }

    /**
     * Sets the value of the includeEncryptedKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeEncryptedKey(Boolean value) {
        this.includeEncryptedKey = value;
    }

    /**
     * Gets the value of the addReferenceList property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isAddReferenceList() {
        if (addReferenceList == null) {
            return false;
        } else {
            return addReferenceList;
        }
    }

    /**
     * Sets the value of the addReferenceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddReferenceList(Boolean value) {
        this.addReferenceList = value;
    }

}
