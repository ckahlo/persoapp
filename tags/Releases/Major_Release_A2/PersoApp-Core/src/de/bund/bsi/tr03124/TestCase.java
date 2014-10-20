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
 * The test case the testing procedure for a single test scenario. There could be one or more test case within a single test unit
 * 
 * <p>Java-Klasse f�r TestCase complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TestCase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bsi.bund.de/TR03124}TestHierarchy">
 *       &lt;sequence>
 *         &lt;element name="Title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *             &lt;element name="Purpose" type="{http://bsi.bund.de/TR03124}Hypertext"/>
 *             &lt;element name="Profile" type="{http://bsi.bund.de/TR03124}LinkIDType" maxOccurs="unbounded"/>
 *             &lt;element name="Reference" type="{http://bsi.bund.de/TR03124}LinkIDType" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="Precondition" type="{http://bsi.bund.de/TR03124}Hypertext" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="TestStep" type="{http://bsi.bund.de/TR03124}ActionStep" maxOccurs="unbounded"/>
 *             &lt;element name="Postcondition" type="{http://bsi.bund.de/TR03124}Hypertext" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="MetaData" type="{http://bsi.bund.de/TR03124}KeyValueType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="Comment" type="{http://bsi.bund.de/TR03124}Hypertext"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestCase", propOrder = {
    "title",
    "version",
    "purpose",
    "profile",
    "reference",
    "precondition",
    "testStep",
    "postcondition",
    "metaData",
    "comment"
})
public class TestCase
    extends TestHierarchy
{

    @XmlElement(name = "Title", required = true)
    protected String title;
    @XmlElement(name = "Version")
    protected String version;
    @XmlElement(name = "Purpose")
    protected Hypertext purpose;
    @XmlElement(name = "Profile")
    protected List<String> profile;
    @XmlElement(name = "Reference")
    protected List<String> reference;
    @XmlElement(name = "Precondition")
    protected List<Hypertext> precondition;
    @XmlElement(name = "TestStep")
    protected List<ActionStep> testStep;
    @XmlElement(name = "Postcondition")
    protected List<Hypertext> postcondition;
    @XmlElement(name = "MetaData")
    protected List<KeyValueType> metaData;
    @XmlElement(name = "Comment")
    protected Hypertext comment;

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
     * Ruft den Wert der version-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Legt den Wert der version-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     * Gets the value of the profile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the profile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProfile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProfile() {
        if (profile == null) {
            profile = new ArrayList<String>();
        }
        return this.profile;
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
     * Gets the value of the precondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the precondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrecondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hypertext }
     * 
     * 
     */
    public List<Hypertext> getPrecondition() {
        if (precondition == null) {
            precondition = new ArrayList<Hypertext>();
        }
        return this.precondition;
    }

    /**
     * Gets the value of the testStep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testStep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ActionStep }
     * 
     * 
     */
    public List<ActionStep> getTestStep() {
        if (testStep == null) {
            testStep = new ArrayList<ActionStep>();
        }
        return this.testStep;
    }

    /**
     * Gets the value of the postcondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postcondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostcondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hypertext }
     * 
     * 
     */
    public List<Hypertext> getPostcondition() {
        if (postcondition == null) {
            postcondition = new ArrayList<Hypertext>();
        }
        return this.postcondition;
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

    /**
     * Ruft den Wert der comment-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Hypertext }
     *     
     */
    public Hypertext getComment() {
        return comment;
    }

    /**
     * Legt den Wert der comment-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Hypertext }
     *     
     */
    public void setComment(Hypertext value) {
        this.comment = value;
    }

}
