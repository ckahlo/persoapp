
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MutualAuthMarkerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MutualAuthMarkerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType">
 *       &lt;sequence>
 *         &lt;element name="EncryptionAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="MacAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="EncryptionAlgorithmForSessionKey" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="MacAlgorithmForSessionKey" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="DerivationAlgorithmSessionKeysAndCounter" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="CardAlgId" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteType" minOccurs="0"/>
 *         &lt;element name="K_enc" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="K_mac" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="DIV_IFD" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="KeyEncMacRef" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="SecurityEnvironmentIdentifier" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteType" minOccurs="0"/>
 *         &lt;element name="ICCSNRef" type="{urn:iso:std:iso-iec:24727:tech:schema}DataRefType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MutualAuthMarkerType")
public class MutualAuthMarkerType
    extends DIDAbstractMarkerType
{


}
