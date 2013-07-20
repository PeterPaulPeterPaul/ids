package com.ids.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ids.businessLogic.DropdownInterface;
import com.ids.context.GetBeansFromContext;


@Controller
@RequestMapping(value="/setup")
public class SetupOtherFactColumn implements DropdownInterface {

	private Connection con;
    static final Logger logger = LoggerFactory.getLogger(SetupOtherFactColumn.class);
    
	 @RequestMapping( method = RequestMethod.GET)
	public String getMethodOne(
           HttpServletResponse response,
			HttpServletRequest request,
			ModelMap model) throws SQLException, JSONException {
		 
		 
		 GetBeansFromContext gcfc = null;
		 gcfc = new GetBeansFromContext();

		 con = gcfc.myConnection();

		 
	      Statement statement1 = con.createStatement(
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);
	      Statement statement2 = con.createStatement(
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);
	      Statement statement3 = con.createStatement(
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);


	      ResultSet resultSet1 = null;
	      ResultSet resultSet2 = null;

	      
	      String query1 = "DELETE from facts where companyId = -1";
	      statement3.executeUpdate(query1);
	      
	      query1 = "DELETE from company where id= -1";
	      statement3.executeUpdate(query1);
	      
	      query1 = "INSERT INTO Company (name, shortname, id) values ('OTHER','OTH',-1) ";
	      statement3.executeUpdate(query1);
	    //  loop 0: Sales or Prod
	      
	      query1 = "SELECT * from facts  where companyId = 11 ";

	      
	      
	      resultSet1 = statement1.executeQuery(query1);
	      while (resultSet1.next()) {
	    	  

	    	 int totalQuantity = Integer.parseInt(resultSet1.getString("quantity"));
	    	  String totalYear = resultSet1.getString("year");
	    	  String totalProduct = resultSet1.getString("productid");
	    	  String totalCountry = resultSet1.getString("countryid");
	    	  String totalsalesPo = resultSet1.getString("sales_production");
	    	  String totalFlag =  resultSet1.getString("flag");
	    	  
	    	  String query2 = "SELECT sum(quantity) as quantity from facts " +
	    	  		" where year = " +totalYear + 
	    	  		" and productid = "+totalProduct +
	    	  		" and countryId = " + totalCountry +
	    	  		" and sales_production = " + totalsalesPo +
	    	  		" and flag = '"+totalFlag +"' " +
	    	  		" and companyId != 11 and companyId != -1 ";
	    	  
	    	  logger.debug(query2);
	    	  resultSet2 = statement2.executeQuery(query2);

	    	  
	    	  while (resultSet2.next()) {
	    		  
	    		  if (resultSet2.getString("quantity")==null) {
	    			  continue;
	    		  }
	    		  int smallerQuant = Integer.parseInt(resultSet2.getString("quantity"));
	    		  if (smallerQuant > totalQuantity){
	    			 System.out.println("Weird result: "+ totalYear + " " +totalFlag + " " + smallerQuant + " "+ totalQuantity+ 
	    					 " "+totalCountry+ " "+totalsalesPo);
	    		  }else{
	    			  
	    		  if (totalQuantity-smallerQuant>0) {
		    	  String query3 = " INSERT INTO facts" +
		    	  		"(companyId, countryId, productId, year,sales_production,quantity,flag) " +
		    	  		" values (-1, "+totalCountry+","+totalProduct+","+totalYear+
		    			  ","+totalsalesPo+","+(totalQuantity-smallerQuant)+",'"+
		    			  totalFlag+"')";
		    	  
		    	  statement3.executeUpdate(query3);
	    		  }
	    	  }
	    	  }
	      }

	      

    	  
		  
			 
gcfc.closeCon();   
return "login";
		 
	 }
	
}

