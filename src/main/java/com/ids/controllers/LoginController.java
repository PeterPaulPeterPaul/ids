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

import org.slf4j.Logger;
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
@RequestMapping(value="/login")
@Transactional
public class LoginController {


       static final Logger logger = LoggerFactory.getLogger(LoginController.class);
   	private Connection con;

		 private boolean found = false;
	 @Transactional
	 @RequestMapping( method = RequestMethod.POST)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException, IllegalBlockSizeException, BadPaddingException {	   


		 model.addAttribute("displaytype2","none");
		 model.addAttribute("passwordlab","Password");
		 




 

		 
 
		 

		 
	       User  user = null;	    

	    if (request.getParameter("fromJsp")==null || request.getParameter("fromJsp").equals("") ) {
	    	  logger.debug("first time in JSP not called");
		    	model.addAttribute("displaytype","none");
	    	return "login";
	    }
	  	 
	    if (request.getParameter("userId")==null || request.getParameter("password")==null
	    		|| request.getParameter("userId").equals("") || request.getParameter("password").equals("") ) {
	    	model.addAttribute("displaytype","block");
	    	model.addAttribute("userId",request.getParameter("userId"));
	    	model.addAttribute("errortext","you must enter password and userId");
	    	return "login";
	    }
	    
	    if (request.getParameter("firstNewPassword") != null && !request.getParameter("firstNewPassword").equals(""))  {
	    	if (request.getParameter("secondNewPassword") == null || 
	    			!request.getParameter("secondNewPassword").equals(request.getParameter("firstNewPassword"))
	    			) {
 		    	  model.addAttribute("displaytype","block");
 		    	  model.addAttribute("userId",request.getParameter("userId"));
 		    	  model.addAttribute("errortext","Both new passwords must match");
 		    	  model.addAttribute("displaytype2","inline-block");
 		    	  model.addAttribute("passwordlab","OLD Password");
		    	return "login";
	    	}
	    }
	    
	    if (request.getParameter("userId").contains("|") || request.getParameter("userId").contains("'") 
	    		|| request.getParameter("userId").contains("\"") 
	    	|| request.getParameter("password").contains("|") || request.getParameter("password").contains("'") 
	    	|| request.getParameter("password").contains("\"")  ) {
	    	model.addAttribute("displaytype","block");
	    	model.addAttribute("userId",request.getParameter("userId"));
	    	model.addAttribute("errortext","You cannot have quotes or pipes in login fields");
	    	return "login";
	    }
	    
	    
		
	        if (!request.getParameter("secondNewPassword").equals("") ) {
	        	   Pattern p = Pattern.compile("(([A-Z].*[0-9])|([0-9].*[A-Z]))");
	        	   Matcher m = p.matcher(request.getParameter("secondNewPassword"));
	        	   boolean b = m.find();
	        	   p = Pattern.compile("(([a-z].*[0-9])|([0-9].*[a-z]))");
	        	   m = p.matcher(request.getParameter("secondNewPassword"));
	        	   if (b && m.find() && request.getParameter("secondNewPassword").length() > 6  &&  
	        			   !request.getParameter("secondNewPassword").equals(request.getParameter("password")) ) {

					try {
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	   
	        		   
	        		   

	        	   } else {
	   		    	  model.addAttribute("displaytype","block");
	   		    	  model.addAttribute("userId",request.getParameter("userId"));
	   		    	  model.addAttribute("errortext","A password MUST be over 6 characters long, contain numbers, and MIXED case letters");
	   		    	  model.addAttribute("displaytype2","inline-block");
	   		    	  model.addAttribute("passwordlab","OLD Password");

	   		    	  return "login";
	        	   }

		        }

	        
	   //     Connection c = null;
	       // try {
	        

	        DriverManager.registerDriver(new AppEngineDriver());
	       con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","123smiggles321","Wednesday");
		 
	  //        DriverManager.registerDriver(new AppEngineDriver());
	   //       con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","123smiggles321","Wednesday");
	         
	      //    GetBeansFromContext gcfc = new GetBeansFromContext();
	 	//	 con = gcfc.myConnection();
	          
		//	 GetBeansFromContext gcfc = null;
		//	 gcfc = new GetBeansFromContext();

			// con = gcfc.myConnection();

			 
		      Statement statement = con.createStatement();

		      ResultSet resultSet = null;
		      
		     
		String      query = " select 'found' as found, access from ids_users where userId = '"+request.getParameter("userId")
				  +"' and passwordId = '"+request.getParameter("password")+"'";

		 String access="";
		 resultSet = statement.executeQuery(query);

           boolean found = false;
		   while (resultSet.next()) {
			   if (resultSet.getString("found").equals("found")) {
				   found = true;
				   access= resultSet.getString("access");
			   }
		   }

  
	        if (found ) {
	        	try{
	            Statement statement2 = con.createStatement();
	        	String view = " drop view Facts ";
	        	statement2.executeUpdate(view);
	        	}catch(Exception e) {}
	        	
	        	if (!access.equals("e")) {
	        	  String view = " create view Facts as select * from Facts_"+access;
	        	  Statement statement2 = con.createStatement();
	        	  statement2.executeUpdate(view);
	        	}
	        	user = new User(request.getParameter("userId"), request.getParameter("password"), access);
	        	
		   		 HttpSession session = request.getSession(true);
			       session.removeAttribute("myUser");
			       session.setAttribute("myUser", user);
			       
			       
	        	model.remove("displaytype2");
			    model.remove("passwordlab");
			    model.remove("buildversion");
			    
			 if (!request.getParameter("userId").equals("changestuff")){
				 if (access.equals("e")) {
					 logger.debug("going down the correct path");
					 return "redirect:editor";
				 } else {
	        	    return "redirect:main";
				 }
			 }else {
				   return "setup2";
			 }
	        } 

	        if (!found) {
	        	logger.debug("user not found");
		    	model.addAttribute("displaytype","block");
		    	model.addAttribute("userId",request.getParameter("userId"));
		    	model.addAttribute("errortext","invalid password or userId");
	        	return "login";
	        }

		return "error";
	}
	

	 
	 
	 @Transactional
	 @RequestMapping( method = RequestMethod.GET)
	public String postMethodTwo(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException {
		 
		 logger.debug("Entering application via GET");


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