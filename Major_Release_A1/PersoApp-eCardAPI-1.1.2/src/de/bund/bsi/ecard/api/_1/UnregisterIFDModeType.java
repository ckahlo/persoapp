
package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnregisterIFDModeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnregisterIFDModeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="temporary"/>
 *     &lt;enumeration value="permanent"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnregisterIFDModeType")
@XmlEnum
public enum UnregisterIFDModeType {

    @XmlEnumValue("temporary")
    TEMPORARY("temporary"),
    @XmlEnumValue("permanent")
    PERMANENT("permanent");
    private final String value;

    UnregisterIFDModeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnregisterIFDModeType fromValue(String v) {
        for (UnregisterIFDModeType c: UnregisterIFDModeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
