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
<title>Print Preview</title>
<style type="text/css" media="print"> .noprint {visibility: hidden;} </style>

</head>
<body>
<br>
<input class='noprint' type="image" onClick="window.print()" name="printer"  src="images/printer.jpg" />
<input class='noprint' type="image" name="close" id="closeit"  src="images/exit.bmp" />
<br>

${printTable}


          <script type="text/javascript">
          
          
          $(document).ready(function(){
        	  
          
 $("#closeit").on("click",function(){
        		 
	 window.close();
				      });
				      
 });
	 </script>
	 
</body>


</html>
