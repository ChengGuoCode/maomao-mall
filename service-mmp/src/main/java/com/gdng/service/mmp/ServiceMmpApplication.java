package com.gdng.service.mmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common"})})
public class ServiceMmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMmpApplication.class, args);
        System.out.println("service-mmp启动成功...");
    }

}
