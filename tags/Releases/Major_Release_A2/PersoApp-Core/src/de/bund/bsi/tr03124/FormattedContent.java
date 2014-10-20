//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2014.10.10 um 05:42:08 PM CEST 
//


package de.bund.bsi.tr03124;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * This type describes formatted document content. Only formatting elements are allowed, no plain text content.
 * 
 * <p>Java-Klasse f�r FormattedContent complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="FormattedContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;group ref="{http://bsi.bund.de/TR03124}FormattedContentGroup"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormattedContent", propOrder = {
    "tableOrImageOrHeading"
})
public class FormattedContent {

    @XmlElements({
        @XmlElement(name = "Table", type = Table.class),
        @XmlElement(name = "Image", type = Image.class),
        @XmlElement(name = "Heading", type = Heading.class),
        @XmlElement(name = "Paragraph", type = Hypertext.class),
        @XmlElement(name = "Enumeration", type = Enumeration.class),
        @XmlElement(name = "List", type = de.bund.bsi.tr03124.List.class)
    })
    protected java.util.List<Object> tableOrImageOrHeading;

    /**
     * Gets the value of the tableOrImageOrHeading property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tableOrImageOrHeading property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTableOrImageOrHeading().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Table }
     * {@link Image }
     * {@link Heading }
     * {@link Hypertext }
     * {@link Enumeration }
     * {@link de.bund.bsi.tr03124.List }
     * 
     * 
     */
    public java.util.List<Object> getTableOrImageOrHeading() {
        if (tableOrImageOrHeading == null) {
            tableOrImageOrHeading = new ArrayList<Object>();
        }
        return this.tableOrImageOrHeading;
    }

}
