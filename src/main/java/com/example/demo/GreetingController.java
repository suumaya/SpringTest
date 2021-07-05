package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class GreetingController {
	
	private static Map<String, Greeting> GreetingRepo = new HashMap<>();
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	static{
		Greeting ob1 = new Greeting(123333, String.format(template,"first ob"));
		GreetingRepo.put(ob1.getContent(), ob1);
		
		Greeting ob2 = new Greeting(123334, String.format(template,"second ob"));
		GreetingRepo.put(ob2.getContent(), ob2);
		}
	
	
	
//	
//	@CrossOrigin(origins = "http://localhost:8080")
//	@GetMapping("/greeting")
//	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
//		System.out.println("==== get greeting ====");
//		return new Greeting(counter.incrementAndGet(), String.format(template,name));
//	}
	
	@GetMapping("/greeting-repo")
	public ResponseEntity<Object> GetGreeting(){
		
		return new ResponseEntity<>(GreetingRepo.values(), HttpStatus.OK);
	}
	
	
	}
