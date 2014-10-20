
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ApplicationDataRefType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplicationDataRefType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}DataRefType">
 *       &lt;sequence>
 *         &lt;element name="Application" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationDataRefType", propOrder = {
    "application"
})
public class ApplicationDataRefType
    extends DataRefType
{

    @XmlElement(name = "Application", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] application;

    /**
     * Gets the value of the application property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getApplication() {
        return application;
    }

    /**
     * Sets the value of the application property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplication(byte[] value) {
        this.application = ((byte[]) value);
    }

}
