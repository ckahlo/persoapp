
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.Result;


/**
 * <p>Java class for EACSessionOutputType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EACSessionOutputType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}DIDAuthenticationDataType">
 *       &lt;sequence>
 *         &lt;element name="AgeVerification" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="CommunityVerification" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="DocumentValidityVerification" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="SectorSpecificIdentifier" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *         &lt;element name="DataSet" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="DataSetName" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameType"/>
 *                   &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/>
 *                   &lt;element name="DSI" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="DSIName" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameType"/>
 *                             &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/>
 *                             &lt;element name="DSIContent" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='skip'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EACSessionOutputType", propOrder = {
    "ageVerification",
    "communityVerification",
    "documentValidityVerification",
    "sectorSpecificIdentifier",
    "dataSet"
})
public class EACSessionOutputType
    extends DIDAuthenticationDataType
{

    @XmlElement(name = "AgeVerification")
    @XmlSchemaType(name = "anyURI")
    protected String ageVerification;
    @XmlElement(name = "CommunityVerification")
    @XmlSchemaType(name = "anyURI")
    protected String communityVerification;
    @XmlElement(name = "DocumentValidityVerification")
    @XmlSchemaType(name = "anyURI")
    protected String documentValidityVerification;
    @XmlElement(name = "SectorSpecificIdentifier", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] sectorSpecificIdentifier;
    @XmlElement(name = "DataSet")
    protected List<EACSessionOutputType.DataSet> dataSet;

    /**
     * Gets the value of the ageVerification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgeVerification() {
        return ageVerification;
    }

    /**
     * Sets the value of the ageVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgeVerification(String value) {
        this.ageVerification = value;
    }

    /**
     * Gets the value of the communityVerification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunityVerification() {
        return communityVerification;
    }

    /**
     * Sets the value of the communityVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunityVerification(String value) {
        this.communityVerification = value;
    }

    /**
     * Gets the value of the documentValidityVerification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentValidityVerification() {
        return documentValidityVerification;
    }

    /**
     * Sets the value of the documentValidityVerification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentValidityVerification(String value) {
        this.documentValidityVerification = value;
    }

    /**
     * Gets the value of the sectorSpecificIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getSectorSpecificIdentifier() {
        return sectorSpecificIdentifier;
    }

    /**
     * Sets the value of the sectorSpecificIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSectorSpecificIdentifier(byte[] value) {
        this.sectorSpecificIdentifier = ((byte[]) value);
    }

    /**
     * Gets the value of the dataSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EACSessionOutputType.DataSet }
     * 
     * 
     */
    public List<EACSessionOutputType.DataSet> getDataSet() {
        if (dataSet == null) {
            dataSet = new ArrayList<EACSessionOutputType.DataSet>();
        }
        return this.dataSet;
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
     *         &lt;element name="DataSetName" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameType"/>
     *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/>
     *         &lt;element name="DSI" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="DSIName" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameType"/>
     *                   &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/>
     *                   &lt;element name="DSIContent" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "dataSetName",
        "result",
        "dsi"
    })
    public static class DataSet {

        @XmlElement(name = "DataSetName", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        protected String dataSetName;
        @XmlElement(name = "Result", namespace = "urn:oasis:names:tc:dss:1.0:core:schema", required = true)
        protected Result result;
        @XmlElement(name = "DSI")
        protected List<EACSessionOutputType.DataSet.DSI> dsi;

        /**
         * Gets the value of the dataSetName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDataSetName() {
            return dataSetName;
        }

        /**
         * Sets the value of the dataSetName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDataSetName(String value) {
            this.dataSetName = value;
        }

        /**
         * Gets the value of the result property.
         * 
         * @return
         *     possible object is
         *     {@link Result }
         *     
         */
        public Result getResult() {
            return result;
        }

        /**
         * Sets the value of the result property.
         * 
         * @param value
         *     allowed object is
         *     {@link Result }
         *     
         */
        public void setResult(Result value) {
            this.result = value;
        }

        /**
         * Gets the value of the dsi property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dsi property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDSI().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EACSessionOutputType.DataSet.DSI }
         * 
         * 
         */
        public List<EACSessionOutputType.DataSet.DSI> getDSI() {
            if (dsi == null) {
                dsi = new ArrayList<EACSessionOutputType.DataSet.DSI>();
            }
            return this.dsi;
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
         *         &lt;element name="DSIName" type="{urn:iso:std:iso-iec:24727:tech:schema}DSINameType"/>
         *         &lt;element ref="{urn:oasis:names:tc:dss:1.0:core:schema}Result"/>
         *         &lt;element name="DSIContent" type="{http://www.w3.org/2001/XMLSchema}hexBinary" minOccurs="0"/>
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
            "dsiName",
            "result",
            "dsiContent"
        })
        public static class DSI {

            @XmlElement(name = "DSIName", required = true)
            @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
            protected String dsiName;
            @XmlElement(name = "Result", namespace = "urn:oasis:names:tc:dss:1.0:core:schema", required = true)
            protected Result result;
            @XmlElement(name = "DSIContent", type = String.class)
            @XmlJavaTypeAdapter(HexBinaryAdapter.class)
            @XmlSchemaType(name = "hexBinary")
            protected byte[] dsiContent;

            /**
             * Gets the value of the dsiName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDSIName() {
                return dsiName;
            }

            /**
             * Sets the value of the dsiName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDSIName(String value) {
                this.dsiName = value;
            }

            /**
             * Gets the value of the result property.
             * 
             * @return
             *     possible object is
             *     {@link Result }
             *     
             */
            public Result getResult() {
                return result;
            }

            /**
             * Sets the value of the result property.
             * 
             * @param value
             *     allowed object is
             *     {@link Result }
             *     
             */
            public void setResult(Result value) {
                this.result = value;
            }

            /**
             * Gets the value of the dsiContent property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public byte[] getDSIContent() {
                return dsiContent;
            }

            /**
             * Sets the value of the dsiContent property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDSIContent(byte[] value) {
                this.dsiContent = ((byte[]) value);
            }

        }

    }

}
