package de.persoapp.core.util;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public final class MyHexBinaryAdapter extends XmlAdapter<String, byte[]> {
	@Override
	public byte[] unmarshal(final String s) {
		if (s == null) {
			return null;
		}
		return DatatypeConverter.parseHexBinary(s.trim());
	}

	@Override
	public String marshal(final byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		return DatatypeConverter.printHexBinary(bytes);
	}
}
