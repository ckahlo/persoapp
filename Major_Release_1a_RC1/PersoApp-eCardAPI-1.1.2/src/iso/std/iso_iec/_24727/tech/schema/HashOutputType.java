
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HashOutputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HashOutputType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;choice>
 *         &lt;element name="HashValue" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *         &lt;element name="HashHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}HashHandleType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HashOutputType")
public class HashOutputType
    extends DIDAuthenticationDataType
{


}
