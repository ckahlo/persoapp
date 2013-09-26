
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CryptoMarkerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CryptoMarkerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAbstractMarkerType">
 *       &lt;sequence>
 *         &lt;element name="AlgorithmInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}AlgorithmInfoType"/>
 *         &lt;element name="KeyInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}CryptoKeyInfoType" minOccurs="0"/>
 *         &lt;element name="SignatureGenerationInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}SignatureGenerationType" minOccurs="0"/>
 *         &lt;element name="HashGenerationInfo" type="{urn:iso:std:iso-iec:24727:tech:schema}HashGenerationInfoType" minOccurs="0"/>
 *         &lt;element name="CertificateRef" type="{urn:iso:std:iso-iec:24727:tech:schema}CertificateRefType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LegacyKeyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "CryptoMarkerType")
public class CryptoMarkerType
    extends DIDAbstractMarkerType
{


}
