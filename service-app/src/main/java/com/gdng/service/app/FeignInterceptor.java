package com.gdng.service.app;

import com.gdng.support.common.dto.UserDTO;
import com.gdng.support.common.spring.SpringContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

import static com.gdng.support.common.constant.HttpConstant.Uri.UID;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/14 15:52
 * @Description:
 * @Version: 1.0.0
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        UserDTO user = SpringContextHolder.getUser();
        if (user != null) {
            template.header(UID, user.getId());
        }
    }
}
