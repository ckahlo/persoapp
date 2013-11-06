
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


/**
 * <p>Java class for EAC1InputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EAC1InputType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;sequence>
 *         &lt;element name="Certificate" type="{http://www.w3.org/2001/XMLSchema}hexBinary" maxOccurs="unbounded"/>
 *         &lt;element name="CertificateDescription" type="{http://www.w3.org/2001/XMLSchema}hexBinary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ProviderInfo" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="RequiredCHAT" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="OptionalCHAT" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="AuthenticatedAuxiliaryData" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="TransactionInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='skip'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EAC1InputType", propOrder = {
    "certificate",
    "certificateDescription",
    "providerInfo",
    "requiredCHAT",
    "optionalCHAT",
    "authenticatedAuxiliaryData",
    "transactionInfo"
})
public class EAC1InputType
    extends DIDAuthenticationDataType
{

    @XmlElement(name = "Certificate", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected List<byte[]> certificate;
    @XmlElement(name = "CertificateDescription", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected List<byte[]> certificateDescription;
    @XmlElement(name = "ProviderInfo", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] providerInfo;
    @XmlElement(name = "RequiredCHAT", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] requiredCHAT;
    @XmlElement(name = "OptionalCHAT", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] optionalCHAT;
    @XmlElement(name = "AuthenticatedAuxiliaryData", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] authenticatedAuxiliaryData;
    @XmlElement(name = "TransactionInfo")
    protected String transactionInfo;

    /**
     * Gets the value of the certificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<byte[]> getCertificate() {
        if (certificate == null) {
            certificate = new ArrayList<byte[]>();
        }
        return this.certificate;
    }

    /**
     * Gets the value of the certificateDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificateDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificateDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<byte[]> getCertificateDescription() {
        if (certificateDescription == null) {
            certificateDescription = new ArrayList<byte[]>();
        }
        return this.certificateDescription;
    }

    /**
     * Gets the value of the providerInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getProviderInfo() {
        return providerInfo;
    }

    /**
     * Sets the value of the providerInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderInfo(byte[] value) {
        this.providerInfo = ((byte[]) value);
    }

    /**
     * Gets the value of the requiredCHAT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getRequiredCHAT() {
        return requiredCHAT;
    }

    /**
     * Sets the value of the requiredCHAT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredCHAT(byte[] value) {
        this.requiredCHAT = ((byte[]) value);
    }

    /**
     * Gets the value of the optionalCHAT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getOptionalCHAT() {
        return optionalCHAT;
    }

    /**
     * Sets the value of the optionalCHAT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOptionalCHAT(byte[] value) {
        this.optionalCHAT = ((byte[]) value);
    }

    /**
     * Gets the value of the authenticatedAuxiliaryData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getAuthenticatedAuxiliaryData() {
        return authenticatedAuxiliaryData;
    }

    /**
     * Sets the value of the authenticatedAuxiliaryData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthenticatedAuxiliaryData(byte[] value) {
        this.authenticatedAuxiliaryData = ((byte[]) value);
    }

    /**
     * Gets the value of the transactionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionInfo() {
        return transactionInfo;
    }

    /**
     * Sets the value of the transactionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionInfo(String value) {
        this.transactionInfo = value;
    }

}
