
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.PathSecurityType;
import oasis.names.tc.dss._1_0.core.schema.KeySelector;


/**
 * <p>Java class for TSServiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSServiceType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bsi.bund.de/ecard/api/1.1}ServiceType">
 *       &lt;sequence>
 *         &lt;element name="TimeStampType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}KeySelector" minOccurs="0"/>
 *         &lt;element name="PathSecurity" type="{urn:iso:std:iso-iec:24727:tech:schema}PathSecurityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSServiceType", propOrder = {
    "timeStampType",
    "keySelector",
    "pathSecurity"
})
public class TSServiceType
    extends ServiceType
{

    @XmlElement(name = "TimeStampType")
    @XmlSchemaType(name = "anyURI")
    protected String timeStampType;
    @XmlElement(name = "KeySelector", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected KeySelector keySelector;
    @XmlElement(name = "PathSecurity")
    protected PathSecurityType pathSecurity;

    /**
     * Gets the value of the timeStampType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStampType() {
        return timeStampType;
    }

    /**
     * Sets the value of the timeStampType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStampType(String value) {
        this.timeStampType = value;
    }

    /**
     * Gets the value of the keySelector property.
     * 
     * @return
     *     possible object is
     *     {@link KeySelector }
     *     
     */
    public KeySelector getKeySelector() {
        return keySelector;
    }

    /**
     * Sets the value of the keySelector property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeySelector }
     *     
     */
    public void setKeySelector(KeySelector value) {
        this.keySelector = value;
    }

    /**
     * Gets the value of the pathSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link PathSecurityType }
     *     
     */
    public PathSecurityType getPathSecurity() {
        return pathSecurity;
    }

    /**
     * Sets the value of the pathSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathSecurityType }
     *     
     */
    public void setPathSecurity(PathSecurityType value) {
        this.pathSecurity = value;
    }

}
