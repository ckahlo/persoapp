
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="CardApplicationNameList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="CardApplicationName" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType"/>
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
    "cardApplicationNameList"
})
@XmlRootElement(name = "CardApplicationListResponse")
public class CardApplicationListResponse
    extends ResponseType
{

    @XmlElement(name = "CardApplicationNameList", required = true)
    protected CardApplicationListResponse.CardApplicationNameList cardApplicationNameList;

    /**
     * Gets the value of the cardApplicationNameList property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationListResponse.CardApplicationNameList }
     *     
     */
    public CardApplicationListResponse.CardApplicationNameList getCardApplicationNameList() {
        return cardApplicationNameList;
    }

    /**
     * Sets the value of the cardApplicationNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationListResponse.CardApplicationNameList }
     *     
     */
    public void setCardApplicationNameList(CardApplicationListResponse.CardApplicationNameList value) {
        this.cardApplicationNameList = value;
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
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="CardApplicationName" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType"/>
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
        "cardApplicationName"
    })
    public static class CardApplicationNameList {

        @XmlElement(name = "CardApplicationName", required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        protected List<byte[]> cardApplicationName;

        /**
         * Gets the value of the cardApplicationName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cardApplicationName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCardApplicationName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<byte[]> getCardApplicationName() {
            if (cardApplicationName == null) {
                cardApplicationName = new ArrayList<byte[]>();
            }
            return this.cardApplicationName;
        }

    }

}
