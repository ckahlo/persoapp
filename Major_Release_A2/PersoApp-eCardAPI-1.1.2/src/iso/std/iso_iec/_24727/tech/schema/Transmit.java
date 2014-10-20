
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
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="SlotHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotHandleType"/>
 *         &lt;element name="InputAPDUInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}InputAPDUInfoType" maxOccurs="unbounded"/>
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
    "inputAPDUInfo"
})
@XmlRootElement(name = "Transmit")
public class Transmit
    extends RequestType
{

    @XmlElement(name = "SlotHandle", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] slotHandle;
    @XmlElement(name = "InputAPDUInfo", required = true)
    protected List<InputAPDUInfoType> inputAPDUInfo;

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
     * Gets the value of the inputAPDUInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputAPDUInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputAPDUInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InputAPDUInfoType }
     * 
     * 
     */
    public List<InputAPDUInfoType> getInputAPDUInfo() {
        if (inputAPDUInfo == null) {
            inputAPDUInfo = new ArrayList<InputAPDUInfoType>();
        }
        return this.inputAPDUInfo;
    }

}
