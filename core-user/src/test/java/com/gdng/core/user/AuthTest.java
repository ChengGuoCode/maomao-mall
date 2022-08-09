package com.gdng.core.user;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.constant.HttpConstant;
import com.gdng.support.common.dto.Claims;
import com.gdng.support.common.security.SecurityStrategyUtil;
import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import com.gdng.support.common.security.asyCrypt.AsyCryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.gdng.core.user.CoreUserApplication.class)
public class AuthTest {

    @Test
    public void testPasswordEncoder() {
    }

    @Test
    public void testMakeSign() {
        String header = "token=eyJwdWJsaWNLZXkiOiJNRnd3RFFZSktvWklodmNOQVFFQkJRQURTd0F3U0FKQkFNTlBhQXNaSXlTYUJIbzZLeHdIcnd6eU54UF9vZ0p0MDZjMS1paU1hYVNVd00xZkFndnFacU9OandtZ3hwWnZtNU1MYmluazFIS2tvRUdVVUpQajdIRUNBd0VBQVEiLCJhbGciOiJSU0EifQ.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE2NTk0OTczMjM2MDIsImV4cCI6LTF9.eyJzaWduYXR1cmUiOiJrQzNkV1didHJWTGtGSmZUQWZoZFpVazQ0VFBoejN0QTBJOF9BNFF2VUFWMFlyWnlmaUFrd1BpdF9mSGRMNzNoaF9BemJzdEtLVkJUSjQyMnlVeTJEQSJ9&ver=1&timestamp=-9527&sign=aae1bed30e9ca37653298cce0ab8f26d&sessionKey=HZYqoxLa-d-9O2zlQXT2T5Ehny2D-WgO9G2TIkyV-CnsvOxgqJqAxHG0olZkGe-NC6JcQ9unVvtMSOu1n4s8tA&uid=1542773459684794370";

        Map<String, String> signMap = getSignMap(header);
        Claims claims = SecurityStrategyUtil.parseToken(signMap.get(HttpConstant.Uri.TOKEN));
        String alg = claims.getAlg();
        AsyCryptAlgEnum algorithm = AsyCryptAlgEnum.getAlgByKey(alg);
        String keyPair = UserRedisCache.getAsyCryptKeyPair(AsyCryptAlgEnum.RSA.getAlgorithm());
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
