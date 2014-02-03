package com.ids.controllers;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ids.businessLogic.DownloadExcel;
import com.ids.businessLogic.DropdownInterface;
import com.ids.businessLogic.FirstTimeQuery;
import com.ids.businessLogic.StoreRequestParameters;
import com.ids.context.GetBeansFromContext;
import com.ids.json.ColumnModel;
import com.ids.json.ColumnNameArray;
import com.ids.json.ColumnSummaryNameArray;
import com.ids.json.JsonGroupSummaryWithinLoop;
import com.ids.json.JsonPackaging;
import com.ids.json.JsonSummaryWithinLoop;
import com.ids.json.JsonWithInLoop;
import com.ids.sql.SQL1;
import com.ids.sql.SQL1GrpSummary;
import com.ids.sql.SQL1Summary;
import com.ids.sql.SQL2;
import com.ids.sql.SQL3;
import com.ids.sql.SQL4;
import com.ids.sql.SQL5;
import com.ids.sql.SQL6;
import com.ids.user.User;
import com.mysql.jdbc.PreparedStatement;

@Controller
@RequestMapping(value="/addcompany")
public class AddCompanyController implements DropdownInterface {
	
	private Connection con;

   	private final static Logger logger = Logger.getLogger(AddController.class.getName()); 
       
   	
    //   HashMap<Integer,Integer> totalLine = null;
       HashMap<String,Integer> totalLine2 = null;
   //    HashMap<Integer,Integer> otherLine = null;

	 
	 @RequestMapping( method = RequestMethod.GET)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) {	   

		 try{
		 logger.warning("Entering Add Company");

		 
		 GetBeansFromContext gcfc = new GetBeansFromContext();
		 con = gcfc.myConnection();
		  
		  
	      Statement statement = con.createStatement();

	      ResultSet resultSet = null;
	      HttpSession session = request.getSession(true);
	      User user = (User) session.getAttribute("myUser");
	 		 if (user==null ) {
	   		      model.addAttribute("errortext","You must logon before you can access IDS");

	   		   	  return "login";
	   		 }
	      
	 		 if (request.getParameter("exit")!= null ){
	 			 if (session.getAttribute("myUser") != null) {
	 			    session.setAttribute("myUser",null);
	 			 }

	 			return "login"; 
	 		 }
	 		 

	 		 
	 		String      query = " select 'found' as found from ids_users where userId = '"+user.getUserName()
					  +"' and passwordId = '"+user.getPassword()+"'";

			 resultSet = statement.executeQuery(query);

	           boolean found = false;
			   while (resultSet.next()) {
				   if (resultSet.getString("found").equals("found")) {
					   found = true;
					   break;
				   }
				   break;
			   }
			   if (!found) {
		   		      model.addAttribute("errortext","Invalid user credentials");

		   		   	  return "login";
			   }
			   
		//	   model.addAttribute("openOrClose","close");
			   
				    query = " select MAX(id)+1 as maxy from Company where access = '"+request.getParameter("accessType")+"'";

				 resultSet = statement.executeQuery(query);
               int newId=0;
                     while (resultSet.next()) {
					   newId =  resultSet.getInt("maxy");
				   }
			   
			   


            	   String newSQL = "Insert into Company ( name,  access,shortname, id) " +
            	   		" values ('"+request.getParameter("company")+"','"+request.getParameter("accessType")+"','"+
            	   		request.getParameter("company").replace(" ", "").substring(0,3).toUpperCase()+"',"+newId+") " ;
            	   logger.warning("InsertSQL: "+newSQL);
            	   PreparedStatement statement2 = (PreparedStatement) con.prepareStatement(newSQL);
            	   int retval = statement2.executeUpdate();

                con.commit();

		 }catch(Exception e) {
			 logger.log(Level.SEVERE,"Error",e);
		 }

		 return "createUser";
	 }
	 
	 @Transactional
	 @RequestMapping( method = RequestMethod.POST)
	public String postMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model)  {
		 
		 logger.warning("Entering application via POST");


		 return getMethodOne(
		            response,
					request,
					model);
	 }
	 
	
}

