//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2014.10.10 um 05:41:30 PM CEST 
//


package de.bund.bsi.tr03124;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse f�r anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://bsi.bund.de/TR03124}Document">
 *       &lt;sequence>
 *         &lt;element name="References" type="{http://bsi.bund.de/TR03124}TestHierarchyReference"/>
 *         &lt;element name="TestProfiles" type="{http://bsi.bund.de/TR03124}TestHierarchyReference"/>
 *         &lt;element name="CertificateDefinition" type="{http://bsi.bund.de/TR03124}TestHierarchyReference"/>
 *         &lt;element name="Module" type="{http://bsi.bund.de/TR03124}TestHierarchyReference" maxOccurs="unbounded"/>
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
    "references",
    "testProfiles",
    "certificateDefinition",
    "module"
})
@XmlRootElement(name = "TR_03124_PART_2")
public class TR03124PART2
    extends Document
{

    @XmlElement(name = "References", required = true)
    protected TestHierarchyReference references;
    @XmlElement(name = "TestProfiles", required = true)
    protected TestHierarchyReference testProfiles;
    @XmlElement(name = "CertificateDefinition", required = true)
    protected TestHierarchyReference certificateDefinition;
    @XmlElement(name = "Module", required = true)
    protected List<TestHierarchyReference> module;

    /**
     * Ruft den Wert der references-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TestHierarchyReference }
     *     
     */
    public TestHierarchyReference getReferences() {
        return references;
    }

    /**
     * Legt den Wert der references-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TestHierarchyReference }
     *     
     */
    public void setReferences(TestHierarchyReference value) {
        this.references = value;
    }

    /**
     * Ruft den Wert der testProfiles-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TestHierarchyReference }
     *     
     */
    public TestHierarchyReference getTestProfiles() {
        return testProfiles;
    }

    /**
     * Legt den Wert der testProfiles-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TestHierarchyReference }
     *     
     */
    public void setTestProfiles(TestHierarchyReference value) {
        this.testProfiles = value;
    }

    /**
     * Ruft den Wert der certificateDefinition-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link TestHierarchyReference }
     *     
     */
    public TestHierarchyReference getCertificateDefinition() {
        return certificateDefinition;
    }

    /**
     * Legt den Wert der certificateDefinition-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link TestHierarchyReference }
     *     
     */
    public void setCertificateDefinition(TestHierarchyReference value) {
        this.certificateDefinition = value;
    }

    /**
     * Gets the value of the module property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the module property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestHierarchyReference }
     * 
     * 
     */
    public List<TestHierarchyReference> getModule() {
        if (module == null) {
            module = new ArrayList<TestHierarchyReference>();
        }
        return this.module;
    }

}
