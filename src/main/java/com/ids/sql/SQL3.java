package com.ids.sql;

public class SQL3 {

	private String query="";
	
	public SQL3(int salesOrProduction, int countryId, int productId, int fromYear, int toYear, String incExCountries,
			String incExProducts, String incExCompanies, String dateParm, String access) {

        String countryClause="";
        String queryPart1 =  "";
        String groupBy="";
		if (countryId == -10) {
			countryClause = " AND a.countryId NOT IN (20,21,-10,0) ";
			queryPart1 =  " select a.year, SUM(a.quantity) as quantity,  CASE  WHEN substr(b.name,1,20) = '_OTHER' then '  OTHER' ELSE substr(b.name,1,20) END  as company, d.name as product, 'EUROPE' as country ";
			groupBy = " group by  a.year,  b.name, d.name, 'EUROPE'  ";
			incExCountries = "";
		} else {
			countryClause = " AND a.countryId = "+countryId;
			queryPart1 =  " select a.year, a.quantity,  CASE  WHEN substr(b.name,1,20) = '_OTHER' then '  OTHER' ELSE substr(b.name,1,20) END  as company, d.name as product, c.country ";
		}


	               query = queryPart1+
	              " from Facts_"+access+" a, Company b, Country c, Product d " +
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
	    		  groupBy+
	    		  " order by  CASE  WHEN substr(b.name,1,20) = '_OTHER' then '  OTHER' ELSE substr(b.name,1,20) END , a.year asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
