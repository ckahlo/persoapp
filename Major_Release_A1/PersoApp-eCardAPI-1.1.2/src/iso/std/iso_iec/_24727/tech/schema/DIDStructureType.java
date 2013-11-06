
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DIDStructureType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DIDStructureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/>
 *         &lt;element name="DIDScope" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDScopeType"/>
 *         &lt;element name="Authenticated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DIDMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType"/>
 *         &lt;element name="DIDQualifier" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDQualifierType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDStructureType", propOrder = {
    "didName",
    "didScope",
    "authenticated",
    "didMarker",
    "didQualifier"
})
public class DIDStructureType {

    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;
    @XmlElement(name = "DIDScope", required = true)
    protected DIDScopeType didScope;
    @XmlElement(name = "Authenticated")
    protected boolean authenticated;
    @XmlElement(name = "DIDMarker", required = true)
    protected DIDAbstractMarkerType didMarker;
    @XmlElement(name = "DIDQualifier")
    protected DIDQualifierType didQualifier;

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
     * Gets the value of the authenticated property.
     * 
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Sets the value of the authenticated property.
     * 
     */
    public void setAuthenticated(boolean value) {
        this.authenticated = value;
    }

    /**
     * Gets the value of the didMarker property.
     * 
     * @return
     *     possible object is
     *     {@link DIDAbstractMarkerType }
     *     
     */
    public DIDAbstractMarkerType getDIDMarker() {
        return didMarker;
    }

    /**
     * Sets the value of the didMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDAbstractMarkerType }
     *     
     */
    public void setDIDMarker(DIDAbstractMarkerType value) {
        this.didMarker = value;
    }

    /**
     * Gets the value of the didQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link DIDQualifierType }
     *     
     */
    public DIDQualifierType getDIDQualifier() {
        return didQualifier;
    }

    /**
     * Sets the value of the didQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDQualifierType }
     *     
     */
    public void setDIDQualifier(DIDQualifierType value) {
        this.didQualifier = value;
    }

}
