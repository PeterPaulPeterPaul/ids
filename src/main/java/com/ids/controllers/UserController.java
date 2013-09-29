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
@RequestMapping(value="/user")
@Transactional
public class UserController {


	private final static Logger logger = Logger.getLogger(UserController.class.getName()); 
   	private Connection con;

		 private boolean found = false;
	 @Transactional
	 @RequestMapping( method = RequestMethod.POST)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException, IllegalBlockSizeException, BadPaddingException {	   


		 


	       User  user = null;	    

	  	 

	    
	    
	       DriverManager.registerDriver(new AppEngineDriver());
	        con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","123smiggles321","Wednesday");

	        Statement statement = con.createStatement();
	        
			 con.setAutoCommit(false);
			 

			 
			 if (request.getParameter("newPass") != null && !request.getParameter("newPass").trim().equals("")) {
				 
				 String newPass = request.getParameter("newPass");
				 String userID = request.getParameter("userID");
				 
		    	  if (newPass.contains(" ") || newPass.length() < 8 ) {
			    	     model.addAttribute("returnedText","Password must not contain spaces and must be at least 8 characters long");
			    	     return "createUser";
		    	  }
		    	  
		        	    		
		        		   String  query = "UPDATE  ids_users set passwordId = '" + newPass + "' " +
		        		            " WHERE userId = '"+userID+"' ";

		        				   
		        		     logger.warning("update user: "+query);
		        		     try{
		        		       statement.executeUpdate(query);
		        		       con.commit();
		        		       model.addAttribute("returnedText","Password successfully changed!");
		        		     } catch(Exception ex) {
		        		    	 ex.printStackTrace();
		        		    	 logger.warning( "failed!" );
		        		    	 model.addAttribute("returnedText","SQL error (each User needs a unique Id name), check logs");
		        		     }
			 		         
		        	      
		        	      

		    	  con.close();
			      return "createUser";
				 
			 }
			 
             
			 
			 
	      if  ( request.getParameter("theirUserName") != null && !request.getParameter("theirUserName").trim().equals("")
	    		  && request.getParameter("theirId") != null &&  !request.getParameter("theirId").trim().equals("")
	              && request.getParameter("theirPassword") != null && !request.getParameter("theirPassword").trim().equals("") ){

	    	  String theirUserName = request.getParameter("theirUserName").trim();
	    	  String theirUserId = request.getParameter("theirId").trim();
	    	  String theirPassword = request.getParameter("theirPassword").trim();
	    	  
	    	  if (theirUserId.contains(" ") ) {
		 	    	model.addAttribute("returnedText","User Id must not contain spaces");
			    	return "createUser";
	    	  }
	    	  
	    	  if (theirPassword.contains(" ") || theirPassword.length() < 8 ) {
		    	     model.addAttribute("returnedText","Password must not contain spaces and must be at least 8 characters long");
		    	     return "createUser";
	    	  }
	    	  
	        	    		
	        		   String  query = "INSERT into  ids_users " +
	        		     		" (  userId, passwordId, user_name )" +
	        		     		" values ('"+theirUserId+"','"+theirPassword+"','"+theirUserName+"' ) ";
	        				   
	        		     logger.warning("update user: "+query);
	        		     try{
	        		       statement.executeUpdate(query);
	        		       con.commit();
	        		       model.addAttribute("returnedText","User successfully created!");
	        		     } catch(Exception ex) {
	        		    	 ex.printStackTrace();
	        		    	 logger.warning( "failed!" );
	        		    	 model.addAttribute("returnedText","SQL error (each Admin user needs a unique Id name), check logs");
	        		     }
		 		         
	        	      } else {
	 		    	     logger.info("A password MUST be over 6 characters long, contain numbers, and MIXED case letters");
	 		    	     model.addAttribute("returnedText","A password MUST be over 6 characters long, contain numbers, and MIXED case letters");
	        	      }
	        	      

	    	  con.close();
		      return "createUser";
	   //   }
	      
	      
	      

		//return "error";
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