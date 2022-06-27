package com.gdng.core.user.security;

import com.gdng.support.common.cache.redis.user.UserRedisCache;
import com.gdng.support.common.constant.HttpConstant;
import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.dto.res.ResDTO;
import com.gdng.support.common.exception.GdngException;
import com.gdng.support.common.security.ISecurityStrategy;
import com.gdng.support.common.security.SecurityStrategyEnum;
import com.gdng.support.common.security.SecurityStrategyFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final ISecurityStrategy securityStrategy;

    public JwtAuthenticationTokenFilter(String securityStrategyName) {
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
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

}
