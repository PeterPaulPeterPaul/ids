package com.ids.sql;

import java.util.logging.Logger;

import com.ids.businessLogic.DropdownInterface;


public class SQL1GrpSummary implements DropdownInterface {

	private String query="";
	private String ONE="";
	private String TWO="";
	private int topHeadingLine;
	
 	private final static Logger logger = Logger.getLogger(SQL1GrpSummary.class.getName()); 
	
	public SQL1GrpSummary(int salesOrProduction, int heading1, int heading2,  int dropdown2, int fromYear, int toYear, int summary,
			String incExCountries, String incExProducts, String incExCompanies, String dateParm, String access) {
		
		String andClause = "";
		String selectClause ="";
		String groupAndOrderByClause = "";

	/*	 if (summary == 3 || summary==5){
			 int sumswap =0;
			 sumswap = heading1;
			 heading1 = heading2;
			 heading2 = sumswap;
			 
		 } */
		 
		logger.warning("summary is: "+summary+ " header1: "+heading1+" header2: "+heading2+" drop2: "+dropdown2);

		switch(heading2) {
		case(COUNTRY):{
				 if (summary != 5 && summary !=3){
						if (dropdown2 == -10) {
							 andClause = " AND a.countryId NOT IN (20,21,-10,0) ";
						} else {
					         andClause = " and a.countryId = "+ dropdown2;
						}
				 }
					ONE="company";
					TWO="year";
					topHeadingLine=YEARS;
		        	selectClause = "b.name as company, a.year,  ";
			switch(heading1){
			  case(PRODUCT):{
	            	TWO="year";
	            	topHeadingLine=YEARS;
	            	selectClause = " b.name as company, a.year ,  ";
	            	groupAndOrderByClause = " b.name, a.year ";
	            	    if (summary ==3){
							 andClause = " AND a.productId = " +dropdown2;
						} 
	            	
	            	break;
			  }
			  case(YEARS):{
	            	TWO="product";
	            	topHeadingLine=PRODUCT;
	            	selectClause = " b.name as company, d.shortname as product,  ";
	            	groupAndOrderByClause = " b.name, d.shortname ";
            	    if (summary ==3){
						 andClause = " AND a.year = " +dropdown2;
					} 
	            	break;
			  }
			  case(COMPANY):{
	            	TWO="product";
	            	ONE="year";
	            	topHeadingLine=PRODUCT;
	            	selectClause = " d.shortname as product, a.year,  ";
	            	groupAndOrderByClause = "a.year, d.shortname ";
          	    if (summary ==3){
						 andClause = " AND a.companyId = " +dropdown2;
					} 
	            	break;
			  }
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
			switch(heading1){
			  case(COUNTRY):{
	            	TWO="year";
	            	topHeadingLine=YEARS;
	            	selectClause = " b.name as company, a.year,  ";
	            	groupAndOrderByClause = " b.name, a.year ";
            	    if (summary ==3){
            	    	
						if (dropdown2 == -10) {
							 andClause = " AND a.countryId NOT IN (20,21,-10,0) ";
						} else {
					         andClause = " and a.countryId = "+ dropdown2;
						}
					} 
	            	break;
	            	
			  }
			  case(YEARS):{
	            	TWO="country";
	            	topHeadingLine=COUNTRY;
	            	selectClause = " b.name as company, c.shortname as country,  ";
	            	groupAndOrderByClause = " b.name, c.shortname ";
            	    if (summary ==3){
						 andClause = " AND a.year = " +dropdown2;
					} 
	            	break;
			  }
			  
			  case(COMPANY):{
	            	TWO="country";
	            	ONE="year";
	            	topHeadingLine=COUNTRY;
	            	selectClause = " c.shortname as country, a.year,  ";
	            	groupAndOrderByClause = "a.year,  c.shortname ";
        	    if (summary ==3){
						 andClause = " AND a.companyId = " +dropdown2;
					} 
	            	break;
			  }
			  
			}
			break;
	    }
		case(YEARS):{
			if (summary != 5){
				   andClause = " and a.year = "+ dropdown2;
				}
				ONE="company";
				TWO="product";
				topHeadingLine=PRODUCT;
	        	selectClause = "b.name as company, d.shortname as product,  ";
	        	groupAndOrderByClause = " b.name, d.shortname  ";
			switch(heading1){
			  case(COUNTRY):{
	             	TWO="product";
	             	topHeadingLine=PRODUCT;
	             	selectClause = " b.name as company, d.shortname as product,  ";
	             	groupAndOrderByClause = " b.name, d.shortname ";
            	    if (summary ==3){
						if (dropdown2 == -10) {
							 andClause = " AND a.countryId NOT IN (20,21,-10,0) ";
						} else {
					         andClause = " and a.countryId = "+ dropdown2;
						}
					} 
	             	break;
			  }
			  case(PRODUCT):{
	             	TWO="country";
	             	topHeadingLine=COUNTRY;
	             	selectClause = " b.name as company, c.shortname as country,  ";
	             	groupAndOrderByClause = " b.name, c.shortname ";
            	    if (summary ==3){
						 andClause = " AND a.productId = " +dropdown2;
					} 
	             	break;
			  }
			  case(COMPANY):{
	            	TWO="country";
	            	ONE="product";
	            	topHeadingLine=COUNTRY;
	            	selectClause = " d.name as product , c.shortname country,  ";
	            	groupAndOrderByClause = "d.name,  c.shortname ";
      	    if (summary ==3){
						 andClause = " AND a.companyId = " +dropdown2;
					} 
	            	break;
			  }
			}
			break;
        }
		case(COMPANY):{
			if (summary != 5){
				   andClause = " and a.companyId = "+ dropdown2;
			 }
			 ONE="product";
			 TWO="year";
			  topHeadingLine=YEARS;
		      selectClause = "a.year, d.shortname as product,  ";
		      groupAndOrderByClause = " a.year, d.shortname ";
			switch(heading1){
			  case(COUNTRY):{
	                TWO="country";
	                topHeadingLine=COUNTRY;
	            	ONE="product";
	            	selectClause = " d.name as product, c.shortname as country,  ";
	            	groupAndOrderByClause = " d.name, c.shortname ";
            	    if (summary ==3){
						if (dropdown2 == -10) {
							 andClause = " AND a.countryId NOT IN (20,21,-10,0) ";
						} else {
					         andClause = " and a.countryId = "+ dropdown2;
						}
					} 
	            	break;
	            }
			  case(PRODUCT):{
	            	TWO="product";
	            	topHeadingLine=PRODUCT;
	            	ONE="country";
	            	selectClause = " c.country, d.shortname as product,  ";
	            	groupAndOrderByClause = "c.country, d.shortname  ";
            	    if (summary ==3){
						 andClause = " AND a.productId = " +dropdown2;
					} 
	            	break;
			  }
			  case(YEARS):{
	            	TWO="product";
	            	topHeadingLine=PRODUCT;
	            	ONE="country";
	            	selectClause = " c.country,  d.shortname as product,   ";
	            	groupAndOrderByClause = " c.country, d.shortname ";
            	    if (summary ==3){
						 andClause = " AND a.year = " +dropdown2;
					} 
			  }
			}
		}
    }



		
		
	      query = " select "+selectClause+"  SUM(a.quantity) quantity " +
	      		" from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  andClause +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and a.access = '" + access + "' " +
	    		  " and d.id = a.productId " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  dateParm+
	    		  " and b.name != 'ALL COMPANIES' " +
	    		  " and a.countryId = c.id" +
	    		  " group by "+groupAndOrderByClause+
	    		  " order by "+groupAndOrderByClause+" asc";
	
	      
	  	logger.warning("AT THIS POINT: "+query);
	  	
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
