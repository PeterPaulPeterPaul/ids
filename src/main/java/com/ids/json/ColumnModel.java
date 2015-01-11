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

		  if (i==0) {
  		      JSONObject obj1a = new JSONObject();
	    	  obj1a.put("name",jsonArray.get(i));
	    	  obj1a.put("index",jsonArray.get(i));
	    	  obj1a.put("classes","titleFont"); 
	    	  obj1a.put("width",150);   
	    	  obj1a.put("editable",false);
	    	  //obj1a.put("fixed",true);	    	  
	  	  //    obj1a.put("classes","colorGrey");
	    	  array3.put(obj1a);
		  }else {
		     JSONObject obj1a = new JSONObject();
  	         obj1a.put("name",jsonArray.get(i));
  	         obj1a.put("align", "right");
  	         obj1a.put("firstsortorder","desc");  	         
  	         obj1a.put("index",jsonArray.get(i));

   		  obj1a.put("width",50); 
   
	    	 // if (jsonArray.length()>11) {
	    	 //    obj1a.put("width",30);
	    	  //} else {
	    	//	  obj1a.put("width",70); 
	    	 // }
	    	  
	    	  obj1a.put("editable",true);
	    	  obj1a.put("classes","colorWhite"); 
	    	  if (((String)jsonArray.get(i)).contains("PPC")){
	    		  obj1a.put("width",20);
	    		  obj1a.put("classes","PPcFont"); 
	    		  obj1a.put("editable",false);
		    	 // obj1a.put("fixed",true);
	    	  }
	    	  
	    	  if (((String)jsonArray.get(i)).contains("TOTAL")){
	    		  obj1a.put("classes","titleFont"); 
	    		  obj1a.put("editable",false);
		    	 // obj1a.put("fixed",true);
	    	  }
  	         obj1a.put("sorttype","int"); 
  	         obj1a.put("formatter","number");
  	           
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
