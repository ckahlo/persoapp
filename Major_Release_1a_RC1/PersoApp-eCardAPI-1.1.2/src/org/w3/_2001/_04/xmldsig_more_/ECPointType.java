
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ECPointType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECPointType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="X" type="{http://www.w3.org/2001/04/xmldsig-more#}FieldElemType"/>
 *         &lt;element name="Y" type="{http://www.w3.org/2001/04/xmldsig-more#}FieldElemType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECPointType", propOrder = {
    "x",
    "y"
})
public class ECPointType {

    @XmlElement(name = "X")
    protected FieldElemType x;
    @XmlElement(name = "Y")
    protected FieldElemType y;

    /**
     * Gets the value of the x property.
     * 
     * @return
     *     possible object is
     *     {@link FieldElemType }
     *     
     */
    public FieldElemType getX() {
        return x;
    }

    /**
     * Sets the value of the x property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldElemType }
     *     
     */
    public void setX(FieldElemType value) {
        this.x = value;
    }

    /**
     * Gets the value of the y property.
     * 
     * @return
     *     possible object is
     *     {@link FieldElemType }
     *     
     */
    public FieldElemType getY() {
        return y;
    }

    /**
     * Sets the value of the y property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldElemType }
     *     
     */
    public void setY(FieldElemType value) {
        this.y = value;
    }

}
