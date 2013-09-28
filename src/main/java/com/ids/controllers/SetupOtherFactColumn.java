package com.ids.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.appengine.api.rdbms.AppEngineDriver;
import com.ids.businessLogic.DropdownInterface;
import com.ids.context.GetBeansFromContext;


@Controller
@Transactional
public class SetupOtherFactColumn {

	private Connection con;
	private final static Logger logger = Logger.getLogger(SetupOtherFactColumn.class.getName()); 
    
    @Transactional
	 @RequestMapping(value="/setup3", method = RequestMethod.POST)
	public String getMethodOne(
           HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws SQLException, JSONException {
		 try{
logger.warning("HHHHHHHHHHHHHHHHHHHH");
		   DriverManager.registerDriver(new AppEngineDriver());
		  con = DriverManager.getConnection("jdbc:google:rdbms://hypothetical-motion4:hypothetical-motion/mydb","123smiggles321","Wednesday");


		 
	      Statement statement1 = con.createStatement();
	      Statement statement2 = con.createStatement();
	      Statement statement3 = con.createStatement();


	      ResultSet resultSet1 = null;
	      ResultSet resultSet2 = null;

	      response.setContentType("application/octet-stream");
	      response.setHeader("Content-Disposition","attachment;filename=temp.txt");
		  
			ServletOutputStream out = response.getOutputStream();
			


	      
	      String query1 = "DELETE from Facts where companyId = -1";
	      statement3.executeUpdate(query1);
	      
	//      query1 = "DELETE from company where id= -1";
	  //    statement3.executeUpdate(query1);
	      
	 //     query1 = "INSERT INTO Company (name, shortname, id) values ('OTHER','OTH',-1) ";
	  //    statement3.executeUpdate(query1);
	    //  loop 0: Sales or Prod
	      


	    	  
	    	  String query2 = "select  a.productid, a.countryId, a.sales_production, "+
"a.flag , a.year, a.companyId ,  (b.quantity -a.quantity) as quantity  from Others a, Facts b "+
"where a.productid = b.productid "+
 "and a.countryId = b.countryId "+
" and a.sales_production = b.sales_production "+
"and a.flag = b.flag "+
"and a.year = b.year "+
"and b.companyid = 11 "+
" and b.quantity - a.quantity > 0 ";
	    	  
	    	//  logger.warning(query2);
	    	  resultSet2 = statement2.executeQuery(query2);

	    	  int flusher=0;
	    	  while (resultSet2.next()) {
	    		  
	    		  if (resultSet2.getString("quantity")==null) {
	    			  continue;
	    		  }
  
	    			  
	    				out.print("-1\t"+ resultSet2.getString("countryId")+"\t" + resultSet2.getString("productid")
	    						+"\t"  + resultSet2.getString("year")+"\t" +
	    						resultSet2.getString("sales_production")+ "\t"
	    						+ resultSet2.getString("quantity")+"\t" + resultSet2.getString("flag")+"\r\n"); 
	    				flusher+=1;
	    				if (flusher> 200) {
	    				out.flush();
	    				flusher=0;
	    			//	break;
	    				}
	    				

	    		  }



	      
			out.flush();

con.close();   
return "setup";
		 
	// }
	 }catch(Exception e) {
		 logger.warning(e.getMessage());
		 e.printStackTrace();
		 return "setup";
	 }
	 }
}


