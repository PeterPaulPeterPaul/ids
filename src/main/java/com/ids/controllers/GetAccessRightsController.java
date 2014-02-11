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

@Controller
@RequestMapping(value="/getAccess")
public class GetAccessRightsController implements DropdownInterface {

	private Connection con;

   	private final static Logger logger = Logger.getLogger(GetAccessRightsController.class.getName()); 
       
    //   HashMap<Integer,Integer> totalLine = null;
       HashMap<String,Integer> totalLine2 = null;
   //    HashMap<Integer,Integer> otherLine = null;

	 
	 @RequestMapping( method = RequestMethod.GET)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) {	   

		 try{

			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 con = gcfc.myConnection();
		  con.setAutoCommit(false);
			 
			 
			 
	      Statement statement = con.createStatement();

	      ResultSet resultSet = null;
	      String query = "select world, china, india from ids_users where userId = '"+request.getParameter("myUserId")+"'";
logger.warning("query: "+query);

		  String world="";
		  String china="";
		  String india="";

		  resultSet = statement.executeQuery(query);
		   while (resultSet.next()) {
			  if (resultSet.getString("world").equals("1")){
				  world="checked";
			  }
			  if (resultSet.getString("china").equals("1")){
				  china="checked";
			  }
			  if (resultSet.getString("india").equals("1")){
				  india="checked";
			  }
		   }

			  String checked = "<div style='width:99%'>IDS: <input class='myrad2' type='checkbox' name='world' id='a1world'  value='w' "+world+" />"+
		                " CDS: <input class='myrad2' type='checkbox' name='china' id='a1china'  value='c' "+china+" />"+
		                " INDS: <input class='myrad2' type='checkbox' name='india' id='a1india'  value='i' "+india+" /></div>";
			  
		   model.addAttribute("mycheckboxes",checked);
		   



                con.commit();
                con.close();

		 }catch(Exception e) {
			 logger.log(Level.SEVERE,"Error",e);
		 }
		 return "checkedOptions";
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
