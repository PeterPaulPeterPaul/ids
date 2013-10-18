package com.ids.businessLogic;

import javax.servlet.http.HttpServletRequest;

public class StoreRequestParameters {

	private int list;
	private int salesOrProduct;
	private int dropdown1;
	private int dropdown2;
	private int heading1;
	private int heading2;
	private boolean doNewDrops = false;
	private int oldHeading1;
	private int oldHeading2;
	private String justClicked="";
	private String incExCountries="";
	private String incExProducts="";
	private String incExCompanies="";
	private String dateParm="";
	private String fromDate="";
	private String toDate="";
	private int summary=0;
	private int swap=0;
	private int myYear=0;
	
	
	public StoreRequestParameters(HttpServletRequest request, int myYear, String longStringCompanies){
		
		if (request.getParameter("newDrops")!= null && request.getParameter("newDrops").equals("yes")){
			doNewDrops = true;
		} else {
			doNewDrops = false;
		}
		this.myYear = myYear;
		list = Integer.parseInt(request.getParameter("list"));
		salesOrProduct = Integer.parseInt(request.getParameter("pors"));
		summary=Integer.parseInt(request.getParameter("summary"));
		System.out.println("SUMMARY: "+summary);
	//	if (summary!=1  &&  summary != 4 ) {
		    dropdown1=Integer.parseInt(request.getParameter("dropdown1"));
	//	}
	//	if (summary!=2  &&  summary != 3 &&  summary != 5 ) {
			System.out.println("DROPDOWN2: "+Integer.parseInt(request.getParameter("dropdown2")) );
		    dropdown2=Integer.parseInt(request.getParameter("dropdown2"));
	//	}
		heading1=Integer.parseInt(request.getParameter("radio1"));
		heading2=Integer.parseInt(request.getParameter("radio2"));
		oldHeading1=Integer.parseInt(request.getParameter("oldHead1"));
		oldHeading2=Integer.parseInt(request.getParameter("oldHead2"));
		justClicked=request.getParameter("clickType");

		swap=Integer.parseInt(request.getParameter("swap"));
		incExCountries = request.getParameter("excludedCountries");
		if (incExCountries== null || incExCountries.length()<1){
			incExCountries = request.getParameter("includedCountries");
		}
		if (incExCountries !=null && incExCountries.length()>0 ){
			incExCountries=incExCountries.substring(0,incExCountries.length()-1);
			if( request.getParameter("excludedCountries")==null ||request.getParameter("excludedCountries").length()<1 ){
				incExCountries= " AND c.id IN ("+incExCountries.replace("|", ",")+") ";
			}else{
				incExCountries= " AND c.id NOT IN ("+incExCountries.replace("|", ",")+") ";
			}
		}
		
		incExProducts = request.getParameter("excludedProducts");
		if (incExProducts== null || incExProducts.length()<1){
			incExProducts = request.getParameter("includedProducts");
		}
		if (incExProducts !=null && incExProducts.length()>0 ){
			incExProducts=incExProducts.substring(0,incExProducts.length()-1);
			if( request.getParameter("excludedProducts")==null ||request.getParameter("excludedProducts").length()<1 ){
				incExProducts= " AND d.id IN ("+incExProducts.replace("|", ",")+") ";
			}else{
				incExProducts= " AND d.id NOT IN ("+incExProducts.replace("|", ",")+") ";
			}
		}
		
		
		incExCompanies = longStringCompanies;
		if (incExCompanies !=null && incExCompanies.length()>1 ){
			incExCompanies=incExCompanies.substring(0,incExCompanies.length()-1);
			incExCompanies= " AND b.id IN ("+incExCompanies.replace("|", ",")+") ";
		}
		
		fromDate = request.getParameter("fromDate");
		toDate = request.getParameter("toDate");
		dateParm = request.getParameter("dateParm");
		

		if (justClicked.equals("myrad2")) {
			justClicked="heading1";
		}
		if (justClicked.equals("myrad3")) {
			justClicked="heading2";
		}

	}
	
	public int getHeading1(){
		return heading1;
	}
	public int getHeading2(){
		return heading2;
	}
	public int getOldHeading1(){
		return oldHeading1;
	}
	public int getOldHeading2(){
		return oldHeading2;
	}
	public int getDropdown1(){
		return dropdown1;
	}
	public int getDropdown2(){
		return dropdown2;
	}
	public int getSwap(){
		return swap;
	}
	
	public void setHeading1(int heading1){
		 this.heading1 = heading1;
	}
	public void setHeading2(int heading2){
		this.heading2 = heading2;
	}
	public void setOldHeading1(int oldHeading1){
		 this.oldHeading1 = oldHeading1;
	}
	public void setOldHeading2(int oldHeading2){
		this.oldHeading2 = oldHeading2;
	}
	public void setDropdown1(int dropdown1){
		this.dropdown1 = dropdown1;
	}
	public void setDropdown2(int dropdown2){
		this.dropdown2 = dropdown2;
	}
	
	public String getIncExCountries(){
		if (incExCountries==null){
			incExCountries="";
		}
		return incExCountries;
	}
	public String getIncExProducts(){
		if (incExProducts==null){
			incExProducts="";
		}
		return incExProducts;
	}
	public String getIncExCompanies(){
		if (incExCompanies==null){
			incExCompanies="";
		}
		return incExCompanies;
	}
	
	public String getDateParm(){
		if (dateParm==null){
			dateParm="";
		}
		return dateParm;
	}
	
	public void setDateParm(String dateParm){
		this.dateParm = dateParm;
	}
	
	public int getFromDate(){
		int theDate=0;
		if (fromDate==null){
			theDate=myYear-5;
		}else {
			theDate = Integer.parseInt(fromDate);
		}
		return theDate;
	}
	
	public int getToDate(){
		int theDate=0;
		if (toDate==null){
			theDate=myYear+5;
		}else {
			theDate = Integer.parseInt(toDate);
		}
		return theDate;
	}
	
	public int getSalesOrProduct(){
		return salesOrProduct;
	}
	public String getJustClicked(){
		return justClicked;
	}
	
	public int getList(){
		return list;
	}
	
	public int getSummary() {
		return summary;
	}
	
	public boolean getDoNewDrops() {
		return doNewDrops;
	}
	
}
