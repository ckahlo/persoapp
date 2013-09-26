
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TargetNameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TargetNameType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="DataSetName" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameType"/>
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/>
 *         &lt;element name="CardApplicationName" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TargetNameType", propOrder = {
    "dataSetName",
    "didName",
    "cardApplicationName"
})
public class TargetNameType {

    @XmlElement(name = "DataSetName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dataSetName;
    @XmlElement(name = "DIDName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;
    @XmlElement(name = "CardApplicationName", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] cardApplicationName;

    /**
     * Gets the value of the dataSetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSetName() {
        return dataSetName;
    }

    /**
     * Sets the value of the dataSetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSetName(String value) {
        this.dataSetName = value;
    }

    /**
     * Gets the value of the didName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDName() {
        return didName;
    }

    /**
     * Sets the value of the didName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDName(String value) {
        this.didName = value;
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

}
