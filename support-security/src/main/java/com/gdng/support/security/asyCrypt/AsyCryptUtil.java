package com.gdng.support.security.asyCrypt;

import java.util.HashMap;
import java.util.Map;

public class AsyCryptUtil {

    private static final Map<AsyCryptAlgEnum, AsyCryptAlgorithm> asyCryptAlgorithmMap = new HashMap<>();

    static {
        asyCryptAlgorithmMap.put(AsyCryptAlgEnum.RSA, new RSACrypt());
    }

    public static Map<String, String> createKeys(AsyCryptAlgEnum algorithm) {
        return asyCryptAlgorithmMap.get(algorithm).createKeys();
    }

    public static Map<String, String> createKeys(int keySize, AsyCryptAlgEnum algorithm) {
        return asyCryptAlgorithmMap.get(algorithm).createKeys(keySize);
    }

    public static String encrypt(String data, String publicKeyStr, AsyCryptAlgEnum algorithm) {
        return asyCryptAlgorithmMap.get(algorithm).encrypt(data, publicKeyStr);
    }

    public static String decrypt(String data, String privateKeyStr, AsyCryptAlgEnum algorithm) {
        return asyCryptAlgorithmMap.get(algorithm).decrypt(data, privateKeyStr);
    }
}
