
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DIDMarkerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DIDMarkerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="PinCompareMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}PinCompareMarkerType"/>
 *         &lt;element name="MutualAuthMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}MutualAuthMarkerType"/>
 *         &lt;element name="RSAAuthMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}RSAAuthMarkerType"/>
 *         &lt;element name="CryptoMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}CryptoMarkerType"/>
 *         &lt;element name="EACMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}EACMarkerType"/>
 *         &lt;element name="PACEMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}PACEMarkerType"/>
 *         &lt;element name="TAMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}TAMarkerType"/>
 *         &lt;element name="CAMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}CAMarkerType"/>
 *         &lt;element name="RIMarker" type="{urn:iso:std:iso-iec:24727:tech:schema}RIMarkerType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DIDMarkerType", propOrder = {
    "pinCompareMarker",
    "mutualAuthMarker",
    "rsaAuthMarker",
    "cryptoMarker",
    "eacMarker",
    "paceMarker",
    "taMarker",
    "caMarker",
    "riMarker"
})
public class DIDMarkerType {

    @XmlElement(name = "PinCompareMarker")
    protected PinCompareMarkerType pinCompareMarker;
    @XmlElement(name = "MutualAuthMarker")
    protected MutualAuthMarkerType mutualAuthMarker;
    @XmlElement(name = "RSAAuthMarker")
    protected RSAAuthMarkerType rsaAuthMarker;
    @XmlElement(name = "CryptoMarker")
    protected CryptoMarkerType cryptoMarker;
    @XmlElement(name = "EACMarker")
    protected EACMarkerType eacMarker;
    @XmlElement(name = "PACEMarker")
    protected PACEMarkerType paceMarker;
    @XmlElement(name = "TAMarker")
    protected TAMarkerType taMarker;
    @XmlElement(name = "CAMarker")
    protected CAMarkerType caMarker;
    @XmlElement(name = "RIMarker")
    protected RIMarkerType riMarker;

    /**
     * Gets the value of the pinCompareMarker property.
     * 
     * @return
     *     possible object is
     *     {@link PinCompareMarkerType }
     *     
     */
    public PinCompareMarkerType getPinCompareMarker() {
        return pinCompareMarker;
    }

    /**
     * Sets the value of the pinCompareMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link PinCompareMarkerType }
     *     
     */
    public void setPinCompareMarker(PinCompareMarkerType value) {
        this.pinCompareMarker = value;
    }

    /**
     * Gets the value of the mutualAuthMarker property.
     * 
     * @return
     *     possible object is
     *     {@link MutualAuthMarkerType }
     *     
     */
    public MutualAuthMarkerType getMutualAuthMarker() {
        return mutualAuthMarker;
    }

    /**
     * Sets the value of the mutualAuthMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link MutualAuthMarkerType }
     *     
     */
    public void setMutualAuthMarker(MutualAuthMarkerType value) {
        this.mutualAuthMarker = value;
    }

    /**
     * Gets the value of the rsaAuthMarker property.
     * 
     * @return
     *     possible object is
     *     {@link RSAAuthMarkerType }
     *     
     */
    public RSAAuthMarkerType getRSAAuthMarker() {
        return rsaAuthMarker;
    }

    /**
     * Sets the value of the rsaAuthMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link RSAAuthMarkerType }
     *     
     */
    public void setRSAAuthMarker(RSAAuthMarkerType value) {
        this.rsaAuthMarker = value;
    }

    /**
     * Gets the value of the cryptoMarker property.
     * 
     * @return
     *     possible object is
     *     {@link CryptoMarkerType }
     *     
     */
    public CryptoMarkerType getCryptoMarker() {
        return cryptoMarker;
    }

    /**
     * Sets the value of the cryptoMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link CryptoMarkerType }
     *     
     */
    public void setCryptoMarker(CryptoMarkerType value) {
        this.cryptoMarker = value;
    }

    /**
     * Gets the value of the eacMarker property.
     * 
     * @return
     *     possible object is
     *     {@link EACMarkerType }
     *     
     */
    public EACMarkerType getEACMarker() {
        return eacMarker;
    }

    /**
     * Sets the value of the eacMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link EACMarkerType }
     *     
     */
    public void setEACMarker(EACMarkerType value) {
        this.eacMarker = value;
    }

    /**
     * Gets the value of the paceMarker property.
     * 
     * @return
     *     possible object is
     *     {@link PACEMarkerType }
     *     
     */
    public PACEMarkerType getPACEMarker() {
        return paceMarker;
    }

    /**
     * Sets the value of the paceMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link PACEMarkerType }
     *     
     */
    public void setPACEMarker(PACEMarkerType value) {
        this.paceMarker = value;
    }

    /**
     * Gets the value of the taMarker property.
     * 
     * @return
     *     possible object is
     *     {@link TAMarkerType }
     *     
     */
    public TAMarkerType getTAMarker() {
        return taMarker;
    }

    /**
     * Sets the value of the taMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAMarkerType }
     *     
     */
    public void setTAMarker(TAMarkerType value) {
        this.taMarker = value;
    }

    /**
     * Gets the value of the caMarker property.
     * 
     * @return
     *     possible object is
     *     {@link CAMarkerType }
     *     
     */
    public CAMarkerType getCAMarker() {
        return caMarker;
    }

    /**
     * Sets the value of the caMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link CAMarkerType }
     *     
     */
    public void setCAMarker(CAMarkerType value) {
        this.caMarker = value;
    }

    /**
     * Gets the value of the riMarker property.
     * 
     * @return
     *     possible object is
     *     {@link RIMarkerType }
     *     
     */
    public RIMarkerType getRIMarker() {
        return riMarker;
    }

    /**
     * Sets the value of the riMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link RIMarkerType }
     *     
     */
    public void setRIMarker(RIMarkerType value) {
        this.riMarker = value;
    }

}
