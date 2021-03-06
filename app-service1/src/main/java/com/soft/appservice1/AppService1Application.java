package com.soft.appservice1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppService1Application {

	public static void main(String[] args) {
		SpringApplication.run(AppService1Application.class, args);
	}
}
