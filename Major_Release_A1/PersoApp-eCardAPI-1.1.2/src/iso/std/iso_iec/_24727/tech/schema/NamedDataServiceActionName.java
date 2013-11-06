
package iso.std.iso_iec._24727.tech.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NamedDataServiceActionName.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="NamedDataServiceActionName">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DataSetList"/>
 *     &lt;enumeration value="DataSetCreate"/>
 *     &lt;enumeration value="DataSetSelect"/>
 *     &lt;enumeration value="DataSetDelete"/>
 *     &lt;enumeration value="DSIList"/>
 *     &lt;enumeration value="DSICreate"/>
 *     &lt;enumeration value="DSIDelete"/>
 *     &lt;enumeration value="DSIRead"/>
 *     &lt;enumeration value="DSIWrite"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "NamedDataServiceActionName")
@XmlEnum
public enum NamedDataServiceActionName {

    @XmlEnumValue("DataSetList")
    DATA_SET_LIST("DataSetList"),
    @XmlEnumValue("DataSetCreate")
    DATA_SET_CREATE("DataSetCreate"),
    @XmlEnumValue("DataSetSelect")
    DATA_SET_SELECT("DataSetSelect"),
    @XmlEnumValue("DataSetDelete")
    DATA_SET_DELETE("DataSetDelete"),
    @XmlEnumValue("DSIList")
    DSI_LIST("DSIList"),
    @XmlEnumValue("DSICreate")
    DSI_CREATE("DSICreate"),
    @XmlEnumValue("DSIDelete")
    DSI_DELETE("DSIDelete"),
    @XmlEnumValue("DSIRead")
    DSI_READ("DSIRead"),
    @XmlEnumValue("DSIWrite")
    DSI_WRITE("DSIWrite");
    private final String value;

    NamedDataServiceActionName(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static NamedDataServiceActionName fromValue(String v) {
        for (NamedDataServiceActionName c: NamedDataServiceActionName.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
