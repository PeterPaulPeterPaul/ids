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

	      Statement statement2 = con.createStatement();
	      Statement statement3 = con.createStatement();


	      ResultSet resultSet2 = null;
	      
	      logger.warning("above response");

	      response.setContentType("application/octet-stream");
	      logger.warning("above response 1") ;
	      response.setHeader("Content-Disposition","attachment;filename=otherFacts.txt");
	      logger.warning("above response 2") ;
			out = response.getOutputStream();
			  logger.warning("created stream") ;

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
	      query1 = "DELETE from Others_"+access;
	      logger.warning("query: "+query1);
	      statement3.executeUpdate(query1);
	      
	      statement2.executeUpdate("insert into Others_"+access+"  select productId, countryId, sales_production," +
	      		" flag, year, sum(quantity) quantity from Facts_"+access+ " where companyID != 11"+multiplier+
	      				" group by productId, countryId, year, sales_production," +
	      		" flag, year");
	      
	      logger.warning("below delete");
	//      query1 = "DELETE from company where id= -1";
	  //    statement3.executeUpdate(query1);
	      
	 //     query1 = "INSERT INTO Company (name, shortname, id) values ('OTHER','OTH',-1) ";
	  //    statement3.executeUpdate(query1);
	    //  loop 0: Sales or Prod
	      


	    	  
	    	  String query2 = "select  a.productid, a.countryId, a.sales_production, "+
"a.flag , a.year, -1"+multiplier+" as companyI ,  (b.quantity -a.quantity) as quantity  from Others_"+access+" a, Facts_"+access+" b "+
"where a.productid = b.productid "+
 "and a.countryId = b.countryId "+
" and a.sales_production = b.sales_production "+
"and a.flag = b.flag "+
"and a.year = b.year "+
"and b.companyid = 11"+multiplier+" "+
" and b.quantity - a.quantity > 0 ";
	    	  
	    	  logger.warning(query2);
	    	  resultSet2 = statement2.executeQuery(query2);

	    	  int flusher=0;
	    	  while (resultSet2.next()) {
	    		  
	    		  if (resultSet2.getString("quantity")==null) {
	    			  continue;
	    		  }
  
	    			  
	    				out.print(resultSet2.getString("companyI")+"\t"+ resultSet2.getString("countryId")+"\t" + resultSet2.getString("productid")
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
		 try {
			 logger.warning(e.getMessage());
			 e.printStackTrace();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 return "setup";
	 }
	 }
}


