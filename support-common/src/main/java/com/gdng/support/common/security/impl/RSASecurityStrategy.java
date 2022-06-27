package com.gdng.support.common.security.impl;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.constant.HttpConstant;
import com.gdng.support.common.dto.Claims;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.GlobalResponseEnum;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.security.ISecurityStrategy;
import com.gdng.support.common.security.SecurityStrategyUtil;
import com.gdng.support.common.security.asyCrypt.AsyCryptAlgEnum;
import com.gdng.support.common.security.asyCrypt.AsyCryptUtil;
import com.gdng.support.common.spring.SpringContextHolder;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class RSASecurityStrategy implements ISecurityStrategy {

    private static final long FIVE_MIN_EXP = 300000;

    @Override
    public ResDTO check(HttpServletRequest request) {
        String token = request.getParameter(HttpConstant.Uri.TOKEN);
        Claims claims = SecurityStrategyUtil.parseToken(token);
        String alg = claims.getAlg();
        String publicKey = claims.getPublicKey();
        String signature = claims.getSignature();
        AsyCryptAlgEnum algorithm = AsyCryptAlgEnum.getAlgByKey(alg);
        if (algorithm == null) {
            return ResDTO.buildFailResult(GlobalResponseEnum.TOKEN_INVALID);
        }
        String keyPair = UserRedisCache.getRSAKeyPair(AsyCryptAlgEnum.RSA.getAlgorithm());
        if (keyPair == null || keyPair.split("#").length != 2) {
            return ResDTO.buildFailResult(GlobalResponseEnum.TOKEN_EXP);
        }
        String[] keyPairs = keyPair.split("#");
        if (!keyPairs[0].equals(publicKey)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.TOKEN_INVALID);
        }

        // 验证签名
        String ver = request.getParameter(HttpConstant.Uri.VER);
        String timestampStr = request.getParameter(HttpConstant.Uri.TIMESTAMP);
        if (!StringUtils.hasText(ver) || !StringUtils.hasText(timestampStr)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.SIGN_ERR);
        }
        String verStr = SpringContextHolder.getProperty("spring." + HttpConstant.Uri.VER);
        if (StringUtils.hasText(verStr)) {
            try {
                if (!checkVer(verStr, ver)) {
                    return ResDTO.buildFailResult(GlobalResponseEnum.SYS_UPGRADE);
                }
            } catch (NumberFormatException e) {
                return ResDTO.buildFailResult(GlobalResponseEnum.PARAM_ERR, "版本号参数有误");
            }
        }
        Long timestamp = Long.valueOf(timestampStr);
        if (new Date().getTime() - timestamp > FIVE_MIN_EXP) {
            return ResDTO.buildFailResult(GlobalResponseEnum.REQUEST_EXP);
        }

        Map<String, String> signMap = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            signMap.put(paramName, request.getParameter(paramName));
        }
        String sign = request.getParameter(HttpConstant.Uri.SIGN);
        signMap.remove(HttpConstant.Uri.SIGN);
        String sessionKeyEncrypt = request.getParameter(HttpConstant.Uri.SESSION_KEY);
        if (!StringUtils.hasText(sessionKeyEncrypt)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.SESSION_KEY_LACK);
        }
        String sessionSecret = AsyCryptUtil.decrypt(sessionKeyEncrypt, keyPairs[1], algorithm);
        String signStr = SecurityStrategyUtil.makeSign(signMap, sessionSecret);
        if (!StringUtils.hasText(sign) || !sign.equalsIgnoreCase(signStr)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.SIGN_ERR);
        }

        // 验证用户信息
        String uid = AsyCryptUtil.decrypt(signature, keyPairs[1], algorithm);
        UserDTO userInfo = UserRedisCache.getUserInfoByUid(uid);
        if (userInfo == null) {
            return ResDTO.buildFailResult(GlobalResponseEnum.TOKEN_INVALID);
        }
        String uidStr = request.getParameter(HttpConstant.Uri.UID);
        if (!StringUtils.hasText(uidStr)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.USER_NONEXIST);
        }
        if (!uidStr.equalsIgnoreCase(uid)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.TOKEN_INVALID);
        }
        String username = userInfo.getUsername();
        if (!username.equals(claims.getSub())) {
            return ResDTO.buildFailResult(GlobalResponseEnum.TOKEN_INVALID);
        }
        Long created = claims.getCreated();
        Long exp = claims.getExp();
        if (exp != -1 && isExp(created, exp)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.TOKEN_EXP);
        }
        String tokenStr = userInfo.getToken();
        if (!StringUtils.hasText(tokenStr) || !tokenStr.equalsIgnoreCase(token)) {
            return ResDTO.buildFailResult(GlobalResponseEnum.NO_LOGIN);
        }
        return ResDTO.buildSuccessResult();
    }

    private boolean isExp(Long created, Long exp) {
        Calendar instance = Calendar.getInstance();
        Date curDate = instance.getTime();
        instance.setTimeInMillis(created + exp);
        return curDate.after(instance.getTime());
    }

    private boolean checkVer(String local, String remote) throws NumberFormatException {
        Double localV = Double.valueOf(local);
        Double remoteV = Double.valueOf(remote);
        return localV <= remoteV;
    }
}
