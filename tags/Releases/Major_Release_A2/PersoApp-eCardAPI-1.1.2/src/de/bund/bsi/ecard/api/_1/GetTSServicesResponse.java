
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;


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
 *         &lt;element name="TimeStampingService" type="{http://www.bsi.bund.de/ecard/api/1.1}TSServiceType" maxOccurs="unbounded" minOccurs="0"/>
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
    "timeStampingService"
})
@XmlRootElement(name = "GetTSServicesResponse")
public class GetTSServicesResponse
    extends ResponseType
{

    @XmlElement(name = "TimeStampingService")
    protected List<TSServiceType> timeStampingService;

    /**
     * Gets the value of the timeStampingService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the timeStampingService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeStampingService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSServiceType }
     * 
     * 
     */
    public List<TSServiceType> getTimeStampingService() {
        if (timeStampingService == null) {
            timeStampingService = new ArrayList<TSServiceType>();
        }
        return this.timeStampingService;
    }

}
