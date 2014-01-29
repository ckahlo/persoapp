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

public class EAC_Info implements IEAC_Info {
	private boolean				valid	= false;

	private final List<byte[]>	cvcerts;
	private byte[]				terminalCertificate;
	private final byte[]		terminalDescription;

	private final String		transactionInfo;
	private String				descriptionType;
	private String				issuerName;
	private String				issuerURL;
	private String				subjectName;
	private String				subjectURL;
	private String				termsOfUsage;
	private String				redirectURL;
	private byte[][]			certificateHashes;
	private Date				effectiveDate;
	private Date				expirationDate;

	private final long			reqCHAT, optCHAT;
	private String				caRef;
	private String				chRef;
	private final byte[]		auxData;

	private String				verifyCommunityID;

	private int					verifyAge;

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

	public static EAC_Info newInstance(final List<byte[]> cvcerts, final byte[] cvcertDescription,
			final String transactionInfo, final byte[] requiredCHAT, final byte[] optionalCHAT, final byte[] auxData) {
		try {
			return new EAC_Info(cvcerts, cvcertDescription, transactionInfo, requiredCHAT, optionalCHAT, auxData);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public final boolean isValid() {
		return this.valid;
	}

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

	//@Override
	public List<byte[]> getCertificateChain() {
		return this.cvcerts;
	}

	//@Override
	public byte[] getTerminalCertificate() {
		return this.terminalCertificate;
	}

	//@Override
	public byte[] getTerminalDescription() {
		return this.terminalDescription;
	}

	//@Override
	public String getAuthorityReference() {
		return this.caRef;
	}

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

	/**
	 * @return the verifyCommunityID
	 */
	@Override
	public String getVerifyCommunityID() {
		return verifyCommunityID;
	}

	/**
	 * @return the verifyAge
	 */
	@Override
	public long getVerifyAge() {
		return verifyAge;
	}
}
