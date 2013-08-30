
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

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
 *         &lt;element name="IncludeVerifier" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeCertificateValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeRevocationValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ExpandBinaryValues" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ReportDetailLevel" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
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
    "includeVerifier",
    "includeCertificateValues",
    "includeRevocationValues",
    "expandBinaryValues",
    "reportDetailLevel"
})
@XmlRootElement(name = "ReturnVerificationReport")
public class ReturnVerificationReport {

    @XmlElement(name = "IncludeVerifier", defaultValue = "true")
    protected Boolean includeVerifier;
    @XmlElement(name = "IncludeCertificateValues", defaultValue = "false")
    protected Boolean includeCertificateValues;
    @XmlElement(name = "IncludeRevocationValues", defaultValue = "false")
    protected Boolean includeRevocationValues;
    @XmlElement(name = "ExpandBinaryValues", defaultValue = "false")
    protected Boolean expandBinaryValues;
    @XmlElement(name = "ReportDetailLevel", defaultValue = "urn:oasis:names:tc:dss:1.0:profiles:verificationreport:reportdetail:allDetails")
    @XmlSchemaType(name = "anyURI")
    protected String reportDetailLevel;

    /**
     * Gets the value of the includeVerifier property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeVerifier() {
        return includeVerifier;
    }

    /**
     * Sets the value of the includeVerifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeVerifier(Boolean value) {
        this.includeVerifier = value;
    }

    /**
     * Gets the value of the includeCertificateValues property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeCertificateValues() {
        return includeCertificateValues;
    }

    /**
     * Sets the value of the includeCertificateValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeCertificateValues(Boolean value) {
        this.includeCertificateValues = value;
    }

    /**
     * Gets the value of the includeRevocationValues property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeRevocationValues() {
        return includeRevocationValues;
    }

    /**
     * Sets the value of the includeRevocationValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeRevocationValues(Boolean value) {
        this.includeRevocationValues = value;
    }

    /**
     * Gets the value of the expandBinaryValues property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExpandBinaryValues() {
        return expandBinaryValues;
    }

    /**
     * Sets the value of the expandBinaryValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExpandBinaryValues(Boolean value) {
        this.expandBinaryValues = value;
    }

    /**
     * Gets the value of the reportDetailLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportDetailLevel() {
        return reportDetailLevel;
    }

    /**
     * Sets the value of the reportDetailLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportDetailLevel(String value) {
        this.reportDetailLevel = value;
    }

}
