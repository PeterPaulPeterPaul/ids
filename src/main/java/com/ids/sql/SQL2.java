package com.ids.sql;

public class SQL2 {

	private String query="";
	
	public SQL2(int salesOrProduction, int countryId, int years, int fromYear, int toYear, String incExCountries, String incExProducts,
			String incExCompanies, String dateParm, String access) {
		

	      query = " select a.year, a.quantity, b.name as company, d.shortname as product, c.country from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.year = " + years+
	    		   " and a.countryId = " + countryId+
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and a.access = '" + access + "' " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  " order by b.name , d.shortname asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
