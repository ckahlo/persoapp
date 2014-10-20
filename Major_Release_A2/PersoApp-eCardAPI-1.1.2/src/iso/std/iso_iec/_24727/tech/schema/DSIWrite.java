
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
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
 *         &lt;element name="DSIName" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameType"/>
 *         &lt;element name="DSIContent" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
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
    "dsiName",
    "dsiContent"
})
@XmlRootElement(name = "DSIWrite")
public class DSIWrite
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "DSIName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dsiName;
    @XmlElement(name = "DSIContent", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] dsiContent;

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

    /**
     * Gets the value of the dsiContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getDSIContent() {
        return dsiContent;
    }

    /**
     * Sets the value of the dsiContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSIContent(byte[] value) {
        this.dsiContent = ((byte[]) value);
    }

}
