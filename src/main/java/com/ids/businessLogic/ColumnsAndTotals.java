package com.ids.businessLogic;

public class ColumnsAndTotals {

	private String colName;
	private int totalValue;
	public ColumnsAndTotals(String colName, int totalValue){
		this.colName = colName;
		this.totalValue = totalValue;
	}
	
	public String getColName(){
		return colName;
	}
	
	public int getTotalValue(){
		return totalValue;
	}
	
}
