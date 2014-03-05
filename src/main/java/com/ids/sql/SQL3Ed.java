package com.ids.sql;

public class SQL3Ed {

	private String query="";
	
	public SQL3Ed(int salesOrProduction, int countryId, int productId, int fromYear, int toYear, String incExCountries,
			String incExProducts, String incExCompanies, String dateParm, String access) {
		
	      query = " select a.year, a.quantity,  CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	       " ELSE substr(b.name,1,70) END  as company, d.name as product, c.country from FactsEdit_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.countryId = " + countryId+
	    		  " and a.productId = " +productId+
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		 //  " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.flag != 'X' " +
	    		  " and a.access = '" + access + "' " +
	    		  " and b.access = '" + access + "' " +
	    		  " and c.access = '" + access + "' " +
	    		  " and d.access = '" + access + "' " +
	    		  " and a.countryId = c.id" +
	    		  " order by  CASE WHEN b.name = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	       " ELSE b.name END ,  a.year asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}

