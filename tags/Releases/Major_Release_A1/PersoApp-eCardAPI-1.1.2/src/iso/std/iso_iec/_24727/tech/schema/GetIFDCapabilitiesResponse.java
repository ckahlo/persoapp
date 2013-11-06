
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
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="IFDCapabilities" type="{urn:iso:std:iso-iec:24727:tech:schema}IFDCapabilitiesType"/>
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
    "ifdCapabilities"
})
@XmlRootElement(name = "GetIFDCapabilitiesResponse")
public class GetIFDCapabilitiesResponse
    extends ResponseType
{

    @XmlElement(name = "IFDCapabilities")
    protected IFDCapabilitiesType ifdCapabilities;

    /**
     * Gets the value of the ifdCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link IFDCapabilitiesType }
     *     
     */
    public IFDCapabilitiesType getIFDCapabilities() {
        return ifdCapabilities;
    }

    /**
     * Sets the value of the ifdCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link IFDCapabilitiesType }
     *     
     */
    public void setIFDCapabilities(IFDCapabilitiesType value) {
        this.ifdCapabilities = value;
    }

}
