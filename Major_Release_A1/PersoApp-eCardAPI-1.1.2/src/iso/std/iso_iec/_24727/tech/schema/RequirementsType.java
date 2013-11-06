
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequirementsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequirementsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequirementLevel" type="{urn:iso:std:iso-iec:24727:tech:schema}BasicRequirementsType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequirementsType", propOrder = {
    "requirementLevel"
})
public class RequirementsType {

    @XmlElement(name = "RequirementLevel")
    protected BasicRequirementsType requirementLevel;

    /**
     * Gets the value of the requirementLevel property.
     * 
     * @return
     *     possible object is
     *     {@link BasicRequirementsType }
     *     
     */
    public BasicRequirementsType getRequirementLevel() {
        return requirementLevel;
    }

    /**
     * Sets the value of the requirementLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicRequirementsType }
     *     
     */
    public void setRequirementLevel(BasicRequirementsType value) {
        this.requirementLevel = value;
    }

}
