//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2014.10.10 um 05:42:08 PM CEST 
//


package de.bund.bsi.tr03124;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A routine is a parametrized sequence of actions. It is defined once and usually called multiple times throughout a test document.
 * 
 * <p>Java-Klasse f�r Routine complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Routine">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bsi.bund.de/TR03124}TestHierarchy">
 *       &lt;sequence>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Purpose" type="{http://bsi.bund.de/TR03124}Hypertext"/>
 *         &lt;element name="Reference" type="{http://bsi.bund.de/TR03124}LinkIDType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Parameter" type="{http://bsi.bund.de/TR03124}KeyValueType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="RoutineStep" type="{http://bsi.bund.de/TR03124}ActionStep" maxOccurs="unbounded"/>
 *         &lt;element name="MetaData" type="{http://bsi.bund.de/TR03124}KeyValueType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Routine", propOrder = {
    "title",
    "purpose",
    "reference",
    "parameter",
    "routineStep",
    "metaData"
})
public class Routine
    extends TestHierarchy
{

    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Purpose", required = true)
    protected Hypertext purpose;
    @XmlElement(name = "Reference")
    protected List<String> reference;
    @XmlElement(name = "Parameter")
    protected List<KeyValueType> parameter;
    @XmlElement(name = "RoutineStep", required = true)
    protected List<ActionStep> routineStep;
    @XmlElement(name = "MetaData")
    protected List<KeyValueType> metaData;

    /**
     * Ruft den Wert der title-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Legt den Wert der title-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Ruft den Wert der purpose-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Hypertext }
     *     
     */
    public Hypertext getPurpose() {
        return purpose;
    }

    /**
     * Legt den Wert der purpose-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Hypertext }
     *     
     */
    public void setPurpose(Hypertext value) {
        this.purpose = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getReference() {
        if (reference == null) {
            reference = new ArrayList<String>();
        }
        return this.reference;
    }

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValueType }
     * 
     * 
     */
    public List<KeyValueType> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<KeyValueType>();
        }
        return this.parameter;
    }

    /**
     * Gets the value of the routineStep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routineStep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoutineStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActionStep }
     * 
     * 
     */
    public List<ActionStep> getRoutineStep() {
        if (routineStep == null) {
            routineStep = new ArrayList<ActionStep>();
        }
        return this.routineStep;
    }

    /**
     * Gets the value of the metaData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metaData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetaData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link KeyValueType }
     * 
     * 
     */
    public List<KeyValueType> getMetaData() {
        if (metaData == null) {
            metaData = new ArrayList<KeyValueType>();
        }
        return this.metaData;
    }

}
