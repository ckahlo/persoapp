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
 * The test unit covers all test scenarios for a single test functionality There could be one or more test unit within a single specification
 * 
 * <p>Java-Klasse f�r TestUnit complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="TestUnit">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bsi.bund.de/TR03124}TestHierarchy">
 *       &lt;sequence>
 *         &lt;element name="TestCase" type="{http://bsi.bund.de/TR03124}TestHierarchyReference" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestUnit", propOrder = {
    "testCase"
})
public class TestUnit
    extends TestHierarchy
{

    @XmlElement(name = "TestCase", required = true)
    protected List<TestHierarchyReference> testCase;

    /**
     * Gets the value of the testCase property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testCase property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestCase().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TestHierarchyReference }
     * 
     * 
     */
    public List<TestHierarchyReference> getTestCase() {
        if (testCase == null) {
            testCase = new ArrayList<TestHierarchyReference>();
        }
        return this.testCase;
    }

}
