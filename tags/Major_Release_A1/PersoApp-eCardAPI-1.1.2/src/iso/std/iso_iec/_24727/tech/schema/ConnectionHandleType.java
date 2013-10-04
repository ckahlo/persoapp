
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ConnectionHandleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConnectionHandleType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}CardApplicationPathType">
 *       &lt;sequence>
 *         &lt;element name="SlotHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}SlotHandleType" minOccurs="0"/>
 *         &lt;element name="RecognitionInfo" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CardType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *                   &lt;element name="CardIdentifier" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *                   &lt;element name="CaptureTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConnectionHandleType", propOrder = {
    "slotHandle",
    "recognitionInfo"
})
public class ConnectionHandleType
    extends CardApplicationPathType
{

    @XmlElement(name = "SlotHandle", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] slotHandle;
    @XmlElement(name = "RecognitionInfo")
    protected ConnectionHandleType.RecognitionInfo recognitionInfo;

    /**
     * Gets the value of the slotHandle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSlotHandle() {
        return slotHandle;
    }

    /**
     * Sets the value of the slotHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlotHandle(byte[] value) {
        this.slotHandle = ((byte[]) value);
    }

    /**
     * Gets the value of the recognitionInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ConnectionHandleType.RecognitionInfo }
     *     
     */
    public ConnectionHandleType.RecognitionInfo getRecognitionInfo() {
        return recognitionInfo;
    }

    /**
     * Sets the value of the recognitionInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConnectionHandleType.RecognitionInfo }
     *     
     */
    public void setRecognitionInfo(ConnectionHandleType.RecognitionInfo value) {
        this.recognitionInfo = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="CardType" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
     *         &lt;element name="CardIdentifier" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
     *         &lt;element name="CaptureTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "cardType",
        "cardIdentifier",
        "captureTime"
    })
    public static class RecognitionInfo {

        @XmlElement(name = "CardType")
        @XmlSchemaType(name = "anyURI")
        protected String cardType;
        @XmlElement(name = "CardIdentifier", type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] cardIdentifier;
        @XmlElement(name = "CaptureTime")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar captureTime;

        /**
         * Gets the value of the cardType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCardType() {
            return cardType;
        }

        /**
         * Sets the value of the cardType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCardType(String value) {
            this.cardType = value;
        }

        /**
         * Gets the value of the cardIdentifier property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getCardIdentifier() {
            return cardIdentifier;
        }

        /**
         * Sets the value of the cardIdentifier property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCardIdentifier(byte[] value) {
            this.cardIdentifier = ((byte[]) value);
        }

        /**
         * Gets the value of the captureTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCaptureTime() {
            return captureTime;
        }

        /**
         * Sets the value of the captureTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCaptureTime(XMLGregorianCalendar value) {
            this.captureTime = value;
        }

    }

}
