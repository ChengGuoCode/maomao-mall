package com.gdng.support.common;

import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import com.gdng.support.common.security.asyCrypt.AsyCryptUtil;
import com.gdng.support.common.security.asyCrypt.RSACrypt;
import org.junit.Test;

public class AsyCryptTest {

    @Test
    public void testRSAEncrypt() {
        System.out.println(AsyCryptUtil.encrypt("74642cf99652488a9f432c95059d43e4", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMNPaAsZIySaBHo6KxwHrwzyNxP_ogJt06c1-iiMaaSUwM1fAgvqZqONjwmgxpZvm5MLbink1HKkoEGUUJPj7HECAwEAAQ", AsyCryptAlgEnum.RSA));
    }

    @Test
    public void testGenerateSessionKey() {
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMNPaAsZIySaBHo6KxwHrwzyNxP_ogJt06c1-iiMaaSUwM1fAgvqZqONjwmgxpZvm5MLbink1HKkoEGUUJPj7HECAwEAAQ";
        String uuid = "355BC39B018E49DC893A1E533D5304CE";
        RSACrypt rsaCrypt = new RSACrypt();
        System.out.println(rsaCrypt.encrypt(uuid, publicKey));
    }

}
