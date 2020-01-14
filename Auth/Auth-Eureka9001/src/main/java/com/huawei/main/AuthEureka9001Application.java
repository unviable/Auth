package com.huawei.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AuthEureka9001Application {
	public static void main(String[] args) {
		SpringApplication.run(AuthEureka9001Application.class, args);
	}
}
