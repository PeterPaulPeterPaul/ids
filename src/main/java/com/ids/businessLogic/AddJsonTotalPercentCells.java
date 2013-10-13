package com.ids.businessLogic;

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

public class AddJsonTotalPercentCells {

	private int myTotal =0;
	private final static Logger logger = Logger.getLogger(AddJsonTotalPercentCells.class.getName()); 
	public  AddJsonTotalPercentCells( JSONObject myTotals, List<ColumnsAndTotals> headers) throws JSONException{
		

		try{
		if(myTotals.has("myTotals")){
			
			//myData.put("TOTAL",totalTotal);
			JSONArray clobArray =myTotals.getJSONArray("myTotals");
			JSONObject jo0 = clobArray.getJSONObject(0);
			for (ColumnsAndTotals h : headers) {
				if (h.getColName().contains("PPC")){
			        jo0.put(h.getColName(),"100");
				}
			}
			clobArray.put(0,jo0);
			
			myTotals.put("myTotals",clobArray);
			//JSONArray myArray = jo0.getJSONArray("");

			//JSONObject obj1a = new JSONObject();
			//obj1a.put("TOTAL",totalTotal);
			//clobArray.put(clobArray.length(),obj1a);
			//myData.put("myTotals",clobArray);
			
		}
		
		logger.warning("HHH: "+ myTotals);
		
		}catch(JSONException je) {
			je.printStackTrace();
			logger.warning(je.getMessage());
		}
	}
	
	
	
	
}
