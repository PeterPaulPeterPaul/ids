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
@RequestMapping(value="/editor")
public class EditorController implements DropdownInterface {

	private Connection con;
	private String access = "w" ;  //TEMP SOLUTION - SET TO WORLD

   	private final static Logger logger = Logger.getLogger(EditorController.class.getName()); 
       
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

		  
	         final Calendar c = Calendar.getInstance();

	            int myYear =  c.get(Calendar.YEAR) ;
	            
			 if (request.getParameter("list") == null || request.getParameter("list").equals("1")){
		    	  
				 FirstTimeEdQuery ftq = new FirstTimeEdQuery(model, con, request, myYear, /* set access to world TEMP*/ "w"  );
				 model = ftq.getModel();
				 
	    		  con.close(); 
	    		    	 logger.warning("returning to idsEditmain");
	    		  return "idsEditmain";
		    		
		      }
		    	



		    	  StoreRequestParameters srp = new   StoreRequestParameters(request,myYear);
		    	  
		    	
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

		    	  model.addAttribute("myDropValue1",srp.getDropdown1());
		    	  model.addAttribute("myDropValue2",srp.getDropdown2());
		    	  model.addAttribute("myOldHeadings",Integer.toString(srp.getHeading1())+Integer.toString(srp.getHeading2()));

		    	  
		    	  if (srp.getSummary()==1 || srp.getSummary()==4){
		    		  
		    		  String colHeading= ""; 
		    		  ColumnSummaryNameArray cna= null;
		    		  switch(srp.getHeading1()){
		    		    case (COUNTRY):{
		    			  colHeading="country";
                          break;
		    		    }
		    		    case (PRODUCT):{
		    			  colHeading="product";
		    			  break;
		    		    }
		    		    case (YEARS):{
		    			  colHeading="year";
		    			  break;
		    		    }
		    		  }
		    		  

		    		  SQL1Summary sql1 =  new SQL1Summary(srp.getSalesOrProduct(),
				    			srp.getHeading1(), srp.getHeading2(), srp.getDropdown2(), srp.getFromDate(), srp.getToDate(), srp.getSummary(),
				    			srp.getIncExCountries(), srp.getIncExProducts(),
				    			srp.getIncExCompanies(),srp.getDateParm(), access);
		    		  
		    		  
	    			  cna = new ColumnSummaryNameArray(statement,sql1.getONE(), sql1.topHeadingLine(),
		    				  myYear);
	    			  
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());


		    		    logger.warning(sql1.getQuery());

		    	  List <JSONObject> l = getObj5Summary(resultSet, statement, sql1.getQuery(),sql1.getONE() ,sql1.getTWO(),
		    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);
              JSONObject obj5 = l.get(0);
              JSONObject obj8 = l.get(1);
		    	  model.addAttribute("jsonData",obj5);
		    	  model.addAttribute("jsonTotal",obj8);
                     
		    	  logger.warning(obj5.toString());
		    	  model.addAttribute("jsonData",obj5);
	    		  
		    	  con.close();
		    	  
		    	//  DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
		    	  return "jsonData";
		    		  
		    	  }
		    	  
		    	  
		    	  
		    	  
		    	  if (srp.getSummary()==2 ||
		    			  srp.getSummary()==3 ||
		    			  srp.getSummary()==5){
		    		  String colHeading= ""; 
		    		  ColumnSummaryNameArray cna= null;

		    			  colHeading="company";
		    			  
		    			  if (srp.getSummary()==2){
		    			      if (srp.getHeading2()==COMPANY) {
		    				     colHeading="year";
		    				     if (srp.getHeading1()==YEARS){
		    					    colHeading="country";
		    				     }
		    			      }
		    			  }else {
			        		  if (srp.getHeading1()==COMPANY) {
			    				  colHeading="year";
			    				  if (srp.getHeading2()==YEARS){
			    					  colHeading="product";
			    				  }  
			        		  }
		    			  }

		    			  int dropdown=0;
		    			  if (srp.getSummary()==2 ) {
		    				  dropdown = srp.getDropdown2(); 
		    			  }else{
		    				  dropdown = srp.getDropdown1(); 
		    			  }
		    		       SQL1GrpSummary sql1 =  new SQL1GrpSummary(srp.getSalesOrProduct(),
				    			   srp.getHeading1(), srp.getHeading2(), dropdown, srp.getFromDate(), srp.getToDate(),srp.getSummary(),
				    			   srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
				    			   ,srp.getDateParm(),access);
	
		    		  
	    			  cna = new ColumnSummaryNameArray(statement,sql1.getONE(), sql1.topHeadingLine(),
		    				  myYear);
	    			  
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());


		    		    logger.warning(sql1.getQuery());

		    		    boolean All = false;
		    		    if (srp.getHeading2()==COMPANY ){
		    		    	All=false;
		    		    }
		    	  List <JSONObject> l = getObj5GroupSummary(resultSet, statement, sql1.getQuery(),sql1.getONE() ,sql1.getTWO(),
		    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), All,srp.getSummary());
              JSONObject obj5 = l.get(0);
              JSONObject obj8 = l.get(1);
		    	  model.addAttribute("jsonData",obj5);
		    	  model.addAttribute("jsonTotal",obj8);
                     
		    	  logger.warning(obj5.toString());
		    	  model.addAttribute("jsonData",obj5);
	    		  
		    	  con.close();  
		    	  // DownloadExcel dx = new DownloadExcel( obj5,request,response) ;
		    	 return "jsonData";
		    		  
		    	  }
		    	  
		    	  
		    	  if ((srp.getHeading1()==PRODUCT && srp.getHeading2()==YEARS) && srp.getSummary()==0 ||
		    			  (srp.getHeading2()==PRODUCT && srp.getHeading1()==YEARS && srp.getSummary()==0)) {
		    		  try{
		    		  String colHeading= "company";
		    		  
		    		  ColumnNameArray cna = new ColumnNameArray(statement,"country shortname", COMPANY,
		    				  srp.getFromDate(), srp.getToDate(), access);
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  model.addAttribute("myDimension","country shortname");
		    		  SQL1Ed sql1 =  new SQL1Ed(srp.getSalesOrProduct(),
		    			(srp.getHeading1()==PRODUCT ? srp.getDropdown1(): srp.getDropdown2()), 
		    		    (srp.getHeading1()==YEARS ? srp.getDropdown1(): srp.getDropdown2()), srp.getFromDate(), srp.getToDate(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);


			    		    logger.warning(sql1.getQuery());

			    	  List<JSONObject> l = getObj5(resultSet, statement, sql1.getQuery(),"company" ,"country","product","year",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), true);
                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
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
		    		  String colHeading= "year";
		    		  String colHead2 = "product";
		    		  
		    		  if (srp.getSwap()==1){
		    			  colHeading="product";
		    			  colHead2="year";
		    			  cna = new ColumnNameArray(statement,"years", PRODUCT,
		    					  srp.getFromDate(), srp.getToDate(), access); 
		    			  model.addAttribute("myDimension","years");
		    		  } else {
		    			  cna = new ColumnNameArray(statement,"product shortname", YEARS,
		    					  srp.getFromDate(), srp.getToDate(), access); 
		    			  model.addAttribute("myDimension","product shortname");
		    		  }

		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  SQL4Ed sql4 =  new SQL4Ed(srp.getSalesOrProduct(),
		    			(srp.getHeading1()==COUNTRY ? srp.getDropdown1(): srp.getDropdown2()), 
		    		    (srp.getHeading1()==COMPANY ? srp.getDropdown1(): srp.getDropdown2()), srp.getFromDate(), srp.getToDate(),srp.getSwap(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);


			    		    logger.warning(sql4.getQuery());

			    	  List <JSONObject> l = getObj5(resultSet, statement, sql4.getQuery(),colHeading ,colHead2,"company","country",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);
                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
			    	  model.addAttribute("jsonData",obj5);
			    	  model.addAttribute("jsonTotal",obj8);
	                       
			    	  logger.warning("STUFF2");
			    	  logger.warning(obj5.toString());
			    	  model.addAttribute("jsonData",obj5);
		    		  
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
		    		  String colHeading= "year";
		    		  String colHead2 = "country";
		    		  System.out.println("SWAP: "+srp.getSwap());
		    		  
		    		  if (srp.getSwap()==1){
		    			  colHeading="country";
		    			  colHead2="year";
		    			  cna = new ColumnNameArray(statement,"years", COUNTRY,
		    					  srp.getFromDate(), srp.getToDate(), access); 
		    			  model.addAttribute("myDimension","years");
		    		  } else {
		    			  cna = new ColumnNameArray(statement,"country shortname", YEARS,
		    					  srp.getFromDate(), srp.getToDate(), access); 
		    			  model.addAttribute("myDimension","country shortname");
		    		  }
		    		  

		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  SQL5Ed sql5 =  new SQL5Ed(srp.getSalesOrProduct(),
		    			(srp.getHeading1()==PRODUCT ? srp.getDropdown1(): srp.getDropdown2()), 
		    		    (srp.getHeading1()==COMPANY ? srp.getDropdown1(): srp.getDropdown2()), srp.getFromDate(), srp.getToDate(),srp.getSwap(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);


			    		    logger.warning(sql5.getQuery());

			    	  List<JSONObject> l = getObj5(resultSet, statement, sql5.getQuery(),colHeading ,colHead2,"company","product",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);

                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
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
		    		  String colHeading= "product";
		    		  String colHead2 = "country";
		    		  
		    		  if (srp.getSwap()==1){
		    			  colHeading="country";
		    			  colHead2="product";
		    			  cna = new ColumnNameArray(statement,"product shortname", COUNTRY,
		    					  srp.getFromDate(), srp.getToDate(),access); 
		    			  model.addAttribute("myDimension","product shortname");
		    		  } else {
		    			  cna = new ColumnNameArray(statement,"country shortname", PRODUCT,
		    					  srp.getFromDate(), srp.getToDate(),access); 
		    			  model.addAttribute("myDimension","country shortname");
		    		  }

		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());

		    		  SQL6Ed sql6 =  new SQL6Ed(srp.getSalesOrProduct(),
		    			(srp.getHeading1()==YEARS ? srp.getDropdown1(): srp.getDropdown2()), 
		    		    (srp.getHeading1()==COMPANY ? srp.getDropdown1(): srp.getDropdown2()), srp.getFromDate(), srp.getToDate(), srp.getSwap(),
		    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    		    ,srp.getDateParm(),access);


			    		    logger.warning(sql6.getQuery());

			    	  List <JSONObject> l = getObj5(resultSet, statement, sql6.getQuery(),colHeading ,colHead2,"company","year",
			    			  colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), false);

                      JSONObject obj5 = l.get(0);
                      JSONObject obj8 = l.get(1);
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
		    		  String colHeading= "company";
		    		  
		    		  ColumnNameArray cna = new ColumnNameArray(statement,"product shortname", COMPANY,
		    				  srp.getFromDate(), srp.getToDate(), access);
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());
		    		  model.addAttribute("myDimension","product shortname");
		    		  
		    		  SQL2Ed sql2 =  new SQL2Ed(srp.getSalesOrProduct(),
		    				  (srp.getHeading1()==COUNTRY ? srp.getDropdown1(): srp.getDropdown2()), 
				    		    (srp.getHeading1()==YEARS ? srp.getDropdown1(): srp.getDropdown2()), srp.getFromDate(), srp.getToDate(),
				    		    srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
				    		    ,srp.getDateParm(),access);


			    		    logger.warning(sql2.getQuery());
			    		    resultSet = statement.executeQuery(sql2.getQuery());

						   List <JSONObject> l = getObj5(resultSet, statement, sql2.getQuery(),"company" ,"product","country","year",
						    colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(), true);

		                      JSONObject obj5 = l.get(0);
		                      JSONObject obj8 = l.get(1);
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
			    		 }  
		    	  }

		    	  
		    	  if ((srp.getHeading1()==COUNTRY && srp.getHeading2()==PRODUCT)||
		    			  (srp.getHeading2()==COUNTRY && srp.getHeading1()==PRODUCT) ) {
		    		  try{
		    		  String colHeading= "company";
		    		  ColumnNameArray cna = new ColumnNameArray(statement,"years" ,COMPANY,
		    				  srp.getFromDate(), srp.getToDate(), access);
		    		  ColumnModel columnModel = new ColumnModel(cna.getColumnNameArray());
		    		  model.addAttribute("myDimension","years");
		    		  
		    		  SQL3Ed sql3 =  new SQL3Ed(srp.getSalesOrProduct(),
		    				  (srp.getHeading1()==COUNTRY ? srp.getDropdown1(): srp.getDropdown2()), 
		    				  (srp.getHeading1()==PRODUCT ? srp.getDropdown1(): srp.getDropdown2()), srp.getFromDate(), srp.getToDate(),
		    				  srp.getIncExCountries(), srp.getIncExProducts(), srp.getIncExCompanies()
		    				  ,srp.getDateParm(),access);		    		 

		    		    logger.warning(sql3.getQuery());
		    		    resultSet = statement.executeQuery(sql3.getQuery());

						 List <JSONObject> l = getObj5(resultSet, statement, sql3.getQuery(),"company" ,"year","country","product",
									    colHeading,columnModel, cna.getColumnNameObject(), srp.getSalesOrProduct(),true);
		                   
	                      JSONObject obj5 = l.get(0);
	                      JSONObject obj8 = l.get(1);
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
				    		 }  
		    		    
		    		  
		    	  }
		    	  
		    	  
		    	  con.close();   
	return "idsEditmain";
}
	 
	 
private List <JSONObject> getObj5(ResultSet resultSet, Statement statement, String query,String ONE ,String TWO,String THREE,String FOUR
		                ,String colHeading,  ColumnModel columnModel, JSONObject columnNameObj, int ProdOrSales, boolean hasAll) throws JSONException, SQLException{
	
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


private List <JSONObject> getObj5GroupSummary(ResultSet resultSet, Statement statement, String query,String ONE ,String TWO, 
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