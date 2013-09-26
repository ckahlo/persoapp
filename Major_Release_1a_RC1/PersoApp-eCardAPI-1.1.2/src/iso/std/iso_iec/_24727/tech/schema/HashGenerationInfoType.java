
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HashGenerationInfoType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HashGenerationInfoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NotOnCard"/>
 *     &lt;enumeration value="CompletelyOnCard"/>
 *     &lt;enumeration value="LastRoundOnCard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HashGenerationInfoType")
@XmlEnum
public enum HashGenerationInfoType {

    @XmlEnumValue("NotOnCard")
    NOT_ON_CARD("NotOnCard"),
    @XmlEnumValue("CompletelyOnCard")
    COMPLETELY_ON_CARD("CompletelyOnCard"),
    @XmlEnumValue("LastRoundOnCard")
    LAST_ROUND_ON_CARD("LastRoundOnCard");
    private final String value;

    HashGenerationInfoType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HashGenerationInfoType fromValue(String v) {
        for (HashGenerationInfoType c: HashGenerationInfoType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
