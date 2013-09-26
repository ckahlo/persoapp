
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.dss._1_0.core.schema.VerificationTimeInfoType;


/**
 * <p>Java class for VerificationReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VerificationReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}VerificationTimeInfo" minOccurs="0"/>
 *         &lt;element name="VerifierIdentity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}IdentifierType" minOccurs="0"/>
 *         &lt;element name="IndividualReport" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}IndividualReportType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerificationReportType", propOrder = {
    "verificationTimeInfo",
    "verifierIdentity",
    "individualReport"
})
public class VerificationReportType {

    @XmlElement(name = "VerificationTimeInfo", namespace = "urn:oasis:names:tc:dss:1.0:core:schema")
    protected VerificationTimeInfoType verificationTimeInfo;
    @XmlElement(name = "VerifierIdentity")
    protected IdentifierType verifierIdentity;
    @XmlElement(name = "IndividualReport")
    protected List<IndividualReportType> individualReport;

    /**
     * Gets the value of the verificationTimeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link VerificationTimeInfoType }
     *     
     */
    public VerificationTimeInfoType getVerificationTimeInfo() {
        return verificationTimeInfo;
    }

    /**
     * Sets the value of the verificationTimeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link VerificationTimeInfoType }
     *     
     */
    public void setVerificationTimeInfo(VerificationTimeInfoType value) {
        this.verificationTimeInfo = value;
    }

    /**
     * Gets the value of the verifierIdentity property.
     * 
     * @return
     *     possible object is
     *     {@link IdentifierType }
     *     
     */
    public IdentifierType getVerifierIdentity() {
        return verifierIdentity;
    }

    /**
     * Sets the value of the verifierIdentity property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentifierType }
     *     
     */
    public void setVerifierIdentity(IdentifierType value) {
        this.verifierIdentity = value;
    }

    /**
     * Gets the value of the individualReport property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the individualReport property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndividualReport().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IndividualReportType }
     * 
     * 
     */
    public List<IndividualReportType> getIndividualReport() {
        if (individualReport == null) {
            individualReport = new ArrayList<IndividualReportType>();
        }
        return this.individualReport;
    }

}
