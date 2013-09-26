
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for CardCallType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardCallType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="CommandAPDU" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *           &lt;element name="ResponseAPDU" type="{urn:iso:std:iso-iec:24727:tech:schema}ResponseAPDUType" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="APICall" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType"/>
 *           &lt;element name="APIResponse" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardCallType", propOrder = {
    "commandAPDU",
    "responseAPDU",
    "apiCall",
    "apiResponse"
})
public class CardCallType {

    @XmlElement(name = "CommandAPDU", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] commandAPDU;
    @XmlElement(name = "ResponseAPDU")
    protected List<ResponseAPDUType> responseAPDU;
    @XmlElement(name = "APICall")
    protected AnyType apiCall;
    @XmlElement(name = "APIResponse")
    protected List<AnyType> apiResponse;

    /**
     * Gets the value of the commandAPDU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCommandAPDU() {
        return commandAPDU;
    }

    /**
     * Sets the value of the commandAPDU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommandAPDU(byte[] value) {
        this.commandAPDU = ((byte[]) value);
    }

    /**
     * Gets the value of the responseAPDU property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseAPDU property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseAPDU().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseAPDUType }
     * 
     * 
     */
    public List<ResponseAPDUType> getResponseAPDU() {
        if (responseAPDU == null) {
            responseAPDU = new ArrayList<ResponseAPDUType>();
        }
        return this.responseAPDU;
    }

    /**
     * Gets the value of the apiCall property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getAPICall() {
        return apiCall;
    }

    /**
     * Sets the value of the apiCall property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setAPICall(AnyType value) {
        this.apiCall = value;
    }

    /**
     * Gets the value of the apiResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apiResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAPIResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnyType }
     * 
     * 
     */
    public List<AnyType> getAPIResponse() {
        if (apiResponse == null) {
            apiResponse = new ArrayList<AnyType>();
        }
        return this.apiResponse;
    }

}
