//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2014.10.10 um 05:42:08 PM CEST 
//


package de.bund.bsi.tr03124;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The APDUcommand is a special kind of command containing a specific ISO 7816 APDU to be send to the test object.
 * 
 * <p>Java-Klasse f�r APDUCommand complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="APDUCommand">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bsi.bund.de/TR03124}Command">
 *       &lt;sequence>
 *         &lt;element name="APDU" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "APDUCommand", propOrder = {
    "apdu"
})
public class APDUCommand
    extends Command
{

    @XmlElement(name = "APDU", required = true)
    protected String apdu;

    /**
     * Ruft den Wert der apdu-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPDU() {
        return apdu;
    }

    /**
     * Legt den Wert der apdu-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPDU(String value) {
        this.apdu = value;
    }

}
