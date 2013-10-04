
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConclusionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConclusionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="RecognizedState" type="{http://www.w3.org/2001/XMLSchema}IDREF"/>
 *         &lt;element name="RecognizedCardType" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element ref="{urn:iso:std:iso-iec:24727:tech:schema}CardCall" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConclusionType", propOrder = {
    "recognizedState",
    "recognizedCardType",
    "cardCall"
})
public class ConclusionType {

    @XmlElement(name = "RecognizedState")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object recognizedState;
    @XmlElement(name = "RecognizedCardType")
    @XmlSchemaType(name = "anyURI")
    protected String recognizedCardType;
    @XmlElement(name = "CardCall")
    protected List<CardCallType> cardCall;

    /**
     * Gets the value of the recognizedState property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getRecognizedState() {
        return recognizedState;
    }

    /**
     * Sets the value of the recognizedState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setRecognizedState(Object value) {
        this.recognizedState = value;
    }

    /**
     * Gets the value of the recognizedCardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecognizedCardType() {
        return recognizedCardType;
    }

    /**
     * Sets the value of the recognizedCardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecognizedCardType(String value) {
        this.recognizedCardType = value;
    }

    /**
     * Gets the value of the cardCall property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardCall property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardCall().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CardCallType }
     * 
     * 
     */
    public List<CardCallType> getCardCall() {
        if (cardCall == null) {
            cardCall = new ArrayList<CardCallType>();
        }
        return this.cardCall;
    }

}
