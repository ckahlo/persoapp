
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChannelHandleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChannelHandleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProtocolTerminationPoint" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="SessionIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Binding" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="PathSecurity" type="{urn:iso:std:iso-iec:24727:tech:schema}PathSecurityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChannelHandleType", propOrder = {
    "protocolTerminationPoint",
    "sessionIdentifier",
    "binding",
    "pathSecurity"
})
public class ChannelHandleType {

    @XmlElement(name = "ProtocolTerminationPoint")
    @XmlSchemaType(name = "anyURI")
    protected String protocolTerminationPoint;
    @XmlElement(name = "SessionIdentifier")
    protected String sessionIdentifier;
    @XmlElement(name = "Binding", defaultValue = "http://schemas.xmlsoap.org/soap/http")
    @XmlSchemaType(name = "anyURI")
    protected String binding;
    @XmlElement(name = "PathSecurity")
    protected PathSecurityType pathSecurity;

    /**
     * Gets the value of the protocolTerminationPoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolTerminationPoint() {
        return protocolTerminationPoint;
    }

    /**
     * Sets the value of the protocolTerminationPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolTerminationPoint(String value) {
        this.protocolTerminationPoint = value;
    }

    /**
     * Gets the value of the sessionIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSessionIdentifier() {
        return sessionIdentifier;
    }

    /**
     * Sets the value of the sessionIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSessionIdentifier(String value) {
        this.sessionIdentifier = value;
    }

    /**
     * Gets the value of the binding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinding() {
        return binding;
    }

    /**
     * Sets the value of the binding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinding(String value) {
        this.binding = value;
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
