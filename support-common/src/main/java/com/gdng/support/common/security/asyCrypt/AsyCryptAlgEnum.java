package com.gdng.support.common.security.asyCrypt;

public enum AsyCryptAlgEnum {
    RSA("RSA"),
    ;

    private final String algorithm;

    AsyCryptAlgEnum(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public static AsyCryptAlgEnum getAlgByKey(String algorithm) {
        AsyCryptAlgEnum[] enums = AsyCryptAlgEnum.values();
        for (AsyCryptAlgEnum anEnum : enums) {
            if (anEnum.algorithm.equals(algorithm)) {
                return anEnum;
            }
        }
        return null;
    }
}
