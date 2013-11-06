
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IFDStatusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IFDStatusType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IFDName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Connected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SlotStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotStatusType" maxOccurs="unbounded"/>
 *         &lt;element name="ActiveAntenna" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DisplayStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SimpleFUStatusType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="KeyPadStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SimpleFUStatusType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BioSensorStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}SimpleFUStatusType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IFDStatusType", propOrder = {
    "ifdName",
    "connected",
    "slotStatus",
    "activeAntenna",
    "displayStatus",
    "keyPadStatus",
    "bioSensorStatus"
})
public class IFDStatusType {

    @XmlElement(name = "IFDName")
    protected String ifdName;
    @XmlElement(name = "Connected")
    protected Boolean connected;
    @XmlElement(name = "SlotStatus", required = true)
    protected List<SlotStatusType> slotStatus;
    @XmlElement(name = "ActiveAntenna")
    protected Boolean activeAntenna;
    @XmlElement(name = "DisplayStatus")
    protected List<SimpleFUStatusType> displayStatus;
    @XmlElement(name = "KeyPadStatus")
    protected List<SimpleFUStatusType> keyPadStatus;
    @XmlElement(name = "BioSensorStatus")
    protected List<SimpleFUStatusType> bioSensorStatus;

    /**
     * Gets the value of the ifdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFDName() {
        return ifdName;
    }

    /**
     * Sets the value of the ifdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFDName(String value) {
        this.ifdName = value;
    }

    /**
     * Gets the value of the connected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConnected() {
        return connected;
    }

    /**
     * Sets the value of the connected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConnected(Boolean value) {
        this.connected = value;
    }

    /**
     * Gets the value of the slotStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slotStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlotStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SlotStatusType }
     * 
     * 
     */
    public List<SlotStatusType> getSlotStatus() {
        if (slotStatus == null) {
            slotStatus = new ArrayList<SlotStatusType>();
        }
        return this.slotStatus;
    }

    /**
     * Gets the value of the activeAntenna property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActiveAntenna() {
        return activeAntenna;
    }

    /**
     * Sets the value of the activeAntenna property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActiveAntenna(Boolean value) {
        this.activeAntenna = value;
    }

    /**
     * Gets the value of the displayStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the displayStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDisplayStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleFUStatusType }
     * 
     * 
     */
    public List<SimpleFUStatusType> getDisplayStatus() {
        if (displayStatus == null) {
            displayStatus = new ArrayList<SimpleFUStatusType>();
        }
        return this.displayStatus;
    }

    /**
     * Gets the value of the keyPadStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyPadStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyPadStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleFUStatusType }
     * 
     * 
     */
    public List<SimpleFUStatusType> getKeyPadStatus() {
        if (keyPadStatus == null) {
            keyPadStatus = new ArrayList<SimpleFUStatusType>();
        }
        return this.keyPadStatus;
    }

    /**
     * Gets the value of the bioSensorStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bioSensorStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBioSensorStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SimpleFUStatusType }
     * 
     * 
     */
    public List<SimpleFUStatusType> getBioSensorStatus() {
        if (bioSensorStatus == null) {
            bioSensorStatus = new ArrayList<SimpleFUStatusType>();
        }
        return this.bioSensorStatus;
    }

}
