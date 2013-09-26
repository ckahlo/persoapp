
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CardApplicationServiceDescriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardApplicationServiceDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ServiceDescriptionText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ServiceDescriptionURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardApplicationServiceDescriptionType", propOrder = {
    "serviceDescriptionText",
    "serviceDescriptionURL"
})
public class CardApplicationServiceDescriptionType {

    @XmlElement(name = "ServiceDescriptionText")
    protected String serviceDescriptionText;
    @XmlElement(name = "ServiceDescriptionURL")
    @XmlSchemaType(name = "anyURI")
    protected String serviceDescriptionURL;

    /**
     * Gets the value of the serviceDescriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDescriptionText() {
        return serviceDescriptionText;
    }

    /**
     * Sets the value of the serviceDescriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDescriptionText(String value) {
        this.serviceDescriptionText = value;
    }

    /**
     * Gets the value of the serviceDescriptionURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceDescriptionURL() {
        return serviceDescriptionURL;
    }

    /**
     * Sets the value of the serviceDescriptionURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceDescriptionURL(String value) {
        this.serviceDescriptionURL = value;
    }

}
