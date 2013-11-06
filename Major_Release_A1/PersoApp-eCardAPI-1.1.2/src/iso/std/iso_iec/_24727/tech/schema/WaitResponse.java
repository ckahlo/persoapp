
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="IFDEvent" type="{urn:iso:std:iso-iec:24727:tech:schema}IFDStatusType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SessionIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ifdEvent",
    "sessionIdentifier"
})
@XmlRootElement(name = "WaitResponse")
public class WaitResponse
    extends ResponseType
{

    @XmlElement(name = "IFDEvent")
    protected List<IFDStatusType> ifdEvent;
    @XmlElement(name = "SessionIdentifier")
    protected String sessionIdentifier;

    /**
     * Gets the value of the ifdEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ifdEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIFDEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IFDStatusType }
     * 
     * 
     */
    public List<IFDStatusType> getIFDEvent() {
        if (ifdEvent == null) {
            ifdEvent = new ArrayList<IFDStatusType>();
        }
        return this.ifdEvent;
    }

    /**
     * Gets the value of the sessionIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    /**
     * Sets the value of the sessionIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionIdentifier(String value) {
        this.sessionIdentifier = value;
    }

}
