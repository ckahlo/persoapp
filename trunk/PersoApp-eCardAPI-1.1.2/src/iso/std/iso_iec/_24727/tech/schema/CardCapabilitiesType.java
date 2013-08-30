
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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;


/**
 * <p>Java class for CardCapabilitiesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardCapabilitiesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Interface" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *                 &lt;sequence>
 *                   &lt;element name="Protocol" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="EF.DIR" type="{urn:iso:std:iso-iec:24727:tech:schema}FileRefReqType" minOccurs="0"/>
 *         &lt;element name="EF.ATRorINFO" type="{urn:iso:std:iso-iec:24727:tech:schema}EFATRorINFOType" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CardCapabilitiesType", propOrder = {
    "_interface",
    "efdir",
    "efatRorINFO",
    "other"
})
public class CardCapabilitiesType {

    @XmlElement(name = "Interface")
    protected List<CardCapabilitiesType.Interface> _interface;
    @XmlElement(name = "EF.DIR")
    protected FileRefReqType efdir;
    @XmlElement(name = "EF.ATRorINFO")
    protected EFATRorINFOType efatRorINFO;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the interface property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interface property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterface().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardCapabilitiesType.Interface }
     * 
     * 
     */
    public List<CardCapabilitiesType.Interface> getInterface() {
        if (_interface == null) {
            _interface = new ArrayList<CardCapabilitiesType.Interface>();
        }
        return this._interface;
    }

    /**
     * Gets the value of the efdir property.
     * 
     * @return
     *     possible object is
     *     {@link FileRefReqType }
     *     
     */
    public FileRefReqType getEFDIR() {
        return efdir;
    }

    /**
     * Sets the value of the efdir property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileRefReqType }
     *     
     */
    public void setEFDIR(FileRefReqType value) {
        this.efdir = value;
    }

    /**
     * Gets the value of the efatRorINFO property.
     * 
     * @return
     *     possible object is
     *     {@link EFATRorINFOType }
     *     
     */
    public EFATRorINFOType getEFATRorINFO() {
        return efatRorINFO;
    }

    /**
     * Sets the value of the efatRorINFO property.
     * 
     * @param value
     *     allowed object is
     *     {@link EFATRorINFOType }
     *     
     */
    public void setEFATRorINFO(EFATRorINFOType value) {
        this.efatRorINFO = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
     *       &lt;sequence>
     *         &lt;element name="Protocol" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "protocol"
    })
    public static class Interface
        extends RequirementsType
    {

        @XmlElement(name = "Protocol", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String protocol;

        /**
         * Gets the value of the protocol property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProtocol() {
            return protocol;
        }

        /**
         * Sets the value of the protocol property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProtocol(String value) {
            this.protocol = value;
        }

    }

}
