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

import static com.github.infineon.ByteUtils.bytesToHex;

/**
 * Response APDU as received from the card.
 */
public class ResponseApdu {


    public static final int SW_SUCCESS = 0x9000;
    public static final int SW_KEY_WITH_IDX_NOT_AVAILABLE = 0x6A88;
    public static final int SW_SUCCESS_WITH_RESPONSE = 0x61;

    // According to ISO 7816-4 last two bytes are the "status words" (SW1 and SW2)
    /**
     * SW1 byte
     */
    protected int sw1 = 0x00;

    /**
     * SW2 byte
     */
    protected int sw2 = 0x00;

    /**
     * Data section of response APDU
     */
    protected byte[] data = new byte[0];

    /**
     * Raw byte representation of this APDU as received over NFC
     */
    protected byte[] apduBytes;


    /**
     * Constructor from raw bytes
     *
     * @param respApdu raw bytes as transceived from NFC card
     */
    public ResponseApdu(byte[] respApdu) {
        if (respApdu.length < 2) {
            throw new IllegalArgumentException("Illegal Response APDU. length < 2");
        }
        if (respApdu.length > 2) {
            data = new byte[respApdu.length - 2];
            System.arraycopy(respApdu, 0, data, 0, respApdu.length - 2);
        }
        sw1 = 0xFF & respApdu[respApdu.length - 2];
        sw2 = 0xFF & respApdu[respApdu.length - 1];
        apduBytes = respApdu;
    }

    /**
     * @return SW1 byte
     */
    public int getSW1() {
        return sw1;
    }

    /**
     * @return SW2 byte
     */
    public int getSW2() {
        return sw2;
    }

    /**
     * @return SW1 and SW2 interpreted as one integer
     */
    public int getSW1SW2() {
        return (sw1 << 8) | sw2;
    }

    /**
     * @return SW1 and SW2 interpreted as one integer
     */
    public String getSW1SW2HexString() {
        return bytesToHex(new byte[]{(byte) getSW1(), (byte) getSW2()});
    }

    /**
     * @return data part of the response APDU
     */
    public byte[] getData() {
        return data;
    }


    /**
     * @return data part of the response APDU (as hex string)
     */
    public String getDataAsHex() {
        return bytesToHex(data);
    }

    /**
     * Get raw byte representation of this APDU
     *
     * @return raw apdu bytes
     */
    public byte[] toBytes() {
        return apduBytes;
    }

    /**
     * @return true if the Status words indicated Success, false otherwise
     */
    public boolean isSuccess() {
        return getSW1SW2() == SW_SUCCESS || getSW1() == SW_SUCCESS_WITH_RESPONSE;
    }

    /**
     * @return the APDU's bytes as hexadecimal String
     */
    public String toHexString() {
        return bytesToHex(apduBytes);
    }
}