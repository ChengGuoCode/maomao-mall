package com.gdng.core.task;

import com.gdng.support.common.handler.GdngUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/14 16:09
 * @Description:
 * @Version: 1.0.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GdngUserInterceptor()).addPathPatterns("/**");
    }

}
