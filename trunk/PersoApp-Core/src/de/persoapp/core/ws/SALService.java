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

import iso.std.iso_iec._24727.tech.schema.DIDAuthenticateResponse;
import iso.std.iso_iec._24727.tech.schema.DIDAuthenticationDataType;
import iso.std.iso_iec._24727.tech.schema.EAC1InputType;
import iso.std.iso_iec._24727.tech.schema.EAC1OutputType;
import iso.std.iso_iec._24727.tech.schema.EAC2InputType;
import iso.std.iso_iec._24727.tech.schema.EAC2OutputType;
import iso.std.iso_iec._24727.tech.schema.EACAdditionalInputType;
import iso.std.iso_iec._24727.tech.schema.SAL;

import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import oasis.names.tc.dss._1_0.core.schema.Result;
import de.persoapp.core.ECardWorker;
import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.card.TransportProvider;
import de.persoapp.core.client.EAC_Info;
import de.persoapp.core.client.ECardSession;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.IMainView.MainDialogResult;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.paos.PAOSInitiator;
import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;
import de.persoapp.core.util.TLV;

/**
 * @author ckahlo
 * 
 */
@WebService(serviceName = "SAL", portName = "SALPort", targetNamespace = "urn:iso:std:iso-iec:24727:tech:schema")
public class SALService implements SAL {

	@Resource
	protected final WebServiceContext	wsCtx	= null;

	@PostConstruct
	public final void init() {
		System.out.println("INIT called: " + wsCtx);
	}

	private static final PropertyResolver.Bundle	textBundle	= PropertyResolver.getBundle("text_core");

	@Override
	// enhace WSS-runtime to be able to remove
	@WebMethod(operationName = "DIDAuthenticate", action = "urn:iso:std:iso-iec:24727:tech:schema:DIDAuthenticate")
	@WebResult(name = "DIDAuthenticateResponse", targetNamespace = "urn:iso:std:iso-iec:24727:tech:schema", partName = "parameters")
	public iso.std.iso_iec._24727.tech.schema.DIDAuthenticateResponse didAuthenticate(
			final iso.std.iso_iec._24727.tech.schema.DIDAuthenticate parameters) {

		//		final byte[] contextHandle = parameters.getConnectionHandle().getContextHandle();
		final byte[] slotHandle = parameters.getConnectionHandle().getSlotHandle();

		final ECardSession session = (ECardSession) wsCtx.getMessageContext().get(ECardSession.class.getName());
		final ICardHandler eCardHandler = session.getCardHandler(slotHandle);

		// final IMainView mainView = session.getMainView(slotHandle);
		final IMainView mainView = session.getMainView();

		System.out.println("DIDAuth-SlotHandle: " + Hex.toString(slotHandle));

		final DIDAuthenticateResponse response = new DIDAuthenticateResponse();
		response.setProfile("http://www.bsi.bund.com/ecard/api/1.1");
		response.setRequestID(parameters.getRequestID());

		final Result result = new Result();
		result.setResultMajor(EcAPIProvider.ECARD_API_RESULT_ERROR);
		response.setResult(result);

		final DIDAuthenticationDataType authPD = parameters.getAuthenticationProtocolData();

		if (authPD == null) {
			System.out.println("no authentication protocol data");
			return null;
		}

		// DIDProtocols
		// has to match urn:oid:1.3.162.15480.3.0.14.2 for EAC-Version 2 (BSI TR-03112-7)
		// don't enforce for now there are eID-Servers out there giving you something, may be null
		System.out.println("DIDAuthProtocol: " + authPD.getProtocol() + " / " + authPD.getClass());

		if (authPD instanceof EAC1InputType) {
			final EAC1InputType eac1in = (EAC1InputType) authPD;
			eCardHandler.reset();

			final List<byte[]> cvcerts = eac1in.getCertificate();

			if (eac1in.getCertificateDescription().size() > 1) {
				System.out.println("WARNING: more than one cert.desc.");
			}

			final byte[] certDescription = eac1in.getCertificateDescription().get(0);
			final byte[] requiredCHAT = eac1in.getRequiredCHAT();
			final byte[] optionalCHAT = eac1in.getOptionalCHAT();
			final byte[] auxData = eac1in.getAuthenticatedAuxiliaryData();
			final String transactionInfo = eac1in.getTransactionInfo();

			System.out.println("Transaction-Info: " + transactionInfo);

			int verified = 0;
			final EAC_Info eacInfo = EAC_Info.newInstance(cvcerts, certDescription, transactionInfo, requiredCHAT,
					optionalCHAT, auxData);

			if (eacInfo != null && eacInfo.isValid()) {
				final byte[][] certHashes = eacInfo.getCertificateHashes();

				try {
					final Certificate[] sourceCerts = (Certificate[]) session
							.getAttribute(ECardSession.KEYS.SPServerCert.name());
					final PAOSInitiator pi = (PAOSInitiator) session.getAttribute(PAOSInitiator.class.getName());
					//
					final Certificate idpCert = pi.getPeerCertificate();

					final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
					final byte[] idpCertHash = sha256.digest(idpCert.getEncoded());

					for (final Certificate spCert : sourceCerts) {
						boolean certVerified = false;
						final byte[] spCertHash = sha256.digest(spCert.getEncoded());

						for (final byte[] certHashe : certHashes) {
							if (ArrayTool.arrayequal(spCertHash, certHashe)) {
								certVerified = true;
								break;
							}
						}

						if (certVerified) {
							verified = 1;
							System.out.println("VALID: " + ((X509Certificate) spCert).getSubjectDN());
						} else {
							verified = 0;
							System.out.println("validation FAILED at: " + ((X509Certificate) spCert).getSubjectDN());
							System.out.println(Hex.toString(spCertHash));
							break;
						}
					}

					if (verified == 1) {
						for (final byte[] certHash : certHashes) {
							if (ArrayTool.arrayequal(idpCertHash, certHash)) {
								verified = 2;
								break;
							}
						}
					}
				} catch (final Exception e1) {
					e1.printStackTrace();
				}
			}

			if (verified < 2) {
				mainView.showMainDialog(eacInfo, IMainView.MODE_NONE);
				mainView.showError(textBundle.get("SALService_certificate_error_title"),
						textBundle.get("SALService_certificate_error_text"));
				return null;
			}

			session.setAttribute(EAC_Info.class.getName(), eacInfo);

			final ECardWorker ecw = (ECardWorker) session.getAttribute(ECardWorker.class.getName());

			/*
			 * as of BSI TR-03112 V1.1.2 the client is required to check the
			 * tcTokenURL for same-origin policy (RFC6454) with the subjectURL
			 */
			final URI tcTokenURL = (URI) session.getAttribute(ECardSession.KEYS.tcTokenURL.name());

			try {
				final URI subjectURL = eacInfo.getSubjectURL() != null
						&& eacInfo.getSubjectURL().toLowerCase().startsWith("https://") ? new URL(
						eacInfo.getSubjectURL()).toURI().resolve("/") : null;
				if (subjectURL == null || !tcTokenURL.resolve("/").equals(subjectURL.resolve("/"))) {
					final String tcTokenWarn = "Die Seite\n " + tcTokenURL.resolve("/") + "\nwill im Namen von\n "
							+ eacInfo.getSubjectURL() + "\neine Online-Authentisierung durchführen.\n";
					System.out.println(tcTokenWarn);
					mainView.showMessage(tcTokenWarn, IMainView.WARNING);

					if ("true".equals(System.getProperty("de.persoapp.forceStrictMode"))) {
						// XXX: authentication failed
						if (ecw != null) {
							ecw.callback(ECardWorker.CALLBACK_RESULT.TA_ERROR);
						}

						return null;
					}
				}
			} catch (final Exception e) {
				e.printStackTrace();
				return null;
			}

			byte[] resultCHAT;
			TransportProvider tp = null;
			boolean mainViewOpen = false;

			final String onlineAuthTitle = textBundle.get("SALService_online_auth_title");
			while (true) {
				do {
					if ((tp = eCardHandler.getECard()) != null) {
						mainView.showMainDialog(eacInfo, eCardHandler.hasPACE(tp) > 0 ? IMainView.MODE_CHATONLY
								: IMainView.MODE_PIN_CHAT);
					} else {
						if (!mainViewOpen) {
							mainView.showMainDialog(eacInfo, IMainView.MODE_PIN_CHAT);
							mainViewOpen = true;
						}
						if (!mainView.showQuestion(onlineAuthTitle, textBundle.get("SALService_insert_card"))) {
							break;
						}
					}
				} while (tp == null);

				if (tp == null) {
					return null;
				}

				if (tp.lastSW() == 0x63C1) {
					mainView.showMessage(textBundle.get("SALService_one_pin_try"), IMainView.WARNING);
				}

				final MainDialogResult dialogResult = mainView.getMainDialogResult();

				if (dialogResult.isApproved()) {
					final long dialogCHAT = dialogResult.getCHAT();
					resultCHAT = new byte[5];
					for (int i = resultCHAT.length - 1; i > 0; i--) {
						resultCHAT[i] = (byte) (dialogCHAT >> 8 * (resultCHAT.length - 1 - i));
					}

					final boolean requireQES = (requiredCHAT[requiredCHAT.length - 1] & 0x80) != 0;
					final boolean wantQES = optionalCHAT != null && (optionalCHAT[optionalCHAT.length - 1] & 0x80) != 0;

					boolean installQES = false;

					if (requireQES || wantQES) {
						installQES = mainView.showQuestion(
								onlineAuthTitle,
								textBundle.get("SALService_install_QES")
										+ (wantQES ? textBundle.get("SALService_install_QES_optional") : "")
										+ textBundle.get("SALService_install_QES_allow"));
					}

					if (installQES) {
						resultCHAT[4] |= 0x80;
					}

					// XXX: rework that
					resultCHAT = TLV.build(0x7F4C, TLV.buildOID("04007F000703010202", TLV.build(0x53, resultCHAT)));

					mainView.showProgress(textBundle.get("SALService_progress_pin"), 10, true);
					final boolean success = eCardHandler.startAuthentication(resultCHAT, dialogResult.getPIN(),
							eacInfo.getTerminalDescription());

					if (success) {
						mainView.showProgress(textBundle.get("SALService_progress_pin_suc"), 25, true);

						if (eCardHandler.getIDPICC() == null) {
							// Fehler bei der Verbindung, es konnte kein PACE durchgeführt werden
							mainView.showError(onlineAuthTitle, textBundle.get("SALService_online_auth_error_text"));
							eCardHandler.reset();
							return null;
						} else {
							break;
						}
					} else {
						final boolean retry = mainView.showQuestion(onlineAuthTitle,
								textBundle.get("SALService_pin_retry"));
						if (retry) {
							continue;
						} else {
							eCardHandler.reset();
							return null;
						}
					}
				} else {
					if (ecw != null) {
						ecw.callback(ECardWorker.CALLBACK_RESULT.CANCEL);
					}

					System.out.println("Declined.");
					return null;
				}
			}

			// check for availability of suitable certificate

			final EAC1OutputType eac1out = new EAC1OutputType();

			eac1out.setRetryCounter(BigInteger.valueOf(3));
			eac1out.setCertificateHolderAuthorizationTemplate(resultCHAT);
			eac1out.setEFCardAccess(eCardHandler.getEFCardAccess());
			eac1out.setIDPICC(eCardHandler.getIDPICC());

			// cardChallenge
			final byte[] taChallenge = eCardHandler.getTAChallenge();
			session.setAttribute("TAChallenge", taChallenge);
			eac1out.setChallenge(taChallenge);

			// send CA ref = no valid cert chain, don't send CA ref = chain complete
			boolean foundCAReference = false;
			for (final byte[] CAR : eCardHandler.getCAReferences()) {
				for (final byte[] cvcert : cvcerts) {
					if (Arrays.equals(CAR,
							TLV.get(TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E), (byte) 0x42))) {
						foundCAReference = true;
						break;
					}
				}
			}

			if (!foundCAReference) {
				eac1out.getCertificationAuthorityReference().addAll(eCardHandler.getCAReferences());
			}

			response.setAuthenticationProtocolData(eac1out);
			result.setResultMajor(EcAPIProvider.ECARD_API_RESULT_OK);
		} else if (authPD instanceof EAC2InputType) {
			final EAC2InputType eac2in = (EAC2InputType) authPD;

			final EAC_Info eacInfo = (EAC_Info) session.getAttribute(EAC_Info.class.getName());

			// use a really hashable representation as key
			final Map<ByteBuffer, byte[]> certMap = new HashMap<ByteBuffer, byte[]>();

			final List<byte[]> eac1certs = eacInfo.getCertificateChain();
			final List<byte[]> eac2certs = eac2in.getCertificate();

			for (final byte[] cvcert : eac1certs) {
				final byte[] CARCert = TLV.get(TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E), (byte) 0x42);
				certMap.put(ByteBuffer.wrap(CARCert), cvcert);
			}

			// XXX: may break!
			byte[] root = null;
			for (final byte[] CAR : eCardHandler.getCAReferences()) {
				if (root == null && certMap.containsKey(ByteBuffer.wrap(CAR))) {
					root = CAR;
					break;
				}
			}

			boolean validMode = false;
			if (root != null) {
				System.out.println("eCard API 1.1.1 mode");
				if (eac2certs.size() == 0) {
					validMode = true;
				} else {
					for (final byte[] cvcert : eac2certs) {
						if (cvcert == null || cvcert.length == 0) {
							continue;
						}
						try {
							final byte[] cvcert_ = TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E);
							final byte[] CARCert = TLV.get(cvcert_, (byte) 0x42);
							final byte[] CHRCert = TLV.get(cvcert_, (short) 0x5F20);

							final byte[] chat = TLV.get(TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E),
									(short) 0x7F4C);
							final byte[] oid = TLV.get(chat, (byte) 0x06); // IS, AT, ST, etc.
							final byte[] auth = TLV.get(chat, (byte) 0x53);

							if (auth != null && (auth[0] & (byte) 0xC0) == 0x00) {
								if (ArrayTool.arrayequal(certMap.get(ByteBuffer.wrap(CARCert)), cvcert)) {
									System.out.println("WARNING: AT-Certificate with ID " + CARCert + "/" + CHRCert
											+ " doubled.");
									validMode = true;
									continue;
								} else {
									System.out.println("ERROR: AT-Certificate with ID " + CARCert + "/" + CHRCert
											+ " is different.");
								}
								// is DVCA / CVCA
							} else {
								System.out.println("ERROR: additional DVCA/CVCA certificate");
							}
						} catch (final Exception e) {
							e.printStackTrace();
						}

						validMode = false;
						root = null;
						break;
					}

					// System.out.println("ERROR: eCard API 1.1.1 mode expected, but received further certificates");
					// root = null;
					// validMode = false;
				}
			} else {
				if (eac2certs.size() == 0) {
					System.out.println("ERROR: no suitable certificate chain.");
				} else {
					System.out.println("eCard API 1.1.0 mode");
					for (final byte[] cvcert : eac2certs) {
						if (cvcert == null || cvcert.length == 0) {
							continue;
						}

						final byte[] cvcert_ = TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E);
						final byte[] CARCert = TLV.get(cvcert_, (byte) 0x42);
						final byte[] CHRCert = TLV.get(cvcert_, (short) 0x5F20);

						final byte[] chat = TLV.get(TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E),
								(short) 0x7F4C);
						final byte[] oid = TLV.get(chat, (byte) 0x06); // IS, AT, ST, etc.
						final byte[] auth = TLV.get(chat, (byte) 0x53);

						// is AT/IS/ST
						if (auth != null && (auth[0] & (byte) 0xC0) == 0x00) {
							if (ArrayTool.arrayequal(certMap.get(ByteBuffer.wrap(CARCert)), cvcert)) {
								System.out.println("WARNING: AT-Certificate with ID " + CARCert + "/" + CHRCert
										+ " doubled.");
								continue;
							} else {
								System.out.println("ERROR: AT-Certificate with ID " + CARCert + "/" + CHRCert
										+ " is different.");
								validMode = false;
								root = null;
								break;
							}
							// is DVCA / CVCA
						} else {
							if (certMap.put(ByteBuffer.wrap(CARCert), cvcert) != null) {
								System.out.println("WARNING: DV-Certificate with ID " + CARCert + "/" + CHRCert
										+ " doubled.");
								// validMode = false;
								// root = null;
								// break;
							} else {
								for (final byte[] CAR : eCardHandler.getCAReferences()) {
									if (root == null && Arrays.equals(CAR, CARCert)) {
										root = CAR;
									}
								}
								validMode = true;
							}
						}
					}
				}
			}

			if (!validMode || root == null) {
				mainView.showError(textBundle.get("SALService_certificate_error_title"),
						textBundle.get("SALService_certificate_error_text"));
			} else {
				final byte[] ephemeralKey = eac2in.getEphemeralPublicKey();
				byte[] cvcert = null;

				while ((cvcert = certMap.get(ByteBuffer.wrap(root))) != null) {
					final byte[] cvcert_ = TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E);
					final byte[] CAR = TLV.get(cvcert_, (byte) 0x42);
					final byte[] CHR = TLV.get(cvcert_, (short) 0x5F20);

					System.out.println("CVC: " + CAR + "/" + CHR + " = " + Hex.toString(cvcert));
					if (eCardHandler.verifyCertificate(cvcert)) {
						root = CHR;
					} else {
						certMap.remove(root);
						System.out.println("WARNING: [" + CAR + "/" + CHR + "] did not pass verification.");
					}
				}

				mainView.showProgress(textBundle.get("SALService_progress_cert"), 50, true);

				eCardHandler.initTA(ephemeralKey, eacInfo.getAuxiliaryData());
			}

			if (validMode) {
				final EAC2OutputType eac2out = new EAC2OutputType();

				if (eac2in.getSignature() != null) {
					final ECardWorker ecw = (ECardWorker) session.getAttribute(ECardWorker.class.getName());
					if (!eCardHandler.verifyTASignature(eac2in.getSignature())) {
						// validation of entitlement certificate failed
						mainView.showError(textBundle.get("SALService_check_error_title"),
								textBundle.get("SALService_check_error_text"));
						eCardHandler.reset();
						if (ecw != null) {
							ecw.callback(ECardWorker.CALLBACK_RESULT.TA_ERROR);
						}
						return null;
					} else {
						if (ecw != null) {
							ecw.callback(ECardWorker.CALLBACK_RESULT.TA_OK,
									session.getAttribute(EAC_Info.class.getName()));
						}
					}

					eac2out.setEFCardSecurity(eCardHandler.getEFCardSecurity());

					final byte[] caData = eCardHandler.execCA();
					eac2out.setNonce(TLV.get(caData, (byte) 0x81));
					eac2out.setAuthenticationToken(TLV.get(caData, (byte) 0x82));

					mainView.showProgress(textBundle.get("SALService_progress_data"), 75, true);
				} else {
					eac2out.setChallenge((byte[]) session.getAttribute("TAChallenge"));
					// eac2out.setChallenge(cardChallenge);
				}

				response.setAuthenticationProtocolData(eac2out);
				result.setResultMajor(EcAPIProvider.ECARD_API_RESULT_OK);
			}
		} else if (authPD instanceof EACAdditionalInputType) {
			final EACAdditionalInputType eacAddin = (EACAdditionalInputType) authPD;

			final ECardWorker ecw = (ECardWorker) session.getAttribute(ECardWorker.class.getName());
			if (!eCardHandler.verifyTASignature(eacAddin.getSignature())) {
				// validation of entitlement certificate failed
				mainView.showError(textBundle.get("SALService_check_error_title"),
						textBundle.get("SALService_check_error_text"));
				eCardHandler.reset();
				if (ecw != null) {
					ecw.callback(ECardWorker.CALLBACK_RESULT.TA_ERROR);
				}
				return null;
			} else {
				if (ecw != null) {
					ecw.callback(ECardWorker.CALLBACK_RESULT.TA_OK, session.getAttribute(EAC_Info.class.getName()));
				}
			}

			final EAC2OutputType eac2out = new EAC2OutputType();
			eac2out.setEFCardSecurity(eCardHandler.getEFCardSecurity());

			final byte[] caData = eCardHandler.execCA();
			eac2out.setNonce(TLV.get(caData, (byte) 0x81));
			eac2out.setAuthenticationToken(TLV.get(caData, (byte) 0x82));

			mainView.showProgress(textBundle.get("SALService_progress_data"), 75, true);

			response.setAuthenticationProtocolData(eac2out);
			result.setResultMajor(EcAPIProvider.ECARD_API_RESULT_OK);
		} else {
			System.out.println("unknown: " + authPD.getClass().getName() + " = " + authPD);
		}

		return response;
	}
}
