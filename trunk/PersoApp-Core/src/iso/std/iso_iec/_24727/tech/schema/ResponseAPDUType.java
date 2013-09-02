
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for ResponseAPDUType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseAPDUType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Body" type="{urn:iso:std:iso-iec:24727:tech:schema}DataMaskType" minOccurs="0"/>
 *         &lt;element name="Trailer" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element ref="{urn:iso:std:iso-iec:24727:tech:schema}Conclusion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseAPDUType", propOrder = {
    "body",
    "trailer",
    "conclusion"
})
public class ResponseAPDUType {

    @XmlElement(name = "Body")
    protected DataMaskType body;
    @XmlElement(name = "Trailer", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] trailer;
    @XmlElement(name = "Conclusion")
    protected ConclusionType conclusion;

    /**
     * Gets the value of the body property.
     * 
     * @return
     *     possible object is
     *     {@link DataMaskType }
     *     
     */
    public DataMaskType getBody() {
        return body;
    }

    /**
     * Sets the value of the body property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataMaskType }
     *     
     */
    public void setBody(DataMaskType value) {
        this.body = value;
    }

    /**
     * Gets the value of the trailer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getTrailer() {
        return trailer;
    }

    /**
     * Sets the value of the trailer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrailer(byte[] value) {
        this.trailer = ((byte[]) value);
    }

    /**
     * Gets the value of the conclusion property.
     * 
     * @return
     *     possible object is
     *     {@link ConclusionType }
     *     
     */
    public ConclusionType getConclusion() {
        return conclusion;
    }

    /**
     * Sets the value of the conclusion property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConclusionType }
     *     
     */
    public void setConclusion(ConclusionType value) {
        this.conclusion = value;
    }

}
