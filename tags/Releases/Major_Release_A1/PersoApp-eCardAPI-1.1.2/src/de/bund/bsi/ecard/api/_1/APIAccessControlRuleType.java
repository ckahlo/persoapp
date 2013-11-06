
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APIAccessControlRuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APIAccessControlRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "APIAccessControlRuleType", propOrder = {
    "apiAccessRule"
})
public class APIAccessControlRuleType {

    @XmlElement(name = "APIAccessRule", required = true)
    protected APIAccessRuleType apiAccessRule;

    /**
     * Gets the value of the apiAccessRule property.
     * 
     * @return
     *     possible object is
     *     {@link APIAccessRuleType }
     *     
     */
    public APIAccessRuleType getAPIAccessRule() {
        return apiAccessRule;
    }

    /**
     * Sets the value of the apiAccessRule property.
     * 
     * @param value
     *     allowed object is
     *     {@link APIAccessRuleType }
     *     
     */
    public void setAPIAccessRule(APIAccessRuleType value) {
        this.apiAccessRule = value;
    }

}
