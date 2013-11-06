
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for SubjectPublicKeyInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubjectPublicKeyInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Algorithm" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmIdentifierType"/>
 *         &lt;element name="SubjectPublicKey" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubjectPublicKeyInfoType", propOrder = {
    "algorithm",
    "subjectPublicKey"
})
public class SubjectPublicKeyInfoType {

    @XmlElement(name = "Algorithm", required = true)
    protected AlgorithmIdentifierType algorithm;
    @XmlElement(name = "SubjectPublicKey", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] subjectPublicKey;

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public AlgorithmIdentifierType getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlgorithmIdentifierType }
     *     
     */
    public void setAlgorithm(AlgorithmIdentifierType value) {
        this.algorithm = value;
    }

    /**
     * Gets the value of the subjectPublicKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSubjectPublicKey() {
        return subjectPublicKey;
    }

    /**
     * Sets the value of the subjectPublicKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubjectPublicKey(byte[] value) {
        this.subjectPublicKey = ((byte[]) value);
    }

}
