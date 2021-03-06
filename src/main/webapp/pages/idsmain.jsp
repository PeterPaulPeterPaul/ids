
<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <meta charset="utf-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.dropdownchecklist.themeroller.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/jquery.selectboxit.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/IDS-YELLOW/jquery-ui-1.10.3.custom.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ids.css" />

<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/ui.dropdownchecklist-1.4-min.js"></script>
<script type="text/javascript" src="js/jquery.selectBoxIt.min.js"></script>
<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>

<title>${textPrefix}</title>
<style>
body { 
background-color:#FFFF00; 
}
body.wait, body.wait *{
 cursor: wait !important;   
}
</style>

</head>

<body class="js">
<div id="wholescreen" style="width:100%;height:100%">

	<div class="box">
	<span class="IDSheader"> 
	<p style="text-align:center; margin-top:2px;margin-bottom:1px">
    <img src="images/OHR-Logo-Medium.gif" alt="Off-Highway Research"/>
    </p>  
	</span>
	
	</div>

<input type="hidden" id="changeFlag" value="no"/>
 
<div id="titleBar" style="float:left;width:100%;height:50px;margin-top:5px">
<div style="float:right; margin-left:5px; margin-right:10px; margin-top:20px;"> <!-- action icons -->


<form  id="excel1" action="${ajaxPrefix}cron/down" method="post" name="factsForm1"   > 
 <input id="dataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="totalsJson"  type="hidden" name="jsonTotals" value="" />
      <input id="title11" type="hidden" name="title1" value=""/>
  <input id="title22" type="hidden" name="title2" value=""/>
  <input id="title33" type="hidden" name="title3" value=""/>
  <input id="title44" type="hidden" name="title4" value=""/>
</form>

<form   target="_blank" id="db11" action="${ajaxPrefix}pages/${filePrefix}.xlsx" method="get" name="db1Form1"   > 
<input id="myDB"  type="hidden" name="myDB" value="dummy" />
</form>

<form  target="_blank"  id="printer" action="${ajaxPrefix}print" method="post" name="factsForm2"   > 
 <input id="printDataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="printTotalJson"  type="hidden" name="jsonTotals" value="" />
    <input id="title1" type="hidden" name="title1" value=""/>
  <input id="title2" type="hidden" name="title2" value=""/>
  <input id="title3" type="hidden" name="title3" value=""/>
  <input id="title4" type="hidden" name="title4" value=""/>
</form>

<!--   <input id="two"  style="font-size:x-small" type="image" src="images/print-icon-32.png"  name="two" value="Print Preview" title="Print Preview" /> -->

 <!--  <input id="toggleRowTotal" style="font-size:x-small" type="image" src="images/table-sum-icon-32.png" name="toggleRowTotal" value="Remove row total" title="Remove row Total" />
  <input id="togglePercent"  style="font-size:x-small" type="image"  src="images/percent-icon-32.png" name="togglePercent" value="Add Percentages" title="Add Percentages"/>   -->
  <input id="one" style="font-size:x-small" type="image" src="images/table-excel-icon-32.png" name="submitBtn" value="Download Excel" title="Download table to Excel" />
  <input type="image" name="Download all ${textPrefix} data as Excel file" id="whenPressed"  src="images/Excel-Folder-icon-32.png" title="Download Database to Excel"/> 
  <input type="image" name="close" id="closeit"  src="images/deletered-32.png" title="Exit"/>

</div> <!-- end of action icons -->
<!-- end of action icons -->

<div style="text-align:center;float:none"> <!-- Div to hold the header drop downs -->

<div style="width:360px;float:left">

<fieldset class="idsBoxDefault" style="background-color:#FFFF00;margin-left: 10px;padding-bottom:3px;padding-top:1px">
<legend style="font-size:medium;"  >Header 1</legend>

<div style="margin-left:1%;float:left">  <!-- Summary icons for header 1 -->
<input type="image" src="images/go-up-icon-32.png" style="font-size:x-small;" class="nosum" name="summary" id="summary" value="Summary" title="Summary" />
<input type="image" src="images/sum-icon-32.png" style="font-size:x-small;" class="nosum" name="grpsum" id="grpsum" value="Group Sum" title="Group Summary"/>
</div>

<div style="margin-left:1px;float:left"> <!-- Header1 dropdown -->

<div id="drop11" class="showornot1 ${hideViewable1a}" style="${hideIt}">
<select class="dropdown1" id="drop11s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1a}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>


<div id="drop12"  class="showornot1 ${hideViewable1b}" style="${hideIt2}">
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
<span  class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 240px;">
<span class="selectboxit-text" style="width: 240px;">SUMMARY - ALL COUNTRIES</span>
</span>
</span>
</div>

<div id="box12"  class="showornot11" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 240px;">
<span class="selectboxit-text" style="width: 240px;">SUMMARY - ALL PRODUCTS</span>
</span>
</span>
</div>

<div id="box13"  class="showornot11" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 240px;">
<span class="selectboxit-text" style="width: 240px;">SUMMARY - ALL YEARS</span>
</span>
</span>
</div>


<div id="box31"  class="showornot111" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 240px;">
<span class="selectboxit-text" style="width: 240px;">GRP SUMMARY: ALL COUNTRIES</span>
</span>
</span>
</div>

<div id="box32"  class="showornot111" style="display:none">
<span class="selectboxit-container">
<span  class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 240px;">
<span  class="selectboxit-text" style="width: 240px;">GRP SUMMARY: ALL PRODUCTS</span>
</span>
</span>
</div>

<div id="box33"  class="showornot111" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 240px;">
<span  class="selectboxit-text" style="width: 240px;">GRP SUMMARY: ALL YEARS</span>
</span>
</span>
</div>


</div>

</fieldset>
</div>


<div style="${hideIt3}width:350px;float:left">

<fieldset class="idsBoxDefault" style="background-color:#FFFF00;margin-left: 10px;padding-bottom:3px;padding-top:1px">
<legend style="font-size:medium;"  >Header 2</legend>


<div style="margin-left:1%;float:left">  <!-- Summary icon for header 2 -->
<input type="image" src="images/sum-icon-32.png" style="font-size:x-small;" class="nosum" name="grpsum2" id="grpsum2" value="Group Sum" title="Group Summary"/>
</div>

<div style="float:left;margin-left:1px;width:250px"> <!-- Header2 dropdown -->

<div id="drop21" class="showornot2" style="display:none;">
<select class="dropdown2" id="drop21s" style="width:200px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1a}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop22"  class="showornot2 viewable2" style="${hideIt}  width:220px">
<select class="dropdown2" id="drop22s" style="width:200px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1b}">
  <option value="${drop1.id}">${drop1.name}</option>
  </c:forEach>
</select>
</div>

<div id="drop23"  class="showornot2" style="display:none">
<select class="dropdown2" id="drop23s" style="width:240px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1c}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop24"  class="showornot2" style="display:none">
<select class="dropdown2" id="drop24s" style="width:240px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1d}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>


<div id="box21"  class="showornot22" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 230px;">
<span class="selectboxit-text" style="width:230px;">GRP SUMMARY: ALL COUNTRIES</span>
</span>
</span>
</div>

<div id="box22"  class="showornot22" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 230px;">
<span class="selectboxit-text" style="width: 230px;">GRP SUMMARY: ALL PRODUCTS</span>
</span>
</span>
</div>

<div id="box23"  class="showornot22" style="display:none">
<span class="selectboxit-container">
<span class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 230px;">
<span class="selectboxit-text" style="width: 230px;">GRP SUMMARY: ALL YEARS</span>
</span>
</span>
</div>

<div id="box24"  class="showornot22" style="display:none">
<span  class="selectboxit-container">
<span  class="selectboxit selectboxit-enabled selectboxit-btn selectboxit-hover"   style="width: 230px;">
<span  class="selectboxit-text" style="width: 230px;">GRP SUMMARY: ALL COMPANIES</span>
</span>
</span>
</div>

</div>

</fieldset>
</div>



<div>

<div style="float:left;margin-left:25px; margin-top: 20px"> <!-- Sales/Prod drop down  -->
<div id="drop31" style="display:block">
<select name="s_or_p" class="dropdown3" id="drop31s" style="width:180px;margin:10px">
  <option value="1">SALES</option>
  <option value="2">PRODUCTION</option>
</select>
</div>
</div>


</div>

<div>

<div style="float:left;margin-left:20px; margin-top: 20px"> <!-- IDS/CDS/INDS dropdown -->
<div id="drop41" style="display:block">
<select name="accessType" class="dropdown3" id="accessType" style="width:100px;margin:10px">
  ${accessoptions}
</select>
</div>
</div>

</div>

<div style="float:left;margin-left:20px; margin-top:20px ">
  <input id="toggleRowTotal" style="font-size:x-small" type="image" src="images/table-sum-icon-32.png" name="toggleRowTotal" value="Add row total" title="Add row Total" />
  <input id="togglePercent"  style="font-size:x-small" type="image"  src="images/percent-icon-32.png" name="togglePercent" value="Add Percentages" title="Add Percentages"/>  
<%--  <div id="drop44" style="display:block">
  <a id="whenPressed" href="#">${textPrefix} download</a>
</div> --%>
</div>


 </div>
 
 
 
</div>
<!-- end of rght Titlebar -->

<div class="HeaderSelector" > <!-- START of right NAV -->

<div style="margin-top: 20px;">



<div id="tempStore" style="display:none"></div>
<div id="tempOldHeadings" style="display:none">12</div>


   <div id="dialogFilter" title="Filter" style="z-index:1500; border-style: none;  ">
             <div style="width:800px;float:left; margin-top:2%; margin-left:6%  ">
           

                <div id="dropa44" style="width:40px; float:right" >
<%-- Comment <input type="button" style="font-size:small;"  name="submit1" id="submit1"  value="Hide"/>  --%>
<input type="image" name="close" id="submit1"  src="images/deletered-32.png" />

    </div>
                          <div id="dropa44a" style="width:40px; float:right" >
<%-- Comment <input type="button" style="font-size:small;"  name="submit2" id="submit2"  value="Submit"/> --%>
<input type="image" name="close" id="submit2"  src="images/greengo-32.png" />
    </div>
          
<div id="dropa1"  style="width:230px; float:left; z-index:1500; margin-top:4px" >
<select multiple="multiple" class="dropdown11" id="drop11as" style="font-family:Arial, Helvetica, sans-serif;">
<option   class="drop11check" style="font-family:Arial, Helvetica, sans-serif;font-size:small;color:green"value="-1">Toggle clear/all &nbsp;</option>
 <c:forEach var="drop1" items="${dropdown1a}">
 <option class="drop11check"  style="font-family:Arial, Helvetica, sans-serif;font-size:small;" value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="dropa2"  style="width:230px;float:left;margin-top:4px"  >
<select multiple="multiple" class="dropdown11" id="drop12as">
<option style="font-size:small;color:green" value="-1">Toggle clear/all&nbsp;</option>
 <c:forEach var="drop1" items="${dropdown1b}">
  <option style="font-size:small;" value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>



<div id="dropa4"  style="width:230px;float:left;margin-top:4px"  >
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


<div style="margin-left:15px;text-align:top">
  <input type="image" name="filter" class="filter" id="filter"   src="images/filter-add-icon-32.png" title="Filter Results"/>
  <input type="button" style="font-size:x-small;display:none"  id="clearfilter"  value="Clear Filter"/>
  <input type="button" style="font-size:x-small;display:none" class="swap" name="swap1" id="swap1"  value="Swap cols/rows"/>
</div>

<div id="protect1" style="z-index:100;" >
<div class="idsdefault" style="float:left;width:90%;margin-right:5%;margin-top:5%;">


<fieldset class="idsBoxDefault"  style="background-color:#FFFF00;margin-left: 17px; margin-bottom:10px">
<legend style="font-size:large;"  >Header 1</legend>
<!-- <input type="button" style="font-size:x-small;" class="nosum" name="summary" id="summary" value="Summary"/>
<input type="button" style="font-size:x-small;" class="nosum" name="grpsum" id="grpsum" value="Group Sum"/>
 -->
<!-- <input type="image" src="images/sum-icon-32.png" style="font-size:x-small;" class="nosum" name="summary" id="summary" value="Summary"/>
<input type="image" src="images/sum-icon-32.png" style="font-size:x-small;" class="nosum" name="grpsum" id="grpsum" value="Group Sum"/> -->
 <!-- <br> -->
<input style="${hideIt3}" class="myrad2" type="radio" name="horiz" id="a1"  value="1" ${checked1} >${hideCountry} 
<input class="myrad2" type="radio" name="horiz" id="a2"  value="2"  ${checked2} >Product<br>
<input class="myrad2" type="radio" name="horiz" id="a3"  value="3"  >Years<br>
<input class="myrad2" type="radio" name="horiz" id="a4"  value="4"  ><label id="a44">Company</label>

</fieldset>



</div>
<div class="idsdefault"  style="${hideIt}float:left; width:90%; margin-right:5%; margin-top:15%; margin-bottom:10px">
<fieldset class="idsBoxDefault"  style="${hideIt}background-color:#FFFF00;margin-left: 17px;">
<legend style="${hideIt}font-size:large;" >Header 2</legend>
<!-- <input type="button" style="font-size:x-small;" class="nosum" name="grpsum2" id="grpsum2" value="Group Sum"/> -->
<!-- <input type="image" src="images/sum-icon-32.png" style="font-size:x-small;" class="nosum" name="grpsum2" id="grpsum2" value="Group Sum"/> -->
<!-- <br> -->
<input class="myrad3" type="radio" id="z1" name="verti" value="1"  >Country<br>
<input class="myrad3" type="radio"  id="z2" name="verti" value="2" checked >Product<br>
<input class="myrad3" type="radio"  id="z3" name="verti" value="3"  >Years<br>
<input class="myrad3" type="radio"  id="z4"  name="verti" value="4"  ><label id="z44">Company</label>



</fieldset>
</div>


</div>


</div>
</div> 
<!-- end of rght NAV -->

<div style="display:none">
<form id="accessform3" action="${ajaxPrefix}login" method="post">
   <input type="hidden" id="fromMain" name="currentAccess" value="populated" />
   <input id='submitLogin' type="submit" value="Login">
</form>
   </div>



<div id="beans" style="margin-left:1px;margin-top:18px;float:left;width:86%;height:90%;background-color:#FFFF00;">
<table id="list47"></table>
<div id="plist47"></div>
</div>
          <script type="text/javascript">
          
          
          
          
          $(function(){
        	    var dialogOpts = {
        	        modal: true,
        	        width: 900,
        	        height: 106,
        	        position: 'top',
        	        dialogClass: "noOverDialog",
        	        open: function(event,ui){
        	        	$(".noOverDialog").next("div").css({opacity:0.2});
        	        }
        	   };

        	    $("#dialogFilter").dialog(dialogOpts);


        	});
          
          var summary=0;
    	  var downloadExcel="no";
          var clickType="";
    	  var swapValue="0";
		  var myStartGridWidth=0;
 
          $(document).ready(function(){

        	  
        	  
        	  
        	  $("input:image").tooltip();
        	  
        	  $("body").removeClass("js");
        	  
        	  
        	  $("#whenPressed").on("click",function() {
         		  $("#db11").submit();
         		  return false;
         	  });
        	  
        	  var newDropdowns = "no";
        	  $("#changeFlag").val("no");
        	  

        	  $( "#two" ).on("click" , function() {
            	  $("#title1").val(  $.trim($("#accessTypeSelectBoxItText").text()) );
            	  $("#title2").val(  $.trim($("#drop31sSelectBoxItText").text()) );
            	  
            	  
            	  var myId1="";
                 	$('.showornot11').each(function (index) {
                   		if ( $(this).css("display") === "block") {
                   			myId1=$(this).attr("id");
                   		}
                    });
                 	if (myId1 != "" ){
        		          $("#title3").val( $("#"+myId1).text() );
        	        } else {
        	        	
                     	$('.showornot111').each(function (index) {
                       		if ( $(this).css("display") === "block") {
                       			myId1=$(this).attr("id");
                       		}
                        });
                     	if (myId1 != "" ){
          		          $("#title3").val( $("#"+myId1).text() );
          	        } else {
        		           myId1= $(".viewable1").attr("id");
        		           $("#title3").val( $.trim($("#"+myId1+" option:selected").text() ));
               	        }
        	        }

           	  var myId2="";
           	$('.showornot22').each(function (index) {
           		if ( $(this).css("display") === "block") {
           			myId2=$(this).attr("id");
           		}
            });
           	 if (myId2 != "" ){
      		     $("#title4").val( $("#"+myId2).text() );
      	      } else {
            	  myId2= $(".viewable2").attr("id");
            	  $("#title4").val(  $.trim($("#"+myId2+" option:selected").text() ));
      	      }  
  		    	  $("#printer").submit();
        		  return false;
        		});
        	  
        	  
        	  $( "#one" ).on("click" , function() {
            	  $("#title11").val(  $.trim($("#accessTypeSelectBoxItText").text()) );
            	  $("#title22").val(  $.trim($("#drop31sSelectBoxItText").text()) );
            	  
            	  var myId1="";
               	  var myId1="";
               	$('.showornot11').each(function (index) {
                 		if ( $(this).css("display") === "block") {
                 			myId1=$(this).attr("id");
                 		}
                  });
               	if (myId1 != "" ){
      		          $("#title33").val( $("#"+myId1).text() );
      	        } else {

               	$('.showornot111').each(function (index) {
                 		if ( $(this).css("display") === "block") {
                 			myId1=$(this).attr("id");
                 		}
                  });
               	
               	
               	
               	if (myId1 != "" ){
          		             $("#title33").val( $("#"+myId1).text() );
             	        } else {
      		           myId1= $(".viewable1").attr("id");
      		           $("#title33").val( $.trim($("#"+myId1+" option:selected").text() ));
             	        }
      	        }

         	  var myId2="";
         	 	$('.showornot22').each(function (index) {
               		if ( $(this).css("display") === "block") {
               			myId2=$(this).attr("id");
               		}
                });
               	 if (myId2 != "" ){
    		     $("#title44").val( $("#"+myId2).text() );
    	      } else {
          	  myId2= $(".viewable2").attr("id");
          	  $("#title44").val(  $.trim($("#"+myId2+" option:selected").text() ));
    	      } 
        		  $("#excel1").submit();
        		  return false;
        		});
           	  
        	  


        	  var lastSel;
        	  
        	  var j = new Date().getFullYear();
        	  j=j+20;
        	  var stringList="<option value='-1'>All</option>";
        	   for(var i = 1980; i <= j; i++) {
        		   stringList = stringList + "<option value='"+i+"'>"+i+"</option>";

        	     }
    		   $("#fromdate").append(stringList);
    		   $("#todate").append(stringList);
        	  
    		   
    		   
    		   
        	  downloadExcel="no";
        	  var rowTotal="off";
        	  var percents="off";
        	  
        	  $("#toggleRowTotal").on("click",function(){ 
        		  if ($("#list47_TOTAL").length) {
        		     rowTotal="off";
        		  } else {
        			  rowTotal="on";
        		  }
        		  getGrid();
        	  });
        	  
        	  $("#togglePercent").on("click",function(){ 
        		  if ($("#list47_PPC1").length) {
        			  percents="off";
        		  } else {
        			  percents="on";
        		  }
        		  getGrid();
        	  });
        	  
        	  $("#toExcel").on("click",function(){ 
        		  downloadExcel="yes";
        		  getGrid();
        	  });

        	  
        	  $("#drop11as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Countries...",  width:200, zIndex:100,maxDropHeight:400
         		 , onItemClick: function(checkbox, selector){ 
         			downloadExcel="no";
        		//	 getGrid();
        		 }
        	  });
        	  
        	  $("#drop12as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Products ...",  width:200, zIndex:100,maxDropHeight:400 
         		 , onItemClick: function(checkbox, selector){ 
         			downloadExcel="no";
        		//	 getGrid();
        		 }
        	  });
        	  $("#drop14as").dropdownchecklist({ firstItemChecksAll: true, emptyText: "Filter Companies ...",  width:200, zIndex:100,maxDropHeight:400
        		 , onItemClick: function(checkbox, selector){ 
        			 downloadExcel="no";
        		//	 getGrid();
        		 }
        	  
        	  });
        	
        	  
        	  
        	 
        	  
        	  $("#dialogFilter").dialog("close");
        	  

        	  
        	  $("#closeit").on("click",function(){
        		  $("body").toggleClass("wait");
        		  $.ajax({
					  url: '${ajaxPrefix}main?exit=yes',
			         type: 'GET',
			       contentType: 'application/html',
			       processData: false,
			       dataType: 'html',
			       success: function(data) {  
			    	   window.location = '${ajaxPrefix}login';
			       },
				    error: function (xhr, ajaxOptions, thrownError) {
				        alert(xhr.status);
				        alert(thrownError);
				        window.location = '${ajaxPrefix}login';
				      }

				  });
        	  });
        	  
       	  $(".dropdown1").selectBoxIt({
       		  
       		  unselectable: "off"
       	  });
        	  $(".dropdown2").selectBoxIt();
        	  $(".dropdown3").selectBoxIt();
        	  $(".dropdown33").selectBoxIt();
        	  
        	  

        	  
        	  $( "input:button" ).button();
        	  
        	  $(".dropdown1").css("width","230px");
        	  $(".dropdown2").css("width","230px");


        	clickType="";
        	swapValue="0";
        	


  
	$(".swap").on("click",function(){
		if($(this).hasClass("noswap")){
			swapValue="0"
		   $(".swap").val("Swap cols/rows");
			$(".swap").removeClass("noswap");
		} else {
			swapValue="1"
		    $(".swap").val("Clear swap");
			$(".swap").addClass("noswap");
		}
		downloadExcel="no";
		 getGrid();
	});
	
	  $("#summary").on("click",function(){
		  $('#summary').attr("disabled", true);
		  if( $(this).hasClass("nosum")){
		     summary=1;
		     $(this).val("Clear summary");
		     $(this).removeClass("nosum");
		     $(this).addClass("sum");
		     $("#a4").hide();
		    $("#a44").hide();
			  if ( $("#grpsum2").hasClass("sum")){
				  summary=4;
			  }
		  } else {
			  $(this).addClass("nosum");
		       summary=0;  
		      $(this).val("Summary");
			  $("#a4").show();
		      $("#a44").show();
			  $(this).removeClass("sum");
			  if ( $("#grpsum2").hasClass("sum")){
				  summary=3;
			  }
		  }
		  $("#grpsum").val("Group Sum");
		  $("#grpsum").removeClass("nosum");
		  $("#grpsum").removeClass("sum");
		  $("#grpsum").addClass("nosum");
		  
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  
	  $("#grpsum").on("click",function(){

		  $('#grpsum').attr("disabled", true);
		  if ( $(this).hasClass("nosum")){
		
			     summary=2;
			     $("#a4").hide();
				 $("#a44").hide();
			     $(this).val("Clr GrpSum");
			     $(this).removeClass("nosum");
			     $(this).addClass("sum");
				  if ( $("#grpsum2").hasClass("sum")){
					  summary=5;
				  }
			  } else {
		
				  $(this).addClass("nosum");
			       summary=0;  
				   if ( $("#grpsum2").hasClass("sum")){
						  summary=3;
				   }
				  $("#a4").show();
				  $("#a44").show();
			      $(this).val("Group Sum");
				  $(this).removeClass("sum");
			  }
		  $("#summary").val("Summary");
		  $("#summary").removeClass("sum");
		  $("#summary").removeClass("nosum");
		  $("#summary").addClass("nosum");
		  downloadExcel="no";
	
		  getGrid();
	
	  });
	  

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
             newDropdowns="yes";
    	     $("#changeFlag").val("yes");
                getGrid();
             
          alert("filters cleared");
          $("#clearfilter").fadeOut();
	  });
	  
	  $("#submit1").on("click",function(){
		  $("#dialogFilter").dialog("close");
	  });
	  
	  $("#submit2").on("click",function(){
          downloadExcel="no";
          newDropdowns="yes";
    	  $("#changeFlag").val("yes");
		  $("#dialogFilter").dialog("close");
		  getGrid();
	  });
	  
	  $("#grpsum2").on("click",function(){
		  $('#grpsum2').attr("disabled", true);
		  if ( $(this).hasClass("nosum")){
			     summary=3;
			     $(this).val("Clr GrpSum");
			     $(this).removeClass("nosum");
			     $(this).addClass("sum");
			     if ( $("#summary").hasClass("sum")){
					  summary=4;
			     }
			     if ( $("#grpsum").hasClass("sum")){
					  summary=5;
			     }
			  } else {
				  $(this).addClass("nosum");
			       summary=0;  
				   if ( $("#summary").hasClass("sum")){
						  summary=1;
				  }
				   if ( $("#grpsum").hasClass("sum")){
						  summary=2;
			      }
			      $(this).val("Group Sum");
				  $(this).removeClass("sum");
			  }
		  downloadExcel="no";
		  getGrid();
	  });

	  $(".dropdown33").on("change",function(){
		  
		  downloadExcel="no";
		  getGrid();
	  });
	  
	  $("#fromdate").on("change",function(){
		  downloadExcel="no";
		 // getGrid();
	  });
	  
	  $("#todate").on("change",function(){
		  downloadExcel="no";
		//  getGrid();
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
		  if ($(this).attr("id")=="accessType") {
			  var accessT = $("#accessType").val();
			  $("#fromMain").val(accessT);
			  $("#accessform3").submit();
			  return false;
		  }
		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  
	  $(".myrad2").on("change",function(){
		  $(".myrad2").prop('disabled', true);
		  $(".myrad3").prop('disabled', true);
		  clickType="myrad2";
		  if ($(".myrad2:checked").val()=="4"){

			  $(".nosum").hide();

			  
		  $(".swap").show();
	      } else {
	    	  $(".nosum").show();
	    	  if ( $(".myrad3:checked").val()=="4"){
	    		  $(".swap").show(); 
	    	  } else {
	    	     $(".swap").hide();  
	    	  }
	      }
		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  $(".myrad3").on("change",function(){
		  $(".myrad2").prop('disabled', true);
		  $(".myrad3").prop('disabled', true);
		  clickType="myrad3";

		  if ($(".myrad2:checked").val()=="4"|| 
			  $(".myrad3:checked").val()=="4"){

		  $(".swap").show();
		  if ($(".myrad2:checked").val()=="4") {
		     $(".nosum").hide();
		  } else {
			  $(".nosum").show(); 
		  }
	      } else {
	    	  $(".swap").hide();  
	    	  $(".nosum").show();
	      }
		  downloadExcel="no";
		  getGrid(); 
	  });
	  
	  
	  
	  
	  
	  function getGrid()
	  {

var myId2= $(".viewable2").attr("id");
var mydropdown2 = $("#"+myId2+" option:selected").val();

var firstPartId = $(".myrad2:checked").val();
var secondPartId = $(".myrad3:checked").val();

if (clickType=="myrad3") {
	   mydropdown2 = $("#drop2"+secondPartId+" option:selected").val();
}

var h1 = $("#tempOldHeadings").html();
var h2 = h1;
h1 = h1.substr(0,1);
h2 = h2.substr(1,1);

var myId= $(".viewable1").attr("id");

var mydropdown1 = $("#"+myId+" option:selected").val();

	if 	(firstPartId == secondPartId && clickType=="myrad3" && (summary ==0 || h2 != "4")){
		$("#"+myId).removeClass("viewable1");
		$("#drop1"+h2).addClass("viewable1");
		
		 mydropdown1 = $("#drop1"+h2+" option:selected").val();		
		  $(".myrad2").prop('checked', false);
		  $("#a"+h2).prop('checked', true);
	}




      if (clickType=="myrad2") {
           mydropdown1 = $("#drop1"+firstPartId+" option:selected").val();
       } 
if 	(firstPartId == secondPartId && clickType=="myrad2" ){
	$("#"+myId).removeClass("viewable2");
	$("#drop2"+h1).addClass("viewable2");
	 mydropdown2 = $("#drop2"+h1+" option:selected").val();
	  $(".myrad3").prop('checked', false);
	  $("#z"+h1).prop('checked', true);
}
/*
if (clickType=="myrad2" || clickType=="myrad3" ) {

	   $(".showornot1").fadeOut();
	   $(".showornot2").fadeOut();
	   $(".showornot2").removeClass("viewable2");
	   $(".showornot1").removeClass("viewable1");
}*/
/*
$(".showornot11").fadeOut();
$(".showornot111").fadeOut();
$(".showornot22").fadeOut()
*/

var my_SorP = $('#drop31s').val();
var accessType = $("#accessType").val();
$("body").toggleClass("wait");
//$("#wholescreen").fadeOut();
//$("#titleBar").fadeOut();


var dateParm=       validateDates() ;
if (dateParm=="todate must be greater or equal to fromdate") {
	 alert(dateParm);
	 $("#titleBar").fadeIn();
	 $("#wholescreen").fadeIn();
	 $("#clearfilter").fadeIn();
	 return;
}
   

		//	 $('#gbox_list47').fadeOut().promise().done(function() {  
	
				 
				 
				 
				 
				 
			//	   $('#gbox_list47').remove();
			//	   $("#beans").append("<table id='list47'></table><div id='plist47'></div>");

				   if (summary!=0  &&  summary !=3 ) {
					   if (h2=="4") {
						  if ($(".myrad3:checked").val() != "4") {
							  if ($(".myrad3:checked").val() == $(".myrad2:checked").val()){
								  if ($(".myrad2:checked").val()=="1") {
									  $("#a1").prop('checked', false);
									  $("#a2").prop('checked', true);
									  mydropdown1 = $("#drop12 option:selected").val();
								  } else {
								      if ($(".myrad2:checked").val()=="2") {
									      $(".myrad2").prop('checked', false);
									      $("#a3").prop('checked', true);
									      mydropdown1 = $("#drop13 option:selected").val();
								      } else { 
								          if ($(".myrad2:checked").val()=="3") {
									       $(".myrad2").prop('checked', false);
									       $("#a1").prop('checked', true);
									       mydropdown1 = $("#drop11 option:selected").val();
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
	   					countriesParm="&includedCountries=";
	                   }
	               
	               
					var productsParm="";
					var productsList="";

					$('input[id^="ddcl-drop12as-i"]').each(function( index ) {
						if($(this).prop("checked")){
						   productsList= productsList+$(this).val()+"|"; 
					     }
					});
					
		               if (productsList!=""){
		   					productsParm="&includedProducts=";
		                   }

						var companiesParm="";
						var companiesList="";

						$('input[id^="ddcl-drop14as-i"]').each(function( index ) {
							if($(this).prop("checked")){
								companiesList= companiesList+$(this).val()+"|"; 
						     }
						});
						
			               if (companiesList!=""){
			   					companiesParm="&includedCompanies=";
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

					  $.ajax({
					         type: 'POST',
						  url: '${ajaxPrefix}main?list=1&accessType='+accessType+'&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
				  +'&radio1='+$(".myrad2:checked").val()+'&radio2='+$(".myrad3:checked").val()+"&clickType="+clickType+
								  "&oldHead1="+h1+"&oldHead2="+h2+"&summary="+summary+"&swap="+swapValue+"&newDrops="+newDropdowns
								  +countriesParm+countriesList+productsParm+productsList+
								  fromDate+toDate+"&excelDownload="+downloadExcel+
								  "&rowTotal="+rowTotal+"&percent="+percents+dateParm,
				       data: JSON.stringify({ "includedCompanies" : companiesList }),
				       contentType: "application/x-www-form-urlencoded;charset=UTF-8", 
				       processData: false,
				       dataType: 'html',
				       success: function(data) {  

				
				    	 $("#beans").fadeOut();
				    	 

				    	$(".showornot11").fadeOut();
				    	$(".showornot111").fadeOut();
				    	$(".showornot22").fadeOut()
				    	
				    	
				    	$('#grpsum2').prop("disabled", false);
				    	  $('#grpsum').prop("disabled", false);
				    	  $('#summary').prop("disabled", false);
						  $(".myrad2").prop('disabled', false);
						  $(".myrad3").prop('disabled', false);

			
				    //	   $("#titleBar").fadeOut();
				    	   
								   $('#gbox_list47').remove();
								   $("#beans").append("<table id='list47'></table><div id='plist47'></div>");

				    	   $("#tempStore").html(data);

			
				    	   
				    	   var data2 = JSON.parse($("#tempStore #myJson").html());
				    	   
				    	   $("#printDataJson").val($("#tempStore #myJson").html());
				    	   $("#dataJson").val($("#tempStore #myJson").html());
				    	   
				    	   $("#tempOldHeadings").text($("#tempStore #myOldHeadings").html() );
				    	
				    	   if ($("#changeFlag").val()=="yes") {
				    		   $("#drop11").html($("#tempStore #drop11_IN").html());
				    		   $("#drop21").html($("#tempStore #drop21_IN").html());
				    		   
				    		   $("#drop12").html($("#tempStore #drop12_IN").html());
				    		   $("#drop22").html($("#tempStore #drop22_IN").html());
				    		   
				    		   $("#drop13").html($("#tempStore #drop13_IN").html());
				    		   $("#drop23").html($("#tempStore #drop23_IN").html());
				    		   
				    		   $("#drop14").html($("#tempStore #drop14_IN").html());
				    		   $("#drop24").html($("#tempStore #drop24_IN").html());
				    		   
				    	   }
				    	 //  newDropdowns="no";
				    	  
	    			        $(".myrad2").prop('checked', false);
		    				   $(".myrad3").prop('checked', false);
		    				   
		    				   var h1 = $("#tempOldHeadings").html();
		    				   var h2 = h1;
		    				   h1 = h1.substr(0,1);
		    				   h2 = h2.substr(1,1);
		    				   
		    				   $("#a"+h1).prop('checked', true);
		    				   $("#z"+h2).prop('checked', true);
		    				   

		    				   if (h1 != "4") {
		    					   $(".nosum").show(); 
		    				   }else {
		    					   $(".nosum").hide();  
		    				   }
		    				
					    	   if (summary==1) {
					    		   $(".showornot1").fadeOut();
					    		   $("#box1"+h1).fadeIn();
					    		   $("#box1"+h1).css("display","block");
					    	   }
					    	   if (summary==2) {
					    		   $(".showornot1").fadeOut();
					    		   $("#box3"+h1).fadeIn();
					    		   $("#box3"+h1).css("display","block");
					    	   }
					    	   
					    	   if (summary==3) {
					    		   $(".showornot2").fadeOut();
					    		   $("#box2"+h2).fadeIn();
					    		   $("#box2"+h2).css("display","block");
					    	   }
					    	   if (summary==4) {
					    		   $(".showornot2").fadeOut();
					    		   $(".showornot1").fadeOut();
					    		   $("#box2"+h2).fadeIn();
					    		   $("#box2"+h2).css("display","block");
					    		   $("#box1"+h1).fadeIn();
					    		   $("#box1"+h1).css("display","block");
					    	   }
					    	   if (summary==5) {
					    		   $(".showornot2").fadeOut();
					    		   $(".showornot1").fadeOut();
					    		   $("#box2"+h2).fadeIn();
					    		   $("#box2"+h2).css("display","block");
					    		   $("#box3"+h1).fadeIn();
					    		   $("#box3"+h1).css("display","block");
					    	   }
					    	
					    	   //HAWTHORNE
					    	   $("#titleBar").fadeOut();
						    	 if (clickType=="myrad2" || clickType=="myrad3" ) {
                                        
						    		   $(".showornot1").fadeOut();
						    		   $(".showornot2").fadeOut();
						    		   $(".showornot2").removeClass("viewable2");
						    		   $(".showornot1").removeClass("viewable1");
						    	}
						    	 
						    	 
						    	 
					    	   
		    				  var value1 =  $("#tempStore #myDropValue1").html();
		    				  var value2 =  $("#tempStore #myDropValue2").html();
		    			
		    			       $("#drop1"+h1+" option:selected").prop("selected", false);
		    				   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);

		    			    $("#drop2"+h2+" option:selected").prop("selected", false);
		    				   $("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);

		    			    $("#drop1"+h1+" option:selected").prop("selected", false);
		    				   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);

		    			 $("#drop2"+h2+" option:selected").prop("selected", false);
		    				   $("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);
		    			
   				    
		    				    
		    				   if (clickType=="myrad2"){
	  		    				   $("#drop1"+h1+" option:selected").prop("selected", false);
	  		    				   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);
		  		    				$("#drop2"+h2+" option:selected").prop("selected", false);
		  		    				$("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);

		    				   }  
		    				   

 		    				   $(".showornot1").removeClass("viewable1");
  		    				   $("#drop1"+h1).addClass("viewable1");
  		    				   $(".showornot2").removeClass("viewable2");
  		    				   $("#drop2"+h2).addClass("viewable2");

  		    			
		    				   if (clickType=="myrad3"){
	  		    				   $("#drop2"+h2+" option:selected").prop("selected", false);
	  		    				   $("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);
			  		    		   $("#drop1"+h1+" option:selected").prop("selected", false);
			  		    		   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);
		    				   }


				    		   
		    				    if (summary != 1 && summary !=4 && summary != 2 && summary !=5) {
		  		    				   $("#drop1"+h1).fadeIn();
	  		    				    }
		    				    
		    				    if (summary != 3 && summary != 4 && summary != 5) {
		  		    				   $("#drop2"+h2).fadeIn();
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


				    			        jQuery("#list47").jqGrid({
				    			        	data:mylocalData,
				    			        	datatype: "local",
				    			        	height: 350,
				    			        	rowNum: 500,
				    			           	colNames:cols,
				    			           	hidegrid:false,
				    			           	colModel:colModels3,
				    			           	//pager: "#plist47",
				    			           	viewrecords : true,
				    			           	caption: mylocalTitle,
				    			         	footerrow: true, 
				    			         	ignoreCase:true,
				    			         	userDataOnFooter: false,
				    			         	shrinkToFit:false
				    			        });
				    			        
				    			        
				    			        $("#titleBar").fadeIn();
			    						  $("#beans").fadeIn();
			    					//	  $("#titleBar").fadeIn();
			    					//	  $("#titleBar").fadeIn();
			    						  
				    			        jQuery("#list47").jqGrid('navGrid','#plist47',{edit:false,add:false,del:false,search:false,refresh:false});
									    
										
										$("#list47").jqGrid
										({
											rowList: [],        // disable page size dropdown
											pgbuttons: false,     // disable page control like next, back button
											pgtext: null,         // disable pager text like 'Page 0 of 10'
											viewrecords: false    // disable current view record text like 'View 1-10 of 100' 
										});
										
				    			  //      jQuery("#list47").jqGrid('gridResize');
				    			        
				    			 //	   $('#gbox_list47').fadeIn();
				    			//	   $('#list47').fadeIn();


				    					 var totals = JSON.parse($("#tempStore #myJsonTotals").html());
				    					 $("#totalsJson").val($("#tempStore #myJsonTotals").html());
				    				//	 $("#printDataJson").val($("#tempStore #myJsonTotals").html());
								    	    $("#printTotalJson").val($("#tempStore #myJsonTotals").html());
								    	   
				    						$("#list47").jqGrid('footerData', 'set', totals.myTotals[0]); 
				    						
				    						 $(".ui-jqgrid-titlebar").remove();
				    						 
											if( $("#list47").width() > $('#beans').width()) {
												myStartGridWidth = $("#list47").width(); 
												}
												else {
												myStartGridWidth = $('#beans').width();
												}
												
											myResizeGrid($("#list47").width());
											
											// $(window).resize(function() {
											//	myResizeGrid();
				    						//	});
				    					 
				    						 
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
				    					
						    			//        jQuery("#list47").jqGrid('setFrozenColumns');
												//  $("#titleBar").fadeIn();
												//  $("#titleBar").fadeIn();
												
				    		        		  if ($("#list47_TOTAL").length) {
					    		         		     $("#toggleRowTotal").val("Remove row total");
					    		         		    $("#toggleRowTotal").attr("title","Remove row total");
					    		         		   $("#toggleRowTotal").tooltip( "option", "content","Remove row total" );
					    		         		   
					    		         		  } else {
					    		         			  $("#toggleRowTotal").val("Add row total");
					    		         			 $("#toggleRowTotal").attr("title","Add row total");
					    		         			  $("#toggleRowTotal").tooltip( "option", "content","Add row total" );
					    		         		  }
				    		        		  
				    		        		  
				    		        		  if ($("#list47_PPC1").length) {
					    		         		     $("#togglePercent").val("Remove Percentages");
					    		         		    $("#togglePercent").attr("title","Remove Percentages");
					    		         		   $("#togglePercent").tooltip( "option", "content","Remove Percentages" );
					    		         		  } else {
					    		         			  $("#togglePercent").val("Add Percentages");
					    		         			 $("#togglePercent").attr("title","Add Percentages");
					    		         			  $("#togglePercent").tooltip( "option", "content","Add Percentages" );
					    		         	  }
				    		        		  
				    		        		  $('div[id^="jqgh_list47_PPC"]').each(function() {
				    		        			  $(this).text("%");
				    		        		  });
				    		        		  
								    		   $(".dropdown1").selectBoxIt();
								    		   $(".dropdown2").selectBoxIt();
								    		   
								    		   if ( $("#changeFlag").val()=="yes") {
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
								    		   } 
								    		   newDropdowns=="no"
								    		  $("#changeFlag").val("no");
								    			  $("body").removeClass("wait");	
								    	
					   },
					    error: function (xhr, ajaxOptions, thrownError) {
					        alert(xhr.status);
					        alert(thrownError);
					      },
					      
					      
					      complete: function () {
					    	  
					      	  $("#drop14sSelectBoxIt").css("width","270px");
					    	  $("#drop24sSelectBoxIt").css("width","270px");
					    	  
					    	  addBoldedcols();
							  
								//("#list47").children("tbody").children("tr").each( function( index ){
									//(this).children("td.titleFont").each(function (ind) {
									//$(this).css("font-weight","bold");
									//);
								//});
								 
								 $("table.ui-jqgrid-htable").children("thead").children("tr").children("th").each(function (index) {
									 $(this).css("font-weight","bold");
				          });
					    	  if( $("#summary").hasClass("sum")){
					    	     $('td[aria-describedby=list47_year]').css( 'cursor','pointer');
					    	     $('td[aria-describedby=list47_product]').css( 'cursor','pointer');
					    	     $('td[aria-describedby=list47_country]').css( 'cursor','pointer');
					    	     
					    	     $('td[aria-describedby=list47_year]').addClass( 'fontAnchorLook');
					    	     $('td[aria-describedby=list47_product]').addClass( 'fontAnchorLook');
					    	     $('td[aria-describedby=list47_country]').addClass( 'fontAnchorLook');
					    	  }
					    	  

		    			  $('td[aria-describedby=list47_country]').on("click",function() {
		    				  if( $("#summary").hasClass("sum")){
		    					  
		    					  $("#summary").removeClass("sum");
		    					  $("#summary").addClass("nosum");
		    					  $("#summary").val("Summary");
		    					  $("#grpsum2").removeClass("sum");
		    					  $("#grpsum2").addClass("nosum");
		    					  $("#grpsum2").val("Group Sum");
		    					  $("#box11").addClass("showornot11");
		    					  $("#box11").css("display","none");
		    					  $("#drop11").addClass("viewable1");
		    					  $("#drop11").css("display","block");
		    					  
		    					  var ss = $(this).html();
		    					  ss= ss+String.fromCharCode(160);		    					  
		    					  $("#drop11s")[0].selectedIndex = -1;
		    					  var myVal="";
		    					  $("#drop11s option").each(function(idx){
	    						     if ($(this).text() == ss)
	    						     {
	    						    	 myVal= $(this).val();
		    						      $(this).prop("selected", "selected");
		    						      $(this).attr("selected", "selected");
		    						      $("#drop11s")[0].selectedIndex = idx;
		    						  }
		    					  });

		    					  summary=0;
		    					  clickType= "drop11";
		    					  downloadExcel="no";

		    					 var myTitle =  $("#drop11:nth-child(1)").children("span").children("span:nth-child(1)").children("span:eq(1)");
		    					 var bb = myTitle.html();
		    				     bb= ss;
		    					 myTitle.html(bb);
		    					 getGrid();
		    				  }

		    			  });
		    			  
		    			  
		    			  $('td[aria-describedby=list47_year]').on("click",function() {
		    				  if( $("#summary").hasClass("sum")){
		    					  
		    					  $("#summary").removeClass("sum");
		    					  $("#summary").addClass("nosum");
		    					  $("#summary").val("Summary");
		    					  $("#grpsum2").removeClass("sum");
		    					  $("#grpsum2").addClass("nosum");
		    					  $("#grpsum2").val("Group Sum");
		    					  $("#box13").addClass("showornot11");
		    					  $("#box13").css("display","none");
		    					  $("#drop13").addClass("viewable1");
		    					  $("#drop13").css("display","block");
		    					  
		    					  var ss = $(this).html();
		    					  ss= ss+String.fromCharCode(160);		    					  
		    					  $("#drop13s")[0].selectedIndex = -1;
		    					  var myVal="";
		    		
		    					  $("#drop13s option").each(function(idx){
	    						     if ($(this).text() == ss)
	    						     {
	    						    	 myVal= $(this).val();
		    						      $(this).prop("selected", "selected");
		    						      $(this).attr("selected", "selected");
		    						      $("#drop13s")[0].selectedIndex = idx;
		    						  }
		    					  });

		    					  summary=0;
		    					  clickType= "drop13";
		    					  downloadExcel="no";
		    					  


		    					 $("#drop13").children("span:nth-child(2)").children("span:nth-child(1)").children("span:nth-child(2)").html(ss);

		    					 
		    					 getGrid();
		    				  }
		    			  });
		    			  
		    			  
		    			  
		    			  
		    			  $('td[aria-describedby=list47_product]').on("click",function() {
		    				  if( $("#summary").hasClass("sum")){
		    					  
		    					  $("#summary").removeClass("sum");
		    					  $("#summary").addClass("nosum");
		    					  $("#summary").val("Summary");
		    					  $("#grpsum2").removeClass("sum");
		    					  $("#grpsum2").addClass("nosum");
		    					  $("#grpsum2").val("Group Sum");
		    					  $("#box12").addClass("showornot11");
		    					  $("#box12").css("display","none");
		    					  $("#drop12").addClass("viewable1");
		    					  $("#drop12").css("display","block");
		    					  
		    					  var ss = $(this).html();
		    					  ss= ss+String.fromCharCode(160);		    					  
		    					  $("#drop12s")[0].selectedIndex = -1;
		    					  var myVal="";
		    					  $("#drop12s option").each(function(idx){
	    						     if ($(this).text() == ss)
	    						     {
	    						    	 myVal= $(this).val();
		    						      $(this).prop("selected", "selected");
		    						      $(this).attr("selected", "selected");
		    						      $("#drop12s")[0].selectedIndex = idx;
		    						  }
		    					  });

		    					  summary=0;
		    					  clickType= "drop12";
		    					  downloadExcel="no";

		    					//  var myTitle =  $("#drop12").children("span:nth-child(2)").children("span:nth-child(1)").children("span:nth-child(2)").html();
		    					// var myTitle =  $("#drop12:nth-child(1)").children("span").children("span:nth-child(1)").children("span:eq(1)");
		    					// var bb = myTitle.html();
		    				   //  bb= ss;
		    					// myTitle.html(bb);
		    					 
		    					 $("#drop12").children("span:nth-child(2)").children("span:nth-child(1)").children("span:nth-child(2)").html(ss);
		    					 
		    					 getGrid();
		    				  }
		    			  });
		    			  
		    				 
		    				 var allC = $("#list47").children("tbody").children("tr:nth-child(2)").children("td:nth-child(1)").html();
		    		    	  if(allC==" OTHERS") {

		    		    		  var newFooterRow1 =$("#list47").children("tbody").children("tr:nth-child(2)").clone();

		    		    		  newFooterRow1.children("td:nth-child(1)").html("OTHERS");
		    		    		  newFooterRow1.children("td").each(function (ind) {
		    		    			  //$(this).addClass("titleFont");

		    		       	            var myWidth =  $("#list47").children("tbody").children("tr:nth-child(2)").children("td:nth-child("+(ind +1)+")").width();

		    		        	          var myBack =  $("#list47").children("tbody").children("tr:nth-child(2)").children("td:nth-child("+(ind +1)+")").css("background-color");

		    		        	            
		    		        	          
		    		       	         $(this).css("background-color",myBack);
		    		                      $(this).removeClass("colorWhite");
		    		                      $(this).css("font-weight","bold");
		    		                      $(this).css("overflow","hidden");
		    		                      $(this).css("white-space","pre");
		    		                     // $(this).css("height","22px");
		    		                     $(this).css("padding","2px");
		    		                //     $(this).css(" border-bottom-width","1px");
		    		                     $(this).css(" border","inherit");
		    		                 //   $(this).css(" border-bottom-style","solid");
		    		                    $(this).css("border-top-style","none");
		    		                    if (ind!=0) {
		    			                      $(this).css("border-left-style","none"); 
		    			                    }
		    		                 //   $(this).css("border-right-style","none"); 
                                        $(this).css("border-color","grey");
		    		                    $(this).css("width",myWidth+"px");
		    		                  
		    		       	        });
		    		    			  
		    		    			  
		    		    			  
		    		    		  newFooterRow1.insertBefore($("tr.footrow"));
		    		    		//  $("#list47").children("tbody").children("tr:nth-child(1)").css("display","none");
		    		    		  $("#list47").children("tbody").children("tr:nth-child(2)").css("display","none");
		    		    		  
		    		     		  newFooterRow1.addClass("footrow");
		    		    		  newFooterRow1.addClass("footrow-ltr");
		    		    		  newFooterRow1.removeClass("ui-widget-content");
		    		    		  newFooterRow1.removeClass("jqgrow ui-row-ltr");

								  
		    		      	      $("th").on("click",function(){
		    		                	//$( "td[title=' OTHERS']" ).parent().remove();
										
										var idSearchValue = " OTHERS";
										var cm = jQuery("#list47").jqGrid("getGridParam", "colModel");
										var colName = cm[0].name;
										
										var rowIds = $("#list47").jqGrid('getDataIDs');
										
										for (i = 1; i <= rowIds.length; i++) {
										rowData = $("#list47").jqGrid('getRowData', i);

											if (rowData[colName] == idSearchValue ) {
								
											 //var xx = $("#list47").children("tbody").children("tr:nth-child("+i+")");
											 $("#list47").children("tbody").children("tr:nth-child("+(i+1)+")").remove();
											 break;
											} //if
										} //for
								   	addBoldedcols();
		    		               });
						   
		    		    		  
		    		    	  }  
		    			  
		    			  

					      }
					     
					  });
				   
				   
				   
				   
		//	 });





  
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
      	rowNum: 500,
         	colNames:cols,
         	colModel:colModels3,
         	//pager: "#plist47",
         	viewrecords: true,
         	caption: mylocalTitle,
         	hidegrid:false,
         	footerrow: true, 
         	ignoreCase:true,
         	shrinkToFit: false,
         	userDataOnFooter: false
      });
     
      jQuery("#list47").jqGrid('navGrid','#plist47',{edit:false,add:false,del:false,search:false,refresh:false});
	  
	  $("#list47").jqGrid
		({
			rowList: [],        // disable page size dropdown
			pgbuttons: false,     // disable page control like next, back button
			pgtext: null,         // disable pager text like 'Page 0 of 10'
			iewrecords: false    // disable current view record text like 'View 1-10 of 100' 
		});
	  
      jQuery("#list47").jqGrid('gridResize');
      
      $("#filter").on("click",function(){
    	  
    	  $("#dialogFilter").dialog("open");
    	  
    	  $("#dialogFilter").removeClass("ui-dialog-content");
    	  $(".ui-dropdownchecklist-text").css("font-weight","lighter");
    	  $(".ui-widget-header").css("background","#FFFF00");
    	  $(".ui-widget-header").css("color","black");
    	  $(".ui-widget-header").css("border","1px solid black");
    	  $(".ui-widget-header").css("font-weight","normal");
  
      });

 
    //  $("#wholescreen").fadeIn();
      
	   $('#gbox_list47').fadeIn();
	   $('#list47').fadeIn();
	
			
			$("#list47").jqGrid('footerData', 'set', 
					totals.myTotals[0]); 
			
			 $('.footrow').children("td").removeClass("colorWhite"); 

			 
			 $(".ui-jqgrid-titlebar").remove();

			 //myStartGridWidth = $("#list47").width();
			if( $("#list47").width() > $('#beans').width()) {
				myStartGridWidth = $("#list47").width(); 
			}
			else {
				myStartGridWidth = $('#beans').width();
			}
			 
			 myResizeGrid($("#list47").width()); 
			 addBoldedcols();

		//$("#list47").children("tbody").children("tr").each( function( index ){
			//$(this).children("td.titleFont").each(function (ind) {
				//$(this).css("font-weight","bold");
			//});
		//});
			 
			 $("table.ui-jqgrid-htable").children("thead").children("tr").children("th").each(function (index) {
					 $(this).css("font-weight","bold");
          });
			 
			 var allC = $("#list47").children("tbody").children("tr:nth-child(2)").children("td:nth-child(1)").html();
	    	if(allC==" OTHERS") {

	    		  var newFooterRow1 =$("#list47").children("tbody").children("tr:nth-child(2)").clone();

	    		  newFooterRow1.children("td:nth-child(1)").html("OTHERS");
	    		  newFooterRow1.children("td").each(function (ind) {
	    			  //$(this).addClass("titleFont");

	       	            var myWidth =  $("#list47").children("tbody").children("tr:nth-child(2)").children("td:nth-child("+(ind +1)+")").width();

	        	          var myBack =  $("#list47").children("tbody").children("tr:nth-child(2)").children("td:nth-child("+(ind +1)+")").css("background-color");
 
	       	         $(this).css("background-color",myBack);
	                      $(this).removeClass("colorWhite");
	                      $(this).css("font-weight","bold");
	                      $(this).css("overflow","hidden");
	                      $(this).css("white-space","pre");
	                   //   $(this).css("height","22px");
	                     $(this).css("padding","2px");
	                   //  $(this).css(" border-bottom-width","1px");
	                  //   $(this).css(" border","inherit");
		                    $(this).css("border-top-style","none");
		                    if (ind!=0) {
		                      $(this).css("border-left-style","none"); 
		                    }
		                    $(this).css("border-color","grey");
		              //      $(this).css("border-right-style","none"); 
	                   // $(this).css(" border-bottom-style","solid");

	                    $(this).css("width",myWidth+"px");
	                  
	       	        });
	    			  

	    		  newFooterRow1.insertBefore($("tr.footrow"));
	    		  $("#list47").children("tbody").children("tr:nth-child(2)").css("display","none");

		    		  
	     		  newFooterRow1.addClass("footrow");
	    		  newFooterRow1.addClass("footrow-ltr");
	    		  newFooterRow1.removeClass("ui-widget-content");
	    		  newFooterRow1.removeClass("jqgrow ui-row-ltr");
	
	    	    //  jQuery("#list47").jqGrid('setFrozenColumns'); 
	    		  
			} //if(allC==" OTHERS") 		 
		
	    		  
	      	      $("th").on("click",function(){
	                	//$( "td[title=' OTHERS']" ).parent().remove();
							var idSearchValue = " OTHERS";
							var cm = jQuery("#list47").jqGrid("getGridParam", "colModel");
							var colName = cm[0].name;
										
							var rowIds = $("#list47").jqGrid('getDataIDs');
										
							for (i = 1; i <= rowIds.length; i++) {
									rowData = $("#list47").jqGrid('getRowData', i);

									if (rowData[colName] == idSearchValue ) {
									//var xx = $("#list47").children("tbody").children("tr:nth-child("+(i+1)+")");
									 $("#list47").children("tbody").children("tr:nth-child("+(i+1)+")").remove();
									break;
								} //if
							} //for
					addBoldedcols();		
									
	               });  //$("th").on("click",function(){
			 
			  $('.footrow').children('td').each(function( index ) {


				  if ( $(this).text()==" " || $(this).text()=='\xa0'){
					  $(this).text("0"); 
				  }
				  
				});

				// $('#list47').setGridWidth($('#beans').width());
				if($("#list47").width() < $('#beans').width()) {
					$('#list47').setGridWidth($('#beans').width(),true);  //turn on shrink if columns less than the window. 
				} else {
					$('#list47').setGridWidth($('#beans').width(),false);  
				}
				
				$(window).resize(function() {
					myResizeGrid(myStartGridWidth)	
				});	

				/*
				
				 $(window).resize(function() {
					// $('#list47').setGridWidth($('#beans').width());
					 if($("#list47").width() < $('#beans').width()) {
						$('#list47').setGridWidth($('#beans').width(),true);  //turn on shrink if columns less than the window. 
						} else {
						$('#list47').setGridWidth($('#beans').width(),false);  
					}
					 var width1 = $("#list47").children("tbody").children("tr:nth-child(4)").children("td:nth-child(1)").width();
					 var width2 = $("#list47").children("tbody").children("tr:nth-child(4)").children("td:nth-child(2)").width();
					 var width3 = $("#list47").children("tbody").children("tr:nth-child(4)").children("td:last").width();
					 var length1 =  $('.footrow:nth-child(1)').children('td').length;
					 
					 $('.footrow:nth-child(1)').children('td').each(function( index ) {
						 
						 if (width1 !=0) {
						      if (index==0) {
                                 $(this).css("width",width1+"px");
						      }else {
						    	  
						    	  $(this).css("width",width2+"px");  
						      }
						      if ((index+1)==length1) {
						    	  $(this).css("width",width3+"px");  
						      }
						 }
						  
						});
					 
					 
					 $('.footrow:nth-child(2)').children('td').each(function( index ) {
						 
						 if (width1 !=0) {
						      if (index==0) {
                                 $(this).css("width",width1+"px");
						      }else {
						    	  
						    	  $(this).css("width",width2+"px");  
						      }
						      if ((index+1)==length1) {
						    	  $(this).css("width",width3+"px");  
						      }
						 }
						  
						});
					 
					 
					 
					});
					*/
					
				function addBoldedcols() {
					
					 $("#list47").children("tbody").children("tr").each( function( index ){
					$(this).children("td.titleFont").each(function (ind) {
					 $(this).css("font-weight","bold");
					});
					});
					
				}

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
		     	 
			function myResizeGrid(myStartGridWidth) { 
		 	//if($("#list47").width() < $('#beans').width()) {
            if( myStartGridWidth < $('#beans').width()) {
				//				var myStartGridWidth = $("#list47").width();				
		 		$('#list47').setGridWidth($('#beans').width(),true);  //turn on shrink if columns less than the window. 
					} else {
					$('#list47').setGridWidth($('#beans').width(),false);  
					}
					var winHeight = window.innerHeight;
					wHeight = winHeight - 230;
										
					if(wHeight < 110){  //Height of five rows in grid is 110 pixels.
						wHeight = 110;
						$('#list47').jqGrid('setGridHeight',wHeight);
					}
					else {
						$('#list47').jqGrid('setGridHeight',wHeight);
					}
					
					$("table.ui-jqgrid-ftable").children("tr").children("td").each(function (ind) {
	       	            var myWidth =  $("#list47").children("tbody").children("tr:nth-child(2)").children("td:nth-child("+(ind +1)+")").width();
	                    $(this).css("width",myWidth+"px");
	                  
	       	        });
	
	}	
  });
  


		
	</script>
	
	


</div>

</body>



</html>
