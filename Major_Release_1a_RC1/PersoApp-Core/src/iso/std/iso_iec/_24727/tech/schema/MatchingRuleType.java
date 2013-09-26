
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MatchingRuleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MatchingRuleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Equals"/>
 *     &lt;enumeration value="Contains"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MatchingRuleType")
@XmlEnum
public enum MatchingRuleType {

    @XmlEnumValue("Equals")
    EQUALS("Equals"),
    @XmlEnumValue("Contains")
    CONTAINS("Contains");
    private final String value;

    MatchingRuleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MatchingRuleType fromValue(String v) {
        for (MatchingRuleType c: MatchingRuleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
