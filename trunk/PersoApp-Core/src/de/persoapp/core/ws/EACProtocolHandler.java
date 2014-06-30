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

import iso.std.iso_iec._24727.tech.schema.DIDAuthenticationDataType;
import iso.std.iso_iec._24727.tech.schema.EAC1InputType;
import iso.std.iso_iec._24727.tech.schema.EAC1OutputType;
import iso.std.iso_iec._24727.tech.schema.EAC2InputType;
import iso.std.iso_iec._24727.tech.schema.EAC2OutputType;
import iso.std.iso_iec._24727.tech.schema.EACAdditionalInputType;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import de.persoapp.core.card.ICardHandler;
import de.persoapp.core.card.TransportProvider;
import de.persoapp.core.client.EAC_Info;
import de.persoapp.core.client.ECardSession;
import de.persoapp.core.client.IMainView;
import de.persoapp.core.client.PropertyResolver;
import de.persoapp.core.client.IMainView.MainDialogResult;
import de.persoapp.core.paos.PAOSInitiator;
import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;
import de.persoapp.core.util.TLV;

/**
 * The <tt>Extended Access Protocol Handler</tt> 
 * 
 * @author Christian Kahlo
 */
public class EACProtocolHandler {
	
	/**
	 * The bundle to resolve necessary properties.
	 */
	private final PropertyResolver.Bundle	textBundle	= PropertyResolver.getBundle("text_core");

	/**
	 * The currently used <tt>CardHandler</tt>.
	 */
	private final ICardHandler				eCardHandler;
	
	/**
	 * The currently running instance of the <tt>PersoApp-Application</tt>.
	 */
	private final IMainView					mainView;
	
	/**
	 * The currently activ session.
	 */
	private final ECardSession				session;

	/**
	 * Creates and initializes a new {@link EACProtocolHandler}.
	 * @param session
	 */
	public EACProtocolHandler(final ECardSession session) {
		this.session = session;
		// this.eCardHandler = session.getCardHandler(slotHandle);
		this.eCardHandler = session.getCardHandler(null);

		// //IMainView mainView = session.getMainView(slotHandle);
		this.mainView = session.getMainView();
	}

	/**
	 * Informations follow in MR3
	 * 
	 * @param in
	 * @return
	 */
	public DIDAuthenticationDataType process(final DIDAuthenticationDataType in) {
		DIDAuthenticationDataType out = null;

		if (in instanceof EAC1InputType) {
			final EAC1InputType eac1in = (EAC1InputType) in;
			eCardHandler.reset();

			final List<byte[]> cvcerts = eac1in.getCertificate();

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
					final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

					final Certificate[] sourceCerts = (Certificate[]) session.getAttribute("SPServerCert");

					final PAOSInitiator pi = (PAOSInitiator) session.getAttribute("PAOSInitiator");
					final Certificate idpCert = pi.getPeerCertificate();
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
						for (final byte[] certHashe : certHashes) {
							if (ArrayTool.arrayequal(idpCertHash, certHashe)) {
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
			TransportProvider tp = null;
			byte[] resultCHAT = null;
			final String onlineAuthTitle = textBundle.get("SALService_online_auth_title");
			boolean mainViewOpen = false;

			while (true) {
				do {
					if ((tp = eCardHandler.getECard()) != null) {
						mainView.showMainDialog(eacInfo, eCardHandler.hasPACE(tp) > 0 ? IMainView.MODE_CHATONLY
								: IMainView.MODE_PIN_CHAT);
					} else {
						if (!mainViewOpen) {
							// @TODO: review magic constant
							mainView.showMainDialog(eacInfo, 0);
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
					final boolean wantQES = (optionalCHAT[optionalCHAT.length - 1] & 0x80) != 0;
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

			// send CA ref = no valid cert chain, don't send CA ref = chain complete

			eac1out.getCertificationAuthorityReference().addAll(eCardHandler.getCAReferences());
			out = eac1out;
		} else if (in instanceof EAC2InputType) {
			final EAC2InputType eac2in = (EAC2InputType) in;

			final EAC_Info eacInfo = (EAC_Info) session.getAttribute(EAC_Info.class.getName());

			final List<byte[]> cvcerts = new ArrayList<byte[]>();
			// final List<byte[]> cvcerts = eacInfo.getCertificateChain();

			cvcerts.addAll(eac2in.getCertificate());

			final byte[] ephemeralKey = eac2in.getEphemeralPublicKey();

			int i = -1;

			final List<String> verifiedCerts = new ArrayList<String>();
			for (final byte[] cvcert : cvcerts) {
				/*
				 * try { FileOutputStream fos = new
				 * FileOutputStream(System.getProperty("user.home") +
				 * "\\Desktop\\CVC" + i + "_" + caRef + "_" + chRef + ".cv");
				 * fos.write(Hex.fromString(value)); fos.close(); } catch
				 * (Exception e1) { e1.printStackTrace(); }
				 */

				i++;
				// @TODO: check with previous certificate or ignore

				String CAR = null, CHR = null, certID = null;
				try {
					final byte[] cvcert_ = TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E);
					CAR = new String(TLV.get(cvcert_, (byte) 0x42), "ISO-8859-1");
					CHR = new String(TLV.get(cvcert_, (short) 0x5F20), "ISO-8859-1");
					certID = CAR + "/" + CHR;
				} catch (final UnsupportedEncodingException e2) {
					e2.printStackTrace();
				}

				if (!verifiedCerts.contains(certID)) {
					System.out.println("CVC" + i + ": " + certID);
					System.out.println("CVC" + i + ": " + Hex.toString(cvcert));
					// eCard API 1.1.1
					if (eCardHandler.verifyCertificate(cvcert)) {
						verifiedCerts.add(certID);
					} else {
						System.out.println("WARNING: [" + certID + "] did not pass verification.");
					}
				}
			}

			mainView.showProgress(textBundle.get("SALService_progress_cert"), 50, true);

			eCardHandler.initTA(ephemeralKey, eacInfo.getAuxiliaryData());

			final EAC2OutputType eac2out = new EAC2OutputType();
			eac2out.setChallenge(eCardHandler.getTAChallenge());

			out = eac2out;
		} else if (in instanceof EACAdditionalInputType) {
			final EACAdditionalInputType eacAddin = (EACAdditionalInputType) in;

			if (!eCardHandler.verifyTASignature(eacAddin.getSignature())) {
				mainView.showError(textBundle.get("SALService_check_error_title"),
						textBundle.get("SALService_check_error_text"));
				eCardHandler.reset();
				return null;
			}

			final EAC2OutputType eac2out = new EAC2OutputType();
			eac2out.setEFCardSecurity(eCardHandler.getEFCardSecurity());

			final byte[] caData = eCardHandler.execCA();
			eac2out.setNonce(TLV.get(caData, (byte) 0x81));
			eac2out.setAuthenticationToken(TLV.get(caData, (byte) 0x82));

			mainView.showProgress(textBundle.get("SALService_progress_data"), 75, true);

			out = eac2out;
		} else {
			System.out.println("unknown: " + in.getClass().getName() + " = " + in);
		}

		return out;
	}

}
