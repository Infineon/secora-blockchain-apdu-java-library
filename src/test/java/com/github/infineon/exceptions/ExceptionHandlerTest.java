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
package com.github.infineon.exceptions;

import com.github.infineon.apdu.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ExceptionHandlerTest {

    @Test
    public void testGenerateKeyPairError() {
        Assertions.assertThrows(GenerateKeypairException.class, () -> {
            GenerateKeyPairApdu apdu = new GenerateKeyPairApdu(0);
            ExceptionHandler.handleErrorCodes(apdu, 0x6A84);
        });
    }

    @Test
    public void testGetKeyInfoError() {
        Assertions.assertThrows(GetKeyInfoException.class, () -> {
            GetKeyInfoApdu apdu = new GetKeyInfoApdu(0);
            ExceptionHandler.handleErrorCodes(apdu, 0x6A88);
        });
    }

    @Test
    public void testGenerateSignatureError() {
        Assertions.assertThrows(GenerateSignatureException.class, () -> {
            GenerateSignatureApdu apdu = new GenerateSignatureApdu(0, new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6985);
        });
    }

    @Test
    public void testGenerateKeyFromSeedError() {
        Assertions.assertThrows(GenerateKeyFromSeedException.class, () -> {
            GenerateKeyFromSeedApdu apdu = new GenerateKeyFromSeedApdu(new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6985);
        });
    }

    @Test
    public void testSetPinError() {
        Assertions.assertThrows(SetPinException.class, () -> {
            SetPinApdu apdu = new SetPinApdu(new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6700);
        });
    }

    @Test
    public void testChangePinError() {
        Assertions.assertThrows(ChangePinException.class, () -> {
            ChangePinApdu apdu = new ChangePinApdu(new byte[]{}, new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6983);
        });
    }

    @Test
    public void testVerifyPinError() {
        Assertions.assertThrows(VerifyPinException.class, () -> {
            VerifyPinApdu apdu = new VerifyPinApdu(new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6983);
        });
    }

    @Test
    public void testUnlockPinError() {
        Assertions.assertThrows(UnlockPinException.class, () -> {
            UnlockPinApdu apdu = new UnlockPinApdu(new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6983);
        });
    }

    @Test
    public void testSelectApplicationError() {
        Assertions.assertThrows(SelectApplicationException.class, () -> {
            SelectApplicationApdu apdu = new SelectApplicationApdu(new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6A82);
        });
    }

    @Test
    public void testNfcCardError() {
        Assertions.assertThrows(NfcCardException.class, () -> {
            SelectApplicationApdu apdu = new SelectApplicationApdu(new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6700);
        });
    }

    @Test
    public void testNfcCardErrorWithSW2Info() {
        Assertions.assertThrows(NfcCardException.class, () -> {
            SelectApplicationApdu apdu = new SelectApplicationApdu(new byte[]{});
            ExceptionHandler.handleErrorCodes(apdu, 0x6401);
        });
    }
}