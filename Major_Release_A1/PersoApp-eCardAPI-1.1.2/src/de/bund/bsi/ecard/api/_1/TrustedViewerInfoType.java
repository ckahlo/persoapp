
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrustedViewerInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrustedViewerInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TrustedViewerId" type="{http://www.bsi.bund.de/ecard/api/1.1}TrustedViewerIdType" minOccurs="0"/>
 *         &lt;element name="StyleSheet" type="{http://www.bsi.bund.de/ecard/api/1.1}StyleSheetType" minOccurs="0"/>
 *         &lt;element name="IncludeViewerManifest" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrustedViewerInfoType", propOrder = {
    "trustedViewerId",
    "styleSheet",
    "includeViewerManifest"
})
public class TrustedViewerInfoType {

    @XmlElement(name = "TrustedViewerId")
    protected String trustedViewerId;
    @XmlElement(name = "StyleSheet")
    protected StyleSheetType styleSheet;
    @XmlElement(name = "IncludeViewerManifest")
    protected Boolean includeViewerManifest;

    /**
     * Gets the value of the trustedViewerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustedViewerId() {
        return trustedViewerId;
    }

    /**
     * Sets the value of the trustedViewerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustedViewerId(String value) {
        this.trustedViewerId = value;
    }

    /**
     * Gets the value of the styleSheet property.
     * 
     * @return
     *     possible object is
     *     {@link StyleSheetType }
     *     
     */
    public StyleSheetType getStyleSheet() {
        return styleSheet;
    }

    /**
     * Sets the value of the styleSheet property.
     * 
     * @param value
     *     allowed object is
     *     {@link StyleSheetType }
     *     
     */
    public void setStyleSheet(StyleSheetType value) {
        this.styleSheet = value;
    }

    /**
     * Gets the value of the includeViewerManifest property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeViewerManifest() {
        return includeViewerManifest;
    }

    /**
     * Sets the value of the includeViewerManifest property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeViewerManifest(Boolean value) {
        this.includeViewerManifest = value;
    }

}
