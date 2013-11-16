package com.ids.sql;

public class SQL2Ed {

	private String query="";
	
	public SQL2Ed(int salesOrProduction, int countryId, int years, int fromYear, int toYear, String incExCountries, String incExProducts,
			String incExCompanies, String dateParm, String access) {
		
		
		
        String countryClause="";
        String queryPart1 =  "";
        String groupBy="";
		if (countryId == -10) {
			countryClause = " AND a.countryId NOT IN (20,21,-10,0) ";
			queryPart1 =  " select a.year, sum(a.quantity) as quantity, substr(b.name,1,20) as company, d.shortname as product, 'EUROPE' as country ";
			groupBy = " group by  a.year,  b.name , d.shortname, 'EUROPE'  ";
			incExCountries = "";
		} else {
			countryClause = " AND a.countryId = "+countryId;
			queryPart1 =  " select a.year, a.quantity, substr(b.name,1,20) as company, d.shortname as product, c.country ";
		}

		
	      query = queryPart1+
	    		  " from FactsEdit_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.year = " + years+
	    		   countryClause+
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and a.access = '" + access + "' " +
	    		  " and d.id = a.productId " +
	    		  " and a.countryId = c.id" +
	    		  groupBy+
	    		  " order by b.name , d.shortname asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
