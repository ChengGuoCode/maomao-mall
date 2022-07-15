package com.gdng.core.user;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.gdng.core.user.CoreUserApplication.class)
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("a", "b");
    }

    @Test
    public void testGetRSAKeyPair() {
        String rsaKeyPair = UserRedisCache.getAsyCryptKeyPair(AsyCryptAlgEnum.RSA.getAlgorithm());
        if (StringUtils.isNotBlank(rsaKeyPair)) {
            String[] split = rsaKeyPair.split("#");
            if (split.length == 2) {
                System.out.println("publicKey:" + split[0] + "\n" + "privateKey:" + split[1]);
            }
        }
    }

}
