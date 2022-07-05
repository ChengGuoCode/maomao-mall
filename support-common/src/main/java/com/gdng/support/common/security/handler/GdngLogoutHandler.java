package com.gdng.support.common.security.handler;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GdngLogoutHandler implements LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal != null) {
            UserDTO userDTO = (UserDTO) principal;
            String uid = userDTO.getId();
            UserDTO userInfo = UserRedisCache.getUserInfoByUid(uid);
            if (userInfo != null) {
                userInfo.setToken(null);
                UserRedisCache.setUserInfo(userInfo);
            }
        }
    }
}
