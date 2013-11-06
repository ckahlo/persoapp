
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for TSLType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSLType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="TSLv2.1.1" type="{http://uri.etsi.org/02231/v2.1.1#}TrustStatusListType"/>
 *         &lt;element name="TSLv2.x-gematik" type="{http://uri.etsi.org/02231/v2.x#}TrustStatusListType"/>
 *         &lt;element name="TSLv3.1.2" type="{http://uri.etsi.org/02231/v3.1.2#}TrustStatusListType"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSLType", propOrder = {
    "tsLv211",
    "tsLv2XGematik",
    "tsLv312",
    "other"
})
public class TSLType {

    @XmlElement(name = "TSLv2.1.1")
    protected org.etsi.uri._02231.v2_1.TrustStatusListType tsLv211;
    @XmlElement(name = "TSLv2.x-gematik")
    protected org.etsi.uri._02231.v2.TrustStatusListType tsLv2XGematik;
    @XmlElement(name = "TSLv3.1.2")
    protected org.etsi.uri._02231.v3_1.TrustStatusListType tsLv312;
    @XmlElement(name = "Other")
    protected AnyType other;

    /**
     * Gets the value of the tsLv211 property.
     * 
     * @return
     *     possible object is
     *     {@link org.etsi.uri._02231.v2_1.TrustStatusListType }
     *     
     */
    public org.etsi.uri._02231.v2_1.TrustStatusListType getTSLv211() {
        return tsLv211;
    }

    /**
     * Sets the value of the tsLv211 property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.etsi.uri._02231.v2_1.TrustStatusListType }
     *     
     */
    public void setTSLv211(org.etsi.uri._02231.v2_1.TrustStatusListType value) {
        this.tsLv211 = value;
    }

    /**
     * Gets the value of the tsLv2XGematik property.
     * 
     * @return
     *     possible object is
     *     {@link org.etsi.uri._02231.v2.TrustStatusListType }
     *     
     */
    public org.etsi.uri._02231.v2.TrustStatusListType getTSLv2XGematik() {
        return tsLv2XGematik;
    }

    /**
     * Sets the value of the tsLv2XGematik property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.etsi.uri._02231.v2.TrustStatusListType }
     *     
     */
    public void setTSLv2XGematik(org.etsi.uri._02231.v2.TrustStatusListType value) {
        this.tsLv2XGematik = value;
    }

    /**
     * Gets the value of the tsLv312 property.
     * 
     * @return
     *     possible object is
     *     {@link org.etsi.uri._02231.v3_1.TrustStatusListType }
     *     
     */
    public org.etsi.uri._02231.v3_1.TrustStatusListType getTSLv312() {
        return tsLv312;
    }

    /**
     * Sets the value of the tsLv312 property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.etsi.uri._02231.v3_1.TrustStatusListType }
     *     
     */
    public void setTSLv312(org.etsi.uri._02231.v3_1.TrustStatusListType value) {
        this.tsLv312 = value;
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
