
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StateInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StateInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StateRecognition" type="{urn:iso:std:iso-iec:24727:tech:schema}CardCallSequenceType" minOccurs="0"/>
 *         &lt;element name="State" type="{urn:iso:std:iso-iec:24727:tech:schema}StateType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StateInfoType", propOrder = {
    "stateRecognition",
    "state"
})
public class StateInfoType {

    @XmlElement(name = "StateRecognition")
    protected CardCallSequenceType stateRecognition;
    @XmlElement(name = "State", required = true)
    protected List<StateType> state;

    /**
     * Gets the value of the stateRecognition property.
     * 
     * @return
     *     possible object is
     *     {@link CardCallSequenceType }
     *     
     */
    public CardCallSequenceType getStateRecognition() {
        return stateRecognition;
    }

    /**
     * Sets the value of the stateRecognition property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardCallSequenceType }
     *     
     */
    public void setStateRecognition(CardCallSequenceType value) {
        this.stateRecognition = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the state property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StateType }
     * 
     * 
     */
    public List<StateType> getState() {
        if (state == null) {
            state = new ArrayList<StateType>();
        }
        return this.state;
    }

}
