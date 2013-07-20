package com.ids.sql;

import com.ids.businessLogic.DropdownInterface;

public class SQL1GrpSummary implements DropdownInterface {

	private String query="";
	private String ONE="";
	private String TWO="";
	private int topHeadingLine;
	
	public SQL1GrpSummary(int salesOrProduction, int heading1, int heading2,  int dropdown2, int curYear, int summary,
			String incExCountries, String incExProducts, String incExCompanies, String dateParm) {
		
		String andClause = "";
		String selectClause ="";
		String groupAndOrderByClause = "";

		 if (summary == 3 || summary==5){
			 int sumswap =0;
			 sumswap = heading1;
			 heading1 = heading2;
			 heading2 = sumswap;
			 
		 }
		 
		switch(heading2){
		case(COUNTRY): {
			 if (summary != 5){
			   andClause = " and a.countryId = "+ dropdown2;
			 }
			ONE="company";
			TWO="year";
			topHeadingLine=YEARS;
        	selectClause = "b.name as company, a.year,  ";
        	groupAndOrderByClause = " b.name, a.year ";
            if (heading1==YEARS) {
            	TWO="product";
            	topHeadingLine=PRODUCT;
            	selectClause = " b.name as company, d.shortname as product,  ";
            	groupAndOrderByClause = " b.name, d.shortname ";
            }
            break;
		}
		case(PRODUCT):{
			if (summary != 5){
			   andClause = " and a.productId = "+ dropdown2;
			}
			ONE="company";
			TWO="year";
			topHeadingLine=YEARS;
        	selectClause = "b.name as company, a.year,  ";
        	groupAndOrderByClause = "b.name,  a.year ";
        	
            if (heading1==YEARS) {
            	TWO="country";
            	topHeadingLine=COUNTRY;
            	selectClause = " b.name as company, c.shortname as country,  ";
            	groupAndOrderByClause = " b.name, c.shortname ";
            }
            break;
			
		}
		case(YEARS): {
			if (summary != 5){
			   andClause = " and a.year = "+ dropdown2;
			}
			ONE="company";
			TWO="product";
			topHeadingLine=PRODUCT;
        	selectClause = "b.name as company, d.shortname as product,  ";
        	groupAndOrderByClause = " b.name, d.shortname  ";
        	 if (heading1==PRODUCT) {
             	TWO="country";
             	topHeadingLine=COUNTRY;
             	selectClause = " b.name as company, c.shortname as country,  ";
             	groupAndOrderByClause = " b.name, c.shortname ";
             }
            break;
		}
		case(COMPANY):{
			if (summary != 5){
			   andClause = " and a.companyId = "+ dropdown2;
			}
			ONE="year";
			TWO="product";
			topHeadingLine=PRODUCT;
        	selectClause = "a.year, d.shortname as product,  ";
        	groupAndOrderByClause = " a.year, d.shortname ";
            if (heading1==PRODUCT) {
            	TWO="country";
            	topHeadingLine=COUNTRY;
            	selectClause = " a.year, c.shortname as country,  ";
            	groupAndOrderByClause = "a.year, c.shortname  ";
            }
            if (heading1==YEARS) {
            	TWO="country";
            	topHeadingLine=COUNTRY;
            	ONE="product";
            	selectClause = " d.name as product, c.shortname as country,  ";
            	groupAndOrderByClause = " d.name, c.shortname ";
            }
            break;
		}
			
		}
		
	      query = " select "+selectClause+"  SUM(a.quantity) quantity " +
	      		" from Facts a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  andClause +
	    		  " and a.year between "+(curYear - 5)+" and "+(curYear+5)+" " +
	    		  " and d.id = a.productId " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		  " and b.name != 'ALL COMPANIES' " +
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
