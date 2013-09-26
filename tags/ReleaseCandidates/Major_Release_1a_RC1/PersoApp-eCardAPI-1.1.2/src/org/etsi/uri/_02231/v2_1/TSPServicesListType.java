
package org.etsi.uri._02231.v2_1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TSPServicesListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSPServicesListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://uri.etsi.org/02231/v2.1.1#}TSPService" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSPServicesListType", propOrder = {
    "tspService"
})
public class TSPServicesListType {

    @XmlElement(name = "TSPService", required = true)
    protected List<TSPServiceType> tspService;

    /**
     * Gets the value of the tspService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tspService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTSPService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSPServiceType }
     * 
     * 
     */
    public List<TSPServiceType> getTSPService() {
        if (tspService == null) {
            tspService = new ArrayList<TSPServiceType>();
        }
        return this.tspService;
    }

}
