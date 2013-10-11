package com.ids.sql;

public class SQL5 {

	private String query="";
	
	public SQL5(int salesOrProduction, int productId, int companyId,int fromYear,int toYear,int swap, String incExCountries,
			 String incExProducts, String incExCompanies, String dateParm, String access) {
		
		String country = "c.shortname";
		String orderby = " order by a.year, "+country+"  asc";
		if (swap==1) {
			country="c.country";
			orderby = " order by "+country+", a.year  asc";
		}
	      query = " select a.year, a.quantity, b.name as company, d.name as product, "+country+" as country " +
	      		" from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.productId = " + productId+
	    		   " and a.access = '" + access + "' " +
	              " and a.companyId = " +companyId+
	               " and b.name != 'ALL COMPANIES' " +
	               incExCountries+
	               incExProducts+
	               incExCompanies+
	               dateParm+
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  orderby;
		
	}
	
	public String getQuery() {
		return query;
	}
}
