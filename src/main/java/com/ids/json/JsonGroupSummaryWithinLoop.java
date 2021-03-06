package com.ids.json;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ids.businessLogic.FirstTimeEdQuery;

public class JsonGroupSummaryWithinLoop {
    private  HashMap<String,Integer> totalLine2 = null;
    private HashMap<String,Integer> totalQuantity3 = null;
    
	private final static Logger logger = Logger.getLogger(JsonGroupSummaryWithinLoop.class.getName());
	
    private String titleTHREE="";
    private String titleFOUR="";
    private String currentCo11="";
    private JSONArray array7;
    private JSONObject obj2a;
    private String colHeading;
	 
    private  int totalQuantity1=0;
    private int year =0;
    
    public JsonGroupSummaryWithinLoop(){
    	 year = Calendar.getInstance().get(Calendar.YEAR);
    }

	public void  loopProcess(String ONE, String TWO,  String quantity, 
			HashMap<String,Integer> totalLine2In,
			 String currentCo11In, JSONArray array7In,
			JSONObject obj2aIn, String colHeadingIn, 
			boolean hasAll, int summary) throws NumberFormatException, SQLException, JSONException{
		
		totalLine2 = totalLine2In;
		currentCo11= currentCo11In;
		array7 = array7In;
		obj2a = obj2aIn;
		colHeading = colHeadingIn;
		
		titleTHREE = "";
		titleFOUR = " Group Summary of 'Heading 2 Value' by "+colHeading;
		if (hasAll) {
		   if (ONE.equals("ALL COMPANIES") || ONE.equals(" ALL COMPANIES"))  {
    		  totalQuantity1=0;
    		  if (totalLine2.get(TWO)!= null) {
    			 totalQuantity1= totalLine2.get(TWO);
    		  }
    		  totalQuantity1 += Integer.parseInt(quantity);
    		//  System.out.println("totalQu: "+totalQuantity1);
    		  totalLine2.put(TWO, totalQuantity1);
			  return;
		   }

		   
		   
		} else {
			
			
			   totalQuantity1=0;
			   if (totalLine2.get(TWO)!= null) {
				   totalQuantity1= totalLine2.get(TWO);
			   }
			   if (ONE.equals(" ALL COMPANIES")){
				   try{
				   if (Integer.parseInt(TWO)>= year) {
				       totalQuantity1 += Integer.parseInt(quantity);
				       totalLine2.put(TWO, totalQuantity1);
				   }
				   }catch(Exception e){
				       totalQuantity1 += Integer.parseInt(quantity);
				       totalLine2.put(TWO, totalQuantity1);  
				   }
			   }else {
			     totalQuantity1 += Integer.parseInt(quantity);
			     totalLine2.put(TWO, totalQuantity1);
			   }
		}
		
		
		if (!currentCo11.equals(ONE))  {
			if (ONE.equals(" ALL COMPANIES")){
				return;
			}
			if (!currentCo11.equals("")){
				array7.put(obj2a);
			}

			   obj2a = new JSONObject();
			   currentCo11=ONE;
			   obj2a.put(colHeading,currentCo11);

			obj2a.put(TWO,quantity);
		} else {

			obj2a.put(TWO,quantity);
		}

	}
	
	public HashMap<String,Integer> getTotalLine2() {
		return totalLine2;
	}

    public String getTitleTHREE(){
    	return titleTHREE;
    }
    public String getTitleFOUR(){
    	return titleFOUR;
    }
    public String getCurrentCo11(){
    	return currentCo11;
    }
    public String getColHeading(){
    	return colHeading;
    }
    public JSONArray getArray7(){
    	return array7;
    }
    public JSONObject getObj2a(){
    	return obj2a;
    }
    public HashMap<String,Integer> getTotalQuantity3(){
    	return totalQuantity3;
    }



	
}