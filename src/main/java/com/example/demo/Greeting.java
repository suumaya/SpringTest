package com.example.demo;

public class Greeting {

	private long id;
	private String content;
	

	public Greeting() {
		this.id = -1;
		this.content = "";
	}

	public Greeting(long id, String content) {
		this.id = id;
		this.content = content;
	}
	

	public void setContent(String content) {
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	
}
