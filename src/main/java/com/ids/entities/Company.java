package com.ids.entities;

import java.util.List;

public class Company {

	private String name ="";
	private String id="";
	private List<Integer> years=null;
	private List<Integer> quantities=null;
	
	public Company (String id, String name) {
		this.name = name;
		this.id=id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getId(){
		return this.id;
	}
	
	public List<Integer> getYears(){
		return years;
	}
	public List<Integer> getQuantities(){
		return quantities;
	}
	
	public void setYears( List<Integer> years) {
		this.years = years;
	}
	public void setQuantities( List<Integer> quantities) {
		this.quantities = quantities;
	}
	
}
