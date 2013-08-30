
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2000._09.xmldsig_.SignatureType;


/**
 * <p>Java class for CardInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardType" type="{urn:iso:std:iso-iec:24727:tech:schema}CardTypeType"/>
 *         &lt;element name="CardIdentification" type="{urn:iso:std:iso-iec:24727:tech:schema}CardIdentificationType"/>
 *         &lt;element name="CardCapabilities" type="{urn:iso:std:iso-iec:24727:tech:schema}CardCapabilitiesType" minOccurs="0"/>
 *         &lt;element name="ApplicationCapabilities" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationCapabilitiesType" minOccurs="0"/>
 *         &lt;element name="Signature" type="{http://www.w3.org/2000/09/xmldsig#}SignatureType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardInfoType", propOrder = {
    "cardType",
    "cardIdentification",
    "cardCapabilities",
    "applicationCapabilities",
    "signature"
})
public class CardInfoType {

    @XmlElement(name = "CardType", required = true)
    protected CardTypeType cardType;
    @XmlElement(name = "CardIdentification", required = true)
    protected CardIdentificationType cardIdentification;
    @XmlElement(name = "CardCapabilities")
    protected CardCapabilitiesType cardCapabilities;
    @XmlElement(name = "ApplicationCapabilities")
    protected ApplicationCapabilitiesType applicationCapabilities;
    @XmlElement(name = "Signature")
    protected List<SignatureType> signature;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the cardType property.
     * 
     * @return
     *     possible object is
     *     {@link CardTypeType }
     *     
     */
    public CardTypeType getCardType() {
        return cardType;
    }

    /**
     * Sets the value of the cardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardTypeType }
     *     
     */
    public void setCardType(CardTypeType value) {
        this.cardType = value;
    }

    /**
     * Gets the value of the cardIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link CardIdentificationType }
     *     
     */
    public CardIdentificationType getCardIdentification() {
        return cardIdentification;
    }

    /**
     * Sets the value of the cardIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardIdentificationType }
     *     
     */
    public void setCardIdentification(CardIdentificationType value) {
        this.cardIdentification = value;
    }

    /**
     * Gets the value of the cardCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link CardCapabilitiesType }
     *     
     */
    public CardCapabilitiesType getCardCapabilities() {
        return cardCapabilities;
    }

    /**
     * Sets the value of the cardCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardCapabilitiesType }
     *     
     */
    public void setCardCapabilities(CardCapabilitiesType value) {
        this.cardCapabilities = value;
    }

    /**
     * Gets the value of the applicationCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link ApplicationCapabilitiesType }
     *     
     */
    public ApplicationCapabilitiesType getApplicationCapabilities() {
        return applicationCapabilities;
    }

    /**
     * Sets the value of the applicationCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplicationCapabilitiesType }
     *     
     */
    public void setApplicationCapabilities(ApplicationCapabilitiesType value) {
        this.applicationCapabilities = value;
    }

    /**
     * Gets the value of the signature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignatureType }
     * 
     * 
     */
    public List<SignatureType> getSignature() {
        if (signature == null) {
            signature = new ArrayList<SignatureType>();
        }
        return this.signature;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
