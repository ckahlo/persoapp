
package org.w3._2001._04.xmldsig_more_;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CharTwoFieldParamsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CharTwoFieldParamsType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/2001/04/xmldsig-more#}FieldParamsType">
 *       &lt;sequence>
 *         &lt;element name="M" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CharTwoFieldParamsType", propOrder = {
    "m"
})
public abstract class CharTwoFieldParamsType
    extends FieldParamsType
{

    @XmlElement(name = "M", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger m;

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

}
