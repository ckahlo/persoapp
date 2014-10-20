
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EvidenceRecordType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EvidenceRecordType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="xmlEvidenceRecord" type="{http://www.setcce.org/schemas/ers}EvidenceRecordType"/>
 *         &lt;element name="asn1EvidenceRecord" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvidenceRecordType", propOrder = {
    "xmlEvidenceRecord",
    "asn1EvidenceRecord"
})
public class EvidenceRecordType {

    protected org.setcce.schemas.ers.EvidenceRecordType xmlEvidenceRecord;
    protected byte[] asn1EvidenceRecord;

    /**
     * Gets the value of the xmlEvidenceRecord property.
     * 
     * @return
     *     possible object is
     *     {@link org.setcce.schemas.ers.EvidenceRecordType }
     *     
     */
    public org.setcce.schemas.ers.EvidenceRecordType getXmlEvidenceRecord() {
        return xmlEvidenceRecord;
    }

    /**
     * Sets the value of the xmlEvidenceRecord property.
     * 
     * @param value
     *     allowed object is
     *     {@link org.setcce.schemas.ers.EvidenceRecordType }
     *     
     */
    public void setXmlEvidenceRecord(org.setcce.schemas.ers.EvidenceRecordType value) {
        this.xmlEvidenceRecord = value;
    }

    /**
     * Gets the value of the asn1EvidenceRecord property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getAsn1EvidenceRecord() {
        return asn1EvidenceRecord;
    }

    /**
     * Sets the value of the asn1EvidenceRecord property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setAsn1EvidenceRecord(byte[] value) {
        this.asn1EvidenceRecord = ((byte[]) value);
    }

}
