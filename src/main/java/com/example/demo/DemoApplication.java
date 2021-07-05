package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootApplication
@RestController

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8080");
//			}
//		};
//		
//	}
	
	
//	
//	@GetMapping("/")
//	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//		return String.format("Hello %s!", name);
//		}

}
