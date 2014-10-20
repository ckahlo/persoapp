
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for PSSParameterType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PSSParameterType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HashAlgorithm" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType" minOccurs="0"/>
 *         &lt;element name="MaskGenAlgorithm" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType" minOccurs="0"/>
 *         &lt;element name="SaltLength" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="TrailerField" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PSSParameterType", propOrder = {
    "hashAlgorithm",
    "maskGenAlgorithm",
    "saltLength",
    "trailerField"
})
public class PSSParameterType {

    @XmlElement(name = "HashAlgorithm")
    protected AlgorithmIdentifierType hashAlgorithm;
    @XmlElement(name = "MaskGenAlgorithm")
    protected AlgorithmIdentifierType maskGenAlgorithm;
    @XmlElement(name = "SaltLength")
    protected BigInteger saltLength;
    @XmlElement(name = "TrailerField", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] trailerField;

    /**
     * Gets the value of the hashAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public AlgorithmIdentifierType getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Sets the value of the hashAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public void setHashAlgorithm(AlgorithmIdentifierType value) {
        this.hashAlgorithm = value;
    }

    /**
     * Gets the value of the maskGenAlgorithm property.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public AlgorithmIdentifierType getMaskGenAlgorithm() {
        return maskGenAlgorithm;
    }

    /**
     * Sets the value of the maskGenAlgorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public void setMaskGenAlgorithm(AlgorithmIdentifierType value) {
        this.maskGenAlgorithm = value;
    }

    /**
     * Gets the value of the saltLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSaltLength() {
        return saltLength;
    }

    /**
     * Sets the value of the saltLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSaltLength(BigInteger value) {
        this.saltLength = value;
    }

    /**
     * Gets the value of the trailerField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTrailerField() {
        return trailerField;
    }

    /**
     * Sets the value of the trailerField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailerField(byte[] value) {
        this.trailerField = ((byte[]) value);
    }

}
