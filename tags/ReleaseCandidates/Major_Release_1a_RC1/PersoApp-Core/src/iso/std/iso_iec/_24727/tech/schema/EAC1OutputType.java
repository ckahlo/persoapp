
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for EAC1OutputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EAC1OutputType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;sequence>
 *         &lt;element name="RetryCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="CertificateHolderAuthorizationTemplate" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="CertificationAuthorityReference" type="{http://www.w3.org/2001/XMLSchema}hexBinary" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="EFCardAccess" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="IDPICC" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="Challenge" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
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
@XmlType(name = "EAC1OutputType", propOrder = {
    "retryCounter",
    "certificateHolderAuthorizationTemplate",
    "certificationAuthorityReference",
    "efCardAccess",
    "idpicc",
    "challenge"
})
public class EAC1OutputType
    extends DIDAuthenticationDataType
{

    @XmlElement(name = "RetryCounter")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger retryCounter;
    @XmlElement(name = "CertificateHolderAuthorizationTemplate", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] certificateHolderAuthorizationTemplate;
    @XmlElement(name = "CertificationAuthorityReference", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected List<byte[]> certificationAuthorityReference;
    @XmlElement(name = "EFCardAccess", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] efCardAccess;
    @XmlElement(name = "IDPICC", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] idpicc;
    @XmlElement(name = "Challenge", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] challenge;

    /**
     * Gets the value of the retryCounter property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRetryCounter() {
        return retryCounter;
    }

    /**
     * Sets the value of the retryCounter property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRetryCounter(BigInteger value) {
        this.retryCounter = value;
    }

    /**
     * Gets the value of the certificateHolderAuthorizationTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCertificateHolderAuthorizationTemplate() {
        return certificateHolderAuthorizationTemplate;
    }

    /**
     * Sets the value of the certificateHolderAuthorizationTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCertificateHolderAuthorizationTemplate(byte[] value) {
        this.certificateHolderAuthorizationTemplate = ((byte[]) value);
    }

    /**
     * Gets the value of the certificationAuthorityReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificationAuthorityReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificationAuthorityReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<byte[]> getCertificationAuthorityReference() {
        if (certificationAuthorityReference == null) {
            certificationAuthorityReference = new ArrayList<byte[]>();
        }
        return this.certificationAuthorityReference;
    }

    /**
     * Gets the value of the efCardAccess property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getEFCardAccess() {
        return efCardAccess;
    }

    /**
     * Sets the value of the efCardAccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEFCardAccess(byte[] value) {
        this.efCardAccess = ((byte[]) value);
    }

    /**
     * Gets the value of the idpicc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getIDPICC() {
        return idpicc;
    }

    /**
     * Sets the value of the idpicc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPICC(byte[] value) {
        this.idpicc = ((byte[]) value);
    }

    /**
     * Gets the value of the challenge property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getChallenge() {
        return challenge;
    }

    /**
     * Sets the value of the challenge property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChallenge(byte[] value) {
        this.challenge = ((byte[]) value);
    }

}
