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

		switch(heading2){
		case(COUNTRY): {
			if (summary!=4) {
			   andClause = " and a.countryId = "+ dropdown2;
			}
            if (heading1==PRODUCT) {
            	TWO="year";
            	ONE="product";
            	topHeadingLine=YEARS;
            	selectClause = "d.name as product, a.year,  ";
            	groupAndOrderByClause = " d.name, a.year ";
            }else{
            	ONE="year";
            	TWO="product";
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
            	ONE="country";
            	TWO="year";
            	ingoreALL = " and b.name != 'ALL COMPANIES' ";
            	selectClause = " c.country as country, a.year, ";
            	groupAndOrderByClause = " c.country, a.year ";
            }else{
            	topHeadingLine=COUNTRY;
            	ONE="year";
            	TWO="country";
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
            	TWO="product";
            	ONE="country";
            	selectClause = " c.country as country, d.shortname as product,  ";
            	groupAndOrderByClause = "  c.country, d.shortname ";
            }else{
            	topHeadingLine=COUNTRY;
            	ONE="product";
            	TWO="country";
            	selectClause = " d.name as product, c.shortname as country,  ";
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
            	TWO="product";
            	ONE="country";
            	selectClause = " c.country as country, d.shortname as product,  ";
            	groupAndOrderByClause = "  c.country, d.shortname ";
            }else{
            	 if (heading1==PRODUCT) {
            	    topHeadingLine=YEARS;
            	    ONE="product";
            	    TWO="year";
            	    selectClause = " d.name as product, a.year,  ";
            	    groupAndOrderByClause = " d.name, a.year ";
            	 } else {
             	    topHeadingLine=PRODUCT;
             	    ONE="year";
             	    TWO="product";
                	selectClause = "  d.shortname as product, a.year,  ";
                	groupAndOrderByClause = " a.year, d.shortname  ";
            	 }
            }
            break;
		}
			
		}
		
	      query = " select "+selectClause+"  SUM(a.quantity) quantity  " +
	      		" from Facts a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  andClause +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and d.id = a.productId " +
	    		  " and a.access = '" + access + "' " +
	    		  " and b.name != 'ALL COMPANIES' " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		  " and a.countryId = c.id" +
	    		  " group by "+groupAndOrderByClause+
	    		  " order by "+groupAndOrderByClause+" asc";
	
	      
	  	System.out.println(query);
	  	
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
