package com.ids.json;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonWithInLoop {
    private  HashMap<String,Integer> totalLine2 = null;
  //  private HashMap<String,Integer> otherLine2 = null;
    private String titleTHREE="";
    private String titleFOUR="";
    private String currentCo11="";
    private JSONArray array7;
    private JSONObject obj2a;
    private String colHeading;
	 
    private  int totalQuantity1=0;

	public void  loopProcess(String ONE, String TWO, String THREE, String FOUR, String quantity,
			HashMap<String,Integer> totalLine2In,
			 String currentCo11In, JSONArray array7In,
			JSONObject obj2aIn, String colHeadingIn, boolean hasAll) throws NumberFormatException, SQLException, JSONException{
		
		totalLine2 = totalLine2In;
	//	otherLine2 = otherLine2In;
		currentCo11= currentCo11In;
		array7 = array7In;
		obj2a = obj2aIn;
		colHeading = colHeadingIn;
		
		titleTHREE = THREE;
		titleFOUR = FOUR;
		
		if (hasAll) {
		//   if (ONE.equals("ALL COMPANIES"))  {
    		  int totalQuantity=0;
    		  if (totalLine2.get(TWO)!= null) {
    			 totalQuantity= totalLine2.get(TWO);
    		  }
    		  totalQuantity += Integer.parseInt(quantity);
    		  totalLine2.put(TWO, totalQuantity);
			//  return;
		//   }

	//	   int otherQuantity=0;
	//	   if (otherLine2.get(TWO)!= null) {
	//		 otherQuantity= otherLine2.get(TWO);
	//	   }
	//	   otherQuantity += Integer.parseInt(quantity);
	//	   otherLine2.put(TWO, otherQuantity);
		} else {
			   totalQuantity1=0;
			   if (totalLine2.get(TWO)!= null) {
				   totalQuantity1= totalLine2.get(TWO);
				   System.out.println("(1) totalQuantity1: "+totalQuantity1+" TWO: "+TWO);
			   }
			   totalQuantity1 += Integer.parseInt(quantity);
			   System.out.println("(2) totalQuantity1: "+totalQuantity1+" TWO: "+TWO);
			   totalLine2.put(TWO, totalQuantity1);
		}
		
		
		if (!currentCo11.equals(ONE))  {
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
//	public HashMap<String,Integer> getOtherLine2() {
//		return otherLine2;
//	}
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



	
}
