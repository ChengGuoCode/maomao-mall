package com.gdng.core.task;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/14 09:31
 * @Description:
 * @Version: 1.0.0
 */
@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common"})})
@MapperScan("com.gdng.entity.task")
@EnableDiscoveryClient
public class CoreTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreTaskApplication.class, args);
        System.out.println("core-task启动成功...");
    }

}
