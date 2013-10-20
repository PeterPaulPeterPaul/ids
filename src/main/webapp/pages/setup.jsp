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
<link rel="stylesheet" type="text/css" media="screen" href="css/ids.css" />
<link rel="stylesheet" type="text/css" media="screen" href="css/ui.dropdownchecklist.themeroller.css" />

          <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/jquery.selectboxit/3.8.0/jquery.selectBoxIt.css" />

<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/ui.dropdownchecklist-1.4-min.js"></script>

          <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.selectboxit/3.8.0/jquery.selectBoxIt.min.js"></script>

<script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
<script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>
<title>Login to IDS</title>

	<script type="text/javascript">
	
	$(function(){
	    var dialogOpts = {
	        modal: true,
	        autoOpen: false
	   };
	    $("#dialog").dialog(dialogOpts);
	    $("#dialog2").dialog(dialogOpts);
	    $("#dialog2a").dialog(dialogOpts);
	    $("#dialog3").dialog(dialogOpts);
	});
	</script>
	
</head>
<style>
body { 
background-color:#FFFF00;
}
body.wait, body.wait *{
 cursor: wait !important;   
}
</style>

<body class="login" style="margin:50px">
	<div class="headerdiv" style="width:500px;">
	<span style="font-size:250%">International Database Service</span><br>
	<span style="font-size:150%">Off-Highway Research</span><br><br>
	<span><input type="image" name="close" id="closeit"  value="exit" src="images/exit.bmp" /></span>
	</div>
	<br><br><br><br>
	<div style="width:700px">
	<span style="margin-left:200px"><input type="button" id="createUser" value="Create User"/>
	             <input type="button" id="changePass" value="change Password"/>
	              <input type="button" id="lockUser" value="Lock or Unlock User"/>
	             	<input type="button" id="upgradeUser" value="Upgrade User"/>
</span>
	</div>


        <div id="successText" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:${displaytype2};background:blue">${done}${successtext}</div>
	
		<div id="errorText" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:${displaytype};background:#ff6666">${errortext}</div>

        <div id="success2Text" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:none;background:blue"></div>
	
		<div id="error2Text" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:none;background:#ff6666"></div>


<div>
<br>
<span style="margin-left:125px">IDS:</span><input type="radio" class="radIT" value="w" name="radio1" id="rad111" checked />
CDS: <input type="radio" class="radIT" value="c" name="radio1" id="rad112"/>
INDS: <input type="radio" class="radIT" value="i" name="radio1" id="rad113"/>

 </div>
<div>


<table style="margin-left:120px;margin-top:10px;">


<tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp11" action="setup2" method="post" name="factsForm1" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="facts" /> 
     <input type="hidden" class="accessType" name="access" value="" /> 
   <td>Fact:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="my1file${rowCount}" id="myfile11"  type="file" /></td>
        <td>  <input id="one1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
		
</tr>

<tr>

		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp21" action="setup2" method="post" name="productsForm4" enctype="multipart/form-data"   >

    <input type="hidden" name="table" value="products" /> 
    <input type="hidden" class="accessType" name="access" value="" /> 
    <td>Products:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="my4file${rowCount}" id="myfile21"  type="file" /></td>
        <td>  <input id="two1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>



    <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp31" action="setup2" method="post" name="countriesForm7" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="countries" /> 
     <input type="hidden" class="accessType" name="access" value="" /> 
    <td>Countries:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="my7file${rowCount}" id="myfile31"  type="file" /></td>
       <td>   <input id="three1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>

</tr>
    <tr>

		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp41" action="setup2" method="post" name="companiesForm11" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="companies" /> 
     <input type="hidden" class="accessType" name="access" value="" /> 
    <td>Companies:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="my11file${rowCount}" id="myfile41"  type="file" /></td>
      <td>    <input id="four1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>

 



  <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp66" action="setup3" method="post" name="testForm1"   > 
    <input type="hidden" class="accessType" name="access" value="" /> 
    <td>Other download:</td><td>get Data </td>
      <td>    <input id="six" class="k-button" type="submit" name="submitBtn" value="test" /></td>

</form>

		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp666" action="setup2" method="post" name="othersForm2" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="otherFacts" /> 
    <input type="hidden" class="accessType" name="access" value="" /> 
    <td>Other reLoad data:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="my14file${rowCount}" id="myfile5"  type="file" /></td>
      <td>    <input id="six" class="k-button" type="submit" name="submitBtn" value="reupload data" /></td>

</form>
</tr>


</table>

 </div>
 
 
 


    

    
    
    
            <div id="dialog" style="height:200px" title="Create User">

    <div style="width:100%">To create a user enter name, Id and password then press CREATE</div>
                <div style="width:100%"><div style="width:30%">User Name: </div><div style="width:60%"><input type="text" id="theirUserName2" name="theirUserName" value="" /></div></div>
                <div style="width:100%"><div style="width:30%">User Id: </div><div style="width:60%"><input type="text" id="theirId2" name="theirId" value="" /></div></div>
                <div style="width:100%"><div style="width:30%">Password: </div><div style="width:60%"><input type="password" id="theirPassword2" name="theirPassword" value="" /></div></div>
                <div style="width:100%"><div style="width:99%">Admin: <input class="myrad2" type="radio" name="access" id="a1"  value="a"  />
                Editor: <input class="myrad2" type="radio" name="access" id="a2"  value="e"  />
                Subscriber: <input class="myrad2" type="radio" name="access" id="a3"  value="s" checked /></div></div>
                <div style="width:100%"><div style="width:99%">IDS: <input class="myrad2" type="checkbox" name="world" id="a11"  value="w"  />
                CDS: <input class="myrad2" type="checkbox" name="china" id="a22"  value="c"  />
                INDS: <input class="myrad2" type="checkbox" name="india" id="a33"  value="i"  /></div></div>
 
     </div>

 
             <div id="dialog2" title="Change Password">
    <div style="width:100%">To change password enter UserId and the new password</div>
                <div style="width:100%"><div style="width:30%">User Id: </div><div style="width:60%"><select id="mySelUser2" name="mySelUser2">${options}</select></div></div>
                <div style="width:100%"><div style="width:30%">New Password: </div><div style="width:60%"><input type="password" id="newPass" name="newPass" value="" /></div></div>
 
     </div>
    
    
                 <div id="dialog2a" title="Lock or Unlock User">
    <div style="width:100%">To Lock or Unlock user select user Id</div>
                <div style="width:100%"><div style="width:30%">User Id: </div><div style="width:60%"><select id="mySelUser2a" name="mySelUser3">${options}</select></div></div>
                
                <div style="width:100%" id="setlockStat" ><div style="width:99%">Lock: <input class="myrad2" type="radio" name="lock" id="lock1"  value="1"  />
                Unlock: <input class="myrad2" type="radio" name="lock" id="lock2"  value="0"  /></div></div>
     </div>
    
                <div id="dialog3" style="height:200px" title="Upgrade User">

    <div style="width:100%">Select user and new access type</div>
                <div style="width:100%"><div style="width:30%">User Id: </div><div style="width:60%"><select id="mySelUser" name="mySelUser">${options}</select></div></div>
     
                <div style="width:100%" id="setboxes" ><div style="width:99%">IDS: <input class="myrad2" type="checkbox" name="world" id="a11"  value="w"  />
                CDS: <input class="myrad2" type="checkbox" name="china" id="a22"  value="c"  />
                INDS: <input class="myrad2" type="checkbox" name="india" id="a33"  value="i"  /></div>
 </div>
     </div>
    
	<script type="text/javascript">
	 $(document).ready(function(){
						

		 var accessIT =  $('input[name=radio1]:checked' ).val();
   	  $('#testUp11').get(0).setAttribute('action', 'setup2?access='+accessIT); 
   	  $('#testUp21').get(0).setAttribute('action', 'setup2?access='+accessIT); 
   	  $('#testUp31').get(0).setAttribute('action', 'setup2?access='+accessIT); 
   	  $('#testUp41').get(0).setAttribute('action', 'setup2?access='+accessIT); 
   	  $('#testUp66').get(0).setAttribute('action', 'setup2?access='+accessIT); 
   	  $('#testUp666').get(0).setAttribute('action', 'setup2?access='+accessIT); 
	 
	      $(".radIT").on("change", function() {
	    	  
	    	  var accessIT =  $('input[name=radio1]:checked' ).val();
	    	  $('#testUp11').get(0).setAttribute('action', 'setup2?access='+accessIT); 
	    	  $('#testUp21').get(0).setAttribute('action', 'setup2?access='+accessIT); 
	    	  $('#testUp31').get(0).setAttribute('action', 'setup2?access='+accessIT); 
	    	  $('#testUp41').get(0).setAttribute('action', 'setup2?access='+accessIT); 
	    	  $('#testUp66').get(0).setAttribute('action', 'setup2?access='+accessIT); 
	    	  $('#testUp666').get(0).setAttribute('action', 'setup2?access='+accessIT); 
	    	//  $(".accessType").val( $('input[name=radio1]:checked' ).val() );
	      });

	      
						    $("#dialog").dialog("close");
						    $("#dialog2").dialog("close");
						    $("#dialog2a").dialog("close");
						    $("#dialog3").dialog("close");
						    
						    
						    $("#mySelUser").on("change", function(e) {
						    	$.ajax({
		    		       	          url: "${ajaxPrefix}getAccess?myUserId="+$("#mySelUser").val()+
		    		       	        		  "&randNum=" + new Date().getTime(),
		    		       	        		  
		    		       	          type: 'GET',
		    		       	          contentType: 'application/html',
		    		       	          processData: false,
		    		       	          async: false,
		    		       	          dataType: 'html',
		    		       	          success: function(data) { 
		    		       	        	  $("#setboxes").html(data);
		    		       	        	return false;
		    		       	          },
		    		    	          error: function (xhr, ajaxOptions, thrownError) {
		    		    	        	  alert("error");
		    		    	              alert(xhr.status);
		    		    	              alert(thrownError);
		    		    	            }

		    		    	      });
						    	
						    	
						    	
						    	
						    });
						    

						    $("#upgradeUser").on("click", function(e) {
						    	$("#SuccessText").css("display","none");
								 $("#errorText").css("display","none");
								 
								 $.ajax({
		    		       	          url: "${ajaxPrefix}getAccess?myUserId="+$("#mySelUser").val()+
		    		       	        		  "&randNum=" + new Date().getTime(),
		    		       	        		  
		    		       	          type: 'GET',
		    		       	          contentType: 'application/html',
		    		       	          processData: false,
		    		       	          async: false,
		    		       	          dataType: 'html',
		    		       	          success: function(data) { 
		    		       	        	  $("#setboxes").html(data);
		    		       	        	return false;
		    		       	          },
		    		    	          error: function (xhr, ajaxOptions, thrownError) {
		    		    	        	  alert("error");
		    		    	              alert(xhr.status);
		    		    	              alert(thrownError);
		    		    	            }

		    		    	      });
								 
								 e.preventDefault();
								 
								 
									var dialogOpts2 = {
								            modal: true,
								            width: 500,
								            height: 300,
								            buttons: [{
								                text: "Upgrade",
								                click : function() {    
								                	
								                	
								    				    	 
											                	var access="";
											                	var world="0";
											                	var china="0";
											                	var india="0";
											                	if($('#a1').is(':checked')){
											                		access="a";
											                	}
											                	if($('#a2').is(':checked')){
											                		access="e";
											                	}
											                	if($('#a3').is(':checked')){
											                		access="s";
											                	}
											                	if($('#a1world').is(':checked')){
											                		world="1";
											                	}
											                	if($('#a1china').is(':checked')){
											                		china="1";
											                	}
											                	if($('#a1india').is(':checked')){
											                		india="1";
											                	}
								    			         
								    				         $.ajax({
								    		       	          url: "${ajaxPrefix}upgrade?userId="+$("#mySelUser").val()+
								    		       	        		  "&world="+world+
								    		       	        		  "&china="+china+
								    		       	        		  "&india="+india+
								    		       	        		  "&randNum=" + new Date().getTime(),
								    		       	        		  
								    		       	          type: 'GET',
								    		       	          contentType: 'application/html',
								    		       	          processData: false,
								    		       	          async: false,
								    		       	          dataType: 'html',
								    		       	          success: function(data) { 

								    		       	   
								    		       	        		  $("#success2Text").text(data);
								    		       	        		  $("#success2Text").css("display","block");
								    		       	        		 $("#error2Text").css("display","none");
								    		       	  		    	 
								    		       	        		  $("#dialog3").dialog("close");

								    		       	        	  return false;
								    		       	          },
								    		    	          error: function (xhr, ajaxOptions, thrownError) {
								    		    	        	  alert("error");
								    		    	              alert(xhr.status);
								    		    	              alert(thrownError);
								    		    	            }

								    		    	      });
								    				         
							
								                }

								                }, {
								                text: "Cancel",
								                click: function() {
								                  $( this ).dialog( "close" );
								                    $(this).dialog(dialogOpts).dialog("close"); //return false; 
								                    return false;
								                } 
								                
								                }]
								       };
									
									
										 $("#dialog3").dialog(dialogOpts2).dialog("open");

				
									});
				
						    
						    
						    
								 
							$("#createUser").on("click", function(e) {
								 $("#SuccessText").css("display","none");
								 $("#errorText").css("display","none");
								 
								 e.preventDefault();
								 
							//	 $("#dialog").dialog(dialogOpts2).dialog("close");
								 
							//alert("1");

							
							var dialogOpts2 = {
						            modal: true,
						            width: 500,
						            height: 300,
						            buttons: [{
						                text: "Create",
						                click : function() {    
						                	
						                	if ($("#theirUserName2").val()=="" ||
						    				         $("#theirId2").val()=="" ||
						    				         $("#theirPassword2").val()=="" ) {
						    				    	 alert("You need to enter: Id, password and user name");
						    				    	 return false;
						    				     } else {
						    				    	 
									                	var access="";
									                	var world="0";
									                	var china="0";
									                	var india="0";
									                	if($('#a1').is(':checked')){
									                		access="a";
									                	}
									                	if($('#a2').is(':checked')){
									                		access="e";
									                	}
									                	if($('#a3').is(':checked')){
									                		access="s";
									                	}
									                	if($('#a11').is(':checked')){
									                		world="1";
									                	}
									                	if($('#a22').is(':checked')){
									                		china="1";
									                	}
									                	if($('#a33').is(':checked')){
									                		india="1";
									                	}
						    			         
						    				         $.ajax({
						    		       	          url: "${ajaxPrefix}user?theirUserName="+$("#theirUserName2").val()
						    		       	        		  +"&theirId="+$("#theirId2").val()+"&theirPassword="+$("#theirPassword2").val()+
						    		       	        		  "&access="+access+
						    		       	        		  "&world="+world+
						    		       	        		  "&china="+china+
						    		       	        		  "&india="+india+
						    		       	        		  "&randNum=" + new Date().getTime(),
						    		       	        		  
						    		       	          type: 'GET',
						    		       	          contentType: 'application/html',
						    		       	          processData: false,
						    		       	          async: false,
						    		       	          dataType: 'html',
						    		       	          success: function(data) { 

						    		       	        	  if (data=="User successfully created!"){
						    		       	        		  $("#success2Text").text(data);
						    		       	        		  $("#success2Text").css("display","block");
						    		       	        		 $("#error2Text").css("display","none");
						    		       	        		  
						    		       	  		          $("#theirUserName2").val("");
						    		       	 		          $("#theirId2").val("");
						    		       	 		          $("#theirPassword2").val("");
						    		       	  		    	 
						    		       	        		  $("#dialog").dialog("close");

						    		       	        	  } else {
						    		       	        		  $("#error2Text").text(data);
						    		       	        		 $("#success2Text").css("display","none");
						    		       	        		  $("#error2Text").css("display","block");
						    		       	        		  
						    		       	        		 $("#theirPassword2").val("");
						    		       	        	  }
						    		       	        	  return false;
						    		       	          },
						    		    	          error: function (xhr, ajaxOptions, thrownError) {
						    		    	        	  alert("error");
						    		    	              alert(xhr.status);
						    		    	              alert(thrownError);
						    		    	            }

						    		    	      });
						    				         
						    				     }
						                }

						                }, {
						                text: "Cancel",
						                click: function() {
						                  $( this ).dialog( "close" );
						                    $(this).dialog(dialogOpts).dialog("close"); //return false; 
						                    return false;
						                } 
						                
						                }]
						       };
							
							
								 $("#dialog").dialog(dialogOpts2).dialog("open");

		
							});
						    
						    
						    
						    
						    
					
							$("#changePass").on("click", function(e) {
								 $("#SuccessText").css("display","none");
								 $("#errorText").css("display","none");
								 
								 e.preventDefault();
								 
							//	 $("#dialog").dialog(dialogOpts2).dialog("close");
								 
							//alert("1");
							
							
							var dialogOpts3 = {
						            modal: true,
						            width: 500,
						            height: 300,
						            buttons: [{
						                text: "Change Password",
						                click : function() {    
						                	if(
						    				         $("#newPass").val()==""  ) {
						    				    	 alert("You need to enter:  new Password");
						    				    	 return false;
						    				     } else {
						    				    	 
						    			         
						    				         $.ajax({
						    		       	          url: "${ajaxPrefix}user?newPass="+$("#newPass").val()
						    		       	        		  +"&userID="+$("#mySelUser2").val()+"&randNum=" + new Date().getTime(),
						    		       	        		  
						    		       	          type: 'GET',
						    		       	          contentType: 'application/html',
						    		       	          processData: false,
						    		       	          async: false,
						    		       	          dataType: 'html',
						    		       	          success: function(data) { 

						    		       	        	  if (data=="Password successfully changed!"){
						    		       	        		  $("#success2Text").text(data);
						    		       	        		  $("#success2Text").css("display","block");
						    		       	        		 $("#error2Text").css("display","none");
						    		       	        		  
						    		       	  		          $("#userID").val("");
						    		       	 		          $("#newPass").val("");
						    		       	  		    	 
						    		       	        		  $("#dialog2").dialog("close");

						    		       	        	  } else {
						    		       	        		  $("#error2Text").text(data);
						    		       	        		 $("#success2Text").css("display","none");
						    		       	        		  $("#error2Text").css("display","block");
						    		       	        		  
						    		       	        		 $("#newPass").val("");
						    		       	        	  }
						    		       	        	  return false;
						    		       	          },
						    		    	          error: function (xhr, ajaxOptions, thrownError) {
						    		    	        	  alert("error");
						    		    	              alert(xhr.status);
						    		    	              alert(thrownError);
						    		    	            }

						    		    	      });
						    				         
						    				     }
						                }

						                }, {
						                text: "Cancel",
						                click: function() {
						                  $( this ).dialog( "close" );
						                    $(this).dialog(dialogOpts).dialog("close"); //return false; 
						                    return false;
						                } 
						                
						                }]
						       };
							
							
								 $("#dialog2").dialog(dialogOpts3).dialog("open");

		
							});
						    
						    
						    
						    
							$("#lockUser").on("click", function(e) {
								 $("#SuccessText").css("display","none");
								 $("#errorText").css("display","none");
								 
								 e.preventDefault();
								 
							//	 $("#dialog").dialog(dialogOpts2).dialog("close");
								 
							//alert("1");
							
							
							var dialogOpts3 = {
						            modal: true,
						            width: 500,
						            height: 300,
						            buttons: [{
						                text: "Lock or Unlock User",
						                click : function() {    
						                	
						                	var locked="";
						                	if($('#lock1').is(':checked')){
						                		locked="1";
						                	}
						                	if($('#lock2').is(':checked')){
						                		locked="0";
						                	}
  
						    				         $.ajax({
						    		       	          url: "${ajaxPrefix}user?lockedUserID="+$("#mySelUser2").val()+"&locked="+locked+"&randNum=" + new Date().getTime(),
						    		       	        		  
						    		       	          type: 'GET',
						    		       	          contentType: 'application/html',
						    		       	          processData: false,
						    		       	          async: false,
						    		       	          dataType: 'html',
						    		       	          success: function(data) { 

						    		       	        		  $("#success2Text").text(data);
						    		       	        		  $("#success2Text").css("display","block");
						    		       	        		 $("#error2Text").css("display","none");
						    		       	        		  $("#dialog2a").dialog("close");

						    		       	        	  return false;
						    		       	          },
						    		    	          error: function (xhr, ajaxOptions, thrownError) {
						    		    	        	  alert("error");
						    		    	              alert(xhr.status);
						    		    	              alert(thrownError);
						    		    	            }

						    		    	      });
	
						                }

						                }, {
						                text: "Cancel",
						                click: function() {
						                  $( this ).dialog( "close" );
						                    $(this).dialog(dialogOpts).dialog("close"); //return false; 
						                    return false;
						                } 
						                
						                }]
						       };
							
							
								 $("#dialog2a").dialog(dialogOpts3).dialog("open");

		
							});
							
							
							
						    
						    
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

							

						});
	</script>


</body>

</html>
