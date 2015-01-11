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
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.ids.context.GetBeansFromContext;
import com.ids.json.JsonGroupSummaryWithinLoop;
import com.ids.user.*;
import com.mysql.jdbc.PreparedStatement;

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


	private final static Logger logger = Logger.getLogger(MainController.class.getName()); 
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
		 model.addAttribute("displaytypePassword","style='display:none'");

		 
	       User  user = null;	
	       
	       if (request.getParameter("currentAccess")!= null && !request.getParameter("currentAccess").equals("")) {
	    	   
	    	   
		   		 HttpSession session = request.getSession(true);
			     User user2 = (User)  session.getAttribute("myUser");
			     if (user2==null) {

			    	 return "redirect:login";
			     }
			     user2.setCurrentLocation(request.getParameter("currentAccess"));
			     session.setAttribute("myUser", user2) ; 

		        	model.remove("displaytype2");
				    model.remove("passwordlab");
				   
				    
				    if (user2.getAccess().equals("e")){
	        	       return "redirect:editor";
				    }else{
				    	 return "redirect:main";
				    }
	    	   
	       }

	       model.addAttribute("displaytypePassword","style='display:none'");
	       
	    if (request.getParameter("fromJsp")==null || request.getParameter("fromJsp").equals("") ) {

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
			 String currentPassword="";
			 try {
			 con = gcfc.myConnection();

			 currentPassword = request.getParameter("password");
			 
		        if ( request.getParameter("secondNewPassword") != null &&  !request.getParameter("secondNewPassword").equals("")){
		        	
		        	 Statement statement = con.createStatement();
		        	
		        	String update = "Update ids_users set passwordId = '"+request.getParameter("secondNewPassword")+"', "
		        			+ " locked=0  where userId = '"+request.getParameter("userId")
					  +"' and passwordId = '"+request.getParameter("password")+"'";
		        	
		        	 java.sql.PreparedStatement ps = con.prepareStatement(update);
		        	 ps.executeUpdate();
		        	 
		        	 currentPassword =request.getParameter("secondNewPassword");
		        }
		        
			 
		      Statement statement = con.createStatement();

		      ResultSet resultSet = null;
		      
		     
		String      query = " select 'found' as found, access, world, china, india, locked from ids_users where userId = '"+request.getParameter("userId")
				  +"' and passwordId = '"+currentPassword+"'";

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

		   System.out.println("WELL WHAT DOES LOCKED CONTAIN: "+locked);
  
	        if (locked.equals("1")) {

		    	model.addAttribute("displaytype","block");
		    	model.addAttribute("userId",request.getParameter("userId"));
		    	model.addAttribute("errortext","UserId is locked - contact Administrator");
		    	 model.addAttribute("displaytypePassword","style='display:none'");
		    	System.out.println("DOWN 1");
	        	return "login";
	        }
	        
	        if (locked.equals("2")) {

		    	model.addAttribute("displaytype","block");
		    	model.addAttribute("userId",request.getParameter("userId"));
		    	model.addAttribute("errortext","First time of entry reset password");
		    	model.addAttribute("displaytypePassword","");
		    	model.addAttribute("myusername",request.getParameter("userId"));
		    	model.addAttribute("mypassword",request.getParameter("password"));
		    	System.out.println("DOWN 2");
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

	        	user = new User(request.getParameter("userId"), currentPassword, access, world, china, india,currentLocation);
	        	
		   		 HttpSession session = request.getSession(true);
			       session.removeAttribute("myUser");
			       session.setAttribute("myUser", user);
			       
			       System.out.println("got as far as setting up user");
			       
	        	model.remove("displaytype2");
			    model.remove("passwordlab");
			    model.remove("buildversion");
			    
			 if (!access.equals("a")){
				 if (access.equals("e")) {
                     con.close(); 
					 return "redirect:editor";
				 } else {
					 con.close();
	        	    return "redirect:main";
				 }
			 }else {
				 con.close();
				   return "redirect:setup2";
			 }
			 
			 
	        } 

	        if (!found) {

		    	model.addAttribute("displaytype","block");
		    	model.addAttribute("userId",request.getParameter("userId"));
		    	model.addAttribute("errortext","invalid password or userId");
		    	
		    	 model.addAttribute("displaytypePassword","style='display:none'");
		    	
		    	
		    	 con.close();
	        	return "login";
	        }
	        
			 } finally {
					logger.warning("just before ending");
					   gcfc.closeCon();
					if (con  != null) {
						try{
				        con.close();

						}
				        catch(Exception e) {
				        	logger.warning("weird error");
				        }
					}
			 }

		return "error";
	}
	

	 
	 
	 @Transactional
	 @RequestMapping( method = RequestMethod.GET)
	public String postMethodTwo(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException {
		 

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