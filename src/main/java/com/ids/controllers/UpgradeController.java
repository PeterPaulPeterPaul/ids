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
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
@RequestMapping(value="/upgrade")
@Transactional
public class UpgradeController {


	private final static Logger logger = Logger.getLogger(UpgradeController.class.getName()); 
   	private Connection con;

		 private boolean found = false;
	 @Transactional
	 @RequestMapping( method = RequestMethod.POST)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException, IllegalBlockSizeException, BadPaddingException {	   


		 


	       User  user = null;	    
			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 con = gcfc.myConnection();
	        Statement statement = con.createStatement();
	        
			 con.setAutoCommit(false);
			 

			 
			
		    	  
		        	    		
		        		   String  query = "UPDATE  ids_users set world = '" + request.getParameter("world") + "', china ='"  +
		        		   		 request.getParameter("china") +
		        				    "', india = '"+request.getParameter("india")+"' where userId = '"+request.getParameter("userId")+"' ";

		        		   logger.warning("QUERY: "+query);
		        		     try{
		        		       statement.executeUpdate(query);
		        		       con.commit();
		        		       model.addAttribute("returnedText","Upgrade complete");
		        		     } catch(Exception ex) {
		        		    	 ex.printStackTrace();
		        		    	 logger.warning( "failed!" );
		        		    	 model.addAttribute("returnedText","SQL error (each User needs a unique Id name), check logs");
		        		     }
			 		         
		        	      
		        	      

		    	  con.close();
			      return "createUser";
				 
			 }
			 


	 
	 
	 @Transactional
	 @RequestMapping( method = RequestMethod.GET)
	public String postMethodTwo(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException {
		 
		 logger.warning("Entering application via GET");


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