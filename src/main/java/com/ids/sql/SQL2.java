package com.ids.sql;

public class SQL2 {

	private String query="";
	
	public SQL2(int salesOrProduction, int countryId, int years, int curYear, String incExCountries, String incExProducts,
			String incExCompanies, String dateParm) {
		
	      query = " select a.year, a.quantity, b.name as company, d.shortname as product, c.country from Facts a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.countryId = " + countryId+
	    		  " and a.year = " + years+
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+(curYear - 5)+" and "+(curYear+5)+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  " order by b.name , d.shortname asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
