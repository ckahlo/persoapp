
package oasis.names.tc.dss_x._1_0.profiles.verificationreport.schema_;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CertifiedRolesListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CertifiedRolesListType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AttributeCertificateValidity" type="{urn:oasis:names:tc:dss-x:1.0:profiles:verificationreport:schema#}AttributeCertificateValidityType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertifiedRolesListType", propOrder = {
    "attributeCertificateValidity"
})
public class CertifiedRolesListType {

    @XmlElement(name = "AttributeCertificateValidity", required = true)
    protected List<AttributeCertificateValidityType> attributeCertificateValidity;

    /**
     * Gets the value of the attributeCertificateValidity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributeCertificateValidity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeCertificateValidity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeCertificateValidityType }
     * 
     * 
     */
    public List<AttributeCertificateValidityType> getAttributeCertificateValidity() {
        if (attributeCertificateValidity == null) {
            attributeCertificateValidity = new ArrayList<AttributeCertificateValidityType>();
        }
        return this.attributeCertificateValidity;
    }

}
