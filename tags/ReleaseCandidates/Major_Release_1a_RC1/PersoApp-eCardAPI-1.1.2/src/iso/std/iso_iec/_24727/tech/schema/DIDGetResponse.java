
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
 *         &lt;element name="DIDStructure" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDStructureType"/>
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
    "didStructure"
})
@XmlRootElement(name = "DIDGetResponse")
public class DIDGetResponse
    extends ResponseType
{

    @XmlElement(name = "DIDStructure", required = true)
    protected DIDStructureType didStructure;

    /**
     * Gets the value of the didStructure property.
     * 
     * @return
     *     possible object is
     *     {@link DIDStructureType }
     *     
     */
    public DIDStructureType getDIDStructure() {
        return didStructure;
    }

    /**
     * Sets the value of the didStructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDStructureType }
     *     
     */
    public void setDIDStructure(DIDStructureType value) {
        this.didStructure = value;
    }

}
