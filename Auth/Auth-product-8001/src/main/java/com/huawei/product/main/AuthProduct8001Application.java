package com.huawei.product.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.huawei.product.mapper")
@ComponentScan({
	"com.huawei.product.handler",
	"com.huawei.product.service.impl",
	"com.huawei.commons.interceptor"
})
@EnableEurekaClient
public class AuthProduct8001Application {
	public static void main(String[] args) {
		SpringApplication.run(AuthProduct8001Application.class, args);
	}
}
