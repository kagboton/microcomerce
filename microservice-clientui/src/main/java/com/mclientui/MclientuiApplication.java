package com.mclientui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.mclientui")
@EnableDiscoveryClient
public class MclientuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MclientuiApplication.class, args);
	}

}
