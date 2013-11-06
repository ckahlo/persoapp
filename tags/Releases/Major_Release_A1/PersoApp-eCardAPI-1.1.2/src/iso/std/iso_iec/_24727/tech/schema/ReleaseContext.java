
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
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
 *         &lt;element name="ContextHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ContextHandleType"/>
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
    "contextHandle"
})
@XmlRootElement(name = "ReleaseContext")
public class ReleaseContext
    extends RequestType
{

    @XmlElement(name = "ContextHandle", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] contextHandle;

    /**
     * Gets the value of the contextHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getContextHandle() {
        return contextHandle;
    }

    /**
     * Sets the value of the contextHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextHandle(byte[] value) {
        this.contextHandle = ((byte[]) value);
    }

}
