
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.InternationalStringType;


/**
 * <p>Java class for CardApplicationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardApplicationType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *       &lt;sequence>
 *         &lt;element name="InterfaceProtocol" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ApplicationIdentifier" type="{urn:iso:std:iso-iec:24727:tech:schema}ApplicationIdentifierType"/>
 *         &lt;element name="ApplicationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocalApplicationName" type="{urn:oasis:names:tc:dss:1.0:core:schema}InternationalStringType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CardApplicationServiceInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationServiceType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CardApplicationACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/>
 *         &lt;element name="DIDInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DataSetInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardApplicationType", propOrder = {
    "interfaceProtocol",
    "applicationIdentifier",
    "applicationName",
    "localApplicationName",
    "cardApplicationServiceInfo",
    "cardApplicationACL",
    "didInfo",
    "dataSetInfo",
    "other"
})
public class CardApplicationType
    extends RequirementsType
{

    @XmlElement(name = "InterfaceProtocol")
    @XmlSchemaType(name = "anyURI")
    protected List<String> interfaceProtocol;
    @XmlElement(name = "ApplicationIdentifier", required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] applicationIdentifier;
    @XmlElement(name = "ApplicationName")
    protected String applicationName;
    @XmlElement(name = "LocalApplicationName")
    protected List<InternationalStringType> localApplicationName;
    @XmlElement(name = "CardApplicationServiceInfo")
    protected List<CardApplicationServiceType> cardApplicationServiceInfo;
    @XmlElement(name = "CardApplicationACL", required = true)
    protected AccessControlListType cardApplicationACL;
    @XmlElement(name = "DIDInfo")
    protected List<DIDInfoType> didInfo;
    @XmlElement(name = "DataSetInfo")
    protected List<DataSetInfoType> dataSetInfo;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the interfaceProtocol property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interfaceProtocol property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterfaceProtocol().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getInterfaceProtocol() {
        if (interfaceProtocol == null) {
            interfaceProtocol = new ArrayList<String>();
        }
        return this.interfaceProtocol;
    }

    /**
     * Gets the value of the applicationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getApplicationIdentifier() {
        return applicationIdentifier;
    }

    /**
     * Sets the value of the applicationIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationIdentifier(byte[] value) {
        this.applicationIdentifier = ((byte[]) value);
    }

    /**
     * Gets the value of the applicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the value of the applicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationName(String value) {
        this.applicationName = value;
    }

    /**
     * Gets the value of the localApplicationName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localApplicationName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalApplicationName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InternationalStringType }
     * 
     * 
     */
    public List<InternationalStringType> getLocalApplicationName() {
        if (localApplicationName == null) {
            localApplicationName = new ArrayList<InternationalStringType>();
        }
        return this.localApplicationName;
    }

    /**
     * Gets the value of the cardApplicationServiceInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardApplicationServiceInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardApplicationServiceInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardApplicationServiceType }
     * 
     * 
     */
    public List<CardApplicationServiceType> getCardApplicationServiceInfo() {
        if (cardApplicationServiceInfo == null) {
            cardApplicationServiceInfo = new ArrayList<CardApplicationServiceType>();
        }
        return this.cardApplicationServiceInfo;
    }

    /**
     * Gets the value of the cardApplicationACL property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getCardApplicationACL() {
        return cardApplicationACL;
    }

    /**
     * Sets the value of the cardApplicationACL property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setCardApplicationACL(AccessControlListType value) {
        this.cardApplicationACL = value;
    }

    /**
     * Gets the value of the didInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the didInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDIDInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DIDInfoType }
     * 
     * 
     */
    public List<DIDInfoType> getDIDInfo() {
        if (didInfo == null) {
            didInfo = new ArrayList<DIDInfoType>();
        }
        return this.didInfo;
    }

    /**
     * Gets the value of the dataSetInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSetInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSetInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSetInfoType }
     * 
     * 
     */
    public List<DataSetInfoType> getDataSetInfo() {
        if (dataSetInfo == null) {
            dataSetInfo = new ArrayList<DataSetInfoType>();
        }
        return this.dataSetInfo;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setOther(AnyType value) {
        this.other = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
