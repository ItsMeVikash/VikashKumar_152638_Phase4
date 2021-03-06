package com.cg.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.cg.spring")
@SpringBootApplication
public class SpringMainApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMainApplication.class, args);
	}
}
