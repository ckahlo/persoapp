
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
 * <p>Java class for CardApplicationPathType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardApplicationPathType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChannelHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ChannelHandleType" minOccurs="0"/>
 *         &lt;element name="ContextHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ContextHandleType" minOccurs="0"/>
 *         &lt;element name="IFDName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SlotIndex" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="CardApplication" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardApplicationPathType", propOrder = {
    "channelHandle",
    "contextHandle",
    "ifdName",
    "slotIndex",
    "cardApplication"
})
public class CardApplicationPathType {

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "ContextHandle", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] contextHandle;
    @XmlElement(name = "IFDName")
    protected String ifdName;
    @XmlElement(name = "SlotIndex")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger slotIndex;
    @XmlElement(name = "CardApplication", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] cardApplication;

    /**
     * Gets the value of the channelHandle property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelHandleType }
     *     
     */
    public ChannelHandleType getChannelHandle() {
        return channelHandle;
    }

    /**
     * Sets the value of the channelHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelHandleType }
     *     
     */
    public void setChannelHandle(ChannelHandleType value) {
        this.channelHandle = value;
    }

    /**
     * Gets the value of the contextHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getContextHandle() {
        return contextHandle;
    }

    /**
     * Sets the value of the contextHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextHandle(byte[] value) {
        this.contextHandle = ((byte[]) value);
    }

    /**
     * Gets the value of the ifdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFDName() {
        return ifdName;
    }

    /**
     * Sets the value of the ifdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFDName(String value) {
        this.ifdName = value;
    }

    /**
     * Gets the value of the slotIndex property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSlotIndex() {
        return slotIndex;
    }

    /**
     * Sets the value of the slotIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSlotIndex(BigInteger value) {
        this.slotIndex = value;
    }

    /**
     * Gets the value of the cardApplication property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCardApplication() {
        return cardApplication;
    }

    /**
     * Sets the value of the cardApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardApplication(byte[] value) {
        this.cardApplication = ((byte[]) value);
    }

}
