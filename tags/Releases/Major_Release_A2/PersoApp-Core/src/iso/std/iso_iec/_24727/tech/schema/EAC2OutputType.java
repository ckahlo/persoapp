
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for EAC2OutputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EAC2OutputType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="EFCardSecurity" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *           &lt;element name="AuthenticationToken" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *           &lt;element name="Nonce" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;/sequence>
 *         &lt;element name="Challenge" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *       &lt;/choice>
 *       &lt;anyAttribute processContents='skip'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EAC2OutputType", propOrder = {
    "efCardSecurity",
    "authenticationToken",
    "nonce",
    "challenge"
})
public class EAC2OutputType
    extends DIDAuthenticationDataType
{

    @XmlElement(name = "EFCardSecurity", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] efCardSecurity;
    @XmlElement(name = "AuthenticationToken", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] authenticationToken;
    @XmlElement(name = "Nonce", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] nonce;
    @XmlElement(name = "Challenge", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] challenge;

    /**
     * Gets the value of the efCardSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getEFCardSecurity() {
        return efCardSecurity;
    }

    /**
     * Sets the value of the efCardSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEFCardSecurity(byte[] value) {
        this.efCardSecurity = ((byte[]) value);
    }

    /**
     * Gets the value of the authenticationToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getAuthenticationToken() {
        return authenticationToken;
    }

    /**
     * Sets the value of the authenticationToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticationToken(byte[] value) {
        this.authenticationToken = ((byte[]) value);
    }

    /**
     * Gets the value of the nonce property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getNonce() {
        return nonce;
    }

    /**
     * Sets the value of the nonce property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonce(byte[] value) {
        this.nonce = ((byte[]) value);
    }

    /**
     * Gets the value of the challenge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getChallenge() {
        return challenge;
    }

    /**
     * Sets the value of the challenge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChallenge(byte[] value) {
        this.challenge = ((byte[]) value);
    }

}
