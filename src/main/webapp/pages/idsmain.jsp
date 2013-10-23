<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ids.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.dropdownchecklist.themeroller.css" />

 <link rel="stylesheet" type="text/css" media="screen" href="css/jquery.selectboxit.css" />
          
<link rel="stylesheet" type="text/css" media="screen" href="css/IDS-YELLOW/jquery-ui-1.10.3.custom.min.css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/ui.dropdownchecklist-1.4-min.js"></script>

          <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.selectboxit/3.8.0/jquery.selectBoxIt.min.js"></script>

<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>
<title>IDS</title>
<style>
body { 
background-color:#FFFF00; 
}
body.wait, body.wait *{
 cursor: wait !important;   
}
</style>

</head>

<body class="jss">
<div id="wholescreen" style="width:100%;height:100%">

<div class="box">
<span class="IDSheader"> 
<img class="leftLogo" src="images/IDS-Logo.png" width="50px" height="50px">
International Database Service 
<img class="rightLogo" src="images/OHR-logo.png"  >
</span>
</div>

<input type="hidden" id="changeFlag" value="no"/>

<div style="float:right;width:18%;background-color:#FFFF00;height:100%;">
<div>

<div id="tempStore" style="display:none"></div>
<div id="tempOldHeadings" style="display:none">12</div>


   <div id="dialogFilter" title="Filter" style="z-index:1500">
             <div style="width:900px;float:left">
           

                <div id="dropa44" style="width:90px; float:left" >
<input type="button" style="font-size:small;"  name="submit1" id="submit1"  value="Hide"/>
    </div>
                          <div id="dropa44a" style="width:90px; float:left" >
<input type="button" style="font-size:small;"  name="submit2" id="submit2"  value="Submit"/>
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


<div style="margin-left:15px;text-align:center"><br><br>
  <input type="image" name="filter" class="filter" id="filter"   src="images/filter-add-icon-32.png" />
  <input type="button" style="font-size:x-small;display:none"  id="clearfilter"  value="Clear Filter"/>
  <input type="button" style="font-size:x-small;display:none" class="swap" name="swap1" id="swap1"  value="Swap cols/rows"/>
</div>
<div class="idsdefault" style="float:left;width:90%;margin-right:5%;margin-top:5%;">
<fieldset class="idsdefault"  style="background-color:#FFFF00;margin-left: 10px;">
<legend style="font-size:medium;"  >Header 1</legend>
<input type="button" style="font-size:x-small;" class="nosum" name="summary" id="summary" value="Summary"/>
<input type="button" style="font-size:x-small;" class="nosum" name="grpsum" id="grpsum" value="Group Sum"/><br>
<input class="myrad2" type="radio" name="horiz" id="a1"  value="1" checked >Country<br>
<input class="myrad2" type="radio" name="horiz" id="a2"  value="2"  >Product<br>
<input class="myrad2" type="radio" name="horiz" id="a3"  value="3"  >Years<br>
<input class="myrad2" type="radio" name="horiz" id="a4"  value="4"  ><label id="a44">Company</label>

</fieldset>
</div>
<div class="idsdefault"  style="float:left;width:90%;margin-right:5%;margin-top:15%">
<fieldset class="idsdefault"  style="background-color:#FFFF00;margin-left: 10px;">
<legend style="font-size:medium;" >Header 2</legend>
<input type="button" style="font-size:x-small;" class="nosum" name="grpsum2" id="grpsum2" value="Group Sum"/><br>
<input class="myrad3" type="radio" id="z1" name="verti" value="1"  >Country<br>
<input class="myrad3" type="radio"  id="z2" name="verti" value="2" checked >Product<br>
<input class="myrad3" type="radio"  id="z3" name="verti" value="3"  >Years<br>
<input class="myrad3" type="radio"  id="z4"  name="verti" value="4"  ><label id="z44">Company</label>



</fieldset>
</div>


</div>

</div>



<div id="titleBar" style="float:left;width:82%;height:40%;padding-top:1%;margin-top:5px">
<div style="float:left; margin-left:5px;">


<form  id="excel1" action="/cron/down" method="post" name="factsForm1"   > 
 <input id="dataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="totalsJson"  type="hidden" name="jsonTotals" value="" />
      <input id="title11" type="hidden" name="title1" value=""/>
  <input id="title22" type="hidden" name="title2" value=""/>
  <input id="title33" type="hidden" name="title3" value=""/>
  <input id="title44" type="hidden" name="title4" value=""/>
</form>

<form  target="_blank"  id="printer" action="/print" method="post" name="factsForm2"   > 
 <input id="printDataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="printTotalJson"  type="hidden" name="jsonTotals" value="" />
    <input id="title1" type="hidden" name="title1" value=""/>
  <input id="title2" type="hidden" name="title2" value=""/>
  <input id="title3" type="hidden" name="title3" value=""/>
  <input id="title4" type="hidden" name="title4" value=""/>
</form>

  <input id="two"  style="font-size:x-small" type="image" src="images/print-icon-32.png"  name="two" value="Print Preview" />
  <input id="one" style="font-size:x-small" type="image" src="images/table-excel-icon-32.png" name="submitBtn" value="Download Excel" />
  <input id="toggleRowTotal" style="font-size:x-small" type="image" src="images/table-sum-icon-32.png" name="toggleRowTotal" value="Remove row total" />
  <input id="togglePercent"  style="font-size:x-small" type="image"  src="images/percent-icon-32.png" name="togglePercent" value="Add Percentages" />  
  <input type="image" name="close" id="closeit"  src="images/deletered-32.png" />

</div>







<div style="text-align:center;float:none">
<div style="margin-left:10%;float:left">



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
<div style="float:left;margin-left:20px">

<div id="drop41" style="display:block">
<select name="accessType" class="dropdown3" id="accessType" style="width:180px;margin:10px">
  ${accessoptions}
</select>
</div>
</div>


 </div>
 
 
 
</div>
<div style="display:none">
<form id="accessform3" action="/login" method="post">
   <input type="hidden" id="fromMain" name="currentAccess" value="populated" />
   <input id='submitLogin' type="submit" value="Login">
</form>
   </div>



<div id="beans" style="margin-left:5px;margin-top:18px;float:left;width:81%;height:90%;background-color:#FFFF00;">
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
          
          $(document).ready(function(){

        	  var newDropdowns = "no";
        	  $("#changeFlag").val("no");

        	  $( "#two" ).on("click" , function() {
            	  $("#title1").val(  $.trim($("#accessTypeSelectBoxItText").text()) );
            	  $("#title2").val(  $.trim($("#drop31sSelectBoxItText").text()) );
            	  
            	  
            	  var myId1="";
                 	if ($('.showornot11').filter(function (index) {
                 		myId1=$(this).attr("id");
                      return $(this).css("display") === "block";
                         }).length) {
        		          $("#title3").val( $("#"+myId1).text() );
        	        } else {
        	        	
        	        	if ($('.showornot111').filter(function (index) {
                     		myId1=$(this).attr("id");
                          return $(this).css("display") === "block";
                             }).length) {
            		             $("#title3").val( $("#"+myId1).text() );
               	        } else {
        		           myId1= $(".viewable1").attr("id");
        		           $("#title3").val( $.trim($("#"+myId1+" option:selected").text() ));
               	        }
        	        }

           	  var myId2="";
           	if ($('.showornot22').filter(function (index) {
           		myId2=$(this).attr("id");
                return $(this).css("display") === "block";
            }).length) {
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
               	if ($('.showornot11').filter(function (index) {
               		myId1=$(this).attr("id");
                    return $(this).css("display") === "block";
                       }).length) {
      		          $("#title33").val( $("#"+myId1).text() );
      	        } else {
      	        	
      	        	if ($('.showornot111').filter(function (index) {
                   		myId1=$(this).attr("id");
                        return $(this).css("display") === "block";
                           }).length) {
          		             $("#title33").val( $("#"+myId1).text() );
             	        } else {
      		           myId1= $(".viewable1").attr("id");
      		           $("#title33").val( $.trim($("#"+myId1+" option:selected").text() ));
             	        }
      	        }

         	  var myId2="";
         	if ($('.showornot22').filter(function (index) {
         		myId2=$(this).attr("id");
              return $(this).css("display") === "block";
          }).length) {
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
        	  
    		   
    		   
    		   
        	  var downloadExcel="no";
        	  var rowTotal="on";
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
        	var swapValue="0";
        	var summary=0;
        	
        //	$("body").removeClass("js");

  
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
          getGrid();
		  $("#dialogFilter").dialog("close");
	  });
	  
	  $("#grpsum2").on("click",function(){
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

$(".showornot11").fadeOut();
$(".showornot111").fadeOut();
$(".showornot22").fadeOut()

var my_SorP = $('#drop31s').val();
var accessType = $("#accessType").val();
$("body").toggleClass("wait");
//$("#wholescreen").fadeOut();
//$("#titleBar").fadeOut();


var dateParm=       validateDates() ;
if (dateParm=="todate must be greater or equal to fromdate") {
	 $("body").removeClass("wait");
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
						  url: '/main?list=1&accessType='+accessType+'&pors='+my_SorP+'&dropdown1='+mydropdown1+'&dropdown2='+mydropdown2
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
				    	
				    	
				    	
				    	
				    	 
				    //	   $("#titleBar").fadeOut();
				    	   
								   $('#gbox_list47').remove();
								   $("#beans").append("<table id='list47'></table><div id='plist47'></div>");
;
				    	   
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
		    				   
		 /*   				   

if (clickType=="drop11s") {
       $("#drop1"+h1+" option:selected").prop("selected", false);
	   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);
 }
if (clickType=="drop21s") {
    $("#drop2"+h2+" option:selected").prop("selected", false);
	   $("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);
}
if (clickType=="drop12s") {
    $("#drop1"+h1+" option:selected").prop("selected", false);
	   $("#drop1"+h1+" option[value="+value1+"]").prop("selected", true);
}
if (clickType=="drop22s") {
 $("#drop2"+h2+" option:selected").prop("selected", false);
	   $("#drop2"+h2+" option[value="+value2+"]").prop("selected", true);
}
		*/    				    
		    				    
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
				    			         	userDataOnFooter: true
				    			        });
				    			        


				    			        
				    			        
				    			        
				    			        $("#titleBar").fadeIn();
			    						  $("#beans").fadeIn();
			    					//	  $("#titleBar").fadeIn();
			    					//	  $("#titleBar").fadeIn();
			    						  
				    			        jQuery("#list47").jqGrid('navGrid','#plist47',{edit:false,add:false,del:false});

				    			        jQuery("#list47").jqGrid('gridResize');
				    			        
				    			 //	   $('#gbox_list47').fadeIn();
				    			//	   $('#list47').fadeIn();


				    					 var totals = JSON.parse($("#tempStore #myJsonTotals").html());
				    					 $("#totalsJson").val($("#tempStore #myJsonTotals").html());
				    				//	 $("#printDataJson").val($("#tempStore #myJsonTotals").html());
								    	    $("#printTotalJson").val($("#tempStore #myJsonTotals").html());
								    	   
				    					 
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
				    						  

				    						//  $("#titleBar").fadeIn();
				    		        		  if ($("#list47_TOTAL").length) {
					    		         		     $("#toggleRowTotal").val("Remove row total");
					    		         		  } else {
					    		         			  $("#toggleRowTotal").val("Add row total");
					    		         		  }
				    		        		  
				    		        		  
				    		        		  if ($("#list47_PPC1").length) {
					    		         		     $("#togglePercent").val("Remove Percentages");
					    		         		  } else {
					    		         			  $("#togglePercent").val("Add Percentages");
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
      
      jQuery("#list47").jqGrid('navGrid','#plist47',{edit:false,add:false,del:false});
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
