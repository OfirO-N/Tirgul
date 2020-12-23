package com.catalog.oon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OonApplication {

	public static void main(String[] args) {
		SpringApplication.run(OonApplication.class, args);
		System.out.println("The IOC container was loaded LOL");
	}

}
