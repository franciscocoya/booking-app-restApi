package com.hosting.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

//@OpenAPIDefinition
@SpringBootApplication
public class BookingSystemApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingSystemApiRestApplication.class, args);
	}

}
