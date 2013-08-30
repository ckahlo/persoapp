
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StateClassType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="StateClassType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Operational"/>
 *     &lt;enumeration value="NotOperational"/>
 *     &lt;enumeration value="RecognitionNecessary"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "StateClassType")
@XmlEnum
public enum StateClassType {

    @XmlEnumValue("Operational")
    OPERATIONAL("Operational"),
    @XmlEnumValue("NotOperational")
    NOT_OPERATIONAL("NotOperational"),
    @XmlEnumValue("RecognitionNecessary")
    RECOGNITION_NECESSARY("RecognitionNecessary");
    private final String value;

    StateClassType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StateClassType fromValue(String v) {
        for (StateClassType c: StateClassType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
