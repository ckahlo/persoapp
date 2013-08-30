
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.TstInfo;


/**
 * <p>Java class for TstContentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TstContentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}TstInfo" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TstContentType", propOrder = {
    "tstInfo",
    "other"
})
public class TstContentType {

    @XmlElement(name = "TstInfo", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected TstInfo tstInfo;
    @XmlElement(name = "Other")
    protected AnyType other;

    /**
     * Gets the value of the tstInfo property.
     * 
     * @return
     *     possible object is
     *     {@link TstInfo }
     *     
     */
    public TstInfo getTstInfo() {
        return tstInfo;
    }

    /**
     * Sets the value of the tstInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TstInfo }
     *     
     */
    public void setTstInfo(TstInfo value) {
        this.tstInfo = value;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setOther(AnyType value) {
        this.other = value;
    }

}
