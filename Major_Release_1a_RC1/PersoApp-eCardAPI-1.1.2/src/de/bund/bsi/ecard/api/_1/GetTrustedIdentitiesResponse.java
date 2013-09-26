
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="TSL" type="{http://www.bsi.bund.de/ecard/api/1.1}TSLType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.bsi.bund.de/ecard/api/1.1}Certificate" maxOccurs="unbounded" minOccurs="0"/>
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
    "tsl",
    "certificate"
})
@XmlRootElement(name = "GetTrustedIdentitiesResponse")
public class GetTrustedIdentitiesResponse
    extends ResponseType
{

    @XmlElement(name = "TSL")
    protected List<TSLType> tsl;
    @XmlElement(name = "Certificate")
    protected List<Certificate> certificate;

    /**
     * Gets the value of the tsl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tsl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTSL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSLType }
     * 
     * 
     */
    public List<TSLType> getTSL() {
        if (tsl == null) {
            tsl = new ArrayList<TSLType>();
        }
        return this.tsl;
    }

    /**
     * Gets the value of the certificate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Certificate }
     * 
     * 
     */
    public List<Certificate> getCertificate() {
        if (certificate == null) {
            certificate = new ArrayList<Certificate>();
        }
        return this.certificate;
    }

}
