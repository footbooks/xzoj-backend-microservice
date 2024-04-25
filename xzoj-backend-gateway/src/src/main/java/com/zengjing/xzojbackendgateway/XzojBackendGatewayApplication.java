package com.zengjing.xzojbackendgateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
//@MapperScan("com.zj.xzOj.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.zengjing")
public class XzojBackendGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(XzojBackendGatewayApplication.class, args);
    }

}
