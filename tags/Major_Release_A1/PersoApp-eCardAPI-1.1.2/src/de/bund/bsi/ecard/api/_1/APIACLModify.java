
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.RequestType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="APIAccessControlList" type="{http://www.bsi.bund.de/ecard/api/1.1}APIAccessControlListType"/>
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
    "apiAccessControlList"
})
@XmlRootElement(name = "APIACLModify")
public class APIACLModify
    extends RequestType
{

    @XmlElement(name = "APIAccessControlList", required = true)
    protected APIAccessControlListType apiAccessControlList;

    /**
     * Gets the value of the apiAccessControlList property.
     * 
     * @return
     *     possible object is
     *     {@link APIAccessControlListType }
     *     
     */
    public APIAccessControlListType getAPIAccessControlList() {
        return apiAccessControlList;
    }

    /**
     * Sets the value of the apiAccessControlList property.
     * 
     * @param value
     *     allowed object is
     *     {@link APIAccessControlListType }
     *     
     */
    public void setAPIAccessControlList(APIAccessControlListType value) {
        this.apiAccessControlList = value;
    }

}
