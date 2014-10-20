
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APISecurityConditionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="APISecurityConditionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="APIAuthenticationState" type="{http://www.bsi.bund.de/ecard/api/1.1}APIAuthenticationStateType"/>
 *         &lt;element name="always" type="{urn:iso:std:iso-iec:24727:tech:schema}TrueType"/>
 *         &lt;element name="never" type="{urn:iso:std:iso-iec:24727:tech:schema}FalseType"/>
 *         &lt;element name="not" type="{http://www.bsi.bund.de/ecard/api/1.1}APISecurityConditionType"/>
 *         &lt;element name="and">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="255">
 *                   &lt;element name="APISecurityCondition" type="{http://www.bsi.bund.de/ecard/api/1.1}APISecurityConditionType"/>
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
 *                   &lt;element name="APISecurityCondition" type="{http://www.bsi.bund.de/ecard/api/1.1}APISecurityConditionType"/>
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
@XmlType(name = "APISecurityConditionType", propOrder = {
    "apiAuthenticationState",
    "always",
    "never",
    "not",
    "and",
    "or"
})
public class APISecurityConditionType {

    @XmlElement(name = "APIAuthenticationState")
    protected APIAuthenticationStateType apiAuthenticationState;
    protected Boolean always;
    protected Boolean never;
    protected APISecurityConditionType not;
    protected APISecurityConditionType.And and;
    protected APISecurityConditionType.Or or;

    /**
     * Gets the value of the apiAuthenticationState property.
     * 
     * @return
     *     possible object is
     *     {@link APIAuthenticationStateType }
     *     
     */
    public APIAuthenticationStateType getAPIAuthenticationState() {
        return apiAuthenticationState;
    }

    /**
     * Sets the value of the apiAuthenticationState property.
     * 
     * @param value
     *     allowed object is
     *     {@link APIAuthenticationStateType }
     *     
     */
    public void setAPIAuthenticationState(APIAuthenticationStateType value) {
        this.apiAuthenticationState = value;
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
     *     {@link APISecurityConditionType }
     *     
     */
    public APISecurityConditionType getNot() {
        return not;
    }

    /**
     * Sets the value of the not property.
     * 
     * @param value
     *     allowed object is
     *     {@link APISecurityConditionType }
     *     
     */
    public void setNot(APISecurityConditionType value) {
        this.not = value;
    }

    /**
     * Gets the value of the and property.
     * 
     * @return
     *     possible object is
     *     {@link APISecurityConditionType.And }
     *     
     */
    public APISecurityConditionType.And getAnd() {
        return and;
    }

    /**
     * Sets the value of the and property.
     * 
     * @param value
     *     allowed object is
     *     {@link APISecurityConditionType.And }
     *     
     */
    public void setAnd(APISecurityConditionType.And value) {
        this.and = value;
    }

    /**
     * Gets the value of the or property.
     * 
     * @return
     *     possible object is
     *     {@link APISecurityConditionType.Or }
     *     
     */
    public APISecurityConditionType.Or getOr() {
        return or;
    }

    /**
     * Sets the value of the or property.
     * 
     * @param value
     *     allowed object is
     *     {@link APISecurityConditionType.Or }
     *     
     */
    public void setOr(APISecurityConditionType.Or value) {
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
     *         &lt;element name="APISecurityCondition" type="{http://www.bsi.bund.de/ecard/api/1.1}APISecurityConditionType"/>
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
        "apiSecurityCondition"
    })
    public static class And {

        @XmlElement(name = "APISecurityCondition", required = true)
        protected List<APISecurityConditionType> apiSecurityCondition;

        /**
         * Gets the value of the apiSecurityCondition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the apiSecurityCondition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAPISecurityCondition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link APISecurityConditionType }
         * 
         * 
         */
        public List<APISecurityConditionType> getAPISecurityCondition() {
            if (apiSecurityCondition == null) {
                apiSecurityCondition = new ArrayList<APISecurityConditionType>();
            }
            return this.apiSecurityCondition;
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
     *         &lt;element name="APISecurityCondition" type="{http://www.bsi.bund.de/ecard/api/1.1}APISecurityConditionType"/>
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
        "apiSecurityCondition"
    })
    public static class Or {

        @XmlElement(name = "APISecurityCondition", required = true)
        protected List<APISecurityConditionType> apiSecurityCondition;

        /**
         * Gets the value of the apiSecurityCondition property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the apiSecurityCondition property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAPISecurityCondition().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link APISecurityConditionType }
         * 
         * 
         */
        public List<APISecurityConditionType> getAPISecurityCondition() {
            if (apiSecurityCondition == null) {
                apiSecurityCondition = new ArrayList<APISecurityConditionType>();
            }
            return this.apiSecurityCondition;
        }

    }

}
