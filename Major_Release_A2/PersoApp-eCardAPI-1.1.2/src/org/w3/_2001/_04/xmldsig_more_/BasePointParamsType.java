
package org.w3._2001._04.xmldsig_more_;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BasePointParamsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasePointParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BasePoint" type="{http://www.w3.org/2001/04/xmldsig-more#}ECPointType"/>
 *         &lt;element name="Order" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Cofactor" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasePointParamsType", propOrder = {
    "basePoint",
    "order",
    "cofactor"
})
public class BasePointParamsType {

    @XmlElement(name = "BasePoint", required = true)
    protected ECPointType basePoint;
    @XmlElement(name = "Order", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger order;
    @XmlElement(name = "Cofactor")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger cofactor;

    /**
     * Gets the value of the basePoint property.
     * 
     * @return
     *     possible object is
     *     {@link ECPointType }
     *     
     */
    public ECPointType getBasePoint() {
        return basePoint;
    }

    /**
     * Sets the value of the basePoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link ECPointType }
     *     
     */
    public void setBasePoint(ECPointType value) {
        this.basePoint = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrder(BigInteger value) {
        this.order = value;
    }

    /**
     * Gets the value of the cofactor property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCofactor() {
        return cofactor;
    }

    /**
     * Sets the value of the cofactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCofactor(BigInteger value) {
        this.cofactor = value;
    }

}
