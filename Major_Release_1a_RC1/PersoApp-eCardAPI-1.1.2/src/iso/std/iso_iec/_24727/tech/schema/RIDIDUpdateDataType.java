
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RIDIDUpdateDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RIDIDUpdateDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDUpdateDataType">
 *       &lt;sequence>
 *         &lt;element name="KeyInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}CAKeyInfoType"/>
 *         &lt;element name="Marker" type="{urn:iso:std:iso-iec:24727:tech:schema}RIMarkerType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RIDIDUpdateDataType")
public class RIDIDUpdateDataType
    extends DIDUpdateDataType
{


}
