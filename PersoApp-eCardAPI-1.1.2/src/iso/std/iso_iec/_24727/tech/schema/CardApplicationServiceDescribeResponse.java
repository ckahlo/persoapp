
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
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="ServiceDescription" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationServiceDescriptionType"/>
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
    "serviceDescription"
})
@XmlRootElement(name = "CardApplicationServiceDescribeResponse")
public class CardApplicationServiceDescribeResponse
    extends ResponseType
{

    @XmlElement(name = "ServiceDescription", required = true)
    protected CardApplicationServiceDescriptionType serviceDescription;

    /**
     * Gets the value of the serviceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationServiceDescriptionType }
     *     
     */
    public CardApplicationServiceDescriptionType getServiceDescription() {
        return serviceDescription;
    }

    /**
     * Sets the value of the serviceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationServiceDescriptionType }
     *     
     */
    public void setServiceDescription(CardApplicationServiceDescriptionType value) {
        this.serviceDescription = value;
    }

}
