
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.saml._1_0.assertion.NameIdentifierType;
import oasis.names.tc.saml._2_0.assertion.NameIDType;
import org.w3._2000._09.xmldsig_.X509DataType;


/**
 * <p>Java class for IdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdentifierType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}X509Data" minOccurs="0"/>
 *         &lt;element name="SAMLv1Identifier" type="{urn:oasis:names:tc:SAML:1.0:assertion}NameIdentifierType" minOccurs="0"/>
 *         &lt;element name="SAMLv2Identifier" type="{urn:oasis:names:tc:SAML:2.0:assertion}NameIDType" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentifierType", propOrder = {
    "x509Data",
    "samLv1Identifier",
    "samLv2Identifier",
    "other"
})
public class IdentifierType {

    @XmlElement(name = "X509Data", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected X509DataType x509Data;
    @XmlElement(name = "SAMLv1Identifier")
    protected NameIdentifierType samLv1Identifier;
    @XmlElement(name = "SAMLv2Identifier")
    protected NameIDType samLv2Identifier;
    @XmlElement(name = "Other")
    protected AnyType other;

    /**
     * Gets the value of the x509Data property.
     * 
     * @return
     *     possible object is
     *     {@link X509DataType }
     *     
     */
    public X509DataType getX509Data() {
        return x509Data;
    }

    /**
     * Sets the value of the x509Data property.
     * 
     * @param value
     *     allowed object is
     *     {@link X509DataType }
     *     
     */
    public void setX509Data(X509DataType value) {
        this.x509Data = value;
    }

    /**
     * Gets the value of the samLv1Identifier property.
     * 
     * @return
     *     possible object is
     *     {@link NameIdentifierType }
     *     
     */
    public NameIdentifierType getSAMLv1Identifier() {
        return samLv1Identifier;
    }

    /**
     * Sets the value of the samLv1Identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIdentifierType }
     *     
     */
    public void setSAMLv1Identifier(NameIdentifierType value) {
        this.samLv1Identifier = value;
    }

    /**
     * Gets the value of the samLv2Identifier property.
     * 
     * @return
     *     possible object is
     *     {@link NameIDType }
     *     
     */
    public NameIDType getSAMLv2Identifier() {
        return samLv2Identifier;
    }

    /**
     * Sets the value of the samLv2Identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameIDType }
     *     
     */
    public void setSAMLv2Identifier(NameIDType value) {
        this.samLv2Identifier = value;
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

}
