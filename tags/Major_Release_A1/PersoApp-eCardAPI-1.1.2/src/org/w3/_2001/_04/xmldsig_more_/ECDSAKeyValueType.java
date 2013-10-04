
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ECDSAKeyValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECDSAKeyValueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DomainParameters" type="{http://www.w3.org/2001/04/xmldsig-more#}DomainParamsType" minOccurs="0"/>
 *         &lt;element name="PublicKey" type="{http://www.w3.org/2001/04/xmldsig-more#}ECPointType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECDSAKeyValueType", propOrder = {
    "domainParameters",
    "publicKey"
})
public class ECDSAKeyValueType {

    @XmlElement(name = "DomainParameters")
    protected DomainParamsType domainParameters;
    @XmlElement(name = "PublicKey", required = true)
    protected ECPointType publicKey;

    /**
     * Gets the value of the domainParameters property.
     * 
     * @return
     *     possible object is
     *     {@link DomainParamsType }
     *     
     */
    public DomainParamsType getDomainParameters() {
        return domainParameters;
    }

    /**
     * Sets the value of the domainParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainParamsType }
     *     
     */
    public void setDomainParameters(DomainParamsType value) {
        this.domainParameters = value;
    }

    /**
     * Gets the value of the publicKey property.
     * 
     * @return
     *     possible object is
     *     {@link ECPointType }
     *     
     */
    public ECPointType getPublicKey() {
        return publicKey;
    }

    /**
     * Sets the value of the publicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECPointType }
     *     
     */
    public void setPublicKey(ECPointType value) {
        this.publicKey = value;
    }

}
