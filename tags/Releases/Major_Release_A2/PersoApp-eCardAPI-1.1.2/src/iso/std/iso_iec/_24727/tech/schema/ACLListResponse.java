
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
 *         &lt;element name="TargetACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/>
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
    "targetACL"
})
@XmlRootElement(name = "ACLListResponse")
public class ACLListResponse
    extends ResponseType
{

    @XmlElement(name = "TargetACL", required = true)
    protected AccessControlListType targetACL;

    /**
     * Gets the value of the targetACL property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getTargetACL() {
        return targetACL;
    }

    /**
     * Sets the value of the targetACL property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setTargetACL(AccessControlListType value) {
        this.targetACL = value;
    }

}
