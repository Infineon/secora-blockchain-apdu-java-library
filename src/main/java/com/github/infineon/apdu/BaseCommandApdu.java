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

import static com.github.infineon.ByteUtils.bytesToHex;

/**
 * Base class for Command APDUs sent to card. Represents a Command APDU as
 * specified in ISO 7816-4
 */
public abstract class BaseCommandApdu {

    /**
     * CLA byte
     */
    protected int cla = 0x00;

    /**
     * INS byte
     */
    protected int ins = 0x00;

    /**
     * P1 byte
     */
    protected int p1 = 0x00;

    /**
     * P2 byte
     */
    protected int p2 = 0x00;

    /**
     * Data part of APDU
     */
    private byte[] data = new byte[0];

    /**
     * Le byte
     */
    protected int le = 0x00;

    /**
     * Flag to remember if Le is used in this APDU
     */
    protected boolean leIncluded = false;

    protected BaseCommandApdu() {
    }

    /**
     * Set APDU data field
     */
    protected void setData(byte[] data) {
        if (data == null) {
            this.data = new byte[0];
            return;
        }
        if (data.length > 0xFF) {
            throw new IllegalArgumentException("Data cannot be larger than 0xFF bytes");
        }
        this.data = data;
    }

    /**
     * @return Hex String of the APDU
     */
    public String toHexString() {
        return bytesToHex(this.toBytes());
    }

    /**
     * Get byte representation of the APDU.
     *
     * @return the byte representation of this APDU, as it is sent over NFC to the card
     */
    public byte[] toBytes() {
        int length = 4; // CLA byte, INS byre, P1 + P2 bytes
        if (data.length != 0) {
            length += 1; // 1 byte LC
            length += data.length; // DATA
        }
        if (leIncluded) {
            length += 1; // LE
        }

        byte[] apdu = new byte[length];

        int index = 0;
        apdu[index] = (byte) cla;
        index++;
        apdu[index] = (byte) ins;
        index++;
        apdu[index] = (byte) p1;
        index++;
        apdu[index] = (byte) p2;
        index++;
        if (data.length != 0) {
            apdu[index] = (byte) data.length;
            index++;
            System.arraycopy(data, 0, apdu, index, data.length);
            index += data.length;
        }
        if (leIncluded) {
            apdu[index] += le; // LE
        }
        return apdu;
    }

}
