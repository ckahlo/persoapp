
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PACEDIDUpdateDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PACEDIDUpdateDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDUpdateDataType">
 *       &lt;sequence>
 *         &lt;element name="PasswordValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Marker" type="{urn:iso:std:iso-iec:24727:tech:schema}PACEMarkerType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PACEDIDUpdateDataType")
public class PACEDIDUpdateDataType
    extends DIDUpdateDataType
{


}
