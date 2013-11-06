
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import iso.std.iso_iec._24727.tech.schema.DIDScopeType;
import org.w3._2000._09.xmldsig_.X509IssuerSerialType;


/**
 * <p>Java class for APIAuthenticationStateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APIAuthenticationStateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/>
 *             &lt;element name="DIDScope" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDScopeType" minOccurs="0"/>
 *             &lt;element name="DIDStateQualifier" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;element name="Certificate" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType"/>
 *         &lt;/choice>
 *         &lt;element name="AuthenticationState" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APIAuthenticationStateType", propOrder = {
    "didName",
    "didScope",
    "didStateQualifier",
    "certificate",
    "authenticationState"
})
public class APIAuthenticationStateType {

    @XmlElement(name = "DIDName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;
    @XmlElement(name = "DIDScope")
    protected DIDScopeType didScope;
    @XmlElement(name = "DIDStateQualifier", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] didStateQualifier;
    @XmlElement(name = "Certificate")
    protected X509IssuerSerialType certificate;
    @XmlElement(name = "AuthenticationState")
    protected boolean authenticationState;

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
     * Gets the value of the didStateQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getDIDStateQualifier() {
        return didStateQualifier;
    }

    /**
     * Sets the value of the didStateQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDStateQualifier(byte[] value) {
        this.didStateQualifier = ((byte[]) value);
    }

    /**
     * Gets the value of the certificate property.
     * 
     * @return
     *     possible object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public X509IssuerSerialType getCertificate() {
        return certificate;
    }

    /**
     * Sets the value of the certificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public void setCertificate(X509IssuerSerialType value) {
        this.certificate = value;
    }

    /**
     * Gets the value of the authenticationState property.
     * 
     */
    public boolean isAuthenticationState() {
        return authenticationState;
    }

    /**
     * Sets the value of the authenticationState property.
     * 
     */
    public void setAuthenticationState(boolean value) {
        this.authenticationState = value;
    }

}
