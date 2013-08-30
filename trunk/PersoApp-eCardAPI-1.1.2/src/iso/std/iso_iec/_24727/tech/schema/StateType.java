
package iso.std.iso_iec._24727.tech.schema;

import java.math.BigInteger;
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


/**
 * <p>Java class for StateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RetryCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="UsageCounter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="StateTransition" type="{urn:iso:std:iso-iec:24727:tech:schema}StateTransitionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="StateName" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="StateClass" use="required" type="{urn:iso:std:iso-iec:24727:tech:schema}StateClassType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateType", propOrder = {
    "retryCounter",
    "usageCounter",
    "stateTransition"
})
public class StateType {

    @XmlElement(name = "RetryCounter")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger retryCounter;
    @XmlElement(name = "UsageCounter")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger usageCounter;
    @XmlElement(name = "StateTransition")
    protected List<StateTransitionType> stateTransition;
    @XmlAttribute(name = "StateName", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String stateName;
    @XmlAttribute(name = "StateClass", required = true)
    protected StateClassType stateClass;

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
     * Gets the value of the usageCounter property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getUsageCounter() {
        return usageCounter;
    }

    /**
     * Sets the value of the usageCounter property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setUsageCounter(BigInteger value) {
        this.usageCounter = value;
    }

    /**
     * Gets the value of the stateTransition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stateTransition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStateTransition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StateTransitionType }
     * 
     * 
     */
    public List<StateTransitionType> getStateTransition() {
        if (stateTransition == null) {
            stateTransition = new ArrayList<StateTransitionType>();
        }
        return this.stateTransition;
    }

    /**
     * Gets the value of the stateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Sets the value of the stateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateName(String value) {
        this.stateName = value;
    }

    /**
     * Gets the value of the stateClass property.
     * 
     * @return
     *     possible object is
     *     {@link StateClassType }
     *     
     */
    public StateClassType getStateClass() {
        return stateClass;
    }

    /**
     * Sets the value of the stateClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateClassType }
     *     
     */
    public void setStateClass(StateClassType value) {
        this.stateClass = value;
    }

}
