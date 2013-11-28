package com.ids.controllers;

import java.io.IOException;
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
import com.google.cloud.sql.jdbc.PreparedStatement;
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
			ModelMap model)  {
    	
    	ServletOutputStream out = null;
    	
		 try{
			 GetBeansFromContext gcfc = new GetBeansFromContext();
			 con = gcfc.myConnection();

	      Statement statement3 = con.createStatement();
;

			 String access = request.getParameter("access");
			  logger.warning("got request") ;
		    	String multiplier="";
		    	if (access.equals("c")) {
		    		multiplier="*10000";
		    	}
		    	if (access.equals("i")) {
		    		multiplier="*20000";
		    	}
	      
		    	logger.warning("above delete") ;
	      String query1 = "DELETE from Facts_"+access+" where companyId < 0";
	      logger.warning("query: "+query1);
	      statement3.executeUpdate(query1);

	    	 String query2 = "INSERT INTO Facts_"+access+"  (companyId, countryid, productid, year, sales_production, quantity, flag, access) " +
	    	 		" select -1"+multiplier+", a.countryId"+multiplier+",a.productid"+multiplier+",a.year,a.sales_production, " +
	    			 "  (b.quantity -a.quantity) as quantity ,a.flag, '"+access+"' " +
		" from  Facts_"+access+" b ,"+
" ( " +
" 		select productId, countryId, sales_production, " +
" 	      		flag, year, sum(quantity) quantity from Facts_"+access+" where companyID != 11"+multiplier+" " +
" 	      		and quantity > 0 " + 
" 	      				group by productId, countryId, year, sales_production, " +
" 	      		 flag, year " +
" 		)  a " +
"where a.productid = b.productid "+
 "and a.countryId = b.countryId "+
" and a.sales_production = b.sales_production "+
"and a.flag = b.flag "+
"and a.year = b.year "+
"and b.companyid = 11"+multiplier+" "+
" and b.quantity - a.quantity > 0 ";
	    	  
	    	  logger.warning(query2);

	    	    PreparedStatement statement = (PreparedStatement) con.prepareStatement(query2);
	    	    statement.executeUpdate();
	    	    
	    	 
con.close();   
return "setup";
		 
	// }
	 }catch(Exception e) {
		 try {
			 logger.warning(e.getMessage());
			 e.printStackTrace();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.warning("SQL ERROR OF SOME KIND");
			e1.printStackTrace();
		}
		 return "setup";
	 }
	 }
}


