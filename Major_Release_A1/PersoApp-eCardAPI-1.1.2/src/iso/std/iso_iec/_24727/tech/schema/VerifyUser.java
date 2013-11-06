
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="SlotHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotHandleType"/>
 *         &lt;element name="InputUnit" type="{urn:iso:std:iso-iec:24727:tech:schema}InputUnitType"/>
 *         &lt;element name="DisplayIndex" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="AltVUMessages" type="{urn:iso:std:iso-iec:24727:tech:schema}AltVUMessagesType" minOccurs="0"/>
 *         &lt;element name="TimeoutUntilFirstKey" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="TimeoutAfterFirstKey" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Template" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
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
    "slotHandle",
    "inputUnit",
    "displayIndex",
    "altVUMessages",
    "timeoutUntilFirstKey",
    "timeoutAfterFirstKey",
    "template"
})
@XmlRootElement(name = "VerifyUser")
public class VerifyUser
    extends RequestType
{

    @XmlElement(name = "SlotHandle", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] slotHandle;
    @XmlElement(name = "InputUnit", required = true)
    protected InputUnitType inputUnit;
    @XmlElement(name = "DisplayIndex")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger displayIndex;
    @XmlElement(name = "AltVUMessages")
    protected AltVUMessagesType altVUMessages;
    @XmlElement(name = "TimeoutUntilFirstKey")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger timeoutUntilFirstKey;
    @XmlElement(name = "TimeoutAfterFirstKey")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger timeoutAfterFirstKey;
    @XmlElement(name = "Template", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] template;

    /**
     * Gets the value of the slotHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSlotHandle() {
        return slotHandle;
    }

    /**
     * Sets the value of the slotHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlotHandle(byte[] value) {
        this.slotHandle = ((byte[]) value);
    }

    /**
     * Gets the value of the inputUnit property.
     * 
     * @return
     *     possible object is
     *     {@link InputUnitType }
     *     
     */
    public InputUnitType getInputUnit() {
        return inputUnit;
    }

    /**
     * Sets the value of the inputUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link InputUnitType }
     *     
     */
    public void setInputUnit(InputUnitType value) {
        this.inputUnit = value;
    }

    /**
     * Gets the value of the displayIndex property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDisplayIndex() {
        return displayIndex;
    }

    /**
     * Sets the value of the displayIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDisplayIndex(BigInteger value) {
        this.displayIndex = value;
    }

    /**
     * Gets the value of the altVUMessages property.
     * 
     * @return
     *     possible object is
     *     {@link AltVUMessagesType }
     *     
     */
    public AltVUMessagesType getAltVUMessages() {
        return altVUMessages;
    }

    /**
     * Sets the value of the altVUMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link AltVUMessagesType }
     *     
     */
    public void setAltVUMessages(AltVUMessagesType value) {
        this.altVUMessages = value;
    }

    /**
     * Gets the value of the timeoutUntilFirstKey property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeoutUntilFirstKey() {
        return timeoutUntilFirstKey;
    }

    /**
     * Sets the value of the timeoutUntilFirstKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeoutUntilFirstKey(BigInteger value) {
        this.timeoutUntilFirstKey = value;
    }

    /**
     * Gets the value of the timeoutAfterFirstKey property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeoutAfterFirstKey() {
        return timeoutAfterFirstKey;
    }

    /**
     * Sets the value of the timeoutAfterFirstKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeoutAfterFirstKey(BigInteger value) {
        this.timeoutAfterFirstKey = value;
    }

    /**
     * Gets the value of the template property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTemplate() {
        return template;
    }

    /**
     * Sets the value of the template property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplate(byte[] value) {
        this.template = ((byte[]) value);
    }

}
