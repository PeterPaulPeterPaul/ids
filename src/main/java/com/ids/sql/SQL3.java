package com.ids.sql;

public class SQL3 {

	private String query="";
	
	public SQL3(int salesOrProduction, int countryId, int productId, int fromYear, int toYear, String incExCountries,
			String incExProducts, String incExCompanies, String dateParm, String access) {

        String countryClause="";
        String queryPart1 =  "";
        String groupBy="";
        
        
        queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
        
		if (countryId == -10) {
			countryClause = " AND a.countryId NOT IN (20,21,-10,0,100) ";
			queryPart1 =  " select a.year, SUM(a.quantity) as quantity,   substr(b.name,1,70)  as company, d.name as product, 'EUROPE' as country ";
			groupBy = " group by  a.year,  b.name, d.name, 'EUROPE'  ";
			incExCountries = "";
		} else {
			if (access.equals("i")) {
				   countryClause = " AND a.countryId = 100" ;
				}else {
					if (access.equals("c")) {
						countryClause = " AND a.countryId = 21";
					} else {
						countryClause = " AND a.countryId = "+countryId;
					}
				}
			//queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
				
		}

        String ProductClause =  "";
		if ( productId == 15 ||   productId == 80  ) {
			queryPart1 =  " select a.year, sum(a.quantity) as quantity,  substr(b.name,1,70)  as company, 'Wheeled Loaders' as product, c.country ";
			ProductClause=  " and d.shortname in ('WL<80','WL>80')"; 
			if (countryId == -10){
				groupBy = " group by  a.year,  b.name,  'Wheeled Loaders',  'EUROPE'  ";
			} else{
				groupBy = " group by  a.year,  b.name,  'Wheeled Loaders',  c.country ";				
			}

		}
		else {
			//queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
			ProductClause=  " and a.productId = " +productId ;
		}


	               query = queryPart1+
	              " from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  countryClause+
	    		 // " and a.productId = " +productId+
	    		  ProductClause+
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    //		   " and b.name != 'ALL COMPANIES' " +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.access = '" + access + "' " +
	    		  " and b.access = '" + access + "' " +
	    		  " and c.access = '" + access + "' " +
	    		  " and d.access = '" + access + "' " +
	    		  " and a.countryId = c.id" +
	    		  groupBy+
	    		  " order by b.name, a.year asc";
		
	}
	
	public String getQuery() {
		return query;
	}
}
