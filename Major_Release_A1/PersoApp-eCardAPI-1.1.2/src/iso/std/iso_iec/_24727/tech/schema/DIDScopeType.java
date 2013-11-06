
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DIDScopeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DIDScopeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="local"/>
 *     &lt;enumeration value="global"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DIDScopeType")
@XmlEnum
public enum DIDScopeType {

    @XmlEnumValue("local")
    LOCAL("local"),
    @XmlEnumValue("global")
    GLOBAL("global");
    private final String value;

    DIDScopeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DIDScopeType fromValue(String v) {
        for (DIDScopeType c: DIDScopeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
