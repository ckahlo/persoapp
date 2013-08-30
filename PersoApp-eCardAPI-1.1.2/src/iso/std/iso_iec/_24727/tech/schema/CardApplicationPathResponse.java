
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="CardAppPathResultSet">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="CardApplicationPathResult" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "cardAppPathResultSet"
})
@XmlRootElement(name = "CardApplicationPathResponse")
public class CardApplicationPathResponse
    extends ResponseType
{

    @XmlElement(name = "CardAppPathResultSet", required = true)
    protected CardApplicationPathResponse.CardAppPathResultSet cardAppPathResultSet;

    /**
     * Gets the value of the cardAppPathResultSet property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationPathResponse.CardAppPathResultSet }
     *     
     */
    public CardApplicationPathResponse.CardAppPathResultSet getCardAppPathResultSet() {
        return cardAppPathResultSet;
    }

    /**
     * Sets the value of the cardAppPathResultSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationPathResponse.CardAppPathResultSet }
     *     
     */
    public void setCardAppPathResultSet(CardApplicationPathResponse.CardAppPathResultSet value) {
        this.cardAppPathResultSet = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="CardApplicationPathResult" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "cardApplicationPathResult"
    })
    public static class CardAppPathResultSet {

        @XmlElement(name = "CardApplicationPathResult")
        protected List<CardApplicationPathType> cardApplicationPathResult;

        /**
         * Gets the value of the cardApplicationPathResult property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cardApplicationPathResult property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCardApplicationPathResult().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CardApplicationPathType }
         * 
         * 
         */
        public List<CardApplicationPathType> getCardApplicationPathResult() {
            if (cardApplicationPathResult == null) {
                cardApplicationPathResult = new ArrayList<CardApplicationPathType>();
            }
            return this.cardApplicationPathResult;
        }

    }

}
