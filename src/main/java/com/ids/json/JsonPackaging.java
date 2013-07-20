package com.ids.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ids.businessLogic.DropdownInterface;




public class JsonPackaging implements DropdownInterface {

	private   JSONObject obj5;
	private   JSONObject obj8;
    static final Logger logger = LoggerFactory.getLogger(JsonPackaging.class);
    
	public JsonPackaging(JSONObject obj2a,JSONArray array7,String colHeading,HashMap<String,Integer> totalLine2,
			 String titleONE, String titleTWO,
			JSONObject model, JSONObject columnNameObject, String salesOrProduction, boolean hasAll ) throws JSONException{
		
		
		 if (obj2a != null) {
    	     array7.put(obj2a);
    	  }  
    	  /*
    	  if ( hasAll) {
    	    obj2a = new JSONObject();
    	    obj2a.put(colHeading,"OTHER");
    	    Iterator<Entry<String, Integer>> it = totalLine2.entrySet().iterator();
    	    while (it.hasNext()) {
    	        Entry<String, Integer> pairs = it.next();
    	        if (otherLine2.get(pairs.getKey())!=null) {
    	        	System.out.println("The substraction:(TOT) "+ pairs.getValue()+" - "+otherLine2.get(pairs.getKey()));
    	          obj2a.put(pairs.getKey(), pairs.getValue() - otherLine2.get(pairs.getKey()));
    	        } else {
    	           obj2a.put(pairs.getKey(), 0 );
    	        }   
    	     }
	    	 if (obj2a != null) {
	    		 System.out.println("putting array");
	    		 System.out.println(obj2a.toString());
		    	   array7.put(obj2a);
		     }
    	  }
    	  */
    	  JSONObject obj2Total = new JSONObject();
    	  obj2Total.put(colHeading,"TOTAL");
    	  Iterator<Entry<String, Integer>>  it = totalLine2.entrySet().iterator();
    	  while (it.hasNext()) {
    	        Entry<String, Integer> pairs = it.next();
    	        obj2Total.put(pairs.getKey(), pairs.getValue());
    	  }
	      if (obj2Total != null) {
	    	     JSONArray array8 = new JSONArray();
	    	     array8.put(obj2Total);
	    	     obj8 = new JSONObject();
	       	     obj8.put("myTotals", array8);
		  }

    	  TitleArray titleArray = new TitleArray(WordUtils.capitalizeFully(titleONE).replace(" Of", " of").replace(" By", " by"),
    			  WordUtils.capitalizeFully(titleTWO), salesOrProduction );
    	  
  JSONObject obj7 = new JSONObject();

  obj7.put("myData", array7);

  JSONArray array4 = new JSONArray();
  
  
  array4.put(model);
  array4.put(columnNameObject);
  
  array4.put(obj7);
  
  array4.put(titleArray.getJsonTitleObject());

  obj5 = new JSONObject();
  obj5.put("tabData", array4);


  logger.debug(obj5.toString());
		
	}
	
	
	public JSONObject getObj5() {
		return obj5;
	}
	
	public JSONObject getObj8() {
		return obj8;
	}
	
}
