
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PinCompareMarkerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PinCompareMarkerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType">
 *       &lt;sequence>
 *         &lt;element name="PinRef" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyRefType"/>
 *         &lt;element name="PinValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PasswordAttributes" type="{urn:iso:std:iso-iec:24727:tech:schema}PasswordAttributesType" minOccurs="0"/>
 *         &lt;element ref="{urn:iso:std:iso-iec:24727:tech:schema}StateInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PinCompareMarkerType")
public class PinCompareMarkerType
    extends DIDAbstractMarkerType
{


}
