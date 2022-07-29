package com.gdng.support.security.handler;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/28 18:19
 * @Description:
 * @Version: 1.0.0
 */
public class GdngAuthorityVoter implements AccessDecisionVoter<FilterInvocation> {

    private static final String ROLE_PREFIX = "role_";
    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

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
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println(object.getFullRequestUrl());
        System.out.println(object.getRequestUrl());
        if (authorities.size() == 1 && ROLE_ANONYMOUS.equals(authorities.iterator().next().getAuthority())) {

        } else {

        }
        return ACCESS_ABSTAIN;
    }
}
