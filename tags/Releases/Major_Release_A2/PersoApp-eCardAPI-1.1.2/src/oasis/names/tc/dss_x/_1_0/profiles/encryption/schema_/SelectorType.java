
package oasis.names.tc.dss_x._1_0.profiles.encryption.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				XPath expression pointing to an (existing) element
 * 				within an input or encrypted document.
 * 			
 * 
 * <p>Java class for SelectorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SelectorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="Selector" use="required" type="{http://www.w3.org/2001/XMLSchema}token" />
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="http://www.w3.org/2001/04/xmlenc#Element" />
 *       &lt;attribute name="EncryptedDataId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CipherReference" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectorType")
public class SelectorType {

    @XmlAttribute(name = "Selector", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String selector;
    @XmlAttribute(name = "Type")
    @XmlSchemaType(name = "anyURI")
    protected String type;
    @XmlAttribute(name = "EncryptedDataId")
    protected String encryptedDataId;
    @XmlAttribute(name = "CipherReference")
    @XmlSchemaType(name = "anyURI")
    protected String cipherReference;

    /**
     * Gets the value of the selector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelector() {
        return selector;
    }

    /**
     * Sets the value of the selector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelector(String value) {
        this.selector = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        if (type == null) {
            return "http://www.w3.org/2001/04/xmlenc#Element";
        } else {
            return type;
        }
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the encryptedDataId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptedDataId() {
        return encryptedDataId;
    }

    /**
     * Sets the value of the encryptedDataId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptedDataId(String value) {
        this.encryptedDataId = value;
    }

    /**
     * Gets the value of the cipherReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCipherReference() {
        return cipherReference;
    }

    /**
     * Sets the value of the cipherReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCipherReference(String value) {
        this.cipherReference = value;
    }

}
