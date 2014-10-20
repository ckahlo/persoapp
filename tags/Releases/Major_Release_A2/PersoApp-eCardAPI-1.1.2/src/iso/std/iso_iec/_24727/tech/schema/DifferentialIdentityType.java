
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.InternationalStringType;


/**
 * <p>Java class for DifferentialIdentityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DifferentialIdentityType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/>
 *         &lt;element name="LocalDIDName" type="{urn:oasis:names:tc:dss:1.0:core:schema}InternationalStringType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DIDProtocol" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="DIDMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDMarkerType"/>
 *         &lt;element name="DIDScope" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDScopeType" minOccurs="0"/>
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
@XmlType(name = "DifferentialIdentityType", propOrder = {
    "didName",
    "localDIDName",
    "didProtocol",
    "didMarker",
    "didScope",
    "didQualifier"
})
public class DifferentialIdentityType {

    @XmlElement(name = "DIDName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;
    @XmlElement(name = "LocalDIDName")
    protected List<InternationalStringType> localDIDName;
    @XmlElement(name = "DIDProtocol", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String didProtocol;
    @XmlElement(name = "DIDMarker", required = true)
    protected DIDMarkerType didMarker;
    @XmlElement(name = "DIDScope")
    protected DIDScopeType didScope;
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
     * Gets the value of the localDIDName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localDIDName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalDIDName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InternationalStringType }
     * 
     * 
     */
    public List<InternationalStringType> getLocalDIDName() {
        if (localDIDName == null) {
            localDIDName = new ArrayList<InternationalStringType>();
        }
        return this.localDIDName;
    }

    /**
     * Gets the value of the didProtocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDProtocol() {
        return didProtocol;
    }

    /**
     * Sets the value of the didProtocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDProtocol(String value) {
        this.didProtocol = value;
    }

    /**
     * Gets the value of the didMarker property.
     * 
     * @return
     *     possible object is
     *     {@link DIDMarkerType }
     *     
     */
    public DIDMarkerType getDIDMarker() {
        return didMarker;
    }

    /**
     * Sets the value of the didMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDMarkerType }
     *     
     */
    public void setDIDMarker(DIDMarkerType value) {
        this.didMarker = value;
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
