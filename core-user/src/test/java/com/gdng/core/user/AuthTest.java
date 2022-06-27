package com.gdng.core.user;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.constant.HttpConstant;
import com.gdng.support.common.dto.Claims;
import com.gdng.support.common.security.SecurityStrategyUtil;
import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import com.gdng.support.common.security.asyCrypt.AsyCryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.gdng.core.user.CoreUserApplication.class)
public class AuthTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final PasswordEncoder passwordEncoderX = new BCryptPasswordEncoder();

    @Test
    public void testPasswordEncoder() {
        String text = "123";
        String text1 = passwordEncoder.encode(text);
        System.out.println(passwordEncoderX.matches("543", text1));
    }

    @Test
    public void testMakeSign() {
        String header = "token=eyJwdWJsaWNLZXkiOiJNRnd3RFFZSktvWklodmNOQVFFQkJRQURTd0F3U0FKQkFJYlhQYnpUUHBvNXRJbHVXdmtLNmZFV25zMVFKZzdMRFd3OVdjUmJzVWJ6amZOb1ZQVThYVTl1QjRBcnlETk9ZeDVhUl9CUEpDWXZKUWtmV0ItcnRSRUNBd0VBQVEiLCJhbGciOiJSU0EifQ.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE2NTUwMDY3MzI5MTUsImV4cCI6LTF9.eyJzaWduYXR1cmUiOiJDOXFJOUxwYUFGTnFBY2poVDFEdU1PeEdHV3AxbUZ5ODQ1TkxFaU1WbW9iNWg3TDN6X1Y2bXExYk1nM2JKZ1pZY3pCSFZVZUtnYS1qZUVWLWhsbGRRdyJ9&ver=0.5&timestamp=1655018036074&sign=256364F8A2C32BCADC272EFADE021940&sessionKey=WJTaPqmurcPezFl8AEyWrqwDpqTPFY3BfspypONO4xxDoxbfBfKNK2fWt8J538glae5mVx6zEniPYa3p5cW3iA&uid=5fc5ee60548876bd5acce4fbb1782155";

        Map<String, String> signMap = getSignMap(header);
        Claims claims = SecurityStrategyUtil.parseToken(signMap.get(HttpConstant.Uri.TOKEN));
        String alg = claims.getAlg();
        AsyCryptAlgEnum algorithm = AsyCryptAlgEnum.getAlgByKey(alg);
        String keyPair = UserRedisCache.getRSAKeyPair(AsyCryptAlgEnum.RSA.getAlgorithm());
        String[] keyPairs = keyPair.split("#");

        signMap.remove(HttpConstant.Uri.SIGN);
        String sessionKeyEncrypt = signMap.get(HttpConstant.Uri.SESSION_KEY);
        String sessionSecret = AsyCryptUtil.decrypt(sessionKeyEncrypt, keyPairs[1], algorithm);
        System.out.println(SecurityStrategyUtil.makeSign(signMap, sessionSecret));
    }

    private Map<String, String> getSignMap(String header) {
        Map<String, String> signMap = new HashMap<>();

        String[] headerPairs = header.split("&");
        for (String headerPair : headerPairs) {
            String[] keyVal = headerPair.split("=");
            if (keyVal.length == 2) {
                signMap.put(keyVal[0], keyVal[1]);
            }
        }
        return signMap;
    }

}
