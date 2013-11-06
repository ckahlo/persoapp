
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DataMaskType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataMaskType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tag" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="MatchingData" type="{urn:iso:std:iso-iec:24727:tech:schema}MatchingDataType"/>
 *           &lt;element name="DataObject" type="{urn:iso:std:iso-iec:24727:tech:schema}DataMaskType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataMaskType", propOrder = {
    "tag",
    "matchingData",
    "dataObject"
})
public class DataMaskType {

    @XmlElement(name = "Tag", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] tag;
    @XmlElement(name = "MatchingData")
    protected MatchingDataType matchingData;
    @XmlElement(name = "DataObject")
    protected DataMaskType dataObject;

    /**
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTag(byte[] value) {
        this.tag = ((byte[]) value);
    }

    /**
     * Gets the value of the matchingData property.
     * 
     * @return
     *     possible object is
     *     {@link MatchingDataType }
     *     
     */
    public MatchingDataType getMatchingData() {
        return matchingData;
    }

    /**
     * Sets the value of the matchingData property.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchingDataType }
     *     
     */
    public void setMatchingData(MatchingDataType value) {
        this.matchingData = value;
    }

    /**
     * Gets the value of the dataObject property.
     * 
     * @return
     *     possible object is
     *     {@link DataMaskType }
     *     
     */
    public DataMaskType getDataObject() {
        return dataObject;
    }

    /**
     * Sets the value of the dataObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataMaskType }
     *     
     */
    public void setDataObject(DataMaskType value) {
        this.dataObject = value;
    }

}
