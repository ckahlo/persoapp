
package oasis.names.tc.dss_x._1_0.profiles.encryption.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				TODO discuss use cases for InsertEncryptedDataType and
 * 				Type attribute (omit Type attribute? define new Type
 * 				attribute?).
 * 			
 * 
 * <p>Java class for InsertEncryptedDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InsertEncryptedDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncryptedDataDestination" type="{urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#}DestinationSelectorType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="WhichDocument" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="EncryptedDataId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CipherReference" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Encoding" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InsertEncryptedDataType", propOrder = {
    "encryptedDataDestination"
})
public class InsertEncryptedDataType {

    @XmlElement(name = "EncryptedDataDestination", required = true)
    protected DestinationSelectorType encryptedDataDestination;
    @XmlAttribute(name = "WhichDocument")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object whichDocument;
    @XmlAttribute(name = "EncryptedDataId")
    protected String encryptedDataId;
    @XmlAttribute(name = "CipherReference")
    @XmlSchemaType(name = "anyURI")
    protected String cipherReference;
    @XmlAttribute(name = "MimeType")
    protected String mimeType;
    @XmlAttribute(name = "Encoding")
    protected String encoding;

    /**
     * Gets the value of the encryptedDataDestination property.
     * 
     * @return
     *     possible object is
     *     {@link DestinationSelectorType }
     *     
     */
    public DestinationSelectorType getEncryptedDataDestination() {
        return encryptedDataDestination;
    }

    /**
     * Sets the value of the encryptedDataDestination property.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationSelectorType }
     *     
     */
    public void setEncryptedDataDestination(DestinationSelectorType value) {
        this.encryptedDataDestination = value;
    }

    /**
     * Gets the value of the whichDocument property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getWhichDocument() {
        return whichDocument;
    }

    /**
     * Sets the value of the whichDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setWhichDocument(Object value) {
        this.whichDocument = value;
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

    /**
     * Gets the value of the mimeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the value of the mimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

}
