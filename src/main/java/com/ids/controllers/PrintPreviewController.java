package com.ids.controllers;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.ids.context.GetBeansFromContext;
import com.ids.json.JsonGroupSummaryWithinLoop;
import com.ids.user.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * 
 * @author John Hawthorne
 * the main controller for on entry into the web,
 *
 */

@Controller
@RequestMapping(value="/print")
@Transactional
public class PrintPreviewController {


	private final static Logger logger = Logger.getLogger(PrintPreviewController.class.getName()); 
   	private Connection con;

		 private boolean found = false;
	 @Transactional
	 @RequestMapping( method = RequestMethod.POST)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException, IllegalBlockSizeException, BadPaddingException {	   


		  JSONObject myData = null;
		  JSONObject myTotals = null;
		  try {
			 myData =  new JSONObject(request.getParameter("jsonStuff"));
			 myTotals = new JSONObject(request.getParameter("jsonTotals"));
		} catch (JSONException e) {
			logger.warning("it failed: " +e.getMessage() );
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		  
		  Enumeration keys = request.getParameterNames();  
		   while (keys.hasMoreElements() )  
		   {  
		      String key = (String)keys.nextElement();  
		   
		      //To retrieve a single value  
		      String value = request.getParameter(key);  
		      logger.warning("key: "+key+" value: "+value);    
		   
  
		   } 
		  
		  
		  StringBuffer sb = new StringBuffer();

		  sb.append("<h2 style='text-align:center'>" + request.getParameter("title1") + " " + request.getParameter("title2") + " "+
				  request.getParameter("title3") + " " + request.getParameter("title4") + "</h2>" );
		  
		  
			if(myData.has("tabData")){
				try {
					logger.warning("it has tabData");

					JSONArray clobArray = myData.getJSONArray("tabData");
				

					JSONObject jo1 = clobArray.getJSONObject(1);
					logger.warning("size1 is: "+clobArray.length());

					List<String> columnHeaders = new ArrayList<String>();
					if (jo1.has("columns")){
						logger.warning("it has myData");
						JSONArray myArray = jo1.getJSONArray("columns");
						logger.warning("myArray size: "+myArray.length());
						
						sb.append("<table  style='border-collapse:collapse;border:1px solid black;margin: 0px auto;'><tr>");
						for (int i = 0; i < myArray.length();i++){
							   sb.append("<td>"+myArray.getString(i)+"</td>");
							columnHeaders.add(myArray.getString(i));

						}
					}
					sb.append("</tr>");
					
					
					JSONObject jo2 = clobArray.getJSONObject(2);
					logger.warning("size2 is: "+jo2.length());

					if (jo2.has("myData")){
						logger.warning("it has myData");
						JSONArray myDataArray = jo2.getJSONArray("myData");
						logger.warning("myData size: "+myDataArray.length());
						sb.append("<tr>");
						for (int i = 0; i < myDataArray.length();i++){
							
							sb.append("<tr>");
							 int j=0;
							for (String s : columnHeaders){
								   j+=1;
							   try{
									   sb.append("<td>"+myDataArray.getJSONObject(i).getString(s).trim().replaceAll(",","")+"</td>");
							   }catch(Exception e){
								   sb.append("<td>0</td>");
							   }
							}
							sb.append("</tr>");
						}
						

						JSONArray totals = myTotals.getJSONArray("myTotals");

						
						 int j=0;
						 sb.append("<tr>");
							for (String s : columnHeaders){
								   j+=1;
							   try{
								   sb.append("<td>"+totals.getJSONObject(0).getString(s).trim().replaceAll(",","")+"</td>");

							   }catch(Exception e){
								   if (j==1) {
									   sb.append("<td>TOTAL</td>"); 
								   }else{
								       sb.append("<td>0</td>");
								   }
							   }
							}
					 sb.append("</tr></table>");
						
					}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		  
		  
		  logger.warning("got in here");

			 model.addAttribute("printTable",sb.toString());
		  return "printPreview";
			

	  }
	

	 
	 
	 @Transactional
	 @RequestMapping( method = RequestMethod.GET)
	public String postMethodTwo(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException {
		 
		 logger.info("Entering application via GET");


		 try {
			return getMethodOne(
			            response,
						request,
						model);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	 }
	   
}