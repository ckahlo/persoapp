
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdatePriorityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpdatePriorityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Mandatory"/>
 *     &lt;enumeration value="Recommended"/>
 *     &lt;enumeration value="Optional"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UpdatePriorityType")
@XmlEnum
public enum UpdatePriorityType {

    @XmlEnumValue("Mandatory")
    MANDATORY("Mandatory"),
    @XmlEnumValue("Recommended")
    RECOMMENDED("Recommended"),
    @XmlEnumValue("Optional")
    OPTIONAL("Optional");
    private final String value;

    UpdatePriorityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UpdatePriorityType fromValue(String v) {
        for (UpdatePriorityType c: UpdatePriorityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
