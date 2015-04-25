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

public class AddJsonRowTotal {

	private int myTotal =0;
	private final static Logger logger = Logger.getLogger(AddJsonRowTotal.class.getName()); 
	public  AddJsonRowTotal( JSONObject myData) throws JSONException{
		
		try{
		if(myData.has("tabData")){
			
			JSONArray clobArray = myData.getJSONArray("tabData");
			JSONObject jo1 = clobArray.getJSONObject(1);
			JSONObject jo0 = clobArray.getJSONObject(0);


			List<String> columnHeaders = new ArrayList<String>();


			if (jo1.has("columns")){
		//		logger.warning("it has myData");
				JSONArray myArray = jo1.getJSONArray("columns");
				
		//		logger.warning("myArray size: "+myArray.length());
				for (int i = 1; i < myArray.length();i++){
					columnHeaders.add(myArray.getString(i));
				}
			}
			
			
			
			JSONObject jo2 = clobArray.getJSONObject(2);
		//	logger.warning("size1 is: "+clobArray.length());
			
			JSONArray myDataArray = jo2.getJSONArray("myData");
		//	logger.warning("myData size: "+myDataArray.length());
			for (int i = 0; i < myDataArray.length();i++){
				int myCurrTotal=0;
				for (String s : columnHeaders ) {
					try{
				//      logger.warning(s+": "+myDataArray.getJSONObject(i).getString(s).trim().replaceAll(",",""));
				      myCurrTotal+= Integer.parseInt(myDataArray.getJSONObject(i).getString(s).
				    		                          trim().replaceAll(",",""))  ;
					} catch(Exception ee) {
						myCurrTotal+=0;
					}
				}
				
				myTotal+=myCurrTotal;
				myDataArray.getJSONObject(i).put("TOTAL", myCurrTotal);

				
			//	logger.warning("MyCurrTotal: "+myCurrTotal);
			}

			
			jo2.put("myData",myDataArray);
			clobArray.put(2,jo2);
			
			
		}
		
//		logger.warning("HHH: "+ myData);
		
		}catch(JSONException je) {
			je.printStackTrace();
			logger.warning(je.getMessage());
		}
	}
	
	public int getTotal() {
		return myTotal;
	}
	
	
}
