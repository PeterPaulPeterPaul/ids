<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href="css/login.css" />
<script type="text/javascript" src="js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<title>Login to IDS</title>

	<script type="text/javascript">
	
	$(function(){
	    var dialogOpts = {
	        modal: true,
	        autoOpen: false
	   };
	    $("#dialog").dialog(dialogOpts);
	    $("#dialog2").dialog(dialogOpts);
	    $("#dialog3").dialog(dialogOpts);
	});
	</script>
	
</head>
<style>
body { 
background-color:#FFFF80;
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
	             	<input type="button" id="upgradeUser" value="Upgrade User"/>
</span>
	</div>


        <div id="successText" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:${displaytype2};background:blue">${done}${successtext}</div>
	
		<div id="errorText" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:${displaytype};background:#ff6666">${errortext}</div>

        <div id="success2Text" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:none;background:blue"></div>
	
		<div id="error2Text" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:none;background:#ff6666"></div>


<div>

<table style="margin-left:120px;margin-top:10px;">


<tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp11" action="setup2?access=w" method="post" name="factsForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="${fileType}" /> 
   <td>Fact World:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile11"  type="file" /></td>
        <td>  <input id="one1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>
<tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp12" action="setup2?access=c" method="post" name="factsForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="${fileType}" /> 
   <td>Fact China:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile12"  type="file" /></td>
        <td>  <input id="one2" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>

<tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp13" action="setup2?access=i" method="post" name="factsForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="${fileType}" /> 
   <td>Fact India:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile13"  type="file" /></td>
        <td>  <input id="one3" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>

 <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp21" action="setup2?access=w" method="post" name="productsForm" enctype="multipart/form-data"   >

    <input type="hidden" name="table" value="products" /> 
    <td>Products World:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile21"  type="file" /></td>
        <td>  <input id="two1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>
 <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp22" action="setup2?access=c" method="post" name="productsForm" enctype="multipart/form-data"   >

    <input type="hidden" name="table" value="products" /> 
    <td>Products China:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile22"  type="file" /></td>
        <td>  <input id="two2" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>
 <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp23" action="setup2?access=i" method="post" name="productsForm" enctype="multipart/form-data"   >

    <input type="hidden" name="table" value="products" /> 
    <td>Products India:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile23"  type="file" /></td>
        <td>  <input id="two3" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>

    <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp31" action="setup2?access=w" method="post" name="countriesForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="countries" /> 
    <td>Countries World:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile31"  type="file" /></td>
       <td>   <input id="three1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>
    <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp32" action="setup2?access=c" method="post" name="countriesForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="countries" /> 
    <td>Countries China:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile32"  type="file" /></td>
       <td>   <input id="three2" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>
    <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp33" action="setup2?access=i" method="post" name="countriesForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="countries" /> 
    <td>Countries India:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile33"  type="file" /></td>
       <td>   <input id="three3" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>




  <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp41" action="setup2" method="post" name="companiesForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="companies" /> 
    <td>Companies World:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile41"  type="file" /></td>
      <td>    <input id="four1" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>
 <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp42" action="setup2" method="post" name="companiesForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="companies" /> 
    <td>Companies China:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile42"  type="file" /></td>
      <td>    <input id="four2" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>
 <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp43" action="setup2" method="post" name="companiesForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="companies" /> 
    <td>Companies India:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile43"  type="file" /></td>
      <td>    <input id="four3" class="k-button" type="submit" name="submitBtn" value="Upload" /></td>

</form>
</tr>




</tr>
  <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp6" action="setup3" method="post" name="testForm"   > 
    <td>Other download:</td><td>get Data </td>
      <td>    <input id="six" class="k-button" type="submit" name="submitBtn" value="test" /></td>

</form>
</tr>

</tr>
  <tr>
		<form style="height:25px;border:none;width:700px;background-color:#E0E0E0;" id="testUp6" action="setup2" method="post" name="othersForm" enctype="multipart/form-data"   >
     <input type="hidden" name="table" value="otherFacts" /> 
    <td>Other Load:</td><td> <input style="background-color:#E0E0E0;border:none" class="beenclicked" name="myfile${rowCount}" id="myfile5"  type="file" /></td>
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
                <div style="width:100%"><div style="width:99%">World: <input class="myrad2" type="checkbox" name="world" id="a11"  value="w"  />
                China: <input class="myrad2" type="checkbox" name="china" id="a22"  value="c"  />
                India: <input class="myrad2" type="checkbox" name="india" id="a33"  value="i"  /></div></div>
 
     </div>

 
             <div id="dialog2" title="Change Password">
    <div style="width:100%">To change password enter UserId and the new password</div>
                <div style="width:100%"><div style="width:30%">User Id: </div><div style="width:60%"><input type="text" id="userID" name="userID" value="" /></div></div>
                <div style="width:100%"><div style="width:30%">New Password: </div><div style="width:60%"><input type="password" id="newPass" name="newPass" value="" /></div></div>
 
     </div>
    
    
                <div id="dialog3" style="height:200px" title="Upgrade User">

    <div style="width:100%">Select user and new access type</div>
                <div style="width:100%"><div style="width:30%">User Name: </div><div style="width:60%"><input type="text" id="theirUserName2" name="theirUserName" value="" /></div></div>
                <div style="width:100%"><div style="width:30%">User Id: </div><div style="width:60%"><input type="text" id="theirId2" name="theirId" value="" /></div></div>
     
                <div style="width:100%"><div style="width:99%">World: <input class="myrad2" type="checkbox" name="world" id="a11"  value="w"  />
                China: <input class="myrad2" type="checkbox" name="china" id="a22"  value="c"  />
                India: <input class="myrad2" type="checkbox" name="india" id="a33"  value="i"  /></div>
 </div>
     </div>
    
	<script type="text/javascript">
	 $(document).ready(function(){
						

						    
						    $("#dialog").dialog("close");
						    $("#dialog2").dialog("close");
						    $("#dialog3").dialog("close");
						    
						    
						    
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
						    		       	          url: "/user?theirUserName="+$("#theirUserName2").val()
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
						                	if ($("#userID").val()=="" ||
						    				         $("#newPass").val()==""  ) {
						    				    	 alert("You need to enter: Id and new Password");
						    				    	 return false;
						    				     } else {
						    				    	 
						    			         
						    				         $.ajax({
						    		       	          url: "/user?newPass="+$("#newPass").val()
						    		       	        		  +"&userID="+$("#userID").val()+"&randNum=" + new Date().getTime(),
						    		       	        		  
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

							

						});
	</script>


</body>

</html>
