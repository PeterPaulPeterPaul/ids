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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
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

//import com.google.appengine.api.rdbms.AppEngineDriver;
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
		  
		  
		  
		  StringBuffer sb = new StringBuffer();
		  
		  
		  Date d = new Date();
		  
		  

		  String myString = DateFormat.getDateInstance(DateFormat.LONG).format(d);

		  String header = "<div class=\"box\"><span class=\"IDSheader\"> "+
		" <p style=\"text-align:center; margin-top:10px\">"+
		" <img src=\"images/OHR-logo.png\" alt=\"Off-Highway Research\">"+
		"</p>  "+

		"</span>"+
		 "<p style='text-align:center'> Date: "+myString +"</p>"+
		"<h2 style='text-align:center'>" + request.getParameter("title1") + " " + request.getParameter("title2") + " "+
		  request.getParameter("title3") + " " + request.getParameter("title4") + "</h2>" +
		"</div>";
			
		  sb.append(header);

		  
		  
			if(myData.has("tabData")){
				try {

					JSONArray clobArray = myData.getJSONArray("tabData");
				

					JSONObject jo1 = clobArray.getJSONObject(1);

					List<String> columnHeaders = new ArrayList<String>();
					JSONArray myArray = null;
					if (jo1.has("columns")){
						
						int pageSize = 0;

						 myArray = jo1.getJSONArray("columns");

						sb.append("<table  style='width:100%; border:1px solid black;margin: 0px auto;'><tr>");
						for (int i = 0; i < myArray.length();i++){
							   sb.append("<td>"+myArray.getString(i)+"</td>");
							columnHeaders.add(myArray.getString(i));

						}
					}
					sb.append("</tr>");
					
					
					JSONObject jo2 = clobArray.getJSONObject(2);
					logger.warning("size2 is: "+jo2.length());

					if (jo2.has("myData")){
						JSONArray myDataArray = jo2.getJSONArray("myData");

						sb.append("<tr>");
		
						int pageSize = 0;
						for (int i = 0; i < myDataArray.length();i++){
							
							pageSize+=1;
							if (pageSize>= 20) {

                                    pageSize=0; 
                                 
                                    sb.append("</table>");
                                    sb.append("<div class=\"pagebreak\">&nbsp</div>");
                                    sb.append(header);

                                    
									sb.append("<table  style='width:100%; border:1px solid black;margin: 0px auto;'><tr style='width:100%'>");

									for (int j = 0; j < myArray.length();j++){
										   sb.append("<td>"+myArray.getString(j)+"</td>");


									}

							        sb.append("</tr>");

							}
							
							sb.append("<tr style='width:100%'>");
							 int j=0;
							NumberFormat nf = NumberFormat.getInstance();
							
							for (String s : columnHeaders){
								   j+=1;
							   try{
								   if (j==1) {
									   sb.append("<td>"+myDataArray.getJSONObject(i).getString(s).trim().replaceAll(",","")+"</td>");
								   }else{
									   sb.append("<td>"+nf.format(Integer.parseInt(myDataArray.getJSONObject(i).getString(s).trim().replaceAll(",","")))+"</td>");  
								   }
							   }catch(Exception e){
								   sb.append("<td>0</td>");
							   }
							}
							sb.append("</tr>");
						}
						

						JSONArray totals = myTotals.getJSONArray("myTotals");

						
						 int j=0;
						 sb.append("<tr  style='width:100%'>");
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