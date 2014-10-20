
package org.w3._2001._04.xmldsig_more_;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OddCharExtensionFieldParamsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OddCharExtensionFieldParamsType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2001/04/xmldsig-more#}FieldParamsType">
 *       &lt;sequence>
 *         &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="W" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OddCharExtensionFieldParamsType", propOrder = {
    "m",
    "w"
})
public class OddCharExtensionFieldParamsType
    extends FieldParamsType
{

    @XmlElement(name = "M", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger m;
    @XmlElement(name = "W", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger w;

    /**
     * Gets the value of the m property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getM() {
        return m;
    }

    /**
     * Sets the value of the m property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setM(BigInteger value) {
        this.m = value;
    }

    /**
     * Gets the value of the w property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getW() {
        return w;
    }

    /**
     * Sets the value of the w property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setW(BigInteger value) {
        this.w = value;
    }

}
