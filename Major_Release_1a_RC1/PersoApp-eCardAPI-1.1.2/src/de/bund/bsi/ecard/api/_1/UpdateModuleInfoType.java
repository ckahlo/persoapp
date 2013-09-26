
package de.bund.bsi.ecard.api._1;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateModuleInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateModuleInfoType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.bsi.bund.de/ecard/api/1.1}ModuleInfoType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UpdatePriority" type="{http://www.bsi.bund.de/ecard/api/1.1}UpdatePriorityType"/>
 *         &lt;element name="File" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SourceURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *                   &lt;element name="UpdateAction" type="{http://www.bsi.bund.de/ecard/api/1.1}UpdateActionType"/>
 *                   &lt;element name="DestinationURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
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
@XmlType(name = "UpdateModuleInfoType", propOrder = {
    "description",
    "updatePriority",
    "file"
})
public class UpdateModuleInfoType
    extends ModuleInfoType
{

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "UpdatePriority", required = true)
    protected UpdatePriorityType updatePriority;
    @XmlElement(name = "File")
    protected List<UpdateModuleInfoType.File> file;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the updatePriority property.
     * 
     * @return
     *     possible object is
     *     {@link UpdatePriorityType }
     *     
     */
    public UpdatePriorityType getUpdatePriority() {
        return updatePriority;
    }

    /**
     * Sets the value of the updatePriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdatePriorityType }
     *     
     */
    public void setUpdatePriority(UpdatePriorityType value) {
        this.updatePriority = value;
    }

    /**
     * Gets the value of the file property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the file property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UpdateModuleInfoType.File }
     * 
     * 
     */
    public List<UpdateModuleInfoType.File> getFile() {
        if (file == null) {
            file = new ArrayList<UpdateModuleInfoType.File>();
        }
        return this.file;
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
     *         &lt;element name="SourceURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
     *         &lt;element name="UpdateAction" type="{http://www.bsi.bund.de/ecard/api/1.1}UpdateActionType"/>
     *         &lt;element name="DestinationURL" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
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
        "sourceURL",
        "updateAction",
        "destinationURL"
    })
    public static class File {

        @XmlElement(name = "SourceURL", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String sourceURL;
        @XmlElement(name = "UpdateAction", required = true)
        protected UpdateActionType updateAction;
        @XmlElement(name = "DestinationURL")
        @XmlSchemaType(name = "anyURI")
        protected String destinationURL;

        /**
         * Gets the value of the sourceURL property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSourceURL() {
            return sourceURL;
        }

        /**
         * Sets the value of the sourceURL property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSourceURL(String value) {
            this.sourceURL = value;
        }

        /**
         * Gets the value of the updateAction property.
         * 
         * @return
         *     possible object is
         *     {@link UpdateActionType }
         *     
         */
        public UpdateActionType getUpdateAction() {
            return updateAction;
        }

        /**
         * Sets the value of the updateAction property.
         * 
         * @param value
         *     allowed object is
         *     {@link UpdateActionType }
         *     
         */
        public void setUpdateAction(UpdateActionType value) {
            this.updateAction = value;
        }

        /**
         * Gets the value of the destinationURL property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDestinationURL() {
            return destinationURL;
        }

        /**
         * Sets the value of the destinationURL property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDestinationURL(String value) {
            this.destinationURL = value;
        }

    }

}
