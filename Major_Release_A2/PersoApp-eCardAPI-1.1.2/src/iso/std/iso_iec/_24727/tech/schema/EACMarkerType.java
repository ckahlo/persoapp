
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EACMarkerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EACMarkerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType">
 *       &lt;sequence>
 *         &lt;element name="PACEDID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType" minOccurs="0"/>
 *         &lt;element name="CADID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/>
 *         &lt;element name="TADID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType"/>
 *         &lt;element name="RIDID" type="{urn:iso:std:iso-iec:24727:tech:schema}DIDNameType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EACMarkerType")
public class EACMarkerType
    extends DIDAbstractMarkerType
{


}
