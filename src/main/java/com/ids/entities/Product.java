package com.ids.entities;

public class Product {

	private String id = "";
	private String name="";
	
	public Product(String id, String name){
		this.id = id;
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}

	
}
