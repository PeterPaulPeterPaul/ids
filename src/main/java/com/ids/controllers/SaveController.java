package com.ids.controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.google.appengine.api.rdbms.AppEngineDriver;
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
