package com.soft.appservice2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppService2Application {

	public static void main(String[] args) {
		SpringApplication.run(AppService2Application.class, args);
	}
}
