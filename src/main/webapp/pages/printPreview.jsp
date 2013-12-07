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
<style type="text/css" media="screen">

table{
border-collapse:collapse;
border:1px solid black;
}

table td{
border:1px solid black;
}
</style>

<style type="text/css" media="print">
      .noprint {visibility: hidden;}
     .pagebreak {page-break-before: always}
     td {font-family: Verdana,Arial,sans-serif;
           font-size:11px;border: 1px solid black;
           display: table-cell;
vertical-align: inherit;
     }
     table{
border-collapse:collapse;
border:1px solid black;
}

 </style>

</head>
<body>
<br>
<input class='noprint' type="image" onClick="window.print()" name="printer"  src="images/print-icon-32.png" />
<input class='noprint' type="image" name="close" id="closeit"  src="images/deletered-32.png" />
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
