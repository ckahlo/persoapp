
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="DefaultParameters" type="{http://www.bsi.bund.de/ecard/api/1.1}DefaultParametersType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "defaultParameters"
})
@XmlRootElement(name = "GetDefaultParametersResponse")
public class GetDefaultParametersResponse
    extends ResponseType
{

    @XmlElement(name = "DefaultParameters", required = true)
    protected DefaultParametersType defaultParameters;

    /**
     * Gets the value of the defaultParameters property.
     * 
     * @return
     *     possible object is
     *     {@link DefaultParametersType }
     *     
     */
    public DefaultParametersType getDefaultParameters() {
        return defaultParameters;
    }

    /**
     * Sets the value of the defaultParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultParametersType }
     *     
     */
    public void setDefaultParameters(DefaultParametersType value) {
        this.defaultParameters = value;
    }

}
