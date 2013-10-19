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
	       
	       if (request.getParameter("currentAccess")!= null && !request.getParameter("currentAccess").equals("")) {
	    	   
	    	   
		   		 HttpSession session = request.getSession(true);
			     User user2 = (User)  session.getAttribute("myUser");
			     if (user2==null) {
			    	 logger.debug("no user");
			    	 return "redirect:login";
			     }
			     user2.setCurrentLocation(request.getParameter("currentAccess"));
			     session.setAttribute("myUser", user2) ; 
			     logger.debug("going to main");
		        	model.remove("displaytype2");
				    model.remove("passwordlab");
				    if (user2.getAccess().equals("e")){
	        	       return "redirect:editor";
				    }else{
				    	 return "redirect:main";
				    }
	    	   
	       }

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

	        
			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 con = gcfc.myConnection();

			 
		      Statement statement = con.createStatement();

		      ResultSet resultSet = null;
		      
		     
		String      query = " select 'found' as found, access, world, china, india, locked from ids_users where userId = '"+request.getParameter("userId")
				  +"' and passwordId = '"+request.getParameter("password")+"'";

		 String access="";
		 int world=0;
		 int china=0;
		 int india=0;
		 resultSet = statement.executeQuery(query);

		 String locked = "0";
           boolean found = false;
		   while (resultSet.next()) {
			   if (resultSet.getString("found").equals("found")) {
				   found = true;
				   access= resultSet.getString("access");
				   world = resultSet.getInt("world");
				   china = resultSet.getInt("china");
				   india = resultSet.getInt("india");
				   locked = resultSet.getString("locked");
			   }
		   }

  
	        if (locked.equals("1")) {
	        	logger.debug("user locked");
		    	model.addAttribute("displaytype","block");
		    	model.addAttribute("userId",request.getParameter("userId"));
		    	model.addAttribute("errortext","UserId is locked - contact Administrator");
	        	return "login";
	        }
	        
	        
	        if (found ) {
	        	
	        	String currentLocation="";
	        	if (india==1) {
	        		currentLocation="i";
	        	}
	        	if (china==1) {
	        		currentLocation="c";
	        	}
	        	if (world==1) {
	        		currentLocation="w";
	        	}

	        	user = new User(request.getParameter("userId"), request.getParameter("password"), access, world, china, india,currentLocation);
	        	
		   		 HttpSession session = request.getSession(true);
			       session.removeAttribute("myUser");
			       session.setAttribute("myUser", user);
			       
			       
	        	model.remove("displaytype2");
			    model.remove("passwordlab");
			    model.remove("buildversion");
			    
			 if (!access.equals("a")){
				 if (access.equals("e")) {
					 logger.debug("going down the correct path");
					 return "redirect:editor";
				 } else {
	        	    return "redirect:main";
				 }
			 }else {
				   return "redirect:setup2";
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