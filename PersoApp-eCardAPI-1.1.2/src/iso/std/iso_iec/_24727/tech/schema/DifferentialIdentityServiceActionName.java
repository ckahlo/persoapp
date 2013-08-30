
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DifferentialIdentityServiceActionName.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DifferentialIdentityServiceActionName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DIDList"/>
 *     &lt;enumeration value="DIDCreate"/>
 *     &lt;enumeration value="DIDGet"/>
 *     &lt;enumeration value="DIDUpdate"/>
 *     &lt;enumeration value="DIDDelete"/>
 *     &lt;enumeration value="DIDAuthenticate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DifferentialIdentityServiceActionName")
@XmlEnum
public enum DifferentialIdentityServiceActionName {

    @XmlEnumValue("DIDList")
    DID_LIST("DIDList"),
    @XmlEnumValue("DIDCreate")
    DID_CREATE("DIDCreate"),
    @XmlEnumValue("DIDGet")
    DID_GET("DIDGet"),
    @XmlEnumValue("DIDUpdate")
    DID_UPDATE("DIDUpdate"),
    @XmlEnumValue("DIDDelete")
    DID_DELETE("DIDDelete"),
    @XmlEnumValue("DIDAuthenticate")
    DID_AUTHENTICATE("DIDAuthenticate");
    private final String value;

    DifferentialIdentityServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DifferentialIdentityServiceActionName fromValue(String v) {
        for (DifferentialIdentityServiceActionName c: DifferentialIdentityServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
