
package oasis.names.tc.dss._1_0.core.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="ResultMajor" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="ResultMinor" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="ResultMessage" type="{urn:oasis:names:tc:dss:1.0:core:schema}InternationalStringType" minOccurs="0"/>
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
    "resultMajor",
    "resultMinor",
    "resultMessage"
})
@XmlRootElement(name = "Result")
public class Result {

    @XmlElement(name = "ResultMajor", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String resultMajor;
    @XmlElement(name = "ResultMinor")
    @XmlSchemaType(name = "anyURI")
    protected String resultMinor;
    @XmlElement(name = "ResultMessage")
    protected InternationalStringType resultMessage;

    /**
     * Gets the value of the resultMajor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMajor() {
        return resultMajor;
    }

    /**
     * Sets the value of the resultMajor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMajor(String value) {
        this.resultMajor = value;
    }

    /**
     * Gets the value of the resultMinor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultMinor() {
        return resultMinor;
    }

    /**
     * Sets the value of the resultMinor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultMinor(String value) {
        this.resultMinor = value;
    }

    /**
     * Gets the value of the resultMessage property.
     * 
     * @return
     *     possible object is
     *     {@link InternationalStringType }
     *     
     */
    public InternationalStringType getResultMessage() {
        return resultMessage;
    }

    /**
     * Sets the value of the resultMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link InternationalStringType }
     *     
     */
    public void setResultMessage(InternationalStringType value) {
        this.resultMessage = value;
    }

}
