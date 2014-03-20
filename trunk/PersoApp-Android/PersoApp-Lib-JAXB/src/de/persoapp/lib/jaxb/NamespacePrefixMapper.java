package de.persoapp.lib.jaxb;

public abstract class NamespacePrefixMapper {
	abstract public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix);
}
