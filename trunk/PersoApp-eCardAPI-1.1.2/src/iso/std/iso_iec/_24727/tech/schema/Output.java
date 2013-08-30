
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ContextHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ContextHandleType"/>
 *         &lt;element name="IFDName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OutputInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}OutputInfoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "contextHandle",
    "ifdName",
    "outputInfo"
})
@XmlRootElement(name = "Output")
public class Output {

    @XmlElement(name = "ContextHandle", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] contextHandle;
    @XmlElement(name = "IFDName", required = true)
    protected String ifdName;
    @XmlElement(name = "OutputInfo", required = true)
    protected OutputInfoType outputInfo;

    /**
     * Gets the value of the contextHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getContextHandle() {
        return contextHandle;
    }

    /**
     * Sets the value of the contextHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContextHandle(byte[] value) {
        this.contextHandle = ((byte[]) value);
    }

    /**
     * Gets the value of the ifdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFDName() {
        return ifdName;
    }

    /**
     * Sets the value of the ifdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFDName(String value) {
        this.ifdName = value;
    }

    /**
     * Gets the value of the outputInfo property.
     * 
     * @return
     *     possible object is
     *     {@link OutputInfoType }
     *     
     */
    public OutputInfoType getOutputInfo() {
        return outputInfo;
    }

    /**
     * Sets the value of the outputInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputInfoType }
     *     
     */
    public void setOutputInfo(OutputInfoType value) {
        this.outputInfo = value;
    }

}
