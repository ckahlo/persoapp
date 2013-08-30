
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccessRuleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessRuleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CardApplicationServiceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Action" type="{urn:iso:std:iso-iec:24727:tech:schema}ActionNameType"/>
 *         &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessRuleType", propOrder = {
    "cardApplicationServiceName",
    "action",
    "securityCondition"
})
public class AccessRuleType {

    @XmlElement(name = "CardApplicationServiceName", required = true)
    protected String cardApplicationServiceName;
    @XmlElement(name = "Action", required = true)
    protected ActionNameType action;
    @XmlElement(name = "SecurityCondition", required = true)
    protected SecurityConditionType securityCondition;

    /**
     * Gets the value of the cardApplicationServiceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardApplicationServiceName() {
        return cardApplicationServiceName;
    }

    /**
     * Sets the value of the cardApplicationServiceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardApplicationServiceName(String value) {
        this.cardApplicationServiceName = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link ActionNameType }
     *     
     */
    public ActionNameType getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionNameType }
     *     
     */
    public void setAction(ActionNameType value) {
        this.action = value;
    }

    /**
     * Gets the value of the securityCondition property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityConditionType }
     *     
     */
    public SecurityConditionType getSecurityCondition() {
        return securityCondition;
    }

    /**
     * Sets the value of the securityCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityConditionType }
     *     
     */
    public void setSecurityCondition(SecurityConditionType value) {
        this.securityCondition = value;
    }

}
