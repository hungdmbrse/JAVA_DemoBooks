package com.demobookstore.beans;

public class Book {

	private int id;
	private String name;
	private String publisher;
	private int page;
	public Book() {}
	
	public Book( String name, String publisher, int page) {
	
		this.name = name;
		this.publisher = publisher;
		this.page = page;
	}
	
	
	public Book(int id, String name, String publisher, int page) {
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.page = page;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
