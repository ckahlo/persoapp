
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DIDAuthenticationStateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DIDAuthenticationStateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}NameType"/>
 *         &lt;element name="DIDScope" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDScopeType" minOccurs="0"/>
 *         &lt;element name="DIDState" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DIDStateQualifier" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDAuthenticationStateType", propOrder = {
    "didName",
    "didScope",
    "didState",
    "didStateQualifier"
})
public class DIDAuthenticationStateType {

    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;
    @XmlElement(name = "DIDScope")
    protected DIDScopeType didScope;
    @XmlElement(name = "DIDState")
    protected boolean didState;
    @XmlElement(name = "DIDStateQualifier", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] didStateQualifier;

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
     * Gets the value of the didState property.
     * 
     */
    public boolean isDIDState() {
        return didState;
    }

    /**
     * Sets the value of the didState property.
     * 
     */
    public void setDIDState(boolean value) {
        this.didState = value;
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

}
