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
public class SetupOtherFactColumn {

	private Connection con;
	
	private final static Logger logger = Logger.getLogger(SetupOtherFactColumn.class.getName()); 
	
	private  boolean viaGet = false;
	
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
		 
	  // 	String multiplier="";

	    	

		 Statement statement = con.createStatement();
		 ResultSet resultSet = null;
   

    	//ServletFileUpload upload = new ServletFileUpload();


    	try{
    			    
 			    ///// CREATE THE EXCEL DOWNLOAD....
 			   FileOutputStream fileOut = new FileOutputStream("../webapps/ids/pages/"+IDSversion+".xls");
 			   
 			   String sql = " select sales_production, product.NAME product, country.NAME country, Quantity quantity, year, company.NAME company " +
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
      	      
      	      
  	    	  map.addAttribute("displaytype2","block");
  	          map.addAttribute("displaytype","none");
			  map.addAttribute("done",k);
		//	  map.addAttribute("fileType","factsPARTDONE");
			    map.addAttribute("successtext","EXCEL WITH "+ IDSversion+" ROWS CREATED!");
      	       
    	        } 
    	    	catch(Exception e ){
    	    		logger.warning("ERROR CREATING TEMP TABLE");
    				map.addAttribute("errortext","ERROR CREATING EXCEL "); 
  	    	    	map.addAttribute("displaytype2","none");
  	    	    	map.addAttribute("displaytype","block");
    	    	}
 			  
    	       con.close();
 	    	   return  "setup";
		 
	 }
	 
}
