
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VariantIndicatorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VariantIndicatorType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CommandWithoutSM"/>
 *     &lt;enumeration value="ResponseWithoutSM"/>
 *     &lt;enumeration value="CommandWithSM"/>
 *     &lt;enumeration value="ResponseWithSM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VariantIndicatorType")
@XmlEnum
public enum VariantIndicatorType {

    @XmlEnumValue("CommandWithoutSM")
    COMMAND_WITHOUT_SM("CommandWithoutSM"),
    @XmlEnumValue("ResponseWithoutSM")
    RESPONSE_WITHOUT_SM("ResponseWithoutSM"),
    @XmlEnumValue("CommandWithSM")
    COMMAND_WITH_SM("CommandWithSM"),
    @XmlEnumValue("ResponseWithSM")
    RESPONSE_WITH_SM("ResponseWithSM");
    private final String value;

    VariantIndicatorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VariantIndicatorType fromValue(String v) {
        for (VariantIndicatorType c: VariantIndicatorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
