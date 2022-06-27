package com.gdng.support.common.dto;

import lombok.Data;

@Data
public class Claims {

    // header
    private String alg;
    private String publicKey;

    // payload
    private String sub;
    private Long created;
    private Long exp;

    // signature
    private String signature;
}
