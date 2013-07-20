package com.ids.json;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ids.businessLogic.DropdownInterface;

public class ColumnSummaryNameArray implements DropdownInterface {

	JSONArray columnNameArray = new JSONArray();
	
	JSONObject columnNameObject = new JSONObject();
	
	public ColumnSummaryNameArray(Statement statement, String firstColumn, int topRow, int curYear) throws SQLException  {
		
	      try {


	      String query=null;
	      ResultSet resultSet = null;
	      columnNameArray.put(firstColumn);
	    	  

	          if (topRow==YEARS) {
	        	  for (int i=(curYear-5); i<=(curYear+5); i++) {
	        		  columnNameArray.put(Integer.toString(i));
	        	  }
	          }
	          
	          if (topRow==PRODUCT) {
	        	  
	        	  query = "select distinct shortname from Product order by shortname asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
	        	  }
	        	  
	          }
	          if (topRow==COUNTRY) {
	        	  
	        	  query = "select distinct shortname from Country where shortname != 'ALLY' order by shortname asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
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