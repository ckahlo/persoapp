
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
 * <p>Java class for CardIdentificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CardIdentificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ATR" type="{urn:iso:std:iso-iec:24727:tech:schema}ATRType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ATS" type="{urn:iso:std:iso-iec:24727:tech:schema}ATSType" minOccurs="0"/>
 *         &lt;element name="CharacteristicFeature" type="{urn:iso:std:iso-iec:24727:tech:schema}CardCallSequenceType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "CardIdentificationType", propOrder = {
    "atr",
    "ats",
    "characteristicFeature",
    "other"
})
public class CardIdentificationType {

    @XmlElement(name = "ATR")
    protected List<ATRType> atr;
    @XmlElement(name = "ATS")
    protected ATSType ats;
    @XmlElement(name = "CharacteristicFeature")
    protected List<CardCallSequenceType> characteristicFeature;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Gets the value of the atr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the atr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getATR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ATRType }
     * 
     * 
     */
    public List<ATRType> getATR() {
        if (atr == null) {
            atr = new ArrayList<ATRType>();
        }
        return this.atr;
    }

    /**
     * Gets the value of the ats property.
     * 
     * @return
     *     possible object is
     *     {@link ATSType }
     *     
     */
    public ATSType getATS() {
        return ats;
    }

    /**
     * Sets the value of the ats property.
     * 
     * @param value
     *     allowed object is
     *     {@link ATSType }
     *     
     */
    public void setATS(ATSType value) {
        this.ats = value;
    }

    /**
     * Gets the value of the characteristicFeature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the characteristicFeature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCharacteristicFeature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardCallSequenceType }
     * 
     * 
     */
    public List<CardCallSequenceType> getCharacteristicFeature() {
        if (characteristicFeature == null) {
            characteristicFeature = new ArrayList<CardCallSequenceType>();
        }
        return this.characteristicFeature;
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
