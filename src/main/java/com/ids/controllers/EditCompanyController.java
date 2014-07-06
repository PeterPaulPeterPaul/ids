package com.ids.controllers;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.google.appengine.api.rdbms.AppEngineDriver;
//import com.google.cloud.sql.jdbc.PreparedStatement;
import com.ids.businessLogic.AddJsonRowTotal;
import com.ids.businessLogic.AddJsonTotalCell;
import com.ids.businessLogic.DownloadExcel;
import com.ids.businessLogic.DropdownInterface;
import com.ids.businessLogic.FirstTimeEdQuery;
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

import com.ids.sql.SQL1Ed;
import com.ids.sql.SQL1GrpSummary;
import com.ids.sql.SQL1Summary;

import com.ids.sql.SQL2Ed;

import com.ids.sql.SQL3Ed;

import com.ids.sql.SQL4Ed;

import com.ids.sql.SQL5Ed;

import com.ids.sql.SQL6Ed;
import com.ids.user.User;




/**
 * 
 * @author John Hawthorne
 * the main controller for on entry into the web,
 * 
 * 
 * 
 * IF RUNNING THIS LOCALLY:
 * comment out these two lines:
 *          DriverManager.registerDriver(new AppEngineDriver());
         con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","user","password");
  
              replace with:
                gcfc = new GetBeansFromContext();
		        con = gcfc.myConnection();
       
 * 
 */

@Controller
public class EditCompanyController implements DropdownInterface {

	private Connection con;

   	private final static Logger logger = Logger.getLogger(EditCompanyController.class.getName()); 
       


	 
	 @RequestMapping( value="/updateComp"
			 , method = RequestMethod.GET)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws SQLException, JSONException, IOException {	   

		 GetBeansFromContext gcfc = null;
		 try{


			  Enumeration keys = request.getParameterNames();  
			   while (keys.hasMoreElements() )  
			   {  
			      String key = (String)keys.nextElement();  
			      logger.warning(key);  
			   
			      //To retrieve a single value  
			      String value = request.getParameter(key);  
			      logger.warning(value);  

			   }  
			   
			 
			 gcfc = new GetBeansFromContext();
			 con = gcfc.myConnection();
			 
			 String s =  request.getParameter("newName");
			 s = s.replaceAll("'","''");

			  logger.warning("Update Company set name = '"+
			   		   s + "' where id = "+request.getParameter("id") + " and access = '"
					  +request.getParameter("accessCurr")+"'");
		      
			  PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("Update Company set name = '"+
			   		   s + "' where id = "+request.getParameter("id") + " and access = '"
					  +request.getParameter("accessCurr")+"'");
				 int rows= statement2.executeUpdate();
			      

				  
				  logger.warning("return from update: "+rows);
				  
				  
		 
           return "success";
		 } catch(Exception ed) {
			 ed.printStackTrace();
					 }
		 
		 return "success";

}
	
	 
	 
	 
	 @RequestMapping( value="/deleteComp"
			 , method = RequestMethod.GET)
	public String getMethodOnea(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws SQLException, JSONException, IOException {	   

		 GetBeansFromContext gcfc = null;
		 try{


			  Enumeration keys = request.getParameterNames();  
			   while (keys.hasMoreElements() )  
			   {  
			      String key = (String)keys.nextElement();  
			      logger.warning(key);  
			   
			      //To retrieve a single value  
			      String value = request.getParameter(key);  
			      logger.warning(value);  

			   }  
			   
			 
			 gcfc = new GetBeansFromContext();
			 con = gcfc.myConnection();
			 
			 String s =  request.getParameter("newName");
			 s = s.replaceAll("'","''");


			   
			  logger.warning("Delete from FactsEdit_"+request.getParameter("accessCurr")+" "+
			   		 " where companyId = "+request.getParameter("id") );
		      
			  
			  
			  PreparedStatement statement2 = (PreparedStatement) con.prepareStatement
					  ("Delete from FactsEdit_"+request.getParameter("accessCurr")+" "+
				   		 " where companyId = "+request.getParameter("id"));
				 int rows= statement2.executeUpdate();
			      

				  
				  logger.warning("return from delete facts: "+rows);
				  
				  PreparedStatement statement3 = (PreparedStatement) con.prepareStatement
						  ("Delete from Company "+
					   		 " where id = "+request.getParameter("id") +" AND access = '"+request.getParameter("accessCurr")+"'");
					 int rows3= statement3.executeUpdate();
				      

					  
					  logger.warning("return from delete company: "+rows3);
				  
				  
		 
           return "success";
		 } catch(Exception ed) {
			 ed.printStackTrace();
					 }
		 
		 return "success";

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