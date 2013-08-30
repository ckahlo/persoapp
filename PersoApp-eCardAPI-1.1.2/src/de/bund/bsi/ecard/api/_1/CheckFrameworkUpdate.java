
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
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
 *         &lt;element name="InstalledModule" type="{http://www.bsi.bund.de/ecard/api/1.1}InstalledModuleInfoType" maxOccurs="unbounded"/>
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
    "installedModule"
})
@XmlRootElement(name = "CheckFrameworkUpdate")
public class CheckFrameworkUpdate
    extends RequestType
{

    @XmlElement(name = "InstalledModule", required = true)
    protected List<InstalledModuleInfoType> installedModule;

    /**
     * Gets the value of the installedModule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the installedModule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstalledModule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstalledModuleInfoType }
     * 
     * 
     */
    public List<InstalledModuleInfoType> getInstalledModule() {
        if (installedModule == null) {
            installedModule = new ArrayList<InstalledModuleInfoType>();
        }
        return this.installedModule;
    }

}
