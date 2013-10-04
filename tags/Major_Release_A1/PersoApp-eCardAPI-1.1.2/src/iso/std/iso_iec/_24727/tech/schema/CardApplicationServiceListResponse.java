
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
 *         &lt;element name="CardApplicationServiceNameList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CardApplicationServiceName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
    "cardApplicationServiceNameList"
})
@XmlRootElement(name = "CardApplicationServiceListResponse")
public class CardApplicationServiceListResponse
    extends ResponseType
{

    @XmlElement(name = "CardApplicationServiceNameList", required = true)
    protected CardApplicationServiceListResponse.CardApplicationServiceNameList cardApplicationServiceNameList;

    /**
     * Gets the value of the cardApplicationServiceNameList property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationServiceListResponse.CardApplicationServiceNameList }
     *     
     */
    public CardApplicationServiceListResponse.CardApplicationServiceNameList getCardApplicationServiceNameList() {
        return cardApplicationServiceNameList;
    }

    /**
     * Sets the value of the cardApplicationServiceNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationServiceListResponse.CardApplicationServiceNameList }
     *     
     */
    public void setCardApplicationServiceNameList(CardApplicationServiceListResponse.CardApplicationServiceNameList value) {
        this.cardApplicationServiceNameList = value;
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
     *       &lt;sequence>
     *         &lt;element name="CardApplicationServiceName" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "cardApplicationServiceName"
    })
    public static class CardApplicationServiceNameList {

        @XmlElement(name = "CardApplicationServiceName")
        protected List<String> cardApplicationServiceName;

        /**
         * Gets the value of the cardApplicationServiceName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cardApplicationServiceName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCardApplicationServiceName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getCardApplicationServiceName() {
            if (cardApplicationServiceName == null) {
                cardApplicationServiceName = new ArrayList<String>();
            }
            return this.cardApplicationServiceName;
        }

    }

}
