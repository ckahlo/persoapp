/**
 *
 * COPYRIGHT (C) 2010, 2011, 2012, 2013, 2014 AGETO Innovation GmbH
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
package de.persoapp.core.client;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import de.persoapp.core.util.ArrayTool;
import de.persoapp.core.util.Hex;
import de.persoapp.core.util.TLV;

/**
 * The <tt>EAC_Info</tt> contains all informations about the initialized
 * certificate and the data for the <em>eID</em>-process of the user.
 * <p>
 * <code>public class EAC_Info implements IEAC_Info<code>
 * </p>
 * 
 * @author Christian Kahlo, Ralf Wondratschek
 * @author Rico Klimsa - added javadoc comments.
 */
public class EAC_Info implements IEAC_Info {
	
	/**
	 * Shows the validity of the certificate informations.
	 */
	private boolean				valid	= false;

	/**
	 * The currently used cv certificates.
	 */
	private final List<byte[]>	cvcerts;
	
	/**
	 * The certificate of a terminal.
	 */
	private byte[]				terminalCertificate;
	
	/**
	 * The description of a terminal.
	 */
	private final byte[]		terminalDescription;

	/**
	 * The informations about the current transaction.
	 */
	private final String		transactionInfo;
	
	/**
	 * The type of the current description.
	 */
	private String				descriptionType;
	
	/**
	 * The name of the current issuer.
	 */
	private String				issuerName;
	
	/**
	 * The URL of the current issuer.
	 */
	private String				issuerURL;

	/**
	 * The name of the current subject.
	 */
	private String				subjectName;
	
	/**
	 * The URL of the current subject.
	 */
	private String				subjectURL;
	
	/**
	 * The terms of usage.
	 */
	private String				termsOfUsage;
	
	/**
	 * The URL to redirect.
	 */
	private String				redirectURL;
	
	/**
	 * The defined certificate hashes.
	 */
	private byte[][]			certificateHashes;
	
	/**
	 * The current date.
	 */
	private Date				effectiveDate;
	
	/**
	 * The date of expiration.
	 */
	private Date				expirationDate;

	/**
	 * The chats for marking the personal data which is needed.
	 */
	private final long			reqCHAT, optCHAT;
	
	/**
	 * The certificate authority reference.
	 */
	private String				caRef;
	
	/**
	 * The certificate handler reference.
	 */
	private String				chRef;
	
	/**
	 * The auxiliary data.
	 */
	private final byte[]		auxData;

	/**
	 * The verified community id.
	 */
	private String				verifyCommunityID;

	/**
	 * The date of the verify action.
	 */
	private int					verifyAge;

	/**
	 * Creates and initializes a new {@link EAC_Info} instance. The received
	 * data from the gui-dialogs is transformed in a readable byteform.
	 * 
	 * @param cvcerts 
	 * @param cvcertDescription
	 * @param transactionInfo
	 * @param requiredCHAT
	 * @param optionalCHAT
	 * @param auxData
	 * @throws IOException
	 */
	private EAC_Info(final List<byte[]> cvcerts, final byte[] cvcertDescription, final String transactionInfo,
			final byte[] requiredCHAT, final byte[] optionalCHAT, final byte[] auxData) throws IOException {
		this.cvcerts = cvcerts;

		for (final byte[] cvcert : cvcerts) {
			final byte[] chat = TLV.get(TLV.get(TLV.get(cvcert, (short) 0x7F21), (short) 0x7F4E), (short) 0x7F4C);
			final byte[] oid = TLV.get(chat, (byte) 0x06); // IS, AT, ST, etc.
			final byte[] auth = TLV.get(chat, (byte) 0x53);

			if (auth != null && (auth[0] & (byte) 0xC0) == 0x00) {
				this.terminalCertificate = cvcert;
				break;
			}
		}

		if (this.terminalCertificate == null) {
			throw new IllegalStateException();
		}

		this.terminalDescription = cvcertDescription;
		initDescription(this.terminalCertificate, this.terminalDescription);
		this.transactionInfo = transactionInfo;
		this.reqCHAT = initCHAT(requiredCHAT);
		this.optCHAT = initCHAT(optionalCHAT);

		this.auxData = auxData;
		initAuxData(this.auxData);
	}

	/**
	 * Creates a new instance of the {@link EAC_Info} with the given parameters.
	 * 
	 * @param cvcerts
	 * @param cvcertDescription
	 * @param transactionInfo
	 * @param requiredCHAT
	 * @param optionalCHAT
	 * @param auxData
	 * @return
	 */
	public static EAC_Info newInstance(final List<byte[]> cvcerts, final byte[] cvcertDescription,
			final String transactionInfo, final byte[] requiredCHAT, final byte[] optionalCHAT, final byte[] auxData) {
		try {
			return new EAC_Info(cvcerts, cvcertDescription, transactionInfo, requiredCHAT, optionalCHAT, auxData);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns <strong>true</strong>, if the informations of is valid. Otherwise the
	 * function returns <strong>false</strong>.
	 * 
	 * @return Returns <strong>true</strong>, if the certificate is valid.
	 *         Otherwise the function returns <strong>false</strong>.
	 */
	public final boolean isValid() {
		return this.valid;
	}

	/**
	 * Converts the certificate date in a regular date object.
	 * 
	 * @param cvDate
	 *            - The date, to convert.
	 * @param endOfDay
	 *            - Set to <tt>true</tt> if the date should set to the end of
	 *            date, otherwise <tt>false</tt>.
	 * @return Returns the retrieved date of the certificate.
	 */
	private final Date cvDate2Date(final byte[] cvDate, final boolean endOfDay) {
		final GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.set(Calendar.YEAR, 2000 + cvDate[0] * 10 + cvDate[1]);
		cal.set(Calendar.MONTH, -1 + cvDate[2] * 10 + cvDate[3]);
		cal.set(Calendar.DAY_OF_MONTH, cvDate[4] * 10 + cvDate[5]);
		cal.set(Calendar.HOUR_OF_DAY, endOfDay ? 23 : 0);
		cal.set(Calendar.MINUTE, endOfDay ? 59 : 0);
		cal.set(Calendar.SECOND, endOfDay ? 59 : 0);
		cal.set(Calendar.MILLISECOND, endOfDay ? 999 : 0);
		return cal.getTime();
	}

	/**
	 * Initializes the auxiliary data.
	 * 
	 * @param auxData
	 *            - The auxiliary data, to initialize.
	 */
	private final void initAuxData(final byte[] auxData) {
		if (auxData == null) {
			return;
		}

		final byte[] ad = TLV.get(auxData, (byte) 0x67);

		// no valid auxData Tag
		if (ad == null) {
			return;
		}

		final List<byte[]> adList = TLV.getM(ad, (byte) 0x73);

		// no data objects
		if (adList == null) {
			return;
		}

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		for (final byte[] data : adList) {
			final byte[] oid = TLV.get(data, (byte) 0x06);
			final byte[] value = TLV.get(data, (byte) 0x53);
			System.out.println("auxData: " + Hex.toString(oid) + " = " + Hex.toString(value));

			if ("04007F000703010401".equals(Hex.toString(oid))) {
				try {
					// kinda dirty hack, but it does it's job quite well ...
					final GregorianCalendar cal = new GregorianCalendar();
					cal.setLenient(true);
					cal.clear();
					final Calendar clone = (Calendar) cal.clone();
					final long diff = new Date().getTime() - sdf.parse(new String(value, "ISO-8859-1")).getTime();
					cal.set(Calendar.DAY_OF_YEAR, (int) (diff / (1000L * 60 * 60 * 24)));

					this.verifyAge = cal.get(Calendar.YEAR) - clone.get(Calendar.YEAR);
					System.out.println("verify Age: " + this.verifyAge);
				} catch (final Exception e) {
					e.printStackTrace();
				}
			} else if ("04007F000703010403".equals(Hex.toString(oid))) {
				this.verifyCommunityID = Hex.toString(value);
				System.out.println("verify CommunityID: " + this.verifyCommunityID);
			}
		}
	}

	/**
	 * Initializes the certificate description, of the given certificate.
	 * 
	 * @param cvcert - The given certificate.
	 * @param certDescription - The certificate description.
	 * 
	 * @throws IOException If an error occurs during the initialization.
	 */
	private final void initDescription(byte[] cvcert, final byte[] certDescription) throws IOException {
		cvcert = TLV.get(cvcert, (short) 0x7F21);
		cvcert = TLV.get(cvcert, (short) 0x7F4E);
		this.caRef = new String(TLV.get(cvcert, (byte) 0x42), "ISO-8859-1");
		this.chRef = new String(TLV.get(cvcert, (short) 0x5F20), "ISO-8859-1");
		this.effectiveDate = cvDate2Date(TLV.get(cvcert, (short) 0x5F25), false);
		this.expirationDate = cvDate2Date(TLV.get(cvcert, (short) 0x5F24), true);

		final byte[] t = TLV.get(certDescription, (byte) 0x30);
		this.descriptionType = Hex.toString(TLV.get(t, (byte) 0x06));

		this.issuerName = TLV.getString(TLV.get(t, (byte) 0xA1));
		this.issuerURL = TLV.getString(TLV.get(t, (byte) 0xA2));
		this.subjectName = TLV.getString(TLV.get(t, (byte) 0xA3));
		this.subjectURL = TLV.getString(TLV.get(t, (byte) 0xA4));
		this.termsOfUsage = TLV.getString(TLV.get(t, (byte) 0xA5));
		this.redirectURL = TLV.getString(TLV.get(t, (byte) 0xA6));
		// set (0x31) of octet strings
		this.certificateHashes = TLV.getM(TLV.get(TLV.get(t, (byte) 0xA7), (byte) 0x31), (byte) 0x04).toArray(
				new byte[0][]);

		byte[] descriptionHash = null;

		final byte[] extensions = TLV.get(cvcert, (byte) 0x65);
		final List<byte[]> list = TLV.getM(extensions, (byte) 0x73);
		for (final byte[] ext : list) {
			final byte[] oid = TLV.get(ext, (byte) 0x06);
			final byte[] value = TLV.get(ext, (byte) 0x80);
			if ("04007F000703010302".equals(Hex.toString(oid))) {
				System.out.println("sector-hash: " + Hex.toString(value));
			} else if ("04007F000703010301".equals(Hex.toString(oid))) {
				descriptionHash = value;
				System.out.println("desc-hash: " + Hex.toString(descriptionHash));
			} else {
				System.out.println("unknown: " + Hex.toString(oid) + " = " + Hex.toString(value));
			}
		}

		try {
			final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			final byte[] d_h = sha256.digest(certDescription);
			if (ArrayTool.arrayequal(descriptionHash, d_h)) {
				valid = true;
			} else {
				System.out.println("ERROR description hash different from cert: " + Hex.toString(d_h));
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		final Date current = new Date();
		if (!(this.effectiveDate.before(current) && this.expirationDate.after(current))) {
			valid = false;
		}
	}

	/**
	 * Initializes the chat through the use of the original chat, which contains
	 * inserted values. Returns <strong>zero</strong> if the <em>origCHAT</em>
	 * is <strong>null</strong>.
	 * 
	 * @param origCHAT
	 *            - The original marked personal data. Can be <strong>null</strong>.
	 * 
	 * @return Returns <strong>zero</strong> if <em>origChat</em> is set to
	 *         <strong>null</strong>.
	 */
	private int initCHAT(final byte[] origCHAT) {
		int result = 0;

		if (origCHAT != null) {
			final byte[] chat = ArrayTool.subArray(origCHAT, origCHAT.length - 5, 5);

			for (int i = 0; i < chat.length; i++) {
				result += (chat[i] & 0xFF) << 8 * (chat.length - 1 - i);
			}
		}

		return result;
	}

	/**
	 * Returns the certificate chain.
	 * 
	 * @return Returns the certificate chain.
	 */
	//@Override
	public List<byte[]> getCertificateChain() {
		return this.cvcerts;
	}

	/**
	 * Returns the currently used terminal certificate.
	 * 
	 * @return Returns the currently used terminal certificate.
	 */
	//@Override
	public byte[] getTerminalCertificate() {
		return this.terminalCertificate;
	}

	/**
	 * Returns the terminal description.
	 * 
	 * @return Returns the terminal description.
	 */
	//@Override
	public byte[] getTerminalDescription() {
		return this.terminalDescription;
	}

	/**
	 * Returns the certificate authority reference.
	 * 
	 * @return Returns the certificate authority reference.
	 */
	//@Override
	public String getAuthorityReference() {
		return this.caRef;
	}

	/**
	 * Returns the certificate holder reference.
	 * 
	 * @return Returns the certificate holder reference.
	 */
	//@Override
	public String getHolderReference() {
		return this.chRef;
	}

	@Override
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	@Override
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	@Override
	public String getTransactionInfo() {
		return this.transactionInfo;
	}

	@Override
	public String getDescriptionType() {
		return this.descriptionType;
	}

	@Override
	public String getIssuerName() {
		return this.issuerName;
	}

	@Override
	public String getIssuerURL() {
		return this.issuerURL;
	}

	@Override
	public String getSubjectName() {
		return this.subjectName;
	}

	@Override
	public String getSubjectURL() {
		return this.subjectURL;
	}

	@Override
	public String getTermsOfUsage() {
		return this.termsOfUsage;
	}

	@Override
	public String getRedirectURL() {
		return this.redirectURL;
	}
	
	/**
	 * Returns the certificate hashes.
	 * 
	 * @return Returns the certificate hashes.
	 */
	//@Override
	public byte[][] getCertificateHashes() {
		return this.certificateHashes;
	}

	@Override
	public long getRequiredChat() {
		return reqCHAT;
	}

	@Override
	public long getOptionalChat() {
		return optCHAT;
	}

	@Override
	public byte[] getAuxiliaryData() {
		return auxData;
	}

	@Override
	public String getVerifyCommunityID() {
		return verifyCommunityID;
	}

	@Override
	public long getVerifyAge() {
		return verifyAge;
	}
}
