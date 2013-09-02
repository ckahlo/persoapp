
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
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="AuthenticationProtocolData" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType"/>
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
    "authenticationProtocolData"
})
@XmlRootElement(name = "DIDAuthenticateResponse")
public class DIDAuthenticateResponse
    extends ResponseType
{

    @XmlElement(name = "AuthenticationProtocolData", required = true)
    protected DIDAuthenticationDataType authenticationProtocolData;

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

}
