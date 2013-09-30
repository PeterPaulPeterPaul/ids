package com.ids.businessLogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import com.ids.entities.Company;
import com.ids.entities.Country;
import com.ids.entities.Product;
import com.ids.entities.Year;
import com.ids.json.ColumnModel;
import com.ids.json.TitleArray;
import com.ids.json.YearArray;

public class FirstTimeEdQuery {

	private ModelMap model;
	
    private  HashMap<String,Integer> totalLine2 = null;
    private HashMap<String,Integer> otherLine2 = null;
    static final Logger logger = LoggerFactory.getLogger(FirstTimeEdQuery.class);
    
	public FirstTimeEdQuery(ModelMap model,Connection con,HttpServletRequest request, int curYear, String access){

		try {
			
	      Statement statement = con.createStatement();

	      String query=null;
	      ResultSet resultSet = null;
	      
		
		  TitleArray titleArray = new TitleArray("Austria","Agricultural Tractor", "sales" );
		  
		  
		  query="SELECT YEAR(DATE_ADD( CURDATE(), INTERVAL -5 YEAR)) as year1 ";

		    	 
		  List<Year> years = new ArrayList<Year>();	
	      resultSet = statement.executeQuery(query);
		   while (resultSet.next()) {
		    	  Year year = new Year(resultSet.getString("year1"),resultSet.getString("year1"));
		    	  years.add(year);
		    	  for (int i=1;i<=10;i++) {
			    	  Year nextYear = new Year(
			    			  Integer.toString(Integer.parseInt(year.getId())+i),
			    			  Integer.toString(Integer.parseInt(year.getId())+i));
			    	  years.add(nextYear);  
		    	  }
		    	  
		   }
		  

    	  YearArray yearArray = new YearArray("Company",years);
    	  
    	  ColumnModel columnModel = new ColumnModel(yearArray.getJsonYearArray());


    	  
	      String query2 = " select a.year, a.quantity, b.name from FactsEdit a, Company b, Country c " +
	    		  " where a.companyid=b.id " +
	    		  " and a.countryid=c.id " + 
	    		  " and c.id=7 " + 
	    		  " and a.year between "+(curYear - 5)+" and "+(curYear+5)+" " +
	    		  " and a.sales_production= 1" + 
	    		  " and a.productid=1 " + 
	    		  " and a.access = '" + access + "' " +
	    		  " and b.name!='ALL COMPANIES' " +
	    		  " order by b.name , a.year asc";

    	  
    	  resultSet = statement.executeQuery(query2);
    	  String currentCompany="";
        JSONObject obj2a = null;
    	  JSONArray array7 = new JSONArray();
    	  
    	  totalLine2 =  new HashMap<String,Integer>();
    	  otherLine2 =  new HashMap<String,Integer>();
    	  
    	  while (resultSet.next()) {
    		  

    	    		int totalQuantity=0;
    	    		if (totalLine2.get(resultSet.getString("year"))!= null) {
    	    			totalQuantity= totalLine2.get(resultSet.getString("year"));
    	    		}
    	    		totalQuantity += Integer.parseInt(resultSet.getString("quantity"));
    	    		totalLine2.put(resultSet.getString("year"), totalQuantity);


    			int otherQuantity=0;
    			if (otherLine2.get(resultSet.getString("year"))!= null) {
    				otherQuantity= otherLine2.get(resultSet.getString("year"));
    			}
    			otherQuantity += Integer.parseInt(resultSet.getString("quantity"));
    			otherLine2.put(resultSet.getString("year"), otherQuantity);
    			
    		if (!currentCompany.equals(resultSet.getString("name")))  {
    			if (!currentCompany.equals("")){
    				array7.put(obj2a);
    			}
    			obj2a = new JSONObject();
    			currentCompany=resultSet.getString("name");
    			obj2a.put("Company",currentCompany);
    			obj2a.put(resultSet.getString("year"),resultSet.getString("quantity"));
    		} else {
    			obj2a.put(resultSet.getString("year"),resultSet.getString("quantity"));
    		}
    	  }

    	  
    	  if (obj2a != null) {
    	     array7.put(obj2a);
    	  }   
    	  

    	  
	      JSONObject objTotal = new JSONObject();
    	  objTotal.put("Company","TOTAL");
    	  Iterator<Entry<String, Integer>> it = totalLine2.entrySet().iterator();
    	   while (it.hasNext()) {
    	        Entry<String, Integer> pairs = it.next();
    	        objTotal.put(pairs.getKey(), pairs.getValue());
    	    }
    	   JSONArray array8 = new JSONArray(); 
    	   
	    	  if (objTotal != null) {
		    	     array8.put(objTotal);
		    	     JSONObject obj8 = new JSONObject();
		       	     obj8.put("myTotals", array8);
		    	     model.addAttribute("jsonTotal",obj8);
		    	  }

    	  
    	  JSONObject obj7 = new JSONObject();
    	  obj7.put("myData", array7);
    	  
    	  
    	  
    	  JSONArray array4 = new JSONArray();
    	  array4.put(columnModel.getModelObject());
    	  array4.put(yearArray.getJsonYearObject());
    	  array4.put(obj7);
    	  array4.put(titleArray.getJsonTitleObject());
    	  
    	  JSONObject obj5 = new JSONObject();
    	  obj5.put("tabData", array4);

    	  model.addAttribute("jsonData",obj5);
    	  if (request.getParameter("list") == null){
    		  
    		  
 		

			    	  
				      query = "select id, country from Country where id != 0 and access = '"+access+"' order by country asc " ;
				      
				         List<Country> countries = new ArrayList<Country>();

				         
				         
					      resultSet = statement.executeQuery(query);
					      while (resultSet.next()) {
					    	  Country country = new Country(resultSet.getString("id"),resultSet.getString("country"));
					    	  countries.add(country);
					      }

						 
						 
					      model.addAttribute("dropdown1a",countries);
					      model.addAttribute("dropdown2a",countries);

					      query = "select id, name from Product where id != 0 and access = '"+access+"' order by name asc " ;
					      
					         List<Product> products = new ArrayList<Product>();

						      resultSet = statement.executeQuery(query);
						      while (resultSet.next()) {
						    	  Product product = new Product(resultSet.getString("id"),resultSet.getString("name"));
						    	  products.add(product);
						      }
						      
						      model.addAttribute("dropdown1b",products);
						      model.addAttribute("dropdown2b",products);
						      
							
						      model.addAttribute("dropdown1c",years);
						      model.addAttribute("dropdown2c",years);


			    		  
			    		  query = " select distinct a.id, a.name from Company a  , Facts b " +
			    		  		" where a.id != 0" +
			    		  		" and b.companyid = a.id " +
			    		  		" and b.year between "+(curYear - 5)+" and "+(curYear+5)+
			    		  		" order by a.name asc " ;
					    
			    		  logger.debug(query);
			    		  	
					         List<Company> companies = new ArrayList<Company>();
					          
						      resultSet = statement.executeQuery(query);
						      while (resultSet.next()) {
						    	  Company company = new Company(resultSet.getString("id"),resultSet.getString("name"));
						    	  companies.add(company);
						      }

						      model.addAttribute("dropdown1d",companies);
						      model.addAttribute("dropdown2d",companies);


    		  
    		  
    		  model.addAttribute("firstTimeFromServer",obj5);  
    		  
    			this.model = model;
    			
    			

    	  }
    	  }catch(Exception e) {
    		  e.printStackTrace();
    	  }
	
	}
	
	public 	ModelMap  getModel() {
		return this.model;
	}
	
	
}
