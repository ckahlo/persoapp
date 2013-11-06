
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APIAccessRuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APIAccessRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="APICall" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TC_Protocol" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="APISecurityCondition" type="{http://www.bsi.bund.de/ecard/api/1.1}APISecurityConditionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APIAccessRuleType", propOrder = {
    "apiCall",
    "address",
    "tcProtocol",
    "apiSecurityCondition"
})
public class APIAccessRuleType {

    @XmlElement(name = "APICall", required = true)
    protected String apiCall;
    @XmlElement(name = "Address")
    protected String address;
    @XmlElement(name = "TC_Protocol")
    @XmlSchemaType(name = "anyURI")
    protected String tcProtocol;
    @XmlElement(name = "APISecurityCondition", required = true)
    protected APISecurityConditionType apiSecurityCondition;

    /**
     * Gets the value of the apiCall property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPICall() {
        return apiCall;
    }

    /**
     * Sets the value of the apiCall property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPICall(String value) {
        this.apiCall = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the tcProtocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTCProtocol() {
        return tcProtocol;
    }

    /**
     * Sets the value of the tcProtocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTCProtocol(String value) {
        this.tcProtocol = value;
    }

    /**
     * Gets the value of the apiSecurityCondition property.
     * 
     * @return
     *     possible object is
     *     {@link APISecurityConditionType }
     *     
     */
    public APISecurityConditionType getAPISecurityCondition() {
        return apiSecurityCondition;
    }

    /**
     * Sets the value of the apiSecurityCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link APISecurityConditionType }
     *     
     */
    public void setAPISecurityCondition(APISecurityConditionType value) {
        this.apiSecurityCondition = value;
    }

}
