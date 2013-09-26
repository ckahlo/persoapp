
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="CardApplicationPath" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/>
 *         &lt;element name="Output" type="{urn:iso:std:iso-iec:24727:tech:schema}OutputInfoType" minOccurs="0"/>
 *         &lt;element name="ExclusiveUse" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cardApplicationPath",
    "output",
    "exclusiveUse"
})
@XmlRootElement(name = "CardApplicationConnect")
public class CardApplicationConnect
    extends RequestType
{

    @XmlElement(name = "CardApplicationPath", required = true)
    protected CardApplicationPathType cardApplicationPath;
    @XmlElement(name = "Output")
    protected OutputInfoType output;
    @XmlElement(name = "ExclusiveUse", defaultValue = "false")
    protected Boolean exclusiveUse;

    /**
     * Gets the value of the cardApplicationPath property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationPathType }
     *     
     */
    public CardApplicationPathType getCardApplicationPath() {
        return cardApplicationPath;
    }

    /**
     * Sets the value of the cardApplicationPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationPathType }
     *     
     */
    public void setCardApplicationPath(CardApplicationPathType value) {
        this.cardApplicationPath = value;
    }

    /**
     * Gets the value of the output property.
     * 
     * @return
     *     possible object is
     *     {@link OutputInfoType }
     *     
     */
    public OutputInfoType getOutput() {
        return output;
    }

    /**
     * Sets the value of the output property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputInfoType }
     *     
     */
    public void setOutput(OutputInfoType value) {
        this.output = value;
    }

    /**
     * Gets the value of the exclusiveUse property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExclusiveUse() {
        return exclusiveUse;
    }

    /**
     * Sets the value of the exclusiveUse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExclusiveUse(Boolean value) {
        this.exclusiveUse = value;
    }

}
