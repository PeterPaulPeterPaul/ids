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

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.google.cloud.sql.jdbc.PreparedStatement;
import com.ids.businessLogic.DropdownInterface;
import com.ids.context.GetBeansFromContext;


@Controller
@RequestMapping(value="/setup2")
@Transactional
public class SetupController implements DropdownInterface {

	private Connection con;
    static final Logger logger = LoggerFactory.getLogger(SetupController.class);
    
    
    
    @Transactional
	 @RequestMapping( method = RequestMethod.POST)
   public String save( HttpServletRequest request,
                   ModelMap map) throws SQLException, JSONException, FileUploadException, IOException {
    	
		 
        DriverManager.registerDriver(new AppEngineDriver());
        con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","user","password");
       

		 con.setAutoCommit(false);
		 Statement statement1 = con.createStatement();
   
    	

    	ServletFileUpload upload = new ServletFileUpload();

    	// Parse the request
    	logger.error("ABOVE THE STUFF");
    	FileItemIterator iter = upload.getItemIterator(request);
    	logger.error("BELOW THE STUFF");
    	while (iter.hasNext()) {
    		logger.error("WHILE WHILE");
    		   


    		  //    statement1.executeUpdate(query1);
    		
    	    FileItemStream item = iter.next();
    	    String name = item.getFieldName();
    	    InputStream stream = item.openStream();
    	    if (item.isFormField()) {
    	    	logger.error("Form field " + name + " with value "
    	            + Streams.asString(stream) + " detected.");
    	    //	String query1 = item.getName();
    	    	//statement1.executeUpdate(query1);
    	    } else {
    	    	logger.error("File field " + name + " with file name "
    	            + item.getName() + " detected.");
    	    	
    	    	BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
    	    	
    	    	String sql = "INSERT INTO Facts (companyId, countryid, productid, year, sales_production, quantity, flag) " +
    	    			" values (?,?,?,?,?, ?, ?)";
    	    //	String sql = "INSERT INTO Product (Name, Shortname, Id, SortOrder ) values (? , ? , ? ,? ) ";
                PreparedStatement statement = (PreparedStatement) con.prepareStatement(sql);

    	    	
    	    	//String query1 = item.getName();
    	    	String sCurrentLine= null;
    	    	
    	    	while ((sCurrentLine = br.readLine()) != null) {
    	    		String[] parms = sCurrentLine.split("\\|");
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
    	    		statement.executeUpdate();
    	    		
    	    		
    			}

    	    	con.commit();
    	        // Process the input stream

    	    }
    	}
    	
    	
    	




	//   String query1 = "INSERT INTO Country (country,shortname,id,sortorder) VALUES ('SPAIN','SPA',2,14)";


	  //    statement1.executeUpdate(query1);
	      con.close() ; 
	     




return "login";
		 
	 }
	
}

