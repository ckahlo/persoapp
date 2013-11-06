
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="DirectoryService" type="{http://www.bsi.bund.de/ecard/api/1.1}ServiceType" maxOccurs="unbounded" minOccurs="0"/>
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
    "directoryService"
})
@XmlRootElement(name = "SetDirectoryServices")
public class SetDirectoryServices
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "DirectoryService")
    protected List<ServiceType> directoryService;

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
     * Gets the value of the directoryService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the directoryService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectoryService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceType }
     * 
     * 
     */
    public List<ServiceType> getDirectoryService() {
        if (directoryService == null) {
            directoryService = new ArrayList<ServiceType>();
        }
        return this.directoryService;
    }

}
