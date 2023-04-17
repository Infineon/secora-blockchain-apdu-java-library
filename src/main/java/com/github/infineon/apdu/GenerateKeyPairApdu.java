
/**
 * MIT License
 *
 * Copyright (c) 2022 Infineon Technologies AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 *
 */
/**
 * This file was copied from https://github.com/Infineon/BlockchainSecurity2Go-Android
 * (sha1: 0d27d53) and modified accordingly.
 *
 * MIT License
 * Copyright (c) 2019 Coinfinity GmbH
 */
package com.github.infineon.apdu;

/**
 * @author Coinfinity, 2018
 */
public class GenerateKeyPairApdu extends BaseCommandApdu {

    /**
     * Instruction byte for GENERATE KEYPAIR operation.
     */
    private static final int INS_GENERATE_KEYPAIR = 0x02;

    /**
     * Curve index, identifying the Secp256k1 elliptic curve.
     */
    public static final int CURVE_INDEX_SECP256K1 = 0x00;

    /**
     * Constructs a GENERATE KEYPAIR command apdu.
     *
     * @param ellipticCurveIndex key id on card, 0x00 means default key
     */
    public GenerateKeyPairApdu(int ellipticCurveIndex) {
        if (ellipticCurveIndex > 0xFF) {
            throw new IllegalArgumentException("Curve index cannot be larger than 0xFF");
        }
        this.ins = INS_GENERATE_KEYPAIR;
        this.p1 = ellipticCurveIndex;
        this.leIncluded = true;
    }

}
