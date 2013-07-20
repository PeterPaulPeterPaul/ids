package com.ids.sql;

public class SQL3 {

	private String query="";
	
	public SQL3(int salesOrProduction, int countryId, int productId, int curYear, String incExCountries,
			String incExProducts, String incExCompanies) {
		
	      query = " select a.year, a.quantity, b.name as company, d.name as product, c.country from Facts a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.countryId = " + countryId+
	    		  " and a.productId = " +productId+
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+(curYear - 5)+" and "+(curYear+5)+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  " order by b.name , a.year asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
