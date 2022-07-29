package com.gdng.service.app;

import com.gdng.support.security.filter.AuthenticationTokenFilter;
import com.gdng.support.security.handler.GdngAccessDeniedHandler;
import com.gdng.support.security.handler.GdngAuthenticationEntryPoint;
import com.gdng.support.security.handler.GdngAuthorityVoter;
import com.gdng.support.security.handler.GdngLogoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${app.token.strategy}")
    private String securityStrategyName;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.logout().addLogoutHandler(new GdngLogoutHandler()).permitAll();

        http.authorizeRequests().antMatchers("/**").authenticated().accessDecisionManager(accessDecisionManager());
        http.addFilterBefore(new AuthenticationTokenFilter(securityStrategyName), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(new GdngAccessDeniedHandler()).authenticationEntryPoint(new GdngAuthenticationEntryPoint());

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters
                = Arrays.asList(
                new GdngAuthorityVoter());
        return new UnanimousBased(decisionVoters);
    }

}
