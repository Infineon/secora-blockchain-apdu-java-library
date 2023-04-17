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
 * @author Johannes Zweng on 02.10.18.
 */
public class GenerateSignatureApdu extends BaseCommandApdu {

    /**
     * Instruction byte for GENERATE SIGNATURE operation
     */
    private static final int INS_GENERATE_SIGNATURE = 0x18;


    /**
     * No hashing is done on card, data is already hashed externally
     * and transmitted as hash into the card.
     *
     * Currently no other mode documented. More modes may follow..
     */
    private static final int P2_DATA_ALREADY_PREHASHED = 0x00;


    /**
     * Constructs a GENERATE SIGNATURE apdu.
     *
     * @param keyIndex   key to use, 0x00 means default key
     * @param dataToSign data to be signed
     */
    public GenerateSignatureApdu(int keyIndex, byte[] dataToSign) {
        this.ins = INS_GENERATE_SIGNATURE;
        this.setData(dataToSign);
        this.p1 = keyIndex;
        this.p2 = P2_DATA_ALREADY_PREHASHED;
        this.leIncluded = true;
    }

}
