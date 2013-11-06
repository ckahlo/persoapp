
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
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
 * <p>Java class for EACSessionInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EACSessionInputType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;sequence>
 *         &lt;element name="RequiredAge" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="RequiredCommunity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VerifyDocumentValidity" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PerformRestrictedIdentification" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RequiredCHAT" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="OptionalCHAT" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="DataSetToBeRead" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "EACSessionInputType", propOrder = {
    "requiredAge",
    "requiredCommunity",
    "verifyDocumentValidity",
    "performRestrictedIdentification",
    "requiredCHAT",
    "optionalCHAT",
    "dataSetToBeRead",
    "transactionInfo"
})
public class EACSessionInputType
    extends DIDAuthenticationDataType
{

    @XmlElement(name = "RequiredAge")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger requiredAge;
    @XmlElement(name = "RequiredCommunity")
    protected String requiredCommunity;
    @XmlElement(name = "VerifyDocumentValidity")
    protected Boolean verifyDocumentValidity;
    @XmlElement(name = "PerformRestrictedIdentification")
    protected Boolean performRestrictedIdentification;
    @XmlElement(name = "RequiredCHAT", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] requiredCHAT;
    @XmlElement(name = "OptionalCHAT", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] optionalCHAT;
    @XmlElement(name = "DataSetToBeRead")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected List<String> dataSetToBeRead;
    @XmlElement(name = "TransactionInfo")
    protected String transactionInfo;

    /**
     * Gets the value of the requiredAge property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRequiredAge() {
        return requiredAge;
    }

    /**
     * Sets the value of the requiredAge property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRequiredAge(BigInteger value) {
        this.requiredAge = value;
    }

    /**
     * Gets the value of the requiredCommunity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiredCommunity() {
        return requiredCommunity;
    }

    /**
     * Sets the value of the requiredCommunity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredCommunity(String value) {
        this.requiredCommunity = value;
    }

    /**
     * Gets the value of the verifyDocumentValidity property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVerifyDocumentValidity() {
        return verifyDocumentValidity;
    }

    /**
     * Sets the value of the verifyDocumentValidity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVerifyDocumentValidity(Boolean value) {
        this.verifyDocumentValidity = value;
    }

    /**
     * Gets the value of the performRestrictedIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPerformRestrictedIdentification() {
        return performRestrictedIdentification;
    }

    /**
     * Sets the value of the performRestrictedIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPerformRestrictedIdentification(Boolean value) {
        this.performRestrictedIdentification = value;
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
     * Gets the value of the dataSetToBeRead property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSetToBeRead property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSetToBeRead().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDataSetToBeRead() {
        if (dataSetToBeRead == null) {
            dataSetToBeRead = new ArrayList<String>();
        }
        return this.dataSetToBeRead;
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
