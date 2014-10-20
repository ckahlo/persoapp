
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PasswordAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PasswordAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pwdFlags" type="{urn:iso:std:iso-iec:24727:tech:schema}PasswordFlagsType"/>
 *         &lt;element name="pwdType" type="{urn:iso:std:iso-iec:24727:tech:schema}PasswordTypeType"/>
 *         &lt;element name="minLength" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="storedLength" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="maxLength" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="padChar" type="{urn:iso:std:iso-iec:24727:tech:schema}PadCharType" minOccurs="0"/>
 *         &lt;element name="lastPasswordChange" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PasswordAttributesType", propOrder = {
    "pwdFlags",
    "pwdType",
    "minLength",
    "storedLength",
    "maxLength",
    "padChar",
    "lastPasswordChange"
})
public class PasswordAttributesType {

    @XmlList
    @XmlElement(required = true)
    protected List<String> pwdFlags;
    @XmlElement(required = true)
    protected PasswordTypeType pwdType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minLength;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger storedLength;
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger maxLength;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] padChar;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastPasswordChange;

    /**
     * Gets the value of the pwdFlags property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pwdFlags property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPwdFlags().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPwdFlags() {
        if (pwdFlags == null) {
            pwdFlags = new ArrayList<String>();
        }
        return this.pwdFlags;
    }

    /**
     * Gets the value of the pwdType property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordTypeType }
     *     
     */
    public PasswordTypeType getPwdType() {
        return pwdType;
    }

    /**
     * Sets the value of the pwdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordTypeType }
     *     
     */
    public void setPwdType(PasswordTypeType value) {
        this.pwdType = value;
    }

    /**
     * Gets the value of the minLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinLength() {
        return minLength;
    }

    /**
     * Sets the value of the minLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinLength(BigInteger value) {
        this.minLength = value;
    }

    /**
     * Gets the value of the storedLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStoredLength() {
        return storedLength;
    }

    /**
     * Sets the value of the storedLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStoredLength(BigInteger value) {
        this.storedLength = value;
    }

    /**
     * Gets the value of the maxLength property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxLength() {
        return maxLength;
    }

    /**
     * Sets the value of the maxLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxLength(BigInteger value) {
        this.maxLength = value;
    }

    /**
     * Gets the value of the padChar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getPadChar() {
        return padChar;
    }

    /**
     * Sets the value of the padChar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPadChar(byte[] value) {
        this.padChar = ((byte[]) value);
    }

    /**
     * Gets the value of the lastPasswordChange property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastPasswordChange() {
        return lastPasswordChange;
    }

    /**
     * Sets the value of the lastPasswordChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastPasswordChange(XMLGregorianCalendar value) {
        this.lastPasswordChange = value;
    }

}
