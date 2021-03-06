package com.ids.controllers;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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


import com.ids.businessLogic.DropdownInterface;
import com.ids.context.GetBeansFromContext;
import com.ids.user.User;

// Chris

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Header;

///=


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


    	
  		 HttpSession session = request.getSession(true);

	     User user =(User) session.getAttribute("myUser");
    	
		 if (user==null || !user.getAccess().equals("a")) {
			 return "redirect:/login";
		 }
		 GetBeansFromContext gcfc = new GetBeansFromContext();
		 con = gcfc.myConnection();
		 con.setAutoCommit(false);
		 
		 
		 String access = request.getParameter("access");
		 logger.warning("access: " + access);
		 
	  
	// Save radio button selection to pass back 
		 String IDSversion = "";
		 if (access.equals("w")) {
			 map.addAttribute("w_Selected","checked"); 
			 IDSversion = "IDS";
		 } else if (access.equals("c")) {
			 map.addAttribute("c_Selected","checked");
			 IDSversion = "CDS";
		 } else if (access.equals("i")) {
			 map.addAttribute("i_Selected","checked"); 
			 IDSversion = "INDS";
		 }	;
		 
	   	String multiplier="";

	    	

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
    	    logger.warning("name: "+name);
    	    InputStream stream = item.openStream();
    	    if (!fileTypeFound)
    	     {

    	    	fileTypeFound=true;
    	    	fileType = Streams.asString(stream);
    	    	
    	    } else {

    	    	if (fileType.contains("facts") && !fileType.contains("otherFacts")){
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
	    	    DropTemp("tempFacts_"+access);	
	    	    
	   		    String sql = "create table tempFacts_"+access+"  as select * from Facts_"+access+" where 1<1";      
		        logger.warning("current sql: "+sql);
		        PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
		        statement.execute();	

		                
	    	      	    	
    	        sql = "INSERT INTO tempFacts_"+access+"  (companyId, countryid, productid, year, sales_production, quantity, flag, access) " +
    	    			" values (?"+multiplier+",?"+multiplier+",?"+multiplier+",?,?, ?, ?,?)";
    	    	logger.warning("current sql: "+sql);
    	    	
		    	statement = con.prepareStatement(sql);    	    	
             //   PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
    	    	
    	    	//String query1 = item.getName();
    	    	String sCurrentLine= null;
    	    	int commitCount=0;
    	    	int totalCount= 0;
    	        int upTo10K =0;
    	        int readToTotalCount=0;
    	        int commitCount1=0;
    	        
    	        try {
    	        	
    	    	while ((sCurrentLine = br.readLine()) != null) {
    	    		
    	    		readToTotalCount+=1;
    	    		
    	    	//	if ( totalCount >=readToTotalCount ) {
    	    			
    	    	//	}else{
    	    		
    	    		commitCount+=1;
    	    		commitCount1+=1;
    	    		String[] parms = sCurrentLine.split("\t");
        	    	
    	    		if (commitCount == 1) {

    	    	    	logger.warning("first time parm 6 = "+parms[7]+" parms 7 =  " + parms[8]);
    	    	    	
    	    			if (parms.length < 8){
    	    				logger.warning("Cannot read file - should be a Main.unl ");
    	    				map.addAttribute("errortext"," Cannot read file - should be a Main.unl"); 
   	    	    	     	map.addAttribute("displaytype2","none");
    	   	    	    	map.addAttribute("displaytype","block");
  	     	    	       con.close();
  	    	    	    	return  "setup";
        	    		}
    	    	    	    			
    	    	    	if (!parms[8].equals("Main")){
    	    				logger.warning("must be a Main.unl ");				    	    				
    	    				 map.addAttribute("errortext"," Wrong table - file must be a Main.unl!");
    	    	    	     map.addAttribute("displaytype2","none");
    	   	   	    	     map.addAttribute("displaytype","block");
   	    	    	       con.close();   
   	    	    	    	return  "setup";
    	    			}
        	    		if (!parms[7].equals(IDSversion)){
    	     			    
    	    				logger.warning("Wrong Version");
   	    				    map.addAttribute("errortext"," Wrong Database : " + parms[7].toString() + " selected - file is :" + IDSversion.toString());  
   	    	    	     	map.addAttribute("displaytype2","none");
   	   	    	    	   map.addAttribute("displaytype","block");
   	    	    	       con.close();
   	    	    	    	return  "setup";
        	    		}
        	    		
        	    	}	
    	    		
 
    	    //		logger.warning(sCurrentLine);
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
    	    	//	} //end of silly else
    	    		
    			 
    	    	}
    	    	con.commit();
    			statement.executeBatch();    	    	

	    		if  (readToTotalCount==0) {
    	    		logger.warning("No ROWS IN MAIN TABLE SELECTED");
    				map.addAttribute("errortext","ERROR reading file - must be a Main.unl"); 
  	    	    	map.addAttribute("displaytype2","none");
  	    	    	map.addAttribute("displaytype","block");
  	    	    	
  	    	       con.close();
 	 	    	   return  "setup";  	    	    	
	    		}
    			
    			// update all company ID to 11 for CDS
    			
    			if (access.equals("i")) {
	   		      sql = "Update tempFacts_"+access+" set CompanyID = 11 WHERE companyID = 1003 " ;
		    	    	logger.warning("UPDATE INDIAN ALL COMPANY ID sql: "+sql);
		    	    	statement = con.prepareStatement(sql);
		                statement.execute();
		    	    	con.commit();
    			};
    			
    			ResultSet resultSet = null;
	              sql="SELECT MAX(year) as year1 from tempFacts_"+access+" where Sales_Production = 2 ";
	              statement = con.prepareStatement(sql);
	      	      resultSet = statement.executeQuery(sql);
	   		      resultSet.next();
	   		      String myYear = resultSet.getString("year1");
	   		      
	   		      
	    	    DropTemp("tempOthFacts_"+access);	   		 
	    	    
	   		      sql = "create table tempOthFacts_"+access+"  as select 0 id , -1  companyId,countryId, ProductId, year," +
	   		      		" sales_production, sum(quantity)*-1 quantity,\'I\' flag, '" + access + "' access" +
	   		      		" from tempFacts_"+access+" WHERE CompanyID <> 11"  + 
	   		      		" and year <= " + myYear  +
	   		      		" Group by countryId, ProductId, year,sales_production ";      
	    	    	logger.warning("current sql: "+sql);
	    	    	statement = con.prepareStatement(sql);
	                statement.execute();	   		      
	   		
	                sql = "Insert into tempOthFacts_"+access+" select 0 id , -1  companyId,countryId, ProductId, year," +
	                		" sales_production, sum(quantity) quantity,\'I\' flag, '" + access + "' access " +
	                		" from tempFacts_"+access+" WHERE CompanyID = 11"  + 
	                		" and year <= " + myYear  +
	                		" Group by countryId, ProductId, year,sales_production";   
	    	    	logger.warning("current sql: "+sql);
	    	    	statement = con.prepareStatement(sql);
	    	    	statement.execute();		  
	    	    	con.commit();
	    	    			    	   

	    	    	sql = "Insert into tempFacts_"+access+" select 0 id , -1  companyId,countryId, ProductId, year," +
	    	    			" sales_production,sum(quantity) quantity,\'I\' flag, access" +
	    	    			" from tempOthFacts_"+access+ 
	    	    			" Group by countryId, ProductId, year,sales_production,access";
	    	    	logger.warning("current sql: "+sql);
	    	    	statement = con.prepareStatement(sql);
	    	    	statement.execute();	
	    	    	con.commit();	    	    	
	    	    	
	    	    	// update from the temp table in 1 transaction 
	    	    	
	    	    	sql = "delete from FactsEdit_"+access+"  where 1=1";
	    	    	logger.warning("current sql: "+sql);
	    	    	statement = con.prepareStatement(sql);
	    	    	statement.execute();	    	    

	    	    	//con.commit(); 	
	    	    	
	    	    	sql = "insert into FactsEdit_"+access+"  select * from tempFacts_"+access;
	    	    	logger.warning("current sql: "+sql);
	    	    	statement = con.prepareStatement(sql);
	    	    	statement.execute();
	    	    	
	    	    	//con.commit(); 	

	    	    	sql = "delete from Facts_"+access+"  where 1=1";
	    	    	logger.warning("current sql: "+sql);
	    	    	statement = con.prepareStatement(sql);
	    	    	statement.execute();	    	    
	    	    	
	    	    	//con.commit(); 	
	    	    	
	    	    	sql = "insert into Facts_"+access+"  select * from tempFacts_"+access;
	    	    	logger.warning("current sql: "+sql);
	    	    	statement = con.prepareStatement(sql);
	    	    	statement.execute();
	    	    	
	    	    	sql = " UPDATE last_update set last_update_time = NOW() where access = '"+access+"'";
	    		    statement.executeUpdate(sql);
	    	    	
	    	    	con.commit(); 	
    	 /*   	   	      
 			    
 			    ///// CREATE THE EXCEL DOWNLOAD....
 			   FileOutputStream fileOut = new FileOutputStream("../webapps/ids/pages/"+IDSversion+".xlsx");
 			   
 			   sql =    " select sales_production, product.NAME product, country.NAME country, Quantity quantity, year, company.NAME company " +
 					    " FROM facts_"+access+" main,country,product,company " +
 					    " WHERE main.countryID = country.ID and main.productID = product.ID  and main.companyID = company.ID " + 
 					    " order By  Sales_Production, product, country , quantity, Year, company ";
 			   
 			  statement = con.prepareStatement(sql);
      	      resultSet = statement.executeQuery(sql);
   
      		 HSSFWorkbook workbook = new HSSFWorkbook();
		  	 HSSFSheet worksheet = workbook.createSheet("Off-Highway Research");
			 
		  	 
		  	 Header header = worksheet.getHeader();
			 header.setCenter("Center Header");
			 header.setLeft("Left Header");
			 header.setRight(HSSFHeader.font("Stencil-Normal", "Italic") +
			                    HSSFHeader.fontSize((short) 16) + "Right w/ Stencil-Normal Italic font and size 16");
			 

			 HSSFCellStyle cellStyle1 = workbook.createCellStyle();  
		     //cellStyle1 = workbook.createCellStyle();  
		     HSSFFont hSSFFont1 = workbook.createFont();  
		     hSSFFont1.setFontName(HSSFFont.FONT_ARIAL);  
		     hSSFFont1.setFontHeightInPoints((short) 16);  
		     hSSFFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		     hSSFFont1.setColor(HSSFColor.BLACK.index);  
		     cellStyle1.setFont(hSSFFont1);
		        
		     String sales_production = "";
		     String product = "";
		     String country = "";
		     String company = "";
		     String quantity = "";
		     String year = "";
		      
		     int k=0;
      	      while (resultSet.next()) {
      	    	  
      	    	 sales_production = resultSet.getString("sales_production");
    		     product = resultSet.getString("product");
    		     country = resultSet.getString("country");
    		     company = resultSet.getString("company");
    		     quantity = resultSet.getString("year");
    		     year = resultSet.getString("quantity");
    		     

      	    	HSSFRow  nextRow = worksheet.createRow((short) k);
      	    	
      	    	k+=1;
      	    	
      	        HSSFCell cellA = nextRow.createCell((short) 0);
      	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
      	    	cellA.setCellValue(sales_production);
      	    	
      	        cellA = nextRow.createCell((short) 1);
      	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
      	    	cellA.setCellValue(product);

      	        cellA = nextRow.createCell((short) 2);
      	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
      	    	cellA.setCellValue(country);

      	        cellA = nextRow.createCell((short) 3);
      	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
      	    	cellA.setCellValue(company);
      	    	

      	        cellA = nextRow.createCell((short) 4);
      	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
      	    	cellA.setCellValue(year);
      	    	

      	        cellA = nextRow.createCell((short) 5);
      	        cellA.setCellType(Cell.CELL_TYPE_NUMERIC);
			    //  cellA.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
				//   cellA.setCellType(Cell.CELL_TYPE_STRING);
			      						      
			      //cellA.setCellValue(" "+nf.format(Integer.parseInt(myDataArray.getJSONObject(0).getString(s).trim().replaceAll(",",""))));
				 // String Str = new String(myDataArray.getJSONObject(0).getString(s).trim().replaceAll(",",""));							       
				 cellA.setCellValue(new Double(quantity));	
      	    	//cellA.setCellType(Cell.CELL_TYPE_STRING);
      	    	 cellA.setCellValue(quantity);

      	       }
      	      
      	      workbook.write(fileOut);
      	      fileOut.flush();
      	      fileOut.close();
      	      
      	   */   
      	      
  	    	  map.addAttribute("displaytype2","block");
  	          map.addAttribute("displaytype","none");
			  map.addAttribute("done",commitCount);
		//	  map.addAttribute("fileType","factsPARTDONE");
			    map.addAttribute("successtext"," records committed. LOAD COMPLETE!");
      	      
    	        } 
    	    	catch(Exception e ){
    	    		logger.warning("ERROR in MAIN TABLE LOAD");
    				map.addAttribute("errortext","ERROR reading file - must be a Main.unl"); 
  	    	    	map.addAttribute("displaytype2","none");
  	    	    	map.addAttribute("displaytype","block");
    	    	}
 			  
    	       con.close();
 	    	   return  "setup";

    	        // Process the input stream
    	    	}
    	    	
    	    	
    	    	
    	    	/*
    	    	
    	    	if (fileType.contains("otherFacts")){
    	    		
    	    		logger.warning("here1");
    	    		//String sql = "INSERT INTO Company (name, shortname, Id, access) values ('[Others]','OTH',-1"+multiplier+",'"+access+"') ";
        	    	//Statement statement11 = con.createStatement();
    	    		//statement11.executeUpdate(sql);
    	    		//logger.warning("here2");
    	    		BufferedReader br3 = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

        	    	
        	    	logger.warning("here3");
        	    	 String sql = "INSERT INTO Facts_"+access+"  (companyId, countryid, productid, year, sales_production, quantity, flag, access) " +
        	    			" values (?"+multiplier+",?"+multiplier+",?"+multiplier+",?,?, ?, ?, ?)";
        	    //	String sql = "INSERT INTO Product (Name, Shortname, Id, SortOrder ) values (? , ? , ? ,? ) ";
                    PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

                    logger.warning("here4");
        	    	
        	    	//String query1 = item.getName();
        	    	String sCurrentLine= null;
        	    	int commitCount=0;
        	    //	int totalCount= Integer.parseInt(name.replace("myfile",""));
        	        int upTo10K =0;
        	        int readToTotalCount=0;
        	        int commitCount1=0;
        	        logger.warning("here5");
        	    	while ((sCurrentLine = br3.readLine()) != null) {
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
        	             statement.setString(8,access);
        	             statement.addBatch();
        	             
        	    	//	statement.executeUpdate();
        	    		if (commitCount1>=1000) {
        	    			statement.executeBatch();
        	    			con.commit();
        	    			commitCount1=0;
        	    			
        	    			 upTo10K += commitCount;

        	    			
        	    		}
        	    	//	} //end of silly else
        	    		
        			 
        	    	}

       			  statement.executeBatch();
      	    	con.commit();
      	    	
      	    	
      	    	
      	    	
        	    map.addAttribute("displaytype2","block");
        	    map.addAttribute("displaytype","none");
     			map.addAttribute("done",commitCount);
     			map.addAttribute("fileType","factsPARTDONE");
     			map.addAttribute("successtext"," records committed. LOAD COMPLETE!");

        	      con.close();
        	      return  "setup";
        	    	   
        	        // Process the input stream
        	    	}
        	    	
        	    	
    	    	*/
    	    	
    	    	
    	    	if (fileType.equals("products")){
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM Product where access='"+request.getParameter("access")+"' ");
    	    		statement.executeUpdate();
    	    		con.commit();
    	    		
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Product (Name, Shortname, Id, SortOrder, access ) values (? , ? , ?"+multiplier+" ,? ,?) ";
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
 			   con.close();
 			   return  "setup";
    	        // Process the input stream
    	    	}
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	
    	    	if (fileType.equals("countries")){
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM Country where  access='"+request.getParameter("access")+"' ");
    	    		statement.executeUpdate();
    	    		con.commit();
    	    		
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Country (Country, ShortName, Id, SortOrder, access ) values (? , ? , ?"+multiplier+" ,? ,?) ";
                statement = (PreparedStatement) con.prepareStatement(sql);

    	    	String sCurrentLine= null;

    	    	int count=0;
    	    	while ((sCurrentLine = br.readLine()) != null) {

    	    		String[] parms = sCurrentLine.split("\t");
    	    		 statement.setString(1,  parms[0]);
    	             statement.setString(2,  parms[1]);
    	             if ( Integer.parseInt(parms[2])==0) {
    	            	 continue;
    	             }
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
 			   con.close();
 			   return  "setup";
    	        // Process the input stream
    	    	}
    	    	
    	    	
    	    	
    	
    	    	
    	    	
    	    	
    	    	if (fileType.equals("companies")){
    	    		
    	    		PreparedStatement statement = (PreparedStatement) con.prepareStatement("DELETE FROM Company  where  ID > 0 and  access='"+request.getParameter("access")+"' ");
    	    		statement.executeUpdate();
    	    		//con.commit();
    	    		
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Company (Name, ShortName, Id, access ) values (? , ? , ?"+multiplier+" ,? ) ";
                statement = (PreparedStatement) con.prepareStatement(sql);

    	    	String sCurrentLine= null;

    	    	int count=0;
    	    	try 
    	    	{
    	    	while ((sCurrentLine = br.readLine()) != null) {
    	    		
    	    		String[] parms = sCurrentLine.split("\t");
    	    		if (parms.length==0)
    	    			continue;
    	    		 statement.setString(1,  parms[0]);
    	             statement.setString(2,  parms[1]);
    	             statement.setInt(3,  Integer.parseInt(parms[2]));
    	             statement.setString(4, request.getParameter("access"));
    	    		statement.executeUpdate();
    	    		count+=1;

    	    		
      	    		if (count == 1) {

    	    	    	logger.warning("first time parm 4 = "+parms[parms.length-1]+" parms 6 =  " + parms[parms.length-2]);
    	    	    	
    	    			//if (parms.length <6 ){
    	    			// logger.warning("Not enough values in the file - should be a Company.unl ");

    	    				//map.addAttribute("errortext"," Cannot read file - should be a Company.unl"); 
   	    	    	     	//map.addAttribute("displaytype2","none");
    	   	    	        //map.addAttribute("displaytype","block");
  	    	 	    	    //con.rollback();
  	    	    	    	//return  "setup";
        	    		//}
    	    	    	    			
    	    	    	if (!parms[parms.length-1].equals("Company")){
    	    				logger.warning("must be a Company.unl ");
  		    	    				
    	    				 map.addAttribute("errortext"," Wrong data - must be a Company.unl!");
    	    	    	     map.addAttribute("displaytype2","none");
    	   	   	    	     map.addAttribute("displaytype","block");
  	    	 	    	    con.rollback();
    	    	 	    	   return  "setup";
    	    			}
        	    		if (!parms[parms.length-2].equals(IDSversion)){
    	        	    	map.addAttribute("displaytype2","block");

    	    				logger.warning("Wrong Version");
   	    				    map.addAttribute("errortext"," Wrong Database : " + IDSversion.toString() + " selected - file is :" + parms[parms.length-2].toString());  
   	    				    map.addAttribute("displaytype2","none");
   	   	    	    	    map.addAttribute("displaytype","block");

  	    	 	    	    con.rollback();
   	    	 	    	   return  "setup";
        	    		}
        	    		
        	    	
      	    		}	
    	    		
    	    		
    	    		
    	    	}
    	    	
    	    	
    	    	//int other=0;
    	    	if ( count == 0 ) {
    	    		logger.warning("ERROR in company load - zero rows read");
    				map.addAttribute("errortext","ERROR no rows read - must be a Company.unl"); 
  	    	    	map.addAttribute("displaytype2","none");
  	    	    	map.addAttribute("displaytype","block");
  	    	    	con.rollback();
    	 	    	   return  "setup";
    	    	} else
    	    	{
        	    	con.commit();    	    		
    	    	}

    	    	
    			if (access.equals("i")) {
  	   		       sql = "Update Company set ID = 11 WHERE ID = 1003 and access = 'i'" ;
  		    	    	logger.warning("UPDATE INDIAN ALL COMPANY ID sql: "+sql);
  		    	    	statement = con.prepareStatement(sql);
  		                statement.execute();
  		    	    	con.commit();
      			}
    	    	
    	    	map.addAttribute("displaytype2","block");
    	    	map.addAttribute("displaytype","none");
    	    	map.addAttribute("displaytype","none");
 			    map.addAttribute("done",count);
 			    map.addAttribute("successtext"," Companies committed. COMPLETE!!!");
 			    con.close();
 			    return  "setup";
    	        // Process the input stream
    	    	
    	    	}
    	    	catch(Exception e ){
    	    		logger.warning("ERROR in company load");
    				map.addAttribute("errortext","ERROR reading file - must be a Company.unl"); 
  	    	    	map.addAttribute("displaytype2","none");
  	    	    	map.addAttribute("displaytype","block");
    	    	}
    	    	}
    	    	
    	    	    	    	
    	    	
    	    }
    	}
    	}
    	catch(Exception e ){
    		logger.warning("FAILED");
    		e.printStackTrace();
    		 logger.log(Level.SEVERE, e.toString(), e);
	    		logger.warning("ERROR in company load");
			map.addAttribute("errortext","ERROR reading file - must be a Company.unl"); 
   	    	map.addAttribute("displaytype2","none");
   	    	map.addAttribute("displaytype","block");
   	    	
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
	    	
		     if (request.getParameter("access") != null ) {
				 if (request.getParameter("access").equals("w")) {
					 model.addAttribute("w_Selected","checked"); 
				 } else if (request.getParameter("access").equals("c")) {
					 model.addAttribute("c_Selected","checked"); 			 
				 } else if (request.getParameter("access").equals("i")) {
					 model.addAttribute("i_Selected","checked"); 			 
				 };
		     } else {model.addAttribute("w_Selected","checked"); }
		     
		     
	    	model.addAttribute("rowCount",0);
			   model.addAttribute("displaytype","none");
			   model.addAttribute("displaytype2","none");
			   model.addAttribute("fileType","facts");
			   
			   if (request.getParameter("d")!=null) {
				   if (request.getParameter("d").equals("w")){
				      model.addAttribute("done1","<span style='font-weight:bold;color:red;background:#CCFFCC' > IDS data updated in client view! </span>");  
				   }
				   if (request.getParameter("d").equals("c")){
					      model.addAttribute("done2","<span style='font-weight:bold;color:red;background:#CCFFCC' > china saved! </span>");  
					   }
				   if (request.getParameter("d").equals("i")){
					      model.addAttribute("done3","<span style='font-weight:bold;color:red;background:#CCFFCC' > India saved! </span>");  
					   }
				   
			   }
 
				 GetBeansFromContext gcfc = new GetBeansFromContext();
				 con = gcfc.myConnection();
				 con.setAutoCommit(false);
				 
				   model.addAttribute("ajaxPrefix",gcfc.ajaxURLprefix());
				 
			      Statement statement = con.createStatement();

			      ResultSet resultSet = null;

			      String query = "select userId from ids_users order by userId asc ";
			      String options="";
				  resultSet = statement.executeQuery(query);


				   while (resultSet.next()) {
					   options += "<option>"+resultSet.getString("userId")+"</option>";
				   }

				   model.addAttribute("options",options);
				   
         con.close();
    	return "setup";
	 }
	 
	 private void DropTemp(String table){

	    	try{
	    	String sql = "DROP TABLE "+table;
	    	PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);
	    	logger.warning(" Dropping TABLE: "+sql);
            statement.execute();  	 
            con.commit();
          
	    	} 
	    	catch(Exception e ){
	    		logger.warning("Drop tempOthFacts failed");
	    	}
	 }
	
}

