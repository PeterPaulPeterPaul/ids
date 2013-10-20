<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" type="text/css" media="screen" href="css/chris.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.dropdownchecklist.themeroller.css" />

          <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.selectboxit/3.6.0/jquery.selectBoxIt.css" />

<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/ui.dropdownchecklist-1.4-min.js"></script>

          <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.selectboxit/3.6.0/jquery.selectBoxIt.min.js"></script>

<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>

         <script type="text/javascript" src="js/idsEdit.js"></script>
<script type="text/javascript">

function parm1JsonTotal() {
    return ${jsonTotal};
}
function parm2firstTimeFromServer() {
	return ${firstTimeFromServer};
}
function parm3openOrClose() {
    return '${openOrClose}';
}
function parm4openOrClose() {
	return '${openOrClose2}';
}
function parm5AjaxPrefix() {
	return '${ajaxPrefix}';
}

</script>


<title>IDS</title>
<style>
body { 
background-color:#FFFF00; 
}
body.wait, body.wait *{
 cursor: wait !important;   
}
body.wait, body.wait *{
 cursor: wait !important;   
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


<div style="float:right;width:18%;background-color:#FFFF00;height:100%;">
<div>

<div id="tempStore" style="display:none"></div>
<div id="tempOldHeadings" style="display:none">12</div>



   <div id="dialogFilter" title="Filter" style="z-index:1500">
             <div style="width:800px;float:left">
             
 
                <div id="dropa44" style="width:90px; float:left" >
<input type="button" style="font-size:small;"  name="submit1" id="submit1"  value="Hide"/>
    </div>
          
<div id="dropa1"  style="width:230px; float:left; z-index:1500;margin-top:4px"  >
<select multiple="multiple" class="dropdown11" id="drop11as" style="font-family:Arial, Helvetica, sans-serif;">
<option   class="drop11check" style="font-family:Arial, Helvetica, sans-serif;font-size:small;color:green"value="-1">Toggle clear/all &nbsp;</option>
 <c:forEach var="drop1" items="${dropdown1a}">
 <option class="drop11check"  style="font-family:Arial, Helvetica, sans-serif;font-size:small;" value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="dropa2"  style="width:230px;float:left;margin-top:4px"   >
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
  <input type="image" name="filter" class="filter" id="filter"   src="images/filter.png" />
   <input type="button" style="font-size:x-small;display:none"  id="clearfilter"  value="Clear Filter"/>
     <input type="button" style="font-size:x-small;display:none" class="swap" name="swap1" id="swap1"  value="Swap cols/rows"/>
</div>
<div style="float:left;width:90%;margin-right:5%;margin-top:5%;">
<fieldset style="background-color:#FFFF00;margin-left: 10px;">
<legend>Header 1</legend>
<input class="myrad2" type="radio" name="horiz" id="a1"  value="1" checked >Country<br>
<input class="myrad2" type="radio" name="horiz" id="a2"  value="2"  >Product<br>
<input class="myrad2" type="radio" name="horiz" id="a3"  value="3"  >Years<br>
<input class="myrad2" type="radio" name="horiz" id="a4"  value="4"  ><label id="a44">Company</label>

</fieldset>
</div>
<div style="float:left;width:90%;margin-right:5%;margin-top:15%">
<fieldset style="background-color:#FFFF00;margin-left: 10px;">
<legend>Header 2</legend>
<input class="myrad3" type="radio" id="z1" name="verti" value="1"  >Country<br>
<input class="myrad3" type="radio"  id="z2" name="verti" value="2" checked >Product<br>
<input class="myrad3" type="radio"  id="z3" name="verti" value="3"  >Years<br>
<input class="myrad3" type="radio"  id="z4"  name="verti" value="4"  ><label id="z44">Company</label>



</fieldset>
</div>


</div>

</div>

<div id="myDimensionHidden" style="display:none">Year</div>

<div id="titleBar" style="float:left;width:82%;height:40%;padding-top:1%;">
<div style="float:left;">


<form  id="excel1" action="/cron/down" method="post" name="factsForm"   > 
 <input id="dataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="totalsJson"  type="hidden" name="jsonTotals" value="" />
    <input id="title11" type="hidden" name="title1" value=""/>
  <input id="title22" type="hidden" name="title2" value=""/>
  <input id="title33" type="hidden" name="title3" value=""/>
  <input id="title44" type="hidden" name="title4" value=""/>


</form>

<form  target="_blank"  id="printer" action="/print" method="post" name="factsForm"   > 
 <input id="printDataJson"  type="hidden" name="jsonStuff" value="" />
  <input id="printTotalJson"  type="hidden" name="jsonTotals" value="" />
  <input id="title1" type="hidden" name="title1" value=""/>
  <input id="title2" type="hidden" name="title2" value=""/>
  <input id="title3" type="hidden" name="title3" value=""/>
  <input id="title4" type="hidden" name="title4" value=""/>


</form>

 <input id="one" style="font-size:x-small" type="button" name="submitBtn" value="Download Excel" />
  <input id="two"  style="font-size:x-small" type="button" name="two" value="Print Preview" /><br>
 <input id="toggleRowTotal"  style="font-size:x-small" type="button" name="toggleRowTotal" value="Add row total" />

<input type="image" name="close" id="closeit"  src="images/exit.bmp" />




</div>







<div style="text-align:center;float:none">
<div style="margin-left:12%;float:left">



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

<div style="float:left;margin-left:15px">

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


   
   <div style="float:left;margin-left:15px">

<div id="drop31" style="display:block">
<select name="s_or_p" class="dropdown3" id="drop31s" style="width:180px;margin:10px">
  <option value="1">Sales</option>
  <option value="2">Production</option>
</select>
</div>


</div>
<div style="float:left;margin-left:15px">

<div id="drop41" style="display:block">
<select name="accessType" class="dropdown3" id="accessType" style="width:180px;margin:10px">
  ${accessoptions}
</select>
</div>
</div>


<div id="saveButId" style="float:left;margin-left:20px;display:${saveBut}">
<div  style="display:block">
<form  id="saving" action="/saverow" method="get" name="saveForm"   > 
 <input  type="hidden" name="save" value="" />
          <input id="twosub" style="background-color:red" class="k-button" type="submit" name="submitBtn" value="Save to PRODUCTION database" />

</form>
</div>
</div>

<div style="float:left;margin-left:5px">
<div  style="display:block">
     <input id="addsub" class="k-button" type="submit" name="submitBtn" value="Add new Row" />
</div>
</div>
<div style="float:left;margin-left:5px">
<div  style="display:block">
     <input id="delsub" class="k-button" type="submit" name="submitBtn" value="Delete row" />
</div>
</div>

<div style="float:left;margin-left:5px">
<div  style="display:block">
     <input id="addcosub" class="k-button" type="submit" name="submitBtn" value="Add new Company" />
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

<div style="display:none">

<form id="addrowForm" action="/addrow">
<input type="text" name="dimension1Val" id="dimension1Val">
<input type="text" name="dimension1Name" id="dimension1Name">
<input type="text" name="dimension2Val" id="dimension2Val">
<input type="text" name="dimension2Name" id="dimension2Name">
<input type="text" name="dimension3Val" id="dimension3Val">
<input type="text" name="dimension3Name" id="dimension3Name">
<input type="text" name="dimension4Val" id="dimension4Val">
<input type="text" name="dimension4Name" id="dimension4Name">
<input type="text" name="dimension5Val" id="dimension5Val">
<input type="text" name="dimension5Name" id="dimension5Name">
<input type="text" name="quantAmt" id="quantAmt">
<input type="text" name="accessCurr" id="accessCurr" >
</form>
 </div>

<div style="display:none">

<form id="deleterowForm" action="/deleterow">
<input type="text" name="dimension1Val" id="dimension11Val">
<input type="text" name="dimension1Name" id="dimension11Name">
<input type="text" name="dimension2Val" id="dimension22Val">
<input type="text" name="dimension2Name" id="dimension22Name">
<input type="text" name="dimension3Val" id="dimension33Val">
<input type="text" name="dimension3Name" id="dimension33Name">
<input type="text" name="dimension4Val" id="dimension44Val">
<input type="text" name="dimension4Name" id="dimension44Name">
<input type="text" name="accessCurr" id="accessCurrr" >
</form>
 </div>
         
    <div id="dialog" title="Database update">
    <p id="pp34" >Database Update complete</p>
 </div>
 
     <div id="dialog44" title="Database update">
    <p id="pp44" >You have outstanding "EDIT" changes. To save to production press the RED save button</p>
 </div>
 
     <div id="dialogAddCompany" title="Add new Company">
     <p id="pap33" >Add new company name then submit</p>
     <input id="companyName" type="text" value="" />
    </div>
 
     <div id="dialogAdd" title="Add new row">
    <p id="pp33" >(You need to add the first quantity here before you can perform the normal row editing functions)</p>
    
    (Side grid column you wish to add)
     <div id="drop211" class="showornot2" style="display:none;">
<select class="dropdown222" id="drop211s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1a}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop222"  class="showornot2 viewable2" style="display:block">
<select class="dropdown222" id="drop222s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1b}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop233"  class="showornot2" style="display:none">
<select class="dropdown222" id="drop233s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1c}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop244"  class="showornot2" style="display:none">
<select class="dropdown222" id="drop244s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1d}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>


(Top grid column name you wish to add first quantity to)

 <div id="drop2111" class="showornot2" style="display:none;">
<select class="dropdown222" id="drop2111s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1a}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop2222"  class="showornot2 viewable2" style="display:block">
<select class="dropdown222" id="drop2222s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1b}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop2333"  class="showornot2" style="display:none">
<select class="dropdown222" id="drop2333s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1c}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop2444"  class="showornot2" style="display:none">
<select class="dropdown222" id="drop2444s" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1d}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

First Quantity:
<div    style="display:block">
<input type="text" id="quantityAmt" name="quantityAmt" value="" />
</div>


 </div>
 
      <div id="dialogDel" title="Delete row">
    <p id="pp333" >Delete row</p>
    
        (Side grid column you wish to delete)
     <div id="drop211z" class="showornot2" style="display:none;">
<select class="dropdown222" id="drop211sz" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1a}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop222z"  class="showornot2 viewable2" style="display:block">
<select class="dropdown222" id="drop222sz" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1b}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop233z"  class="showornot2" style="display:none">
<select class="dropdown222" id="drop233sz" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1c}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>

<div id="drop244z"  class="showornot2" style="display:none">
<select class="dropdown222" id="drop244sz" style="width:180px;margin:10px">
 <c:forEach var="drop1" items="${dropdown1d}">
  <option value="${drop1.id}">${drop1.name}&nbsp;</option>
  </c:forEach>
</select>
</div>
    
 </div>
 
 
 </div>
          <div id="success2Text" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:none;background:blue">
         Database update now taking place</div>
         

 </body>
  
	

</html>
