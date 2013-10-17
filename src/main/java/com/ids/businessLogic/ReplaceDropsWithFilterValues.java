package com.ids.businessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.ui.ModelMap;

import com.ids.controllers.MainController;
import com.ids.entities.Country;
import com.ids.entities.Year;

public class ReplaceDropsWithFilterValues  implements DropdownInterface {

	private Connection con;

   	private final static Logger logger = Logger.getLogger(ReplaceDropsWithFilterValues.class.getName()); 
	private ModelMap model;
	private int countryId;
	private int productId;
	private int companyId;
	private int countryId2;
	private int productId2;
	private int companyId2;
	private int yearParam;
	private int fromYear;
	private int toYear;
	private String countries;
	private String products;
	private String companies;
	private boolean countryFilter = false;
	public ReplaceDropsWithFilterValues ( Connection con, StoreRequestParameters srp, String access, ModelMap model ) 
			 throws SQLException {

		this.model = model;
		ResultSet resultSet = null;
		Statement statement = con.createStatement();
		
		countryFilter=false;
		
		companies = srp.getIncExCompanies();
		logger.warning("companies: "+companies);
		countries = srp.getIncExCountries();
		products = srp.getIncExProducts();
		yearParam = srp.getFromDate();
		fromYear = srp.getFromDate();
		toYear = srp.getToDate();

		if (srp.getHeading1()==COUNTRY) {
  		  countryId2= srp.getDropdown1();
  		  logger.warning("head 1 countryID: "+countryId );
  	  }
  	  if (srp.getHeading2()==COUNTRY) {
  		  countryId2= srp.getDropdown2();
  		  logger.warning("head 2 countryID: "+countryId );
  	  }
		if (srp.getHeading1()==COMPANY) {
	  		  companyId2= srp.getDropdown1();
	  		  logger.warning("head 1 companyID: "+companyId );
	  	  }
	  	  if (srp.getHeading2()==COMPANY) {
	  		  companyId2= srp.getDropdown2();
	  		  logger.warning("head 2 companyID: "+companyId );
	  	  }
  	  if (srp.getHeading1()==PRODUCT) {
  		  productId2= srp.getDropdown1();
  		  logger.warning("head 1 productID: "+productId );
  	  }
  	  if (srp.getHeading2()==PRODUCT) {
  		  productId2= srp.getDropdown2();
  		  logger.warning("head 2 productID: "+productId );
  	  }
  	  if (srp.getHeading1()==YEARS) {
  		yearParam = srp.getDropdown1();
  		  logger.warning("head 1 year: "+yearParam );
  	  }
  	  if (srp.getHeading2()==YEARS) {
  		yearParam = srp.getDropdown2();
  		  logger.warning("head 2 year: "+yearParam );
  	  }
		
		if (countries==null || countries.length()<3) {
			countries="";
		}
		if (products==null || products.length()<3) {
			products="";
		}
		if (companies==null || companies.length()<3) {
			companies="";
		}

			 model.addAttribute("drop11","drop11");
			 model.addAttribute("drop21","drop21");
		
	         String  query = "select c.id as id , c.country as country from Country c where c.id != 0 and c.access = '"+access+"' " +
	         		countries +" order by c.country asc " ;
	      
	         logger.warning(query);

		      resultSet = statement.executeQuery(query);
		      StringBuilder sb = new StringBuilder();
		      boolean first =true;
		      while (resultSet.next()) {
		    	  if (!countryFilter && first) {
		    		  this.countryId = Integer.parseInt(resultSet.getString("id"));  
		    		  first = false;
		    	  }
		    	  sb.append("<option value="+resultSet.getString("id")+">"+resultSet.getString("country")+" </option>");
		          if (Integer.parseInt(resultSet.getString("id"))==countryId2 ){
		        	  countryFilter=true;
		        	  this.countryId = Integer.parseInt(resultSet.getString("id"));
		        	  logger.warning("found ID when should not");
		          }
		      }

		      model.addAttribute("countries",sb.toString());
		      
		      
		      
		      
		      
		      model.addAttribute("drop12","drop12");
				 model.addAttribute("drop22","drop22");
			
		         String  query2 = "select d.id as id , d.name as name from Product d where d.id != 0 and d.access = '"+access+"' " +
		         		products +" order by d.name asc " ;
		      
		         logger.warning(query2);

			      resultSet = statement.executeQuery(query2);
			      StringBuilder sb2 = new StringBuilder();
			      boolean first2 =true;
			      while (resultSet.next()) {
			    	  if ( first2) {
			    		  this.productId = Integer.parseInt(resultSet.getString("id"));  
			    		  first2 = false;
			    	  }
			    	  sb2.append("<option value="+resultSet.getString("id")+">"+resultSet.getString("name")+" </option>");
			          if (Integer.parseInt(resultSet.getString("id"))==productId2 ){
			        	  this.productId = Integer.parseInt(resultSet.getString("id"));
			          }
			      }

			      model.addAttribute("products",sb2.toString());
		      
		      
			      
			      
			      
			         model.addAttribute("drop13","drop13");
					 model.addAttribute("drop23","drop23");
					 
						 if (yearParam < fromYear) {
							 yearParam = fromYear;
						 }
						 if (yearParam > toYear) {
							 yearParam = fromYear;
						 }

					 StringBuilder sb3 = new StringBuilder();
					 for (int i=fromYear;i<=toYear;i++) {
						 sb3.append("<option value="+i+">"+i+" </option>");
			    	  }
				      model.addAttribute("years",sb3.toString());
				      
				      
				      
				      model.addAttribute("drop14","drop14");
						 model.addAttribute("drop24","drop24");
						 
					String query4=	 " select distinct b.id, substr(b.name,1,20) name from Company b " +
		    		  		" where b.id != 0" +
							companies +
		    		  		" and b.access = '" + access + "' " +
		    		  		" order by b.name asc " ;
				      
				         logger.warning(query4);

					      resultSet = statement.executeQuery(query4);
					      StringBuilder sb4 = new StringBuilder();
					      boolean first4 =true;
					      while (resultSet.next()) {
					    	  if (first4) {
					    		  this.companyId = Integer.parseInt(resultSet.getString("id"));  
					    		  first4 = false;
					    	  }
					    	  sb4.append("<option value="+resultSet.getString("id")+">"+resultSet.getString("name")+" </option>");
					          if (Integer.parseInt(resultSet.getString("id"))==companyId2 ){
					        	  this.companyId = Integer.parseInt(resultSet.getString("id"));
					        	  logger.warning("found ID when should not");
					          }
					      }

					      model.addAttribute("companies",sb4.toString());
					      
						 
						 
	}
	
	public ModelMap getModel() {
		return model;
	}
	public boolean getCountryFilter() {
		return countryFilter;
	}
	public int getCountryId() {
		return countryId;
	}
	public int getProductId() {
		return productId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public int getYearParam() {
		return yearParam;
	}
}