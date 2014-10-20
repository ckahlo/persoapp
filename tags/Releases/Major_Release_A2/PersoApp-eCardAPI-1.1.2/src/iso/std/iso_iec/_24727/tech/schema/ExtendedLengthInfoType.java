
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExtendedLengthInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtendedLengthInfoType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *       &lt;sequence>
 *         &lt;element name="GlobalLengthInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}LengthInfoType"/>
 *         &lt;element name="CommandSpecificLengthInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}CommandSpecificLengthInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtendedLengthInfoType", propOrder = {
    "globalLengthInfo",
    "commandSpecificLengthInfo"
})
public class ExtendedLengthInfoType
    extends RequirementsType
{

    @XmlElement(name = "GlobalLengthInfo", required = true)
    protected LengthInfoType globalLengthInfo;
    @XmlElement(name = "CommandSpecificLengthInfo")
    protected List<CommandSpecificLengthInfoType> commandSpecificLengthInfo;

    /**
     * Gets the value of the globalLengthInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LengthInfoType }
     *     
     */
    public LengthInfoType getGlobalLengthInfo() {
        return globalLengthInfo;
    }

    /**
     * Sets the value of the globalLengthInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthInfoType }
     *     
     */
    public void setGlobalLengthInfo(LengthInfoType value) {
        this.globalLengthInfo = value;
    }

    /**
     * Gets the value of the commandSpecificLengthInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commandSpecificLengthInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommandSpecificLengthInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommandSpecificLengthInfoType }
     * 
     * 
     */
    public List<CommandSpecificLengthInfoType> getCommandSpecificLengthInfo() {
        if (commandSpecificLengthInfo == null) {
            commandSpecificLengthInfo = new ArrayList<CommandSpecificLengthInfoType>();
        }
        return this.commandSpecificLengthInfo;
    }

}
