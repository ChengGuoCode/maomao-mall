package com.gdng.support.common.handler;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.spring.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gdng.support.common.constant.HttpConstant.Uri.UID;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/14 16:04
 * @Description:
 * @Version: 1.0.0
 */
public class GdngUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uid = request.getHeader(UID);
        if (StringUtils.isNotBlank(uid)) {
            UserDTO userInfo = UserRedisCache.getUserInfoByUid(uid);
            SpringContextHolder.setUser(userInfo);
        } else {
            SpringContextHolder.setUser(null);
        }
        return true;
    }

}
