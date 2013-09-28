package com.ids.json;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ids.businessLogic.DropdownInterface;

public class ColumnNameArray implements DropdownInterface {

	JSONArray columnNameArray = new JSONArray();
	
	JSONObject columnNameObject = new JSONObject();
	
	public ColumnNameArray(Statement statement, String topRow, int dimension1, int fromYear, int toYear, String access) throws SQLException  {
		
	      try {


	      String query=null;
	      ResultSet resultSet = null;

	          if (dimension1 == PRODUCT) {
	    	     columnNameArray.put("product");
	          }else{
	        	  if (dimension1== COUNTRY) {
	        		  columnNameArray.put("country");   
	        	  } else {
	        		  if (dimension1==YEARS) {
	        	          columnNameArray.put("year"); 
	        		  } else {
	        			  columnNameArray.put("company"); 
	        		  }
	        	  }
	          }
	    	  

	          if (topRow.equals("years")) {
	        	  for (int i=fromYear; i<=toYear; i++) {
	        		  columnNameArray.put(Integer.toString(i));
	        	  }
	          }
	          
	          if (topRow.equals("product shortname")) {
	        	  
	        	  query = "select distinct shortname from Product where access = '"+access+"' order by shortname asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
	        	  }
	        	  
	          }
	          if (topRow.equals("country shortname")) {
	        	  
	        	  query = "select distinct shortname from Country where shortname != 'ALLY' and access = '"+access+"'  order by shortname asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
	        	  }
	        	  
	          }
	          if (topRow.equals("country")) {
	        	  
	        	  query = "select distinct country as name from Country where shortname != 'ALLY'  and access = '"+access+"' order by shortname asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("name"));
	        	  }
	        	  
	          }

	    	  
	    	  columnNameObject.put("columns", columnNameArray);
	      
	  //    } catch (SQLException e) {
				// TODO Auto-generated catch block
		//		e.printStackTrace();
			} catch(JSONException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		
	}
	
	public JSONArray getColumnNameArray(){
		return this.columnNameArray;
	}
	
	public JSONObject getColumnNameObject(){
		return this.columnNameObject;
	}
}