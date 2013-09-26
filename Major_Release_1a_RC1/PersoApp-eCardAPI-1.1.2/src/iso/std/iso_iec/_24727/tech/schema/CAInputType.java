
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CAInputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CAInputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;sequence>
 *         &lt;element name="PublicKey" type="{urn:iso:std:iso-iec:24727:tech:schema}SubjectPublicKeyInfoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAInputType")
public class CAInputType
    extends DIDAuthenticationDataType
{


}
