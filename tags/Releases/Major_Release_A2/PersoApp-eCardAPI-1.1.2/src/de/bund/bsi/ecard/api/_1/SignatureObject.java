
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.Base64Signature;
import oasis.names.tc.dss._1_0.core.schema.SignaturePtr;
import oasis.names.tc.dss._1_0.core.schema.Timestamp;
import org.w3._2000._09.xmldsig_.SignatureType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *           &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Timestamp"/>
 *           &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Base64Signature"/>
 *           &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}SignaturePtr"/>
 *           &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="SchemaRefs" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="SignedDocuments" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "signature",
    "timestamp",
    "base64Signature",
    "signaturePtr",
    "other"
})
@XmlRootElement(name = "SignatureObject")
public class SignatureObject {

    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlElement(name = "Timestamp", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected Timestamp timestamp;
    @XmlElement(name = "Base64Signature", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected Base64Signature base64Signature;
    @XmlElement(name = "SignaturePtr", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected SignaturePtr signaturePtr;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "SchemaRefs")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> schemaRefs;
    @XmlAttribute(name = "SignedDocuments")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> signedDocuments;

    /**
     * Gets the value of the signature property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Sets the value of the signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setTimestamp(Timestamp value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the base64Signature property.
     * 
     * @return
     *     possible object is
     *     {@link Base64Signature }
     *     
     */
    public Base64Signature getBase64Signature() {
        return base64Signature;
    }

    /**
     * Sets the value of the base64Signature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Base64Signature }
     *     
     */
    public void setBase64Signature(Base64Signature value) {
        this.base64Signature = value;
    }

    /**
     * Gets the value of the signaturePtr property.
     * 
     * @return
     *     possible object is
     *     {@link SignaturePtr }
     *     
     */
    public SignaturePtr getSignaturePtr() {
        return signaturePtr;
    }

    /**
     * Sets the value of the signaturePtr property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignaturePtr }
     *     
     */
    public void setSignaturePtr(SignaturePtr value) {
        this.signaturePtr = value;
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
     * Gets the value of the schemaRefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the schemaRefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSchemaRefs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getSchemaRefs() {
        if (schemaRefs == null) {
            schemaRefs = new ArrayList<Object>();
        }
        return this.schemaRefs;
    }

    /**
     * Gets the value of the signedDocuments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signedDocuments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSignedDocuments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getSignedDocuments() {
        if (signedDocuments == null) {
            signedDocuments = new ArrayList<Object>();
        }
        return this.signedDocuments;
    }

}
