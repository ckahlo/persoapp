
package iso.std.iso_iec._24727.tech.schema;

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
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="ConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType"/>
 *         &lt;element name="TargetName" type="{urn:iso:std:iso-iec:24727:tech:schema}TargetNameType"/>
 *         &lt;element name="CardApplicationServiceName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ActionName" type="{urn:iso:std:iso-iec:24727:tech:schema}ActionNameType"/>
 *         &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/>
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
    "connectionHandle",
    "targetName",
    "cardApplicationServiceName",
    "actionName",
    "securityCondition"
})
@XmlRootElement(name = "ACLModify")
public class ACLModify
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "TargetName", required = true)
    protected TargetNameType targetName;
    @XmlElement(name = "CardApplicationServiceName", required = true)
    protected String cardApplicationServiceName;
    @XmlElement(name = "ActionName", required = true)
    protected ActionNameType actionName;
    @XmlElement(name = "SecurityCondition", required = true)
    protected SecurityConditionType securityCondition;

    /**
     * Gets the value of the connectionHandle property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType }
     *     
     */
    public ConnectionHandleType getConnectionHandle() {
        return connectionHandle;
    }

    /**
     * Sets the value of the connectionHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType }
     *     
     */
    public void setConnectionHandle(ConnectionHandleType value) {
        this.connectionHandle = value;
    }

    /**
     * Gets the value of the targetName property.
     * 
     * @return
     *     possible object is
     *     {@link TargetNameType }
     *     
     */
    public TargetNameType getTargetName() {
        return targetName;
    }

    /**
     * Sets the value of the targetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetNameType }
     *     
     */
    public void setTargetName(TargetNameType value) {
        this.targetName = value;
    }

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
     * Gets the value of the actionName property.
     * 
     * @return
     *     possible object is
     *     {@link ActionNameType }
     *     
     */
    public ActionNameType getActionName() {
        return actionName;
    }

    /**
     * Sets the value of the actionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionNameType }
     *     
     */
    public void setActionName(ActionNameType value) {
        this.actionName = value;
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
