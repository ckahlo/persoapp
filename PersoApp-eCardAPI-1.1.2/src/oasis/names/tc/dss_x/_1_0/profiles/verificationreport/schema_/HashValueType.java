
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for HashValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HashValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HashValue" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *       &lt;/sequence>
 *       &lt;attribute name="HashedObject" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HashValueType", propOrder = {
    "hashValue"
})
public class HashValueType {

    @XmlElement(name = "HashValue", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] hashValue;
    @XmlAttribute(name = "HashedObject")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object hashedObject;

    /**
     * Gets the value of the hashValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getHashValue() {
        return hashValue;
    }

    /**
     * Sets the value of the hashValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashValue(byte[] value) {
        this.hashValue = ((byte[]) value);
    }

    /**
     * Gets the value of the hashedObject property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHashedObject() {
        return hashedObject;
    }

    /**
     * Sets the value of the hashedObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHashedObject(Object value) {
        this.hashedObject = value;
    }

}
