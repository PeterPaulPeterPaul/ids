package com.ids.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TitleArray {

	private  JSONArray jsonTitleArray = new JSONArray();
	private  JSONObject jsonTitleObject = new JSONObject();
	
	public TitleArray(String part1, String part2, String part3) {
		
		jsonTitleArray.put(part1+" "+part2+" "+part3);
		try {
			jsonTitleObject.put("title", jsonTitleArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public JSONArray getJsonTitleArray(){
		return this.jsonTitleArray;
	}
	
	public JSONObject getJsonTitleObject(){
		return this.jsonTitleObject;
	}
	
}