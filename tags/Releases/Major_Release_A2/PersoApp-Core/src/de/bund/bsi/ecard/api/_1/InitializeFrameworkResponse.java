
package de.bund.bsi.ecard.api._1;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import iso.std.iso_iec._24727.tech.schema.ResponseType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}ResponseType">
 *       &lt;sequence>
 *         &lt;element name="Version">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *                   &lt;element name="SubMinor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "version"
})
@XmlRootElement(name = "InitializeFrameworkResponse")
public class InitializeFrameworkResponse
    extends ResponseType
{

    @XmlElement(name = "Version", required = true)
    protected InitializeFrameworkResponse.Version version;

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link InitializeFrameworkResponse.Version }
     *     
     */
    public InitializeFrameworkResponse.Version getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link InitializeFrameworkResponse.Version }
     *     
     */
    public void setVersion(InitializeFrameworkResponse.Version value) {
        this.version = value;
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
     *         &lt;element name="Major" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="Minor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
     *         &lt;element name="SubMinor" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
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
        "major",
        "minor",
        "subMinor"
    })
    public static class Version {

        @XmlElement(name = "Major", required = true)
        protected BigInteger major;
        @XmlElement(name = "Minor")
        protected BigInteger minor;
        @XmlElement(name = "SubMinor")
        protected BigInteger subMinor;

        /**
         * Gets the value of the major property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMajor() {
            return major;
        }

        /**
         * Sets the value of the major property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMajor(BigInteger value) {
            this.major = value;
        }

        /**
         * Gets the value of the minor property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getMinor() {
            return minor;
        }

        /**
         * Sets the value of the minor property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setMinor(BigInteger value) {
            this.minor = value;
        }

        /**
         * Gets the value of the subMinor property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getSubMinor() {
            return subMinor;
        }

        /**
         * Sets the value of the subMinor property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setSubMinor(BigInteger value) {
            this.subMinor = value;
        }

    }

}
