package com.gdng.core.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common","com.gdng.entity.user"})})
public class CoreGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreGoodsApplication.class, args);
        System.out.println("core-goods启动成功...");
    }

}
