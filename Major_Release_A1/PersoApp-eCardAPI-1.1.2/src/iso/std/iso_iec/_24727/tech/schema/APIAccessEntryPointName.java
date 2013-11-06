
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for APIAccessEntryPointName.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="APIAccessEntryPointName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Initialize"/>
 *     &lt;enumeration value="Terminate"/>
 *     &lt;enumeration value="CardApplicationPath"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "APIAccessEntryPointName")
@XmlEnum
public enum APIAccessEntryPointName {

    @XmlEnumValue("Initialize")
    INITIALIZE("Initialize"),
    @XmlEnumValue("Terminate")
    TERMINATE("Terminate"),
    @XmlEnumValue("CardApplicationPath")
    CARD_APPLICATION_PATH("CardApplicationPath");
    private final String value;

    APIAccessEntryPointName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static APIAccessEntryPointName fromValue(String v) {
        for (APIAccessEntryPointName c: APIAccessEntryPointName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
