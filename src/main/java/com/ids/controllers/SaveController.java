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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.cloud.sql.jdbc.PreparedStatement;
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

@Controller
@RequestMapping(value="/saverow")
public class SaveController implements DropdownInterface {

	private Connection con;

   	private final static Logger logger = Logger.getLogger(SaveController.class.getName()); 
       
    //   HashMap<Integer,Integer> totalLine = null;
       HashMap<String,Integer> totalLine2 = null;
   //    HashMap<Integer,Integer> otherLine = null;

	 
	 @RequestMapping( method = RequestMethod.GET)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws SQLException, JSONException, IOException {	   

		 
		 logger.warning("Entering application via GEt");
		 logger.warning("excelDownload: "+request.getParameter("excelDownload"));
		 
		
		 Enumeration keys = request.getParameterNames();  
		   while (keys.hasMoreElements() )  
		   {  
		      String key = (String)keys.nextElement();  
		      logger.warning(key);  
		   
		      //To retrieve a single value  
		      String value = request.getParameter(key);  
		      logger.warning(value);  
		   
		      // If the same key has multiple values (check boxes)  
		      String[] valueArray = request.getParameterValues(key);  
		        
		      for(int i = 0; i > valueArray.length; i++){  
		    	  logger.warning("VALUE ARRAY" + valueArray[i]);  
		      }  
		   }  
		 
		 
		 

		   DriverManager.registerDriver(new AppEngineDriver());
		  con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","123smiggles321","Wednesday");
	//	 GetBeansFromContext gcfc = new GetBeansFromContext();
     //    gcfc = new GetBeansFromContext();
	// con = gcfc.myConnection();
		 
      //   DriverManager.registerDriver(new AppEngineDriver());
      //   con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","user","password");
       
		 
		  
		  
		  
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
			   
			   model.addAttribute("openOrClose","close");
			   
			   if (request.getParameter("save")!= null ) {
				   PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("Delete from Facts_w");
				   int retval = statement2.executeUpdate();
				   statement2 = (PreparedStatement) con.prepareStatement("insert into Facts_w select * from FactsEdit ");
				   retval = statement2.executeUpdate();
				   con.commit();
				   model.addAttribute("openOrClose","open");
				   return "redirect:editor";
 
			   }
			   
			   String year = "";
			   String productId = "";
			   String countryId = "";
			   String companyId = "";
			   String PorS ="";
			   String SQL = "";
			   
			   String value = request.getParameter("dimension1Name");
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension1Val");
               } else {
            	   
            	  SQL = " select id from "+value+ " where name = '" +request.getParameter("dimension1Val") +"' ";
            	  logger.warning("SQL1: "+SQL);
            	  resultSet = statement.executeQuery(SQL);
            	  while (resultSet.next()) {
            		  if (value.equals("Country")) {
            			  countryId= resultSet.getString("id");
            			  break;
            		  }
            		  if (value.equals("Company")) {
            			  companyId= resultSet.getString("id");
            			  break;
            		  }
            		  if (value.equals("Product")) {
            			  productId= resultSet.getString("id");
            			  break;
            		  }
            	  }

               }
               
               
               value = request.getParameter("dimension2Name");
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension2Val");
               } else {
             	  SQL = " select id from "+value+ " where name = '" +request.getParameter("dimension2Val").trim() +"' ";
             	 logger.warning("SQL2: "+SQL);
             	logger.warning("value2: "+value);
             	if (resultSet != null) {
             		resultSet.close();
             	}
             	
             	  resultSet = statement.executeQuery(SQL);
             	  while (resultSet.next()) {
             		  logger.warning("got in here ok");
             		  if (value.trim().equals("Country")) {
             			  logger.warning("come one we must have got in here");
             			  countryId= Integer.toString(resultSet.getInt("id"));
             			  break;
             		  }
             		  if (value.equals("Company")) {
             			  companyId=Integer.toString(resultSet.getInt("id"));
             			  break;
             		  }
             		  if (value.equals("Product")) {
             			  productId= Integer.toString(resultSet.getInt("id"));
             			  break;
             		  }
             	  }
               }
               
               
               value = request.getParameter("dimension3Name");
               
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension3Val");
               } else {
            	   SQL = " select id from "+value+ " where name = '" +request.getParameter("dimension3Val").trim() +"' ";
            	   logger.warning("SQL3: "+SQL);
            	   logger.warning("value3: "+value);
              	  resultSet = statement.executeQuery(SQL);
              	  while (resultSet.next()) {
              		  if (value.equals("Country")) {
              			  countryId= Integer.toString(resultSet.getInt("id"));
              			  break;
              		  }
              		  if (value.equals("Company")) {
              			  companyId= Integer.toString(resultSet.getInt("id"));
              			  break;
              		  }
              		  if (value.equals("Product")) {
              			  productId= Integer.toString(resultSet.getInt("id"));
              			  break;
              		  }
              	  } 
            	   
               }
               
               if (request.getParameter("dimension4Val").equals("Sales") ) {
            	   PorS= "1"; 
               } else {
            	   PorS = "2";
               }
               
               value = request.getParameter("dimension5Name");
               logger.warning("dimension5Name: "+value);
               
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension5Val");
               } else { 

            	   value = value.substring(0,value.indexOf(" "));
            	   SQL = " select id from "+value+ " where shortname = '" +request.getParameter("dimension5Val") +"' ";
            	   logger.warning("SQL5: "+SQL);
               	  resultSet = statement.executeQuery(SQL);
               	  while (resultSet.next()) {
               		  if (value.equals("Country")) {
               			  countryId= resultSet.getString("id");
               			  break;
               		  }
               		  if (value.equals("Product")) {
               			  productId= resultSet.getString("id");
               			  break;
               		  }
               	  } 
            	   
               }
               

               logger.warning("year: "+year);
               logger.warning("productId: "+productId);
               logger.warning("countryId: "+countryId);
               logger.warning("companyId: "+companyId);
               logger.warning("PorS: "+PorS);
               

               int quant =0;
               if (!request.getParameter("FactVal").trim().equals("")) { 
                   quant = Integer.parseInt(request.getParameter("FactVal"));
               }
               if (quant==0) {
            	   
            	   PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("Delete from FactsEdit where "+
                		    " productId = "+productId + " and year = "+year+ " and access = 'w' "+
                		   " and companyId = "+companyId + " and countryId = "+countryId+ " and sales_production = "+PorS);
                   int retval = statement2.executeUpdate();
            	   
               }else {
			    
               PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("Update FactsEdit set quantity = "+
            		   request.getParameter("FactVal")+ " where productId = "+productId + " and access= 'w' and year = "+year+
            		   " and companyId = "+companyId + " and countryId = "+countryId+ " and sales_production = "+PorS);
               int retval = statement2.executeUpdate();
               
               if (retval==0) {
            	   String newSQL = "Insert into FactsEdit (quantity, productId, year, companyId, countryId," +
            	   		" sales_production, access) values ("+request.getParameter("FactVal")+","+productId
            	   		+","+year+","+companyId+","+countryId+","+PorS+",'w')";
            	   logger.warning("InsertSQL: "+newSQL);
            	   statement2 = (PreparedStatement) con.prepareStatement(newSQL);
            	   retval = statement2.executeUpdate();
            	   
               }
               }
                con.commit();

			   
			   return "login";
	 }
	 
	 @Transactional
	 @RequestMapping( method = RequestMethod.POST)
	public String postMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException {
		 
		 logger.warning("Entering application via POST");


		 return getMethodOne(
		            response,
					request,
					model);
	 }
	 
	
}
