package com.ids.sql;

public class SQL6 {

	private String query="";
	
	public SQL6(int salesOrProduction, int years, int companyId, int curYear,int swap,String incExCountries,
			String incExProducts, String incExCompanies, String dateParm) {
		
		String country = "c.shortname";
		String product = "d.name";
		String orderby = " order by d.name, "+country+"  asc";
		if (swap==1) {
			country="c.country ";
			 product = "d.shortname";
			orderby = " order by "+country+", d.shortname  asc";
		}
		
		
	      query = " select a.year, a.quantity, b.name as company, "+product+" as product, "+country+" as country " +
	      		" from Facts a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.year = " + years+
	    		  " and a.companyId = " +companyId+
	    		   " and b.name != 'ALL COMPANIES' " +
	    		   incExCountries +
	    		   incExProducts +
	    		   incExCompanies+
	    		   dateParm+
	    		  " and a.year between "+(curYear - 5)+" and "+(curYear+5)+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  orderby;
		
	}
	
	public String getQuery() {
		return query;
	}
}