//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �nderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2014.10.10 um 05:42:08 PM CEST 
//


package de.bund.bsi.tr03124;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * The Certificate type defines elements specifying a specific CV certificate used in the test scenarios.
 * 
 * <p>Java-Klasse f�r Certificate complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="Certificate">
 *   &lt;complexContent>
 *     &lt;extension base="{http://bsi.bund.de/TR03124}LinkData">
 *       &lt;sequence>
 *         &lt;element name="Purpose" type="{http://bsi.bund.de/TR03124}Hypertext"/>
 *         &lt;element name="Version" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CertificateContent">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;choice>
 *                     &lt;sequence>
 *                       &lt;element name="SignerCertificate" type="{http://bsi.bund.de/TR03124}LinkIDType"/>
 *                       &lt;element name="CertificateAuthorityReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                       &lt;element name="SigningKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;/sequence>
 *                     &lt;sequence>
 *                       &lt;element name="CertificateAuthorityReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                       &lt;element name="SigningKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;/sequence>
 *                   &lt;/choice>
 *                   &lt;element name="CertificateHolderReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CertificateHolderAuthorization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CertificateEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CertificateExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="PublicKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CertificateExtension" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Certificate", propOrder = {
    "purpose",
    "version",
    "certificateContent"
})
public class Certificate
    extends LinkData
{

    @XmlElement(name = "Purpose", required = true)
    protected Hypertext purpose;
    @XmlElement(name = "Version", required = true)
    protected String version;
    @XmlElement(name = "CertificateContent", required = true)
    protected Certificate.CertificateContent certificateContent;

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
     * Ruft den Wert der certificateContent-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Certificate.CertificateContent }
     *     
     */
    public Certificate.CertificateContent getCertificateContent() {
        return certificateContent;
    }

    /**
     * Legt den Wert der certificateContent-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Certificate.CertificateContent }
     *     
     */
    public void setCertificateContent(Certificate.CertificateContent value) {
        this.certificateContent = value;
    }


    /**
     * <p>Java-Klasse f�r anonymous complex type.
     * 
     * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;choice>
     *           &lt;sequence>
     *             &lt;element name="SignerCertificate" type="{http://bsi.bund.de/TR03124}LinkIDType"/>
     *             &lt;element name="CertificateAuthorityReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *             &lt;element name="SigningKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *           &lt;/sequence>
     *           &lt;sequence>
     *             &lt;element name="CertificateAuthorityReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *             &lt;element name="SigningKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *           &lt;/sequence>
     *         &lt;/choice>
     *         &lt;element name="CertificateHolderReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CertificateHolderAuthorization" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CertificateEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CertificateExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="PublicKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CertificateExtension" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    public static class CertificateContent {

        @XmlElementRefs({
            @XmlElementRef(name = "CertificateHolderReference", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "CertificateEffectiveDate", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "CertificateHolderAuthorization", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "CertificateExpirationDate", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "SignerCertificate", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "CertificateExtension", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "SigningKey", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "CertificateAuthorityReference", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false),
            @XmlElementRef(name = "PublicKey", namespace = "http://bsi.bund.de/TR03124", type = JAXBElement.class, required = false)
        })
        protected List<JAXBElement<String>> content;

        /**
         * Ruft das restliche Contentmodell ab. 
         * 
         * <p>
         * Sie rufen diese "catch-all"-Eigenschaft aus folgendem Grund ab: 
         * Der Feldname "CertificateAuthorityReference" wird von zwei verschiedenen Teilen eines Schemas verwendet. Siehe: 
         * Zeile 46 von file:/C:/Users/rklimsa/workspaceEclipseBranch/PersoApp-Core/schemas/TR03124Schema.xsd
         * Zeile 39 von file:/C:/Users/rklimsa/workspaceEclipseBranch/PersoApp-Core/schemas/TR03124Schema.xsd
         * <p>
         * Um diese Eigenschaft zu entfernen, wenden Sie eine Eigenschaftenanpassung f�r eine
         * der beiden folgenden Deklarationen an, um deren Namen zu �ndern: 
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * {@link JAXBElement }{@code <}{@link String }{@code >}
         * 
         * 
         */
        public List<JAXBElement<String>> getContent() {
            if (content == null) {
                content = new ArrayList<JAXBElement<String>>();
            }
            return this.content;
        }

    }

}
