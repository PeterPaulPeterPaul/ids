package com.ids.json;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ids.businessLogic.DropdownInterface;
import com.ids.businessLogic.StoreRequestParameters;

public class ColumnNameArray implements DropdownInterface {

	JSONArray columnNameArray = new JSONArray();
	
	JSONObject columnNameObject = new JSONObject();
	
	public ColumnNameArray(StoreRequestParameters srp, Statement statement, String topRow, int dimension1, int fromYear, int toYear, 
			   String access, String total, String percent) throws SQLException  {
		
	      try {


	      String query=null;
	      ResultSet resultSet = null;

	          if (dimension1 == PRODUCT) {
	    	     columnNameArray.put("Product");
	          }else{
	        	  if (dimension1== COUNTRY) {
	        		  columnNameArray.put("Country");   
	        	  } else {
	        		  if (dimension1==YEARS) {
	        	          columnNameArray.put("Year"); 
	        		  } else {
	        			  columnNameArray.put("Company"); 
	        		  }
	        	  }
	          }
	    	  
	          int j=0;

	          if (topRow.equals("years")) {
	        	  for (int i=fromYear; i<=toYear; i++) {
	        		  columnNameArray.put(Integer.toString(i));
	        		  if (percent.equals("yes")) {
	        		     j+=1;
	        		     columnNameArray.put("PPC"+j);
	        		  }
	        	  }
	          }
	          
	          if (topRow.equals("product shortname")) {
	        	  
	        	  query = "select distinct d.shortname from Product d where d.access = '"+access+"'" +
	        	  srp.getIncExProducts()+
	        	  		" order by d.shortname asc";
	        	  resultSet = statement.executeQuery(query);

	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
	        		  if (percent.equals("yes")) {
		        		     j+=1;
		        		     columnNameArray.put("PPC"+j);
		        		  }
	        	  }
	        	  
	          }
	          if (topRow.equals("country shortname")) {
	        	  
	        	  query = "select distinct c.shortname from Country c where c.shortname != 'ALLY' and c.ID > 0 and c.access = '"+access+"' " +
	        			  srp.getIncExCountries()+
	        	  		" order by SortOrder asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("shortname"));
	        		  if (percent.equals("yes")) {
		        		     j+=1;
		        		     columnNameArray.put("PPC"+j);
		        		  }
	        	  }
	        	  
	          }
	          if (topRow.equals("country")) {
	        	  
	        	  query = "select distinct c.country as name from Country c where c.shortname != 'ALLY' and c.ID > 0 and access = '"+access+"' " +
	        			  srp.getIncExCountries()+
	        			  " order by c.SortOrder asc";
	        	  resultSet = statement.executeQuery(query);
	        	  
	        	  while (resultSet.next()) {
	        		  columnNameArray.put(resultSet.getString("name"));
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