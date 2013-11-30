package com.ids.controllers;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

public class DownDataBaseController extends HttpServlet {
	
	private final static Logger logger = Logger.getLogger(Down.class.getName()); 
	
  public void doGet(HttpServletRequest request,
                    HttpServletResponse resp)
      throws ServletException, IOException {
      
    // Use "request" to read incoming HTTP headers (e.g. cookies)
    // and HTML form data (e.g. data the user entered and submitted)
    
    // Use "response" to specify the HTTP response line and headers
    // (e.g. specifying the content type, setting cookies).
    

	  logger.warning("got in here");

	  
	  BufferedReader br = new BufferedReader(new FileReader("../pages/world.csv"));
	    try {
	        String line = br.readLine();

	        while (line != null) {
               logger.warning("LINE: "+line);
	            line = br.readLine();
	        }
	       
	    } finally {
	        br.close();
	    }

    		   

		

  }
  
  
  public void doPost(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException{
	  
	  doGet(request, response);
  }
  
	
  
}