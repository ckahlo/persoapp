
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DomainParamsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DomainParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ExplicitParams" type="{http://www.w3.org/2001/04/xmldsig-more#}ExplicitParamsType"/>
 *         &lt;element name="NamedCurve">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="URN" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
@XmlType(name = "DomainParamsType", propOrder = {
    "explicitParams",
    "namedCurve"
})
public class DomainParamsType {

    @XmlElement(name = "ExplicitParams")
    protected ExplicitParamsType explicitParams;
    @XmlElement(name = "NamedCurve")
    protected DomainParamsType.NamedCurve namedCurve;

    /**
     * Gets the value of the explicitParams property.
     * 
     * @return
     *     possible object is
     *     {@link ExplicitParamsType }
     *     
     */
    public ExplicitParamsType getExplicitParams() {
        return explicitParams;
    }

    /**
     * Sets the value of the explicitParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExplicitParamsType }
     *     
     */
    public void setExplicitParams(ExplicitParamsType value) {
        this.explicitParams = value;
    }

    /**
     * Gets the value of the namedCurve property.
     * 
     * @return
     *     possible object is
     *     {@link DomainParamsType.NamedCurve }
     *     
     */
    public DomainParamsType.NamedCurve getNamedCurve() {
        return namedCurve;
    }

    /**
     * Sets the value of the namedCurve property.
     * 
     * @param value
     *     allowed object is
     *     {@link DomainParamsType.NamedCurve }
     *     
     */
    public void setNamedCurve(DomainParamsType.NamedCurve value) {
        this.namedCurve = value;
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
     *       &lt;attribute name="URN" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class NamedCurve {

        @XmlAttribute(name = "URN", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String urn;

        /**
         * Gets the value of the urn property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getURN() {
            return urn;
        }

        /**
         * Sets the value of the urn property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setURN(String value) {
            this.urn = value;
        }

    }

}
