
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="ContextHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ContextHandleType"/>
 *         &lt;element name="TimeOut" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="IFDStatus" type="{urn:iso:std:iso-iec:24727:tech:schema}IFDStatusType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Callback" type="{urn:iso:std:iso-iec:24727:tech:schema}ChannelHandleType" minOccurs="0"/>
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
    "contextHandle",
    "timeOut",
    "ifdStatus",
    "callback"
})
@XmlRootElement(name = "Wait")
public class Wait
    extends RequestType
{

    @XmlElement(name = "ContextHandle", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] contextHandle;
    @XmlElement(name = "TimeOut")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger timeOut;
    @XmlElement(name = "IFDStatus")
    protected List<IFDStatusType> ifdStatus;
    @XmlElement(name = "Callback")
    protected ChannelHandleType callback;

    /**
     * Gets the value of the contextHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getContextHandle() {
        return contextHandle;
    }

    /**
     * Sets the value of the contextHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextHandle(byte[] value) {
        this.contextHandle = ((byte[]) value);
    }

    /**
     * Gets the value of the timeOut property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeOut() {
        return timeOut;
    }

    /**
     * Sets the value of the timeOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeOut(BigInteger value) {
        this.timeOut = value;
    }

    /**
     * Gets the value of the ifdStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifdStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIFDStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IFDStatusType }
     * 
     * 
     */
    public List<IFDStatusType> getIFDStatus() {
        if (ifdStatus == null) {
            ifdStatus = new ArrayList<IFDStatusType>();
        }
        return this.ifdStatus;
    }

    /**
     * Gets the value of the callback property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelHandleType }
     *     
     */
    public ChannelHandleType getCallback() {
        return callback;
    }

    /**
     * Sets the value of the callback property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelHandleType }
     *     
     */
    public void setCallback(ChannelHandleType value) {
        this.callback = value;
    }

}
