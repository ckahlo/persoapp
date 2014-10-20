
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WriteBehaviourType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WriteBehaviourType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="One-time-write"/>
 *     &lt;enumeration value="Proprietary"/>
 *     &lt;enumeration value="Write-OR"/>
 *     &lt;enumeration value="Write-AND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WriteBehaviourType")
@XmlEnum
public enum WriteBehaviourType {

    @XmlEnumValue("One-time-write")
    ONE_TIME_WRITE("One-time-write"),
    @XmlEnumValue("Proprietary")
    PROPRIETARY("Proprietary"),
    @XmlEnumValue("Write-OR")
    WRITE_OR("Write-OR"),
    @XmlEnumValue("Write-AND")
    WRITE_AND("Write-AND");
    private final String value;

    WriteBehaviourType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WriteBehaviourType fromValue(String v) {
        for (WriteBehaviourType c: WriteBehaviourType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
