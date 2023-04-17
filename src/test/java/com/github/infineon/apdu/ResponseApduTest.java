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

import com.github.infineon.apdu.response.ResponseApdu;
import com.github.infineon.ByteUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ResponseApduTest {

    @Test
    public void testResponseApdu() {
        ResponseApdu r = new ResponseApdu(ByteUtils.fromHexString("12349000"));

        Assertions.assertEquals("12349000", r.toHexString());
        Assertions.assertEquals(0x90, r.getSW1());
        Assertions.assertEquals(0, r.getSW2());
        Assertions.assertEquals(0x9000, r.getSW1SW2());
        Assertions.assertEquals("9000", r.getSW1SW2HexString());
        Assertions.assertTrue(r.isSuccess());
    }
}