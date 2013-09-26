
package org.w3._2001._04.xmldsig_more_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for CurveParamsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CurveParamsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="A" type="{http://www.w3.org/2001/04/xmldsig-more#}FieldElemType"/>
 *         &lt;element name="B" type="{http://www.w3.org/2001/04/xmldsig-more#}FieldElemType"/>
 *         &lt;element name="Seed" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurveParamsType", propOrder = {
    "a",
    "b",
    "seed"
})
public class CurveParamsType {

    @XmlElement(name = "A", required = true)
    protected FieldElemType a;
    @XmlElement(name = "B", required = true)
    protected FieldElemType b;
    @XmlElement(name = "Seed", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] seed;

    /**
     * Gets the value of the a property.
     * 
     * @return
     *     possible object is
     *     {@link FieldElemType }
     *     
     */
    public FieldElemType getA() {
        return a;
    }

    /**
     * Sets the value of the a property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldElemType }
     *     
     */
    public void setA(FieldElemType value) {
        this.a = value;
    }

    /**
     * Gets the value of the b property.
     * 
     * @return
     *     possible object is
     *     {@link FieldElemType }
     *     
     */
    public FieldElemType getB() {
        return b;
    }

    /**
     * Sets the value of the b property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldElemType }
     *     
     */
    public void setB(FieldElemType value) {
        this.b = value;
    }

    /**
     * Gets the value of the seed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSeed() {
        return seed;
    }

    /**
     * Sets the value of the seed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeed(byte[] value) {
        this.seed = ((byte[]) value);
    }

}
