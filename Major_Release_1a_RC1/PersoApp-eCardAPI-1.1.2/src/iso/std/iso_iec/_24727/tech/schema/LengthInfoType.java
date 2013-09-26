
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LengthInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LengthInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MaxNc" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="MaxNe" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LengthInfoType", propOrder = {
    "maxNc",
    "maxNe"
})
public class LengthInfoType {

    @XmlElement(name = "MaxNc", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxNc;
    @XmlElement(name = "MaxNe", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger maxNe;

    /**
     * Gets the value of the maxNc property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNc() {
        return maxNc;
    }

    /**
     * Sets the value of the maxNc property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNc(BigInteger value) {
        this.maxNc = value;
    }

    /**
     * Gets the value of the maxNe property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNe() {
        return maxNe;
    }

    /**
     * Sets the value of the maxNe property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNe(BigInteger value) {
        this.maxNe = value;
    }

}
