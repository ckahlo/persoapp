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
package de.persoapp.core.card;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Random;

import de.persoapp.core.util.ArrayTool;

/**
 * A small, self-contained PACE implementation using BigInteger. Quite a bit an
 * experiment based on the fast-ECDSA-Signer code using a x86-native replacement
 * for BigInteger.
 * 
 * XXX: Might be replaced with standard-BouncyCastle code later.
 * 
 * @author ckahlo
 * 
 */
public class PACE {
	private static final BigInteger		ZERO		= BigInteger.ZERO;
	private static final BigInteger		ONE			= BigInteger.ONE;
	private static final BigInteger[]	INFINITY	= new BigInteger[] { null, null, null };

	private Random						random;

	private final BigInteger			Q;
	private final BigInteger			A;
	private final BigInteger[][]		preComp;

	private final BigInteger			nonce;

	private BigInteger					ephemeralKey;

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

	private final void preComputation(final BigInteger[][] pcData) {
		final BigInteger[] Ptwice = multiplyBy2NEW(pcData[0]);
		for (int i = 1; i < pcData.length; i++) {
			pcData[i] = add(Ptwice, pcData[i - 1]);
		}
	}

	private BigInteger createPrivateKey() {
		final byte[] keyArray = new byte[(Q.bitLength() + 7) / 8];
		random.nextBytes(keyArray);
		return new BigInteger(1, keyArray);
	}

	private byte[] reduceBigInt(final BigInteger i) {
		byte[] result = i.toByteArray();
		while (result[0] == 0) {
			result = ArrayTool.subArray(result, 1, result.length - 1);
		}
		return result;
	}

	private byte[] encodePoint(final BigInteger[] p) {
		return ArrayTool
				.arrayconcat(new byte[] { 0x04 }, ArrayTool.arrayconcat(reduceBigInt(p[0]), reduceBigInt(p[1])));
	}

	private BigInteger[] decodePoint(final byte[] in) {
		if (in[0] != 0x04) {
			return null;
		}

		final int xyLen = in.length - 1 >> 1;
		return new BigInteger[] { new BigInteger(1, ArrayTool.subArray(in, 1, xyLen)),
				new BigInteger(1, ArrayTool.subArray(in, 1 + xyLen, xyLen)), ONE };
	}

	public final byte[] init() {
		this.ephemeralKey = createPrivateKey();
		return encodePoint(this.fastMultiply(this.ephemeralKey));
	}

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

	private BigInteger[] subtract(final BigInteger[] a, final BigInteger[] b) {
		return add(a, new BigInteger[] { b[0], b[1].negate(), b[2] });
	}

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
