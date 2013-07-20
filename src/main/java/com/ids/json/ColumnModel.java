package com.ids.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ColumnModel {

	private JSONObject columnModelObject = new JSONObject();
	public ColumnModel(JSONArray jsonArray) {
	
	  try{
		  
	  JSONArray array3 = new JSONArray();
		  
		  
	  for (int i=0;i<jsonArray.length();i++) {
		  System.out.println(jsonArray.get(i));
		  if (i==0) {
  		      JSONObject obj1a = new JSONObject();
	    	  obj1a.put("name",jsonArray.get(i));
	    	  obj1a.put("index",jsonArray.get(i));
	    	  obj1a.put("classes","titleFont"); 
	    	  obj1a.put("width",100);   
	  	  //    obj1a.put("classes","colorGrey");
	    	  array3.put(obj1a);
		  }else {
		     JSONObject obj1a = new JSONObject();
  	         obj1a.put("name",jsonArray.get(i));
  	         obj1a.put("index",jsonArray.get(i));

	    	  if (jsonArray.length()>11) {
	    	     obj1a.put("width",40);
	    	  } else {
	    		  obj1a.put("width",90); 
	    	  }
  	         obj1a.put("sorttype","int"); 
  	         obj1a.put("formatter","number");
  	            obj1a.put("classes","colorWhite"); 
             array3.put(obj1a);

		  }
  	  
	  }
	  columnModelObject.put("columnModels", array3);
	  } catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	 }
	  
	}
	public JSONObject getModelObject(){
		return columnModelObject; 
	}
}
