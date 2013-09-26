
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CryptographicServiceActionName.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CryptographicServiceActionName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Encipher"/>
 *     &lt;enumeration value="Decipher"/>
 *     &lt;enumeration value="GetRandom"/>
 *     &lt;enumeration value="Hash"/>
 *     &lt;enumeration value="Sign"/>
 *     &lt;enumeration value="VerifySignature"/>
 *     &lt;enumeration value="VerifyCertificate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CryptographicServiceActionName")
@XmlEnum
public enum CryptographicServiceActionName {

    @XmlEnumValue("Encipher")
    ENCIPHER("Encipher"),
    @XmlEnumValue("Decipher")
    DECIPHER("Decipher"),
    @XmlEnumValue("GetRandom")
    GET_RANDOM("GetRandom"),
    @XmlEnumValue("Hash")
    HASH("Hash"),
    @XmlEnumValue("Sign")
    SIGN("Sign"),
    @XmlEnumValue("VerifySignature")
    VERIFY_SIGNATURE("VerifySignature"),
    @XmlEnumValue("VerifyCertificate")
    VERIFY_CERTIFICATE("VerifyCertificate");
    private final String value;

    CryptographicServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CryptographicServiceActionName fromValue(String v) {
        for (CryptographicServiceActionName c: CryptographicServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
