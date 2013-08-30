
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CryptoKeyInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CryptoKeyInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KeyRef" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyRefType" minOccurs="0"/>
 *         &lt;element name="KeySize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="NonceSize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="SecretKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/>
 *           &lt;sequence>
 *             &lt;element name="PrivateKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType" minOccurs="0"/>
 *             &lt;element name="PublicKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/>
 *           &lt;/sequence>
 *           &lt;element name="generateFlag" type="{urn:iso:std:iso-iec:24727:tech:schema}NULL"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CryptoKeyInfoType", propOrder = {
    "keyRef",
    "keySize",
    "nonceSize",
    "secretKeyValue",
    "privateKeyValue",
    "publicKeyValue",
    "generateFlag"
})
public class CryptoKeyInfoType {

    @XmlElement(name = "KeyRef")
    protected KeyRefType keyRef;
    @XmlElement(name = "KeySize")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger keySize;
    @XmlElement(name = "NonceSize")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger nonceSize;
    @XmlElement(name = "SecretKeyValue")
    protected KeyValueType secretKeyValue;
    @XmlElement(name = "PrivateKeyValue")
    protected KeyValueType privateKeyValue;
    @XmlElement(name = "PublicKeyValue")
    protected KeyValueType publicKeyValue;
    protected NULL generateFlag;

    /**
     * Gets the value of the keyRef property.
     * 
     * @return
     *     possible object is
     *     {@link KeyRefType }
     *     
     */
    public KeyRefType getKeyRef() {
        return keyRef;
    }

    /**
     * Sets the value of the keyRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyRefType }
     *     
     */
    public void setKeyRef(KeyRefType value) {
        this.keyRef = value;
    }

    /**
     * Gets the value of the keySize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getKeySize() {
        return keySize;
    }

    /**
     * Sets the value of the keySize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setKeySize(BigInteger value) {
        this.keySize = value;
    }

    /**
     * Gets the value of the nonceSize property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNonceSize() {
        return nonceSize;
    }

    /**
     * Sets the value of the nonceSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNonceSize(BigInteger value) {
        this.nonceSize = value;
    }

    /**
     * Gets the value of the secretKeyValue property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getSecretKeyValue() {
        return secretKeyValue;
    }

    /**
     * Sets the value of the secretKeyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setSecretKeyValue(KeyValueType value) {
        this.secretKeyValue = value;
    }

    /**
     * Gets the value of the privateKeyValue property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getPrivateKeyValue() {
        return privateKeyValue;
    }

    /**
     * Sets the value of the privateKeyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setPrivateKeyValue(KeyValueType value) {
        this.privateKeyValue = value;
    }

    /**
     * Gets the value of the publicKeyValue property.
     * 
     * @return
     *     possible object is
     *     {@link KeyValueType }
     *     
     */
    public KeyValueType getPublicKeyValue() {
        return publicKeyValue;
    }

    /**
     * Sets the value of the publicKeyValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyValueType }
     *     
     */
    public void setPublicKeyValue(KeyValueType value) {
        this.publicKeyValue = value;
    }

    /**
     * Gets the value of the generateFlag property.
     * 
     * @return
     *     possible object is
     *     {@link NULL }
     *     
     */
    public NULL getGenerateFlag() {
        return generateFlag;
    }

    /**
     * Sets the value of the generateFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link NULL }
     *     
     */
    public void setGenerateFlag(NULL value) {
        this.generateFlag = value;
    }

}
