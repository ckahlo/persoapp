
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActionNameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionNameType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="APIAccessEntryPoint" type="{urn:iso:std:iso-iec:24727:tech:schema}APIAccessEntryPointName"/>
 *         &lt;element name="ConnectionServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionServiceActionName"/>
 *         &lt;element name="CardApplicationServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationServiceActionName"/>
 *         &lt;element name="NamedDataServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}NamedDataServiceActionName"/>
 *         &lt;element name="CryptographicServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}CryptographicServiceActionName"/>
 *         &lt;element name="DifferentialIdentityServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}DifferentialIdentityServiceActionName"/>
 *         &lt;element name="AuthorizationServiceAction" type="{urn:iso:std:iso-iec:24727:tech:schema}AuthorizationServiceActionName"/>
 *         &lt;element name="LoadedAction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionNameType", propOrder = {
    "apiAccessEntryPoint",
    "connectionServiceAction",
    "cardApplicationServiceAction",
    "namedDataServiceAction",
    "cryptographicServiceAction",
    "differentialIdentityServiceAction",
    "authorizationServiceAction",
    "loadedAction"
})
public class ActionNameType {

    @XmlElement(name = "APIAccessEntryPoint")
    protected APIAccessEntryPointName apiAccessEntryPoint;
    @XmlElement(name = "ConnectionServiceAction")
    protected ConnectionServiceActionName connectionServiceAction;
    @XmlElement(name = "CardApplicationServiceAction")
    protected CardApplicationServiceActionName cardApplicationServiceAction;
    @XmlElement(name = "NamedDataServiceAction")
    protected NamedDataServiceActionName namedDataServiceAction;
    @XmlElement(name = "CryptographicServiceAction")
    protected CryptographicServiceActionName cryptographicServiceAction;
    @XmlElement(name = "DifferentialIdentityServiceAction")
    protected DifferentialIdentityServiceActionName differentialIdentityServiceAction;
    @XmlElement(name = "AuthorizationServiceAction")
    protected AuthorizationServiceActionName authorizationServiceAction;
    @XmlElement(name = "LoadedAction")
    protected String loadedAction;

    /**
     * Gets the value of the apiAccessEntryPoint property.
     * 
     * @return
     *     possible object is
     *     {@link APIAccessEntryPointName }
     *     
     */
    public APIAccessEntryPointName getAPIAccessEntryPoint() {
        return apiAccessEntryPoint;
    }

    /**
     * Sets the value of the apiAccessEntryPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link APIAccessEntryPointName }
     *     
     */
    public void setAPIAccessEntryPoint(APIAccessEntryPointName value) {
        this.apiAccessEntryPoint = value;
    }

    /**
     * Gets the value of the connectionServiceAction property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionServiceActionName }
     *     
     */
    public ConnectionServiceActionName getConnectionServiceAction() {
        return connectionServiceAction;
    }

    /**
     * Sets the value of the connectionServiceAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionServiceActionName }
     *     
     */
    public void setConnectionServiceAction(ConnectionServiceActionName value) {
        this.connectionServiceAction = value;
    }

    /**
     * Gets the value of the cardApplicationServiceAction property.
     * 
     * @return
     *     possible object is
     *     {@link CardApplicationServiceActionName }
     *     
     */
    public CardApplicationServiceActionName getCardApplicationServiceAction() {
        return cardApplicationServiceAction;
    }

    /**
     * Sets the value of the cardApplicationServiceAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardApplicationServiceActionName }
     *     
     */
    public void setCardApplicationServiceAction(CardApplicationServiceActionName value) {
        this.cardApplicationServiceAction = value;
    }

    /**
     * Gets the value of the namedDataServiceAction property.
     * 
     * @return
     *     possible object is
     *     {@link NamedDataServiceActionName }
     *     
     */
    public NamedDataServiceActionName getNamedDataServiceAction() {
        return namedDataServiceAction;
    }

    /**
     * Sets the value of the namedDataServiceAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamedDataServiceActionName }
     *     
     */
    public void setNamedDataServiceAction(NamedDataServiceActionName value) {
        this.namedDataServiceAction = value;
    }

    /**
     * Gets the value of the cryptographicServiceAction property.
     * 
     * @return
     *     possible object is
     *     {@link CryptographicServiceActionName }
     *     
     */
    public CryptographicServiceActionName getCryptographicServiceAction() {
        return cryptographicServiceAction;
    }

    /**
     * Sets the value of the cryptographicServiceAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link CryptographicServiceActionName }
     *     
     */
    public void setCryptographicServiceAction(CryptographicServiceActionName value) {
        this.cryptographicServiceAction = value;
    }

    /**
     * Gets the value of the differentialIdentityServiceAction property.
     * 
     * @return
     *     possible object is
     *     {@link DifferentialIdentityServiceActionName }
     *     
     */
    public DifferentialIdentityServiceActionName getDifferentialIdentityServiceAction() {
        return differentialIdentityServiceAction;
    }

    /**
     * Sets the value of the differentialIdentityServiceAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link DifferentialIdentityServiceActionName }
     *     
     */
    public void setDifferentialIdentityServiceAction(DifferentialIdentityServiceActionName value) {
        this.differentialIdentityServiceAction = value;
    }

    /**
     * Gets the value of the authorizationServiceAction property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorizationServiceActionName }
     *     
     */
    public AuthorizationServiceActionName getAuthorizationServiceAction() {
        return authorizationServiceAction;
    }

    /**
     * Sets the value of the authorizationServiceAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorizationServiceActionName }
     *     
     */
    public void setAuthorizationServiceAction(AuthorizationServiceActionName value) {
        this.authorizationServiceAction = value;
    }

    /**
     * Gets the value of the loadedAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoadedAction() {
        return loadedAction;
    }

    /**
     * Sets the value of the loadedAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoadedAction(String value) {
        this.loadedAction = value;
    }

}
