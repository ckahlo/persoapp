
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BasicRequirementsType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="BasicRequirementsType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PlatformMandatory"/>
 *     &lt;enumeration value="PlatformOptional"/>
 *     &lt;enumeration value="PersonalizationMandatory"/>
 *     &lt;enumeration value="PersonalizationOptional"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "BasicRequirementsType")
@XmlEnum
public enum BasicRequirementsType {

    @XmlEnumValue("PlatformMandatory")
    PLATFORM_MANDATORY("PlatformMandatory"),
    @XmlEnumValue("PlatformOptional")
    PLATFORM_OPTIONAL("PlatformOptional"),
    @XmlEnumValue("PersonalizationMandatory")
    PERSONALIZATION_MANDATORY("PersonalizationMandatory"),
    @XmlEnumValue("PersonalizationOptional")
    PERSONALIZATION_OPTIONAL("PersonalizationOptional");
    private final String value;

    BasicRequirementsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static BasicRequirementsType fromValue(String v) {
        for (BasicRequirementsType c: BasicRequirementsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
