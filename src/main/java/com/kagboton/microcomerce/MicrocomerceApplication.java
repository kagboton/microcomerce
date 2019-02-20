package com.kagboton.microcomerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MicrocomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicrocomerceApplication.class, args);
	}

}
