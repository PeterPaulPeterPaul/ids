package com.ids.sql;

public class SQL4Ed {

	private String query="";
	
	public SQL4Ed(int salesOrProduction, int countryId, int companyId, int fromYear, int toYear,int swap, String incExCountries,
			 String incExProducts, String incExCompanies, String dateParm, String access) {
		
		String product = "d.shortname";
		String orderby = " order by a.year, "+product+"  asc";
		if (swap==1) {
			product="d.name ";
			orderby = " order by "+product+", a.year  asc";
		}
	      query = " select a.year, a.quantity, b.name as company, "+product+" as product, c.country " +
	      		" from FactsEdit a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.countryId = " + countryId+
	    		  " and a.companyId = " +companyId+
	    		   " and a.access = '" + access + "' " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  orderby;
		
	}
	
	public String getQuery() {
		return query;
	}
}