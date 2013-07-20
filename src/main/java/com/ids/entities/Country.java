package com.ids.entities;

public class Country {

	private String id = "";
	private String name="";
	
	public Country(String id, String name){
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
