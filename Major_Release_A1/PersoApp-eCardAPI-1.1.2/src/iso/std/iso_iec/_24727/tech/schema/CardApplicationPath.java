
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
 *         &lt;element name="CardAppPathRequest" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/>
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
    "cardAppPathRequest"
})
@XmlRootElement(name = "CardApplicationPath")
public class CardApplicationPath
    extends RequestType
{

    @XmlElement(name = "CardAppPathRequest", required = true)
    protected CardApplicationPathType cardAppPathRequest;

    /**
     * Gets the value of the cardAppPathRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationPathType }
     *     
     */
    public CardApplicationPathType getCardAppPathRequest() {
        return cardAppPathRequest;
    }

    /**
     * Sets the value of the cardAppPathRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationPathType }
     *     
     */
    public void setCardAppPathRequest(CardApplicationPathType value) {
        this.cardAppPathRequest = value;
    }

}
