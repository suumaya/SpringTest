package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//to support war
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@RestController
public class DemoApplication extends SpringBootServletInitializer{

	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(DemoApplication.class);
	   }
	
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	

	//try tomcat
	@RequestMapping(value = "/")
	public String hello() {
	      return "Hello World from Tomcat";
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
