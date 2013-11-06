
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
 *         &lt;element name="DIDNameList" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameListType"/>
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
    "didNameList"
})
@XmlRootElement(name = "DIDListResponse")
public class DIDListResponse
    extends ResponseType
{

    @XmlElement(name = "DIDNameList", required = true)
    protected DIDNameListType didNameList;

    /**
     * Gets the value of the didNameList property.
     * 
     * @return
     *     possible object is
     *     {@link DIDNameListType }
     *     
     */
    public DIDNameListType getDIDNameList() {
        return didNameList;
    }

    /**
     * Sets the value of the didNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDNameListType }
     *     
     */
    public void setDIDNameList(DIDNameListType value) {
        this.didNameList = value;
    }

}
