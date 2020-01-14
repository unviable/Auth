package com.huawei.authcenter.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan({
	"com.huawei.commons.utils",
	"com.huawei.authcenter.handler",
	"com.huawei.commons.configuration"
})
public class AuthCenter7788Application {
	public static void main(String[] args) {
		SpringApplication.run(AuthCenter7788Application.class, args);
	}
}
