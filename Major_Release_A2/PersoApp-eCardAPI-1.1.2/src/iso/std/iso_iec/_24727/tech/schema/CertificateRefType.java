
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CertificateRefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertificateRefType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}DataRefType">
 *       &lt;sequence>
 *         &lt;element name="CertificateType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateRefType", propOrder = {
    "certificateType"
})
public class CertificateRefType
    extends DataRefType
{

    @XmlElement(name = "CertificateType")
    @XmlSchemaType(name = "anyURI")
    protected String certificateType;

    /**
     * Gets the value of the certificateType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCertificateType() {
        return certificateType;
    }

    /**
     * Sets the value of the certificateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateType(String value) {
        this.certificateType = value;
    }

}
