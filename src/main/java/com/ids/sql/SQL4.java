package com.ids.sql;

public class SQL4 {

	private String query="";
	
	public SQL4(int salesOrProduction, int countryId, int companyId, int fromYear, int toYear,int swap, String incExCountries,
			 String incExProducts, String incExCompanies, String dateParm, String access, boolean countryFilter) {
		
		String product = "d.shortname";
		String orderby = " order by a.year, "+product+"  asc";
		if (swap==1) {
			product="d.name ";
			orderby = " order by "+product+", a.year  asc";
		}
		String countryClause="";
		if (countryFilter){
			countryClause= " and a.countryId = " + countryId;
		}
	      query = " select a.year, a.quantity, b.name as company, "+product+" as product, c.country " +
	      		" from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  countryClause+
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