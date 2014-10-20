
package org.etsi.uri._02231.v2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AdditionalInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdditionalInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="TextualInformation" type="{http://uri.etsi.org/02231/v2.x#}MultiLangStringType"/>
 *         &lt;element name="OtherInformation" type="{http://uri.etsi.org/02231/v2.x#}AnyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdditionalInformationType", propOrder = {
    "textualInformationOrOtherInformation"
})
public class AdditionalInformationType {

    @XmlElements({
        @XmlElement(name = "OtherInformation", type = AnyType.class),
        @XmlElement(name = "TextualInformation", type = MultiLangStringType.class)
    })
    protected List<Object> textualInformationOrOtherInformation;

    /**
     * Gets the value of the textualInformationOrOtherInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textualInformationOrOtherInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextualInformationOrOtherInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnyType }
     * {@link MultiLangStringType }
     * 
     * 
     */
    public List<Object> getTextualInformationOrOtherInformation() {
        if (textualInformationOrOtherInformation == null) {
            textualInformationOrOtherInformation = new ArrayList<Object>();
        }
        return this.textualInformationOrOtherInformation;
    }

}
