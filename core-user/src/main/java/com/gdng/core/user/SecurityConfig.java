package com.gdng.core.user;

import com.gdng.core.user.security.CoreAccessDeniedHandler;
import com.gdng.core.user.security.CoreAuthenticationEntryPoint;
import com.gdng.core.user.security.GdngLogoutHandler;
import com.gdng.core.user.security.JwtAuthenticationTokenFilter;
import com.gdng.core.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth.token.strategy}")
    private String securityStrategyName;

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public SecurityConfig(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.logout().addLogoutHandler(new GdngLogoutHandler()).permitAll();

        http.authorizeRequests().antMatchers("/core/user/login", "/core/user/addOrUpdate").anonymous().antMatchers("/**").authenticated();
        http.addFilterBefore(new JwtAuthenticationTokenFilter(securityStrategyName), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().accessDeniedHandler(new CoreAccessDeniedHandler()).authenticationEntryPoint(new CoreAuthenticationEntryPoint());

        http.csrf().disable();
        http.cors();
    }

}
