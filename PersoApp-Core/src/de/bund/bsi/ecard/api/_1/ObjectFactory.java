package de.bund.bsi.ecard.api._1;

import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

//import org.etsi.uri._01903.v1_3.DigestAlgAndValueType;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the de.bund.bsi.ecard.api._1 package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName	_InitializeFramework_QNAME	= new QName("http://www.bsi.bund.de/ecard/api/1.1",
																	"InitializeFramework");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: de.bund.bsi.ecard.api._1
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link InitializeFrameworkResponse.Version }
	 * 
	 */
	public InitializeFrameworkResponse.Version createInitializeFrameworkResponseVersion() {
		return new InitializeFrameworkResponse.Version();
	}

	/**
	 * Create an instance of {@link InitializeFrameworkResponse }
	 * 
	 */
	public InitializeFrameworkResponse createInitializeFrameworkResponse() {
		return new InitializeFrameworkResponse();
	}
}
