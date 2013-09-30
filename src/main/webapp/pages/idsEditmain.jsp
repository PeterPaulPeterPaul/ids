<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css" media="screen" href="css/ui-lightness/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.dropdownchecklist.themeroller.css" />

          <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.selectboxit/3.6.0/jquery.selectBoxIt.css" />

<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/ui.dropdownchecklist-1.4-min.js"></script>

          <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.selectboxit/3.6.0/jquery.selectBoxIt.min.js"></script>

<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>
<title>IDS</title>
</head>
<style>
body { 
background-color:#FFFF80; 
}
.js {display: none;}
.colorWhite {
            background-color: white !important;
        }
.colorGrey {
            background-color: grey !important;
        }
.colorPink {
            background-color: #f6a828 !important;
        }
.colorLightPink {
            background-color: pink !important;
        }
 .filter {
 border-color: gray;
border-bottom: 2px solid;
border-right: 2px solid;
 }
 .printerIcon {
 background-image: url(images/exit.bmp);
background-repeat: no-repeat;
background-position:left;
padding-left: 3px;
 }
 .footrow td
{
color:#1c94c4;
}       
.titleFont{
color:#1c94c4;
}


div.box{
background: url(images/yellow.png) center center fixed; 
-webkit-background-size: cover;
-moz-background-size: cover;
background-size: cover;
border-style:solid;
border-width:1px;
border-color:grey;
width:100%;
height:56px;
}
.IDSheader{
margin-top:15px;
font-family:Arial,Helvetica,sans-serif;
font-weight:normal;
font-size:30px;
position:relative;
top:10px;
}
.leftLogo{
float:left;
margin-top:3px;
margin-left:3px;
margin-right:20px;
}
.noOverlayDialog{
opacity:0.2;
}
.rightLogo{
float:right;
margin-top:3px;
margin-left:10px;
margin-right:3px;
}
</style>

<body>
<div id="wholescreen" style="width:100%;height:100%">

<div class="box">
<span class="IDSheader"> 
<img class="leftLogo" src="images/IDS-Logo.png" width="50px" height="50px">
International Database Service 
<img class="rightLogo" src="images/OHR-logo.png"  >
</span>
</div>


<div style="float:right;width:18%;background-color:#FFFF80;height:100%;">
<div>

<div id="tempStore" style="display:none"></div>
<div id="tempOldHeadings" style="display:none">12</div>



   <div id="dialogFilter" title="Filter" style="z-index:1500">
             <div style="width:800px;float:left">
             
             <div id="dropa11" style="width:230px; float:left" >
<select name="e_or_i1" class="dropdown33" id="dropa11s" style="font-size:small;width:180px;margin:10px">
  <option value="1">Exclude</option>
  <option value="2">Include</option>
</select>
</div>

             <div id="dropa22" style="width:230px; float:left" >
<select name="e_or_i2" class="dropdown33" id="dropa22s" style="font-size:small;width:180px;margin:10px">
  <option value="1">Exclude</option>
  <option value="2">Include</option>
</select>
</div>

                <div id="dropa33" style="width:250px; float:left" >
<select name="e_or_i3" class="dropdown33" id="dropa33s" style="font-size:small;width:180px;margin:10px">
  <option value="1">Exclude</option>
  <option value="2">Include</option>
</select>
</div>

                <div id="dropa44" style="width:90px; float:left" >
<input type="button" style="font-size:small;"  name="submit1" id="submit1"  value="Hide"/>
    </div>
          
<div id="dropa1"  style="width:230px; float:left; z-index:1500" >
<select multiple="multiple" class="dropdown11" id="drop11as" style="font-family:Arial, Helvetica, sans-serif;">
<option   class="drop11check" style="font-family:Arial, Helvetica, sans-serif;font-size:small;color:green"value="-1">Toggle clear/all &nbsp;</option>
 <c:forEach var="drop1" items="${dropdown1a}">
 <option class="drop11check"  style="font-family:Arial, Helvetica, sans-serif;font-size:small;" value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="dropa2"  style="width:230px;float:left"  >
<select multiple="multiple" class="dropdown11" id="drop12as">
<option style="font-size:small;color:green" value="-1">Toggle clear/all&nbsp;</option>
 <c:forEach var="drop1" items="${dropdown1b}">
  <option style="font-size:small;" value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>



<div id="dropa4"  style="width:230px;float:left"  >
<select multiple="multiple" class="dropdown11" id="drop14as" >
<option style="font-size:small;color:green"value="-1">Toggle clear/all&nbsp;</option>
 <c:forEach var="drop1" items="${dropdown1d}">
  <option style="font-size:small;" value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>



</div>
             <div style="width:800px;float:left">
          <br><span style="margin-left:200px;float:left;font-family:Arial, Helvetica, sans-serif;font-size:small;"> Include only dates within selected range</span><br>   
       <div style="margin-left:250px;width:150px;float:left;font-family:Arial, Helvetica, sans-serif;font-size:small;">   
From <select  id="fromdate" name="fromdate" >
</select>
</div>

       <div style="font-family:Arial, Helvetica, sans-serif;font-size:small;" >      
To <select id="todate" name="todate" >
</select>
</div>


</div>
    </div>



<div style="margin-left:15px;float:right;">




</div>


<div style="margin-left:15px;text-align:center"><br><br>
  <input type="image" name="filter" class="filter" id="filter"   src="images/filter.png" />
   <input type="button" style="font-size:x-small;display:none"  id="clearfilter"  value="Clear Filter"/>
</div>
<div style="float:left;width:90%;margin-right:5%;margin-top:5%;">
<fieldset style="background-color:#FFFF80;margin-left: 10px;">
<legend>Header 1</legend>
<input class="myrad2" type="radio" name="horiz" id="a1"  value="1" checked >Country<br>
<input class="myrad2" type="radio" name="horiz" id="a2"  value="2"  >Product<br>
<input class="myrad2" type="radio" name="horiz" id="a3"  value="3"  >Years<br>
<input class="myrad2" type="radio" name="horiz" id="a4"  value="4"  ><label id="a44">Company</label>

</fieldset>
</div>
<div style="float:left;width:90%;margin-right:5%;margin-top:15%">
<fieldset style="background-color:#FFFF80;margin-left: 10px;">
<legend>Header 2</legend>
<input class="myrad3" type="radio" id="z1" name="verti" value="1"  >Country<br>
<input class="myrad3" type="radio"  id="z2" name="verti" value="2" checked >Product<br>
<input class="myrad3" type="radio"  id="z3" name="verti" value="3"  >Years<br>
<input class="myrad3" type="radio"  id="z4"  name="verti" value="4"  ><label id="z44">Company</label>



</fieldset>
</div>


</div>

</div>

<div id="myDimensionHidden" style="display:none"></div>

<div id="titleBar" style="float:left;width:82%;height:40%;padding-top:1%;">
<div style="float:left;">


<form  id="testUp1" action="/cron/down" method="post" name="factsForm"   > 
 <input id="dataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="totalsJson"  type="hidden" name="jsonTotals" value="" />
          <input id="one" class="k-button" type="submit" name="submitBtn" value="Download Excel" />

</form>

<form  id="printer" action="/print" method="post" name="factsForm"   > 
 <input id="printDataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="printTotalJson"  type="hidden" name="jsonTotals" value="" />
          <input id="two" class="k-button" type="submit" name="submitBtn" value="Print Preview" />

</form>



<input type="image" name="close" id="closeit"  src="images/exit.bmp" />




</div>







<div style="text-align:center;float:none">
<div style="margin-left:15%;float:left">



<div id="drop11" class="showornot1 viewable1" style="display:block;">
<select class="dropdown1" id="drop11s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1a}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop12"  class="showornot1" style="display:none">
<select class="dropdown1" id="drop12s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1b}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop13"  class="showornot1" style="display:none">
<select class="dropdown1" id="drop13s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1c}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop14"  class="showornot1" style="display:none">
<select class="dropdown1" id="drop14s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1d}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>


<div id="box11"  class="showornot11" style="display:none">
<span  class="selectboxit-container">
<span  class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span class="selectboxit-text" style="width: 180px;">Summary - All Countries</span>
</span>
</span>
</div>

<div id="box12"  class="showornot11" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span class="selectboxit-text" style="width: 180px;">Summary - All Products</span>
</span>
</span>
</div>

<div id="box13"  class="showornot11" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span class="selectboxit-text" style="width: 180px;">Summary - All Years</span>
</span>
</span>
</div>


<div id="box31"  class="showornot111" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span class="selectboxit-text" style="width: 180px;">Grp Sumary: All Countries</span>
</span>
</span>
</div>

<div id="box32"  class="showornot111" style="display:none">
<span class="selectboxit-container">
<span  class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span  class="selectboxit-text" style="width: 180px;">Grp Summary: All Products</span>
</span>
</span>
</div>

<div id="box33"  class="showornot111" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span  class="selectboxit-text" style="width: 180px;">Grp Summary: All Years</span>
</span>
</span>
</div>


</div>

<div style="float:left;margin-left:20px">
<div id="drop21" class="showornot2" style="display:none;">
<select class="dropdown2" id="drop21s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1a}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop22"  class="showornot2 viewable2" style="display:block">
<select class="dropdown2" id="drop22s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1b}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop23"  class="showornot2" style="display:none">
<select class="dropdown2" id="drop23s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1c}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop24"  class="showornot2" style="display:none">
<select class="dropdown2" id="drop24s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1d}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>


<div id="box21"  class="showornot22" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span class="selectboxit-text" style="width: 180px;">Grp Sumary: All Countries</span>
</span>
</span>
</div>

<div id="box22"  class="showornot22" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span class="selectboxit-text" style="width: 180px;">Grp Summary: All Products</span>
</span>
</span>
</div>

<div id="box23"  class="showornot22" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span class="selectboxit-text" style="width: 180px;">Grp Summary: All Years</span>
</span>
</span>
</div>

<div id="box24"  class="showornot22" style="display:none">
<span  class="selectboxit-container">
<span  class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 190px;">
<span  class="selectboxit-text" style="width: 180px;">Grp Summary: All Companies</span>
</span>
</span>
</div>

</div>





<div style="float:left;margin-left:20px">

<div id="drop31" style="display:block">
<select name="s_or_p" class="dropdown3" id="drop31s" style="width:180px;margin:10px">
  <option value="1">Sales</option>
  <option value="2">Production</option>
</select>
</div>


</div>



 </div>
 
 
 
</div>


<div id="beans" style="margin-left:5px;margin-top:18px;float:left;width:81%;height:90%;background-color:#FFFF80;">
<table id="list47"></table>
<div id="plist47"></div>
</div>
          <script type="text/javascript">
          
          
          
          $(function(){
        	    var dialogOpts = {
        	        modal: true,
        	        width: 800,
        	        height: 106,
        	        position: 'top',
        	        dialogClass: "noOverDialog",
        	        open: function(event,ui){
        	        	$(".noOverDialog").next("div").css({opacity:0.2});
        	        }
        	   };

        	    $("#dialogFilter").dialog(dialogOpts);


        	});
          
          $(document).ready(function(){

        	  var lastSel;
        	  
        	  var j = new Date().getFullYear();
        	  j=j+20;
        	  var stringList="<option value='-1'>All</option>";
        	   for(var i = 1980; i <= j; i++) {
        		   stringList = stringList + "<option value='"+i+"'>"+i+"</option>";

        	     }
    		   $("#fromdate").append(stringList);
    		   $("#todate").append(stringList);
        	  
    		   
    		   
    		   
        	  var downloadExcel="no";
        	  
        	  $("#toExcel").on("click",function(){ 
        		  downloadExcel="yes";
        		  getGrid();
        	  });

        	  $("#drop11as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Countries...",  width:200, zIndex:100,maxDropHeight:400
         		 , onItemClick: function(checkbox, selector){ 
         			downloadExcel="no";
        			 getGrid();
        		 }
        	  });
        	  
        	  $("#drop12as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Products ...",  width:200, zIndex:100,maxDropHeight:400 
         		 , onItemClick: function(checkbox, selector){ 
         			downloadExcel="no";
        			 getGrid();
        		 }
        	  });
        	  $("#drop14as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Companies ...",  width:200, zIndex:100,maxDropHeight:400
        		 , onItemClick: function(checkbox, selector){ 
        			 downloadExcel="no";
        			 getGrid();
        		 }
        	  
        	  });
        	
        	  
        	  
        	 
        	  
        	  $("#dialogFilter").dialog("close");
        	  

        	  
        	  $("#closeit").on("click",function(){
        		  $.ajax({
					  url: '/main?exit=yes',
			         type: 'GET',
			       contentType: 'application/html',
			       processData: false,
			       dataType: 'html',
			       success: function(data) {  
			    	   window.location = '/login';
			       },
				    error: function (xhr, ajaxOptions, thrownError) {
				        alert(xhr.status);
				        alert(thrownError);
				        window.location = '/login';
				      }

				  });
        	  });
        	  
       	  $(".dropdown1").selectBoxIt();
        	  $(".dropdown2").selectBoxIt();
        	  $(".dropdown3").selectBoxIt();
        	  $(".dropdown33").selectBoxIt();
        	  
        	  $( "input:button" ).button();
        	  
        	  $(".dropdown1").css("width","182px");
        	  $(".dropdown2").css("width","182px");

        	var clickType="";


  
	
	
	  
	  
	  
	  $("#clearfilter").on("click",function(){
          $("#fromdate").val("-1");
          $("#todate").val("-1");
			$('input[id^="ddcl-drop12as-i"]').each(function( index ) {
				if($(this).prop("checked")){
				   $(this).prop("checked",false); 
			     }
			});
            $('input[id^="ddcl-drop14as-i"]').each(function( index ) {
					if($(this).prop("checked")){
						$(this).prop("checked",false);  
				     }
				});
             $('input[id^="ddcl-drop11as-i"]').each(function( index ) {
					if($(this).prop("checked")){
						$(this).prop("checked",false); 
						$(this).children("span").text();
				     }
				});
				
             $(".ui-dropdownchecklist-text:first").prop("title","Filter Countries...");
             $(".ui-dropdownchecklist-text:first").children("span").remove();
             $(".ui-dropdownchecklist-text:first").text("Filter Countries...");
             $("#ddcl-drop12as").children("span").children(".ui-dropdownchecklist-text").prop("title","Filter Products ...");
             $("#ddcl-drop12as").children("span").children(".ui-dropdownchecklist-text").children("span").remove();
             $("#ddcl-drop12as").children("span").children(".ui-dropdownchecklist-text").text("Filter Products ...");
             
             $("#ddcl-drop14as").children("span").children(".ui-dropdownchecklist-text").prop("title","Filter Companies...");
             $("#ddcl-drop14as").children("span").children(".ui-dropdownchecklist-text").children("span").remove();
             $("#ddcl-drop14as").children("span").children(".ui-dropdownchecklist-text").text("Filter Companies...");
             downloadExcel="no";
             getGrid();
             
          alert("filters cleared");
          $("#clearfilter").fadeOut();
	  });
	  
	  $("#submit1").on("click",function(){
		  $("#dialogFilter").dialog("close");
	  });
	  
	 
	  $(".dropdown33").on("change",function(){
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $("#fromdate").on("change",function(){
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $("#todate").on("change",function(){
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $(".dropdown1").on("change",function(){
		  clickType= $(this).attr('id');
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $(".dropdown2").on("change",function(){
		  clickType=$(this).attr('id');
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  
	  $(".dropdown3").on("change",function(){
		  clickType="myrad";
		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  
	  $(".myrad2").on("change",function(){
		  clickType="myrad2";
		 
		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  $(".myrad3").on("change",function(){
		  clickType="myrad3";


		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  function getGrid()
	  {


          
		  
var myId2= $(".viewable2").attr("id");
var mydropdown2 = $("#"+myId2+"s").val();

var firstPartId = $(".myrad2:checked").val();
var secondPartId = $(".myrad3:checked").val();

if (clickType=="myrad3") {

	   mydropdown2 = $("#drop2"+secondPartId+"s").val();

}

var h1 = $("#tempOldHeadings").html();
var h2 = h1;
h1 = h1.substr(0,1);
h2 = h2.substr(1,1);

var myId= $(".viewable1").attr("id");
var mydropdown1 = $("#"+myId+"s").val();


	if 	(firstPartId == secondPartId && clickType=="myrad3" && (summary ==0 || h2 != "4")){
		$("#"+myId).removeClass("viewable1");
		$("#drop1"+h2).addClass("viewable1");
		 mydropdown1 = $("#drop1"+h2+"s").val();
		  $(".myrad2").prop('checked', false);
		  $("#a"+h2).prop('checked', true);
	}




if (clickType=="myrad2") {
           mydropdown1 = $("#drop1"+firstPartId+"s").val();
} 

if 	(firstPartId == secondPartId && clickType=="myrad2" ){
	$("#"+myId).removeClass("viewable2");
	$("#drop2"+h1).addClass("viewable2");
	 mydropdown2 = $("#drop2"+h1+"s").val();
	  $(".myrad3").prop('checked', false);
	  $("#z"+h1).prop('checked', true);
}

if (clickType=="myrad2" || clickType=="myrad3" ) {
	   $(".showornot1").fadeOut();
	   $(".showornot2").fadeOut();
	   $(".showornot2").removeClass("viewable2");
	   $(".showornot1").removeClass("viewable1");
}

$(".showornot11").fadeOut();
$(".showornot111").fadeOut();
$(".showornot22").fadeOut()

var my_SorP = $('#drop31s').val();
			 
$("#wholescreen").fadeOut();
$("#titleBar").fadeOut();


var dateParm=       validateDates() ;
if (dateParm=="todate must be greater or equal to fromdate") {
	 alert(dateParm);
	 $("#titleBar").fadeIn();
	 $("#wholescreen").fadeIn();
	 
	 $("#clearfilter").fadeIn();
	  

	  
	 return;
}
   

			 $('#gbox_list47').fadeOut().promise().done(function() {  
	
				 
				 
				 
				 
				 
				   $('#gbox_list47').remove();
				   $("#beans").append("<table id='list47'></table><div id='plist47'></div>");

				   if (summary!=0  &&  summary !=3 ) {
					   if (h2=="4") {
						  if ($(".myrad3:checked").val() != "4") {
							  if ($(".myrad3:checked").val() == $(".myrad2:checked").val()){
								  if ($(".myrad2:checked").val()=="1") {
									  $("#a1").prop('checked', false);
									  $("#a2").prop('checked', true);
									  mydropdown1 = $("#drop12s").val();
								  } else {
								      if ($(".myrad2:checked").val()=="2") {
									      $(".myrad2").prop('checked', false);
									      $("#a3").prop('checked', true);
									      mydropdown1 = $("#drop13s").val();
								      } else { 
								          if ($(".myrad2:checked").val()=="3") {
									       $(".myrad2").prop('checked', false);
									       $("#a1").prop('checked', true);
									       mydropdown1 = $("#drop11s").val();
								          }
								      }
								  }
							  }
						  }
					   }
				   }
				   
				   
				//   alert('/ids/main?list=2&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
				//			  +'&radio1='+$(".myrad2:checked").val()+'&radio2='+$(".myrad3:checked").val()+"&clickType="+clickType+
				//			  "&oldHead1="+h1+"&oldHead2="+h2+"&summary="+summary+"&swap="+swapValue);

				var countriesParm="";
				var countriesList="";

				$('input[id^="ddcl-drop11as-i"]').each(function( index ) {
					if($(this).prop("checked")){
					   countriesList= countriesList+$(this).val()+"|"; 
				     }
				});
				
	               if (countriesList!=""){
	      				if ($('#dropa11s').val()=="1"){
	   					countriesParm="&excludedCountries=";
	   				}else{
	   					countriesParm="&includedCountries=";
	   				}
	                   }
	               
	               
					var productsParm="";
					var productsList="";

					$('input[id^="ddcl-drop12as-i"]').each(function( index ) {
						if($(this).prop("checked")){
						   productsList= productsList+$(this).val()+"|"; 
					     }
					});
					
		               if (productsList!=""){
		      				if ($('#dropa22s').val()=="1"){
		   					productsParm="&excludedProducts=";
		   				}else{
		   					productsParm="&includedProducts=";
		   				}
		                   }

						var companiesParm="";
						var companiesList="";

						$('input[id^="ddcl-drop14as-i"]').each(function( index ) {
							if($(this).prop("checked")){
								companiesList= companiesList+$(this).val()+"|"; 
						     }
						});
						
			               if (companiesList!=""){
			      				if ($('#dropa33s').val()=="1"){
			      					companiesParm="&excludedCompanies=";
			   				}else{
			   					companiesParm="&includedCompanies=";
			   				}
			                   }
   
			               var fromDate = "";
			               if ($("#fromdate").val() != "-1") {
			            	   fromDate = "&fromDate="+$("#fromdate").val();
			               }
			               var toDate = "";
			               if ($("#todate").val() != "-1") {
			            	   toDate = "&toDate="+$("#todate").val();
			               }
			               
			       		if ($("#fromdate").val() != "-1" ||
			       				$("#todate").val() != "-1"	||
			       				companiesList!="" ||
			       				productsList!="" ||
			       				countriesList!=""){
			       	              $("#clearfilter").fadeIn();
			       		}
			       		
			       		
			       		var saveURL = '/saverow?list=2&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
						  +'&radio1='+$(".myrad2:checked").val()+'&radio2='+$(".myrad3:checked").val()+"&clickType="+clickType+
						  "&oldHead1="+h1+"&oldHead2="+h2+"&summary="+summary+"&swap="+swapValue
						  +countriesParm+countriesList+productsParm+productsList+
						  companiesParm+companiesList+fromDate+toDate+"&excelDownload="+downloadExcel+dateParm;
						
			       		
			       		
			       		var saveparameters = {
			       				"successfunc" : null,
			       				"url" : saveURL,
			       			        "extraparam" : {},
			       				"aftersavefunc" : null,
			       				"errorfunc": null,
			       				"afterrestorefunc" : null,
			       				"restoreAfterError" : true,
			       				"mtype" : "POST"
			       			}
			       		
			       		
			               
					  $.ajax({
						  url: '/main?list=2&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
				  +'&radio1='+$(".myrad2:checked").val()+'&radio2='+$(".myrad3:checked").val()+"&clickType="+clickType+
								  "&oldHead1="+h1+"&oldHead2="+h2+"&summary="+summary+"&swap="+swapValue
								  +countriesParm+countriesList+productsParm+productsList+
								  companiesParm+companiesList+fromDate+toDate+"&excelDownload="+downloadExcel+dateParm,
				         type: 'GET',
				       contentType: 'application/html',
				       processData: false,
				       dataType: 'html',
				       success: function(data) {  

				    	  
				    	   $("#tempStore").html(data);
				    	   var data2 = JSON.parse($("#tempStore #myJson").html());
				    	   
				    	   $("#printDataJson").val($("#tempStore #myJson").html());
				    	   $("#dataJson").val($("#tempStore #myJson").html());
				    	   
				    	   $("#tempOldHeadings").text($("#tempStore #myOldHeadings").html() );
				    	   
				    	   $("#myDimensionHidden").text($("#tempStore #myDimension").html() ) ;
				    	   
	    			        $(".myrad2").prop('checked', false);
		    				   $(".myrad3").prop('checked', false);
		    				   
		    				   var h1 = $("#tempOldHeadings").html();
		    				   var h2 = h1;
		    				   h1 = h1.substr(0,1);
		    				   h2 = h2.substr(1,1);
		    				   
		    				   $("#a"+h1).prop('checked', true);
		    				   $("#z"+h2).prop('checked', true);
		    				   

		    				
		    				  


					    	   if (summary==1) {
					    		   $(".showornot1").fadeOut();
					    		   $("#box1"+h1).fadeIn();
					    	   }
					    	   if (summary==2) {
					    		   $(".showornot1").fadeOut();
					    		   $("#box3"+h1).fadeIn();
					    	   }
					    	   
					    	   if (summary==3) {
					    		   $(".showornot2").fadeOut();
					    		   $("#box2"+h2).fadeIn();
					    	   }
					    	   if (summary==4) {
					    		   $(".showornot2").fadeOut();
					    		   $(".showornot1").fadeOut();
					    		   $("#box2"+h2).fadeIn();
					    		   $("#box1"+h1).fadeIn();
					    	   }
					    	   if (summary==5) {
					    		   $(".showornot2").fadeOut();
					    		   $(".showornot1").fadeOut();
					    		   $("#box2"+h2).fadeIn();
					    		   $("#box3"+h1).fadeIn();
					    	   }

		    				  var value1 =  $("#tempStore #myDropValue1").html();
		    				  var value2 =  $("#tempStore #myDropValue2").html();
 		    				   $(".showornot1").removeClass("viewable1");
  		    				   $("#drop1"+h1).addClass("viewable1");
  		    				   $(".showornot2").removeClass("viewable2");
  		    				   $("#drop2"+h2).addClass("viewable2");
  		    				   
		    				    if (summary != 1 && summary !=4 && summary != 2 && summary !=5) {
		  		    				   $("#drop1"+h1).fadeIn();
	  		    				    }
		    				    
		    				    if (summary != 3 && summary != 4 && summary != 5) {
		  		    				   $("#drop2"+h2).fadeIn();
		  		    				}
		    				    
		    				    
		    				   if (clickType=="myrad2"){
	  		    				   $("#drop1"+h1+" option:selected").prop("selected", false);
	  		    				   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);
		  		    				$("#drop2"+h2+" option:selected").prop("selected", false);
		  		    				$("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);

		    				   }  
		    				   
		    				   if (clickType=="myrad3"){
	  		    				   $("#drop2"+h2+" option:selected").prop("selected", false);
	  		    				   $("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);
			  		    		   $("#drop1"+h1+" option:selected").prop("selected", false);
			  		    		   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);

		    				   }
		    				   
		    				   
		    				   
				    	   
				    	   var myTabData = data2.tabData;
			var cols= myTabData[1].columns;
		    var colModels=myTabData[0].columnModels;
		    var mylocalData=myTabData[2].myData;
		    var mylocalTitle=myTabData[3].title;
		mylocalTitle = JSON.stringify(mylocalTitle)
		mylocalTitle = mylocalTitle.replace(/"/g, '');
		mylocalTitle = mylocalTitle.replace("[", "");
		mylocalTitle = mylocalTitle.replace("]", "");


		var colModels2 = JSON.stringify(colModels);
		  colModels2 = colModels2.replace(/\"formatter\":\"number\"/g,"\"formatter\":\"number\",\"formatoptions\":{\"decimalPlaces\":0,\"defaultValue\":\"0\"}");
		 var  colModels3 = JSON.parse(colModels2);
		  //formatter: 'number', formatoptions: { decimalPlaces: 2 }
		  
		 saveparameters = {
				    successfunc : null,
				    url : '/saverow',
				    extraparam : {},
				    aftersavefunc : function( response ) {
				                          alert('saved');
				                      },
				    errorfunc: null,
				    afterrestorefunc : null,
				    restoreAfterError : true,
				    mtype : "POST"
				};

				    			        jQuery("#list47").jqGrid({
				    			        	data:mylocalData,
				    			        	datatype: "local",
				    			        	editurl: saveURL,
				    			        	height: 350,
				    			        	cellEdit : true,
				    			        	cellsubmit : 'remote',
				    			        	cellurl : saveURL,
				    			        	
				    			        	beforeSubmitCell : function(rowid,celname,value,iRow,iCol) { 
				    			        		//if( some_condition ) { 
				    			        			

				    			        			var retVal = $("#list47").children("tbody").children("#"+rowid).children(":first").html();

				    			        			alert("dimension1:  " + retVal);
				    			        			var dimension2 = $(".viewable1").attr("id");
				    			        			var text2 = $("#"+dimension2+"sSelectBoxItText").text();
				    			        			var di_name="";
				    			        			if (dimension2 == "drop11") {
				    			        				di_name="countries";
				    			        			}
				    			        			if (dimension2 == "drop12") {
				    			        				di_name="products";
				    			        			}
				    			        			if (dimension2 == "drop13") {
				    			        				di_name="years";
				    			        			}
				    			        			if (dimension2 == "drop14") {
				    			        				di_name="companies";
				    			        			}
				    			        			alert("dimension2 value:  " + text2 +" name: "+di_name);

				    			        			var dimension3 = $(".viewable2").attr("id");
				    			        			var text3 = $("#"+dimension3+"sSelectBoxItText").text();
				    			        			
				    			        			if (dimension3 == "drop21") {
				    			        				di_name="countries";
				    			        			}
				    			        			if (dimension3 == "drop22") {
				    			        				di_name="products";
				    			        			}
				    			        			if (dimension3 == "drop23") {
				    			        				di_name="years";
				    			        			}
				    			        			if (dimension3 == "drop24") {
				    			        				di_name="companies";
				    			        			}
				    			        			
				    			        			alert("dimension3:  " + text3+" name: "+di_name);
				    			        			alert("dimension4:  " +$("#drop31sSelectBoxItText").text() + "name production or sales");
				    			        			alert("dimension5:  " +celname + " name: "+ $("#myDimensionHidden").text());
				    			        			
				    			        		return {firstColumn:retVal} 
				    			        	//	} 
				    			        	//	else 
				    			        	//	{ return {} } 
				    			        		} ,
				    			        	
				    			        	rowNum: 30,
				    			        	rowList: [10,20,30],
				    			           	colNames:cols,
				    			           	hidegrid:false,
				    			           	colModel:colModels3,
				    			           	pager: "#plist47",
				    			           	viewrecords: true,
				    			           	caption: mylocalTitle,
				    			         	footerrow: true, 
				    			         	 ignoreCase:true,
				    			         	userDataOnFooter: true,
				    			        	   onSelectRow: function(id){

					    			        	     if(id && id!==lastSel){ 
					    			        	    	 
					    			        	    	 jQuery('#list47').jqGrid('saveRow',id ,saveparameters);

					    			        	        lastSel=id; 
					    			        	     }
					    			        	     jQuery('#list47').editRow(id, true); 
					    			        	   },
				    			        });
				    			        


				    			        
				    			        
				    			        
				    			        
			    						  $("#wholescreen").fadeIn();
			    						  
				    			        jQuery("#list47").jqGrid('navGrid','#plist47',{edit:true,add:false,del:false});

				    	
				    			 	   $('#gbox_list47').fadeIn();
				    				   $('#list47').fadeIn();


				    					 var totals = JSON.parse($("#tempStore #myJsonTotals").html());
				    					 $("#totalsJson").val($("#tempStore #myJsonTotals").html());
				    					 $("#printDataJson").val($("#tempStore #myJsonTotals").html());
								    	   
								    	   
				    					 
				    						$("#list47").jqGrid('footerData', 'set', 
				    								totals.myTotals[0]); 
				    						
				    					
				    						 $(".ui-jqgrid-titlebar").remove();
				    						 
				    						 
				    						 $('#list47').setGridWidth($('#beans').width());
				    						 $(window).resize(function() {
				    							 $('#list47').setGridWidth($('#beans').width());
				    							});
				    					 
				    						 
				    						  if ($(".myrad2:checked").val()=="4"|| 
				    								  $(".myrad3:checked").val()=="4"){
				    							  $(".swap").show();
				    						  }
				    						  
				    						  $('.footrow').children("td").removeClass("colorWhite"); 
				    						  
				    						  $('.footrow').children('td').each(function( index ) {
				    							  if ( $(this).text()==" " || $(this).text()=='\xa0'){
				    								  $(this).text("0"); 
				    							  }
				    							  
				    							});
				    						  $("#titleBar").fadeIn();

					   },
					    error: function (xhr, ajaxOptions, thrownError) {
					        alert(xhr.status);
					        alert(thrownError);
					      }

					     
					     
					  });
				   
				   
				   
				   
			 });





  
  }  
	
	  var totals=${jsonTotal};

  
  var data = ${firstTimeFromServer};
  
  $("#dataJson").val(JSON.stringify(data));
  $("#printDataJson").val(JSON.stringify(data));
  
  $("#totalsJson").val(JSON.stringify(totals));
  
	 $("#printTotalJson").val(JSON.stringify(totals));

  
  
	   var myTabData = data.tabData;
		var cols= myTabData[1].columns;
	    var colModels=myTabData[0].columnModels;
	    var mylocalData=myTabData[2].myData;
	    var mylocalTitle=myTabData[3].title;
	mylocalTitle = JSON.stringify(mylocalTitle)
	mylocalTitle = mylocalTitle.replace(/"/g, '');
	mylocalTitle = mylocalTitle.replace("[", "");
	mylocalTitle = mylocalTitle.replace("]", "");

	
	var colModels2 = JSON.stringify(colModels);
	  colModels2 = colModels2.replace(/\"formatter\":\"number\"/g,"\"formatter\":\"number\",\"formatoptions\":{\"decimalPlaces\":0,\"defaultValue\":\"0\"}");
	 var  colModels3 = JSON.parse(colModels2);
	// $("#wholescreen").fadeIn();

      jQuery("#list47").jqGrid({
      	data:mylocalData,
      	datatype: "local",
      	height: 350,
      	cellEdit : true,
    	cellsubmit : 'remote',
    	cellurl :"/saverow",
    	
    	beforeSubmitCell : function(rowid,celname,value,iRow,iCol) { 
    		//if( some_condition ) { 

			var retVal = $("#list47").children("tbody").children("#"+rowid).children(":first").html();

			alert("dimension1:  " + retVal);
			var dimension2 = $(".viewable1").attr("id");
			var text2 = $("#"+dimension2+"sSelectBoxItText").text();
			var di_name="";
			if (dimension2 == "drop11") {
				di_name="countries";
			}
			if (dimension2 == "drop12") {
				di_name="products";
			}
			if (dimension2 == "drop13") {
				di_name="years";
			}
			if (dimension2 == "drop14") {
				di_name="companies";
			}
			alert("dimension2 value:  " + text2 +" name: "+di_name);

			var dimension3 = $(".viewable2").attr("id");
			var text3 = $("#"+dimension3+"sSelectBoxItText").text();
			
			if (dimension3 == "drop21") {
				di_name="countries";
			}
			if (dimension3 == "drop22") {
				di_name="products";
			}
			if (dimension3 == "drop23") {
				di_name="years";
			}
			if (dimension3 == "drop24") {
				di_name="companies";
			}
			
			alert("dimension3:  " + text3+" name: "+di_name);
			alert("dimension4:  " +$("#drop31sSelectBoxItText").text() + "name production or sales");
			alert("dimension5:  " +celname+ " name: years");
    			
    		return {firstColumn:retVal} 
    	//	} 
    	//	else 
    	//	{ return {} } 
    		} ,
      	rowNum: 30,
      	rowList: [10,20,30],
         	colNames:cols,
         	colModel:colModels3,
         	pager: "#plist47",
         	viewrecords: true,
         	caption: mylocalTitle,
         	hidegrid:false,
         	footerrow: true, 
         	 ignoreCase:true,
         	userDataOnFooter: true
      });
      
      jQuery("#list47").jqGrid('navGrid','#plist47',{edit:true,add:false,del:false});
      
      $("#filter").on("click",function(){
    	  
    	  $("#dialogFilter").dialog("open");
    	  
    	  $("#dialogFilter").removeClass("ui-dialog-content");
    	  $(".ui-dropdownchecklist-text").css("font-weight","lighter");
    	  $(".ui-widget-header").css("background","#FFFF80");
    	  $(".ui-widget-header").css("color","black");
    	  $(".ui-widget-header").css("border","1px solid black");
    	  $(".ui-widget-header").css("font-weight","normal");
       //   jQuery("#list47").jqGrid('searchGrid', 
       	//       {sopt:['eq','ne','cn','bw','bn','gt','lt','the','ge','in','ni','en','ew','nc','noo','nn'], multipleSearch:true, ignoreCase:true}
       //	);
       

    	  
    	  
      });

 
    //  $("#wholescreen").fadeIn();
      
	   $('#gbox_list47').fadeIn();
	   $('#list47').fadeIn();

/*
	   $("#gbox_list47").closest("div.ui-jqgrid-view")
	    .children("div.ui-jqgrid-titlebar")
	    .css("text-align", "center")
	    .children("span.ui-jqgrid-title")
	    .css("float", "none");
	   
	   $("#list47").closest("div.ui-jqgrid-view")
	    .children("div.ui-jqgrid-titlebar")
	    .css("text-align", "center")
	    .children("span.ui-jqgrid-title")
	    .css("float", "none");
	   $(".ui-jqgrid-titlebar").css("color","black");


			*/
			
  		 //   var newHeight = $(window).height() + "px";
		 //   $("body").css("height", newHeight);
		    
	//Every resize of window
//$(window).resize(function() {
 //   var newHeight =$(window).height()  + "px";
 //   $("body").css("height", newHeight);
//});
	
	
	
			
			$("#list47").jqGrid('footerData', 'set', 
					totals.myTotals[0]); 
			
			 $('.footrow').children("td").removeClass("colorWhite"); 
			 
		//	 var myval = $("span.ui-jqgrid-title").text();
		//	 $("#myTitle").text(myval);
			 
			 $(".ui-jqgrid-titlebar").remove();
			
			 
			 $('#list47').setGridWidth($('#beans').width());
			 
			 $(window).resize(function() {
				 $('#list47').setGridWidth($('#beans').width());
				});
			 
			 
			 
			  $('.footrow').children('td').each(function( index ) {


				  if ( $(this).text()==" " || $(this).text()=='\xa0'){
					  $(this).text("0"); 
				  }
				  
				});


		     	 function validateDates() {
		     		 var testing ="";
		     		 if ($("#fromdate").val()=="-1" && $("#todate").val()=="-1"){
		     			 return "";
		     		 }
		     		 if ($("#fromdate").val()=="-1" ||$("#todate").val()=="-1" ){
		     			 if ($("#fromdate").val()=="-1"){
		     				testing ="&dateParm= and a.year <="+$("#todate").val() + " "; 
		     			 } else {
		     				testing ="&dateParm= and a.year >="+$("#fromdate").val() + " ";  
		     			 }
		     		 }else{
		     			 if (parseInt($("#fromdate").val())> parseInt($("#todate").val())){
		     				 testing="todate must be greater or equal to fromdate";
		     			 }
		     			 if ($("#fromdate").val()== $("#todate").val()){
		     				 testing ="&dateParm= and a.year ="+$("#todate").val() + " ";
		     			 }
		     			 if (parseInt($("#fromdate").val())< parseInt($("#todate").val())){
		     				testing = "&dateParm= and a.year between  "+$("#fromdate").val() + " and "+ $("#todate").val() + " ";
		     			}
		     		 }

			   
		    		 return testing;
		    	}
		     	 
		     	 
			
  });
  


		
	</script>
	
	


</div>
</body>



</html>