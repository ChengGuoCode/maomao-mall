package com.gdng.support.common.spring;

import com.gdng.support.common.dto.UserDTO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springContextHolder")
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext context;

    private static final ThreadLocal<UserDTO> principalThreadLocal = new ThreadLocal<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> classType) throws BeansException {
        return context.getBean(classType);
    }

    public static Object getBean(String beanName) throws BeansException {
        return context.getBean(beanName);
    }

    public static String getProperty(String propertyName) {
        return context.getEnvironment().getProperty(propertyName);
    }

    public static void setUser(UserDTO userDTO) {
        principalThreadLocal.set(userDTO);
    }

    public static UserDTO getUser() {
        return principalThreadLocal.get();
    }

}
