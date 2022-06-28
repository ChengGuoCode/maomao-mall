package com.gdng.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/6/28 17:33
 * @Description:
 * @Version: 1.0.0
 */
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
        System.out.println("config-server启动成功...");
    }

}
