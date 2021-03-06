package com.ids.sql;

public class SQL1Ed {

	private String query="";
	
	public SQL1Ed(int salesOrProduction, int productId, int years, int fromYear, int toYear, String incExCountries, String incExProducts
			,String incExCompanies, String dateParm, String access) {
		
	      query = " select a.year, a.quantity,  CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	       " ELSE substr(b.name,1,70) END  as company, d.name as product, c.shortname as country from FactsEdit_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.productId = "+ productId +
	    		  " and a.year = " + years+
	    		  " and a.access = '" + access + "' " +
	    		  " and b.access = '" + access + "' " +
	    		  " and c.access = '" + access + "' " +
	    		  " and d.access = '" + access + "' " +
	    		  " and a.flag != 'X' " +
	    		  " and c.shortname != 'EUR' " +
	    	//	  " and b.name != 'ALL COMPANIES' " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  " order by  CASE WHEN b.name = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	       " ELSE b.name END ,  c.shortname asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
