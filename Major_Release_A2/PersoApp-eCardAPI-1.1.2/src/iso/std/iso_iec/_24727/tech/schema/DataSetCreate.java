
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
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
 *         &lt;element name="ConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType"/>
 *         &lt;element name="DataSetName" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameType"/>
 *         &lt;element name="DataSetACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/>
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
    "connectionHandle",
    "dataSetName",
    "dataSetACL"
})
@XmlRootElement(name = "DataSetCreate")
public class DataSetCreate
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "DataSetName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dataSetName;
    @XmlElement(name = "DataSetACL", required = true)
    protected AccessControlListType dataSetACL;

    /**
     * Gets the value of the connectionHandle property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType }
     *     
     */
    public ConnectionHandleType getConnectionHandle() {
        return connectionHandle;
    }

    /**
     * Sets the value of the connectionHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType }
     *     
     */
    public void setConnectionHandle(ConnectionHandleType value) {
        this.connectionHandle = value;
    }

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
     * Gets the value of the dataSetACL property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getDataSetACL() {
        return dataSetACL;
    }

    /**
     * Sets the value of the dataSetACL property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setDataSetACL(AccessControlListType value) {
        this.dataSetACL = value;
    }

}
