package com.ids.sql;

import com.ids.businessLogic.DropdownInterface;

public class SQL1Summary implements DropdownInterface {

	private String query="";
	private String ONE="";
	private String TWO="";
	private int topHeadingLine;

	
	public SQL1Summary(int salesOrProduction, int heading1, int heading2,  int dropdown2, int fromYear, int toYear, int summary,
			String incExCountries, String incExProducts, String incExCompanies, String dateParm, String access) {
		
		String andClause = "";
		String selectClause ="";
		String groupAndOrderByClause = "";
		 String ingoreALL = "";
		 String CompClause = "";


		switch(heading2){
		case(COUNTRY): {
			if (summary!=4) {
			
				if (dropdown2 == -10) {
					 andClause = " AND a.countryId NOT IN (20,21,-10,0,100) ";
				} else {
					if (access.equals("i")) {
						andClause = " AND a.countryId = 100" ;
						}else {
							if (access.equals("c")) {
								andClause = " AND a.countryId = 21";
							} else {
								andClause = " AND a.countryId = "+dropdown2;
							}
						}
				}
			}
            if (heading1==PRODUCT) {
            	TWO="Year";
            	ONE="Product";
            	topHeadingLine=YEARS;
            	selectClause = "UPPER(d.name) as product, a.year,  ";
            	groupAndOrderByClause = " d.name, a.year ";
            }else{
            	ONE="Year";
            	TWO="Product";
            	topHeadingLine=PRODUCT;
            	selectClause = " a.year, d.shortname as product,  ";
            	groupAndOrderByClause = " a.year, d.shortname ";
            }
            break;
		}
		case(PRODUCT):{
			if (summary!=4) {
			   andClause = " and a.productId = "+ dropdown2;
			}
            if (heading1==COUNTRY) {
            	topHeadingLine=YEARS;
            	ONE="Country";
            	TWO="Year";
            	ingoreALL = " and b.name != 'ALL COMPANIES' ";
            	selectClause = " c.country as country, a.year, ";
            	groupAndOrderByClause = " c.country, a.year ";
            }else{
            	topHeadingLine=COUNTRY;
            	ONE="Year";
            	TWO="Country";
            	selectClause = " a.year, c.shortname as country,  ";
            	groupAndOrderByClause = " a.year, c.shortname ";
            }
            break;
			
		}
		case(YEARS): {
			if (summary!=4) {
			    andClause = " and a.year = "+ dropdown2;
			}
            if (heading1==COUNTRY) {
            	topHeadingLine=PRODUCT;
            	TWO="Product";
            	ONE="Country";
            	selectClause = " c.country as country, d.shortname as product,  ";
            	groupAndOrderByClause = "  c.country, d.shortname ";
            }else{
            	topHeadingLine=COUNTRY;
            	ONE="Product";
            	TWO="Country";
            	selectClause = " UPPER(d.name) as product, c.shortname as country,  ";
            	groupAndOrderByClause = " d.name, c.shortname ";
            }
            break;
		}
		case(COMPANY): {
			if (summary!=4) {
			    andClause = " and a.companyId = "+ dropdown2;
			}
            if (heading1==COUNTRY) {
            	topHeadingLine=PRODUCT;
            	TWO="Product";
            	ONE="Country";
            	selectClause = " c.country as country, d.shortname as product,  ";
            	groupAndOrderByClause = "  c.country, d.shortname ";
            }else{
            	 if (heading1==PRODUCT) {
            	    topHeadingLine=YEARS;
            	    ONE="Product";
            	    TWO="Year";
            	    selectClause = " UPPER(d.name) as product, a.year,  ";
            	    groupAndOrderByClause = " d.name, a.year ";
            	 } else {
             	    topHeadingLine=PRODUCT;
             	    ONE="Year";
             	    TWO="Product";
                	selectClause = "  d.shortname as product, a.year,  ";
                	groupAndOrderByClause = " a.year, d.shortname  ";
            	 }
            }
            break;
		}
			
		}

		// use all companies unoless the company filter is set.
		//private int xtst = incExProducts.length();
		//incExProducts.
		 if (incExCompanies.isEmpty()) {
			 	CompClause = "and b.name = 'ALL COMPANIES'" ;	 
			 }else{
				 CompClause = "and b.name != 'ALL COMPANIES'" ; 
			 }
		
	      query = " select "+selectClause+"  SUM(a.quantity) quantity  " +
	      		" from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  andClause +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.access = '" + access + "' " +
	    		  " and b.access = '" + access + "' " +
	    		  " and c.access = '" + access + "' " +
	    		  " and d.access = '" + access + "' " +
	    		  CompClause + 
	    		  //" and b.name != 'ALL COMPANIES' " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		  " and a.countryId = c.id" +
	    		  " group by "+groupAndOrderByClause+
	    		  " order by "+groupAndOrderByClause+" asc";
	

	  	
	}
	

	public String getQuery() {
		return query;
	}
	
	public String getONE(){
		return ONE;
	}
	public String getTWO(){
		return TWO;
	}
	
	public int topHeadingLine() {
		return topHeadingLine;
	}
}
