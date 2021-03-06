package com.ids.controllers;



import com.ids.businessLogic.AddJsonTotalCell;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.google.appengine.api.rdbms.AppEngineDriver;
import com.ids.businessLogic.AddJsonPercentage;
import com.ids.businessLogic.AddJsonRowTotal;
import com.ids.businessLogic.AddJsonTotalPercentCells;
import com.ids.businessLogic.DownloadExcel;
import com.ids.businessLogic.DropdownInterface;
import com.ids.businessLogic.FirstTimeQuery;
import com.ids.businessLogic.ReplaceDropsWithFilterValues;
import com.ids.businessLogic.StoreRequestParameters;
import com.ids.context.GetBeansFromContext;
import com.ids.entities.Year;
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
@RequestMapping(value="/main")
public class MainController implements DropdownInterface {

	private Connection con;

   	private final static Logger logger = Logger.getLogger(MainController.class.getName()); 
       
    //   HashMap<Integer,Integer> totalLine = null;
       HashMap<String,Integer> totalLine2 = null;
   //    HashMap<Integer,Integer> otherLine = null;

	 private String access= "";
	 private String total= "";	 
	 private String percent="";
	 
	 
	 @RequestMapping( method = RequestMethod.GET)
	public String getMethodOne(
            HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws SQLException, JSONException, IOException {	   

		 GetBeansFromContext gcfc = null;
		 
		 try{
		 logger.warning("Entering application via GEt");
		 logger.warning("excelDownload: "+request.getParameter("excelDownload"));
		 
		 gcfc = new GetBeansFromContext();
		 con = gcfc.myConnection();
		 
		 model.addAttribute("ajaxPrefix",gcfc.ajaxURLprefix());
		 
	      Statement statement = con.createStatement();

	      ResultSet resultSet = null;
	      HttpSession session = request.getSession(true);
	      
	      User user = (User) session.getAttribute("myUser");
	 		 if (user==null ) {
	   		      model.addAttribute("errortext","You must logon before you can access IDS");
	   		      con.close();
	   		   logger.warning("JOHN 1");
	   		   	  return "redirect:/login";
	   		 }
	      
	 		 if (request.getParameter("exit")!= null ){
	 			 if (session.getAttribute("myUser") != null) {
	 			    session.setAttribute("myUser",null);
	 			 }
	 			 con.close();
	 			logger.warning("JOHN 2");
	 			return "redirect:/login"; 
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
		   		      con.close();
		   		   logger.warning("JOHN 3");
		   		   	  return "redirect:/login";
			   }

		  
	           // final Calendar c = Calendar.getInstance();
	          //chris   int myYear =  c.get(Calendar.YEAR) ;
			   
           	      access=user.getCurrentLocation();

   			   // Set up the last updated date 
     			 
           	      String lastUpdated = "";
           	      
           	      query=" SELECT DATE_FORMAT( last_update_time, '%d-%b-%y') as lastUpdated from last_update where access = '" +access+ "'";
	      	      resultSet = statement.executeQuery(query);
	   		      resultSet.next();
	   		      lastUpdated = resultSet.getString("lastUpdated");
	   		      model.addAttribute("lastUpdated",lastUpdated);
   			   //
           	      
	              query="SELECT MAX(year) as year1 from Facts_"+access+" where Sales_Production = 2 ";
	      	      resultSet = statement.executeQuery(query);
	   		      resultSet.next();
	   		      int myYear = resultSet.getInt("year1");
	               	            	
	            	
	            	String hideIt="";
	            	String hideIt2="";
	            	String hideIt3="";
	            	String hideCountry="";
	            	String hideViewable1a="";
	            	String hideViewable1b="";
	            	String checked1="";
	            	String checked2="";
	            	
	            	String longStringCompanies="";
	            	
	            	@SuppressWarnings("unused")
					String XX = request.getParameter("rowTotal");

		    		  if (request.getParameter("rowTotal")!=null && request.getParameter("rowTotal").equals("on")) {
		    			  total="TOTAL";
		    		  }else {
		    			  total="";
		    		  }
		    		  if (request.getParameter("percent")!=null && request.getParameter("percent").equals("on")) {
		    			  percent="yes";
		    		  }else {
		    			  percent="";
		    		  }
	            	
	            	String accessoptions = "";
	            	String selected = "";
	            	String textPrefix = "";
	            	String filePrefix = "";
	            	boolean set = false;
	            	if (user.getWorld()==1) {
	            		if (access.equals("w")) {
	            			selected = "selected";
		            		filePrefix = "ids";
		            		textPrefix = "IDS";
		            		 hideIt="display:block;";
		            		 hideIt2="display:none;";
		            		 hideIt3="";
		            		 hideCountry="Country<br>";
		            		 hideViewable1a="viewable1";
		            		 hideViewable1b="";
		            		 checked1="checked";
		            		 checked2="";
		            		 
	            		} else {
	            			selected = "";
	            		}
	            		accessoptions+="<option value='w' "+selected+" >INTERNATIONAL</option>";
	            		
	            	}
	            	if (user.getChina()==1) {
	            		if (access.equals("c")) {
	            			selected = "selected";
		            		   filePrefix = "cds";
		            		   textPrefix = "CDS";
		            		   hideIt="display:none;";
		            		   hideIt3="display:none;";
		            		   hideIt2="display:block;";
		            		   hideCountry="";
			            		 hideViewable1b="viewable1";
			            		 hideViewable1a="";
			            		 checked2="checked";
			            		 checked1="";
	            		} else {
	            			selected = "";
	            		}
	            		accessoptions+="<option value='c' "+selected+" >CHINESE</option>";
	            	}
	            	if (user.getIndia()==1) {
	            		if (access.equals("i")) {
	            			selected = "selected";
		            		  filePrefix = "inds";
		            		  textPrefix = "INDS";
		            		   hideIt="display:none;";
		            		   hideIt2="display:block;";
		            		   hideIt3="display:none;";
		            		   hideCountry="";			
		            		   hideViewable1b="viewable1";
			            		 hideViewable1a="";
			            		 checked2="checked";
			            		 checked1="";
		            		   
	            		} else {
	            			selected = "";
	            		}
	            		accessoptions+="<option value='i' "+selected+" >INDIAN</option>";
	            	}
	            	  model.addAttribute("accessoptions",accessoptions);
	            	  model.addAttribute("filePrefix",filePrefix);
	            	  model.addAttribute("textPrefix",textPrefix);
	            	  
	            	  model.addAttribute("hideIt",hideIt);
	            	  model.addAttribute("hideIt2",hideIt2);
	            	  model.addAttribute("hideIt3",hideIt3);
	            	  model.addAttribute("hideCountry",hideCountry);
	            	  model.addAttribute("hideViewable1a",hideViewable1a);
	            	  model.addAttribute("hideViewable1b",hideViewable1b);
	            	  model.addAttribute("checked1",checked1);
	            	  model.addAttribute("checked2",checked2);
	            	  
	            	
	            
			 if (request.getParameter("list") == null || !request.getParameter("list").equals("1")){
				 
				 FirstTimeQuery ftq = new FirstTimeQuery(model, con, request, myYear, access );
				 model = ftq.getModel();
				 
	    		  con.close(); 
	    		    	 logger.warning("coming out of first time");
	    		    	 logger.warning("JOHN 4");
	    		  return "idsmain";

		    		
		      }
		    	
			 
			 
			 Enumeration keys = request.getParameterNames(); 
			 while (keys.hasMoreElements() )  
      	   {  
      		   longStringCompanies = (String)keys.nextElement();  
      	      
      	      if ( longStringCompanies.contains("includedCompanies")) {
      	    	  longStringCompanies= longStringCompanies.replace("{\"includedCompanies\":\"","");
      	    	  longStringCompanies= longStringCompanies.replace("}","");
       	    	  longStringCompanies = longStringCompanies.replace("\"","");
      	    	  logger.warning(longStringCompanies); 
      	    	  break;
      	      }

      	   }  


		    	  StoreRequestParameters srp = new   StoreRequestParameters(request,myYear,longStringCompanies,false);

		    	  
		   
		    	  if (srp.getJustClicked().equals("heading1")) {
		    		  
		    		  if (srp.getHeading1()==srp.getHeading2()){		    			  
		    			srp.setHeading2(srp.getOldHeading1());
		    			srp.setOldHeading2(srp.getHeading2());
		    			srp.setOldHeading1(srp.getHeading1());
		    			int tempFlipOver = srp.getDropdown1();
		    			srp.setDropdown1(srp.getDropdown2());
		    			srp.setDropdown2(tempFlipOver);
		    			
		    		  }
		    	  }
		    		  
		    	  if (srp.getJustClicked().equals("heading2")) {

		    		  if (srp.getHeading1()==srp.getHeading2()){ 
		    			srp.setHeading1(srp.getOldHeading2());
		    			srp.setOldHeading1(srp.getHeading1());
		    			srp.setOldHeading2(srp.getHeading2());
		    			int tempFlipOver = srp.getDropdown1();
		    			srp.setDropdown1(srp.getDropdown2());
		    			srp.setDropdown2(tempFlipOver);
		    		  }
		    	  }

		    	  if (!access.equals("w")) {
		    	   	  if (srp.getHeading1()==2 && srp.getHeading2()==2) {
		    		     srp.setHeading2(1);
		    	      }
		    	   	if (srp.getHeading1()>2) {
		    	   		srp.setHeading2(1);
		    	   	}
		    	   	  if (srp.getHeading2()>2) {
		    	   		  if (srp.getHeading1()!=1) {
		    	   		      srp.setHeading2(1);
		    	   		  }else {
		    	   			srp.setHeading2(2); 
		    	   		  }
		    	   	  }
		    	  }

		    	  ReplaceDropsWithFilterValues rd = new ReplaceDropsWithFilterValues(  con, srp, access,  model);
		    	  model = rd.getModel();

		    		  
		    	  model.addAttribute("myDropValue1",srp.getDropdown1());
		    	  model.addAttribute("myDropValue2",srp.getDropdown2());
		    	  model.addAttribute("myOldHeadings",Integer.toString(srp.getHeading1())+Integer.toString(srp.getHeading2()));

		    	  
		    	  if (srp.getSummary()==1 || srp.getSummary()==4){
		    		  
		    		  String colHeading= ""; 
		    		  ColumnSummaryNameArray cna= null;
		    		  switch(srp.getHeading1()){
		    		    case (COUNTRY):{
		    			  colHeading="Country";
                          break;
		    		    }
		    		    case (PRODUCT):{
		    			  colHeading="Product";
		    			  break;
		    		    }
		    		    case (YEARS):{
		    			  colHeading="Year";
		    			  break;
		    		    }
		    		  }
		    		  

		    		  SQL1Summary sql1 =  new SQL1Summary(srp.getSalesOrProduct(),
				    			srp.getHeading1(), srp.getHeading2(), srp.getDropdown2(), srp.getFromDate(), srp.getToDate(), srp.getSummary(),
				    			srp.getIncExCountries(), srp.getIncExProducts(),
				    			srp.getIncExCompanies(),srp.getDateParm(), access);
		    		  
		    		  
	    			  cna = new ColumnSummaryNameArray(statement,sql1.getONE(), sql1.topHeadingLine(),
	    					  srp.getFromDate(), srp.getToDate(), access, total,percent);
	    			  
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());


		    		    logger.warning("sql1: "+ sql1.getQuery());

		    	  List <JSONObject> l = getObj5Summary(resultSet, statement, sql1.getQuery(),sql1.getONE() ,sql1.getTWO(),
		    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);
              JSONObject obj5 = l.get(0);
              JSONObject obj8 = l.get(1);
              
              if (total.equals("TOTAL")){
                  AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
                  new AddJsonTotalCell(obj8,aj.getTotal());
               }
              
              if (percent.equals("yes")) {
                  AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
                  new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
              }
		    	  model.addAttribute("jsonData",obj5);
		    	  model.addAttribute("jsonTotal",obj8);
                     
		    	  logger.warning(obj5.toString());
	    		  
		    	  con.close();
		    	  
		    	//  DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
		    	  return "jsonData";
		    		  
		    	  }
		    	  
		    	  
		    	  
		    	  
		    	  if (srp.getSummary()==2 ||
		    			  srp.getSummary()==3 ||
		    			  srp.getSummary()==5){
		    		  String colHeading= ""; 
		    		  ColumnSummaryNameArray cna= null;

		    			  colHeading="Company";
		    			  
		    			//  if (srp.getSummary()==2){
		    			//      if (srp.getHeading2()==COMPANY) {
		    			//	     colHeading="product";
		    			//	     if (srp.getHeading1()==YEARS){
		    			//		    colHeading="country";
		    			//	     }
		    			//	     if (srp.getHeading1()==PRODUCT){
		    			//		    colHeading="year";
		    			//	     }
		    			//      }
		    			//  }else {
			        		  if (srp.getHeading1()==COMPANY) {
			    				  colHeading="Year";
			    				  if (srp.getHeading2()==YEARS){
			    					  colHeading="Product";
			    				  }  
			        		  }
			        		  if (srp.getHeading1()==COUNTRY && srp.getHeading2()==COMPANY) {
			    				  colHeading="Year";
                                  if (srp.getSwap()==1) {
                                	  colHeading="Product";  
                                  }
			        		  }
			        		  if (srp.getHeading1()==PRODUCT && srp.getHeading2()==COMPANY) {
			    				  colHeading="Year";
                                  if (srp.getSwap()==1) {
                                	  colHeading="Country";  
                                  }
			        		  }
			        		  if (srp.getHeading1()==YEARS && srp.getHeading2()==COMPANY) {
			    				  colHeading="Product";
                                  if (srp.getSwap()==1) {
                                	  colHeading="Country";  
                                  }
			        		  }
			        		  if (srp.getHeading1()==COMPANY && srp.getHeading2()==YEARS) {
			    				  colHeading="Product";
                                  if (srp.getSwap()==1) {
                                	  colHeading="Country";  
                                  }
			        		  }
			        		  if (srp.getHeading1()==COMPANY && srp.getHeading2()==COUNTRY) {
			    				  colHeading="Year";
                                  if (srp.getSwap()==1) {
                                	  colHeading="Product";  
                                  }
			        		  }
			        		  
			        		  if (srp.getHeading1()==COMPANY && srp.getHeading2()==PRODUCT) {
			    				  colHeading="Year";
                                  if (srp.getSwap()==1) {
                                	  colHeading="Country";  
                                  }
			        		  }
			        		  
		    			//  }
                          
		    			  int dropdown=0;
		    			  if (srp.getSummary()==2 ) {
		    				  dropdown = srp.getDropdown2(); 
		    			  }else{
		    				  dropdown = srp.getDropdown1(); 
		    			  }

		    		       SQL1GrpSummary sql1 =  new SQL1GrpSummary(srp.getSalesOrProduct(),
				    			   srp.getHeading1(), srp.getHeading2(), dropdown, srp.getFromDate(), srp.getToDate(),srp.getSummary(),
				    			   srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
				    			   ,srp.getDateParm(),access,srp.getSwap());
	
		    		  
	    			  cna = new ColumnSummaryNameArray(statement,sql1.getONE(), sql1.topHeadingLine(),
	    					  srp.getFromDate(), srp.getToDate(), access, total,percent );
	    			  
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());


		    		    logger.warning("sql1a: "+ sql1.getQuery());

		    		    boolean All = false;
		    		    if (srp.getHeading2()==COMPANY ){
		    		    	All=false;
		    		    }
		    	  List <JSONObject> l = getObj5GroupSummary(resultSet, statement, sql1.getQuery(),sql1.getONE() ,sql1.getTWO(),
		    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), All,srp.getSummary());
              JSONObject obj5 = l.get(0);
              JSONObject obj8 = l.get(1);
              if (total.equals("TOTAL")){
                  AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
                  logger.warning("THIS--> "+obj8);
                  new AddJsonTotalCell(obj8,aj.getTotal());
               }
              
              if (percent.equals("yes")) {
                  AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
                  new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
              }
		    	  model.addAttribute("jsonData",obj5);
		    	  model.addAttribute("jsonTotal",obj8);
                     
		    	  logger.warning(obj5.toString());
	    		  
		    	  con.close();  
		    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
		    	 return "jsonData";
		    		  
		    	  }

		    	  if ((srp.getHeading1()==PRODUCT && srp.getHeading2()==YEARS) && srp.getSummary()==0 ||
		    			  (srp.getHeading2()==PRODUCT && srp.getHeading1()==YEARS && srp.getSummary()==0)) {
		    		  try{
		    		  String colHeading= "Company";

		    		  ColumnNameArray cna = new ColumnNameArray(srp,statement,"country shortname", COMPANY,
		    				  srp.getFromDate(), srp.getToDate(), access,total,percent);
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  model.addAttribute("myDimension","country shortname");
		    		  SQL1 sql1 =  new SQL1(srp.getSalesOrProduct(),
		    			rd.getProductId(), 
		    		    rd.getYearParam(), srp.getFromDate(), srp.getToDate(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);

			    		    logger.warning("sql1b: "+ sql1.getQuery());

			    	  List<JSONObject> l = getObj5(resultSet, statement, sql1.getQuery(),"company" ,"country","product","year",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), true);
                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
                      if (total.equals("TOTAL")){
                         AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
                         logger.warning("THIS--> "+obj8);
                         new AddJsonTotalCell(obj8,aj.getTotal());
                      }
                      if (percent.equals("yes")) {
                          AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
                          new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
                      }
			    	  model.addAttribute("jsonData",obj5);
			    	  model.addAttribute("jsonTotal",obj8);

			    	  logger.warning("STUFF");
			    	  logger.warning(obj5.toString());
			    	  con.close(); 
			    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
				    	 return "jsonData";
		    	  }
		    		  catch(Exception e) {
			    			 System.out.println("EXCEPTION");
			    		 e.printStackTrace();
			    		 }  
		    	  }
		    	  

		    	  
		    	  
		    	  if ((srp.getHeading1()==COMPANY && srp.getHeading2()==COUNTRY)  && srp.getSummary() ==0||
		    			  (srp.getHeading2()==COMPANY && srp.getHeading1()==COUNTRY && srp.getSummary()==0) ) {
		    		  try{
		    			  
		    			  
		    		  ColumnNameArray cna= null;
		    		  String colHeading= "Year";
		    		  String colHead2 = "Product";
		    		  
		    		  if (srp.getSwap()==1){
		    			  colHeading="Product";
		    			  colHead2="Year";
		    			  cna = new ColumnNameArray(srp,statement,"years", PRODUCT,
		    					  srp.getFromDate(), srp.getToDate(), access, total,percent); 
		    			  model.addAttribute("myDimension","years");
		    		  } else {
		    			  cna = new ColumnNameArray(srp,statement,"product shortname", YEARS,
		    					  srp.getFromDate(), srp.getToDate(), access, total,percent); 
		    			  model.addAttribute("myDimension","product shortname");
		    		  }

		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  SQL4 sql4 =  new SQL4(srp.getSalesOrProduct(),
		    			rd.getCountryId(), 
		    		   rd.getCompanyId(), srp.getFromDate(), srp.getToDate(),srp.getSwap(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);


			    		    logger.warning("sql4: "+ sql4.getQuery());

			    	  List <JSONObject> l = getObj5(resultSet, statement, sql4.getQuery(),colHeading ,colHead2,"company","country",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);
                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
                      if (total.equals("TOTAL")){
                          AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
                          logger.warning("THIS--> "+obj8);
                           new AddJsonTotalCell(obj8,aj.getTotal());
                           new AddJsonPercentage(obj5, obj8);
                       }
                      if (percent.equals("yes")) {
                          AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
                          new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
                      }
			    	  model.addAttribute("jsonData",obj5);
			    	  model.addAttribute("jsonTotal",obj8);
	                       
			    	  logger.warning("STUFF2");
			    	  logger.warning(obj5.toString());
		    		  
			    	  con.close();  
			    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
				    	 return "jsonData"; 
		    		  
		    	  }
		    		  catch(Exception e) {
			    			 System.out.println("EXCEPTION");
			    		 e.printStackTrace();
			    		 }  
		    	  }

		    	  
		    	  if ((srp.getHeading1()==COMPANY && srp.getHeading2()==PRODUCT)||
		    			  (srp.getHeading2()==COMPANY && srp.getHeading1()==PRODUCT) ) {
		    		  try{
		    			  
		    			  ColumnNameArray cna= null;
		    		  String colHeading= "Year";
		    		  String colHead2 = "Country";
		    		  System.out.println("SWAP: "+srp.getSwap());
		    		  
		    		  if (srp.getSwap()==1){
		    			  colHeading="Country";
		    			  colHead2="Year";
		    			  cna = new ColumnNameArray(srp,statement,"years", COUNTRY,
		    					  srp.getFromDate(), srp.getToDate(), access, total,percent); 
		    			  model.addAttribute("myDimension","years");
		    		  } else {
		    			  cna = new ColumnNameArray(srp,statement,"country shortname", YEARS,
		    					  srp.getFromDate(), srp.getToDate(),access, total,percent); 
		    			  model.addAttribute("myDimension","country shortname");
		    		  }
		    		  

		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  SQL5 sql5 =  new SQL5(srp.getSalesOrProduct(),
		    				  rd.getProductId(), 
		    		   rd.getCompanyId(), srp.getFromDate(), srp.getToDate(),srp.getSwap(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);


			    		    logger.warning("sql5: "+ sql5.getQuery());

			    	  List<JSONObject> l = getObj5(resultSet, statement, sql5.getQuery(),colHeading ,colHead2,"company","product",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);

                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
                      if (total.equals("TOTAL")){
                          AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
                          logger.warning("THIS--> "+obj8);
                           new AddJsonTotalCell(obj8,aj.getTotal());
                           new AddJsonPercentage(obj5, obj8);
                       }
                      if (percent.equals("yes")) {
                          AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
                          new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
                      }
			    	  model.addAttribute("jsonData",obj5);
			    	  model.addAttribute("jsonTotal",obj8);
			    	  logger.warning("STUFF3");
			    	  logger.warning(obj5.toString());
			    	  con.close(); 
			    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
				    	 return "jsonData";  
		    		  
		    	  }
		    		  catch(Exception e) {
			    			 System.out.println("EXCEPTION");
			    		 e.printStackTrace();
			    		 }  
		    	  }
		    	  
		    	  if ((srp.getHeading1()==COMPANY && srp.getHeading2()==YEARS)||
		    			  (srp.getHeading2()==COMPANY && srp.getHeading1()==YEARS) ) {
		    		  try{

		    		  ColumnNameArray cna= null;
		    		  String colHeading= "Product";
		    		  String colHead2 = "Country";
		    		  
		    		  if (srp.getSwap()==1){
		    			  colHeading="Country";
		    			  colHead2="Product";
		    			  cna = new ColumnNameArray(srp,statement,"product shortname", COUNTRY,
		    					  srp.getFromDate(), srp.getToDate(), access, total,percent); 
		    			  model.addAttribute("myDimension","product shortname");
		    		  } else {
		    			  cna = new ColumnNameArray(srp,statement,"country shortname", PRODUCT,
		    					  srp.getFromDate(), srp.getToDate(), access, total,percent); 
		    			  model.addAttribute("myDimension","country shortname");
		    		  }

		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  SQL6 sql6 =  new SQL6(srp.getSalesOrProduct(),
		    				  rd.getYearParam(), 
		    		    rd.getCompanyId(), srp.getFromDate(), srp.getToDate(), srp.getSwap(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);


			    		    logger.warning("sql6: "+ sql6.getQuery());

			    	  List <JSONObject> l = getObj5(resultSet, statement, sql6.getQuery(),colHeading ,colHead2,"company","year",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);

                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
                      if (total.equals("TOTAL")){
                          AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
                          new AddJsonTotalCell(obj8,aj.getTotal());
                          new AddJsonPercentage(obj5, obj8);
                       }
                      if (percent.equals("yes")) {
                          AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
                          new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
                      }
			    	  model.addAttribute("jsonData",obj5);
			    	  model.addAttribute("jsonTotal",obj8);
			    	  logger.warning("STUFF4");
			    	  logger.warning(obj5.toString());
			    	  con.close();
			    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
				    	 return "jsonData";  
		    		  
		    	  }
		    		  catch(Exception e) {
			    			 System.out.println("EXCEPTION");
			    		 e.printStackTrace();
			    		 }  
		    	  }

		    	  
		    	  
		    	  if ((srp.getHeading1()==COUNTRY && srp.getHeading2()==YEARS)||
		    			  (srp.getHeading2()==COUNTRY && srp.getHeading1()==YEARS) ) {
		    		  try{
		    		  String colHeading= "Company";
		    		  
		    		  ColumnNameArray cna = new ColumnNameArray(srp,statement,"product shortname", COMPANY,
		    				  srp.getFromDate(), srp.getToDate(),access, total,percent);
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());
		    		  model.addAttribute("myDimension","product shortname");
		    		  
		    		  SQL2 sql2 =  new SQL2(srp.getSalesOrProduct(),
		    				     rd.getCountryId(), 
		    				     rd.getYearParam(), srp.getFromDate(), srp.getToDate(),
				    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
				    		    ,srp.getDateParm(),access);


			    		    logger.warning("sql22: "+ sql2.getQuery());
			    		    resultSet = statement.executeQuery(sql2.getQuery());

						   List <JSONObject> l = getObj5(resultSet, statement, sql2.getQuery(),"company" ,"product","country","year",
						    colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), true);

		                      JSONObject obj5 = l.get(0);
		                      JSONObject obj8 = l.get(1);
		                      if (total.equals("TOTAL")){
		                          AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
                                  new AddJsonTotalCell(obj8,aj.getTotal());
                                  new AddJsonPercentage(obj5, obj8);
		                       }
		                      if (percent.equals("yes")) {
		                          AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
		                          new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
		                      }
					    	  model.addAttribute("jsonData",obj5);
					    	  model.addAttribute("jsonTotal",obj8);
		    		  
					    	  con.close(); 
					    	  logger.warning("STUFF5");
					    	  logger.warning(obj5.toString());
					    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
						    	 return "jsonData";
		    	  }
		    		  catch(Exception e) {
			    			 System.out.println("EXCEPTION");
			    		 e.printStackTrace();
			    		    con.close();
			    		 }  
		    	  }

		    	  
		    	  if ((srp.getHeading1()==COUNTRY && srp.getHeading2()==PRODUCT)||
		    			  (srp.getHeading2()==COUNTRY && srp.getHeading1()==PRODUCT) ) {
		    		  try{
		    		  String colHeading= "Company";
		    		  ColumnNameArray cna = new ColumnNameArray(srp,statement,"years" ,COMPANY,
		    				  srp.getFromDate(), srp.getToDate(), access, total,percent);
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());
		    		  model.addAttribute("myDimension","years");
		    		  
		    		  SQL3 sql3 =  new SQL3(srp.getSalesOrProduct(),
		    				  rd.getCountryId(), 
		    				  rd.getProductId(), srp.getFromDate(), srp.getToDate(),
		    				  srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    				  ,srp.getDateParm(),access);		    		 

		    		    logger.warning("sql3: "+ sql3.getQuery());
		    		    resultSet = statement.executeQuery(sql3.getQuery());

						 List <JSONObject> l = getObj5(resultSet, statement, sql3.getQuery(),"company" ,"year","country","product",
									    colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(),true);
		                   
	                      JSONObject obj5 = l.get(0);
	                      JSONObject obj8 = l.get(1);
	                      if (total.equals("TOTAL")){
	                          AddJsonRowTotal aj = new AddJsonRowTotal(obj5);
	                           new AddJsonTotalCell(obj8,aj.getTotal());
	                           new AddJsonPercentage(obj5, obj8);
	                       }
	                      if (percent.equals("yes")) {
	                          AddJsonPercentage ajp = new AddJsonPercentage(obj5, obj8);
	                          new AddJsonTotalPercentCells(obj8,ajp.getHeaderValues());
	                      }
				    	  model.addAttribute("jsonData",obj5);
				    	  model.addAttribute("jsonTotal",obj8);  

				    	  con.close();
				    	  logger.warning("STUFF6");
				    	  logger.warning(obj5.toString());
				    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
					    	 return "jsonData"; 
				    	  
		    		  }  
				    	  catch(Exception e) {
				    			 System.out.println("EXCEPTION");
				    		 e.printStackTrace();
				    		    con.close();
				    		 }  
		    		    
		    		  
		    	  }
		    	  
		    	  
		    	  con.close();   
		    	  logger.warning("JOHN 5");
		    	  logger.warning("heading1" +srp.getHeading1());
		    	  logger.warning("heading2" +srp.getHeading2());

	
	return "jsonData";
	
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
}
	 
	 
private List <JSONObject> getObj5(ResultSet resultSet, Statement statement, String query,String ONE ,String TWO,String THREE,String FOUR
		                ,String colHeading,  ColumnModel columnModel, JSONObject columnNameObj, int ProdOrSales,  boolean hasAll) throws JSONException, SQLException{
	
    String titleCountry = ""; 
    String titleProduct ="";

    logger.warning(query);
    resultSet = statement.executeQuery(query);
	  String currentCo11="";
	  JSONObject obj2a = null;
	  JSONArray array7 = new JSONArray();
	  
	  totalLine2 =  new HashMap<String,Integer>();
	 // otherLine2 =  new HashMap<String,Integer>();
	  
	  JsonWithInLoop jl = new JsonWithInLoop();
	  
	  while (resultSet.next()) {
		  
		  jl.loopProcess(resultSet.getString(ONE), resultSet.getString(TWO), 
				  resultSet.getString(THREE), resultSet.getString(FOUR),
				  resultSet.getString("quantity"),totalLine2, currentCo11,  array7,
					 obj2a, colHeading, hasAll);
		  totalLine2 = jl.getTotalLine2();
		//  otherLine2 = jl.getOtherLine2();
		  currentCo11= jl.getCurrentCo11();
		  array7 = jl.getArray7();
		  obj2a = jl.getObj2a();
		  colHeading = jl.getColHeading();
		  titleProduct= jl.getTitleTHREE();
		  titleCountry= jl.getTitleFOUR();
	  }

		JsonPackaging jp = new	 JsonPackaging(obj2a,array7,colHeading,totalLine2,
			 titleCountry,  titleProduct,
				columnModel.getModelObject(),  columnNameObj, 
				(ProdOrSales==SALES ? "Sales":"Production"), hasAll );

		List <JSONObject> returnValues = new ArrayList<JSONObject>();
		returnValues.add(0,jp.getObj5());
		returnValues.add(1,jp.getObj8());
      return returnValues;

}


private List <JSONObject> getObj5Summary(ResultSet resultSet, Statement statement, String query,String ONE ,String TWO, 
		 String colHeading,  ColumnModel columnModel, JSONObject columnNameObj, int ProdOrSales, boolean hasAll) throws JSONException, SQLException{

    String titleCountry = ""; 
    String titleProduct ="";

    logger.warning(query);
    resultSet = statement.executeQuery(query);
    String currentCo11="";
    JSONObject obj2a = null;
    JSONArray array7 = new JSONArray();

    totalLine2 =  new HashMap<String,Integer>();
   

    JsonSummaryWithinLoop jl = new JsonSummaryWithinLoop();

    while (resultSet.next()) {

        jl.loopProcess(resultSet.getString(ONE), resultSet.getString(TWO), 
           resultSet.getString("quantity"),totalLine2, currentCo11,  array7,
	       obj2a, colHeading, hasAll);
        
        totalLine2 = jl.getTotalLine2();

        currentCo11= jl.getCurrentCo11();
        array7 = jl.getArray7();
        obj2a = jl.getObj2a();
        colHeading = jl.getColHeading();
        titleProduct= jl.getTitleTHREE();
        titleCountry= jl.getTitleFOUR();
    }

     JsonPackaging jp = new	 JsonPackaging(obj2a,array7,colHeading,totalLine2,
            titleCountry,  titleProduct,
           columnModel.getModelObject(),  columnNameObj, 
           (ProdOrSales==SALES ? "Sales":"Production"), hasAll );

     List <JSONObject> returnValues = new ArrayList<JSONObject>();
     returnValues.add(0,jp.getObj5());
     returnValues.add(1,jp.getObj8());
     return returnValues;
    

}


private List <JSONObject> getObj5GroupSummary( ResultSet resultSet, Statement statement, String query,String ONE ,String TWO, 
		 String colHeading,  ColumnModel columnModel, JSONObject columnNameObj, int ProdOrSales, 
		 boolean hasAll, int summary) throws JSONException, SQLException{

   String titleCountry = ""; 
   String titleProduct ="";

   logger.warning(query);
   resultSet = statement.executeQuery(query);
   String currentCo11="";
   JSONObject obj2a = null;
   JSONArray array7 = new JSONArray();

   totalLine2 =  new HashMap<String,Integer>();


   JsonGroupSummaryWithinLoop jl = new JsonGroupSummaryWithinLoop();

   while (resultSet.next()) {

       jl.loopProcess(resultSet.getString(ONE), resultSet.getString(TWO),
          resultSet.getString("quantity"),totalLine2, currentCo11,  array7,
	       obj2a, colHeading, hasAll, summary);
       
       totalLine2 = jl.getTotalLine2();
       currentCo11= jl.getCurrentCo11();
       array7 = jl.getArray7();
       obj2a = jl.getObj2a();
       colHeading = jl.getColHeading();
       titleProduct= jl.getTitleTHREE();
       titleCountry= jl.getTitleFOUR();
   }

   resultSet.close();
   statement.close();
    JsonPackaging jp = new	 JsonPackaging(obj2a,array7,colHeading,totalLine2,
          titleCountry,  titleProduct,
          columnModel.getModelObject(),  columnNameObj, 
          (ProdOrSales==SALES ? "Sales":"Production"), hasAll );

    List <JSONObject> returnValues = new ArrayList<JSONObject>();
    returnValues.add(0,jp.getObj5());
    returnValues.add(1,jp.getObj8());
    return returnValues;


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