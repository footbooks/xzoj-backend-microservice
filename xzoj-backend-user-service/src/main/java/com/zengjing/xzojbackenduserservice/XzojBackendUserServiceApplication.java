package com.zengjing.xzojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@MapperScan("com.zengjing.xzojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.zengjing")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.zengjing.xzojbackendserviceclient.service")
public class XzojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(XzojBackendUserServiceApplication.class, args);
    }

}
