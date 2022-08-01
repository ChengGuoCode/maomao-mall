package com.gdng.support.security.handler;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/28 18:19
 * @Description:
 * @Version: 1.0.0
 */
public class GdngAuthorityVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
        String requestUrl = object.getRequestUrl();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority next : authorities) {
            String authority = next.getAuthority();
            List<String> userRolePermission = UserRedisCache.getUserRolePermission(authority);
            for (String permissionUrl : userRolePermission) {
                if (requestUrl.startsWith(permissionUrl)) {
                    return ACCESS_GRANTED;
                }
            }
        }
        return ACCESS_DENIED;
    }
}
