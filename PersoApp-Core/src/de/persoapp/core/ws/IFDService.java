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

package de.persoapp.core.ws;

import iso.std.iso_iec._24727.tech.schema.IFD;
import iso.std.iso_iec._24727.tech.schema.InputAPDUInfoType;
import iso.std.iso_iec._24727.tech.schema.TransmitResponse;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import oasis.names.tc.dss._1_0.core.schema.Result;
import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.client.ECardSession;

/**
 * @author ckahlo
 * 
 */
@WebService(serviceName = "IFD", portName = "IFDPort", targetNamespace = "urn:iso:std:iso-iec:24727:tech:schema")
public final class IFDService implements IFD {

	@Resource
	private final WebServiceContext	wsCtx	= null;

	@PostConstruct
	public void init() {
		System.out.println("INIT called: " + wsCtx);
	}

	@Override
	public iso.std.iso_iec._24727.tech.schema.TransmitResponse transmit(
			final iso.std.iso_iec._24727.tech.schema.Transmit parameters) {

		final TransmitResponse response = new TransmitResponse();
		response.setRequestID(parameters.getRequestID());

		final ECardSession session = (ECardSession) wsCtx.getMessageContext().get(ECardSession.class.getName());
		final ICardHandler eCardHandler = session.getCardHandler(parameters.getSlotHandle());

		final List<byte[]> outputAPDUList = response.getOutputAPDU();
		for (final InputAPDUInfoType apdu : parameters.getInputAPDUInfo()) {
			byte[] apduMsg = apdu.getInputAPDU();
			if (apduMsg != null && apduMsg.length > 0) {
				if (apduMsg[0] != (byte) 0xFF) {
					apduMsg = eCardHandler.transmit(apduMsg);
				} else {
					apduMsg = new byte[] { 0x6D, 0x00 };
				}
				outputAPDUList.add(apduMsg);
			} else {
				System.out.println("IFD> empty APDU!");
			}
		}

		final Result result = new Result();
		result.setResultMajor(EcAPIProvider.ECARD_API_RESULT_OK);
		response.setResult(result);
		return response;
	}

}
