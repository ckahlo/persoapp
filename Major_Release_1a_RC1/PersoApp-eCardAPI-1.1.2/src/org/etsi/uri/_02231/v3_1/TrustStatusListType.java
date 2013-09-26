
package org.etsi.uri._02231.v3_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2000._09.xmldsig_.SignatureType;


/**
 * <p>Java class for TrustStatusListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustStatusListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://uri.etsi.org/02231/v3.1.2#}SchemeInformation"/>
 *         &lt;element ref="{http://uri.etsi.org/02231/v3.1.2#}TrustServiceProviderList" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TSLTag" use="required" type="{http://uri.etsi.org/02231/v3.1.2#}TSLTagType" />
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustStatusListType", propOrder = {
    "schemeInformation",
    "trustServiceProviderList",
    "signature"
})
public class TrustStatusListType {

    @XmlElement(name = "SchemeInformation", required = true)
    protected TSLSchemeInformationType schemeInformation;
    @XmlElement(name = "TrustServiceProviderList")
    protected TrustServiceProviderListType trustServiceProviderList;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlAttribute(name = "TSLTag", required = true)
    protected String tslTag;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the schemeInformation property.
     * 
     * @return
     *     possible object is
     *     {@link TSLSchemeInformationType }
     *     
     */
    public TSLSchemeInformationType getSchemeInformation() {
        return schemeInformation;
    }

    /**
     * Sets the value of the schemeInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link TSLSchemeInformationType }
     *     
     */
    public void setSchemeInformation(TSLSchemeInformationType value) {
        this.schemeInformation = value;
    }

    /**
     * Gets the value of the trustServiceProviderList property.
     * 
     * @return
     *     possible object is
     *     {@link TrustServiceProviderListType }
     *     
     */
    public TrustServiceProviderListType getTrustServiceProviderList() {
        return trustServiceProviderList;
    }

    /**
     * Sets the value of the trustServiceProviderList property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrustServiceProviderListType }
     *     
     */
    public void setTrustServiceProviderList(TrustServiceProviderListType value) {
        this.trustServiceProviderList = value;
    }

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
     * Gets the value of the tslTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSLTag() {
        return tslTag;
    }

    /**
     * Sets the value of the tslTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSLTag(String value) {
        this.tslTag = value;
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
