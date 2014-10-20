
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for PathType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PathType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *           &lt;element name="TagRef">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *                     &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="AppFileRef">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *                     &lt;element name="efIDOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *           &lt;element name="AppTagRef">
 *             &lt;complexType>
 *               &lt;complexContent>
 *                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                   &lt;sequence>
 *                     &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
 *                     &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/restriction>
 *               &lt;/complexContent>
 *             &lt;/complexType>
 *           &lt;/element>
 *         &lt;/choice>
 *         &lt;element name="Index" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="Length" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PathType", propOrder = {
    "efIdOrPath",
    "tagRef",
    "appFileRef",
    "appTagRef",
    "index",
    "length"
})
public class PathType {

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] efIdOrPath;
    @XmlElement(name = "TagRef")
    protected PathType.TagRef tagRef;
    @XmlElement(name = "AppFileRef")
    protected PathType.AppFileRef appFileRef;
    @XmlElement(name = "AppTagRef")
    protected PathType.AppTagRef appTagRef;
    @XmlElement(name = "Index", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] index;
    @XmlElement(name = "Length", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] length;

    /**
     * Gets the value of the efIdOrPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getEfIdOrPath() {
        return efIdOrPath;
    }

    /**
     * Sets the value of the efIdOrPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEfIdOrPath(byte[] value) {
        this.efIdOrPath = ((byte[]) value);
    }

    /**
     * Gets the value of the tagRef property.
     * 
     * @return
     *     possible object is
     *     {@link PathType.TagRef }
     *     
     */
    public PathType.TagRef getTagRef() {
        return tagRef;
    }

    /**
     * Sets the value of the tagRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType.TagRef }
     *     
     */
    public void setTagRef(PathType.TagRef value) {
        this.tagRef = value;
    }

    /**
     * Gets the value of the appFileRef property.
     * 
     * @return
     *     possible object is
     *     {@link PathType.AppFileRef }
     *     
     */
    public PathType.AppFileRef getAppFileRef() {
        return appFileRef;
    }

    /**
     * Sets the value of the appFileRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType.AppFileRef }
     *     
     */
    public void setAppFileRef(PathType.AppFileRef value) {
        this.appFileRef = value;
    }

    /**
     * Gets the value of the appTagRef property.
     * 
     * @return
     *     possible object is
     *     {@link PathType.AppTagRef }
     *     
     */
    public PathType.AppTagRef getAppTagRef() {
        return appTagRef;
    }

    /**
     * Sets the value of the appTagRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType.AppTagRef }
     *     
     */
    public void setAppTagRef(PathType.AppTagRef value) {
        this.appTagRef = value;
    }

    /**
     * Gets the value of the index property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndex(byte[] value) {
        this.index = ((byte[]) value);
    }

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLength(byte[] value) {
        this.length = ((byte[]) value);
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
     *         &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
     *         &lt;element name="efIDOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
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
        "aid",
        "efIDOrPath"
    })
    public static class AppFileRef {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] aid;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] efIDOrPath;

        /**
         * Gets the value of the aid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getAid() {
            return aid;
        }

        /**
         * Sets the value of the aid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAid(byte[] value) {
            this.aid = ((byte[]) value);
        }

        /**
         * Gets the value of the efIDOrPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getEfIDOrPath() {
            return efIDOrPath;
        }

        /**
         * Sets the value of the efIDOrPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEfIDOrPath(byte[] value) {
            this.efIDOrPath = ((byte[]) value);
        }

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
     *         &lt;element name="aid" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
     *         &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
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
        "aid",
        "tag",
        "efIdOrPath"
    })
    public static class AppTagRef {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] aid;
        @XmlElement(required = true)
        protected String tag;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] efIdOrPath;

        /**
         * Gets the value of the aid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getAid() {
            return aid;
        }

        /**
         * Sets the value of the aid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAid(byte[] value) {
            this.aid = ((byte[]) value);
        }

        /**
         * Gets the value of the tag property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTag() {
            return tag;
        }

        /**
         * Sets the value of the tag property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTag(String value) {
            this.tag = value;
        }

        /**
         * Gets the value of the efIdOrPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getEfIdOrPath() {
            return efIdOrPath;
        }

        /**
         * Sets the value of the efIdOrPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEfIdOrPath(byte[] value) {
            this.efIdOrPath = ((byte[]) value);
        }

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
     *         &lt;element name="tag" type="{http://www.w3.org/2001/XMLSchema}hexBinary"/>
     *         &lt;element name="efIdOrPath" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
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
        "tag",
        "efIdOrPath"
    })
    public static class TagRef {

        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] tag;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(HexBinaryAdapter.class)
        @XmlSchemaType(name = "hexBinary")
        protected byte[] efIdOrPath;

        /**
         * Gets the value of the tag property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getTag() {
            return tag;
        }

        /**
         * Sets the value of the tag property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTag(byte[] value) {
            this.tag = ((byte[]) value);
        }

        /**
         * Gets the value of the efIdOrPath property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public byte[] getEfIdOrPath() {
            return efIdOrPath;
        }

        /**
         * Sets the value of the efIdOrPath property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEfIdOrPath(byte[] value) {
            this.efIdOrPath = ((byte[]) value);
        }

    }

}
