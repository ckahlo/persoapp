/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package de.persoapp.core.ws;

import java.math.BigInteger;

import javax.jws.WebService;

import oasis.names.tc.dss._1_0.core.schema.Result;
import de.bund.bsi.ecard.api._1.InitializeFrameworkResponse;
import de.bund.bsi.ecard.api._1.Management;

/**
 * 
 * @author ckahlo
 */
// @WebService(serviceName = "Management", portName = "ManagementPort", endpointInterface = "de.bund.bsi.ecard.api._1.Management", targetNamespace =
// "http://www.bsi.bund.de/ecard/api/1.1", wsdlLocation = "wsdl/Management.wsdl")
@WebService(serviceName = "Management", portName = "ManagementPort", targetNamespace = "http://www.bsi.bund.de/ecard/api/1.1")
public final class ManagementService implements Management {

	@Override
	public de.bund.bsi.ecard.api._1.InitializeFrameworkResponse initializeFramework(
			final iso.std.iso_iec._24727.tech.schema.RequestType parameters) {
		final InitializeFrameworkResponse initFWResponse = new InitializeFrameworkResponse();
		final InitializeFrameworkResponse.Version fwVersion = new InitializeFrameworkResponse.Version();
		fwVersion.setMajor(BigInteger.valueOf(1));
		fwVersion.setMinor(BigInteger.valueOf(12));
		fwVersion.setSubMinor(BigInteger.valueOf(0));
		initFWResponse.setVersion(fwVersion);

		final Result result = new Result();
		result.setResultMajor(EcAPIProvider.ECARD_API_RESULT_OK);
		initFWResponse.setResult(result);
		initFWResponse.setRequestID(parameters.getRequestID());

		return initFWResponse;
	}
}
