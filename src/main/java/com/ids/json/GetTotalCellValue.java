package com.ids.json;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;

import com.ids.controllers.MainController;

public class GetTotalCellValue {

	private JSONObject totals;
	private final static Logger logger = Logger.getLogger(GetTotalCellValue.class.getName()); 
	public  GetTotalCellValue( JSONObject totals) {
		
		this.totals = totals;
	}
	
	public int getTotal(String key) {
		
		int myTotal=0;

		try{
			myTotal = (Integer) totals.get(key);
			
		}catch(JSONException e) {
			return 0;
		}
		
		return myTotal;
		
	}
	
	
	
	
}
