
package org.setcce.schemas.ers;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for Attribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Attribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attrType" type="{http://www.setcce.org/schemas/ers}ObjectIdentifier"/>
 *         &lt;element name="attrValues">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="AttributeValue" type="{http://www.setcce.org/schemas/ers}AttributeValue"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Attribute", propOrder = {
    "attrType",
    "attrValues"
})
public class Attribute {

    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String attrType;
    @XmlElement(required = true)
    protected Attribute.AttrValues attrValues;

    /**
     * Gets the value of the attrType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * Sets the value of the attrType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttrType(String value) {
        this.attrType = value;
    }

    /**
     * Gets the value of the attrValues property.
     * 
     * @return
     *     possible object is
     *     {@link Attribute.AttrValues }
     *     
     */
    public Attribute.AttrValues getAttrValues() {
        return attrValues;
    }

    /**
     * Sets the value of the attrValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attribute.AttrValues }
     *     
     */
    public void setAttrValues(Attribute.AttrValues value) {
        this.attrValues = value;
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
     *       &lt;sequence maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="AttributeValue" type="{http://www.setcce.org/schemas/ers}AttributeValue"/>
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
        "attributeValue"
    })
    public static class AttrValues {

        @XmlElement(name = "AttributeValue")
        protected List<AttributeValue> attributeValue;

        /**
         * Gets the value of the attributeValue property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the attributeValue property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAttributeValue().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AttributeValue }
         * 
         * 
         */
        public List<AttributeValue> getAttributeValue() {
            if (attributeValue == null) {
                attributeValue = new ArrayList<AttributeValue>();
            }
            return this.attributeValue;
        }

    }

}
