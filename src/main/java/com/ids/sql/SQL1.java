package com.ids.sql;

public class SQL1 {

	private String query="";
	
	public SQL1(int salesOrProduction, int productId, int years, int curYear, String incExCountries, String incExProducts
			,String incExCompanies) {
		
	      query = " select a.year, a.quantity, b.name as company, d.name as product, c.shortname as country from Facts a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.productId = "+ productId +
	    		  " and a.year = " + years+
	    		  " and b.name != 'ALL COMPANIES' " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  " and a.year between "+(curYear - 5)+" and "+(curYear+5)+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  " order by b.name , c.shortname asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
