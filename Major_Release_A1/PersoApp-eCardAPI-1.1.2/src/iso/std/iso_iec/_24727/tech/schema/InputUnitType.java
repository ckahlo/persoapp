
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InputUnitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InputUnitType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="PinInput" type="{urn:iso:std:iso-iec:24727:tech:schema}PinInputType"/>
 *         &lt;element name="BiometricInput" type="{urn:iso:std:iso-iec:24727:tech:schema}BiometricInputType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InputUnitType", propOrder = {
    "pinInput",
    "biometricInput"
})
public class InputUnitType {

    @XmlElement(name = "PinInput")
    protected PinInputType pinInput;
    @XmlElement(name = "BiometricInput")
    protected BiometricInputType biometricInput;

    /**
     * Gets the value of the pinInput property.
     * 
     * @return
     *     possible object is
     *     {@link PinInputType }
     *     
     */
    public PinInputType getPinInput() {
        return pinInput;
    }

    /**
     * Sets the value of the pinInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link PinInputType }
     *     
     */
    public void setPinInput(PinInputType value) {
        this.pinInput = value;
    }

    /**
     * Gets the value of the biometricInput property.
     * 
     * @return
     *     possible object is
     *     {@link BiometricInputType }
     *     
     */
    public BiometricInputType getBiometricInput() {
        return biometricInput;
    }

    /**
     * Sets the value of the biometricInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link BiometricInputType }
     *     
     */
    public void setBiometricInput(BiometricInputType value) {
        this.biometricInput = value;
    }

}
