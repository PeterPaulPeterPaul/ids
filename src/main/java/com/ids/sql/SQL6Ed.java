package com.ids.sql;

public class SQL6Ed {

	private String query="";
	
	public SQL6Ed(int salesOrProduction, int years, int companyId, int fromYear, int toYear, int swap,String incExCountries,
			String incExProducts, String incExCompanies, String dateParm, String access) {
		
		String country = "c.shortname";
		String product = "d.name";
		String orderby = " order by d.name, "+country+"  asc";
		if (swap==1) {
			country="c.country ";
			 product = "d.shortname";
			orderby = " order by "+country+", d.shortname  asc";
		}
		
		
	      query = " select a.year, a.quantity,   CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	       " ELSE substr(b.name,1,70) END  as company, "+product+" as product, "+country+" as country " +
	      		" from FactsEdit_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.year = " + years+
	    		    " and a.flag != 'X' " +
	    		  " and a.companyId = " +companyId+
	    		  	    		  " and c.shortname != 'EUR' " +
	    		//   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.access = '" + access + "' " +
	    		  " and b.access = '" + access + "' " +
	    		  " and c.access = '" + access + "' " +
	    		  " and d.access = '" + access + "' " +
	    		   incExCountries +
	    		   incExProducts +
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