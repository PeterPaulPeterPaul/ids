package com.ids.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ids.entities.Year;

public class YearArray {

	private  JSONArray jsonYearArray = new JSONArray();
	private  JSONObject jsonYearObject = new JSONObject();
	
	public YearArray(String horizonalHeading, List<Year> years) {
		
		jsonYearArray.put(horizonalHeading);
		for (Year y : years) {
			jsonYearArray.put(y.getId());
		}

  	  try {
		jsonYearObject.put("columns", jsonYearArray);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}
	
	public JSONArray getJsonYearArray(){
		return this.jsonYearArray;
	}
	
	public JSONObject getJsonYearObject(){
		return this.jsonYearObject;
		
	}
	
}
