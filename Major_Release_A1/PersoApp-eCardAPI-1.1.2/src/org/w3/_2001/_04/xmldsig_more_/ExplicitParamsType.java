
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExplicitParamsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExplicitParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FieldParams" type="{http://www.w3.org/2001/04/xmldsig-more#}FieldParamsType"/>
 *         &lt;element name="CurveParams" type="{http://www.w3.org/2001/04/xmldsig-more#}CurveParamsType"/>
 *         &lt;element name="BasePointParams" type="{http://www.w3.org/2001/04/xmldsig-more#}BasePointParamsType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExplicitParamsType", propOrder = {
    "fieldParams",
    "curveParams",
    "basePointParams"
})
public class ExplicitParamsType {

    @XmlElement(name = "FieldParams", required = true)
    protected FieldParamsType fieldParams;
    @XmlElement(name = "CurveParams", required = true)
    protected CurveParamsType curveParams;
    @XmlElement(name = "BasePointParams", required = true)
    protected BasePointParamsType basePointParams;

    /**
     * Gets the value of the fieldParams property.
     * 
     * @return
     *     possible object is
     *     {@link FieldParamsType }
     *     
     */
    public FieldParamsType getFieldParams() {
        return fieldParams;
    }

    /**
     * Sets the value of the fieldParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldParamsType }
     *     
     */
    public void setFieldParams(FieldParamsType value) {
        this.fieldParams = value;
    }

    /**
     * Gets the value of the curveParams property.
     * 
     * @return
     *     possible object is
     *     {@link CurveParamsType }
     *     
     */
    public CurveParamsType getCurveParams() {
        return curveParams;
    }

    /**
     * Sets the value of the curveParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurveParamsType }
     *     
     */
    public void setCurveParams(CurveParamsType value) {
        this.curveParams = value;
    }

    /**
     * Gets the value of the basePointParams property.
     * 
     * @return
     *     possible object is
     *     {@link BasePointParamsType }
     *     
     */
    public BasePointParamsType getBasePointParams() {
        return basePointParams;
    }

    /**
     * Sets the value of the basePointParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasePointParamsType }
     *     
     */
    public void setBasePointParams(BasePointParamsType value) {
        this.basePointParams = value;
    }

}
