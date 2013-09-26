
package oasis.names.tc.dss_x._1_0.profiles.encryption.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3._2001._04.xmlenc_.EncryptionPropertiesType;


/**
 * 
 * 				Could be rename to EncryptDocument since a dss:Document
 * 				is referenced. However, dss:Document is somewhat
 * 				misleading (also Base64Data), and several
 * 				EncryptionContents might result in one EncryptedDocument
 * 				(Enc-Sig-Enc). Do NOT rename to EncryptData, since one
 * 				EncryptionContent might result in multiple
 * 				xenc:EncryptedData elts.
 * 			
 * 
 * <p>Java class for EncryptionContentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncryptionContentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DetachEncryptedKeys" type="{urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#}DestinationSelectorType" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}EncryptionProperties" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="EncryptAndReplaceData" type="{urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#}SelectorType"/>
 *           &lt;element name="InsertEncryptedData" type="{urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#}InsertEncryptedDataType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="WhichDocument" use="required" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="EncryptedDocumentId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EncryptionSyntax" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="http://www.w3.org/2001/04/xmlenc#" />
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
@XmlType(name = "EncryptionContentType", propOrder = {
    "detachEncryptedKeys",
    "encryptionProperties",
    "encryptAndReplaceDataOrInsertEncryptedData"
})
public class EncryptionContentType {

    @XmlElement(name = "DetachEncryptedKeys")
    protected DestinationSelectorType detachEncryptedKeys;
    @XmlElement(name = "EncryptionProperties", namespace = "http://www.w3.org/2001/04/xmlenc#")
    protected EncryptionPropertiesType encryptionProperties;
    @XmlElements({
        @XmlElement(name = "EncryptAndReplaceData", type = SelectorType.class),
        @XmlElement(name = "InsertEncryptedData", type = InsertEncryptedDataType.class)
    })
    protected List<Object> encryptAndReplaceDataOrInsertEncryptedData;
    @XmlAttribute(name = "WhichDocument", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object whichDocument;
    @XmlAttribute(name = "EncryptedDocumentId")
    protected String encryptedDocumentId;
    @XmlAttribute(name = "EncryptionSyntax")
    @XmlSchemaType(name = "anyURI")
    protected String encryptionSyntax;
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
     * Gets the value of the detachEncryptedKeys property.
     * 
     * @return
     *     possible object is
     *     {@link DestinationSelectorType }
     *     
     */
    public DestinationSelectorType getDetachEncryptedKeys() {
        return detachEncryptedKeys;
    }

    /**
     * Sets the value of the detachEncryptedKeys property.
     * 
     * @param value
     *     allowed object is
     *     {@link DestinationSelectorType }
     *     
     */
    public void setDetachEncryptedKeys(DestinationSelectorType value) {
        this.detachEncryptedKeys = value;
    }

    /**
     * 
     * 						TODO If present, the individual properties MAY
     * 						be taken into account during request processing.
     * 						They MUST be added to the all resulting
     * 						xenc:EncryptedData elements. The service MAY add
     * 						additional properties.
     * 					
     * 
     * @return
     *     possible object is
     *     {@link EncryptionPropertiesType }
     *     
     */
    public EncryptionPropertiesType getEncryptionProperties() {
        return encryptionProperties;
    }

    /**
     * Sets the value of the encryptionProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionPropertiesType }
     *     
     */
    public void setEncryptionProperties(EncryptionPropertiesType value) {
        this.encryptionProperties = value;
    }

    /**
     * Gets the value of the encryptAndReplaceDataOrInsertEncryptedData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the encryptAndReplaceDataOrInsertEncryptedData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEncryptAndReplaceDataOrInsertEncryptedData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectorType }
     * {@link InsertEncryptedDataType }
     * 
     * 
     */
    public List<Object> getEncryptAndReplaceDataOrInsertEncryptedData() {
        if (encryptAndReplaceDataOrInsertEncryptedData == null) {
            encryptAndReplaceDataOrInsertEncryptedData = new ArrayList<Object>();
        }
        return this.encryptAndReplaceDataOrInsertEncryptedData;
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
     * Gets the value of the encryptedDocumentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptedDocumentId() {
        return encryptedDocumentId;
    }

    /**
     * Sets the value of the encryptedDocumentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptedDocumentId(String value) {
        this.encryptedDocumentId = value;
    }

    /**
     * Gets the value of the encryptionSyntax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptionSyntax() {
        if (encryptionSyntax == null) {
            return "http://www.w3.org/2001/04/xmlenc#";
        } else {
            return encryptionSyntax;
        }
    }

    /**
     * Sets the value of the encryptionSyntax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptionSyntax(String value) {
        this.encryptionSyntax = value;
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
