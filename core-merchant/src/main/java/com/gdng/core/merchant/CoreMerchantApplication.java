package com.gdng.core.merchant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common"})})
@MapperScan("com.gdng.entity.merchant")
@EnableDiscoveryClient
public class CoreMerchantApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreMerchantApplication.class, args);
        System.out.println("core-merchant启动成功...");
    }

}
