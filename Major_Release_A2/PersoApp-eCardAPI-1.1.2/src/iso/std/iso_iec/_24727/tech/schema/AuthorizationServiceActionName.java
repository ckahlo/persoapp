
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthorizationServiceActionName.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AuthorizationServiceActionName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACLList"/>
 *     &lt;enumeration value="ACLModify"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AuthorizationServiceActionName")
@XmlEnum
public enum AuthorizationServiceActionName {

    @XmlEnumValue("ACLList")
    ACL_LIST("ACLList"),
    @XmlEnumValue("ACLModify")
    ACL_MODIFY("ACLModify");
    private final String value;

    AuthorizationServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AuthorizationServiceActionName fromValue(String v) {
        for (AuthorizationServiceActionName c: AuthorizationServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
