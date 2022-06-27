package com.gdng.core.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common","com.gdng.entity.user"})})
public class CoreOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreOrderApplication.class, args);
        System.out.println("core-order启动成功...");
    }

}
