package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


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
	
	
	// get API
	@GetMapping("/greeting-repo")
	public ResponseEntity<Object> GetGreeting(){
		return new ResponseEntity<>(GreetingRepo.values(), HttpStatus.OK);
	}
	
	// post API
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Greeting product){
		GreetingRepo.put(product.getContent(), product);
		 return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	   }
	
	//put api
	@RequestMapping(value = "/product/{content}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("content") String content, @RequestBody Greeting product){
		
		GreetingRepo.remove(content);
		product.setContent(content);
		GreetingRepo.put(content, product);
		
		
		 return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
	   }
	
	//delete api
	@RequestMapping(value = "/product/{content}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable("content") String content){
		
		GreetingRepo.remove(content);
		return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
		
	} 
	
	
	}
