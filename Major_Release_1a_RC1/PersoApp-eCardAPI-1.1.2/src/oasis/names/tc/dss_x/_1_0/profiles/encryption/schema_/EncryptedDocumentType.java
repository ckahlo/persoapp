
package oasis.names.tc.dss_x._1_0.profiles.encryption.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;


/**
 * <p>Java class for EncryptedDocumentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncryptedDocumentType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:dss:1.0:core:schema}DocumentType">
 *       &lt;sequence>
 *         &lt;element name="EncryptedKeyParentSelector" type="{urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#}SelectorType" minOccurs="0"/>
 *         &lt;element name="EncryptedDataSelector" type="{urn:oasis:names:tc:dss-x:1.0:profiles:encryption:schema#}SelectorType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedDocumentType", propOrder = {
    "encryptedKeyParentSelector",
    "encryptedDataSelector"
})
public class EncryptedDocumentType
    extends DocumentType
{

    @XmlElement(name = "EncryptedKeyParentSelector")
    protected SelectorType encryptedKeyParentSelector;
    @XmlElement(name = "EncryptedDataSelector")
    protected List<SelectorType> encryptedDataSelector;

    /**
     * Gets the value of the encryptedKeyParentSelector property.
     * 
     * @return
     *     possible object is
     *     {@link SelectorType }
     *     
     */
    public SelectorType getEncryptedKeyParentSelector() {
        return encryptedKeyParentSelector;
    }

    /**
     * Sets the value of the encryptedKeyParentSelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectorType }
     *     
     */
    public void setEncryptedKeyParentSelector(SelectorType value) {
        this.encryptedKeyParentSelector = value;
    }

    /**
     * Gets the value of the encryptedDataSelector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the encryptedDataSelector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEncryptedDataSelector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SelectorType }
     * 
     * 
     */
    public List<SelectorType> getEncryptedDataSelector() {
        if (encryptedDataSelector == null) {
            encryptedDataSelector = new ArrayList<SelectorType>();
        }
        return this.encryptedDataSelector;
    }

}
