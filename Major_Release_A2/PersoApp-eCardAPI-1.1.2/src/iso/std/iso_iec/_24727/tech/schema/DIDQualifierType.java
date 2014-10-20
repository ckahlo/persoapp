
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DIDQualifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DIDQualifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ApplicationIdentifier" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType"/>
 *         &lt;element name="ObjectIdentifier" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="ApplicationFunction" type="{urn:iso:std:iso-iec:24727:tech:schema}BitString"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDQualifierType", propOrder = {
    "applicationIdentifier",
    "objectIdentifier",
    "applicationFunction"
})
public class DIDQualifierType {

    @XmlElement(name = "ApplicationIdentifier", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] applicationIdentifier;
    @XmlElement(name = "ObjectIdentifier")
    @XmlSchemaType(name = "anyURI")
    protected String objectIdentifier;
    @XmlElement(name = "ApplicationFunction")
    protected String applicationFunction;

    /**
     * Gets the value of the applicationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getApplicationIdentifier() {
        return applicationIdentifier;
    }

    /**
     * Sets the value of the applicationIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationIdentifier(byte[] value) {
        this.applicationIdentifier = ((byte[]) value);
    }

    /**
     * Gets the value of the objectIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjectIdentifier() {
        return objectIdentifier;
    }

    /**
     * Sets the value of the objectIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjectIdentifier(String value) {
        this.objectIdentifier = value;
    }

    /**
     * Gets the value of the applicationFunction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationFunction() {
        return applicationFunction;
    }

    /**
     * Sets the value of the applicationFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationFunction(String value) {
        this.applicationFunction = value;
    }

}
