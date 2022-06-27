package com.gdng.support.common.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gdng.support.common.dto.Claims;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import com.gdng.support.common.security.asyCrypt.AsyCryptUtil;
import com.gdng.support.common.util.ConvertUtil;
import com.gdng.support.common.util.JacksonUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SecurityStrategyUtil {

    private static final Logger log = LoggerFactory.getLogger(SecurityStrategyUtil.class);

    private static final String ALG = "alg";
    private static final String PUB_KEY = "publicKey";
    private static final String SUB = "sub";
    private static final String CREATED = "created";
    private static final String EXP = "exp";
    private static final String SIGNATURE = "signature";

    private static final String SEPARATOR = "-";

    private static final String MD5 = "MD5";

    /**
     * Base64
    */
    private static String base64Encode(String data) {
        return Base64.encodeBase64URLSafeString(data.getBytes(StandardCharsets.UTF_8));
    }

    private static String base64Decode(String base64Str) {
        return new String(Base64.decodeBase64URLSafe(base64Str), StandardCharsets.UTF_8);
    }

    /**
     * JwtToken
    */
    public static String generateToken(AsyCryptAlgEnum alg, String publicKeyStr, String username, Long expire, String uid) {
        Map<String, Object> headerMap = new HashMap<>();
        Map<String, Object> payloadMap = new HashMap<>();
        Map<String, Object> signatureMap = new HashMap<>();
        headerMap.put(ALG, alg.getAlgorithm());
        headerMap.put(PUB_KEY, publicKeyStr);
        payloadMap.put(SUB, username);
        payloadMap.put(CREATED, Calendar.getInstance().getTimeInMillis());
        payloadMap.put(EXP, expire);
        signatureMap.put(SIGNATURE, AsyCryptUtil.encrypt(uid, publicKeyStr, alg));
        return base64Encode(JacksonUtil.anyToJson(headerMap)) + "." +
                base64Encode(JacksonUtil.anyToJson(payloadMap)) + "." +
                base64Encode(JacksonUtil.anyToJson(signatureMap));
    }

    public static Claims parseToken(String token) {
        String[] split = token.split("\\.");
        if (split.length != 3) {
            throw new GdngException(GlobalResponseEnum.TOKEN_INVALID);
        }
        String headerStr = base64Decode(split[0]);
        String payloadStr = base64Decode(split[1]);
        String signatureStr = base64Decode(split[2]);
        Map<String, Object> headerMap = JacksonUtil.jsonToMap(headerStr);
        Map<String, Object> payloadMap = JacksonUtil.jsonToMap(payloadStr);
        Map<String, Object> signatureMap = JacksonUtil.jsonToMap(signatureStr);
        Claims claims = new Claims();
        claims.setAlg(String.valueOf(headerMap.get(ALG)));
        String publicKey = String.valueOf(headerMap.get(PUB_KEY));
        claims.setPublicKey(publicKey);
        claims.setSub(String.valueOf(payloadMap.get(SUB)));
        claims.setCreated(ConvertUtil.parseObjToLong(payloadMap.get(CREATED)));
        claims.setExp(ConvertUtil.parseObjToLong(payloadMap.get(EXP)));
        claims.setSignature(String.valueOf(signatureMap.get(SIGNATURE)));
        return claims;
    }


    /**
     * 签名
    */
    public static String makeSign(Map<String, String> signMap, String secret) {
        String[] keys = signMap.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder md5Sb = new StringBuilder();
        for (String key : keys) {
            String val = signMap.get(key);
            if (StringUtils.isNotBlank(val)) {
                try {
                    md5Sb.append(URLDecoder.decode(val, StandardCharsets.UTF_8.toString())).append(SEPARATOR).append(key);
                } catch (UnsupportedEncodingException e) {
                    log.error("makeSign urlDecode error", e);
                }
            }
        }
        md5Sb.append(secret);
        return encryptMD5(md5Sb.toString());
    }

    /**
     * MD5
    */
    private static String encryptMD5(String data) {
        try {
            return byte2hex(encryptMD5(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5 encrypt error", e);
        }
        return data;
    }

    private static byte[] encryptMD5(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5);
        return md.digest(data);
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(aByte & 255);
            if (hex.length() == 1) {
                sb.append("0");
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

}
