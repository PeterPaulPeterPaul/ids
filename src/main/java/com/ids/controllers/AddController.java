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
@RequestMapping(value="/addrow")
public class AddController implements DropdownInterface {

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
		 logger.warning("Entering application via GEt");
		 logger.warning("excelDownload: "+request.getParameter("excelDownload"));
		 
		 GetBeansFromContext gcfc = new GetBeansFromContext();
		 con = gcfc.myConnection();
		  
		 con.setAutoCommit(false);

	      Statement statement = con.createStatement();
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


	      ResultSet resultSet = null;
	      HttpSession session = request.getSession(true);
	      User user = (User) session.getAttribute("myUser");
	 		 if (user==null ) {
	   		      model.addAttribute("errortext","You must logon before you can access IDS");
	   		      con.close();
	   		   	  return "login";
	   		 }
	      
	 		 if (request.getParameter("exit")!= null ){
	 			 if (session.getAttribute("myUser") != null) {
	 			    session.setAttribute("myUser",null);
	 			 }
	 			con.close();
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
			   String access = request.getParameter("accessCurr");
					   
			   model.addAttribute("openOrClose","close");
			   

			   
			   String year = "";
			   String productId = "";
			   String countryId = "";
			   String companyId = "";
			   String PorS ="";
			   String SQL = "";
			   
			   String value = request.getParameter("dimension1Name");
			   logger.warning("dimension1Name: "+value);
			   value = WordUtils.capitalize(value); 
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension1Val");
               } else {
            	   
            	   if (value.equals("Company")) {
         			  companyId=request.getParameter("dimension1Val");
            	   }  else{
         			  
            	  SQL = " select id from "+value+ " where UPPER(name) = '" +request.getParameter("dimension1Val") +"' " +
            	  		" and access = '"+access+"' ";
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
            	  
            	  
            	   } //not company

               }
               
               
               value = request.getParameter("dimension2Name");
               value =WordUtils.capitalize(value) ;
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension2Val");
               } else {
            	   if (request.getParameter("dimension2Name").trim().equals("Country")){
            		   SQL = " select id from "+value+ " where country = '" +request.getParameter("dimension2Val").trim() +"'" +
            				   " and access = '"+access+"' ";
            	   } else {
             	      SQL = " select id from "+value+ " where UPPER(name) = '" +request.getParameter("dimension2Val").trim() +"'" +
             	    		 " and access = '"+access+"' ";
            	   }
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
               value =WordUtils.capitalize(value) ;
               
               if (value != null && !value.equals("")) {
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension3Val");
               } else {
            	   if (value.equals("Country")) {
            	       SQL = " select id from "+value+ " where country = '" +request.getParameter("dimension3Val").trim() +"'" +
            	    		   " and access = '"+access+"' ";
            	   }else{
            		   SQL = " select id from "+value+ " where UPPER(name) = '" +request.getParameter("dimension3Val").trim() +"' " +
            				   " and access = '"+access+"' ";
            	   }
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
               }
               logger.warning("sales stuff is: "+request.getParameter("dimension4Val"));
               
               if (request.getParameter("dimension4Val").equals("PRODUCTION") ) {
            	   PorS= "2"; 
               } else {
            	   PorS = "1";
               }
               
               value = request.getParameter("dimension5Name");
               value =WordUtils.capitalize(value) ;
               logger.warning("dimension5Name: "+value);
               
               if (value.equals("Year")) {
            	   year =  request.getParameter("dimension5Val");
               } else { 
                   try{
            	   value = value.substring(0,value.indexOf(" "));
                   }catch(Exception e) {
                	   //Ignore 
                   }
            	  // SQL = " select id from "+value+ " where shortname = '" +request.getParameter("dimension5Val") +"' ";
            	  // logger.warning("SQL5: "+SQL);
             //  	  resultSet = statement.executeQuery(SQL);
              // 	  while (resultSet.next()) {
               		  if (value.equals("Country")) {
               			  countryId= request.getParameter("dimension5Val");
               		  }
               		  if (value.equals("Product")) {
               			  productId= request.getParameter("dimension5Val");
               	  } 
            	   
               }
               
               if (access.equals("c")) {
            	   countryId="21";
               }else{
            	   if (access.equals("i")) {
            		   countryId="100";
            	   }
               }


               logger.warning("year: "+year);
               logger.warning("productId: "+productId);
               logger.warning("countryId: "+countryId);
               logger.warning("companyId: "+companyId);
               logger.warning("PorS: "+PorS);
               


            	   String newSQL = "Insert into FactsEdit_"+request.getParameter("accessCurr")+" (quantity, productId, year, companyId, countryId," +
            	   		" sales_production, access,flag) values ("+request.getParameter("quantAmt")+","+productId
            	   		+","+year+","+companyId+","+countryId+","+PorS+",'"+access+"','I')";
            	   logger.warning("InsertSQL: "+newSQL);
            	   PreparedStatement statement2 = (PreparedStatement) con.prepareStatement(newSQL);
            	   int retval = statement2.executeUpdate();
            	   
            	// Chris - check to see if there is a total row for this grid yet. If not then add it.   
            	   
            	   newSQL = "Select 'found' as found FROM FactsEdit_"+request.getParameter("accessCurr")+
            			   	" where productId = " + productId + 
            			   	" and year = " + year +
            			   	" and countryId = " + companyId +
            			   	" and sales_production = " + PorS + 
            			   	" and CompanyId = 11 ";
            	   
               	   logger.warning("Check for a total SQL: "+newSQL);
        	     
            	   resultSet = statement2.executeQuery(newSQL);
            	   
      	           found = false;
      			   while (resultSet.next()) {
      				   if (resultSet.getString("found").equals("found")) {
      					   found = true;
      					   break;
      				   }
      			   }
            		
      			   
      			if (found == false){
             	    newSQL = "Insert into FactsEdit_"+request.getParameter("accessCurr")+" (quantity, productId, year, companyId, countryId," +
                  	   		" sales_production, access,flag) values ("+request.getParameter("quantAmt")+","+productId
                  	   		+","+year+",11,"+countryId+","+PorS+",'"+access+"','I')";
                  	   logger.warning("InsertSQL: "+newSQL);
                  	   statement2 = (PreparedStatement) con.prepareStatement(newSQL);
                  	   retval = statement2.executeUpdate();
                  	   
                	    newSQL = "Insert into FactsEdit_"+request.getParameter("accessCurr")+" (quantity, productId, year, companyId, countryId," +
                      	   		" sales_production, access,flag) values (0," + productId
                      	   		+","+year+",-1,"+countryId+","+PorS+",'"+access+"','I')";
                      	   logger.warning("InsertSQL: "+newSQL);
                      	   statement2 = (PreparedStatement) con.prepareStatement(newSQL);
                      	   retval = statement2.executeUpdate();
                  	   
      			}

               	             	   
                con.commit();
                
         	   newSQL = "update editing set flag = '1' ";
         	   PreparedStatement statement3 = (PreparedStatement) con.prepareStatement(newSQL);
            	   statement3.executeUpdate();
                
                 con.commit();
        		 con.close();
		 }catch(Exception e) {
			 logger.log(Level.SEVERE,"Error",e);
		 }

		 return "redirect:editor?openOrClose=open";
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
