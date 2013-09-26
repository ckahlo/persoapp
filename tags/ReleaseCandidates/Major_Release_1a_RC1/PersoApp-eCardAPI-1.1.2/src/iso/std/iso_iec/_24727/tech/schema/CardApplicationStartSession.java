
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="DIDScope" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDScopeType" minOccurs="0"/>
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}NameType"/>
 *         &lt;element name="AuthenticationProtocolData" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType"/>
 *         &lt;element name="SAMConnectionHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ConnectionHandleType" minOccurs="0"/>
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
    "didScope",
    "didName",
    "authenticationProtocolData",
    "samConnectionHandle"
})
@XmlRootElement(name = "CardApplicationStartSession")
public class CardApplicationStartSession
    extends RequestType
{

    @XmlElement(name = "ConnectionHandle", required = true)
    protected ConnectionHandleType connectionHandle;
    @XmlElement(name = "DIDScope")
    protected DIDScopeType didScope;
    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;
    @XmlElement(name = "AuthenticationProtocolData", required = true)
    protected DIDAuthenticationDataType authenticationProtocolData;
    @XmlElement(name = "SAMConnectionHandle")
    protected ConnectionHandleType samConnectionHandle;

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
     * Gets the value of the didScope property.
     * 
     * @return
     *     possible object is
     *     {@link DIDScopeType }
     *     
     */
    public DIDScopeType getDIDScope() {
        return didScope;
    }

    /**
     * Sets the value of the didScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDScopeType }
     *     
     */
    public void setDIDScope(DIDScopeType value) {
        this.didScope = value;
    }

    /**
     * Gets the value of the didName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDName() {
        return didName;
    }

    /**
     * Sets the value of the didName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDName(String value) {
        this.didName = value;
    }

    /**
     * Gets the value of the authenticationProtocolData property.
     * 
     * @return
     *     possible object is
     *     {@link DIDAuthenticationDataType }
     *     
     */
    public DIDAuthenticationDataType getAuthenticationProtocolData() {
        return authenticationProtocolData;
    }

    /**
     * Sets the value of the authenticationProtocolData property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDAuthenticationDataType }
     *     
     */
    public void setAuthenticationProtocolData(DIDAuthenticationDataType value) {
        this.authenticationProtocolData = value;
    }

    /**
     * Gets the value of the samConnectionHandle property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType }
     *     
     */
    public ConnectionHandleType getSAMConnectionHandle() {
        return samConnectionHandle;
    }

    /**
     * Sets the value of the samConnectionHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType }
     *     
     */
    public void setSAMConnectionHandle(ConnectionHandleType value) {
        this.samConnectionHandle = value;
    }

}
