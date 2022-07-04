package com.gdng.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.gdng.inner.api.**")
@ComponentScans(value = {@ComponentScan(value = {"com.gdng.support.common"})})
public class ServiceAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAppApplication.class, args);
        System.out.println("service-app启动成功...");
    }

}
