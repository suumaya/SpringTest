package com.example.demo;

public class Product {

	private String id;
	private String content;
	

	public Product() {
		this.id = "1";
		this.content = "";
	}

	public Product(String id, String content) {
		this.id = id;
		this.content = content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	
}
