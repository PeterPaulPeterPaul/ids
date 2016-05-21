package com.ids.sql;

public class SQL5 {

	private String query="";
	
	public SQL5(int salesOrProduction, int productId, int companyId,int fromYear,int toYear,int swap, String incExCountries,
			 String incExProducts, String incExCompanies, String dateParm, String access) {
		
		String country = "c.shortname";
		String orderby = " order by a.year, "+country+"  asc";
		if (swap==1) {
			country="c.country";
			orderby = " order by "+country+", a.year  asc";
		}
	
	    String queryPart1 = " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, "+country+" as country ";
        String ProductClause =  "";
        
		if ( productId == 15 ||   productId == 80  ) {
			queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, 'Wheeled Loaders' as product,  "+country+" as country ";
			ProductClause=  " and d.shortname in ('WL<80','WL>80') "; 
		}
		else {
			//queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
			
			ProductClause=  " and a.productId = " +productId ;
		}
		
	      query = queryPart1+
	    		 // " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, "+country+" as country "+
	      		" from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  " and a.productId = " + productId+
	    		  " and a.companyid = " + companyId+
	    		  " and a.access = '" + access + "' " +
	    		  " and b.access = '" + access + "' " +
	    		  " and c.access = '" + access + "' " +
	    		  " and d.access = '" + access + "' " +
	    		  ProductClause+
	    		  //" and a.companyId = " +companyId+
	          //     " and b.name != 'ALL COMPANIES' " +
	               incExCountries+
	               incExProducts+
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
