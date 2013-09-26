
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StateTransitionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StateTransitionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="DIDAuthenticationState" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationStateType"/>
 *           &lt;element name="RetryCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *           &lt;element name="UsageCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *           &lt;element name="FixedProcedure" type="{urn:iso:std:iso-iec:24727:tech:schema}CardCallSequenceType"/>
 *         &lt;/choice>
 *         &lt;element name="UpdateCounter" type="{urn:iso:std:iso-iec:24727:tech:schema}UpdateCounterType" maxOccurs="2" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="TargetState" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateTransitionType", propOrder = {
    "didAuthenticationStateOrRetryCounterOrUsageCounter",
    "updateCounter"
})
public class StateTransitionType {

    @XmlElementRefs({
        @XmlElementRef(name = "UsageCounter", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class),
        @XmlElementRef(name = "DIDAuthenticationState", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class),
        @XmlElementRef(name = "RetryCounter", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class),
        @XmlElementRef(name = "FixedProcedure", namespace = "urn:iso:std:iso-iec:24727:tech:schema", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> didAuthenticationStateOrRetryCounterOrUsageCounter;
    @XmlElement(name = "UpdateCounter")
    protected List<UpdateCounterType> updateCounter;
    @XmlAttribute(name = "TargetState")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object targetState;

    /**
     * Gets the value of the didAuthenticationStateOrRetryCounterOrUsageCounter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the didAuthenticationStateOrRetryCounterOrUsageCounter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDIDAuthenticationStateOrRetryCounterOrUsageCounter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link CardCallSequenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link DIDAuthenticationStateType }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getDIDAuthenticationStateOrRetryCounterOrUsageCounter() {
        if (didAuthenticationStateOrRetryCounterOrUsageCounter == null) {
            didAuthenticationStateOrRetryCounterOrUsageCounter = new ArrayList<JAXBElement<?>>();
        }
        return this.didAuthenticationStateOrRetryCounterOrUsageCounter;
    }

    /**
     * Gets the value of the updateCounter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the updateCounter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUpdateCounter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdateCounterType }
     * 
     * 
     */
    public List<UpdateCounterType> getUpdateCounter() {
        if (updateCounter == null) {
            updateCounter = new ArrayList<UpdateCounterType>();
        }
        return this.updateCounter;
    }

    /**
     * Gets the value of the targetState property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getTargetState() {
        return targetState;
    }

    /**
     * Sets the value of the targetState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setTargetState(Object value) {
        this.targetState = value;
    }

}
