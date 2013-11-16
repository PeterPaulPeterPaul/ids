package com.ids.sql;

public class SQL4 {

	private String query="";
	
	public SQL4(int salesOrProduction, int countryId, int companyId, int fromYear, int toYear,int swap, String incExCountries,
			 String incExProducts, String incExCompanies, String dateParm, String access) {
		
		String product = "d.shortname";
		String orderby = " order by a.year, "+product+"  asc";
		if (swap==1) {
			product="d.name ";
			orderby = " order by "+product+", a.year  asc";
		}
        String countryClause="";
        String queryPart1 =  "";
        String groupBy="";
		if (countryId == -10) {
			countryClause = " AND a.countryId NOT IN (20,21,-10,0) ";
			queryPart1 =  "select a.year, Sum(a.quantity) as quantity, substr(b.name,1,20) as company, "+product+" as product, 'EUROPE' as country ";
			groupBy = " group by  a.year, b.name, "+product+" , 'EUROPE'  ";
			incExCountries = "";
		} else {
			countryClause = " AND a.countryId = "+countryId;
			queryPart1 =  " select a.year, a.quantity, substr(b.name,1,20) as company, "+product+" as product, c.country ";
		}
		
		
	      query = queryPart1+
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
	    		  groupBy +
	    		  orderby;
		
	}
	
	public String getQuery() {
		return query;
	}
}