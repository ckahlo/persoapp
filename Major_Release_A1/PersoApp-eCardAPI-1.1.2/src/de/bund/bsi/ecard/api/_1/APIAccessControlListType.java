
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APIAccessControlListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APIAccessControlListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="APIAccessRule" type="{http://www.bsi.bund.de/ecard/api/1.1}APIAccessRuleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APIAccessControlListType", propOrder = {
    "apiAccessRule"
})
public class APIAccessControlListType {

    @XmlElement(name = "APIAccessRule")
    protected List<APIAccessRuleType> apiAccessRule;

    /**
     * Gets the value of the apiAccessRule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apiAccessRule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAPIAccessRule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link APIAccessRuleType }
     * 
     * 
     */
    public List<APIAccessRuleType> getAPIAccessRule() {
        if (apiAccessRule == null) {
            apiAccessRule = new ArrayList<APIAccessRuleType>();
        }
        return this.apiAccessRule;
    }

}
