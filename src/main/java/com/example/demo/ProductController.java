package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class ProductController {
	
	private static Map<String, Product> ProductRepo = new HashMap<>();
	private static final String template = "Hello, %s!";
//	private final AtomicLong counter = new AtomicLong();
	
	 static {
		
			Product ob1 = new Product("1", String.format(template,"first ob"));
			ProductRepo.put(ob1.getContent(), ob1);
			
			Product ob2 = new Product("2", String.format(template,"second ob"));
			ProductRepo.put(ob2.getContent(), ob2);
			
//			 ResponseEntity<Object> object = createProduct(ob1);
			
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
	public ResponseEntity<Object> GetProduct(){
		return new ResponseEntity<>(ProductRepo.values(), HttpStatus.OK);
	}
	
	// post API "not working method not supported"
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product){
		ProductRepo.put(product.getId(), product);
		 return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	   }
	
	//put api "not working"
	@RequestMapping(value = "/product/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
		
		ProductRepo.remove(id);
		product.setContent(id);
		ProductRepo.put(id, product);
		
		
		 return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
	   }
	
	//delete api
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id){
		
		ProductRepo.remove(id);
		return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
		
	} 
	
	
	}
