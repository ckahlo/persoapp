
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DSIType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DSIType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *       &lt;sequence>
 *         &lt;element name="DSIName" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameType"/>
 *         &lt;element name="DSIPath" type="{urn:iso:std:iso-iec:24727:tech:schema}PathType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DSIType", propOrder = {
    "dsiName",
    "dsiPath"
})
public class DSIType
    extends RequirementsType
{

    @XmlElement(name = "DSIName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dsiName;
    @XmlElement(name = "DSIPath", required = true)
    protected PathType dsiPath;

    /**
     * Gets the value of the dsiName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDSIName() {
        return dsiName;
    }

    /**
     * Sets the value of the dsiName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDSIName(String value) {
        this.dsiName = value;
    }

    /**
     * Gets the value of the dsiPath property.
     * 
     * @return
     *     possible object is
     *     {@link PathType }
     *     
     */
    public PathType getDSIPath() {
        return dsiPath;
    }

    /**
     * Sets the value of the dsiPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType }
     *     
     */
    public void setDSIPath(PathType value) {
        this.dsiPath = value;
    }

}
