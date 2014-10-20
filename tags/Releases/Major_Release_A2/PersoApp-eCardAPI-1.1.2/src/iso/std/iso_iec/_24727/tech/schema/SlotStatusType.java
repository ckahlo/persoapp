
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
 * <p>Java class for SlotStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SlotStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="CardAvailable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ATRorATS" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SlotStatusType", propOrder = {
    "index",
    "cardAvailable",
    "atRorATS"
})
public class SlotStatusType {

    @XmlElement(name = "Index", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger index;
    @XmlElement(name = "CardAvailable")
    protected boolean cardAvailable;
    @XmlElement(name = "ATRorATS", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] atRorATS;

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
     * Gets the value of the cardAvailable property.
     * 
     */
    public boolean isCardAvailable() {
        return cardAvailable;
    }

    /**
     * Sets the value of the cardAvailable property.
     * 
     */
    public void setCardAvailable(boolean value) {
        this.cardAvailable = value;
    }

    /**
     * Gets the value of the atRorATS property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getATRorATS() {
        return atRorATS;
    }

    /**
     * Sets the value of the atRorATS property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setATRorATS(byte[] value) {
        this.atRorATS = ((byte[]) value);
    }

}
