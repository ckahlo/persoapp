
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for DIDInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DIDInfoType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *       &lt;sequence>
 *         &lt;element name="DifferentialIdentity" type="{urn:iso:std:iso-iec:24727:tech:schema}DifferentialIdentityType"/>
 *         &lt;element name="DIDACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDInfoType", propOrder = {
    "differentialIdentity",
    "didacl",
    "other"
})
public class DIDInfoType
    extends RequirementsType
{

    @XmlElement(name = "DifferentialIdentity", required = true)
    protected DifferentialIdentityType differentialIdentity;
    @XmlElement(name = "DIDACL", required = true)
    protected AccessControlListType didacl;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the differentialIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link DifferentialIdentityType }
     *     
     */
    public DifferentialIdentityType getDifferentialIdentity() {
        return differentialIdentity;
    }

    /**
     * Sets the value of the differentialIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link DifferentialIdentityType }
     *     
     */
    public void setDifferentialIdentity(DifferentialIdentityType value) {
        this.differentialIdentity = value;
    }

    /**
     * Gets the value of the didacl property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getDIDACL() {
        return didacl;
    }

    /**
     * Sets the value of the didacl property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setDIDACL(AccessControlListType value) {
        this.didacl = value;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setOther(AnyType value) {
        this.other = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
