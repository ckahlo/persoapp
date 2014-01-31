/**
 * 
 * COPYRIGHT (C) 2010, 2011, 2012, 2013 AGETO Innovation GmbH
 * 
 * Authors Christian Kahlo, Ralf Wondratschek
 * 
 * All Rights Reserved.
 * 
 * Contact: PersoApp, http://www.persoapp.de
 * 
 * @version 1.0, 30.07.2013 13:50:47
 * 
 *          This file is part of PersoApp.
 * 
 *          PersoApp is free software: you can redistribute it and/or modify it
 *          under the terms of the GNU Lesser General Public License as
 *          published by the Free Software Foundation, either version 3 of the
 *          License, or (at your option) any later version.
 * 
 *          PersoApp is distributed in the hope that it will be useful, but
 *          WITHOUT ANY WARRANTY; without even the implied warranty of
 *          MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *          Lesser General Public License for more details.
 * 
 *          You should have received a copy of the GNU Lesser General Public
 *          License along with PersoApp. If not, see
 *          <http://www.gnu.org/licenses/>.
 * 
 *          Diese Datei ist Teil von PersoApp.
 * 
 *          PersoApp ist Freie Software: Sie können es unter den Bedingungen der
 *          GNU Lesser General Public License, wie von der Free Software
 *          Foundation, Version 3 der Lizenz oder (nach Ihrer Option) jeder
 *          späteren veröffentlichten Version, weiterverbreiten und/oder
 *          modifizieren.
 * 
 *          PersoApp wird in der Hoffnung, dass es nützlich sein wird, aber OHNE
 *          JEDE GEWÄHRLEISTUNG, bereitgestellt; sogar ohne die implizite
 *          Gewährleistung der MARKTFÄHIGKEIT oder EIGNUNG FÜR EINEN BESTIMMTEN
 *          ZWECK. Siehe die GNU Lesser General Public License für weitere
 *          Details.
 * 
 *          Sie sollten eine Kopie der GNU Lesser General Public License
 *          zusammen mit diesem Programm erhalten haben. Wenn nicht, siehe
 *          <http://www.gnu.org/licenses/>.
 * 
 */
package de.persoapp.core.util;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * 
 * @author ckahlo
 */
public class EcApi_TCTokenHandler implements ContentHandler {

	private final Map<String, String>	props				= new HashMap<String, String>();

	private String						currentElementName	= null;
	private StringBuffer				currentValue;

	public Map<String, String> getProperties() {
		return props;
	}

	@Override
	public void setDocumentLocator(final Locator locator) {
	}

	@Override
	public void startDocument() throws SAXException {
		props.clear();
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startPrefixMapping(final String prefix, final String uri) throws SAXException {
	}

	@Override
	public void endPrefixMapping(final String prefix) throws SAXException {
	}

	@Override
	public void startElement(final String uri, final String localName, final String qName, final Attributes atts)
			throws SAXException {
		if ("TCTokenType".equals(localName)) {
			return;
		}

		if (currentElementName == null) {
			currentElementName = localName;
			currentValue = new StringBuffer();
		}
	}

	@Override
	public void endElement(final String uri, final String localName, final String qName) throws SAXException {
		if (localName.equals(currentElementName)) {
			if (currentValue != null) {
				props.put(currentElementName, currentValue.toString().trim());
				currentValue = null;
			}
			currentElementName = null;
		}
	}

	@Override
	public void characters(final char[] ch, final int start, final int length) throws SAXException {
		if (currentValue != null) {
			currentValue.append(ch, start, length);
		}
	}

	@Override
	public void ignorableWhitespace(final char[] ch, final int start, final int length) throws SAXException {
	}

	@Override
	public void processingInstruction(final String target, final String data) throws SAXException {
	}

	@Override
	public void skippedEntity(final String name) throws SAXException {
	}
}
