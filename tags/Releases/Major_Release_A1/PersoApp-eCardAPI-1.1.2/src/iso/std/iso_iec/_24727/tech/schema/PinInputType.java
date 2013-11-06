
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PinInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PinInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="PasswordAttributes" type="{urn:iso:std:iso-iec:24727:tech:schema}PasswordAttributesType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PinInputType", propOrder = {
    "index",
    "passwordAttributes"
})
public class PinInputType {

    @XmlElement(name = "Index", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger index;
    @XmlElement(name = "PasswordAttributes", required = true)
    protected PasswordAttributesType passwordAttributes;

    /**
     * Gets the value of the index property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIndex(BigInteger value) {
        this.index = value;
    }

    /**
     * Gets the value of the passwordAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordAttributesType }
     *     
     */
    public PasswordAttributesType getPasswordAttributes() {
        return passwordAttributes;
    }

    /**
     * Sets the value of the passwordAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordAttributesType }
     *     
     */
    public void setPasswordAttributes(PasswordAttributesType value) {
        this.passwordAttributes = value;
    }

}
