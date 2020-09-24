package com.carapp.eserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EserverApplication.class, args);
	}

}
