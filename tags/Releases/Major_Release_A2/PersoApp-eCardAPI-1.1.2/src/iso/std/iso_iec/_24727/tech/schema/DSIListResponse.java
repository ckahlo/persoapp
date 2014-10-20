
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
 *         &lt;element name="DSINameList" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameListType"/>
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
    "dsiNameList"
})
@XmlRootElement(name = "DSIListResponse")
public class DSIListResponse
    extends ResponseType
{

    @XmlElement(name = "DSINameList", required = true)
    protected DSINameListType dsiNameList;

    /**
     * Gets the value of the dsiNameList property.
     * 
     * @return
     *     possible object is
     *     {@link DSINameListType }
     *     
     */
    public DSINameListType getDSINameList() {
        return dsiNameList;
    }

    /**
     * Sets the value of the dsiNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DSINameListType }
     *     
     */
    public void setDSINameList(DSINameListType value) {
        this.dsiNameList = value;
    }

}
