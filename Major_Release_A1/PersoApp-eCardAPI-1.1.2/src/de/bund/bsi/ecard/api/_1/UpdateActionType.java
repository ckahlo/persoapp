
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateActionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UpdateActionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Execute"/>
 *     &lt;enumeration value="Copy"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UpdateActionType")
@XmlEnum
public enum UpdateActionType {

    @XmlEnumValue("Execute")
    EXECUTE("Execute"),
    @XmlEnumValue("Copy")
    COPY("Copy");
    private final String value;

    UpdateActionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UpdateActionType fromValue(String v) {
        for (UpdateActionType c: UpdateActionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
