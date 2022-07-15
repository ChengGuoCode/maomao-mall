package com.gdng.support.security.filter;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.constant.HttpConstant;
import com.gdng.support.common.dto.GdngGrantedAuthority;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.security.ISecurityStrategy;
import com.gdng.support.common.security.SecurityStrategyEnum;
import com.gdng.support.common.security.SecurityStrategyFactory;
import com.gdng.support.common.spring.SpringContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final ISecurityStrategy securityStrategy;

    public AuthenticationTokenFilter(String securityStrategyName) {
        SecurityStrategyEnum strategyByName = SecurityStrategyEnum.getStrategyByName(securityStrategyName);
        this.securityStrategy = SecurityStrategyFactory.getInstance(strategyByName);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getParameter(HttpConstant.Uri.TOKEN);
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        ResDTO resDTO = securityStrategy.check(request);
        if (!resDTO.isSuccess()) {
            throw new GdngException(resDTO.getCode(), resDTO.getMsg());
        }
        String uid = request.getParameter(HttpConstant.Uri.UID);
        UserDTO userInfo = UserRedisCache.getUserInfoByUid(uid);
        if (userInfo != null) {
            List<GdngGrantedAuthority> gdngAuthorities = userInfo.getAuthorities();
            List<GrantedAuthority> authorities = null;
            if (!CollectionUtils.isEmpty(gdngAuthorities)) {
                authorities = new ArrayList<>();
                List<GrantedAuthority> finalAuthorities = authorities;
                gdngAuthorities.forEach(e -> {
                    finalAuthorities.add(new SimpleGrantedAuthority(e.getRoleName()));
                });
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        SpringContextHolder.setUser(userInfo);
        filterChain.doFilter(request, response);
        SpringContextHolder.setUser(null);
    }

}
