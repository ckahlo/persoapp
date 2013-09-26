
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.IncludeObject;
import oasis.names.tc.dss._1_0.core.schema.Properties;
import oasis.names.tc.dss._1_0.core.schema.SchemasType;
import oasis.names.tc.dss._1_0.core.schema.SignaturePlacement;


/**
 * <p>Java class for SignOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SignatureForm" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="SignatureType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Properties" minOccurs="0"/>
 *         &lt;element name="IncludeEContent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}IncludeObject" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}SignaturePlacement" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Schemas" minOccurs="0"/>
 *         &lt;element name="TrustedViewerInfo" type="{http://www.bsi.bund.de/ecard/api/1.1}TrustedViewerInfoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignOptionsType", propOrder = {
    "signatureForm",
    "signatureType",
    "properties",
    "includeEContent",
    "includeObject",
    "signaturePlacement",
    "schemas",
    "trustedViewerInfo"
})
public class SignOptionsType {

    @XmlElement(name = "SignatureForm")
    @XmlSchemaType(name = "anyURI")
    protected String signatureForm;
    @XmlElement(name = "SignatureType")
    @XmlSchemaType(name = "anyURI")
    protected String signatureType;
    @XmlElement(name = "Properties", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected Properties properties;
    @XmlElement(name = "IncludeEContent")
    protected Boolean includeEContent;
    @XmlElement(name = "IncludeObject", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected List<IncludeObject> includeObject;
    @XmlElement(name = "SignaturePlacement", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected SignaturePlacement signaturePlacement;
    @XmlElement(name = "Schemas", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected SchemasType schemas;
    @XmlElement(name = "TrustedViewerInfo")
    protected TrustedViewerInfoType trustedViewerInfo;

    /**
     * Gets the value of the signatureForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureForm() {
        return signatureForm;
    }

    /**
     * Sets the value of the signatureForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureForm(String value) {
        this.signatureForm = value;
    }

    /**
     * Gets the value of the signatureType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureType() {
        return signatureType;
    }

    /**
     * Sets the value of the signatureType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureType(String value) {
        this.signatureType = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link Properties }
     *     
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Properties }
     *     
     */
    public void setProperties(Properties value) {
        this.properties = value;
    }

    /**
     * Gets the value of the includeEContent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeEContent() {
        return includeEContent;
    }

    /**
     * Sets the value of the includeEContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeEContent(Boolean value) {
        this.includeEContent = value;
    }

    /**
     * Gets the value of the includeObject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includeObject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludeObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IncludeObject }
     * 
     * 
     */
    public List<IncludeObject> getIncludeObject() {
        if (includeObject == null) {
            includeObject = new ArrayList<IncludeObject>();
        }
        return this.includeObject;
    }

    /**
     * Gets the value of the signaturePlacement property.
     * 
     * @return
     *     possible object is
     *     {@link SignaturePlacement }
     *     
     */
    public SignaturePlacement getSignaturePlacement() {
        return signaturePlacement;
    }

    /**
     * Sets the value of the signaturePlacement property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignaturePlacement }
     *     
     */
    public void setSignaturePlacement(SignaturePlacement value) {
        this.signaturePlacement = value;
    }

    /**
     * Gets the value of the schemas property.
     * 
     * @return
     *     possible object is
     *     {@link SchemasType }
     *     
     */
    public SchemasType getSchemas() {
        return schemas;
    }

    /**
     * Sets the value of the schemas property.
     * 
     * @param value
     *     allowed object is
     *     {@link SchemasType }
     *     
     */
    public void setSchemas(SchemasType value) {
        this.schemas = value;
    }

    /**
     * Gets the value of the trustedViewerInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TrustedViewerInfoType }
     *     
     */
    public TrustedViewerInfoType getTrustedViewerInfo() {
        return trustedViewerInfo;
    }

    /**
     * Sets the value of the trustedViewerInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustedViewerInfoType }
     *     
     */
    public void setTrustedViewerInfo(TrustedViewerInfoType value) {
        this.trustedViewerInfo = value;
    }

}
