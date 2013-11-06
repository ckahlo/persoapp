
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SecurityConditionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SecurityConditionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="DIDAuthentication" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationStateType"/>
 *         &lt;element name="always" type="{urn:iso:std:iso-iec:24727:tech:schema}TrueType"/>
 *         &lt;element name="never" type="{urn:iso:std:iso-iec:24727:tech:schema}FalseType"/>
 *         &lt;element name="not" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/>
 *         &lt;element name="and">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="255">
 *                   &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="or">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="255">
 *                   &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SecurityConditionType", propOrder = {
    "didAuthentication",
    "always",
    "never",
    "not",
    "and",
    "or"
})
public class SecurityConditionType {

    @XmlElement(name = "DIDAuthentication")
    protected DIDAuthenticationStateType didAuthentication;
    protected Boolean always;
    protected Boolean never;
    protected SecurityConditionType not;
    protected SecurityConditionType.And and;
    protected SecurityConditionType.Or or;

    /**
     * Gets the value of the didAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link DIDAuthenticationStateType }
     *     
     */
    public DIDAuthenticationStateType getDIDAuthentication() {
        return didAuthentication;
    }

    /**
     * Sets the value of the didAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link DIDAuthenticationStateType }
     *     
     */
    public void setDIDAuthentication(DIDAuthenticationStateType value) {
        this.didAuthentication = value;
    }

    /**
     * Gets the value of the always property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAlways() {
        return always;
    }

    /**
     * Sets the value of the always property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAlways(Boolean value) {
        this.always = value;
    }

    /**
     * Gets the value of the never property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNever() {
        return never;
    }

    /**
     * Sets the value of the never property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNever(Boolean value) {
        this.never = value;
    }

    /**
     * Gets the value of the not property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityConditionType }
     *     
     */
    public SecurityConditionType getNot() {
        return not;
    }

    /**
     * Sets the value of the not property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityConditionType }
     *     
     */
    public void setNot(SecurityConditionType value) {
        this.not = value;
    }

    /**
     * Gets the value of the and property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityConditionType.And }
     *     
     */
    public SecurityConditionType.And getAnd() {
        return and;
    }

    /**
     * Sets the value of the and property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityConditionType.And }
     *     
     */
    public void setAnd(SecurityConditionType.And value) {
        this.and = value;
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link SecurityConditionType.Or }
     *     
     */
    public SecurityConditionType.Or getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link SecurityConditionType.Or }
     *     
     */
    public void setOr(SecurityConditionType.Or value) {
        this.or = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="255">
     *         &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "securityCondition"
    })
    public static class And {

        @XmlElement(name = "SecurityCondition", required = true)
        protected List<SecurityConditionType> securityCondition;

        /**
         * Gets the value of the securityCondition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the securityCondition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSecurityCondition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SecurityConditionType }
         * 
         * 
         */
        public List<SecurityConditionType> getSecurityCondition() {
            if (securityCondition == null) {
                securityCondition = new ArrayList<SecurityConditionType>();
            }
            return this.securityCondition;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="255">
     *         &lt;element name="SecurityCondition" type="{urn:iso:std:iso-iec:24727:tech:schema}SecurityConditionType"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "securityCondition"
    })
    public static class Or {

        @XmlElement(name = "SecurityCondition", required = true)
        protected List<SecurityConditionType> securityCondition;

        /**
         * Gets the value of the securityCondition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the securityCondition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSecurityCondition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SecurityConditionType }
         * 
         * 
         */
        public List<SecurityConditionType> getSecurityCondition() {
            if (securityCondition == null) {
                securityCondition = new ArrayList<SecurityConditionType>();
            }
            return this.securityCondition;
        }

    }

}
