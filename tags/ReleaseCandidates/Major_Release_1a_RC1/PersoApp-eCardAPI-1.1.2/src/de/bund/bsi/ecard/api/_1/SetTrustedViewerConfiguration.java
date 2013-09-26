
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
 *         &lt;element name="TrustedViewerId" type="{http://www.bsi.bund.de/ecard/api/1.1}TrustedViewerIdType"/>
 *         &lt;element name="ViewerConfiguration" type="{http://www.bsi.bund.de/ecard/api/1.1}ViewerConfigurationType"/>
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
    "trustedViewerId",
    "viewerConfiguration"
})
@XmlRootElement(name = "SetTrustedViewerConfiguration")
public class SetTrustedViewerConfiguration
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "TrustedViewerId", required = true)
    protected String trustedViewerId;
    @XmlElement(name = "ViewerConfiguration", required = true)
    protected ViewerConfigurationType viewerConfiguration;

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
     * Gets the value of the trustedViewerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustedViewerId() {
        return trustedViewerId;
    }

    /**
     * Sets the value of the trustedViewerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustedViewerId(String value) {
        this.trustedViewerId = value;
    }

    /**
     * Gets the value of the viewerConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link ViewerConfigurationType }
     *     
     */
    public ViewerConfigurationType getViewerConfiguration() {
        return viewerConfiguration;
    }

    /**
     * Sets the value of the viewerConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewerConfigurationType }
     *     
     */
    public void setViewerConfiguration(ViewerConfigurationType value) {
        this.viewerConfiguration = value;
    }

}
