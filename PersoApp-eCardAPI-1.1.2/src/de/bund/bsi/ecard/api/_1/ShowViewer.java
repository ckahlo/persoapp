
package de.bund.bsi.ecard.api._1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ChannelHandleType;
import iso.std.iso_iec._24727.tech.schema.RequestType;
import oasis.names.tc.dss._1_0.core.schema.DocumentType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequestType">
 *       &lt;sequence>
 *         &lt;element name="ChannelHandle" type="{urn:iso:std:iso-iec:24727:tech:schema}ChannelHandleType" minOccurs="0"/>
 *         &lt;element name="TrustedViewerId" type="{http://www.bsi.bund.de/ecard/api/1.1}TrustedViewerIdType" minOccurs="0"/>
 *         &lt;element name="Document" type="{urn:oasis:names:tc:dss:1.0:core:schema}DocumentType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="StyleSheetContent" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="ViewerMessage" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="FrameMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="BodyMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Timeout" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "channelHandle",
    "trustedViewerId",
    "document",
    "styleSheetContent",
    "viewerMessage",
    "timeout"
})
@XmlRootElement(name = "ShowViewer")
public class ShowViewer
    extends RequestType
{

    @XmlElement(name = "ChannelHandle")
    protected ChannelHandleType channelHandle;
    @XmlElement(name = "TrustedViewerId")
    protected String trustedViewerId;
    @XmlElement(name = "Document")
    protected List<DocumentType> document;
    @XmlElement(name = "StyleSheetContent")
    protected byte[] styleSheetContent;
    @XmlElement(name = "ViewerMessage")
    protected ShowViewer.ViewerMessage viewerMessage;
    @XmlElement(name = "Timeout")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger timeout;

    /**
     * Gets the value of the channelHandle property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelHandleType }
     *     
     */
    public ChannelHandleType getChannelHandle() {
        return channelHandle;
    }

    /**
     * Sets the value of the channelHandle property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelHandleType }
     *     
     */
    public void setChannelHandle(ChannelHandleType value) {
        this.channelHandle = value;
    }

    /**
     * Gets the value of the trustedViewerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrustedViewerId() {
        return trustedViewerId;
    }

    /**
     * Sets the value of the trustedViewerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrustedViewerId(String value) {
        this.trustedViewerId = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the document property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocument().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentType }
     * 
     * 
     */
    public List<DocumentType> getDocument() {
        if (document == null) {
            document = new ArrayList<DocumentType>();
        }
        return this.document;
    }

    /**
     * Gets the value of the styleSheetContent property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getStyleSheetContent() {
        return styleSheetContent;
    }

    /**
     * Sets the value of the styleSheetContent property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setStyleSheetContent(byte[] value) {
        this.styleSheetContent = ((byte[]) value);
    }

    /**
     * Gets the value of the viewerMessage property.
     * 
     * @return
     *     possible object is
     *     {@link ShowViewer.ViewerMessage }
     *     
     */
    public ShowViewer.ViewerMessage getViewerMessage() {
        return viewerMessage;
    }

    /**
     * Sets the value of the viewerMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShowViewer.ViewerMessage }
     *     
     */
    public void setViewerMessage(ShowViewer.ViewerMessage value) {
        this.viewerMessage = value;
    }

    /**
     * Gets the value of the timeout property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTimeout() {
        return timeout;
    }

    /**
     * Sets the value of the timeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTimeout(BigInteger value) {
        this.timeout = value;
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
     *         &lt;element name="FrameMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="BodyMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "frameMessage",
        "bodyMessage"
    })
    public static class ViewerMessage {

        @XmlElement(name = "FrameMessage")
        protected String frameMessage;
        @XmlElement(name = "BodyMessage")
        protected String bodyMessage;

        /**
         * Gets the value of the frameMessage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFrameMessage() {
            return frameMessage;
        }

        /**
         * Sets the value of the frameMessage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFrameMessage(String value) {
            this.frameMessage = value;
        }

        /**
         * Gets the value of the bodyMessage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBodyMessage() {
            return bodyMessage;
        }

        /**
         * Sets the value of the bodyMessage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBodyMessage(String value) {
            this.bodyMessage = value;
        }

    }

}
