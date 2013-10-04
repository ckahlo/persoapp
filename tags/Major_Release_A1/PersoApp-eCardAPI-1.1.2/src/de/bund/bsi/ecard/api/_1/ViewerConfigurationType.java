
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.InlineXMLType;


/**
 * <p>Java class for ViewerConfigurationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ViewerConfigurationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SupportedDocumentTypes" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="StyleSheet" type="{urn:oasis:names:tc:dss:1.0:core:schema}InlineXMLType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="IFDName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewerConfigurationType", propOrder = {
    "supportedDocumentTypes",
    "ifdName"
})
public class ViewerConfigurationType {

    @XmlElement(name = "SupportedDocumentTypes")
    protected List<ViewerConfigurationType.SupportedDocumentTypes> supportedDocumentTypes;
    @XmlElement(name = "IFDName")
    protected String ifdName;

    /**
     * Gets the value of the supportedDocumentTypes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supportedDocumentTypes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupportedDocumentTypes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ViewerConfigurationType.SupportedDocumentTypes }
     * 
     * 
     */
    public List<ViewerConfigurationType.SupportedDocumentTypes> getSupportedDocumentTypes() {
        if (supportedDocumentTypes == null) {
            supportedDocumentTypes = new ArrayList<ViewerConfigurationType.SupportedDocumentTypes>();
        }
        return this.supportedDocumentTypes;
    }

    /**
     * Gets the value of the ifdName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIFDName() {
        return ifdName;
    }

    /**
     * Sets the value of the ifdName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIFDName(String value) {
        this.ifdName = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Application" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="StyleSheet" type="{urn:oasis:names:tc:dss:1.0:core:schema}InlineXMLType" maxOccurs="unbounded" minOccurs="0"/>
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
        "mimeType",
        "application",
        "styleSheet"
    })
    public static class SupportedDocumentTypes {

        @XmlElement(name = "MimeType", required = true)
        protected String mimeType;
        @XmlElement(name = "Application")
        protected String application;
        @XmlElement(name = "StyleSheet")
        protected List<InlineXMLType> styleSheet;

        /**
         * Gets the value of the mimeType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMimeType() {
            return mimeType;
        }

        /**
         * Sets the value of the mimeType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMimeType(String value) {
            this.mimeType = value;
        }

        /**
         * Gets the value of the application property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplication() {
            return application;
        }

        /**
         * Sets the value of the application property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplication(String value) {
            this.application = value;
        }

        /**
         * Gets the value of the styleSheet property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the styleSheet property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStyleSheet().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link InlineXMLType }
         * 
         * 
         */
        public List<InlineXMLType> getStyleSheet() {
            if (styleSheet == null) {
                styleSheet = new ArrayList<InlineXMLType>();
            }
            return this.styleSheet;
        }

    }

}
