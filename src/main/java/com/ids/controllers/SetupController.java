package com.ids.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;



import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.apphosting.api.DeadlineExceededException;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.ids.businessLogic.DropdownInterface;
import com.ids.context.GetBeansFromContext;
import com.ids.user.User;



@Controller
@Transactional
public class SetupController {

	private Connection con;
	
	private final static Logger logger = Logger.getLogger(SetupController.class.getName()); 
	
	private  boolean viaGet = false;
	
   // static final Logger logger = LoggerFactory.getLogger(SetupController.class);
    
    
	 @Transactional
	 @RequestMapping(value="/setup2", method = RequestMethod.POST)
   public String save(
		   HttpServletResponse response,  HttpServletRequest request,
                   ModelMap map) throws SQLException,  IOException  {

    	
    	logger.warning("ABOVE THE STUFF a");
    	System.out.println("ABOVE THE STUFF b");
    	
  		 HttpSession session = request.getSession(true);
  		 
  		 
  		Enumeration keys = request.getParameterNames();  
  	   while (keys.hasMoreElements() )  
  	   {  
  	      String key = (String)keys.nextElement();  
  	    logger.warning("key name: "+key);  
  	   
  	      //To retrieve a single value  
  	      String value = request.getParameter(key);  
  	    logger.warning("parm value: "+value);  
  	   

  	   }  
  	   
  		 
  		 
  		 
	     User user =(User) session.getAttribute("myUser");
    	
		 if (user==null || !user.getUserName().equals("changestuff")) {
			 return "redirect:/login";
		 }
    	
		 logger.warning("ABOVE THE STUFF aa");
	    	System.out.println("ABOVE THE STUFF bb");
	    	

    	
	    logger.warning("ABOVE THE STUFF aaa: " +request.getParameter("table"));
    	System.out.println("ABOVE THE STUFF bbb");
    	
    	
    	
        DriverManager.registerDriver(new AppEngineDriver());
        con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","123smiggles321","Wednesday");
      //  con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","user","password");
       

		 con.setAutoCommit(false);
		 
		 
		 String access = request.getParameter("access");

	//	 Statement statement1 = con.createStatement();
   
    	

    	ServletFileUpload upload = new ServletFileUpload();


    	try{
    	FileItemIterator iter = upload.getItemIterator(request);

    	boolean fileTypeFound = false;
    	String fileType="";
    	

    	  
    	while (iter.hasNext()) {

    	    FileItemStream item = iter.next();
    	    String name = item.getFieldName();
    	    if (name.equals("submitBtn")){
    	    	fileType="none";
    	    }
    	    InputStream stream = item.openStream();
    	    if (!fileTypeFound)
    	     {
    	    //	logger.warning("Form field " + name + " with value "
    	    //        + Streams.asString(stream) + " detected.");
    	    	fileTypeFound=true;
    	    	fileType = Streams.asString(stream);
    	    	
    	    	if (fileType.equals("resetOthers")){
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM " +
                            "   Facts_"+access+" where companyId = -1 and access='"+request.getParameter("access")+"' ");
                    statement.executeUpdate();
                     con.commit();
    	    		
    	    		 statement = (PreparedStatement) con.prepareStatement("DELETE FROM " +
    	    				                                                       "   Company where id = -1 and access='"+request.getParameter("access")+"' ");
    	    		 
    	    		statement.executeUpdate();
    	    		con.commit();
    	    		
        	    	String sql = " Insert into Company (Name, ShortName, Id, access ) values ('Others' , 'OTH' , -1 ,'"+request.getParameter("access")+"' ) ";
                    statement = (PreparedStatement) con.prepareStatement(sql);

                    statement.executeUpdate();
                    con.commit();
        	    	map.addAttribute("displaytype2","block");
        	    	map.addAttribute("displaytype","none");
     			   map.addAttribute("successtext","Other Companies successfully deleted - ready for load!");
     			   return  "setup";
    	    		
    	    	}
    	    	if (fileType.equals("facts")) { 
    	    		map.addAttribute("fileType","factsPARTDONE");
    	    		
    	        
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM Facts_"+access+"   where access='"+request.getParameter("access")+"' ");
    	    		statement.executeUpdate();
    	    		con.commit();
    	     }
    	    } else {

    	    	if (fileType.contains("facts")){
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Facts_"+access+"  (companyId, countryid, productid, year, sales_production, quantity, flag, access) " +
    	    			" values (?,?,?,?,?, ?, ?,?)";
    	    //	String sql = "INSERT INTO Product (Name, Shortname, Id, SortOrder ) values (? , ? , ? ,? ) ";
                PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);


    	    	
    	    	//String query1 = item.getName();
    	    	String sCurrentLine= null;
    	    	int commitCount=0;
    	    	int totalCount= 0;
    	        int upTo10K =0;
    	        int readToTotalCount=0;
    	        int commitCount1=0;
    	    	while ((sCurrentLine = br.readLine()) != null) {
    	    		
    	    		readToTotalCount+=1;
    	    		
    	    		if ( totalCount >=readToTotalCount ) {
    	    			
    	    		}else{
    	    		
    	    		commitCount+=1;
    	    		commitCount1+=1;
    	    		String[] parms = sCurrentLine.split("\t");
    	    		 statement.setString(1,  parms[0]);
    	             statement.setString(2,  parms[1]);
    	             statement.setInt(3,  Integer.parseInt(parms[2]));
    	             
    	    		 statement.setInt(4,  Integer.parseInt(parms[3]));
    	             statement.setInt(5,  Integer.parseInt(parms[4]));
    	             statement.setString(6, parms[5]);
    	             if (parms.length> 6){
    	    		    statement.setString(7,  parms[6]);
    	             } else {
    	            	 statement.setString(7,  " "); 
    	             }
    	             statement.setString(8,access);
    	             statement.addBatch();
    	             
    	    	//	statement.executeUpdate();
    	    		if (commitCount1>=5000) {
    	    			statement.executeBatch();
    	    			con.commit();
    	    			commitCount1=0;
    	    			
    	    			 upTo10K += commitCount;

    	    			
    	    		}
    	    		} //end of silly else
    	    		
    			 
    	    	}
    	    	map.addAttribute("displaytype2","block");
    	    	map.addAttribute("displaytype","none");
 			   map.addAttribute("done",commitCount);
 			  map.addAttribute("fileType","factsPARTDONE");
 			   map.addAttribute("successtext"," records committed. LOAD COMPLETE!");
 			  statement.executeBatch();
    	    	con.commit();
    	        // Process the input stream
    	    	}
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	if (fileType.contains("otherFacts")){
        	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        	    	
        	    	String sql = "INSERT INTO Facts_"+access+"  (companyId, countryid, productid, year, sales_production, quantity, flag, access) " +
        	    			" values (?,?,?,?,?, ?, ?, ?)";
        	    //	String sql = "INSERT INTO Product (Name, Shortname, Id, SortOrder ) values (? , ? , ? ,? ) ";
                    PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);


        	    	
        	    	//String query1 = item.getName();
        	    	String sCurrentLine= null;
        	    	int commitCount=0;
        	    //	int totalCount= Integer.parseInt(name.replace("myfile",""));
        	        int upTo10K =0;
        	        int readToTotalCount=0;
        	        int commitCount1=0;
        	    	while ((sCurrentLine = br.readLine()) != null) {
        	    		
        	    		readToTotalCount+=1;
        	    		
        	    	//	if ( totalCount >=readToTotalCount ) {
        	    			
        	    	//	}else{
        	    		
        	    		commitCount+=1;
        	    		commitCount1+=1;
        	    		String[] parms = sCurrentLine.split("\t");
        	    		 statement.setString(1,  parms[0]);
        	             statement.setString(2,  parms[1]);
        	             statement.setInt(3,  Integer.parseInt(parms[2]));
        	             
        	    		 statement.setInt(4,  Integer.parseInt(parms[3]));
        	             statement.setInt(5,  Integer.parseInt(parms[4]));
        	             statement.setString(6, parms[5]);
        	             if (parms.length> 6){
        	    		    statement.setString(7,  parms[6]);
        	             } else {
        	            	 statement.setString(7,  " "); 
        	             }
        	             statement.setString(8," ");
        	             statement.addBatch();
        	             
        	    	//	statement.executeUpdate();
        	    		if (commitCount1>=5000) {
        	    			statement.executeBatch();
        	    			con.commit();
        	    			commitCount1=0;
        	    			
        	    			 upTo10K += commitCount;

        	    			
        	    		}
        	    	//	} //end of silly else
        	    		
        			 
        	    	}
        	    	map.addAttribute("displaytype2","block");
        	    	map.addAttribute("displaytype","none");
     			   map.addAttribute("done",commitCount);
     			  map.addAttribute("fileType","factsPARTDONE");
     			   map.addAttribute("successtext"," records committed. LOAD COMPLETE!");
     			  statement.executeBatch();
        	    	con.commit();
        	        // Process the input stream
        	    	}
        	    	
        	    	
    	    	
    	    	
    	    	
    	    	if (fileType.equals("products")){
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM Product where access='"+request.getParameter("access")+"' ");
    	    		statement.executeUpdate();
    	    		con.commit();
    	    		
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Product (Name, Shortname, Id, SortOrder, access ) values (? , ? , ? ,? ,?) ";
                statement = (PreparedStatement) con.prepareStatement(sql);

    	    	String sCurrentLine= null;
                int count=0;
    	    	while ((sCurrentLine = br.readLine()) != null) {

    	    		String[] parms = sCurrentLine.split("\t");
    	    		 if (parms.length > 2) {
    	    		 statement.setString(1,  parms[0]);
    	             statement.setString(2,  parms[1]);
    	             statement.setInt(3,  Integer.parseInt(parms[2]));
    	             
    	    		 statement.setInt(4,  Integer.parseInt(parms[3]));
    	            
    	    		 statement.setString(5, request.getParameter("access"));
    	    		statement.executeUpdate();
    	    		count+=1;
    	    		 }

    	    	}

    	    	con.commit();
    	    	map.addAttribute("displaytype2","block");
    	    	map.addAttribute("displaytype","none");
 			   map.addAttribute("successtext",count+" Products committed. COMPLETE!!!");
 			   return  "setup";
    	        // Process the input stream
    	    	}
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	if (fileType.equals("countries")){
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM Country where  access='"+request.getParameter("access")+"' ");
    	    		statement.executeUpdate();
    	    		con.commit();
    	    		
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Country (Country, ShortName, Id, SortOrder, access ) values (? , ? , ? ,? ,?) ";
                statement = (PreparedStatement) con.prepareStatement(sql);

    	    	String sCurrentLine= null;

    	    	int count=0;
    	    	while ((sCurrentLine = br.readLine()) != null) {

    	    		String[] parms = sCurrentLine.split("\t");
    	    		 statement.setString(1,  parms[0]);
    	             statement.setString(2,  parms[1]);
    	             statement.setInt(3,  Integer.parseInt(parms[2]));
    	             
    	    		 statement.setInt(4,  Integer.parseInt(parms[3]));
    	    		 statement.setString(5, request.getParameter("access"));
    	            
    	    		statement.executeUpdate();
    	    		count+=1;

    	    	}

    	    	con.commit();
    	    	map.addAttribute("displaytype2","block");
    	    	map.addAttribute("displaytype","none");
 			   map.addAttribute("successtext",count+" Countries committed. COMPLETE!!!");
 			   return  "setup";
    	        // Process the input stream
    	    	}
    	    	
    	    	
    	    	
    	
    	    	
    	    	
    	    	
    	    	if (fileType.equals("companies")){
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM Company  where  access='"+request.getParameter("access")+"' ");
    	    		statement.executeUpdate();
    	    		con.commit();
    	    		
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Company (Name, ShortName, Id, access ) values (? , ? , ? ,? ) ";
                statement = (PreparedStatement) con.prepareStatement(sql);

    	    	String sCurrentLine= null;

    	    	int count=0;
    	    	while ((sCurrentLine = br.readLine()) != null) {
    	    		String[] parms = sCurrentLine.split("\t");
    	    		 statement.setString(1,  parms[0]);
    	             statement.setString(2,  parms[1]);
    	             statement.setInt(3,  Integer.parseInt(parms[2]));
    	             statement.setString(4, request.getParameter("access"));
    	    		statement.executeUpdate();
    	    		count+=1;

    	    	}
    	    	
    	    	
    	    	int other=0;
    	    	con.commit();	   
    	    	
    	    	/*
    	    	 Statement statement2 = con.createStatement();
    	    	 Statement statement3 = con.createStatement();
    	    	 Statement statement1 = con.createStatement();
    	    	
    	    	String query1 = "DELETE from Facts where companyId = -1";
    		      statement2.executeUpdate(query1);
    		      
    		      query1 = "DELETE from Company where id= -1";
    		      statement2.executeUpdate(query1);
    		      
    		      query1 = "INSERT INTO Company (name, shortname, id) values ('OTHER','OTH',-1) ";
    		      statement2.executeUpdate(query1);
    		    //  loop 0: Sales or Prod
    		      
    		      query1 = "SELECT * from Facts  where companyId = 11 ";

    		      
    		      ResultSet resultSet1 = null;
    		      ResultSet resultSet2 = null;
    		      con.commit();
    		      resultSet1 = statement1.executeQuery(query1);
    		      List<String> lines = new ArrayList<String>();
    		   //   List<String> lines2 = new ArrayList<String>();
    		      logger.warning("check how many times"); */
    		/*      while (resultSet1.next()) {
    		    	  

    		    	 int totalQuantity = Integer.parseInt(resultSet1.getString("quantity"));
    		    	  String totalYear = resultSet1.getString("year");
    		    	  String totalProduct = resultSet1.getString("productid");
    		    	  String totalCountry = resultSet1.getString("countryid");
    		    	  String totalsalesPo = resultSet1.getString("sales_production");
    		    	  String totalFlag =  resultSet1.getString("flag");
    		    	  
    		    	  
    		    	  
    		    	  String query2 =	  " INSERT INTO Facts " +
		    	  		"(companyId, countryId, productId, year,sales_production,quantity,flag) " +
                            "SELECT -1 as companyId ,countryId, productId, year,sales_production, sum(quantity) as quantity ,flag from Facts " +
    		    	  		" where year = " +totalYear + 
    		    	  		" and productid = "+totalProduct +
    		    	  		" and countryId = " + totalCountry +
    		    	  		" and quantity > 0 "+
    		    	  		" and sales_production = " + totalsalesPo +
    		    	  		" and flag = '"+totalFlag +"' " +
    		    	  		" and companyId != 11 and companyId != -1 " +
    		    	  		" group by -1,countryId, productId, year,sales_production,quantity,flag "+
    		    	  		" HAVING sum(quantity) <  "+ totalQuantity;
    		    	  
    		    	  lines.add(query2);
    		    	//  lines2.add(totalYear+"|"+totalQuantity+"|"+totalProduct+"|"+ totalCountry+"|"+ totalsalesPo+"|"+totalFlag+"|");
    		    	  

    		    	  }
    		      */
    	    	/*
    		      resultSet1.close();
    		      logger.warning("size of lines: "+lines.size());
    		      int lineIt = 0;
    		      for (String s: lines) {
		    	//  logger.warning(s);
    		    	  /*
    		    	int  totalYear=	  Integer.parseInt(lines2.get(lineIt).split("\\|")[0]);
    		    	int totalQuantity =    Integer.parseInt(lines2.get(lineIt).split("\\|")[1]);
    		    	int totalProduct =    Integer.parseInt(lines2.get(lineIt).split("\\|")[2]);
    		    	int totalCountry =    Integer.parseInt(lines2.get(lineIt).split("\\|")[3]);
    		    	String totalsalesPo =    lines2.get(lineIt).split("\\|")[4];
    		    	String totalFlag =    lines2.get(lineIt).split("\\|")[5];
    		    	
    		    	 lineIt+=1;
    		    	  
    		    	  statement3.addBatch(s);
    		    	  
    		    	  if (lineIt>=500) {
    		    		  logger.warning("into commit");
      	    			statement3.executeBatch();
      	    			con.commit();
      	    			lineIt=0;
      	    			
    		    	  }
    		    	  
		    	//  resultSet2 = statement2.executeQuery(s);

		    /*	   List<String> lastLines = new ArrayList<String>();
		    	   
		    	  while (resultSet2.next()) {
		    		  
		    		  if (resultSet2.getString("quantity")==null || resultSet2.getString("quantity").equals("0")) {
		    			  continue;
		    		  }
		    		  int smallerQuant = Integer.parseInt(resultSet2.getString("quantity"));
		    		  if (smallerQuant > totalQuantity){

		    		  }else{
		    			  
		    		  if (totalQuantity-smallerQuant>0) {
			    	  String query3 = " INSERT INTO Facts" +
			    	  		"(companyId, countryId, productId, year,sales_production,quantity,flag) " +
			    	  		" values (-1, "+totalCountry+","+totalProduct+","+totalYear+
			    			  ","+totalsalesPo+","+(totalQuantity-smallerQuant)+",'"+
			    			  totalFlag+"')";
			    	  other+=1;
			    	  

			    	   lastLines.add(query3);*/
			    	//  statement3.executeUpdate(query3);
		    		//  }
    		    	  
    		    	  
    		    	  

		    	//  }

		   /* 		  
		    	  }
    		      
            	    statement2.executeBatch();
          			con.commit();
    	    

    		   //   }

    		     
    	    	*/
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    		      con.commit();
    	    	map.addAttribute("displaytype2","block");
    	    	map.addAttribute("displaytype","none");
 			   map.addAttribute("successtext"," Companies committed. (plus  'Other' companies. COMPLETE!!!");
 			   return  "setup";
    	        // Process the input stream
    	    	}
    	    	
    	    	
    	    	
    	 
    	    	
    	    	
    	    	
    	    }
    	}
    	}
    	catch(Exception e ){
    		logger.warning("FAILED");
    		e.printStackTrace();
    		 logger.log(Level.SEVERE, e.toString(), e);
 	    	map.addAttribute("displaytype2","none");
 	    	map.addAttribute("displaytype","none");
    	}
    	
    	
    




	//   String query1 = "INSERT INTO Country (country,shortname,id,sortorder) VALUES ('SPAIN','SPA',2,14)";


	  //    statement1.executeUpdate(query1);
	      con.close() ; 
	     

	   //   map.addAttribute("successtext"," records committed. COMPLETE!!!");
	   // 	map.addAttribute("displaytype2","block");
	   // 	map.addAttribute("displaytype","none");

return "setup";
		 
	 }
    
	 @Transactional
	 @RequestMapping(value="/setup2", method = RequestMethod.GET)
	public String postMethodTwo(
           HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws IOException, JSONException, SQLException {
		 
		 logger.warning("Entering application via GET");


	    	logger.warning("FIRST TIME");
	    	model.addAttribute("rowCount",0);
			   model.addAttribute("displaytype","none");
			   model.addAttribute("displaytype2","none");
			   model.addAttribute("fileType","facts");
			 //  model.addAttribute("fileType2","products");
			//   model.addAttribute("fileType4","companies");
			 //  model.addAttribute("fileType3","countries");
    	return "setup";
	 }
	
}

