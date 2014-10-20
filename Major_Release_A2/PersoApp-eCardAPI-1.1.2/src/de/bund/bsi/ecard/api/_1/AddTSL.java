
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
 *         &lt;element name="TrustStatusList" type="{http://www.bsi.bund.de/ecard/api/1.1}TSLType" maxOccurs="unbounded"/>
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
    "trustStatusList"
})
@XmlRootElement(name = "AddTSL")
public class AddTSL
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "TrustStatusList", required = true)
    protected List<TSLType> trustStatusList;

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
     * Gets the value of the trustStatusList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the trustStatusList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTrustStatusList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSLType }
     * 
     * 
     */
    public List<TSLType> getTrustStatusList() {
        if (trustStatusList == null) {
            trustStatusList = new ArrayList<TSLType>();
        }
        return this.trustStatusList;
    }

}
