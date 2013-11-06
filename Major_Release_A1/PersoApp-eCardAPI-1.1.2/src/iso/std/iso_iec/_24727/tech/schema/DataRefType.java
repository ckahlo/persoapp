
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DataRefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataRefType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataSetName" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameType"/>
 *         &lt;element name="DSIName" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataRefType", propOrder = {
    "dataSetName",
    "dsiName"
})
public class DataRefType {

    @XmlElement(name = "DataSetName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dataSetName;
    @XmlElement(name = "DSIName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dsiName;

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
     * Gets the value of the dsiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSIName() {
        return dsiName;
    }

    /**
     * Sets the value of the dsiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSIName(String value) {
        this.dsiName = value;
    }

}
