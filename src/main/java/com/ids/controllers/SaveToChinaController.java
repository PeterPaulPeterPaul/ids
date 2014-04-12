package com.ids.controllers;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
public class SaveToChinaController implements DropdownInterface {

	private Connection con;

   	private final static Logger logger = Logger.getLogger(SaveToChinaController.class.getName()); 
       
    //   HashMap<Integer,Integer> totalLine = null;
       HashMap<String,Integer> totalLine2 = null;
   //    HashMap<Integer,Integer> otherLine = null;

	 
       @RequestMapping( value="/savetochina", method = RequestMethod.GET)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) {	   

		 try{

			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 con = gcfc.myConnection();
		  con.setAutoCommit(false);
			 
			 
			 
	      Statement statement = con.createStatement();
	      
	      DatabaseMetaData metadata = con.getMetaData();
	      ResultSet resultSet;
	      
	      resultSet = metadata.getTables(null, null, "TEMP_FACTS_c_"+Calendar.getInstance().get(Calendar.YEAR), null);
	      Boolean tableExists=false;
	      if(resultSet.next()){
	    	  tableExists=true;
	      }
	      String SQL_string="";
	      if (tableExists) {
	    	  SQL_string=" DELETE from TEMP_FACTS_c_"+Calendar.getInstance().get(Calendar.YEAR);
	    	  statement.executeUpdate(SQL_string);
	    	  SQL_string=" INSERT INTO TEMP_FACTS_c_"+Calendar.getInstance().get(Calendar.YEAR) +" SELECT * FROM Facts_c ";
	    	  statement.executeUpdate(SQL_string);
	    	  
	      }else {
	    	  SQL_string= " create table TEMP_FACTS_c_"+Calendar.getInstance().get(Calendar.YEAR) +" SELECT * FROM Facts_c ";
	    	  statement.executeUpdate(SQL_string);
	      }
	      con.commit();
	      SQL_string = "delete from  Facts_c  ";
	      statement.executeUpdate(SQL_string);
	      
          con.commit();
	      
	      SQL_string = "insert into Facts_c select * from FactsEdit_c ";
	      statement.executeUpdate(SQL_string);
	      
          con.commit();
          con.close();
	      
logger.warning("HELLO WORLD");

		
return "redirect:setup2?done=done";




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
