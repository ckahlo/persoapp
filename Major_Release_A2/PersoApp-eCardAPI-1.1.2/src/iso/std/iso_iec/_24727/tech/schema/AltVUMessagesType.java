
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AltVUMessagesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AltVUMessagesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthenticationRequestMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuccessMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthenticationFailedMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequestConfirmationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CancelMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AltVUMessagesType", propOrder = {
    "authenticationRequestMessage",
    "successMessage",
    "authenticationFailedMessage",
    "requestConfirmationMessage",
    "cancelMessage"
})
public class AltVUMessagesType {

    @XmlElement(name = "AuthenticationRequestMessage")
    protected String authenticationRequestMessage;
    @XmlElement(name = "SuccessMessage")
    protected String successMessage;
    @XmlElement(name = "AuthenticationFailedMessage")
    protected String authenticationFailedMessage;
    @XmlElement(name = "RequestConfirmationMessage")
    protected String requestConfirmationMessage;
    @XmlElement(name = "CancelMessage")
    protected String cancelMessage;

    /**
     * Gets the value of the authenticationRequestMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationRequestMessage() {
        return authenticationRequestMessage;
    }

    /**
     * Sets the value of the authenticationRequestMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationRequestMessage(String value) {
        this.authenticationRequestMessage = value;
    }

    /**
     * Gets the value of the successMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Sets the value of the successMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessMessage(String value) {
        this.successMessage = value;
    }

    /**
     * Gets the value of the authenticationFailedMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthenticationFailedMessage() {
        return authenticationFailedMessage;
    }

    /**
     * Sets the value of the authenticationFailedMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationFailedMessage(String value) {
        this.authenticationFailedMessage = value;
    }

    /**
     * Gets the value of the requestConfirmationMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestConfirmationMessage() {
        return requestConfirmationMessage;
    }

    /**
     * Sets the value of the requestConfirmationMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestConfirmationMessage(String value) {
        this.requestConfirmationMessage = value;
    }

    /**
     * Gets the value of the cancelMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelMessage() {
        return cancelMessage;
    }

    /**
     * Sets the value of the cancelMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelMessage(String value) {
        this.cancelMessage = value;
    }

}
