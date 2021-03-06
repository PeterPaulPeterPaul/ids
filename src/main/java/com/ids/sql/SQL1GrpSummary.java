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
			String incExCountries, String incExProducts, String incExCompanies, String dateParm, String access, int swap) {
		
		String andClause = "";
		String selectClause ="";
		String groupAndOrderByClause = "";
		String allCompClause = "";
		boolean isAllComps = false;


		//CXM - if Companies is not in the group by clause, then filter out ALL COMPANIES
		//incExProducts.
		 if (incExCompanies.isEmpty()) {
			 	allCompClause = "and b.name = 'ALL COMPANIES' " ;	 
			 }else{
				 allCompClause = " and b.name != 'ALL COMPANIES' " ; 
			 }
		
		 
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
					ONE="Company";
					TWO="Year";
					topHeadingLine=YEARS;
		        	selectClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END as company,  a.year,  ";
			switch(heading1){
			  case(PRODUCT):{
	            	TWO="Year";
	            	topHeadingLine=YEARS;
	            	selectClause = "  CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END as company,  a.year ,  ";
	            	groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END, a.year ";

	            	    if (summary ==3){
	        				if ( dropdown2 == 15 ||   dropdown2 == 80  ) {
	        					andClause=  " and d.shortname in ('WL<80','WL>80') "; 
	        				}
	        				else {
	        					//queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
	        					andClause = " and a.productId = "+ dropdown2;
	        				}
							 //andClause = " AND a.productId = " +dropdown2;
						} 
	            	
	            	break;
			  }
			  case(YEARS):{
	            	TWO="Product";
	            	topHeadingLine=PRODUCT;
	            	selectClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END as company,  d.shortname as product,  ";
	            	groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	          			  "   ELSE substr(b.name,1,30) END, d.shortname ";
            	    if (summary ==3){
						 andClause = " AND a.year = " +dropdown2;
					} 
	            	break;
			  }
			  case(COMPANY):{
	            	TWO="Product";
	            	ONE="Year";
	            	topHeadingLine=PRODUCT;
	            	if (swap!=1) {
	            	  selectClause = " d.shortname as product, a.year,  ";
	            	  groupAndOrderByClause = "a.year, d.shortname ";
	            	}else {
		              selectClause = " d.name as product, a.year,  ";
	  				  groupAndOrderByClause = "d.name, a.year";	            		
	            	}
	            	//CXM
				     isAllComps = true;
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
				if ( dropdown2 == 15 ||   dropdown2 == 80  ) {
					andClause=  " and d.shortname in ('WL<80','WL>80') "; 
				}
				else {
					//queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
					andClause = " and a.productId = "+ dropdown2;
				}
				}
				ONE="Company";
				TWO="Year";
				topHeadingLine=YEARS;
	        	selectClause = "b.name as company, a.year,  ";
	        	groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END,  a.year ";
			switch(heading1){
			  case(COUNTRY):{
	            	TWO="Year";
	            	topHeadingLine=YEARS;
	            	selectClause = "  CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END as company,  a.year,  ";
	            	groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	          			  "   ELSE substr(b.name,1,30) END, a.year ";
            	    if (summary ==3){
            	    	
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
	            	break;
	            	
			  }
			  case(YEARS):{
	            	TWO="Country";
	            	topHeadingLine=COUNTRY;
	            	selectClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END as company,  c.shortname as country,  ";
	            	groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	          			  "   ELSE substr(b.name,1,30) END, c.shortname ";
            	    if (summary ==3){
						 andClause = " AND a.year = " +dropdown2;
					} 
	            	break;
			  }
			  
			  case(COMPANY):{
	            	TWO="Country";
	            	ONE="Year";
	            	topHeadingLine=COUNTRY;
	            	if (swap!=1) {
	            	   selectClause = " c.shortname as country, a.year,  ";
	            	   groupAndOrderByClause = "a.year,  c.shortname ";
	            	} else {
		               selectClause = " c.country, a.year,  ";
		               groupAndOrderByClause = "c.country, a.year ";	            		
	            	}
	            	//CXM
				     isAllComps = true;
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
				ONE="Company";
				TWO="Product";
				topHeadingLine=PRODUCT;
	        	selectClause = "b.name as company, d.shortname as product,  ";
	        	groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	      			  "   ELSE substr(b.name,1,30) END, d.shortname  ";
			switch(heading1){
			  case(COUNTRY):{
	             	TWO="product";
	             	topHeadingLine=PRODUCT;
	             	selectClause = "  CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END as company,  d.shortname as product,  ";
	             	groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	          			  "   ELSE substr(b.name,1,30) END, d.shortname ";
            	    if (summary ==3){
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
	             	break;
			  }
			  case(PRODUCT):{
	             	TWO="Country";
	             	topHeadingLine=COUNTRY;
	             	  selectClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
    			  "   ELSE substr(b.name,1,30) END as company, c.shortname as country,  ";
	             	   groupAndOrderByClause = " CASE WHEN substr(b.name,1,70) = 'ALL COMPANIES' then  ' ALL COMPANIES' "+
	             			  "   ELSE substr(b.name,1,30) END, c.shortname ";
            	    if (summary ==3){
        				if ( dropdown2 == 15 ||   dropdown2 == 80  ) {
        					andClause=  " and d.shortname in ('WL<80','WL>80') "; 
        				}
        				else {
        					//queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
        					andClause = " and a.productId = "+ dropdown2;
        				}
						 //andClause = " AND a.productId = " +dropdown2;
					} 
	             	break;
			  }
			  case(COMPANY):{
	            	TWO="Country";
	            	ONE="Product";
	            	topHeadingLine=COUNTRY;
	            	if (swap!=1){
	            	  selectClause = " d.name as product , c.shortname country,  ";
	            	  groupAndOrderByClause = "d.name,  c.shortname ";
	            	} else {
		              selectClause = " d.shortname as product , c.country,  ";
		              groupAndOrderByClause = "c.country, d.shortname ";	            		
	            	}
	            	//CXM
				     isAllComps = true;
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
			 ONE="Product";
			 TWO="Year";
			  topHeadingLine=YEARS;
			  if (swap!=1) {
		         selectClause = "a.year, d.shortname as product,  ";
		         groupAndOrderByClause = " a.year, d.shortname ";
			  }else {
			     selectClause = "a.year, d.name as product,  ";
			     groupAndOrderByClause = " d.name, a.year";			
			  }
			  //CXM
			  isAllComps = true;
			  
			switch(heading1){
			  case(COUNTRY):{
	                TWO="Product";
	                topHeadingLine=PRODUCT;
	            	ONE="Year";
	            	if (swap!=1) {
	            	  selectClause = " d.shortname as product, a.year,  ";
	            	  groupAndOrderByClause = " a.year, d.shortname ";
	            	}else {
		              selectClause = " d.name as product, a.year,  ";
		              groupAndOrderByClause = " d.name , a.year ";	            		
	            	}
	            	//CXM
				     isAllComps = true;
            	    if (summary ==3){
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
	            	break;
	            }
			  case(PRODUCT):{
	            	TWO="Country";
	            	topHeadingLine=COUNTRY;
	            	ONE="Year";
	            	if (swap!=1) {
	            	   selectClause = " c.shortname as country, a.year,  ";
	            	   groupAndOrderByClause = "a.year, c.shortname  ";
	            	}else {
		               selectClause = " c.country, a.year,  ";
		               groupAndOrderByClause = "c.country, a.year  ";	            		
	            	}
            	    if (summary ==3){
        				if ( dropdown2 == 15 ||   dropdown2 == 80  ) {
        					andClause=  " and d.shortname in ('WL<80','WL>80') "; 
        				}
        				else {
        					//queryPart1 =  " select a.year, a.quantity,  substr(b.name,1,70)  as company, d.name as product, c.country ";
        					andClause = " and a.productId = "+ dropdown2;
        				}
						 //andClause = " AND a.productId = " +dropdown2;
					}
   			       isAllComps = true;
	            	break;
			  }
			  case(YEARS):{
	            	TWO="Country";
	            	topHeadingLine=COUNTRY;
	            	ONE="Product";
	            	if (swap!=1){
	            	  selectClause = " c.shortname as country,  d.name as product,   ";
	            	  groupAndOrderByClause = " d.name, c.shortname ";
	            	} else {
		              selectClause = " c.country,  d.shortname as product,   ";
		              groupAndOrderByClause = " c.country, d.shortname ";
	            	}
	            	//CXM
				     isAllComps = true;
				     
            	    if (summary ==3){
						 andClause = " AND a.year = " +dropdown2;
					} 
			  }
			}
		}
    }


		if (swap==1) {
			
			if(ONE.equals("Country")) {
				topHeadingLine=COUNTRY;
			}
			if(ONE.equals("Product")) {
				topHeadingLine=PRODUCT;
			}
			if(ONE.equals("Year")) {
				topHeadingLine=YEARS;
			}
			if(ONE.equals("Company")) {
				topHeadingLine=COMPANY;
			}
			String oneA = ONE;
			ONE = TWO;
			TWO = oneA;
			
		}
		
		 //CXM --
		  if(isAllComps == false){
  		      allCompClause = ""; 	    			  ;
		  }
		
	      query = " select "+selectClause+"  SUM(a.quantity) quantity " +
	      		" from Facts_"+access+" a, Company b, Country c, Product d " +
	    		  " where a.companyid=b.id " +
	    		  " and a.sales_production=" +salesOrProduction +
	    		  andClause +
	    		  " and a.year between "+fromYear+" and "+toYear+" " +
	    		  " and a.access = '" + access + "' " +
	    		  " and b.access = '" + access + "' " +
	    		  " and c.access = '" + access + "' " +
	    		  " and d.access = '" + access + "' " +
	    		  " and d.id = a.productId " +
	    		  incExCountries +
	    		  incExProducts+
	    		  incExCompanies+
	    		  //CXM
	  		      allCompClause+ 
	    		  dateParm+
	    	//	  " and b.name != 'ALL COMPANIES' " +
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
