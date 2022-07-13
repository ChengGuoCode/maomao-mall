package com.gdng.core.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common"})})
@MapperScan("com.gdng.entity.goods")
@EnableDiscoveryClient
public class CoreGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreGoodsApplication.class, args);
        System.out.println("core-goods启动成功...");
    }

}
