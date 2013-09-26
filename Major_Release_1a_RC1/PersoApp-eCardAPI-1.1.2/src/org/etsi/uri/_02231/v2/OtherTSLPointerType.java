
package org.etsi.uri._02231.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherTSLPointerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherTSLPointerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TSLLocation" type="{http://uri.etsi.org/02231/v2.x#}NonEmptyURIType"/>
 *         &lt;element ref="{http://uri.etsi.org/02231/v2.x#}AdditionalInformation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherTSLPointerType", propOrder = {
    "tslLocation",
    "additionalInformation"
})
public class OtherTSLPointerType {

    @XmlElement(name = "TSLLocation", required = true)
    protected String tslLocation;
    @XmlElement(name = "AdditionalInformation", required = true)
    protected AdditionalInformationType additionalInformation;

    /**
     * Gets the value of the tslLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTSLLocation() {
        return tslLocation;
    }

    /**
     * Sets the value of the tslLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTSLLocation(String value) {
        this.tslLocation = value;
    }

    /**
     * Gets the value of the additionalInformation property.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalInformationType }
     *     
     */
    public AdditionalInformationType getAdditionalInformation() {
        return additionalInformation;
    }

    /**
     * Sets the value of the additionalInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalInformationType }
     *     
     */
    public void setAdditionalInformation(AdditionalInformationType value) {
        this.additionalInformation = value;
    }

}
