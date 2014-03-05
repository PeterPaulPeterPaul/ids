<!doctype html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
   <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="css/jquery-ui.css" />
<link rel="stylesheet" href="css/login.css" />
<link rel="stylesheet" href="css/ids.css" />
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
	});
	</script>
	
</head>
<style>
body { 
background-color:#FFFFFF;
}
</style>

<body class="login" style="margin: 20px 50px 50px;">


<!-- 	<div class="headerdiv" style="width:500px;">
	<span style="font-size:250%">International Database Service</span><br>
	<span style="font-size:150%">Off-Highway Research</span>
	</div> -->
<!-- 	<div class="box">
	<span class="IDSheader"> 
	<img class="leftLogo" src="images/IDS-Logo.png" width="50px" height="50px">
	International Database Service
	<img class="rightLogo" src="images/OHRLOGO-BnW.png"  >
	</span>
	</div> -->
	<div class="Loginbox">
	<span class="IDSheader"> 
	<p style="text-align:center; margin-top:2px">
    <img src="images/OHR-Logo-Medium.png" alt="Off-Highway Research"/>
    </p>  
	</span>
	</div>

	<div class="dbservicestyle" >
	<div style="margin-bottom:20px"> 	Welcome to the Database Services </div>
		<div class="HeaderPics">
		<img src="images/International-Computer-Logo-300A.jpg" alt="Off-Highway Research">
		</div>
		<div class="HeaderPics">
		<img src="images/Chinese-Computer-Logo-300A.jpg" alt="Off-Highway Research">
		</div>
		<div class="HeaderPics">
		<img src="images/India-Computer-Logo-300A.jpg" alt="Off-Highway Research">
		</div>
		<div class="HeaderPics" style="margin-left:1%"> International Database Service </div>
		<div class="HeaderPics"> Chinese Database Service </div>
		<div class="HeaderPics"> Indian Database Service </div>
	</div>

	<div style="height: 25%"></div>
	<div class="inner">
<!--  	<div class="inner1" style="height:100%;float:left;width:20px"> </div> -->


		<form id="territoryform" action="login" method="post">
		<table>
			<tr>
				<td width="100px">User Name</td>
				<td width="150px"><input class="input" id="userId" name="userId" type="text" value="" /></td>
			</tr>
			<tr>
				<td width="100px">Password</td>
				<td width="150px"><input class="input" id="password" name="password" type="password" value=""/></td>
			</tr>
			<tr id="showpass1" style="display:none" >
				<td width="100px">NEW Password</td>
				<td width="150px"><input class="input" id="firstNewPassword" name="firstNewPassword" type="password" /></td>
			</tr>
			<tr id="showpass2" style="display:none">
				<td width="100px">Reenter NEW Password</td>
				<td width="150px"><input class="input" id="secondNewPassword" name="secondNewPassword" type="password" /></td>
			</tr>
						<tr>
				<td></td>
				<td><input type="hidden" name="fromJsp" value="populated" /> <input
					id='submitLogin' type="submit" value="Login"><td>
			</tr>
		</table>
				</form>
		<br>
		<br>

	</div>

	<div id="errorText" style="color:white;font-size: large;text-align: center; vertical-align: middle;height:20px;display:${displaytype};background:#ff6666">${errortext}</div>

    <div id="dialog" title="Invalid user credentials!">
    <p id="pp3" >The login credentials you have entered are invalid. Please enter valid credentials</p>
 </div>
 <div style="height:50px;width:100%"></div>
<div class="box" id="banner" ></div> 

	<script type="text/javascript">
	 $(document).ready(function(){
						

						    
						    $("#dialog").dialog("close");
						    

							$("#submitLogin")
									.on(
											"click",
											function() {
												var myUserId = $("#userId").val();
												if ($.trim($("#password").val()).length < 8 
												|| 	$.trim($("#password").val())==$.trim($("#userId").val()) ) {
													$("#errorText").css("display","none");
													 $("#dialog").dialog("open");
													 return false;
												}

											});

						});
	</script>


</body>

</html>
