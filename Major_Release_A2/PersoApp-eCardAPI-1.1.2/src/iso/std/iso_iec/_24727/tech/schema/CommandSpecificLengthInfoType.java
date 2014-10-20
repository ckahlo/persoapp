
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for CommandSpecificLengthInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommandSpecificLengthInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="Command" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element name="LengthInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}LengthInfoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommandSpecificLengthInfoType", propOrder = {
    "tag",
    "command",
    "lengthInfo"
})
public class CommandSpecificLengthInfoType {

    @XmlElement(name = "Tag")
    protected byte tag;
    @XmlElement(name = "Command", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] command;
    @XmlElement(name = "LengthInfo", required = true)
    protected LengthInfoType lengthInfo;

    /**
     * Gets the value of the tag property.
     * 
     */
    public byte getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     */
    public void setTag(byte value) {
        this.tag = value;
    }

    /**
     * Gets the value of the command property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCommand() {
        return command;
    }

    /**
     * Sets the value of the command property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommand(byte[] value) {
        this.command = ((byte[]) value);
    }

    /**
     * Gets the value of the lengthInfo property.
     * 
     * @return
     *     possible object is
     *     {@link LengthInfoType }
     *     
     */
    public LengthInfoType getLengthInfo() {
        return lengthInfo;
    }

    /**
     * Sets the value of the lengthInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LengthInfoType }
     *     
     */
    public void setLengthInfo(LengthInfoType value) {
        this.lengthInfo = value;
    }

}
