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
	
	public ColumnSummaryNameArray(Statement statement, String firstColumn, int topRow,  int fromYear, int toYear,
			String access, String total, String percent) throws SQLException  {
		
	      try {


	      String query=null;
	      ResultSet resultSet = null;
	      columnNameArray.put(firstColumn);
	    	  
	      int j=0;
	          if (topRow==YEARS) {
	        	  for (int i=fromYear; i<=toYear; i++) {
	        		  columnNameArray.put(Integer.toString(i));
	        		  if (percent.equals("yes")) {
		        		     j+=1;
		        		     columnNameArray.put("PPC"+j);
		        		  }
	        	  }
	          }
	          
	          if (topRow==PRODUCT) {
	        	  
	        	  // CXM  Dont show WHLO On the top line as it douple counts. 
	        	  
	        	  query = "select distinct shortname from Product where access = '"+access+"' and shortname <> 'WHLO' order by shortname asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
	        		  if (percent.equals("yes")) {
		        		     j+=1;
		        		     columnNameArray.put("PPC"+j);
		        		  }
	        	  }
	        	  
	          }
	          if (topRow==COUNTRY) {
	        	  
	        	  // CXM - Exclude Europe as a column#
	        	  
	        	  query = "select distinct shortname from Country where shortname != 'EUR' and access = '"+access+"' order by shortname asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
	        		  if (percent.equals("yes")) {
		        		     j+=1;
		        		     columnNameArray.put("PPC"+j);
		        		  }
	        	  }
	        	  
	          }

	          if (!total.equals("")) {
	        	  columnNameArray.put("TOTAL");
	        	  if (percent.equals("yes")) {
	        		     j+=1;
	        		     columnNameArray.put("PPC"+j);
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