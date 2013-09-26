
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddCertificateOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddCertificateOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CheckCertificatePath" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CheckCertificateStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddCertificateOptionsType", propOrder = {
    "checkCertificatePath",
    "checkCertificateStatus"
})
public class AddCertificateOptionsType {

    @XmlElement(name = "CheckCertificatePath")
    protected Boolean checkCertificatePath;
    @XmlElement(name = "CheckCertificateStatus")
    protected Boolean checkCertificateStatus;

    /**
     * Gets the value of the checkCertificatePath property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCheckCertificatePath() {
        return checkCertificatePath;
    }

    /**
     * Sets the value of the checkCertificatePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCheckCertificatePath(Boolean value) {
        this.checkCertificatePath = value;
    }

    /**
     * Gets the value of the checkCertificateStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCheckCertificateStatus() {
        return checkCertificateStatus;
    }

    /**
     * Sets the value of the checkCertificateStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCheckCertificateStatus(Boolean value) {
        this.checkCertificateStatus = value;
    }

}
