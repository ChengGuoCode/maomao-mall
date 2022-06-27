package com.gdng.support.common;

import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import com.gdng.support.common.security.asyCrypt.AsyCryptUtil;
import org.junit.Test;

public class AsyCryptTest {

    @Test
    public void testRSAEncrypt() {
        System.out.println(AsyCryptUtil.encrypt("dc3414bce1e3488eb6c63c2167f34cde", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIbXPbzTPpo5tIluWvkK6fEWns1QJg7LDWw9WcRbsUbzjfNoVPU8XU9uB4AryDNOYx5aR_BPJCYvJQkfWB-rtRECAwEAAQ", AsyCryptAlgEnum.RSA));
    }

}
