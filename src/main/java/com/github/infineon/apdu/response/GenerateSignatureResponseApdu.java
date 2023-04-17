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
package com.github.infineon.apdu.response;

import java.math.BigInteger;

/**
 * Response APDU of GENERATE SIGNATURE as received from the card.
 */
public class GenerateSignatureResponseApdu extends ResponseApdu {

    /**
     * Global signature counter section of response APDU
     */
    protected byte[] globalSigCounter = new byte[4];

    /**
     * Signature counter section of response APDU
     */
    protected byte[] sigCounter = new byte[4];

    /**
     * Signature section of response APDU
     */
    protected byte[] signature;

    /**
     * Constructor from raw bytes
     *
     * @param respApdu raw bytes as transceived from NFC card
     */
    public GenerateSignatureResponseApdu(byte[] respApdu) {
        super(respApdu);
        signature = new byte[respApdu.length - globalSigCounter.length - sigCounter.length];
        for (int i = 0; i < respApdu.length; i++) {
            if (i < globalSigCounter.length) {
                globalSigCounter[i] = respApdu[i];
            } else if (i < globalSigCounter.length + sigCounter.length) {
                sigCounter[i - globalSigCounter.length] = respApdu[i];
            } else if (i < globalSigCounter.length + sigCounter.length + signature.length) {
                signature[i - globalSigCounter.length - sigCounter.length] = respApdu[i];
            }
        }
    }

    public byte[] getGlobalSigCounter() {
        return globalSigCounter;
    }

    public int getGlobalSigCounterAsInteger() {
        return new BigInteger(globalSigCounter).intValue();
    }

    public byte[] getSigCounter() {
        return sigCounter;
    }

    public int getSigCounterAsInteger() {
        return new BigInteger(sigCounter).intValue();
    }

    public byte[] getSignature() {
        return signature;
    }
}
