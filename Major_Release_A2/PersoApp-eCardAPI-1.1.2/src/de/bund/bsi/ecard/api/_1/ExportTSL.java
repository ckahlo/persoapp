
package de.bund.bsi.ecard.api._1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ChannelHandleType;
import iso.std.iso_iec._24727.tech.schema.RequestType;
import org.etsi.uri._02231.v3_1.InternationalNamesType;


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
 *         &lt;element name="SchemeName" type="{http://uri.etsi.org/02231/v3.1.2#}InternationalNamesType" minOccurs="0"/>
 *         &lt;element name="TSLSequenceNumber" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
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
    "schemeName",
    "tslSequenceNumber"
})
@XmlRootElement(name = "ExportTSL")
public class ExportTSL
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "SchemeName")
    protected InternationalNamesType schemeName;
    @XmlElement(name = "TSLSequenceNumber")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger tslSequenceNumber;

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
     * Gets the value of the schemeName property.
     * 
     * @return
     *     possible object is
     *     {@link InternationalNamesType }
     *     
     */
    public InternationalNamesType getSchemeName() {
        return schemeName;
    }

    /**
     * Sets the value of the schemeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternationalNamesType }
     *     
     */
    public void setSchemeName(InternationalNamesType value) {
        this.schemeName = value;
    }

    /**
     * Gets the value of the tslSequenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTSLSequenceNumber() {
        return tslSequenceNumber;
    }

    /**
     * Sets the value of the tslSequenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTSLSequenceNumber(BigInteger value) {
        this.tslSequenceNumber = value;
    }

}
