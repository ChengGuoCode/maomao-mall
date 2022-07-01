package com.gdng.support.common.spring.feign;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/1 17:34
 * @Description:
 * @Version: 1.0.0
 */
@Configuration
public class FeignConf {
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    public FeignConf() {
    }

    @Bean
    @Primary
    @Scope("prototype")
    Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(this.messageConverters));
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options();
    }
}
