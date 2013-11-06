
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.AltMVDMessagesType;
import iso.std.iso_iec._24727.tech.schema.AltVUMessagesType;


/**
 * <p>Java class for LocalizedMessagesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocalizedMessagesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DefaultVUMessages" type="{urn:iso:std:iso-iec:24727:tech:schema}AltVUMessagesType"/>
 *         &lt;element name="DefaultMVDMessages" type="{urn:iso:std:iso-iec:24727:tech:schema}AltMVDMessagesType"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang use="required""/>
 *       &lt;attribute name="Protocol" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="urn:oid:1.0.24727.3.0.0.7" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LocalizedMessagesType", propOrder = {
    "defaultVUMessages",
    "defaultMVDMessages"
})
public class LocalizedMessagesType {

    @XmlElement(name = "DefaultVUMessages", required = true)
    protected AltVUMessagesType defaultVUMessages;
    @XmlElement(name = "DefaultMVDMessages", required = true)
    protected AltMVDMessagesType defaultMVDMessages;
    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace", required = true)
    protected String lang;
    @XmlAttribute(name = "Protocol")
    @XmlSchemaType(name = "anyURI")
    protected String protocol;

    /**
     * Gets the value of the defaultVUMessages property.
     * 
     * @return
     *     possible object is
     *     {@link AltVUMessagesType }
     *     
     */
    public AltVUMessagesType getDefaultVUMessages() {
        return defaultVUMessages;
    }

    /**
     * Sets the value of the defaultVUMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link AltVUMessagesType }
     *     
     */
    public void setDefaultVUMessages(AltVUMessagesType value) {
        this.defaultVUMessages = value;
    }

    /**
     * Gets the value of the defaultMVDMessages property.
     * 
     * @return
     *     possible object is
     *     {@link AltMVDMessagesType }
     *     
     */
    public AltMVDMessagesType getDefaultMVDMessages() {
        return defaultMVDMessages;
    }

    /**
     * Sets the value of the defaultMVDMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link AltMVDMessagesType }
     *     
     */
    public void setDefaultMVDMessages(AltMVDMessagesType value) {
        this.defaultMVDMessages = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocol() {
        if (protocol == null) {
            return "urn:oid:1.0.24727.3.0.0.7";
        } else {
            return protocol;
        }
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocol(String value) {
        this.protocol = value;
    }

}
