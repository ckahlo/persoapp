
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for MatchingDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MatchingDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Offset" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="MatchingValue" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element name="Mask" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="MatchingRule" type="{urn:iso:std:iso-iec:24727:tech:schema}MatchingRuleType" default="Equals" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatchingDataType", propOrder = {
    "offset",
    "length",
    "matchingValue",
    "mask"
})
public class MatchingDataType {

    @XmlElement(name = "Offset", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] offset;
    @XmlElement(name = "Length", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] length;
    @XmlElement(name = "MatchingValue", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] matchingValue;
    @XmlElement(name = "Mask", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] mask;
    @XmlAttribute(name = "MatchingRule")
    protected MatchingRuleType matchingRule;

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOffset(byte[] value) {
        this.offset = ((byte[]) value);
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLength(byte[] value) {
        this.length = ((byte[]) value);
    }

    /**
     * Gets the value of the matchingValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getMatchingValue() {
        return matchingValue;
    }

    /**
     * Sets the value of the matchingValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatchingValue(byte[] value) {
        this.matchingValue = ((byte[]) value);
    }

    /**
     * Gets the value of the mask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getMask() {
        return mask;
    }

    /**
     * Sets the value of the mask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMask(byte[] value) {
        this.mask = ((byte[]) value);
    }

    /**
     * Gets the value of the matchingRule property.
     * 
     * @return
     *     possible object is
     *     {@link MatchingRuleType }
     *     
     */
    public MatchingRuleType getMatchingRule() {
        if (matchingRule == null) {
            return MatchingRuleType.EQUALS;
        } else {
            return matchingRule;
        }
    }

    /**
     * Sets the value of the matchingRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchingRuleType }
     *     
     */
    public void setMatchingRule(MatchingRuleType value) {
        this.matchingRule = value;
    }

}
