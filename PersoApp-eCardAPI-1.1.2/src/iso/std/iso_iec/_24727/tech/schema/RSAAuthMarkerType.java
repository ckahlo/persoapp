
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RSAAuthMarkerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RSAAuthMarkerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType">
 *       &lt;sequence>
 *         &lt;element name="EncryptionAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="SignatureAlgorithm" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="DerivationAlgorithmSessionKeysAndCounter" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="MacAlgorithmForSessionKey" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="EncryptionAlgorithmForSessionKey" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="CardAlgId" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteType" minOccurs="0"/>
 *         &lt;element name="KeySize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;sequence>
 *             &lt;element name="PrivateKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType" minOccurs="0"/>
 *             &lt;element name="PublicKeyValue" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyValueType"/>
 *           &lt;/sequence>
 *           &lt;element name="generateFlag" type="{urn:iso:std:iso-iec:24727:tech:schema}NULL"/>
 *         &lt;/choice>
 *         &lt;element name="NonceSize" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="KeyRef" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyRefType"/>
 *         &lt;element name="RootRef" type="{urn:iso:std:iso-iec:24727:tech:schema}KeyRefType" minOccurs="0"/>
 *         &lt;element name="SecurityEnvironmentIdentifier" type="{urn:iso:std:iso-iec:24727:tech:schema}ByteType" minOccurs="0"/>
 *         &lt;element name="CertificateRef" type="{urn:iso:std:iso-iec:24727:tech:schema}CertificateRefType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ICCSNRef" type="{urn:iso:std:iso-iec:24727:tech:schema}DataRefType" minOccurs="0"/>
 *         &lt;element name="LegacyKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RSAAuthMarkerType")
public class RSAAuthMarkerType
    extends DIDAbstractMarkerType
{


}
