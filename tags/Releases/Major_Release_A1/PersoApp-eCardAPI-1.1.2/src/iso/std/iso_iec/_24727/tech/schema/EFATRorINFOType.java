
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EFATRorINFOType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EFATRorINFOType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}FileRefReqType">
 *       &lt;sequence>
 *         &lt;element name="ISO7816-4-CardServiceData" type="{urn:iso:std:iso-iec:24727:tech:schema}ISO7816-4-CardServiceDataType" minOccurs="0"/>
 *         &lt;element name="Pre-Issuing-DO" type="{urn:iso:std:iso-iec:24727:tech:schema}FileRefReqType" minOccurs="0"/>
 *         &lt;element name="ISO7816-4-CardCapabilities" type="{urn:iso:std:iso-iec:24727:tech:schema}ISO7816-4-CardCapabilitiesType" minOccurs="0"/>
 *         &lt;element name="ImplicitlySelectedApplication" type="{urn:iso:std:iso-iec:24727:tech:schema}FileRefReqType" minOccurs="0"/>
 *         &lt;element name="ExtendedLengthInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}ExtendedLengthInfoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EFATRorINFOType", propOrder = {
    "iso78164CardServiceData",
    "preIssuingDO",
    "iso78164CardCapabilities",
    "implicitlySelectedApplication",
    "extendedLengthInfo"
})
public class EFATRorINFOType
    extends FileRefReqType
{

    @XmlElement(name = "ISO7816-4-CardServiceData")
    protected ISO78164CardServiceDataType iso78164CardServiceData;
    @XmlElement(name = "Pre-Issuing-DO")
    protected FileRefReqType preIssuingDO;
    @XmlElement(name = "ISO7816-4-CardCapabilities")
    protected ISO78164CardCapabilitiesType iso78164CardCapabilities;
    @XmlElement(name = "ImplicitlySelectedApplication")
    protected FileRefReqType implicitlySelectedApplication;
    @XmlElement(name = "ExtendedLengthInfo")
    protected ExtendedLengthInfoType extendedLengthInfo;

    /**
     * Gets the value of the iso78164CardServiceData property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardServiceDataType }
     *     
     */
    public ISO78164CardServiceDataType getISO78164CardServiceData() {
        return iso78164CardServiceData;
    }

    /**
     * Sets the value of the iso78164CardServiceData property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardServiceDataType }
     *     
     */
    public void setISO78164CardServiceData(ISO78164CardServiceDataType value) {
        this.iso78164CardServiceData = value;
    }

    /**
     * Gets the value of the preIssuingDO property.
     * 
     * @return
     *     possible object is
     *     {@link FileRefReqType }
     *     
     */
    public FileRefReqType getPreIssuingDO() {
        return preIssuingDO;
    }

    /**
     * Sets the value of the preIssuingDO property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileRefReqType }
     *     
     */
    public void setPreIssuingDO(FileRefReqType value) {
        this.preIssuingDO = value;
    }

    /**
     * Gets the value of the iso78164CardCapabilities property.
     * 
     * @return
     *     possible object is
     *     {@link ISO78164CardCapabilitiesType }
     *     
     */
    public ISO78164CardCapabilitiesType getISO78164CardCapabilities() {
        return iso78164CardCapabilities;
    }

    /**
     * Sets the value of the iso78164CardCapabilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ISO78164CardCapabilitiesType }
     *     
     */
    public void setISO78164CardCapabilities(ISO78164CardCapabilitiesType value) {
        this.iso78164CardCapabilities = value;
    }

    /**
     * Gets the value of the implicitlySelectedApplication property.
     * 
     * @return
     *     possible object is
     *     {@link FileRefReqType }
     *     
     */
    public FileRefReqType getImplicitlySelectedApplication() {
        return implicitlySelectedApplication;
    }

    /**
     * Sets the value of the implicitlySelectedApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileRefReqType }
     *     
     */
    public void setImplicitlySelectedApplication(FileRefReqType value) {
        this.implicitlySelectedApplication = value;
    }

    /**
     * Gets the value of the extendedLengthInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ExtendedLengthInfoType }
     *     
     */
    public ExtendedLengthInfoType getExtendedLengthInfo() {
        return extendedLengthInfo;
    }

    /**
     * Sets the value of the extendedLengthInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtendedLengthInfoType }
     *     
     */
    public void setExtendedLengthInfo(ExtendedLengthInfoType value) {
        this.extendedLengthInfo = value;
    }

}
