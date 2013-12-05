package com.ids.sql;

public class SQL1 {

	private String query="";
	
	public SQL1(int salesOrProduction, int productId, int years, int fromYear, int toYear, String incExCountries, String incExProducts
			,String incExCompanies, String dateParm, String access) {
		
	      query = " select a.year, a.quantity, CASE  WHEN substr(b.name,1,20) = '_OTHER' then '  OTHER' ELSE substr(b.name,1,20) END as company, d.name as product, c.shortname as country from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.productId = "+ productId +
	    		  " and a.year = " + years+
	    		  " and a.access = '" + access + "' " +
	    		  " and b.name != 'ALL COMPANIES' " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  " order by  CASE  WHEN substr(b.name,1,20) = '_OTHER' then '  OTHER' ELSE substr(b.name,1,20) END  , c.shortname asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
