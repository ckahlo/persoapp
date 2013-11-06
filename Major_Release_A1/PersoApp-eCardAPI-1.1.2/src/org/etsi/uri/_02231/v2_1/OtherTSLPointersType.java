
package org.etsi.uri._02231.v2_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OtherTSLPointersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OtherTSLPointersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://uri.etsi.org/02231/v2.1.1#}OtherTSLPointer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OtherTSLPointersType", propOrder = {
    "otherTSLPointer"
})
public class OtherTSLPointersType {

    @XmlElement(name = "OtherTSLPointer", required = true)
    protected OtherTSLPointerType otherTSLPointer;

    /**
     * Gets the value of the otherTSLPointer property.
     * 
     * @return
     *     possible object is
     *     {@link OtherTSLPointerType }
     *     
     */
    public OtherTSLPointerType getOtherTSLPointer() {
        return otherTSLPointer;
    }

    /**
     * Sets the value of the otherTSLPointer property.
     * 
     * @param value
     *     allowed object is
     *     {@link OtherTSLPointerType }
     *     
     */
    public void setOtherTSLPointer(OtherTSLPointerType value) {
        this.otherTSLPointer = value;
    }

}
