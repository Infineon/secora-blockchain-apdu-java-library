package com.github.infineon.apdu;

import com.github.infineon.ByteUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.infineon.ByteUtils.fromHexString;
import static com.github.infineon.apdu.GenerateKeyPairApdu.CURVE_INDEX_SECP256K1;

public class CommandApduTest {

    @Test
    public void testChangePinApdu() {
        byte[] currentPin = ByteUtils.fromHexString("DEADBEEF");
        byte[] newPin = ByteUtils.fromHexString("BEEFCAFE");
        byte[] expectedApdu = ByteUtils.fromHexString("004200000A04DEADBEEF04BEEFCAFE00");

        ChangePinApdu changePinApdu = new ChangePinApdu(currentPin, newPin);
        Assertions.assertArrayEquals(expectedApdu, changePinApdu.toBytes());
    }

    @Test
    public void testGenerateKeyFromSeedApdu() {
        byte[] seedData = ByteUtils.fromHexString("DEADBEEF");
        byte[] expectedApdu = ByteUtils.fromHexString("0020000004DEADBEEF");

        GenerateKeyFromSeedApdu generateKeyFromSeedApdu = new GenerateKeyFromSeedApdu(seedData);
        Assertions.assertArrayEquals(expectedApdu, generateKeyFromSeedApdu.toBytes());
    }

    @Test
    public void testGenerateKeyPairApdu() {
        byte[] expectedApdu = ByteUtils.fromHexString("0002000000");

        GenerateKeyPairApdu generateKeyPairApdu = new GenerateKeyPairApdu(CURVE_INDEX_SECP256K1);
        Assertions.assertArrayEquals(expectedApdu, generateKeyPairApdu.toBytes());
    }

    @Test
    public void testGenerateSignatureApdu() {
        byte[] dataToSign = ByteUtils.fromHexString("00112233445566778899AABBCCDDEEFF");
        byte[] expectedApdu = ByteUtils.fromHexString("001800001000112233445566778899AABBCCDDEEFF00");

        GenerateSignatureApdu generateSignatureApdu = new GenerateSignatureApdu(0, dataToSign);
        Assertions.assertArrayEquals(expectedApdu, generateSignatureApdu.toBytes());
    }

    @Test
    public void testGetKeyInfoApdu() {
        byte[] expectedApdu = ByteUtils.fromHexString("0016010000");

        GetKeyInfoApdu getKeyInfoApdu = new GetKeyInfoApdu(1);
        Assertions.assertArrayEquals(expectedApdu, getKeyInfoApdu.toBytes());
    }

    @Test
    public void testSelectApplicationApdu() {
        byte[] aid = fromHexString("D2760000041502000100000001");
        byte[] expectedApdu = ByteUtils.fromHexString("00A404000DD276000004150200010000000100");

        SelectApplicationApdu selectApplicationapdu = new SelectApplicationApdu(aid);
        Assertions.assertArrayEquals(expectedApdu, selectApplicationapdu.toBytes());
    }

    @Test
    public void testSetPinApdu() {
        byte[] pin = fromHexString("DEADBEEF");
        byte[] expectedApdu = ByteUtils.fromHexString("0040000004DEADBEEF00");

        SetPinApdu setPinApdu = new SetPinApdu(pin);
        Assertions.assertArrayEquals(expectedApdu, setPinApdu.toBytes());
    }

    @Test
    public void testUnlockPinApdu() {
        byte[] puk = fromHexString("DEADBEEFBEEFCAFE");
        byte[] expectedApdu = ByteUtils.fromHexString("0046000008DEADBEEFBEEFCAFE");

        UnlockPinApdu unlockPinApdu = new UnlockPinApdu(puk);
        Assertions.assertArrayEquals(expectedApdu, unlockPinApdu.toBytes());
    }

    @Test
    public void testVerifyPinApdu() {
        byte[] pin = fromHexString("DEADBEEF");
        byte[] expectedApdu = ByteUtils.fromHexString("0044000004DEADBEEF");

        VerifyPinApdu verifyPinApdu = new VerifyPinApdu(pin);
        Assertions.assertArrayEquals(expectedApdu, verifyPinApdu.toBytes());
    }

}
