
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
 *         &lt;element ref="{http://www.bsi.bund.de/ecard/api/1.1}Certificate" maxOccurs="unbounded"/>
 *         &lt;element name="AddCertificateOptions" type="{http://www.bsi.bund.de/ecard/api/1.1}AddCertificateOptionsType" minOccurs="0"/>
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
    "certificate",
    "addCertificateOptions"
})
@XmlRootElement(name = "AddCertificate")
public class AddCertificate
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "Certificate", required = true)
    protected List<Certificate> certificate;
    @XmlElement(name = "AddCertificateOptions")
    protected AddCertificateOptionsType addCertificateOptions;

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
     * Gets the value of the certificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Certificate }
     * 
     * 
     */
    public List<Certificate> getCertificate() {
        if (certificate == null) {
            certificate = new ArrayList<Certificate>();
        }
        return this.certificate;
    }

    /**
     * Gets the value of the addCertificateOptions property.
     * 
     * @return
     *     possible object is
     *     {@link AddCertificateOptionsType }
     *     
     */
    public AddCertificateOptionsType getAddCertificateOptions() {
        return addCertificateOptions;
    }

    /**
     * Sets the value of the addCertificateOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddCertificateOptionsType }
     *     
     */
    public void setAddCertificateOptions(AddCertificateOptionsType value) {
        this.addCertificateOptions = value;
    }

}
