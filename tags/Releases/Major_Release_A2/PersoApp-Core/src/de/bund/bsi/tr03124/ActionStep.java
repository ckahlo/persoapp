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
 * The action step represents one single step inside a sequence of tasks, e.g. within test cases.
 * 
 * <p>Java-Klasse f�r ActionStep complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="ActionStep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Command" type="{http://bsi.bund.de/TR03124}Command"/>
 *         &lt;element name="TechnicalCommand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TestDataReference" type="{http://bsi.bund.de/TR03124}LinkIDType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://bsi.bund.de/TR03124}Hypertext" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ExpectedResult" type="{http://bsi.bund.de/TR03124}Result" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionStep", propOrder = {
    "command",
    "technicalCommand",
    "testDataReference",
    "description",
    "expectedResult"
})
public class ActionStep {

    @XmlElement(name = "Command", required = true)
    protected Command command;
    @XmlElement(name = "TechnicalCommand")
    protected String technicalCommand;
    @XmlElement(name = "TestDataReference")
    protected List<String> testDataReference;
    @XmlElement(name = "Description")
    protected List<Hypertext> description;
    @XmlElement(name = "ExpectedResult", required = true)
    protected List<Result> expectedResult;

    /**
     * Ruft den Wert der command-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Command }
     *     
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Legt den Wert der command-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Command }
     *     
     */
    public void setCommand(Command value) {
        this.command = value;
    }

    /**
     * Ruft den Wert der technicalCommand-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnicalCommand() {
        return technicalCommand;
    }

    /**
     * Legt den Wert der technicalCommand-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnicalCommand(String value) {
        this.technicalCommand = value;
    }

    /**
     * Gets the value of the testDataReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testDataReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestDataReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTestDataReference() {
        if (testDataReference == null) {
            testDataReference = new ArrayList<String>();
        }
        return this.testDataReference;
    }

    /**
     * Gets the value of the description property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the description property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hypertext }
     * 
     * 
     */
    public List<Hypertext> getDescription() {
        if (description == null) {
            description = new ArrayList<Hypertext>();
        }
        return this.description;
    }

    /**
     * Gets the value of the expectedResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the expectedResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExpectedResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Result }
     * 
     * 
     */
    public List<Result> getExpectedResult() {
        if (expectedResult == null) {
            expectedResult = new ArrayList<Result>();
        }
        return this.expectedResult;
    }

}
