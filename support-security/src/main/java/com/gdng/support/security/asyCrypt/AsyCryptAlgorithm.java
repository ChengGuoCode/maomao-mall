package com.gdng.support.security.asyCrypt;

import java.util.Map;

public interface AsyCryptAlgorithm {

    Map<String, String> createKeys();

    Map<String, String> createKeys(int keySize);

    String encrypt(String data, String publicKeyStr);

    String decrypt(String data, String privateKeyStr);

}
