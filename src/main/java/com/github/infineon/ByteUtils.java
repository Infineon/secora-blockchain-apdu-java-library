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
package com.github.infineon;

/**
 * Some helper utils to work with byte arrays.
 */
public class ByteUtils {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * returns hexadecimal string representation of a byte array.
     *
     * @param bytes byte array
     * @return hex string
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Creates a byte array out of a Hex string.
     *
     * @param hexString
     * @return byte array
     */
    public static byte[] fromHexString(String hexString) {
        hexString = hexString.replaceAll("\\s+", "");
        int len = hexString.length();

        if (!isNumeric(hexString) || len % 2 != 0) {
            return new byte[]{};
        }

        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    private static boolean isNumeric(String hexString) {
        if (hexString.length() == 0 ||
                (hexString.charAt(0) != '-' && Character.digit(hexString.charAt(0), 16) == -1))
            return false;
        if (hexString.length() == 1 && hexString.charAt(0) == '-')
            return false;

        for (int i = 1; i < hexString.length(); i++)
            if (Character.digit(hexString.charAt(i), 16) == -1)
                return false;
        return true;
    }
}
