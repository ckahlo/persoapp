
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="DataSetNameList" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameListType"/>
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
    "dataSetNameList"
})
@XmlRootElement(name = "DataSetListResponse")
public class DataSetListResponse
    extends ResponseType
{

    @XmlElement(name = "DataSetNameList", required = true)
    protected DataSetNameListType dataSetNameList;

    /**
     * Gets the value of the dataSetNameList property.
     * 
     * @return
     *     possible object is
     *     {@link DataSetNameListType }
     *     
     */
    public DataSetNameListType getDataSetNameList() {
        return dataSetNameList;
    }

    /**
     * Sets the value of the dataSetNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSetNameListType }
     *     
     */
    public void setDataSetNameList(DataSetNameListType value) {
        this.dataSetNameList = value;
    }

}
