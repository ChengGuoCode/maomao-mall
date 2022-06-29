package com.gdng.core.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common","com.gdng.entity.user"})})
@EnableDiscoveryClient
public class CoreUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreUserApplication.class, args);
        System.out.println("core-user启动成功...");
    }

}
