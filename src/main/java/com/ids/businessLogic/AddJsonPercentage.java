package com.ids.businessLogic;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.ui.ModelMap;

import com.ids.controllers.MainController;
import com.ids.json.GetTotalCellValue;

public class AddJsonPercentage {

	private int myTotal =0;
	private int currTotal =0;
	private GetTotalCellValue  getTotalCellValue;
	private final static Logger logger = Logger.getLogger(AddJsonPercentage.class.getName()); 
	public  AddJsonPercentage( JSONObject myData, JSONObject totals) throws JSONException{
		
		try{
			if(totals.has("myTotals")){
			
			    JSONArray clobArray = totals.getJSONArray("myTotals");
			    JSONObject jo0 = clobArray.getJSONObject(0);
			    getTotalCellValue = new GetTotalCellValue(jo0); 
			
			}
			
			if(myData.has("tabData")){
				
				JSONArray clobArray = myData.getJSONArray("tabData");
				JSONObject jo1 = clobArray.getJSONObject(1);
			
			    if (jo1.has("columns")){
				   logger.warning("it has myData");
				   JSONArray myArray = jo1.getJSONArray("columns");
				   HashMap<String,Integer> headers = new HashMap<String,Integer>();
				   for (int i = 1; i < myArray.length();i++){
					   int value = getTotalCellValue.getTotal(myArray.getString(i));
					   headers.put(myArray.getString(i),value);
				   }
				   
				   Iterator it = headers.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pairs = (Map.Entry)it.next();
				        logger.warning(pairs.getKey() + " = " + pairs.getValue());
				        it.remove(); // avoids a ConcurrentModificationException
				    }
				
			    }
			}
		}catch(Exception e) {
			logger.warning("failed here");
			e.printStackTrace();
			}
			
	
	}
	
	public int getTotal() {
		return myTotal;
	}
	
	
}
