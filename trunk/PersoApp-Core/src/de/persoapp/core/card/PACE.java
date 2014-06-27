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
package de.persoapp.core.card;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Random;

import de.persoapp.core.util.ArrayTool;

// TODO: Auto-generated Javadoc
/**
 * A small, self-contained PACE(
 * <tt>Password Authenticated Connection Establishment</tt>) implementation
 * using BigInteger. Quite a bit an experiment based on the fast-ECDSA-Signer
 * code using a x86-native replacement for BigInteger. The internal functions
 * encode a ephemeral key as a elliptic curve point diffie hellmann (ECDH),
 * according to ISO 7816.
 * <p>
 * <code>public class PACE</code>
 * </p>
 * 
 * XXX: Might be replaced with standard-BouncyCastle code later.
 * 
 * @author Christian Kahlo
 * @author Rico Klimsa - added javadoc comments.
 */
public class PACE {
	
	/**
	 * The BigInteger constant <tt>ZERO</tt>, which is necessary for encryption and decryption.
	 */
	private static final BigInteger		ZERO		= BigInteger.ZERO;
	
	/**
	 * The BigInteger constant <tt>ONE</tt>, which is necessary for encryption and decryption.
	 */
	private static final BigInteger		ONE			= BigInteger.ONE;
	
	/**
	 * The BigInteger constant <tt>INFINITY</tt>, which is necessary for encryption and decryption.
	 */
	private static final BigInteger[]	INFINITY	= new BigInteger[] { null, null, null };

	/**
	 * A pseudorandom number generator.
	 */
	private Random						random;

	/** The q. */
	private final BigInteger			Q;
	
	/** The a. */
	private final BigInteger			A;
	
	/** The pre comp. */
	private final BigInteger[][]		preComp;

	/**
	 * The value of <tt>nonce</tt>.
	 */
	private final BigInteger			nonce;

	/** The ephemeral key. */
	private BigInteger					ephemeralKey;

	/**
	 * Initializes a creation of the <tt>ECDH</tt> according to the given
	 * <tt>ecSpec</tt>.
	 * 
	 * @param ecSpec
	 *            - The given elliptic curve specifications.
	 * @param nonce
	 *            - The nonce-value.
	 */
	public PACE(final ECParameterSpec ecSpec, final BigInteger nonce) {
		try {
			this.random = SecureRandom.getInstance("SHA1PRNG");
		} catch (final Exception e) {
			e.printStackTrace();
			this.random = new SecureRandom();
		}

		final EllipticCurve curve = ecSpec.getCurve();
		this.Q = ((ECFieldFp) curve.getField()).getP();
		this.A = curve.getA();

		this.preComp = new BigInteger[16][];
		final ECPoint G = ecSpec.getGenerator();
		this.preComp[0] = new BigInteger[] { G.getAffineX(), G.getAffineY(), ONE };
		preComputation(this.preComp);

		this.nonce = nonce;
	}

	/**
	 * Precomputes <tt>pcData</tt> to achieve higher calculation speed of the
	 * <tt>ECDH</tt>.
	 * 
	 * @param pcData
	 *            - The data to precompute.
	 */
	private final void preComputation(final BigInteger[][] pcData) {
		final BigInteger[] Ptwice = multiplyBy2NEW(pcData[0]);
		for (int i = 1; i < pcData.length; i++) {
			pcData[i] = add(Ptwice, pcData[i - 1]);
		}
	}
	
	/**
	 * Creates and returns a new private key.
	 * 
	 * @return Creates and returns a new private key.
	 */
	private BigInteger createPrivateKey() {
		final byte[] keyArray = new byte[(Q.bitLength() + 7) / 8];
		random.nextBytes(keyArray);
		return new BigInteger(1, keyArray);
	}

	/**
	 * Reduces the given <tt>BigInteger</tt> and returns it as a
	 * <tt>byte array</tt>. Deletes the MSB, if it is set to <em>zero</em>.
	 * 
	 * @param i
	 *            - The <tt>BigInteger</tt> which is about to be reduced. Can't
	 *            be <strong>null</strong>.
	 * 
	 * @return returns the reduced BigInteger.
	 */
	private byte[] reduceBigInt(final BigInteger i) {
		byte[] result = i.toByteArray();
		while (result[0] == 0) {
			result = ArrayTool.subArray(result, 1, result.length - 1);
		}
		return result;
	}

	/**
	 * Encodes the point, which is identified through the parameter <em>p</em>
	 * and returns it as a <tt>byte array</tt>.
	 * 
	 * @param p
	 *            - The array of <tt>BigInteger</tt> values, which needs to be encoded. Can't be
	 *            <strong>null</strong>.
	 * 
	 * @return The encoded point as a <tt>byte array</tt>.
	 */
	private byte[] encodePoint(final BigInteger[] p) {
		return ArrayTool
				.arrayconcat(new byte[] { 0x04 }, ArrayTool.arrayconcat(reduceBigInt(p[0]), reduceBigInt(p[1])));
	}

	/**
	 * Decodes the given <tt>byte array</tt> to a array of <tt>BigInteger</tt>
	 * -values.
	 * 
	 * @param in
	 *            - The <tt>byte array</tt>, which needs to be encoded to
	 *            identify a point.
	 * @return Returns the decoded array of <tt>BigInteger</tt> values.
	 */
	private BigInteger[] decodePoint(final byte[] in) {
		if (in[0] != 0x04) {
			return null;
		}

		final int xyLen = in.length - 1 >> 1;
		return new BigInteger[] { new BigInteger(1, ArrayTool.subArray(in, 1, xyLen)),
				new BigInteger(1, ArrayTool.subArray(in, 1 + xyLen, xyLen)), ONE };
	}
	
	/**
	 * Creates a <tt>ephemeral Key</tt> and returns the.
	 *
	 * @return Returns the public key of key-pair on base-curve from card
	 */
	public final byte[] init() {
		this.ephemeralKey = createPrivateKey();
		return encodePoint(this.fastMultiply(this.ephemeralKey));
	}

	/**
	 * Proceeds with the creation of the ECDH. Calculates the public point
	 * relative to 'G'.
	 * 
	 * @param mapData
	 *            - The mapping function.
	 * 
	 * @return Retrieve public point relative to 'G'.
	 */
	public final byte[] step(final byte[] mapData) {
		final BigInteger[] G_ = fastMultiply(this.nonce);

		this.preComp[0] = decodePoint(mapData);
		preComputation(this.preComp);

		final BigInteger[] H = fastMultiply(this.ephemeralKey);

		this.preComp[0] = add(G_, H);
		preComputation(this.preComp);

		this.ephemeralKey = createPrivateKey();
		return encodePoint(this.fastMultiply(this.ephemeralKey));
	}

	/**
	 * Finishes the creation of a ECDH and returns the created curve as a two
	 * dimensional array of bytes.
	 * 
	 * @param mapData
	 *            - The mapping function.
	 * @return Returns the created ECDH.
	 */
	public final byte[][] finish(final byte[] mapData) {
		this.preComp[0] = decodePoint(mapData);
		preComputation(this.preComp);

		final BigInteger[] S = fastMultiply(this.ephemeralKey);

		return new byte[][] { reduceBigInt(this.preComp[0][0]), // IDPICC
				reduceBigInt(S[0]) // shared secret
		};
	}

	/*
	 * The following implementation uses modified Jacobian coordinates somewhat
	 * inspired from
	 * http://point-at-infinity.org/ecc/Prime_Curve_Standard_Projective_Coordinates
	 * .html
	 * 
	 * but without returning aZ'^4, a point is a therefore a triple, not a
	 * quadruple. aZ^4 is calculated because this couple of multiplications
	 * turned out to be quite fast.
	 */

	/**
	 * Determines the non adjacent form. The non-adjacent form (NAF) of a number
	 * is a unique signed-digit representation. Like the name suggests, non-zero
	 * values cannot be adjacent.
	 * 
	 * @param e
	 *            - The number, which non adjacent form is requested.
	 * @param w
	 *            - The <em>w</em> value.
	 * @param b
	 *            - The bitlength of <em>e</em>.
	 * 
	 * @return Returns the non adjacent form
	 */
	private static int[] determineNaf(final BigInteger e, final int w, final int b) {
		final int power2wi = 1 << w;
		int j, u;
		final int[] N = new int[b + 1];
		BigInteger c = e.abs();
		final int s = e.signum();

		j = 0;
		while (c.compareTo(BigInteger.ZERO) > 0) {
			if (c.testBit(0)) {
				u = c.intValue() & (power2wi << 1) - 1;
				if ((u & power2wi) != 0) {
					u = u - (power2wi << 1);
				}

				c = c.subtract(BigInteger.valueOf(u));
			} else {
				u = 0;
			}

			N[j++] = s > 0 ? u : -u;
			c = c.shiftRight(1);
		}

		// fill with zeros
		while (j <= b) {
			N[j++] = 0;
		}

		return N;
	}

	/**
	 * Multiplies the parameter <em>k</em> with the <tt>Naf</tt> determined by
	 * the function <tt>determineNaf</tt>. Returns the result after the multiply
	 * has finished.
	 * 
	 * @param k
	 *            - The BigInteger, which has to be multiplied.
	 * 
	 * @return Returns the Result as a <tt>array</tt> of <tt>BigInteger</tt>
	 *         values.
	 */
	public BigInteger[] fastMultiply(final BigInteger k) {
		final int[] N = determineNaf(k, 5, k.bitLength());

		BigInteger[] r = INFINITY;
		final int l = N.length - 1;
		for (int i = l; i >= 0; i--) {
			r = multiplyBy2NEW(r);
			final int index = N[i];
			if (index > 0) {
				r = add(r, this.preComp[index - 1 >> 1]);
			} else if (index < 0) {
				r = subtract(r, this.preComp[-index - 1 >> 1]);
			}
		}

		BigInteger x = r[0], y = r[1], z = r[2];

		z = z.modInverse(Q);
		final BigInteger z2 = z.multiply(z);
		z = z.multiply(z2);
		x = x.multiply(z2);
		y = y.multiply(z);

		// coordinates are affine, that's why Z = 1
		return new BigInteger[] { x.mod(Q), y.mod(Q), ONE };
	}

	/**
	 * Adds the two points together and returns the result.
	 * 
	 * @param a
	 *            - The first point. Must be three digits long. Can't be
	 *            <strong>null</strong>.
	 * @param b
	 *            - The second point. Must be three digits long. Can't be
	 *            <strong>null</strong>.
	 * @return The combined added value.
	 */
	private BigInteger[] add(final BigInteger[] a, final BigInteger[] b) {
		if (a[0] == null && a[1] == null) {
			return b;
		}
		if (b[0] == null && b[1] == null) {
			return a;
		}

		final BigInteger aX = a[0], aY = a[1], aZ = a[2];
		final BigInteger bX = b[0], bY = b[1], bZ = b[2];

		BigInteger U1, S1, U2, S2;

		// point B is affine
		if (bZ.equals(ONE)) {
			// U1 = aX*bZ^2
			U1 = aX;
			// S1 = aY*bZ^3
			S1 = aY;
		} else {
			final BigInteger bZ2 = bZ.multiply(bZ).mod(Q);
			// U1 = aX*bZ^2
			U1 = aX.multiply(bZ2).mod(Q);
			// S1 = aY*bZ^3
			S1 = aY.multiply(bZ2.multiply(bZ).mod(Q)).mod(Q);
		}

		// point A is affine
		if (aZ.equals(ONE)) {
			// U2 = bX*aZ^2
			U2 = bX;
			// S2 = bY*aZ^3
			S2 = bY;
		} else {
			final BigInteger aZ2 = aZ.multiply(aZ).mod(Q);
			// U2 = bX*aZ^2
			U2 = bX.multiply(aZ2).mod(Q);
			// S2 = bY*aZ^3
			S2 = bY.multiply(aZ2.multiply(aZ).mod(Q)).mod(Q);
		}

		// H = bX*aZ^2 - aX*bZ^2
		final BigInteger H = U2.subtract(U1);

		// r = bY*aZ^3 - aY*bZ^3
		final BigInteger r = S2.subtract(S1);
		if (H.equals(ZERO)) {
			if (r.equals(ZERO)) {
				return multiplyBy2NEW(a);
			}
			return INFINITY;
		}

		// U2 = (bX*aZ^2 - aX*bZ^2)^2
		U2 = H.multiply(H).mod(Q);

		// S2 = (bX*aZ^2 - aX*bZ^2)^3
		S2 = U2.multiply(H).mod(Q);

		// U2 = U1H^2
		U2 = U2.multiply(U1).mod(Q);

		// x = r^2 - S2 - 2U2
		final BigInteger x = r.multiply(r).mod(Q).subtract(S2).subtract(U2.add(U2));

		// y = r(U2 - x) - S1S2
		final BigInteger y = r.multiply(U2.subtract(x)).mod(Q).subtract(S1.multiply(S2).mod(Q));

		BigInteger z;

		// z = Z1Z2H
		// point A is affine
		if (aZ.equals(ONE)) {
			// point B is not affine
			if (!bZ.equals(ONE)) {
				z = bZ.multiply(H);
			} else {
				// both are affine
				z = H;
			}
		} else if (!bZ.equals(ONE)) {
			U1 = aZ.multiply(bZ).mod(Q);
			z = U1.multiply(H);
		} else {
			z = aZ.multiply(H);
		}

		return new BigInteger[] { x.mod(Q), y.mod(Q), z.mod(Q) };
	}
	
	/**
	 * Further informations follow in MR3.
	 * 
	 * @param a
	 *            - The first <tt>BigInteger</tt>. Must be three digits long.
	 *            Can't be <strong>null</strong>.
	 * @param b
	 *            - The second <tt>BigInteger</tt>. Must be three digits long.
	 *            Can't be <strong>null</strong>.
	 * 
	 * @return The calculated array.
	 */
	private BigInteger[] subtract(final BigInteger[] a, final BigInteger[] b) {
		return add(a, new BigInteger[] { b[0], b[1].negate(), b[2] });
	}

	/**
	 * Further informations follow in MR3.
	 */
	public BigInteger[] multiplyBy2NEW(final BigInteger[] p) {
		final BigInteger pX = p[0], pY = p[1], pZ = p[2];

		if (pX == null && pY == null) { // is infinity
			// Twice identity element (point at infinity) is identity
			return p;
		}

		if (pY.signum() == 0) {
			// if y1 == 0, then (x1, y1) == (x1, -y1)
			// and hence this = -this and thus 2(x1, y1) == infinity
			return INFINITY; // that's infinity
		}

		// z = Y^2
		BigInteger z = pY.multiply(pY).mod(Q);

		// S = 4XY^2
		BigInteger S = pX.multiply(z).mod(Q);
		S = S.add(S);
		S = S.add(S);

		// M = 3X^2 + a(Z^2)^2
		BigInteger pAZ4 = A;
		// point is not affine
		if (!pZ.equals(ONE)) {
			pAZ4 = pZ.multiply(pZ).mod(Q);
			pAZ4 = A.multiply(pAZ4.multiply(pAZ4).mod(Q)).mod(Q);
		}

		BigInteger y = pX.multiply(pX).mod(Q);

		final BigInteger M = y.add(y).add(y).add(pAZ4); // 3X^2+aZ^4

		// T = x = -2S + M^2
		final BigInteger x = M.multiply(M).mod(Q).subtract(S.add(S));

		// y = -8Y^4 + M(S - T)
		y = z.multiply(z).mod(Q);
		BigInteger U = y.add(y); // 2Y^4
		z = U.add(U);
		U = z.add(z); // 8Y^4
		y = M.multiply(S.subtract(x)).mod(Q).subtract(U);

		// Z = 2Y
		if (!pZ.equals(ONE)) {
			z = pY.multiply(pZ).mod(Q);
		} else {
			z = pY;
		}
		z = z.add(z);

		return new BigInteger[] { x.mod(Q), y.mod(Q), z.mod(Q) };
	}
}
