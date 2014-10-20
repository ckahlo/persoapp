
package iso.std.iso_iec._24727.tech.schema;

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
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="ConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType"/>
 *         &lt;element name="CardApplicationName" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType"/>
 *         &lt;element name="CardApplicationACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/>
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
    "connectionHandle",
    "cardApplicationName",
    "cardApplicationACL"
})
@XmlRootElement(name = "CardApplicationCreate")
public class CardApplicationCreate
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "CardApplicationName", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] cardApplicationName;
    @XmlElement(name = "CardApplicationACL", required = true)
    protected AccessControlListType cardApplicationACL;

    /**
     * Gets the value of the connectionHandle property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType }
     *     
     */
    public ConnectionHandleType getConnectionHandle() {
        return connectionHandle;
    }

    /**
     * Sets the value of the connectionHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType }
     *     
     */
    public void setConnectionHandle(ConnectionHandleType value) {
        this.connectionHandle = value;
    }

    /**
     * Gets the value of the cardApplicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCardApplicationName() {
        return cardApplicationName;
    }

    /**
     * Sets the value of the cardApplicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardApplicationName(byte[] value) {
        this.cardApplicationName = ((byte[]) value);
    }

    /**
     * Gets the value of the cardApplicationACL property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getCardApplicationACL() {
        return cardApplicationACL;
    }

    /**
     * Sets the value of the cardApplicationACL property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setCardApplicationACL(AccessControlListType value) {
        this.cardApplicationACL = value;
    }

}
