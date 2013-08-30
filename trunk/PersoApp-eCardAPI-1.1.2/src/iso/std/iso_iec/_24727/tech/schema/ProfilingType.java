
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProfilingType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProfilingType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="extends"/>
 *     &lt;enumeration value="redefines"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProfilingType")
@XmlEnum
public enum ProfilingType {

    @XmlEnumValue("extends")
    EXTENDS("extends"),
    @XmlEnumValue("redefines")
    REDEFINES("redefines");
    private final String value;

    ProfilingType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProfilingType fromValue(String v) {
        for (ProfilingType c: ProfilingType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
