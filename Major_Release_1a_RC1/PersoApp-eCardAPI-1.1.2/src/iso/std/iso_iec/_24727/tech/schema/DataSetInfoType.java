
package iso.std.iso_iec._24727.tech.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import oasis.names.tc.dss._1_0.core.schema.AnyType;
import oasis.names.tc.dss._1_0.core.schema.InternationalStringType;


/**
 * <p>Java class for DataSetInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataSetInfoType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:std:iso-iec:24727:tech:schema}RequirementsType">
 *       &lt;sequence>
 *         &lt;element name="DataSetName" type="{urn:iso:std:iso-iec:24727:tech:schema}DataSetNameType"/>
 *         &lt;element name="LocalDataSetName" type="{urn:oasis:names:tc:dss:1.0:core:schema}InternationalStringType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DataSetACL" type="{urn:iso:std:iso-iec:24727:tech:schema}AccessControlListType"/>
 *         &lt;element name="DataSetPath" type="{urn:iso:std:iso-iec:24727:tech:schema}PathType"/>
 *         &lt;element name="DSI" type="{urn:iso:std:iso-iec:24727:tech:schema}DSIType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Other" type="{urn:oasis:names:tc:dss:1.0:core:schema}AnyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSetInfoType", propOrder = {
    "dataSetName",
    "localDataSetName",
    "dataSetACL",
    "dataSetPath",
    "dsi",
    "other"
})
public class DataSetInfoType
    extends RequirementsType
{

    @XmlElement(name = "DataSetName", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String dataSetName;
    @XmlElement(name = "LocalDataSetName")
    protected List<InternationalStringType> localDataSetName;
    @XmlElement(name = "DataSetACL", required = true)
    protected AccessControlListType dataSetACL;
    @XmlElement(name = "DataSetPath", required = true)
    protected PathType dataSetPath;
    @XmlElement(name = "DSI")
    protected List<DSIType> dsi;
    @XmlElement(name = "Other")
    protected AnyType other;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

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
     * Gets the value of the localDataSetName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localDataSetName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalDataSetName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InternationalStringType }
     * 
     * 
     */
    public List<InternationalStringType> getLocalDataSetName() {
        if (localDataSetName == null) {
            localDataSetName = new ArrayList<InternationalStringType>();
        }
        return this.localDataSetName;
    }

    /**
     * Gets the value of the dataSetACL property.
     * 
     * @return
     *     possible object is
     *     {@link AccessControlListType }
     *     
     */
    public AccessControlListType getDataSetACL() {
        return dataSetACL;
    }

    /**
     * Sets the value of the dataSetACL property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessControlListType }
     *     
     */
    public void setDataSetACL(AccessControlListType value) {
        this.dataSetACL = value;
    }

    /**
     * Gets the value of the dataSetPath property.
     * 
     * @return
     *     possible object is
     *     {@link PathType }
     *     
     */
    public PathType getDataSetPath() {
        return dataSetPath;
    }

    /**
     * Sets the value of the dataSetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link PathType }
     *     
     */
    public void setDataSetPath(PathType value) {
        this.dataSetPath = value;
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
     * {@link DSIType }
     * 
     * 
     */
    public List<DSIType> getDSI() {
        if (dsi == null) {
            dsi = new ArrayList<DSIType>();
        }
        return this.dsi;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link AnyType }
     *     
     */
    public AnyType getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnyType }
     *     
     */
    public void setOther(AnyType value) {
        this.other = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
