package com.ids.sql;

public class SQL3 {

	private String query="";
	
	public SQL3(int salesOrProduction, int countryId, int productId, int fromYear, int toYear, String incExCountries,
			String incExProducts, String incExCompanies, String dateParm, String access, boolean countryFilter) {
		
		String countryClause="";
		if (countryFilter){
			countryClause= " and a.countryId = " + countryId;
		}
	      query = " select a.year, a.quantity, b.name as company, d.name as product, c.country from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  countryClause+
	    		  " and a.productId = " +productId+
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.access = '" + access + "' " +
	    		  " and a.countryId = c.id" +
	    		  " order by b.name , a.year asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
