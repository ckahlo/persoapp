
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for CardApplicationServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardApplicationServiceType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *       &lt;sequence>
 *         &lt;element name="CardApplicationServiceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CardApplicationServiceDescription" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationServiceDescriptionType" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardApplicationServiceType", propOrder = {
    "cardApplicationServiceName",
    "cardApplicationServiceDescription",
    "other"
})
public class CardApplicationServiceType
    extends RequirementsType
{

    @XmlElement(name = "CardApplicationServiceName", required = true)
    protected String cardApplicationServiceName;
    @XmlElement(name = "CardApplicationServiceDescription")
    protected CardApplicationServiceDescriptionType cardApplicationServiceDescription;
    @XmlElement(name = "Other")
    protected AnyType other;

    /**
     * Gets the value of the cardApplicationServiceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardApplicationServiceName() {
        return cardApplicationServiceName;
    }

    /**
     * Sets the value of the cardApplicationServiceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardApplicationServiceName(String value) {
        this.cardApplicationServiceName = value;
    }

    /**
     * Gets the value of the cardApplicationServiceDescription property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationServiceDescriptionType }
     *     
     */
    public CardApplicationServiceDescriptionType getCardApplicationServiceDescription() {
        return cardApplicationServiceDescription;
    }

    /**
     * Sets the value of the cardApplicationServiceDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationServiceDescriptionType }
     *     
     */
    public void setCardApplicationServiceDescription(CardApplicationServiceDescriptionType value) {
        this.cardApplicationServiceDescription = value;
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

}
