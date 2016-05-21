package com.ids.controllers;

//import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.InputStreamReader;
import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.apache.commons.fileupload.FileItemIterator;
//import org.apache.commons.fileupload.FileItemStream;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.fileupload.util.Streams;
//import org.json.JSONException;

//import java.util.ArrayList;
//import java.util.Enumeration;
//import java.util.List;
//import java.util.logging.Level;
import java.util.logging.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;


//import com.google.appengine.api.mail.MailService.Header;
//import com.ids.businessLogic.DropdownInterface;
import com.ids.context.GetBeansFromContext;
import com.ids.user.User;

import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;





@Controller
@Transactional
public class SetupOtherFactColumn {

	private Connection con;
	
	private final static Logger logger = Logger.getLogger(SetupOtherFactColumn.class.getName()); 
	
	//private  boolean viaGet = false;
   // static final Logger logger = LoggerFactory.getLogger(SetupController.class);
    
    
	 @Transactional
	 @RequestMapping(value="/setup3", method = RequestMethod.POST)
   public String save(
		   HttpServletResponse response,  HttpServletRequest request,
                   ModelMap map) throws SQLException,  IOException  {
		 
  		 HttpSession session = request.getSession(true);

	     User user =(User) session.getAttribute("myUser");
    	
		 if (user==null || !user.getAccess().equals("a")) {
			 return "redirect:/login";
		 }

		 String retVal = "setup";

		 String doAction = request.getParameter("doaction");
		 
		 //String access = request.getParameter("access");
		 //logger.warning("access: " + access);
		 	 
    	//ServletFileUpload upload = new ServletFileUpload();

     	if (doAction.equals("mkExl")) { 
			
	          retVal = updateExcelDownloadfile ( request, response, map); 
	  	   
	   } // end of doaction 
	 
		if (doAction.equals("showUsers")) { 
			
			   retVal = unloadUsersToExcel(request, response, map); 
			} // end of doaction 
					   
		return retVal;
		
	 }

	 @Transactional
	 @RequestMapping(value="/setup4", method = RequestMethod.POST)
   public void save2(
		   HttpServletResponse response,  HttpServletRequest request,
                   ModelMap map) throws SQLException,  IOException  {
		 
  		 HttpSession session = request.getSession(true);

	     User user =(User) session.getAttribute("myUser");
    	
		 if (user==null || !user.getAccess().equals("a")) {
			 return ;
			 //"redirect:/login";
		 }

		 String doAction = request.getParameter("doaction");	 
		 
			if (doAction.equals("unloadMain")) { 
				
				unloadMainDB (request,  response,  map);
				
				} // end of doaction 
			
			if (doAction.equals("unloadComp")) { 
				
				unloadCompanyDB (request,  response,  map);
				
				} // end of doaction 	
	 }

	 
	 /*
	  public void doPost(HttpServletRequest request,
	          HttpServletResponse response) throws ServletException, IOException{
		  
		  //save(request, response);
	  }
	  */
	 
	 
	  public String updateExcelDownloadfile (HttpServletRequest request,
	          HttpServletResponse response, ModelMap map) throws SQLException,  IOException 
	  {
		  
	  		 HttpSession session = request.getSession(true);

		     User user =(User) session.getAttribute("myUser");
	    	
			 if (user==null || !user.getAccess().equals("a")) {
				 return "redirect:/login";
			 }
			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 
			 con = gcfc.myConnection();
			 con.setAutoCommit(false);
			 
			 
			 String access = request.getParameter("access");
			// String doAction = request.getParameter("doaction");
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
			 
		  // 	String multiplier="";

		    	

			 Statement statement = con.createStatement();
			 ResultSet resultSet = null;
		  
		  try{
	    		
			    
			    ///// CREATE THE EXCEL DOWNLOAD....
			  
				// HttpSession session = request.getSession(true);
				// ServletContext context = session.getServletContext();
			   //  String realContextPath = context.getRealPath(request.getContextPath());
			   //  logger.warning(realContextPath);
			   //  String contextPath = realContextPath.replaceFirst("ids","");
			   //  logger.warning(contextPath);
			    // logger.warning(contextPath + "/webapps"+gcfc.ajaxURLprefix()+"/pages/"+IDSversion.toLowerCase()+".xlsx");
			     
			    //opt/IDS/apache-tomcat-7.0.40/webapps/ids/images


					 //  FileOutputStream fileOut = new FileOutputStream(realContextPath+"\\off-highway.xls");
					//	HSSFWorkbook workbook = new HSSFWorkbook();
					//	HSSFSheet worksheet = workbook.createSheet("Off-Highway Research");

					//	workbook.write(fileOut);
					//	fileOut.flush();
					//	fileOut.close();
			  
			    // use this to test on windows. 
			  // FileOutputStream fileOut = new FileOutputStream("../webapps"+gcfc.ajaxURLprefix()+"/pages/"+IDSversion.toLowerCase()+".xlsx");
			  //logger.warning(fileOut.toString());
			  //logger.warning(gcfc.myURL());
			  logger.warning(gcfc.excelDownloadPath());
			  // use this one in prod...   
			   FileOutputStream fileOut = new FileOutputStream( gcfc.excelDownloadPath() + IDSversion.toLowerCase()+".xlsx") ;

			   //FileOutputStream fileOut = new FileOutputStream(IDSversion+".xlsx");			   

				  
			   String sql = " select case Sales_Production WHEN 1 then 'Sales' WHEN 2 then 'Production' END sales_production, " +
			            " product.NAME product, country.country country, Quantity quantity, year, company.NAME company " +
					    " FROM Facts_"+access+" main,country,product,company " +
					    " WHERE main.countryID = country.ID and main.productID = product.ID  and main.companyID = company.ID" +
					    " AND country.access = '" + access + "' and product.access = '" + access + "' AND company.access ='" + access +"'" +
					    " order By  Sales_Production, product, country , quantity, Year, company ";
			   
			   logger.warning(sql);
			   
			  statement = con.prepareStatement(sql);
    	      resultSet = statement.executeQuery(sql);
 
    	//    Workbook[] wbs = new Workbook[] { new HSSFWorkbook(), new XSSFWorkbook() };
    	      
    	    

    	   // Workbook wb = new XSSFWorkbook();
    	    //wb.write(fileOut);
    	    //fileOut.close();
    	                    
    	    //SXSSFWorkbook 
    	     SXSSFWorkbook workbook =  new SXSSFWorkbook();
    		// HSSFWorkbook workbook = new HSSFWorkbook();
    	     SXSSFSheet worksheet = (SXSSFSheet) workbook.createSheet("Off-Highway Research");
		  	// HSSFSheet worksheet = workbook.createSheet("Off-Highway Research");
			 
		  	
    	     XSSFCellStyle cellStyle1 = (XSSFCellStyle) workbook.createCellStyle();  
		     XSSFFont XSSFFont1 = (XSSFFont) workbook.createFont();  
		     XSSFFont1.setFontName(XSSFFont.DEFAULT_FONT_NAME);  
		     XSSFFont1.setFontHeightInPoints((short) 16);  
		     XSSFFont1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
		   //  XSSFFont1.setColor(XSSFColor.BLACK.index);  
		     cellStyle1.setFont(XSSFFont1);
			 
		     String sales_production = "";
		     String product = "";
		     String country = "";
		     String company = "";
		     String quantity = "";
		     String year = "";
		     
		     Cell cellA= null;
		     
		     int k=0;
    	      while (resultSet.next()) {
    	    	  
    	    	 sales_production = resultSet.getString("sales_production");
  		     product = resultSet.getString("product");
  		     country = resultSet.getString("country");
  		     company = resultSet.getString("company");
  		     year = resultSet.getString("year");
  		     quantity = resultSet.getString("quantity");
  		     

    	    //	XSSFRow  nextRow = (XSSFRow) worksheet.createRow(k);
  	        Row  nextRow = worksheet.createRow(k);      	    	
    	    	
  	        k+=1;
    	    	
              
    	        cellA = nextRow.createCell((short) 0);
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
    	        // cellA.setCellType(Cell.CELL_TYPE_NUMERIC);
			    //  cellA.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
				//   cellA.setCellType(Cell.CELL_TYPE_STRING);
			      						      
			      //cellA.setCellValue(" "+nf.format(Integer.parseInt(myDataArray.getJSONObject(0).getString(s).trim().replaceAll(",",""))));
				 // String Str = new String(myDataArray.getJSONObject(0).getString(s).trim().replaceAll(",",""));							       
				 //cellA.setCellValue(new Double(quantity));	
    	         cellA.setCellType(Cell.CELL_TYPE_STRING);
    	    	 cellA.setCellValue(quantity);

    	       }
    	      
    	      workbook.write(fileOut);
    	      fileOut.flush();
    	      fileOut.close();
    	      workbook.close();    	      

    	     
	    	  map.addAttribute("displaytype2","block");
	          map.addAttribute("displaytype","none");
			  map.addAttribute("done",k);
		//	  map.addAttribute("fileType","factsPARTDONE");
			  map.addAttribute("successtext"," ROWS IN EXCEL FILE FOR "+ IDSversion+" CREATED !");
    	       
  	        } 
  	    	catch(Exception e ){
  	    		logger.severe("ERROR CREATING TEMP TABLE"+ e.getMessage());
  				map.addAttribute("errortext","ERROR CREATING EXCEL "); 
	    	    	map.addAttribute("displaytype2","none");
	    	    	map.addAttribute("displaytype","block");
  	    	}
		  finally{
     	      con.close();
		  }
			  

	       return  "setup";
		  
	  }
	  
	  public String unloadCompanyDB (HttpServletRequest request,
	          HttpServletResponse response, ModelMap map) throws SQLException,  IOException 
	  {
		  
		  
	  		 HttpSession session = request.getSession(true);

		     User user =(User) session.getAttribute("myUser");
	    	
			 if (user==null || !user.getAccess().equals("a")) {
				 return "redirect:/login";
			 }
			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 
			 con = gcfc.myConnection();
			 con.setAutoCommit(false);
			 
			 
			 String access = request.getParameter("access");
			 String doAction = request.getParameter("doaction");
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
			 
		  // 	String multiplier="";

			 Statement statement = con.createStatement();
			 ResultSet resultSet = null;


			 try{

					response.setContentType("application/octet-stream");
					
					//response.setHeader("Content-Disposition","attachment;filename=main.csv");
				  
					ServletOutputStream out = response.getOutputStream();
				
						
					String sql = "SELECT DISTINCT name, shortname, C.ID " + 
					" FROM Company C, FactsEdit_"+ access +" F WHERE F.CompanyID = C.ID AND F.CompanyID > 0 and C.access = '"+access+"'";		
					
					logger.warning(sql);
					
					statement = con.prepareStatement(sql);
		      	    resultSet = statement.executeQuery(sql);
		      	    
		      	    String name = "";
		      	    String shortname = ""; 
		      	    String id = "";
		      	    
					StringBuffer writer = new StringBuffer();
		      	    
					int kk = 0;
		      	    while (resultSet.next()){
		      	    
		     	     name = resultSet.getString("name");
		   		     shortname = resultSet.getString("shortname");
		   		     id = resultSet.getString("id");
		   		      
		   			writer.append(name); 
		   			writer.append(',');
		   			writer.append(shortname);
		   			writer.append(',');
		   			writer.append(id);
		 			writer.append(',');
		   			writer.append(IDSversion);
		 			writer.append(',');
		   			writer.append("company"); 			
		   			writer.append('\n');
		   		      	    
		   			kk+=1;
		   			
		      	    }
		      	    
		      	    
		      	    response.setContentLength(writer.length());
		      	    logger.warning("Writer Length" + writer.length());
		      	    
		            // forces download
		            String headerKey = "Content-Disposition";
		            //String headerValue = String.format("attachment; filename=\"%s\"", "main.csv");
		            String headerValue = String.format("attachment; filename=\"company.unl\"");
		            response.setHeader(headerKey, headerValue);
		      	    	
		   			InputStream in = new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));
		      		 
		   			byte[] outputByte = new byte[4096];
		   			//copy binary contect to output stream
		   			while(in.read(outputByte, 0, 4096) != -1)
		   			{
		   				out.write(outputByte, 0, 4096);
		   			}
		   			in.close();
		   			out.flush();
		   			out.close();

			    	  map.addAttribute("displaytype2","block");
		  	          map.addAttribute("displaytype","none");
					  map.addAttribute("done",kk);
				//	  map.addAttribute("fileType","factsPARTDONE");
					  map.addAttribute("successtext","ComanyCSV WITH "+ IDSversion+" ROWS CREATED!");
					    
					}
			    	catch(Exception e ){
			    		logger.severe("ERROR UNLOADING companies"+e.getMessage());
						map.addAttribute("errortext","ERROR UNLOADING companies "); 
			    	    map.addAttribute("displaytype2","none");
			    	    map.addAttribute("displaytype","block");
			    	}
		        return  "setup";			
				
	  }
	    
      public String unloadMainDB (HttpServletRequest request,
			          HttpServletResponse response, ModelMap map) throws SQLException,  IOException 
			  {

    	  		try {
			  		 HttpSession session = request.getSession(true);

				     User user =(User) session.getAttribute("myUser");
			    	
					 if (user==null || !user.getAccess().equals("a")) {
						 return "redirect:/login";
					 }
					 GetBeansFromContext gcfc = new GetBeansFromContext();
					 
					 con = gcfc.myConnection();
					 con.setAutoCommit(false);
					 
					 
					 String access = request.getParameter("access");
					 String doAction = request.getParameter("doaction");
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
					 
				  // 	String multiplier="";

					 Statement statement = con.createStatement();
					 ResultSet resultSet = null;

						response.setContentType("application/octet-stream");
						
						//response.setHeader("Content-Disposition","attachment;filename=main.csv");
					  
						ServletOutputStream out = response.getOutputStream();
					
						String sql = "SELECT companyID, countryID, productID,year, sales_production, quantity" + 
						" FROM Facts_"+access+" WHERE CompanyID >  0 and Quantity > 0 ";
						
						logger.warning(sql);
						
						statement = con.prepareStatement(sql);
			      	    resultSet = statement.executeQuery(sql);
			      	    
			      	    String companyID = "";
			      	    String countryID = ""; 
			      	    String productID = "";
			      	    String year  = ""; 
			      	    String sales_production  = ""; 
			      	    String quantity  = "";
			      	    
						StringBuffer writer = new StringBuffer();
			      	    
						int kk = 0;
			      	    while (resultSet.next()){
			      	    

			      		 companyID = resultSet.getString("companyID");
			   		     countryID = resultSet.getString("countryID");
			   		     productID = resultSet.getString("productID");
			   		     year = resultSet.getString("year");   		     
			      		 sales_production = resultSet.getString("sales_production");
			   		     quantity = resultSet.getString("quantity");
			      	    	
			   		    writer.append(companyID);
			       		writer.append(',');
			   			writer.append(countryID);
			 			writer.append(',');
			   			writer.append(productID);
			   			writer.append(',');
			   			writer.append(year);
			 			writer.append(',');   			
			   			writer.append(sales_production); 
			   			writer.append(',');
			   			writer.append(quantity);
			   			writer.append(',');
			   			writer.append(IDSversion);
			 			writer.append(',');
			   			writer.append("main"); 			
			   			writer.append('\n');
			   		      	    
			   			kk+=1;
			   			
			      	    }
			      	    
			      	    
			      	    response.setContentLength(writer.length());
			      	    logger.warning("Writer Length" + writer.length());
			      	    
			            // forces download
			            String headerKey = "Content-Disposition";
			            //String headerValue = String.format("attachment; filename=\"%s\"", "main.csv");
			            String headerValue = String.format("attachment; filename=\"main.unl\"");
			            response.setHeader(headerKey, headerValue);
			      	    	
			   			InputStream in = new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));
			      		 
			   			byte[] outputByte = new byte[4096];
			   			//copy binary contect to output stream
			   			while(in.read(outputByte, 0, 4096) != -1)
			   			{
			   				out.write(outputByte, 0, 4096);
			   			}
			   			in.close();
			   			out.flush();
			   			out.close();

				    	  map.addAttribute("displaytype2","block");
			  	          map.addAttribute("displaytype","none");
						  map.addAttribute("done",kk);
					//	  map.addAttribute("fileType","factsPARTDONE");
						  map.addAttribute("successtext","CSV WITH "+ IDSversion+" ROWS CREATED!");
						    
						}
				    	catch(Exception e ){
				    		logger.severe("ERROR UNLOADNG main" + e.toString());
							map.addAttribute("errortext","ERROR CREATING EXCEL "); 
				    	    map.addAttribute("displaytype2","none");
				    	    map.addAttribute("displaytype","block");
				    	}
				        return  "setup";			
      }
					 	  	  
	  public String unloadUsersToExcel (HttpServletRequest request,
			          HttpServletResponse response, ModelMap map) throws SQLException,  IOException 
			  {
				  
			  		 HttpSession session = request.getSession(true);

				     User user =(User) session.getAttribute("myUser");
			    	
					 if (user==null || !user.getAccess().equals("a")) {
						 return "redirect:/login";
					 }
					 GetBeansFromContext gcfc = new GetBeansFromContext();
					 
					 con = gcfc.myConnection();
					 con.setAutoCommit(false);
					 
					 
					 String access = request.getParameter("access");
				//	 String doAction = request.getParameter("doaction");
					 logger.warning("access: " + access);
					 
				  

					 
				  // 	String multiplier="";

					 Statement statement = con.createStatement();
					 ResultSet resultSet = null;
					   
						try{
					 	    
					      	SXSSFWorkbook workbook =  new SXSSFWorkbook();
					      	SXSSFSheet worksheet = (SXSSFSheet) workbook.createSheet("Off-Highway Research");

					      	XSSFCellStyle cellStyle1 = (XSSFCellStyle) workbook.createCellStyle();  
						    XSSFFont XSSFFont1 = (XSSFFont) workbook.createFont();  
							XSSFFont1.setFontName(XSSFFont.DEFAULT_FONT_NAME);  
							XSSFFont1.setFontHeightInPoints((short) 20);  
							XSSFFont1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);  
						//	XSSFFont1.setColor(XSSFColor);  
							cellStyle1.setFont(XSSFFont1);
					 	    
							// Create the header
							
					     //   hSSFFont1.setFontName(HSSFFont.FONT_ARIAL);  
					     //   hSSFFont1.setFontHeightInPoints((short) 20);  
					    //    hSSFFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
					     //   hSSFFont1.setColor(HSSFColor.BLACK.index); 
							
						    Cell cellA= null;
					        Row  TitleRow = worksheet.createRow(0); 
					        
				  	        cellA = TitleRow.createCell((short) 0);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("ID");
				  	    	
				  	        cellA = TitleRow.createCell((short) 1);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("USER NAME");

				  	        cellA = TitleRow.createCell((short) 2);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("ACCOUNT");

				  	        cellA = TitleRow.createCell((short) 3);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("IDS");	        
					        
				  	        cellA = TitleRow.createCell((short) 4);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("CDS");
				  	    	
				  	        cellA = TitleRow.createCell((short) 5);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("INDS");

				  	        cellA = TitleRow.createCell((short) 6);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("LOCKED");

				  	        cellA = TitleRow.createCell((short) 7);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue("LAST UPDATED");	  
				  	    	
							XSSFFont1.setFontName(XSSFFont.DEFAULT_FONT_NAME);  
							XSSFFont1.setFontHeightInPoints((short) 16);  
							XSSFFont1.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
						//	XSSFFont1.setColor(XSSFColor);  
							cellStyle1.setFont(XSSFFont1);
								
							String sql=" SELECT id, user_name , " +
							" case access when 'a' then 'admin' when 'e' then 'editor' when 's' then 'aclient' else '' end account, " + 
							" case world when 1 then 'yes' else '' end IDS_access, " +
							" case china when 1 then 'yes' else '' end CDS_access, " +
							" case india when 1 then 'yes' else '' end INDS_access,  " +
							" case locked  when 0 then '' when 1 then 'yes' when 2 then 'reset' end locked,  " +
							" IDS_TIMESTAMP last_updated  " +
							" from ids_users  " +
							" order by account " ;
							
							logger.warning(sql);
							
							statement = con.prepareStatement(sql);
				      	    resultSet = statement.executeQuery(sql);
				      	        	    
				      	    String id = "";
				      	    String user_name = ""; 
				      	    String account = "";
				      	    String IDS_access  = ""; 
				      	    String CDS_access  = ""; 
				      	    String INDS_access  = ""; 
				      	    String locked  = ""; 
				      	    String last_updated  = "";
				      	    
				      	    
							int k = 2;
				      	    while (resultSet.next()){
				      	    	
				      	    	  
				      	    	
				      	    id = resultSet.getString("id");
				          	user_name = resultSet.getString("user_name");
				          	account = resultSet.getString("account");
				          	IDS_access  = resultSet.getString("IDS_access");
				          	CDS_access  = resultSet.getString("CDS_access");
				          	INDS_access  = resultSet.getString("INDS_access");
				          	locked  = resultSet.getString("locked");
				          //	last_updated  = resultSet.getTimestamp("last_updated").toString();
				         	    
				          	
					        Row  nextRow = worksheet.createRow(k);            	
				          	
					        k+=1;	        
				          	
				  	        cellA = nextRow.createCell((short) 0);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(id);
				  	    	
				  	        cellA = nextRow.createCell((short) 1);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(user_name);

				  	        cellA = nextRow.createCell((short) 2);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(account);

				  	        cellA = nextRow.createCell((short) 3);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(IDS_access);	        
					        
				  	        cellA = nextRow.createCell((short) 4);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(CDS_access);
				  	    	
				  	        cellA = nextRow.createCell((short) 5);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(INDS_access);

				  	        cellA = nextRow.createCell((short) 6);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(locked);

				  	        cellA = nextRow.createCell((short) 7);
				  	    	cellA.setCellType(Cell.CELL_TYPE_STRING);
				  	    	cellA.setCellValue(last_updated);	    	        
					           			
				      	    }
				      	    
				    	    //workbook.write(fileOut);
				    	    //fileOut.flush();
				    	    //fileOut.close();
				    	    //workbook.close();

							// write it as an excel attachment
							ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
							workbook.write(outByteStream);
							byte [] outArray = outByteStream.toByteArray();
							response.setContentType("application/ms-excel");
							response.setContentLength(outArray.length);
							response.setHeader("Expires:", "0"); // eliminates browser caching
							response.setHeader("Content-Disposition", "attachment; filename=users.xlsx");
							OutputStream outStream = response.getOutputStream();
							outStream.write(outArray);
							outStream.flush();
							workbook.close();				      	   

					    	  map.addAttribute("displaytype2","block");
				  	          map.addAttribute("displaytype","none");
							  map.addAttribute("done",k);
						//	  map.addAttribute("fileType","factsPARTDONE");
							    map.addAttribute("successtext","Rows added to Excel file");
					    
							}
					    	catch(Exception e ){
					    		logger.severe("ERROR CREATING users EXCEL" + e.getMessage());
								map.addAttribute("errortext","ERROR CREATING EXCEL "); 
					    	    	map.addAttribute("displaytype2","none");
					    	    	map.addAttribute("displaytype","block");
					    	}
						finally{
							con.close();
						}
							
								
					        return  "setup";			
								 
					 
			  }

	  }
			 
	 
	 

