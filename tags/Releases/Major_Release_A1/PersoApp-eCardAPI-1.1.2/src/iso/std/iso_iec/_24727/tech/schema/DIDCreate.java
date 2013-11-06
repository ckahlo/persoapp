
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
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/>
 *         &lt;element name="DIDUpdateData" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDUpdateDataType"/>
 *         &lt;element name="DIDACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/>
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
    "didName",
    "didUpdateData",
    "didacl"
})
@XmlRootElement(name = "DIDCreate")
public class DIDCreate
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;
    @XmlElement(name = "DIDUpdateData", required = true)
    protected DIDUpdateDataType didUpdateData;
    @XmlElement(name = "DIDACL", required = true)
    protected AccessControlListType didacl;

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
     * Gets the value of the didUpdateData property.
     * 
     * @return
     *     possible object is
     *     {@link DIDUpdateDataType }
     *     
     */
    public DIDUpdateDataType getDIDUpdateData() {
        return didUpdateData;
    }

    /**
     * Sets the value of the didUpdateData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDUpdateDataType }
     *     
     */
    public void setDIDUpdateData(DIDUpdateDataType value) {
        this.didUpdateData = value;
    }

    /**
     * Gets the value of the didacl property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getDIDACL() {
        return didacl;
    }

    /**
     * Sets the value of the didacl property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setDIDACL(AccessControlListType value) {
        this.didacl = value;
    }

}
