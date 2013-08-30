
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for TLS_PSK_ParametersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TLS_PSK_ParametersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PSK" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element name="CipherSuite" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DIDName" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLS_PSK_ParametersType", propOrder = {
    "psk",
    "cipherSuite",
    "didName"
})
public class TLSPSKParametersType {

    @XmlElement(name = "PSK", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] psk;
    @XmlElement(name = "CipherSuite")
    protected List<String> cipherSuite;
    @XmlElement(name = "DIDName")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String didName;

    /**
     * Gets the value of the psk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getPSK() {
        return psk;
    }

    /**
     * Sets the value of the psk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPSK(byte[] value) {
        this.psk = ((byte[]) value);
    }

    /**
     * Gets the value of the cipherSuite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cipherSuite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCipherSuite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCipherSuite() {
        if (cipherSuite == null) {
            cipherSuite = new ArrayList<String>();
        }
        return this.cipherSuite;
    }

    /**
     * Gets the value of the didName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDIDName() {
        return didName;
    }

    /**
     * Sets the value of the didName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDIDName(String value) {
        this.didName = value;
    }

}
