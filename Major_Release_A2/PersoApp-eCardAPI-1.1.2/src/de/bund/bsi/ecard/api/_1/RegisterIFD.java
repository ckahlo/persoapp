
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ChannelHandleType;
import iso.std.iso_iec._24727.tech.schema.RequestType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="ChannelHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ChannelHandleType" minOccurs="0"/>
 *         &lt;element name="IFDName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IFDConfiguration" type="{http://www.bsi.bund.de/ecard/api/1.1}IFDConfigurationType" minOccurs="0"/>
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
    "channelHandle",
    "ifdName",
    "ifdConfiguration"
})
@XmlRootElement(name = "RegisterIFD")
public class RegisterIFD
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "IFDName")
    protected String ifdName;
    @XmlElement(name = "IFDConfiguration")
    protected IFDConfigurationType ifdConfiguration;

    /**
     * Gets the value of the channelHandle property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelHandleType }
     *     
     */
    public ChannelHandleType getChannelHandle() {
        return channelHandle;
    }

    /**
     * Sets the value of the channelHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelHandleType }
     *     
     */
    public void setChannelHandle(ChannelHandleType value) {
        this.channelHandle = value;
    }

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
     * Gets the value of the ifdConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link IFDConfigurationType }
     *     
     */
    public IFDConfigurationType getIFDConfiguration() {
        return ifdConfiguration;
    }

    /**
     * Sets the value of the ifdConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link IFDConfigurationType }
     *     
     */
    public void setIFDConfiguration(IFDConfigurationType value) {
        this.ifdConfiguration = value;
    }

}
