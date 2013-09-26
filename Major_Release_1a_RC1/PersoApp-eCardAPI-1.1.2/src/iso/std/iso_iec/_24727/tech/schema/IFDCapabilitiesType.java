
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IFDCapabilitiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IFDCapabilitiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SlotCapability" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotCapabilityType" maxOccurs="unbounded"/>
 *         &lt;element name="DisplayCapability" type="{urn:iso:std:iso-iec:24727:tech:schema}DisplayCapabilityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="KeyPadCapability" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyPadCapabilityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BioSensorCapability" type="{urn:iso:std:iso-iec:24727:tech:schema}BioSensorCapabilityType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OpticalSignalUnit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AcousticSignalUnit" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IFDCapabilitiesType", propOrder = {
    "slotCapability",
    "displayCapability",
    "keyPadCapability",
    "bioSensorCapability",
    "opticalSignalUnit",
    "acousticSignalUnit"
})
public class IFDCapabilitiesType {

    @XmlElement(name = "SlotCapability", required = true)
    protected List<SlotCapabilityType> slotCapability;
    @XmlElement(name = "DisplayCapability")
    protected List<DisplayCapabilityType> displayCapability;
    @XmlElement(name = "KeyPadCapability")
    protected List<KeyPadCapabilityType> keyPadCapability;
    @XmlElement(name = "BioSensorCapability")
    protected List<BioSensorCapabilityType> bioSensorCapability;
    @XmlElement(name = "OpticalSignalUnit")
    protected boolean opticalSignalUnit;
    @XmlElement(name = "AcousticSignalUnit")
    protected boolean acousticSignalUnit;

    /**
     * Gets the value of the slotCapability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slotCapability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlotCapability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SlotCapabilityType }
     * 
     * 
     */
    public List<SlotCapabilityType> getSlotCapability() {
        if (slotCapability == null) {
            slotCapability = new ArrayList<SlotCapabilityType>();
        }
        return this.slotCapability;
    }

    /**
     * Gets the value of the displayCapability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayCapability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayCapability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DisplayCapabilityType }
     * 
     * 
     */
    public List<DisplayCapabilityType> getDisplayCapability() {
        if (displayCapability == null) {
            displayCapability = new ArrayList<DisplayCapabilityType>();
        }
        return this.displayCapability;
    }

    /**
     * Gets the value of the keyPadCapability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyPadCapability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyPadCapability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyPadCapabilityType }
     * 
     * 
     */
    public List<KeyPadCapabilityType> getKeyPadCapability() {
        if (keyPadCapability == null) {
            keyPadCapability = new ArrayList<KeyPadCapabilityType>();
        }
        return this.keyPadCapability;
    }

    /**
     * Gets the value of the bioSensorCapability property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bioSensorCapability property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBioSensorCapability().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BioSensorCapabilityType }
     * 
     * 
     */
    public List<BioSensorCapabilityType> getBioSensorCapability() {
        if (bioSensorCapability == null) {
            bioSensorCapability = new ArrayList<BioSensorCapabilityType>();
        }
        return this.bioSensorCapability;
    }

    /**
     * Gets the value of the opticalSignalUnit property.
     * 
     */
    public boolean isOpticalSignalUnit() {
        return opticalSignalUnit;
    }

    /**
     * Sets the value of the opticalSignalUnit property.
     * 
     */
    public void setOpticalSignalUnit(boolean value) {
        this.opticalSignalUnit = value;
    }

    /**
     * Gets the value of the acousticSignalUnit property.
     * 
     */
    public boolean isAcousticSignalUnit() {
        return acousticSignalUnit;
    }

    /**
     * Sets the value of the acousticSignalUnit property.
     * 
     */
    public void setAcousticSignalUnit(boolean value) {
        this.acousticSignalUnit = value;
    }

}
