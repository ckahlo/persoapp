
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="ViewerConfiguration" type="{http://www.bsi.bund.de/ecard/api/1.1}ViewerConfigurationType"/>
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
    "viewerConfiguration"
})
@XmlRootElement(name = "GetTrustedViewerConfigurationResponse")
public class GetTrustedViewerConfigurationResponse
    extends ResponseType
{

    @XmlElement(name = "ViewerConfiguration", required = true)
    protected ViewerConfigurationType viewerConfiguration;

    /**
     * Gets the value of the viewerConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link ViewerConfigurationType }
     *     
     */
    public ViewerConfigurationType getViewerConfiguration() {
        return viewerConfiguration;
    }

    /**
     * Sets the value of the viewerConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ViewerConfigurationType }
     *     
     */
    public void setViewerConfiguration(ViewerConfigurationType value) {
        this.viewerConfiguration = value;
    }

}
